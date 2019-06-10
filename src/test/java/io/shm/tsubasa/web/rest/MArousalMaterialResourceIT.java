package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MArousalMaterial;
import io.shm.tsubasa.repository.MArousalMaterialRepository;
import io.shm.tsubasa.service.MArousalMaterialService;
import io.shm.tsubasa.service.dto.MArousalMaterialDTO;
import io.shm.tsubasa.service.mapper.MArousalMaterialMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MArousalMaterialCriteria;
import io.shm.tsubasa.service.MArousalMaterialQueryService;

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
 * Integration tests for the {@Link MArousalMaterialResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MArousalMaterialResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    private static final Integer DEFAULT_MAIN_ACTION_LEVEL = 1;
    private static final Integer UPDATED_MAIN_ACTION_LEVEL = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MArousalMaterialRepository mArousalMaterialRepository;

    @Autowired
    private MArousalMaterialMapper mArousalMaterialMapper;

    @Autowired
    private MArousalMaterialService mArousalMaterialService;

    @Autowired
    private MArousalMaterialQueryService mArousalMaterialQueryService;

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

    private MockMvc restMArousalMaterialMockMvc;

    private MArousalMaterial mArousalMaterial;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MArousalMaterialResource mArousalMaterialResource = new MArousalMaterialResource(mArousalMaterialService, mArousalMaterialQueryService);
        this.restMArousalMaterialMockMvc = MockMvcBuilders.standaloneSetup(mArousalMaterialResource)
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
    public static MArousalMaterial createEntity(EntityManager em) {
        MArousalMaterial mArousalMaterial = new MArousalMaterial()
            .groupId(DEFAULT_GROUP_ID)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT)
            .mainActionLevel(DEFAULT_MAIN_ACTION_LEVEL)
            .description(DEFAULT_DESCRIPTION);
        return mArousalMaterial;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MArousalMaterial createUpdatedEntity(EntityManager em) {
        MArousalMaterial mArousalMaterial = new MArousalMaterial()
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT)
            .mainActionLevel(UPDATED_MAIN_ACTION_LEVEL)
            .description(UPDATED_DESCRIPTION);
        return mArousalMaterial;
    }

    @BeforeEach
    public void initTest() {
        mArousalMaterial = createEntity(em);
    }

    @Test
    @Transactional
    public void createMArousalMaterial() throws Exception {
        int databaseSizeBeforeCreate = mArousalMaterialRepository.findAll().size();

        // Create the MArousalMaterial
        MArousalMaterialDTO mArousalMaterialDTO = mArousalMaterialMapper.toDto(mArousalMaterial);
        restMArousalMaterialMockMvc.perform(post("/api/m-arousal-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalMaterialDTO)))
            .andExpect(status().isCreated());

        // Validate the MArousalMaterial in the database
        List<MArousalMaterial> mArousalMaterialList = mArousalMaterialRepository.findAll();
        assertThat(mArousalMaterialList).hasSize(databaseSizeBeforeCreate + 1);
        MArousalMaterial testMArousalMaterial = mArousalMaterialList.get(mArousalMaterialList.size() - 1);
        assertThat(testMArousalMaterial.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMArousalMaterial.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMArousalMaterial.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMArousalMaterial.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
        assertThat(testMArousalMaterial.getMainActionLevel()).isEqualTo(DEFAULT_MAIN_ACTION_LEVEL);
        assertThat(testMArousalMaterial.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMArousalMaterialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mArousalMaterialRepository.findAll().size();

        // Create the MArousalMaterial with an existing ID
        mArousalMaterial.setId(1L);
        MArousalMaterialDTO mArousalMaterialDTO = mArousalMaterialMapper.toDto(mArousalMaterial);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMArousalMaterialMockMvc.perform(post("/api/m-arousal-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalMaterialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MArousalMaterial in the database
        List<MArousalMaterial> mArousalMaterialList = mArousalMaterialRepository.findAll();
        assertThat(mArousalMaterialList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mArousalMaterialRepository.findAll().size();
        // set the field null
        mArousalMaterial.setGroupId(null);

        // Create the MArousalMaterial, which fails.
        MArousalMaterialDTO mArousalMaterialDTO = mArousalMaterialMapper.toDto(mArousalMaterial);

        restMArousalMaterialMockMvc.perform(post("/api/m-arousal-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalMaterialDTO)))
            .andExpect(status().isBadRequest());

        List<MArousalMaterial> mArousalMaterialList = mArousalMaterialRepository.findAll();
        assertThat(mArousalMaterialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mArousalMaterialRepository.findAll().size();
        // set the field null
        mArousalMaterial.setContentType(null);

        // Create the MArousalMaterial, which fails.
        MArousalMaterialDTO mArousalMaterialDTO = mArousalMaterialMapper.toDto(mArousalMaterial);

        restMArousalMaterialMockMvc.perform(post("/api/m-arousal-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalMaterialDTO)))
            .andExpect(status().isBadRequest());

        List<MArousalMaterial> mArousalMaterialList = mArousalMaterialRepository.findAll();
        assertThat(mArousalMaterialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mArousalMaterialRepository.findAll().size();
        // set the field null
        mArousalMaterial.setContentId(null);

        // Create the MArousalMaterial, which fails.
        MArousalMaterialDTO mArousalMaterialDTO = mArousalMaterialMapper.toDto(mArousalMaterial);

        restMArousalMaterialMockMvc.perform(post("/api/m-arousal-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalMaterialDTO)))
            .andExpect(status().isBadRequest());

        List<MArousalMaterial> mArousalMaterialList = mArousalMaterialRepository.findAll();
        assertThat(mArousalMaterialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mArousalMaterialRepository.findAll().size();
        // set the field null
        mArousalMaterial.setContentAmount(null);

        // Create the MArousalMaterial, which fails.
        MArousalMaterialDTO mArousalMaterialDTO = mArousalMaterialMapper.toDto(mArousalMaterial);

        restMArousalMaterialMockMvc.perform(post("/api/m-arousal-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalMaterialDTO)))
            .andExpect(status().isBadRequest());

        List<MArousalMaterial> mArousalMaterialList = mArousalMaterialRepository.findAll();
        assertThat(mArousalMaterialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterials() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList
        restMArousalMaterialMockMvc.perform(get("/api/m-arousal-materials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mArousalMaterial.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)))
            .andExpect(jsonPath("$.[*].mainActionLevel").value(hasItem(DEFAULT_MAIN_ACTION_LEVEL)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMArousalMaterial() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get the mArousalMaterial
        restMArousalMaterialMockMvc.perform(get("/api/m-arousal-materials/{id}", mArousalMaterial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mArousalMaterial.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT))
            .andExpect(jsonPath("$.mainActionLevel").value(DEFAULT_MAIN_ACTION_LEVEL))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where groupId equals to DEFAULT_GROUP_ID
        defaultMArousalMaterialShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mArousalMaterialList where groupId equals to UPDATED_GROUP_ID
        defaultMArousalMaterialShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMArousalMaterialShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mArousalMaterialList where groupId equals to UPDATED_GROUP_ID
        defaultMArousalMaterialShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where groupId is not null
        defaultMArousalMaterialShouldBeFound("groupId.specified=true");

        // Get all the mArousalMaterialList where groupId is null
        defaultMArousalMaterialShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMArousalMaterialShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mArousalMaterialList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMArousalMaterialShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMArousalMaterialShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mArousalMaterialList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMArousalMaterialShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMArousalMaterialShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mArousalMaterialList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMArousalMaterialShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMArousalMaterialShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mArousalMaterialList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMArousalMaterialShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentType is not null
        defaultMArousalMaterialShouldBeFound("contentType.specified=true");

        // Get all the mArousalMaterialList where contentType is null
        defaultMArousalMaterialShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMArousalMaterialShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mArousalMaterialList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMArousalMaterialShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMArousalMaterialShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mArousalMaterialList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMArousalMaterialShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentId equals to DEFAULT_CONTENT_ID
        defaultMArousalMaterialShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mArousalMaterialList where contentId equals to UPDATED_CONTENT_ID
        defaultMArousalMaterialShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMArousalMaterialShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mArousalMaterialList where contentId equals to UPDATED_CONTENT_ID
        defaultMArousalMaterialShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentId is not null
        defaultMArousalMaterialShouldBeFound("contentId.specified=true");

        // Get all the mArousalMaterialList where contentId is null
        defaultMArousalMaterialShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMArousalMaterialShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mArousalMaterialList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMArousalMaterialShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMArousalMaterialShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mArousalMaterialList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMArousalMaterialShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMArousalMaterialShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mArousalMaterialList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMArousalMaterialShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMArousalMaterialShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mArousalMaterialList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMArousalMaterialShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentAmount is not null
        defaultMArousalMaterialShouldBeFound("contentAmount.specified=true");

        // Get all the mArousalMaterialList where contentAmount is null
        defaultMArousalMaterialShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMArousalMaterialShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mArousalMaterialList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMArousalMaterialShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMArousalMaterialShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mArousalMaterialList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMArousalMaterialShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMArousalMaterialsByMainActionLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where mainActionLevel equals to DEFAULT_MAIN_ACTION_LEVEL
        defaultMArousalMaterialShouldBeFound("mainActionLevel.equals=" + DEFAULT_MAIN_ACTION_LEVEL);

        // Get all the mArousalMaterialList where mainActionLevel equals to UPDATED_MAIN_ACTION_LEVEL
        defaultMArousalMaterialShouldNotBeFound("mainActionLevel.equals=" + UPDATED_MAIN_ACTION_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByMainActionLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where mainActionLevel in DEFAULT_MAIN_ACTION_LEVEL or UPDATED_MAIN_ACTION_LEVEL
        defaultMArousalMaterialShouldBeFound("mainActionLevel.in=" + DEFAULT_MAIN_ACTION_LEVEL + "," + UPDATED_MAIN_ACTION_LEVEL);

        // Get all the mArousalMaterialList where mainActionLevel equals to UPDATED_MAIN_ACTION_LEVEL
        defaultMArousalMaterialShouldNotBeFound("mainActionLevel.in=" + UPDATED_MAIN_ACTION_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByMainActionLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where mainActionLevel is not null
        defaultMArousalMaterialShouldBeFound("mainActionLevel.specified=true");

        // Get all the mArousalMaterialList where mainActionLevel is null
        defaultMArousalMaterialShouldNotBeFound("mainActionLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByMainActionLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where mainActionLevel greater than or equals to DEFAULT_MAIN_ACTION_LEVEL
        defaultMArousalMaterialShouldBeFound("mainActionLevel.greaterOrEqualThan=" + DEFAULT_MAIN_ACTION_LEVEL);

        // Get all the mArousalMaterialList where mainActionLevel greater than or equals to UPDATED_MAIN_ACTION_LEVEL
        defaultMArousalMaterialShouldNotBeFound("mainActionLevel.greaterOrEqualThan=" + UPDATED_MAIN_ACTION_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMArousalMaterialsByMainActionLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        // Get all the mArousalMaterialList where mainActionLevel less than or equals to DEFAULT_MAIN_ACTION_LEVEL
        defaultMArousalMaterialShouldNotBeFound("mainActionLevel.lessThan=" + DEFAULT_MAIN_ACTION_LEVEL);

        // Get all the mArousalMaterialList where mainActionLevel less than or equals to UPDATED_MAIN_ACTION_LEVEL
        defaultMArousalMaterialShouldBeFound("mainActionLevel.lessThan=" + UPDATED_MAIN_ACTION_LEVEL);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMArousalMaterialShouldBeFound(String filter) throws Exception {
        restMArousalMaterialMockMvc.perform(get("/api/m-arousal-materials?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mArousalMaterial.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)))
            .andExpect(jsonPath("$.[*].mainActionLevel").value(hasItem(DEFAULT_MAIN_ACTION_LEVEL)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMArousalMaterialMockMvc.perform(get("/api/m-arousal-materials/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMArousalMaterialShouldNotBeFound(String filter) throws Exception {
        restMArousalMaterialMockMvc.perform(get("/api/m-arousal-materials?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMArousalMaterialMockMvc.perform(get("/api/m-arousal-materials/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMArousalMaterial() throws Exception {
        // Get the mArousalMaterial
        restMArousalMaterialMockMvc.perform(get("/api/m-arousal-materials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMArousalMaterial() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        int databaseSizeBeforeUpdate = mArousalMaterialRepository.findAll().size();

        // Update the mArousalMaterial
        MArousalMaterial updatedMArousalMaterial = mArousalMaterialRepository.findById(mArousalMaterial.getId()).get();
        // Disconnect from session so that the updates on updatedMArousalMaterial are not directly saved in db
        em.detach(updatedMArousalMaterial);
        updatedMArousalMaterial
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT)
            .mainActionLevel(UPDATED_MAIN_ACTION_LEVEL)
            .description(UPDATED_DESCRIPTION);
        MArousalMaterialDTO mArousalMaterialDTO = mArousalMaterialMapper.toDto(updatedMArousalMaterial);

        restMArousalMaterialMockMvc.perform(put("/api/m-arousal-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalMaterialDTO)))
            .andExpect(status().isOk());

        // Validate the MArousalMaterial in the database
        List<MArousalMaterial> mArousalMaterialList = mArousalMaterialRepository.findAll();
        assertThat(mArousalMaterialList).hasSize(databaseSizeBeforeUpdate);
        MArousalMaterial testMArousalMaterial = mArousalMaterialList.get(mArousalMaterialList.size() - 1);
        assertThat(testMArousalMaterial.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMArousalMaterial.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMArousalMaterial.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMArousalMaterial.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
        assertThat(testMArousalMaterial.getMainActionLevel()).isEqualTo(UPDATED_MAIN_ACTION_LEVEL);
        assertThat(testMArousalMaterial.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMArousalMaterial() throws Exception {
        int databaseSizeBeforeUpdate = mArousalMaterialRepository.findAll().size();

        // Create the MArousalMaterial
        MArousalMaterialDTO mArousalMaterialDTO = mArousalMaterialMapper.toDto(mArousalMaterial);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMArousalMaterialMockMvc.perform(put("/api/m-arousal-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalMaterialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MArousalMaterial in the database
        List<MArousalMaterial> mArousalMaterialList = mArousalMaterialRepository.findAll();
        assertThat(mArousalMaterialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMArousalMaterial() throws Exception {
        // Initialize the database
        mArousalMaterialRepository.saveAndFlush(mArousalMaterial);

        int databaseSizeBeforeDelete = mArousalMaterialRepository.findAll().size();

        // Delete the mArousalMaterial
        restMArousalMaterialMockMvc.perform(delete("/api/m-arousal-materials/{id}", mArousalMaterial.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MArousalMaterial> mArousalMaterialList = mArousalMaterialRepository.findAll();
        assertThat(mArousalMaterialList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MArousalMaterial.class);
        MArousalMaterial mArousalMaterial1 = new MArousalMaterial();
        mArousalMaterial1.setId(1L);
        MArousalMaterial mArousalMaterial2 = new MArousalMaterial();
        mArousalMaterial2.setId(mArousalMaterial1.getId());
        assertThat(mArousalMaterial1).isEqualTo(mArousalMaterial2);
        mArousalMaterial2.setId(2L);
        assertThat(mArousalMaterial1).isNotEqualTo(mArousalMaterial2);
        mArousalMaterial1.setId(null);
        assertThat(mArousalMaterial1).isNotEqualTo(mArousalMaterial2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MArousalMaterialDTO.class);
        MArousalMaterialDTO mArousalMaterialDTO1 = new MArousalMaterialDTO();
        mArousalMaterialDTO1.setId(1L);
        MArousalMaterialDTO mArousalMaterialDTO2 = new MArousalMaterialDTO();
        assertThat(mArousalMaterialDTO1).isNotEqualTo(mArousalMaterialDTO2);
        mArousalMaterialDTO2.setId(mArousalMaterialDTO1.getId());
        assertThat(mArousalMaterialDTO1).isEqualTo(mArousalMaterialDTO2);
        mArousalMaterialDTO2.setId(2L);
        assertThat(mArousalMaterialDTO1).isNotEqualTo(mArousalMaterialDTO2);
        mArousalMaterialDTO1.setId(null);
        assertThat(mArousalMaterialDTO1).isNotEqualTo(mArousalMaterialDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mArousalMaterialMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mArousalMaterialMapper.fromId(null)).isNull();
    }
}
