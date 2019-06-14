package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MUniformUp;
import io.shm.tsubasa.repository.MUniformUpRepository;
import io.shm.tsubasa.service.MUniformUpService;
import io.shm.tsubasa.service.dto.MUniformUpDTO;
import io.shm.tsubasa.service.mapper.MUniformUpMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MUniformUpCriteria;
import io.shm.tsubasa.service.MUniformUpQueryService;

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
 * Integration tests for the {@Link MUniformUpResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MUniformUpResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MENU_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MENU_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MODEL_ID = 1;
    private static final Integer UPDATED_MODEL_ID = 2;

    private static final String DEFAULT_THUMBNAIL_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_UNIFORM_TYPE = 1;
    private static final Integer UPDATED_UNIFORM_TYPE = 2;

    private static final Integer DEFAULT_IS_DEFAULT = 1;
    private static final Integer UPDATED_IS_DEFAULT = 2;

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MUniformUpRepository mUniformUpRepository;

    @Autowired
    private MUniformUpMapper mUniformUpMapper;

    @Autowired
    private MUniformUpService mUniformUpService;

    @Autowired
    private MUniformUpQueryService mUniformUpQueryService;

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

    private MockMvc restMUniformUpMockMvc;

    private MUniformUp mUniformUp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MUniformUpResource mUniformUpResource = new MUniformUpResource(mUniformUpService, mUniformUpQueryService);
        this.restMUniformUpMockMvc = MockMvcBuilders.standaloneSetup(mUniformUpResource)
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
    public static MUniformUp createEntity(EntityManager em) {
        MUniformUp mUniformUp = new MUniformUp()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .menuName(DEFAULT_MENU_NAME)
            .modelId(DEFAULT_MODEL_ID)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME)
            .uniformType(DEFAULT_UNIFORM_TYPE)
            .isDefault(DEFAULT_IS_DEFAULT)
            .orderNum(DEFAULT_ORDER_NUM)
            .description(DEFAULT_DESCRIPTION);
        return mUniformUp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MUniformUp createUpdatedEntity(EntityManager em) {
        MUniformUp mUniformUp = new MUniformUp()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .menuName(UPDATED_MENU_NAME)
            .modelId(UPDATED_MODEL_ID)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .uniformType(UPDATED_UNIFORM_TYPE)
            .isDefault(UPDATED_IS_DEFAULT)
            .orderNum(UPDATED_ORDER_NUM)
            .description(UPDATED_DESCRIPTION);
        return mUniformUp;
    }

    @BeforeEach
    public void initTest() {
        mUniformUp = createEntity(em);
    }

    @Test
    @Transactional
    public void createMUniformUp() throws Exception {
        int databaseSizeBeforeCreate = mUniformUpRepository.findAll().size();

        // Create the MUniformUp
        MUniformUpDTO mUniformUpDTO = mUniformUpMapper.toDto(mUniformUp);
        restMUniformUpMockMvc.perform(post("/api/m-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformUpDTO)))
            .andExpect(status().isCreated());

        // Validate the MUniformUp in the database
        List<MUniformUp> mUniformUpList = mUniformUpRepository.findAll();
        assertThat(mUniformUpList).hasSize(databaseSizeBeforeCreate + 1);
        MUniformUp testMUniformUp = mUniformUpList.get(mUniformUpList.size() - 1);
        assertThat(testMUniformUp.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMUniformUp.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMUniformUp.getMenuName()).isEqualTo(DEFAULT_MENU_NAME);
        assertThat(testMUniformUp.getModelId()).isEqualTo(DEFAULT_MODEL_ID);
        assertThat(testMUniformUp.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
        assertThat(testMUniformUp.getUniformType()).isEqualTo(DEFAULT_UNIFORM_TYPE);
        assertThat(testMUniformUp.getIsDefault()).isEqualTo(DEFAULT_IS_DEFAULT);
        assertThat(testMUniformUp.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMUniformUp.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMUniformUpWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mUniformUpRepository.findAll().size();

        // Create the MUniformUp with an existing ID
        mUniformUp.setId(1L);
        MUniformUpDTO mUniformUpDTO = mUniformUpMapper.toDto(mUniformUp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMUniformUpMockMvc.perform(post("/api/m-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformUpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUniformUp in the database
        List<MUniformUp> mUniformUpList = mUniformUpRepository.findAll();
        assertThat(mUniformUpList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkModelIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformUpRepository.findAll().size();
        // set the field null
        mUniformUp.setModelId(null);

        // Create the MUniformUp, which fails.
        MUniformUpDTO mUniformUpDTO = mUniformUpMapper.toDto(mUniformUp);

        restMUniformUpMockMvc.perform(post("/api/m-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformUp> mUniformUpList = mUniformUpRepository.findAll();
        assertThat(mUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformUpRepository.findAll().size();
        // set the field null
        mUniformUp.setUniformType(null);

        // Create the MUniformUp, which fails.
        MUniformUpDTO mUniformUpDTO = mUniformUpMapper.toDto(mUniformUp);

        restMUniformUpMockMvc.perform(post("/api/m-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformUp> mUniformUpList = mUniformUpRepository.findAll();
        assertThat(mUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDefaultIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformUpRepository.findAll().size();
        // set the field null
        mUniformUp.setIsDefault(null);

        // Create the MUniformUp, which fails.
        MUniformUpDTO mUniformUpDTO = mUniformUpMapper.toDto(mUniformUp);

        restMUniformUpMockMvc.perform(post("/api/m-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformUp> mUniformUpList = mUniformUpRepository.findAll();
        assertThat(mUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformUpRepository.findAll().size();
        // set the field null
        mUniformUp.setOrderNum(null);

        // Create the MUniformUp, which fails.
        MUniformUpDTO mUniformUpDTO = mUniformUpMapper.toDto(mUniformUp);

        restMUniformUpMockMvc.perform(post("/api/m-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformUp> mUniformUpList = mUniformUpRepository.findAll();
        assertThat(mUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMUniformUps() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList
        restMUniformUpMockMvc.perform(get("/api/m-uniform-ups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUniformUp.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].menuName").value(hasItem(DEFAULT_MENU_NAME.toString())))
            .andExpect(jsonPath("$.[*].modelId").value(hasItem(DEFAULT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].uniformType").value(hasItem(DEFAULT_UNIFORM_TYPE)))
            .andExpect(jsonPath("$.[*].isDefault").value(hasItem(DEFAULT_IS_DEFAULT)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMUniformUp() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get the mUniformUp
        restMUniformUpMockMvc.perform(get("/api/m-uniform-ups/{id}", mUniformUp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mUniformUp.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.menuName").value(DEFAULT_MENU_NAME.toString()))
            .andExpect(jsonPath("$.modelId").value(DEFAULT_MODEL_ID))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.uniformType").value(DEFAULT_UNIFORM_TYPE))
            .andExpect(jsonPath("$.isDefault").value(DEFAULT_IS_DEFAULT))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByModelIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where modelId equals to DEFAULT_MODEL_ID
        defaultMUniformUpShouldBeFound("modelId.equals=" + DEFAULT_MODEL_ID);

        // Get all the mUniformUpList where modelId equals to UPDATED_MODEL_ID
        defaultMUniformUpShouldNotBeFound("modelId.equals=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByModelIdIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where modelId in DEFAULT_MODEL_ID or UPDATED_MODEL_ID
        defaultMUniformUpShouldBeFound("modelId.in=" + DEFAULT_MODEL_ID + "," + UPDATED_MODEL_ID);

        // Get all the mUniformUpList where modelId equals to UPDATED_MODEL_ID
        defaultMUniformUpShouldNotBeFound("modelId.in=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByModelIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where modelId is not null
        defaultMUniformUpShouldBeFound("modelId.specified=true");

        // Get all the mUniformUpList where modelId is null
        defaultMUniformUpShouldNotBeFound("modelId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByModelIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where modelId greater than or equals to DEFAULT_MODEL_ID
        defaultMUniformUpShouldBeFound("modelId.greaterOrEqualThan=" + DEFAULT_MODEL_ID);

        // Get all the mUniformUpList where modelId greater than or equals to UPDATED_MODEL_ID
        defaultMUniformUpShouldNotBeFound("modelId.greaterOrEqualThan=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByModelIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where modelId less than or equals to DEFAULT_MODEL_ID
        defaultMUniformUpShouldNotBeFound("modelId.lessThan=" + DEFAULT_MODEL_ID);

        // Get all the mUniformUpList where modelId less than or equals to UPDATED_MODEL_ID
        defaultMUniformUpShouldBeFound("modelId.lessThan=" + UPDATED_MODEL_ID);
    }


    @Test
    @Transactional
    public void getAllMUniformUpsByUniformTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where uniformType equals to DEFAULT_UNIFORM_TYPE
        defaultMUniformUpShouldBeFound("uniformType.equals=" + DEFAULT_UNIFORM_TYPE);

        // Get all the mUniformUpList where uniformType equals to UPDATED_UNIFORM_TYPE
        defaultMUniformUpShouldNotBeFound("uniformType.equals=" + UPDATED_UNIFORM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByUniformTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where uniformType in DEFAULT_UNIFORM_TYPE or UPDATED_UNIFORM_TYPE
        defaultMUniformUpShouldBeFound("uniformType.in=" + DEFAULT_UNIFORM_TYPE + "," + UPDATED_UNIFORM_TYPE);

        // Get all the mUniformUpList where uniformType equals to UPDATED_UNIFORM_TYPE
        defaultMUniformUpShouldNotBeFound("uniformType.in=" + UPDATED_UNIFORM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByUniformTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where uniformType is not null
        defaultMUniformUpShouldBeFound("uniformType.specified=true");

        // Get all the mUniformUpList where uniformType is null
        defaultMUniformUpShouldNotBeFound("uniformType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByUniformTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where uniformType greater than or equals to DEFAULT_UNIFORM_TYPE
        defaultMUniformUpShouldBeFound("uniformType.greaterOrEqualThan=" + DEFAULT_UNIFORM_TYPE);

        // Get all the mUniformUpList where uniformType greater than or equals to UPDATED_UNIFORM_TYPE
        defaultMUniformUpShouldNotBeFound("uniformType.greaterOrEqualThan=" + UPDATED_UNIFORM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByUniformTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where uniformType less than or equals to DEFAULT_UNIFORM_TYPE
        defaultMUniformUpShouldNotBeFound("uniformType.lessThan=" + DEFAULT_UNIFORM_TYPE);

        // Get all the mUniformUpList where uniformType less than or equals to UPDATED_UNIFORM_TYPE
        defaultMUniformUpShouldBeFound("uniformType.lessThan=" + UPDATED_UNIFORM_TYPE);
    }


    @Test
    @Transactional
    public void getAllMUniformUpsByIsDefaultIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where isDefault equals to DEFAULT_IS_DEFAULT
        defaultMUniformUpShouldBeFound("isDefault.equals=" + DEFAULT_IS_DEFAULT);

        // Get all the mUniformUpList where isDefault equals to UPDATED_IS_DEFAULT
        defaultMUniformUpShouldNotBeFound("isDefault.equals=" + UPDATED_IS_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByIsDefaultIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where isDefault in DEFAULT_IS_DEFAULT or UPDATED_IS_DEFAULT
        defaultMUniformUpShouldBeFound("isDefault.in=" + DEFAULT_IS_DEFAULT + "," + UPDATED_IS_DEFAULT);

        // Get all the mUniformUpList where isDefault equals to UPDATED_IS_DEFAULT
        defaultMUniformUpShouldNotBeFound("isDefault.in=" + UPDATED_IS_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByIsDefaultIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where isDefault is not null
        defaultMUniformUpShouldBeFound("isDefault.specified=true");

        // Get all the mUniformUpList where isDefault is null
        defaultMUniformUpShouldNotBeFound("isDefault.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByIsDefaultIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where isDefault greater than or equals to DEFAULT_IS_DEFAULT
        defaultMUniformUpShouldBeFound("isDefault.greaterOrEqualThan=" + DEFAULT_IS_DEFAULT);

        // Get all the mUniformUpList where isDefault greater than or equals to UPDATED_IS_DEFAULT
        defaultMUniformUpShouldNotBeFound("isDefault.greaterOrEqualThan=" + UPDATED_IS_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByIsDefaultIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where isDefault less than or equals to DEFAULT_IS_DEFAULT
        defaultMUniformUpShouldNotBeFound("isDefault.lessThan=" + DEFAULT_IS_DEFAULT);

        // Get all the mUniformUpList where isDefault less than or equals to UPDATED_IS_DEFAULT
        defaultMUniformUpShouldBeFound("isDefault.lessThan=" + UPDATED_IS_DEFAULT);
    }


    @Test
    @Transactional
    public void getAllMUniformUpsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMUniformUpShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mUniformUpList where orderNum equals to UPDATED_ORDER_NUM
        defaultMUniformUpShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMUniformUpShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mUniformUpList where orderNum equals to UPDATED_ORDER_NUM
        defaultMUniformUpShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where orderNum is not null
        defaultMUniformUpShouldBeFound("orderNum.specified=true");

        // Get all the mUniformUpList where orderNum is null
        defaultMUniformUpShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMUniformUpShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mUniformUpList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMUniformUpShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMUniformUpsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        // Get all the mUniformUpList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMUniformUpShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mUniformUpList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMUniformUpShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMUniformUpShouldBeFound(String filter) throws Exception {
        restMUniformUpMockMvc.perform(get("/api/m-uniform-ups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUniformUp.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].menuName").value(hasItem(DEFAULT_MENU_NAME.toString())))
            .andExpect(jsonPath("$.[*].modelId").value(hasItem(DEFAULT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].uniformType").value(hasItem(DEFAULT_UNIFORM_TYPE)))
            .andExpect(jsonPath("$.[*].isDefault").value(hasItem(DEFAULT_IS_DEFAULT)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMUniformUpMockMvc.perform(get("/api/m-uniform-ups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMUniformUpShouldNotBeFound(String filter) throws Exception {
        restMUniformUpMockMvc.perform(get("/api/m-uniform-ups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMUniformUpMockMvc.perform(get("/api/m-uniform-ups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMUniformUp() throws Exception {
        // Get the mUniformUp
        restMUniformUpMockMvc.perform(get("/api/m-uniform-ups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMUniformUp() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        int databaseSizeBeforeUpdate = mUniformUpRepository.findAll().size();

        // Update the mUniformUp
        MUniformUp updatedMUniformUp = mUniformUpRepository.findById(mUniformUp.getId()).get();
        // Disconnect from session so that the updates on updatedMUniformUp are not directly saved in db
        em.detach(updatedMUniformUp);
        updatedMUniformUp
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .menuName(UPDATED_MENU_NAME)
            .modelId(UPDATED_MODEL_ID)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .uniformType(UPDATED_UNIFORM_TYPE)
            .isDefault(UPDATED_IS_DEFAULT)
            .orderNum(UPDATED_ORDER_NUM)
            .description(UPDATED_DESCRIPTION);
        MUniformUpDTO mUniformUpDTO = mUniformUpMapper.toDto(updatedMUniformUp);

        restMUniformUpMockMvc.perform(put("/api/m-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformUpDTO)))
            .andExpect(status().isOk());

        // Validate the MUniformUp in the database
        List<MUniformUp> mUniformUpList = mUniformUpRepository.findAll();
        assertThat(mUniformUpList).hasSize(databaseSizeBeforeUpdate);
        MUniformUp testMUniformUp = mUniformUpList.get(mUniformUpList.size() - 1);
        assertThat(testMUniformUp.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMUniformUp.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMUniformUp.getMenuName()).isEqualTo(UPDATED_MENU_NAME);
        assertThat(testMUniformUp.getModelId()).isEqualTo(UPDATED_MODEL_ID);
        assertThat(testMUniformUp.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
        assertThat(testMUniformUp.getUniformType()).isEqualTo(UPDATED_UNIFORM_TYPE);
        assertThat(testMUniformUp.getIsDefault()).isEqualTo(UPDATED_IS_DEFAULT);
        assertThat(testMUniformUp.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMUniformUp.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMUniformUp() throws Exception {
        int databaseSizeBeforeUpdate = mUniformUpRepository.findAll().size();

        // Create the MUniformUp
        MUniformUpDTO mUniformUpDTO = mUniformUpMapper.toDto(mUniformUp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMUniformUpMockMvc.perform(put("/api/m-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformUpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUniformUp in the database
        List<MUniformUp> mUniformUpList = mUniformUpRepository.findAll();
        assertThat(mUniformUpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMUniformUp() throws Exception {
        // Initialize the database
        mUniformUpRepository.saveAndFlush(mUniformUp);

        int databaseSizeBeforeDelete = mUniformUpRepository.findAll().size();

        // Delete the mUniformUp
        restMUniformUpMockMvc.perform(delete("/api/m-uniform-ups/{id}", mUniformUp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MUniformUp> mUniformUpList = mUniformUpRepository.findAll();
        assertThat(mUniformUpList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUniformUp.class);
        MUniformUp mUniformUp1 = new MUniformUp();
        mUniformUp1.setId(1L);
        MUniformUp mUniformUp2 = new MUniformUp();
        mUniformUp2.setId(mUniformUp1.getId());
        assertThat(mUniformUp1).isEqualTo(mUniformUp2);
        mUniformUp2.setId(2L);
        assertThat(mUniformUp1).isNotEqualTo(mUniformUp2);
        mUniformUp1.setId(null);
        assertThat(mUniformUp1).isNotEqualTo(mUniformUp2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUniformUpDTO.class);
        MUniformUpDTO mUniformUpDTO1 = new MUniformUpDTO();
        mUniformUpDTO1.setId(1L);
        MUniformUpDTO mUniformUpDTO2 = new MUniformUpDTO();
        assertThat(mUniformUpDTO1).isNotEqualTo(mUniformUpDTO2);
        mUniformUpDTO2.setId(mUniformUpDTO1.getId());
        assertThat(mUniformUpDTO1).isEqualTo(mUniformUpDTO2);
        mUniformUpDTO2.setId(2L);
        assertThat(mUniformUpDTO1).isNotEqualTo(mUniformUpDTO2);
        mUniformUpDTO1.setId(null);
        assertThat(mUniformUpDTO1).isNotEqualTo(mUniformUpDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mUniformUpMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mUniformUpMapper.fromId(null)).isNull();
    }
}
