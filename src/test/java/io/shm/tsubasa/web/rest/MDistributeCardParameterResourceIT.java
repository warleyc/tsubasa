package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDistributeCardParameter;
import io.shm.tsubasa.repository.MDistributeCardParameterRepository;
import io.shm.tsubasa.service.MDistributeCardParameterService;
import io.shm.tsubasa.service.dto.MDistributeCardParameterDTO;
import io.shm.tsubasa.service.mapper.MDistributeCardParameterMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDistributeCardParameterCriteria;
import io.shm.tsubasa.service.MDistributeCardParameterQueryService;

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
 * Integration tests for the {@Link MDistributeCardParameterResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDistributeCardParameterResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_IS_GK = 1;
    private static final Integer UPDATED_IS_GK = 2;

    private static final Integer DEFAULT_MAX_STEP_COUNT = 1;
    private static final Integer UPDATED_MAX_STEP_COUNT = 2;

    private static final Integer DEFAULT_MAX_TOTAL_STEP_COUNT = 1;
    private static final Integer UPDATED_MAX_TOTAL_STEP_COUNT = 2;

    private static final Integer DEFAULT_DISTRIBUTE_POINT_BY_STEP = 1;
    private static final Integer UPDATED_DISTRIBUTE_POINT_BY_STEP = 2;

    @Autowired
    private MDistributeCardParameterRepository mDistributeCardParameterRepository;

    @Autowired
    private MDistributeCardParameterMapper mDistributeCardParameterMapper;

    @Autowired
    private MDistributeCardParameterService mDistributeCardParameterService;

    @Autowired
    private MDistributeCardParameterQueryService mDistributeCardParameterQueryService;

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

    private MockMvc restMDistributeCardParameterMockMvc;

    private MDistributeCardParameter mDistributeCardParameter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDistributeCardParameterResource mDistributeCardParameterResource = new MDistributeCardParameterResource(mDistributeCardParameterService, mDistributeCardParameterQueryService);
        this.restMDistributeCardParameterMockMvc = MockMvcBuilders.standaloneSetup(mDistributeCardParameterResource)
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
    public static MDistributeCardParameter createEntity(EntityManager em) {
        MDistributeCardParameter mDistributeCardParameter = new MDistributeCardParameter()
            .rarity(DEFAULT_RARITY)
            .isGk(DEFAULT_IS_GK)
            .maxStepCount(DEFAULT_MAX_STEP_COUNT)
            .maxTotalStepCount(DEFAULT_MAX_TOTAL_STEP_COUNT)
            .distributePointByStep(DEFAULT_DISTRIBUTE_POINT_BY_STEP);
        return mDistributeCardParameter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDistributeCardParameter createUpdatedEntity(EntityManager em) {
        MDistributeCardParameter mDistributeCardParameter = new MDistributeCardParameter()
            .rarity(UPDATED_RARITY)
            .isGk(UPDATED_IS_GK)
            .maxStepCount(UPDATED_MAX_STEP_COUNT)
            .maxTotalStepCount(UPDATED_MAX_TOTAL_STEP_COUNT)
            .distributePointByStep(UPDATED_DISTRIBUTE_POINT_BY_STEP);
        return mDistributeCardParameter;
    }

    @BeforeEach
    public void initTest() {
        mDistributeCardParameter = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDistributeCardParameter() throws Exception {
        int databaseSizeBeforeCreate = mDistributeCardParameterRepository.findAll().size();

        // Create the MDistributeCardParameter
        MDistributeCardParameterDTO mDistributeCardParameterDTO = mDistributeCardParameterMapper.toDto(mDistributeCardParameter);
        restMDistributeCardParameterMockMvc.perform(post("/api/m-distribute-card-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterDTO)))
            .andExpect(status().isCreated());

        // Validate the MDistributeCardParameter in the database
        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeCreate + 1);
        MDistributeCardParameter testMDistributeCardParameter = mDistributeCardParameterList.get(mDistributeCardParameterList.size() - 1);
        assertThat(testMDistributeCardParameter.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMDistributeCardParameter.getIsGk()).isEqualTo(DEFAULT_IS_GK);
        assertThat(testMDistributeCardParameter.getMaxStepCount()).isEqualTo(DEFAULT_MAX_STEP_COUNT);
        assertThat(testMDistributeCardParameter.getMaxTotalStepCount()).isEqualTo(DEFAULT_MAX_TOTAL_STEP_COUNT);
        assertThat(testMDistributeCardParameter.getDistributePointByStep()).isEqualTo(DEFAULT_DISTRIBUTE_POINT_BY_STEP);
    }

    @Test
    @Transactional
    public void createMDistributeCardParameterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDistributeCardParameterRepository.findAll().size();

        // Create the MDistributeCardParameter with an existing ID
        mDistributeCardParameter.setId(1L);
        MDistributeCardParameterDTO mDistributeCardParameterDTO = mDistributeCardParameterMapper.toDto(mDistributeCardParameter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDistributeCardParameterMockMvc.perform(post("/api/m-distribute-card-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDistributeCardParameter in the database
        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeCardParameterRepository.findAll().size();
        // set the field null
        mDistributeCardParameter.setRarity(null);

        // Create the MDistributeCardParameter, which fails.
        MDistributeCardParameterDTO mDistributeCardParameterDTO = mDistributeCardParameterMapper.toDto(mDistributeCardParameter);

        restMDistributeCardParameterMockMvc.perform(post("/api/m-distribute-card-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsGkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeCardParameterRepository.findAll().size();
        // set the field null
        mDistributeCardParameter.setIsGk(null);

        // Create the MDistributeCardParameter, which fails.
        MDistributeCardParameterDTO mDistributeCardParameterDTO = mDistributeCardParameterMapper.toDto(mDistributeCardParameter);

        restMDistributeCardParameterMockMvc.perform(post("/api/m-distribute-card-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxStepCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeCardParameterRepository.findAll().size();
        // set the field null
        mDistributeCardParameter.setMaxStepCount(null);

        // Create the MDistributeCardParameter, which fails.
        MDistributeCardParameterDTO mDistributeCardParameterDTO = mDistributeCardParameterMapper.toDto(mDistributeCardParameter);

        restMDistributeCardParameterMockMvc.perform(post("/api/m-distribute-card-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxTotalStepCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeCardParameterRepository.findAll().size();
        // set the field null
        mDistributeCardParameter.setMaxTotalStepCount(null);

        // Create the MDistributeCardParameter, which fails.
        MDistributeCardParameterDTO mDistributeCardParameterDTO = mDistributeCardParameterMapper.toDto(mDistributeCardParameter);

        restMDistributeCardParameterMockMvc.perform(post("/api/m-distribute-card-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDistributePointByStepIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeCardParameterRepository.findAll().size();
        // set the field null
        mDistributeCardParameter.setDistributePointByStep(null);

        // Create the MDistributeCardParameter, which fails.
        MDistributeCardParameterDTO mDistributeCardParameterDTO = mDistributeCardParameterMapper.toDto(mDistributeCardParameter);

        restMDistributeCardParameterMockMvc.perform(post("/api/m-distribute-card-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameters() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList
        restMDistributeCardParameterMockMvc.perform(get("/api/m-distribute-card-parameters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDistributeCardParameter.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].isGk").value(hasItem(DEFAULT_IS_GK)))
            .andExpect(jsonPath("$.[*].maxStepCount").value(hasItem(DEFAULT_MAX_STEP_COUNT)))
            .andExpect(jsonPath("$.[*].maxTotalStepCount").value(hasItem(DEFAULT_MAX_TOTAL_STEP_COUNT)))
            .andExpect(jsonPath("$.[*].distributePointByStep").value(hasItem(DEFAULT_DISTRIBUTE_POINT_BY_STEP)));
    }
    
    @Test
    @Transactional
    public void getMDistributeCardParameter() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get the mDistributeCardParameter
        restMDistributeCardParameterMockMvc.perform(get("/api/m-distribute-card-parameters/{id}", mDistributeCardParameter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDistributeCardParameter.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.isGk").value(DEFAULT_IS_GK))
            .andExpect(jsonPath("$.maxStepCount").value(DEFAULT_MAX_STEP_COUNT))
            .andExpect(jsonPath("$.maxTotalStepCount").value(DEFAULT_MAX_TOTAL_STEP_COUNT))
            .andExpect(jsonPath("$.distributePointByStep").value(DEFAULT_DISTRIBUTE_POINT_BY_STEP));
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where rarity equals to DEFAULT_RARITY
        defaultMDistributeCardParameterShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mDistributeCardParameterList where rarity equals to UPDATED_RARITY
        defaultMDistributeCardParameterShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMDistributeCardParameterShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mDistributeCardParameterList where rarity equals to UPDATED_RARITY
        defaultMDistributeCardParameterShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where rarity is not null
        defaultMDistributeCardParameterShouldBeFound("rarity.specified=true");

        // Get all the mDistributeCardParameterList where rarity is null
        defaultMDistributeCardParameterShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where rarity greater than or equals to DEFAULT_RARITY
        defaultMDistributeCardParameterShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mDistributeCardParameterList where rarity greater than or equals to UPDATED_RARITY
        defaultMDistributeCardParameterShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where rarity less than or equals to DEFAULT_RARITY
        defaultMDistributeCardParameterShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mDistributeCardParameterList where rarity less than or equals to UPDATED_RARITY
        defaultMDistributeCardParameterShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMDistributeCardParametersByIsGkIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where isGk equals to DEFAULT_IS_GK
        defaultMDistributeCardParameterShouldBeFound("isGk.equals=" + DEFAULT_IS_GK);

        // Get all the mDistributeCardParameterList where isGk equals to UPDATED_IS_GK
        defaultMDistributeCardParameterShouldNotBeFound("isGk.equals=" + UPDATED_IS_GK);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByIsGkIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where isGk in DEFAULT_IS_GK or UPDATED_IS_GK
        defaultMDistributeCardParameterShouldBeFound("isGk.in=" + DEFAULT_IS_GK + "," + UPDATED_IS_GK);

        // Get all the mDistributeCardParameterList where isGk equals to UPDATED_IS_GK
        defaultMDistributeCardParameterShouldNotBeFound("isGk.in=" + UPDATED_IS_GK);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByIsGkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where isGk is not null
        defaultMDistributeCardParameterShouldBeFound("isGk.specified=true");

        // Get all the mDistributeCardParameterList where isGk is null
        defaultMDistributeCardParameterShouldNotBeFound("isGk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByIsGkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where isGk greater than or equals to DEFAULT_IS_GK
        defaultMDistributeCardParameterShouldBeFound("isGk.greaterOrEqualThan=" + DEFAULT_IS_GK);

        // Get all the mDistributeCardParameterList where isGk greater than or equals to UPDATED_IS_GK
        defaultMDistributeCardParameterShouldNotBeFound("isGk.greaterOrEqualThan=" + UPDATED_IS_GK);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByIsGkIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where isGk less than or equals to DEFAULT_IS_GK
        defaultMDistributeCardParameterShouldNotBeFound("isGk.lessThan=" + DEFAULT_IS_GK);

        // Get all the mDistributeCardParameterList where isGk less than or equals to UPDATED_IS_GK
        defaultMDistributeCardParameterShouldBeFound("isGk.lessThan=" + UPDATED_IS_GK);
    }


    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxStepCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxStepCount equals to DEFAULT_MAX_STEP_COUNT
        defaultMDistributeCardParameterShouldBeFound("maxStepCount.equals=" + DEFAULT_MAX_STEP_COUNT);

        // Get all the mDistributeCardParameterList where maxStepCount equals to UPDATED_MAX_STEP_COUNT
        defaultMDistributeCardParameterShouldNotBeFound("maxStepCount.equals=" + UPDATED_MAX_STEP_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxStepCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxStepCount in DEFAULT_MAX_STEP_COUNT or UPDATED_MAX_STEP_COUNT
        defaultMDistributeCardParameterShouldBeFound("maxStepCount.in=" + DEFAULT_MAX_STEP_COUNT + "," + UPDATED_MAX_STEP_COUNT);

        // Get all the mDistributeCardParameterList where maxStepCount equals to UPDATED_MAX_STEP_COUNT
        defaultMDistributeCardParameterShouldNotBeFound("maxStepCount.in=" + UPDATED_MAX_STEP_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxStepCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxStepCount is not null
        defaultMDistributeCardParameterShouldBeFound("maxStepCount.specified=true");

        // Get all the mDistributeCardParameterList where maxStepCount is null
        defaultMDistributeCardParameterShouldNotBeFound("maxStepCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxStepCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxStepCount greater than or equals to DEFAULT_MAX_STEP_COUNT
        defaultMDistributeCardParameterShouldBeFound("maxStepCount.greaterOrEqualThan=" + DEFAULT_MAX_STEP_COUNT);

        // Get all the mDistributeCardParameterList where maxStepCount greater than or equals to UPDATED_MAX_STEP_COUNT
        defaultMDistributeCardParameterShouldNotBeFound("maxStepCount.greaterOrEqualThan=" + UPDATED_MAX_STEP_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxStepCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxStepCount less than or equals to DEFAULT_MAX_STEP_COUNT
        defaultMDistributeCardParameterShouldNotBeFound("maxStepCount.lessThan=" + DEFAULT_MAX_STEP_COUNT);

        // Get all the mDistributeCardParameterList where maxStepCount less than or equals to UPDATED_MAX_STEP_COUNT
        defaultMDistributeCardParameterShouldBeFound("maxStepCount.lessThan=" + UPDATED_MAX_STEP_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxTotalStepCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxTotalStepCount equals to DEFAULT_MAX_TOTAL_STEP_COUNT
        defaultMDistributeCardParameterShouldBeFound("maxTotalStepCount.equals=" + DEFAULT_MAX_TOTAL_STEP_COUNT);

        // Get all the mDistributeCardParameterList where maxTotalStepCount equals to UPDATED_MAX_TOTAL_STEP_COUNT
        defaultMDistributeCardParameterShouldNotBeFound("maxTotalStepCount.equals=" + UPDATED_MAX_TOTAL_STEP_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxTotalStepCountIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxTotalStepCount in DEFAULT_MAX_TOTAL_STEP_COUNT or UPDATED_MAX_TOTAL_STEP_COUNT
        defaultMDistributeCardParameterShouldBeFound("maxTotalStepCount.in=" + DEFAULT_MAX_TOTAL_STEP_COUNT + "," + UPDATED_MAX_TOTAL_STEP_COUNT);

        // Get all the mDistributeCardParameterList where maxTotalStepCount equals to UPDATED_MAX_TOTAL_STEP_COUNT
        defaultMDistributeCardParameterShouldNotBeFound("maxTotalStepCount.in=" + UPDATED_MAX_TOTAL_STEP_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxTotalStepCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxTotalStepCount is not null
        defaultMDistributeCardParameterShouldBeFound("maxTotalStepCount.specified=true");

        // Get all the mDistributeCardParameterList where maxTotalStepCount is null
        defaultMDistributeCardParameterShouldNotBeFound("maxTotalStepCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxTotalStepCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxTotalStepCount greater than or equals to DEFAULT_MAX_TOTAL_STEP_COUNT
        defaultMDistributeCardParameterShouldBeFound("maxTotalStepCount.greaterOrEqualThan=" + DEFAULT_MAX_TOTAL_STEP_COUNT);

        // Get all the mDistributeCardParameterList where maxTotalStepCount greater than or equals to UPDATED_MAX_TOTAL_STEP_COUNT
        defaultMDistributeCardParameterShouldNotBeFound("maxTotalStepCount.greaterOrEqualThan=" + UPDATED_MAX_TOTAL_STEP_COUNT);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByMaxTotalStepCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where maxTotalStepCount less than or equals to DEFAULT_MAX_TOTAL_STEP_COUNT
        defaultMDistributeCardParameterShouldNotBeFound("maxTotalStepCount.lessThan=" + DEFAULT_MAX_TOTAL_STEP_COUNT);

        // Get all the mDistributeCardParameterList where maxTotalStepCount less than or equals to UPDATED_MAX_TOTAL_STEP_COUNT
        defaultMDistributeCardParameterShouldBeFound("maxTotalStepCount.lessThan=" + UPDATED_MAX_TOTAL_STEP_COUNT);
    }


    @Test
    @Transactional
    public void getAllMDistributeCardParametersByDistributePointByStepIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where distributePointByStep equals to DEFAULT_DISTRIBUTE_POINT_BY_STEP
        defaultMDistributeCardParameterShouldBeFound("distributePointByStep.equals=" + DEFAULT_DISTRIBUTE_POINT_BY_STEP);

        // Get all the mDistributeCardParameterList where distributePointByStep equals to UPDATED_DISTRIBUTE_POINT_BY_STEP
        defaultMDistributeCardParameterShouldNotBeFound("distributePointByStep.equals=" + UPDATED_DISTRIBUTE_POINT_BY_STEP);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByDistributePointByStepIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where distributePointByStep in DEFAULT_DISTRIBUTE_POINT_BY_STEP or UPDATED_DISTRIBUTE_POINT_BY_STEP
        defaultMDistributeCardParameterShouldBeFound("distributePointByStep.in=" + DEFAULT_DISTRIBUTE_POINT_BY_STEP + "," + UPDATED_DISTRIBUTE_POINT_BY_STEP);

        // Get all the mDistributeCardParameterList where distributePointByStep equals to UPDATED_DISTRIBUTE_POINT_BY_STEP
        defaultMDistributeCardParameterShouldNotBeFound("distributePointByStep.in=" + UPDATED_DISTRIBUTE_POINT_BY_STEP);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByDistributePointByStepIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where distributePointByStep is not null
        defaultMDistributeCardParameterShouldBeFound("distributePointByStep.specified=true");

        // Get all the mDistributeCardParameterList where distributePointByStep is null
        defaultMDistributeCardParameterShouldNotBeFound("distributePointByStep.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByDistributePointByStepIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where distributePointByStep greater than or equals to DEFAULT_DISTRIBUTE_POINT_BY_STEP
        defaultMDistributeCardParameterShouldBeFound("distributePointByStep.greaterOrEqualThan=" + DEFAULT_DISTRIBUTE_POINT_BY_STEP);

        // Get all the mDistributeCardParameterList where distributePointByStep greater than or equals to UPDATED_DISTRIBUTE_POINT_BY_STEP
        defaultMDistributeCardParameterShouldNotBeFound("distributePointByStep.greaterOrEqualThan=" + UPDATED_DISTRIBUTE_POINT_BY_STEP);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParametersByDistributePointByStepIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        // Get all the mDistributeCardParameterList where distributePointByStep less than or equals to DEFAULT_DISTRIBUTE_POINT_BY_STEP
        defaultMDistributeCardParameterShouldNotBeFound("distributePointByStep.lessThan=" + DEFAULT_DISTRIBUTE_POINT_BY_STEP);

        // Get all the mDistributeCardParameterList where distributePointByStep less than or equals to UPDATED_DISTRIBUTE_POINT_BY_STEP
        defaultMDistributeCardParameterShouldBeFound("distributePointByStep.lessThan=" + UPDATED_DISTRIBUTE_POINT_BY_STEP);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDistributeCardParameterShouldBeFound(String filter) throws Exception {
        restMDistributeCardParameterMockMvc.perform(get("/api/m-distribute-card-parameters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDistributeCardParameter.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].isGk").value(hasItem(DEFAULT_IS_GK)))
            .andExpect(jsonPath("$.[*].maxStepCount").value(hasItem(DEFAULT_MAX_STEP_COUNT)))
            .andExpect(jsonPath("$.[*].maxTotalStepCount").value(hasItem(DEFAULT_MAX_TOTAL_STEP_COUNT)))
            .andExpect(jsonPath("$.[*].distributePointByStep").value(hasItem(DEFAULT_DISTRIBUTE_POINT_BY_STEP)));

        // Check, that the count call also returns 1
        restMDistributeCardParameterMockMvc.perform(get("/api/m-distribute-card-parameters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDistributeCardParameterShouldNotBeFound(String filter) throws Exception {
        restMDistributeCardParameterMockMvc.perform(get("/api/m-distribute-card-parameters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDistributeCardParameterMockMvc.perform(get("/api/m-distribute-card-parameters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDistributeCardParameter() throws Exception {
        // Get the mDistributeCardParameter
        restMDistributeCardParameterMockMvc.perform(get("/api/m-distribute-card-parameters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDistributeCardParameter() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        int databaseSizeBeforeUpdate = mDistributeCardParameterRepository.findAll().size();

        // Update the mDistributeCardParameter
        MDistributeCardParameter updatedMDistributeCardParameter = mDistributeCardParameterRepository.findById(mDistributeCardParameter.getId()).get();
        // Disconnect from session so that the updates on updatedMDistributeCardParameter are not directly saved in db
        em.detach(updatedMDistributeCardParameter);
        updatedMDistributeCardParameter
            .rarity(UPDATED_RARITY)
            .isGk(UPDATED_IS_GK)
            .maxStepCount(UPDATED_MAX_STEP_COUNT)
            .maxTotalStepCount(UPDATED_MAX_TOTAL_STEP_COUNT)
            .distributePointByStep(UPDATED_DISTRIBUTE_POINT_BY_STEP);
        MDistributeCardParameterDTO mDistributeCardParameterDTO = mDistributeCardParameterMapper.toDto(updatedMDistributeCardParameter);

        restMDistributeCardParameterMockMvc.perform(put("/api/m-distribute-card-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterDTO)))
            .andExpect(status().isOk());

        // Validate the MDistributeCardParameter in the database
        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeUpdate);
        MDistributeCardParameter testMDistributeCardParameter = mDistributeCardParameterList.get(mDistributeCardParameterList.size() - 1);
        assertThat(testMDistributeCardParameter.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMDistributeCardParameter.getIsGk()).isEqualTo(UPDATED_IS_GK);
        assertThat(testMDistributeCardParameter.getMaxStepCount()).isEqualTo(UPDATED_MAX_STEP_COUNT);
        assertThat(testMDistributeCardParameter.getMaxTotalStepCount()).isEqualTo(UPDATED_MAX_TOTAL_STEP_COUNT);
        assertThat(testMDistributeCardParameter.getDistributePointByStep()).isEqualTo(UPDATED_DISTRIBUTE_POINT_BY_STEP);
    }

    @Test
    @Transactional
    public void updateNonExistingMDistributeCardParameter() throws Exception {
        int databaseSizeBeforeUpdate = mDistributeCardParameterRepository.findAll().size();

        // Create the MDistributeCardParameter
        MDistributeCardParameterDTO mDistributeCardParameterDTO = mDistributeCardParameterMapper.toDto(mDistributeCardParameter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDistributeCardParameterMockMvc.perform(put("/api/m-distribute-card-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDistributeCardParameter in the database
        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDistributeCardParameter() throws Exception {
        // Initialize the database
        mDistributeCardParameterRepository.saveAndFlush(mDistributeCardParameter);

        int databaseSizeBeforeDelete = mDistributeCardParameterRepository.findAll().size();

        // Delete the mDistributeCardParameter
        restMDistributeCardParameterMockMvc.perform(delete("/api/m-distribute-card-parameters/{id}", mDistributeCardParameter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDistributeCardParameter> mDistributeCardParameterList = mDistributeCardParameterRepository.findAll();
        assertThat(mDistributeCardParameterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDistributeCardParameter.class);
        MDistributeCardParameter mDistributeCardParameter1 = new MDistributeCardParameter();
        mDistributeCardParameter1.setId(1L);
        MDistributeCardParameter mDistributeCardParameter2 = new MDistributeCardParameter();
        mDistributeCardParameter2.setId(mDistributeCardParameter1.getId());
        assertThat(mDistributeCardParameter1).isEqualTo(mDistributeCardParameter2);
        mDistributeCardParameter2.setId(2L);
        assertThat(mDistributeCardParameter1).isNotEqualTo(mDistributeCardParameter2);
        mDistributeCardParameter1.setId(null);
        assertThat(mDistributeCardParameter1).isNotEqualTo(mDistributeCardParameter2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDistributeCardParameterDTO.class);
        MDistributeCardParameterDTO mDistributeCardParameterDTO1 = new MDistributeCardParameterDTO();
        mDistributeCardParameterDTO1.setId(1L);
        MDistributeCardParameterDTO mDistributeCardParameterDTO2 = new MDistributeCardParameterDTO();
        assertThat(mDistributeCardParameterDTO1).isNotEqualTo(mDistributeCardParameterDTO2);
        mDistributeCardParameterDTO2.setId(mDistributeCardParameterDTO1.getId());
        assertThat(mDistributeCardParameterDTO1).isEqualTo(mDistributeCardParameterDTO2);
        mDistributeCardParameterDTO2.setId(2L);
        assertThat(mDistributeCardParameterDTO1).isNotEqualTo(mDistributeCardParameterDTO2);
        mDistributeCardParameterDTO1.setId(null);
        assertThat(mDistributeCardParameterDTO1).isNotEqualTo(mDistributeCardParameterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDistributeCardParameterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDistributeCardParameterMapper.fromId(null)).isNull();
    }
}
