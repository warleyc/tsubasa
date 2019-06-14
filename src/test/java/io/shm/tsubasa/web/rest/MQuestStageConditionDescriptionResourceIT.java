package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestStageConditionDescription;
import io.shm.tsubasa.repository.MQuestStageConditionDescriptionRepository;
import io.shm.tsubasa.service.MQuestStageConditionDescriptionService;
import io.shm.tsubasa.service.dto.MQuestStageConditionDescriptionDTO;
import io.shm.tsubasa.service.mapper.MQuestStageConditionDescriptionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestStageConditionDescriptionCriteria;
import io.shm.tsubasa.service.MQuestStageConditionDescriptionQueryService;

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
 * Integration tests for the {@Link MQuestStageConditionDescriptionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestStageConditionDescriptionResourceIT {

    private static final Integer DEFAULT_SUCCESS_CONDITION_TYPE = 1;
    private static final Integer UPDATED_SUCCESS_CONDITION_TYPE = 2;

    private static final Integer DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE = 1;
    private static final Integer UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE = 2;

    private static final Integer DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP = 1;
    private static final Integer UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP = 2;

    private static final Integer DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY = 1;
    private static final Integer UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY = 2;

    private static final Integer DEFAULT_FAILURE_CONDITION_TYPE_VALUE = 1;
    private static final Integer UPDATED_FAILURE_CONDITION_TYPE_VALUE = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MQuestStageConditionDescriptionRepository mQuestStageConditionDescriptionRepository;

    @Autowired
    private MQuestStageConditionDescriptionMapper mQuestStageConditionDescriptionMapper;

    @Autowired
    private MQuestStageConditionDescriptionService mQuestStageConditionDescriptionService;

    @Autowired
    private MQuestStageConditionDescriptionQueryService mQuestStageConditionDescriptionQueryService;

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

    private MockMvc restMQuestStageConditionDescriptionMockMvc;

    private MQuestStageConditionDescription mQuestStageConditionDescription;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestStageConditionDescriptionResource mQuestStageConditionDescriptionResource = new MQuestStageConditionDescriptionResource(mQuestStageConditionDescriptionService, mQuestStageConditionDescriptionQueryService);
        this.restMQuestStageConditionDescriptionMockMvc = MockMvcBuilders.standaloneSetup(mQuestStageConditionDescriptionResource)
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
    public static MQuestStageConditionDescription createEntity(EntityManager em) {
        MQuestStageConditionDescription mQuestStageConditionDescription = new MQuestStageConditionDescription()
            .successConditionType(DEFAULT_SUCCESS_CONDITION_TYPE)
            .successConditionDetailTypeValue(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE)
            .hasExistTargetCharacterGroup(DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP)
            .hasSuccessConditionValueOneOnly(DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY)
            .failureConditionTypeValue(DEFAULT_FAILURE_CONDITION_TYPE_VALUE)
            .description(DEFAULT_DESCRIPTION);
        return mQuestStageConditionDescription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestStageConditionDescription createUpdatedEntity(EntityManager em) {
        MQuestStageConditionDescription mQuestStageConditionDescription = new MQuestStageConditionDescription()
            .successConditionType(UPDATED_SUCCESS_CONDITION_TYPE)
            .successConditionDetailTypeValue(UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE)
            .hasExistTargetCharacterGroup(UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP)
            .hasSuccessConditionValueOneOnly(UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY)
            .failureConditionTypeValue(UPDATED_FAILURE_CONDITION_TYPE_VALUE)
            .description(UPDATED_DESCRIPTION);
        return mQuestStageConditionDescription;
    }

    @BeforeEach
    public void initTest() {
        mQuestStageConditionDescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestStageConditionDescription() throws Exception {
        int databaseSizeBeforeCreate = mQuestStageConditionDescriptionRepository.findAll().size();

        // Create the MQuestStageConditionDescription
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescription);
        restMQuestStageConditionDescriptionMockMvc.perform(post("/api/m-quest-stage-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDescriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestStageConditionDescription in the database
        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestStageConditionDescription testMQuestStageConditionDescription = mQuestStageConditionDescriptionList.get(mQuestStageConditionDescriptionList.size() - 1);
        assertThat(testMQuestStageConditionDescription.getSuccessConditionType()).isEqualTo(DEFAULT_SUCCESS_CONDITION_TYPE);
        assertThat(testMQuestStageConditionDescription.getSuccessConditionDetailTypeValue()).isEqualTo(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);
        assertThat(testMQuestStageConditionDescription.getHasExistTargetCharacterGroup()).isEqualTo(DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP);
        assertThat(testMQuestStageConditionDescription.getHasSuccessConditionValueOneOnly()).isEqualTo(DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);
        assertThat(testMQuestStageConditionDescription.getFailureConditionTypeValue()).isEqualTo(DEFAULT_FAILURE_CONDITION_TYPE_VALUE);
        assertThat(testMQuestStageConditionDescription.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMQuestStageConditionDescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestStageConditionDescriptionRepository.findAll().size();

        // Create the MQuestStageConditionDescription with an existing ID
        mQuestStageConditionDescription.setId(1L);
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestStageConditionDescriptionMockMvc.perform(post("/api/m-quest-stage-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestStageConditionDescription in the database
        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSuccessConditionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageConditionDescriptionRepository.findAll().size();
        // set the field null
        mQuestStageConditionDescription.setSuccessConditionType(null);

        // Create the MQuestStageConditionDescription, which fails.
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescription);

        restMQuestStageConditionDescriptionMockMvc.perform(post("/api/m-quest-stage-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSuccessConditionDetailTypeValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageConditionDescriptionRepository.findAll().size();
        // set the field null
        mQuestStageConditionDescription.setSuccessConditionDetailTypeValue(null);

        // Create the MQuestStageConditionDescription, which fails.
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescription);

        restMQuestStageConditionDescriptionMockMvc.perform(post("/api/m-quest-stage-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHasExistTargetCharacterGroupIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageConditionDescriptionRepository.findAll().size();
        // set the field null
        mQuestStageConditionDescription.setHasExistTargetCharacterGroup(null);

        // Create the MQuestStageConditionDescription, which fails.
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescription);

        restMQuestStageConditionDescriptionMockMvc.perform(post("/api/m-quest-stage-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHasSuccessConditionValueOneOnlyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageConditionDescriptionRepository.findAll().size();
        // set the field null
        mQuestStageConditionDescription.setHasSuccessConditionValueOneOnly(null);

        // Create the MQuestStageConditionDescription, which fails.
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescription);

        restMQuestStageConditionDescriptionMockMvc.perform(post("/api/m-quest-stage-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFailureConditionTypeValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestStageConditionDescriptionRepository.findAll().size();
        // set the field null
        mQuestStageConditionDescription.setFailureConditionTypeValue(null);

        // Create the MQuestStageConditionDescription, which fails.
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescription);

        restMQuestStageConditionDescriptionMockMvc.perform(post("/api/m-quest-stage-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptions() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList
        restMQuestStageConditionDescriptionMockMvc.perform(get("/api/m-quest-stage-condition-descriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestStageConditionDescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].successConditionType").value(hasItem(DEFAULT_SUCCESS_CONDITION_TYPE)))
            .andExpect(jsonPath("$.[*].successConditionDetailTypeValue").value(hasItem(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE)))
            .andExpect(jsonPath("$.[*].hasExistTargetCharacterGroup").value(hasItem(DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP)))
            .andExpect(jsonPath("$.[*].hasSuccessConditionValueOneOnly").value(hasItem(DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY)))
            .andExpect(jsonPath("$.[*].failureConditionTypeValue").value(hasItem(DEFAULT_FAILURE_CONDITION_TYPE_VALUE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMQuestStageConditionDescription() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get the mQuestStageConditionDescription
        restMQuestStageConditionDescriptionMockMvc.perform(get("/api/m-quest-stage-condition-descriptions/{id}", mQuestStageConditionDescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestStageConditionDescription.getId().intValue()))
            .andExpect(jsonPath("$.successConditionType").value(DEFAULT_SUCCESS_CONDITION_TYPE))
            .andExpect(jsonPath("$.successConditionDetailTypeValue").value(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE))
            .andExpect(jsonPath("$.hasExistTargetCharacterGroup").value(DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP))
            .andExpect(jsonPath("$.hasSuccessConditionValueOneOnly").value(DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY))
            .andExpect(jsonPath("$.failureConditionTypeValue").value(DEFAULT_FAILURE_CONDITION_TYPE_VALUE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionType equals to DEFAULT_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionType.equals=" + DEFAULT_SUCCESS_CONDITION_TYPE);

        // Get all the mQuestStageConditionDescriptionList where successConditionType equals to UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionType.equals=" + UPDATED_SUCCESS_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionType in DEFAULT_SUCCESS_CONDITION_TYPE or UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionType.in=" + DEFAULT_SUCCESS_CONDITION_TYPE + "," + UPDATED_SUCCESS_CONDITION_TYPE);

        // Get all the mQuestStageConditionDescriptionList where successConditionType equals to UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionType.in=" + UPDATED_SUCCESS_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionType is not null
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionType.specified=true");

        // Get all the mQuestStageConditionDescriptionList where successConditionType is null
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionType greater than or equals to DEFAULT_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionType.greaterOrEqualThan=" + DEFAULT_SUCCESS_CONDITION_TYPE);

        // Get all the mQuestStageConditionDescriptionList where successConditionType greater than or equals to UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionType.greaterOrEqualThan=" + UPDATED_SUCCESS_CONDITION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionType less than or equals to DEFAULT_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionType.lessThan=" + DEFAULT_SUCCESS_CONDITION_TYPE);

        // Get all the mQuestStageConditionDescriptionList where successConditionType less than or equals to UPDATED_SUCCESS_CONDITION_TYPE
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionType.lessThan=" + UPDATED_SUCCESS_CONDITION_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionDetailTypeValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue equals to DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionDetailTypeValue.equals=" + DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue equals to UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionDetailTypeValue.equals=" + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionDetailTypeValueIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue in DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE or UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionDetailTypeValue.in=" + DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE + "," + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue equals to UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionDetailTypeValue.in=" + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionDetailTypeValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue is not null
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionDetailTypeValue.specified=true");

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue is null
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionDetailTypeValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionDetailTypeValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue greater than or equals to DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionDetailTypeValue.greaterOrEqualThan=" + DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue greater than or equals to UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionDetailTypeValue.greaterOrEqualThan=" + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsBySuccessConditionDetailTypeValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue less than or equals to DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("successConditionDetailTypeValue.lessThan=" + DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);

        // Get all the mQuestStageConditionDescriptionList where successConditionDetailTypeValue less than or equals to UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldBeFound("successConditionDetailTypeValue.lessThan=" + UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);
    }


    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasExistTargetCharacterGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup equals to DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP
        defaultMQuestStageConditionDescriptionShouldBeFound("hasExistTargetCharacterGroup.equals=" + DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP);

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup equals to UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasExistTargetCharacterGroup.equals=" + UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasExistTargetCharacterGroupIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup in DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP or UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP
        defaultMQuestStageConditionDescriptionShouldBeFound("hasExistTargetCharacterGroup.in=" + DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP + "," + UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP);

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup equals to UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasExistTargetCharacterGroup.in=" + UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasExistTargetCharacterGroupIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup is not null
        defaultMQuestStageConditionDescriptionShouldBeFound("hasExistTargetCharacterGroup.specified=true");

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup is null
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasExistTargetCharacterGroup.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasExistTargetCharacterGroupIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup greater than or equals to DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP
        defaultMQuestStageConditionDescriptionShouldBeFound("hasExistTargetCharacterGroup.greaterOrEqualThan=" + DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP);

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup greater than or equals to UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasExistTargetCharacterGroup.greaterOrEqualThan=" + UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasExistTargetCharacterGroupIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup less than or equals to DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasExistTargetCharacterGroup.lessThan=" + DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP);

        // Get all the mQuestStageConditionDescriptionList where hasExistTargetCharacterGroup less than or equals to UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP
        defaultMQuestStageConditionDescriptionShouldBeFound("hasExistTargetCharacterGroup.lessThan=" + UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP);
    }


    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasSuccessConditionValueOneOnlyIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly equals to DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY
        defaultMQuestStageConditionDescriptionShouldBeFound("hasSuccessConditionValueOneOnly.equals=" + DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly equals to UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasSuccessConditionValueOneOnly.equals=" + UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasSuccessConditionValueOneOnlyIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly in DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY or UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY
        defaultMQuestStageConditionDescriptionShouldBeFound("hasSuccessConditionValueOneOnly.in=" + DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY + "," + UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly equals to UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasSuccessConditionValueOneOnly.in=" + UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasSuccessConditionValueOneOnlyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly is not null
        defaultMQuestStageConditionDescriptionShouldBeFound("hasSuccessConditionValueOneOnly.specified=true");

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly is null
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasSuccessConditionValueOneOnly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasSuccessConditionValueOneOnlyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly greater than or equals to DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY
        defaultMQuestStageConditionDescriptionShouldBeFound("hasSuccessConditionValueOneOnly.greaterOrEqualThan=" + DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly greater than or equals to UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasSuccessConditionValueOneOnly.greaterOrEqualThan=" + UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByHasSuccessConditionValueOneOnlyIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly less than or equals to DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY
        defaultMQuestStageConditionDescriptionShouldNotBeFound("hasSuccessConditionValueOneOnly.lessThan=" + DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);

        // Get all the mQuestStageConditionDescriptionList where hasSuccessConditionValueOneOnly less than or equals to UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY
        defaultMQuestStageConditionDescriptionShouldBeFound("hasSuccessConditionValueOneOnly.lessThan=" + UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);
    }


    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByFailureConditionTypeValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue equals to DEFAULT_FAILURE_CONDITION_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldBeFound("failureConditionTypeValue.equals=" + DEFAULT_FAILURE_CONDITION_TYPE_VALUE);

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue equals to UPDATED_FAILURE_CONDITION_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("failureConditionTypeValue.equals=" + UPDATED_FAILURE_CONDITION_TYPE_VALUE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByFailureConditionTypeValueIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue in DEFAULT_FAILURE_CONDITION_TYPE_VALUE or UPDATED_FAILURE_CONDITION_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldBeFound("failureConditionTypeValue.in=" + DEFAULT_FAILURE_CONDITION_TYPE_VALUE + "," + UPDATED_FAILURE_CONDITION_TYPE_VALUE);

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue equals to UPDATED_FAILURE_CONDITION_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("failureConditionTypeValue.in=" + UPDATED_FAILURE_CONDITION_TYPE_VALUE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByFailureConditionTypeValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue is not null
        defaultMQuestStageConditionDescriptionShouldBeFound("failureConditionTypeValue.specified=true");

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue is null
        defaultMQuestStageConditionDescriptionShouldNotBeFound("failureConditionTypeValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByFailureConditionTypeValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue greater than or equals to DEFAULT_FAILURE_CONDITION_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldBeFound("failureConditionTypeValue.greaterOrEqualThan=" + DEFAULT_FAILURE_CONDITION_TYPE_VALUE);

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue greater than or equals to UPDATED_FAILURE_CONDITION_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("failureConditionTypeValue.greaterOrEqualThan=" + UPDATED_FAILURE_CONDITION_TYPE_VALUE);
    }

    @Test
    @Transactional
    public void getAllMQuestStageConditionDescriptionsByFailureConditionTypeValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue less than or equals to DEFAULT_FAILURE_CONDITION_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldNotBeFound("failureConditionTypeValue.lessThan=" + DEFAULT_FAILURE_CONDITION_TYPE_VALUE);

        // Get all the mQuestStageConditionDescriptionList where failureConditionTypeValue less than or equals to UPDATED_FAILURE_CONDITION_TYPE_VALUE
        defaultMQuestStageConditionDescriptionShouldBeFound("failureConditionTypeValue.lessThan=" + UPDATED_FAILURE_CONDITION_TYPE_VALUE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestStageConditionDescriptionShouldBeFound(String filter) throws Exception {
        restMQuestStageConditionDescriptionMockMvc.perform(get("/api/m-quest-stage-condition-descriptions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestStageConditionDescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].successConditionType").value(hasItem(DEFAULT_SUCCESS_CONDITION_TYPE)))
            .andExpect(jsonPath("$.[*].successConditionDetailTypeValue").value(hasItem(DEFAULT_SUCCESS_CONDITION_DETAIL_TYPE_VALUE)))
            .andExpect(jsonPath("$.[*].hasExistTargetCharacterGroup").value(hasItem(DEFAULT_HAS_EXIST_TARGET_CHARACTER_GROUP)))
            .andExpect(jsonPath("$.[*].hasSuccessConditionValueOneOnly").value(hasItem(DEFAULT_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY)))
            .andExpect(jsonPath("$.[*].failureConditionTypeValue").value(hasItem(DEFAULT_FAILURE_CONDITION_TYPE_VALUE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMQuestStageConditionDescriptionMockMvc.perform(get("/api/m-quest-stage-condition-descriptions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestStageConditionDescriptionShouldNotBeFound(String filter) throws Exception {
        restMQuestStageConditionDescriptionMockMvc.perform(get("/api/m-quest-stage-condition-descriptions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestStageConditionDescriptionMockMvc.perform(get("/api/m-quest-stage-condition-descriptions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestStageConditionDescription() throws Exception {
        // Get the mQuestStageConditionDescription
        restMQuestStageConditionDescriptionMockMvc.perform(get("/api/m-quest-stage-condition-descriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestStageConditionDescription() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        int databaseSizeBeforeUpdate = mQuestStageConditionDescriptionRepository.findAll().size();

        // Update the mQuestStageConditionDescription
        MQuestStageConditionDescription updatedMQuestStageConditionDescription = mQuestStageConditionDescriptionRepository.findById(mQuestStageConditionDescription.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestStageConditionDescription are not directly saved in db
        em.detach(updatedMQuestStageConditionDescription);
        updatedMQuestStageConditionDescription
            .successConditionType(UPDATED_SUCCESS_CONDITION_TYPE)
            .successConditionDetailTypeValue(UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE)
            .hasExistTargetCharacterGroup(UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP)
            .hasSuccessConditionValueOneOnly(UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY)
            .failureConditionTypeValue(UPDATED_FAILURE_CONDITION_TYPE_VALUE)
            .description(UPDATED_DESCRIPTION);
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionMapper.toDto(updatedMQuestStageConditionDescription);

        restMQuestStageConditionDescriptionMockMvc.perform(put("/api/m-quest-stage-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDescriptionDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestStageConditionDescription in the database
        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeUpdate);
        MQuestStageConditionDescription testMQuestStageConditionDescription = mQuestStageConditionDescriptionList.get(mQuestStageConditionDescriptionList.size() - 1);
        assertThat(testMQuestStageConditionDescription.getSuccessConditionType()).isEqualTo(UPDATED_SUCCESS_CONDITION_TYPE);
        assertThat(testMQuestStageConditionDescription.getSuccessConditionDetailTypeValue()).isEqualTo(UPDATED_SUCCESS_CONDITION_DETAIL_TYPE_VALUE);
        assertThat(testMQuestStageConditionDescription.getHasExistTargetCharacterGroup()).isEqualTo(UPDATED_HAS_EXIST_TARGET_CHARACTER_GROUP);
        assertThat(testMQuestStageConditionDescription.getHasSuccessConditionValueOneOnly()).isEqualTo(UPDATED_HAS_SUCCESS_CONDITION_VALUE_ONE_ONLY);
        assertThat(testMQuestStageConditionDescription.getFailureConditionTypeValue()).isEqualTo(UPDATED_FAILURE_CONDITION_TYPE_VALUE);
        assertThat(testMQuestStageConditionDescription.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestStageConditionDescription() throws Exception {
        int databaseSizeBeforeUpdate = mQuestStageConditionDescriptionRepository.findAll().size();

        // Create the MQuestStageConditionDescription
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO = mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestStageConditionDescriptionMockMvc.perform(put("/api/m-quest-stage-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestStageConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestStageConditionDescription in the database
        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestStageConditionDescription() throws Exception {
        // Initialize the database
        mQuestStageConditionDescriptionRepository.saveAndFlush(mQuestStageConditionDescription);

        int databaseSizeBeforeDelete = mQuestStageConditionDescriptionRepository.findAll().size();

        // Delete the mQuestStageConditionDescription
        restMQuestStageConditionDescriptionMockMvc.perform(delete("/api/m-quest-stage-condition-descriptions/{id}", mQuestStageConditionDescription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestStageConditionDescription> mQuestStageConditionDescriptionList = mQuestStageConditionDescriptionRepository.findAll();
        assertThat(mQuestStageConditionDescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestStageConditionDescription.class);
        MQuestStageConditionDescription mQuestStageConditionDescription1 = new MQuestStageConditionDescription();
        mQuestStageConditionDescription1.setId(1L);
        MQuestStageConditionDescription mQuestStageConditionDescription2 = new MQuestStageConditionDescription();
        mQuestStageConditionDescription2.setId(mQuestStageConditionDescription1.getId());
        assertThat(mQuestStageConditionDescription1).isEqualTo(mQuestStageConditionDescription2);
        mQuestStageConditionDescription2.setId(2L);
        assertThat(mQuestStageConditionDescription1).isNotEqualTo(mQuestStageConditionDescription2);
        mQuestStageConditionDescription1.setId(null);
        assertThat(mQuestStageConditionDescription1).isNotEqualTo(mQuestStageConditionDescription2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestStageConditionDescriptionDTO.class);
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO1 = new MQuestStageConditionDescriptionDTO();
        mQuestStageConditionDescriptionDTO1.setId(1L);
        MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO2 = new MQuestStageConditionDescriptionDTO();
        assertThat(mQuestStageConditionDescriptionDTO1).isNotEqualTo(mQuestStageConditionDescriptionDTO2);
        mQuestStageConditionDescriptionDTO2.setId(mQuestStageConditionDescriptionDTO1.getId());
        assertThat(mQuestStageConditionDescriptionDTO1).isEqualTo(mQuestStageConditionDescriptionDTO2);
        mQuestStageConditionDescriptionDTO2.setId(2L);
        assertThat(mQuestStageConditionDescriptionDTO1).isNotEqualTo(mQuestStageConditionDescriptionDTO2);
        mQuestStageConditionDescriptionDTO1.setId(null);
        assertThat(mQuestStageConditionDescriptionDTO1).isNotEqualTo(mQuestStageConditionDescriptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestStageConditionDescriptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestStageConditionDescriptionMapper.fromId(null)).isNull();
    }
}
