package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLuck;
import io.shm.tsubasa.repository.MLuckRepository;
import io.shm.tsubasa.service.MLuckService;
import io.shm.tsubasa.service.dto.MLuckDTO;
import io.shm.tsubasa.service.mapper.MLuckMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLuckCriteria;
import io.shm.tsubasa.service.MLuckQueryService;

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
 * Integration tests for the {@Link MLuckResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLuckResourceIT {

    private static final Integer DEFAULT_TARGET_ATTRIBUTE = 1;
    private static final Integer UPDATED_TARGET_ATTRIBUTE = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_GROUP_ID = 2;

    private static final Integer DEFAULT_TARGET_TEAM_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_TEAM_GROUP_ID = 2;

    private static final Integer DEFAULT_TARGET_NATIONALITY_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_NATIONALITY_GROUP_ID = 2;

    private static final Integer DEFAULT_LUCK_RATE_GROUP_ID = 1;
    private static final Integer UPDATED_LUCK_RATE_GROUP_ID = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MLuckRepository mLuckRepository;

    @Autowired
    private MLuckMapper mLuckMapper;

    @Autowired
    private MLuckService mLuckService;

    @Autowired
    private MLuckQueryService mLuckQueryService;

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

    private MockMvc restMLuckMockMvc;

    private MLuck mLuck;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLuckResource mLuckResource = new MLuckResource(mLuckService, mLuckQueryService);
        this.restMLuckMockMvc = MockMvcBuilders.standaloneSetup(mLuckResource)
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
    public static MLuck createEntity(EntityManager em) {
        MLuck mLuck = new MLuck()
            .targetAttribute(DEFAULT_TARGET_ATTRIBUTE)
            .targetCharacterGroupId(DEFAULT_TARGET_CHARACTER_GROUP_ID)
            .targetTeamGroupId(DEFAULT_TARGET_TEAM_GROUP_ID)
            .targetNationalityGroupId(DEFAULT_TARGET_NATIONALITY_GROUP_ID)
            .luckRateGroupId(DEFAULT_LUCK_RATE_GROUP_ID)
            .description(DEFAULT_DESCRIPTION);
        return mLuck;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLuck createUpdatedEntity(EntityManager em) {
        MLuck mLuck = new MLuck()
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetCharacterGroupId(UPDATED_TARGET_CHARACTER_GROUP_ID)
            .targetTeamGroupId(UPDATED_TARGET_TEAM_GROUP_ID)
            .targetNationalityGroupId(UPDATED_TARGET_NATIONALITY_GROUP_ID)
            .luckRateGroupId(UPDATED_LUCK_RATE_GROUP_ID)
            .description(UPDATED_DESCRIPTION);
        return mLuck;
    }

    @BeforeEach
    public void initTest() {
        mLuck = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLuck() throws Exception {
        int databaseSizeBeforeCreate = mLuckRepository.findAll().size();

        // Create the MLuck
        MLuckDTO mLuckDTO = mLuckMapper.toDto(mLuck);
        restMLuckMockMvc.perform(post("/api/m-lucks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckDTO)))
            .andExpect(status().isCreated());

        // Validate the MLuck in the database
        List<MLuck> mLuckList = mLuckRepository.findAll();
        assertThat(mLuckList).hasSize(databaseSizeBeforeCreate + 1);
        MLuck testMLuck = mLuckList.get(mLuckList.size() - 1);
        assertThat(testMLuck.getTargetAttribute()).isEqualTo(DEFAULT_TARGET_ATTRIBUTE);
        assertThat(testMLuck.getTargetCharacterGroupId()).isEqualTo(DEFAULT_TARGET_CHARACTER_GROUP_ID);
        assertThat(testMLuck.getTargetTeamGroupId()).isEqualTo(DEFAULT_TARGET_TEAM_GROUP_ID);
        assertThat(testMLuck.getTargetNationalityGroupId()).isEqualTo(DEFAULT_TARGET_NATIONALITY_GROUP_ID);
        assertThat(testMLuck.getLuckRateGroupId()).isEqualTo(DEFAULT_LUCK_RATE_GROUP_ID);
        assertThat(testMLuck.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMLuckWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLuckRepository.findAll().size();

        // Create the MLuck with an existing ID
        mLuck.setId(1L);
        MLuckDTO mLuckDTO = mLuckMapper.toDto(mLuck);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLuckMockMvc.perform(post("/api/m-lucks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuck in the database
        List<MLuck> mLuckList = mLuckRepository.findAll();
        assertThat(mLuckList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLuckRateGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckRepository.findAll().size();
        // set the field null
        mLuck.setLuckRateGroupId(null);

        // Create the MLuck, which fails.
        MLuckDTO mLuckDTO = mLuckMapper.toDto(mLuck);

        restMLuckMockMvc.perform(post("/api/m-lucks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckDTO)))
            .andExpect(status().isBadRequest());

        List<MLuck> mLuckList = mLuckRepository.findAll();
        assertThat(mLuckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLucks() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList
        restMLuckMockMvc.perform(get("/api/m-lucks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuck.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].luckRateGroupId").value(hasItem(DEFAULT_LUCK_RATE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMLuck() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get the mLuck
        restMLuckMockMvc.perform(get("/api/m-lucks/{id}", mLuck.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLuck.getId().intValue()))
            .andExpect(jsonPath("$.targetAttribute").value(DEFAULT_TARGET_ATTRIBUTE))
            .andExpect(jsonPath("$.targetCharacterGroupId").value(DEFAULT_TARGET_CHARACTER_GROUP_ID))
            .andExpect(jsonPath("$.targetTeamGroupId").value(DEFAULT_TARGET_TEAM_GROUP_ID))
            .andExpect(jsonPath("$.targetNationalityGroupId").value(DEFAULT_TARGET_NATIONALITY_GROUP_ID))
            .andExpect(jsonPath("$.luckRateGroupId").value(DEFAULT_LUCK_RATE_GROUP_ID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetAttributeIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetAttribute equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMLuckShouldBeFound("targetAttribute.equals=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mLuckList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMLuckShouldNotBeFound("targetAttribute.equals=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetAttributeIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetAttribute in DEFAULT_TARGET_ATTRIBUTE or UPDATED_TARGET_ATTRIBUTE
        defaultMLuckShouldBeFound("targetAttribute.in=" + DEFAULT_TARGET_ATTRIBUTE + "," + UPDATED_TARGET_ATTRIBUTE);

        // Get all the mLuckList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMLuckShouldNotBeFound("targetAttribute.in=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetAttributeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetAttribute is not null
        defaultMLuckShouldBeFound("targetAttribute.specified=true");

        // Get all the mLuckList where targetAttribute is null
        defaultMLuckShouldNotBeFound("targetAttribute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetAttributeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetAttribute greater than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMLuckShouldBeFound("targetAttribute.greaterOrEqualThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mLuckList where targetAttribute greater than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMLuckShouldNotBeFound("targetAttribute.greaterOrEqualThan=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetAttributeIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetAttribute less than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMLuckShouldNotBeFound("targetAttribute.lessThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mLuckList where targetAttribute less than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMLuckShouldBeFound("targetAttribute.lessThan=" + UPDATED_TARGET_ATTRIBUTE);
    }


    @Test
    @Transactional
    public void getAllMLucksByTargetCharacterGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetCharacterGroupId equals to DEFAULT_TARGET_CHARACTER_GROUP_ID
        defaultMLuckShouldBeFound("targetCharacterGroupId.equals=" + DEFAULT_TARGET_CHARACTER_GROUP_ID);

        // Get all the mLuckList where targetCharacterGroupId equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMLuckShouldNotBeFound("targetCharacterGroupId.equals=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetCharacterGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetCharacterGroupId in DEFAULT_TARGET_CHARACTER_GROUP_ID or UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMLuckShouldBeFound("targetCharacterGroupId.in=" + DEFAULT_TARGET_CHARACTER_GROUP_ID + "," + UPDATED_TARGET_CHARACTER_GROUP_ID);

        // Get all the mLuckList where targetCharacterGroupId equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMLuckShouldNotBeFound("targetCharacterGroupId.in=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetCharacterGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetCharacterGroupId is not null
        defaultMLuckShouldBeFound("targetCharacterGroupId.specified=true");

        // Get all the mLuckList where targetCharacterGroupId is null
        defaultMLuckShouldNotBeFound("targetCharacterGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetCharacterGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetCharacterGroupId greater than or equals to DEFAULT_TARGET_CHARACTER_GROUP_ID
        defaultMLuckShouldBeFound("targetCharacterGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_GROUP_ID);

        // Get all the mLuckList where targetCharacterGroupId greater than or equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMLuckShouldNotBeFound("targetCharacterGroupId.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetCharacterGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetCharacterGroupId less than or equals to DEFAULT_TARGET_CHARACTER_GROUP_ID
        defaultMLuckShouldNotBeFound("targetCharacterGroupId.lessThan=" + DEFAULT_TARGET_CHARACTER_GROUP_ID);

        // Get all the mLuckList where targetCharacterGroupId less than or equals to UPDATED_TARGET_CHARACTER_GROUP_ID
        defaultMLuckShouldBeFound("targetCharacterGroupId.lessThan=" + UPDATED_TARGET_CHARACTER_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLucksByTargetTeamGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetTeamGroupId equals to DEFAULT_TARGET_TEAM_GROUP_ID
        defaultMLuckShouldBeFound("targetTeamGroupId.equals=" + DEFAULT_TARGET_TEAM_GROUP_ID);

        // Get all the mLuckList where targetTeamGroupId equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMLuckShouldNotBeFound("targetTeamGroupId.equals=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetTeamGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetTeamGroupId in DEFAULT_TARGET_TEAM_GROUP_ID or UPDATED_TARGET_TEAM_GROUP_ID
        defaultMLuckShouldBeFound("targetTeamGroupId.in=" + DEFAULT_TARGET_TEAM_GROUP_ID + "," + UPDATED_TARGET_TEAM_GROUP_ID);

        // Get all the mLuckList where targetTeamGroupId equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMLuckShouldNotBeFound("targetTeamGroupId.in=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetTeamGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetTeamGroupId is not null
        defaultMLuckShouldBeFound("targetTeamGroupId.specified=true");

        // Get all the mLuckList where targetTeamGroupId is null
        defaultMLuckShouldNotBeFound("targetTeamGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetTeamGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetTeamGroupId greater than or equals to DEFAULT_TARGET_TEAM_GROUP_ID
        defaultMLuckShouldBeFound("targetTeamGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_TEAM_GROUP_ID);

        // Get all the mLuckList where targetTeamGroupId greater than or equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMLuckShouldNotBeFound("targetTeamGroupId.greaterOrEqualThan=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetTeamGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetTeamGroupId less than or equals to DEFAULT_TARGET_TEAM_GROUP_ID
        defaultMLuckShouldNotBeFound("targetTeamGroupId.lessThan=" + DEFAULT_TARGET_TEAM_GROUP_ID);

        // Get all the mLuckList where targetTeamGroupId less than or equals to UPDATED_TARGET_TEAM_GROUP_ID
        defaultMLuckShouldBeFound("targetTeamGroupId.lessThan=" + UPDATED_TARGET_TEAM_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLucksByTargetNationalityGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetNationalityGroupId equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMLuckShouldBeFound("targetNationalityGroupId.equals=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mLuckList where targetNationalityGroupId equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMLuckShouldNotBeFound("targetNationalityGroupId.equals=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetNationalityGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetNationalityGroupId in DEFAULT_TARGET_NATIONALITY_GROUP_ID or UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMLuckShouldBeFound("targetNationalityGroupId.in=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID + "," + UPDATED_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mLuckList where targetNationalityGroupId equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMLuckShouldNotBeFound("targetNationalityGroupId.in=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetNationalityGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetNationalityGroupId is not null
        defaultMLuckShouldBeFound("targetNationalityGroupId.specified=true");

        // Get all the mLuckList where targetNationalityGroupId is null
        defaultMLuckShouldNotBeFound("targetNationalityGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetNationalityGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetNationalityGroupId greater than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMLuckShouldBeFound("targetNationalityGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mLuckList where targetNationalityGroupId greater than or equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMLuckShouldNotBeFound("targetNationalityGroupId.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByTargetNationalityGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where targetNationalityGroupId less than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMLuckShouldNotBeFound("targetNationalityGroupId.lessThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mLuckList where targetNationalityGroupId less than or equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMLuckShouldBeFound("targetNationalityGroupId.lessThan=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLucksByLuckRateGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where luckRateGroupId equals to DEFAULT_LUCK_RATE_GROUP_ID
        defaultMLuckShouldBeFound("luckRateGroupId.equals=" + DEFAULT_LUCK_RATE_GROUP_ID);

        // Get all the mLuckList where luckRateGroupId equals to UPDATED_LUCK_RATE_GROUP_ID
        defaultMLuckShouldNotBeFound("luckRateGroupId.equals=" + UPDATED_LUCK_RATE_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByLuckRateGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where luckRateGroupId in DEFAULT_LUCK_RATE_GROUP_ID or UPDATED_LUCK_RATE_GROUP_ID
        defaultMLuckShouldBeFound("luckRateGroupId.in=" + DEFAULT_LUCK_RATE_GROUP_ID + "," + UPDATED_LUCK_RATE_GROUP_ID);

        // Get all the mLuckList where luckRateGroupId equals to UPDATED_LUCK_RATE_GROUP_ID
        defaultMLuckShouldNotBeFound("luckRateGroupId.in=" + UPDATED_LUCK_RATE_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByLuckRateGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where luckRateGroupId is not null
        defaultMLuckShouldBeFound("luckRateGroupId.specified=true");

        // Get all the mLuckList where luckRateGroupId is null
        defaultMLuckShouldNotBeFound("luckRateGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLucksByLuckRateGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where luckRateGroupId greater than or equals to DEFAULT_LUCK_RATE_GROUP_ID
        defaultMLuckShouldBeFound("luckRateGroupId.greaterOrEqualThan=" + DEFAULT_LUCK_RATE_GROUP_ID);

        // Get all the mLuckList where luckRateGroupId greater than or equals to UPDATED_LUCK_RATE_GROUP_ID
        defaultMLuckShouldNotBeFound("luckRateGroupId.greaterOrEqualThan=" + UPDATED_LUCK_RATE_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLucksByLuckRateGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        // Get all the mLuckList where luckRateGroupId less than or equals to DEFAULT_LUCK_RATE_GROUP_ID
        defaultMLuckShouldNotBeFound("luckRateGroupId.lessThan=" + DEFAULT_LUCK_RATE_GROUP_ID);

        // Get all the mLuckList where luckRateGroupId less than or equals to UPDATED_LUCK_RATE_GROUP_ID
        defaultMLuckShouldBeFound("luckRateGroupId.lessThan=" + UPDATED_LUCK_RATE_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLuckShouldBeFound(String filter) throws Exception {
        restMLuckMockMvc.perform(get("/api/m-lucks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuck.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetCharacterGroupId").value(hasItem(DEFAULT_TARGET_CHARACTER_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetTeamGroupId").value(hasItem(DEFAULT_TARGET_TEAM_GROUP_ID)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].luckRateGroupId").value(hasItem(DEFAULT_LUCK_RATE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMLuckMockMvc.perform(get("/api/m-lucks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLuckShouldNotBeFound(String filter) throws Exception {
        restMLuckMockMvc.perform(get("/api/m-lucks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLuckMockMvc.perform(get("/api/m-lucks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLuck() throws Exception {
        // Get the mLuck
        restMLuckMockMvc.perform(get("/api/m-lucks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLuck() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        int databaseSizeBeforeUpdate = mLuckRepository.findAll().size();

        // Update the mLuck
        MLuck updatedMLuck = mLuckRepository.findById(mLuck.getId()).get();
        // Disconnect from session so that the updates on updatedMLuck are not directly saved in db
        em.detach(updatedMLuck);
        updatedMLuck
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetCharacterGroupId(UPDATED_TARGET_CHARACTER_GROUP_ID)
            .targetTeamGroupId(UPDATED_TARGET_TEAM_GROUP_ID)
            .targetNationalityGroupId(UPDATED_TARGET_NATIONALITY_GROUP_ID)
            .luckRateGroupId(UPDATED_LUCK_RATE_GROUP_ID)
            .description(UPDATED_DESCRIPTION);
        MLuckDTO mLuckDTO = mLuckMapper.toDto(updatedMLuck);

        restMLuckMockMvc.perform(put("/api/m-lucks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckDTO)))
            .andExpect(status().isOk());

        // Validate the MLuck in the database
        List<MLuck> mLuckList = mLuckRepository.findAll();
        assertThat(mLuckList).hasSize(databaseSizeBeforeUpdate);
        MLuck testMLuck = mLuckList.get(mLuckList.size() - 1);
        assertThat(testMLuck.getTargetAttribute()).isEqualTo(UPDATED_TARGET_ATTRIBUTE);
        assertThat(testMLuck.getTargetCharacterGroupId()).isEqualTo(UPDATED_TARGET_CHARACTER_GROUP_ID);
        assertThat(testMLuck.getTargetTeamGroupId()).isEqualTo(UPDATED_TARGET_TEAM_GROUP_ID);
        assertThat(testMLuck.getTargetNationalityGroupId()).isEqualTo(UPDATED_TARGET_NATIONALITY_GROUP_ID);
        assertThat(testMLuck.getLuckRateGroupId()).isEqualTo(UPDATED_LUCK_RATE_GROUP_ID);
        assertThat(testMLuck.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMLuck() throws Exception {
        int databaseSizeBeforeUpdate = mLuckRepository.findAll().size();

        // Create the MLuck
        MLuckDTO mLuckDTO = mLuckMapper.toDto(mLuck);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLuckMockMvc.perform(put("/api/m-lucks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuck in the database
        List<MLuck> mLuckList = mLuckRepository.findAll();
        assertThat(mLuckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLuck() throws Exception {
        // Initialize the database
        mLuckRepository.saveAndFlush(mLuck);

        int databaseSizeBeforeDelete = mLuckRepository.findAll().size();

        // Delete the mLuck
        restMLuckMockMvc.perform(delete("/api/m-lucks/{id}", mLuck.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLuck> mLuckList = mLuckRepository.findAll();
        assertThat(mLuckList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuck.class);
        MLuck mLuck1 = new MLuck();
        mLuck1.setId(1L);
        MLuck mLuck2 = new MLuck();
        mLuck2.setId(mLuck1.getId());
        assertThat(mLuck1).isEqualTo(mLuck2);
        mLuck2.setId(2L);
        assertThat(mLuck1).isNotEqualTo(mLuck2);
        mLuck1.setId(null);
        assertThat(mLuck1).isNotEqualTo(mLuck2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuckDTO.class);
        MLuckDTO mLuckDTO1 = new MLuckDTO();
        mLuckDTO1.setId(1L);
        MLuckDTO mLuckDTO2 = new MLuckDTO();
        assertThat(mLuckDTO1).isNotEqualTo(mLuckDTO2);
        mLuckDTO2.setId(mLuckDTO1.getId());
        assertThat(mLuckDTO1).isEqualTo(mLuckDTO2);
        mLuckDTO2.setId(2L);
        assertThat(mLuckDTO1).isNotEqualTo(mLuckDTO2);
        mLuckDTO1.setId(null);
        assertThat(mLuckDTO1).isNotEqualTo(mLuckDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLuckMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLuckMapper.fromId(null)).isNull();
    }
}
