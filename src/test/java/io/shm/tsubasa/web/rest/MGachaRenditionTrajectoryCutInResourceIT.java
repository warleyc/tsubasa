package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionTrajectoryCutIn;
import io.shm.tsubasa.repository.MGachaRenditionTrajectoryCutInRepository;
import io.shm.tsubasa.service.MGachaRenditionTrajectoryCutInService;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCutInDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTrajectoryCutInMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCutInCriteria;
import io.shm.tsubasa.service.MGachaRenditionTrajectoryCutInQueryService;

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
 * Integration tests for the {@Link MGachaRenditionTrajectoryCutInResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionTrajectoryCutInResourceIT {

    private static final Integer DEFAULT_RENDITION_ID = 1;
    private static final Integer UPDATED_RENDITION_ID = 2;

    private static final Integer DEFAULT_TRAJECTORY_TYPE = 1;
    private static final Integer UPDATED_TRAJECTORY_TYPE = 2;

    private static final String DEFAULT_SPRITE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPRITE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_VOICE = "AAAAAAAAAA";
    private static final String UPDATED_VOICE = "BBBBBBBBBB";

    private static final Integer DEFAULT_EXCEPT_KICKER_ID = 1;
    private static final Integer UPDATED_EXCEPT_KICKER_ID = 2;

    @Autowired
    private MGachaRenditionTrajectoryCutInRepository mGachaRenditionTrajectoryCutInRepository;

    @Autowired
    private MGachaRenditionTrajectoryCutInMapper mGachaRenditionTrajectoryCutInMapper;

    @Autowired
    private MGachaRenditionTrajectoryCutInService mGachaRenditionTrajectoryCutInService;

    @Autowired
    private MGachaRenditionTrajectoryCutInQueryService mGachaRenditionTrajectoryCutInQueryService;

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

    private MockMvc restMGachaRenditionTrajectoryCutInMockMvc;

    private MGachaRenditionTrajectoryCutIn mGachaRenditionTrajectoryCutIn;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionTrajectoryCutInResource mGachaRenditionTrajectoryCutInResource = new MGachaRenditionTrajectoryCutInResource(mGachaRenditionTrajectoryCutInService, mGachaRenditionTrajectoryCutInQueryService);
        this.restMGachaRenditionTrajectoryCutInMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionTrajectoryCutInResource)
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
    public static MGachaRenditionTrajectoryCutIn createEntity(EntityManager em) {
        MGachaRenditionTrajectoryCutIn mGachaRenditionTrajectoryCutIn = new MGachaRenditionTrajectoryCutIn()
            .renditionId(DEFAULT_RENDITION_ID)
            .trajectoryType(DEFAULT_TRAJECTORY_TYPE)
            .spriteName(DEFAULT_SPRITE_NAME)
            .weight(DEFAULT_WEIGHT)
            .voice(DEFAULT_VOICE)
            .exceptKickerId(DEFAULT_EXCEPT_KICKER_ID);
        return mGachaRenditionTrajectoryCutIn;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionTrajectoryCutIn createUpdatedEntity(EntityManager em) {
        MGachaRenditionTrajectoryCutIn mGachaRenditionTrajectoryCutIn = new MGachaRenditionTrajectoryCutIn()
            .renditionId(UPDATED_RENDITION_ID)
            .trajectoryType(UPDATED_TRAJECTORY_TYPE)
            .spriteName(UPDATED_SPRITE_NAME)
            .weight(UPDATED_WEIGHT)
            .voice(UPDATED_VOICE)
            .exceptKickerId(UPDATED_EXCEPT_KICKER_ID);
        return mGachaRenditionTrajectoryCutIn;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionTrajectoryCutIn = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionTrajectoryCutIn() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionTrajectoryCutInRepository.findAll().size();

        // Create the MGachaRenditionTrajectoryCutIn
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO = mGachaRenditionTrajectoryCutInMapper.toDto(mGachaRenditionTrajectoryCutIn);
        restMGachaRenditionTrajectoryCutInMockMvc.perform(post("/api/m-gacha-rendition-trajectory-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryCutInDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionTrajectoryCutIn in the database
        List<MGachaRenditionTrajectoryCutIn> mGachaRenditionTrajectoryCutInList = mGachaRenditionTrajectoryCutInRepository.findAll();
        assertThat(mGachaRenditionTrajectoryCutInList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionTrajectoryCutIn testMGachaRenditionTrajectoryCutIn = mGachaRenditionTrajectoryCutInList.get(mGachaRenditionTrajectoryCutInList.size() - 1);
        assertThat(testMGachaRenditionTrajectoryCutIn.getRenditionId()).isEqualTo(DEFAULT_RENDITION_ID);
        assertThat(testMGachaRenditionTrajectoryCutIn.getTrajectoryType()).isEqualTo(DEFAULT_TRAJECTORY_TYPE);
        assertThat(testMGachaRenditionTrajectoryCutIn.getSpriteName()).isEqualTo(DEFAULT_SPRITE_NAME);
        assertThat(testMGachaRenditionTrajectoryCutIn.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionTrajectoryCutIn.getVoice()).isEqualTo(DEFAULT_VOICE);
        assertThat(testMGachaRenditionTrajectoryCutIn.getExceptKickerId()).isEqualTo(DEFAULT_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void createMGachaRenditionTrajectoryCutInWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionTrajectoryCutInRepository.findAll().size();

        // Create the MGachaRenditionTrajectoryCutIn with an existing ID
        mGachaRenditionTrajectoryCutIn.setId(1L);
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO = mGachaRenditionTrajectoryCutInMapper.toDto(mGachaRenditionTrajectoryCutIn);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionTrajectoryCutInMockMvc.perform(post("/api/m-gacha-rendition-trajectory-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryCutInDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionTrajectoryCutIn in the database
        List<MGachaRenditionTrajectoryCutIn> mGachaRenditionTrajectoryCutInList = mGachaRenditionTrajectoryCutInRepository.findAll();
        assertThat(mGachaRenditionTrajectoryCutInList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRenditionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTrajectoryCutInRepository.findAll().size();
        // set the field null
        mGachaRenditionTrajectoryCutIn.setRenditionId(null);

        // Create the MGachaRenditionTrajectoryCutIn, which fails.
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO = mGachaRenditionTrajectoryCutInMapper.toDto(mGachaRenditionTrajectoryCutIn);

        restMGachaRenditionTrajectoryCutInMockMvc.perform(post("/api/m-gacha-rendition-trajectory-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryCutInDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTrajectoryCutIn> mGachaRenditionTrajectoryCutInList = mGachaRenditionTrajectoryCutInRepository.findAll();
        assertThat(mGachaRenditionTrajectoryCutInList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrajectoryTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTrajectoryCutInRepository.findAll().size();
        // set the field null
        mGachaRenditionTrajectoryCutIn.setTrajectoryType(null);

        // Create the MGachaRenditionTrajectoryCutIn, which fails.
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO = mGachaRenditionTrajectoryCutInMapper.toDto(mGachaRenditionTrajectoryCutIn);

        restMGachaRenditionTrajectoryCutInMockMvc.perform(post("/api/m-gacha-rendition-trajectory-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryCutInDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTrajectoryCutIn> mGachaRenditionTrajectoryCutInList = mGachaRenditionTrajectoryCutInRepository.findAll();
        assertThat(mGachaRenditionTrajectoryCutInList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTrajectoryCutInRepository.findAll().size();
        // set the field null
        mGachaRenditionTrajectoryCutIn.setWeight(null);

        // Create the MGachaRenditionTrajectoryCutIn, which fails.
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO = mGachaRenditionTrajectoryCutInMapper.toDto(mGachaRenditionTrajectoryCutIn);

        restMGachaRenditionTrajectoryCutInMockMvc.perform(post("/api/m-gacha-rendition-trajectory-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryCutInDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTrajectoryCutIn> mGachaRenditionTrajectoryCutInList = mGachaRenditionTrajectoryCutInRepository.findAll();
        assertThat(mGachaRenditionTrajectoryCutInList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExceptKickerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTrajectoryCutInRepository.findAll().size();
        // set the field null
        mGachaRenditionTrajectoryCutIn.setExceptKickerId(null);

        // Create the MGachaRenditionTrajectoryCutIn, which fails.
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO = mGachaRenditionTrajectoryCutInMapper.toDto(mGachaRenditionTrajectoryCutIn);

        restMGachaRenditionTrajectoryCutInMockMvc.perform(post("/api/m-gacha-rendition-trajectory-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryCutInDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTrajectoryCutIn> mGachaRenditionTrajectoryCutInList = mGachaRenditionTrajectoryCutInRepository.findAll();
        assertThat(mGachaRenditionTrajectoryCutInList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutIns() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList
        restMGachaRenditionTrajectoryCutInMockMvc.perform(get("/api/m-gacha-rendition-trajectory-cut-ins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionTrajectoryCutIn.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].trajectoryType").value(hasItem(DEFAULT_TRAJECTORY_TYPE)))
            .andExpect(jsonPath("$.[*].spriteName").value(hasItem(DEFAULT_SPRITE_NAME.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].voice").value(hasItem(DEFAULT_VOICE.toString())))
            .andExpect(jsonPath("$.[*].exceptKickerId").value(hasItem(DEFAULT_EXCEPT_KICKER_ID)));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionTrajectoryCutIn() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get the mGachaRenditionTrajectoryCutIn
        restMGachaRenditionTrajectoryCutInMockMvc.perform(get("/api/m-gacha-rendition-trajectory-cut-ins/{id}", mGachaRenditionTrajectoryCutIn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionTrajectoryCutIn.getId().intValue()))
            .andExpect(jsonPath("$.renditionId").value(DEFAULT_RENDITION_ID))
            .andExpect(jsonPath("$.trajectoryType").value(DEFAULT_TRAJECTORY_TYPE))
            .andExpect(jsonPath("$.spriteName").value(DEFAULT_SPRITE_NAME.toString()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.voice").value(DEFAULT_VOICE.toString()))
            .andExpect(jsonPath("$.exceptKickerId").value(DEFAULT_EXCEPT_KICKER_ID));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByRenditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("renditionId.equals=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("renditionId.equals=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByRenditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId in DEFAULT_RENDITION_ID or UPDATED_RENDITION_ID
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("renditionId.in=" + DEFAULT_RENDITION_ID + "," + UPDATED_RENDITION_ID);

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("renditionId.in=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByRenditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId is not null
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("renditionId.specified=true");

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId is null
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("renditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByRenditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId greater than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("renditionId.greaterOrEqualThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId greater than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("renditionId.greaterOrEqualThan=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByRenditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId less than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("renditionId.lessThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionTrajectoryCutInList where renditionId less than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("renditionId.lessThan=" + UPDATED_RENDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByTrajectoryTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType equals to DEFAULT_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("trajectoryType.equals=" + DEFAULT_TRAJECTORY_TYPE);

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType equals to UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("trajectoryType.equals=" + UPDATED_TRAJECTORY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByTrajectoryTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType in DEFAULT_TRAJECTORY_TYPE or UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("trajectoryType.in=" + DEFAULT_TRAJECTORY_TYPE + "," + UPDATED_TRAJECTORY_TYPE);

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType equals to UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("trajectoryType.in=" + UPDATED_TRAJECTORY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByTrajectoryTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType is not null
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("trajectoryType.specified=true");

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType is null
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("trajectoryType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByTrajectoryTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType greater than or equals to DEFAULT_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("trajectoryType.greaterOrEqualThan=" + DEFAULT_TRAJECTORY_TYPE);

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType greater than or equals to UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("trajectoryType.greaterOrEqualThan=" + UPDATED_TRAJECTORY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByTrajectoryTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType less than or equals to DEFAULT_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("trajectoryType.lessThan=" + DEFAULT_TRAJECTORY_TYPE);

        // Get all the mGachaRenditionTrajectoryCutInList where trajectoryType less than or equals to UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("trajectoryType.lessThan=" + UPDATED_TRAJECTORY_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTrajectoryCutInList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionTrajectoryCutInList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where weight is not null
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionTrajectoryCutInList where weight is null
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTrajectoryCutInList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTrajectoryCutInList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByExceptKickerIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId equals to DEFAULT_EXCEPT_KICKER_ID
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("exceptKickerId.equals=" + DEFAULT_EXCEPT_KICKER_ID);

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId equals to UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("exceptKickerId.equals=" + UPDATED_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByExceptKickerIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId in DEFAULT_EXCEPT_KICKER_ID or UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("exceptKickerId.in=" + DEFAULT_EXCEPT_KICKER_ID + "," + UPDATED_EXCEPT_KICKER_ID);

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId equals to UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("exceptKickerId.in=" + UPDATED_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByExceptKickerIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId is not null
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("exceptKickerId.specified=true");

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId is null
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("exceptKickerId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByExceptKickerIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId greater than or equals to DEFAULT_EXCEPT_KICKER_ID
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("exceptKickerId.greaterOrEqualThan=" + DEFAULT_EXCEPT_KICKER_ID);

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId greater than or equals to UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("exceptKickerId.greaterOrEqualThan=" + UPDATED_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryCutInsByExceptKickerIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId less than or equals to DEFAULT_EXCEPT_KICKER_ID
        defaultMGachaRenditionTrajectoryCutInShouldNotBeFound("exceptKickerId.lessThan=" + DEFAULT_EXCEPT_KICKER_ID);

        // Get all the mGachaRenditionTrajectoryCutInList where exceptKickerId less than or equals to UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionTrajectoryCutInShouldBeFound("exceptKickerId.lessThan=" + UPDATED_EXCEPT_KICKER_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionTrajectoryCutInShouldBeFound(String filter) throws Exception {
        restMGachaRenditionTrajectoryCutInMockMvc.perform(get("/api/m-gacha-rendition-trajectory-cut-ins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionTrajectoryCutIn.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].trajectoryType").value(hasItem(DEFAULT_TRAJECTORY_TYPE)))
            .andExpect(jsonPath("$.[*].spriteName").value(hasItem(DEFAULT_SPRITE_NAME.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].voice").value(hasItem(DEFAULT_VOICE.toString())))
            .andExpect(jsonPath("$.[*].exceptKickerId").value(hasItem(DEFAULT_EXCEPT_KICKER_ID)));

        // Check, that the count call also returns 1
        restMGachaRenditionTrajectoryCutInMockMvc.perform(get("/api/m-gacha-rendition-trajectory-cut-ins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionTrajectoryCutInShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionTrajectoryCutInMockMvc.perform(get("/api/m-gacha-rendition-trajectory-cut-ins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionTrajectoryCutInMockMvc.perform(get("/api/m-gacha-rendition-trajectory-cut-ins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionTrajectoryCutIn() throws Exception {
        // Get the mGachaRenditionTrajectoryCutIn
        restMGachaRenditionTrajectoryCutInMockMvc.perform(get("/api/m-gacha-rendition-trajectory-cut-ins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionTrajectoryCutIn() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        int databaseSizeBeforeUpdate = mGachaRenditionTrajectoryCutInRepository.findAll().size();

        // Update the mGachaRenditionTrajectoryCutIn
        MGachaRenditionTrajectoryCutIn updatedMGachaRenditionTrajectoryCutIn = mGachaRenditionTrajectoryCutInRepository.findById(mGachaRenditionTrajectoryCutIn.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionTrajectoryCutIn are not directly saved in db
        em.detach(updatedMGachaRenditionTrajectoryCutIn);
        updatedMGachaRenditionTrajectoryCutIn
            .renditionId(UPDATED_RENDITION_ID)
            .trajectoryType(UPDATED_TRAJECTORY_TYPE)
            .spriteName(UPDATED_SPRITE_NAME)
            .weight(UPDATED_WEIGHT)
            .voice(UPDATED_VOICE)
            .exceptKickerId(UPDATED_EXCEPT_KICKER_ID);
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO = mGachaRenditionTrajectoryCutInMapper.toDto(updatedMGachaRenditionTrajectoryCutIn);

        restMGachaRenditionTrajectoryCutInMockMvc.perform(put("/api/m-gacha-rendition-trajectory-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryCutInDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionTrajectoryCutIn in the database
        List<MGachaRenditionTrajectoryCutIn> mGachaRenditionTrajectoryCutInList = mGachaRenditionTrajectoryCutInRepository.findAll();
        assertThat(mGachaRenditionTrajectoryCutInList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionTrajectoryCutIn testMGachaRenditionTrajectoryCutIn = mGachaRenditionTrajectoryCutInList.get(mGachaRenditionTrajectoryCutInList.size() - 1);
        assertThat(testMGachaRenditionTrajectoryCutIn.getRenditionId()).isEqualTo(UPDATED_RENDITION_ID);
        assertThat(testMGachaRenditionTrajectoryCutIn.getTrajectoryType()).isEqualTo(UPDATED_TRAJECTORY_TYPE);
        assertThat(testMGachaRenditionTrajectoryCutIn.getSpriteName()).isEqualTo(UPDATED_SPRITE_NAME);
        assertThat(testMGachaRenditionTrajectoryCutIn.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionTrajectoryCutIn.getVoice()).isEqualTo(UPDATED_VOICE);
        assertThat(testMGachaRenditionTrajectoryCutIn.getExceptKickerId()).isEqualTo(UPDATED_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionTrajectoryCutIn() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionTrajectoryCutInRepository.findAll().size();

        // Create the MGachaRenditionTrajectoryCutIn
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO = mGachaRenditionTrajectoryCutInMapper.toDto(mGachaRenditionTrajectoryCutIn);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionTrajectoryCutInMockMvc.perform(put("/api/m-gacha-rendition-trajectory-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryCutInDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionTrajectoryCutIn in the database
        List<MGachaRenditionTrajectoryCutIn> mGachaRenditionTrajectoryCutInList = mGachaRenditionTrajectoryCutInRepository.findAll();
        assertThat(mGachaRenditionTrajectoryCutInList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionTrajectoryCutIn() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryCutInRepository.saveAndFlush(mGachaRenditionTrajectoryCutIn);

        int databaseSizeBeforeDelete = mGachaRenditionTrajectoryCutInRepository.findAll().size();

        // Delete the mGachaRenditionTrajectoryCutIn
        restMGachaRenditionTrajectoryCutInMockMvc.perform(delete("/api/m-gacha-rendition-trajectory-cut-ins/{id}", mGachaRenditionTrajectoryCutIn.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionTrajectoryCutIn> mGachaRenditionTrajectoryCutInList = mGachaRenditionTrajectoryCutInRepository.findAll();
        assertThat(mGachaRenditionTrajectoryCutInList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionTrajectoryCutIn.class);
        MGachaRenditionTrajectoryCutIn mGachaRenditionTrajectoryCutIn1 = new MGachaRenditionTrajectoryCutIn();
        mGachaRenditionTrajectoryCutIn1.setId(1L);
        MGachaRenditionTrajectoryCutIn mGachaRenditionTrajectoryCutIn2 = new MGachaRenditionTrajectoryCutIn();
        mGachaRenditionTrajectoryCutIn2.setId(mGachaRenditionTrajectoryCutIn1.getId());
        assertThat(mGachaRenditionTrajectoryCutIn1).isEqualTo(mGachaRenditionTrajectoryCutIn2);
        mGachaRenditionTrajectoryCutIn2.setId(2L);
        assertThat(mGachaRenditionTrajectoryCutIn1).isNotEqualTo(mGachaRenditionTrajectoryCutIn2);
        mGachaRenditionTrajectoryCutIn1.setId(null);
        assertThat(mGachaRenditionTrajectoryCutIn1).isNotEqualTo(mGachaRenditionTrajectoryCutIn2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionTrajectoryCutInDTO.class);
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO1 = new MGachaRenditionTrajectoryCutInDTO();
        mGachaRenditionTrajectoryCutInDTO1.setId(1L);
        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO2 = new MGachaRenditionTrajectoryCutInDTO();
        assertThat(mGachaRenditionTrajectoryCutInDTO1).isNotEqualTo(mGachaRenditionTrajectoryCutInDTO2);
        mGachaRenditionTrajectoryCutInDTO2.setId(mGachaRenditionTrajectoryCutInDTO1.getId());
        assertThat(mGachaRenditionTrajectoryCutInDTO1).isEqualTo(mGachaRenditionTrajectoryCutInDTO2);
        mGachaRenditionTrajectoryCutInDTO2.setId(2L);
        assertThat(mGachaRenditionTrajectoryCutInDTO1).isNotEqualTo(mGachaRenditionTrajectoryCutInDTO2);
        mGachaRenditionTrajectoryCutInDTO1.setId(null);
        assertThat(mGachaRenditionTrajectoryCutInDTO1).isNotEqualTo(mGachaRenditionTrajectoryCutInDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionTrajectoryCutInMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionTrajectoryCutInMapper.fromId(null)).isNull();
    }
}
