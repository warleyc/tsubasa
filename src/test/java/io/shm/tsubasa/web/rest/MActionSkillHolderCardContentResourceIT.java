package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MActionSkillHolderCardContent;
import io.shm.tsubasa.domain.MCharacter;
import io.shm.tsubasa.repository.MActionSkillHolderCardContentRepository;
import io.shm.tsubasa.service.MActionSkillHolderCardContentService;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardContentDTO;
import io.shm.tsubasa.service.mapper.MActionSkillHolderCardContentMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardContentCriteria;
import io.shm.tsubasa.service.MActionSkillHolderCardContentQueryService;

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
 * Integration tests for the {@Link MActionSkillHolderCardContentResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MActionSkillHolderCardContentResourceIT {

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
    private MActionSkillHolderCardContentRepository mActionSkillHolderCardContentRepository;

    @Autowired
    private MActionSkillHolderCardContentMapper mActionSkillHolderCardContentMapper;

    @Autowired
    private MActionSkillHolderCardContentService mActionSkillHolderCardContentService;

    @Autowired
    private MActionSkillHolderCardContentQueryService mActionSkillHolderCardContentQueryService;

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

    private MockMvc restMActionSkillHolderCardContentMockMvc;

    private MActionSkillHolderCardContent mActionSkillHolderCardContent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MActionSkillHolderCardContentResource mActionSkillHolderCardContentResource = new MActionSkillHolderCardContentResource(mActionSkillHolderCardContentService, mActionSkillHolderCardContentQueryService);
        this.restMActionSkillHolderCardContentMockMvc = MockMvcBuilders.standaloneSetup(mActionSkillHolderCardContentResource)
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
    public static MActionSkillHolderCardContent createEntity(EntityManager em) {
        MActionSkillHolderCardContent mActionSkillHolderCardContent = new MActionSkillHolderCardContent()
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
        mActionSkillHolderCardContent.setMcharacter(mCharacter);
        return mActionSkillHolderCardContent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MActionSkillHolderCardContent createUpdatedEntity(EntityManager em) {
        MActionSkillHolderCardContent mActionSkillHolderCardContent = new MActionSkillHolderCardContent()
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
        mActionSkillHolderCardContent.setMcharacter(mCharacter);
        return mActionSkillHolderCardContent;
    }

    @BeforeEach
    public void initTest() {
        mActionSkillHolderCardContent = createEntity(em);
    }

    @Test
    @Transactional
    public void createMActionSkillHolderCardContent() throws Exception {
        int databaseSizeBeforeCreate = mActionSkillHolderCardContentRepository.findAll().size();

        // Create the MActionSkillHolderCardContent
        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO = mActionSkillHolderCardContentMapper.toDto(mActionSkillHolderCardContent);
        restMActionSkillHolderCardContentMockMvc.perform(post("/api/m-action-skill-holder-card-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardContentDTO)))
            .andExpect(status().isCreated());

        // Validate the MActionSkillHolderCardContent in the database
        List<MActionSkillHolderCardContent> mActionSkillHolderCardContentList = mActionSkillHolderCardContentRepository.findAll();
        assertThat(mActionSkillHolderCardContentList).hasSize(databaseSizeBeforeCreate + 1);
        MActionSkillHolderCardContent testMActionSkillHolderCardContent = mActionSkillHolderCardContentList.get(mActionSkillHolderCardContentList.size() - 1);
        assertThat(testMActionSkillHolderCardContent.getTargetCharaId()).isEqualTo(DEFAULT_TARGET_CHARA_ID);
        assertThat(testMActionSkillHolderCardContent.getActionMasterId()).isEqualTo(DEFAULT_ACTION_MASTER_ID);
        assertThat(testMActionSkillHolderCardContent.getActionSkillExp()).isEqualTo(DEFAULT_ACTION_SKILL_EXP);
        assertThat(testMActionSkillHolderCardContent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMActionSkillHolderCardContent.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMActionSkillHolderCardContentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mActionSkillHolderCardContentRepository.findAll().size();

        // Create the MActionSkillHolderCardContent with an existing ID
        mActionSkillHolderCardContent.setId(1L);
        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO = mActionSkillHolderCardContentMapper.toDto(mActionSkillHolderCardContent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMActionSkillHolderCardContentMockMvc.perform(post("/api/m-action-skill-holder-card-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardContentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionSkillHolderCardContent in the database
        List<MActionSkillHolderCardContent> mActionSkillHolderCardContentList = mActionSkillHolderCardContentRepository.findAll();
        assertThat(mActionSkillHolderCardContentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTargetCharaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillHolderCardContentRepository.findAll().size();
        // set the field null
        mActionSkillHolderCardContent.setTargetCharaId(null);

        // Create the MActionSkillHolderCardContent, which fails.
        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO = mActionSkillHolderCardContentMapper.toDto(mActionSkillHolderCardContent);

        restMActionSkillHolderCardContentMockMvc.perform(post("/api/m-action-skill-holder-card-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardContentDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillHolderCardContent> mActionSkillHolderCardContentList = mActionSkillHolderCardContentRepository.findAll();
        assertThat(mActionSkillHolderCardContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillHolderCardContentRepository.findAll().size();
        // set the field null
        mActionSkillHolderCardContent.setActionMasterId(null);

        // Create the MActionSkillHolderCardContent, which fails.
        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO = mActionSkillHolderCardContentMapper.toDto(mActionSkillHolderCardContent);

        restMActionSkillHolderCardContentMockMvc.perform(post("/api/m-action-skill-holder-card-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardContentDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillHolderCardContent> mActionSkillHolderCardContentList = mActionSkillHolderCardContentRepository.findAll();
        assertThat(mActionSkillHolderCardContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionSkillExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillHolderCardContentRepository.findAll().size();
        // set the field null
        mActionSkillHolderCardContent.setActionSkillExp(null);

        // Create the MActionSkillHolderCardContent, which fails.
        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO = mActionSkillHolderCardContentMapper.toDto(mActionSkillHolderCardContent);

        restMActionSkillHolderCardContentMockMvc.perform(post("/api/m-action-skill-holder-card-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardContentDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillHolderCardContent> mActionSkillHolderCardContentList = mActionSkillHolderCardContentRepository.findAll();
        assertThat(mActionSkillHolderCardContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContents() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList
        restMActionSkillHolderCardContentMockMvc.perform(get("/api/m-action-skill-holder-card-contents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionSkillHolderCardContent.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetCharaId").value(hasItem(DEFAULT_TARGET_CHARA_ID)))
            .andExpect(jsonPath("$.[*].actionMasterId").value(hasItem(DEFAULT_ACTION_MASTER_ID)))
            .andExpect(jsonPath("$.[*].actionSkillExp").value(hasItem(DEFAULT_ACTION_SKILL_EXP)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMActionSkillHolderCardContent() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get the mActionSkillHolderCardContent
        restMActionSkillHolderCardContentMockMvc.perform(get("/api/m-action-skill-holder-card-contents/{id}", mActionSkillHolderCardContent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mActionSkillHolderCardContent.getId().intValue()))
            .andExpect(jsonPath("$.targetCharaId").value(DEFAULT_TARGET_CHARA_ID))
            .andExpect(jsonPath("$.actionMasterId").value(DEFAULT_ACTION_MASTER_ID))
            .andExpect(jsonPath("$.actionSkillExp").value(DEFAULT_ACTION_SKILL_EXP))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByTargetCharaIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where targetCharaId equals to DEFAULT_TARGET_CHARA_ID
        defaultMActionSkillHolderCardContentShouldBeFound("targetCharaId.equals=" + DEFAULT_TARGET_CHARA_ID);

        // Get all the mActionSkillHolderCardContentList where targetCharaId equals to UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardContentShouldNotBeFound("targetCharaId.equals=" + UPDATED_TARGET_CHARA_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByTargetCharaIdIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where targetCharaId in DEFAULT_TARGET_CHARA_ID or UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardContentShouldBeFound("targetCharaId.in=" + DEFAULT_TARGET_CHARA_ID + "," + UPDATED_TARGET_CHARA_ID);

        // Get all the mActionSkillHolderCardContentList where targetCharaId equals to UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardContentShouldNotBeFound("targetCharaId.in=" + UPDATED_TARGET_CHARA_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByTargetCharaIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where targetCharaId is not null
        defaultMActionSkillHolderCardContentShouldBeFound("targetCharaId.specified=true");

        // Get all the mActionSkillHolderCardContentList where targetCharaId is null
        defaultMActionSkillHolderCardContentShouldNotBeFound("targetCharaId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByTargetCharaIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where targetCharaId greater than or equals to DEFAULT_TARGET_CHARA_ID
        defaultMActionSkillHolderCardContentShouldBeFound("targetCharaId.greaterOrEqualThan=" + DEFAULT_TARGET_CHARA_ID);

        // Get all the mActionSkillHolderCardContentList where targetCharaId greater than or equals to UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardContentShouldNotBeFound("targetCharaId.greaterOrEqualThan=" + UPDATED_TARGET_CHARA_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByTargetCharaIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where targetCharaId less than or equals to DEFAULT_TARGET_CHARA_ID
        defaultMActionSkillHolderCardContentShouldNotBeFound("targetCharaId.lessThan=" + DEFAULT_TARGET_CHARA_ID);

        // Get all the mActionSkillHolderCardContentList where targetCharaId less than or equals to UPDATED_TARGET_CHARA_ID
        defaultMActionSkillHolderCardContentShouldBeFound("targetCharaId.lessThan=" + UPDATED_TARGET_CHARA_ID);
    }


    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionMasterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionMasterId equals to DEFAULT_ACTION_MASTER_ID
        defaultMActionSkillHolderCardContentShouldBeFound("actionMasterId.equals=" + DEFAULT_ACTION_MASTER_ID);

        // Get all the mActionSkillHolderCardContentList where actionMasterId equals to UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionMasterId.equals=" + UPDATED_ACTION_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionMasterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionMasterId in DEFAULT_ACTION_MASTER_ID or UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardContentShouldBeFound("actionMasterId.in=" + DEFAULT_ACTION_MASTER_ID + "," + UPDATED_ACTION_MASTER_ID);

        // Get all the mActionSkillHolderCardContentList where actionMasterId equals to UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionMasterId.in=" + UPDATED_ACTION_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionMasterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionMasterId is not null
        defaultMActionSkillHolderCardContentShouldBeFound("actionMasterId.specified=true");

        // Get all the mActionSkillHolderCardContentList where actionMasterId is null
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionMasterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionMasterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionMasterId greater than or equals to DEFAULT_ACTION_MASTER_ID
        defaultMActionSkillHolderCardContentShouldBeFound("actionMasterId.greaterOrEqualThan=" + DEFAULT_ACTION_MASTER_ID);

        // Get all the mActionSkillHolderCardContentList where actionMasterId greater than or equals to UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionMasterId.greaterOrEqualThan=" + UPDATED_ACTION_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionMasterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionMasterId less than or equals to DEFAULT_ACTION_MASTER_ID
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionMasterId.lessThan=" + DEFAULT_ACTION_MASTER_ID);

        // Get all the mActionSkillHolderCardContentList where actionMasterId less than or equals to UPDATED_ACTION_MASTER_ID
        defaultMActionSkillHolderCardContentShouldBeFound("actionMasterId.lessThan=" + UPDATED_ACTION_MASTER_ID);
    }


    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionSkillExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionSkillExp equals to DEFAULT_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardContentShouldBeFound("actionSkillExp.equals=" + DEFAULT_ACTION_SKILL_EXP);

        // Get all the mActionSkillHolderCardContentList where actionSkillExp equals to UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionSkillExp.equals=" + UPDATED_ACTION_SKILL_EXP);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionSkillExpIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionSkillExp in DEFAULT_ACTION_SKILL_EXP or UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardContentShouldBeFound("actionSkillExp.in=" + DEFAULT_ACTION_SKILL_EXP + "," + UPDATED_ACTION_SKILL_EXP);

        // Get all the mActionSkillHolderCardContentList where actionSkillExp equals to UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionSkillExp.in=" + UPDATED_ACTION_SKILL_EXP);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionSkillExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionSkillExp is not null
        defaultMActionSkillHolderCardContentShouldBeFound("actionSkillExp.specified=true");

        // Get all the mActionSkillHolderCardContentList where actionSkillExp is null
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionSkillExp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionSkillExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionSkillExp greater than or equals to DEFAULT_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardContentShouldBeFound("actionSkillExp.greaterOrEqualThan=" + DEFAULT_ACTION_SKILL_EXP);

        // Get all the mActionSkillHolderCardContentList where actionSkillExp greater than or equals to UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionSkillExp.greaterOrEqualThan=" + UPDATED_ACTION_SKILL_EXP);
    }

    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByActionSkillExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        // Get all the mActionSkillHolderCardContentList where actionSkillExp less than or equals to DEFAULT_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardContentShouldNotBeFound("actionSkillExp.lessThan=" + DEFAULT_ACTION_SKILL_EXP);

        // Get all the mActionSkillHolderCardContentList where actionSkillExp less than or equals to UPDATED_ACTION_SKILL_EXP
        defaultMActionSkillHolderCardContentShouldBeFound("actionSkillExp.lessThan=" + UPDATED_ACTION_SKILL_EXP);
    }


    @Test
    @Transactional
    public void getAllMActionSkillHolderCardContentsByMcharacterIsEqualToSomething() throws Exception {
        // Get already existing entity
        MCharacter mcharacter = mActionSkillHolderCardContent.getMcharacter();
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);
        Long mcharacterId = mcharacter.getId();

        // Get all the mActionSkillHolderCardContentList where mcharacter equals to mcharacterId
        defaultMActionSkillHolderCardContentShouldBeFound("mcharacterId.equals=" + mcharacterId);

        // Get all the mActionSkillHolderCardContentList where mcharacter equals to mcharacterId + 1
        defaultMActionSkillHolderCardContentShouldNotBeFound("mcharacterId.equals=" + (mcharacterId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMActionSkillHolderCardContentShouldBeFound(String filter) throws Exception {
        restMActionSkillHolderCardContentMockMvc.perform(get("/api/m-action-skill-holder-card-contents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionSkillHolderCardContent.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetCharaId").value(hasItem(DEFAULT_TARGET_CHARA_ID)))
            .andExpect(jsonPath("$.[*].actionMasterId").value(hasItem(DEFAULT_ACTION_MASTER_ID)))
            .andExpect(jsonPath("$.[*].actionSkillExp").value(hasItem(DEFAULT_ACTION_SKILL_EXP)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMActionSkillHolderCardContentMockMvc.perform(get("/api/m-action-skill-holder-card-contents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMActionSkillHolderCardContentShouldNotBeFound(String filter) throws Exception {
        restMActionSkillHolderCardContentMockMvc.perform(get("/api/m-action-skill-holder-card-contents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMActionSkillHolderCardContentMockMvc.perform(get("/api/m-action-skill-holder-card-contents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMActionSkillHolderCardContent() throws Exception {
        // Get the mActionSkillHolderCardContent
        restMActionSkillHolderCardContentMockMvc.perform(get("/api/m-action-skill-holder-card-contents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMActionSkillHolderCardContent() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        int databaseSizeBeforeUpdate = mActionSkillHolderCardContentRepository.findAll().size();

        // Update the mActionSkillHolderCardContent
        MActionSkillHolderCardContent updatedMActionSkillHolderCardContent = mActionSkillHolderCardContentRepository.findById(mActionSkillHolderCardContent.getId()).get();
        // Disconnect from session so that the updates on updatedMActionSkillHolderCardContent are not directly saved in db
        em.detach(updatedMActionSkillHolderCardContent);
        updatedMActionSkillHolderCardContent
            .targetCharaId(UPDATED_TARGET_CHARA_ID)
            .actionMasterId(UPDATED_ACTION_MASTER_ID)
            .actionSkillExp(UPDATED_ACTION_SKILL_EXP)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO = mActionSkillHolderCardContentMapper.toDto(updatedMActionSkillHolderCardContent);

        restMActionSkillHolderCardContentMockMvc.perform(put("/api/m-action-skill-holder-card-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardContentDTO)))
            .andExpect(status().isOk());

        // Validate the MActionSkillHolderCardContent in the database
        List<MActionSkillHolderCardContent> mActionSkillHolderCardContentList = mActionSkillHolderCardContentRepository.findAll();
        assertThat(mActionSkillHolderCardContentList).hasSize(databaseSizeBeforeUpdate);
        MActionSkillHolderCardContent testMActionSkillHolderCardContent = mActionSkillHolderCardContentList.get(mActionSkillHolderCardContentList.size() - 1);
        assertThat(testMActionSkillHolderCardContent.getTargetCharaId()).isEqualTo(UPDATED_TARGET_CHARA_ID);
        assertThat(testMActionSkillHolderCardContent.getActionMasterId()).isEqualTo(UPDATED_ACTION_MASTER_ID);
        assertThat(testMActionSkillHolderCardContent.getActionSkillExp()).isEqualTo(UPDATED_ACTION_SKILL_EXP);
        assertThat(testMActionSkillHolderCardContent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMActionSkillHolderCardContent.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMActionSkillHolderCardContent() throws Exception {
        int databaseSizeBeforeUpdate = mActionSkillHolderCardContentRepository.findAll().size();

        // Create the MActionSkillHolderCardContent
        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO = mActionSkillHolderCardContentMapper.toDto(mActionSkillHolderCardContent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMActionSkillHolderCardContentMockMvc.perform(put("/api/m-action-skill-holder-card-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillHolderCardContentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionSkillHolderCardContent in the database
        List<MActionSkillHolderCardContent> mActionSkillHolderCardContentList = mActionSkillHolderCardContentRepository.findAll();
        assertThat(mActionSkillHolderCardContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMActionSkillHolderCardContent() throws Exception {
        // Initialize the database
        mActionSkillHolderCardContentRepository.saveAndFlush(mActionSkillHolderCardContent);

        int databaseSizeBeforeDelete = mActionSkillHolderCardContentRepository.findAll().size();

        // Delete the mActionSkillHolderCardContent
        restMActionSkillHolderCardContentMockMvc.perform(delete("/api/m-action-skill-holder-card-contents/{id}", mActionSkillHolderCardContent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MActionSkillHolderCardContent> mActionSkillHolderCardContentList = mActionSkillHolderCardContentRepository.findAll();
        assertThat(mActionSkillHolderCardContentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionSkillHolderCardContent.class);
        MActionSkillHolderCardContent mActionSkillHolderCardContent1 = new MActionSkillHolderCardContent();
        mActionSkillHolderCardContent1.setId(1L);
        MActionSkillHolderCardContent mActionSkillHolderCardContent2 = new MActionSkillHolderCardContent();
        mActionSkillHolderCardContent2.setId(mActionSkillHolderCardContent1.getId());
        assertThat(mActionSkillHolderCardContent1).isEqualTo(mActionSkillHolderCardContent2);
        mActionSkillHolderCardContent2.setId(2L);
        assertThat(mActionSkillHolderCardContent1).isNotEqualTo(mActionSkillHolderCardContent2);
        mActionSkillHolderCardContent1.setId(null);
        assertThat(mActionSkillHolderCardContent1).isNotEqualTo(mActionSkillHolderCardContent2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionSkillHolderCardContentDTO.class);
        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO1 = new MActionSkillHolderCardContentDTO();
        mActionSkillHolderCardContentDTO1.setId(1L);
        MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO2 = new MActionSkillHolderCardContentDTO();
        assertThat(mActionSkillHolderCardContentDTO1).isNotEqualTo(mActionSkillHolderCardContentDTO2);
        mActionSkillHolderCardContentDTO2.setId(mActionSkillHolderCardContentDTO1.getId());
        assertThat(mActionSkillHolderCardContentDTO1).isEqualTo(mActionSkillHolderCardContentDTO2);
        mActionSkillHolderCardContentDTO2.setId(2L);
        assertThat(mActionSkillHolderCardContentDTO1).isNotEqualTo(mActionSkillHolderCardContentDTO2);
        mActionSkillHolderCardContentDTO1.setId(null);
        assertThat(mActionSkillHolderCardContentDTO1).isNotEqualTo(mActionSkillHolderCardContentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mActionSkillHolderCardContentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mActionSkillHolderCardContentMapper.fromId(null)).isNull();
    }
}
