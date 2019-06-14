package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMatchOption;
import io.shm.tsubasa.domain.MPassiveEffectRange;
import io.shm.tsubasa.domain.MLeagueRegulation;
import io.shm.tsubasa.domain.MPvpRegulation;
import io.shm.tsubasa.repository.MMatchOptionRepository;
import io.shm.tsubasa.service.MMatchOptionService;
import io.shm.tsubasa.service.dto.MMatchOptionDTO;
import io.shm.tsubasa.service.mapper.MMatchOptionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMatchOptionCriteria;
import io.shm.tsubasa.service.MMatchOptionQueryService;

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
 * Integration tests for the {@Link MMatchOptionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMatchOptionResourceIT {

    private static final Integer DEFAULT_PASSIVE_EFFECT_ID = 1;
    private static final Integer UPDATED_PASSIVE_EFFECT_ID = 2;

    private static final Integer DEFAULT_PASSIVE_EFFECT_VALUE = 1;
    private static final Integer UPDATED_PASSIVE_EFFECT_VALUE = 2;

    private static final Integer DEFAULT_ACTIVATE_FULL_POWER_TYPE = 1;
    private static final Integer UPDATED_ACTIVATE_FULL_POWER_TYPE = 2;

    private static final Integer DEFAULT_ATTRIBUTE_MAGNIFICATION = 1;
    private static final Integer UPDATED_ATTRIBUTE_MAGNIFICATION = 2;

    private static final Integer DEFAULT_USE_STAMINA_MAGNIFICATION = 1;
    private static final Integer UPDATED_USE_STAMINA_MAGNIFICATION = 2;

    private static final Integer DEFAULT_IS_IGNORE_TEAM_SKILL = 1;
    private static final Integer UPDATED_IS_IGNORE_TEAM_SKILL = 2;

    private static final Integer DEFAULT_STAMINA_INFINITY_TYPE = 1;
    private static final Integer UPDATED_STAMINA_INFINITY_TYPE = 2;

    @Autowired
    private MMatchOptionRepository mMatchOptionRepository;

    @Autowired
    private MMatchOptionMapper mMatchOptionMapper;

    @Autowired
    private MMatchOptionService mMatchOptionService;

    @Autowired
    private MMatchOptionQueryService mMatchOptionQueryService;

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

    private MockMvc restMMatchOptionMockMvc;

    private MMatchOption mMatchOption;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMatchOptionResource mMatchOptionResource = new MMatchOptionResource(mMatchOptionService, mMatchOptionQueryService);
        this.restMMatchOptionMockMvc = MockMvcBuilders.standaloneSetup(mMatchOptionResource)
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
    public static MMatchOption createEntity(EntityManager em) {
        MMatchOption mMatchOption = new MMatchOption()
            .passiveEffectId(DEFAULT_PASSIVE_EFFECT_ID)
            .passiveEffectValue(DEFAULT_PASSIVE_EFFECT_VALUE)
            .activateFullPowerType(DEFAULT_ACTIVATE_FULL_POWER_TYPE)
            .attributeMagnification(DEFAULT_ATTRIBUTE_MAGNIFICATION)
            .useStaminaMagnification(DEFAULT_USE_STAMINA_MAGNIFICATION)
            .isIgnoreTeamSkill(DEFAULT_IS_IGNORE_TEAM_SKILL)
            .staminaInfinityType(DEFAULT_STAMINA_INFINITY_TYPE);
        // Add required entity
        MPassiveEffectRange mPassiveEffectRange;
        if (TestUtil.findAll(em, MPassiveEffectRange.class).isEmpty()) {
            mPassiveEffectRange = MPassiveEffectRangeResourceIT.createEntity(em);
            em.persist(mPassiveEffectRange);
            em.flush();
        } else {
            mPassiveEffectRange = TestUtil.findAll(em, MPassiveEffectRange.class).get(0);
        }
        mMatchOption.setMpassiveeffectrange(mPassiveEffectRange);
        return mMatchOption;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMatchOption createUpdatedEntity(EntityManager em) {
        MMatchOption mMatchOption = new MMatchOption()
            .passiveEffectId(UPDATED_PASSIVE_EFFECT_ID)
            .passiveEffectValue(UPDATED_PASSIVE_EFFECT_VALUE)
            .activateFullPowerType(UPDATED_ACTIVATE_FULL_POWER_TYPE)
            .attributeMagnification(UPDATED_ATTRIBUTE_MAGNIFICATION)
            .useStaminaMagnification(UPDATED_USE_STAMINA_MAGNIFICATION)
            .isIgnoreTeamSkill(UPDATED_IS_IGNORE_TEAM_SKILL)
            .staminaInfinityType(UPDATED_STAMINA_INFINITY_TYPE);
        // Add required entity
        MPassiveEffectRange mPassiveEffectRange;
        if (TestUtil.findAll(em, MPassiveEffectRange.class).isEmpty()) {
            mPassiveEffectRange = MPassiveEffectRangeResourceIT.createUpdatedEntity(em);
            em.persist(mPassiveEffectRange);
            em.flush();
        } else {
            mPassiveEffectRange = TestUtil.findAll(em, MPassiveEffectRange.class).get(0);
        }
        mMatchOption.setMpassiveeffectrange(mPassiveEffectRange);
        return mMatchOption;
    }

    @BeforeEach
    public void initTest() {
        mMatchOption = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMatchOption() throws Exception {
        int databaseSizeBeforeCreate = mMatchOptionRepository.findAll().size();

        // Create the MMatchOption
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(mMatchOption);
        restMMatchOptionMockMvc.perform(post("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isCreated());

        // Validate the MMatchOption in the database
        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeCreate + 1);
        MMatchOption testMMatchOption = mMatchOptionList.get(mMatchOptionList.size() - 1);
        assertThat(testMMatchOption.getPassiveEffectId()).isEqualTo(DEFAULT_PASSIVE_EFFECT_ID);
        assertThat(testMMatchOption.getPassiveEffectValue()).isEqualTo(DEFAULT_PASSIVE_EFFECT_VALUE);
        assertThat(testMMatchOption.getActivateFullPowerType()).isEqualTo(DEFAULT_ACTIVATE_FULL_POWER_TYPE);
        assertThat(testMMatchOption.getAttributeMagnification()).isEqualTo(DEFAULT_ATTRIBUTE_MAGNIFICATION);
        assertThat(testMMatchOption.getUseStaminaMagnification()).isEqualTo(DEFAULT_USE_STAMINA_MAGNIFICATION);
        assertThat(testMMatchOption.getIsIgnoreTeamSkill()).isEqualTo(DEFAULT_IS_IGNORE_TEAM_SKILL);
        assertThat(testMMatchOption.getStaminaInfinityType()).isEqualTo(DEFAULT_STAMINA_INFINITY_TYPE);
    }

    @Test
    @Transactional
    public void createMMatchOptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMatchOptionRepository.findAll().size();

        // Create the MMatchOption with an existing ID
        mMatchOption.setId(1L);
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(mMatchOption);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMatchOptionMockMvc.perform(post("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMatchOption in the database
        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPassiveEffectValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchOptionRepository.findAll().size();
        // set the field null
        mMatchOption.setPassiveEffectValue(null);

        // Create the MMatchOption, which fails.
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(mMatchOption);

        restMMatchOptionMockMvc.perform(post("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActivateFullPowerTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchOptionRepository.findAll().size();
        // set the field null
        mMatchOption.setActivateFullPowerType(null);

        // Create the MMatchOption, which fails.
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(mMatchOption);

        restMMatchOptionMockMvc.perform(post("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAttributeMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchOptionRepository.findAll().size();
        // set the field null
        mMatchOption.setAttributeMagnification(null);

        // Create the MMatchOption, which fails.
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(mMatchOption);

        restMMatchOptionMockMvc.perform(post("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUseStaminaMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchOptionRepository.findAll().size();
        // set the field null
        mMatchOption.setUseStaminaMagnification(null);

        // Create the MMatchOption, which fails.
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(mMatchOption);

        restMMatchOptionMockMvc.perform(post("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsIgnoreTeamSkillIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchOptionRepository.findAll().size();
        // set the field null
        mMatchOption.setIsIgnoreTeamSkill(null);

        // Create the MMatchOption, which fails.
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(mMatchOption);

        restMMatchOptionMockMvc.perform(post("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStaminaInfinityTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchOptionRepository.findAll().size();
        // set the field null
        mMatchOption.setStaminaInfinityType(null);

        // Create the MMatchOption, which fails.
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(mMatchOption);

        restMMatchOptionMockMvc.perform(post("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMatchOptions() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList
        restMMatchOptionMockMvc.perform(get("/api/m-match-options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMatchOption.getId().intValue())))
            .andExpect(jsonPath("$.[*].passiveEffectId").value(hasItem(DEFAULT_PASSIVE_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].passiveEffectValue").value(hasItem(DEFAULT_PASSIVE_EFFECT_VALUE)))
            .andExpect(jsonPath("$.[*].activateFullPowerType").value(hasItem(DEFAULT_ACTIVATE_FULL_POWER_TYPE)))
            .andExpect(jsonPath("$.[*].attributeMagnification").value(hasItem(DEFAULT_ATTRIBUTE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].useStaminaMagnification").value(hasItem(DEFAULT_USE_STAMINA_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].isIgnoreTeamSkill").value(hasItem(DEFAULT_IS_IGNORE_TEAM_SKILL)))
            .andExpect(jsonPath("$.[*].staminaInfinityType").value(hasItem(DEFAULT_STAMINA_INFINITY_TYPE)));
    }
    
    @Test
    @Transactional
    public void getMMatchOption() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get the mMatchOption
        restMMatchOptionMockMvc.perform(get("/api/m-match-options/{id}", mMatchOption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMatchOption.getId().intValue()))
            .andExpect(jsonPath("$.passiveEffectId").value(DEFAULT_PASSIVE_EFFECT_ID))
            .andExpect(jsonPath("$.passiveEffectValue").value(DEFAULT_PASSIVE_EFFECT_VALUE))
            .andExpect(jsonPath("$.activateFullPowerType").value(DEFAULT_ACTIVATE_FULL_POWER_TYPE))
            .andExpect(jsonPath("$.attributeMagnification").value(DEFAULT_ATTRIBUTE_MAGNIFICATION))
            .andExpect(jsonPath("$.useStaminaMagnification").value(DEFAULT_USE_STAMINA_MAGNIFICATION))
            .andExpect(jsonPath("$.isIgnoreTeamSkill").value(DEFAULT_IS_IGNORE_TEAM_SKILL))
            .andExpect(jsonPath("$.staminaInfinityType").value(DEFAULT_STAMINA_INFINITY_TYPE));
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectId equals to DEFAULT_PASSIVE_EFFECT_ID
        defaultMMatchOptionShouldBeFound("passiveEffectId.equals=" + DEFAULT_PASSIVE_EFFECT_ID);

        // Get all the mMatchOptionList where passiveEffectId equals to UPDATED_PASSIVE_EFFECT_ID
        defaultMMatchOptionShouldNotBeFound("passiveEffectId.equals=" + UPDATED_PASSIVE_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectId in DEFAULT_PASSIVE_EFFECT_ID or UPDATED_PASSIVE_EFFECT_ID
        defaultMMatchOptionShouldBeFound("passiveEffectId.in=" + DEFAULT_PASSIVE_EFFECT_ID + "," + UPDATED_PASSIVE_EFFECT_ID);

        // Get all the mMatchOptionList where passiveEffectId equals to UPDATED_PASSIVE_EFFECT_ID
        defaultMMatchOptionShouldNotBeFound("passiveEffectId.in=" + UPDATED_PASSIVE_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectId is not null
        defaultMMatchOptionShouldBeFound("passiveEffectId.specified=true");

        // Get all the mMatchOptionList where passiveEffectId is null
        defaultMMatchOptionShouldNotBeFound("passiveEffectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectId greater than or equals to DEFAULT_PASSIVE_EFFECT_ID
        defaultMMatchOptionShouldBeFound("passiveEffectId.greaterOrEqualThan=" + DEFAULT_PASSIVE_EFFECT_ID);

        // Get all the mMatchOptionList where passiveEffectId greater than or equals to UPDATED_PASSIVE_EFFECT_ID
        defaultMMatchOptionShouldNotBeFound("passiveEffectId.greaterOrEqualThan=" + UPDATED_PASSIVE_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectId less than or equals to DEFAULT_PASSIVE_EFFECT_ID
        defaultMMatchOptionShouldNotBeFound("passiveEffectId.lessThan=" + DEFAULT_PASSIVE_EFFECT_ID);

        // Get all the mMatchOptionList where passiveEffectId less than or equals to UPDATED_PASSIVE_EFFECT_ID
        defaultMMatchOptionShouldBeFound("passiveEffectId.lessThan=" + UPDATED_PASSIVE_EFFECT_ID);
    }


    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectValue equals to DEFAULT_PASSIVE_EFFECT_VALUE
        defaultMMatchOptionShouldBeFound("passiveEffectValue.equals=" + DEFAULT_PASSIVE_EFFECT_VALUE);

        // Get all the mMatchOptionList where passiveEffectValue equals to UPDATED_PASSIVE_EFFECT_VALUE
        defaultMMatchOptionShouldNotBeFound("passiveEffectValue.equals=" + UPDATED_PASSIVE_EFFECT_VALUE);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectValueIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectValue in DEFAULT_PASSIVE_EFFECT_VALUE or UPDATED_PASSIVE_EFFECT_VALUE
        defaultMMatchOptionShouldBeFound("passiveEffectValue.in=" + DEFAULT_PASSIVE_EFFECT_VALUE + "," + UPDATED_PASSIVE_EFFECT_VALUE);

        // Get all the mMatchOptionList where passiveEffectValue equals to UPDATED_PASSIVE_EFFECT_VALUE
        defaultMMatchOptionShouldNotBeFound("passiveEffectValue.in=" + UPDATED_PASSIVE_EFFECT_VALUE);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectValue is not null
        defaultMMatchOptionShouldBeFound("passiveEffectValue.specified=true");

        // Get all the mMatchOptionList where passiveEffectValue is null
        defaultMMatchOptionShouldNotBeFound("passiveEffectValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectValue greater than or equals to DEFAULT_PASSIVE_EFFECT_VALUE
        defaultMMatchOptionShouldBeFound("passiveEffectValue.greaterOrEqualThan=" + DEFAULT_PASSIVE_EFFECT_VALUE);

        // Get all the mMatchOptionList where passiveEffectValue greater than or equals to UPDATED_PASSIVE_EFFECT_VALUE
        defaultMMatchOptionShouldNotBeFound("passiveEffectValue.greaterOrEqualThan=" + UPDATED_PASSIVE_EFFECT_VALUE);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByPassiveEffectValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where passiveEffectValue less than or equals to DEFAULT_PASSIVE_EFFECT_VALUE
        defaultMMatchOptionShouldNotBeFound("passiveEffectValue.lessThan=" + DEFAULT_PASSIVE_EFFECT_VALUE);

        // Get all the mMatchOptionList where passiveEffectValue less than or equals to UPDATED_PASSIVE_EFFECT_VALUE
        defaultMMatchOptionShouldBeFound("passiveEffectValue.lessThan=" + UPDATED_PASSIVE_EFFECT_VALUE);
    }


    @Test
    @Transactional
    public void getAllMMatchOptionsByActivateFullPowerTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where activateFullPowerType equals to DEFAULT_ACTIVATE_FULL_POWER_TYPE
        defaultMMatchOptionShouldBeFound("activateFullPowerType.equals=" + DEFAULT_ACTIVATE_FULL_POWER_TYPE);

        // Get all the mMatchOptionList where activateFullPowerType equals to UPDATED_ACTIVATE_FULL_POWER_TYPE
        defaultMMatchOptionShouldNotBeFound("activateFullPowerType.equals=" + UPDATED_ACTIVATE_FULL_POWER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByActivateFullPowerTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where activateFullPowerType in DEFAULT_ACTIVATE_FULL_POWER_TYPE or UPDATED_ACTIVATE_FULL_POWER_TYPE
        defaultMMatchOptionShouldBeFound("activateFullPowerType.in=" + DEFAULT_ACTIVATE_FULL_POWER_TYPE + "," + UPDATED_ACTIVATE_FULL_POWER_TYPE);

        // Get all the mMatchOptionList where activateFullPowerType equals to UPDATED_ACTIVATE_FULL_POWER_TYPE
        defaultMMatchOptionShouldNotBeFound("activateFullPowerType.in=" + UPDATED_ACTIVATE_FULL_POWER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByActivateFullPowerTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where activateFullPowerType is not null
        defaultMMatchOptionShouldBeFound("activateFullPowerType.specified=true");

        // Get all the mMatchOptionList where activateFullPowerType is null
        defaultMMatchOptionShouldNotBeFound("activateFullPowerType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByActivateFullPowerTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where activateFullPowerType greater than or equals to DEFAULT_ACTIVATE_FULL_POWER_TYPE
        defaultMMatchOptionShouldBeFound("activateFullPowerType.greaterOrEqualThan=" + DEFAULT_ACTIVATE_FULL_POWER_TYPE);

        // Get all the mMatchOptionList where activateFullPowerType greater than or equals to UPDATED_ACTIVATE_FULL_POWER_TYPE
        defaultMMatchOptionShouldNotBeFound("activateFullPowerType.greaterOrEqualThan=" + UPDATED_ACTIVATE_FULL_POWER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByActivateFullPowerTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where activateFullPowerType less than or equals to DEFAULT_ACTIVATE_FULL_POWER_TYPE
        defaultMMatchOptionShouldNotBeFound("activateFullPowerType.lessThan=" + DEFAULT_ACTIVATE_FULL_POWER_TYPE);

        // Get all the mMatchOptionList where activateFullPowerType less than or equals to UPDATED_ACTIVATE_FULL_POWER_TYPE
        defaultMMatchOptionShouldBeFound("activateFullPowerType.lessThan=" + UPDATED_ACTIVATE_FULL_POWER_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMatchOptionsByAttributeMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where attributeMagnification equals to DEFAULT_ATTRIBUTE_MAGNIFICATION
        defaultMMatchOptionShouldBeFound("attributeMagnification.equals=" + DEFAULT_ATTRIBUTE_MAGNIFICATION);

        // Get all the mMatchOptionList where attributeMagnification equals to UPDATED_ATTRIBUTE_MAGNIFICATION
        defaultMMatchOptionShouldNotBeFound("attributeMagnification.equals=" + UPDATED_ATTRIBUTE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByAttributeMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where attributeMagnification in DEFAULT_ATTRIBUTE_MAGNIFICATION or UPDATED_ATTRIBUTE_MAGNIFICATION
        defaultMMatchOptionShouldBeFound("attributeMagnification.in=" + DEFAULT_ATTRIBUTE_MAGNIFICATION + "," + UPDATED_ATTRIBUTE_MAGNIFICATION);

        // Get all the mMatchOptionList where attributeMagnification equals to UPDATED_ATTRIBUTE_MAGNIFICATION
        defaultMMatchOptionShouldNotBeFound("attributeMagnification.in=" + UPDATED_ATTRIBUTE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByAttributeMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where attributeMagnification is not null
        defaultMMatchOptionShouldBeFound("attributeMagnification.specified=true");

        // Get all the mMatchOptionList where attributeMagnification is null
        defaultMMatchOptionShouldNotBeFound("attributeMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByAttributeMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where attributeMagnification greater than or equals to DEFAULT_ATTRIBUTE_MAGNIFICATION
        defaultMMatchOptionShouldBeFound("attributeMagnification.greaterOrEqualThan=" + DEFAULT_ATTRIBUTE_MAGNIFICATION);

        // Get all the mMatchOptionList where attributeMagnification greater than or equals to UPDATED_ATTRIBUTE_MAGNIFICATION
        defaultMMatchOptionShouldNotBeFound("attributeMagnification.greaterOrEqualThan=" + UPDATED_ATTRIBUTE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByAttributeMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where attributeMagnification less than or equals to DEFAULT_ATTRIBUTE_MAGNIFICATION
        defaultMMatchOptionShouldNotBeFound("attributeMagnification.lessThan=" + DEFAULT_ATTRIBUTE_MAGNIFICATION);

        // Get all the mMatchOptionList where attributeMagnification less than or equals to UPDATED_ATTRIBUTE_MAGNIFICATION
        defaultMMatchOptionShouldBeFound("attributeMagnification.lessThan=" + UPDATED_ATTRIBUTE_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMMatchOptionsByUseStaminaMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where useStaminaMagnification equals to DEFAULT_USE_STAMINA_MAGNIFICATION
        defaultMMatchOptionShouldBeFound("useStaminaMagnification.equals=" + DEFAULT_USE_STAMINA_MAGNIFICATION);

        // Get all the mMatchOptionList where useStaminaMagnification equals to UPDATED_USE_STAMINA_MAGNIFICATION
        defaultMMatchOptionShouldNotBeFound("useStaminaMagnification.equals=" + UPDATED_USE_STAMINA_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByUseStaminaMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where useStaminaMagnification in DEFAULT_USE_STAMINA_MAGNIFICATION or UPDATED_USE_STAMINA_MAGNIFICATION
        defaultMMatchOptionShouldBeFound("useStaminaMagnification.in=" + DEFAULT_USE_STAMINA_MAGNIFICATION + "," + UPDATED_USE_STAMINA_MAGNIFICATION);

        // Get all the mMatchOptionList where useStaminaMagnification equals to UPDATED_USE_STAMINA_MAGNIFICATION
        defaultMMatchOptionShouldNotBeFound("useStaminaMagnification.in=" + UPDATED_USE_STAMINA_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByUseStaminaMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where useStaminaMagnification is not null
        defaultMMatchOptionShouldBeFound("useStaminaMagnification.specified=true");

        // Get all the mMatchOptionList where useStaminaMagnification is null
        defaultMMatchOptionShouldNotBeFound("useStaminaMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByUseStaminaMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where useStaminaMagnification greater than or equals to DEFAULT_USE_STAMINA_MAGNIFICATION
        defaultMMatchOptionShouldBeFound("useStaminaMagnification.greaterOrEqualThan=" + DEFAULT_USE_STAMINA_MAGNIFICATION);

        // Get all the mMatchOptionList where useStaminaMagnification greater than or equals to UPDATED_USE_STAMINA_MAGNIFICATION
        defaultMMatchOptionShouldNotBeFound("useStaminaMagnification.greaterOrEqualThan=" + UPDATED_USE_STAMINA_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByUseStaminaMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where useStaminaMagnification less than or equals to DEFAULT_USE_STAMINA_MAGNIFICATION
        defaultMMatchOptionShouldNotBeFound("useStaminaMagnification.lessThan=" + DEFAULT_USE_STAMINA_MAGNIFICATION);

        // Get all the mMatchOptionList where useStaminaMagnification less than or equals to UPDATED_USE_STAMINA_MAGNIFICATION
        defaultMMatchOptionShouldBeFound("useStaminaMagnification.lessThan=" + UPDATED_USE_STAMINA_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMMatchOptionsByIsIgnoreTeamSkillIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where isIgnoreTeamSkill equals to DEFAULT_IS_IGNORE_TEAM_SKILL
        defaultMMatchOptionShouldBeFound("isIgnoreTeamSkill.equals=" + DEFAULT_IS_IGNORE_TEAM_SKILL);

        // Get all the mMatchOptionList where isIgnoreTeamSkill equals to UPDATED_IS_IGNORE_TEAM_SKILL
        defaultMMatchOptionShouldNotBeFound("isIgnoreTeamSkill.equals=" + UPDATED_IS_IGNORE_TEAM_SKILL);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByIsIgnoreTeamSkillIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where isIgnoreTeamSkill in DEFAULT_IS_IGNORE_TEAM_SKILL or UPDATED_IS_IGNORE_TEAM_SKILL
        defaultMMatchOptionShouldBeFound("isIgnoreTeamSkill.in=" + DEFAULT_IS_IGNORE_TEAM_SKILL + "," + UPDATED_IS_IGNORE_TEAM_SKILL);

        // Get all the mMatchOptionList where isIgnoreTeamSkill equals to UPDATED_IS_IGNORE_TEAM_SKILL
        defaultMMatchOptionShouldNotBeFound("isIgnoreTeamSkill.in=" + UPDATED_IS_IGNORE_TEAM_SKILL);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByIsIgnoreTeamSkillIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where isIgnoreTeamSkill is not null
        defaultMMatchOptionShouldBeFound("isIgnoreTeamSkill.specified=true");

        // Get all the mMatchOptionList where isIgnoreTeamSkill is null
        defaultMMatchOptionShouldNotBeFound("isIgnoreTeamSkill.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByIsIgnoreTeamSkillIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where isIgnoreTeamSkill greater than or equals to DEFAULT_IS_IGNORE_TEAM_SKILL
        defaultMMatchOptionShouldBeFound("isIgnoreTeamSkill.greaterOrEqualThan=" + DEFAULT_IS_IGNORE_TEAM_SKILL);

        // Get all the mMatchOptionList where isIgnoreTeamSkill greater than or equals to UPDATED_IS_IGNORE_TEAM_SKILL
        defaultMMatchOptionShouldNotBeFound("isIgnoreTeamSkill.greaterOrEqualThan=" + UPDATED_IS_IGNORE_TEAM_SKILL);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByIsIgnoreTeamSkillIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where isIgnoreTeamSkill less than or equals to DEFAULT_IS_IGNORE_TEAM_SKILL
        defaultMMatchOptionShouldNotBeFound("isIgnoreTeamSkill.lessThan=" + DEFAULT_IS_IGNORE_TEAM_SKILL);

        // Get all the mMatchOptionList where isIgnoreTeamSkill less than or equals to UPDATED_IS_IGNORE_TEAM_SKILL
        defaultMMatchOptionShouldBeFound("isIgnoreTeamSkill.lessThan=" + UPDATED_IS_IGNORE_TEAM_SKILL);
    }


    @Test
    @Transactional
    public void getAllMMatchOptionsByStaminaInfinityTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where staminaInfinityType equals to DEFAULT_STAMINA_INFINITY_TYPE
        defaultMMatchOptionShouldBeFound("staminaInfinityType.equals=" + DEFAULT_STAMINA_INFINITY_TYPE);

        // Get all the mMatchOptionList where staminaInfinityType equals to UPDATED_STAMINA_INFINITY_TYPE
        defaultMMatchOptionShouldNotBeFound("staminaInfinityType.equals=" + UPDATED_STAMINA_INFINITY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByStaminaInfinityTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where staminaInfinityType in DEFAULT_STAMINA_INFINITY_TYPE or UPDATED_STAMINA_INFINITY_TYPE
        defaultMMatchOptionShouldBeFound("staminaInfinityType.in=" + DEFAULT_STAMINA_INFINITY_TYPE + "," + UPDATED_STAMINA_INFINITY_TYPE);

        // Get all the mMatchOptionList where staminaInfinityType equals to UPDATED_STAMINA_INFINITY_TYPE
        defaultMMatchOptionShouldNotBeFound("staminaInfinityType.in=" + UPDATED_STAMINA_INFINITY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByStaminaInfinityTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where staminaInfinityType is not null
        defaultMMatchOptionShouldBeFound("staminaInfinityType.specified=true");

        // Get all the mMatchOptionList where staminaInfinityType is null
        defaultMMatchOptionShouldNotBeFound("staminaInfinityType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByStaminaInfinityTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where staminaInfinityType greater than or equals to DEFAULT_STAMINA_INFINITY_TYPE
        defaultMMatchOptionShouldBeFound("staminaInfinityType.greaterOrEqualThan=" + DEFAULT_STAMINA_INFINITY_TYPE);

        // Get all the mMatchOptionList where staminaInfinityType greater than or equals to UPDATED_STAMINA_INFINITY_TYPE
        defaultMMatchOptionShouldNotBeFound("staminaInfinityType.greaterOrEqualThan=" + UPDATED_STAMINA_INFINITY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMatchOptionsByStaminaInfinityTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        // Get all the mMatchOptionList where staminaInfinityType less than or equals to DEFAULT_STAMINA_INFINITY_TYPE
        defaultMMatchOptionShouldNotBeFound("staminaInfinityType.lessThan=" + DEFAULT_STAMINA_INFINITY_TYPE);

        // Get all the mMatchOptionList where staminaInfinityType less than or equals to UPDATED_STAMINA_INFINITY_TYPE
        defaultMMatchOptionShouldBeFound("staminaInfinityType.lessThan=" + UPDATED_STAMINA_INFINITY_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMatchOptionsByMpassiveeffectrangeIsEqualToSomething() throws Exception {
        // Get already existing entity
        MPassiveEffectRange mpassiveeffectrange = mMatchOption.getMpassiveeffectrange();
        mMatchOptionRepository.saveAndFlush(mMatchOption);
        Long mpassiveeffectrangeId = mpassiveeffectrange.getId();

        // Get all the mMatchOptionList where mpassiveeffectrange equals to mpassiveeffectrangeId
        defaultMMatchOptionShouldBeFound("mpassiveeffectrangeId.equals=" + mpassiveeffectrangeId);

        // Get all the mMatchOptionList where mpassiveeffectrange equals to mpassiveeffectrangeId + 1
        defaultMMatchOptionShouldNotBeFound("mpassiveeffectrangeId.equals=" + (mpassiveeffectrangeId + 1));
    }


    @Test
    @Transactional
    public void getAllMMatchOptionsByMLeagueRegulationIsEqualToSomething() throws Exception {
        // Initialize the database
        MLeagueRegulation mLeagueRegulation = MLeagueRegulationResourceIT.createEntity(em);
        em.persist(mLeagueRegulation);
        em.flush();
        mMatchOption.addMLeagueRegulation(mLeagueRegulation);
        mMatchOptionRepository.saveAndFlush(mMatchOption);
        Long mLeagueRegulationId = mLeagueRegulation.getId();

        // Get all the mMatchOptionList where mLeagueRegulation equals to mLeagueRegulationId
        defaultMMatchOptionShouldBeFound("mLeagueRegulationId.equals=" + mLeagueRegulationId);

        // Get all the mMatchOptionList where mLeagueRegulation equals to mLeagueRegulationId + 1
        defaultMMatchOptionShouldNotBeFound("mLeagueRegulationId.equals=" + (mLeagueRegulationId + 1));
    }


    @Test
    @Transactional
    public void getAllMMatchOptionsByMPvpRegulationIsEqualToSomething() throws Exception {
        // Initialize the database
        MPvpRegulation mPvpRegulation = MPvpRegulationResourceIT.createEntity(em);
        em.persist(mPvpRegulation);
        em.flush();
        mMatchOption.addMPvpRegulation(mPvpRegulation);
        mMatchOptionRepository.saveAndFlush(mMatchOption);
        Long mPvpRegulationId = mPvpRegulation.getId();

        // Get all the mMatchOptionList where mPvpRegulation equals to mPvpRegulationId
        defaultMMatchOptionShouldBeFound("mPvpRegulationId.equals=" + mPvpRegulationId);

        // Get all the mMatchOptionList where mPvpRegulation equals to mPvpRegulationId + 1
        defaultMMatchOptionShouldNotBeFound("mPvpRegulationId.equals=" + (mPvpRegulationId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMatchOptionShouldBeFound(String filter) throws Exception {
        restMMatchOptionMockMvc.perform(get("/api/m-match-options?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMatchOption.getId().intValue())))
            .andExpect(jsonPath("$.[*].passiveEffectId").value(hasItem(DEFAULT_PASSIVE_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].passiveEffectValue").value(hasItem(DEFAULT_PASSIVE_EFFECT_VALUE)))
            .andExpect(jsonPath("$.[*].activateFullPowerType").value(hasItem(DEFAULT_ACTIVATE_FULL_POWER_TYPE)))
            .andExpect(jsonPath("$.[*].attributeMagnification").value(hasItem(DEFAULT_ATTRIBUTE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].useStaminaMagnification").value(hasItem(DEFAULT_USE_STAMINA_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].isIgnoreTeamSkill").value(hasItem(DEFAULT_IS_IGNORE_TEAM_SKILL)))
            .andExpect(jsonPath("$.[*].staminaInfinityType").value(hasItem(DEFAULT_STAMINA_INFINITY_TYPE)));

        // Check, that the count call also returns 1
        restMMatchOptionMockMvc.perform(get("/api/m-match-options/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMatchOptionShouldNotBeFound(String filter) throws Exception {
        restMMatchOptionMockMvc.perform(get("/api/m-match-options?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMatchOptionMockMvc.perform(get("/api/m-match-options/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMatchOption() throws Exception {
        // Get the mMatchOption
        restMMatchOptionMockMvc.perform(get("/api/m-match-options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMatchOption() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        int databaseSizeBeforeUpdate = mMatchOptionRepository.findAll().size();

        // Update the mMatchOption
        MMatchOption updatedMMatchOption = mMatchOptionRepository.findById(mMatchOption.getId()).get();
        // Disconnect from session so that the updates on updatedMMatchOption are not directly saved in db
        em.detach(updatedMMatchOption);
        updatedMMatchOption
            .passiveEffectId(UPDATED_PASSIVE_EFFECT_ID)
            .passiveEffectValue(UPDATED_PASSIVE_EFFECT_VALUE)
            .activateFullPowerType(UPDATED_ACTIVATE_FULL_POWER_TYPE)
            .attributeMagnification(UPDATED_ATTRIBUTE_MAGNIFICATION)
            .useStaminaMagnification(UPDATED_USE_STAMINA_MAGNIFICATION)
            .isIgnoreTeamSkill(UPDATED_IS_IGNORE_TEAM_SKILL)
            .staminaInfinityType(UPDATED_STAMINA_INFINITY_TYPE);
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(updatedMMatchOption);

        restMMatchOptionMockMvc.perform(put("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isOk());

        // Validate the MMatchOption in the database
        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeUpdate);
        MMatchOption testMMatchOption = mMatchOptionList.get(mMatchOptionList.size() - 1);
        assertThat(testMMatchOption.getPassiveEffectId()).isEqualTo(UPDATED_PASSIVE_EFFECT_ID);
        assertThat(testMMatchOption.getPassiveEffectValue()).isEqualTo(UPDATED_PASSIVE_EFFECT_VALUE);
        assertThat(testMMatchOption.getActivateFullPowerType()).isEqualTo(UPDATED_ACTIVATE_FULL_POWER_TYPE);
        assertThat(testMMatchOption.getAttributeMagnification()).isEqualTo(UPDATED_ATTRIBUTE_MAGNIFICATION);
        assertThat(testMMatchOption.getUseStaminaMagnification()).isEqualTo(UPDATED_USE_STAMINA_MAGNIFICATION);
        assertThat(testMMatchOption.getIsIgnoreTeamSkill()).isEqualTo(UPDATED_IS_IGNORE_TEAM_SKILL);
        assertThat(testMMatchOption.getStaminaInfinityType()).isEqualTo(UPDATED_STAMINA_INFINITY_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMMatchOption() throws Exception {
        int databaseSizeBeforeUpdate = mMatchOptionRepository.findAll().size();

        // Create the MMatchOption
        MMatchOptionDTO mMatchOptionDTO = mMatchOptionMapper.toDto(mMatchOption);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMatchOptionMockMvc.perform(put("/api/m-match-options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchOptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMatchOption in the database
        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMatchOption() throws Exception {
        // Initialize the database
        mMatchOptionRepository.saveAndFlush(mMatchOption);

        int databaseSizeBeforeDelete = mMatchOptionRepository.findAll().size();

        // Delete the mMatchOption
        restMMatchOptionMockMvc.perform(delete("/api/m-match-options/{id}", mMatchOption.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMatchOption> mMatchOptionList = mMatchOptionRepository.findAll();
        assertThat(mMatchOptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMatchOption.class);
        MMatchOption mMatchOption1 = new MMatchOption();
        mMatchOption1.setId(1L);
        MMatchOption mMatchOption2 = new MMatchOption();
        mMatchOption2.setId(mMatchOption1.getId());
        assertThat(mMatchOption1).isEqualTo(mMatchOption2);
        mMatchOption2.setId(2L);
        assertThat(mMatchOption1).isNotEqualTo(mMatchOption2);
        mMatchOption1.setId(null);
        assertThat(mMatchOption1).isNotEqualTo(mMatchOption2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMatchOptionDTO.class);
        MMatchOptionDTO mMatchOptionDTO1 = new MMatchOptionDTO();
        mMatchOptionDTO1.setId(1L);
        MMatchOptionDTO mMatchOptionDTO2 = new MMatchOptionDTO();
        assertThat(mMatchOptionDTO1).isNotEqualTo(mMatchOptionDTO2);
        mMatchOptionDTO2.setId(mMatchOptionDTO1.getId());
        assertThat(mMatchOptionDTO1).isEqualTo(mMatchOptionDTO2);
        mMatchOptionDTO2.setId(2L);
        assertThat(mMatchOptionDTO1).isNotEqualTo(mMatchOptionDTO2);
        mMatchOptionDTO1.setId(null);
        assertThat(mMatchOptionDTO1).isNotEqualTo(mMatchOptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMatchOptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMatchOptionMapper.fromId(null)).isNull();
    }
}
