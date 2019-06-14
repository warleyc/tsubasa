package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestClearRewardWeight;
import io.shm.tsubasa.repository.MQuestClearRewardWeightRepository;
import io.shm.tsubasa.service.MQuestClearRewardWeightService;
import io.shm.tsubasa.service.dto.MQuestClearRewardWeightDTO;
import io.shm.tsubasa.service.mapper.MQuestClearRewardWeightMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestClearRewardWeightCriteria;
import io.shm.tsubasa.service.MQuestClearRewardWeightQueryService;

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
 * Integration tests for the {@Link MQuestClearRewardWeightResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestClearRewardWeightResourceIT {

    private static final Integer DEFAULT_REWARD_1 = 1;
    private static final Integer UPDATED_REWARD_1 = 2;

    private static final Integer DEFAULT_REWARD_2 = 1;
    private static final Integer UPDATED_REWARD_2 = 2;

    private static final Integer DEFAULT_REWARD_3 = 1;
    private static final Integer UPDATED_REWARD_3 = 2;

    private static final Integer DEFAULT_REWARD_4 = 1;
    private static final Integer UPDATED_REWARD_4 = 2;

    private static final Integer DEFAULT_REWARD_5 = 1;
    private static final Integer UPDATED_REWARD_5 = 2;

    @Autowired
    private MQuestClearRewardWeightRepository mQuestClearRewardWeightRepository;

    @Autowired
    private MQuestClearRewardWeightMapper mQuestClearRewardWeightMapper;

    @Autowired
    private MQuestClearRewardWeightService mQuestClearRewardWeightService;

    @Autowired
    private MQuestClearRewardWeightQueryService mQuestClearRewardWeightQueryService;

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

    private MockMvc restMQuestClearRewardWeightMockMvc;

    private MQuestClearRewardWeight mQuestClearRewardWeight;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestClearRewardWeightResource mQuestClearRewardWeightResource = new MQuestClearRewardWeightResource(mQuestClearRewardWeightService, mQuestClearRewardWeightQueryService);
        this.restMQuestClearRewardWeightMockMvc = MockMvcBuilders.standaloneSetup(mQuestClearRewardWeightResource)
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
    public static MQuestClearRewardWeight createEntity(EntityManager em) {
        MQuestClearRewardWeight mQuestClearRewardWeight = new MQuestClearRewardWeight()
            .reward1(DEFAULT_REWARD_1)
            .reward2(DEFAULT_REWARD_2)
            .reward3(DEFAULT_REWARD_3)
            .reward4(DEFAULT_REWARD_4)
            .reward5(DEFAULT_REWARD_5);
        return mQuestClearRewardWeight;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestClearRewardWeight createUpdatedEntity(EntityManager em) {
        MQuestClearRewardWeight mQuestClearRewardWeight = new MQuestClearRewardWeight()
            .reward1(UPDATED_REWARD_1)
            .reward2(UPDATED_REWARD_2)
            .reward3(UPDATED_REWARD_3)
            .reward4(UPDATED_REWARD_4)
            .reward5(UPDATED_REWARD_5);
        return mQuestClearRewardWeight;
    }

    @BeforeEach
    public void initTest() {
        mQuestClearRewardWeight = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestClearRewardWeight() throws Exception {
        int databaseSizeBeforeCreate = mQuestClearRewardWeightRepository.findAll().size();

        // Create the MQuestClearRewardWeight
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeight);
        restMQuestClearRewardWeightMockMvc.perform(post("/api/m-quest-clear-reward-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestClearRewardWeightDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestClearRewardWeight in the database
        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestClearRewardWeight testMQuestClearRewardWeight = mQuestClearRewardWeightList.get(mQuestClearRewardWeightList.size() - 1);
        assertThat(testMQuestClearRewardWeight.getReward1()).isEqualTo(DEFAULT_REWARD_1);
        assertThat(testMQuestClearRewardWeight.getReward2()).isEqualTo(DEFAULT_REWARD_2);
        assertThat(testMQuestClearRewardWeight.getReward3()).isEqualTo(DEFAULT_REWARD_3);
        assertThat(testMQuestClearRewardWeight.getReward4()).isEqualTo(DEFAULT_REWARD_4);
        assertThat(testMQuestClearRewardWeight.getReward5()).isEqualTo(DEFAULT_REWARD_5);
    }

    @Test
    @Transactional
    public void createMQuestClearRewardWeightWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestClearRewardWeightRepository.findAll().size();

        // Create the MQuestClearRewardWeight with an existing ID
        mQuestClearRewardWeight.setId(1L);
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeight);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestClearRewardWeightMockMvc.perform(post("/api/m-quest-clear-reward-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestClearRewardWeightDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestClearRewardWeight in the database
        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkReward1IsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestClearRewardWeightRepository.findAll().size();
        // set the field null
        mQuestClearRewardWeight.setReward1(null);

        // Create the MQuestClearRewardWeight, which fails.
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeight);

        restMQuestClearRewardWeightMockMvc.perform(post("/api/m-quest-clear-reward-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestClearRewardWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReward2IsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestClearRewardWeightRepository.findAll().size();
        // set the field null
        mQuestClearRewardWeight.setReward2(null);

        // Create the MQuestClearRewardWeight, which fails.
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeight);

        restMQuestClearRewardWeightMockMvc.perform(post("/api/m-quest-clear-reward-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestClearRewardWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReward3IsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestClearRewardWeightRepository.findAll().size();
        // set the field null
        mQuestClearRewardWeight.setReward3(null);

        // Create the MQuestClearRewardWeight, which fails.
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeight);

        restMQuestClearRewardWeightMockMvc.perform(post("/api/m-quest-clear-reward-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestClearRewardWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReward4IsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestClearRewardWeightRepository.findAll().size();
        // set the field null
        mQuestClearRewardWeight.setReward4(null);

        // Create the MQuestClearRewardWeight, which fails.
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeight);

        restMQuestClearRewardWeightMockMvc.perform(post("/api/m-quest-clear-reward-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestClearRewardWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReward5IsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestClearRewardWeightRepository.findAll().size();
        // set the field null
        mQuestClearRewardWeight.setReward5(null);

        // Create the MQuestClearRewardWeight, which fails.
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeight);

        restMQuestClearRewardWeightMockMvc.perform(post("/api/m-quest-clear-reward-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestClearRewardWeightDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeights() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList
        restMQuestClearRewardWeightMockMvc.perform(get("/api/m-quest-clear-reward-weights?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestClearRewardWeight.getId().intValue())))
            .andExpect(jsonPath("$.[*].reward1").value(hasItem(DEFAULT_REWARD_1)))
            .andExpect(jsonPath("$.[*].reward2").value(hasItem(DEFAULT_REWARD_2)))
            .andExpect(jsonPath("$.[*].reward3").value(hasItem(DEFAULT_REWARD_3)))
            .andExpect(jsonPath("$.[*].reward4").value(hasItem(DEFAULT_REWARD_4)))
            .andExpect(jsonPath("$.[*].reward5").value(hasItem(DEFAULT_REWARD_5)));
    }
    
    @Test
    @Transactional
    public void getMQuestClearRewardWeight() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get the mQuestClearRewardWeight
        restMQuestClearRewardWeightMockMvc.perform(get("/api/m-quest-clear-reward-weights/{id}", mQuestClearRewardWeight.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestClearRewardWeight.getId().intValue()))
            .andExpect(jsonPath("$.reward1").value(DEFAULT_REWARD_1))
            .andExpect(jsonPath("$.reward2").value(DEFAULT_REWARD_2))
            .andExpect(jsonPath("$.reward3").value(DEFAULT_REWARD_3))
            .andExpect(jsonPath("$.reward4").value(DEFAULT_REWARD_4))
            .andExpect(jsonPath("$.reward5").value(DEFAULT_REWARD_5));
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward1IsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward1 equals to DEFAULT_REWARD_1
        defaultMQuestClearRewardWeightShouldBeFound("reward1.equals=" + DEFAULT_REWARD_1);

        // Get all the mQuestClearRewardWeightList where reward1 equals to UPDATED_REWARD_1
        defaultMQuestClearRewardWeightShouldNotBeFound("reward1.equals=" + UPDATED_REWARD_1);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward1IsInShouldWork() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward1 in DEFAULT_REWARD_1 or UPDATED_REWARD_1
        defaultMQuestClearRewardWeightShouldBeFound("reward1.in=" + DEFAULT_REWARD_1 + "," + UPDATED_REWARD_1);

        // Get all the mQuestClearRewardWeightList where reward1 equals to UPDATED_REWARD_1
        defaultMQuestClearRewardWeightShouldNotBeFound("reward1.in=" + UPDATED_REWARD_1);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward1IsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward1 is not null
        defaultMQuestClearRewardWeightShouldBeFound("reward1.specified=true");

        // Get all the mQuestClearRewardWeightList where reward1 is null
        defaultMQuestClearRewardWeightShouldNotBeFound("reward1.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward1 greater than or equals to DEFAULT_REWARD_1
        defaultMQuestClearRewardWeightShouldBeFound("reward1.greaterOrEqualThan=" + DEFAULT_REWARD_1);

        // Get all the mQuestClearRewardWeightList where reward1 greater than or equals to UPDATED_REWARD_1
        defaultMQuestClearRewardWeightShouldNotBeFound("reward1.greaterOrEqualThan=" + UPDATED_REWARD_1);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward1IsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward1 less than or equals to DEFAULT_REWARD_1
        defaultMQuestClearRewardWeightShouldNotBeFound("reward1.lessThan=" + DEFAULT_REWARD_1);

        // Get all the mQuestClearRewardWeightList where reward1 less than or equals to UPDATED_REWARD_1
        defaultMQuestClearRewardWeightShouldBeFound("reward1.lessThan=" + UPDATED_REWARD_1);
    }


    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward2IsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward2 equals to DEFAULT_REWARD_2
        defaultMQuestClearRewardWeightShouldBeFound("reward2.equals=" + DEFAULT_REWARD_2);

        // Get all the mQuestClearRewardWeightList where reward2 equals to UPDATED_REWARD_2
        defaultMQuestClearRewardWeightShouldNotBeFound("reward2.equals=" + UPDATED_REWARD_2);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward2IsInShouldWork() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward2 in DEFAULT_REWARD_2 or UPDATED_REWARD_2
        defaultMQuestClearRewardWeightShouldBeFound("reward2.in=" + DEFAULT_REWARD_2 + "," + UPDATED_REWARD_2);

        // Get all the mQuestClearRewardWeightList where reward2 equals to UPDATED_REWARD_2
        defaultMQuestClearRewardWeightShouldNotBeFound("reward2.in=" + UPDATED_REWARD_2);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward2 is not null
        defaultMQuestClearRewardWeightShouldBeFound("reward2.specified=true");

        // Get all the mQuestClearRewardWeightList where reward2 is null
        defaultMQuestClearRewardWeightShouldNotBeFound("reward2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward2 greater than or equals to DEFAULT_REWARD_2
        defaultMQuestClearRewardWeightShouldBeFound("reward2.greaterOrEqualThan=" + DEFAULT_REWARD_2);

        // Get all the mQuestClearRewardWeightList where reward2 greater than or equals to UPDATED_REWARD_2
        defaultMQuestClearRewardWeightShouldNotBeFound("reward2.greaterOrEqualThan=" + UPDATED_REWARD_2);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward2IsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward2 less than or equals to DEFAULT_REWARD_2
        defaultMQuestClearRewardWeightShouldNotBeFound("reward2.lessThan=" + DEFAULT_REWARD_2);

        // Get all the mQuestClearRewardWeightList where reward2 less than or equals to UPDATED_REWARD_2
        defaultMQuestClearRewardWeightShouldBeFound("reward2.lessThan=" + UPDATED_REWARD_2);
    }


    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward3IsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward3 equals to DEFAULT_REWARD_3
        defaultMQuestClearRewardWeightShouldBeFound("reward3.equals=" + DEFAULT_REWARD_3);

        // Get all the mQuestClearRewardWeightList where reward3 equals to UPDATED_REWARD_3
        defaultMQuestClearRewardWeightShouldNotBeFound("reward3.equals=" + UPDATED_REWARD_3);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward3IsInShouldWork() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward3 in DEFAULT_REWARD_3 or UPDATED_REWARD_3
        defaultMQuestClearRewardWeightShouldBeFound("reward3.in=" + DEFAULT_REWARD_3 + "," + UPDATED_REWARD_3);

        // Get all the mQuestClearRewardWeightList where reward3 equals to UPDATED_REWARD_3
        defaultMQuestClearRewardWeightShouldNotBeFound("reward3.in=" + UPDATED_REWARD_3);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward3IsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward3 is not null
        defaultMQuestClearRewardWeightShouldBeFound("reward3.specified=true");

        // Get all the mQuestClearRewardWeightList where reward3 is null
        defaultMQuestClearRewardWeightShouldNotBeFound("reward3.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward3 greater than or equals to DEFAULT_REWARD_3
        defaultMQuestClearRewardWeightShouldBeFound("reward3.greaterOrEqualThan=" + DEFAULT_REWARD_3);

        // Get all the mQuestClearRewardWeightList where reward3 greater than or equals to UPDATED_REWARD_3
        defaultMQuestClearRewardWeightShouldNotBeFound("reward3.greaterOrEqualThan=" + UPDATED_REWARD_3);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward3IsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward3 less than or equals to DEFAULT_REWARD_3
        defaultMQuestClearRewardWeightShouldNotBeFound("reward3.lessThan=" + DEFAULT_REWARD_3);

        // Get all the mQuestClearRewardWeightList where reward3 less than or equals to UPDATED_REWARD_3
        defaultMQuestClearRewardWeightShouldBeFound("reward3.lessThan=" + UPDATED_REWARD_3);
    }


    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward4IsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward4 equals to DEFAULT_REWARD_4
        defaultMQuestClearRewardWeightShouldBeFound("reward4.equals=" + DEFAULT_REWARD_4);

        // Get all the mQuestClearRewardWeightList where reward4 equals to UPDATED_REWARD_4
        defaultMQuestClearRewardWeightShouldNotBeFound("reward4.equals=" + UPDATED_REWARD_4);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward4IsInShouldWork() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward4 in DEFAULT_REWARD_4 or UPDATED_REWARD_4
        defaultMQuestClearRewardWeightShouldBeFound("reward4.in=" + DEFAULT_REWARD_4 + "," + UPDATED_REWARD_4);

        // Get all the mQuestClearRewardWeightList where reward4 equals to UPDATED_REWARD_4
        defaultMQuestClearRewardWeightShouldNotBeFound("reward4.in=" + UPDATED_REWARD_4);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward4IsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward4 is not null
        defaultMQuestClearRewardWeightShouldBeFound("reward4.specified=true");

        // Get all the mQuestClearRewardWeightList where reward4 is null
        defaultMQuestClearRewardWeightShouldNotBeFound("reward4.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward4 greater than or equals to DEFAULT_REWARD_4
        defaultMQuestClearRewardWeightShouldBeFound("reward4.greaterOrEqualThan=" + DEFAULT_REWARD_4);

        // Get all the mQuestClearRewardWeightList where reward4 greater than or equals to UPDATED_REWARD_4
        defaultMQuestClearRewardWeightShouldNotBeFound("reward4.greaterOrEqualThan=" + UPDATED_REWARD_4);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward4IsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward4 less than or equals to DEFAULT_REWARD_4
        defaultMQuestClearRewardWeightShouldNotBeFound("reward4.lessThan=" + DEFAULT_REWARD_4);

        // Get all the mQuestClearRewardWeightList where reward4 less than or equals to UPDATED_REWARD_4
        defaultMQuestClearRewardWeightShouldBeFound("reward4.lessThan=" + UPDATED_REWARD_4);
    }


    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward5IsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward5 equals to DEFAULT_REWARD_5
        defaultMQuestClearRewardWeightShouldBeFound("reward5.equals=" + DEFAULT_REWARD_5);

        // Get all the mQuestClearRewardWeightList where reward5 equals to UPDATED_REWARD_5
        defaultMQuestClearRewardWeightShouldNotBeFound("reward5.equals=" + UPDATED_REWARD_5);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward5IsInShouldWork() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward5 in DEFAULT_REWARD_5 or UPDATED_REWARD_5
        defaultMQuestClearRewardWeightShouldBeFound("reward5.in=" + DEFAULT_REWARD_5 + "," + UPDATED_REWARD_5);

        // Get all the mQuestClearRewardWeightList where reward5 equals to UPDATED_REWARD_5
        defaultMQuestClearRewardWeightShouldNotBeFound("reward5.in=" + UPDATED_REWARD_5);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward5IsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward5 is not null
        defaultMQuestClearRewardWeightShouldBeFound("reward5.specified=true");

        // Get all the mQuestClearRewardWeightList where reward5 is null
        defaultMQuestClearRewardWeightShouldNotBeFound("reward5.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward5 greater than or equals to DEFAULT_REWARD_5
        defaultMQuestClearRewardWeightShouldBeFound("reward5.greaterOrEqualThan=" + DEFAULT_REWARD_5);

        // Get all the mQuestClearRewardWeightList where reward5 greater than or equals to UPDATED_REWARD_5
        defaultMQuestClearRewardWeightShouldNotBeFound("reward5.greaterOrEqualThan=" + UPDATED_REWARD_5);
    }

    @Test
    @Transactional
    public void getAllMQuestClearRewardWeightsByReward5IsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        // Get all the mQuestClearRewardWeightList where reward5 less than or equals to DEFAULT_REWARD_5
        defaultMQuestClearRewardWeightShouldNotBeFound("reward5.lessThan=" + DEFAULT_REWARD_5);

        // Get all the mQuestClearRewardWeightList where reward5 less than or equals to UPDATED_REWARD_5
        defaultMQuestClearRewardWeightShouldBeFound("reward5.lessThan=" + UPDATED_REWARD_5);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestClearRewardWeightShouldBeFound(String filter) throws Exception {
        restMQuestClearRewardWeightMockMvc.perform(get("/api/m-quest-clear-reward-weights?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestClearRewardWeight.getId().intValue())))
            .andExpect(jsonPath("$.[*].reward1").value(hasItem(DEFAULT_REWARD_1)))
            .andExpect(jsonPath("$.[*].reward2").value(hasItem(DEFAULT_REWARD_2)))
            .andExpect(jsonPath("$.[*].reward3").value(hasItem(DEFAULT_REWARD_3)))
            .andExpect(jsonPath("$.[*].reward4").value(hasItem(DEFAULT_REWARD_4)))
            .andExpect(jsonPath("$.[*].reward5").value(hasItem(DEFAULT_REWARD_5)));

        // Check, that the count call also returns 1
        restMQuestClearRewardWeightMockMvc.perform(get("/api/m-quest-clear-reward-weights/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestClearRewardWeightShouldNotBeFound(String filter) throws Exception {
        restMQuestClearRewardWeightMockMvc.perform(get("/api/m-quest-clear-reward-weights?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestClearRewardWeightMockMvc.perform(get("/api/m-quest-clear-reward-weights/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestClearRewardWeight() throws Exception {
        // Get the mQuestClearRewardWeight
        restMQuestClearRewardWeightMockMvc.perform(get("/api/m-quest-clear-reward-weights/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestClearRewardWeight() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        int databaseSizeBeforeUpdate = mQuestClearRewardWeightRepository.findAll().size();

        // Update the mQuestClearRewardWeight
        MQuestClearRewardWeight updatedMQuestClearRewardWeight = mQuestClearRewardWeightRepository.findById(mQuestClearRewardWeight.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestClearRewardWeight are not directly saved in db
        em.detach(updatedMQuestClearRewardWeight);
        updatedMQuestClearRewardWeight
            .reward1(UPDATED_REWARD_1)
            .reward2(UPDATED_REWARD_2)
            .reward3(UPDATED_REWARD_3)
            .reward4(UPDATED_REWARD_4)
            .reward5(UPDATED_REWARD_5);
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = mQuestClearRewardWeightMapper.toDto(updatedMQuestClearRewardWeight);

        restMQuestClearRewardWeightMockMvc.perform(put("/api/m-quest-clear-reward-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestClearRewardWeightDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestClearRewardWeight in the database
        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeUpdate);
        MQuestClearRewardWeight testMQuestClearRewardWeight = mQuestClearRewardWeightList.get(mQuestClearRewardWeightList.size() - 1);
        assertThat(testMQuestClearRewardWeight.getReward1()).isEqualTo(UPDATED_REWARD_1);
        assertThat(testMQuestClearRewardWeight.getReward2()).isEqualTo(UPDATED_REWARD_2);
        assertThat(testMQuestClearRewardWeight.getReward3()).isEqualTo(UPDATED_REWARD_3);
        assertThat(testMQuestClearRewardWeight.getReward4()).isEqualTo(UPDATED_REWARD_4);
        assertThat(testMQuestClearRewardWeight.getReward5()).isEqualTo(UPDATED_REWARD_5);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestClearRewardWeight() throws Exception {
        int databaseSizeBeforeUpdate = mQuestClearRewardWeightRepository.findAll().size();

        // Create the MQuestClearRewardWeight
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO = mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeight);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestClearRewardWeightMockMvc.perform(put("/api/m-quest-clear-reward-weights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestClearRewardWeightDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestClearRewardWeight in the database
        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestClearRewardWeight() throws Exception {
        // Initialize the database
        mQuestClearRewardWeightRepository.saveAndFlush(mQuestClearRewardWeight);

        int databaseSizeBeforeDelete = mQuestClearRewardWeightRepository.findAll().size();

        // Delete the mQuestClearRewardWeight
        restMQuestClearRewardWeightMockMvc.perform(delete("/api/m-quest-clear-reward-weights/{id}", mQuestClearRewardWeight.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestClearRewardWeight> mQuestClearRewardWeightList = mQuestClearRewardWeightRepository.findAll();
        assertThat(mQuestClearRewardWeightList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestClearRewardWeight.class);
        MQuestClearRewardWeight mQuestClearRewardWeight1 = new MQuestClearRewardWeight();
        mQuestClearRewardWeight1.setId(1L);
        MQuestClearRewardWeight mQuestClearRewardWeight2 = new MQuestClearRewardWeight();
        mQuestClearRewardWeight2.setId(mQuestClearRewardWeight1.getId());
        assertThat(mQuestClearRewardWeight1).isEqualTo(mQuestClearRewardWeight2);
        mQuestClearRewardWeight2.setId(2L);
        assertThat(mQuestClearRewardWeight1).isNotEqualTo(mQuestClearRewardWeight2);
        mQuestClearRewardWeight1.setId(null);
        assertThat(mQuestClearRewardWeight1).isNotEqualTo(mQuestClearRewardWeight2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestClearRewardWeightDTO.class);
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO1 = new MQuestClearRewardWeightDTO();
        mQuestClearRewardWeightDTO1.setId(1L);
        MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO2 = new MQuestClearRewardWeightDTO();
        assertThat(mQuestClearRewardWeightDTO1).isNotEqualTo(mQuestClearRewardWeightDTO2);
        mQuestClearRewardWeightDTO2.setId(mQuestClearRewardWeightDTO1.getId());
        assertThat(mQuestClearRewardWeightDTO1).isEqualTo(mQuestClearRewardWeightDTO2);
        mQuestClearRewardWeightDTO2.setId(2L);
        assertThat(mQuestClearRewardWeightDTO1).isNotEqualTo(mQuestClearRewardWeightDTO2);
        mQuestClearRewardWeightDTO1.setId(null);
        assertThat(mQuestClearRewardWeightDTO1).isNotEqualTo(mQuestClearRewardWeightDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestClearRewardWeightMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestClearRewardWeightMapper.fromId(null)).isNull();
    }
}
