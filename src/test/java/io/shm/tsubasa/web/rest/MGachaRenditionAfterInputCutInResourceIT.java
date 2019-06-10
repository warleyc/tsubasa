package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionAfterInputCutIn;
import io.shm.tsubasa.repository.MGachaRenditionAfterInputCutInRepository;
import io.shm.tsubasa.service.MGachaRenditionAfterInputCutInService;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionAfterInputCutInMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInCriteria;
import io.shm.tsubasa.service.MGachaRenditionAfterInputCutInQueryService;

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
 * Integration tests for the {@Link MGachaRenditionAfterInputCutInResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionAfterInputCutInResourceIT {

    private static final Integer DEFAULT_RENDITION_ID = 1;
    private static final Integer UPDATED_RENDITION_ID = 2;

    private static final Integer DEFAULT_IS_SSR = 1;
    private static final Integer UPDATED_IS_SSR = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_CUT_IN_BG_PREFAB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUT_IN_BG_PREFAB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SE_START_CUT_IN = "AAAAAAAAAA";
    private static final String UPDATED_SE_START_CUT_IN = "BBBBBBBBBB";

    @Autowired
    private MGachaRenditionAfterInputCutInRepository mGachaRenditionAfterInputCutInRepository;

    @Autowired
    private MGachaRenditionAfterInputCutInMapper mGachaRenditionAfterInputCutInMapper;

    @Autowired
    private MGachaRenditionAfterInputCutInService mGachaRenditionAfterInputCutInService;

    @Autowired
    private MGachaRenditionAfterInputCutInQueryService mGachaRenditionAfterInputCutInQueryService;

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

    private MockMvc restMGachaRenditionAfterInputCutInMockMvc;

    private MGachaRenditionAfterInputCutIn mGachaRenditionAfterInputCutIn;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionAfterInputCutInResource mGachaRenditionAfterInputCutInResource = new MGachaRenditionAfterInputCutInResource(mGachaRenditionAfterInputCutInService, mGachaRenditionAfterInputCutInQueryService);
        this.restMGachaRenditionAfterInputCutInMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionAfterInputCutInResource)
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
    public static MGachaRenditionAfterInputCutIn createEntity(EntityManager em) {
        MGachaRenditionAfterInputCutIn mGachaRenditionAfterInputCutIn = new MGachaRenditionAfterInputCutIn()
            .renditionId(DEFAULT_RENDITION_ID)
            .isSsr(DEFAULT_IS_SSR)
            .weight(DEFAULT_WEIGHT)
            .cutInBgPrefabName(DEFAULT_CUT_IN_BG_PREFAB_NAME)
            .seStartCutIn(DEFAULT_SE_START_CUT_IN);
        return mGachaRenditionAfterInputCutIn;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionAfterInputCutIn createUpdatedEntity(EntityManager em) {
        MGachaRenditionAfterInputCutIn mGachaRenditionAfterInputCutIn = new MGachaRenditionAfterInputCutIn()
            .renditionId(UPDATED_RENDITION_ID)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .cutInBgPrefabName(UPDATED_CUT_IN_BG_PREFAB_NAME)
            .seStartCutIn(UPDATED_SE_START_CUT_IN);
        return mGachaRenditionAfterInputCutIn;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionAfterInputCutIn = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionAfterInputCutIn() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionAfterInputCutInRepository.findAll().size();

        // Create the MGachaRenditionAfterInputCutIn
        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO = mGachaRenditionAfterInputCutInMapper.toDto(mGachaRenditionAfterInputCutIn);
        restMGachaRenditionAfterInputCutInMockMvc.perform(post("/api/m-gacha-rendition-after-input-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionAfterInputCutIn in the database
        List<MGachaRenditionAfterInputCutIn> mGachaRenditionAfterInputCutInList = mGachaRenditionAfterInputCutInRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionAfterInputCutIn testMGachaRenditionAfterInputCutIn = mGachaRenditionAfterInputCutInList.get(mGachaRenditionAfterInputCutInList.size() - 1);
        assertThat(testMGachaRenditionAfterInputCutIn.getRenditionId()).isEqualTo(DEFAULT_RENDITION_ID);
        assertThat(testMGachaRenditionAfterInputCutIn.getIsSsr()).isEqualTo(DEFAULT_IS_SSR);
        assertThat(testMGachaRenditionAfterInputCutIn.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionAfterInputCutIn.getCutInBgPrefabName()).isEqualTo(DEFAULT_CUT_IN_BG_PREFAB_NAME);
        assertThat(testMGachaRenditionAfterInputCutIn.getSeStartCutIn()).isEqualTo(DEFAULT_SE_START_CUT_IN);
    }

    @Test
    @Transactional
    public void createMGachaRenditionAfterInputCutInWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionAfterInputCutInRepository.findAll().size();

        // Create the MGachaRenditionAfterInputCutIn with an existing ID
        mGachaRenditionAfterInputCutIn.setId(1L);
        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO = mGachaRenditionAfterInputCutInMapper.toDto(mGachaRenditionAfterInputCutIn);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionAfterInputCutInMockMvc.perform(post("/api/m-gacha-rendition-after-input-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionAfterInputCutIn in the database
        List<MGachaRenditionAfterInputCutIn> mGachaRenditionAfterInputCutInList = mGachaRenditionAfterInputCutInRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRenditionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionAfterInputCutInRepository.findAll().size();
        // set the field null
        mGachaRenditionAfterInputCutIn.setRenditionId(null);

        // Create the MGachaRenditionAfterInputCutIn, which fails.
        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO = mGachaRenditionAfterInputCutInMapper.toDto(mGachaRenditionAfterInputCutIn);

        restMGachaRenditionAfterInputCutInMockMvc.perform(post("/api/m-gacha-rendition-after-input-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionAfterInputCutIn> mGachaRenditionAfterInputCutInList = mGachaRenditionAfterInputCutInRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsSsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionAfterInputCutInRepository.findAll().size();
        // set the field null
        mGachaRenditionAfterInputCutIn.setIsSsr(null);

        // Create the MGachaRenditionAfterInputCutIn, which fails.
        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO = mGachaRenditionAfterInputCutInMapper.toDto(mGachaRenditionAfterInputCutIn);

        restMGachaRenditionAfterInputCutInMockMvc.perform(post("/api/m-gacha-rendition-after-input-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionAfterInputCutIn> mGachaRenditionAfterInputCutInList = mGachaRenditionAfterInputCutInRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionAfterInputCutInRepository.findAll().size();
        // set the field null
        mGachaRenditionAfterInputCutIn.setWeight(null);

        // Create the MGachaRenditionAfterInputCutIn, which fails.
        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO = mGachaRenditionAfterInputCutInMapper.toDto(mGachaRenditionAfterInputCutIn);

        restMGachaRenditionAfterInputCutInMockMvc.perform(post("/api/m-gacha-rendition-after-input-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionAfterInputCutIn> mGachaRenditionAfterInputCutInList = mGachaRenditionAfterInputCutInRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutIns() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList
        restMGachaRenditionAfterInputCutInMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-ins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionAfterInputCutIn.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].cutInBgPrefabName").value(hasItem(DEFAULT_CUT_IN_BG_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].seStartCutIn").value(hasItem(DEFAULT_SE_START_CUT_IN.toString())));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionAfterInputCutIn() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get the mGachaRenditionAfterInputCutIn
        restMGachaRenditionAfterInputCutInMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-ins/{id}", mGachaRenditionAfterInputCutIn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionAfterInputCutIn.getId().intValue()))
            .andExpect(jsonPath("$.renditionId").value(DEFAULT_RENDITION_ID))
            .andExpect(jsonPath("$.isSsr").value(DEFAULT_IS_SSR))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.cutInBgPrefabName").value(DEFAULT_CUT_IN_BG_PREFAB_NAME.toString()))
            .andExpect(jsonPath("$.seStartCutIn").value(DEFAULT_SE_START_CUT_IN.toString()));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByRenditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where renditionId equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionAfterInputCutInShouldBeFound("renditionId.equals=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionAfterInputCutInList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("renditionId.equals=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByRenditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where renditionId in DEFAULT_RENDITION_ID or UPDATED_RENDITION_ID
        defaultMGachaRenditionAfterInputCutInShouldBeFound("renditionId.in=" + DEFAULT_RENDITION_ID + "," + UPDATED_RENDITION_ID);

        // Get all the mGachaRenditionAfterInputCutInList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("renditionId.in=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByRenditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where renditionId is not null
        defaultMGachaRenditionAfterInputCutInShouldBeFound("renditionId.specified=true");

        // Get all the mGachaRenditionAfterInputCutInList where renditionId is null
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("renditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByRenditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where renditionId greater than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionAfterInputCutInShouldBeFound("renditionId.greaterOrEqualThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionAfterInputCutInList where renditionId greater than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("renditionId.greaterOrEqualThan=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByRenditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where renditionId less than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("renditionId.lessThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionAfterInputCutInList where renditionId less than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionAfterInputCutInShouldBeFound("renditionId.lessThan=" + UPDATED_RENDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByIsSsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where isSsr equals to DEFAULT_IS_SSR
        defaultMGachaRenditionAfterInputCutInShouldBeFound("isSsr.equals=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionAfterInputCutInList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("isSsr.equals=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByIsSsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where isSsr in DEFAULT_IS_SSR or UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInShouldBeFound("isSsr.in=" + DEFAULT_IS_SSR + "," + UPDATED_IS_SSR);

        // Get all the mGachaRenditionAfterInputCutInList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("isSsr.in=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByIsSsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where isSsr is not null
        defaultMGachaRenditionAfterInputCutInShouldBeFound("isSsr.specified=true");

        // Get all the mGachaRenditionAfterInputCutInList where isSsr is null
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("isSsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByIsSsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where isSsr greater than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionAfterInputCutInShouldBeFound("isSsr.greaterOrEqualThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionAfterInputCutInList where isSsr greater than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("isSsr.greaterOrEqualThan=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByIsSsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where isSsr less than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("isSsr.lessThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionAfterInputCutInList where isSsr less than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInShouldBeFound("isSsr.lessThan=" + UPDATED_IS_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionAfterInputCutInShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionAfterInputCutInList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionAfterInputCutInList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where weight is not null
        defaultMGachaRenditionAfterInputCutInShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionAfterInputCutInList where weight is null
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionAfterInputCutInShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionAfterInputCutInList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        // Get all the mGachaRenditionAfterInputCutInList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionAfterInputCutInShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionAfterInputCutInList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionAfterInputCutInShouldBeFound(String filter) throws Exception {
        restMGachaRenditionAfterInputCutInMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-ins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionAfterInputCutIn.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].cutInBgPrefabName").value(hasItem(DEFAULT_CUT_IN_BG_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].seStartCutIn").value(hasItem(DEFAULT_SE_START_CUT_IN.toString())));

        // Check, that the count call also returns 1
        restMGachaRenditionAfterInputCutInMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-ins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionAfterInputCutInShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionAfterInputCutInMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-ins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionAfterInputCutInMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-ins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionAfterInputCutIn() throws Exception {
        // Get the mGachaRenditionAfterInputCutIn
        restMGachaRenditionAfterInputCutInMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-ins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionAfterInputCutIn() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        int databaseSizeBeforeUpdate = mGachaRenditionAfterInputCutInRepository.findAll().size();

        // Update the mGachaRenditionAfterInputCutIn
        MGachaRenditionAfterInputCutIn updatedMGachaRenditionAfterInputCutIn = mGachaRenditionAfterInputCutInRepository.findById(mGachaRenditionAfterInputCutIn.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionAfterInputCutIn are not directly saved in db
        em.detach(updatedMGachaRenditionAfterInputCutIn);
        updatedMGachaRenditionAfterInputCutIn
            .renditionId(UPDATED_RENDITION_ID)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .cutInBgPrefabName(UPDATED_CUT_IN_BG_PREFAB_NAME)
            .seStartCutIn(UPDATED_SE_START_CUT_IN);
        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO = mGachaRenditionAfterInputCutInMapper.toDto(updatedMGachaRenditionAfterInputCutIn);

        restMGachaRenditionAfterInputCutInMockMvc.perform(put("/api/m-gacha-rendition-after-input-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionAfterInputCutIn in the database
        List<MGachaRenditionAfterInputCutIn> mGachaRenditionAfterInputCutInList = mGachaRenditionAfterInputCutInRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionAfterInputCutIn testMGachaRenditionAfterInputCutIn = mGachaRenditionAfterInputCutInList.get(mGachaRenditionAfterInputCutInList.size() - 1);
        assertThat(testMGachaRenditionAfterInputCutIn.getRenditionId()).isEqualTo(UPDATED_RENDITION_ID);
        assertThat(testMGachaRenditionAfterInputCutIn.getIsSsr()).isEqualTo(UPDATED_IS_SSR);
        assertThat(testMGachaRenditionAfterInputCutIn.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionAfterInputCutIn.getCutInBgPrefabName()).isEqualTo(UPDATED_CUT_IN_BG_PREFAB_NAME);
        assertThat(testMGachaRenditionAfterInputCutIn.getSeStartCutIn()).isEqualTo(UPDATED_SE_START_CUT_IN);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionAfterInputCutIn() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionAfterInputCutInRepository.findAll().size();

        // Create the MGachaRenditionAfterInputCutIn
        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO = mGachaRenditionAfterInputCutInMapper.toDto(mGachaRenditionAfterInputCutIn);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionAfterInputCutInMockMvc.perform(put("/api/m-gacha-rendition-after-input-cut-ins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionAfterInputCutIn in the database
        List<MGachaRenditionAfterInputCutIn> mGachaRenditionAfterInputCutInList = mGachaRenditionAfterInputCutInRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionAfterInputCutIn() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInRepository.saveAndFlush(mGachaRenditionAfterInputCutIn);

        int databaseSizeBeforeDelete = mGachaRenditionAfterInputCutInRepository.findAll().size();

        // Delete the mGachaRenditionAfterInputCutIn
        restMGachaRenditionAfterInputCutInMockMvc.perform(delete("/api/m-gacha-rendition-after-input-cut-ins/{id}", mGachaRenditionAfterInputCutIn.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionAfterInputCutIn> mGachaRenditionAfterInputCutInList = mGachaRenditionAfterInputCutInRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionAfterInputCutIn.class);
        MGachaRenditionAfterInputCutIn mGachaRenditionAfterInputCutIn1 = new MGachaRenditionAfterInputCutIn();
        mGachaRenditionAfterInputCutIn1.setId(1L);
        MGachaRenditionAfterInputCutIn mGachaRenditionAfterInputCutIn2 = new MGachaRenditionAfterInputCutIn();
        mGachaRenditionAfterInputCutIn2.setId(mGachaRenditionAfterInputCutIn1.getId());
        assertThat(mGachaRenditionAfterInputCutIn1).isEqualTo(mGachaRenditionAfterInputCutIn2);
        mGachaRenditionAfterInputCutIn2.setId(2L);
        assertThat(mGachaRenditionAfterInputCutIn1).isNotEqualTo(mGachaRenditionAfterInputCutIn2);
        mGachaRenditionAfterInputCutIn1.setId(null);
        assertThat(mGachaRenditionAfterInputCutIn1).isNotEqualTo(mGachaRenditionAfterInputCutIn2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionAfterInputCutInDTO.class);
        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO1 = new MGachaRenditionAfterInputCutInDTO();
        mGachaRenditionAfterInputCutInDTO1.setId(1L);
        MGachaRenditionAfterInputCutInDTO mGachaRenditionAfterInputCutInDTO2 = new MGachaRenditionAfterInputCutInDTO();
        assertThat(mGachaRenditionAfterInputCutInDTO1).isNotEqualTo(mGachaRenditionAfterInputCutInDTO2);
        mGachaRenditionAfterInputCutInDTO2.setId(mGachaRenditionAfterInputCutInDTO1.getId());
        assertThat(mGachaRenditionAfterInputCutInDTO1).isEqualTo(mGachaRenditionAfterInputCutInDTO2);
        mGachaRenditionAfterInputCutInDTO2.setId(2L);
        assertThat(mGachaRenditionAfterInputCutInDTO1).isNotEqualTo(mGachaRenditionAfterInputCutInDTO2);
        mGachaRenditionAfterInputCutInDTO1.setId(null);
        assertThat(mGachaRenditionAfterInputCutInDTO1).isNotEqualTo(mGachaRenditionAfterInputCutInDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionAfterInputCutInMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionAfterInputCutInMapper.fromId(null)).isNull();
    }
}
