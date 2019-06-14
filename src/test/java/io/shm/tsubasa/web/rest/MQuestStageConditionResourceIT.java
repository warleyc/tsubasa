package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestStageCondition;
import io.shm.tsubasa.repository.MQuestStageConditionRepository;
import io.shm.tsubasa.service.MQuestStageConditionService;
import io.shm.tsubasa.service.dto.MQuestStageConditionDTO;
import io.shm.tsubasa.service.mapper.MQuestStageConditionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestStageConditionCriteria;
import io.shm.tsubasa.service.MQuestStageConditionQueryService;

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
 * Integration tests for the {@Link MQuestStageConditionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestStageConditionResourceIT {

    private static final Integer DEFAULT_SUCCESS_CONDITION_TYPE = 1;
    private static final Integer UPDATED_SUCCESS_CONDITION_TYPE = 2;

    private static final Integer DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE = 1;
    private static final Integer UPDATED_SUCCESS_CONDITION_DETAIL_TYPE = 2;

    private static final Integer DEFAULT_SUCCESS_CONDITION_VALUE = 1;
    private static final Integer UPDATED_SUCCESS_CONDITION_VALUE = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_GROUP_ID = 2;

    private static final Integer DEFAULT_FAILURE_CONDITION_TYPE = 1;
    private static final Integer UPDATED_FAILURE_CONDITION_TYPE = 2;

    @Autowired
    private MQuestStageConditionRepository mQuestStageConditionRepository;

    @Autowired
    private MQuestStageConditionMapper mQuestStageConditionMapper;

    @Autowired
    private MQuestStageConditionService mQuestStageConditionService;

    @Autowired
    private MQuestStageConditionQueryService mQuestStageConditionQueryService;

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

    private MockMvc restMQuestStageConditionMockMvc;

    private MQuestStageCondition mQuestStageCondition;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestStageConditionResource mQuestStageConditionResource = new MQuestStageConditionResource(mQuestStageConditionService, mQuestStageConditionQueryService);
        this.restMQuestStageConditionMockMvc = MockMvcBuilders.standaloneSetup(mQuestStageConditionResource)
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
    public static MQuestStageCondition createEntity(EntityManager em) {
        MQuestStageCondition mQuestStageCondition = new MQuestStageCondition()
            .successConditionType(DEFAULT_SUCCESS_CONDITION_TYPE)
            .successConditionDetailType(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE)
            .successConditionValue(DEFAULT_SUCCESS_CONDITION_VALUE)
            .targetCharacterGroupId(DEFAULT_TARGET_CHARACTER_GROUP_ID)
            .failureConditionType(DEFAULT_FAILURE_CONDITION_TYPE);
        return mQuestStageCondition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestStageCondition createUpdatedEntity(EntityManager em) {
        MQuestStageCondition mQuestStageCondition = new MQuestStageCondition()
            .successConditionType(UPDATED_SUCCESS_CONDITION_TYPE)
            .successConditionDetailType(UPDATED_SUCCESS_CONDITION_DETAIL_TYPE)
            .successConditionValue(UPDATED_SUCCESS_CONDITION_VALUE)
            .targetCharacterGroupId(UPDATED_TARGET_CHARACTER_GROUP_ID)
            .failureConditionType(UPDATED_FAILURE_CONDITION_TYPE);
        return mQuestStageCondition;
    }

    @BeforeEach
    public void initTest() {
        mQuestStageCondition = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestStageCondition() throws Exception {
        int databaseSizeBeforeCreate = mQuestStageConditionRepository.findAll().size();

        // Create the MQuestStageCondition
        MQuestStageConditionDTO mQuestStageConditionDTO = mQuestStageConditionMapper.toDto(mQuestStageCondition);
        restMQuestStageConditionMockMvc.perform(post("/api/m-quest-stage-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestStageCondition in the database
        List<MQuestStageCondition> mQuestStageConditionList = mQuestStageConditionRepository.findAll();
        assertThat(mQuestStageConditionList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestStageCondition testMQuestStageCondition = mQuestStageConditionList.get(mQuestStageConditionList.size() - 1);
        assertThat(testMQuestStageCondition.getSuccessConditionType()).isEqualTo(DEFAULT_SUCCESS_CONDITION_TYPE);
        assertThat(testMQuestStageCondition.getSuccessConditionDetailType()).isEqualTo(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE);
        assertThat(testMQuestStageCondition.getSuccessConditionValue()).isEqualTo(DEFAULT_SUCCESS_CONDITION_VALUE);
        assertThat(testMQuestStageCondition.getTargetCharacterGroupId()).isEqualTo(DEFAULT_TARGET_CHARACTER_GROUP_ID);
        assertThat(testMQuestStageCondition.getFailureConditionType()).isEqualTo(DEFAULT_FAILURE_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void createMQuestStageConditionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestStageConditionRepository.findAll().size();

        // Create the MQuestStageCondition with an existing ID
        mQuestStageCondition.setId(1L);
        MQuestStageConditionDTO mQuestStageConditionDTO = mQuestStageConditionMapper.toDto(mQuestStageCondition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestStageConditionMockMvc.perform(post("/api/m-quest-stage-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestStageCondition in the database
        List<MQuestStageCondition> mQuestStageConditionList = mQuestStageConditionRepository.findAll();
        assertThat(mQuestStageConditionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSuccessConditionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageConditionRepository.findAll().size();
        // set the field null
        mQuestStageCondition.setSuccessConditionType(null);

        // Create the MQuestStageCondition, which fails.
        MQuestStageConditionDTO mQuestStageConditionDTO = mQuestStageConditionMapper.toDto(mQuestStageCondition);

        restMQuestStageConditionMockMvc.perform(post("/api/m-quest-stage-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageCondition> mQuestStageConditionList = mQuestStageConditionRepository.findAll();
        assertThat(mQuestStageConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSuccessConditionValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageConditionRepository.findAll().size();
        // set the field null
        mQuestStageCondition.setSuccessConditionValue(null);

        // Create the MQuestStageCondition, which fails.
        MQuestStageConditionDTO mQuestStageConditionDTO = mQuestStageConditionMapper.toDto(mQuestStageCondition);

        restMQuestStageConditionMockMvc.perform(post("/api/m-quest-stage-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageCondition> mQuestStageConditionList = mQuestStageConditionRepository.findAll();
        assertThat(mQuestStageConditionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditions() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList
        restMQuestStageConditionMockMvc.perform(get("/api/m-quest-stage-conditions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestStageCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].successConditionType").value(hasItem(DEFAULT_SUCCESS_CONDITION_TYPE)))
            .andExpect(jsonPath("$.[*].successConditionDetailType").value(hasItem(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE)))
            .andExpect(jsonPath("$.[*].successConditionValue").value(hasItem(DEFAULT_SUCCESS_CONDITION_VALUE)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_ID)))
            .andExpect(jsonPath("$.[*].failureConditionType").value(hasItem(DEFAULT_FAILURE_CONDITION_TYPE)));
    }
    
    @Test
    @Transactional
    public void getMQuestStageCondition() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get the mQuestStageCondition
        restMQuestStageConditionMockMvc.perform(get("/api/m-quest-stage-conditions/{id}", mQuestStageCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestStageCondition.getId().intValue()))
            .andExpect(jsonPath("$.successConditionType").value(DEFAULT_SUCCESS_CONDITION_TYPE))
            .andExpect(jsonPath("$.successConditionDetailType").value(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE))
            .andExpect(jsonPath("$.successConditionValue").value(DEFAULT_SUCCESS_CONDITION_VALUE))
            .andExpect(jsonPath("$.targetCharacterGroupId").value(DEFAULT_TARGET_CHARACTER_GROUP_ID))
            .andExpect(jsonPath("$.failureConditionType").value(DEFAULT_FAILURE_CONDITION_TYPE));
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionType equals to DEFAULT_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionShouldBeFound("successConditionType.equals=" + DEFAULT_SUCCESS_CONDITION_TYPE);

        // Get all the mQuestStageConditionList where successConditionType equals to UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionShouldNotBeFound("successConditionType.equals=" + UPDATED_SUCCESS_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionType in DEFAULT_SUCCESS_CONDITION_TYPE or UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionShouldBeFound("successConditionType.in=" + DEFAULT_SUCCESS_CONDITION_TYPE + "," + UPDATED_SUCCESS_CONDITION_TYPE);

        // Get all the mQuestStageConditionList where successConditionType equals to UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionShouldNotBeFound("successConditionType.in=" + UPDATED_SUCCESS_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionType is not null
        defaultMQuestStageConditionShouldBeFound("successConditionType.specified=true");

        // Get all the mQuestStageConditionList where successConditionType is null
        defaultMQuestStageConditionShouldNotBeFound("successConditionType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionType greater than or equals to DEFAULT_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionShouldBeFound("successConditionType.greaterOrEqualThan=" + DEFAULT_SUCCESS_CONDITION_TYPE);

        // Get all the mQuestStageConditionList where successConditionType greater than or equals to UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionShouldNotBeFound("successConditionType.greaterOrEqualThan=" + UPDATED_SUCCESS_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionType less than or equals to DEFAULT_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionShouldNotBeFound("successConditionType.lessThan=" + DEFAULT_SUCCESS_CONDITION_TYPE);

        // Get all the mQuestStageConditionList where successConditionType less than or equals to UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionShouldBeFound("successConditionType.lessThan=" + UPDATED_SUCCESS_CONDITION_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionDetailTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionDetailType equals to DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE
        defaultMQuestStageConditionShouldBeFound("successConditionDetailType.equals=" + DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE);

        // Get all the mQuestStageConditionList where successConditionDetailType equals to UPDATED_SUCCESS_CONDITION_DETAIL_TYPE
        defaultMQuestStageConditionShouldNotBeFound("successConditionDetailType.equals=" + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionDetailTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionDetailType in DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE or UPDATED_SUCCESS_CONDITION_DETAIL_TYPE
        defaultMQuestStageConditionShouldBeFound("successConditionDetailType.in=" + DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE + "," + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE);

        // Get all the mQuestStageConditionList where successConditionDetailType equals to UPDATED_SUCCESS_CONDITION_DETAIL_TYPE
        defaultMQuestStageConditionShouldNotBeFound("successConditionDetailType.in=" + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionDetailTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionDetailType is not null
        defaultMQuestStageConditionShouldBeFound("successConditionDetailType.specified=true");

        // Get all the mQuestStageConditionList where successConditionDetailType is null
        defaultMQuestStageConditionShouldNotBeFound("successConditionDetailType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionDetailTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionDetailType greater than or equals to DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE
        defaultMQuestStageConditionShouldBeFound("successConditionDetailType.greaterOrEqualThan=" + DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE);

        // Get all the mQuestStageConditionList where successConditionDetailType greater than or equals to UPDATED_SUCCESS_CONDITION_DETAIL_TYPE
        defaultMQuestStageConditionShouldNotBeFound("successConditionDetailType.greaterOrEqualThan=" + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionDetailTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionDetailType less than or equals to DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE
        defaultMQuestStageConditionShouldNotBeFound("successConditionDetailType.lessThan=" + DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE);

        // Get all the mQuestStageConditionList where successConditionDetailType less than or equals to UPDATED_SUCCESS_CONDITION_DETAIL_TYPE
        defaultMQuestStageConditionShouldBeFound("successConditionDetailType.lessThan=" + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionValue equals to DEFAULT_SUCCESS_CONDITION_VALUE
        defaultMQuestStageConditionShouldBeFound("successConditionValue.equals=" + DEFAULT_SUCCESS_CONDITION_VALUE);

        // Get all the mQuestStageConditionList where successConditionValue equals to UPDATED_SUCCESS_CONDITION_VALUE
        defaultMQuestStageConditionShouldNotBeFound("successConditionValue.equals=" + UPDATED_SUCCESS_CONDITION_VALUE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionValueIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionValue in DEFAULT_SUCCESS_CONDITION_VALUE or UPDATED_SUCCESS_CONDITION_VALUE
        defaultMQuestStageConditionShouldBeFound("successConditionValue.in=" + DEFAULT_SUCCESS_CONDITION_VALUE + "," + UPDATED_SUCCESS_CONDITION_VALUE);

        // Get all the mQuestStageConditionList where successConditionValue equals to UPDATED_SUCCESS_CONDITION_VALUE
        defaultMQuestStageConditionShouldNotBeFound("successConditionValue.in=" + UPDATED_SUCCESS_CONDITION_VALUE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionValue is not null
        defaultMQuestStageConditionShouldBeFound("successConditionValue.specified=true");

        // Get all the mQuestStageConditionList where successConditionValue is null
        defaultMQuestStageConditionShouldNotBeFound("successConditionValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionValue greater than or equals to DEFAULT_SUCCESS_CONDITION_VALUE
        defaultMQuestStageConditionShouldBeFound("successConditionValue.greaterOrEqualThan=" + DEFAULT_SUCCESS_CONDITION_VALUE);

        // Get all the mQuestStageConditionList where successConditionValue greater than or equals to UPDATED_SUCCESS_CONDITION_VALUE
        defaultMQuestStageConditionShouldNotBeFound("successConditionValue.greaterOrEqualThan=" + UPDATED_SUCCESS_CONDITION_VALUE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsBySuccessConditionValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where successConditionValue less than or equals to DEFAULT_SUCCESS_CONDITION_VALUE
        defaultMQuestStageConditionShouldNotBeFound("successConditionValue.lessThan=" + DEFAULT_SUCCESS_CONDITION_VALUE);

        // Get all the mQuestStageConditionList where successConditionValue less than or equals to UPDATED_SUCCESS_CONDITION_VALUE
        defaultMQuestStageConditionShouldBeFound("successConditionValue.lessThan=" + UPDATED_SUCCESS_CONDITION_VALUE);
    }


    @Test
    @Transactional
    public void getAllMQuestStageConditionsByTargetCharacterGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where targetCharacterGroupId equals to DEFAULT_TARGET_CHARACTER_GROUP_ID
        defaultMQuestStageConditionShouldBeFound("targetCharacterGroupId.equals=" + DEFAULT_TARGET_CHARACTER_GROUP_ID);

        // Get all the mQuestStageConditionList where targetCharacterGroupId equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMQuestStageConditionShouldNotBeFound("targetCharacterGroupId.equals=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsByTargetCharacterGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where targetCharacterGroupId in DEFAULT_TARGET_CHARACTER_GROUP_ID or UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMQuestStageConditionShouldBeFound("targetCharacterGroupId.in=" + DEFAULT_TARGET_CHARACTER_GROUP_ID + "," + UPDATED_TARGET_CHARACTER_GROUP_ID);

        // Get all the mQuestStageConditionList where targetCharacterGroupId equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMQuestStageConditionShouldNotBeFound("targetCharacterGroupId.in=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsByTargetCharacterGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where targetCharacterGroupId is not null
        defaultMQuestStageConditionShouldBeFound("targetCharacterGroupId.specified=true");

        // Get all the mQuestStageConditionList where targetCharacterGroupId is null
        defaultMQuestStageConditionShouldNotBeFound("targetCharacterGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsByTargetCharacterGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where targetCharacterGroupId greater than or equals to DEFAULT_TARGET_CHARACTER_GROUP_ID
        defaultMQuestStageConditionShouldBeFound("targetCharacterGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_GROUP_ID);

        // Get all the mQuestStageConditionList where targetCharacterGroupId greater than or equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMQuestStageConditionShouldNotBeFound("targetCharacterGroupId.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsByTargetCharacterGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where targetCharacterGroupId less than or equals to DEFAULT_TARGET_CHARACTER_GROUP_ID
        defaultMQuestStageConditionShouldNotBeFound("targetCharacterGroupId.lessThan=" + DEFAULT_TARGET_CHARACTER_GROUP_ID);

        // Get all the mQuestStageConditionList where targetCharacterGroupId less than or equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMQuestStageConditionShouldBeFound("targetCharacterGroupId.lessThan=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestStageConditionsByFailureConditionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where failureConditionType equals to DEFAULT_FAILURE_CONDITION_TYPE
        defaultMQuestStageConditionShouldBeFound("failureConditionType.equals=" + DEFAULT_FAILURE_CONDITION_TYPE);

        // Get all the mQuestStageConditionList where failureConditionType equals to UPDATED_FAILURE_CONDITION_TYPE
        defaultMQuestStageConditionShouldNotBeFound("failureConditionType.equals=" + UPDATED_FAILURE_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsByFailureConditionTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where failureConditionType in DEFAULT_FAILURE_CONDITION_TYPE or UPDATED_FAILURE_CONDITION_TYPE
        defaultMQuestStageConditionShouldBeFound("failureConditionType.in=" + DEFAULT_FAILURE_CONDITION_TYPE + "," + UPDATED_FAILURE_CONDITION_TYPE);

        // Get all the mQuestStageConditionList where failureConditionType equals to UPDATED_FAILURE_CONDITION_TYPE
        defaultMQuestStageConditionShouldNotBeFound("failureConditionType.in=" + UPDATED_FAILURE_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsByFailureConditionTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where failureConditionType is not null
        defaultMQuestStageConditionShouldBeFound("failureConditionType.specified=true");

        // Get all the mQuestStageConditionList where failureConditionType is null
        defaultMQuestStageConditionShouldNotBeFound("failureConditionType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsByFailureConditionTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where failureConditionType greater than or equals to DEFAULT_FAILURE_CONDITION_TYPE
        defaultMQuestStageConditionShouldBeFound("failureConditionType.greaterOrEqualThan=" + DEFAULT_FAILURE_CONDITION_TYPE);

        // Get all the mQuestStageConditionList where failureConditionType greater than or equals to UPDATED_FAILURE_CONDITION_TYPE
        defaultMQuestStageConditionShouldNotBeFound("failureConditionType.greaterOrEqualThan=" + UPDATED_FAILURE_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionsByFailureConditionTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        // Get all the mQuestStageConditionList where failureConditionType less than or equals to DEFAULT_FAILURE_CONDITION_TYPE
        defaultMQuestStageConditionShouldNotBeFound("failureConditionType.lessThan=" + DEFAULT_FAILURE_CONDITION_TYPE);

        // Get all the mQuestStageConditionList where failureConditionType less than or equals to UPDATED_FAILURE_CONDITION_TYPE
        defaultMQuestStageConditionShouldBeFound("failureConditionType.lessThan=" + UPDATED_FAILURE_CONDITION_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestStageConditionShouldBeFound(String filter) throws Exception {
        restMQuestStageConditionMockMvc.perform(get("/api/m-quest-stage-conditions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestStageCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].successConditionType").value(hasItem(DEFAULT_SUCCESS_CONDITION_TYPE)))
            .andExpect(jsonPath("$.[*].successConditionDetailType").value(hasItem(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE)))
            .andExpect(jsonPath("$.[*].successConditionValue").value(hasItem(DEFAULT_SUCCESS_CONDITION_VALUE)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_ID)))
            .andExpect(jsonPath("$.[*].failureConditionType").value(hasItem(DEFAULT_FAILURE_CONDITION_TYPE)));

        // Check, that the count call also returns 1
        restMQuestStageConditionMockMvc.perform(get("/api/m-quest-stage-conditions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestStageConditionShouldNotBeFound(String filter) throws Exception {
        restMQuestStageConditionMockMvc.perform(get("/api/m-quest-stage-conditions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestStageConditionMockMvc.perform(get("/api/m-quest-stage-conditions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestStageCondition() throws Exception {
        // Get the mQuestStageCondition
        restMQuestStageConditionMockMvc.perform(get("/api/m-quest-stage-conditions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestStageCondition() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        int databaseSizeBeforeUpdate = mQuestStageConditionRepository.findAll().size();

        // Update the mQuestStageCondition
        MQuestStageCondition updatedMQuestStageCondition = mQuestStageConditionRepository.findById(mQuestStageCondition.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestStageCondition are not directly saved in db
        em.detach(updatedMQuestStageCondition);
        updatedMQuestStageCondition
            .successConditionType(UPDATED_SUCCESS_CONDITION_TYPE)
            .successConditionDetailType(UPDATED_SUCCESS_CONDITION_DETAIL_TYPE)
            .successConditionValue(UPDATED_SUCCESS_CONDITION_VALUE)
            .targetCharacterGroupId(UPDATED_TARGET_CHARACTER_GROUP_ID)
            .failureConditionType(UPDATED_FAILURE_CONDITION_TYPE);
        MQuestStageConditionDTO mQuestStageConditionDTO = mQuestStageConditionMapper.toDto(updatedMQuestStageCondition);

        restMQuestStageConditionMockMvc.perform(put("/api/m-quest-stage-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestStageCondition in the database
        List<MQuestStageCondition> mQuestStageConditionList = mQuestStageConditionRepository.findAll();
        assertThat(mQuestStageConditionList).hasSize(databaseSizeBeforeUpdate);
        MQuestStageCondition testMQuestStageCondition = mQuestStageConditionList.get(mQuestStageConditionList.size() - 1);
        assertThat(testMQuestStageCondition.getSuccessConditionType()).isEqualTo(UPDATED_SUCCESS_CONDITION_TYPE);
        assertThat(testMQuestStageCondition.getSuccessConditionDetailType()).isEqualTo(UPDATED_SUCCESS_CONDITION_DETAIL_TYPE);
        assertThat(testMQuestStageCondition.getSuccessConditionValue()).isEqualTo(UPDATED_SUCCESS_CONDITION_VALUE);
        assertThat(testMQuestStageCondition.getTargetCharacterGroupId()).isEqualTo(UPDATED_TARGET_CHARACTER_GROUP_ID);
        assertThat(testMQuestStageCondition.getFailureConditionType()).isEqualTo(UPDATED_FAILURE_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestStageCondition() throws Exception {
        int databaseSizeBeforeUpdate = mQuestStageConditionRepository.findAll().size();

        // Create the MQuestStageCondition
        MQuestStageConditionDTO mQuestStageConditionDTO = mQuestStageConditionMapper.toDto(mQuestStageCondition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestStageConditionMockMvc.perform(put("/api/m-quest-stage-conditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestStageCondition in the database
        List<MQuestStageCondition> mQuestStageConditionList = mQuestStageConditionRepository.findAll();
        assertThat(mQuestStageConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestStageCondition() throws Exception {
        // Initialize the database
        mQuestStageConditionRepository.saveAndFlush(mQuestStageCondition);

        int databaseSizeBeforeDelete = mQuestStageConditionRepository.findAll().size();

        // Delete the mQuestStageCondition
        restMQuestStageConditionMockMvc.perform(delete("/api/m-quest-stage-conditions/{id}", mQuestStageCondition.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestStageCondition> mQuestStageConditionList = mQuestStageConditionRepository.findAll();
        assertThat(mQuestStageConditionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestStageCondition.class);
        MQuestStageCondition mQuestStageCondition1 = new MQuestStageCondition();
        mQuestStageCondition1.setId(1L);
        MQuestStageCondition mQuestStageCondition2 = new MQuestStageCondition();
        mQuestStageCondition2.setId(mQuestStageCondition1.getId());
        assertThat(mQuestStageCondition1).isEqualTo(mQuestStageCondition2);
        mQuestStageCondition2.setId(2L);
        assertThat(mQuestStageCondition1).isNotEqualTo(mQuestStageCondition2);
        mQuestStageCondition1.setId(null);
        assertThat(mQuestStageCondition1).isNotEqualTo(mQuestStageCondition2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestStageConditionDTO.class);
        MQuestStageConditionDTO mQuestStageConditionDTO1 = new MQuestStageConditionDTO();
        mQuestStageConditionDTO1.setId(1L);
        MQuestStageConditionDTO mQuestStageConditionDTO2 = new MQuestStageConditionDTO();
        assertThat(mQuestStageConditionDTO1).isNotEqualTo(mQuestStageConditionDTO2);
        mQuestStageConditionDTO2.setId(mQuestStageConditionDTO1.getId());
        assertThat(mQuestStageConditionDTO1).isEqualTo(mQuestStageConditionDTO2);
        mQuestStageConditionDTO2.setId(2L);
        assertThat(mQuestStageConditionDTO1).isNotEqualTo(mQuestStageConditionDTO2);
        mQuestStageConditionDTO1.setId(null);
        assertThat(mQuestStageConditionDTO1).isNotEqualTo(mQuestStageConditionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestStageConditionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestStageConditionMapper.fromId(null)).isNull();
    }
}
