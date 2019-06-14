package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MNpcPersonality;
import io.shm.tsubasa.repository.MNpcPersonalityRepository;
import io.shm.tsubasa.service.MNpcPersonalityService;
import io.shm.tsubasa.service.dto.MNpcPersonalityDTO;
import io.shm.tsubasa.service.mapper.MNpcPersonalityMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MNpcPersonalityCriteria;
import io.shm.tsubasa.service.MNpcPersonalityQueryService;

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
 * Integration tests for the {@Link MNpcPersonalityResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MNpcPersonalityResourceIT {

    private static final Integer DEFAULT_PASSING_TARGET_RATE = 1;
    private static final Integer UPDATED_PASSING_TARGET_RATE = 2;

    private static final Integer DEFAULT_ACTION_SKILL_RATE = 1;
    private static final Integer UPDATED_ACTION_SKILL_RATE = 2;

    private static final Integer DEFAULT_DRIBBLE_MAGNIFICATION = 1;
    private static final Integer UPDATED_DRIBBLE_MAGNIFICATION = 2;

    private static final Integer DEFAULT_PASSING_MAGNIFICATION = 1;
    private static final Integer UPDATED_PASSING_MAGNIFICATION = 2;

    private static final Integer DEFAULT_ONETWO_MAGNIFICATION = 1;
    private static final Integer UPDATED_ONETWO_MAGNIFICATION = 2;

    private static final Integer DEFAULT_SHOOT_MAGNIFICATION = 1;
    private static final Integer UPDATED_SHOOT_MAGNIFICATION = 2;

    private static final Integer DEFAULT_VOLLEY_SHOOT_MAGNIFICATION = 1;
    private static final Integer UPDATED_VOLLEY_SHOOT_MAGNIFICATION = 2;

    private static final Integer DEFAULT_HEADING_SHOOT_MAGNIFICATION = 1;
    private static final Integer UPDATED_HEADING_SHOOT_MAGNIFICATION = 2;

    private static final Integer DEFAULT_TACKLE_MAGNIFICATION = 1;
    private static final Integer UPDATED_TACKLE_MAGNIFICATION = 2;

    private static final Integer DEFAULT_BLOCK_MAGNIFICATION = 1;
    private static final Integer UPDATED_BLOCK_MAGNIFICATION = 2;

    private static final Integer DEFAULT_PASS_CUT_MAGNIFICATION = 1;
    private static final Integer UPDATED_PASS_CUT_MAGNIFICATION = 2;

    private static final Integer DEFAULT_CLEAR_MAGNIFICATION = 1;
    private static final Integer UPDATED_CLEAR_MAGNIFICATION = 2;

    private static final Integer DEFAULT_COMPETE_MAGNIFICATION = 1;
    private static final Integer UPDATED_COMPETE_MAGNIFICATION = 2;

    private static final Integer DEFAULT_TRAP_MAGNIFICATION = 1;
    private static final Integer UPDATED_TRAP_MAGNIFICATION = 2;

    @Autowired
    private MNpcPersonalityRepository mNpcPersonalityRepository;

    @Autowired
    private MNpcPersonalityMapper mNpcPersonalityMapper;

    @Autowired
    private MNpcPersonalityService mNpcPersonalityService;

    @Autowired
    private MNpcPersonalityQueryService mNpcPersonalityQueryService;

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

    private MockMvc restMNpcPersonalityMockMvc;

    private MNpcPersonality mNpcPersonality;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MNpcPersonalityResource mNpcPersonalityResource = new MNpcPersonalityResource(mNpcPersonalityService, mNpcPersonalityQueryService);
        this.restMNpcPersonalityMockMvc = MockMvcBuilders.standaloneSetup(mNpcPersonalityResource)
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
    public static MNpcPersonality createEntity(EntityManager em) {
        MNpcPersonality mNpcPersonality = new MNpcPersonality()
            .passingTargetRate(DEFAULT_PASSING_TARGET_RATE)
            .actionSkillRate(DEFAULT_ACTION_SKILL_RATE)
            .dribbleMagnification(DEFAULT_DRIBBLE_MAGNIFICATION)
            .passingMagnification(DEFAULT_PASSING_MAGNIFICATION)
            .onetwoMagnification(DEFAULT_ONETWO_MAGNIFICATION)
            .shootMagnification(DEFAULT_SHOOT_MAGNIFICATION)
            .volleyShootMagnification(DEFAULT_VOLLEY_SHOOT_MAGNIFICATION)
            .headingShootMagnification(DEFAULT_HEADING_SHOOT_MAGNIFICATION)
            .tackleMagnification(DEFAULT_TACKLE_MAGNIFICATION)
            .blockMagnification(DEFAULT_BLOCK_MAGNIFICATION)
            .passCutMagnification(DEFAULT_PASS_CUT_MAGNIFICATION)
            .clearMagnification(DEFAULT_CLEAR_MAGNIFICATION)
            .competeMagnification(DEFAULT_COMPETE_MAGNIFICATION)
            .trapMagnification(DEFAULT_TRAP_MAGNIFICATION);
        return mNpcPersonality;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MNpcPersonality createUpdatedEntity(EntityManager em) {
        MNpcPersonality mNpcPersonality = new MNpcPersonality()
            .passingTargetRate(UPDATED_PASSING_TARGET_RATE)
            .actionSkillRate(UPDATED_ACTION_SKILL_RATE)
            .dribbleMagnification(UPDATED_DRIBBLE_MAGNIFICATION)
            .passingMagnification(UPDATED_PASSING_MAGNIFICATION)
            .onetwoMagnification(UPDATED_ONETWO_MAGNIFICATION)
            .shootMagnification(UPDATED_SHOOT_MAGNIFICATION)
            .volleyShootMagnification(UPDATED_VOLLEY_SHOOT_MAGNIFICATION)
            .headingShootMagnification(UPDATED_HEADING_SHOOT_MAGNIFICATION)
            .tackleMagnification(UPDATED_TACKLE_MAGNIFICATION)
            .blockMagnification(UPDATED_BLOCK_MAGNIFICATION)
            .passCutMagnification(UPDATED_PASS_CUT_MAGNIFICATION)
            .clearMagnification(UPDATED_CLEAR_MAGNIFICATION)
            .competeMagnification(UPDATED_COMPETE_MAGNIFICATION)
            .trapMagnification(UPDATED_TRAP_MAGNIFICATION);
        return mNpcPersonality;
    }

    @BeforeEach
    public void initTest() {
        mNpcPersonality = createEntity(em);
    }

    @Test
    @Transactional
    public void createMNpcPersonality() throws Exception {
        int databaseSizeBeforeCreate = mNpcPersonalityRepository.findAll().size();

        // Create the MNpcPersonality
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);
        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isCreated());

        // Validate the MNpcPersonality in the database
        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeCreate + 1);
        MNpcPersonality testMNpcPersonality = mNpcPersonalityList.get(mNpcPersonalityList.size() - 1);
        assertThat(testMNpcPersonality.getPassingTargetRate()).isEqualTo(DEFAULT_PASSING_TARGET_RATE);
        assertThat(testMNpcPersonality.getActionSkillRate()).isEqualTo(DEFAULT_ACTION_SKILL_RATE);
        assertThat(testMNpcPersonality.getDribbleMagnification()).isEqualTo(DEFAULT_DRIBBLE_MAGNIFICATION);
        assertThat(testMNpcPersonality.getPassingMagnification()).isEqualTo(DEFAULT_PASSING_MAGNIFICATION);
        assertThat(testMNpcPersonality.getOnetwoMagnification()).isEqualTo(DEFAULT_ONETWO_MAGNIFICATION);
        assertThat(testMNpcPersonality.getShootMagnification()).isEqualTo(DEFAULT_SHOOT_MAGNIFICATION);
        assertThat(testMNpcPersonality.getVolleyShootMagnification()).isEqualTo(DEFAULT_VOLLEY_SHOOT_MAGNIFICATION);
        assertThat(testMNpcPersonality.getHeadingShootMagnification()).isEqualTo(DEFAULT_HEADING_SHOOT_MAGNIFICATION);
        assertThat(testMNpcPersonality.getTackleMagnification()).isEqualTo(DEFAULT_TACKLE_MAGNIFICATION);
        assertThat(testMNpcPersonality.getBlockMagnification()).isEqualTo(DEFAULT_BLOCK_MAGNIFICATION);
        assertThat(testMNpcPersonality.getPassCutMagnification()).isEqualTo(DEFAULT_PASS_CUT_MAGNIFICATION);
        assertThat(testMNpcPersonality.getClearMagnification()).isEqualTo(DEFAULT_CLEAR_MAGNIFICATION);
        assertThat(testMNpcPersonality.getCompeteMagnification()).isEqualTo(DEFAULT_COMPETE_MAGNIFICATION);
        assertThat(testMNpcPersonality.getTrapMagnification()).isEqualTo(DEFAULT_TRAP_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void createMNpcPersonalityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mNpcPersonalityRepository.findAll().size();

        // Create the MNpcPersonality with an existing ID
        mNpcPersonality.setId(1L);
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNpcPersonality in the database
        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPassingTargetRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setPassingTargetRate(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionSkillRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setActionSkillRate(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDribbleMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setDribbleMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPassingMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setPassingMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOnetwoMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setOnetwoMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShootMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setShootMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVolleyShootMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setVolleyShootMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeadingShootMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setHeadingShootMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTackleMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setTackleMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBlockMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setBlockMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPassCutMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setPassCutMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkClearMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setClearMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCompeteMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setCompeteMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrapMagnificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcPersonalityRepository.findAll().size();
        // set the field null
        mNpcPersonality.setTrapMagnification(null);

        // Create the MNpcPersonality, which fails.
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        restMNpcPersonalityMockMvc.perform(post("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalities() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList
        restMNpcPersonalityMockMvc.perform(get("/api/m-npc-personalities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNpcPersonality.getId().intValue())))
            .andExpect(jsonPath("$.[*].passingTargetRate").value(hasItem(DEFAULT_PASSING_TARGET_RATE)))
            .andExpect(jsonPath("$.[*].actionSkillRate").value(hasItem(DEFAULT_ACTION_SKILL_RATE)))
            .andExpect(jsonPath("$.[*].dribbleMagnification").value(hasItem(DEFAULT_DRIBBLE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].passingMagnification").value(hasItem(DEFAULT_PASSING_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].onetwoMagnification").value(hasItem(DEFAULT_ONETWO_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].shootMagnification").value(hasItem(DEFAULT_SHOOT_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].volleyShootMagnification").value(hasItem(DEFAULT_VOLLEY_SHOOT_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].headingShootMagnification").value(hasItem(DEFAULT_HEADING_SHOOT_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].tackleMagnification").value(hasItem(DEFAULT_TACKLE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].blockMagnification").value(hasItem(DEFAULT_BLOCK_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].passCutMagnification").value(hasItem(DEFAULT_PASS_CUT_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].clearMagnification").value(hasItem(DEFAULT_CLEAR_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].competeMagnification").value(hasItem(DEFAULT_COMPETE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].trapMagnification").value(hasItem(DEFAULT_TRAP_MAGNIFICATION)));
    }
    
    @Test
    @Transactional
    public void getMNpcPersonality() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get the mNpcPersonality
        restMNpcPersonalityMockMvc.perform(get("/api/m-npc-personalities/{id}", mNpcPersonality.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mNpcPersonality.getId().intValue()))
            .andExpect(jsonPath("$.passingTargetRate").value(DEFAULT_PASSING_TARGET_RATE))
            .andExpect(jsonPath("$.actionSkillRate").value(DEFAULT_ACTION_SKILL_RATE))
            .andExpect(jsonPath("$.dribbleMagnification").value(DEFAULT_DRIBBLE_MAGNIFICATION))
            .andExpect(jsonPath("$.passingMagnification").value(DEFAULT_PASSING_MAGNIFICATION))
            .andExpect(jsonPath("$.onetwoMagnification").value(DEFAULT_ONETWO_MAGNIFICATION))
            .andExpect(jsonPath("$.shootMagnification").value(DEFAULT_SHOOT_MAGNIFICATION))
            .andExpect(jsonPath("$.volleyShootMagnification").value(DEFAULT_VOLLEY_SHOOT_MAGNIFICATION))
            .andExpect(jsonPath("$.headingShootMagnification").value(DEFAULT_HEADING_SHOOT_MAGNIFICATION))
            .andExpect(jsonPath("$.tackleMagnification").value(DEFAULT_TACKLE_MAGNIFICATION))
            .andExpect(jsonPath("$.blockMagnification").value(DEFAULT_BLOCK_MAGNIFICATION))
            .andExpect(jsonPath("$.passCutMagnification").value(DEFAULT_PASS_CUT_MAGNIFICATION))
            .andExpect(jsonPath("$.clearMagnification").value(DEFAULT_CLEAR_MAGNIFICATION))
            .andExpect(jsonPath("$.competeMagnification").value(DEFAULT_COMPETE_MAGNIFICATION))
            .andExpect(jsonPath("$.trapMagnification").value(DEFAULT_TRAP_MAGNIFICATION));
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingTargetRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingTargetRate equals to DEFAULT_PASSING_TARGET_RATE
        defaultMNpcPersonalityShouldBeFound("passingTargetRate.equals=" + DEFAULT_PASSING_TARGET_RATE);

        // Get all the mNpcPersonalityList where passingTargetRate equals to UPDATED_PASSING_TARGET_RATE
        defaultMNpcPersonalityShouldNotBeFound("passingTargetRate.equals=" + UPDATED_PASSING_TARGET_RATE);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingTargetRateIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingTargetRate in DEFAULT_PASSING_TARGET_RATE or UPDATED_PASSING_TARGET_RATE
        defaultMNpcPersonalityShouldBeFound("passingTargetRate.in=" + DEFAULT_PASSING_TARGET_RATE + "," + UPDATED_PASSING_TARGET_RATE);

        // Get all the mNpcPersonalityList where passingTargetRate equals to UPDATED_PASSING_TARGET_RATE
        defaultMNpcPersonalityShouldNotBeFound("passingTargetRate.in=" + UPDATED_PASSING_TARGET_RATE);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingTargetRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingTargetRate is not null
        defaultMNpcPersonalityShouldBeFound("passingTargetRate.specified=true");

        // Get all the mNpcPersonalityList where passingTargetRate is null
        defaultMNpcPersonalityShouldNotBeFound("passingTargetRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingTargetRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingTargetRate greater than or equals to DEFAULT_PASSING_TARGET_RATE
        defaultMNpcPersonalityShouldBeFound("passingTargetRate.greaterOrEqualThan=" + DEFAULT_PASSING_TARGET_RATE);

        // Get all the mNpcPersonalityList where passingTargetRate greater than or equals to UPDATED_PASSING_TARGET_RATE
        defaultMNpcPersonalityShouldNotBeFound("passingTargetRate.greaterOrEqualThan=" + UPDATED_PASSING_TARGET_RATE);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingTargetRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingTargetRate less than or equals to DEFAULT_PASSING_TARGET_RATE
        defaultMNpcPersonalityShouldNotBeFound("passingTargetRate.lessThan=" + DEFAULT_PASSING_TARGET_RATE);

        // Get all the mNpcPersonalityList where passingTargetRate less than or equals to UPDATED_PASSING_TARGET_RATE
        defaultMNpcPersonalityShouldBeFound("passingTargetRate.lessThan=" + UPDATED_PASSING_TARGET_RATE);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByActionSkillRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where actionSkillRate equals to DEFAULT_ACTION_SKILL_RATE
        defaultMNpcPersonalityShouldBeFound("actionSkillRate.equals=" + DEFAULT_ACTION_SKILL_RATE);

        // Get all the mNpcPersonalityList where actionSkillRate equals to UPDATED_ACTION_SKILL_RATE
        defaultMNpcPersonalityShouldNotBeFound("actionSkillRate.equals=" + UPDATED_ACTION_SKILL_RATE);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByActionSkillRateIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where actionSkillRate in DEFAULT_ACTION_SKILL_RATE or UPDATED_ACTION_SKILL_RATE
        defaultMNpcPersonalityShouldBeFound("actionSkillRate.in=" + DEFAULT_ACTION_SKILL_RATE + "," + UPDATED_ACTION_SKILL_RATE);

        // Get all the mNpcPersonalityList where actionSkillRate equals to UPDATED_ACTION_SKILL_RATE
        defaultMNpcPersonalityShouldNotBeFound("actionSkillRate.in=" + UPDATED_ACTION_SKILL_RATE);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByActionSkillRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where actionSkillRate is not null
        defaultMNpcPersonalityShouldBeFound("actionSkillRate.specified=true");

        // Get all the mNpcPersonalityList where actionSkillRate is null
        defaultMNpcPersonalityShouldNotBeFound("actionSkillRate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByActionSkillRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where actionSkillRate greater than or equals to DEFAULT_ACTION_SKILL_RATE
        defaultMNpcPersonalityShouldBeFound("actionSkillRate.greaterOrEqualThan=" + DEFAULT_ACTION_SKILL_RATE);

        // Get all the mNpcPersonalityList where actionSkillRate greater than or equals to UPDATED_ACTION_SKILL_RATE
        defaultMNpcPersonalityShouldNotBeFound("actionSkillRate.greaterOrEqualThan=" + UPDATED_ACTION_SKILL_RATE);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByActionSkillRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where actionSkillRate less than or equals to DEFAULT_ACTION_SKILL_RATE
        defaultMNpcPersonalityShouldNotBeFound("actionSkillRate.lessThan=" + DEFAULT_ACTION_SKILL_RATE);

        // Get all the mNpcPersonalityList where actionSkillRate less than or equals to UPDATED_ACTION_SKILL_RATE
        defaultMNpcPersonalityShouldBeFound("actionSkillRate.lessThan=" + UPDATED_ACTION_SKILL_RATE);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByDribbleMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where dribbleMagnification equals to DEFAULT_DRIBBLE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("dribbleMagnification.equals=" + DEFAULT_DRIBBLE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where dribbleMagnification equals to UPDATED_DRIBBLE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("dribbleMagnification.equals=" + UPDATED_DRIBBLE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByDribbleMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where dribbleMagnification in DEFAULT_DRIBBLE_MAGNIFICATION or UPDATED_DRIBBLE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("dribbleMagnification.in=" + DEFAULT_DRIBBLE_MAGNIFICATION + "," + UPDATED_DRIBBLE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where dribbleMagnification equals to UPDATED_DRIBBLE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("dribbleMagnification.in=" + UPDATED_DRIBBLE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByDribbleMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where dribbleMagnification is not null
        defaultMNpcPersonalityShouldBeFound("dribbleMagnification.specified=true");

        // Get all the mNpcPersonalityList where dribbleMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("dribbleMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByDribbleMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where dribbleMagnification greater than or equals to DEFAULT_DRIBBLE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("dribbleMagnification.greaterOrEqualThan=" + DEFAULT_DRIBBLE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where dribbleMagnification greater than or equals to UPDATED_DRIBBLE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("dribbleMagnification.greaterOrEqualThan=" + UPDATED_DRIBBLE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByDribbleMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where dribbleMagnification less than or equals to DEFAULT_DRIBBLE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("dribbleMagnification.lessThan=" + DEFAULT_DRIBBLE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where dribbleMagnification less than or equals to UPDATED_DRIBBLE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("dribbleMagnification.lessThan=" + UPDATED_DRIBBLE_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingMagnification equals to DEFAULT_PASSING_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("passingMagnification.equals=" + DEFAULT_PASSING_MAGNIFICATION);

        // Get all the mNpcPersonalityList where passingMagnification equals to UPDATED_PASSING_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("passingMagnification.equals=" + UPDATED_PASSING_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingMagnification in DEFAULT_PASSING_MAGNIFICATION or UPDATED_PASSING_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("passingMagnification.in=" + DEFAULT_PASSING_MAGNIFICATION + "," + UPDATED_PASSING_MAGNIFICATION);

        // Get all the mNpcPersonalityList where passingMagnification equals to UPDATED_PASSING_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("passingMagnification.in=" + UPDATED_PASSING_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingMagnification is not null
        defaultMNpcPersonalityShouldBeFound("passingMagnification.specified=true");

        // Get all the mNpcPersonalityList where passingMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("passingMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingMagnification greater than or equals to DEFAULT_PASSING_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("passingMagnification.greaterOrEqualThan=" + DEFAULT_PASSING_MAGNIFICATION);

        // Get all the mNpcPersonalityList where passingMagnification greater than or equals to UPDATED_PASSING_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("passingMagnification.greaterOrEqualThan=" + UPDATED_PASSING_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassingMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passingMagnification less than or equals to DEFAULT_PASSING_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("passingMagnification.lessThan=" + DEFAULT_PASSING_MAGNIFICATION);

        // Get all the mNpcPersonalityList where passingMagnification less than or equals to UPDATED_PASSING_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("passingMagnification.lessThan=" + UPDATED_PASSING_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByOnetwoMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where onetwoMagnification equals to DEFAULT_ONETWO_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("onetwoMagnification.equals=" + DEFAULT_ONETWO_MAGNIFICATION);

        // Get all the mNpcPersonalityList where onetwoMagnification equals to UPDATED_ONETWO_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("onetwoMagnification.equals=" + UPDATED_ONETWO_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByOnetwoMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where onetwoMagnification in DEFAULT_ONETWO_MAGNIFICATION or UPDATED_ONETWO_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("onetwoMagnification.in=" + DEFAULT_ONETWO_MAGNIFICATION + "," + UPDATED_ONETWO_MAGNIFICATION);

        // Get all the mNpcPersonalityList where onetwoMagnification equals to UPDATED_ONETWO_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("onetwoMagnification.in=" + UPDATED_ONETWO_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByOnetwoMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where onetwoMagnification is not null
        defaultMNpcPersonalityShouldBeFound("onetwoMagnification.specified=true");

        // Get all the mNpcPersonalityList where onetwoMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("onetwoMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByOnetwoMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where onetwoMagnification greater than or equals to DEFAULT_ONETWO_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("onetwoMagnification.greaterOrEqualThan=" + DEFAULT_ONETWO_MAGNIFICATION);

        // Get all the mNpcPersonalityList where onetwoMagnification greater than or equals to UPDATED_ONETWO_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("onetwoMagnification.greaterOrEqualThan=" + UPDATED_ONETWO_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByOnetwoMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where onetwoMagnification less than or equals to DEFAULT_ONETWO_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("onetwoMagnification.lessThan=" + DEFAULT_ONETWO_MAGNIFICATION);

        // Get all the mNpcPersonalityList where onetwoMagnification less than or equals to UPDATED_ONETWO_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("onetwoMagnification.lessThan=" + UPDATED_ONETWO_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByShootMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where shootMagnification equals to DEFAULT_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("shootMagnification.equals=" + DEFAULT_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where shootMagnification equals to UPDATED_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("shootMagnification.equals=" + UPDATED_SHOOT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByShootMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where shootMagnification in DEFAULT_SHOOT_MAGNIFICATION or UPDATED_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("shootMagnification.in=" + DEFAULT_SHOOT_MAGNIFICATION + "," + UPDATED_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where shootMagnification equals to UPDATED_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("shootMagnification.in=" + UPDATED_SHOOT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByShootMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where shootMagnification is not null
        defaultMNpcPersonalityShouldBeFound("shootMagnification.specified=true");

        // Get all the mNpcPersonalityList where shootMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("shootMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByShootMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where shootMagnification greater than or equals to DEFAULT_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("shootMagnification.greaterOrEqualThan=" + DEFAULT_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where shootMagnification greater than or equals to UPDATED_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("shootMagnification.greaterOrEqualThan=" + UPDATED_SHOOT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByShootMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where shootMagnification less than or equals to DEFAULT_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("shootMagnification.lessThan=" + DEFAULT_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where shootMagnification less than or equals to UPDATED_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("shootMagnification.lessThan=" + UPDATED_SHOOT_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByVolleyShootMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where volleyShootMagnification equals to DEFAULT_VOLLEY_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("volleyShootMagnification.equals=" + DEFAULT_VOLLEY_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where volleyShootMagnification equals to UPDATED_VOLLEY_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("volleyShootMagnification.equals=" + UPDATED_VOLLEY_SHOOT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByVolleyShootMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where volleyShootMagnification in DEFAULT_VOLLEY_SHOOT_MAGNIFICATION or UPDATED_VOLLEY_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("volleyShootMagnification.in=" + DEFAULT_VOLLEY_SHOOT_MAGNIFICATION + "," + UPDATED_VOLLEY_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where volleyShootMagnification equals to UPDATED_VOLLEY_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("volleyShootMagnification.in=" + UPDATED_VOLLEY_SHOOT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByVolleyShootMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where volleyShootMagnification is not null
        defaultMNpcPersonalityShouldBeFound("volleyShootMagnification.specified=true");

        // Get all the mNpcPersonalityList where volleyShootMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("volleyShootMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByVolleyShootMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where volleyShootMagnification greater than or equals to DEFAULT_VOLLEY_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("volleyShootMagnification.greaterOrEqualThan=" + DEFAULT_VOLLEY_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where volleyShootMagnification greater than or equals to UPDATED_VOLLEY_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("volleyShootMagnification.greaterOrEqualThan=" + UPDATED_VOLLEY_SHOOT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByVolleyShootMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where volleyShootMagnification less than or equals to DEFAULT_VOLLEY_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("volleyShootMagnification.lessThan=" + DEFAULT_VOLLEY_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where volleyShootMagnification less than or equals to UPDATED_VOLLEY_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("volleyShootMagnification.lessThan=" + UPDATED_VOLLEY_SHOOT_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByHeadingShootMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where headingShootMagnification equals to DEFAULT_HEADING_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("headingShootMagnification.equals=" + DEFAULT_HEADING_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where headingShootMagnification equals to UPDATED_HEADING_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("headingShootMagnification.equals=" + UPDATED_HEADING_SHOOT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByHeadingShootMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where headingShootMagnification in DEFAULT_HEADING_SHOOT_MAGNIFICATION or UPDATED_HEADING_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("headingShootMagnification.in=" + DEFAULT_HEADING_SHOOT_MAGNIFICATION + "," + UPDATED_HEADING_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where headingShootMagnification equals to UPDATED_HEADING_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("headingShootMagnification.in=" + UPDATED_HEADING_SHOOT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByHeadingShootMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where headingShootMagnification is not null
        defaultMNpcPersonalityShouldBeFound("headingShootMagnification.specified=true");

        // Get all the mNpcPersonalityList where headingShootMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("headingShootMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByHeadingShootMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where headingShootMagnification greater than or equals to DEFAULT_HEADING_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("headingShootMagnification.greaterOrEqualThan=" + DEFAULT_HEADING_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where headingShootMagnification greater than or equals to UPDATED_HEADING_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("headingShootMagnification.greaterOrEqualThan=" + UPDATED_HEADING_SHOOT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByHeadingShootMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where headingShootMagnification less than or equals to DEFAULT_HEADING_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("headingShootMagnification.lessThan=" + DEFAULT_HEADING_SHOOT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where headingShootMagnification less than or equals to UPDATED_HEADING_SHOOT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("headingShootMagnification.lessThan=" + UPDATED_HEADING_SHOOT_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTackleMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where tackleMagnification equals to DEFAULT_TACKLE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("tackleMagnification.equals=" + DEFAULT_TACKLE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where tackleMagnification equals to UPDATED_TACKLE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("tackleMagnification.equals=" + UPDATED_TACKLE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTackleMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where tackleMagnification in DEFAULT_TACKLE_MAGNIFICATION or UPDATED_TACKLE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("tackleMagnification.in=" + DEFAULT_TACKLE_MAGNIFICATION + "," + UPDATED_TACKLE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where tackleMagnification equals to UPDATED_TACKLE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("tackleMagnification.in=" + UPDATED_TACKLE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTackleMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where tackleMagnification is not null
        defaultMNpcPersonalityShouldBeFound("tackleMagnification.specified=true");

        // Get all the mNpcPersonalityList where tackleMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("tackleMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTackleMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where tackleMagnification greater than or equals to DEFAULT_TACKLE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("tackleMagnification.greaterOrEqualThan=" + DEFAULT_TACKLE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where tackleMagnification greater than or equals to UPDATED_TACKLE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("tackleMagnification.greaterOrEqualThan=" + UPDATED_TACKLE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTackleMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where tackleMagnification less than or equals to DEFAULT_TACKLE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("tackleMagnification.lessThan=" + DEFAULT_TACKLE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where tackleMagnification less than or equals to UPDATED_TACKLE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("tackleMagnification.lessThan=" + UPDATED_TACKLE_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByBlockMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where blockMagnification equals to DEFAULT_BLOCK_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("blockMagnification.equals=" + DEFAULT_BLOCK_MAGNIFICATION);

        // Get all the mNpcPersonalityList where blockMagnification equals to UPDATED_BLOCK_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("blockMagnification.equals=" + UPDATED_BLOCK_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByBlockMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where blockMagnification in DEFAULT_BLOCK_MAGNIFICATION or UPDATED_BLOCK_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("blockMagnification.in=" + DEFAULT_BLOCK_MAGNIFICATION + "," + UPDATED_BLOCK_MAGNIFICATION);

        // Get all the mNpcPersonalityList where blockMagnification equals to UPDATED_BLOCK_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("blockMagnification.in=" + UPDATED_BLOCK_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByBlockMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where blockMagnification is not null
        defaultMNpcPersonalityShouldBeFound("blockMagnification.specified=true");

        // Get all the mNpcPersonalityList where blockMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("blockMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByBlockMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where blockMagnification greater than or equals to DEFAULT_BLOCK_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("blockMagnification.greaterOrEqualThan=" + DEFAULT_BLOCK_MAGNIFICATION);

        // Get all the mNpcPersonalityList where blockMagnification greater than or equals to UPDATED_BLOCK_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("blockMagnification.greaterOrEqualThan=" + UPDATED_BLOCK_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByBlockMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where blockMagnification less than or equals to DEFAULT_BLOCK_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("blockMagnification.lessThan=" + DEFAULT_BLOCK_MAGNIFICATION);

        // Get all the mNpcPersonalityList where blockMagnification less than or equals to UPDATED_BLOCK_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("blockMagnification.lessThan=" + UPDATED_BLOCK_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassCutMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passCutMagnification equals to DEFAULT_PASS_CUT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("passCutMagnification.equals=" + DEFAULT_PASS_CUT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where passCutMagnification equals to UPDATED_PASS_CUT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("passCutMagnification.equals=" + UPDATED_PASS_CUT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassCutMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passCutMagnification in DEFAULT_PASS_CUT_MAGNIFICATION or UPDATED_PASS_CUT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("passCutMagnification.in=" + DEFAULT_PASS_CUT_MAGNIFICATION + "," + UPDATED_PASS_CUT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where passCutMagnification equals to UPDATED_PASS_CUT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("passCutMagnification.in=" + UPDATED_PASS_CUT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassCutMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passCutMagnification is not null
        defaultMNpcPersonalityShouldBeFound("passCutMagnification.specified=true");

        // Get all the mNpcPersonalityList where passCutMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("passCutMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassCutMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passCutMagnification greater than or equals to DEFAULT_PASS_CUT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("passCutMagnification.greaterOrEqualThan=" + DEFAULT_PASS_CUT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where passCutMagnification greater than or equals to UPDATED_PASS_CUT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("passCutMagnification.greaterOrEqualThan=" + UPDATED_PASS_CUT_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByPassCutMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where passCutMagnification less than or equals to DEFAULT_PASS_CUT_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("passCutMagnification.lessThan=" + DEFAULT_PASS_CUT_MAGNIFICATION);

        // Get all the mNpcPersonalityList where passCutMagnification less than or equals to UPDATED_PASS_CUT_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("passCutMagnification.lessThan=" + UPDATED_PASS_CUT_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByClearMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where clearMagnification equals to DEFAULT_CLEAR_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("clearMagnification.equals=" + DEFAULT_CLEAR_MAGNIFICATION);

        // Get all the mNpcPersonalityList where clearMagnification equals to UPDATED_CLEAR_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("clearMagnification.equals=" + UPDATED_CLEAR_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByClearMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where clearMagnification in DEFAULT_CLEAR_MAGNIFICATION or UPDATED_CLEAR_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("clearMagnification.in=" + DEFAULT_CLEAR_MAGNIFICATION + "," + UPDATED_CLEAR_MAGNIFICATION);

        // Get all the mNpcPersonalityList where clearMagnification equals to UPDATED_CLEAR_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("clearMagnification.in=" + UPDATED_CLEAR_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByClearMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where clearMagnification is not null
        defaultMNpcPersonalityShouldBeFound("clearMagnification.specified=true");

        // Get all the mNpcPersonalityList where clearMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("clearMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByClearMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where clearMagnification greater than or equals to DEFAULT_CLEAR_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("clearMagnification.greaterOrEqualThan=" + DEFAULT_CLEAR_MAGNIFICATION);

        // Get all the mNpcPersonalityList where clearMagnification greater than or equals to UPDATED_CLEAR_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("clearMagnification.greaterOrEqualThan=" + UPDATED_CLEAR_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByClearMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where clearMagnification less than or equals to DEFAULT_CLEAR_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("clearMagnification.lessThan=" + DEFAULT_CLEAR_MAGNIFICATION);

        // Get all the mNpcPersonalityList where clearMagnification less than or equals to UPDATED_CLEAR_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("clearMagnification.lessThan=" + UPDATED_CLEAR_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByCompeteMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where competeMagnification equals to DEFAULT_COMPETE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("competeMagnification.equals=" + DEFAULT_COMPETE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where competeMagnification equals to UPDATED_COMPETE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("competeMagnification.equals=" + UPDATED_COMPETE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByCompeteMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where competeMagnification in DEFAULT_COMPETE_MAGNIFICATION or UPDATED_COMPETE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("competeMagnification.in=" + DEFAULT_COMPETE_MAGNIFICATION + "," + UPDATED_COMPETE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where competeMagnification equals to UPDATED_COMPETE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("competeMagnification.in=" + UPDATED_COMPETE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByCompeteMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where competeMagnification is not null
        defaultMNpcPersonalityShouldBeFound("competeMagnification.specified=true");

        // Get all the mNpcPersonalityList where competeMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("competeMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByCompeteMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where competeMagnification greater than or equals to DEFAULT_COMPETE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("competeMagnification.greaterOrEqualThan=" + DEFAULT_COMPETE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where competeMagnification greater than or equals to UPDATED_COMPETE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("competeMagnification.greaterOrEqualThan=" + UPDATED_COMPETE_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByCompeteMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where competeMagnification less than or equals to DEFAULT_COMPETE_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("competeMagnification.lessThan=" + DEFAULT_COMPETE_MAGNIFICATION);

        // Get all the mNpcPersonalityList where competeMagnification less than or equals to UPDATED_COMPETE_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("competeMagnification.lessThan=" + UPDATED_COMPETE_MAGNIFICATION);
    }


    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTrapMagnificationIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where trapMagnification equals to DEFAULT_TRAP_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("trapMagnification.equals=" + DEFAULT_TRAP_MAGNIFICATION);

        // Get all the mNpcPersonalityList where trapMagnification equals to UPDATED_TRAP_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("trapMagnification.equals=" + UPDATED_TRAP_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTrapMagnificationIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where trapMagnification in DEFAULT_TRAP_MAGNIFICATION or UPDATED_TRAP_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("trapMagnification.in=" + DEFAULT_TRAP_MAGNIFICATION + "," + UPDATED_TRAP_MAGNIFICATION);

        // Get all the mNpcPersonalityList where trapMagnification equals to UPDATED_TRAP_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("trapMagnification.in=" + UPDATED_TRAP_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTrapMagnificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where trapMagnification is not null
        defaultMNpcPersonalityShouldBeFound("trapMagnification.specified=true");

        // Get all the mNpcPersonalityList where trapMagnification is null
        defaultMNpcPersonalityShouldNotBeFound("trapMagnification.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTrapMagnificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where trapMagnification greater than or equals to DEFAULT_TRAP_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("trapMagnification.greaterOrEqualThan=" + DEFAULT_TRAP_MAGNIFICATION);

        // Get all the mNpcPersonalityList where trapMagnification greater than or equals to UPDATED_TRAP_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("trapMagnification.greaterOrEqualThan=" + UPDATED_TRAP_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void getAllMNpcPersonalitiesByTrapMagnificationIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        // Get all the mNpcPersonalityList where trapMagnification less than or equals to DEFAULT_TRAP_MAGNIFICATION
        defaultMNpcPersonalityShouldNotBeFound("trapMagnification.lessThan=" + DEFAULT_TRAP_MAGNIFICATION);

        // Get all the mNpcPersonalityList where trapMagnification less than or equals to UPDATED_TRAP_MAGNIFICATION
        defaultMNpcPersonalityShouldBeFound("trapMagnification.lessThan=" + UPDATED_TRAP_MAGNIFICATION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMNpcPersonalityShouldBeFound(String filter) throws Exception {
        restMNpcPersonalityMockMvc.perform(get("/api/m-npc-personalities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNpcPersonality.getId().intValue())))
            .andExpect(jsonPath("$.[*].passingTargetRate").value(hasItem(DEFAULT_PASSING_TARGET_RATE)))
            .andExpect(jsonPath("$.[*].actionSkillRate").value(hasItem(DEFAULT_ACTION_SKILL_RATE)))
            .andExpect(jsonPath("$.[*].dribbleMagnification").value(hasItem(DEFAULT_DRIBBLE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].passingMagnification").value(hasItem(DEFAULT_PASSING_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].onetwoMagnification").value(hasItem(DEFAULT_ONETWO_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].shootMagnification").value(hasItem(DEFAULT_SHOOT_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].volleyShootMagnification").value(hasItem(DEFAULT_VOLLEY_SHOOT_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].headingShootMagnification").value(hasItem(DEFAULT_HEADING_SHOOT_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].tackleMagnification").value(hasItem(DEFAULT_TACKLE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].blockMagnification").value(hasItem(DEFAULT_BLOCK_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].passCutMagnification").value(hasItem(DEFAULT_PASS_CUT_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].clearMagnification").value(hasItem(DEFAULT_CLEAR_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].competeMagnification").value(hasItem(DEFAULT_COMPETE_MAGNIFICATION)))
            .andExpect(jsonPath("$.[*].trapMagnification").value(hasItem(DEFAULT_TRAP_MAGNIFICATION)));

        // Check, that the count call also returns 1
        restMNpcPersonalityMockMvc.perform(get("/api/m-npc-personalities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMNpcPersonalityShouldNotBeFound(String filter) throws Exception {
        restMNpcPersonalityMockMvc.perform(get("/api/m-npc-personalities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMNpcPersonalityMockMvc.perform(get("/api/m-npc-personalities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMNpcPersonality() throws Exception {
        // Get the mNpcPersonality
        restMNpcPersonalityMockMvc.perform(get("/api/m-npc-personalities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMNpcPersonality() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        int databaseSizeBeforeUpdate = mNpcPersonalityRepository.findAll().size();

        // Update the mNpcPersonality
        MNpcPersonality updatedMNpcPersonality = mNpcPersonalityRepository.findById(mNpcPersonality.getId()).get();
        // Disconnect from session so that the updates on updatedMNpcPersonality are not directly saved in db
        em.detach(updatedMNpcPersonality);
        updatedMNpcPersonality
            .passingTargetRate(UPDATED_PASSING_TARGET_RATE)
            .actionSkillRate(UPDATED_ACTION_SKILL_RATE)
            .dribbleMagnification(UPDATED_DRIBBLE_MAGNIFICATION)
            .passingMagnification(UPDATED_PASSING_MAGNIFICATION)
            .onetwoMagnification(UPDATED_ONETWO_MAGNIFICATION)
            .shootMagnification(UPDATED_SHOOT_MAGNIFICATION)
            .volleyShootMagnification(UPDATED_VOLLEY_SHOOT_MAGNIFICATION)
            .headingShootMagnification(UPDATED_HEADING_SHOOT_MAGNIFICATION)
            .tackleMagnification(UPDATED_TACKLE_MAGNIFICATION)
            .blockMagnification(UPDATED_BLOCK_MAGNIFICATION)
            .passCutMagnification(UPDATED_PASS_CUT_MAGNIFICATION)
            .clearMagnification(UPDATED_CLEAR_MAGNIFICATION)
            .competeMagnification(UPDATED_COMPETE_MAGNIFICATION)
            .trapMagnification(UPDATED_TRAP_MAGNIFICATION);
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(updatedMNpcPersonality);

        restMNpcPersonalityMockMvc.perform(put("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isOk());

        // Validate the MNpcPersonality in the database
        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeUpdate);
        MNpcPersonality testMNpcPersonality = mNpcPersonalityList.get(mNpcPersonalityList.size() - 1);
        assertThat(testMNpcPersonality.getPassingTargetRate()).isEqualTo(UPDATED_PASSING_TARGET_RATE);
        assertThat(testMNpcPersonality.getActionSkillRate()).isEqualTo(UPDATED_ACTION_SKILL_RATE);
        assertThat(testMNpcPersonality.getDribbleMagnification()).isEqualTo(UPDATED_DRIBBLE_MAGNIFICATION);
        assertThat(testMNpcPersonality.getPassingMagnification()).isEqualTo(UPDATED_PASSING_MAGNIFICATION);
        assertThat(testMNpcPersonality.getOnetwoMagnification()).isEqualTo(UPDATED_ONETWO_MAGNIFICATION);
        assertThat(testMNpcPersonality.getShootMagnification()).isEqualTo(UPDATED_SHOOT_MAGNIFICATION);
        assertThat(testMNpcPersonality.getVolleyShootMagnification()).isEqualTo(UPDATED_VOLLEY_SHOOT_MAGNIFICATION);
        assertThat(testMNpcPersonality.getHeadingShootMagnification()).isEqualTo(UPDATED_HEADING_SHOOT_MAGNIFICATION);
        assertThat(testMNpcPersonality.getTackleMagnification()).isEqualTo(UPDATED_TACKLE_MAGNIFICATION);
        assertThat(testMNpcPersonality.getBlockMagnification()).isEqualTo(UPDATED_BLOCK_MAGNIFICATION);
        assertThat(testMNpcPersonality.getPassCutMagnification()).isEqualTo(UPDATED_PASS_CUT_MAGNIFICATION);
        assertThat(testMNpcPersonality.getClearMagnification()).isEqualTo(UPDATED_CLEAR_MAGNIFICATION);
        assertThat(testMNpcPersonality.getCompeteMagnification()).isEqualTo(UPDATED_COMPETE_MAGNIFICATION);
        assertThat(testMNpcPersonality.getTrapMagnification()).isEqualTo(UPDATED_TRAP_MAGNIFICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingMNpcPersonality() throws Exception {
        int databaseSizeBeforeUpdate = mNpcPersonalityRepository.findAll().size();

        // Create the MNpcPersonality
        MNpcPersonalityDTO mNpcPersonalityDTO = mNpcPersonalityMapper.toDto(mNpcPersonality);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMNpcPersonalityMockMvc.perform(put("/api/m-npc-personalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcPersonalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNpcPersonality in the database
        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMNpcPersonality() throws Exception {
        // Initialize the database
        mNpcPersonalityRepository.saveAndFlush(mNpcPersonality);

        int databaseSizeBeforeDelete = mNpcPersonalityRepository.findAll().size();

        // Delete the mNpcPersonality
        restMNpcPersonalityMockMvc.perform(delete("/api/m-npc-personalities/{id}", mNpcPersonality.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MNpcPersonality> mNpcPersonalityList = mNpcPersonalityRepository.findAll();
        assertThat(mNpcPersonalityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNpcPersonality.class);
        MNpcPersonality mNpcPersonality1 = new MNpcPersonality();
        mNpcPersonality1.setId(1L);
        MNpcPersonality mNpcPersonality2 = new MNpcPersonality();
        mNpcPersonality2.setId(mNpcPersonality1.getId());
        assertThat(mNpcPersonality1).isEqualTo(mNpcPersonality2);
        mNpcPersonality2.setId(2L);
        assertThat(mNpcPersonality1).isNotEqualTo(mNpcPersonality2);
        mNpcPersonality1.setId(null);
        assertThat(mNpcPersonality1).isNotEqualTo(mNpcPersonality2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNpcPersonalityDTO.class);
        MNpcPersonalityDTO mNpcPersonalityDTO1 = new MNpcPersonalityDTO();
        mNpcPersonalityDTO1.setId(1L);
        MNpcPersonalityDTO mNpcPersonalityDTO2 = new MNpcPersonalityDTO();
        assertThat(mNpcPersonalityDTO1).isNotEqualTo(mNpcPersonalityDTO2);
        mNpcPersonalityDTO2.setId(mNpcPersonalityDTO1.getId());
        assertThat(mNpcPersonalityDTO1).isEqualTo(mNpcPersonalityDTO2);
        mNpcPersonalityDTO2.setId(2L);
        assertThat(mNpcPersonalityDTO1).isNotEqualTo(mNpcPersonalityDTO2);
        mNpcPersonalityDTO1.setId(null);
        assertThat(mNpcPersonalityDTO1).isNotEqualTo(mNpcPersonalityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mNpcPersonalityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mNpcPersonalityMapper.fromId(null)).isNull();
    }
}
