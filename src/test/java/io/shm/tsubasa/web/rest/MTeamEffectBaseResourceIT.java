package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTeamEffectBase;
import io.shm.tsubasa.domain.MPassiveEffectRange;
import io.shm.tsubasa.repository.MTeamEffectBaseRepository;
import io.shm.tsubasa.service.MTeamEffectBaseService;
import io.shm.tsubasa.service.dto.MTeamEffectBaseDTO;
import io.shm.tsubasa.service.mapper.MTeamEffectBaseMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTeamEffectBaseCriteria;
import io.shm.tsubasa.service.MTeamEffectBaseQueryService;

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
 * Integration tests for the {@Link MTeamEffectBaseResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTeamEffectBaseResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_EFFECT_VALUE_MIN = 1;
    private static final Integer UPDATED_EFFECT_VALUE_MIN = 2;

    private static final Integer DEFAULT_EFFECT_VALUE_MAX = 1;
    private static final Integer UPDATED_EFFECT_VALUE_MAX = 2;

    private static final Integer DEFAULT_TRIGGER_PROBABILITY_MIN = 1;
    private static final Integer UPDATED_TRIGGER_PROBABILITY_MIN = 2;

    private static final Integer DEFAULT_TRIGGER_PROBABILITY_MAX = 1;
    private static final Integer UPDATED_TRIGGER_PROBABILITY_MAX = 2;

    private static final Integer DEFAULT_EFFECT_ID = 1;
    private static final Integer UPDATED_EFFECT_ID = 2;

    private static final Integer DEFAULT_EFFECT_VALUE_MIN_2 = 1;
    private static final Integer UPDATED_EFFECT_VALUE_MIN_2 = 2;

    private static final Integer DEFAULT_EFFECT_VALUE_MAX_2 = 1;
    private static final Integer UPDATED_EFFECT_VALUE_MAX_2 = 2;

    private static final Integer DEFAULT_TRIGGER_PROBABILITY_MIN_2 = 1;
    private static final Integer UPDATED_TRIGGER_PROBABILITY_MIN_2 = 2;

    private static final Integer DEFAULT_TRIGGER_PROBABILITY_MAX_2 = 1;
    private static final Integer UPDATED_TRIGGER_PROBABILITY_MAX_2 = 2;

    private static final Integer DEFAULT_EFFECT_ID_2 = 1;
    private static final Integer UPDATED_EFFECT_ID_2 = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MTeamEffectBaseRepository mTeamEffectBaseRepository;

    @Autowired
    private MTeamEffectBaseMapper mTeamEffectBaseMapper;

    @Autowired
    private MTeamEffectBaseService mTeamEffectBaseService;

    @Autowired
    private MTeamEffectBaseQueryService mTeamEffectBaseQueryService;

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

    private MockMvc restMTeamEffectBaseMockMvc;

    private MTeamEffectBase mTeamEffectBase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTeamEffectBaseResource mTeamEffectBaseResource = new MTeamEffectBaseResource(mTeamEffectBaseService, mTeamEffectBaseQueryService);
        this.restMTeamEffectBaseMockMvc = MockMvcBuilders.standaloneSetup(mTeamEffectBaseResource)
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
    public static MTeamEffectBase createEntity(EntityManager em) {
        MTeamEffectBase mTeamEffectBase = new MTeamEffectBase()
            .name(DEFAULT_NAME)
            .rarity(DEFAULT_RARITY)
            .effectValueMin(DEFAULT_EFFECT_VALUE_MIN)
            .effectValueMax(DEFAULT_EFFECT_VALUE_MAX)
            .triggerProbabilityMin(DEFAULT_TRIGGER_PROBABILITY_MIN)
            .triggerProbabilityMax(DEFAULT_TRIGGER_PROBABILITY_MAX)
            .effectId(DEFAULT_EFFECT_ID)
            .effectValueMin2(DEFAULT_EFFECT_VALUE_MIN_2)
            .effectValueMax2(DEFAULT_EFFECT_VALUE_MAX_2)
            .triggerProbabilityMin2(DEFAULT_TRIGGER_PROBABILITY_MIN_2)
            .triggerProbabilityMax2(DEFAULT_TRIGGER_PROBABILITY_MAX_2)
            .effectId2(DEFAULT_EFFECT_ID_2)
            .description(DEFAULT_DESCRIPTION);
        // Add required entity
        MPassiveEffectRange mPassiveEffectRange;
        if (TestUtil.findAll(em, MPassiveEffectRange.class).isEmpty()) {
            mPassiveEffectRange = MPassiveEffectRangeResourceIT.createEntity(em);
            em.persist(mPassiveEffectRange);
            em.flush();
        } else {
            mPassiveEffectRange = TestUtil.findAll(em, MPassiveEffectRange.class).get(0);
        }
        mTeamEffectBase.setId(mPassiveEffectRange);
        return mTeamEffectBase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTeamEffectBase createUpdatedEntity(EntityManager em) {
        MTeamEffectBase mTeamEffectBase = new MTeamEffectBase()
            .name(UPDATED_NAME)
            .rarity(UPDATED_RARITY)
            .effectValueMin(UPDATED_EFFECT_VALUE_MIN)
            .effectValueMax(UPDATED_EFFECT_VALUE_MAX)
            .triggerProbabilityMin(UPDATED_TRIGGER_PROBABILITY_MIN)
            .triggerProbabilityMax(UPDATED_TRIGGER_PROBABILITY_MAX)
            .effectId(UPDATED_EFFECT_ID)
            .effectValueMin2(UPDATED_EFFECT_VALUE_MIN_2)
            .effectValueMax2(UPDATED_EFFECT_VALUE_MAX_2)
            .triggerProbabilityMin2(UPDATED_TRIGGER_PROBABILITY_MIN_2)
            .triggerProbabilityMax2(UPDATED_TRIGGER_PROBABILITY_MAX_2)
            .effectId2(UPDATED_EFFECT_ID_2)
            .description(UPDATED_DESCRIPTION);
        // Add required entity
        MPassiveEffectRange mPassiveEffectRange;
        if (TestUtil.findAll(em, MPassiveEffectRange.class).isEmpty()) {
            mPassiveEffectRange = MPassiveEffectRangeResourceIT.createUpdatedEntity(em);
            em.persist(mPassiveEffectRange);
            em.flush();
        } else {
            mPassiveEffectRange = TestUtil.findAll(em, MPassiveEffectRange.class).get(0);
        }
        mTeamEffectBase.setId(mPassiveEffectRange);
        return mTeamEffectBase;
    }

    @BeforeEach
    public void initTest() {
        mTeamEffectBase = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTeamEffectBase() throws Exception {
        int databaseSizeBeforeCreate = mTeamEffectBaseRepository.findAll().size();

        // Create the MTeamEffectBase
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);
        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isCreated());

        // Validate the MTeamEffectBase in the database
        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeCreate + 1);
        MTeamEffectBase testMTeamEffectBase = mTeamEffectBaseList.get(mTeamEffectBaseList.size() - 1);
        assertThat(testMTeamEffectBase.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMTeamEffectBase.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMTeamEffectBase.getEffectValueMin()).isEqualTo(DEFAULT_EFFECT_VALUE_MIN);
        assertThat(testMTeamEffectBase.getEffectValueMax()).isEqualTo(DEFAULT_EFFECT_VALUE_MAX);
        assertThat(testMTeamEffectBase.getTriggerProbabilityMin()).isEqualTo(DEFAULT_TRIGGER_PROBABILITY_MIN);
        assertThat(testMTeamEffectBase.getTriggerProbabilityMax()).isEqualTo(DEFAULT_TRIGGER_PROBABILITY_MAX);
        assertThat(testMTeamEffectBase.getEffectId()).isEqualTo(DEFAULT_EFFECT_ID);
        assertThat(testMTeamEffectBase.getEffectValueMin2()).isEqualTo(DEFAULT_EFFECT_VALUE_MIN_2);
        assertThat(testMTeamEffectBase.getEffectValueMax2()).isEqualTo(DEFAULT_EFFECT_VALUE_MAX_2);
        assertThat(testMTeamEffectBase.getTriggerProbabilityMin2()).isEqualTo(DEFAULT_TRIGGER_PROBABILITY_MIN_2);
        assertThat(testMTeamEffectBase.getTriggerProbabilityMax2()).isEqualTo(DEFAULT_TRIGGER_PROBABILITY_MAX_2);
        assertThat(testMTeamEffectBase.getEffectId2()).isEqualTo(DEFAULT_EFFECT_ID_2);
        assertThat(testMTeamEffectBase.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMTeamEffectBaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTeamEffectBaseRepository.findAll().size();

        // Create the MTeamEffectBase with an existing ID
        mTeamEffectBase.setId(1L);
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTeamEffectBase in the database
        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setRarity(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectValueMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setEffectValueMin(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectValueMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setEffectValueMax(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTriggerProbabilityMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setTriggerProbabilityMin(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTriggerProbabilityMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setTriggerProbabilityMax(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setEffectId(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectValueMin2IsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setEffectValueMin2(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectValueMax2IsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setEffectValueMax2(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTriggerProbabilityMin2IsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setTriggerProbabilityMin2(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTriggerProbabilityMax2IsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectBaseRepository.findAll().size();
        // set the field null
        mTeamEffectBase.setTriggerProbabilityMax2(null);

        // Create the MTeamEffectBase, which fails.
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(post("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBases() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList
        restMTeamEffectBaseMockMvc.perform(get("/api/m-team-effect-bases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTeamEffectBase.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].effectValueMin").value(hasItem(DEFAULT_EFFECT_VALUE_MIN)))
            .andExpect(jsonPath("$.[*].effectValueMax").value(hasItem(DEFAULT_EFFECT_VALUE_MAX)))
            .andExpect(jsonPath("$.[*].triggerProbabilityMin").value(hasItem(DEFAULT_TRIGGER_PROBABILITY_MIN)))
            .andExpect(jsonPath("$.[*].triggerProbabilityMax").value(hasItem(DEFAULT_TRIGGER_PROBABILITY_MAX)))
            .andExpect(jsonPath("$.[*].effectId").value(hasItem(DEFAULT_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].effectValueMin2").value(hasItem(DEFAULT_EFFECT_VALUE_MIN_2)))
            .andExpect(jsonPath("$.[*].effectValueMax2").value(hasItem(DEFAULT_EFFECT_VALUE_MAX_2)))
            .andExpect(jsonPath("$.[*].triggerProbabilityMin2").value(hasItem(DEFAULT_TRIGGER_PROBABILITY_MIN_2)))
            .andExpect(jsonPath("$.[*].triggerProbabilityMax2").value(hasItem(DEFAULT_TRIGGER_PROBABILITY_MAX_2)))
            .andExpect(jsonPath("$.[*].effectId2").value(hasItem(DEFAULT_EFFECT_ID_2)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMTeamEffectBase() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get the mTeamEffectBase
        restMTeamEffectBaseMockMvc.perform(get("/api/m-team-effect-bases/{id}", mTeamEffectBase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTeamEffectBase.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.effectValueMin").value(DEFAULT_EFFECT_VALUE_MIN))
            .andExpect(jsonPath("$.effectValueMax").value(DEFAULT_EFFECT_VALUE_MAX))
            .andExpect(jsonPath("$.triggerProbabilityMin").value(DEFAULT_TRIGGER_PROBABILITY_MIN))
            .andExpect(jsonPath("$.triggerProbabilityMax").value(DEFAULT_TRIGGER_PROBABILITY_MAX))
            .andExpect(jsonPath("$.effectId").value(DEFAULT_EFFECT_ID))
            .andExpect(jsonPath("$.effectValueMin2").value(DEFAULT_EFFECT_VALUE_MIN_2))
            .andExpect(jsonPath("$.effectValueMax2").value(DEFAULT_EFFECT_VALUE_MAX_2))
            .andExpect(jsonPath("$.triggerProbabilityMin2").value(DEFAULT_TRIGGER_PROBABILITY_MIN_2))
            .andExpect(jsonPath("$.triggerProbabilityMax2").value(DEFAULT_TRIGGER_PROBABILITY_MAX_2))
            .andExpect(jsonPath("$.effectId2").value(DEFAULT_EFFECT_ID_2))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where rarity equals to DEFAULT_RARITY
        defaultMTeamEffectBaseShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mTeamEffectBaseList where rarity equals to UPDATED_RARITY
        defaultMTeamEffectBaseShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMTeamEffectBaseShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mTeamEffectBaseList where rarity equals to UPDATED_RARITY
        defaultMTeamEffectBaseShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where rarity is not null
        defaultMTeamEffectBaseShouldBeFound("rarity.specified=true");

        // Get all the mTeamEffectBaseList where rarity is null
        defaultMTeamEffectBaseShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where rarity greater than or equals to DEFAULT_RARITY
        defaultMTeamEffectBaseShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mTeamEffectBaseList where rarity greater than or equals to UPDATED_RARITY
        defaultMTeamEffectBaseShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where rarity less than or equals to DEFAULT_RARITY
        defaultMTeamEffectBaseShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mTeamEffectBaseList where rarity less than or equals to UPDATED_RARITY
        defaultMTeamEffectBaseShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin equals to DEFAULT_EFFECT_VALUE_MIN
        defaultMTeamEffectBaseShouldBeFound("effectValueMin.equals=" + DEFAULT_EFFECT_VALUE_MIN);

        // Get all the mTeamEffectBaseList where effectValueMin equals to UPDATED_EFFECT_VALUE_MIN
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin.equals=" + UPDATED_EFFECT_VALUE_MIN);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMinIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin in DEFAULT_EFFECT_VALUE_MIN or UPDATED_EFFECT_VALUE_MIN
        defaultMTeamEffectBaseShouldBeFound("effectValueMin.in=" + DEFAULT_EFFECT_VALUE_MIN + "," + UPDATED_EFFECT_VALUE_MIN);

        // Get all the mTeamEffectBaseList where effectValueMin equals to UPDATED_EFFECT_VALUE_MIN
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin.in=" + UPDATED_EFFECT_VALUE_MIN);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin is not null
        defaultMTeamEffectBaseShouldBeFound("effectValueMin.specified=true");

        // Get all the mTeamEffectBaseList where effectValueMin is null
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin greater than or equals to DEFAULT_EFFECT_VALUE_MIN
        defaultMTeamEffectBaseShouldBeFound("effectValueMin.greaterOrEqualThan=" + DEFAULT_EFFECT_VALUE_MIN);

        // Get all the mTeamEffectBaseList where effectValueMin greater than or equals to UPDATED_EFFECT_VALUE_MIN
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin.greaterOrEqualThan=" + UPDATED_EFFECT_VALUE_MIN);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin less than or equals to DEFAULT_EFFECT_VALUE_MIN
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin.lessThan=" + DEFAULT_EFFECT_VALUE_MIN);

        // Get all the mTeamEffectBaseList where effectValueMin less than or equals to UPDATED_EFFECT_VALUE_MIN
        defaultMTeamEffectBaseShouldBeFound("effectValueMin.lessThan=" + UPDATED_EFFECT_VALUE_MIN);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax equals to DEFAULT_EFFECT_VALUE_MAX
        defaultMTeamEffectBaseShouldBeFound("effectValueMax.equals=" + DEFAULT_EFFECT_VALUE_MAX);

        // Get all the mTeamEffectBaseList where effectValueMax equals to UPDATED_EFFECT_VALUE_MAX
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax.equals=" + UPDATED_EFFECT_VALUE_MAX);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax in DEFAULT_EFFECT_VALUE_MAX or UPDATED_EFFECT_VALUE_MAX
        defaultMTeamEffectBaseShouldBeFound("effectValueMax.in=" + DEFAULT_EFFECT_VALUE_MAX + "," + UPDATED_EFFECT_VALUE_MAX);

        // Get all the mTeamEffectBaseList where effectValueMax equals to UPDATED_EFFECT_VALUE_MAX
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax.in=" + UPDATED_EFFECT_VALUE_MAX);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax is not null
        defaultMTeamEffectBaseShouldBeFound("effectValueMax.specified=true");

        // Get all the mTeamEffectBaseList where effectValueMax is null
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax greater than or equals to DEFAULT_EFFECT_VALUE_MAX
        defaultMTeamEffectBaseShouldBeFound("effectValueMax.greaterOrEqualThan=" + DEFAULT_EFFECT_VALUE_MAX);

        // Get all the mTeamEffectBaseList where effectValueMax greater than or equals to UPDATED_EFFECT_VALUE_MAX
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax.greaterOrEqualThan=" + UPDATED_EFFECT_VALUE_MAX);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax less than or equals to DEFAULT_EFFECT_VALUE_MAX
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax.lessThan=" + DEFAULT_EFFECT_VALUE_MAX);

        // Get all the mTeamEffectBaseList where effectValueMax less than or equals to UPDATED_EFFECT_VALUE_MAX
        defaultMTeamEffectBaseShouldBeFound("effectValueMax.lessThan=" + UPDATED_EFFECT_VALUE_MAX);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMinIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin equals to DEFAULT_TRIGGER_PROBABILITY_MIN
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin.equals=" + DEFAULT_TRIGGER_PROBABILITY_MIN);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin equals to UPDATED_TRIGGER_PROBABILITY_MIN
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin.equals=" + UPDATED_TRIGGER_PROBABILITY_MIN);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMinIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin in DEFAULT_TRIGGER_PROBABILITY_MIN or UPDATED_TRIGGER_PROBABILITY_MIN
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin.in=" + DEFAULT_TRIGGER_PROBABILITY_MIN + "," + UPDATED_TRIGGER_PROBABILITY_MIN);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin equals to UPDATED_TRIGGER_PROBABILITY_MIN
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin.in=" + UPDATED_TRIGGER_PROBABILITY_MIN);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin is not null
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin.specified=true");

        // Get all the mTeamEffectBaseList where triggerProbabilityMin is null
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin greater than or equals to DEFAULT_TRIGGER_PROBABILITY_MIN
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin.greaterOrEqualThan=" + DEFAULT_TRIGGER_PROBABILITY_MIN);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin greater than or equals to UPDATED_TRIGGER_PROBABILITY_MIN
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin.greaterOrEqualThan=" + UPDATED_TRIGGER_PROBABILITY_MIN);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMinIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin less than or equals to DEFAULT_TRIGGER_PROBABILITY_MIN
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin.lessThan=" + DEFAULT_TRIGGER_PROBABILITY_MIN);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin less than or equals to UPDATED_TRIGGER_PROBABILITY_MIN
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin.lessThan=" + UPDATED_TRIGGER_PROBABILITY_MIN);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax equals to DEFAULT_TRIGGER_PROBABILITY_MAX
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax.equals=" + DEFAULT_TRIGGER_PROBABILITY_MAX);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax equals to UPDATED_TRIGGER_PROBABILITY_MAX
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax.equals=" + UPDATED_TRIGGER_PROBABILITY_MAX);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMaxIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax in DEFAULT_TRIGGER_PROBABILITY_MAX or UPDATED_TRIGGER_PROBABILITY_MAX
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax.in=" + DEFAULT_TRIGGER_PROBABILITY_MAX + "," + UPDATED_TRIGGER_PROBABILITY_MAX);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax equals to UPDATED_TRIGGER_PROBABILITY_MAX
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax.in=" + UPDATED_TRIGGER_PROBABILITY_MAX);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax is not null
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax.specified=true");

        // Get all the mTeamEffectBaseList where triggerProbabilityMax is null
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax greater than or equals to DEFAULT_TRIGGER_PROBABILITY_MAX
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax.greaterOrEqualThan=" + DEFAULT_TRIGGER_PROBABILITY_MAX);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax greater than or equals to UPDATED_TRIGGER_PROBABILITY_MAX
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax.greaterOrEqualThan=" + UPDATED_TRIGGER_PROBABILITY_MAX);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax less than or equals to DEFAULT_TRIGGER_PROBABILITY_MAX
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax.lessThan=" + DEFAULT_TRIGGER_PROBABILITY_MAX);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax less than or equals to UPDATED_TRIGGER_PROBABILITY_MAX
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax.lessThan=" + UPDATED_TRIGGER_PROBABILITY_MAX);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId equals to DEFAULT_EFFECT_ID
        defaultMTeamEffectBaseShouldBeFound("effectId.equals=" + DEFAULT_EFFECT_ID);

        // Get all the mTeamEffectBaseList where effectId equals to UPDATED_EFFECT_ID
        defaultMTeamEffectBaseShouldNotBeFound("effectId.equals=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId in DEFAULT_EFFECT_ID or UPDATED_EFFECT_ID
        defaultMTeamEffectBaseShouldBeFound("effectId.in=" + DEFAULT_EFFECT_ID + "," + UPDATED_EFFECT_ID);

        // Get all the mTeamEffectBaseList where effectId equals to UPDATED_EFFECT_ID
        defaultMTeamEffectBaseShouldNotBeFound("effectId.in=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId is not null
        defaultMTeamEffectBaseShouldBeFound("effectId.specified=true");

        // Get all the mTeamEffectBaseList where effectId is null
        defaultMTeamEffectBaseShouldNotBeFound("effectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId greater than or equals to DEFAULT_EFFECT_ID
        defaultMTeamEffectBaseShouldBeFound("effectId.greaterOrEqualThan=" + DEFAULT_EFFECT_ID);

        // Get all the mTeamEffectBaseList where effectId greater than or equals to UPDATED_EFFECT_ID
        defaultMTeamEffectBaseShouldNotBeFound("effectId.greaterOrEqualThan=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId less than or equals to DEFAULT_EFFECT_ID
        defaultMTeamEffectBaseShouldNotBeFound("effectId.lessThan=" + DEFAULT_EFFECT_ID);

        // Get all the mTeamEffectBaseList where effectId less than or equals to UPDATED_EFFECT_ID
        defaultMTeamEffectBaseShouldBeFound("effectId.lessThan=" + UPDATED_EFFECT_ID);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMin2IsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin2 equals to DEFAULT_EFFECT_VALUE_MIN_2
        defaultMTeamEffectBaseShouldBeFound("effectValueMin2.equals=" + DEFAULT_EFFECT_VALUE_MIN_2);

        // Get all the mTeamEffectBaseList where effectValueMin2 equals to UPDATED_EFFECT_VALUE_MIN_2
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin2.equals=" + UPDATED_EFFECT_VALUE_MIN_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMin2IsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin2 in DEFAULT_EFFECT_VALUE_MIN_2 or UPDATED_EFFECT_VALUE_MIN_2
        defaultMTeamEffectBaseShouldBeFound("effectValueMin2.in=" + DEFAULT_EFFECT_VALUE_MIN_2 + "," + UPDATED_EFFECT_VALUE_MIN_2);

        // Get all the mTeamEffectBaseList where effectValueMin2 equals to UPDATED_EFFECT_VALUE_MIN_2
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin2.in=" + UPDATED_EFFECT_VALUE_MIN_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMin2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin2 is not null
        defaultMTeamEffectBaseShouldBeFound("effectValueMin2.specified=true");

        // Get all the mTeamEffectBaseList where effectValueMin2 is null
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMin2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin2 greater than or equals to DEFAULT_EFFECT_VALUE_MIN_2
        defaultMTeamEffectBaseShouldBeFound("effectValueMin2.greaterOrEqualThan=" + DEFAULT_EFFECT_VALUE_MIN_2);

        // Get all the mTeamEffectBaseList where effectValueMin2 greater than or equals to UPDATED_EFFECT_VALUE_MIN_2
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin2.greaterOrEqualThan=" + UPDATED_EFFECT_VALUE_MIN_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMin2IsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMin2 less than or equals to DEFAULT_EFFECT_VALUE_MIN_2
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMin2.lessThan=" + DEFAULT_EFFECT_VALUE_MIN_2);

        // Get all the mTeamEffectBaseList where effectValueMin2 less than or equals to UPDATED_EFFECT_VALUE_MIN_2
        defaultMTeamEffectBaseShouldBeFound("effectValueMin2.lessThan=" + UPDATED_EFFECT_VALUE_MIN_2);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMax2IsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax2 equals to DEFAULT_EFFECT_VALUE_MAX_2
        defaultMTeamEffectBaseShouldBeFound("effectValueMax2.equals=" + DEFAULT_EFFECT_VALUE_MAX_2);

        // Get all the mTeamEffectBaseList where effectValueMax2 equals to UPDATED_EFFECT_VALUE_MAX_2
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax2.equals=" + UPDATED_EFFECT_VALUE_MAX_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMax2IsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax2 in DEFAULT_EFFECT_VALUE_MAX_2 or UPDATED_EFFECT_VALUE_MAX_2
        defaultMTeamEffectBaseShouldBeFound("effectValueMax2.in=" + DEFAULT_EFFECT_VALUE_MAX_2 + "," + UPDATED_EFFECT_VALUE_MAX_2);

        // Get all the mTeamEffectBaseList where effectValueMax2 equals to UPDATED_EFFECT_VALUE_MAX_2
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax2.in=" + UPDATED_EFFECT_VALUE_MAX_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMax2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax2 is not null
        defaultMTeamEffectBaseShouldBeFound("effectValueMax2.specified=true");

        // Get all the mTeamEffectBaseList where effectValueMax2 is null
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMax2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax2 greater than or equals to DEFAULT_EFFECT_VALUE_MAX_2
        defaultMTeamEffectBaseShouldBeFound("effectValueMax2.greaterOrEqualThan=" + DEFAULT_EFFECT_VALUE_MAX_2);

        // Get all the mTeamEffectBaseList where effectValueMax2 greater than or equals to UPDATED_EFFECT_VALUE_MAX_2
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax2.greaterOrEqualThan=" + UPDATED_EFFECT_VALUE_MAX_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectValueMax2IsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectValueMax2 less than or equals to DEFAULT_EFFECT_VALUE_MAX_2
        defaultMTeamEffectBaseShouldNotBeFound("effectValueMax2.lessThan=" + DEFAULT_EFFECT_VALUE_MAX_2);

        // Get all the mTeamEffectBaseList where effectValueMax2 less than or equals to UPDATED_EFFECT_VALUE_MAX_2
        defaultMTeamEffectBaseShouldBeFound("effectValueMax2.lessThan=" + UPDATED_EFFECT_VALUE_MAX_2);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMin2IsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 equals to DEFAULT_TRIGGER_PROBABILITY_MIN_2
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin2.equals=" + DEFAULT_TRIGGER_PROBABILITY_MIN_2);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 equals to UPDATED_TRIGGER_PROBABILITY_MIN_2
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin2.equals=" + UPDATED_TRIGGER_PROBABILITY_MIN_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMin2IsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 in DEFAULT_TRIGGER_PROBABILITY_MIN_2 or UPDATED_TRIGGER_PROBABILITY_MIN_2
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin2.in=" + DEFAULT_TRIGGER_PROBABILITY_MIN_2 + "," + UPDATED_TRIGGER_PROBABILITY_MIN_2);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 equals to UPDATED_TRIGGER_PROBABILITY_MIN_2
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin2.in=" + UPDATED_TRIGGER_PROBABILITY_MIN_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMin2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 is not null
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin2.specified=true");

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 is null
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMin2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 greater than or equals to DEFAULT_TRIGGER_PROBABILITY_MIN_2
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin2.greaterOrEqualThan=" + DEFAULT_TRIGGER_PROBABILITY_MIN_2);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 greater than or equals to UPDATED_TRIGGER_PROBABILITY_MIN_2
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin2.greaterOrEqualThan=" + UPDATED_TRIGGER_PROBABILITY_MIN_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMin2IsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 less than or equals to DEFAULT_TRIGGER_PROBABILITY_MIN_2
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMin2.lessThan=" + DEFAULT_TRIGGER_PROBABILITY_MIN_2);

        // Get all the mTeamEffectBaseList where triggerProbabilityMin2 less than or equals to UPDATED_TRIGGER_PROBABILITY_MIN_2
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMin2.lessThan=" + UPDATED_TRIGGER_PROBABILITY_MIN_2);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMax2IsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 equals to DEFAULT_TRIGGER_PROBABILITY_MAX_2
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax2.equals=" + DEFAULT_TRIGGER_PROBABILITY_MAX_2);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 equals to UPDATED_TRIGGER_PROBABILITY_MAX_2
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax2.equals=" + UPDATED_TRIGGER_PROBABILITY_MAX_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMax2IsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 in DEFAULT_TRIGGER_PROBABILITY_MAX_2 or UPDATED_TRIGGER_PROBABILITY_MAX_2
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax2.in=" + DEFAULT_TRIGGER_PROBABILITY_MAX_2 + "," + UPDATED_TRIGGER_PROBABILITY_MAX_2);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 equals to UPDATED_TRIGGER_PROBABILITY_MAX_2
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax2.in=" + UPDATED_TRIGGER_PROBABILITY_MAX_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMax2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 is not null
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax2.specified=true");

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 is null
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMax2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 greater than or equals to DEFAULT_TRIGGER_PROBABILITY_MAX_2
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax2.greaterOrEqualThan=" + DEFAULT_TRIGGER_PROBABILITY_MAX_2);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 greater than or equals to UPDATED_TRIGGER_PROBABILITY_MAX_2
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax2.greaterOrEqualThan=" + UPDATED_TRIGGER_PROBABILITY_MAX_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByTriggerProbabilityMax2IsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 less than or equals to DEFAULT_TRIGGER_PROBABILITY_MAX_2
        defaultMTeamEffectBaseShouldNotBeFound("triggerProbabilityMax2.lessThan=" + DEFAULT_TRIGGER_PROBABILITY_MAX_2);

        // Get all the mTeamEffectBaseList where triggerProbabilityMax2 less than or equals to UPDATED_TRIGGER_PROBABILITY_MAX_2
        defaultMTeamEffectBaseShouldBeFound("triggerProbabilityMax2.lessThan=" + UPDATED_TRIGGER_PROBABILITY_MAX_2);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectId2IsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId2 equals to DEFAULT_EFFECT_ID_2
        defaultMTeamEffectBaseShouldBeFound("effectId2.equals=" + DEFAULT_EFFECT_ID_2);

        // Get all the mTeamEffectBaseList where effectId2 equals to UPDATED_EFFECT_ID_2
        defaultMTeamEffectBaseShouldNotBeFound("effectId2.equals=" + UPDATED_EFFECT_ID_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectId2IsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId2 in DEFAULT_EFFECT_ID_2 or UPDATED_EFFECT_ID_2
        defaultMTeamEffectBaseShouldBeFound("effectId2.in=" + DEFAULT_EFFECT_ID_2 + "," + UPDATED_EFFECT_ID_2);

        // Get all the mTeamEffectBaseList where effectId2 equals to UPDATED_EFFECT_ID_2
        defaultMTeamEffectBaseShouldNotBeFound("effectId2.in=" + UPDATED_EFFECT_ID_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectId2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId2 is not null
        defaultMTeamEffectBaseShouldBeFound("effectId2.specified=true");

        // Get all the mTeamEffectBaseList where effectId2 is null
        defaultMTeamEffectBaseShouldNotBeFound("effectId2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectId2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId2 greater than or equals to DEFAULT_EFFECT_ID_2
        defaultMTeamEffectBaseShouldBeFound("effectId2.greaterOrEqualThan=" + DEFAULT_EFFECT_ID_2);

        // Get all the mTeamEffectBaseList where effectId2 greater than or equals to UPDATED_EFFECT_ID_2
        defaultMTeamEffectBaseShouldNotBeFound("effectId2.greaterOrEqualThan=" + UPDATED_EFFECT_ID_2);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectBasesByEffectId2IsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        // Get all the mTeamEffectBaseList where effectId2 less than or equals to DEFAULT_EFFECT_ID_2
        defaultMTeamEffectBaseShouldNotBeFound("effectId2.lessThan=" + DEFAULT_EFFECT_ID_2);

        // Get all the mTeamEffectBaseList where effectId2 less than or equals to UPDATED_EFFECT_ID_2
        defaultMTeamEffectBaseShouldBeFound("effectId2.lessThan=" + UPDATED_EFFECT_ID_2);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectBasesByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MPassiveEffectRange id = mTeamEffectBase.getId();
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);
        Long idId = id.getId();

        // Get all the mTeamEffectBaseList where id equals to idId
        defaultMTeamEffectBaseShouldBeFound("idId.equals=" + idId);

        // Get all the mTeamEffectBaseList where id equals to idId + 1
        defaultMTeamEffectBaseShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTeamEffectBaseShouldBeFound(String filter) throws Exception {
        restMTeamEffectBaseMockMvc.perform(get("/api/m-team-effect-bases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTeamEffectBase.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].effectValueMin").value(hasItem(DEFAULT_EFFECT_VALUE_MIN)))
            .andExpect(jsonPath("$.[*].effectValueMax").value(hasItem(DEFAULT_EFFECT_VALUE_MAX)))
            .andExpect(jsonPath("$.[*].triggerProbabilityMin").value(hasItem(DEFAULT_TRIGGER_PROBABILITY_MIN)))
            .andExpect(jsonPath("$.[*].triggerProbabilityMax").value(hasItem(DEFAULT_TRIGGER_PROBABILITY_MAX)))
            .andExpect(jsonPath("$.[*].effectId").value(hasItem(DEFAULT_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].effectValueMin2").value(hasItem(DEFAULT_EFFECT_VALUE_MIN_2)))
            .andExpect(jsonPath("$.[*].effectValueMax2").value(hasItem(DEFAULT_EFFECT_VALUE_MAX_2)))
            .andExpect(jsonPath("$.[*].triggerProbabilityMin2").value(hasItem(DEFAULT_TRIGGER_PROBABILITY_MIN_2)))
            .andExpect(jsonPath("$.[*].triggerProbabilityMax2").value(hasItem(DEFAULT_TRIGGER_PROBABILITY_MAX_2)))
            .andExpect(jsonPath("$.[*].effectId2").value(hasItem(DEFAULT_EFFECT_ID_2)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMTeamEffectBaseMockMvc.perform(get("/api/m-team-effect-bases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTeamEffectBaseShouldNotBeFound(String filter) throws Exception {
        restMTeamEffectBaseMockMvc.perform(get("/api/m-team-effect-bases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTeamEffectBaseMockMvc.perform(get("/api/m-team-effect-bases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTeamEffectBase() throws Exception {
        // Get the mTeamEffectBase
        restMTeamEffectBaseMockMvc.perform(get("/api/m-team-effect-bases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTeamEffectBase() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        int databaseSizeBeforeUpdate = mTeamEffectBaseRepository.findAll().size();

        // Update the mTeamEffectBase
        MTeamEffectBase updatedMTeamEffectBase = mTeamEffectBaseRepository.findById(mTeamEffectBase.getId()).get();
        // Disconnect from session so that the updates on updatedMTeamEffectBase are not directly saved in db
        em.detach(updatedMTeamEffectBase);
        updatedMTeamEffectBase
            .name(UPDATED_NAME)
            .rarity(UPDATED_RARITY)
            .effectValueMin(UPDATED_EFFECT_VALUE_MIN)
            .effectValueMax(UPDATED_EFFECT_VALUE_MAX)
            .triggerProbabilityMin(UPDATED_TRIGGER_PROBABILITY_MIN)
            .triggerProbabilityMax(UPDATED_TRIGGER_PROBABILITY_MAX)
            .effectId(UPDATED_EFFECT_ID)
            .effectValueMin2(UPDATED_EFFECT_VALUE_MIN_2)
            .effectValueMax2(UPDATED_EFFECT_VALUE_MAX_2)
            .triggerProbabilityMin2(UPDATED_TRIGGER_PROBABILITY_MIN_2)
            .triggerProbabilityMax2(UPDATED_TRIGGER_PROBABILITY_MAX_2)
            .effectId2(UPDATED_EFFECT_ID_2)
            .description(UPDATED_DESCRIPTION);
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(updatedMTeamEffectBase);

        restMTeamEffectBaseMockMvc.perform(put("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isOk());

        // Validate the MTeamEffectBase in the database
        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeUpdate);
        MTeamEffectBase testMTeamEffectBase = mTeamEffectBaseList.get(mTeamEffectBaseList.size() - 1);
        assertThat(testMTeamEffectBase.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMTeamEffectBase.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMTeamEffectBase.getEffectValueMin()).isEqualTo(UPDATED_EFFECT_VALUE_MIN);
        assertThat(testMTeamEffectBase.getEffectValueMax()).isEqualTo(UPDATED_EFFECT_VALUE_MAX);
        assertThat(testMTeamEffectBase.getTriggerProbabilityMin()).isEqualTo(UPDATED_TRIGGER_PROBABILITY_MIN);
        assertThat(testMTeamEffectBase.getTriggerProbabilityMax()).isEqualTo(UPDATED_TRIGGER_PROBABILITY_MAX);
        assertThat(testMTeamEffectBase.getEffectId()).isEqualTo(UPDATED_EFFECT_ID);
        assertThat(testMTeamEffectBase.getEffectValueMin2()).isEqualTo(UPDATED_EFFECT_VALUE_MIN_2);
        assertThat(testMTeamEffectBase.getEffectValueMax2()).isEqualTo(UPDATED_EFFECT_VALUE_MAX_2);
        assertThat(testMTeamEffectBase.getTriggerProbabilityMin2()).isEqualTo(UPDATED_TRIGGER_PROBABILITY_MIN_2);
        assertThat(testMTeamEffectBase.getTriggerProbabilityMax2()).isEqualTo(UPDATED_TRIGGER_PROBABILITY_MAX_2);
        assertThat(testMTeamEffectBase.getEffectId2()).isEqualTo(UPDATED_EFFECT_ID_2);
        assertThat(testMTeamEffectBase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMTeamEffectBase() throws Exception {
        int databaseSizeBeforeUpdate = mTeamEffectBaseRepository.findAll().size();

        // Create the MTeamEffectBase
        MTeamEffectBaseDTO mTeamEffectBaseDTO = mTeamEffectBaseMapper.toDto(mTeamEffectBase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTeamEffectBaseMockMvc.perform(put("/api/m-team-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTeamEffectBase in the database
        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTeamEffectBase() throws Exception {
        // Initialize the database
        mTeamEffectBaseRepository.saveAndFlush(mTeamEffectBase);

        int databaseSizeBeforeDelete = mTeamEffectBaseRepository.findAll().size();

        // Delete the mTeamEffectBase
        restMTeamEffectBaseMockMvc.perform(delete("/api/m-team-effect-bases/{id}", mTeamEffectBase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTeamEffectBase> mTeamEffectBaseList = mTeamEffectBaseRepository.findAll();
        assertThat(mTeamEffectBaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTeamEffectBase.class);
        MTeamEffectBase mTeamEffectBase1 = new MTeamEffectBase();
        mTeamEffectBase1.setId(1L);
        MTeamEffectBase mTeamEffectBase2 = new MTeamEffectBase();
        mTeamEffectBase2.setId(mTeamEffectBase1.getId());
        assertThat(mTeamEffectBase1).isEqualTo(mTeamEffectBase2);
        mTeamEffectBase2.setId(2L);
        assertThat(mTeamEffectBase1).isNotEqualTo(mTeamEffectBase2);
        mTeamEffectBase1.setId(null);
        assertThat(mTeamEffectBase1).isNotEqualTo(mTeamEffectBase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTeamEffectBaseDTO.class);
        MTeamEffectBaseDTO mTeamEffectBaseDTO1 = new MTeamEffectBaseDTO();
        mTeamEffectBaseDTO1.setId(1L);
        MTeamEffectBaseDTO mTeamEffectBaseDTO2 = new MTeamEffectBaseDTO();
        assertThat(mTeamEffectBaseDTO1).isNotEqualTo(mTeamEffectBaseDTO2);
        mTeamEffectBaseDTO2.setId(mTeamEffectBaseDTO1.getId());
        assertThat(mTeamEffectBaseDTO1).isEqualTo(mTeamEffectBaseDTO2);
        mTeamEffectBaseDTO2.setId(2L);
        assertThat(mTeamEffectBaseDTO1).isNotEqualTo(mTeamEffectBaseDTO2);
        mTeamEffectBaseDTO1.setId(null);
        assertThat(mTeamEffectBaseDTO1).isNotEqualTo(mTeamEffectBaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTeamEffectBaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTeamEffectBaseMapper.fromId(null)).isNull();
    }
}
