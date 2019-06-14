package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuildTopBanner;
import io.shm.tsubasa.repository.MGuildTopBannerRepository;
import io.shm.tsubasa.service.MGuildTopBannerService;
import io.shm.tsubasa.service.dto.MGuildTopBannerDTO;
import io.shm.tsubasa.service.mapper.MGuildTopBannerMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuildTopBannerCriteria;
import io.shm.tsubasa.service.MGuildTopBannerQueryService;

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
 * Integration tests for the {@Link MGuildTopBannerResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuildTopBannerResourceIT {

    private static final String DEFAULT_ASSET_PATH = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_PATH = "BBBBBBBBBB";

    private static final Integer DEFAULT_GUILD_BANNER_TYPE = 1;
    private static final Integer UPDATED_GUILD_BANNER_TYPE = 2;

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    @Autowired
    private MGuildTopBannerRepository mGuildTopBannerRepository;

    @Autowired
    private MGuildTopBannerMapper mGuildTopBannerMapper;

    @Autowired
    private MGuildTopBannerService mGuildTopBannerService;

    @Autowired
    private MGuildTopBannerQueryService mGuildTopBannerQueryService;

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

    private MockMvc restMGuildTopBannerMockMvc;

    private MGuildTopBanner mGuildTopBanner;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuildTopBannerResource mGuildTopBannerResource = new MGuildTopBannerResource(mGuildTopBannerService, mGuildTopBannerQueryService);
        this.restMGuildTopBannerMockMvc = MockMvcBuilders.standaloneSetup(mGuildTopBannerResource)
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
    public static MGuildTopBanner createEntity(EntityManager em) {
        MGuildTopBanner mGuildTopBanner = new MGuildTopBanner()
            .assetPath(DEFAULT_ASSET_PATH)
            .guildBannerType(DEFAULT_GUILD_BANNER_TYPE)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT);
        return mGuildTopBanner;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuildTopBanner createUpdatedEntity(EntityManager em) {
        MGuildTopBanner mGuildTopBanner = new MGuildTopBanner()
            .assetPath(UPDATED_ASSET_PATH)
            .guildBannerType(UPDATED_GUILD_BANNER_TYPE)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        return mGuildTopBanner;
    }

    @BeforeEach
    public void initTest() {
        mGuildTopBanner = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuildTopBanner() throws Exception {
        int databaseSizeBeforeCreate = mGuildTopBannerRepository.findAll().size();

        // Create the MGuildTopBanner
        MGuildTopBannerDTO mGuildTopBannerDTO = mGuildTopBannerMapper.toDto(mGuildTopBanner);
        restMGuildTopBannerMockMvc.perform(post("/api/m-guild-top-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildTopBannerDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuildTopBanner in the database
        List<MGuildTopBanner> mGuildTopBannerList = mGuildTopBannerRepository.findAll();
        assertThat(mGuildTopBannerList).hasSize(databaseSizeBeforeCreate + 1);
        MGuildTopBanner testMGuildTopBanner = mGuildTopBannerList.get(mGuildTopBannerList.size() - 1);
        assertThat(testMGuildTopBanner.getAssetPath()).isEqualTo(DEFAULT_ASSET_PATH);
        assertThat(testMGuildTopBanner.getGuildBannerType()).isEqualTo(DEFAULT_GUILD_BANNER_TYPE);
        assertThat(testMGuildTopBanner.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMGuildTopBanner.getEndAt()).isEqualTo(DEFAULT_END_AT);
    }

    @Test
    @Transactional
    public void createMGuildTopBannerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuildTopBannerRepository.findAll().size();

        // Create the MGuildTopBanner with an existing ID
        mGuildTopBanner.setId(1L);
        MGuildTopBannerDTO mGuildTopBannerDTO = mGuildTopBannerMapper.toDto(mGuildTopBanner);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuildTopBannerMockMvc.perform(post("/api/m-guild-top-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildTopBannerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildTopBanner in the database
        List<MGuildTopBanner> mGuildTopBannerList = mGuildTopBannerRepository.findAll();
        assertThat(mGuildTopBannerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGuildBannerTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildTopBannerRepository.findAll().size();
        // set the field null
        mGuildTopBanner.setGuildBannerType(null);

        // Create the MGuildTopBanner, which fails.
        MGuildTopBannerDTO mGuildTopBannerDTO = mGuildTopBannerMapper.toDto(mGuildTopBanner);

        restMGuildTopBannerMockMvc.perform(post("/api/m-guild-top-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildTopBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildTopBanner> mGuildTopBannerList = mGuildTopBannerRepository.findAll();
        assertThat(mGuildTopBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildTopBannerRepository.findAll().size();
        // set the field null
        mGuildTopBanner.setStartAt(null);

        // Create the MGuildTopBanner, which fails.
        MGuildTopBannerDTO mGuildTopBannerDTO = mGuildTopBannerMapper.toDto(mGuildTopBanner);

        restMGuildTopBannerMockMvc.perform(post("/api/m-guild-top-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildTopBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildTopBanner> mGuildTopBannerList = mGuildTopBannerRepository.findAll();
        assertThat(mGuildTopBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildTopBannerRepository.findAll().size();
        // set the field null
        mGuildTopBanner.setEndAt(null);

        // Create the MGuildTopBanner, which fails.
        MGuildTopBannerDTO mGuildTopBannerDTO = mGuildTopBannerMapper.toDto(mGuildTopBanner);

        restMGuildTopBannerMockMvc.perform(post("/api/m-guild-top-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildTopBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildTopBanner> mGuildTopBannerList = mGuildTopBannerRepository.findAll();
        assertThat(mGuildTopBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBanners() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList
        restMGuildTopBannerMockMvc.perform(get("/api/m-guild-top-banners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildTopBanner.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetPath").value(hasItem(DEFAULT_ASSET_PATH.toString())))
            .andExpect(jsonPath("$.[*].guildBannerType").value(hasItem(DEFAULT_GUILD_BANNER_TYPE)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));
    }
    
    @Test
    @Transactional
    public void getMGuildTopBanner() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get the mGuildTopBanner
        restMGuildTopBannerMockMvc.perform(get("/api/m-guild-top-banners/{id}", mGuildTopBanner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuildTopBanner.getId().intValue()))
            .andExpect(jsonPath("$.assetPath").value(DEFAULT_ASSET_PATH.toString()))
            .andExpect(jsonPath("$.guildBannerType").value(DEFAULT_GUILD_BANNER_TYPE))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT));
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByGuildBannerTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where guildBannerType equals to DEFAULT_GUILD_BANNER_TYPE
        defaultMGuildTopBannerShouldBeFound("guildBannerType.equals=" + DEFAULT_GUILD_BANNER_TYPE);

        // Get all the mGuildTopBannerList where guildBannerType equals to UPDATED_GUILD_BANNER_TYPE
        defaultMGuildTopBannerShouldNotBeFound("guildBannerType.equals=" + UPDATED_GUILD_BANNER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByGuildBannerTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where guildBannerType in DEFAULT_GUILD_BANNER_TYPE or UPDATED_GUILD_BANNER_TYPE
        defaultMGuildTopBannerShouldBeFound("guildBannerType.in=" + DEFAULT_GUILD_BANNER_TYPE + "," + UPDATED_GUILD_BANNER_TYPE);

        // Get all the mGuildTopBannerList where guildBannerType equals to UPDATED_GUILD_BANNER_TYPE
        defaultMGuildTopBannerShouldNotBeFound("guildBannerType.in=" + UPDATED_GUILD_BANNER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByGuildBannerTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where guildBannerType is not null
        defaultMGuildTopBannerShouldBeFound("guildBannerType.specified=true");

        // Get all the mGuildTopBannerList where guildBannerType is null
        defaultMGuildTopBannerShouldNotBeFound("guildBannerType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByGuildBannerTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where guildBannerType greater than or equals to DEFAULT_GUILD_BANNER_TYPE
        defaultMGuildTopBannerShouldBeFound("guildBannerType.greaterOrEqualThan=" + DEFAULT_GUILD_BANNER_TYPE);

        // Get all the mGuildTopBannerList where guildBannerType greater than or equals to UPDATED_GUILD_BANNER_TYPE
        defaultMGuildTopBannerShouldNotBeFound("guildBannerType.greaterOrEqualThan=" + UPDATED_GUILD_BANNER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByGuildBannerTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where guildBannerType less than or equals to DEFAULT_GUILD_BANNER_TYPE
        defaultMGuildTopBannerShouldNotBeFound("guildBannerType.lessThan=" + DEFAULT_GUILD_BANNER_TYPE);

        // Get all the mGuildTopBannerList where guildBannerType less than or equals to UPDATED_GUILD_BANNER_TYPE
        defaultMGuildTopBannerShouldBeFound("guildBannerType.lessThan=" + UPDATED_GUILD_BANNER_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGuildTopBannersByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where startAt equals to DEFAULT_START_AT
        defaultMGuildTopBannerShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mGuildTopBannerList where startAt equals to UPDATED_START_AT
        defaultMGuildTopBannerShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMGuildTopBannerShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mGuildTopBannerList where startAt equals to UPDATED_START_AT
        defaultMGuildTopBannerShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where startAt is not null
        defaultMGuildTopBannerShouldBeFound("startAt.specified=true");

        // Get all the mGuildTopBannerList where startAt is null
        defaultMGuildTopBannerShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where startAt greater than or equals to DEFAULT_START_AT
        defaultMGuildTopBannerShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mGuildTopBannerList where startAt greater than or equals to UPDATED_START_AT
        defaultMGuildTopBannerShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where startAt less than or equals to DEFAULT_START_AT
        defaultMGuildTopBannerShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mGuildTopBannerList where startAt less than or equals to UPDATED_START_AT
        defaultMGuildTopBannerShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMGuildTopBannersByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where endAt equals to DEFAULT_END_AT
        defaultMGuildTopBannerShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mGuildTopBannerList where endAt equals to UPDATED_END_AT
        defaultMGuildTopBannerShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMGuildTopBannerShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mGuildTopBannerList where endAt equals to UPDATED_END_AT
        defaultMGuildTopBannerShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where endAt is not null
        defaultMGuildTopBannerShouldBeFound("endAt.specified=true");

        // Get all the mGuildTopBannerList where endAt is null
        defaultMGuildTopBannerShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where endAt greater than or equals to DEFAULT_END_AT
        defaultMGuildTopBannerShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mGuildTopBannerList where endAt greater than or equals to UPDATED_END_AT
        defaultMGuildTopBannerShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMGuildTopBannersByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        // Get all the mGuildTopBannerList where endAt less than or equals to DEFAULT_END_AT
        defaultMGuildTopBannerShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mGuildTopBannerList where endAt less than or equals to UPDATED_END_AT
        defaultMGuildTopBannerShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuildTopBannerShouldBeFound(String filter) throws Exception {
        restMGuildTopBannerMockMvc.perform(get("/api/m-guild-top-banners?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildTopBanner.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetPath").value(hasItem(DEFAULT_ASSET_PATH.toString())))
            .andExpect(jsonPath("$.[*].guildBannerType").value(hasItem(DEFAULT_GUILD_BANNER_TYPE)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));

        // Check, that the count call also returns 1
        restMGuildTopBannerMockMvc.perform(get("/api/m-guild-top-banners/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuildTopBannerShouldNotBeFound(String filter) throws Exception {
        restMGuildTopBannerMockMvc.perform(get("/api/m-guild-top-banners?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuildTopBannerMockMvc.perform(get("/api/m-guild-top-banners/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuildTopBanner() throws Exception {
        // Get the mGuildTopBanner
        restMGuildTopBannerMockMvc.perform(get("/api/m-guild-top-banners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuildTopBanner() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        int databaseSizeBeforeUpdate = mGuildTopBannerRepository.findAll().size();

        // Update the mGuildTopBanner
        MGuildTopBanner updatedMGuildTopBanner = mGuildTopBannerRepository.findById(mGuildTopBanner.getId()).get();
        // Disconnect from session so that the updates on updatedMGuildTopBanner are not directly saved in db
        em.detach(updatedMGuildTopBanner);
        updatedMGuildTopBanner
            .assetPath(UPDATED_ASSET_PATH)
            .guildBannerType(UPDATED_GUILD_BANNER_TYPE)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        MGuildTopBannerDTO mGuildTopBannerDTO = mGuildTopBannerMapper.toDto(updatedMGuildTopBanner);

        restMGuildTopBannerMockMvc.perform(put("/api/m-guild-top-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildTopBannerDTO)))
            .andExpect(status().isOk());

        // Validate the MGuildTopBanner in the database
        List<MGuildTopBanner> mGuildTopBannerList = mGuildTopBannerRepository.findAll();
        assertThat(mGuildTopBannerList).hasSize(databaseSizeBeforeUpdate);
        MGuildTopBanner testMGuildTopBanner = mGuildTopBannerList.get(mGuildTopBannerList.size() - 1);
        assertThat(testMGuildTopBanner.getAssetPath()).isEqualTo(UPDATED_ASSET_PATH);
        assertThat(testMGuildTopBanner.getGuildBannerType()).isEqualTo(UPDATED_GUILD_BANNER_TYPE);
        assertThat(testMGuildTopBanner.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMGuildTopBanner.getEndAt()).isEqualTo(UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuildTopBanner() throws Exception {
        int databaseSizeBeforeUpdate = mGuildTopBannerRepository.findAll().size();

        // Create the MGuildTopBanner
        MGuildTopBannerDTO mGuildTopBannerDTO = mGuildTopBannerMapper.toDto(mGuildTopBanner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuildTopBannerMockMvc.perform(put("/api/m-guild-top-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildTopBannerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildTopBanner in the database
        List<MGuildTopBanner> mGuildTopBannerList = mGuildTopBannerRepository.findAll();
        assertThat(mGuildTopBannerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuildTopBanner() throws Exception {
        // Initialize the database
        mGuildTopBannerRepository.saveAndFlush(mGuildTopBanner);

        int databaseSizeBeforeDelete = mGuildTopBannerRepository.findAll().size();

        // Delete the mGuildTopBanner
        restMGuildTopBannerMockMvc.perform(delete("/api/m-guild-top-banners/{id}", mGuildTopBanner.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuildTopBanner> mGuildTopBannerList = mGuildTopBannerRepository.findAll();
        assertThat(mGuildTopBannerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildTopBanner.class);
        MGuildTopBanner mGuildTopBanner1 = new MGuildTopBanner();
        mGuildTopBanner1.setId(1L);
        MGuildTopBanner mGuildTopBanner2 = new MGuildTopBanner();
        mGuildTopBanner2.setId(mGuildTopBanner1.getId());
        assertThat(mGuildTopBanner1).isEqualTo(mGuildTopBanner2);
        mGuildTopBanner2.setId(2L);
        assertThat(mGuildTopBanner1).isNotEqualTo(mGuildTopBanner2);
        mGuildTopBanner1.setId(null);
        assertThat(mGuildTopBanner1).isNotEqualTo(mGuildTopBanner2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildTopBannerDTO.class);
        MGuildTopBannerDTO mGuildTopBannerDTO1 = new MGuildTopBannerDTO();
        mGuildTopBannerDTO1.setId(1L);
        MGuildTopBannerDTO mGuildTopBannerDTO2 = new MGuildTopBannerDTO();
        assertThat(mGuildTopBannerDTO1).isNotEqualTo(mGuildTopBannerDTO2);
        mGuildTopBannerDTO2.setId(mGuildTopBannerDTO1.getId());
        assertThat(mGuildTopBannerDTO1).isEqualTo(mGuildTopBannerDTO2);
        mGuildTopBannerDTO2.setId(2L);
        assertThat(mGuildTopBannerDTO1).isNotEqualTo(mGuildTopBannerDTO2);
        mGuildTopBannerDTO1.setId(null);
        assertThat(mGuildTopBannerDTO1).isNotEqualTo(mGuildTopBannerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuildTopBannerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuildTopBannerMapper.fromId(null)).isNull();
    }
}
