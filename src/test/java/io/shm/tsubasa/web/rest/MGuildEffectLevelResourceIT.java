package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuildEffectLevel;
import io.shm.tsubasa.repository.MGuildEffectLevelRepository;
import io.shm.tsubasa.service.MGuildEffectLevelService;
import io.shm.tsubasa.service.dto.MGuildEffectLevelDTO;
import io.shm.tsubasa.service.mapper.MGuildEffectLevelMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuildEffectLevelCriteria;
import io.shm.tsubasa.service.MGuildEffectLevelQueryService;

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
 * Integration tests for the {@Link MGuildEffectLevelResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuildEffectLevelResourceIT {

    private static final Integer DEFAULT_EFFECT_TYPE = 1;
    private static final Integer UPDATED_EFFECT_TYPE = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    private static final String DEFAULT_RATE_STR = "AAAAAAAAAA";
    private static final String UPDATED_RATE_STR = "BBBBBBBBBB";

    private static final Integer DEFAULT_COIN = 1;
    private static final Integer UPDATED_COIN = 2;

    private static final Integer DEFAULT_GUILD_LEVEL = 1;
    private static final Integer UPDATED_GUILD_LEVEL = 2;

    private static final Integer DEFAULT_GUILD_MEDAL = 1;
    private static final Integer UPDATED_GUILD_MEDAL = 2;

    @Autowired
    private MGuildEffectLevelRepository mGuildEffectLevelRepository;

    @Autowired
    private MGuildEffectLevelMapper mGuildEffectLevelMapper;

    @Autowired
    private MGuildEffectLevelService mGuildEffectLevelService;

    @Autowired
    private MGuildEffectLevelQueryService mGuildEffectLevelQueryService;

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

    private MockMvc restMGuildEffectLevelMockMvc;

    private MGuildEffectLevel mGuildEffectLevel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuildEffectLevelResource mGuildEffectLevelResource = new MGuildEffectLevelResource(mGuildEffectLevelService, mGuildEffectLevelQueryService);
        this.restMGuildEffectLevelMockMvc = MockMvcBuilders.standaloneSetup(mGuildEffectLevelResource)
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
    public static MGuildEffectLevel createEntity(EntityManager em) {
        MGuildEffectLevel mGuildEffectLevel = new MGuildEffectLevel()
            .effectType(DEFAULT_EFFECT_TYPE)
            .level(DEFAULT_LEVEL)
            .rate(DEFAULT_RATE)
            .rateStr(DEFAULT_RATE_STR)
            .coin(DEFAULT_COIN)
            .guildLevel(DEFAULT_GUILD_LEVEL)
            .guildMedal(DEFAULT_GUILD_MEDAL);
        return mGuildEffectLevel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuildEffectLevel createUpdatedEntity(EntityManager em) {
        MGuildEffectLevel mGuildEffectLevel = new MGuildEffectLevel()
            .effectType(UPDATED_EFFECT_TYPE)
            .level(UPDATED_LEVEL)
            .rate(UPDATED_RATE)
            .rateStr(UPDATED_RATE_STR)
            .coin(UPDATED_COIN)
            .guildLevel(UPDATED_GUILD_LEVEL)
            .guildMedal(UPDATED_GUILD_MEDAL);
        return mGuildEffectLevel;
    }

    @BeforeEach
    public void initTest() {
        mGuildEffectLevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuildEffectLevel() throws Exception {
        int databaseSizeBeforeCreate = mGuildEffectLevelRepository.findAll().size();

        // Create the MGuildEffectLevel
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(mGuildEffectLevel);
        restMGuildEffectLevelMockMvc.perform(post("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuildEffectLevel in the database
        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeCreate + 1);
        MGuildEffectLevel testMGuildEffectLevel = mGuildEffectLevelList.get(mGuildEffectLevelList.size() - 1);
        assertThat(testMGuildEffectLevel.getEffectType()).isEqualTo(DEFAULT_EFFECT_TYPE);
        assertThat(testMGuildEffectLevel.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMGuildEffectLevel.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testMGuildEffectLevel.getRateStr()).isEqualTo(DEFAULT_RATE_STR);
        assertThat(testMGuildEffectLevel.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMGuildEffectLevel.getGuildLevel()).isEqualTo(DEFAULT_GUILD_LEVEL);
        assertThat(testMGuildEffectLevel.getGuildMedal()).isEqualTo(DEFAULT_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void createMGuildEffectLevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuildEffectLevelRepository.findAll().size();

        // Create the MGuildEffectLevel with an existing ID
        mGuildEffectLevel.setId(1L);
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(mGuildEffectLevel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuildEffectLevelMockMvc.perform(post("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildEffectLevel in the database
        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildEffectLevelRepository.findAll().size();
        // set the field null
        mGuildEffectLevel.setEffectType(null);

        // Create the MGuildEffectLevel, which fails.
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(mGuildEffectLevel);

        restMGuildEffectLevelMockMvc.perform(post("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildEffectLevelRepository.findAll().size();
        // set the field null
        mGuildEffectLevel.setLevel(null);

        // Create the MGuildEffectLevel, which fails.
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(mGuildEffectLevel);

        restMGuildEffectLevelMockMvc.perform(post("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildEffectLevelRepository.findAll().size();
        // set the field null
        mGuildEffectLevel.setRate(null);

        // Create the MGuildEffectLevel, which fails.
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(mGuildEffectLevel);

        restMGuildEffectLevelMockMvc.perform(post("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildEffectLevelRepository.findAll().size();
        // set the field null
        mGuildEffectLevel.setCoin(null);

        // Create the MGuildEffectLevel, which fails.
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(mGuildEffectLevel);

        restMGuildEffectLevelMockMvc.perform(post("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildEffectLevelRepository.findAll().size();
        // set the field null
        mGuildEffectLevel.setGuildLevel(null);

        // Create the MGuildEffectLevel, which fails.
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(mGuildEffectLevel);

        restMGuildEffectLevelMockMvc.perform(post("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildMedalIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildEffectLevelRepository.findAll().size();
        // set the field null
        mGuildEffectLevel.setGuildMedal(null);

        // Create the MGuildEffectLevel, which fails.
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(mGuildEffectLevel);

        restMGuildEffectLevelMockMvc.perform(post("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevels() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList
        restMGuildEffectLevelMockMvc.perform(get("/api/m-guild-effect-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildEffectLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectType").value(hasItem(DEFAULT_EFFECT_TYPE)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].rateStr").value(hasItem(DEFAULT_RATE_STR.toString())))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].guildLevel").value(hasItem(DEFAULT_GUILD_LEVEL)))
            .andExpect(jsonPath("$.[*].guildMedal").value(hasItem(DEFAULT_GUILD_MEDAL)));
    }
    
    @Test
    @Transactional
    public void getMGuildEffectLevel() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get the mGuildEffectLevel
        restMGuildEffectLevelMockMvc.perform(get("/api/m-guild-effect-levels/{id}", mGuildEffectLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuildEffectLevel.getId().intValue()))
            .andExpect(jsonPath("$.effectType").value(DEFAULT_EFFECT_TYPE))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE))
            .andExpect(jsonPath("$.rateStr").value(DEFAULT_RATE_STR.toString()))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN))
            .andExpect(jsonPath("$.guildLevel").value(DEFAULT_GUILD_LEVEL))
            .andExpect(jsonPath("$.guildMedal").value(DEFAULT_GUILD_MEDAL));
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByEffectTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where effectType equals to DEFAULT_EFFECT_TYPE
        defaultMGuildEffectLevelShouldBeFound("effectType.equals=" + DEFAULT_EFFECT_TYPE);

        // Get all the mGuildEffectLevelList where effectType equals to UPDATED_EFFECT_TYPE
        defaultMGuildEffectLevelShouldNotBeFound("effectType.equals=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByEffectTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where effectType in DEFAULT_EFFECT_TYPE or UPDATED_EFFECT_TYPE
        defaultMGuildEffectLevelShouldBeFound("effectType.in=" + DEFAULT_EFFECT_TYPE + "," + UPDATED_EFFECT_TYPE);

        // Get all the mGuildEffectLevelList where effectType equals to UPDATED_EFFECT_TYPE
        defaultMGuildEffectLevelShouldNotBeFound("effectType.in=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByEffectTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where effectType is not null
        defaultMGuildEffectLevelShouldBeFound("effectType.specified=true");

        // Get all the mGuildEffectLevelList where effectType is null
        defaultMGuildEffectLevelShouldNotBeFound("effectType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByEffectTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where effectType greater than or equals to DEFAULT_EFFECT_TYPE
        defaultMGuildEffectLevelShouldBeFound("effectType.greaterOrEqualThan=" + DEFAULT_EFFECT_TYPE);

        // Get all the mGuildEffectLevelList where effectType greater than or equals to UPDATED_EFFECT_TYPE
        defaultMGuildEffectLevelShouldNotBeFound("effectType.greaterOrEqualThan=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByEffectTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where effectType less than or equals to DEFAULT_EFFECT_TYPE
        defaultMGuildEffectLevelShouldNotBeFound("effectType.lessThan=" + DEFAULT_EFFECT_TYPE);

        // Get all the mGuildEffectLevelList where effectType less than or equals to UPDATED_EFFECT_TYPE
        defaultMGuildEffectLevelShouldBeFound("effectType.lessThan=" + UPDATED_EFFECT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where level equals to DEFAULT_LEVEL
        defaultMGuildEffectLevelShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mGuildEffectLevelList where level equals to UPDATED_LEVEL
        defaultMGuildEffectLevelShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMGuildEffectLevelShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mGuildEffectLevelList where level equals to UPDATED_LEVEL
        defaultMGuildEffectLevelShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where level is not null
        defaultMGuildEffectLevelShouldBeFound("level.specified=true");

        // Get all the mGuildEffectLevelList where level is null
        defaultMGuildEffectLevelShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where level greater than or equals to DEFAULT_LEVEL
        defaultMGuildEffectLevelShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mGuildEffectLevelList where level greater than or equals to UPDATED_LEVEL
        defaultMGuildEffectLevelShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where level less than or equals to DEFAULT_LEVEL
        defaultMGuildEffectLevelShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mGuildEffectLevelList where level less than or equals to UPDATED_LEVEL
        defaultMGuildEffectLevelShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where rate equals to DEFAULT_RATE
        defaultMGuildEffectLevelShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the mGuildEffectLevelList where rate equals to UPDATED_RATE
        defaultMGuildEffectLevelShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByRateIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultMGuildEffectLevelShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the mGuildEffectLevelList where rate equals to UPDATED_RATE
        defaultMGuildEffectLevelShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where rate is not null
        defaultMGuildEffectLevelShouldBeFound("rate.specified=true");

        // Get all the mGuildEffectLevelList where rate is null
        defaultMGuildEffectLevelShouldNotBeFound("rate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where rate greater than or equals to DEFAULT_RATE
        defaultMGuildEffectLevelShouldBeFound("rate.greaterOrEqualThan=" + DEFAULT_RATE);

        // Get all the mGuildEffectLevelList where rate greater than or equals to UPDATED_RATE
        defaultMGuildEffectLevelShouldNotBeFound("rate.greaterOrEqualThan=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where rate less than or equals to DEFAULT_RATE
        defaultMGuildEffectLevelShouldNotBeFound("rate.lessThan=" + DEFAULT_RATE);

        // Get all the mGuildEffectLevelList where rate less than or equals to UPDATED_RATE
        defaultMGuildEffectLevelShouldBeFound("rate.lessThan=" + UPDATED_RATE);
    }


    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where coin equals to DEFAULT_COIN
        defaultMGuildEffectLevelShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mGuildEffectLevelList where coin equals to UPDATED_COIN
        defaultMGuildEffectLevelShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMGuildEffectLevelShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mGuildEffectLevelList where coin equals to UPDATED_COIN
        defaultMGuildEffectLevelShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where coin is not null
        defaultMGuildEffectLevelShouldBeFound("coin.specified=true");

        // Get all the mGuildEffectLevelList where coin is null
        defaultMGuildEffectLevelShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where coin greater than or equals to DEFAULT_COIN
        defaultMGuildEffectLevelShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mGuildEffectLevelList where coin greater than or equals to UPDATED_COIN
        defaultMGuildEffectLevelShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where coin less than or equals to DEFAULT_COIN
        defaultMGuildEffectLevelShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mGuildEffectLevelList where coin less than or equals to UPDATED_COIN
        defaultMGuildEffectLevelShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildLevel equals to DEFAULT_GUILD_LEVEL
        defaultMGuildEffectLevelShouldBeFound("guildLevel.equals=" + DEFAULT_GUILD_LEVEL);

        // Get all the mGuildEffectLevelList where guildLevel equals to UPDATED_GUILD_LEVEL
        defaultMGuildEffectLevelShouldNotBeFound("guildLevel.equals=" + UPDATED_GUILD_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildLevel in DEFAULT_GUILD_LEVEL or UPDATED_GUILD_LEVEL
        defaultMGuildEffectLevelShouldBeFound("guildLevel.in=" + DEFAULT_GUILD_LEVEL + "," + UPDATED_GUILD_LEVEL);

        // Get all the mGuildEffectLevelList where guildLevel equals to UPDATED_GUILD_LEVEL
        defaultMGuildEffectLevelShouldNotBeFound("guildLevel.in=" + UPDATED_GUILD_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildLevel is not null
        defaultMGuildEffectLevelShouldBeFound("guildLevel.specified=true");

        // Get all the mGuildEffectLevelList where guildLevel is null
        defaultMGuildEffectLevelShouldNotBeFound("guildLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildLevel greater than or equals to DEFAULT_GUILD_LEVEL
        defaultMGuildEffectLevelShouldBeFound("guildLevel.greaterOrEqualThan=" + DEFAULT_GUILD_LEVEL);

        // Get all the mGuildEffectLevelList where guildLevel greater than or equals to UPDATED_GUILD_LEVEL
        defaultMGuildEffectLevelShouldNotBeFound("guildLevel.greaterOrEqualThan=" + UPDATED_GUILD_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildLevel less than or equals to DEFAULT_GUILD_LEVEL
        defaultMGuildEffectLevelShouldNotBeFound("guildLevel.lessThan=" + DEFAULT_GUILD_LEVEL);

        // Get all the mGuildEffectLevelList where guildLevel less than or equals to UPDATED_GUILD_LEVEL
        defaultMGuildEffectLevelShouldBeFound("guildLevel.lessThan=" + UPDATED_GUILD_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildMedalIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildMedal equals to DEFAULT_GUILD_MEDAL
        defaultMGuildEffectLevelShouldBeFound("guildMedal.equals=" + DEFAULT_GUILD_MEDAL);

        // Get all the mGuildEffectLevelList where guildMedal equals to UPDATED_GUILD_MEDAL
        defaultMGuildEffectLevelShouldNotBeFound("guildMedal.equals=" + UPDATED_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildMedalIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildMedal in DEFAULT_GUILD_MEDAL or UPDATED_GUILD_MEDAL
        defaultMGuildEffectLevelShouldBeFound("guildMedal.in=" + DEFAULT_GUILD_MEDAL + "," + UPDATED_GUILD_MEDAL);

        // Get all the mGuildEffectLevelList where guildMedal equals to UPDATED_GUILD_MEDAL
        defaultMGuildEffectLevelShouldNotBeFound("guildMedal.in=" + UPDATED_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildMedalIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildMedal is not null
        defaultMGuildEffectLevelShouldBeFound("guildMedal.specified=true");

        // Get all the mGuildEffectLevelList where guildMedal is null
        defaultMGuildEffectLevelShouldNotBeFound("guildMedal.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildMedalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildMedal greater than or equals to DEFAULT_GUILD_MEDAL
        defaultMGuildEffectLevelShouldBeFound("guildMedal.greaterOrEqualThan=" + DEFAULT_GUILD_MEDAL);

        // Get all the mGuildEffectLevelList where guildMedal greater than or equals to UPDATED_GUILD_MEDAL
        defaultMGuildEffectLevelShouldNotBeFound("guildMedal.greaterOrEqualThan=" + UPDATED_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectLevelsByGuildMedalIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        // Get all the mGuildEffectLevelList where guildMedal less than or equals to DEFAULT_GUILD_MEDAL
        defaultMGuildEffectLevelShouldNotBeFound("guildMedal.lessThan=" + DEFAULT_GUILD_MEDAL);

        // Get all the mGuildEffectLevelList where guildMedal less than or equals to UPDATED_GUILD_MEDAL
        defaultMGuildEffectLevelShouldBeFound("guildMedal.lessThan=" + UPDATED_GUILD_MEDAL);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuildEffectLevelShouldBeFound(String filter) throws Exception {
        restMGuildEffectLevelMockMvc.perform(get("/api/m-guild-effect-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildEffectLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectType").value(hasItem(DEFAULT_EFFECT_TYPE)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].rateStr").value(hasItem(DEFAULT_RATE_STR.toString())))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].guildLevel").value(hasItem(DEFAULT_GUILD_LEVEL)))
            .andExpect(jsonPath("$.[*].guildMedal").value(hasItem(DEFAULT_GUILD_MEDAL)));

        // Check, that the count call also returns 1
        restMGuildEffectLevelMockMvc.perform(get("/api/m-guild-effect-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuildEffectLevelShouldNotBeFound(String filter) throws Exception {
        restMGuildEffectLevelMockMvc.perform(get("/api/m-guild-effect-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuildEffectLevelMockMvc.perform(get("/api/m-guild-effect-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuildEffectLevel() throws Exception {
        // Get the mGuildEffectLevel
        restMGuildEffectLevelMockMvc.perform(get("/api/m-guild-effect-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuildEffectLevel() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        int databaseSizeBeforeUpdate = mGuildEffectLevelRepository.findAll().size();

        // Update the mGuildEffectLevel
        MGuildEffectLevel updatedMGuildEffectLevel = mGuildEffectLevelRepository.findById(mGuildEffectLevel.getId()).get();
        // Disconnect from session so that the updates on updatedMGuildEffectLevel are not directly saved in db
        em.detach(updatedMGuildEffectLevel);
        updatedMGuildEffectLevel
            .effectType(UPDATED_EFFECT_TYPE)
            .level(UPDATED_LEVEL)
            .rate(UPDATED_RATE)
            .rateStr(UPDATED_RATE_STR)
            .coin(UPDATED_COIN)
            .guildLevel(UPDATED_GUILD_LEVEL)
            .guildMedal(UPDATED_GUILD_MEDAL);
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(updatedMGuildEffectLevel);

        restMGuildEffectLevelMockMvc.perform(put("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isOk());

        // Validate the MGuildEffectLevel in the database
        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeUpdate);
        MGuildEffectLevel testMGuildEffectLevel = mGuildEffectLevelList.get(mGuildEffectLevelList.size() - 1);
        assertThat(testMGuildEffectLevel.getEffectType()).isEqualTo(UPDATED_EFFECT_TYPE);
        assertThat(testMGuildEffectLevel.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMGuildEffectLevel.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testMGuildEffectLevel.getRateStr()).isEqualTo(UPDATED_RATE_STR);
        assertThat(testMGuildEffectLevel.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMGuildEffectLevel.getGuildLevel()).isEqualTo(UPDATED_GUILD_LEVEL);
        assertThat(testMGuildEffectLevel.getGuildMedal()).isEqualTo(UPDATED_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuildEffectLevel() throws Exception {
        int databaseSizeBeforeUpdate = mGuildEffectLevelRepository.findAll().size();

        // Create the MGuildEffectLevel
        MGuildEffectLevelDTO mGuildEffectLevelDTO = mGuildEffectLevelMapper.toDto(mGuildEffectLevel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuildEffectLevelMockMvc.perform(put("/api/m-guild-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildEffectLevel in the database
        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuildEffectLevel() throws Exception {
        // Initialize the database
        mGuildEffectLevelRepository.saveAndFlush(mGuildEffectLevel);

        int databaseSizeBeforeDelete = mGuildEffectLevelRepository.findAll().size();

        // Delete the mGuildEffectLevel
        restMGuildEffectLevelMockMvc.perform(delete("/api/m-guild-effect-levels/{id}", mGuildEffectLevel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuildEffectLevel> mGuildEffectLevelList = mGuildEffectLevelRepository.findAll();
        assertThat(mGuildEffectLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildEffectLevel.class);
        MGuildEffectLevel mGuildEffectLevel1 = new MGuildEffectLevel();
        mGuildEffectLevel1.setId(1L);
        MGuildEffectLevel mGuildEffectLevel2 = new MGuildEffectLevel();
        mGuildEffectLevel2.setId(mGuildEffectLevel1.getId());
        assertThat(mGuildEffectLevel1).isEqualTo(mGuildEffectLevel2);
        mGuildEffectLevel2.setId(2L);
        assertThat(mGuildEffectLevel1).isNotEqualTo(mGuildEffectLevel2);
        mGuildEffectLevel1.setId(null);
        assertThat(mGuildEffectLevel1).isNotEqualTo(mGuildEffectLevel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildEffectLevelDTO.class);
        MGuildEffectLevelDTO mGuildEffectLevelDTO1 = new MGuildEffectLevelDTO();
        mGuildEffectLevelDTO1.setId(1L);
        MGuildEffectLevelDTO mGuildEffectLevelDTO2 = new MGuildEffectLevelDTO();
        assertThat(mGuildEffectLevelDTO1).isNotEqualTo(mGuildEffectLevelDTO2);
        mGuildEffectLevelDTO2.setId(mGuildEffectLevelDTO1.getId());
        assertThat(mGuildEffectLevelDTO1).isEqualTo(mGuildEffectLevelDTO2);
        mGuildEffectLevelDTO2.setId(2L);
        assertThat(mGuildEffectLevelDTO1).isNotEqualTo(mGuildEffectLevelDTO2);
        mGuildEffectLevelDTO1.setId(null);
        assertThat(mGuildEffectLevelDTO1).isNotEqualTo(mGuildEffectLevelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuildEffectLevelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuildEffectLevelMapper.fromId(null)).isNull();
    }
}
