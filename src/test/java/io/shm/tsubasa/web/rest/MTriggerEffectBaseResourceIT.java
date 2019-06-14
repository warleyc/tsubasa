package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTriggerEffectBase;
import io.shm.tsubasa.domain.MTriggerEffectRange;
import io.shm.tsubasa.domain.MTargetTriggerEffectGroup;
import io.shm.tsubasa.repository.MTriggerEffectBaseRepository;
import io.shm.tsubasa.service.MTriggerEffectBaseService;
import io.shm.tsubasa.service.dto.MTriggerEffectBaseDTO;
import io.shm.tsubasa.service.mapper.MTriggerEffectBaseMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTriggerEffectBaseCriteria;
import io.shm.tsubasa.service.MTriggerEffectBaseQueryService;

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
 * Integration tests for the {@Link MTriggerEffectBaseResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTriggerEffectBaseResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_EFFECT_VALUE = 1;
    private static final Integer UPDATED_EFFECT_VALUE = 2;

    private static final Integer DEFAULT_TRIGGER_PROBABILITY = 1;
    private static final Integer UPDATED_TRIGGER_PROBABILITY = 2;

    private static final Integer DEFAULT_TARGET_CARD_PARAMETER = 1;
    private static final Integer UPDATED_TARGET_CARD_PARAMETER = 2;

    private static final Integer DEFAULT_EFFECT_ID = 1;
    private static final Integer UPDATED_EFFECT_ID = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MTriggerEffectBaseRepository mTriggerEffectBaseRepository;

    @Autowired
    private MTriggerEffectBaseMapper mTriggerEffectBaseMapper;

    @Autowired
    private MTriggerEffectBaseService mTriggerEffectBaseService;

    @Autowired
    private MTriggerEffectBaseQueryService mTriggerEffectBaseQueryService;

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

    private MockMvc restMTriggerEffectBaseMockMvc;

    private MTriggerEffectBase mTriggerEffectBase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTriggerEffectBaseResource mTriggerEffectBaseResource = new MTriggerEffectBaseResource(mTriggerEffectBaseService, mTriggerEffectBaseQueryService);
        this.restMTriggerEffectBaseMockMvc = MockMvcBuilders.standaloneSetup(mTriggerEffectBaseResource)
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
    public static MTriggerEffectBase createEntity(EntityManager em) {
        MTriggerEffectBase mTriggerEffectBase = new MTriggerEffectBase()
            .name(DEFAULT_NAME)
            .rarity(DEFAULT_RARITY)
            .effectValue(DEFAULT_EFFECT_VALUE)
            .triggerProbability(DEFAULT_TRIGGER_PROBABILITY)
            .targetCardParameter(DEFAULT_TARGET_CARD_PARAMETER)
            .effectId(DEFAULT_EFFECT_ID)
            .description(DEFAULT_DESCRIPTION);
        // Add required entity
        MTriggerEffectRange mTriggerEffectRange;
        if (TestUtil.findAll(em, MTriggerEffectRange.class).isEmpty()) {
            mTriggerEffectRange = MTriggerEffectRangeResourceIT.createEntity(em);
            em.persist(mTriggerEffectRange);
            em.flush();
        } else {
            mTriggerEffectRange = TestUtil.findAll(em, MTriggerEffectRange.class).get(0);
        }
        mTriggerEffectBase.setMtriggereffectrange(mTriggerEffectRange);
        return mTriggerEffectBase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTriggerEffectBase createUpdatedEntity(EntityManager em) {
        MTriggerEffectBase mTriggerEffectBase = new MTriggerEffectBase()
            .name(UPDATED_NAME)
            .rarity(UPDATED_RARITY)
            .effectValue(UPDATED_EFFECT_VALUE)
            .triggerProbability(UPDATED_TRIGGER_PROBABILITY)
            .targetCardParameter(UPDATED_TARGET_CARD_PARAMETER)
            .effectId(UPDATED_EFFECT_ID)
            .description(UPDATED_DESCRIPTION);
        // Add required entity
        MTriggerEffectRange mTriggerEffectRange;
        if (TestUtil.findAll(em, MTriggerEffectRange.class).isEmpty()) {
            mTriggerEffectRange = MTriggerEffectRangeResourceIT.createUpdatedEntity(em);
            em.persist(mTriggerEffectRange);
            em.flush();
        } else {
            mTriggerEffectRange = TestUtil.findAll(em, MTriggerEffectRange.class).get(0);
        }
        mTriggerEffectBase.setMtriggereffectrange(mTriggerEffectRange);
        return mTriggerEffectBase;
    }

    @BeforeEach
    public void initTest() {
        mTriggerEffectBase = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTriggerEffectBase() throws Exception {
        int databaseSizeBeforeCreate = mTriggerEffectBaseRepository.findAll().size();

        // Create the MTriggerEffectBase
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO = mTriggerEffectBaseMapper.toDto(mTriggerEffectBase);
        restMTriggerEffectBaseMockMvc.perform(post("/api/m-trigger-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectBaseDTO)))
            .andExpect(status().isCreated());

        // Validate the MTriggerEffectBase in the database
        List<MTriggerEffectBase> mTriggerEffectBaseList = mTriggerEffectBaseRepository.findAll();
        assertThat(mTriggerEffectBaseList).hasSize(databaseSizeBeforeCreate + 1);
        MTriggerEffectBase testMTriggerEffectBase = mTriggerEffectBaseList.get(mTriggerEffectBaseList.size() - 1);
        assertThat(testMTriggerEffectBase.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMTriggerEffectBase.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMTriggerEffectBase.getEffectValue()).isEqualTo(DEFAULT_EFFECT_VALUE);
        assertThat(testMTriggerEffectBase.getTriggerProbability()).isEqualTo(DEFAULT_TRIGGER_PROBABILITY);
        assertThat(testMTriggerEffectBase.getTargetCardParameter()).isEqualTo(DEFAULT_TARGET_CARD_PARAMETER);
        assertThat(testMTriggerEffectBase.getEffectId()).isEqualTo(DEFAULT_EFFECT_ID);
        assertThat(testMTriggerEffectBase.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMTriggerEffectBaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTriggerEffectBaseRepository.findAll().size();

        // Create the MTriggerEffectBase with an existing ID
        mTriggerEffectBase.setId(1L);
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO = mTriggerEffectBaseMapper.toDto(mTriggerEffectBase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTriggerEffectBaseMockMvc.perform(post("/api/m-trigger-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTriggerEffectBase in the database
        List<MTriggerEffectBase> mTriggerEffectBaseList = mTriggerEffectBaseRepository.findAll();
        assertThat(mTriggerEffectBaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectBaseRepository.findAll().size();
        // set the field null
        mTriggerEffectBase.setRarity(null);

        // Create the MTriggerEffectBase, which fails.
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO = mTriggerEffectBaseMapper.toDto(mTriggerEffectBase);

        restMTriggerEffectBaseMockMvc.perform(post("/api/m-trigger-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectBase> mTriggerEffectBaseList = mTriggerEffectBaseRepository.findAll();
        assertThat(mTriggerEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectBaseRepository.findAll().size();
        // set the field null
        mTriggerEffectBase.setEffectValue(null);

        // Create the MTriggerEffectBase, which fails.
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO = mTriggerEffectBaseMapper.toDto(mTriggerEffectBase);

        restMTriggerEffectBaseMockMvc.perform(post("/api/m-trigger-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectBase> mTriggerEffectBaseList = mTriggerEffectBaseRepository.findAll();
        assertThat(mTriggerEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTriggerProbabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectBaseRepository.findAll().size();
        // set the field null
        mTriggerEffectBase.setTriggerProbability(null);

        // Create the MTriggerEffectBase, which fails.
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO = mTriggerEffectBaseMapper.toDto(mTriggerEffectBase);

        restMTriggerEffectBaseMockMvc.perform(post("/api/m-trigger-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectBase> mTriggerEffectBaseList = mTriggerEffectBaseRepository.findAll();
        assertThat(mTriggerEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTriggerEffectBaseRepository.findAll().size();
        // set the field null
        mTriggerEffectBase.setEffectId(null);

        // Create the MTriggerEffectBase, which fails.
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO = mTriggerEffectBaseMapper.toDto(mTriggerEffectBase);

        restMTriggerEffectBaseMockMvc.perform(post("/api/m-trigger-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        List<MTriggerEffectBase> mTriggerEffectBaseList = mTriggerEffectBaseRepository.findAll();
        assertThat(mTriggerEffectBaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBases() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList
        restMTriggerEffectBaseMockMvc.perform(get("/api/m-trigger-effect-bases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTriggerEffectBase.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].effectValue").value(hasItem(DEFAULT_EFFECT_VALUE)))
            .andExpect(jsonPath("$.[*].triggerProbability").value(hasItem(DEFAULT_TRIGGER_PROBABILITY)))
            .andExpect(jsonPath("$.[*].targetCardParameter").value(hasItem(DEFAULT_TARGET_CARD_PARAMETER)))
            .andExpect(jsonPath("$.[*].effectId").value(hasItem(DEFAULT_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMTriggerEffectBase() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get the mTriggerEffectBase
        restMTriggerEffectBaseMockMvc.perform(get("/api/m-trigger-effect-bases/{id}", mTriggerEffectBase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTriggerEffectBase.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.effectValue").value(DEFAULT_EFFECT_VALUE))
            .andExpect(jsonPath("$.triggerProbability").value(DEFAULT_TRIGGER_PROBABILITY))
            .andExpect(jsonPath("$.targetCardParameter").value(DEFAULT_TARGET_CARD_PARAMETER))
            .andExpect(jsonPath("$.effectId").value(DEFAULT_EFFECT_ID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where rarity equals to DEFAULT_RARITY
        defaultMTriggerEffectBaseShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mTriggerEffectBaseList where rarity equals to UPDATED_RARITY
        defaultMTriggerEffectBaseShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMTriggerEffectBaseShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mTriggerEffectBaseList where rarity equals to UPDATED_RARITY
        defaultMTriggerEffectBaseShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where rarity is not null
        defaultMTriggerEffectBaseShouldBeFound("rarity.specified=true");

        // Get all the mTriggerEffectBaseList where rarity is null
        defaultMTriggerEffectBaseShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where rarity greater than or equals to DEFAULT_RARITY
        defaultMTriggerEffectBaseShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mTriggerEffectBaseList where rarity greater than or equals to UPDATED_RARITY
        defaultMTriggerEffectBaseShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where rarity less than or equals to DEFAULT_RARITY
        defaultMTriggerEffectBaseShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mTriggerEffectBaseList where rarity less than or equals to UPDATED_RARITY
        defaultMTriggerEffectBaseShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectValue equals to DEFAULT_EFFECT_VALUE
        defaultMTriggerEffectBaseShouldBeFound("effectValue.equals=" + DEFAULT_EFFECT_VALUE);

        // Get all the mTriggerEffectBaseList where effectValue equals to UPDATED_EFFECT_VALUE
        defaultMTriggerEffectBaseShouldNotBeFound("effectValue.equals=" + UPDATED_EFFECT_VALUE);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectValueIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectValue in DEFAULT_EFFECT_VALUE or UPDATED_EFFECT_VALUE
        defaultMTriggerEffectBaseShouldBeFound("effectValue.in=" + DEFAULT_EFFECT_VALUE + "," + UPDATED_EFFECT_VALUE);

        // Get all the mTriggerEffectBaseList where effectValue equals to UPDATED_EFFECT_VALUE
        defaultMTriggerEffectBaseShouldNotBeFound("effectValue.in=" + UPDATED_EFFECT_VALUE);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectValue is not null
        defaultMTriggerEffectBaseShouldBeFound("effectValue.specified=true");

        // Get all the mTriggerEffectBaseList where effectValue is null
        defaultMTriggerEffectBaseShouldNotBeFound("effectValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectValue greater than or equals to DEFAULT_EFFECT_VALUE
        defaultMTriggerEffectBaseShouldBeFound("effectValue.greaterOrEqualThan=" + DEFAULT_EFFECT_VALUE);

        // Get all the mTriggerEffectBaseList where effectValue greater than or equals to UPDATED_EFFECT_VALUE
        defaultMTriggerEffectBaseShouldNotBeFound("effectValue.greaterOrEqualThan=" + UPDATED_EFFECT_VALUE);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectValue less than or equals to DEFAULT_EFFECT_VALUE
        defaultMTriggerEffectBaseShouldNotBeFound("effectValue.lessThan=" + DEFAULT_EFFECT_VALUE);

        // Get all the mTriggerEffectBaseList where effectValue less than or equals to UPDATED_EFFECT_VALUE
        defaultMTriggerEffectBaseShouldBeFound("effectValue.lessThan=" + UPDATED_EFFECT_VALUE);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTriggerProbabilityIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where triggerProbability equals to DEFAULT_TRIGGER_PROBABILITY
        defaultMTriggerEffectBaseShouldBeFound("triggerProbability.equals=" + DEFAULT_TRIGGER_PROBABILITY);

        // Get all the mTriggerEffectBaseList where triggerProbability equals to UPDATED_TRIGGER_PROBABILITY
        defaultMTriggerEffectBaseShouldNotBeFound("triggerProbability.equals=" + UPDATED_TRIGGER_PROBABILITY);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTriggerProbabilityIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where triggerProbability in DEFAULT_TRIGGER_PROBABILITY or UPDATED_TRIGGER_PROBABILITY
        defaultMTriggerEffectBaseShouldBeFound("triggerProbability.in=" + DEFAULT_TRIGGER_PROBABILITY + "," + UPDATED_TRIGGER_PROBABILITY);

        // Get all the mTriggerEffectBaseList where triggerProbability equals to UPDATED_TRIGGER_PROBABILITY
        defaultMTriggerEffectBaseShouldNotBeFound("triggerProbability.in=" + UPDATED_TRIGGER_PROBABILITY);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTriggerProbabilityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where triggerProbability is not null
        defaultMTriggerEffectBaseShouldBeFound("triggerProbability.specified=true");

        // Get all the mTriggerEffectBaseList where triggerProbability is null
        defaultMTriggerEffectBaseShouldNotBeFound("triggerProbability.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTriggerProbabilityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where triggerProbability greater than or equals to DEFAULT_TRIGGER_PROBABILITY
        defaultMTriggerEffectBaseShouldBeFound("triggerProbability.greaterOrEqualThan=" + DEFAULT_TRIGGER_PROBABILITY);

        // Get all the mTriggerEffectBaseList where triggerProbability greater than or equals to UPDATED_TRIGGER_PROBABILITY
        defaultMTriggerEffectBaseShouldNotBeFound("triggerProbability.greaterOrEqualThan=" + UPDATED_TRIGGER_PROBABILITY);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTriggerProbabilityIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where triggerProbability less than or equals to DEFAULT_TRIGGER_PROBABILITY
        defaultMTriggerEffectBaseShouldNotBeFound("triggerProbability.lessThan=" + DEFAULT_TRIGGER_PROBABILITY);

        // Get all the mTriggerEffectBaseList where triggerProbability less than or equals to UPDATED_TRIGGER_PROBABILITY
        defaultMTriggerEffectBaseShouldBeFound("triggerProbability.lessThan=" + UPDATED_TRIGGER_PROBABILITY);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTargetCardParameterIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where targetCardParameter equals to DEFAULT_TARGET_CARD_PARAMETER
        defaultMTriggerEffectBaseShouldBeFound("targetCardParameter.equals=" + DEFAULT_TARGET_CARD_PARAMETER);

        // Get all the mTriggerEffectBaseList where targetCardParameter equals to UPDATED_TARGET_CARD_PARAMETER
        defaultMTriggerEffectBaseShouldNotBeFound("targetCardParameter.equals=" + UPDATED_TARGET_CARD_PARAMETER);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTargetCardParameterIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where targetCardParameter in DEFAULT_TARGET_CARD_PARAMETER or UPDATED_TARGET_CARD_PARAMETER
        defaultMTriggerEffectBaseShouldBeFound("targetCardParameter.in=" + DEFAULT_TARGET_CARD_PARAMETER + "," + UPDATED_TARGET_CARD_PARAMETER);

        // Get all the mTriggerEffectBaseList where targetCardParameter equals to UPDATED_TARGET_CARD_PARAMETER
        defaultMTriggerEffectBaseShouldNotBeFound("targetCardParameter.in=" + UPDATED_TARGET_CARD_PARAMETER);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTargetCardParameterIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where targetCardParameter is not null
        defaultMTriggerEffectBaseShouldBeFound("targetCardParameter.specified=true");

        // Get all the mTriggerEffectBaseList where targetCardParameter is null
        defaultMTriggerEffectBaseShouldNotBeFound("targetCardParameter.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTargetCardParameterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where targetCardParameter greater than or equals to DEFAULT_TARGET_CARD_PARAMETER
        defaultMTriggerEffectBaseShouldBeFound("targetCardParameter.greaterOrEqualThan=" + DEFAULT_TARGET_CARD_PARAMETER);

        // Get all the mTriggerEffectBaseList where targetCardParameter greater than or equals to UPDATED_TARGET_CARD_PARAMETER
        defaultMTriggerEffectBaseShouldNotBeFound("targetCardParameter.greaterOrEqualThan=" + UPDATED_TARGET_CARD_PARAMETER);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByTargetCardParameterIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where targetCardParameter less than or equals to DEFAULT_TARGET_CARD_PARAMETER
        defaultMTriggerEffectBaseShouldNotBeFound("targetCardParameter.lessThan=" + DEFAULT_TARGET_CARD_PARAMETER);

        // Get all the mTriggerEffectBaseList where targetCardParameter less than or equals to UPDATED_TARGET_CARD_PARAMETER
        defaultMTriggerEffectBaseShouldBeFound("targetCardParameter.lessThan=" + UPDATED_TARGET_CARD_PARAMETER);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectId equals to DEFAULT_EFFECT_ID
        defaultMTriggerEffectBaseShouldBeFound("effectId.equals=" + DEFAULT_EFFECT_ID);

        // Get all the mTriggerEffectBaseList where effectId equals to UPDATED_EFFECT_ID
        defaultMTriggerEffectBaseShouldNotBeFound("effectId.equals=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectId in DEFAULT_EFFECT_ID or UPDATED_EFFECT_ID
        defaultMTriggerEffectBaseShouldBeFound("effectId.in=" + DEFAULT_EFFECT_ID + "," + UPDATED_EFFECT_ID);

        // Get all the mTriggerEffectBaseList where effectId equals to UPDATED_EFFECT_ID
        defaultMTriggerEffectBaseShouldNotBeFound("effectId.in=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectId is not null
        defaultMTriggerEffectBaseShouldBeFound("effectId.specified=true");

        // Get all the mTriggerEffectBaseList where effectId is null
        defaultMTriggerEffectBaseShouldNotBeFound("effectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectId greater than or equals to DEFAULT_EFFECT_ID
        defaultMTriggerEffectBaseShouldBeFound("effectId.greaterOrEqualThan=" + DEFAULT_EFFECT_ID);

        // Get all the mTriggerEffectBaseList where effectId greater than or equals to UPDATED_EFFECT_ID
        defaultMTriggerEffectBaseShouldNotBeFound("effectId.greaterOrEqualThan=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        // Get all the mTriggerEffectBaseList where effectId less than or equals to DEFAULT_EFFECT_ID
        defaultMTriggerEffectBaseShouldNotBeFound("effectId.lessThan=" + DEFAULT_EFFECT_ID);

        // Get all the mTriggerEffectBaseList where effectId less than or equals to UPDATED_EFFECT_ID
        defaultMTriggerEffectBaseShouldBeFound("effectId.lessThan=" + UPDATED_EFFECT_ID);
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByMtriggereffectrangeIsEqualToSomething() throws Exception {
        // Get already existing entity
        MTriggerEffectRange mtriggereffectrange = mTriggerEffectBase.getMtriggereffectrange();
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);
        Long mtriggereffectrangeId = mtriggereffectrange.getId();

        // Get all the mTriggerEffectBaseList where mtriggereffectrange equals to mtriggereffectrangeId
        defaultMTriggerEffectBaseShouldBeFound("mtriggereffectrangeId.equals=" + mtriggereffectrangeId);

        // Get all the mTriggerEffectBaseList where mtriggereffectrange equals to mtriggereffectrangeId + 1
        defaultMTriggerEffectBaseShouldNotBeFound("mtriggereffectrangeId.equals=" + (mtriggereffectrangeId + 1));
    }


    @Test
    @Transactional
    public void getAllMTriggerEffectBasesByMTargetTriggerEffectGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        MTargetTriggerEffectGroup mTargetTriggerEffectGroup = MTargetTriggerEffectGroupResourceIT.createEntity(em);
        em.persist(mTargetTriggerEffectGroup);
        em.flush();
        mTriggerEffectBase.addMTargetTriggerEffectGroup(mTargetTriggerEffectGroup);
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);
        Long mTargetTriggerEffectGroupId = mTargetTriggerEffectGroup.getId();

        // Get all the mTriggerEffectBaseList where mTargetTriggerEffectGroup equals to mTargetTriggerEffectGroupId
        defaultMTriggerEffectBaseShouldBeFound("mTargetTriggerEffectGroupId.equals=" + mTargetTriggerEffectGroupId);

        // Get all the mTriggerEffectBaseList where mTargetTriggerEffectGroup equals to mTargetTriggerEffectGroupId + 1
        defaultMTriggerEffectBaseShouldNotBeFound("mTargetTriggerEffectGroupId.equals=" + (mTargetTriggerEffectGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTriggerEffectBaseShouldBeFound(String filter) throws Exception {
        restMTriggerEffectBaseMockMvc.perform(get("/api/m-trigger-effect-bases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTriggerEffectBase.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].effectValue").value(hasItem(DEFAULT_EFFECT_VALUE)))
            .andExpect(jsonPath("$.[*].triggerProbability").value(hasItem(DEFAULT_TRIGGER_PROBABILITY)))
            .andExpect(jsonPath("$.[*].targetCardParameter").value(hasItem(DEFAULT_TARGET_CARD_PARAMETER)))
            .andExpect(jsonPath("$.[*].effectId").value(hasItem(DEFAULT_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMTriggerEffectBaseMockMvc.perform(get("/api/m-trigger-effect-bases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTriggerEffectBaseShouldNotBeFound(String filter) throws Exception {
        restMTriggerEffectBaseMockMvc.perform(get("/api/m-trigger-effect-bases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTriggerEffectBaseMockMvc.perform(get("/api/m-trigger-effect-bases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTriggerEffectBase() throws Exception {
        // Get the mTriggerEffectBase
        restMTriggerEffectBaseMockMvc.perform(get("/api/m-trigger-effect-bases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTriggerEffectBase() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        int databaseSizeBeforeUpdate = mTriggerEffectBaseRepository.findAll().size();

        // Update the mTriggerEffectBase
        MTriggerEffectBase updatedMTriggerEffectBase = mTriggerEffectBaseRepository.findById(mTriggerEffectBase.getId()).get();
        // Disconnect from session so that the updates on updatedMTriggerEffectBase are not directly saved in db
        em.detach(updatedMTriggerEffectBase);
        updatedMTriggerEffectBase
            .name(UPDATED_NAME)
            .rarity(UPDATED_RARITY)
            .effectValue(UPDATED_EFFECT_VALUE)
            .triggerProbability(UPDATED_TRIGGER_PROBABILITY)
            .targetCardParameter(UPDATED_TARGET_CARD_PARAMETER)
            .effectId(UPDATED_EFFECT_ID)
            .description(UPDATED_DESCRIPTION);
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO = mTriggerEffectBaseMapper.toDto(updatedMTriggerEffectBase);

        restMTriggerEffectBaseMockMvc.perform(put("/api/m-trigger-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectBaseDTO)))
            .andExpect(status().isOk());

        // Validate the MTriggerEffectBase in the database
        List<MTriggerEffectBase> mTriggerEffectBaseList = mTriggerEffectBaseRepository.findAll();
        assertThat(mTriggerEffectBaseList).hasSize(databaseSizeBeforeUpdate);
        MTriggerEffectBase testMTriggerEffectBase = mTriggerEffectBaseList.get(mTriggerEffectBaseList.size() - 1);
        assertThat(testMTriggerEffectBase.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMTriggerEffectBase.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMTriggerEffectBase.getEffectValue()).isEqualTo(UPDATED_EFFECT_VALUE);
        assertThat(testMTriggerEffectBase.getTriggerProbability()).isEqualTo(UPDATED_TRIGGER_PROBABILITY);
        assertThat(testMTriggerEffectBase.getTargetCardParameter()).isEqualTo(UPDATED_TARGET_CARD_PARAMETER);
        assertThat(testMTriggerEffectBase.getEffectId()).isEqualTo(UPDATED_EFFECT_ID);
        assertThat(testMTriggerEffectBase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMTriggerEffectBase() throws Exception {
        int databaseSizeBeforeUpdate = mTriggerEffectBaseRepository.findAll().size();

        // Create the MTriggerEffectBase
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO = mTriggerEffectBaseMapper.toDto(mTriggerEffectBase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTriggerEffectBaseMockMvc.perform(put("/api/m-trigger-effect-bases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTriggerEffectBaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTriggerEffectBase in the database
        List<MTriggerEffectBase> mTriggerEffectBaseList = mTriggerEffectBaseRepository.findAll();
        assertThat(mTriggerEffectBaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTriggerEffectBase() throws Exception {
        // Initialize the database
        mTriggerEffectBaseRepository.saveAndFlush(mTriggerEffectBase);

        int databaseSizeBeforeDelete = mTriggerEffectBaseRepository.findAll().size();

        // Delete the mTriggerEffectBase
        restMTriggerEffectBaseMockMvc.perform(delete("/api/m-trigger-effect-bases/{id}", mTriggerEffectBase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTriggerEffectBase> mTriggerEffectBaseList = mTriggerEffectBaseRepository.findAll();
        assertThat(mTriggerEffectBaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTriggerEffectBase.class);
        MTriggerEffectBase mTriggerEffectBase1 = new MTriggerEffectBase();
        mTriggerEffectBase1.setId(1L);
        MTriggerEffectBase mTriggerEffectBase2 = new MTriggerEffectBase();
        mTriggerEffectBase2.setId(mTriggerEffectBase1.getId());
        assertThat(mTriggerEffectBase1).isEqualTo(mTriggerEffectBase2);
        mTriggerEffectBase2.setId(2L);
        assertThat(mTriggerEffectBase1).isNotEqualTo(mTriggerEffectBase2);
        mTriggerEffectBase1.setId(null);
        assertThat(mTriggerEffectBase1).isNotEqualTo(mTriggerEffectBase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTriggerEffectBaseDTO.class);
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO1 = new MTriggerEffectBaseDTO();
        mTriggerEffectBaseDTO1.setId(1L);
        MTriggerEffectBaseDTO mTriggerEffectBaseDTO2 = new MTriggerEffectBaseDTO();
        assertThat(mTriggerEffectBaseDTO1).isNotEqualTo(mTriggerEffectBaseDTO2);
        mTriggerEffectBaseDTO2.setId(mTriggerEffectBaseDTO1.getId());
        assertThat(mTriggerEffectBaseDTO1).isEqualTo(mTriggerEffectBaseDTO2);
        mTriggerEffectBaseDTO2.setId(2L);
        assertThat(mTriggerEffectBaseDTO1).isNotEqualTo(mTriggerEffectBaseDTO2);
        mTriggerEffectBaseDTO1.setId(null);
        assertThat(mTriggerEffectBaseDTO1).isNotEqualTo(mTriggerEffectBaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTriggerEffectBaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTriggerEffectBaseMapper.fromId(null)).isNull();
    }
}
