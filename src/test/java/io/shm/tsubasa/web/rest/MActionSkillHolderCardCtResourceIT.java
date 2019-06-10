package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MActionSkillHolderCardCt;
import io.shm.tsubasa.domain.MCharacter;
import io.shm.tsubasa.repository.MActionSkillHolderCardCtRepository;
import io.shm.tsubasa.service.MActionSkillHolderCardCtService;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardCtDTO;
import io.shm.tsubasa.service.mapper.MActionSkillHolderCardCtMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardCtCriteria;
import io.shm.tsubasa.service.MActionSkillHolderCardCtQueryService;

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
 * Integration tests for the {@Link MActionSkillHolderCardCtResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MActionSkillHolderCardCtResourceIT {

    private static final Integer DEFAULT_TARGET_CHARA_ID = 1;
    private static final Integer UPDATED_TARGET_CHARA_ID = 2;

    private static final Integer DEFAULT_ACTION_MASTER_ID = 1;
    private static final Integer UPDATED_ACTION_MASTER_ID = 2;

    private static final Integer DEFAULT_ACTION_SKILL_EXP = 1;
    private static final Integer UPDATED_ACTION_SKILL_EXP = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MActionSkillHolderCardCtRepository mActionSkillHolderCardCtRepository;

    @Autowired
    private MActionSkillHolderCardCtMapper mActionSkillHolderCardCtMapper;

    @Autowired
    private MActionSkillHolderCardCtService mActionSkillHolderCardCtService;

    @Autowired
    private MActionSkillHolderCardCtQueryService mActionSkillHolderCardCtQueryService;

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

    private MockMvc restMActionSkillHolderCardCtMockMvc;

    private MActionSkillHolderCardCt mActionSkillHolderCardCt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MActionSkillHolderCardCtResource mActionSkillHolderCardCtResource = new MActionSkillHolderCardCtResource(mActionSkillHolderCardCtService, mActionSkillHolderCardCtQueryService);
        this.restMActionSkillHolderCardCtMockMvc = MockMvcBuilders.standaloneSetup(mActionSkillHolderCardCtResource)
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
    public static MActionSkillHolderCardCt createEntity(EntityManager em) {
        MActionSkillHolderCardCt mActionSkillHolderCardCt = new MActionSkillHolderCardCt()
            .targetCharaId(DEFAULT_TARGET_CHARA_ID)
            .actionMasterId(DEFAULT_ACTION_MASTER_ID)
            .actionSkillExp(DEFAULT_ACTION_SKILL_EXP)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mActionSkillHolderCardCt.setId(mCharacter);
        return mActionSkillHolderCardCt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MActionSkillHolderCardCt createUpdatedEntity(EntityManager em) {
        MActionSkillHolderCardCt mActionSkillHolderCardCt = new MActionSkillHolderCardCt()
            .targetCharaId(UPDATED_TARGET_CHARA_ID)
            .actionMasterId(UPDATED_ACTION_MASTER_ID)
            .actionSkillExp(UPDATED_ACTION_SKILL_EXP)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createUpdatedEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mActionSkillHolderCardCt.setId(mCharacter);
        return mActionSkillHolderCardCt;
    }

    @BeforeEach
    public void initTest() {
        mActionSkillHolderCardCt = createEntity(em);
    }

    @Test
    @Transactional
    public void createMActionSkillHolderCardCt() throws Exception {
        int databaseSizeBeforeCreate = mActionSkillHolderCardCtRepository.findAll().size();

        // Create the MActionSkillHolderCardCt
        MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO = mActionSkillHolderCardCtMapper.toDto(mActionSkillHolderCardCt);
        restMActionSkillHolderCardCtMockMvc.perform(post("/api/m-action-skill-holder-card-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardCtDTO)))
            .andExpect(status().isCreated());

        // Validate the MActionSkillHolderCardCt in the database
        List<MActionSkillHolderCardCt> mActionSkillHolderCardCtList = mActionSkillHolderCardCtRepository.findAll();
        assertThat(mActionSkillHolderCardCtList).hasSize(databaseSizeBeforeCreate + 1);
        MActionSkillHolderCardCt testMActionSkillHolderCardCt = mActionSkillHolderCardCtList.get(mActionSkillHolderCardCtList.size() - 1);
        assertThat(testMActionSkillHolderCardCt.getTargetCharaId()).isEqualTo(DEFAULT_TARGET_CHARA_ID);
        assertThat(testMActionSkillHolderCardCt.getActionMasterId()).isEqualTo(DEFAULT_ACTION_MASTER_ID);
        assertThat(testMActionSkillHolderCardCt.getActionSkillExp()).isEqualTo(DEFAULT_ACTION_SKILL_EXP);
        assertThat(testMActionSkillHolderCardCt.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMActionSkillHolderCardCt.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMActionSkillHolderCardCtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mActionSkillHolderCardCtRepository.findAll().size();

        // Create the MActionSkillHolderCardCt with an existing ID
        mActionSkillHolderCardCt.setId(1L);
        MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO = mActionSkillHolderCardCtMapper.toDto(mActionSkillHolderCardCt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMActionSkillHolderCardCtMockMvc.perform(post("/api/m-action-skill-holder-card-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardCtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionSkillHolderCardCt in the database
        List<MActionSkillHolderCardCt> mActionSkillHolderCardCtList = mActionSkillHolderCardCtRepository.findAll();
        assertThat(mActionSkillHolderCardCtList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTargetCharaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillHolderCardCtRepository.findAll().size();
        // set the field null
        mActionSkillHolderCardCt.setTargetCharaId(null);

        // Create the MActionSkillHolderCardCt, which fails.
        MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO = mActionSkillHolderCardCtMapper.toDto(mActionSkillHolderCardCt);

        restMActionSkillHolderCardCtMockMvc.perform(post("/api/m-action-skill-holder-card-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardCtDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillHolderCardCt> mActionSkillHolderCardCtList = mActionSkillHolderCardCtRepository.findAll();
        assertThat(mActionSkillHolderCardCtList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillHolderCardCtRepository.findAll().size();
        // set the field null
        mActionSkillHolderCardCt.setActionMasterId(null);

        // Create the MActionSkillHolderCardCt, which fails.
        MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO = mActionSkillHolderCardCtMapper.toDto(mActionSkillHolderCardCt);

        restMActionSkillHolderCardCtMockMvc.perform(post("/api/m-action-skill-holder-card-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardCtDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillHolderCardCt> mActionSkillHolderCardCtList = mActionSkillHolderCardCtRepository.findAll();
        assertThat(mActionSkillHolderCardCtList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionSkillExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillHolderCardCtRepository.findAll().size();
        // set the field null
        mActionSkillHolderCardCt.setActionSkillExp(null);

        // Create the MActionSkillHolderCardCt, which fails.
        MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO = mActionSkillHolderCardCtMapper.toDto(mActionSkillHolderCardCt);

        restMActionSkillHolderCardCtMockMvc.perform(post("/api/m-action-skill-holder-card-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardCtDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillHolderCardCt> mActionSkillHolderCardCtList = mActionSkillHolderCardCtRepository.findAll();
        assertThat(mActionSkillHolderCardCtList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCts() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList
        restMActionSkillHolderCardCtMockMvc.perform(get("/api/m-action-skill-holder-card-cts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionSkillHolderCardCt.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetCharaId").value(hasItem(DEFAULT_TARGET_CHARA_ID)))
            .andExpect(jsonPath("$.[*].actionMasterId").value(hasItem(DEFAULT_ACTION_MASTER_ID)))
            .andExpect(jsonPath("$.[*].actionSkillExp").value(hasItem(DEFAULT_ACTION_SKILL_EXP)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMActionSkillHolderCardCt() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get the mActionSkillHolderCardCt
        restMActionSkillHolderCardCtMockMvc.perform(get("/api/m-action-skill-holder-card-cts/{id}", mActionSkillHolderCardCt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mActionSkillHolderCardCt.getId().intValue()))
            .andExpect(jsonPath("$.targetCharaId").value(DEFAULT_TARGET_CHARA_ID))
            .andExpect(jsonPath("$.actionMasterId").value(DEFAULT_ACTION_MASTER_ID))
            .andExpect(jsonPath("$.actionSkillExp").value(DEFAULT_ACTION_SKILL_EXP))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByTargetCharaIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where targetCharaId equals to DEFAULT_TARGET_CHARA_ID
        defaultMActionSkillHolderCardCtShouldBeFound("targetCharaId.equals=" + DEFAULT_TARGET_CHARA_ID);

        // Get all the mActionSkillHolderCardCtList where targetCharaId equals to UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardCtShouldNotBeFound("targetCharaId.equals=" + UPDATED_TARGET_CHARA_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByTargetCharaIdIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where targetCharaId in DEFAULT_TARGET_CHARA_ID or UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardCtShouldBeFound("targetCharaId.in=" + DEFAULT_TARGET_CHARA_ID + "," + UPDATED_TARGET_CHARA_ID);

        // Get all the mActionSkillHolderCardCtList where targetCharaId equals to UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardCtShouldNotBeFound("targetCharaId.in=" + UPDATED_TARGET_CHARA_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByTargetCharaIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where targetCharaId is not null
        defaultMActionSkillHolderCardCtShouldBeFound("targetCharaId.specified=true");

        // Get all the mActionSkillHolderCardCtList where targetCharaId is null
        defaultMActionSkillHolderCardCtShouldNotBeFound("targetCharaId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByTargetCharaIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where targetCharaId greater than or equals to DEFAULT_TARGET_CHARA_ID
        defaultMActionSkillHolderCardCtShouldBeFound("targetCharaId.greaterOrEqualThan=" + DEFAULT_TARGET_CHARA_ID);

        // Get all the mActionSkillHolderCardCtList where targetCharaId greater than or equals to UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardCtShouldNotBeFound("targetCharaId.greaterOrEqualThan=" + UPDATED_TARGET_CHARA_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByTargetCharaIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where targetCharaId less than or equals to DEFAULT_TARGET_CHARA_ID
        defaultMActionSkillHolderCardCtShouldNotBeFound("targetCharaId.lessThan=" + DEFAULT_TARGET_CHARA_ID);

        // Get all the mActionSkillHolderCardCtList where targetCharaId less than or equals to UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardCtShouldBeFound("targetCharaId.lessThan=" + UPDATED_TARGET_CHARA_ID);
    }


    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionMasterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionMasterId equals to DEFAULT_ACTION_MASTER_ID
        defaultMActionSkillHolderCardCtShouldBeFound("actionMasterId.equals=" + DEFAULT_ACTION_MASTER_ID);

        // Get all the mActionSkillHolderCardCtList where actionMasterId equals to UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionMasterId.equals=" + UPDATED_ACTION_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionMasterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionMasterId in DEFAULT_ACTION_MASTER_ID or UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardCtShouldBeFound("actionMasterId.in=" + DEFAULT_ACTION_MASTER_ID + "," + UPDATED_ACTION_MASTER_ID);

        // Get all the mActionSkillHolderCardCtList where actionMasterId equals to UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionMasterId.in=" + UPDATED_ACTION_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionMasterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionMasterId is not null
        defaultMActionSkillHolderCardCtShouldBeFound("actionMasterId.specified=true");

        // Get all the mActionSkillHolderCardCtList where actionMasterId is null
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionMasterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionMasterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionMasterId greater than or equals to DEFAULT_ACTION_MASTER_ID
        defaultMActionSkillHolderCardCtShouldBeFound("actionMasterId.greaterOrEqualThan=" + DEFAULT_ACTION_MASTER_ID);

        // Get all the mActionSkillHolderCardCtList where actionMasterId greater than or equals to UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionMasterId.greaterOrEqualThan=" + UPDATED_ACTION_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionMasterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionMasterId less than or equals to DEFAULT_ACTION_MASTER_ID
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionMasterId.lessThan=" + DEFAULT_ACTION_MASTER_ID);

        // Get all the mActionSkillHolderCardCtList where actionMasterId less than or equals to UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardCtShouldBeFound("actionMasterId.lessThan=" + UPDATED_ACTION_MASTER_ID);
    }


    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionSkillExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionSkillExp equals to DEFAULT_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardCtShouldBeFound("actionSkillExp.equals=" + DEFAULT_ACTION_SKILL_EXP);

        // Get all the mActionSkillHolderCardCtList where actionSkillExp equals to UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionSkillExp.equals=" + UPDATED_ACTION_SKILL_EXP);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionSkillExpIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionSkillExp in DEFAULT_ACTION_SKILL_EXP or UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardCtShouldBeFound("actionSkillExp.in=" + DEFAULT_ACTION_SKILL_EXP + "," + UPDATED_ACTION_SKILL_EXP);

        // Get all the mActionSkillHolderCardCtList where actionSkillExp equals to UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionSkillExp.in=" + UPDATED_ACTION_SKILL_EXP);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionSkillExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionSkillExp is not null
        defaultMActionSkillHolderCardCtShouldBeFound("actionSkillExp.specified=true");

        // Get all the mActionSkillHolderCardCtList where actionSkillExp is null
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionSkillExp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionSkillExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionSkillExp greater than or equals to DEFAULT_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardCtShouldBeFound("actionSkillExp.greaterOrEqualThan=" + DEFAULT_ACTION_SKILL_EXP);

        // Get all the mActionSkillHolderCardCtList where actionSkillExp greater than or equals to UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionSkillExp.greaterOrEqualThan=" + UPDATED_ACTION_SKILL_EXP);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByActionSkillExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        // Get all the mActionSkillHolderCardCtList where actionSkillExp less than or equals to DEFAULT_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardCtShouldNotBeFound("actionSkillExp.lessThan=" + DEFAULT_ACTION_SKILL_EXP);

        // Get all the mActionSkillHolderCardCtList where actionSkillExp less than or equals to UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardCtShouldBeFound("actionSkillExp.lessThan=" + UPDATED_ACTION_SKILL_EXP);
    }


    @Test
    @Transactional
    public void getAllMActionSkillHolderCardCtsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MCharacter id = mActionSkillHolderCardCt.getId();
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);
        Long idId = id.getId();

        // Get all the mActionSkillHolderCardCtList where id equals to idId
        defaultMActionSkillHolderCardCtShouldBeFound("idId.equals=" + idId);

        // Get all the mActionSkillHolderCardCtList where id equals to idId + 1
        defaultMActionSkillHolderCardCtShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMActionSkillHolderCardCtShouldBeFound(String filter) throws Exception {
        restMActionSkillHolderCardCtMockMvc.perform(get("/api/m-action-skill-holder-card-cts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionSkillHolderCardCt.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetCharaId").value(hasItem(DEFAULT_TARGET_CHARA_ID)))
            .andExpect(jsonPath("$.[*].actionMasterId").value(hasItem(DEFAULT_ACTION_MASTER_ID)))
            .andExpect(jsonPath("$.[*].actionSkillExp").value(hasItem(DEFAULT_ACTION_SKILL_EXP)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMActionSkillHolderCardCtMockMvc.perform(get("/api/m-action-skill-holder-card-cts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMActionSkillHolderCardCtShouldNotBeFound(String filter) throws Exception {
        restMActionSkillHolderCardCtMockMvc.perform(get("/api/m-action-skill-holder-card-cts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMActionSkillHolderCardCtMockMvc.perform(get("/api/m-action-skill-holder-card-cts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMActionSkillHolderCardCt() throws Exception {
        // Get the mActionSkillHolderCardCt
        restMActionSkillHolderCardCtMockMvc.perform(get("/api/m-action-skill-holder-card-cts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMActionSkillHolderCardCt() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        int databaseSizeBeforeUpdate = mActionSkillHolderCardCtRepository.findAll().size();

        // Update the mActionSkillHolderCardCt
        MActionSkillHolderCardCt updatedMActionSkillHolderCardCt = mActionSkillHolderCardCtRepository.findById(mActionSkillHolderCardCt.getId()).get();
        // Disconnect from session so that the updates on updatedMActionSkillHolderCardCt are not directly saved in db
        em.detach(updatedMActionSkillHolderCardCt);
        updatedMActionSkillHolderCardCt
            .targetCharaId(UPDATED_TARGET_CHARA_ID)
            .actionMasterId(UPDATED_ACTION_MASTER_ID)
            .actionSkillExp(UPDATED_ACTION_SKILL_EXP)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO = mActionSkillHolderCardCtMapper.toDto(updatedMActionSkillHolderCardCt);

        restMActionSkillHolderCardCtMockMvc.perform(put("/api/m-action-skill-holder-card-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardCtDTO)))
            .andExpect(status().isOk());

        // Validate the MActionSkillHolderCardCt in the database
        List<MActionSkillHolderCardCt> mActionSkillHolderCardCtList = mActionSkillHolderCardCtRepository.findAll();
        assertThat(mActionSkillHolderCardCtList).hasSize(databaseSizeBeforeUpdate);
        MActionSkillHolderCardCt testMActionSkillHolderCardCt = mActionSkillHolderCardCtList.get(mActionSkillHolderCardCtList.size() - 1);
        assertThat(testMActionSkillHolderCardCt.getTargetCharaId()).isEqualTo(UPDATED_TARGET_CHARA_ID);
        assertThat(testMActionSkillHolderCardCt.getActionMasterId()).isEqualTo(UPDATED_ACTION_MASTER_ID);
        assertThat(testMActionSkillHolderCardCt.getActionSkillExp()).isEqualTo(UPDATED_ACTION_SKILL_EXP);
        assertThat(testMActionSkillHolderCardCt.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMActionSkillHolderCardCt.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMActionSkillHolderCardCt() throws Exception {
        int databaseSizeBeforeUpdate = mActionSkillHolderCardCtRepository.findAll().size();

        // Create the MActionSkillHolderCardCt
        MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO = mActionSkillHolderCardCtMapper.toDto(mActionSkillHolderCardCt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMActionSkillHolderCardCtMockMvc.perform(put("/api/m-action-skill-holder-card-cts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardCtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionSkillHolderCardCt in the database
        List<MActionSkillHolderCardCt> mActionSkillHolderCardCtList = mActionSkillHolderCardCtRepository.findAll();
        assertThat(mActionSkillHolderCardCtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMActionSkillHolderCardCt() throws Exception {
        // Initialize the database
        mActionSkillHolderCardCtRepository.saveAndFlush(mActionSkillHolderCardCt);

        int databaseSizeBeforeDelete = mActionSkillHolderCardCtRepository.findAll().size();

        // Delete the mActionSkillHolderCardCt
        restMActionSkillHolderCardCtMockMvc.perform(delete("/api/m-action-skill-holder-card-cts/{id}", mActionSkillHolderCardCt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MActionSkillHolderCardCt> mActionSkillHolderCardCtList = mActionSkillHolderCardCtRepository.findAll();
        assertThat(mActionSkillHolderCardCtList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionSkillHolderCardCt.class);
        MActionSkillHolderCardCt mActionSkillHolderCardCt1 = new MActionSkillHolderCardCt();
        mActionSkillHolderCardCt1.setId(1L);
        MActionSkillHolderCardCt mActionSkillHolderCardCt2 = new MActionSkillHolderCardCt();
        mActionSkillHolderCardCt2.setId(mActionSkillHolderCardCt1.getId());
        assertThat(mActionSkillHolderCardCt1).isEqualTo(mActionSkillHolderCardCt2);
        mActionSkillHolderCardCt2.setId(2L);
        assertThat(mActionSkillHolderCardCt1).isNotEqualTo(mActionSkillHolderCardCt2);
        mActionSkillHolderCardCt1.setId(null);
        assertThat(mActionSkillHolderCardCt1).isNotEqualTo(mActionSkillHolderCardCt2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionSkillHolderCardCtDTO.class);
        MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO1 = new MActionSkillHolderCardCtDTO();
        mActionSkillHolderCardCtDTO1.setId(1L);
        MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO2 = new MActionSkillHolderCardCtDTO();
        assertThat(mActionSkillHolderCardCtDTO1).isNotEqualTo(mActionSkillHolderCardCtDTO2);
        mActionSkillHolderCardCtDTO2.setId(mActionSkillHolderCardCtDTO1.getId());
        assertThat(mActionSkillHolderCardCtDTO1).isEqualTo(mActionSkillHolderCardCtDTO2);
        mActionSkillHolderCardCtDTO2.setId(2L);
        assertThat(mActionSkillHolderCardCtDTO1).isNotEqualTo(mActionSkillHolderCardCtDTO2);
        mActionSkillHolderCardCtDTO1.setId(null);
        assertThat(mActionSkillHolderCardCtDTO1).isNotEqualTo(mActionSkillHolderCardCtDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mActionSkillHolderCardCtMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mActionSkillHolderCardCtMapper.fromId(null)).isNull();
    }
}
