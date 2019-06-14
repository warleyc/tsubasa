package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MUniformOriginalSet;
import io.shm.tsubasa.repository.MUniformOriginalSetRepository;
import io.shm.tsubasa.service.MUniformOriginalSetService;
import io.shm.tsubasa.service.dto.MUniformOriginalSetDTO;
import io.shm.tsubasa.service.mapper.MUniformOriginalSetMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MUniformOriginalSetCriteria;
import io.shm.tsubasa.service.MUniformOriginalSetQueryService;

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
 * Integration tests for the {@Link MUniformOriginalSetResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MUniformOriginalSetResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MENU_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MENU_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_UP_MODEL_ID = 1;
    private static final Integer UPDATED_UP_MODEL_ID = 2;

    private static final Integer DEFAULT_BOTTOM_MODEL_ID = 1;
    private static final Integer UPDATED_BOTTOM_MODEL_ID = 2;

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
    private MUniformOriginalSetRepository mUniformOriginalSetRepository;

    @Autowired
    private MUniformOriginalSetMapper mUniformOriginalSetMapper;

    @Autowired
    private MUniformOriginalSetService mUniformOriginalSetService;

    @Autowired
    private MUniformOriginalSetQueryService mUniformOriginalSetQueryService;

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

    private MockMvc restMUniformOriginalSetMockMvc;

    private MUniformOriginalSet mUniformOriginalSet;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MUniformOriginalSetResource mUniformOriginalSetResource = new MUniformOriginalSetResource(mUniformOriginalSetService, mUniformOriginalSetQueryService);
        this.restMUniformOriginalSetMockMvc = MockMvcBuilders.standaloneSetup(mUniformOriginalSetResource)
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
    public static MUniformOriginalSet createEntity(EntityManager em) {
        MUniformOriginalSet mUniformOriginalSet = new MUniformOriginalSet()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .menuName(DEFAULT_MENU_NAME)
            .upModelId(DEFAULT_UP_MODEL_ID)
            .bottomModelId(DEFAULT_BOTTOM_MODEL_ID)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME)
            .uniformType(DEFAULT_UNIFORM_TYPE)
            .isDefault(DEFAULT_IS_DEFAULT)
            .orderNum(DEFAULT_ORDER_NUM)
            .description(DEFAULT_DESCRIPTION);
        return mUniformOriginalSet;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MUniformOriginalSet createUpdatedEntity(EntityManager em) {
        MUniformOriginalSet mUniformOriginalSet = new MUniformOriginalSet()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .menuName(UPDATED_MENU_NAME)
            .upModelId(UPDATED_UP_MODEL_ID)
            .bottomModelId(UPDATED_BOTTOM_MODEL_ID)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .uniformType(UPDATED_UNIFORM_TYPE)
            .isDefault(UPDATED_IS_DEFAULT)
            .orderNum(UPDATED_ORDER_NUM)
            .description(UPDATED_DESCRIPTION);
        return mUniformOriginalSet;
    }

    @BeforeEach
    public void initTest() {
        mUniformOriginalSet = createEntity(em);
    }

    @Test
    @Transactional
    public void createMUniformOriginalSet() throws Exception {
        int databaseSizeBeforeCreate = mUniformOriginalSetRepository.findAll().size();

        // Create the MUniformOriginalSet
        MUniformOriginalSetDTO mUniformOriginalSetDTO = mUniformOriginalSetMapper.toDto(mUniformOriginalSet);
        restMUniformOriginalSetMockMvc.perform(post("/api/m-uniform-original-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformOriginalSetDTO)))
            .andExpect(status().isCreated());

        // Validate the MUniformOriginalSet in the database
        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeCreate + 1);
        MUniformOriginalSet testMUniformOriginalSet = mUniformOriginalSetList.get(mUniformOriginalSetList.size() - 1);
        assertThat(testMUniformOriginalSet.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMUniformOriginalSet.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMUniformOriginalSet.getMenuName()).isEqualTo(DEFAULT_MENU_NAME);
        assertThat(testMUniformOriginalSet.getUpModelId()).isEqualTo(DEFAULT_UP_MODEL_ID);
        assertThat(testMUniformOriginalSet.getBottomModelId()).isEqualTo(DEFAULT_BOTTOM_MODEL_ID);
        assertThat(testMUniformOriginalSet.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
        assertThat(testMUniformOriginalSet.getUniformType()).isEqualTo(DEFAULT_UNIFORM_TYPE);
        assertThat(testMUniformOriginalSet.getIsDefault()).isEqualTo(DEFAULT_IS_DEFAULT);
        assertThat(testMUniformOriginalSet.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMUniformOriginalSet.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMUniformOriginalSetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mUniformOriginalSetRepository.findAll().size();

        // Create the MUniformOriginalSet with an existing ID
        mUniformOriginalSet.setId(1L);
        MUniformOriginalSetDTO mUniformOriginalSetDTO = mUniformOriginalSetMapper.toDto(mUniformOriginalSet);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMUniformOriginalSetMockMvc.perform(post("/api/m-uniform-original-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformOriginalSetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUniformOriginalSet in the database
        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUpModelIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformOriginalSetRepository.findAll().size();
        // set the field null
        mUniformOriginalSet.setUpModelId(null);

        // Create the MUniformOriginalSet, which fails.
        MUniformOriginalSetDTO mUniformOriginalSetDTO = mUniformOriginalSetMapper.toDto(mUniformOriginalSet);

        restMUniformOriginalSetMockMvc.perform(post("/api/m-uniform-original-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformOriginalSetDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBottomModelIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformOriginalSetRepository.findAll().size();
        // set the field null
        mUniformOriginalSet.setBottomModelId(null);

        // Create the MUniformOriginalSet, which fails.
        MUniformOriginalSetDTO mUniformOriginalSetDTO = mUniformOriginalSetMapper.toDto(mUniformOriginalSet);

        restMUniformOriginalSetMockMvc.perform(post("/api/m-uniform-original-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformOriginalSetDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformOriginalSetRepository.findAll().size();
        // set the field null
        mUniformOriginalSet.setUniformType(null);

        // Create the MUniformOriginalSet, which fails.
        MUniformOriginalSetDTO mUniformOriginalSetDTO = mUniformOriginalSetMapper.toDto(mUniformOriginalSet);

        restMUniformOriginalSetMockMvc.perform(post("/api/m-uniform-original-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformOriginalSetDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsDefaultIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformOriginalSetRepository.findAll().size();
        // set the field null
        mUniformOriginalSet.setIsDefault(null);

        // Create the MUniformOriginalSet, which fails.
        MUniformOriginalSetDTO mUniformOriginalSetDTO = mUniformOriginalSetMapper.toDto(mUniformOriginalSet);

        restMUniformOriginalSetMockMvc.perform(post("/api/m-uniform-original-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformOriginalSetDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUniformOriginalSetRepository.findAll().size();
        // set the field null
        mUniformOriginalSet.setOrderNum(null);

        // Create the MUniformOriginalSet, which fails.
        MUniformOriginalSetDTO mUniformOriginalSetDTO = mUniformOriginalSetMapper.toDto(mUniformOriginalSet);

        restMUniformOriginalSetMockMvc.perform(post("/api/m-uniform-original-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformOriginalSetDTO)))
            .andExpect(status().isBadRequest());

        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSets() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList
        restMUniformOriginalSetMockMvc.perform(get("/api/m-uniform-original-sets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUniformOriginalSet.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].menuName").value(hasItem(DEFAULT_MENU_NAME.toString())))
            .andExpect(jsonPath("$.[*].upModelId").value(hasItem(DEFAULT_UP_MODEL_ID)))
            .andExpect(jsonPath("$.[*].bottomModelId").value(hasItem(DEFAULT_BOTTOM_MODEL_ID)))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].uniformType").value(hasItem(DEFAULT_UNIFORM_TYPE)))
            .andExpect(jsonPath("$.[*].isDefault").value(hasItem(DEFAULT_IS_DEFAULT)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMUniformOriginalSet() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get the mUniformOriginalSet
        restMUniformOriginalSetMockMvc.perform(get("/api/m-uniform-original-sets/{id}", mUniformOriginalSet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mUniformOriginalSet.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.menuName").value(DEFAULT_MENU_NAME.toString()))
            .andExpect(jsonPath("$.upModelId").value(DEFAULT_UP_MODEL_ID))
            .andExpect(jsonPath("$.bottomModelId").value(DEFAULT_BOTTOM_MODEL_ID))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.uniformType").value(DEFAULT_UNIFORM_TYPE))
            .andExpect(jsonPath("$.isDefault").value(DEFAULT_IS_DEFAULT))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUpModelIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where upModelId equals to DEFAULT_UP_MODEL_ID
        defaultMUniformOriginalSetShouldBeFound("upModelId.equals=" + DEFAULT_UP_MODEL_ID);

        // Get all the mUniformOriginalSetList where upModelId equals to UPDATED_UP_MODEL_ID
        defaultMUniformOriginalSetShouldNotBeFound("upModelId.equals=" + UPDATED_UP_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUpModelIdIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where upModelId in DEFAULT_UP_MODEL_ID or UPDATED_UP_MODEL_ID
        defaultMUniformOriginalSetShouldBeFound("upModelId.in=" + DEFAULT_UP_MODEL_ID + "," + UPDATED_UP_MODEL_ID);

        // Get all the mUniformOriginalSetList where upModelId equals to UPDATED_UP_MODEL_ID
        defaultMUniformOriginalSetShouldNotBeFound("upModelId.in=" + UPDATED_UP_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUpModelIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where upModelId is not null
        defaultMUniformOriginalSetShouldBeFound("upModelId.specified=true");

        // Get all the mUniformOriginalSetList where upModelId is null
        defaultMUniformOriginalSetShouldNotBeFound("upModelId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUpModelIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where upModelId greater than or equals to DEFAULT_UP_MODEL_ID
        defaultMUniformOriginalSetShouldBeFound("upModelId.greaterOrEqualThan=" + DEFAULT_UP_MODEL_ID);

        // Get all the mUniformOriginalSetList where upModelId greater than or equals to UPDATED_UP_MODEL_ID
        defaultMUniformOriginalSetShouldNotBeFound("upModelId.greaterOrEqualThan=" + UPDATED_UP_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUpModelIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where upModelId less than or equals to DEFAULT_UP_MODEL_ID
        defaultMUniformOriginalSetShouldNotBeFound("upModelId.lessThan=" + DEFAULT_UP_MODEL_ID);

        // Get all the mUniformOriginalSetList where upModelId less than or equals to UPDATED_UP_MODEL_ID
        defaultMUniformOriginalSetShouldBeFound("upModelId.lessThan=" + UPDATED_UP_MODEL_ID);
    }


    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByBottomModelIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where bottomModelId equals to DEFAULT_BOTTOM_MODEL_ID
        defaultMUniformOriginalSetShouldBeFound("bottomModelId.equals=" + DEFAULT_BOTTOM_MODEL_ID);

        // Get all the mUniformOriginalSetList where bottomModelId equals to UPDATED_BOTTOM_MODEL_ID
        defaultMUniformOriginalSetShouldNotBeFound("bottomModelId.equals=" + UPDATED_BOTTOM_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByBottomModelIdIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where bottomModelId in DEFAULT_BOTTOM_MODEL_ID or UPDATED_BOTTOM_MODEL_ID
        defaultMUniformOriginalSetShouldBeFound("bottomModelId.in=" + DEFAULT_BOTTOM_MODEL_ID + "," + UPDATED_BOTTOM_MODEL_ID);

        // Get all the mUniformOriginalSetList where bottomModelId equals to UPDATED_BOTTOM_MODEL_ID
        defaultMUniformOriginalSetShouldNotBeFound("bottomModelId.in=" + UPDATED_BOTTOM_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByBottomModelIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where bottomModelId is not null
        defaultMUniformOriginalSetShouldBeFound("bottomModelId.specified=true");

        // Get all the mUniformOriginalSetList where bottomModelId is null
        defaultMUniformOriginalSetShouldNotBeFound("bottomModelId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByBottomModelIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where bottomModelId greater than or equals to DEFAULT_BOTTOM_MODEL_ID
        defaultMUniformOriginalSetShouldBeFound("bottomModelId.greaterOrEqualThan=" + DEFAULT_BOTTOM_MODEL_ID);

        // Get all the mUniformOriginalSetList where bottomModelId greater than or equals to UPDATED_BOTTOM_MODEL_ID
        defaultMUniformOriginalSetShouldNotBeFound("bottomModelId.greaterOrEqualThan=" + UPDATED_BOTTOM_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByBottomModelIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where bottomModelId less than or equals to DEFAULT_BOTTOM_MODEL_ID
        defaultMUniformOriginalSetShouldNotBeFound("bottomModelId.lessThan=" + DEFAULT_BOTTOM_MODEL_ID);

        // Get all the mUniformOriginalSetList where bottomModelId less than or equals to UPDATED_BOTTOM_MODEL_ID
        defaultMUniformOriginalSetShouldBeFound("bottomModelId.lessThan=" + UPDATED_BOTTOM_MODEL_ID);
    }


    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUniformTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where uniformType equals to DEFAULT_UNIFORM_TYPE
        defaultMUniformOriginalSetShouldBeFound("uniformType.equals=" + DEFAULT_UNIFORM_TYPE);

        // Get all the mUniformOriginalSetList where uniformType equals to UPDATED_UNIFORM_TYPE
        defaultMUniformOriginalSetShouldNotBeFound("uniformType.equals=" + UPDATED_UNIFORM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUniformTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where uniformType in DEFAULT_UNIFORM_TYPE or UPDATED_UNIFORM_TYPE
        defaultMUniformOriginalSetShouldBeFound("uniformType.in=" + DEFAULT_UNIFORM_TYPE + "," + UPDATED_UNIFORM_TYPE);

        // Get all the mUniformOriginalSetList where uniformType equals to UPDATED_UNIFORM_TYPE
        defaultMUniformOriginalSetShouldNotBeFound("uniformType.in=" + UPDATED_UNIFORM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUniformTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where uniformType is not null
        defaultMUniformOriginalSetShouldBeFound("uniformType.specified=true");

        // Get all the mUniformOriginalSetList where uniformType is null
        defaultMUniformOriginalSetShouldNotBeFound("uniformType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUniformTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where uniformType greater than or equals to DEFAULT_UNIFORM_TYPE
        defaultMUniformOriginalSetShouldBeFound("uniformType.greaterOrEqualThan=" + DEFAULT_UNIFORM_TYPE);

        // Get all the mUniformOriginalSetList where uniformType greater than or equals to UPDATED_UNIFORM_TYPE
        defaultMUniformOriginalSetShouldNotBeFound("uniformType.greaterOrEqualThan=" + UPDATED_UNIFORM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByUniformTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where uniformType less than or equals to DEFAULT_UNIFORM_TYPE
        defaultMUniformOriginalSetShouldNotBeFound("uniformType.lessThan=" + DEFAULT_UNIFORM_TYPE);

        // Get all the mUniformOriginalSetList where uniformType less than or equals to UPDATED_UNIFORM_TYPE
        defaultMUniformOriginalSetShouldBeFound("uniformType.lessThan=" + UPDATED_UNIFORM_TYPE);
    }


    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByIsDefaultIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where isDefault equals to DEFAULT_IS_DEFAULT
        defaultMUniformOriginalSetShouldBeFound("isDefault.equals=" + DEFAULT_IS_DEFAULT);

        // Get all the mUniformOriginalSetList where isDefault equals to UPDATED_IS_DEFAULT
        defaultMUniformOriginalSetShouldNotBeFound("isDefault.equals=" + UPDATED_IS_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByIsDefaultIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where isDefault in DEFAULT_IS_DEFAULT or UPDATED_IS_DEFAULT
        defaultMUniformOriginalSetShouldBeFound("isDefault.in=" + DEFAULT_IS_DEFAULT + "," + UPDATED_IS_DEFAULT);

        // Get all the mUniformOriginalSetList where isDefault equals to UPDATED_IS_DEFAULT
        defaultMUniformOriginalSetShouldNotBeFound("isDefault.in=" + UPDATED_IS_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByIsDefaultIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where isDefault is not null
        defaultMUniformOriginalSetShouldBeFound("isDefault.specified=true");

        // Get all the mUniformOriginalSetList where isDefault is null
        defaultMUniformOriginalSetShouldNotBeFound("isDefault.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByIsDefaultIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where isDefault greater than or equals to DEFAULT_IS_DEFAULT
        defaultMUniformOriginalSetShouldBeFound("isDefault.greaterOrEqualThan=" + DEFAULT_IS_DEFAULT);

        // Get all the mUniformOriginalSetList where isDefault greater than or equals to UPDATED_IS_DEFAULT
        defaultMUniformOriginalSetShouldNotBeFound("isDefault.greaterOrEqualThan=" + UPDATED_IS_DEFAULT);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByIsDefaultIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where isDefault less than or equals to DEFAULT_IS_DEFAULT
        defaultMUniformOriginalSetShouldNotBeFound("isDefault.lessThan=" + DEFAULT_IS_DEFAULT);

        // Get all the mUniformOriginalSetList where isDefault less than or equals to UPDATED_IS_DEFAULT
        defaultMUniformOriginalSetShouldBeFound("isDefault.lessThan=" + UPDATED_IS_DEFAULT);
    }


    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMUniformOriginalSetShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mUniformOriginalSetList where orderNum equals to UPDATED_ORDER_NUM
        defaultMUniformOriginalSetShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMUniformOriginalSetShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mUniformOriginalSetList where orderNum equals to UPDATED_ORDER_NUM
        defaultMUniformOriginalSetShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where orderNum is not null
        defaultMUniformOriginalSetShouldBeFound("orderNum.specified=true");

        // Get all the mUniformOriginalSetList where orderNum is null
        defaultMUniformOriginalSetShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMUniformOriginalSetShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mUniformOriginalSetList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMUniformOriginalSetShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMUniformOriginalSetsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        // Get all the mUniformOriginalSetList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMUniformOriginalSetShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mUniformOriginalSetList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMUniformOriginalSetShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMUniformOriginalSetShouldBeFound(String filter) throws Exception {
        restMUniformOriginalSetMockMvc.perform(get("/api/m-uniform-original-sets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUniformOriginalSet.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].menuName").value(hasItem(DEFAULT_MENU_NAME.toString())))
            .andExpect(jsonPath("$.[*].upModelId").value(hasItem(DEFAULT_UP_MODEL_ID)))
            .andExpect(jsonPath("$.[*].bottomModelId").value(hasItem(DEFAULT_BOTTOM_MODEL_ID)))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].uniformType").value(hasItem(DEFAULT_UNIFORM_TYPE)))
            .andExpect(jsonPath("$.[*].isDefault").value(hasItem(DEFAULT_IS_DEFAULT)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMUniformOriginalSetMockMvc.perform(get("/api/m-uniform-original-sets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMUniformOriginalSetShouldNotBeFound(String filter) throws Exception {
        restMUniformOriginalSetMockMvc.perform(get("/api/m-uniform-original-sets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMUniformOriginalSetMockMvc.perform(get("/api/m-uniform-original-sets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMUniformOriginalSet() throws Exception {
        // Get the mUniformOriginalSet
        restMUniformOriginalSetMockMvc.perform(get("/api/m-uniform-original-sets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMUniformOriginalSet() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        int databaseSizeBeforeUpdate = mUniformOriginalSetRepository.findAll().size();

        // Update the mUniformOriginalSet
        MUniformOriginalSet updatedMUniformOriginalSet = mUniformOriginalSetRepository.findById(mUniformOriginalSet.getId()).get();
        // Disconnect from session so that the updates on updatedMUniformOriginalSet are not directly saved in db
        em.detach(updatedMUniformOriginalSet);
        updatedMUniformOriginalSet
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .menuName(UPDATED_MENU_NAME)
            .upModelId(UPDATED_UP_MODEL_ID)
            .bottomModelId(UPDATED_BOTTOM_MODEL_ID)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .uniformType(UPDATED_UNIFORM_TYPE)
            .isDefault(UPDATED_IS_DEFAULT)
            .orderNum(UPDATED_ORDER_NUM)
            .description(UPDATED_DESCRIPTION);
        MUniformOriginalSetDTO mUniformOriginalSetDTO = mUniformOriginalSetMapper.toDto(updatedMUniformOriginalSet);

        restMUniformOriginalSetMockMvc.perform(put("/api/m-uniform-original-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformOriginalSetDTO)))
            .andExpect(status().isOk());

        // Validate the MUniformOriginalSet in the database
        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeUpdate);
        MUniformOriginalSet testMUniformOriginalSet = mUniformOriginalSetList.get(mUniformOriginalSetList.size() - 1);
        assertThat(testMUniformOriginalSet.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMUniformOriginalSet.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMUniformOriginalSet.getMenuName()).isEqualTo(UPDATED_MENU_NAME);
        assertThat(testMUniformOriginalSet.getUpModelId()).isEqualTo(UPDATED_UP_MODEL_ID);
        assertThat(testMUniformOriginalSet.getBottomModelId()).isEqualTo(UPDATED_BOTTOM_MODEL_ID);
        assertThat(testMUniformOriginalSet.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
        assertThat(testMUniformOriginalSet.getUniformType()).isEqualTo(UPDATED_UNIFORM_TYPE);
        assertThat(testMUniformOriginalSet.getIsDefault()).isEqualTo(UPDATED_IS_DEFAULT);
        assertThat(testMUniformOriginalSet.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMUniformOriginalSet.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMUniformOriginalSet() throws Exception {
        int databaseSizeBeforeUpdate = mUniformOriginalSetRepository.findAll().size();

        // Create the MUniformOriginalSet
        MUniformOriginalSetDTO mUniformOriginalSetDTO = mUniformOriginalSetMapper.toDto(mUniformOriginalSet);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMUniformOriginalSetMockMvc.perform(put("/api/m-uniform-original-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUniformOriginalSetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUniformOriginalSet in the database
        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMUniformOriginalSet() throws Exception {
        // Initialize the database
        mUniformOriginalSetRepository.saveAndFlush(mUniformOriginalSet);

        int databaseSizeBeforeDelete = mUniformOriginalSetRepository.findAll().size();

        // Delete the mUniformOriginalSet
        restMUniformOriginalSetMockMvc.perform(delete("/api/m-uniform-original-sets/{id}", mUniformOriginalSet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MUniformOriginalSet> mUniformOriginalSetList = mUniformOriginalSetRepository.findAll();
        assertThat(mUniformOriginalSetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUniformOriginalSet.class);
        MUniformOriginalSet mUniformOriginalSet1 = new MUniformOriginalSet();
        mUniformOriginalSet1.setId(1L);
        MUniformOriginalSet mUniformOriginalSet2 = new MUniformOriginalSet();
        mUniformOriginalSet2.setId(mUniformOriginalSet1.getId());
        assertThat(mUniformOriginalSet1).isEqualTo(mUniformOriginalSet2);
        mUniformOriginalSet2.setId(2L);
        assertThat(mUniformOriginalSet1).isNotEqualTo(mUniformOriginalSet2);
        mUniformOriginalSet1.setId(null);
        assertThat(mUniformOriginalSet1).isNotEqualTo(mUniformOriginalSet2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUniformOriginalSetDTO.class);
        MUniformOriginalSetDTO mUniformOriginalSetDTO1 = new MUniformOriginalSetDTO();
        mUniformOriginalSetDTO1.setId(1L);
        MUniformOriginalSetDTO mUniformOriginalSetDTO2 = new MUniformOriginalSetDTO();
        assertThat(mUniformOriginalSetDTO1).isNotEqualTo(mUniformOriginalSetDTO2);
        mUniformOriginalSetDTO2.setId(mUniformOriginalSetDTO1.getId());
        assertThat(mUniformOriginalSetDTO1).isEqualTo(mUniformOriginalSetDTO2);
        mUniformOriginalSetDTO2.setId(2L);
        assertThat(mUniformOriginalSetDTO1).isNotEqualTo(mUniformOriginalSetDTO2);
        mUniformOriginalSetDTO1.setId(null);
        assertThat(mUniformOriginalSetDTO1).isNotEqualTo(mUniformOriginalSetDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mUniformOriginalSetMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mUniformOriginalSetMapper.fromId(null)).isNull();
    }
}
