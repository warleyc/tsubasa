package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MUniformBottom;
import io.shm.tsubasa.repository.MUniformBottomRepository;
import io.shm.tsubasa.service.MUniformBottomService;
import io.shm.tsubasa.service.dto.MUniformBottomDTO;
import io.shm.tsubasa.service.mapper.MUniformBottomMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MUniformBottomCriteria;
import io.shm.tsubasa.service.MUniformBottomQueryService;

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
 * Integration tests for the {@Link MUniformBottomResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MUniformBottomResourceIT {

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
    private MUniformBottomRepository mUniformBottomRepository;

    @Autowired
    private MUniformBottomMapper mUniformBottomMapper;

    @Autowired
    private MUniformBottomService mUniformBottomService;

    @Autowired
    private MUniformBottomQueryService mUniformBottomQueryService;

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

    private MockMvc restMUniformBottomMockMvc;

    private MUniformBottom mUniformBottom;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MUniformBottomResource mUniformBottomResource = new MUniformBottomResource(mUniformBottomService, mUniformBottomQueryService);
        this.restMUniformBottomMockMvc = MockMvcBuilders.standaloneSetup(mUniformBottomResource)
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
    public static MUniformBottom createEntity(EntityManager em) {
        MUniformBottom mUniformBottom = new MUniformBottom()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .menuName(DEFAULT_MENU_NAME)
            .modelId(DEFAULT_MODEL_ID)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME)
            .uniformType(DEFAULT_UNIFORM_TYPE)
            .isDefault(DEFAULT_IS_DEFAULT)
            .orderNum(DEFAULT_ORDER_NUM)
            .description(DEFAULT_DESCRIPTION);
        return mUniformBottom;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MUniformBottom createUpdatedEntity(EntityManager em) {
        MUniformBottom mUniformBottom = new MUniformBottom()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .menuName(UPDATED_MENU_NAME)
            .modelId(UPDATED_MODEL_ID)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .uniformType(UPDATED_UNIFORM_TYPE)
            .isDefault(UPDATED_IS_DEFAULT)
            .orderNum(UPDATED_ORDER_NUM)
            .description(UPDATED_DESCRIPTION);
        return mUniformBottom;
    }

    @BeforeEach
    public void initTest() {
        mUniformBottom = createEntity(em);
    }

    @Test
    @Transactional
    public void createMUniformBottom() throws Exception {
        int databaseSizeBeforeCreate = mUniformBottomRepository.findAll().size();

        // Create the MUniformBottom
        MUniformBottomDTO mUniformBottomDTO = mUniformBottomMapper.toDto(mUniformBottom);
        restMUniformBottomMockMvc.perform(post("/api/m-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformBottomDTO)))
            .andExpect(status().isCreated());

        // Validate the MUniformBottom in the database
        List<MUniformBottom> mUniformBottomList = mUniformBottomRepository.findAll();
        assertThat(mUniformBottomList).hasSize(databaseSizeBeforeCreate + 1);
        MUniformBottom testMUniformBottom = mUniformBottomList.get(mUniformBottomList.size() - 1);
        assertThat(testMUniformBottom.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMUniformBottom.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMUniformBottom.getMenuName()).isEqualTo(DEFAULT_MENU_NAME);
        assertThat(testMUniformBottom.getModelId()).isEqualTo(DEFAULT_MODEL_ID);
        assertThat(testMUniformBottom.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
        assertThat(testMUniformBottom.getUniformType()).isEqualTo(DEFAULT_UNIFORM_TYPE);
        assertThat(testMUniformBottom.getIsDefault()).isEqualTo(DEFAULT_IS_DEFAULT);
        assertThat(testMUniformBottom.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMUniformBottom.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMUniformBottomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mUniformBottomRepository.findAll().size();

        // Create the MUniformBottom with an existing ID
        mUniformBottom.setId(1L);
        MUniformBottomDTO mUniformBottomDTO = mUniformBottomMapper.toDto(mUniformBottom);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMUniformBottomMockMvc.perform(post("/api/m-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUniformBottom in the database
        List<MUniformBottom> mUniformBottomList = mUniformBottomRepository.findAll();
        assertThat(mUniformBottomList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkModelIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformBottomRepository.findAll().size();
        // set the field null
        mUniformBottom.setModelId(null);

        // Create the MUniformBottom, which fails.
        MUniformBottomDTO mUniformBottomDTO = mUniformBottomMapper.toDto(mUniformBottom);

        restMUniformBottomMockMvc.perform(post("/api/m-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformBottom> mUniformBottomList = mUniformBottomRepository.findAll();
        assertThat(mUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformBottomRepository.findAll().size();
        // set the field null
        mUniformBottom.setUniformType(null);

        // Create the MUniformBottom, which fails.
        MUniformBottomDTO mUniformBottomDTO = mUniformBottomMapper.toDto(mUniformBottom);

        restMUniformBottomMockMvc.perform(post("/api/m-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformBottom> mUniformBottomList = mUniformBottomRepository.findAll();
        assertThat(mUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDefaultIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformBottomRepository.findAll().size();
        // set the field null
        mUniformBottom.setIsDefault(null);

        // Create the MUniformBottom, which fails.
        MUniformBottomDTO mUniformBottomDTO = mUniformBottomMapper.toDto(mUniformBottom);

        restMUniformBottomMockMvc.perform(post("/api/m-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformBottom> mUniformBottomList = mUniformBottomRepository.findAll();
        assertThat(mUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformBottomRepository.findAll().size();
        // set the field null
        mUniformBottom.setOrderNum(null);

        // Create the MUniformBottom, which fails.
        MUniformBottomDTO mUniformBottomDTO = mUniformBottomMapper.toDto(mUniformBottom);

        restMUniformBottomMockMvc.perform(post("/api/m-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformBottom> mUniformBottomList = mUniformBottomRepository.findAll();
        assertThat(mUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMUniformBottoms() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList
        restMUniformBottomMockMvc.perform(get("/api/m-uniform-bottoms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUniformBottom.getId().intValue())))
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
    public void getMUniformBottom() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get the mUniformBottom
        restMUniformBottomMockMvc.perform(get("/api/m-uniform-bottoms/{id}", mUniformBottom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mUniformBottom.getId().intValue()))
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
    public void getAllMUniformBottomsByModelIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where modelId equals to DEFAULT_MODEL_ID
        defaultMUniformBottomShouldBeFound("modelId.equals=" + DEFAULT_MODEL_ID);

        // Get all the mUniformBottomList where modelId equals to UPDATED_MODEL_ID
        defaultMUniformBottomShouldNotBeFound("modelId.equals=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByModelIdIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where modelId in DEFAULT_MODEL_ID or UPDATED_MODEL_ID
        defaultMUniformBottomShouldBeFound("modelId.in=" + DEFAULT_MODEL_ID + "," + UPDATED_MODEL_ID);

        // Get all the mUniformBottomList where modelId equals to UPDATED_MODEL_ID
        defaultMUniformBottomShouldNotBeFound("modelId.in=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByModelIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where modelId is not null
        defaultMUniformBottomShouldBeFound("modelId.specified=true");

        // Get all the mUniformBottomList where modelId is null
        defaultMUniformBottomShouldNotBeFound("modelId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByModelIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where modelId greater than or equals to DEFAULT_MODEL_ID
        defaultMUniformBottomShouldBeFound("modelId.greaterOrEqualThan=" + DEFAULT_MODEL_ID);

        // Get all the mUniformBottomList where modelId greater than or equals to UPDATED_MODEL_ID
        defaultMUniformBottomShouldNotBeFound("modelId.greaterOrEqualThan=" + UPDATED_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByModelIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where modelId less than or equals to DEFAULT_MODEL_ID
        defaultMUniformBottomShouldNotBeFound("modelId.lessThan=" + DEFAULT_MODEL_ID);

        // Get all the mUniformBottomList where modelId less than or equals to UPDATED_MODEL_ID
        defaultMUniformBottomShouldBeFound("modelId.lessThan=" + UPDATED_MODEL_ID);
    }


    @Test
    @Transactional
    public void getAllMUniformBottomsByUniformTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where uniformType equals to DEFAULT_UNIFORM_TYPE
        defaultMUniformBottomShouldBeFound("uniformType.equals=" + DEFAULT_UNIFORM_TYPE);

        // Get all the mUniformBottomList where uniformType equals to UPDATED_UNIFORM_TYPE
        defaultMUniformBottomShouldNotBeFound("uniformType.equals=" + UPDATED_UNIFORM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByUniformTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where uniformType in DEFAULT_UNIFORM_TYPE or UPDATED_UNIFORM_TYPE
        defaultMUniformBottomShouldBeFound("uniformType.in=" + DEFAULT_UNIFORM_TYPE + "," + UPDATED_UNIFORM_TYPE);

        // Get all the mUniformBottomList where uniformType equals to UPDATED_UNIFORM_TYPE
        defaultMUniformBottomShouldNotBeFound("uniformType.in=" + UPDATED_UNIFORM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByUniformTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where uniformType is not null
        defaultMUniformBottomShouldBeFound("uniformType.specified=true");

        // Get all the mUniformBottomList where uniformType is null
        defaultMUniformBottomShouldNotBeFound("uniformType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByUniformTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where uniformType greater than or equals to DEFAULT_UNIFORM_TYPE
        defaultMUniformBottomShouldBeFound("uniformType.greaterOrEqualThan=" + DEFAULT_UNIFORM_TYPE);

        // Get all the mUniformBottomList where uniformType greater than or equals to UPDATED_UNIFORM_TYPE
        defaultMUniformBottomShouldNotBeFound("uniformType.greaterOrEqualThan=" + UPDATED_UNIFORM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByUniformTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where uniformType less than or equals to DEFAULT_UNIFORM_TYPE
        defaultMUniformBottomShouldNotBeFound("uniformType.lessThan=" + DEFAULT_UNIFORM_TYPE);

        // Get all the mUniformBottomList where uniformType less than or equals to UPDATED_UNIFORM_TYPE
        defaultMUniformBottomShouldBeFound("uniformType.lessThan=" + UPDATED_UNIFORM_TYPE);
    }


    @Test
    @Transactional
    public void getAllMUniformBottomsByIsDefaultIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where isDefault equals to DEFAULT_IS_DEFAULT
        defaultMUniformBottomShouldBeFound("isDefault.equals=" + DEFAULT_IS_DEFAULT);

        // Get all the mUniformBottomList where isDefault equals to UPDATED_IS_DEFAULT
        defaultMUniformBottomShouldNotBeFound("isDefault.equals=" + UPDATED_IS_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByIsDefaultIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where isDefault in DEFAULT_IS_DEFAULT or UPDATED_IS_DEFAULT
        defaultMUniformBottomShouldBeFound("isDefault.in=" + DEFAULT_IS_DEFAULT + "," + UPDATED_IS_DEFAULT);

        // Get all the mUniformBottomList where isDefault equals to UPDATED_IS_DEFAULT
        defaultMUniformBottomShouldNotBeFound("isDefault.in=" + UPDATED_IS_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByIsDefaultIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where isDefault is not null
        defaultMUniformBottomShouldBeFound("isDefault.specified=true");

        // Get all the mUniformBottomList where isDefault is null
        defaultMUniformBottomShouldNotBeFound("isDefault.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByIsDefaultIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where isDefault greater than or equals to DEFAULT_IS_DEFAULT
        defaultMUniformBottomShouldBeFound("isDefault.greaterOrEqualThan=" + DEFAULT_IS_DEFAULT);

        // Get all the mUniformBottomList where isDefault greater than or equals to UPDATED_IS_DEFAULT
        defaultMUniformBottomShouldNotBeFound("isDefault.greaterOrEqualThan=" + UPDATED_IS_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByIsDefaultIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where isDefault less than or equals to DEFAULT_IS_DEFAULT
        defaultMUniformBottomShouldNotBeFound("isDefault.lessThan=" + DEFAULT_IS_DEFAULT);

        // Get all the mUniformBottomList where isDefault less than or equals to UPDATED_IS_DEFAULT
        defaultMUniformBottomShouldBeFound("isDefault.lessThan=" + UPDATED_IS_DEFAULT);
    }


    @Test
    @Transactional
    public void getAllMUniformBottomsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMUniformBottomShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mUniformBottomList where orderNum equals to UPDATED_ORDER_NUM
        defaultMUniformBottomShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMUniformBottomShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mUniformBottomList where orderNum equals to UPDATED_ORDER_NUM
        defaultMUniformBottomShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where orderNum is not null
        defaultMUniformBottomShouldBeFound("orderNum.specified=true");

        // Get all the mUniformBottomList where orderNum is null
        defaultMUniformBottomShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMUniformBottomShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mUniformBottomList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMUniformBottomShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMUniformBottomsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        // Get all the mUniformBottomList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMUniformBottomShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mUniformBottomList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMUniformBottomShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMUniformBottomShouldBeFound(String filter) throws Exception {
        restMUniformBottomMockMvc.perform(get("/api/m-uniform-bottoms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUniformBottom.getId().intValue())))
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
        restMUniformBottomMockMvc.perform(get("/api/m-uniform-bottoms/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMUniformBottomShouldNotBeFound(String filter) throws Exception {
        restMUniformBottomMockMvc.perform(get("/api/m-uniform-bottoms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMUniformBottomMockMvc.perform(get("/api/m-uniform-bottoms/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMUniformBottom() throws Exception {
        // Get the mUniformBottom
        restMUniformBottomMockMvc.perform(get("/api/m-uniform-bottoms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMUniformBottom() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        int databaseSizeBeforeUpdate = mUniformBottomRepository.findAll().size();

        // Update the mUniformBottom
        MUniformBottom updatedMUniformBottom = mUniformBottomRepository.findById(mUniformBottom.getId()).get();
        // Disconnect from session so that the updates on updatedMUniformBottom are not directly saved in db
        em.detach(updatedMUniformBottom);
        updatedMUniformBottom
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .menuName(UPDATED_MENU_NAME)
            .modelId(UPDATED_MODEL_ID)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .uniformType(UPDATED_UNIFORM_TYPE)
            .isDefault(UPDATED_IS_DEFAULT)
            .orderNum(UPDATED_ORDER_NUM)
            .description(UPDATED_DESCRIPTION);
        MUniformBottomDTO mUniformBottomDTO = mUniformBottomMapper.toDto(updatedMUniformBottom);

        restMUniformBottomMockMvc.perform(put("/api/m-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformBottomDTO)))
            .andExpect(status().isOk());

        // Validate the MUniformBottom in the database
        List<MUniformBottom> mUniformBottomList = mUniformBottomRepository.findAll();
        assertThat(mUniformBottomList).hasSize(databaseSizeBeforeUpdate);
        MUniformBottom testMUniformBottom = mUniformBottomList.get(mUniformBottomList.size() - 1);
        assertThat(testMUniformBottom.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMUniformBottom.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMUniformBottom.getMenuName()).isEqualTo(UPDATED_MENU_NAME);
        assertThat(testMUniformBottom.getModelId()).isEqualTo(UPDATED_MODEL_ID);
        assertThat(testMUniformBottom.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
        assertThat(testMUniformBottom.getUniformType()).isEqualTo(UPDATED_UNIFORM_TYPE);
        assertThat(testMUniformBottom.getIsDefault()).isEqualTo(UPDATED_IS_DEFAULT);
        assertThat(testMUniformBottom.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMUniformBottom.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMUniformBottom() throws Exception {
        int databaseSizeBeforeUpdate = mUniformBottomRepository.findAll().size();

        // Create the MUniformBottom
        MUniformBottomDTO mUniformBottomDTO = mUniformBottomMapper.toDto(mUniformBottom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMUniformBottomMockMvc.perform(put("/api/m-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUniformBottom in the database
        List<MUniformBottom> mUniformBottomList = mUniformBottomRepository.findAll();
        assertThat(mUniformBottomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMUniformBottom() throws Exception {
        // Initialize the database
        mUniformBottomRepository.saveAndFlush(mUniformBottom);

        int databaseSizeBeforeDelete = mUniformBottomRepository.findAll().size();

        // Delete the mUniformBottom
        restMUniformBottomMockMvc.perform(delete("/api/m-uniform-bottoms/{id}", mUniformBottom.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MUniformBottom> mUniformBottomList = mUniformBottomRepository.findAll();
        assertThat(mUniformBottomList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUniformBottom.class);
        MUniformBottom mUniformBottom1 = new MUniformBottom();
        mUniformBottom1.setId(1L);
        MUniformBottom mUniformBottom2 = new MUniformBottom();
        mUniformBottom2.setId(mUniformBottom1.getId());
        assertThat(mUniformBottom1).isEqualTo(mUniformBottom2);
        mUniformBottom2.setId(2L);
        assertThat(mUniformBottom1).isNotEqualTo(mUniformBottom2);
        mUniformBottom1.setId(null);
        assertThat(mUniformBottom1).isNotEqualTo(mUniformBottom2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUniformBottomDTO.class);
        MUniformBottomDTO mUniformBottomDTO1 = new MUniformBottomDTO();
        mUniformBottomDTO1.setId(1L);
        MUniformBottomDTO mUniformBottomDTO2 = new MUniformBottomDTO();
        assertThat(mUniformBottomDTO1).isNotEqualTo(mUniformBottomDTO2);
        mUniformBottomDTO2.setId(mUniformBottomDTO1.getId());
        assertThat(mUniformBottomDTO1).isEqualTo(mUniformBottomDTO2);
        mUniformBottomDTO2.setId(2L);
        assertThat(mUniformBottomDTO1).isNotEqualTo(mUniformBottomDTO2);
        mUniformBottomDTO1.setId(null);
        assertThat(mUniformBottomDTO1).isNotEqualTo(mUniformBottomDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mUniformBottomMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mUniformBottomMapper.fromId(null)).isNull();
    }
}
