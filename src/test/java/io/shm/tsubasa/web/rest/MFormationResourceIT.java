package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MFormation;
import io.shm.tsubasa.domain.MPassiveEffectRange;
import io.shm.tsubasa.domain.MNpcDeck;
import io.shm.tsubasa.domain.MTargetFormationGroup;
import io.shm.tsubasa.repository.MFormationRepository;
import io.shm.tsubasa.service.MFormationService;
import io.shm.tsubasa.service.dto.MFormationDTO;
import io.shm.tsubasa.service.mapper.MFormationMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MFormationCriteria;
import io.shm.tsubasa.service.MFormationQueryService;

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
 * Integration tests for the {@Link MFormationResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MFormationResourceIT {

    private static final Integer DEFAULT_EFFECT_VALUE = 1;
    private static final Integer UPDATED_EFFECT_VALUE = 2;

    private static final Integer DEFAULT_EFFECT_PROBABILITY = 1;
    private static final Integer UPDATED_EFFECT_PROBABILITY = 2;

    private static final String DEFAULT_FORMATION_ARRANGEMENT_FW = "AAAAAAAAAA";
    private static final String UPDATED_FORMATION_ARRANGEMENT_FW = "BBBBBBBBBB";

    private static final String DEFAULT_FORMATION_ARRANGEMENT_OMF = "AAAAAAAAAA";
    private static final String UPDATED_FORMATION_ARRANGEMENT_OMF = "BBBBBBBBBB";

    private static final String DEFAULT_FORMATION_ARRANGEMENT_DMF = "AAAAAAAAAA";
    private static final String UPDATED_FORMATION_ARRANGEMENT_DMF = "BBBBBBBBBB";

    private static final String DEFAULT_FORMATION_ARRANGEMENT_DF = "AAAAAAAAAA";
    private static final String UPDATED_FORMATION_ARRANGEMENT_DF = "BBBBBBBBBB";

    private static final Integer DEFAULT_EFFECT_ID = 1;
    private static final Integer UPDATED_EFFECT_ID = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STARTING_UNIFORM_NOS = "AAAAAAAAAA";
    private static final String UPDATED_STARTING_UNIFORM_NOS = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_UNIFORM_NOS = "AAAAAAAAAA";
    private static final String UPDATED_SUB_UNIFORM_NOS = "BBBBBBBBBB";

    private static final Integer DEFAULT_EX_TYPE = 1;
    private static final Integer UPDATED_EX_TYPE = 2;

    private static final Integer DEFAULT_MATCH_FORMATION_ID = 1;
    private static final Integer UPDATED_MATCH_FORMATION_ID = 2;

    @Autowired
    private MFormationRepository mFormationRepository;

    @Autowired
    private MFormationMapper mFormationMapper;

    @Autowired
    private MFormationService mFormationService;

    @Autowired
    private MFormationQueryService mFormationQueryService;

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

    private MockMvc restMFormationMockMvc;

    private MFormation mFormation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MFormationResource mFormationResource = new MFormationResource(mFormationService, mFormationQueryService);
        this.restMFormationMockMvc = MockMvcBuilders.standaloneSetup(mFormationResource)
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
    public static MFormation createEntity(EntityManager em) {
        MFormation mFormation = new MFormation()
            .effectValue(DEFAULT_EFFECT_VALUE)
            .effectProbability(DEFAULT_EFFECT_PROBABILITY)
            .formationArrangementFw(DEFAULT_FORMATION_ARRANGEMENT_FW)
            .formationArrangementOmf(DEFAULT_FORMATION_ARRANGEMENT_OMF)
            .formationArrangementDmf(DEFAULT_FORMATION_ARRANGEMENT_DMF)
            .formationArrangementDf(DEFAULT_FORMATION_ARRANGEMENT_DF)
            .effectId(DEFAULT_EFFECT_ID)
            .description(DEFAULT_DESCRIPTION)
            .shortDescription(DEFAULT_SHORT_DESCRIPTION)
            .name(DEFAULT_NAME)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME)
            .startingUniformNos(DEFAULT_STARTING_UNIFORM_NOS)
            .subUniformNos(DEFAULT_SUB_UNIFORM_NOS)
            .exType(DEFAULT_EX_TYPE)
            .matchFormationId(DEFAULT_MATCH_FORMATION_ID);
        // Add required entity
        MPassiveEffectRange mPassiveEffectRange;
        if (TestUtil.findAll(em, MPassiveEffectRange.class).isEmpty()) {
            mPassiveEffectRange = MPassiveEffectRangeResourceIT.createEntity(em);
            em.persist(mPassiveEffectRange);
            em.flush();
        } else {
            mPassiveEffectRange = TestUtil.findAll(em, MPassiveEffectRange.class).get(0);
        }
        mFormation.setMpassiveeffectrange(mPassiveEffectRange);
        return mFormation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MFormation createUpdatedEntity(EntityManager em) {
        MFormation mFormation = new MFormation()
            .effectValue(UPDATED_EFFECT_VALUE)
            .effectProbability(UPDATED_EFFECT_PROBABILITY)
            .formationArrangementFw(UPDATED_FORMATION_ARRANGEMENT_FW)
            .formationArrangementOmf(UPDATED_FORMATION_ARRANGEMENT_OMF)
            .formationArrangementDmf(UPDATED_FORMATION_ARRANGEMENT_DMF)
            .formationArrangementDf(UPDATED_FORMATION_ARRANGEMENT_DF)
            .effectId(UPDATED_EFFECT_ID)
            .description(UPDATED_DESCRIPTION)
            .shortDescription(UPDATED_SHORT_DESCRIPTION)
            .name(UPDATED_NAME)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .startingUniformNos(UPDATED_STARTING_UNIFORM_NOS)
            .subUniformNos(UPDATED_SUB_UNIFORM_NOS)
            .exType(UPDATED_EX_TYPE)
            .matchFormationId(UPDATED_MATCH_FORMATION_ID);
        // Add required entity
        MPassiveEffectRange mPassiveEffectRange;
        if (TestUtil.findAll(em, MPassiveEffectRange.class).isEmpty()) {
            mPassiveEffectRange = MPassiveEffectRangeResourceIT.createUpdatedEntity(em);
            em.persist(mPassiveEffectRange);
            em.flush();
        } else {
            mPassiveEffectRange = TestUtil.findAll(em, MPassiveEffectRange.class).get(0);
        }
        mFormation.setMpassiveeffectrange(mPassiveEffectRange);
        return mFormation;
    }

    @BeforeEach
    public void initTest() {
        mFormation = createEntity(em);
    }

    @Test
    @Transactional
    public void createMFormation() throws Exception {
        int databaseSizeBeforeCreate = mFormationRepository.findAll().size();

        // Create the MFormation
        MFormationDTO mFormationDTO = mFormationMapper.toDto(mFormation);
        restMFormationMockMvc.perform(post("/api/m-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFormationDTO)))
            .andExpect(status().isCreated());

        // Validate the MFormation in the database
        List<MFormation> mFormationList = mFormationRepository.findAll();
        assertThat(mFormationList).hasSize(databaseSizeBeforeCreate + 1);
        MFormation testMFormation = mFormationList.get(mFormationList.size() - 1);
        assertThat(testMFormation.getEffectValue()).isEqualTo(DEFAULT_EFFECT_VALUE);
        assertThat(testMFormation.getEffectProbability()).isEqualTo(DEFAULT_EFFECT_PROBABILITY);
        assertThat(testMFormation.getFormationArrangementFw()).isEqualTo(DEFAULT_FORMATION_ARRANGEMENT_FW);
        assertThat(testMFormation.getFormationArrangementOmf()).isEqualTo(DEFAULT_FORMATION_ARRANGEMENT_OMF);
        assertThat(testMFormation.getFormationArrangementDmf()).isEqualTo(DEFAULT_FORMATION_ARRANGEMENT_DMF);
        assertThat(testMFormation.getFormationArrangementDf()).isEqualTo(DEFAULT_FORMATION_ARRANGEMENT_DF);
        assertThat(testMFormation.getEffectId()).isEqualTo(DEFAULT_EFFECT_ID);
        assertThat(testMFormation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMFormation.getShortDescription()).isEqualTo(DEFAULT_SHORT_DESCRIPTION);
        assertThat(testMFormation.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMFormation.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
        assertThat(testMFormation.getStartingUniformNos()).isEqualTo(DEFAULT_STARTING_UNIFORM_NOS);
        assertThat(testMFormation.getSubUniformNos()).isEqualTo(DEFAULT_SUB_UNIFORM_NOS);
        assertThat(testMFormation.getExType()).isEqualTo(DEFAULT_EX_TYPE);
        assertThat(testMFormation.getMatchFormationId()).isEqualTo(DEFAULT_MATCH_FORMATION_ID);
    }

    @Test
    @Transactional
    public void createMFormationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mFormationRepository.findAll().size();

        // Create the MFormation with an existing ID
        mFormation.setId(1L);
        MFormationDTO mFormationDTO = mFormationMapper.toDto(mFormation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMFormationMockMvc.perform(post("/api/m-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFormationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MFormation in the database
        List<MFormation> mFormationList = mFormationRepository.findAll();
        assertThat(mFormationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mFormationRepository.findAll().size();
        // set the field null
        mFormation.setEffectValue(null);

        // Create the MFormation, which fails.
        MFormationDTO mFormationDTO = mFormationMapper.toDto(mFormation);

        restMFormationMockMvc.perform(post("/api/m-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MFormation> mFormationList = mFormationRepository.findAll();
        assertThat(mFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectProbabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mFormationRepository.findAll().size();
        // set the field null
        mFormation.setEffectProbability(null);

        // Create the MFormation, which fails.
        MFormationDTO mFormationDTO = mFormationMapper.toDto(mFormation);

        restMFormationMockMvc.perform(post("/api/m-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MFormation> mFormationList = mFormationRepository.findAll();
        assertThat(mFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatchFormationIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mFormationRepository.findAll().size();
        // set the field null
        mFormation.setMatchFormationId(null);

        // Create the MFormation, which fails.
        MFormationDTO mFormationDTO = mFormationMapper.toDto(mFormation);

        restMFormationMockMvc.perform(post("/api/m-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFormationDTO)))
            .andExpect(status().isBadRequest());

        List<MFormation> mFormationList = mFormationRepository.findAll();
        assertThat(mFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMFormations() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList
        restMFormationMockMvc.perform(get("/api/m-formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectValue").value(hasItem(DEFAULT_EFFECT_VALUE)))
            .andExpect(jsonPath("$.[*].effectProbability").value(hasItem(DEFAULT_EFFECT_PROBABILITY)))
            .andExpect(jsonPath("$.[*].formationArrangementFw").value(hasItem(DEFAULT_FORMATION_ARRANGEMENT_FW.toString())))
            .andExpect(jsonPath("$.[*].formationArrangementOmf").value(hasItem(DEFAULT_FORMATION_ARRANGEMENT_OMF.toString())))
            .andExpect(jsonPath("$.[*].formationArrangementDmf").value(hasItem(DEFAULT_FORMATION_ARRANGEMENT_DMF.toString())))
            .andExpect(jsonPath("$.[*].formationArrangementDf").value(hasItem(DEFAULT_FORMATION_ARRANGEMENT_DF.toString())))
            .andExpect(jsonPath("$.[*].effectId").value(hasItem(DEFAULT_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].shortDescription").value(hasItem(DEFAULT_SHORT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].startingUniformNos").value(hasItem(DEFAULT_STARTING_UNIFORM_NOS.toString())))
            .andExpect(jsonPath("$.[*].subUniformNos").value(hasItem(DEFAULT_SUB_UNIFORM_NOS.toString())))
            .andExpect(jsonPath("$.[*].exType").value(hasItem(DEFAULT_EX_TYPE)))
            .andExpect(jsonPath("$.[*].matchFormationId").value(hasItem(DEFAULT_MATCH_FORMATION_ID)));
    }
    
    @Test
    @Transactional
    public void getMFormation() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get the mFormation
        restMFormationMockMvc.perform(get("/api/m-formations/{id}", mFormation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mFormation.getId().intValue()))
            .andExpect(jsonPath("$.effectValue").value(DEFAULT_EFFECT_VALUE))
            .andExpect(jsonPath("$.effectProbability").value(DEFAULT_EFFECT_PROBABILITY))
            .andExpect(jsonPath("$.formationArrangementFw").value(DEFAULT_FORMATION_ARRANGEMENT_FW.toString()))
            .andExpect(jsonPath("$.formationArrangementOmf").value(DEFAULT_FORMATION_ARRANGEMENT_OMF.toString()))
            .andExpect(jsonPath("$.formationArrangementDmf").value(DEFAULT_FORMATION_ARRANGEMENT_DMF.toString()))
            .andExpect(jsonPath("$.formationArrangementDf").value(DEFAULT_FORMATION_ARRANGEMENT_DF.toString()))
            .andExpect(jsonPath("$.effectId").value(DEFAULT_EFFECT_ID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.shortDescription").value(DEFAULT_SHORT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.startingUniformNos").value(DEFAULT_STARTING_UNIFORM_NOS.toString()))
            .andExpect(jsonPath("$.subUniformNos").value(DEFAULT_SUB_UNIFORM_NOS.toString()))
            .andExpect(jsonPath("$.exType").value(DEFAULT_EX_TYPE))
            .andExpect(jsonPath("$.matchFormationId").value(DEFAULT_MATCH_FORMATION_ID));
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectValue equals to DEFAULT_EFFECT_VALUE
        defaultMFormationShouldBeFound("effectValue.equals=" + DEFAULT_EFFECT_VALUE);

        // Get all the mFormationList where effectValue equals to UPDATED_EFFECT_VALUE
        defaultMFormationShouldNotBeFound("effectValue.equals=" + UPDATED_EFFECT_VALUE);
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectValueIsInShouldWork() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectValue in DEFAULT_EFFECT_VALUE or UPDATED_EFFECT_VALUE
        defaultMFormationShouldBeFound("effectValue.in=" + DEFAULT_EFFECT_VALUE + "," + UPDATED_EFFECT_VALUE);

        // Get all the mFormationList where effectValue equals to UPDATED_EFFECT_VALUE
        defaultMFormationShouldNotBeFound("effectValue.in=" + UPDATED_EFFECT_VALUE);
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectValue is not null
        defaultMFormationShouldBeFound("effectValue.specified=true");

        // Get all the mFormationList where effectValue is null
        defaultMFormationShouldNotBeFound("effectValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectValue greater than or equals to DEFAULT_EFFECT_VALUE
        defaultMFormationShouldBeFound("effectValue.greaterOrEqualThan=" + DEFAULT_EFFECT_VALUE);

        // Get all the mFormationList where effectValue greater than or equals to UPDATED_EFFECT_VALUE
        defaultMFormationShouldNotBeFound("effectValue.greaterOrEqualThan=" + UPDATED_EFFECT_VALUE);
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectValue less than or equals to DEFAULT_EFFECT_VALUE
        defaultMFormationShouldNotBeFound("effectValue.lessThan=" + DEFAULT_EFFECT_VALUE);

        // Get all the mFormationList where effectValue less than or equals to UPDATED_EFFECT_VALUE
        defaultMFormationShouldBeFound("effectValue.lessThan=" + UPDATED_EFFECT_VALUE);
    }


    @Test
    @Transactional
    public void getAllMFormationsByEffectProbabilityIsEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectProbability equals to DEFAULT_EFFECT_PROBABILITY
        defaultMFormationShouldBeFound("effectProbability.equals=" + DEFAULT_EFFECT_PROBABILITY);

        // Get all the mFormationList where effectProbability equals to UPDATED_EFFECT_PROBABILITY
        defaultMFormationShouldNotBeFound("effectProbability.equals=" + UPDATED_EFFECT_PROBABILITY);
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectProbabilityIsInShouldWork() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectProbability in DEFAULT_EFFECT_PROBABILITY or UPDATED_EFFECT_PROBABILITY
        defaultMFormationShouldBeFound("effectProbability.in=" + DEFAULT_EFFECT_PROBABILITY + "," + UPDATED_EFFECT_PROBABILITY);

        // Get all the mFormationList where effectProbability equals to UPDATED_EFFECT_PROBABILITY
        defaultMFormationShouldNotBeFound("effectProbability.in=" + UPDATED_EFFECT_PROBABILITY);
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectProbabilityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectProbability is not null
        defaultMFormationShouldBeFound("effectProbability.specified=true");

        // Get all the mFormationList where effectProbability is null
        defaultMFormationShouldNotBeFound("effectProbability.specified=false");
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectProbabilityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectProbability greater than or equals to DEFAULT_EFFECT_PROBABILITY
        defaultMFormationShouldBeFound("effectProbability.greaterOrEqualThan=" + DEFAULT_EFFECT_PROBABILITY);

        // Get all the mFormationList where effectProbability greater than or equals to UPDATED_EFFECT_PROBABILITY
        defaultMFormationShouldNotBeFound("effectProbability.greaterOrEqualThan=" + UPDATED_EFFECT_PROBABILITY);
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectProbabilityIsLessThanSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectProbability less than or equals to DEFAULT_EFFECT_PROBABILITY
        defaultMFormationShouldNotBeFound("effectProbability.lessThan=" + DEFAULT_EFFECT_PROBABILITY);

        // Get all the mFormationList where effectProbability less than or equals to UPDATED_EFFECT_PROBABILITY
        defaultMFormationShouldBeFound("effectProbability.lessThan=" + UPDATED_EFFECT_PROBABILITY);
    }


    @Test
    @Transactional
    public void getAllMFormationsByEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectId equals to DEFAULT_EFFECT_ID
        defaultMFormationShouldBeFound("effectId.equals=" + DEFAULT_EFFECT_ID);

        // Get all the mFormationList where effectId equals to UPDATED_EFFECT_ID
        defaultMFormationShouldNotBeFound("effectId.equals=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectId in DEFAULT_EFFECT_ID or UPDATED_EFFECT_ID
        defaultMFormationShouldBeFound("effectId.in=" + DEFAULT_EFFECT_ID + "," + UPDATED_EFFECT_ID);

        // Get all the mFormationList where effectId equals to UPDATED_EFFECT_ID
        defaultMFormationShouldNotBeFound("effectId.in=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectId is not null
        defaultMFormationShouldBeFound("effectId.specified=true");

        // Get all the mFormationList where effectId is null
        defaultMFormationShouldNotBeFound("effectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectId greater than or equals to DEFAULT_EFFECT_ID
        defaultMFormationShouldBeFound("effectId.greaterOrEqualThan=" + DEFAULT_EFFECT_ID);

        // Get all the mFormationList where effectId greater than or equals to UPDATED_EFFECT_ID
        defaultMFormationShouldNotBeFound("effectId.greaterOrEqualThan=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMFormationsByEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where effectId less than or equals to DEFAULT_EFFECT_ID
        defaultMFormationShouldNotBeFound("effectId.lessThan=" + DEFAULT_EFFECT_ID);

        // Get all the mFormationList where effectId less than or equals to UPDATED_EFFECT_ID
        defaultMFormationShouldBeFound("effectId.lessThan=" + UPDATED_EFFECT_ID);
    }


    @Test
    @Transactional
    public void getAllMFormationsByExTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where exType equals to DEFAULT_EX_TYPE
        defaultMFormationShouldBeFound("exType.equals=" + DEFAULT_EX_TYPE);

        // Get all the mFormationList where exType equals to UPDATED_EX_TYPE
        defaultMFormationShouldNotBeFound("exType.equals=" + UPDATED_EX_TYPE);
    }

    @Test
    @Transactional
    public void getAllMFormationsByExTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where exType in DEFAULT_EX_TYPE or UPDATED_EX_TYPE
        defaultMFormationShouldBeFound("exType.in=" + DEFAULT_EX_TYPE + "," + UPDATED_EX_TYPE);

        // Get all the mFormationList where exType equals to UPDATED_EX_TYPE
        defaultMFormationShouldNotBeFound("exType.in=" + UPDATED_EX_TYPE);
    }

    @Test
    @Transactional
    public void getAllMFormationsByExTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where exType is not null
        defaultMFormationShouldBeFound("exType.specified=true");

        // Get all the mFormationList where exType is null
        defaultMFormationShouldNotBeFound("exType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMFormationsByExTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where exType greater than or equals to DEFAULT_EX_TYPE
        defaultMFormationShouldBeFound("exType.greaterOrEqualThan=" + DEFAULT_EX_TYPE);

        // Get all the mFormationList where exType greater than or equals to UPDATED_EX_TYPE
        defaultMFormationShouldNotBeFound("exType.greaterOrEqualThan=" + UPDATED_EX_TYPE);
    }

    @Test
    @Transactional
    public void getAllMFormationsByExTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where exType less than or equals to DEFAULT_EX_TYPE
        defaultMFormationShouldNotBeFound("exType.lessThan=" + DEFAULT_EX_TYPE);

        // Get all the mFormationList where exType less than or equals to UPDATED_EX_TYPE
        defaultMFormationShouldBeFound("exType.lessThan=" + UPDATED_EX_TYPE);
    }


    @Test
    @Transactional
    public void getAllMFormationsByMatchFormationIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where matchFormationId equals to DEFAULT_MATCH_FORMATION_ID
        defaultMFormationShouldBeFound("matchFormationId.equals=" + DEFAULT_MATCH_FORMATION_ID);

        // Get all the mFormationList where matchFormationId equals to UPDATED_MATCH_FORMATION_ID
        defaultMFormationShouldNotBeFound("matchFormationId.equals=" + UPDATED_MATCH_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMFormationsByMatchFormationIdIsInShouldWork() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where matchFormationId in DEFAULT_MATCH_FORMATION_ID or UPDATED_MATCH_FORMATION_ID
        defaultMFormationShouldBeFound("matchFormationId.in=" + DEFAULT_MATCH_FORMATION_ID + "," + UPDATED_MATCH_FORMATION_ID);

        // Get all the mFormationList where matchFormationId equals to UPDATED_MATCH_FORMATION_ID
        defaultMFormationShouldNotBeFound("matchFormationId.in=" + UPDATED_MATCH_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMFormationsByMatchFormationIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where matchFormationId is not null
        defaultMFormationShouldBeFound("matchFormationId.specified=true");

        // Get all the mFormationList where matchFormationId is null
        defaultMFormationShouldNotBeFound("matchFormationId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMFormationsByMatchFormationIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where matchFormationId greater than or equals to DEFAULT_MATCH_FORMATION_ID
        defaultMFormationShouldBeFound("matchFormationId.greaterOrEqualThan=" + DEFAULT_MATCH_FORMATION_ID);

        // Get all the mFormationList where matchFormationId greater than or equals to UPDATED_MATCH_FORMATION_ID
        defaultMFormationShouldNotBeFound("matchFormationId.greaterOrEqualThan=" + UPDATED_MATCH_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMFormationsByMatchFormationIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        // Get all the mFormationList where matchFormationId less than or equals to DEFAULT_MATCH_FORMATION_ID
        defaultMFormationShouldNotBeFound("matchFormationId.lessThan=" + DEFAULT_MATCH_FORMATION_ID);

        // Get all the mFormationList where matchFormationId less than or equals to UPDATED_MATCH_FORMATION_ID
        defaultMFormationShouldBeFound("matchFormationId.lessThan=" + UPDATED_MATCH_FORMATION_ID);
    }


    @Test
    @Transactional
    public void getAllMFormationsByMpassiveeffectrangeIsEqualToSomething() throws Exception {
        // Get already existing entity
        MPassiveEffectRange mpassiveeffectrange = mFormation.getMpassiveeffectrange();
        mFormationRepository.saveAndFlush(mFormation);
        Long mpassiveeffectrangeId = mpassiveeffectrange.getId();

        // Get all the mFormationList where mpassiveeffectrange equals to mpassiveeffectrangeId
        defaultMFormationShouldBeFound("mpassiveeffectrangeId.equals=" + mpassiveeffectrangeId);

        // Get all the mFormationList where mpassiveeffectrange equals to mpassiveeffectrangeId + 1
        defaultMFormationShouldNotBeFound("mpassiveeffectrangeId.equals=" + (mpassiveeffectrangeId + 1));
    }


    @Test
    @Transactional
    public void getAllMFormationsByMNpcDeckIsEqualToSomething() throws Exception {
        // Initialize the database
        MNpcDeck mNpcDeck = MNpcDeckResourceIT.createEntity(em);
        em.persist(mNpcDeck);
        em.flush();
        mFormation.addMNpcDeck(mNpcDeck);
        mFormationRepository.saveAndFlush(mFormation);
        Long mNpcDeckId = mNpcDeck.getId();

        // Get all the mFormationList where mNpcDeck equals to mNpcDeckId
        defaultMFormationShouldBeFound("mNpcDeckId.equals=" + mNpcDeckId);

        // Get all the mFormationList where mNpcDeck equals to mNpcDeckId + 1
        defaultMFormationShouldNotBeFound("mNpcDeckId.equals=" + (mNpcDeckId + 1));
    }


    @Test
    @Transactional
    public void getAllMFormationsByMTargetFormationGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        MTargetFormationGroup mTargetFormationGroup = MTargetFormationGroupResourceIT.createEntity(em);
        em.persist(mTargetFormationGroup);
        em.flush();
        mFormation.addMTargetFormationGroup(mTargetFormationGroup);
        mFormationRepository.saveAndFlush(mFormation);
        Long mTargetFormationGroupId = mTargetFormationGroup.getId();

        // Get all the mFormationList where mTargetFormationGroup equals to mTargetFormationGroupId
        defaultMFormationShouldBeFound("mTargetFormationGroupId.equals=" + mTargetFormationGroupId);

        // Get all the mFormationList where mTargetFormationGroup equals to mTargetFormationGroupId + 1
        defaultMFormationShouldNotBeFound("mTargetFormationGroupId.equals=" + (mTargetFormationGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMFormationShouldBeFound(String filter) throws Exception {
        restMFormationMockMvc.perform(get("/api/m-formations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectValue").value(hasItem(DEFAULT_EFFECT_VALUE)))
            .andExpect(jsonPath("$.[*].effectProbability").value(hasItem(DEFAULT_EFFECT_PROBABILITY)))
            .andExpect(jsonPath("$.[*].formationArrangementFw").value(hasItem(DEFAULT_FORMATION_ARRANGEMENT_FW.toString())))
            .andExpect(jsonPath("$.[*].formationArrangementOmf").value(hasItem(DEFAULT_FORMATION_ARRANGEMENT_OMF.toString())))
            .andExpect(jsonPath("$.[*].formationArrangementDmf").value(hasItem(DEFAULT_FORMATION_ARRANGEMENT_DMF.toString())))
            .andExpect(jsonPath("$.[*].formationArrangementDf").value(hasItem(DEFAULT_FORMATION_ARRANGEMENT_DF.toString())))
            .andExpect(jsonPath("$.[*].effectId").value(hasItem(DEFAULT_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].shortDescription").value(hasItem(DEFAULT_SHORT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].startingUniformNos").value(hasItem(DEFAULT_STARTING_UNIFORM_NOS.toString())))
            .andExpect(jsonPath("$.[*].subUniformNos").value(hasItem(DEFAULT_SUB_UNIFORM_NOS.toString())))
            .andExpect(jsonPath("$.[*].exType").value(hasItem(DEFAULT_EX_TYPE)))
            .andExpect(jsonPath("$.[*].matchFormationId").value(hasItem(DEFAULT_MATCH_FORMATION_ID)));

        // Check, that the count call also returns 1
        restMFormationMockMvc.perform(get("/api/m-formations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMFormationShouldNotBeFound(String filter) throws Exception {
        restMFormationMockMvc.perform(get("/api/m-formations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMFormationMockMvc.perform(get("/api/m-formations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMFormation() throws Exception {
        // Get the mFormation
        restMFormationMockMvc.perform(get("/api/m-formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMFormation() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        int databaseSizeBeforeUpdate = mFormationRepository.findAll().size();

        // Update the mFormation
        MFormation updatedMFormation = mFormationRepository.findById(mFormation.getId()).get();
        // Disconnect from session so that the updates on updatedMFormation are not directly saved in db
        em.detach(updatedMFormation);
        updatedMFormation
            .effectValue(UPDATED_EFFECT_VALUE)
            .effectProbability(UPDATED_EFFECT_PROBABILITY)
            .formationArrangementFw(UPDATED_FORMATION_ARRANGEMENT_FW)
            .formationArrangementOmf(UPDATED_FORMATION_ARRANGEMENT_OMF)
            .formationArrangementDmf(UPDATED_FORMATION_ARRANGEMENT_DMF)
            .formationArrangementDf(UPDATED_FORMATION_ARRANGEMENT_DF)
            .effectId(UPDATED_EFFECT_ID)
            .description(UPDATED_DESCRIPTION)
            .shortDescription(UPDATED_SHORT_DESCRIPTION)
            .name(UPDATED_NAME)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .startingUniformNos(UPDATED_STARTING_UNIFORM_NOS)
            .subUniformNos(UPDATED_SUB_UNIFORM_NOS)
            .exType(UPDATED_EX_TYPE)
            .matchFormationId(UPDATED_MATCH_FORMATION_ID);
        MFormationDTO mFormationDTO = mFormationMapper.toDto(updatedMFormation);

        restMFormationMockMvc.perform(put("/api/m-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFormationDTO)))
            .andExpect(status().isOk());

        // Validate the MFormation in the database
        List<MFormation> mFormationList = mFormationRepository.findAll();
        assertThat(mFormationList).hasSize(databaseSizeBeforeUpdate);
        MFormation testMFormation = mFormationList.get(mFormationList.size() - 1);
        assertThat(testMFormation.getEffectValue()).isEqualTo(UPDATED_EFFECT_VALUE);
        assertThat(testMFormation.getEffectProbability()).isEqualTo(UPDATED_EFFECT_PROBABILITY);
        assertThat(testMFormation.getFormationArrangementFw()).isEqualTo(UPDATED_FORMATION_ARRANGEMENT_FW);
        assertThat(testMFormation.getFormationArrangementOmf()).isEqualTo(UPDATED_FORMATION_ARRANGEMENT_OMF);
        assertThat(testMFormation.getFormationArrangementDmf()).isEqualTo(UPDATED_FORMATION_ARRANGEMENT_DMF);
        assertThat(testMFormation.getFormationArrangementDf()).isEqualTo(UPDATED_FORMATION_ARRANGEMENT_DF);
        assertThat(testMFormation.getEffectId()).isEqualTo(UPDATED_EFFECT_ID);
        assertThat(testMFormation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMFormation.getShortDescription()).isEqualTo(UPDATED_SHORT_DESCRIPTION);
        assertThat(testMFormation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMFormation.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
        assertThat(testMFormation.getStartingUniformNos()).isEqualTo(UPDATED_STARTING_UNIFORM_NOS);
        assertThat(testMFormation.getSubUniformNos()).isEqualTo(UPDATED_SUB_UNIFORM_NOS);
        assertThat(testMFormation.getExType()).isEqualTo(UPDATED_EX_TYPE);
        assertThat(testMFormation.getMatchFormationId()).isEqualTo(UPDATED_MATCH_FORMATION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMFormation() throws Exception {
        int databaseSizeBeforeUpdate = mFormationRepository.findAll().size();

        // Create the MFormation
        MFormationDTO mFormationDTO = mFormationMapper.toDto(mFormation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMFormationMockMvc.perform(put("/api/m-formations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFormationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MFormation in the database
        List<MFormation> mFormationList = mFormationRepository.findAll();
        assertThat(mFormationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMFormation() throws Exception {
        // Initialize the database
        mFormationRepository.saveAndFlush(mFormation);

        int databaseSizeBeforeDelete = mFormationRepository.findAll().size();

        // Delete the mFormation
        restMFormationMockMvc.perform(delete("/api/m-formations/{id}", mFormation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MFormation> mFormationList = mFormationRepository.findAll();
        assertThat(mFormationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MFormation.class);
        MFormation mFormation1 = new MFormation();
        mFormation1.setId(1L);
        MFormation mFormation2 = new MFormation();
        mFormation2.setId(mFormation1.getId());
        assertThat(mFormation1).isEqualTo(mFormation2);
        mFormation2.setId(2L);
        assertThat(mFormation1).isNotEqualTo(mFormation2);
        mFormation1.setId(null);
        assertThat(mFormation1).isNotEqualTo(mFormation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MFormationDTO.class);
        MFormationDTO mFormationDTO1 = new MFormationDTO();
        mFormationDTO1.setId(1L);
        MFormationDTO mFormationDTO2 = new MFormationDTO();
        assertThat(mFormationDTO1).isNotEqualTo(mFormationDTO2);
        mFormationDTO2.setId(mFormationDTO1.getId());
        assertThat(mFormationDTO1).isEqualTo(mFormationDTO2);
        mFormationDTO2.setId(2L);
        assertThat(mFormationDTO1).isNotEqualTo(mFormationDTO2);
        mFormationDTO1.setId(null);
        assertThat(mFormationDTO1).isNotEqualTo(mFormationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mFormationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mFormationMapper.fromId(null)).isNull();
    }
}
