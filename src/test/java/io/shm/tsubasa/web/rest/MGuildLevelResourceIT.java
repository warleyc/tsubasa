package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuildLevel;
import io.shm.tsubasa.repository.MGuildLevelRepository;
import io.shm.tsubasa.service.MGuildLevelService;
import io.shm.tsubasa.service.dto.MGuildLevelDTO;
import io.shm.tsubasa.service.mapper.MGuildLevelMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuildLevelCriteria;
import io.shm.tsubasa.service.MGuildLevelQueryService;

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
 * Integration tests for the {@Link MGuildLevelResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuildLevelResourceIT {

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_EXP = 1;
    private static final Integer UPDATED_EXP = 2;

    private static final Integer DEFAULT_MEMBER_COUNT = 1;
    private static final Integer UPDATED_MEMBER_COUNT = 2;

    private static final Integer DEFAULT_RECOMMEND_MEMBER_COUNT = 1;
    private static final Integer UPDATED_RECOMMEND_MEMBER_COUNT = 2;

    private static final Integer DEFAULT_GUILD_MEDAL = 1;
    private static final Integer UPDATED_GUILD_MEDAL = 2;

    @Autowired
    private MGuildLevelRepository mGuildLevelRepository;

    @Autowired
    private MGuildLevelMapper mGuildLevelMapper;

    @Autowired
    private MGuildLevelService mGuildLevelService;

    @Autowired
    private MGuildLevelQueryService mGuildLevelQueryService;

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

    private MockMvc restMGuildLevelMockMvc;

    private MGuildLevel mGuildLevel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuildLevelResource mGuildLevelResource = new MGuildLevelResource(mGuildLevelService, mGuildLevelQueryService);
        this.restMGuildLevelMockMvc = MockMvcBuilders.standaloneSetup(mGuildLevelResource)
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
    public static MGuildLevel createEntity(EntityManager em) {
        MGuildLevel mGuildLevel = new MGuildLevel()
            .level(DEFAULT_LEVEL)
            .exp(DEFAULT_EXP)
            .memberCount(DEFAULT_MEMBER_COUNT)
            .recommendMemberCount(DEFAULT_RECOMMEND_MEMBER_COUNT)
            .guildMedal(DEFAULT_GUILD_MEDAL);
        return mGuildLevel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuildLevel createUpdatedEntity(EntityManager em) {
        MGuildLevel mGuildLevel = new MGuildLevel()
            .level(UPDATED_LEVEL)
            .exp(UPDATED_EXP)
            .memberCount(UPDATED_MEMBER_COUNT)
            .recommendMemberCount(UPDATED_RECOMMEND_MEMBER_COUNT)
            .guildMedal(UPDATED_GUILD_MEDAL);
        return mGuildLevel;
    }

    @BeforeEach
    public void initTest() {
        mGuildLevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuildLevel() throws Exception {
        int databaseSizeBeforeCreate = mGuildLevelRepository.findAll().size();

        // Create the MGuildLevel
        MGuildLevelDTO mGuildLevelDTO = mGuildLevelMapper.toDto(mGuildLevel);
        restMGuildLevelMockMvc.perform(post("/api/m-guild-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildLevelDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuildLevel in the database
        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeCreate + 1);
        MGuildLevel testMGuildLevel = mGuildLevelList.get(mGuildLevelList.size() - 1);
        assertThat(testMGuildLevel.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMGuildLevel.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMGuildLevel.getMemberCount()).isEqualTo(DEFAULT_MEMBER_COUNT);
        assertThat(testMGuildLevel.getRecommendMemberCount()).isEqualTo(DEFAULT_RECOMMEND_MEMBER_COUNT);
        assertThat(testMGuildLevel.getGuildMedal()).isEqualTo(DEFAULT_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void createMGuildLevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuildLevelRepository.findAll().size();

        // Create the MGuildLevel with an existing ID
        mGuildLevel.setId(1L);
        MGuildLevelDTO mGuildLevelDTO = mGuildLevelMapper.toDto(mGuildLevel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuildLevelMockMvc.perform(post("/api/m-guild-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildLevel in the database
        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildLevelRepository.findAll().size();
        // set the field null
        mGuildLevel.setLevel(null);

        // Create the MGuildLevel, which fails.
        MGuildLevelDTO mGuildLevelDTO = mGuildLevelMapper.toDto(mGuildLevel);

        restMGuildLevelMockMvc.perform(post("/api/m-guild-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildLevelRepository.findAll().size();
        // set the field null
        mGuildLevel.setExp(null);

        // Create the MGuildLevel, which fails.
        MGuildLevelDTO mGuildLevelDTO = mGuildLevelMapper.toDto(mGuildLevel);

        restMGuildLevelMockMvc.perform(post("/api/m-guild-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMemberCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildLevelRepository.findAll().size();
        // set the field null
        mGuildLevel.setMemberCount(null);

        // Create the MGuildLevel, which fails.
        MGuildLevelDTO mGuildLevelDTO = mGuildLevelMapper.toDto(mGuildLevel);

        restMGuildLevelMockMvc.perform(post("/api/m-guild-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRecommendMemberCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildLevelRepository.findAll().size();
        // set the field null
        mGuildLevel.setRecommendMemberCount(null);

        // Create the MGuildLevel, which fails.
        MGuildLevelDTO mGuildLevelDTO = mGuildLevelMapper.toDto(mGuildLevel);

        restMGuildLevelMockMvc.perform(post("/api/m-guild-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGuildMedalIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildLevelRepository.findAll().size();
        // set the field null
        mGuildLevel.setGuildMedal(null);

        // Create the MGuildLevel, which fails.
        MGuildLevelDTO mGuildLevelDTO = mGuildLevelMapper.toDto(mGuildLevel);

        restMGuildLevelMockMvc.perform(post("/api/m-guild-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuildLevels() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList
        restMGuildLevelMockMvc.perform(get("/api/m-guild-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)))
            .andExpect(jsonPath("$.[*].memberCount").value(hasItem(DEFAULT_MEMBER_COUNT)))
            .andExpect(jsonPath("$.[*].recommendMemberCount").value(hasItem(DEFAULT_RECOMMEND_MEMBER_COUNT)))
            .andExpect(jsonPath("$.[*].guildMedal").value(hasItem(DEFAULT_GUILD_MEDAL)));
    }
    
    @Test
    @Transactional
    public void getMGuildLevel() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get the mGuildLevel
        restMGuildLevelMockMvc.perform(get("/api/m-guild-levels/{id}", mGuildLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuildLevel.getId().intValue()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.exp").value(DEFAULT_EXP))
            .andExpect(jsonPath("$.memberCount").value(DEFAULT_MEMBER_COUNT))
            .andExpect(jsonPath("$.recommendMemberCount").value(DEFAULT_RECOMMEND_MEMBER_COUNT))
            .andExpect(jsonPath("$.guildMedal").value(DEFAULT_GUILD_MEDAL));
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where level equals to DEFAULT_LEVEL
        defaultMGuildLevelShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mGuildLevelList where level equals to UPDATED_LEVEL
        defaultMGuildLevelShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMGuildLevelShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mGuildLevelList where level equals to UPDATED_LEVEL
        defaultMGuildLevelShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where level is not null
        defaultMGuildLevelShouldBeFound("level.specified=true");

        // Get all the mGuildLevelList where level is null
        defaultMGuildLevelShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where level greater than or equals to DEFAULT_LEVEL
        defaultMGuildLevelShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mGuildLevelList where level greater than or equals to UPDATED_LEVEL
        defaultMGuildLevelShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where level less than or equals to DEFAULT_LEVEL
        defaultMGuildLevelShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mGuildLevelList where level less than or equals to UPDATED_LEVEL
        defaultMGuildLevelShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMGuildLevelsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where exp equals to DEFAULT_EXP
        defaultMGuildLevelShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mGuildLevelList where exp equals to UPDATED_EXP
        defaultMGuildLevelShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMGuildLevelShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mGuildLevelList where exp equals to UPDATED_EXP
        defaultMGuildLevelShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where exp is not null
        defaultMGuildLevelShouldBeFound("exp.specified=true");

        // Get all the mGuildLevelList where exp is null
        defaultMGuildLevelShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where exp greater than or equals to DEFAULT_EXP
        defaultMGuildLevelShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mGuildLevelList where exp greater than or equals to UPDATED_EXP
        defaultMGuildLevelShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where exp less than or equals to DEFAULT_EXP
        defaultMGuildLevelShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mGuildLevelList where exp less than or equals to UPDATED_EXP
        defaultMGuildLevelShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMGuildLevelsByMemberCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where memberCount equals to DEFAULT_MEMBER_COUNT
        defaultMGuildLevelShouldBeFound("memberCount.equals=" + DEFAULT_MEMBER_COUNT);

        // Get all the mGuildLevelList where memberCount equals to UPDATED_MEMBER_COUNT
        defaultMGuildLevelShouldNotBeFound("memberCount.equals=" + UPDATED_MEMBER_COUNT);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByMemberCountIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where memberCount in DEFAULT_MEMBER_COUNT or UPDATED_MEMBER_COUNT
        defaultMGuildLevelShouldBeFound("memberCount.in=" + DEFAULT_MEMBER_COUNT + "," + UPDATED_MEMBER_COUNT);

        // Get all the mGuildLevelList where memberCount equals to UPDATED_MEMBER_COUNT
        defaultMGuildLevelShouldNotBeFound("memberCount.in=" + UPDATED_MEMBER_COUNT);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByMemberCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where memberCount is not null
        defaultMGuildLevelShouldBeFound("memberCount.specified=true");

        // Get all the mGuildLevelList where memberCount is null
        defaultMGuildLevelShouldNotBeFound("memberCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByMemberCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where memberCount greater than or equals to DEFAULT_MEMBER_COUNT
        defaultMGuildLevelShouldBeFound("memberCount.greaterOrEqualThan=" + DEFAULT_MEMBER_COUNT);

        // Get all the mGuildLevelList where memberCount greater than or equals to UPDATED_MEMBER_COUNT
        defaultMGuildLevelShouldNotBeFound("memberCount.greaterOrEqualThan=" + UPDATED_MEMBER_COUNT);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByMemberCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where memberCount less than or equals to DEFAULT_MEMBER_COUNT
        defaultMGuildLevelShouldNotBeFound("memberCount.lessThan=" + DEFAULT_MEMBER_COUNT);

        // Get all the mGuildLevelList where memberCount less than or equals to UPDATED_MEMBER_COUNT
        defaultMGuildLevelShouldBeFound("memberCount.lessThan=" + UPDATED_MEMBER_COUNT);
    }


    @Test
    @Transactional
    public void getAllMGuildLevelsByRecommendMemberCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where recommendMemberCount equals to DEFAULT_RECOMMEND_MEMBER_COUNT
        defaultMGuildLevelShouldBeFound("recommendMemberCount.equals=" + DEFAULT_RECOMMEND_MEMBER_COUNT);

        // Get all the mGuildLevelList where recommendMemberCount equals to UPDATED_RECOMMEND_MEMBER_COUNT
        defaultMGuildLevelShouldNotBeFound("recommendMemberCount.equals=" + UPDATED_RECOMMEND_MEMBER_COUNT);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByRecommendMemberCountIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where recommendMemberCount in DEFAULT_RECOMMEND_MEMBER_COUNT or UPDATED_RECOMMEND_MEMBER_COUNT
        defaultMGuildLevelShouldBeFound("recommendMemberCount.in=" + DEFAULT_RECOMMEND_MEMBER_COUNT + "," + UPDATED_RECOMMEND_MEMBER_COUNT);

        // Get all the mGuildLevelList where recommendMemberCount equals to UPDATED_RECOMMEND_MEMBER_COUNT
        defaultMGuildLevelShouldNotBeFound("recommendMemberCount.in=" + UPDATED_RECOMMEND_MEMBER_COUNT);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByRecommendMemberCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where recommendMemberCount is not null
        defaultMGuildLevelShouldBeFound("recommendMemberCount.specified=true");

        // Get all the mGuildLevelList where recommendMemberCount is null
        defaultMGuildLevelShouldNotBeFound("recommendMemberCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByRecommendMemberCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where recommendMemberCount greater than or equals to DEFAULT_RECOMMEND_MEMBER_COUNT
        defaultMGuildLevelShouldBeFound("recommendMemberCount.greaterOrEqualThan=" + DEFAULT_RECOMMEND_MEMBER_COUNT);

        // Get all the mGuildLevelList where recommendMemberCount greater than or equals to UPDATED_RECOMMEND_MEMBER_COUNT
        defaultMGuildLevelShouldNotBeFound("recommendMemberCount.greaterOrEqualThan=" + UPDATED_RECOMMEND_MEMBER_COUNT);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByRecommendMemberCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where recommendMemberCount less than or equals to DEFAULT_RECOMMEND_MEMBER_COUNT
        defaultMGuildLevelShouldNotBeFound("recommendMemberCount.lessThan=" + DEFAULT_RECOMMEND_MEMBER_COUNT);

        // Get all the mGuildLevelList where recommendMemberCount less than or equals to UPDATED_RECOMMEND_MEMBER_COUNT
        defaultMGuildLevelShouldBeFound("recommendMemberCount.lessThan=" + UPDATED_RECOMMEND_MEMBER_COUNT);
    }


    @Test
    @Transactional
    public void getAllMGuildLevelsByGuildMedalIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where guildMedal equals to DEFAULT_GUILD_MEDAL
        defaultMGuildLevelShouldBeFound("guildMedal.equals=" + DEFAULT_GUILD_MEDAL);

        // Get all the mGuildLevelList where guildMedal equals to UPDATED_GUILD_MEDAL
        defaultMGuildLevelShouldNotBeFound("guildMedal.equals=" + UPDATED_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByGuildMedalIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where guildMedal in DEFAULT_GUILD_MEDAL or UPDATED_GUILD_MEDAL
        defaultMGuildLevelShouldBeFound("guildMedal.in=" + DEFAULT_GUILD_MEDAL + "," + UPDATED_GUILD_MEDAL);

        // Get all the mGuildLevelList where guildMedal equals to UPDATED_GUILD_MEDAL
        defaultMGuildLevelShouldNotBeFound("guildMedal.in=" + UPDATED_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByGuildMedalIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where guildMedal is not null
        defaultMGuildLevelShouldBeFound("guildMedal.specified=true");

        // Get all the mGuildLevelList where guildMedal is null
        defaultMGuildLevelShouldNotBeFound("guildMedal.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByGuildMedalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where guildMedal greater than or equals to DEFAULT_GUILD_MEDAL
        defaultMGuildLevelShouldBeFound("guildMedal.greaterOrEqualThan=" + DEFAULT_GUILD_MEDAL);

        // Get all the mGuildLevelList where guildMedal greater than or equals to UPDATED_GUILD_MEDAL
        defaultMGuildLevelShouldNotBeFound("guildMedal.greaterOrEqualThan=" + UPDATED_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void getAllMGuildLevelsByGuildMedalIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        // Get all the mGuildLevelList where guildMedal less than or equals to DEFAULT_GUILD_MEDAL
        defaultMGuildLevelShouldNotBeFound("guildMedal.lessThan=" + DEFAULT_GUILD_MEDAL);

        // Get all the mGuildLevelList where guildMedal less than or equals to UPDATED_GUILD_MEDAL
        defaultMGuildLevelShouldBeFound("guildMedal.lessThan=" + UPDATED_GUILD_MEDAL);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuildLevelShouldBeFound(String filter) throws Exception {
        restMGuildLevelMockMvc.perform(get("/api/m-guild-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)))
            .andExpect(jsonPath("$.[*].memberCount").value(hasItem(DEFAULT_MEMBER_COUNT)))
            .andExpect(jsonPath("$.[*].recommendMemberCount").value(hasItem(DEFAULT_RECOMMEND_MEMBER_COUNT)))
            .andExpect(jsonPath("$.[*].guildMedal").value(hasItem(DEFAULT_GUILD_MEDAL)));

        // Check, that the count call also returns 1
        restMGuildLevelMockMvc.perform(get("/api/m-guild-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuildLevelShouldNotBeFound(String filter) throws Exception {
        restMGuildLevelMockMvc.perform(get("/api/m-guild-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuildLevelMockMvc.perform(get("/api/m-guild-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuildLevel() throws Exception {
        // Get the mGuildLevel
        restMGuildLevelMockMvc.perform(get("/api/m-guild-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuildLevel() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        int databaseSizeBeforeUpdate = mGuildLevelRepository.findAll().size();

        // Update the mGuildLevel
        MGuildLevel updatedMGuildLevel = mGuildLevelRepository.findById(mGuildLevel.getId()).get();
        // Disconnect from session so that the updates on updatedMGuildLevel are not directly saved in db
        em.detach(updatedMGuildLevel);
        updatedMGuildLevel
            .level(UPDATED_LEVEL)
            .exp(UPDATED_EXP)
            .memberCount(UPDATED_MEMBER_COUNT)
            .recommendMemberCount(UPDATED_RECOMMEND_MEMBER_COUNT)
            .guildMedal(UPDATED_GUILD_MEDAL);
        MGuildLevelDTO mGuildLevelDTO = mGuildLevelMapper.toDto(updatedMGuildLevel);

        restMGuildLevelMockMvc.perform(put("/api/m-guild-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildLevelDTO)))
            .andExpect(status().isOk());

        // Validate the MGuildLevel in the database
        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeUpdate);
        MGuildLevel testMGuildLevel = mGuildLevelList.get(mGuildLevelList.size() - 1);
        assertThat(testMGuildLevel.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMGuildLevel.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMGuildLevel.getMemberCount()).isEqualTo(UPDATED_MEMBER_COUNT);
        assertThat(testMGuildLevel.getRecommendMemberCount()).isEqualTo(UPDATED_RECOMMEND_MEMBER_COUNT);
        assertThat(testMGuildLevel.getGuildMedal()).isEqualTo(UPDATED_GUILD_MEDAL);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuildLevel() throws Exception {
        int databaseSizeBeforeUpdate = mGuildLevelRepository.findAll().size();

        // Create the MGuildLevel
        MGuildLevelDTO mGuildLevelDTO = mGuildLevelMapper.toDto(mGuildLevel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuildLevelMockMvc.perform(put("/api/m-guild-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildLevel in the database
        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuildLevel() throws Exception {
        // Initialize the database
        mGuildLevelRepository.saveAndFlush(mGuildLevel);

        int databaseSizeBeforeDelete = mGuildLevelRepository.findAll().size();

        // Delete the mGuildLevel
        restMGuildLevelMockMvc.perform(delete("/api/m-guild-levels/{id}", mGuildLevel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuildLevel> mGuildLevelList = mGuildLevelRepository.findAll();
        assertThat(mGuildLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildLevel.class);
        MGuildLevel mGuildLevel1 = new MGuildLevel();
        mGuildLevel1.setId(1L);
        MGuildLevel mGuildLevel2 = new MGuildLevel();
        mGuildLevel2.setId(mGuildLevel1.getId());
        assertThat(mGuildLevel1).isEqualTo(mGuildLevel2);
        mGuildLevel2.setId(2L);
        assertThat(mGuildLevel1).isNotEqualTo(mGuildLevel2);
        mGuildLevel1.setId(null);
        assertThat(mGuildLevel1).isNotEqualTo(mGuildLevel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildLevelDTO.class);
        MGuildLevelDTO mGuildLevelDTO1 = new MGuildLevelDTO();
        mGuildLevelDTO1.setId(1L);
        MGuildLevelDTO mGuildLevelDTO2 = new MGuildLevelDTO();
        assertThat(mGuildLevelDTO1).isNotEqualTo(mGuildLevelDTO2);
        mGuildLevelDTO2.setId(mGuildLevelDTO1.getId());
        assertThat(mGuildLevelDTO1).isEqualTo(mGuildLevelDTO2);
        mGuildLevelDTO2.setId(2L);
        assertThat(mGuildLevelDTO1).isNotEqualTo(mGuildLevelDTO2);
        mGuildLevelDTO1.setId(null);
        assertThat(mGuildLevelDTO1).isNotEqualTo(mGuildLevelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuildLevelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuildLevelMapper.fromId(null)).isNull();
    }
}
