package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDeckRarityConditionDescription;
import io.shm.tsubasa.repository.MDeckRarityConditionDescriptionRepository;
import io.shm.tsubasa.service.MDeckRarityConditionDescriptionService;
import io.shm.tsubasa.service.dto.MDeckRarityConditionDescriptionDTO;
import io.shm.tsubasa.service.mapper.MDeckRarityConditionDescriptionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDeckRarityConditionDescriptionCriteria;
import io.shm.tsubasa.service.MDeckRarityConditionDescriptionQueryService;

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
 * Integration tests for the {@Link MDeckRarityConditionDescriptionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDeckRarityConditionDescriptionResourceIT {

    private static final Integer DEFAULT_RARITY_GROUP_ID = 1;
    private static final Integer UPDATED_RARITY_GROUP_ID = 2;

    private static final Integer DEFAULT_COUNT_TYPE = 1;
    private static final Integer UPDATED_COUNT_TYPE = 2;

    private static final Integer DEFAULT_IS_STARTING = 1;
    private static final Integer UPDATED_IS_STARTING = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ICON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ICON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SMALL_ICON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SMALL_ICON_NAME = "BBBBBBBBBB";

    @Autowired
    private MDeckRarityConditionDescriptionRepository mDeckRarityConditionDescriptionRepository;

    @Autowired
    private MDeckRarityConditionDescriptionMapper mDeckRarityConditionDescriptionMapper;

    @Autowired
    private MDeckRarityConditionDescriptionService mDeckRarityConditionDescriptionService;

    @Autowired
    private MDeckRarityConditionDescriptionQueryService mDeckRarityConditionDescriptionQueryService;

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

    private MockMvc restMDeckRarityConditionDescriptionMockMvc;

    private MDeckRarityConditionDescription mDeckRarityConditionDescription;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDeckRarityConditionDescriptionResource mDeckRarityConditionDescriptionResource = new MDeckRarityConditionDescriptionResource(mDeckRarityConditionDescriptionService, mDeckRarityConditionDescriptionQueryService);
        this.restMDeckRarityConditionDescriptionMockMvc = MockMvcBuilders.standaloneSetup(mDeckRarityConditionDescriptionResource)
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
    public static MDeckRarityConditionDescription createEntity(EntityManager em) {
        MDeckRarityConditionDescription mDeckRarityConditionDescription = new MDeckRarityConditionDescription()
            .rarityGroupId(DEFAULT_RARITY_GROUP_ID)
            .countType(DEFAULT_COUNT_TYPE)
            .isStarting(DEFAULT_IS_STARTING)
            .description(DEFAULT_DESCRIPTION)
            .iconName(DEFAULT_ICON_NAME)
            .smallIconName(DEFAULT_SMALL_ICON_NAME);
        return mDeckRarityConditionDescription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDeckRarityConditionDescription createUpdatedEntity(EntityManager em) {
        MDeckRarityConditionDescription mDeckRarityConditionDescription = new MDeckRarityConditionDescription()
            .rarityGroupId(UPDATED_RARITY_GROUP_ID)
            .countType(UPDATED_COUNT_TYPE)
            .isStarting(UPDATED_IS_STARTING)
            .description(UPDATED_DESCRIPTION)
            .iconName(UPDATED_ICON_NAME)
            .smallIconName(UPDATED_SMALL_ICON_NAME);
        return mDeckRarityConditionDescription;
    }

    @BeforeEach
    public void initTest() {
        mDeckRarityConditionDescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDeckRarityConditionDescription() throws Exception {
        int databaseSizeBeforeCreate = mDeckRarityConditionDescriptionRepository.findAll().size();

        // Create the MDeckRarityConditionDescription
        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO = mDeckRarityConditionDescriptionMapper.toDto(mDeckRarityConditionDescription);
        restMDeckRarityConditionDescriptionMockMvc.perform(post("/api/m-deck-rarity-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckRarityConditionDescriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the MDeckRarityConditionDescription in the database
        List<MDeckRarityConditionDescription> mDeckRarityConditionDescriptionList = mDeckRarityConditionDescriptionRepository.findAll();
        assertThat(mDeckRarityConditionDescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        MDeckRarityConditionDescription testMDeckRarityConditionDescription = mDeckRarityConditionDescriptionList.get(mDeckRarityConditionDescriptionList.size() - 1);
        assertThat(testMDeckRarityConditionDescription.getRarityGroupId()).isEqualTo(DEFAULT_RARITY_GROUP_ID);
        assertThat(testMDeckRarityConditionDescription.getCountType()).isEqualTo(DEFAULT_COUNT_TYPE);
        assertThat(testMDeckRarityConditionDescription.getIsStarting()).isEqualTo(DEFAULT_IS_STARTING);
        assertThat(testMDeckRarityConditionDescription.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMDeckRarityConditionDescription.getIconName()).isEqualTo(DEFAULT_ICON_NAME);
        assertThat(testMDeckRarityConditionDescription.getSmallIconName()).isEqualTo(DEFAULT_SMALL_ICON_NAME);
    }

    @Test
    @Transactional
    public void createMDeckRarityConditionDescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDeckRarityConditionDescriptionRepository.findAll().size();

        // Create the MDeckRarityConditionDescription with an existing ID
        mDeckRarityConditionDescription.setId(1L);
        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO = mDeckRarityConditionDescriptionMapper.toDto(mDeckRarityConditionDescription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDeckRarityConditionDescriptionMockMvc.perform(post("/api/m-deck-rarity-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckRarityConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDeckRarityConditionDescription in the database
        List<MDeckRarityConditionDescription> mDeckRarityConditionDescriptionList = mDeckRarityConditionDescriptionRepository.findAll();
        assertThat(mDeckRarityConditionDescriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckRarityConditionDescriptionRepository.findAll().size();
        // set the field null
        mDeckRarityConditionDescription.setRarityGroupId(null);

        // Create the MDeckRarityConditionDescription, which fails.
        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO = mDeckRarityConditionDescriptionMapper.toDto(mDeckRarityConditionDescription);

        restMDeckRarityConditionDescriptionMockMvc.perform(post("/api/m-deck-rarity-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckRarityConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckRarityConditionDescription> mDeckRarityConditionDescriptionList = mDeckRarityConditionDescriptionRepository.findAll();
        assertThat(mDeckRarityConditionDescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckRarityConditionDescriptionRepository.findAll().size();
        // set the field null
        mDeckRarityConditionDescription.setCountType(null);

        // Create the MDeckRarityConditionDescription, which fails.
        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO = mDeckRarityConditionDescriptionMapper.toDto(mDeckRarityConditionDescription);

        restMDeckRarityConditionDescriptionMockMvc.perform(post("/api/m-deck-rarity-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckRarityConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckRarityConditionDescription> mDeckRarityConditionDescriptionList = mDeckRarityConditionDescriptionRepository.findAll();
        assertThat(mDeckRarityConditionDescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsStartingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDeckRarityConditionDescriptionRepository.findAll().size();
        // set the field null
        mDeckRarityConditionDescription.setIsStarting(null);

        // Create the MDeckRarityConditionDescription, which fails.
        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO = mDeckRarityConditionDescriptionMapper.toDto(mDeckRarityConditionDescription);

        restMDeckRarityConditionDescriptionMockMvc.perform(post("/api/m-deck-rarity-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckRarityConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<MDeckRarityConditionDescription> mDeckRarityConditionDescriptionList = mDeckRarityConditionDescriptionRepository.findAll();
        assertThat(mDeckRarityConditionDescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptions() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList
        restMDeckRarityConditionDescriptionMockMvc.perform(get("/api/m-deck-rarity-condition-descriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDeckRarityConditionDescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarityGroupId").value(hasItem(DEFAULT_RARITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].countType").value(hasItem(DEFAULT_COUNT_TYPE)))
            .andExpect(jsonPath("$.[*].isStarting").value(hasItem(DEFAULT_IS_STARTING)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].iconName").value(hasItem(DEFAULT_ICON_NAME.toString())))
            .andExpect(jsonPath("$.[*].smallIconName").value(hasItem(DEFAULT_SMALL_ICON_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMDeckRarityConditionDescription() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get the mDeckRarityConditionDescription
        restMDeckRarityConditionDescriptionMockMvc.perform(get("/api/m-deck-rarity-condition-descriptions/{id}", mDeckRarityConditionDescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDeckRarityConditionDescription.getId().intValue()))
            .andExpect(jsonPath("$.rarityGroupId").value(DEFAULT_RARITY_GROUP_ID))
            .andExpect(jsonPath("$.countType").value(DEFAULT_COUNT_TYPE))
            .andExpect(jsonPath("$.isStarting").value(DEFAULT_IS_STARTING))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.iconName").value(DEFAULT_ICON_NAME.toString()))
            .andExpect(jsonPath("$.smallIconName").value(DEFAULT_SMALL_ICON_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByRarityGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId equals to DEFAULT_RARITY_GROUP_ID
        defaultMDeckRarityConditionDescriptionShouldBeFound("rarityGroupId.equals=" + DEFAULT_RARITY_GROUP_ID);

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId equals to UPDATED_RARITY_GROUP_ID
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("rarityGroupId.equals=" + UPDATED_RARITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByRarityGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId in DEFAULT_RARITY_GROUP_ID or UPDATED_RARITY_GROUP_ID
        defaultMDeckRarityConditionDescriptionShouldBeFound("rarityGroupId.in=" + DEFAULT_RARITY_GROUP_ID + "," + UPDATED_RARITY_GROUP_ID);

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId equals to UPDATED_RARITY_GROUP_ID
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("rarityGroupId.in=" + UPDATED_RARITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByRarityGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId is not null
        defaultMDeckRarityConditionDescriptionShouldBeFound("rarityGroupId.specified=true");

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId is null
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("rarityGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByRarityGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId greater than or equals to DEFAULT_RARITY_GROUP_ID
        defaultMDeckRarityConditionDescriptionShouldBeFound("rarityGroupId.greaterOrEqualThan=" + DEFAULT_RARITY_GROUP_ID);

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId greater than or equals to UPDATED_RARITY_GROUP_ID
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("rarityGroupId.greaterOrEqualThan=" + UPDATED_RARITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByRarityGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId less than or equals to DEFAULT_RARITY_GROUP_ID
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("rarityGroupId.lessThan=" + DEFAULT_RARITY_GROUP_ID);

        // Get all the mDeckRarityConditionDescriptionList where rarityGroupId less than or equals to UPDATED_RARITY_GROUP_ID
        defaultMDeckRarityConditionDescriptionShouldBeFound("rarityGroupId.lessThan=" + UPDATED_RARITY_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByCountTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where countType equals to DEFAULT_COUNT_TYPE
        defaultMDeckRarityConditionDescriptionShouldBeFound("countType.equals=" + DEFAULT_COUNT_TYPE);

        // Get all the mDeckRarityConditionDescriptionList where countType equals to UPDATED_COUNT_TYPE
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("countType.equals=" + UPDATED_COUNT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByCountTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where countType in DEFAULT_COUNT_TYPE or UPDATED_COUNT_TYPE
        defaultMDeckRarityConditionDescriptionShouldBeFound("countType.in=" + DEFAULT_COUNT_TYPE + "," + UPDATED_COUNT_TYPE);

        // Get all the mDeckRarityConditionDescriptionList where countType equals to UPDATED_COUNT_TYPE
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("countType.in=" + UPDATED_COUNT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByCountTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where countType is not null
        defaultMDeckRarityConditionDescriptionShouldBeFound("countType.specified=true");

        // Get all the mDeckRarityConditionDescriptionList where countType is null
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("countType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByCountTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where countType greater than or equals to DEFAULT_COUNT_TYPE
        defaultMDeckRarityConditionDescriptionShouldBeFound("countType.greaterOrEqualThan=" + DEFAULT_COUNT_TYPE);

        // Get all the mDeckRarityConditionDescriptionList where countType greater than or equals to UPDATED_COUNT_TYPE
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("countType.greaterOrEqualThan=" + UPDATED_COUNT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByCountTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where countType less than or equals to DEFAULT_COUNT_TYPE
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("countType.lessThan=" + DEFAULT_COUNT_TYPE);

        // Get all the mDeckRarityConditionDescriptionList where countType less than or equals to UPDATED_COUNT_TYPE
        defaultMDeckRarityConditionDescriptionShouldBeFound("countType.lessThan=" + UPDATED_COUNT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByIsStartingIsEqualToSomething() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where isStarting equals to DEFAULT_IS_STARTING
        defaultMDeckRarityConditionDescriptionShouldBeFound("isStarting.equals=" + DEFAULT_IS_STARTING);

        // Get all the mDeckRarityConditionDescriptionList where isStarting equals to UPDATED_IS_STARTING
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("isStarting.equals=" + UPDATED_IS_STARTING);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByIsStartingIsInShouldWork() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where isStarting in DEFAULT_IS_STARTING or UPDATED_IS_STARTING
        defaultMDeckRarityConditionDescriptionShouldBeFound("isStarting.in=" + DEFAULT_IS_STARTING + "," + UPDATED_IS_STARTING);

        // Get all the mDeckRarityConditionDescriptionList where isStarting equals to UPDATED_IS_STARTING
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("isStarting.in=" + UPDATED_IS_STARTING);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByIsStartingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where isStarting is not null
        defaultMDeckRarityConditionDescriptionShouldBeFound("isStarting.specified=true");

        // Get all the mDeckRarityConditionDescriptionList where isStarting is null
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("isStarting.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByIsStartingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where isStarting greater than or equals to DEFAULT_IS_STARTING
        defaultMDeckRarityConditionDescriptionShouldBeFound("isStarting.greaterOrEqualThan=" + DEFAULT_IS_STARTING);

        // Get all the mDeckRarityConditionDescriptionList where isStarting greater than or equals to UPDATED_IS_STARTING
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("isStarting.greaterOrEqualThan=" + UPDATED_IS_STARTING);
    }

    @Test
    @Transactional
    public void getAllMDeckRarityConditionDescriptionsByIsStartingIsLessThanSomething() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        // Get all the mDeckRarityConditionDescriptionList where isStarting less than or equals to DEFAULT_IS_STARTING
        defaultMDeckRarityConditionDescriptionShouldNotBeFound("isStarting.lessThan=" + DEFAULT_IS_STARTING);

        // Get all the mDeckRarityConditionDescriptionList where isStarting less than or equals to UPDATED_IS_STARTING
        defaultMDeckRarityConditionDescriptionShouldBeFound("isStarting.lessThan=" + UPDATED_IS_STARTING);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDeckRarityConditionDescriptionShouldBeFound(String filter) throws Exception {
        restMDeckRarityConditionDescriptionMockMvc.perform(get("/api/m-deck-rarity-condition-descriptions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDeckRarityConditionDescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarityGroupId").value(hasItem(DEFAULT_RARITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].countType").value(hasItem(DEFAULT_COUNT_TYPE)))
            .andExpect(jsonPath("$.[*].isStarting").value(hasItem(DEFAULT_IS_STARTING)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].iconName").value(hasItem(DEFAULT_ICON_NAME.toString())))
            .andExpect(jsonPath("$.[*].smallIconName").value(hasItem(DEFAULT_SMALL_ICON_NAME.toString())));

        // Check, that the count call also returns 1
        restMDeckRarityConditionDescriptionMockMvc.perform(get("/api/m-deck-rarity-condition-descriptions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDeckRarityConditionDescriptionShouldNotBeFound(String filter) throws Exception {
        restMDeckRarityConditionDescriptionMockMvc.perform(get("/api/m-deck-rarity-condition-descriptions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDeckRarityConditionDescriptionMockMvc.perform(get("/api/m-deck-rarity-condition-descriptions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDeckRarityConditionDescription() throws Exception {
        // Get the mDeckRarityConditionDescription
        restMDeckRarityConditionDescriptionMockMvc.perform(get("/api/m-deck-rarity-condition-descriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDeckRarityConditionDescription() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        int databaseSizeBeforeUpdate = mDeckRarityConditionDescriptionRepository.findAll().size();

        // Update the mDeckRarityConditionDescription
        MDeckRarityConditionDescription updatedMDeckRarityConditionDescription = mDeckRarityConditionDescriptionRepository.findById(mDeckRarityConditionDescription.getId()).get();
        // Disconnect from session so that the updates on updatedMDeckRarityConditionDescription are not directly saved in db
        em.detach(updatedMDeckRarityConditionDescription);
        updatedMDeckRarityConditionDescription
            .rarityGroupId(UPDATED_RARITY_GROUP_ID)
            .countType(UPDATED_COUNT_TYPE)
            .isStarting(UPDATED_IS_STARTING)
            .description(UPDATED_DESCRIPTION)
            .iconName(UPDATED_ICON_NAME)
            .smallIconName(UPDATED_SMALL_ICON_NAME);
        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO = mDeckRarityConditionDescriptionMapper.toDto(updatedMDeckRarityConditionDescription);

        restMDeckRarityConditionDescriptionMockMvc.perform(put("/api/m-deck-rarity-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckRarityConditionDescriptionDTO)))
            .andExpect(status().isOk());

        // Validate the MDeckRarityConditionDescription in the database
        List<MDeckRarityConditionDescription> mDeckRarityConditionDescriptionList = mDeckRarityConditionDescriptionRepository.findAll();
        assertThat(mDeckRarityConditionDescriptionList).hasSize(databaseSizeBeforeUpdate);
        MDeckRarityConditionDescription testMDeckRarityConditionDescription = mDeckRarityConditionDescriptionList.get(mDeckRarityConditionDescriptionList.size() - 1);
        assertThat(testMDeckRarityConditionDescription.getRarityGroupId()).isEqualTo(UPDATED_RARITY_GROUP_ID);
        assertThat(testMDeckRarityConditionDescription.getCountType()).isEqualTo(UPDATED_COUNT_TYPE);
        assertThat(testMDeckRarityConditionDescription.getIsStarting()).isEqualTo(UPDATED_IS_STARTING);
        assertThat(testMDeckRarityConditionDescription.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMDeckRarityConditionDescription.getIconName()).isEqualTo(UPDATED_ICON_NAME);
        assertThat(testMDeckRarityConditionDescription.getSmallIconName()).isEqualTo(UPDATED_SMALL_ICON_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMDeckRarityConditionDescription() throws Exception {
        int databaseSizeBeforeUpdate = mDeckRarityConditionDescriptionRepository.findAll().size();

        // Create the MDeckRarityConditionDescription
        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO = mDeckRarityConditionDescriptionMapper.toDto(mDeckRarityConditionDescription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDeckRarityConditionDescriptionMockMvc.perform(put("/api/m-deck-rarity-condition-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDeckRarityConditionDescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDeckRarityConditionDescription in the database
        List<MDeckRarityConditionDescription> mDeckRarityConditionDescriptionList = mDeckRarityConditionDescriptionRepository.findAll();
        assertThat(mDeckRarityConditionDescriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDeckRarityConditionDescription() throws Exception {
        // Initialize the database
        mDeckRarityConditionDescriptionRepository.saveAndFlush(mDeckRarityConditionDescription);

        int databaseSizeBeforeDelete = mDeckRarityConditionDescriptionRepository.findAll().size();

        // Delete the mDeckRarityConditionDescription
        restMDeckRarityConditionDescriptionMockMvc.perform(delete("/api/m-deck-rarity-condition-descriptions/{id}", mDeckRarityConditionDescription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDeckRarityConditionDescription> mDeckRarityConditionDescriptionList = mDeckRarityConditionDescriptionRepository.findAll();
        assertThat(mDeckRarityConditionDescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDeckRarityConditionDescription.class);
        MDeckRarityConditionDescription mDeckRarityConditionDescription1 = new MDeckRarityConditionDescription();
        mDeckRarityConditionDescription1.setId(1L);
        MDeckRarityConditionDescription mDeckRarityConditionDescription2 = new MDeckRarityConditionDescription();
        mDeckRarityConditionDescription2.setId(mDeckRarityConditionDescription1.getId());
        assertThat(mDeckRarityConditionDescription1).isEqualTo(mDeckRarityConditionDescription2);
        mDeckRarityConditionDescription2.setId(2L);
        assertThat(mDeckRarityConditionDescription1).isNotEqualTo(mDeckRarityConditionDescription2);
        mDeckRarityConditionDescription1.setId(null);
        assertThat(mDeckRarityConditionDescription1).isNotEqualTo(mDeckRarityConditionDescription2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDeckRarityConditionDescriptionDTO.class);
        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO1 = new MDeckRarityConditionDescriptionDTO();
        mDeckRarityConditionDescriptionDTO1.setId(1L);
        MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO2 = new MDeckRarityConditionDescriptionDTO();
        assertThat(mDeckRarityConditionDescriptionDTO1).isNotEqualTo(mDeckRarityConditionDescriptionDTO2);
        mDeckRarityConditionDescriptionDTO2.setId(mDeckRarityConditionDescriptionDTO1.getId());
        assertThat(mDeckRarityConditionDescriptionDTO1).isEqualTo(mDeckRarityConditionDescriptionDTO2);
        mDeckRarityConditionDescriptionDTO2.setId(2L);
        assertThat(mDeckRarityConditionDescriptionDTO1).isNotEqualTo(mDeckRarityConditionDescriptionDTO2);
        mDeckRarityConditionDescriptionDTO1.setId(null);
        assertThat(mDeckRarityConditionDescriptionDTO1).isNotEqualTo(mDeckRarityConditionDescriptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDeckRarityConditionDescriptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDeckRarityConditionDescriptionMapper.fromId(null)).isNull();
    }
}
