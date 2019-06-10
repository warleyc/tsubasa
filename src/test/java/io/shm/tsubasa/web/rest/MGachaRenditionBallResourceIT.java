package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionBall;
import io.shm.tsubasa.repository.MGachaRenditionBallRepository;
import io.shm.tsubasa.service.MGachaRenditionBallService;
import io.shm.tsubasa.service.dto.MGachaRenditionBallDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionBallMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionBallCriteria;
import io.shm.tsubasa.service.MGachaRenditionBallQueryService;

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
 * Integration tests for the {@Link MGachaRenditionBallResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionBallResourceIT {

    private static final Integer DEFAULT_RENDITION_ID = 1;
    private static final Integer UPDATED_RENDITION_ID = 2;

    private static final Integer DEFAULT_IS_SSR = 1;
    private static final Integer UPDATED_IS_SSR = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_BALL_TEXTURE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BALL_TEXTURE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRAJECTORY_NORMAL_TEXTURE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRAJECTORY_NORMAL_TEXTURE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRAJECTORY_GOLD_TEXTURE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRAJECTORY_GOLD_TEXTURE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRAJECTORY_RAINBOW_TEXTURE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRAJECTORY_RAINBOW_TEXTURE_NAME = "BBBBBBBBBB";

    @Autowired
    private MGachaRenditionBallRepository mGachaRenditionBallRepository;

    @Autowired
    private MGachaRenditionBallMapper mGachaRenditionBallMapper;

    @Autowired
    private MGachaRenditionBallService mGachaRenditionBallService;

    @Autowired
    private MGachaRenditionBallQueryService mGachaRenditionBallQueryService;

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

    private MockMvc restMGachaRenditionBallMockMvc;

    private MGachaRenditionBall mGachaRenditionBall;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionBallResource mGachaRenditionBallResource = new MGachaRenditionBallResource(mGachaRenditionBallService, mGachaRenditionBallQueryService);
        this.restMGachaRenditionBallMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionBallResource)
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
    public static MGachaRenditionBall createEntity(EntityManager em) {
        MGachaRenditionBall mGachaRenditionBall = new MGachaRenditionBall()
            .renditionId(DEFAULT_RENDITION_ID)
            .isSsr(DEFAULT_IS_SSR)
            .weight(DEFAULT_WEIGHT)
            .ballTextureName(DEFAULT_BALL_TEXTURE_NAME)
            .trajectoryNormalTextureName(DEFAULT_TRAJECTORY_NORMAL_TEXTURE_NAME)
            .trajectoryGoldTextureName(DEFAULT_TRAJECTORY_GOLD_TEXTURE_NAME)
            .trajectoryRainbowTextureName(DEFAULT_TRAJECTORY_RAINBOW_TEXTURE_NAME);
        return mGachaRenditionBall;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionBall createUpdatedEntity(EntityManager em) {
        MGachaRenditionBall mGachaRenditionBall = new MGachaRenditionBall()
            .renditionId(UPDATED_RENDITION_ID)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .ballTextureName(UPDATED_BALL_TEXTURE_NAME)
            .trajectoryNormalTextureName(UPDATED_TRAJECTORY_NORMAL_TEXTURE_NAME)
            .trajectoryGoldTextureName(UPDATED_TRAJECTORY_GOLD_TEXTURE_NAME)
            .trajectoryRainbowTextureName(UPDATED_TRAJECTORY_RAINBOW_TEXTURE_NAME);
        return mGachaRenditionBall;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionBall = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionBall() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionBallRepository.findAll().size();

        // Create the MGachaRenditionBall
        MGachaRenditionBallDTO mGachaRenditionBallDTO = mGachaRenditionBallMapper.toDto(mGachaRenditionBall);
        restMGachaRenditionBallMockMvc.perform(post("/api/m-gacha-rendition-balls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBallDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionBall in the database
        List<MGachaRenditionBall> mGachaRenditionBallList = mGachaRenditionBallRepository.findAll();
        assertThat(mGachaRenditionBallList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionBall testMGachaRenditionBall = mGachaRenditionBallList.get(mGachaRenditionBallList.size() - 1);
        assertThat(testMGachaRenditionBall.getRenditionId()).isEqualTo(DEFAULT_RENDITION_ID);
        assertThat(testMGachaRenditionBall.getIsSsr()).isEqualTo(DEFAULT_IS_SSR);
        assertThat(testMGachaRenditionBall.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionBall.getBallTextureName()).isEqualTo(DEFAULT_BALL_TEXTURE_NAME);
        assertThat(testMGachaRenditionBall.getTrajectoryNormalTextureName()).isEqualTo(DEFAULT_TRAJECTORY_NORMAL_TEXTURE_NAME);
        assertThat(testMGachaRenditionBall.getTrajectoryGoldTextureName()).isEqualTo(DEFAULT_TRAJECTORY_GOLD_TEXTURE_NAME);
        assertThat(testMGachaRenditionBall.getTrajectoryRainbowTextureName()).isEqualTo(DEFAULT_TRAJECTORY_RAINBOW_TEXTURE_NAME);
    }

    @Test
    @Transactional
    public void createMGachaRenditionBallWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionBallRepository.findAll().size();

        // Create the MGachaRenditionBall with an existing ID
        mGachaRenditionBall.setId(1L);
        MGachaRenditionBallDTO mGachaRenditionBallDTO = mGachaRenditionBallMapper.toDto(mGachaRenditionBall);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionBallMockMvc.perform(post("/api/m-gacha-rendition-balls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBallDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionBall in the database
        List<MGachaRenditionBall> mGachaRenditionBallList = mGachaRenditionBallRepository.findAll();
        assertThat(mGachaRenditionBallList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRenditionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBallRepository.findAll().size();
        // set the field null
        mGachaRenditionBall.setRenditionId(null);

        // Create the MGachaRenditionBall, which fails.
        MGachaRenditionBallDTO mGachaRenditionBallDTO = mGachaRenditionBallMapper.toDto(mGachaRenditionBall);

        restMGachaRenditionBallMockMvc.perform(post("/api/m-gacha-rendition-balls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBallDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBall> mGachaRenditionBallList = mGachaRenditionBallRepository.findAll();
        assertThat(mGachaRenditionBallList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsSsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBallRepository.findAll().size();
        // set the field null
        mGachaRenditionBall.setIsSsr(null);

        // Create the MGachaRenditionBall, which fails.
        MGachaRenditionBallDTO mGachaRenditionBallDTO = mGachaRenditionBallMapper.toDto(mGachaRenditionBall);

        restMGachaRenditionBallMockMvc.perform(post("/api/m-gacha-rendition-balls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBallDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBall> mGachaRenditionBallList = mGachaRenditionBallRepository.findAll();
        assertThat(mGachaRenditionBallList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBallRepository.findAll().size();
        // set the field null
        mGachaRenditionBall.setWeight(null);

        // Create the MGachaRenditionBall, which fails.
        MGachaRenditionBallDTO mGachaRenditionBallDTO = mGachaRenditionBallMapper.toDto(mGachaRenditionBall);

        restMGachaRenditionBallMockMvc.perform(post("/api/m-gacha-rendition-balls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBallDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBall> mGachaRenditionBallList = mGachaRenditionBallRepository.findAll();
        assertThat(mGachaRenditionBallList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBalls() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList
        restMGachaRenditionBallMockMvc.perform(get("/api/m-gacha-rendition-balls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionBall.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].ballTextureName").value(hasItem(DEFAULT_BALL_TEXTURE_NAME.toString())))
            .andExpect(jsonPath("$.[*].trajectoryNormalTextureName").value(hasItem(DEFAULT_TRAJECTORY_NORMAL_TEXTURE_NAME.toString())))
            .andExpect(jsonPath("$.[*].trajectoryGoldTextureName").value(hasItem(DEFAULT_TRAJECTORY_GOLD_TEXTURE_NAME.toString())))
            .andExpect(jsonPath("$.[*].trajectoryRainbowTextureName").value(hasItem(DEFAULT_TRAJECTORY_RAINBOW_TEXTURE_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionBall() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get the mGachaRenditionBall
        restMGachaRenditionBallMockMvc.perform(get("/api/m-gacha-rendition-balls/{id}", mGachaRenditionBall.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionBall.getId().intValue()))
            .andExpect(jsonPath("$.renditionId").value(DEFAULT_RENDITION_ID))
            .andExpect(jsonPath("$.isSsr").value(DEFAULT_IS_SSR))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.ballTextureName").value(DEFAULT_BALL_TEXTURE_NAME.toString()))
            .andExpect(jsonPath("$.trajectoryNormalTextureName").value(DEFAULT_TRAJECTORY_NORMAL_TEXTURE_NAME.toString()))
            .andExpect(jsonPath("$.trajectoryGoldTextureName").value(DEFAULT_TRAJECTORY_GOLD_TEXTURE_NAME.toString()))
            .andExpect(jsonPath("$.trajectoryRainbowTextureName").value(DEFAULT_TRAJECTORY_RAINBOW_TEXTURE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByRenditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where renditionId equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionBallShouldBeFound("renditionId.equals=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionBallList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionBallShouldNotBeFound("renditionId.equals=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByRenditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where renditionId in DEFAULT_RENDITION_ID or UPDATED_RENDITION_ID
        defaultMGachaRenditionBallShouldBeFound("renditionId.in=" + DEFAULT_RENDITION_ID + "," + UPDATED_RENDITION_ID);

        // Get all the mGachaRenditionBallList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionBallShouldNotBeFound("renditionId.in=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByRenditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where renditionId is not null
        defaultMGachaRenditionBallShouldBeFound("renditionId.specified=true");

        // Get all the mGachaRenditionBallList where renditionId is null
        defaultMGachaRenditionBallShouldNotBeFound("renditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByRenditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where renditionId greater than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionBallShouldBeFound("renditionId.greaterOrEqualThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionBallList where renditionId greater than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionBallShouldNotBeFound("renditionId.greaterOrEqualThan=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByRenditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where renditionId less than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionBallShouldNotBeFound("renditionId.lessThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionBallList where renditionId less than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionBallShouldBeFound("renditionId.lessThan=" + UPDATED_RENDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByIsSsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where isSsr equals to DEFAULT_IS_SSR
        defaultMGachaRenditionBallShouldBeFound("isSsr.equals=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionBallList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionBallShouldNotBeFound("isSsr.equals=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByIsSsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where isSsr in DEFAULT_IS_SSR or UPDATED_IS_SSR
        defaultMGachaRenditionBallShouldBeFound("isSsr.in=" + DEFAULT_IS_SSR + "," + UPDATED_IS_SSR);

        // Get all the mGachaRenditionBallList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionBallShouldNotBeFound("isSsr.in=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByIsSsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where isSsr is not null
        defaultMGachaRenditionBallShouldBeFound("isSsr.specified=true");

        // Get all the mGachaRenditionBallList where isSsr is null
        defaultMGachaRenditionBallShouldNotBeFound("isSsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByIsSsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where isSsr greater than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionBallShouldBeFound("isSsr.greaterOrEqualThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionBallList where isSsr greater than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionBallShouldNotBeFound("isSsr.greaterOrEqualThan=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByIsSsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where isSsr less than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionBallShouldNotBeFound("isSsr.lessThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionBallList where isSsr less than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionBallShouldBeFound("isSsr.lessThan=" + UPDATED_IS_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionBallShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionBallList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionBallShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionBallShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionBallList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionBallShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where weight is not null
        defaultMGachaRenditionBallShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionBallList where weight is null
        defaultMGachaRenditionBallShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionBallShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionBallList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionBallShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBallsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        // Get all the mGachaRenditionBallList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionBallShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionBallList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionBallShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionBallShouldBeFound(String filter) throws Exception {
        restMGachaRenditionBallMockMvc.perform(get("/api/m-gacha-rendition-balls?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionBall.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].ballTextureName").value(hasItem(DEFAULT_BALL_TEXTURE_NAME.toString())))
            .andExpect(jsonPath("$.[*].trajectoryNormalTextureName").value(hasItem(DEFAULT_TRAJECTORY_NORMAL_TEXTURE_NAME.toString())))
            .andExpect(jsonPath("$.[*].trajectoryGoldTextureName").value(hasItem(DEFAULT_TRAJECTORY_GOLD_TEXTURE_NAME.toString())))
            .andExpect(jsonPath("$.[*].trajectoryRainbowTextureName").value(hasItem(DEFAULT_TRAJECTORY_RAINBOW_TEXTURE_NAME.toString())));

        // Check, that the count call also returns 1
        restMGachaRenditionBallMockMvc.perform(get("/api/m-gacha-rendition-balls/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionBallShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionBallMockMvc.perform(get("/api/m-gacha-rendition-balls?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionBallMockMvc.perform(get("/api/m-gacha-rendition-balls/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionBall() throws Exception {
        // Get the mGachaRenditionBall
        restMGachaRenditionBallMockMvc.perform(get("/api/m-gacha-rendition-balls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionBall() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        int databaseSizeBeforeUpdate = mGachaRenditionBallRepository.findAll().size();

        // Update the mGachaRenditionBall
        MGachaRenditionBall updatedMGachaRenditionBall = mGachaRenditionBallRepository.findById(mGachaRenditionBall.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionBall are not directly saved in db
        em.detach(updatedMGachaRenditionBall);
        updatedMGachaRenditionBall
            .renditionId(UPDATED_RENDITION_ID)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .ballTextureName(UPDATED_BALL_TEXTURE_NAME)
            .trajectoryNormalTextureName(UPDATED_TRAJECTORY_NORMAL_TEXTURE_NAME)
            .trajectoryGoldTextureName(UPDATED_TRAJECTORY_GOLD_TEXTURE_NAME)
            .trajectoryRainbowTextureName(UPDATED_TRAJECTORY_RAINBOW_TEXTURE_NAME);
        MGachaRenditionBallDTO mGachaRenditionBallDTO = mGachaRenditionBallMapper.toDto(updatedMGachaRenditionBall);

        restMGachaRenditionBallMockMvc.perform(put("/api/m-gacha-rendition-balls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBallDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionBall in the database
        List<MGachaRenditionBall> mGachaRenditionBallList = mGachaRenditionBallRepository.findAll();
        assertThat(mGachaRenditionBallList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionBall testMGachaRenditionBall = mGachaRenditionBallList.get(mGachaRenditionBallList.size() - 1);
        assertThat(testMGachaRenditionBall.getRenditionId()).isEqualTo(UPDATED_RENDITION_ID);
        assertThat(testMGachaRenditionBall.getIsSsr()).isEqualTo(UPDATED_IS_SSR);
        assertThat(testMGachaRenditionBall.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionBall.getBallTextureName()).isEqualTo(UPDATED_BALL_TEXTURE_NAME);
        assertThat(testMGachaRenditionBall.getTrajectoryNormalTextureName()).isEqualTo(UPDATED_TRAJECTORY_NORMAL_TEXTURE_NAME);
        assertThat(testMGachaRenditionBall.getTrajectoryGoldTextureName()).isEqualTo(UPDATED_TRAJECTORY_GOLD_TEXTURE_NAME);
        assertThat(testMGachaRenditionBall.getTrajectoryRainbowTextureName()).isEqualTo(UPDATED_TRAJECTORY_RAINBOW_TEXTURE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionBall() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionBallRepository.findAll().size();

        // Create the MGachaRenditionBall
        MGachaRenditionBallDTO mGachaRenditionBallDTO = mGachaRenditionBallMapper.toDto(mGachaRenditionBall);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionBallMockMvc.perform(put("/api/m-gacha-rendition-balls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBallDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionBall in the database
        List<MGachaRenditionBall> mGachaRenditionBallList = mGachaRenditionBallRepository.findAll();
        assertThat(mGachaRenditionBallList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionBall() throws Exception {
        // Initialize the database
        mGachaRenditionBallRepository.saveAndFlush(mGachaRenditionBall);

        int databaseSizeBeforeDelete = mGachaRenditionBallRepository.findAll().size();

        // Delete the mGachaRenditionBall
        restMGachaRenditionBallMockMvc.perform(delete("/api/m-gacha-rendition-balls/{id}", mGachaRenditionBall.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionBall> mGachaRenditionBallList = mGachaRenditionBallRepository.findAll();
        assertThat(mGachaRenditionBallList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionBall.class);
        MGachaRenditionBall mGachaRenditionBall1 = new MGachaRenditionBall();
        mGachaRenditionBall1.setId(1L);
        MGachaRenditionBall mGachaRenditionBall2 = new MGachaRenditionBall();
        mGachaRenditionBall2.setId(mGachaRenditionBall1.getId());
        assertThat(mGachaRenditionBall1).isEqualTo(mGachaRenditionBall2);
        mGachaRenditionBall2.setId(2L);
        assertThat(mGachaRenditionBall1).isNotEqualTo(mGachaRenditionBall2);
        mGachaRenditionBall1.setId(null);
        assertThat(mGachaRenditionBall1).isNotEqualTo(mGachaRenditionBall2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionBallDTO.class);
        MGachaRenditionBallDTO mGachaRenditionBallDTO1 = new MGachaRenditionBallDTO();
        mGachaRenditionBallDTO1.setId(1L);
        MGachaRenditionBallDTO mGachaRenditionBallDTO2 = new MGachaRenditionBallDTO();
        assertThat(mGachaRenditionBallDTO1).isNotEqualTo(mGachaRenditionBallDTO2);
        mGachaRenditionBallDTO2.setId(mGachaRenditionBallDTO1.getId());
        assertThat(mGachaRenditionBallDTO1).isEqualTo(mGachaRenditionBallDTO2);
        mGachaRenditionBallDTO2.setId(2L);
        assertThat(mGachaRenditionBallDTO1).isNotEqualTo(mGachaRenditionBallDTO2);
        mGachaRenditionBallDTO1.setId(null);
        assertThat(mGachaRenditionBallDTO1).isNotEqualTo(mGachaRenditionBallDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionBallMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionBallMapper.fromId(null)).isNull();
    }
}
