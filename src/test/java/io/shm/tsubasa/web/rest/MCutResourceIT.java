package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCut;
import io.shm.tsubasa.repository.MCutRepository;
import io.shm.tsubasa.service.MCutService;
import io.shm.tsubasa.service.dto.MCutDTO;
import io.shm.tsubasa.service.mapper.MCutMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCutCriteria;
import io.shm.tsubasa.service.MCutQueryService;

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
 * Integration tests for the {@Link MCutResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCutResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_FP_A = 1;
    private static final Integer UPDATED_FP_A = 2;

    private static final Integer DEFAULT_FP_B = 1;
    private static final Integer UPDATED_FP_B = 2;

    private static final Integer DEFAULT_FP_C = 1;
    private static final Integer UPDATED_FP_C = 2;

    private static final Integer DEFAULT_FP_D = 1;
    private static final Integer UPDATED_FP_D = 2;

    private static final Integer DEFAULT_FP_E = 1;
    private static final Integer UPDATED_FP_E = 2;

    private static final Integer DEFAULT_FP_F = 1;
    private static final Integer UPDATED_FP_F = 2;

    private static final Integer DEFAULT_GK_A = 1;
    private static final Integer UPDATED_GK_A = 2;

    private static final Integer DEFAULT_GK_B = 1;
    private static final Integer UPDATED_GK_B = 2;

    private static final Integer DEFAULT_SHOW_ENVIRONMENTAL_EFFECT = 1;
    private static final Integer UPDATED_SHOW_ENVIRONMENTAL_EFFECT = 2;

    private static final Integer DEFAULT_CUT_TYPE = 1;
    private static final Integer UPDATED_CUT_TYPE = 2;

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_IS_COMBI_CUT = 1;
    private static final Integer UPDATED_IS_COMBI_CUT = 2;

    @Autowired
    private MCutRepository mCutRepository;

    @Autowired
    private MCutMapper mCutMapper;

    @Autowired
    private MCutService mCutService;

    @Autowired
    private MCutQueryService mCutQueryService;

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

    private MockMvc restMCutMockMvc;

    private MCut mCut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCutResource mCutResource = new MCutResource(mCutService, mCutQueryService);
        this.restMCutMockMvc = MockMvcBuilders.standaloneSetup(mCutResource)
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
    public static MCut createEntity(EntityManager em) {
        MCut mCut = new MCut()
            .name(DEFAULT_NAME)
            .fpA(DEFAULT_FP_A)
            .fpB(DEFAULT_FP_B)
            .fpC(DEFAULT_FP_C)
            .fpD(DEFAULT_FP_D)
            .fpE(DEFAULT_FP_E)
            .fpF(DEFAULT_FP_F)
            .gkA(DEFAULT_GK_A)
            .gkB(DEFAULT_GK_B)
            .showEnvironmentalEffect(DEFAULT_SHOW_ENVIRONMENTAL_EFFECT)
            .cutType(DEFAULT_CUT_TYPE)
            .groupId(DEFAULT_GROUP_ID)
            .isCombiCut(DEFAULT_IS_COMBI_CUT);
        return mCut;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCut createUpdatedEntity(EntityManager em) {
        MCut mCut = new MCut()
            .name(UPDATED_NAME)
            .fpA(UPDATED_FP_A)
            .fpB(UPDATED_FP_B)
            .fpC(UPDATED_FP_C)
            .fpD(UPDATED_FP_D)
            .fpE(UPDATED_FP_E)
            .fpF(UPDATED_FP_F)
            .gkA(UPDATED_GK_A)
            .gkB(UPDATED_GK_B)
            .showEnvironmentalEffect(UPDATED_SHOW_ENVIRONMENTAL_EFFECT)
            .cutType(UPDATED_CUT_TYPE)
            .groupId(UPDATED_GROUP_ID)
            .isCombiCut(UPDATED_IS_COMBI_CUT);
        return mCut;
    }

    @BeforeEach
    public void initTest() {
        mCut = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCut() throws Exception {
        int databaseSizeBeforeCreate = mCutRepository.findAll().size();

        // Create the MCut
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);
        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isCreated());

        // Validate the MCut in the database
        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeCreate + 1);
        MCut testMCut = mCutList.get(mCutList.size() - 1);
        assertThat(testMCut.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMCut.getFpA()).isEqualTo(DEFAULT_FP_A);
        assertThat(testMCut.getFpB()).isEqualTo(DEFAULT_FP_B);
        assertThat(testMCut.getFpC()).isEqualTo(DEFAULT_FP_C);
        assertThat(testMCut.getFpD()).isEqualTo(DEFAULT_FP_D);
        assertThat(testMCut.getFpE()).isEqualTo(DEFAULT_FP_E);
        assertThat(testMCut.getFpF()).isEqualTo(DEFAULT_FP_F);
        assertThat(testMCut.getGkA()).isEqualTo(DEFAULT_GK_A);
        assertThat(testMCut.getGkB()).isEqualTo(DEFAULT_GK_B);
        assertThat(testMCut.getShowEnvironmentalEffect()).isEqualTo(DEFAULT_SHOW_ENVIRONMENTAL_EFFECT);
        assertThat(testMCut.getCutType()).isEqualTo(DEFAULT_CUT_TYPE);
        assertThat(testMCut.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMCut.getIsCombiCut()).isEqualTo(DEFAULT_IS_COMBI_CUT);
    }

    @Test
    @Transactional
    public void createMCutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCutRepository.findAll().size();

        // Create the MCut with an existing ID
        mCut.setId(1L);
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCut in the database
        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFpAIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setFpA(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFpBIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setFpB(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFpCIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setFpC(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFpDIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setFpD(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFpEIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setFpE(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFpFIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setFpF(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGkAIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setGkA(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGkBIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setGkB(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShowEnvironmentalEffectIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setShowEnvironmentalEffect(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCutTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setCutType(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setGroupId(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCombiCutIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutRepository.findAll().size();
        // set the field null
        mCut.setIsCombiCut(null);

        // Create the MCut, which fails.
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        restMCutMockMvc.perform(post("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCuts() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList
        restMCutMockMvc.perform(get("/api/m-cuts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCut.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fpA").value(hasItem(DEFAULT_FP_A)))
            .andExpect(jsonPath("$.[*].fpB").value(hasItem(DEFAULT_FP_B)))
            .andExpect(jsonPath("$.[*].fpC").value(hasItem(DEFAULT_FP_C)))
            .andExpect(jsonPath("$.[*].fpD").value(hasItem(DEFAULT_FP_D)))
            .andExpect(jsonPath("$.[*].fpE").value(hasItem(DEFAULT_FP_E)))
            .andExpect(jsonPath("$.[*].fpF").value(hasItem(DEFAULT_FP_F)))
            .andExpect(jsonPath("$.[*].gkA").value(hasItem(DEFAULT_GK_A)))
            .andExpect(jsonPath("$.[*].gkB").value(hasItem(DEFAULT_GK_B)))
            .andExpect(jsonPath("$.[*].showEnvironmentalEffect").value(hasItem(DEFAULT_SHOW_ENVIRONMENTAL_EFFECT)))
            .andExpect(jsonPath("$.[*].cutType").value(hasItem(DEFAULT_CUT_TYPE)))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].isCombiCut").value(hasItem(DEFAULT_IS_COMBI_CUT)));
    }
    
    @Test
    @Transactional
    public void getMCut() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get the mCut
        restMCutMockMvc.perform(get("/api/m-cuts/{id}", mCut.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCut.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fpA").value(DEFAULT_FP_A))
            .andExpect(jsonPath("$.fpB").value(DEFAULT_FP_B))
            .andExpect(jsonPath("$.fpC").value(DEFAULT_FP_C))
            .andExpect(jsonPath("$.fpD").value(DEFAULT_FP_D))
            .andExpect(jsonPath("$.fpE").value(DEFAULT_FP_E))
            .andExpect(jsonPath("$.fpF").value(DEFAULT_FP_F))
            .andExpect(jsonPath("$.gkA").value(DEFAULT_GK_A))
            .andExpect(jsonPath("$.gkB").value(DEFAULT_GK_B))
            .andExpect(jsonPath("$.showEnvironmentalEffect").value(DEFAULT_SHOW_ENVIRONMENTAL_EFFECT))
            .andExpect(jsonPath("$.cutType").value(DEFAULT_CUT_TYPE))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.isCombiCut").value(DEFAULT_IS_COMBI_CUT));
    }

    @Test
    @Transactional
    public void getAllMCutsByFpAIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpA equals to DEFAULT_FP_A
        defaultMCutShouldBeFound("fpA.equals=" + DEFAULT_FP_A);

        // Get all the mCutList where fpA equals to UPDATED_FP_A
        defaultMCutShouldNotBeFound("fpA.equals=" + UPDATED_FP_A);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpAIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpA in DEFAULT_FP_A or UPDATED_FP_A
        defaultMCutShouldBeFound("fpA.in=" + DEFAULT_FP_A + "," + UPDATED_FP_A);

        // Get all the mCutList where fpA equals to UPDATED_FP_A
        defaultMCutShouldNotBeFound("fpA.in=" + UPDATED_FP_A);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpAIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpA is not null
        defaultMCutShouldBeFound("fpA.specified=true");

        // Get all the mCutList where fpA is null
        defaultMCutShouldNotBeFound("fpA.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByFpAIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpA greater than or equals to DEFAULT_FP_A
        defaultMCutShouldBeFound("fpA.greaterOrEqualThan=" + DEFAULT_FP_A);

        // Get all the mCutList where fpA greater than or equals to UPDATED_FP_A
        defaultMCutShouldNotBeFound("fpA.greaterOrEqualThan=" + UPDATED_FP_A);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpAIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpA less than or equals to DEFAULT_FP_A
        defaultMCutShouldNotBeFound("fpA.lessThan=" + DEFAULT_FP_A);

        // Get all the mCutList where fpA less than or equals to UPDATED_FP_A
        defaultMCutShouldBeFound("fpA.lessThan=" + UPDATED_FP_A);
    }


    @Test
    @Transactional
    public void getAllMCutsByFpBIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpB equals to DEFAULT_FP_B
        defaultMCutShouldBeFound("fpB.equals=" + DEFAULT_FP_B);

        // Get all the mCutList where fpB equals to UPDATED_FP_B
        defaultMCutShouldNotBeFound("fpB.equals=" + UPDATED_FP_B);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpBIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpB in DEFAULT_FP_B or UPDATED_FP_B
        defaultMCutShouldBeFound("fpB.in=" + DEFAULT_FP_B + "," + UPDATED_FP_B);

        // Get all the mCutList where fpB equals to UPDATED_FP_B
        defaultMCutShouldNotBeFound("fpB.in=" + UPDATED_FP_B);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpBIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpB is not null
        defaultMCutShouldBeFound("fpB.specified=true");

        // Get all the mCutList where fpB is null
        defaultMCutShouldNotBeFound("fpB.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByFpBIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpB greater than or equals to DEFAULT_FP_B
        defaultMCutShouldBeFound("fpB.greaterOrEqualThan=" + DEFAULT_FP_B);

        // Get all the mCutList where fpB greater than or equals to UPDATED_FP_B
        defaultMCutShouldNotBeFound("fpB.greaterOrEqualThan=" + UPDATED_FP_B);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpBIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpB less than or equals to DEFAULT_FP_B
        defaultMCutShouldNotBeFound("fpB.lessThan=" + DEFAULT_FP_B);

        // Get all the mCutList where fpB less than or equals to UPDATED_FP_B
        defaultMCutShouldBeFound("fpB.lessThan=" + UPDATED_FP_B);
    }


    @Test
    @Transactional
    public void getAllMCutsByFpCIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpC equals to DEFAULT_FP_C
        defaultMCutShouldBeFound("fpC.equals=" + DEFAULT_FP_C);

        // Get all the mCutList where fpC equals to UPDATED_FP_C
        defaultMCutShouldNotBeFound("fpC.equals=" + UPDATED_FP_C);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpCIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpC in DEFAULT_FP_C or UPDATED_FP_C
        defaultMCutShouldBeFound("fpC.in=" + DEFAULT_FP_C + "," + UPDATED_FP_C);

        // Get all the mCutList where fpC equals to UPDATED_FP_C
        defaultMCutShouldNotBeFound("fpC.in=" + UPDATED_FP_C);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpCIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpC is not null
        defaultMCutShouldBeFound("fpC.specified=true");

        // Get all the mCutList where fpC is null
        defaultMCutShouldNotBeFound("fpC.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByFpCIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpC greater than or equals to DEFAULT_FP_C
        defaultMCutShouldBeFound("fpC.greaterOrEqualThan=" + DEFAULT_FP_C);

        // Get all the mCutList where fpC greater than or equals to UPDATED_FP_C
        defaultMCutShouldNotBeFound("fpC.greaterOrEqualThan=" + UPDATED_FP_C);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpCIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpC less than or equals to DEFAULT_FP_C
        defaultMCutShouldNotBeFound("fpC.lessThan=" + DEFAULT_FP_C);

        // Get all the mCutList where fpC less than or equals to UPDATED_FP_C
        defaultMCutShouldBeFound("fpC.lessThan=" + UPDATED_FP_C);
    }


    @Test
    @Transactional
    public void getAllMCutsByFpDIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpD equals to DEFAULT_FP_D
        defaultMCutShouldBeFound("fpD.equals=" + DEFAULT_FP_D);

        // Get all the mCutList where fpD equals to UPDATED_FP_D
        defaultMCutShouldNotBeFound("fpD.equals=" + UPDATED_FP_D);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpDIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpD in DEFAULT_FP_D or UPDATED_FP_D
        defaultMCutShouldBeFound("fpD.in=" + DEFAULT_FP_D + "," + UPDATED_FP_D);

        // Get all the mCutList where fpD equals to UPDATED_FP_D
        defaultMCutShouldNotBeFound("fpD.in=" + UPDATED_FP_D);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpDIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpD is not null
        defaultMCutShouldBeFound("fpD.specified=true");

        // Get all the mCutList where fpD is null
        defaultMCutShouldNotBeFound("fpD.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByFpDIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpD greater than or equals to DEFAULT_FP_D
        defaultMCutShouldBeFound("fpD.greaterOrEqualThan=" + DEFAULT_FP_D);

        // Get all the mCutList where fpD greater than or equals to UPDATED_FP_D
        defaultMCutShouldNotBeFound("fpD.greaterOrEqualThan=" + UPDATED_FP_D);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpDIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpD less than or equals to DEFAULT_FP_D
        defaultMCutShouldNotBeFound("fpD.lessThan=" + DEFAULT_FP_D);

        // Get all the mCutList where fpD less than or equals to UPDATED_FP_D
        defaultMCutShouldBeFound("fpD.lessThan=" + UPDATED_FP_D);
    }


    @Test
    @Transactional
    public void getAllMCutsByFpEIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpE equals to DEFAULT_FP_E
        defaultMCutShouldBeFound("fpE.equals=" + DEFAULT_FP_E);

        // Get all the mCutList where fpE equals to UPDATED_FP_E
        defaultMCutShouldNotBeFound("fpE.equals=" + UPDATED_FP_E);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpEIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpE in DEFAULT_FP_E or UPDATED_FP_E
        defaultMCutShouldBeFound("fpE.in=" + DEFAULT_FP_E + "," + UPDATED_FP_E);

        // Get all the mCutList where fpE equals to UPDATED_FP_E
        defaultMCutShouldNotBeFound("fpE.in=" + UPDATED_FP_E);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpEIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpE is not null
        defaultMCutShouldBeFound("fpE.specified=true");

        // Get all the mCutList where fpE is null
        defaultMCutShouldNotBeFound("fpE.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByFpEIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpE greater than or equals to DEFAULT_FP_E
        defaultMCutShouldBeFound("fpE.greaterOrEqualThan=" + DEFAULT_FP_E);

        // Get all the mCutList where fpE greater than or equals to UPDATED_FP_E
        defaultMCutShouldNotBeFound("fpE.greaterOrEqualThan=" + UPDATED_FP_E);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpEIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpE less than or equals to DEFAULT_FP_E
        defaultMCutShouldNotBeFound("fpE.lessThan=" + DEFAULT_FP_E);

        // Get all the mCutList where fpE less than or equals to UPDATED_FP_E
        defaultMCutShouldBeFound("fpE.lessThan=" + UPDATED_FP_E);
    }


    @Test
    @Transactional
    public void getAllMCutsByFpFIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpF equals to DEFAULT_FP_F
        defaultMCutShouldBeFound("fpF.equals=" + DEFAULT_FP_F);

        // Get all the mCutList where fpF equals to UPDATED_FP_F
        defaultMCutShouldNotBeFound("fpF.equals=" + UPDATED_FP_F);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpFIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpF in DEFAULT_FP_F or UPDATED_FP_F
        defaultMCutShouldBeFound("fpF.in=" + DEFAULT_FP_F + "," + UPDATED_FP_F);

        // Get all the mCutList where fpF equals to UPDATED_FP_F
        defaultMCutShouldNotBeFound("fpF.in=" + UPDATED_FP_F);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpFIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpF is not null
        defaultMCutShouldBeFound("fpF.specified=true");

        // Get all the mCutList where fpF is null
        defaultMCutShouldNotBeFound("fpF.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByFpFIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpF greater than or equals to DEFAULT_FP_F
        defaultMCutShouldBeFound("fpF.greaterOrEqualThan=" + DEFAULT_FP_F);

        // Get all the mCutList where fpF greater than or equals to UPDATED_FP_F
        defaultMCutShouldNotBeFound("fpF.greaterOrEqualThan=" + UPDATED_FP_F);
    }

    @Test
    @Transactional
    public void getAllMCutsByFpFIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where fpF less than or equals to DEFAULT_FP_F
        defaultMCutShouldNotBeFound("fpF.lessThan=" + DEFAULT_FP_F);

        // Get all the mCutList where fpF less than or equals to UPDATED_FP_F
        defaultMCutShouldBeFound("fpF.lessThan=" + UPDATED_FP_F);
    }


    @Test
    @Transactional
    public void getAllMCutsByGkAIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkA equals to DEFAULT_GK_A
        defaultMCutShouldBeFound("gkA.equals=" + DEFAULT_GK_A);

        // Get all the mCutList where gkA equals to UPDATED_GK_A
        defaultMCutShouldNotBeFound("gkA.equals=" + UPDATED_GK_A);
    }

    @Test
    @Transactional
    public void getAllMCutsByGkAIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkA in DEFAULT_GK_A or UPDATED_GK_A
        defaultMCutShouldBeFound("gkA.in=" + DEFAULT_GK_A + "," + UPDATED_GK_A);

        // Get all the mCutList where gkA equals to UPDATED_GK_A
        defaultMCutShouldNotBeFound("gkA.in=" + UPDATED_GK_A);
    }

    @Test
    @Transactional
    public void getAllMCutsByGkAIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkA is not null
        defaultMCutShouldBeFound("gkA.specified=true");

        // Get all the mCutList where gkA is null
        defaultMCutShouldNotBeFound("gkA.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByGkAIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkA greater than or equals to DEFAULT_GK_A
        defaultMCutShouldBeFound("gkA.greaterOrEqualThan=" + DEFAULT_GK_A);

        // Get all the mCutList where gkA greater than or equals to UPDATED_GK_A
        defaultMCutShouldNotBeFound("gkA.greaterOrEqualThan=" + UPDATED_GK_A);
    }

    @Test
    @Transactional
    public void getAllMCutsByGkAIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkA less than or equals to DEFAULT_GK_A
        defaultMCutShouldNotBeFound("gkA.lessThan=" + DEFAULT_GK_A);

        // Get all the mCutList where gkA less than or equals to UPDATED_GK_A
        defaultMCutShouldBeFound("gkA.lessThan=" + UPDATED_GK_A);
    }


    @Test
    @Transactional
    public void getAllMCutsByGkBIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkB equals to DEFAULT_GK_B
        defaultMCutShouldBeFound("gkB.equals=" + DEFAULT_GK_B);

        // Get all the mCutList where gkB equals to UPDATED_GK_B
        defaultMCutShouldNotBeFound("gkB.equals=" + UPDATED_GK_B);
    }

    @Test
    @Transactional
    public void getAllMCutsByGkBIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkB in DEFAULT_GK_B or UPDATED_GK_B
        defaultMCutShouldBeFound("gkB.in=" + DEFAULT_GK_B + "," + UPDATED_GK_B);

        // Get all the mCutList where gkB equals to UPDATED_GK_B
        defaultMCutShouldNotBeFound("gkB.in=" + UPDATED_GK_B);
    }

    @Test
    @Transactional
    public void getAllMCutsByGkBIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkB is not null
        defaultMCutShouldBeFound("gkB.specified=true");

        // Get all the mCutList where gkB is null
        defaultMCutShouldNotBeFound("gkB.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByGkBIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkB greater than or equals to DEFAULT_GK_B
        defaultMCutShouldBeFound("gkB.greaterOrEqualThan=" + DEFAULT_GK_B);

        // Get all the mCutList where gkB greater than or equals to UPDATED_GK_B
        defaultMCutShouldNotBeFound("gkB.greaterOrEqualThan=" + UPDATED_GK_B);
    }

    @Test
    @Transactional
    public void getAllMCutsByGkBIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where gkB less than or equals to DEFAULT_GK_B
        defaultMCutShouldNotBeFound("gkB.lessThan=" + DEFAULT_GK_B);

        // Get all the mCutList where gkB less than or equals to UPDATED_GK_B
        defaultMCutShouldBeFound("gkB.lessThan=" + UPDATED_GK_B);
    }


    @Test
    @Transactional
    public void getAllMCutsByShowEnvironmentalEffectIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where showEnvironmentalEffect equals to DEFAULT_SHOW_ENVIRONMENTAL_EFFECT
        defaultMCutShouldBeFound("showEnvironmentalEffect.equals=" + DEFAULT_SHOW_ENVIRONMENTAL_EFFECT);

        // Get all the mCutList where showEnvironmentalEffect equals to UPDATED_SHOW_ENVIRONMENTAL_EFFECT
        defaultMCutShouldNotBeFound("showEnvironmentalEffect.equals=" + UPDATED_SHOW_ENVIRONMENTAL_EFFECT);
    }

    @Test
    @Transactional
    public void getAllMCutsByShowEnvironmentalEffectIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where showEnvironmentalEffect in DEFAULT_SHOW_ENVIRONMENTAL_EFFECT or UPDATED_SHOW_ENVIRONMENTAL_EFFECT
        defaultMCutShouldBeFound("showEnvironmentalEffect.in=" + DEFAULT_SHOW_ENVIRONMENTAL_EFFECT + "," + UPDATED_SHOW_ENVIRONMENTAL_EFFECT);

        // Get all the mCutList where showEnvironmentalEffect equals to UPDATED_SHOW_ENVIRONMENTAL_EFFECT
        defaultMCutShouldNotBeFound("showEnvironmentalEffect.in=" + UPDATED_SHOW_ENVIRONMENTAL_EFFECT);
    }

    @Test
    @Transactional
    public void getAllMCutsByShowEnvironmentalEffectIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where showEnvironmentalEffect is not null
        defaultMCutShouldBeFound("showEnvironmentalEffect.specified=true");

        // Get all the mCutList where showEnvironmentalEffect is null
        defaultMCutShouldNotBeFound("showEnvironmentalEffect.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByShowEnvironmentalEffectIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where showEnvironmentalEffect greater than or equals to DEFAULT_SHOW_ENVIRONMENTAL_EFFECT
        defaultMCutShouldBeFound("showEnvironmentalEffect.greaterOrEqualThan=" + DEFAULT_SHOW_ENVIRONMENTAL_EFFECT);

        // Get all the mCutList where showEnvironmentalEffect greater than or equals to UPDATED_SHOW_ENVIRONMENTAL_EFFECT
        defaultMCutShouldNotBeFound("showEnvironmentalEffect.greaterOrEqualThan=" + UPDATED_SHOW_ENVIRONMENTAL_EFFECT);
    }

    @Test
    @Transactional
    public void getAllMCutsByShowEnvironmentalEffectIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where showEnvironmentalEffect less than or equals to DEFAULT_SHOW_ENVIRONMENTAL_EFFECT
        defaultMCutShouldNotBeFound("showEnvironmentalEffect.lessThan=" + DEFAULT_SHOW_ENVIRONMENTAL_EFFECT);

        // Get all the mCutList where showEnvironmentalEffect less than or equals to UPDATED_SHOW_ENVIRONMENTAL_EFFECT
        defaultMCutShouldBeFound("showEnvironmentalEffect.lessThan=" + UPDATED_SHOW_ENVIRONMENTAL_EFFECT);
    }


    @Test
    @Transactional
    public void getAllMCutsByCutTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where cutType equals to DEFAULT_CUT_TYPE
        defaultMCutShouldBeFound("cutType.equals=" + DEFAULT_CUT_TYPE);

        // Get all the mCutList where cutType equals to UPDATED_CUT_TYPE
        defaultMCutShouldNotBeFound("cutType.equals=" + UPDATED_CUT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCutsByCutTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where cutType in DEFAULT_CUT_TYPE or UPDATED_CUT_TYPE
        defaultMCutShouldBeFound("cutType.in=" + DEFAULT_CUT_TYPE + "," + UPDATED_CUT_TYPE);

        // Get all the mCutList where cutType equals to UPDATED_CUT_TYPE
        defaultMCutShouldNotBeFound("cutType.in=" + UPDATED_CUT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCutsByCutTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where cutType is not null
        defaultMCutShouldBeFound("cutType.specified=true");

        // Get all the mCutList where cutType is null
        defaultMCutShouldNotBeFound("cutType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByCutTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where cutType greater than or equals to DEFAULT_CUT_TYPE
        defaultMCutShouldBeFound("cutType.greaterOrEqualThan=" + DEFAULT_CUT_TYPE);

        // Get all the mCutList where cutType greater than or equals to UPDATED_CUT_TYPE
        defaultMCutShouldNotBeFound("cutType.greaterOrEqualThan=" + UPDATED_CUT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCutsByCutTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where cutType less than or equals to DEFAULT_CUT_TYPE
        defaultMCutShouldNotBeFound("cutType.lessThan=" + DEFAULT_CUT_TYPE);

        // Get all the mCutList where cutType less than or equals to UPDATED_CUT_TYPE
        defaultMCutShouldBeFound("cutType.lessThan=" + UPDATED_CUT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMCutsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where groupId equals to DEFAULT_GROUP_ID
        defaultMCutShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mCutList where groupId equals to UPDATED_GROUP_ID
        defaultMCutShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMCutsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMCutShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mCutList where groupId equals to UPDATED_GROUP_ID
        defaultMCutShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMCutsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where groupId is not null
        defaultMCutShouldBeFound("groupId.specified=true");

        // Get all the mCutList where groupId is null
        defaultMCutShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMCutShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mCutList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMCutShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMCutsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMCutShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mCutList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMCutShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMCutsByIsCombiCutIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where isCombiCut equals to DEFAULT_IS_COMBI_CUT
        defaultMCutShouldBeFound("isCombiCut.equals=" + DEFAULT_IS_COMBI_CUT);

        // Get all the mCutList where isCombiCut equals to UPDATED_IS_COMBI_CUT
        defaultMCutShouldNotBeFound("isCombiCut.equals=" + UPDATED_IS_COMBI_CUT);
    }

    @Test
    @Transactional
    public void getAllMCutsByIsCombiCutIsInShouldWork() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where isCombiCut in DEFAULT_IS_COMBI_CUT or UPDATED_IS_COMBI_CUT
        defaultMCutShouldBeFound("isCombiCut.in=" + DEFAULT_IS_COMBI_CUT + "," + UPDATED_IS_COMBI_CUT);

        // Get all the mCutList where isCombiCut equals to UPDATED_IS_COMBI_CUT
        defaultMCutShouldNotBeFound("isCombiCut.in=" + UPDATED_IS_COMBI_CUT);
    }

    @Test
    @Transactional
    public void getAllMCutsByIsCombiCutIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where isCombiCut is not null
        defaultMCutShouldBeFound("isCombiCut.specified=true");

        // Get all the mCutList where isCombiCut is null
        defaultMCutShouldNotBeFound("isCombiCut.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutsByIsCombiCutIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where isCombiCut greater than or equals to DEFAULT_IS_COMBI_CUT
        defaultMCutShouldBeFound("isCombiCut.greaterOrEqualThan=" + DEFAULT_IS_COMBI_CUT);

        // Get all the mCutList where isCombiCut greater than or equals to UPDATED_IS_COMBI_CUT
        defaultMCutShouldNotBeFound("isCombiCut.greaterOrEqualThan=" + UPDATED_IS_COMBI_CUT);
    }

    @Test
    @Transactional
    public void getAllMCutsByIsCombiCutIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        // Get all the mCutList where isCombiCut less than or equals to DEFAULT_IS_COMBI_CUT
        defaultMCutShouldNotBeFound("isCombiCut.lessThan=" + DEFAULT_IS_COMBI_CUT);

        // Get all the mCutList where isCombiCut less than or equals to UPDATED_IS_COMBI_CUT
        defaultMCutShouldBeFound("isCombiCut.lessThan=" + UPDATED_IS_COMBI_CUT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCutShouldBeFound(String filter) throws Exception {
        restMCutMockMvc.perform(get("/api/m-cuts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCut.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fpA").value(hasItem(DEFAULT_FP_A)))
            .andExpect(jsonPath("$.[*].fpB").value(hasItem(DEFAULT_FP_B)))
            .andExpect(jsonPath("$.[*].fpC").value(hasItem(DEFAULT_FP_C)))
            .andExpect(jsonPath("$.[*].fpD").value(hasItem(DEFAULT_FP_D)))
            .andExpect(jsonPath("$.[*].fpE").value(hasItem(DEFAULT_FP_E)))
            .andExpect(jsonPath("$.[*].fpF").value(hasItem(DEFAULT_FP_F)))
            .andExpect(jsonPath("$.[*].gkA").value(hasItem(DEFAULT_GK_A)))
            .andExpect(jsonPath("$.[*].gkB").value(hasItem(DEFAULT_GK_B)))
            .andExpect(jsonPath("$.[*].showEnvironmentalEffect").value(hasItem(DEFAULT_SHOW_ENVIRONMENTAL_EFFECT)))
            .andExpect(jsonPath("$.[*].cutType").value(hasItem(DEFAULT_CUT_TYPE)))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].isCombiCut").value(hasItem(DEFAULT_IS_COMBI_CUT)));

        // Check, that the count call also returns 1
        restMCutMockMvc.perform(get("/api/m-cuts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCutShouldNotBeFound(String filter) throws Exception {
        restMCutMockMvc.perform(get("/api/m-cuts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCutMockMvc.perform(get("/api/m-cuts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCut() throws Exception {
        // Get the mCut
        restMCutMockMvc.perform(get("/api/m-cuts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCut() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        int databaseSizeBeforeUpdate = mCutRepository.findAll().size();

        // Update the mCut
        MCut updatedMCut = mCutRepository.findById(mCut.getId()).get();
        // Disconnect from session so that the updates on updatedMCut are not directly saved in db
        em.detach(updatedMCut);
        updatedMCut
            .name(UPDATED_NAME)
            .fpA(UPDATED_FP_A)
            .fpB(UPDATED_FP_B)
            .fpC(UPDATED_FP_C)
            .fpD(UPDATED_FP_D)
            .fpE(UPDATED_FP_E)
            .fpF(UPDATED_FP_F)
            .gkA(UPDATED_GK_A)
            .gkB(UPDATED_GK_B)
            .showEnvironmentalEffect(UPDATED_SHOW_ENVIRONMENTAL_EFFECT)
            .cutType(UPDATED_CUT_TYPE)
            .groupId(UPDATED_GROUP_ID)
            .isCombiCut(UPDATED_IS_COMBI_CUT);
        MCutDTO mCutDTO = mCutMapper.toDto(updatedMCut);

        restMCutMockMvc.perform(put("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isOk());

        // Validate the MCut in the database
        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeUpdate);
        MCut testMCut = mCutList.get(mCutList.size() - 1);
        assertThat(testMCut.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMCut.getFpA()).isEqualTo(UPDATED_FP_A);
        assertThat(testMCut.getFpB()).isEqualTo(UPDATED_FP_B);
        assertThat(testMCut.getFpC()).isEqualTo(UPDATED_FP_C);
        assertThat(testMCut.getFpD()).isEqualTo(UPDATED_FP_D);
        assertThat(testMCut.getFpE()).isEqualTo(UPDATED_FP_E);
        assertThat(testMCut.getFpF()).isEqualTo(UPDATED_FP_F);
        assertThat(testMCut.getGkA()).isEqualTo(UPDATED_GK_A);
        assertThat(testMCut.getGkB()).isEqualTo(UPDATED_GK_B);
        assertThat(testMCut.getShowEnvironmentalEffect()).isEqualTo(UPDATED_SHOW_ENVIRONMENTAL_EFFECT);
        assertThat(testMCut.getCutType()).isEqualTo(UPDATED_CUT_TYPE);
        assertThat(testMCut.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMCut.getIsCombiCut()).isEqualTo(UPDATED_IS_COMBI_CUT);
    }

    @Test
    @Transactional
    public void updateNonExistingMCut() throws Exception {
        int databaseSizeBeforeUpdate = mCutRepository.findAll().size();

        // Create the MCut
        MCutDTO mCutDTO = mCutMapper.toDto(mCut);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCutMockMvc.perform(put("/api/m-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCut in the database
        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCut() throws Exception {
        // Initialize the database
        mCutRepository.saveAndFlush(mCut);

        int databaseSizeBeforeDelete = mCutRepository.findAll().size();

        // Delete the mCut
        restMCutMockMvc.perform(delete("/api/m-cuts/{id}", mCut.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCut> mCutList = mCutRepository.findAll();
        assertThat(mCutList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCut.class);
        MCut mCut1 = new MCut();
        mCut1.setId(1L);
        MCut mCut2 = new MCut();
        mCut2.setId(mCut1.getId());
        assertThat(mCut1).isEqualTo(mCut2);
        mCut2.setId(2L);
        assertThat(mCut1).isNotEqualTo(mCut2);
        mCut1.setId(null);
        assertThat(mCut1).isNotEqualTo(mCut2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCutDTO.class);
        MCutDTO mCutDTO1 = new MCutDTO();
        mCutDTO1.setId(1L);
        MCutDTO mCutDTO2 = new MCutDTO();
        assertThat(mCutDTO1).isNotEqualTo(mCutDTO2);
        mCutDTO2.setId(mCutDTO1.getId());
        assertThat(mCutDTO1).isEqualTo(mCutDTO2);
        mCutDTO2.setId(2L);
        assertThat(mCutDTO1).isNotEqualTo(mCutDTO2);
        mCutDTO1.setId(null);
        assertThat(mCutDTO1).isNotEqualTo(mCutDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCutMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCutMapper.fromId(null)).isNull();
    }
}
