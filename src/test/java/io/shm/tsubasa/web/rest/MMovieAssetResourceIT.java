package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMovieAsset;
import io.shm.tsubasa.repository.MMovieAssetRepository;
import io.shm.tsubasa.service.MMovieAssetService;
import io.shm.tsubasa.service.dto.MMovieAssetDTO;
import io.shm.tsubasa.service.mapper.MMovieAssetMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMovieAssetCriteria;
import io.shm.tsubasa.service.MMovieAssetQueryService;

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
 * Integration tests for the {@Link MMovieAssetResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMovieAssetResourceIT {

    private static final Integer DEFAULT_LANG = 1;
    private static final Integer UPDATED_LANG = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer UPDATED_SIZE = 2;

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    @Autowired
    private MMovieAssetRepository mMovieAssetRepository;

    @Autowired
    private MMovieAssetMapper mMovieAssetMapper;

    @Autowired
    private MMovieAssetService mMovieAssetService;

    @Autowired
    private MMovieAssetQueryService mMovieAssetQueryService;

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

    private MockMvc restMMovieAssetMockMvc;

    private MMovieAsset mMovieAsset;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMovieAssetResource mMovieAssetResource = new MMovieAssetResource(mMovieAssetService, mMovieAssetQueryService);
        this.restMMovieAssetMockMvc = MockMvcBuilders.standaloneSetup(mMovieAssetResource)
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
    public static MMovieAsset createEntity(EntityManager em) {
        MMovieAsset mMovieAsset = new MMovieAsset()
            .lang(DEFAULT_LANG)
            .name(DEFAULT_NAME)
            .size(DEFAULT_SIZE)
            .version(DEFAULT_VERSION)
            .type(DEFAULT_TYPE);
        return mMovieAsset;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMovieAsset createUpdatedEntity(EntityManager em) {
        MMovieAsset mMovieAsset = new MMovieAsset()
            .lang(UPDATED_LANG)
            .name(UPDATED_NAME)
            .size(UPDATED_SIZE)
            .version(UPDATED_VERSION)
            .type(UPDATED_TYPE);
        return mMovieAsset;
    }

    @BeforeEach
    public void initTest() {
        mMovieAsset = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMovieAsset() throws Exception {
        int databaseSizeBeforeCreate = mMovieAssetRepository.findAll().size();

        // Create the MMovieAsset
        MMovieAssetDTO mMovieAssetDTO = mMovieAssetMapper.toDto(mMovieAsset);
        restMMovieAssetMockMvc.perform(post("/api/m-movie-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMovieAssetDTO)))
            .andExpect(status().isCreated());

        // Validate the MMovieAsset in the database
        List<MMovieAsset> mMovieAssetList = mMovieAssetRepository.findAll();
        assertThat(mMovieAssetList).hasSize(databaseSizeBeforeCreate + 1);
        MMovieAsset testMMovieAsset = mMovieAssetList.get(mMovieAssetList.size() - 1);
        assertThat(testMMovieAsset.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testMMovieAsset.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMMovieAsset.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testMMovieAsset.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testMMovieAsset.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createMMovieAssetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMovieAssetRepository.findAll().size();

        // Create the MMovieAsset with an existing ID
        mMovieAsset.setId(1L);
        MMovieAssetDTO mMovieAssetDTO = mMovieAssetMapper.toDto(mMovieAsset);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMovieAssetMockMvc.perform(post("/api/m-movie-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMovieAssetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMovieAsset in the database
        List<MMovieAsset> mMovieAssetList = mMovieAssetRepository.findAll();
        assertThat(mMovieAssetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLangIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMovieAssetRepository.findAll().size();
        // set the field null
        mMovieAsset.setLang(null);

        // Create the MMovieAsset, which fails.
        MMovieAssetDTO mMovieAssetDTO = mMovieAssetMapper.toDto(mMovieAsset);

        restMMovieAssetMockMvc.perform(post("/api/m-movie-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMovieAssetDTO)))
            .andExpect(status().isBadRequest());

        List<MMovieAsset> mMovieAssetList = mMovieAssetRepository.findAll();
        assertThat(mMovieAssetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMovieAssetRepository.findAll().size();
        // set the field null
        mMovieAsset.setSize(null);

        // Create the MMovieAsset, which fails.
        MMovieAssetDTO mMovieAssetDTO = mMovieAssetMapper.toDto(mMovieAsset);

        restMMovieAssetMockMvc.perform(post("/api/m-movie-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMovieAssetDTO)))
            .andExpect(status().isBadRequest());

        List<MMovieAsset> mMovieAssetList = mMovieAssetRepository.findAll();
        assertThat(mMovieAssetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMovieAssetRepository.findAll().size();
        // set the field null
        mMovieAsset.setVersion(null);

        // Create the MMovieAsset, which fails.
        MMovieAssetDTO mMovieAssetDTO = mMovieAssetMapper.toDto(mMovieAsset);

        restMMovieAssetMockMvc.perform(post("/api/m-movie-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMovieAssetDTO)))
            .andExpect(status().isBadRequest());

        List<MMovieAsset> mMovieAssetList = mMovieAssetRepository.findAll();
        assertThat(mMovieAssetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMovieAssetRepository.findAll().size();
        // set the field null
        mMovieAsset.setType(null);

        // Create the MMovieAsset, which fails.
        MMovieAssetDTO mMovieAssetDTO = mMovieAssetMapper.toDto(mMovieAsset);

        restMMovieAssetMockMvc.perform(post("/api/m-movie-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMovieAssetDTO)))
            .andExpect(status().isBadRequest());

        List<MMovieAsset> mMovieAssetList = mMovieAssetRepository.findAll();
        assertThat(mMovieAssetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMovieAssets() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList
        restMMovieAssetMockMvc.perform(get("/api/m-movie-assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMovieAsset.getId().intValue())))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getMMovieAsset() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get the mMovieAsset
        restMMovieAssetMockMvc.perform(get("/api/m-movie-assets/{id}", mMovieAsset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMovieAsset.getId().intValue()))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByLangIsEqualToSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where lang equals to DEFAULT_LANG
        defaultMMovieAssetShouldBeFound("lang.equals=" + DEFAULT_LANG);

        // Get all the mMovieAssetList where lang equals to UPDATED_LANG
        defaultMMovieAssetShouldNotBeFound("lang.equals=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByLangIsInShouldWork() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where lang in DEFAULT_LANG or UPDATED_LANG
        defaultMMovieAssetShouldBeFound("lang.in=" + DEFAULT_LANG + "," + UPDATED_LANG);

        // Get all the mMovieAssetList where lang equals to UPDATED_LANG
        defaultMMovieAssetShouldNotBeFound("lang.in=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByLangIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where lang is not null
        defaultMMovieAssetShouldBeFound("lang.specified=true");

        // Get all the mMovieAssetList where lang is null
        defaultMMovieAssetShouldNotBeFound("lang.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByLangIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where lang greater than or equals to DEFAULT_LANG
        defaultMMovieAssetShouldBeFound("lang.greaterOrEqualThan=" + DEFAULT_LANG);

        // Get all the mMovieAssetList where lang greater than or equals to UPDATED_LANG
        defaultMMovieAssetShouldNotBeFound("lang.greaterOrEqualThan=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByLangIsLessThanSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where lang less than or equals to DEFAULT_LANG
        defaultMMovieAssetShouldNotBeFound("lang.lessThan=" + DEFAULT_LANG);

        // Get all the mMovieAssetList where lang less than or equals to UPDATED_LANG
        defaultMMovieAssetShouldBeFound("lang.lessThan=" + UPDATED_LANG);
    }


    @Test
    @Transactional
    public void getAllMMovieAssetsBySizeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where size equals to DEFAULT_SIZE
        defaultMMovieAssetShouldBeFound("size.equals=" + DEFAULT_SIZE);

        // Get all the mMovieAssetList where size equals to UPDATED_SIZE
        defaultMMovieAssetShouldNotBeFound("size.equals=" + UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsBySizeIsInShouldWork() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where size in DEFAULT_SIZE or UPDATED_SIZE
        defaultMMovieAssetShouldBeFound("size.in=" + DEFAULT_SIZE + "," + UPDATED_SIZE);

        // Get all the mMovieAssetList where size equals to UPDATED_SIZE
        defaultMMovieAssetShouldNotBeFound("size.in=" + UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsBySizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where size is not null
        defaultMMovieAssetShouldBeFound("size.specified=true");

        // Get all the mMovieAssetList where size is null
        defaultMMovieAssetShouldNotBeFound("size.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsBySizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where size greater than or equals to DEFAULT_SIZE
        defaultMMovieAssetShouldBeFound("size.greaterOrEqualThan=" + DEFAULT_SIZE);

        // Get all the mMovieAssetList where size greater than or equals to UPDATED_SIZE
        defaultMMovieAssetShouldNotBeFound("size.greaterOrEqualThan=" + UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsBySizeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where size less than or equals to DEFAULT_SIZE
        defaultMMovieAssetShouldNotBeFound("size.lessThan=" + DEFAULT_SIZE);

        // Get all the mMovieAssetList where size less than or equals to UPDATED_SIZE
        defaultMMovieAssetShouldBeFound("size.lessThan=" + UPDATED_SIZE);
    }


    @Test
    @Transactional
    public void getAllMMovieAssetsByVersionIsEqualToSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where version equals to DEFAULT_VERSION
        defaultMMovieAssetShouldBeFound("version.equals=" + DEFAULT_VERSION);

        // Get all the mMovieAssetList where version equals to UPDATED_VERSION
        defaultMMovieAssetShouldNotBeFound("version.equals=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByVersionIsInShouldWork() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where version in DEFAULT_VERSION or UPDATED_VERSION
        defaultMMovieAssetShouldBeFound("version.in=" + DEFAULT_VERSION + "," + UPDATED_VERSION);

        // Get all the mMovieAssetList where version equals to UPDATED_VERSION
        defaultMMovieAssetShouldNotBeFound("version.in=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByVersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where version is not null
        defaultMMovieAssetShouldBeFound("version.specified=true");

        // Get all the mMovieAssetList where version is null
        defaultMMovieAssetShouldNotBeFound("version.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByVersionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where version greater than or equals to DEFAULT_VERSION
        defaultMMovieAssetShouldBeFound("version.greaterOrEqualThan=" + DEFAULT_VERSION);

        // Get all the mMovieAssetList where version greater than or equals to UPDATED_VERSION
        defaultMMovieAssetShouldNotBeFound("version.greaterOrEqualThan=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByVersionIsLessThanSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where version less than or equals to DEFAULT_VERSION
        defaultMMovieAssetShouldNotBeFound("version.lessThan=" + DEFAULT_VERSION);

        // Get all the mMovieAssetList where version less than or equals to UPDATED_VERSION
        defaultMMovieAssetShouldBeFound("version.lessThan=" + UPDATED_VERSION);
    }


    @Test
    @Transactional
    public void getAllMMovieAssetsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where type equals to DEFAULT_TYPE
        defaultMMovieAssetShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the mMovieAssetList where type equals to UPDATED_TYPE
        defaultMMovieAssetShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultMMovieAssetShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the mMovieAssetList where type equals to UPDATED_TYPE
        defaultMMovieAssetShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where type is not null
        defaultMMovieAssetShouldBeFound("type.specified=true");

        // Get all the mMovieAssetList where type is null
        defaultMMovieAssetShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where type greater than or equals to DEFAULT_TYPE
        defaultMMovieAssetShouldBeFound("type.greaterOrEqualThan=" + DEFAULT_TYPE);

        // Get all the mMovieAssetList where type greater than or equals to UPDATED_TYPE
        defaultMMovieAssetShouldNotBeFound("type.greaterOrEqualThan=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMovieAssetsByTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        // Get all the mMovieAssetList where type less than or equals to DEFAULT_TYPE
        defaultMMovieAssetShouldNotBeFound("type.lessThan=" + DEFAULT_TYPE);

        // Get all the mMovieAssetList where type less than or equals to UPDATED_TYPE
        defaultMMovieAssetShouldBeFound("type.lessThan=" + UPDATED_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMovieAssetShouldBeFound(String filter) throws Exception {
        restMMovieAssetMockMvc.perform(get("/api/m-movie-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMovieAsset.getId().intValue())))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));

        // Check, that the count call also returns 1
        restMMovieAssetMockMvc.perform(get("/api/m-movie-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMovieAssetShouldNotBeFound(String filter) throws Exception {
        restMMovieAssetMockMvc.perform(get("/api/m-movie-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMovieAssetMockMvc.perform(get("/api/m-movie-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMovieAsset() throws Exception {
        // Get the mMovieAsset
        restMMovieAssetMockMvc.perform(get("/api/m-movie-assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMovieAsset() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        int databaseSizeBeforeUpdate = mMovieAssetRepository.findAll().size();

        // Update the mMovieAsset
        MMovieAsset updatedMMovieAsset = mMovieAssetRepository.findById(mMovieAsset.getId()).get();
        // Disconnect from session so that the updates on updatedMMovieAsset are not directly saved in db
        em.detach(updatedMMovieAsset);
        updatedMMovieAsset
            .lang(UPDATED_LANG)
            .name(UPDATED_NAME)
            .size(UPDATED_SIZE)
            .version(UPDATED_VERSION)
            .type(UPDATED_TYPE);
        MMovieAssetDTO mMovieAssetDTO = mMovieAssetMapper.toDto(updatedMMovieAsset);

        restMMovieAssetMockMvc.perform(put("/api/m-movie-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMovieAssetDTO)))
            .andExpect(status().isOk());

        // Validate the MMovieAsset in the database
        List<MMovieAsset> mMovieAssetList = mMovieAssetRepository.findAll();
        assertThat(mMovieAssetList).hasSize(databaseSizeBeforeUpdate);
        MMovieAsset testMMovieAsset = mMovieAssetList.get(mMovieAssetList.size() - 1);
        assertThat(testMMovieAsset.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testMMovieAsset.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMMovieAsset.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testMMovieAsset.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMMovieAsset.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMMovieAsset() throws Exception {
        int databaseSizeBeforeUpdate = mMovieAssetRepository.findAll().size();

        // Create the MMovieAsset
        MMovieAssetDTO mMovieAssetDTO = mMovieAssetMapper.toDto(mMovieAsset);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMovieAssetMockMvc.perform(put("/api/m-movie-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMovieAssetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMovieAsset in the database
        List<MMovieAsset> mMovieAssetList = mMovieAssetRepository.findAll();
        assertThat(mMovieAssetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMovieAsset() throws Exception {
        // Initialize the database
        mMovieAssetRepository.saveAndFlush(mMovieAsset);

        int databaseSizeBeforeDelete = mMovieAssetRepository.findAll().size();

        // Delete the mMovieAsset
        restMMovieAssetMockMvc.perform(delete("/api/m-movie-assets/{id}", mMovieAsset.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMovieAsset> mMovieAssetList = mMovieAssetRepository.findAll();
        assertThat(mMovieAssetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMovieAsset.class);
        MMovieAsset mMovieAsset1 = new MMovieAsset();
        mMovieAsset1.setId(1L);
        MMovieAsset mMovieAsset2 = new MMovieAsset();
        mMovieAsset2.setId(mMovieAsset1.getId());
        assertThat(mMovieAsset1).isEqualTo(mMovieAsset2);
        mMovieAsset2.setId(2L);
        assertThat(mMovieAsset1).isNotEqualTo(mMovieAsset2);
        mMovieAsset1.setId(null);
        assertThat(mMovieAsset1).isNotEqualTo(mMovieAsset2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMovieAssetDTO.class);
        MMovieAssetDTO mMovieAssetDTO1 = new MMovieAssetDTO();
        mMovieAssetDTO1.setId(1L);
        MMovieAssetDTO mMovieAssetDTO2 = new MMovieAssetDTO();
        assertThat(mMovieAssetDTO1).isNotEqualTo(mMovieAssetDTO2);
        mMovieAssetDTO2.setId(mMovieAssetDTO1.getId());
        assertThat(mMovieAssetDTO1).isEqualTo(mMovieAssetDTO2);
        mMovieAssetDTO2.setId(2L);
        assertThat(mMovieAssetDTO1).isNotEqualTo(mMovieAssetDTO2);
        mMovieAssetDTO1.setId(null);
        assertThat(mMovieAssetDTO1).isNotEqualTo(mMovieAssetDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMovieAssetMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMovieAssetMapper.fromId(null)).isNull();
    }
}
