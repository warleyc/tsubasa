package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMatchFormation;
import io.shm.tsubasa.repository.MMatchFormationRepository;
import io.shm.tsubasa.service.MMatchFormationService;
import io.shm.tsubasa.service.dto.MMatchFormationDTO;
import io.shm.tsubasa.service.mapper.MMatchFormationMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMatchFormationCriteria;
import io.shm.tsubasa.service.MMatchFormationQueryService;

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
 * Integration tests for the {@Link MMatchFormationResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMatchFormationResourceIT {

    private static final Integer DEFAULT_POSITION_1 = 1;
    private static final Integer UPDATED_POSITION_1 = 2;

    private static final Integer DEFAULT_POSITION_2 = 1;
    private static final Integer UPDATED_POSITION_2 = 2;

    private static final Integer DEFAULT_POSITION_3 = 1;
    private static final Integer UPDATED_POSITION_3 = 2;

    private static final Integer DEFAULT_POSITION_4 = 1;
    private static final Integer UPDATED_POSITION_4 = 2;

    private static final Integer DEFAULT_POSITION_5 = 1;
    private static final Integer UPDATED_POSITION_5 = 2;

    private static final Integer DEFAULT_POSITION_6 = 1;
    private static final Integer UPDATED_POSITION_6 = 2;

    private static final Integer DEFAULT_POSITION_7 = 1;
    private static final Integer UPDATED_POSITION_7 = 2;

    private static final Integer DEFAULT_POSITION_8 = 1;
    private static final Integer UPDATED_POSITION_8 = 2;

    private static final Integer DEFAULT_POSITION_9 = 1;
    private static final Integer UPDATED_POSITION_9 = 2;

    private static final Integer DEFAULT_POSITION_10 = 1;
    private static final Integer UPDATED_POSITION_10 = 2;

    private static final Integer DEFAULT_POSITION_11 = 1;
    private static final Integer UPDATED_POSITION_11 = 2;

    @Autowired
    private MMatchFormationRepository mMatchFormationRepository;

    @Autowired
    private MMatchFormationMapper mMatchFormationMapper;

    @Autowired
    private MMatchFormationService mMatchFormationService;

    @Autowired
    private MMatchFormationQueryService mMatchFormationQueryService;

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

    private MockMvc restMMatchFormationMockMvc;

    private MMatchFormation mMatchFormation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMatchFormationResource mMatchFormationResource = new MMatchFormationResource(mMatchFormationService, mMatchFormationQueryService);
        this.restMMatchFormationMockMvc = MockMvcBuilders.standaloneSetup(mMatchFormationResource)
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
    public static MMatchFormation createEntity(EntityManager em) {
        MMatchFormation mMatchFormation = new MMatchFormation()
            .position1(DEFAULT_POSITION_1)
            .position2(DEFAULT_POSITION_2)
            .position3(DEFAULT_POSITION_3)
            .position4(DEFAULT_POSITION_4)
            .position5(DEFAULT_POSITION_5)
            .position6(DEFAULT_POSITION_6)
            .position7(DEFAULT_POSITION_7)
            .position8(DEFAULT_POSITION_8)
            .position9(DEFAULT_POSITION_9)
            .position10(DEFAULT_POSITION_10)
            .position11(DEFAULT_POSITION_11);
        return mMatchFormation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMatchFormation createUpdatedEntity(EntityManager em) {
        MMatchFormation mMatchFormation = new MMatchFormation()
            .position1(UPDATED_POSITION_1)
            .position2(UPDATED_POSITION_2)
            .position3(UPDATED_POSITION_3)
            .position4(UPDATED_POSITION_4)
            .position5(UPDATED_POSITION_5)
            .position6(UPDATED_POSITION_6)
            .position7(UPDATED_POSITION_7)
            .position8(UPDATED_POSITION_8)
            .position9(UPDATED_POSITION_9)
            .position10(UPDATED_POSITION_10)
            .position11(UPDATED_POSITION_11);
        return mMatchFormation;
    }

    @BeforeEach
    public void initTest() {
        mMatchFormation = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMatchFormation() throws Exception {
        int databaseSizeBeforeCreate = mMatchFormationRepository.findAll().size();

        // Create the MMatchFormation
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);
        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isCreated());

        // Validate the MMatchFormation in the database
        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeCreate + 1);
        MMatchFormation testMMatchFormation = mMatchFormationList.get(mMatchFormationList.size() - 1);
        assertThat(testMMatchFormation.getPosition1()).isEqualTo(DEFAULT_POSITION_1);
        assertThat(testMMatchFormation.getPosition2()).isEqualTo(DEFAULT_POSITION_2);
        assertThat(testMMatchFormation.getPosition3()).isEqualTo(DEFAULT_POSITION_3);
        assertThat(testMMatchFormation.getPosition4()).isEqualTo(DEFAULT_POSITION_4);
        assertThat(testMMatchFormation.getPosition5()).isEqualTo(DEFAULT_POSITION_5);
        assertThat(testMMatchFormation.getPosition6()).isEqualTo(DEFAULT_POSITION_6);
        assertThat(testMMatchFormation.getPosition7()).isEqualTo(DEFAULT_POSITION_7);
        assertThat(testMMatchFormation.getPosition8()).isEqualTo(DEFAULT_POSITION_8);
        assertThat(testMMatchFormation.getPosition9()).isEqualTo(DEFAULT_POSITION_9);
        assertThat(testMMatchFormation.getPosition10()).isEqualTo(DEFAULT_POSITION_10);
        assertThat(testMMatchFormation.getPosition11()).isEqualTo(DEFAULT_POSITION_11);
    }

    @Test
    @Transactional
    public void createMMatchFormationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMatchFormationRepository.findAll().size();

        // Create the MMatchFormation with an existing ID
        mMatchFormation.setId(1L);
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMatchFormation in the database
        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPosition1IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition1(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition2IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition2(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition3IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition3(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition4IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition4(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition5IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition5(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition6IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition6(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition7IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition7(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition8IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition8(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition9IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition9(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition10IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition10(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPosition11IsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchFormationRepository.findAll().size();
        // set the field null
        mMatchFormation.setPosition11(null);

        // Create the MMatchFormation, which fails.
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        restMMatchFormationMockMvc.perform(post("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMatchFormations() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList
        restMMatchFormationMockMvc.perform(get("/api/m-match-formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMatchFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].position1").value(hasItem(DEFAULT_POSITION_1)))
            .andExpect(jsonPath("$.[*].position2").value(hasItem(DEFAULT_POSITION_2)))
            .andExpect(jsonPath("$.[*].position3").value(hasItem(DEFAULT_POSITION_3)))
            .andExpect(jsonPath("$.[*].position4").value(hasItem(DEFAULT_POSITION_4)))
            .andExpect(jsonPath("$.[*].position5").value(hasItem(DEFAULT_POSITION_5)))
            .andExpect(jsonPath("$.[*].position6").value(hasItem(DEFAULT_POSITION_6)))
            .andExpect(jsonPath("$.[*].position7").value(hasItem(DEFAULT_POSITION_7)))
            .andExpect(jsonPath("$.[*].position8").value(hasItem(DEFAULT_POSITION_8)))
            .andExpect(jsonPath("$.[*].position9").value(hasItem(DEFAULT_POSITION_9)))
            .andExpect(jsonPath("$.[*].position10").value(hasItem(DEFAULT_POSITION_10)))
            .andExpect(jsonPath("$.[*].position11").value(hasItem(DEFAULT_POSITION_11)));
    }
    
    @Test
    @Transactional
    public void getMMatchFormation() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get the mMatchFormation
        restMMatchFormationMockMvc.perform(get("/api/m-match-formations/{id}", mMatchFormation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMatchFormation.getId().intValue()))
            .andExpect(jsonPath("$.position1").value(DEFAULT_POSITION_1))
            .andExpect(jsonPath("$.position2").value(DEFAULT_POSITION_2))
            .andExpect(jsonPath("$.position3").value(DEFAULT_POSITION_3))
            .andExpect(jsonPath("$.position4").value(DEFAULT_POSITION_4))
            .andExpect(jsonPath("$.position5").value(DEFAULT_POSITION_5))
            .andExpect(jsonPath("$.position6").value(DEFAULT_POSITION_6))
            .andExpect(jsonPath("$.position7").value(DEFAULT_POSITION_7))
            .andExpect(jsonPath("$.position8").value(DEFAULT_POSITION_8))
            .andExpect(jsonPath("$.position9").value(DEFAULT_POSITION_9))
            .andExpect(jsonPath("$.position10").value(DEFAULT_POSITION_10))
            .andExpect(jsonPath("$.position11").value(DEFAULT_POSITION_11));
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition1IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position1 equals to DEFAULT_POSITION_1
        defaultMMatchFormationShouldBeFound("position1.equals=" + DEFAULT_POSITION_1);

        // Get all the mMatchFormationList where position1 equals to UPDATED_POSITION_1
        defaultMMatchFormationShouldNotBeFound("position1.equals=" + UPDATED_POSITION_1);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition1IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position1 in DEFAULT_POSITION_1 or UPDATED_POSITION_1
        defaultMMatchFormationShouldBeFound("position1.in=" + DEFAULT_POSITION_1 + "," + UPDATED_POSITION_1);

        // Get all the mMatchFormationList where position1 equals to UPDATED_POSITION_1
        defaultMMatchFormationShouldNotBeFound("position1.in=" + UPDATED_POSITION_1);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition1IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position1 is not null
        defaultMMatchFormationShouldBeFound("position1.specified=true");

        // Get all the mMatchFormationList where position1 is null
        defaultMMatchFormationShouldNotBeFound("position1.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position1 greater than or equals to DEFAULT_POSITION_1
        defaultMMatchFormationShouldBeFound("position1.greaterOrEqualThan=" + DEFAULT_POSITION_1);

        // Get all the mMatchFormationList where position1 greater than or equals to UPDATED_POSITION_1
        defaultMMatchFormationShouldNotBeFound("position1.greaterOrEqualThan=" + UPDATED_POSITION_1);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition1IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position1 less than or equals to DEFAULT_POSITION_1
        defaultMMatchFormationShouldNotBeFound("position1.lessThan=" + DEFAULT_POSITION_1);

        // Get all the mMatchFormationList where position1 less than or equals to UPDATED_POSITION_1
        defaultMMatchFormationShouldBeFound("position1.lessThan=" + UPDATED_POSITION_1);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition2IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position2 equals to DEFAULT_POSITION_2
        defaultMMatchFormationShouldBeFound("position2.equals=" + DEFAULT_POSITION_2);

        // Get all the mMatchFormationList where position2 equals to UPDATED_POSITION_2
        defaultMMatchFormationShouldNotBeFound("position2.equals=" + UPDATED_POSITION_2);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition2IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position2 in DEFAULT_POSITION_2 or UPDATED_POSITION_2
        defaultMMatchFormationShouldBeFound("position2.in=" + DEFAULT_POSITION_2 + "," + UPDATED_POSITION_2);

        // Get all the mMatchFormationList where position2 equals to UPDATED_POSITION_2
        defaultMMatchFormationShouldNotBeFound("position2.in=" + UPDATED_POSITION_2);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position2 is not null
        defaultMMatchFormationShouldBeFound("position2.specified=true");

        // Get all the mMatchFormationList where position2 is null
        defaultMMatchFormationShouldNotBeFound("position2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position2 greater than or equals to DEFAULT_POSITION_2
        defaultMMatchFormationShouldBeFound("position2.greaterOrEqualThan=" + DEFAULT_POSITION_2);

        // Get all the mMatchFormationList where position2 greater than or equals to UPDATED_POSITION_2
        defaultMMatchFormationShouldNotBeFound("position2.greaterOrEqualThan=" + UPDATED_POSITION_2);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition2IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position2 less than or equals to DEFAULT_POSITION_2
        defaultMMatchFormationShouldNotBeFound("position2.lessThan=" + DEFAULT_POSITION_2);

        // Get all the mMatchFormationList where position2 less than or equals to UPDATED_POSITION_2
        defaultMMatchFormationShouldBeFound("position2.lessThan=" + UPDATED_POSITION_2);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition3IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position3 equals to DEFAULT_POSITION_3
        defaultMMatchFormationShouldBeFound("position3.equals=" + DEFAULT_POSITION_3);

        // Get all the mMatchFormationList where position3 equals to UPDATED_POSITION_3
        defaultMMatchFormationShouldNotBeFound("position3.equals=" + UPDATED_POSITION_3);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition3IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position3 in DEFAULT_POSITION_3 or UPDATED_POSITION_3
        defaultMMatchFormationShouldBeFound("position3.in=" + DEFAULT_POSITION_3 + "," + UPDATED_POSITION_3);

        // Get all the mMatchFormationList where position3 equals to UPDATED_POSITION_3
        defaultMMatchFormationShouldNotBeFound("position3.in=" + UPDATED_POSITION_3);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition3IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position3 is not null
        defaultMMatchFormationShouldBeFound("position3.specified=true");

        // Get all the mMatchFormationList where position3 is null
        defaultMMatchFormationShouldNotBeFound("position3.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position3 greater than or equals to DEFAULT_POSITION_3
        defaultMMatchFormationShouldBeFound("position3.greaterOrEqualThan=" + DEFAULT_POSITION_3);

        // Get all the mMatchFormationList where position3 greater than or equals to UPDATED_POSITION_3
        defaultMMatchFormationShouldNotBeFound("position3.greaterOrEqualThan=" + UPDATED_POSITION_3);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition3IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position3 less than or equals to DEFAULT_POSITION_3
        defaultMMatchFormationShouldNotBeFound("position3.lessThan=" + DEFAULT_POSITION_3);

        // Get all the mMatchFormationList where position3 less than or equals to UPDATED_POSITION_3
        defaultMMatchFormationShouldBeFound("position3.lessThan=" + UPDATED_POSITION_3);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition4IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position4 equals to DEFAULT_POSITION_4
        defaultMMatchFormationShouldBeFound("position4.equals=" + DEFAULT_POSITION_4);

        // Get all the mMatchFormationList where position4 equals to UPDATED_POSITION_4
        defaultMMatchFormationShouldNotBeFound("position4.equals=" + UPDATED_POSITION_4);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition4IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position4 in DEFAULT_POSITION_4 or UPDATED_POSITION_4
        defaultMMatchFormationShouldBeFound("position4.in=" + DEFAULT_POSITION_4 + "," + UPDATED_POSITION_4);

        // Get all the mMatchFormationList where position4 equals to UPDATED_POSITION_4
        defaultMMatchFormationShouldNotBeFound("position4.in=" + UPDATED_POSITION_4);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition4IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position4 is not null
        defaultMMatchFormationShouldBeFound("position4.specified=true");

        // Get all the mMatchFormationList where position4 is null
        defaultMMatchFormationShouldNotBeFound("position4.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position4 greater than or equals to DEFAULT_POSITION_4
        defaultMMatchFormationShouldBeFound("position4.greaterOrEqualThan=" + DEFAULT_POSITION_4);

        // Get all the mMatchFormationList where position4 greater than or equals to UPDATED_POSITION_4
        defaultMMatchFormationShouldNotBeFound("position4.greaterOrEqualThan=" + UPDATED_POSITION_4);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition4IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position4 less than or equals to DEFAULT_POSITION_4
        defaultMMatchFormationShouldNotBeFound("position4.lessThan=" + DEFAULT_POSITION_4);

        // Get all the mMatchFormationList where position4 less than or equals to UPDATED_POSITION_4
        defaultMMatchFormationShouldBeFound("position4.lessThan=" + UPDATED_POSITION_4);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition5IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position5 equals to DEFAULT_POSITION_5
        defaultMMatchFormationShouldBeFound("position5.equals=" + DEFAULT_POSITION_5);

        // Get all the mMatchFormationList where position5 equals to UPDATED_POSITION_5
        defaultMMatchFormationShouldNotBeFound("position5.equals=" + UPDATED_POSITION_5);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition5IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position5 in DEFAULT_POSITION_5 or UPDATED_POSITION_5
        defaultMMatchFormationShouldBeFound("position5.in=" + DEFAULT_POSITION_5 + "," + UPDATED_POSITION_5);

        // Get all the mMatchFormationList where position5 equals to UPDATED_POSITION_5
        defaultMMatchFormationShouldNotBeFound("position5.in=" + UPDATED_POSITION_5);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition5IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position5 is not null
        defaultMMatchFormationShouldBeFound("position5.specified=true");

        // Get all the mMatchFormationList where position5 is null
        defaultMMatchFormationShouldNotBeFound("position5.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position5 greater than or equals to DEFAULT_POSITION_5
        defaultMMatchFormationShouldBeFound("position5.greaterOrEqualThan=" + DEFAULT_POSITION_5);

        // Get all the mMatchFormationList where position5 greater than or equals to UPDATED_POSITION_5
        defaultMMatchFormationShouldNotBeFound("position5.greaterOrEqualThan=" + UPDATED_POSITION_5);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition5IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position5 less than or equals to DEFAULT_POSITION_5
        defaultMMatchFormationShouldNotBeFound("position5.lessThan=" + DEFAULT_POSITION_5);

        // Get all the mMatchFormationList where position5 less than or equals to UPDATED_POSITION_5
        defaultMMatchFormationShouldBeFound("position5.lessThan=" + UPDATED_POSITION_5);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition6IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position6 equals to DEFAULT_POSITION_6
        defaultMMatchFormationShouldBeFound("position6.equals=" + DEFAULT_POSITION_6);

        // Get all the mMatchFormationList where position6 equals to UPDATED_POSITION_6
        defaultMMatchFormationShouldNotBeFound("position6.equals=" + UPDATED_POSITION_6);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition6IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position6 in DEFAULT_POSITION_6 or UPDATED_POSITION_6
        defaultMMatchFormationShouldBeFound("position6.in=" + DEFAULT_POSITION_6 + "," + UPDATED_POSITION_6);

        // Get all the mMatchFormationList where position6 equals to UPDATED_POSITION_6
        defaultMMatchFormationShouldNotBeFound("position6.in=" + UPDATED_POSITION_6);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition6IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position6 is not null
        defaultMMatchFormationShouldBeFound("position6.specified=true");

        // Get all the mMatchFormationList where position6 is null
        defaultMMatchFormationShouldNotBeFound("position6.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position6 greater than or equals to DEFAULT_POSITION_6
        defaultMMatchFormationShouldBeFound("position6.greaterOrEqualThan=" + DEFAULT_POSITION_6);

        // Get all the mMatchFormationList where position6 greater than or equals to UPDATED_POSITION_6
        defaultMMatchFormationShouldNotBeFound("position6.greaterOrEqualThan=" + UPDATED_POSITION_6);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition6IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position6 less than or equals to DEFAULT_POSITION_6
        defaultMMatchFormationShouldNotBeFound("position6.lessThan=" + DEFAULT_POSITION_6);

        // Get all the mMatchFormationList where position6 less than or equals to UPDATED_POSITION_6
        defaultMMatchFormationShouldBeFound("position6.lessThan=" + UPDATED_POSITION_6);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition7IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position7 equals to DEFAULT_POSITION_7
        defaultMMatchFormationShouldBeFound("position7.equals=" + DEFAULT_POSITION_7);

        // Get all the mMatchFormationList where position7 equals to UPDATED_POSITION_7
        defaultMMatchFormationShouldNotBeFound("position7.equals=" + UPDATED_POSITION_7);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition7IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position7 in DEFAULT_POSITION_7 or UPDATED_POSITION_7
        defaultMMatchFormationShouldBeFound("position7.in=" + DEFAULT_POSITION_7 + "," + UPDATED_POSITION_7);

        // Get all the mMatchFormationList where position7 equals to UPDATED_POSITION_7
        defaultMMatchFormationShouldNotBeFound("position7.in=" + UPDATED_POSITION_7);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition7IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position7 is not null
        defaultMMatchFormationShouldBeFound("position7.specified=true");

        // Get all the mMatchFormationList where position7 is null
        defaultMMatchFormationShouldNotBeFound("position7.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition7IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position7 greater than or equals to DEFAULT_POSITION_7
        defaultMMatchFormationShouldBeFound("position7.greaterOrEqualThan=" + DEFAULT_POSITION_7);

        // Get all the mMatchFormationList where position7 greater than or equals to UPDATED_POSITION_7
        defaultMMatchFormationShouldNotBeFound("position7.greaterOrEqualThan=" + UPDATED_POSITION_7);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition7IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position7 less than or equals to DEFAULT_POSITION_7
        defaultMMatchFormationShouldNotBeFound("position7.lessThan=" + DEFAULT_POSITION_7);

        // Get all the mMatchFormationList where position7 less than or equals to UPDATED_POSITION_7
        defaultMMatchFormationShouldBeFound("position7.lessThan=" + UPDATED_POSITION_7);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition8IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position8 equals to DEFAULT_POSITION_8
        defaultMMatchFormationShouldBeFound("position8.equals=" + DEFAULT_POSITION_8);

        // Get all the mMatchFormationList where position8 equals to UPDATED_POSITION_8
        defaultMMatchFormationShouldNotBeFound("position8.equals=" + UPDATED_POSITION_8);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition8IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position8 in DEFAULT_POSITION_8 or UPDATED_POSITION_8
        defaultMMatchFormationShouldBeFound("position8.in=" + DEFAULT_POSITION_8 + "," + UPDATED_POSITION_8);

        // Get all the mMatchFormationList where position8 equals to UPDATED_POSITION_8
        defaultMMatchFormationShouldNotBeFound("position8.in=" + UPDATED_POSITION_8);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition8IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position8 is not null
        defaultMMatchFormationShouldBeFound("position8.specified=true");

        // Get all the mMatchFormationList where position8 is null
        defaultMMatchFormationShouldNotBeFound("position8.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition8IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position8 greater than or equals to DEFAULT_POSITION_8
        defaultMMatchFormationShouldBeFound("position8.greaterOrEqualThan=" + DEFAULT_POSITION_8);

        // Get all the mMatchFormationList where position8 greater than or equals to UPDATED_POSITION_8
        defaultMMatchFormationShouldNotBeFound("position8.greaterOrEqualThan=" + UPDATED_POSITION_8);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition8IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position8 less than or equals to DEFAULT_POSITION_8
        defaultMMatchFormationShouldNotBeFound("position8.lessThan=" + DEFAULT_POSITION_8);

        // Get all the mMatchFormationList where position8 less than or equals to UPDATED_POSITION_8
        defaultMMatchFormationShouldBeFound("position8.lessThan=" + UPDATED_POSITION_8);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition9IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position9 equals to DEFAULT_POSITION_9
        defaultMMatchFormationShouldBeFound("position9.equals=" + DEFAULT_POSITION_9);

        // Get all the mMatchFormationList where position9 equals to UPDATED_POSITION_9
        defaultMMatchFormationShouldNotBeFound("position9.equals=" + UPDATED_POSITION_9);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition9IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position9 in DEFAULT_POSITION_9 or UPDATED_POSITION_9
        defaultMMatchFormationShouldBeFound("position9.in=" + DEFAULT_POSITION_9 + "," + UPDATED_POSITION_9);

        // Get all the mMatchFormationList where position9 equals to UPDATED_POSITION_9
        defaultMMatchFormationShouldNotBeFound("position9.in=" + UPDATED_POSITION_9);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition9IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position9 is not null
        defaultMMatchFormationShouldBeFound("position9.specified=true");

        // Get all the mMatchFormationList where position9 is null
        defaultMMatchFormationShouldNotBeFound("position9.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition9IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position9 greater than or equals to DEFAULT_POSITION_9
        defaultMMatchFormationShouldBeFound("position9.greaterOrEqualThan=" + DEFAULT_POSITION_9);

        // Get all the mMatchFormationList where position9 greater than or equals to UPDATED_POSITION_9
        defaultMMatchFormationShouldNotBeFound("position9.greaterOrEqualThan=" + UPDATED_POSITION_9);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition9IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position9 less than or equals to DEFAULT_POSITION_9
        defaultMMatchFormationShouldNotBeFound("position9.lessThan=" + DEFAULT_POSITION_9);

        // Get all the mMatchFormationList where position9 less than or equals to UPDATED_POSITION_9
        defaultMMatchFormationShouldBeFound("position9.lessThan=" + UPDATED_POSITION_9);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition10IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position10 equals to DEFAULT_POSITION_10
        defaultMMatchFormationShouldBeFound("position10.equals=" + DEFAULT_POSITION_10);

        // Get all the mMatchFormationList where position10 equals to UPDATED_POSITION_10
        defaultMMatchFormationShouldNotBeFound("position10.equals=" + UPDATED_POSITION_10);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition10IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position10 in DEFAULT_POSITION_10 or UPDATED_POSITION_10
        defaultMMatchFormationShouldBeFound("position10.in=" + DEFAULT_POSITION_10 + "," + UPDATED_POSITION_10);

        // Get all the mMatchFormationList where position10 equals to UPDATED_POSITION_10
        defaultMMatchFormationShouldNotBeFound("position10.in=" + UPDATED_POSITION_10);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition10IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position10 is not null
        defaultMMatchFormationShouldBeFound("position10.specified=true");

        // Get all the mMatchFormationList where position10 is null
        defaultMMatchFormationShouldNotBeFound("position10.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition10IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position10 greater than or equals to DEFAULT_POSITION_10
        defaultMMatchFormationShouldBeFound("position10.greaterOrEqualThan=" + DEFAULT_POSITION_10);

        // Get all the mMatchFormationList where position10 greater than or equals to UPDATED_POSITION_10
        defaultMMatchFormationShouldNotBeFound("position10.greaterOrEqualThan=" + UPDATED_POSITION_10);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition10IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position10 less than or equals to DEFAULT_POSITION_10
        defaultMMatchFormationShouldNotBeFound("position10.lessThan=" + DEFAULT_POSITION_10);

        // Get all the mMatchFormationList where position10 less than or equals to UPDATED_POSITION_10
        defaultMMatchFormationShouldBeFound("position10.lessThan=" + UPDATED_POSITION_10);
    }


    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition11IsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position11 equals to DEFAULT_POSITION_11
        defaultMMatchFormationShouldBeFound("position11.equals=" + DEFAULT_POSITION_11);

        // Get all the mMatchFormationList where position11 equals to UPDATED_POSITION_11
        defaultMMatchFormationShouldNotBeFound("position11.equals=" + UPDATED_POSITION_11);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition11IsInShouldWork() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position11 in DEFAULT_POSITION_11 or UPDATED_POSITION_11
        defaultMMatchFormationShouldBeFound("position11.in=" + DEFAULT_POSITION_11 + "," + UPDATED_POSITION_11);

        // Get all the mMatchFormationList where position11 equals to UPDATED_POSITION_11
        defaultMMatchFormationShouldNotBeFound("position11.in=" + UPDATED_POSITION_11);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition11IsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position11 is not null
        defaultMMatchFormationShouldBeFound("position11.specified=true");

        // Get all the mMatchFormationList where position11 is null
        defaultMMatchFormationShouldNotBeFound("position11.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition11IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position11 greater than or equals to DEFAULT_POSITION_11
        defaultMMatchFormationShouldBeFound("position11.greaterOrEqualThan=" + DEFAULT_POSITION_11);

        // Get all the mMatchFormationList where position11 greater than or equals to UPDATED_POSITION_11
        defaultMMatchFormationShouldNotBeFound("position11.greaterOrEqualThan=" + UPDATED_POSITION_11);
    }

    @Test
    @Transactional
    public void getAllMMatchFormationsByPosition11IsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        // Get all the mMatchFormationList where position11 less than or equals to DEFAULT_POSITION_11
        defaultMMatchFormationShouldNotBeFound("position11.lessThan=" + DEFAULT_POSITION_11);

        // Get all the mMatchFormationList where position11 less than or equals to UPDATED_POSITION_11
        defaultMMatchFormationShouldBeFound("position11.lessThan=" + UPDATED_POSITION_11);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMatchFormationShouldBeFound(String filter) throws Exception {
        restMMatchFormationMockMvc.perform(get("/api/m-match-formations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMatchFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].position1").value(hasItem(DEFAULT_POSITION_1)))
            .andExpect(jsonPath("$.[*].position2").value(hasItem(DEFAULT_POSITION_2)))
            .andExpect(jsonPath("$.[*].position3").value(hasItem(DEFAULT_POSITION_3)))
            .andExpect(jsonPath("$.[*].position4").value(hasItem(DEFAULT_POSITION_4)))
            .andExpect(jsonPath("$.[*].position5").value(hasItem(DEFAULT_POSITION_5)))
            .andExpect(jsonPath("$.[*].position6").value(hasItem(DEFAULT_POSITION_6)))
            .andExpect(jsonPath("$.[*].position7").value(hasItem(DEFAULT_POSITION_7)))
            .andExpect(jsonPath("$.[*].position8").value(hasItem(DEFAULT_POSITION_8)))
            .andExpect(jsonPath("$.[*].position9").value(hasItem(DEFAULT_POSITION_9)))
            .andExpect(jsonPath("$.[*].position10").value(hasItem(DEFAULT_POSITION_10)))
            .andExpect(jsonPath("$.[*].position11").value(hasItem(DEFAULT_POSITION_11)));

        // Check, that the count call also returns 1
        restMMatchFormationMockMvc.perform(get("/api/m-match-formations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMatchFormationShouldNotBeFound(String filter) throws Exception {
        restMMatchFormationMockMvc.perform(get("/api/m-match-formations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMatchFormationMockMvc.perform(get("/api/m-match-formations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMatchFormation() throws Exception {
        // Get the mMatchFormation
        restMMatchFormationMockMvc.perform(get("/api/m-match-formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMatchFormation() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        int databaseSizeBeforeUpdate = mMatchFormationRepository.findAll().size();

        // Update the mMatchFormation
        MMatchFormation updatedMMatchFormation = mMatchFormationRepository.findById(mMatchFormation.getId()).get();
        // Disconnect from session so that the updates on updatedMMatchFormation are not directly saved in db
        em.detach(updatedMMatchFormation);
        updatedMMatchFormation
            .position1(UPDATED_POSITION_1)
            .position2(UPDATED_POSITION_2)
            .position3(UPDATED_POSITION_3)
            .position4(UPDATED_POSITION_4)
            .position5(UPDATED_POSITION_5)
            .position6(UPDATED_POSITION_6)
            .position7(UPDATED_POSITION_7)
            .position8(UPDATED_POSITION_8)
            .position9(UPDATED_POSITION_9)
            .position10(UPDATED_POSITION_10)
            .position11(UPDATED_POSITION_11);
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(updatedMMatchFormation);

        restMMatchFormationMockMvc.perform(put("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isOk());

        // Validate the MMatchFormation in the database
        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeUpdate);
        MMatchFormation testMMatchFormation = mMatchFormationList.get(mMatchFormationList.size() - 1);
        assertThat(testMMatchFormation.getPosition1()).isEqualTo(UPDATED_POSITION_1);
        assertThat(testMMatchFormation.getPosition2()).isEqualTo(UPDATED_POSITION_2);
        assertThat(testMMatchFormation.getPosition3()).isEqualTo(UPDATED_POSITION_3);
        assertThat(testMMatchFormation.getPosition4()).isEqualTo(UPDATED_POSITION_4);
        assertThat(testMMatchFormation.getPosition5()).isEqualTo(UPDATED_POSITION_5);
        assertThat(testMMatchFormation.getPosition6()).isEqualTo(UPDATED_POSITION_6);
        assertThat(testMMatchFormation.getPosition7()).isEqualTo(UPDATED_POSITION_7);
        assertThat(testMMatchFormation.getPosition8()).isEqualTo(UPDATED_POSITION_8);
        assertThat(testMMatchFormation.getPosition9()).isEqualTo(UPDATED_POSITION_9);
        assertThat(testMMatchFormation.getPosition10()).isEqualTo(UPDATED_POSITION_10);
        assertThat(testMMatchFormation.getPosition11()).isEqualTo(UPDATED_POSITION_11);
    }

    @Test
    @Transactional
    public void updateNonExistingMMatchFormation() throws Exception {
        int databaseSizeBeforeUpdate = mMatchFormationRepository.findAll().size();

        // Create the MMatchFormation
        MMatchFormationDTO mMatchFormationDTO = mMatchFormationMapper.toDto(mMatchFormation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMatchFormationMockMvc.perform(put("/api/m-match-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchFormationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMatchFormation in the database
        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMatchFormation() throws Exception {
        // Initialize the database
        mMatchFormationRepository.saveAndFlush(mMatchFormation);

        int databaseSizeBeforeDelete = mMatchFormationRepository.findAll().size();

        // Delete the mMatchFormation
        restMMatchFormationMockMvc.perform(delete("/api/m-match-formations/{id}", mMatchFormation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMatchFormation> mMatchFormationList = mMatchFormationRepository.findAll();
        assertThat(mMatchFormationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMatchFormation.class);
        MMatchFormation mMatchFormation1 = new MMatchFormation();
        mMatchFormation1.setId(1L);
        MMatchFormation mMatchFormation2 = new MMatchFormation();
        mMatchFormation2.setId(mMatchFormation1.getId());
        assertThat(mMatchFormation1).isEqualTo(mMatchFormation2);
        mMatchFormation2.setId(2L);
        assertThat(mMatchFormation1).isNotEqualTo(mMatchFormation2);
        mMatchFormation1.setId(null);
        assertThat(mMatchFormation1).isNotEqualTo(mMatchFormation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMatchFormationDTO.class);
        MMatchFormationDTO mMatchFormationDTO1 = new MMatchFormationDTO();
        mMatchFormationDTO1.setId(1L);
        MMatchFormationDTO mMatchFormationDTO2 = new MMatchFormationDTO();
        assertThat(mMatchFormationDTO1).isNotEqualTo(mMatchFormationDTO2);
        mMatchFormationDTO2.setId(mMatchFormationDTO1.getId());
        assertThat(mMatchFormationDTO1).isEqualTo(mMatchFormationDTO2);
        mMatchFormationDTO2.setId(2L);
        assertThat(mMatchFormationDTO1).isNotEqualTo(mMatchFormationDTO2);
        mMatchFormationDTO1.setId(null);
        assertThat(mMatchFormationDTO1).isNotEqualTo(mMatchFormationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMatchFormationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMatchFormationMapper.fromId(null)).isNull();
    }
}
