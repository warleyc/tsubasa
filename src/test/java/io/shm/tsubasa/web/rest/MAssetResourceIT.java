package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAsset;
import io.shm.tsubasa.repository.MAssetRepository;
import io.shm.tsubasa.service.MAssetService;
import io.shm.tsubasa.service.dto.MAssetDTO;
import io.shm.tsubasa.service.mapper.MAssetMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MAssetCriteria;
import io.shm.tsubasa.service.MAssetQueryService;

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
 * Integration tests for the {@Link MAssetResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MAssetResourceIT {

    private static final String DEFAULT_ASSET_BUNDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_BUNDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_DEPENDENCIES = "AAAAAAAAAA";
    private static final String UPDATED_DEPENDENCIES = "BBBBBBBBBB";

    private static final Integer DEFAULT_I_18_N = 1;
    private static final Integer UPDATED_I_18_N = 2;

    private static final String DEFAULT_PLATFORM = "AAAAAAAAAA";
    private static final String UPDATED_PLATFORM = "BBBBBBBBBB";

    private static final String DEFAULT_PACK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PACK_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_HEAD = 1;
    private static final Integer UPDATED_HEAD = 2;

    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer UPDATED_SIZE = 2;

    private static final Integer DEFAULT_KEY_1 = 1;
    private static final Integer UPDATED_KEY_1 = 2;

    private static final Integer DEFAULT_KEY_2 = 1;
    private static final Integer UPDATED_KEY_2 = 2;

    @Autowired
    private MAssetRepository mAssetRepository;

    @Autowired
    private MAssetMapper mAssetMapper;

    @Autowired
    private MAssetService mAssetService;

    @Autowired
    private MAssetQueryService mAssetQueryService;

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

    private MockMvc restMAssetMockMvc;

    private MAsset mAsset;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MAssetResource mAssetResource = new MAssetResource(mAssetService, mAssetQueryService);
        this.restMAssetMockMvc = MockMvcBuilders.standaloneSetup(mAssetResource)
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
    public static MAsset createEntity(EntityManager em) {
        MAsset mAsset = new MAsset()
            .assetBundleName(DEFAULT_ASSET_BUNDLE_NAME)
            .tag(DEFAULT_TAG)
            .dependencies(DEFAULT_DEPENDENCIES)
            .i18n(DEFAULT_I_18_N)
            .platform(DEFAULT_PLATFORM)
            .packName(DEFAULT_PACK_NAME)
            .head(DEFAULT_HEAD)
            .size(DEFAULT_SIZE)
            .key1(DEFAULT_KEY_1)
            .key2(DEFAULT_KEY_2);
        return mAsset;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAsset createUpdatedEntity(EntityManager em) {
        MAsset mAsset = new MAsset()
            .assetBundleName(UPDATED_ASSET_BUNDLE_NAME)
            .tag(UPDATED_TAG)
            .dependencies(UPDATED_DEPENDENCIES)
            .i18n(UPDATED_I_18_N)
            .platform(UPDATED_PLATFORM)
            .packName(UPDATED_PACK_NAME)
            .head(UPDATED_HEAD)
            .size(UPDATED_SIZE)
            .key1(UPDATED_KEY_1)
            .key2(UPDATED_KEY_2);
        return mAsset;
    }

    @BeforeEach
    public void initTest() {
        mAsset = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAsset() throws Exception {
        int databaseSizeBeforeCreate = mAssetRepository.findAll().size();

        // Create the MAsset
        MAssetDTO mAssetDTO = mAssetMapper.toDto(mAsset);
        restMAssetMockMvc.perform(post("/api/m-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetDTO)))
            .andExpect(status().isCreated());

        // Validate the MAsset in the database
        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeCreate + 1);
        MAsset testMAsset = mAssetList.get(mAssetList.size() - 1);
        assertThat(testMAsset.getAssetBundleName()).isEqualTo(DEFAULT_ASSET_BUNDLE_NAME);
        assertThat(testMAsset.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testMAsset.getDependencies()).isEqualTo(DEFAULT_DEPENDENCIES);
        assertThat(testMAsset.geti18n()).isEqualTo(DEFAULT_I_18_N);
        assertThat(testMAsset.getPlatform()).isEqualTo(DEFAULT_PLATFORM);
        assertThat(testMAsset.getPackName()).isEqualTo(DEFAULT_PACK_NAME);
        assertThat(testMAsset.getHead()).isEqualTo(DEFAULT_HEAD);
        assertThat(testMAsset.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testMAsset.getKey1()).isEqualTo(DEFAULT_KEY_1);
        assertThat(testMAsset.getKey2()).isEqualTo(DEFAULT_KEY_2);
    }

    @Test
    @Transactional
    public void createMAssetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mAssetRepository.findAll().size();

        // Create the MAsset with an existing ID
        mAsset.setId(1L);
        MAssetDTO mAssetDTO = mAssetMapper.toDto(mAsset);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMAssetMockMvc.perform(post("/api/m-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAsset in the database
        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checki18nIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAssetRepository.findAll().size();
        // set the field null
        mAsset.seti18n(null);

        // Create the MAsset, which fails.
        MAssetDTO mAssetDTO = mAssetMapper.toDto(mAsset);

        restMAssetMockMvc.perform(post("/api/m-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetDTO)))
            .andExpect(status().isBadRequest());

        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeadIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAssetRepository.findAll().size();
        // set the field null
        mAsset.setHead(null);

        // Create the MAsset, which fails.
        MAssetDTO mAssetDTO = mAssetMapper.toDto(mAsset);

        restMAssetMockMvc.perform(post("/api/m-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetDTO)))
            .andExpect(status().isBadRequest());

        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAssetRepository.findAll().size();
        // set the field null
        mAsset.setSize(null);

        // Create the MAsset, which fails.
        MAssetDTO mAssetDTO = mAssetMapper.toDto(mAsset);

        restMAssetMockMvc.perform(post("/api/m-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetDTO)))
            .andExpect(status().isBadRequest());

        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKey1IsRequired() throws Exception {
        int databaseSizeBeforeTest = mAssetRepository.findAll().size();
        // set the field null
        mAsset.setKey1(null);

        // Create the MAsset, which fails.
        MAssetDTO mAssetDTO = mAssetMapper.toDto(mAsset);

        restMAssetMockMvc.perform(post("/api/m-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetDTO)))
            .andExpect(status().isBadRequest());

        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKey2IsRequired() throws Exception {
        int databaseSizeBeforeTest = mAssetRepository.findAll().size();
        // set the field null
        mAsset.setKey2(null);

        // Create the MAsset, which fails.
        MAssetDTO mAssetDTO = mAssetMapper.toDto(mAsset);

        restMAssetMockMvc.perform(post("/api/m-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetDTO)))
            .andExpect(status().isBadRequest());

        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMAssets() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList
        restMAssetMockMvc.perform(get("/api/m-assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAsset.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetBundleName").value(hasItem(DEFAULT_ASSET_BUNDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG.toString())))
            .andExpect(jsonPath("$.[*].dependencies").value(hasItem(DEFAULT_DEPENDENCIES.toString())))
            .andExpect(jsonPath("$.[*].i18n").value(hasItem(DEFAULT_I_18_N)))
            .andExpect(jsonPath("$.[*].platform").value(hasItem(DEFAULT_PLATFORM.toString())))
            .andExpect(jsonPath("$.[*].packName").value(hasItem(DEFAULT_PACK_NAME.toString())))
            .andExpect(jsonPath("$.[*].head").value(hasItem(DEFAULT_HEAD)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].key1").value(hasItem(DEFAULT_KEY_1)))
            .andExpect(jsonPath("$.[*].key2").value(hasItem(DEFAULT_KEY_2)));
    }
    
    @Test
    @Transactional
    public void getMAsset() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get the mAsset
        restMAssetMockMvc.perform(get("/api/m-assets/{id}", mAsset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAsset.getId().intValue()))
            .andExpect(jsonPath("$.assetBundleName").value(DEFAULT_ASSET_BUNDLE_NAME.toString()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG.toString()))
            .andExpect(jsonPath("$.dependencies").value(DEFAULT_DEPENDENCIES.toString()))
            .andExpect(jsonPath("$.i18n").value(DEFAULT_I_18_N))
            .andExpect(jsonPath("$.platform").value(DEFAULT_PLATFORM.toString()))
            .andExpect(jsonPath("$.packName").value(DEFAULT_PACK_NAME.toString()))
            .andExpect(jsonPath("$.head").value(DEFAULT_HEAD))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE))
            .andExpect(jsonPath("$.key1").value(DEFAULT_KEY_1))
            .andExpect(jsonPath("$.key2").value(DEFAULT_KEY_2));
    }

    @Test
    @Transactional
    public void getAllMAssetsByi18nIsEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where i18n equals to DEFAULT_I_18_N
        defaultMAssetShouldBeFound("i18n.equals=" + DEFAULT_I_18_N);

        // Get all the mAssetList where i18n equals to UPDATED_I_18_N
        defaultMAssetShouldNotBeFound("i18n.equals=" + UPDATED_I_18_N);
    }

    @Test
    @Transactional
    public void getAllMAssetsByi18nIsInShouldWork() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where i18n in DEFAULT_I_18_N or UPDATED_I_18_N
        defaultMAssetShouldBeFound("i18n.in=" + DEFAULT_I_18_N + "," + UPDATED_I_18_N);

        // Get all the mAssetList where i18n equals to UPDATED_I_18_N
        defaultMAssetShouldNotBeFound("i18n.in=" + UPDATED_I_18_N);
    }

    @Test
    @Transactional
    public void getAllMAssetsByi18nIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where i18n is not null
        defaultMAssetShouldBeFound("i18n.specified=true");

        // Get all the mAssetList where i18n is null
        defaultMAssetShouldNotBeFound("i18n.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAssetsByi18nIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where i18n greater than or equals to DEFAULT_I_18_N
        defaultMAssetShouldBeFound("i18n.greaterOrEqualThan=" + DEFAULT_I_18_N);

        // Get all the mAssetList where i18n greater than or equals to UPDATED_I_18_N
        defaultMAssetShouldNotBeFound("i18n.greaterOrEqualThan=" + UPDATED_I_18_N);
    }

    @Test
    @Transactional
    public void getAllMAssetsByi18nIsLessThanSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where i18n less than or equals to DEFAULT_I_18_N
        defaultMAssetShouldNotBeFound("i18n.lessThan=" + DEFAULT_I_18_N);

        // Get all the mAssetList where i18n less than or equals to UPDATED_I_18_N
        defaultMAssetShouldBeFound("i18n.lessThan=" + UPDATED_I_18_N);
    }


    @Test
    @Transactional
    public void getAllMAssetsByHeadIsEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where head equals to DEFAULT_HEAD
        defaultMAssetShouldBeFound("head.equals=" + DEFAULT_HEAD);

        // Get all the mAssetList where head equals to UPDATED_HEAD
        defaultMAssetShouldNotBeFound("head.equals=" + UPDATED_HEAD);
    }

    @Test
    @Transactional
    public void getAllMAssetsByHeadIsInShouldWork() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where head in DEFAULT_HEAD or UPDATED_HEAD
        defaultMAssetShouldBeFound("head.in=" + DEFAULT_HEAD + "," + UPDATED_HEAD);

        // Get all the mAssetList where head equals to UPDATED_HEAD
        defaultMAssetShouldNotBeFound("head.in=" + UPDATED_HEAD);
    }

    @Test
    @Transactional
    public void getAllMAssetsByHeadIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where head is not null
        defaultMAssetShouldBeFound("head.specified=true");

        // Get all the mAssetList where head is null
        defaultMAssetShouldNotBeFound("head.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAssetsByHeadIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where head greater than or equals to DEFAULT_HEAD
        defaultMAssetShouldBeFound("head.greaterOrEqualThan=" + DEFAULT_HEAD);

        // Get all the mAssetList where head greater than or equals to UPDATED_HEAD
        defaultMAssetShouldNotBeFound("head.greaterOrEqualThan=" + UPDATED_HEAD);
    }

    @Test
    @Transactional
    public void getAllMAssetsByHeadIsLessThanSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where head less than or equals to DEFAULT_HEAD
        defaultMAssetShouldNotBeFound("head.lessThan=" + DEFAULT_HEAD);

        // Get all the mAssetList where head less than or equals to UPDATED_HEAD
        defaultMAssetShouldBeFound("head.lessThan=" + UPDATED_HEAD);
    }


    @Test
    @Transactional
    public void getAllMAssetsBySizeIsEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where size equals to DEFAULT_SIZE
        defaultMAssetShouldBeFound("size.equals=" + DEFAULT_SIZE);

        // Get all the mAssetList where size equals to UPDATED_SIZE
        defaultMAssetShouldNotBeFound("size.equals=" + UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void getAllMAssetsBySizeIsInShouldWork() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where size in DEFAULT_SIZE or UPDATED_SIZE
        defaultMAssetShouldBeFound("size.in=" + DEFAULT_SIZE + "," + UPDATED_SIZE);

        // Get all the mAssetList where size equals to UPDATED_SIZE
        defaultMAssetShouldNotBeFound("size.in=" + UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void getAllMAssetsBySizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where size is not null
        defaultMAssetShouldBeFound("size.specified=true");

        // Get all the mAssetList where size is null
        defaultMAssetShouldNotBeFound("size.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAssetsBySizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where size greater than or equals to DEFAULT_SIZE
        defaultMAssetShouldBeFound("size.greaterOrEqualThan=" + DEFAULT_SIZE);

        // Get all the mAssetList where size greater than or equals to UPDATED_SIZE
        defaultMAssetShouldNotBeFound("size.greaterOrEqualThan=" + UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void getAllMAssetsBySizeIsLessThanSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where size less than or equals to DEFAULT_SIZE
        defaultMAssetShouldNotBeFound("size.lessThan=" + DEFAULT_SIZE);

        // Get all the mAssetList where size less than or equals to UPDATED_SIZE
        defaultMAssetShouldBeFound("size.lessThan=" + UPDATED_SIZE);
    }


    @Test
    @Transactional
    public void getAllMAssetsByKey1IsEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key1 equals to DEFAULT_KEY_1
        defaultMAssetShouldBeFound("key1.equals=" + DEFAULT_KEY_1);

        // Get all the mAssetList where key1 equals to UPDATED_KEY_1
        defaultMAssetShouldNotBeFound("key1.equals=" + UPDATED_KEY_1);
    }

    @Test
    @Transactional
    public void getAllMAssetsByKey1IsInShouldWork() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key1 in DEFAULT_KEY_1 or UPDATED_KEY_1
        defaultMAssetShouldBeFound("key1.in=" + DEFAULT_KEY_1 + "," + UPDATED_KEY_1);

        // Get all the mAssetList where key1 equals to UPDATED_KEY_1
        defaultMAssetShouldNotBeFound("key1.in=" + UPDATED_KEY_1);
    }

    @Test
    @Transactional
    public void getAllMAssetsByKey1IsNullOrNotNull() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key1 is not null
        defaultMAssetShouldBeFound("key1.specified=true");

        // Get all the mAssetList where key1 is null
        defaultMAssetShouldNotBeFound("key1.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAssetsByKey1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key1 greater than or equals to DEFAULT_KEY_1
        defaultMAssetShouldBeFound("key1.greaterOrEqualThan=" + DEFAULT_KEY_1);

        // Get all the mAssetList where key1 greater than or equals to UPDATED_KEY_1
        defaultMAssetShouldNotBeFound("key1.greaterOrEqualThan=" + UPDATED_KEY_1);
    }

    @Test
    @Transactional
    public void getAllMAssetsByKey1IsLessThanSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key1 less than or equals to DEFAULT_KEY_1
        defaultMAssetShouldNotBeFound("key1.lessThan=" + DEFAULT_KEY_1);

        // Get all the mAssetList where key1 less than or equals to UPDATED_KEY_1
        defaultMAssetShouldBeFound("key1.lessThan=" + UPDATED_KEY_1);
    }


    @Test
    @Transactional
    public void getAllMAssetsByKey2IsEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key2 equals to DEFAULT_KEY_2
        defaultMAssetShouldBeFound("key2.equals=" + DEFAULT_KEY_2);

        // Get all the mAssetList where key2 equals to UPDATED_KEY_2
        defaultMAssetShouldNotBeFound("key2.equals=" + UPDATED_KEY_2);
    }

    @Test
    @Transactional
    public void getAllMAssetsByKey2IsInShouldWork() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key2 in DEFAULT_KEY_2 or UPDATED_KEY_2
        defaultMAssetShouldBeFound("key2.in=" + DEFAULT_KEY_2 + "," + UPDATED_KEY_2);

        // Get all the mAssetList where key2 equals to UPDATED_KEY_2
        defaultMAssetShouldNotBeFound("key2.in=" + UPDATED_KEY_2);
    }

    @Test
    @Transactional
    public void getAllMAssetsByKey2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key2 is not null
        defaultMAssetShouldBeFound("key2.specified=true");

        // Get all the mAssetList where key2 is null
        defaultMAssetShouldNotBeFound("key2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAssetsByKey2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key2 greater than or equals to DEFAULT_KEY_2
        defaultMAssetShouldBeFound("key2.greaterOrEqualThan=" + DEFAULT_KEY_2);

        // Get all the mAssetList where key2 greater than or equals to UPDATED_KEY_2
        defaultMAssetShouldNotBeFound("key2.greaterOrEqualThan=" + UPDATED_KEY_2);
    }

    @Test
    @Transactional
    public void getAllMAssetsByKey2IsLessThanSomething() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        // Get all the mAssetList where key2 less than or equals to DEFAULT_KEY_2
        defaultMAssetShouldNotBeFound("key2.lessThan=" + DEFAULT_KEY_2);

        // Get all the mAssetList where key2 less than or equals to UPDATED_KEY_2
        defaultMAssetShouldBeFound("key2.lessThan=" + UPDATED_KEY_2);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMAssetShouldBeFound(String filter) throws Exception {
        restMAssetMockMvc.perform(get("/api/m-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAsset.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetBundleName").value(hasItem(DEFAULT_ASSET_BUNDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG.toString())))
            .andExpect(jsonPath("$.[*].dependencies").value(hasItem(DEFAULT_DEPENDENCIES.toString())))
            .andExpect(jsonPath("$.[*].i18n").value(hasItem(DEFAULT_I_18_N)))
            .andExpect(jsonPath("$.[*].platform").value(hasItem(DEFAULT_PLATFORM.toString())))
            .andExpect(jsonPath("$.[*].packName").value(hasItem(DEFAULT_PACK_NAME.toString())))
            .andExpect(jsonPath("$.[*].head").value(hasItem(DEFAULT_HEAD)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].key1").value(hasItem(DEFAULT_KEY_1)))
            .andExpect(jsonPath("$.[*].key2").value(hasItem(DEFAULT_KEY_2)));

        // Check, that the count call also returns 1
        restMAssetMockMvc.perform(get("/api/m-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMAssetShouldNotBeFound(String filter) throws Exception {
        restMAssetMockMvc.perform(get("/api/m-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMAssetMockMvc.perform(get("/api/m-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAsset() throws Exception {
        // Get the mAsset
        restMAssetMockMvc.perform(get("/api/m-assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAsset() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        int databaseSizeBeforeUpdate = mAssetRepository.findAll().size();

        // Update the mAsset
        MAsset updatedMAsset = mAssetRepository.findById(mAsset.getId()).get();
        // Disconnect from session so that the updates on updatedMAsset are not directly saved in db
        em.detach(updatedMAsset);
        updatedMAsset
            .assetBundleName(UPDATED_ASSET_BUNDLE_NAME)
            .tag(UPDATED_TAG)
            .dependencies(UPDATED_DEPENDENCIES)
            .i18n(UPDATED_I_18_N)
            .platform(UPDATED_PLATFORM)
            .packName(UPDATED_PACK_NAME)
            .head(UPDATED_HEAD)
            .size(UPDATED_SIZE)
            .key1(UPDATED_KEY_1)
            .key2(UPDATED_KEY_2);
        MAssetDTO mAssetDTO = mAssetMapper.toDto(updatedMAsset);

        restMAssetMockMvc.perform(put("/api/m-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetDTO)))
            .andExpect(status().isOk());

        // Validate the MAsset in the database
        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeUpdate);
        MAsset testMAsset = mAssetList.get(mAssetList.size() - 1);
        assertThat(testMAsset.getAssetBundleName()).isEqualTo(UPDATED_ASSET_BUNDLE_NAME);
        assertThat(testMAsset.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testMAsset.getDependencies()).isEqualTo(UPDATED_DEPENDENCIES);
        assertThat(testMAsset.geti18n()).isEqualTo(UPDATED_I_18_N);
        assertThat(testMAsset.getPlatform()).isEqualTo(UPDATED_PLATFORM);
        assertThat(testMAsset.getPackName()).isEqualTo(UPDATED_PACK_NAME);
        assertThat(testMAsset.getHead()).isEqualTo(UPDATED_HEAD);
        assertThat(testMAsset.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testMAsset.getKey1()).isEqualTo(UPDATED_KEY_1);
        assertThat(testMAsset.getKey2()).isEqualTo(UPDATED_KEY_2);
    }

    @Test
    @Transactional
    public void updateNonExistingMAsset() throws Exception {
        int databaseSizeBeforeUpdate = mAssetRepository.findAll().size();

        // Create the MAsset
        MAssetDTO mAssetDTO = mAssetMapper.toDto(mAsset);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMAssetMockMvc.perform(put("/api/m-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAsset in the database
        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAsset() throws Exception {
        // Initialize the database
        mAssetRepository.saveAndFlush(mAsset);

        int databaseSizeBeforeDelete = mAssetRepository.findAll().size();

        // Delete the mAsset
        restMAssetMockMvc.perform(delete("/api/m-assets/{id}", mAsset.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAsset> mAssetList = mAssetRepository.findAll();
        assertThat(mAssetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAsset.class);
        MAsset mAsset1 = new MAsset();
        mAsset1.setId(1L);
        MAsset mAsset2 = new MAsset();
        mAsset2.setId(mAsset1.getId());
        assertThat(mAsset1).isEqualTo(mAsset2);
        mAsset2.setId(2L);
        assertThat(mAsset1).isNotEqualTo(mAsset2);
        mAsset1.setId(null);
        assertThat(mAsset1).isNotEqualTo(mAsset2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAssetDTO.class);
        MAssetDTO mAssetDTO1 = new MAssetDTO();
        mAssetDTO1.setId(1L);
        MAssetDTO mAssetDTO2 = new MAssetDTO();
        assertThat(mAssetDTO1).isNotEqualTo(mAssetDTO2);
        mAssetDTO2.setId(mAssetDTO1.getId());
        assertThat(mAssetDTO1).isEqualTo(mAssetDTO2);
        mAssetDTO2.setId(2L);
        assertThat(mAssetDTO1).isNotEqualTo(mAssetDTO2);
        mAssetDTO1.setId(null);
        assertThat(mAssetDTO1).isNotEqualTo(mAssetDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mAssetMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mAssetMapper.fromId(null)).isNull();
    }
}
