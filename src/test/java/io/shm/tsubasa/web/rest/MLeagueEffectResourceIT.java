package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLeagueEffect;
import io.shm.tsubasa.repository.MLeagueEffectRepository;
import io.shm.tsubasa.service.MLeagueEffectService;
import io.shm.tsubasa.service.dto.MLeagueEffectDTO;
import io.shm.tsubasa.service.mapper.MLeagueEffectMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLeagueEffectCriteria;
import io.shm.tsubasa.service.MLeagueEffectQueryService;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MLeagueEffectResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLeagueEffectResourceIT {

    private static final Integer DEFAULT_EFFECT_TYPE = 1;
    private static final Integer UPDATED_EFFECT_TYPE = 2;

    private static final Integer DEFAULT_LEAGUE_HIERARCHY = 1;
    private static final Integer UPDATED_LEAGUE_HIERARCHY = 2;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    private static final Integer DEFAULT_PRICE = 1;
    private static final Integer UPDATED_PRICE = 2;

    @Autowired
    private MLeagueEffectRepository mLeagueEffectRepository;

    @Autowired
    private MLeagueEffectMapper mLeagueEffectMapper;

    @Autowired
    private MLeagueEffectService mLeagueEffectService;

    @Autowired
    private MLeagueEffectQueryService mLeagueEffectQueryService;

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

    private MockMvc restMLeagueEffectMockMvc;

    private MLeagueEffect mLeagueEffect;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLeagueEffectResource mLeagueEffectResource = new MLeagueEffectResource(mLeagueEffectService, mLeagueEffectQueryService);
        this.restMLeagueEffectMockMvc = MockMvcBuilders.standaloneSetup(mLeagueEffectResource)
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
    public static MLeagueEffect createEntity(EntityManager em) {
        MLeagueEffect mLeagueEffect = new MLeagueEffect()
            .effectType(DEFAULT_EFFECT_TYPE)
            .leagueHierarchy(DEFAULT_LEAGUE_HIERARCHY)
            .rate(DEFAULT_RATE)
            .price(DEFAULT_PRICE);
        return mLeagueEffect;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLeagueEffect createUpdatedEntity(EntityManager em) {
        MLeagueEffect mLeagueEffect = new MLeagueEffect()
            .effectType(UPDATED_EFFECT_TYPE)
            .leagueHierarchy(UPDATED_LEAGUE_HIERARCHY)
            .rate(UPDATED_RATE)
            .price(UPDATED_PRICE);
        return mLeagueEffect;
    }

    @BeforeEach
    public void initTest() {
        mLeagueEffect = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLeagueEffect() throws Exception {
        int databaseSizeBeforeCreate = mLeagueEffectRepository.findAll().size();

        // Create the MLeagueEffect
        MLeagueEffectDTO mLeagueEffectDTO = mLeagueEffectMapper.toDto(mLeagueEffect);
        restMLeagueEffectMockMvc.perform(post("/api/m-league-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueEffectDTO)))
            .andExpect(status().isCreated());

        // Validate the MLeagueEffect in the database
        List<MLeagueEffect> mLeagueEffectList = mLeagueEffectRepository.findAll();
        assertThat(mLeagueEffectList).hasSize(databaseSizeBeforeCreate + 1);
        MLeagueEffect testMLeagueEffect = mLeagueEffectList.get(mLeagueEffectList.size() - 1);
        assertThat(testMLeagueEffect.getEffectType()).isEqualTo(DEFAULT_EFFECT_TYPE);
        assertThat(testMLeagueEffect.getLeagueHierarchy()).isEqualTo(DEFAULT_LEAGUE_HIERARCHY);
        assertThat(testMLeagueEffect.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testMLeagueEffect.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void createMLeagueEffectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLeagueEffectRepository.findAll().size();

        // Create the MLeagueEffect with an existing ID
        mLeagueEffect.setId(1L);
        MLeagueEffectDTO mLeagueEffectDTO = mLeagueEffectMapper.toDto(mLeagueEffect);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLeagueEffectMockMvc.perform(post("/api/m-league-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueEffectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueEffect in the database
        List<MLeagueEffect> mLeagueEffectList = mLeagueEffectRepository.findAll();
        assertThat(mLeagueEffectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueEffectRepository.findAll().size();
        // set the field null
        mLeagueEffect.setEffectType(null);

        // Create the MLeagueEffect, which fails.
        MLeagueEffectDTO mLeagueEffectDTO = mLeagueEffectMapper.toDto(mLeagueEffect);

        restMLeagueEffectMockMvc.perform(post("/api/m-league-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueEffectDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueEffect> mLeagueEffectList = mLeagueEffectRepository.findAll();
        assertThat(mLeagueEffectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLeagueHierarchyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueEffectRepository.findAll().size();
        // set the field null
        mLeagueEffect.setLeagueHierarchy(null);

        // Create the MLeagueEffect, which fails.
        MLeagueEffectDTO mLeagueEffectDTO = mLeagueEffectMapper.toDto(mLeagueEffect);

        restMLeagueEffectMockMvc.perform(post("/api/m-league-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueEffectDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueEffect> mLeagueEffectList = mLeagueEffectRepository.findAll();
        assertThat(mLeagueEffectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueEffectRepository.findAll().size();
        // set the field null
        mLeagueEffect.setRate(null);

        // Create the MLeagueEffect, which fails.
        MLeagueEffectDTO mLeagueEffectDTO = mLeagueEffectMapper.toDto(mLeagueEffect);

        restMLeagueEffectMockMvc.perform(post("/api/m-league-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueEffectDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueEffect> mLeagueEffectList = mLeagueEffectRepository.findAll();
        assertThat(mLeagueEffectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLeagueEffectRepository.findAll().size();
        // set the field null
        mLeagueEffect.setPrice(null);

        // Create the MLeagueEffect, which fails.
        MLeagueEffectDTO mLeagueEffectDTO = mLeagueEffectMapper.toDto(mLeagueEffect);

        restMLeagueEffectMockMvc.perform(post("/api/m-league-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueEffectDTO)))
            .andExpect(status().isBadRequest());

        List<MLeagueEffect> mLeagueEffectList = mLeagueEffectRepository.findAll();
        assertThat(mLeagueEffectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffects() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList
        restMLeagueEffectMockMvc.perform(get("/api/m-league-effects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueEffect.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectType").value(hasItem(DEFAULT_EFFECT_TYPE)))
            .andExpect(jsonPath("$.[*].leagueHierarchy").value(hasItem(DEFAULT_LEAGUE_HIERARCHY)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)));
    }
    
    @Test
    @Transactional
    public void getMLeagueEffect() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get the mLeagueEffect
        restMLeagueEffectMockMvc.perform(get("/api/m-league-effects/{id}", mLeagueEffect.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLeagueEffect.getId().intValue()))
            .andExpect(jsonPath("$.effectType").value(DEFAULT_EFFECT_TYPE))
            .andExpect(jsonPath("$.leagueHierarchy").value(DEFAULT_LEAGUE_HIERARCHY))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE));
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByEffectTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where effectType equals to DEFAULT_EFFECT_TYPE
        defaultMLeagueEffectShouldBeFound("effectType.equals=" + DEFAULT_EFFECT_TYPE);

        // Get all the mLeagueEffectList where effectType equals to UPDATED_EFFECT_TYPE
        defaultMLeagueEffectShouldNotBeFound("effectType.equals=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByEffectTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where effectType in DEFAULT_EFFECT_TYPE or UPDATED_EFFECT_TYPE
        defaultMLeagueEffectShouldBeFound("effectType.in=" + DEFAULT_EFFECT_TYPE + "," + UPDATED_EFFECT_TYPE);

        // Get all the mLeagueEffectList where effectType equals to UPDATED_EFFECT_TYPE
        defaultMLeagueEffectShouldNotBeFound("effectType.in=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByEffectTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where effectType is not null
        defaultMLeagueEffectShouldBeFound("effectType.specified=true");

        // Get all the mLeagueEffectList where effectType is null
        defaultMLeagueEffectShouldNotBeFound("effectType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByEffectTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where effectType greater than or equals to DEFAULT_EFFECT_TYPE
        defaultMLeagueEffectShouldBeFound("effectType.greaterOrEqualThan=" + DEFAULT_EFFECT_TYPE);

        // Get all the mLeagueEffectList where effectType greater than or equals to UPDATED_EFFECT_TYPE
        defaultMLeagueEffectShouldNotBeFound("effectType.greaterOrEqualThan=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByEffectTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where effectType less than or equals to DEFAULT_EFFECT_TYPE
        defaultMLeagueEffectShouldNotBeFound("effectType.lessThan=" + DEFAULT_EFFECT_TYPE);

        // Get all the mLeagueEffectList where effectType less than or equals to UPDATED_EFFECT_TYPE
        defaultMLeagueEffectShouldBeFound("effectType.lessThan=" + UPDATED_EFFECT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMLeagueEffectsByLeagueHierarchyIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where leagueHierarchy equals to DEFAULT_LEAGUE_HIERARCHY
        defaultMLeagueEffectShouldBeFound("leagueHierarchy.equals=" + DEFAULT_LEAGUE_HIERARCHY);

        // Get all the mLeagueEffectList where leagueHierarchy equals to UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueEffectShouldNotBeFound("leagueHierarchy.equals=" + UPDATED_LEAGUE_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByLeagueHierarchyIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where leagueHierarchy in DEFAULT_LEAGUE_HIERARCHY or UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueEffectShouldBeFound("leagueHierarchy.in=" + DEFAULT_LEAGUE_HIERARCHY + "," + UPDATED_LEAGUE_HIERARCHY);

        // Get all the mLeagueEffectList where leagueHierarchy equals to UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueEffectShouldNotBeFound("leagueHierarchy.in=" + UPDATED_LEAGUE_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByLeagueHierarchyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where leagueHierarchy is not null
        defaultMLeagueEffectShouldBeFound("leagueHierarchy.specified=true");

        // Get all the mLeagueEffectList where leagueHierarchy is null
        defaultMLeagueEffectShouldNotBeFound("leagueHierarchy.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByLeagueHierarchyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where leagueHierarchy greater than or equals to DEFAULT_LEAGUE_HIERARCHY
        defaultMLeagueEffectShouldBeFound("leagueHierarchy.greaterOrEqualThan=" + DEFAULT_LEAGUE_HIERARCHY);

        // Get all the mLeagueEffectList where leagueHierarchy greater than or equals to UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueEffectShouldNotBeFound("leagueHierarchy.greaterOrEqualThan=" + UPDATED_LEAGUE_HIERARCHY);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByLeagueHierarchyIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where leagueHierarchy less than or equals to DEFAULT_LEAGUE_HIERARCHY
        defaultMLeagueEffectShouldNotBeFound("leagueHierarchy.lessThan=" + DEFAULT_LEAGUE_HIERARCHY);

        // Get all the mLeagueEffectList where leagueHierarchy less than or equals to UPDATED_LEAGUE_HIERARCHY
        defaultMLeagueEffectShouldBeFound("leagueHierarchy.lessThan=" + UPDATED_LEAGUE_HIERARCHY);
    }


    @Test
    @Transactional
    public void getAllMLeagueEffectsByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where rate equals to DEFAULT_RATE
        defaultMLeagueEffectShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the mLeagueEffectList where rate equals to UPDATED_RATE
        defaultMLeagueEffectShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByRateIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultMLeagueEffectShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the mLeagueEffectList where rate equals to UPDATED_RATE
        defaultMLeagueEffectShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where rate is not null
        defaultMLeagueEffectShouldBeFound("rate.specified=true");

        // Get all the mLeagueEffectList where rate is null
        defaultMLeagueEffectShouldNotBeFound("rate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where rate greater than or equals to DEFAULT_RATE
        defaultMLeagueEffectShouldBeFound("rate.greaterOrEqualThan=" + DEFAULT_RATE);

        // Get all the mLeagueEffectList where rate greater than or equals to UPDATED_RATE
        defaultMLeagueEffectShouldNotBeFound("rate.greaterOrEqualThan=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where rate less than or equals to DEFAULT_RATE
        defaultMLeagueEffectShouldNotBeFound("rate.lessThan=" + DEFAULT_RATE);

        // Get all the mLeagueEffectList where rate less than or equals to UPDATED_RATE
        defaultMLeagueEffectShouldBeFound("rate.lessThan=" + UPDATED_RATE);
    }


    @Test
    @Transactional
    public void getAllMLeagueEffectsByPriceIsEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where price equals to DEFAULT_PRICE
        defaultMLeagueEffectShouldBeFound("price.equals=" + DEFAULT_PRICE);

        // Get all the mLeagueEffectList where price equals to UPDATED_PRICE
        defaultMLeagueEffectShouldNotBeFound("price.equals=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByPriceIsInShouldWork() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where price in DEFAULT_PRICE or UPDATED_PRICE
        defaultMLeagueEffectShouldBeFound("price.in=" + DEFAULT_PRICE + "," + UPDATED_PRICE);

        // Get all the mLeagueEffectList where price equals to UPDATED_PRICE
        defaultMLeagueEffectShouldNotBeFound("price.in=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByPriceIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where price is not null
        defaultMLeagueEffectShouldBeFound("price.specified=true");

        // Get all the mLeagueEffectList where price is null
        defaultMLeagueEffectShouldNotBeFound("price.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByPriceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where price greater than or equals to DEFAULT_PRICE
        defaultMLeagueEffectShouldBeFound("price.greaterOrEqualThan=" + DEFAULT_PRICE);

        // Get all the mLeagueEffectList where price greater than or equals to UPDATED_PRICE
        defaultMLeagueEffectShouldNotBeFound("price.greaterOrEqualThan=" + UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void getAllMLeagueEffectsByPriceIsLessThanSomething() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        // Get all the mLeagueEffectList where price less than or equals to DEFAULT_PRICE
        defaultMLeagueEffectShouldNotBeFound("price.lessThan=" + DEFAULT_PRICE);

        // Get all the mLeagueEffectList where price less than or equals to UPDATED_PRICE
        defaultMLeagueEffectShouldBeFound("price.lessThan=" + UPDATED_PRICE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLeagueEffectShouldBeFound(String filter) throws Exception {
        restMLeagueEffectMockMvc.perform(get("/api/m-league-effects?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLeagueEffect.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectType").value(hasItem(DEFAULT_EFFECT_TYPE)))
            .andExpect(jsonPath("$.[*].leagueHierarchy").value(hasItem(DEFAULT_LEAGUE_HIERARCHY)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)));

        // Check, that the count call also returns 1
        restMLeagueEffectMockMvc.perform(get("/api/m-league-effects/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLeagueEffectShouldNotBeFound(String filter) throws Exception {
        restMLeagueEffectMockMvc.perform(get("/api/m-league-effects?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLeagueEffectMockMvc.perform(get("/api/m-league-effects/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLeagueEffect() throws Exception {
        // Get the mLeagueEffect
        restMLeagueEffectMockMvc.perform(get("/api/m-league-effects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLeagueEffect() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        int databaseSizeBeforeUpdate = mLeagueEffectRepository.findAll().size();

        // Update the mLeagueEffect
        MLeagueEffect updatedMLeagueEffect = mLeagueEffectRepository.findById(mLeagueEffect.getId()).get();
        // Disconnect from session so that the updates on updatedMLeagueEffect are not directly saved in db
        em.detach(updatedMLeagueEffect);
        updatedMLeagueEffect
            .effectType(UPDATED_EFFECT_TYPE)
            .leagueHierarchy(UPDATED_LEAGUE_HIERARCHY)
            .rate(UPDATED_RATE)
            .price(UPDATED_PRICE);
        MLeagueEffectDTO mLeagueEffectDTO = mLeagueEffectMapper.toDto(updatedMLeagueEffect);

        restMLeagueEffectMockMvc.perform(put("/api/m-league-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueEffectDTO)))
            .andExpect(status().isOk());

        // Validate the MLeagueEffect in the database
        List<MLeagueEffect> mLeagueEffectList = mLeagueEffectRepository.findAll();
        assertThat(mLeagueEffectList).hasSize(databaseSizeBeforeUpdate);
        MLeagueEffect testMLeagueEffect = mLeagueEffectList.get(mLeagueEffectList.size() - 1);
        assertThat(testMLeagueEffect.getEffectType()).isEqualTo(UPDATED_EFFECT_TYPE);
        assertThat(testMLeagueEffect.getLeagueHierarchy()).isEqualTo(UPDATED_LEAGUE_HIERARCHY);
        assertThat(testMLeagueEffect.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testMLeagueEffect.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingMLeagueEffect() throws Exception {
        int databaseSizeBeforeUpdate = mLeagueEffectRepository.findAll().size();

        // Create the MLeagueEffect
        MLeagueEffectDTO mLeagueEffectDTO = mLeagueEffectMapper.toDto(mLeagueEffect);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLeagueEffectMockMvc.perform(put("/api/m-league-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLeagueEffectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLeagueEffect in the database
        List<MLeagueEffect> mLeagueEffectList = mLeagueEffectRepository.findAll();
        assertThat(mLeagueEffectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLeagueEffect() throws Exception {
        // Initialize the database
        mLeagueEffectRepository.saveAndFlush(mLeagueEffect);

        int databaseSizeBeforeDelete = mLeagueEffectRepository.findAll().size();

        // Delete the mLeagueEffect
        restMLeagueEffectMockMvc.perform(delete("/api/m-league-effects/{id}", mLeagueEffect.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLeagueEffect> mLeagueEffectList = mLeagueEffectRepository.findAll();
        assertThat(mLeagueEffectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueEffect.class);
        MLeagueEffect mLeagueEffect1 = new MLeagueEffect();
        mLeagueEffect1.setId(1L);
        MLeagueEffect mLeagueEffect2 = new MLeagueEffect();
        mLeagueEffect2.setId(mLeagueEffect1.getId());
        assertThat(mLeagueEffect1).isEqualTo(mLeagueEffect2);
        mLeagueEffect2.setId(2L);
        assertThat(mLeagueEffect1).isNotEqualTo(mLeagueEffect2);
        mLeagueEffect1.setId(null);
        assertThat(mLeagueEffect1).isNotEqualTo(mLeagueEffect2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLeagueEffectDTO.class);
        MLeagueEffectDTO mLeagueEffectDTO1 = new MLeagueEffectDTO();
        mLeagueEffectDTO1.setId(1L);
        MLeagueEffectDTO mLeagueEffectDTO2 = new MLeagueEffectDTO();
        assertThat(mLeagueEffectDTO1).isNotEqualTo(mLeagueEffectDTO2);
        mLeagueEffectDTO2.setId(mLeagueEffectDTO1.getId());
        assertThat(mLeagueEffectDTO1).isEqualTo(mLeagueEffectDTO2);
        mLeagueEffectDTO2.setId(2L);
        assertThat(mLeagueEffectDTO1).isNotEqualTo(mLeagueEffectDTO2);
        mLeagueEffectDTO1.setId(null);
        assertThat(mLeagueEffectDTO1).isNotEqualTo(mLeagueEffectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLeagueEffectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLeagueEffectMapper.fromId(null)).isNull();
    }
}
