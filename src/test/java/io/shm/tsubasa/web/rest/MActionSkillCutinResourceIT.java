package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MActionSkillCutin;
import io.shm.tsubasa.repository.MActionSkillCutinRepository;
import io.shm.tsubasa.service.MActionSkillCutinService;
import io.shm.tsubasa.service.dto.MActionSkillCutinDTO;
import io.shm.tsubasa.service.mapper.MActionSkillCutinMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MActionSkillCutinCriteria;
import io.shm.tsubasa.service.MActionSkillCutinQueryService;

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
 * Integration tests for the {@Link MActionSkillCutinResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MActionSkillCutinResourceIT {

    private static final Integer DEFAULT_ACTION_CUT_ID = 1;
    private static final Integer UPDATED_ACTION_CUT_ID = 2;

    private static final Integer DEFAULT_CHARACTER_ID = 1;
    private static final Integer UPDATED_CHARACTER_ID = 2;

    private static final String DEFAULT_CUT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_START_SYNCHRONIZE = 1;
    private static final Integer UPDATED_START_SYNCHRONIZE = 2;

    private static final Integer DEFAULT_FINISH_SYNCHRONIZE = 1;
    private static final Integer UPDATED_FINISH_SYNCHRONIZE = 2;

    private static final Integer DEFAULT_START_DELAY = 1;
    private static final Integer UPDATED_START_DELAY = 2;

    private static final Integer DEFAULT_CHAPTER_1_CHARACTER = 1;
    private static final Integer UPDATED_CHAPTER_1_CHARACTER = 2;

    private static final String DEFAULT_CHAPTER_1_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_1_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHAPTER_1_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_1_SOUND_EVENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHAPTER_2_CHARACTER = 1;
    private static final Integer UPDATED_CHAPTER_2_CHARACTER = 2;

    private static final String DEFAULT_CHAPTER_2_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_2_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHAPTER_2_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_2_SOUND_EVENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHAPTER_3_CHARACTER = 1;
    private static final Integer UPDATED_CHAPTER_3_CHARACTER = 2;

    private static final String DEFAULT_CHAPTER_3_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_3_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHAPTER_3_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_3_SOUND_EVENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHAPTER_4_CHARACTER = 1;
    private static final Integer UPDATED_CHAPTER_4_CHARACTER = 2;

    private static final String DEFAULT_CHAPTER_4_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_4_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHAPTER_4_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_4_SOUND_EVENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHAPTER_5_CHARACTER = 1;
    private static final Integer UPDATED_CHAPTER_5_CHARACTER = 2;

    private static final String DEFAULT_CHAPTER_5_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_5_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHAPTER_5_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_CHAPTER_5_SOUND_EVENT = "BBBBBBBBBB";

    private static final String DEFAULT_SYNCHRONIZE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_SYNCHRONIZE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_SYNCHRONIZE_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_SYNCHRONIZE_SOUND_EVENT = "BBBBBBBBBB";

    @Autowired
    private MActionSkillCutinRepository mActionSkillCutinRepository;

    @Autowired
    private MActionSkillCutinMapper mActionSkillCutinMapper;

    @Autowired
    private MActionSkillCutinService mActionSkillCutinService;

    @Autowired
    private MActionSkillCutinQueryService mActionSkillCutinQueryService;

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

    private MockMvc restMActionSkillCutinMockMvc;

    private MActionSkillCutin mActionSkillCutin;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MActionSkillCutinResource mActionSkillCutinResource = new MActionSkillCutinResource(mActionSkillCutinService, mActionSkillCutinQueryService);
        this.restMActionSkillCutinMockMvc = MockMvcBuilders.standaloneSetup(mActionSkillCutinResource)
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
    public static MActionSkillCutin createEntity(EntityManager em) {
        MActionSkillCutin mActionSkillCutin = new MActionSkillCutin()
            .actionCutId(DEFAULT_ACTION_CUT_ID)
            .characterId(DEFAULT_CHARACTER_ID)
            .cutName(DEFAULT_CUT_NAME)
            .type(DEFAULT_TYPE)
            .startSynchronize(DEFAULT_START_SYNCHRONIZE)
            .finishSynchronize(DEFAULT_FINISH_SYNCHRONIZE)
            .startDelay(DEFAULT_START_DELAY)
            .chapter1Character(DEFAULT_CHAPTER_1_CHARACTER)
            .chapter1Text(DEFAULT_CHAPTER_1_TEXT)
            .chapter1SoundEvent(DEFAULT_CHAPTER_1_SOUND_EVENT)
            .chapter2Character(DEFAULT_CHAPTER_2_CHARACTER)
            .chapter2Text(DEFAULT_CHAPTER_2_TEXT)
            .chapter2SoundEvent(DEFAULT_CHAPTER_2_SOUND_EVENT)
            .chapter3Character(DEFAULT_CHAPTER_3_CHARACTER)
            .chapter3Text(DEFAULT_CHAPTER_3_TEXT)
            .chapter3SoundEvent(DEFAULT_CHAPTER_3_SOUND_EVENT)
            .chapter4Character(DEFAULT_CHAPTER_4_CHARACTER)
            .chapter4Text(DEFAULT_CHAPTER_4_TEXT)
            .chapter4SoundEvent(DEFAULT_CHAPTER_4_SOUND_EVENT)
            .chapter5Character(DEFAULT_CHAPTER_5_CHARACTER)
            .chapter5Text(DEFAULT_CHAPTER_5_TEXT)
            .chapter5SoundEvent(DEFAULT_CHAPTER_5_SOUND_EVENT)
            .synchronizeText(DEFAULT_SYNCHRONIZE_TEXT)
            .synchronizeSoundEvent(DEFAULT_SYNCHRONIZE_SOUND_EVENT);
        return mActionSkillCutin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MActionSkillCutin createUpdatedEntity(EntityManager em) {
        MActionSkillCutin mActionSkillCutin = new MActionSkillCutin()
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .characterId(UPDATED_CHARACTER_ID)
            .cutName(UPDATED_CUT_NAME)
            .type(UPDATED_TYPE)
            .startSynchronize(UPDATED_START_SYNCHRONIZE)
            .finishSynchronize(UPDATED_FINISH_SYNCHRONIZE)
            .startDelay(UPDATED_START_DELAY)
            .chapter1Character(UPDATED_CHAPTER_1_CHARACTER)
            .chapter1Text(UPDATED_CHAPTER_1_TEXT)
            .chapter1SoundEvent(UPDATED_CHAPTER_1_SOUND_EVENT)
            .chapter2Character(UPDATED_CHAPTER_2_CHARACTER)
            .chapter2Text(UPDATED_CHAPTER_2_TEXT)
            .chapter2SoundEvent(UPDATED_CHAPTER_2_SOUND_EVENT)
            .chapter3Character(UPDATED_CHAPTER_3_CHARACTER)
            .chapter3Text(UPDATED_CHAPTER_3_TEXT)
            .chapter3SoundEvent(UPDATED_CHAPTER_3_SOUND_EVENT)
            .chapter4Character(UPDATED_CHAPTER_4_CHARACTER)
            .chapter4Text(UPDATED_CHAPTER_4_TEXT)
            .chapter4SoundEvent(UPDATED_CHAPTER_4_SOUND_EVENT)
            .chapter5Character(UPDATED_CHAPTER_5_CHARACTER)
            .chapter5Text(UPDATED_CHAPTER_5_TEXT)
            .chapter5SoundEvent(UPDATED_CHAPTER_5_SOUND_EVENT)
            .synchronizeText(UPDATED_SYNCHRONIZE_TEXT)
            .synchronizeSoundEvent(UPDATED_SYNCHRONIZE_SOUND_EVENT);
        return mActionSkillCutin;
    }

    @BeforeEach
    public void initTest() {
        mActionSkillCutin = createEntity(em);
    }

    @Test
    @Transactional
    public void createMActionSkillCutin() throws Exception {
        int databaseSizeBeforeCreate = mActionSkillCutinRepository.findAll().size();

        // Create the MActionSkillCutin
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(mActionSkillCutin);
        restMActionSkillCutinMockMvc.perform(post("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isCreated());

        // Validate the MActionSkillCutin in the database
        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeCreate + 1);
        MActionSkillCutin testMActionSkillCutin = mActionSkillCutinList.get(mActionSkillCutinList.size() - 1);
        assertThat(testMActionSkillCutin.getActionCutId()).isEqualTo(DEFAULT_ACTION_CUT_ID);
        assertThat(testMActionSkillCutin.getCharacterId()).isEqualTo(DEFAULT_CHARACTER_ID);
        assertThat(testMActionSkillCutin.getCutName()).isEqualTo(DEFAULT_CUT_NAME);
        assertThat(testMActionSkillCutin.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMActionSkillCutin.getStartSynchronize()).isEqualTo(DEFAULT_START_SYNCHRONIZE);
        assertThat(testMActionSkillCutin.getFinishSynchronize()).isEqualTo(DEFAULT_FINISH_SYNCHRONIZE);
        assertThat(testMActionSkillCutin.getStartDelay()).isEqualTo(DEFAULT_START_DELAY);
        assertThat(testMActionSkillCutin.getChapter1Character()).isEqualTo(DEFAULT_CHAPTER_1_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter1Text()).isEqualTo(DEFAULT_CHAPTER_1_TEXT);
        assertThat(testMActionSkillCutin.getChapter1SoundEvent()).isEqualTo(DEFAULT_CHAPTER_1_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getChapter2Character()).isEqualTo(DEFAULT_CHAPTER_2_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter2Text()).isEqualTo(DEFAULT_CHAPTER_2_TEXT);
        assertThat(testMActionSkillCutin.getChapter2SoundEvent()).isEqualTo(DEFAULT_CHAPTER_2_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getChapter3Character()).isEqualTo(DEFAULT_CHAPTER_3_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter3Text()).isEqualTo(DEFAULT_CHAPTER_3_TEXT);
        assertThat(testMActionSkillCutin.getChapter3SoundEvent()).isEqualTo(DEFAULT_CHAPTER_3_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getChapter4Character()).isEqualTo(DEFAULT_CHAPTER_4_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter4Text()).isEqualTo(DEFAULT_CHAPTER_4_TEXT);
        assertThat(testMActionSkillCutin.getChapter4SoundEvent()).isEqualTo(DEFAULT_CHAPTER_4_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getChapter5Character()).isEqualTo(DEFAULT_CHAPTER_5_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter5Text()).isEqualTo(DEFAULT_CHAPTER_5_TEXT);
        assertThat(testMActionSkillCutin.getChapter5SoundEvent()).isEqualTo(DEFAULT_CHAPTER_5_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getSynchronizeText()).isEqualTo(DEFAULT_SYNCHRONIZE_TEXT);
        assertThat(testMActionSkillCutin.getSynchronizeSoundEvent()).isEqualTo(DEFAULT_SYNCHRONIZE_SOUND_EVENT);
    }

    @Test
    @Transactional
    public void createMActionSkillCutinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mActionSkillCutinRepository.findAll().size();

        // Create the MActionSkillCutin with an existing ID
        mActionSkillCutin.setId(1L);
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(mActionSkillCutin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMActionSkillCutinMockMvc.perform(post("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionSkillCutin in the database
        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActionCutIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillCutinRepository.findAll().size();
        // set the field null
        mActionSkillCutin.setActionCutId(null);

        // Create the MActionSkillCutin, which fails.
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(mActionSkillCutin);

        restMActionSkillCutinMockMvc.perform(post("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCharacterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillCutinRepository.findAll().size();
        // set the field null
        mActionSkillCutin.setCharacterId(null);

        // Create the MActionSkillCutin, which fails.
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(mActionSkillCutin);

        restMActionSkillCutinMockMvc.perform(post("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillCutinRepository.findAll().size();
        // set the field null
        mActionSkillCutin.setType(null);

        // Create the MActionSkillCutin, which fails.
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(mActionSkillCutin);

        restMActionSkillCutinMockMvc.perform(post("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartSynchronizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillCutinRepository.findAll().size();
        // set the field null
        mActionSkillCutin.setStartSynchronize(null);

        // Create the MActionSkillCutin, which fails.
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(mActionSkillCutin);

        restMActionSkillCutinMockMvc.perform(post("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFinishSynchronizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillCutinRepository.findAll().size();
        // set the field null
        mActionSkillCutin.setFinishSynchronize(null);

        // Create the MActionSkillCutin, which fails.
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(mActionSkillCutin);

        restMActionSkillCutinMockMvc.perform(post("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDelayIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionSkillCutinRepository.findAll().size();
        // set the field null
        mActionSkillCutin.setStartDelay(null);

        // Create the MActionSkillCutin, which fails.
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(mActionSkillCutin);

        restMActionSkillCutinMockMvc.perform(post("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutins() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList
        restMActionSkillCutinMockMvc.perform(get("/api/m-action-skill-cutins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionSkillCutin.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].cutName").value(hasItem(DEFAULT_CUT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].startSynchronize").value(hasItem(DEFAULT_START_SYNCHRONIZE)))
            .andExpect(jsonPath("$.[*].finishSynchronize").value(hasItem(DEFAULT_FINISH_SYNCHRONIZE)))
            .andExpect(jsonPath("$.[*].startDelay").value(hasItem(DEFAULT_START_DELAY)))
            .andExpect(jsonPath("$.[*].chapter1Character").value(hasItem(DEFAULT_CHAPTER_1_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter1Text").value(hasItem(DEFAULT_CHAPTER_1_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter1SoundEvent").value(hasItem(DEFAULT_CHAPTER_1_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter2Character").value(hasItem(DEFAULT_CHAPTER_2_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter2Text").value(hasItem(DEFAULT_CHAPTER_2_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter2SoundEvent").value(hasItem(DEFAULT_CHAPTER_2_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter3Character").value(hasItem(DEFAULT_CHAPTER_3_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter3Text").value(hasItem(DEFAULT_CHAPTER_3_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter3SoundEvent").value(hasItem(DEFAULT_CHAPTER_3_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter4Character").value(hasItem(DEFAULT_CHAPTER_4_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter4Text").value(hasItem(DEFAULT_CHAPTER_4_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter4SoundEvent").value(hasItem(DEFAULT_CHAPTER_4_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter5Character").value(hasItem(DEFAULT_CHAPTER_5_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter5Text").value(hasItem(DEFAULT_CHAPTER_5_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter5SoundEvent").value(hasItem(DEFAULT_CHAPTER_5_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].synchronizeText").value(hasItem(DEFAULT_SYNCHRONIZE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].synchronizeSoundEvent").value(hasItem(DEFAULT_SYNCHRONIZE_SOUND_EVENT.toString())));
    }
    
    @Test
    @Transactional
    public void getMActionSkillCutin() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get the mActionSkillCutin
        restMActionSkillCutinMockMvc.perform(get("/api/m-action-skill-cutins/{id}", mActionSkillCutin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mActionSkillCutin.getId().intValue()))
            .andExpect(jsonPath("$.actionCutId").value(DEFAULT_ACTION_CUT_ID))
            .andExpect(jsonPath("$.characterId").value(DEFAULT_CHARACTER_ID))
            .andExpect(jsonPath("$.cutName").value(DEFAULT_CUT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.startSynchronize").value(DEFAULT_START_SYNCHRONIZE))
            .andExpect(jsonPath("$.finishSynchronize").value(DEFAULT_FINISH_SYNCHRONIZE))
            .andExpect(jsonPath("$.startDelay").value(DEFAULT_START_DELAY))
            .andExpect(jsonPath("$.chapter1Character").value(DEFAULT_CHAPTER_1_CHARACTER))
            .andExpect(jsonPath("$.chapter1Text").value(DEFAULT_CHAPTER_1_TEXT.toString()))
            .andExpect(jsonPath("$.chapter1SoundEvent").value(DEFAULT_CHAPTER_1_SOUND_EVENT.toString()))
            .andExpect(jsonPath("$.chapter2Character").value(DEFAULT_CHAPTER_2_CHARACTER))
            .andExpect(jsonPath("$.chapter2Text").value(DEFAULT_CHAPTER_2_TEXT.toString()))
            .andExpect(jsonPath("$.chapter2SoundEvent").value(DEFAULT_CHAPTER_2_SOUND_EVENT.toString()))
            .andExpect(jsonPath("$.chapter3Character").value(DEFAULT_CHAPTER_3_CHARACTER))
            .andExpect(jsonPath("$.chapter3Text").value(DEFAULT_CHAPTER_3_TEXT.toString()))
            .andExpect(jsonPath("$.chapter3SoundEvent").value(DEFAULT_CHAPTER_3_SOUND_EVENT.toString()))
            .andExpect(jsonPath("$.chapter4Character").value(DEFAULT_CHAPTER_4_CHARACTER))
            .andExpect(jsonPath("$.chapter4Text").value(DEFAULT_CHAPTER_4_TEXT.toString()))
            .andExpect(jsonPath("$.chapter4SoundEvent").value(DEFAULT_CHAPTER_4_SOUND_EVENT.toString()))
            .andExpect(jsonPath("$.chapter5Character").value(DEFAULT_CHAPTER_5_CHARACTER))
            .andExpect(jsonPath("$.chapter5Text").value(DEFAULT_CHAPTER_5_TEXT.toString()))
            .andExpect(jsonPath("$.chapter5SoundEvent").value(DEFAULT_CHAPTER_5_SOUND_EVENT.toString()))
            .andExpect(jsonPath("$.synchronizeText").value(DEFAULT_SYNCHRONIZE_TEXT.toString()))
            .andExpect(jsonPath("$.synchronizeSoundEvent").value(DEFAULT_SYNCHRONIZE_SOUND_EVENT.toString()));
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByActionCutIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where actionCutId equals to DEFAULT_ACTION_CUT_ID
        defaultMActionSkillCutinShouldBeFound("actionCutId.equals=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mActionSkillCutinList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMActionSkillCutinShouldNotBeFound("actionCutId.equals=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByActionCutIdIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where actionCutId in DEFAULT_ACTION_CUT_ID or UPDATED_ACTION_CUT_ID
        defaultMActionSkillCutinShouldBeFound("actionCutId.in=" + DEFAULT_ACTION_CUT_ID + "," + UPDATED_ACTION_CUT_ID);

        // Get all the mActionSkillCutinList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMActionSkillCutinShouldNotBeFound("actionCutId.in=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByActionCutIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where actionCutId is not null
        defaultMActionSkillCutinShouldBeFound("actionCutId.specified=true");

        // Get all the mActionSkillCutinList where actionCutId is null
        defaultMActionSkillCutinShouldNotBeFound("actionCutId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByActionCutIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where actionCutId greater than or equals to DEFAULT_ACTION_CUT_ID
        defaultMActionSkillCutinShouldBeFound("actionCutId.greaterOrEqualThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mActionSkillCutinList where actionCutId greater than or equals to UPDATED_ACTION_CUT_ID
        defaultMActionSkillCutinShouldNotBeFound("actionCutId.greaterOrEqualThan=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByActionCutIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where actionCutId less than or equals to DEFAULT_ACTION_CUT_ID
        defaultMActionSkillCutinShouldNotBeFound("actionCutId.lessThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mActionSkillCutinList where actionCutId less than or equals to UPDATED_ACTION_CUT_ID
        defaultMActionSkillCutinShouldBeFound("actionCutId.lessThan=" + UPDATED_ACTION_CUT_ID);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where characterId equals to DEFAULT_CHARACTER_ID
        defaultMActionSkillCutinShouldBeFound("characterId.equals=" + DEFAULT_CHARACTER_ID);

        // Get all the mActionSkillCutinList where characterId equals to UPDATED_CHARACTER_ID
        defaultMActionSkillCutinShouldNotBeFound("characterId.equals=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where characterId in DEFAULT_CHARACTER_ID or UPDATED_CHARACTER_ID
        defaultMActionSkillCutinShouldBeFound("characterId.in=" + DEFAULT_CHARACTER_ID + "," + UPDATED_CHARACTER_ID);

        // Get all the mActionSkillCutinList where characterId equals to UPDATED_CHARACTER_ID
        defaultMActionSkillCutinShouldNotBeFound("characterId.in=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where characterId is not null
        defaultMActionSkillCutinShouldBeFound("characterId.specified=true");

        // Get all the mActionSkillCutinList where characterId is null
        defaultMActionSkillCutinShouldNotBeFound("characterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where characterId greater than or equals to DEFAULT_CHARACTER_ID
        defaultMActionSkillCutinShouldBeFound("characterId.greaterOrEqualThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mActionSkillCutinList where characterId greater than or equals to UPDATED_CHARACTER_ID
        defaultMActionSkillCutinShouldNotBeFound("characterId.greaterOrEqualThan=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where characterId less than or equals to DEFAULT_CHARACTER_ID
        defaultMActionSkillCutinShouldNotBeFound("characterId.lessThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mActionSkillCutinList where characterId less than or equals to UPDATED_CHARACTER_ID
        defaultMActionSkillCutinShouldBeFound("characterId.lessThan=" + UPDATED_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where type equals to DEFAULT_TYPE
        defaultMActionSkillCutinShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the mActionSkillCutinList where type equals to UPDATED_TYPE
        defaultMActionSkillCutinShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultMActionSkillCutinShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the mActionSkillCutinList where type equals to UPDATED_TYPE
        defaultMActionSkillCutinShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where type is not null
        defaultMActionSkillCutinShouldBeFound("type.specified=true");

        // Get all the mActionSkillCutinList where type is null
        defaultMActionSkillCutinShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where type greater than or equals to DEFAULT_TYPE
        defaultMActionSkillCutinShouldBeFound("type.greaterOrEqualThan=" + DEFAULT_TYPE);

        // Get all the mActionSkillCutinList where type greater than or equals to UPDATED_TYPE
        defaultMActionSkillCutinShouldNotBeFound("type.greaterOrEqualThan=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where type less than or equals to DEFAULT_TYPE
        defaultMActionSkillCutinShouldNotBeFound("type.lessThan=" + DEFAULT_TYPE);

        // Get all the mActionSkillCutinList where type less than or equals to UPDATED_TYPE
        defaultMActionSkillCutinShouldBeFound("type.lessThan=" + UPDATED_TYPE);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartSynchronizeIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startSynchronize equals to DEFAULT_START_SYNCHRONIZE
        defaultMActionSkillCutinShouldBeFound("startSynchronize.equals=" + DEFAULT_START_SYNCHRONIZE);

        // Get all the mActionSkillCutinList where startSynchronize equals to UPDATED_START_SYNCHRONIZE
        defaultMActionSkillCutinShouldNotBeFound("startSynchronize.equals=" + UPDATED_START_SYNCHRONIZE);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartSynchronizeIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startSynchronize in DEFAULT_START_SYNCHRONIZE or UPDATED_START_SYNCHRONIZE
        defaultMActionSkillCutinShouldBeFound("startSynchronize.in=" + DEFAULT_START_SYNCHRONIZE + "," + UPDATED_START_SYNCHRONIZE);

        // Get all the mActionSkillCutinList where startSynchronize equals to UPDATED_START_SYNCHRONIZE
        defaultMActionSkillCutinShouldNotBeFound("startSynchronize.in=" + UPDATED_START_SYNCHRONIZE);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartSynchronizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startSynchronize is not null
        defaultMActionSkillCutinShouldBeFound("startSynchronize.specified=true");

        // Get all the mActionSkillCutinList where startSynchronize is null
        defaultMActionSkillCutinShouldNotBeFound("startSynchronize.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartSynchronizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startSynchronize greater than or equals to DEFAULT_START_SYNCHRONIZE
        defaultMActionSkillCutinShouldBeFound("startSynchronize.greaterOrEqualThan=" + DEFAULT_START_SYNCHRONIZE);

        // Get all the mActionSkillCutinList where startSynchronize greater than or equals to UPDATED_START_SYNCHRONIZE
        defaultMActionSkillCutinShouldNotBeFound("startSynchronize.greaterOrEqualThan=" + UPDATED_START_SYNCHRONIZE);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartSynchronizeIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startSynchronize less than or equals to DEFAULT_START_SYNCHRONIZE
        defaultMActionSkillCutinShouldNotBeFound("startSynchronize.lessThan=" + DEFAULT_START_SYNCHRONIZE);

        // Get all the mActionSkillCutinList where startSynchronize less than or equals to UPDATED_START_SYNCHRONIZE
        defaultMActionSkillCutinShouldBeFound("startSynchronize.lessThan=" + UPDATED_START_SYNCHRONIZE);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByFinishSynchronizeIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where finishSynchronize equals to DEFAULT_FINISH_SYNCHRONIZE
        defaultMActionSkillCutinShouldBeFound("finishSynchronize.equals=" + DEFAULT_FINISH_SYNCHRONIZE);

        // Get all the mActionSkillCutinList where finishSynchronize equals to UPDATED_FINISH_SYNCHRONIZE
        defaultMActionSkillCutinShouldNotBeFound("finishSynchronize.equals=" + UPDATED_FINISH_SYNCHRONIZE);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByFinishSynchronizeIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where finishSynchronize in DEFAULT_FINISH_SYNCHRONIZE or UPDATED_FINISH_SYNCHRONIZE
        defaultMActionSkillCutinShouldBeFound("finishSynchronize.in=" + DEFAULT_FINISH_SYNCHRONIZE + "," + UPDATED_FINISH_SYNCHRONIZE);

        // Get all the mActionSkillCutinList where finishSynchronize equals to UPDATED_FINISH_SYNCHRONIZE
        defaultMActionSkillCutinShouldNotBeFound("finishSynchronize.in=" + UPDATED_FINISH_SYNCHRONIZE);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByFinishSynchronizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where finishSynchronize is not null
        defaultMActionSkillCutinShouldBeFound("finishSynchronize.specified=true");

        // Get all the mActionSkillCutinList where finishSynchronize is null
        defaultMActionSkillCutinShouldNotBeFound("finishSynchronize.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByFinishSynchronizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where finishSynchronize greater than or equals to DEFAULT_FINISH_SYNCHRONIZE
        defaultMActionSkillCutinShouldBeFound("finishSynchronize.greaterOrEqualThan=" + DEFAULT_FINISH_SYNCHRONIZE);

        // Get all the mActionSkillCutinList where finishSynchronize greater than or equals to UPDATED_FINISH_SYNCHRONIZE
        defaultMActionSkillCutinShouldNotBeFound("finishSynchronize.greaterOrEqualThan=" + UPDATED_FINISH_SYNCHRONIZE);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByFinishSynchronizeIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where finishSynchronize less than or equals to DEFAULT_FINISH_SYNCHRONIZE
        defaultMActionSkillCutinShouldNotBeFound("finishSynchronize.lessThan=" + DEFAULT_FINISH_SYNCHRONIZE);

        // Get all the mActionSkillCutinList where finishSynchronize less than or equals to UPDATED_FINISH_SYNCHRONIZE
        defaultMActionSkillCutinShouldBeFound("finishSynchronize.lessThan=" + UPDATED_FINISH_SYNCHRONIZE);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartDelayIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startDelay equals to DEFAULT_START_DELAY
        defaultMActionSkillCutinShouldBeFound("startDelay.equals=" + DEFAULT_START_DELAY);

        // Get all the mActionSkillCutinList where startDelay equals to UPDATED_START_DELAY
        defaultMActionSkillCutinShouldNotBeFound("startDelay.equals=" + UPDATED_START_DELAY);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartDelayIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startDelay in DEFAULT_START_DELAY or UPDATED_START_DELAY
        defaultMActionSkillCutinShouldBeFound("startDelay.in=" + DEFAULT_START_DELAY + "," + UPDATED_START_DELAY);

        // Get all the mActionSkillCutinList where startDelay equals to UPDATED_START_DELAY
        defaultMActionSkillCutinShouldNotBeFound("startDelay.in=" + UPDATED_START_DELAY);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartDelayIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startDelay is not null
        defaultMActionSkillCutinShouldBeFound("startDelay.specified=true");

        // Get all the mActionSkillCutinList where startDelay is null
        defaultMActionSkillCutinShouldNotBeFound("startDelay.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartDelayIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startDelay greater than or equals to DEFAULT_START_DELAY
        defaultMActionSkillCutinShouldBeFound("startDelay.greaterOrEqualThan=" + DEFAULT_START_DELAY);

        // Get all the mActionSkillCutinList where startDelay greater than or equals to UPDATED_START_DELAY
        defaultMActionSkillCutinShouldNotBeFound("startDelay.greaterOrEqualThan=" + UPDATED_START_DELAY);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByStartDelayIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where startDelay less than or equals to DEFAULT_START_DELAY
        defaultMActionSkillCutinShouldNotBeFound("startDelay.lessThan=" + DEFAULT_START_DELAY);

        // Get all the mActionSkillCutinList where startDelay less than or equals to UPDATED_START_DELAY
        defaultMActionSkillCutinShouldBeFound("startDelay.lessThan=" + UPDATED_START_DELAY);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter1CharacterIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter1Character equals to DEFAULT_CHAPTER_1_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter1Character.equals=" + DEFAULT_CHAPTER_1_CHARACTER);

        // Get all the mActionSkillCutinList where chapter1Character equals to UPDATED_CHAPTER_1_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter1Character.equals=" + UPDATED_CHAPTER_1_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter1CharacterIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter1Character in DEFAULT_CHAPTER_1_CHARACTER or UPDATED_CHAPTER_1_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter1Character.in=" + DEFAULT_CHAPTER_1_CHARACTER + "," + UPDATED_CHAPTER_1_CHARACTER);

        // Get all the mActionSkillCutinList where chapter1Character equals to UPDATED_CHAPTER_1_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter1Character.in=" + UPDATED_CHAPTER_1_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter1CharacterIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter1Character is not null
        defaultMActionSkillCutinShouldBeFound("chapter1Character.specified=true");

        // Get all the mActionSkillCutinList where chapter1Character is null
        defaultMActionSkillCutinShouldNotBeFound("chapter1Character.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter1CharacterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter1Character greater than or equals to DEFAULT_CHAPTER_1_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter1Character.greaterOrEqualThan=" + DEFAULT_CHAPTER_1_CHARACTER);

        // Get all the mActionSkillCutinList where chapter1Character greater than or equals to UPDATED_CHAPTER_1_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter1Character.greaterOrEqualThan=" + UPDATED_CHAPTER_1_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter1CharacterIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter1Character less than or equals to DEFAULT_CHAPTER_1_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter1Character.lessThan=" + DEFAULT_CHAPTER_1_CHARACTER);

        // Get all the mActionSkillCutinList where chapter1Character less than or equals to UPDATED_CHAPTER_1_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter1Character.lessThan=" + UPDATED_CHAPTER_1_CHARACTER);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter2CharacterIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter2Character equals to DEFAULT_CHAPTER_2_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter2Character.equals=" + DEFAULT_CHAPTER_2_CHARACTER);

        // Get all the mActionSkillCutinList where chapter2Character equals to UPDATED_CHAPTER_2_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter2Character.equals=" + UPDATED_CHAPTER_2_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter2CharacterIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter2Character in DEFAULT_CHAPTER_2_CHARACTER or UPDATED_CHAPTER_2_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter2Character.in=" + DEFAULT_CHAPTER_2_CHARACTER + "," + UPDATED_CHAPTER_2_CHARACTER);

        // Get all the mActionSkillCutinList where chapter2Character equals to UPDATED_CHAPTER_2_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter2Character.in=" + UPDATED_CHAPTER_2_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter2CharacterIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter2Character is not null
        defaultMActionSkillCutinShouldBeFound("chapter2Character.specified=true");

        // Get all the mActionSkillCutinList where chapter2Character is null
        defaultMActionSkillCutinShouldNotBeFound("chapter2Character.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter2CharacterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter2Character greater than or equals to DEFAULT_CHAPTER_2_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter2Character.greaterOrEqualThan=" + DEFAULT_CHAPTER_2_CHARACTER);

        // Get all the mActionSkillCutinList where chapter2Character greater than or equals to UPDATED_CHAPTER_2_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter2Character.greaterOrEqualThan=" + UPDATED_CHAPTER_2_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter2CharacterIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter2Character less than or equals to DEFAULT_CHAPTER_2_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter2Character.lessThan=" + DEFAULT_CHAPTER_2_CHARACTER);

        // Get all the mActionSkillCutinList where chapter2Character less than or equals to UPDATED_CHAPTER_2_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter2Character.lessThan=" + UPDATED_CHAPTER_2_CHARACTER);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter3CharacterIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter3Character equals to DEFAULT_CHAPTER_3_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter3Character.equals=" + DEFAULT_CHAPTER_3_CHARACTER);

        // Get all the mActionSkillCutinList where chapter3Character equals to UPDATED_CHAPTER_3_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter3Character.equals=" + UPDATED_CHAPTER_3_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter3CharacterIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter3Character in DEFAULT_CHAPTER_3_CHARACTER or UPDATED_CHAPTER_3_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter3Character.in=" + DEFAULT_CHAPTER_3_CHARACTER + "," + UPDATED_CHAPTER_3_CHARACTER);

        // Get all the mActionSkillCutinList where chapter3Character equals to UPDATED_CHAPTER_3_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter3Character.in=" + UPDATED_CHAPTER_3_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter3CharacterIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter3Character is not null
        defaultMActionSkillCutinShouldBeFound("chapter3Character.specified=true");

        // Get all the mActionSkillCutinList where chapter3Character is null
        defaultMActionSkillCutinShouldNotBeFound("chapter3Character.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter3CharacterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter3Character greater than or equals to DEFAULT_CHAPTER_3_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter3Character.greaterOrEqualThan=" + DEFAULT_CHAPTER_3_CHARACTER);

        // Get all the mActionSkillCutinList where chapter3Character greater than or equals to UPDATED_CHAPTER_3_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter3Character.greaterOrEqualThan=" + UPDATED_CHAPTER_3_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter3CharacterIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter3Character less than or equals to DEFAULT_CHAPTER_3_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter3Character.lessThan=" + DEFAULT_CHAPTER_3_CHARACTER);

        // Get all the mActionSkillCutinList where chapter3Character less than or equals to UPDATED_CHAPTER_3_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter3Character.lessThan=" + UPDATED_CHAPTER_3_CHARACTER);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter4CharacterIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter4Character equals to DEFAULT_CHAPTER_4_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter4Character.equals=" + DEFAULT_CHAPTER_4_CHARACTER);

        // Get all the mActionSkillCutinList where chapter4Character equals to UPDATED_CHAPTER_4_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter4Character.equals=" + UPDATED_CHAPTER_4_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter4CharacterIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter4Character in DEFAULT_CHAPTER_4_CHARACTER or UPDATED_CHAPTER_4_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter4Character.in=" + DEFAULT_CHAPTER_4_CHARACTER + "," + UPDATED_CHAPTER_4_CHARACTER);

        // Get all the mActionSkillCutinList where chapter4Character equals to UPDATED_CHAPTER_4_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter4Character.in=" + UPDATED_CHAPTER_4_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter4CharacterIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter4Character is not null
        defaultMActionSkillCutinShouldBeFound("chapter4Character.specified=true");

        // Get all the mActionSkillCutinList where chapter4Character is null
        defaultMActionSkillCutinShouldNotBeFound("chapter4Character.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter4CharacterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter4Character greater than or equals to DEFAULT_CHAPTER_4_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter4Character.greaterOrEqualThan=" + DEFAULT_CHAPTER_4_CHARACTER);

        // Get all the mActionSkillCutinList where chapter4Character greater than or equals to UPDATED_CHAPTER_4_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter4Character.greaterOrEqualThan=" + UPDATED_CHAPTER_4_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter4CharacterIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter4Character less than or equals to DEFAULT_CHAPTER_4_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter4Character.lessThan=" + DEFAULT_CHAPTER_4_CHARACTER);

        // Get all the mActionSkillCutinList where chapter4Character less than or equals to UPDATED_CHAPTER_4_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter4Character.lessThan=" + UPDATED_CHAPTER_4_CHARACTER);
    }


    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter5CharacterIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter5Character equals to DEFAULT_CHAPTER_5_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter5Character.equals=" + DEFAULT_CHAPTER_5_CHARACTER);

        // Get all the mActionSkillCutinList where chapter5Character equals to UPDATED_CHAPTER_5_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter5Character.equals=" + UPDATED_CHAPTER_5_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter5CharacterIsInShouldWork() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter5Character in DEFAULT_CHAPTER_5_CHARACTER or UPDATED_CHAPTER_5_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter5Character.in=" + DEFAULT_CHAPTER_5_CHARACTER + "," + UPDATED_CHAPTER_5_CHARACTER);

        // Get all the mActionSkillCutinList where chapter5Character equals to UPDATED_CHAPTER_5_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter5Character.in=" + UPDATED_CHAPTER_5_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter5CharacterIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter5Character is not null
        defaultMActionSkillCutinShouldBeFound("chapter5Character.specified=true");

        // Get all the mActionSkillCutinList where chapter5Character is null
        defaultMActionSkillCutinShouldNotBeFound("chapter5Character.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter5CharacterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter5Character greater than or equals to DEFAULT_CHAPTER_5_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter5Character.greaterOrEqualThan=" + DEFAULT_CHAPTER_5_CHARACTER);

        // Get all the mActionSkillCutinList where chapter5Character greater than or equals to UPDATED_CHAPTER_5_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter5Character.greaterOrEqualThan=" + UPDATED_CHAPTER_5_CHARACTER);
    }

    @Test
    @Transactional
    public void getAllMActionSkillCutinsByChapter5CharacterIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        // Get all the mActionSkillCutinList where chapter5Character less than or equals to DEFAULT_CHAPTER_5_CHARACTER
        defaultMActionSkillCutinShouldNotBeFound("chapter5Character.lessThan=" + DEFAULT_CHAPTER_5_CHARACTER);

        // Get all the mActionSkillCutinList where chapter5Character less than or equals to UPDATED_CHAPTER_5_CHARACTER
        defaultMActionSkillCutinShouldBeFound("chapter5Character.lessThan=" + UPDATED_CHAPTER_5_CHARACTER);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMActionSkillCutinShouldBeFound(String filter) throws Exception {
        restMActionSkillCutinMockMvc.perform(get("/api/m-action-skill-cutins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionSkillCutin.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].cutName").value(hasItem(DEFAULT_CUT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].startSynchronize").value(hasItem(DEFAULT_START_SYNCHRONIZE)))
            .andExpect(jsonPath("$.[*].finishSynchronize").value(hasItem(DEFAULT_FINISH_SYNCHRONIZE)))
            .andExpect(jsonPath("$.[*].startDelay").value(hasItem(DEFAULT_START_DELAY)))
            .andExpect(jsonPath("$.[*].chapter1Character").value(hasItem(DEFAULT_CHAPTER_1_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter1Text").value(hasItem(DEFAULT_CHAPTER_1_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter1SoundEvent").value(hasItem(DEFAULT_CHAPTER_1_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter2Character").value(hasItem(DEFAULT_CHAPTER_2_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter2Text").value(hasItem(DEFAULT_CHAPTER_2_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter2SoundEvent").value(hasItem(DEFAULT_CHAPTER_2_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter3Character").value(hasItem(DEFAULT_CHAPTER_3_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter3Text").value(hasItem(DEFAULT_CHAPTER_3_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter3SoundEvent").value(hasItem(DEFAULT_CHAPTER_3_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter4Character").value(hasItem(DEFAULT_CHAPTER_4_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter4Text").value(hasItem(DEFAULT_CHAPTER_4_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter4SoundEvent").value(hasItem(DEFAULT_CHAPTER_4_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].chapter5Character").value(hasItem(DEFAULT_CHAPTER_5_CHARACTER)))
            .andExpect(jsonPath("$.[*].chapter5Text").value(hasItem(DEFAULT_CHAPTER_5_TEXT.toString())))
            .andExpect(jsonPath("$.[*].chapter5SoundEvent").value(hasItem(DEFAULT_CHAPTER_5_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].synchronizeText").value(hasItem(DEFAULT_SYNCHRONIZE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].synchronizeSoundEvent").value(hasItem(DEFAULT_SYNCHRONIZE_SOUND_EVENT.toString())));

        // Check, that the count call also returns 1
        restMActionSkillCutinMockMvc.perform(get("/api/m-action-skill-cutins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMActionSkillCutinShouldNotBeFound(String filter) throws Exception {
        restMActionSkillCutinMockMvc.perform(get("/api/m-action-skill-cutins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMActionSkillCutinMockMvc.perform(get("/api/m-action-skill-cutins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMActionSkillCutin() throws Exception {
        // Get the mActionSkillCutin
        restMActionSkillCutinMockMvc.perform(get("/api/m-action-skill-cutins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMActionSkillCutin() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        int databaseSizeBeforeUpdate = mActionSkillCutinRepository.findAll().size();

        // Update the mActionSkillCutin
        MActionSkillCutin updatedMActionSkillCutin = mActionSkillCutinRepository.findById(mActionSkillCutin.getId()).get();
        // Disconnect from session so that the updates on updatedMActionSkillCutin are not directly saved in db
        em.detach(updatedMActionSkillCutin);
        updatedMActionSkillCutin
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .characterId(UPDATED_CHARACTER_ID)
            .cutName(UPDATED_CUT_NAME)
            .type(UPDATED_TYPE)
            .startSynchronize(UPDATED_START_SYNCHRONIZE)
            .finishSynchronize(UPDATED_FINISH_SYNCHRONIZE)
            .startDelay(UPDATED_START_DELAY)
            .chapter1Character(UPDATED_CHAPTER_1_CHARACTER)
            .chapter1Text(UPDATED_CHAPTER_1_TEXT)
            .chapter1SoundEvent(UPDATED_CHAPTER_1_SOUND_EVENT)
            .chapter2Character(UPDATED_CHAPTER_2_CHARACTER)
            .chapter2Text(UPDATED_CHAPTER_2_TEXT)
            .chapter2SoundEvent(UPDATED_CHAPTER_2_SOUND_EVENT)
            .chapter3Character(UPDATED_CHAPTER_3_CHARACTER)
            .chapter3Text(UPDATED_CHAPTER_3_TEXT)
            .chapter3SoundEvent(UPDATED_CHAPTER_3_SOUND_EVENT)
            .chapter4Character(UPDATED_CHAPTER_4_CHARACTER)
            .chapter4Text(UPDATED_CHAPTER_4_TEXT)
            .chapter4SoundEvent(UPDATED_CHAPTER_4_SOUND_EVENT)
            .chapter5Character(UPDATED_CHAPTER_5_CHARACTER)
            .chapter5Text(UPDATED_CHAPTER_5_TEXT)
            .chapter5SoundEvent(UPDATED_CHAPTER_5_SOUND_EVENT)
            .synchronizeText(UPDATED_SYNCHRONIZE_TEXT)
            .synchronizeSoundEvent(UPDATED_SYNCHRONIZE_SOUND_EVENT);
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(updatedMActionSkillCutin);

        restMActionSkillCutinMockMvc.perform(put("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isOk());

        // Validate the MActionSkillCutin in the database
        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeUpdate);
        MActionSkillCutin testMActionSkillCutin = mActionSkillCutinList.get(mActionSkillCutinList.size() - 1);
        assertThat(testMActionSkillCutin.getActionCutId()).isEqualTo(UPDATED_ACTION_CUT_ID);
        assertThat(testMActionSkillCutin.getCharacterId()).isEqualTo(UPDATED_CHARACTER_ID);
        assertThat(testMActionSkillCutin.getCutName()).isEqualTo(UPDATED_CUT_NAME);
        assertThat(testMActionSkillCutin.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMActionSkillCutin.getStartSynchronize()).isEqualTo(UPDATED_START_SYNCHRONIZE);
        assertThat(testMActionSkillCutin.getFinishSynchronize()).isEqualTo(UPDATED_FINISH_SYNCHRONIZE);
        assertThat(testMActionSkillCutin.getStartDelay()).isEqualTo(UPDATED_START_DELAY);
        assertThat(testMActionSkillCutin.getChapter1Character()).isEqualTo(UPDATED_CHAPTER_1_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter1Text()).isEqualTo(UPDATED_CHAPTER_1_TEXT);
        assertThat(testMActionSkillCutin.getChapter1SoundEvent()).isEqualTo(UPDATED_CHAPTER_1_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getChapter2Character()).isEqualTo(UPDATED_CHAPTER_2_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter2Text()).isEqualTo(UPDATED_CHAPTER_2_TEXT);
        assertThat(testMActionSkillCutin.getChapter2SoundEvent()).isEqualTo(UPDATED_CHAPTER_2_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getChapter3Character()).isEqualTo(UPDATED_CHAPTER_3_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter3Text()).isEqualTo(UPDATED_CHAPTER_3_TEXT);
        assertThat(testMActionSkillCutin.getChapter3SoundEvent()).isEqualTo(UPDATED_CHAPTER_3_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getChapter4Character()).isEqualTo(UPDATED_CHAPTER_4_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter4Text()).isEqualTo(UPDATED_CHAPTER_4_TEXT);
        assertThat(testMActionSkillCutin.getChapter4SoundEvent()).isEqualTo(UPDATED_CHAPTER_4_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getChapter5Character()).isEqualTo(UPDATED_CHAPTER_5_CHARACTER);
        assertThat(testMActionSkillCutin.getChapter5Text()).isEqualTo(UPDATED_CHAPTER_5_TEXT);
        assertThat(testMActionSkillCutin.getChapter5SoundEvent()).isEqualTo(UPDATED_CHAPTER_5_SOUND_EVENT);
        assertThat(testMActionSkillCutin.getSynchronizeText()).isEqualTo(UPDATED_SYNCHRONIZE_TEXT);
        assertThat(testMActionSkillCutin.getSynchronizeSoundEvent()).isEqualTo(UPDATED_SYNCHRONIZE_SOUND_EVENT);
    }

    @Test
    @Transactional
    public void updateNonExistingMActionSkillCutin() throws Exception {
        int databaseSizeBeforeUpdate = mActionSkillCutinRepository.findAll().size();

        // Create the MActionSkillCutin
        MActionSkillCutinDTO mActionSkillCutinDTO = mActionSkillCutinMapper.toDto(mActionSkillCutin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMActionSkillCutinMockMvc.perform(put("/api/m-action-skill-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionSkillCutinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionSkillCutin in the database
        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMActionSkillCutin() throws Exception {
        // Initialize the database
        mActionSkillCutinRepository.saveAndFlush(mActionSkillCutin);

        int databaseSizeBeforeDelete = mActionSkillCutinRepository.findAll().size();

        // Delete the mActionSkillCutin
        restMActionSkillCutinMockMvc.perform(delete("/api/m-action-skill-cutins/{id}", mActionSkillCutin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MActionSkillCutin> mActionSkillCutinList = mActionSkillCutinRepository.findAll();
        assertThat(mActionSkillCutinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionSkillCutin.class);
        MActionSkillCutin mActionSkillCutin1 = new MActionSkillCutin();
        mActionSkillCutin1.setId(1L);
        MActionSkillCutin mActionSkillCutin2 = new MActionSkillCutin();
        mActionSkillCutin2.setId(mActionSkillCutin1.getId());
        assertThat(mActionSkillCutin1).isEqualTo(mActionSkillCutin2);
        mActionSkillCutin2.setId(2L);
        assertThat(mActionSkillCutin1).isNotEqualTo(mActionSkillCutin2);
        mActionSkillCutin1.setId(null);
        assertThat(mActionSkillCutin1).isNotEqualTo(mActionSkillCutin2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionSkillCutinDTO.class);
        MActionSkillCutinDTO mActionSkillCutinDTO1 = new MActionSkillCutinDTO();
        mActionSkillCutinDTO1.setId(1L);
        MActionSkillCutinDTO mActionSkillCutinDTO2 = new MActionSkillCutinDTO();
        assertThat(mActionSkillCutinDTO1).isNotEqualTo(mActionSkillCutinDTO2);
        mActionSkillCutinDTO2.setId(mActionSkillCutinDTO1.getId());
        assertThat(mActionSkillCutinDTO1).isEqualTo(mActionSkillCutinDTO2);
        mActionSkillCutinDTO2.setId(2L);
        assertThat(mActionSkillCutinDTO1).isNotEqualTo(mActionSkillCutinDTO2);
        mActionSkillCutinDTO1.setId(null);
        assertThat(mActionSkillCutinDTO1).isNotEqualTo(mActionSkillCutinDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mActionSkillCutinMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mActionSkillCutinMapper.fromId(null)).isNull();
    }
}
