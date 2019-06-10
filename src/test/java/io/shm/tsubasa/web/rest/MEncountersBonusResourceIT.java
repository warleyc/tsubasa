package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MEncountersBonus;
import io.shm.tsubasa.repository.MEncountersBonusRepository;
import io.shm.tsubasa.service.MEncountersBonusService;
import io.shm.tsubasa.service.dto.MEncountersBonusDTO;
import io.shm.tsubasa.service.mapper.MEncountersBonusMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MEncountersBonusCriteria;
import io.shm.tsubasa.service.MEncountersBonusQueryService;

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
 * Integration tests for the {@Link MEncountersBonusResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MEncountersBonusResourceIT {

    private static final Integer DEFAULT_ENCOUNTERS_TYPE = 1;
    private static final Integer UPDATED_ENCOUNTERS_TYPE = 2;

    private static final Integer DEFAULT_IS_SKILL_SUCCESS = 1;
    private static final Integer UPDATED_IS_SKILL_SUCCESS = 2;

    private static final Integer DEFAULT_THRESHOLD = 1;
    private static final Integer UPDATED_THRESHOLD = 2;

    private static final Integer DEFAULT_PROBABILITY_BONUS_VALUE = 1;
    private static final Integer UPDATED_PROBABILITY_BONUS_VALUE = 2;

    @Autowired
    private MEncountersBonusRepository mEncountersBonusRepository;

    @Autowired
    private MEncountersBonusMapper mEncountersBonusMapper;

    @Autowired
    private MEncountersBonusService mEncountersBonusService;

    @Autowired
    private MEncountersBonusQueryService mEncountersBonusQueryService;

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

    private MockMvc restMEncountersBonusMockMvc;

    private MEncountersBonus mEncountersBonus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MEncountersBonusResource mEncountersBonusResource = new MEncountersBonusResource(mEncountersBonusService, mEncountersBonusQueryService);
        this.restMEncountersBonusMockMvc = MockMvcBuilders.standaloneSetup(mEncountersBonusResource)
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
    public static MEncountersBonus createEntity(EntityManager em) {
        MEncountersBonus mEncountersBonus = new MEncountersBonus()
            .encountersType(DEFAULT_ENCOUNTERS_TYPE)
            .isSkillSuccess(DEFAULT_IS_SKILL_SUCCESS)
            .threshold(DEFAULT_THRESHOLD)
            .probabilityBonusValue(DEFAULT_PROBABILITY_BONUS_VALUE);
        return mEncountersBonus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MEncountersBonus createUpdatedEntity(EntityManager em) {
        MEncountersBonus mEncountersBonus = new MEncountersBonus()
            .encountersType(UPDATED_ENCOUNTERS_TYPE)
            .isSkillSuccess(UPDATED_IS_SKILL_SUCCESS)
            .threshold(UPDATED_THRESHOLD)
            .probabilityBonusValue(UPDATED_PROBABILITY_BONUS_VALUE);
        return mEncountersBonus;
    }

    @BeforeEach
    public void initTest() {
        mEncountersBonus = createEntity(em);
    }

    @Test
    @Transactional
    public void createMEncountersBonus() throws Exception {
        int databaseSizeBeforeCreate = mEncountersBonusRepository.findAll().size();

        // Create the MEncountersBonus
        MEncountersBonusDTO mEncountersBonusDTO = mEncountersBonusMapper.toDto(mEncountersBonus);
        restMEncountersBonusMockMvc.perform(post("/api/m-encounters-bonuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersBonusDTO)))
            .andExpect(status().isCreated());

        // Validate the MEncountersBonus in the database
        List<MEncountersBonus> mEncountersBonusList = mEncountersBonusRepository.findAll();
        assertThat(mEncountersBonusList).hasSize(databaseSizeBeforeCreate + 1);
        MEncountersBonus testMEncountersBonus = mEncountersBonusList.get(mEncountersBonusList.size() - 1);
        assertThat(testMEncountersBonus.getEncountersType()).isEqualTo(DEFAULT_ENCOUNTERS_TYPE);
        assertThat(testMEncountersBonus.getIsSkillSuccess()).isEqualTo(DEFAULT_IS_SKILL_SUCCESS);
        assertThat(testMEncountersBonus.getThreshold()).isEqualTo(DEFAULT_THRESHOLD);
        assertThat(testMEncountersBonus.getProbabilityBonusValue()).isEqualTo(DEFAULT_PROBABILITY_BONUS_VALUE);
    }

    @Test
    @Transactional
    public void createMEncountersBonusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mEncountersBonusRepository.findAll().size();

        // Create the MEncountersBonus with an existing ID
        mEncountersBonus.setId(1L);
        MEncountersBonusDTO mEncountersBonusDTO = mEncountersBonusMapper.toDto(mEncountersBonus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMEncountersBonusMockMvc.perform(post("/api/m-encounters-bonuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersBonusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEncountersBonus in the database
        List<MEncountersBonus> mEncountersBonusList = mEncountersBonusRepository.findAll();
        assertThat(mEncountersBonusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEncountersTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersBonusRepository.findAll().size();
        // set the field null
        mEncountersBonus.setEncountersType(null);

        // Create the MEncountersBonus, which fails.
        MEncountersBonusDTO mEncountersBonusDTO = mEncountersBonusMapper.toDto(mEncountersBonus);

        restMEncountersBonusMockMvc.perform(post("/api/m-encounters-bonuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersBonusDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersBonus> mEncountersBonusList = mEncountersBonusRepository.findAll();
        assertThat(mEncountersBonusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsSkillSuccessIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersBonusRepository.findAll().size();
        // set the field null
        mEncountersBonus.setIsSkillSuccess(null);

        // Create the MEncountersBonus, which fails.
        MEncountersBonusDTO mEncountersBonusDTO = mEncountersBonusMapper.toDto(mEncountersBonus);

        restMEncountersBonusMockMvc.perform(post("/api/m-encounters-bonuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersBonusDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersBonus> mEncountersBonusList = mEncountersBonusRepository.findAll();
        assertThat(mEncountersBonusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkThresholdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersBonusRepository.findAll().size();
        // set the field null
        mEncountersBonus.setThreshold(null);

        // Create the MEncountersBonus, which fails.
        MEncountersBonusDTO mEncountersBonusDTO = mEncountersBonusMapper.toDto(mEncountersBonus);

        restMEncountersBonusMockMvc.perform(post("/api/m-encounters-bonuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersBonusDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersBonus> mEncountersBonusList = mEncountersBonusRepository.findAll();
        assertThat(mEncountersBonusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProbabilityBonusValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEncountersBonusRepository.findAll().size();
        // set the field null
        mEncountersBonus.setProbabilityBonusValue(null);

        // Create the MEncountersBonus, which fails.
        MEncountersBonusDTO mEncountersBonusDTO = mEncountersBonusMapper.toDto(mEncountersBonus);

        restMEncountersBonusMockMvc.perform(post("/api/m-encounters-bonuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersBonusDTO)))
            .andExpect(status().isBadRequest());

        List<MEncountersBonus> mEncountersBonusList = mEncountersBonusRepository.findAll();
        assertThat(mEncountersBonusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonuses() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList
        restMEncountersBonusMockMvc.perform(get("/api/m-encounters-bonuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEncountersBonus.getId().intValue())))
            .andExpect(jsonPath("$.[*].encountersType").value(hasItem(DEFAULT_ENCOUNTERS_TYPE)))
            .andExpect(jsonPath("$.[*].isSkillSuccess").value(hasItem(DEFAULT_IS_SKILL_SUCCESS)))
            .andExpect(jsonPath("$.[*].threshold").value(hasItem(DEFAULT_THRESHOLD)))
            .andExpect(jsonPath("$.[*].probabilityBonusValue").value(hasItem(DEFAULT_PROBABILITY_BONUS_VALUE)));
    }
    
    @Test
    @Transactional
    public void getMEncountersBonus() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get the mEncountersBonus
        restMEncountersBonusMockMvc.perform(get("/api/m-encounters-bonuses/{id}", mEncountersBonus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mEncountersBonus.getId().intValue()))
            .andExpect(jsonPath("$.encountersType").value(DEFAULT_ENCOUNTERS_TYPE))
            .andExpect(jsonPath("$.isSkillSuccess").value(DEFAULT_IS_SKILL_SUCCESS))
            .andExpect(jsonPath("$.threshold").value(DEFAULT_THRESHOLD))
            .andExpect(jsonPath("$.probabilityBonusValue").value(DEFAULT_PROBABILITY_BONUS_VALUE));
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByEncountersTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where encountersType equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMEncountersBonusShouldBeFound("encountersType.equals=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mEncountersBonusList where encountersType equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersBonusShouldNotBeFound("encountersType.equals=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByEncountersTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where encountersType in DEFAULT_ENCOUNTERS_TYPE or UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersBonusShouldBeFound("encountersType.in=" + DEFAULT_ENCOUNTERS_TYPE + "," + UPDATED_ENCOUNTERS_TYPE);

        // Get all the mEncountersBonusList where encountersType equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersBonusShouldNotBeFound("encountersType.in=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByEncountersTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where encountersType is not null
        defaultMEncountersBonusShouldBeFound("encountersType.specified=true");

        // Get all the mEncountersBonusList where encountersType is null
        defaultMEncountersBonusShouldNotBeFound("encountersType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByEncountersTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where encountersType greater than or equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMEncountersBonusShouldBeFound("encountersType.greaterOrEqualThan=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mEncountersBonusList where encountersType greater than or equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersBonusShouldNotBeFound("encountersType.greaterOrEqualThan=" + UPDATED_ENCOUNTERS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByEncountersTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where encountersType less than or equals to DEFAULT_ENCOUNTERS_TYPE
        defaultMEncountersBonusShouldNotBeFound("encountersType.lessThan=" + DEFAULT_ENCOUNTERS_TYPE);

        // Get all the mEncountersBonusList where encountersType less than or equals to UPDATED_ENCOUNTERS_TYPE
        defaultMEncountersBonusShouldBeFound("encountersType.lessThan=" + UPDATED_ENCOUNTERS_TYPE);
    }


    @Test
    @Transactional
    public void getAllMEncountersBonusesByIsSkillSuccessIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where isSkillSuccess equals to DEFAULT_IS_SKILL_SUCCESS
        defaultMEncountersBonusShouldBeFound("isSkillSuccess.equals=" + DEFAULT_IS_SKILL_SUCCESS);

        // Get all the mEncountersBonusList where isSkillSuccess equals to UPDATED_IS_SKILL_SUCCESS
        defaultMEncountersBonusShouldNotBeFound("isSkillSuccess.equals=" + UPDATED_IS_SKILL_SUCCESS);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByIsSkillSuccessIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where isSkillSuccess in DEFAULT_IS_SKILL_SUCCESS or UPDATED_IS_SKILL_SUCCESS
        defaultMEncountersBonusShouldBeFound("isSkillSuccess.in=" + DEFAULT_IS_SKILL_SUCCESS + "," + UPDATED_IS_SKILL_SUCCESS);

        // Get all the mEncountersBonusList where isSkillSuccess equals to UPDATED_IS_SKILL_SUCCESS
        defaultMEncountersBonusShouldNotBeFound("isSkillSuccess.in=" + UPDATED_IS_SKILL_SUCCESS);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByIsSkillSuccessIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where isSkillSuccess is not null
        defaultMEncountersBonusShouldBeFound("isSkillSuccess.specified=true");

        // Get all the mEncountersBonusList where isSkillSuccess is null
        defaultMEncountersBonusShouldNotBeFound("isSkillSuccess.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByIsSkillSuccessIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where isSkillSuccess greater than or equals to DEFAULT_IS_SKILL_SUCCESS
        defaultMEncountersBonusShouldBeFound("isSkillSuccess.greaterOrEqualThan=" + DEFAULT_IS_SKILL_SUCCESS);

        // Get all the mEncountersBonusList where isSkillSuccess greater than or equals to UPDATED_IS_SKILL_SUCCESS
        defaultMEncountersBonusShouldNotBeFound("isSkillSuccess.greaterOrEqualThan=" + UPDATED_IS_SKILL_SUCCESS);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByIsSkillSuccessIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where isSkillSuccess less than or equals to DEFAULT_IS_SKILL_SUCCESS
        defaultMEncountersBonusShouldNotBeFound("isSkillSuccess.lessThan=" + DEFAULT_IS_SKILL_SUCCESS);

        // Get all the mEncountersBonusList where isSkillSuccess less than or equals to UPDATED_IS_SKILL_SUCCESS
        defaultMEncountersBonusShouldBeFound("isSkillSuccess.lessThan=" + UPDATED_IS_SKILL_SUCCESS);
    }


    @Test
    @Transactional
    public void getAllMEncountersBonusesByThresholdIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where threshold equals to DEFAULT_THRESHOLD
        defaultMEncountersBonusShouldBeFound("threshold.equals=" + DEFAULT_THRESHOLD);

        // Get all the mEncountersBonusList where threshold equals to UPDATED_THRESHOLD
        defaultMEncountersBonusShouldNotBeFound("threshold.equals=" + UPDATED_THRESHOLD);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByThresholdIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where threshold in DEFAULT_THRESHOLD or UPDATED_THRESHOLD
        defaultMEncountersBonusShouldBeFound("threshold.in=" + DEFAULT_THRESHOLD + "," + UPDATED_THRESHOLD);

        // Get all the mEncountersBonusList where threshold equals to UPDATED_THRESHOLD
        defaultMEncountersBonusShouldNotBeFound("threshold.in=" + UPDATED_THRESHOLD);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByThresholdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where threshold is not null
        defaultMEncountersBonusShouldBeFound("threshold.specified=true");

        // Get all the mEncountersBonusList where threshold is null
        defaultMEncountersBonusShouldNotBeFound("threshold.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByThresholdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where threshold greater than or equals to DEFAULT_THRESHOLD
        defaultMEncountersBonusShouldBeFound("threshold.greaterOrEqualThan=" + DEFAULT_THRESHOLD);

        // Get all the mEncountersBonusList where threshold greater than or equals to UPDATED_THRESHOLD
        defaultMEncountersBonusShouldNotBeFound("threshold.greaterOrEqualThan=" + UPDATED_THRESHOLD);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByThresholdIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where threshold less than or equals to DEFAULT_THRESHOLD
        defaultMEncountersBonusShouldNotBeFound("threshold.lessThan=" + DEFAULT_THRESHOLD);

        // Get all the mEncountersBonusList where threshold less than or equals to UPDATED_THRESHOLD
        defaultMEncountersBonusShouldBeFound("threshold.lessThan=" + UPDATED_THRESHOLD);
    }


    @Test
    @Transactional
    public void getAllMEncountersBonusesByProbabilityBonusValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where probabilityBonusValue equals to DEFAULT_PROBABILITY_BONUS_VALUE
        defaultMEncountersBonusShouldBeFound("probabilityBonusValue.equals=" + DEFAULT_PROBABILITY_BONUS_VALUE);

        // Get all the mEncountersBonusList where probabilityBonusValue equals to UPDATED_PROBABILITY_BONUS_VALUE
        defaultMEncountersBonusShouldNotBeFound("probabilityBonusValue.equals=" + UPDATED_PROBABILITY_BONUS_VALUE);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByProbabilityBonusValueIsInShouldWork() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where probabilityBonusValue in DEFAULT_PROBABILITY_BONUS_VALUE or UPDATED_PROBABILITY_BONUS_VALUE
        defaultMEncountersBonusShouldBeFound("probabilityBonusValue.in=" + DEFAULT_PROBABILITY_BONUS_VALUE + "," + UPDATED_PROBABILITY_BONUS_VALUE);

        // Get all the mEncountersBonusList where probabilityBonusValue equals to UPDATED_PROBABILITY_BONUS_VALUE
        defaultMEncountersBonusShouldNotBeFound("probabilityBonusValue.in=" + UPDATED_PROBABILITY_BONUS_VALUE);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByProbabilityBonusValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where probabilityBonusValue is not null
        defaultMEncountersBonusShouldBeFound("probabilityBonusValue.specified=true");

        // Get all the mEncountersBonusList where probabilityBonusValue is null
        defaultMEncountersBonusShouldNotBeFound("probabilityBonusValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByProbabilityBonusValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where probabilityBonusValue greater than or equals to DEFAULT_PROBABILITY_BONUS_VALUE
        defaultMEncountersBonusShouldBeFound("probabilityBonusValue.greaterOrEqualThan=" + DEFAULT_PROBABILITY_BONUS_VALUE);

        // Get all the mEncountersBonusList where probabilityBonusValue greater than or equals to UPDATED_PROBABILITY_BONUS_VALUE
        defaultMEncountersBonusShouldNotBeFound("probabilityBonusValue.greaterOrEqualThan=" + UPDATED_PROBABILITY_BONUS_VALUE);
    }

    @Test
    @Transactional
    public void getAllMEncountersBonusesByProbabilityBonusValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        // Get all the mEncountersBonusList where probabilityBonusValue less than or equals to DEFAULT_PROBABILITY_BONUS_VALUE
        defaultMEncountersBonusShouldNotBeFound("probabilityBonusValue.lessThan=" + DEFAULT_PROBABILITY_BONUS_VALUE);

        // Get all the mEncountersBonusList where probabilityBonusValue less than or equals to UPDATED_PROBABILITY_BONUS_VALUE
        defaultMEncountersBonusShouldBeFound("probabilityBonusValue.lessThan=" + UPDATED_PROBABILITY_BONUS_VALUE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMEncountersBonusShouldBeFound(String filter) throws Exception {
        restMEncountersBonusMockMvc.perform(get("/api/m-encounters-bonuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEncountersBonus.getId().intValue())))
            .andExpect(jsonPath("$.[*].encountersType").value(hasItem(DEFAULT_ENCOUNTERS_TYPE)))
            .andExpect(jsonPath("$.[*].isSkillSuccess").value(hasItem(DEFAULT_IS_SKILL_SUCCESS)))
            .andExpect(jsonPath("$.[*].threshold").value(hasItem(DEFAULT_THRESHOLD)))
            .andExpect(jsonPath("$.[*].probabilityBonusValue").value(hasItem(DEFAULT_PROBABILITY_BONUS_VALUE)));

        // Check, that the count call also returns 1
        restMEncountersBonusMockMvc.perform(get("/api/m-encounters-bonuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMEncountersBonusShouldNotBeFound(String filter) throws Exception {
        restMEncountersBonusMockMvc.perform(get("/api/m-encounters-bonuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMEncountersBonusMockMvc.perform(get("/api/m-encounters-bonuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMEncountersBonus() throws Exception {
        // Get the mEncountersBonus
        restMEncountersBonusMockMvc.perform(get("/api/m-encounters-bonuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMEncountersBonus() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        int databaseSizeBeforeUpdate = mEncountersBonusRepository.findAll().size();

        // Update the mEncountersBonus
        MEncountersBonus updatedMEncountersBonus = mEncountersBonusRepository.findById(mEncountersBonus.getId()).get();
        // Disconnect from session so that the updates on updatedMEncountersBonus are not directly saved in db
        em.detach(updatedMEncountersBonus);
        updatedMEncountersBonus
            .encountersType(UPDATED_ENCOUNTERS_TYPE)
            .isSkillSuccess(UPDATED_IS_SKILL_SUCCESS)
            .threshold(UPDATED_THRESHOLD)
            .probabilityBonusValue(UPDATED_PROBABILITY_BONUS_VALUE);
        MEncountersBonusDTO mEncountersBonusDTO = mEncountersBonusMapper.toDto(updatedMEncountersBonus);

        restMEncountersBonusMockMvc.perform(put("/api/m-encounters-bonuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersBonusDTO)))
            .andExpect(status().isOk());

        // Validate the MEncountersBonus in the database
        List<MEncountersBonus> mEncountersBonusList = mEncountersBonusRepository.findAll();
        assertThat(mEncountersBonusList).hasSize(databaseSizeBeforeUpdate);
        MEncountersBonus testMEncountersBonus = mEncountersBonusList.get(mEncountersBonusList.size() - 1);
        assertThat(testMEncountersBonus.getEncountersType()).isEqualTo(UPDATED_ENCOUNTERS_TYPE);
        assertThat(testMEncountersBonus.getIsSkillSuccess()).isEqualTo(UPDATED_IS_SKILL_SUCCESS);
        assertThat(testMEncountersBonus.getThreshold()).isEqualTo(UPDATED_THRESHOLD);
        assertThat(testMEncountersBonus.getProbabilityBonusValue()).isEqualTo(UPDATED_PROBABILITY_BONUS_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingMEncountersBonus() throws Exception {
        int databaseSizeBeforeUpdate = mEncountersBonusRepository.findAll().size();

        // Create the MEncountersBonus
        MEncountersBonusDTO mEncountersBonusDTO = mEncountersBonusMapper.toDto(mEncountersBonus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMEncountersBonusMockMvc.perform(put("/api/m-encounters-bonuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEncountersBonusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEncountersBonus in the database
        List<MEncountersBonus> mEncountersBonusList = mEncountersBonusRepository.findAll();
        assertThat(mEncountersBonusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMEncountersBonus() throws Exception {
        // Initialize the database
        mEncountersBonusRepository.saveAndFlush(mEncountersBonus);

        int databaseSizeBeforeDelete = mEncountersBonusRepository.findAll().size();

        // Delete the mEncountersBonus
        restMEncountersBonusMockMvc.perform(delete("/api/m-encounters-bonuses/{id}", mEncountersBonus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MEncountersBonus> mEncountersBonusList = mEncountersBonusRepository.findAll();
        assertThat(mEncountersBonusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEncountersBonus.class);
        MEncountersBonus mEncountersBonus1 = new MEncountersBonus();
        mEncountersBonus1.setId(1L);
        MEncountersBonus mEncountersBonus2 = new MEncountersBonus();
        mEncountersBonus2.setId(mEncountersBonus1.getId());
        assertThat(mEncountersBonus1).isEqualTo(mEncountersBonus2);
        mEncountersBonus2.setId(2L);
        assertThat(mEncountersBonus1).isNotEqualTo(mEncountersBonus2);
        mEncountersBonus1.setId(null);
        assertThat(mEncountersBonus1).isNotEqualTo(mEncountersBonus2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEncountersBonusDTO.class);
        MEncountersBonusDTO mEncountersBonusDTO1 = new MEncountersBonusDTO();
        mEncountersBonusDTO1.setId(1L);
        MEncountersBonusDTO mEncountersBonusDTO2 = new MEncountersBonusDTO();
        assertThat(mEncountersBonusDTO1).isNotEqualTo(mEncountersBonusDTO2);
        mEncountersBonusDTO2.setId(mEncountersBonusDTO1.getId());
        assertThat(mEncountersBonusDTO1).isEqualTo(mEncountersBonusDTO2);
        mEncountersBonusDTO2.setId(2L);
        assertThat(mEncountersBonusDTO1).isNotEqualTo(mEncountersBonusDTO2);
        mEncountersBonusDTO1.setId(null);
        assertThat(mEncountersBonusDTO1).isNotEqualTo(mEncountersBonusDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mEncountersBonusMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mEncountersBonusMapper.fromId(null)).isNull();
    }
}
