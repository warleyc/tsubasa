package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionKicker;
import io.shm.tsubasa.repository.MGachaRenditionKickerRepository;
import io.shm.tsubasa.service.MGachaRenditionKickerService;
import io.shm.tsubasa.service.dto.MGachaRenditionKickerDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionKickerMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionKickerCriteria;
import io.shm.tsubasa.service.MGachaRenditionKickerQueryService;

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
 * Integration tests for the {@Link MGachaRenditionKickerResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionKickerResourceIT {

    private static final Integer DEFAULT_RENDITION_ID = 1;
    private static final Integer UPDATED_RENDITION_ID = 2;

    private static final Integer DEFAULT_IS_MANY_SSR = 1;
    private static final Integer UPDATED_IS_MANY_SSR = 2;

    private static final Integer DEFAULT_IS_SSR = 1;
    private static final Integer UPDATED_IS_SSR = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Integer DEFAULT_LEFT_MODEL_ID = 1;
    private static final Integer UPDATED_LEFT_MODEL_ID = 2;

    private static final Integer DEFAULT_LEFT_UNIFORM_UP_ID = 1;
    private static final Integer UPDATED_LEFT_UNIFORM_UP_ID = 2;

    private static final Integer DEFAULT_LEFT_UNIFORM_BOTTOM_ID = 1;
    private static final Integer UPDATED_LEFT_UNIFORM_BOTTOM_ID = 2;

    private static final Integer DEFAULT_LEFT_UNIFORM_NUMBER = 1;
    private static final Integer UPDATED_LEFT_UNIFORM_NUMBER = 2;

    private static final Integer DEFAULT_RIGHT_MODEL_ID = 1;
    private static final Integer UPDATED_RIGHT_MODEL_ID = 2;

    private static final Integer DEFAULT_RIGHT_UNIFORM_UP_ID = 1;
    private static final Integer UPDATED_RIGHT_UNIFORM_UP_ID = 2;

    private static final Integer DEFAULT_RIGHT_UNIFORM_BOTTOM_ID = 1;
    private static final Integer UPDATED_RIGHT_UNIFORM_BOTTOM_ID = 2;

    private static final Integer DEFAULT_RIGHT_UNIFORM_NUMBER = 1;
    private static final Integer UPDATED_RIGHT_UNIFORM_NUMBER = 2;

    private static final String DEFAULT_CUT_IN_SPRITE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUT_IN_SPRITE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LEFT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_LEFT_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_RIGHT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_RIGHT_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_VOICE_TRIGGER = "AAAAAAAAAA";
    private static final String UPDATED_VOICE_TRIGGER = "BBBBBBBBBB";

    private static final String DEFAULT_VOICE_START_CUT_IN = "AAAAAAAAAA";
    private static final String UPDATED_VOICE_START_CUT_IN = "BBBBBBBBBB";

    private static final Integer DEFAULT_KICKER_ID = 1;
    private static final Integer UPDATED_KICKER_ID = 2;

    @Autowired
    private MGachaRenditionKickerRepository mGachaRenditionKickerRepository;

    @Autowired
    private MGachaRenditionKickerMapper mGachaRenditionKickerMapper;

    @Autowired
    private MGachaRenditionKickerService mGachaRenditionKickerService;

    @Autowired
    private MGachaRenditionKickerQueryService mGachaRenditionKickerQueryService;

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

    private MockMvc restMGachaRenditionKickerMockMvc;

    private MGachaRenditionKicker mGachaRenditionKicker;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionKickerResource mGachaRenditionKickerResource = new MGachaRenditionKickerResource(mGachaRenditionKickerService, mGachaRenditionKickerQueryService);
        this.restMGachaRenditionKickerMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionKickerResource)
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
    public static MGachaRenditionKicker createEntity(EntityManager em) {
        MGachaRenditionKicker mGachaRenditionKicker = new MGachaRenditionKicker()
            .renditionId(DEFAULT_RENDITION_ID)
            .isManySsr(DEFAULT_IS_MANY_SSR)
            .isSsr(DEFAULT_IS_SSR)
            .weight(DEFAULT_WEIGHT)
            .leftModelId(DEFAULT_LEFT_MODEL_ID)
            .leftUniformUpId(DEFAULT_LEFT_UNIFORM_UP_ID)
            .leftUniformBottomId(DEFAULT_LEFT_UNIFORM_BOTTOM_ID)
            .leftUniformNumber(DEFAULT_LEFT_UNIFORM_NUMBER)
            .rightModelId(DEFAULT_RIGHT_MODEL_ID)
            .rightUniformUpId(DEFAULT_RIGHT_UNIFORM_UP_ID)
            .rightUniformBottomId(DEFAULT_RIGHT_UNIFORM_BOTTOM_ID)
            .rightUniformNumber(DEFAULT_RIGHT_UNIFORM_NUMBER)
            .cutInSpriteName(DEFAULT_CUT_IN_SPRITE_NAME)
            .leftMessage(DEFAULT_LEFT_MESSAGE)
            .rightMessage(DEFAULT_RIGHT_MESSAGE)
            .voiceTrigger(DEFAULT_VOICE_TRIGGER)
            .voiceStartCutIn(DEFAULT_VOICE_START_CUT_IN)
            .kickerId(DEFAULT_KICKER_ID);
        return mGachaRenditionKicker;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionKicker createUpdatedEntity(EntityManager em) {
        MGachaRenditionKicker mGachaRenditionKicker = new MGachaRenditionKicker()
            .renditionId(UPDATED_RENDITION_ID)
            .isManySsr(UPDATED_IS_MANY_SSR)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .leftModelId(UPDATED_LEFT_MODEL_ID)
            .leftUniformUpId(UPDATED_LEFT_UNIFORM_UP_ID)
            .leftUniformBottomId(UPDATED_LEFT_UNIFORM_BOTTOM_ID)
            .leftUniformNumber(UPDATED_LEFT_UNIFORM_NUMBER)
            .rightModelId(UPDATED_RIGHT_MODEL_ID)
            .rightUniformUpId(UPDATED_RIGHT_UNIFORM_UP_ID)
            .rightUniformBottomId(UPDATED_RIGHT_UNIFORM_BOTTOM_ID)
            .rightUniformNumber(UPDATED_RIGHT_UNIFORM_NUMBER)
            .cutInSpriteName(UPDATED_CUT_IN_SPRITE_NAME)
            .leftMessage(UPDATED_LEFT_MESSAGE)
            .rightMessage(UPDATED_RIGHT_MESSAGE)
            .voiceTrigger(UPDATED_VOICE_TRIGGER)
            .voiceStartCutIn(UPDATED_VOICE_START_CUT_IN)
            .kickerId(UPDATED_KICKER_ID);
        return mGachaRenditionKicker;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionKicker = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionKicker() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionKickerRepository.findAll().size();

        // Create the MGachaRenditionKicker
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);
        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionKicker in the database
        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionKicker testMGachaRenditionKicker = mGachaRenditionKickerList.get(mGachaRenditionKickerList.size() - 1);
        assertThat(testMGachaRenditionKicker.getRenditionId()).isEqualTo(DEFAULT_RENDITION_ID);
        assertThat(testMGachaRenditionKicker.getIsManySsr()).isEqualTo(DEFAULT_IS_MANY_SSR);
        assertThat(testMGachaRenditionKicker.getIsSsr()).isEqualTo(DEFAULT_IS_SSR);
        assertThat(testMGachaRenditionKicker.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionKicker.getLeftModelId()).isEqualTo(DEFAULT_LEFT_MODEL_ID);
        assertThat(testMGachaRenditionKicker.getLeftUniformUpId()).isEqualTo(DEFAULT_LEFT_UNIFORM_UP_ID);
        assertThat(testMGachaRenditionKicker.getLeftUniformBottomId()).isEqualTo(DEFAULT_LEFT_UNIFORM_BOTTOM_ID);
        assertThat(testMGachaRenditionKicker.getLeftUniformNumber()).isEqualTo(DEFAULT_LEFT_UNIFORM_NUMBER);
        assertThat(testMGachaRenditionKicker.getRightModelId()).isEqualTo(DEFAULT_RIGHT_MODEL_ID);
        assertThat(testMGachaRenditionKicker.getRightUniformUpId()).isEqualTo(DEFAULT_RIGHT_UNIFORM_UP_ID);
        assertThat(testMGachaRenditionKicker.getRightUniformBottomId()).isEqualTo(DEFAULT_RIGHT_UNIFORM_BOTTOM_ID);
        assertThat(testMGachaRenditionKicker.getRightUniformNumber()).isEqualTo(DEFAULT_RIGHT_UNIFORM_NUMBER);
        assertThat(testMGachaRenditionKicker.getCutInSpriteName()).isEqualTo(DEFAULT_CUT_IN_SPRITE_NAME);
        assertThat(testMGachaRenditionKicker.getLeftMessage()).isEqualTo(DEFAULT_LEFT_MESSAGE);
        assertThat(testMGachaRenditionKicker.getRightMessage()).isEqualTo(DEFAULT_RIGHT_MESSAGE);
        assertThat(testMGachaRenditionKicker.getVoiceTrigger()).isEqualTo(DEFAULT_VOICE_TRIGGER);
        assertThat(testMGachaRenditionKicker.getVoiceStartCutIn()).isEqualTo(DEFAULT_VOICE_START_CUT_IN);
        assertThat(testMGachaRenditionKicker.getKickerId()).isEqualTo(DEFAULT_KICKER_ID);
    }

    @Test
    @Transactional
    public void createMGachaRenditionKickerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionKickerRepository.findAll().size();

        // Create the MGachaRenditionKicker with an existing ID
        mGachaRenditionKicker.setId(1L);
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionKicker in the database
        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRenditionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setRenditionId(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsManySsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setIsManySsr(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsSsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setIsSsr(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setWeight(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLeftModelIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setLeftModelId(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLeftUniformUpIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setLeftUniformUpId(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLeftUniformBottomIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setLeftUniformBottomId(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLeftUniformNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setLeftUniformNumber(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRightModelIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setRightModelId(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRightUniformUpIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setRightUniformUpId(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRightUniformBottomIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setRightUniformBottomId(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRightUniformNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setRightUniformNumber(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKickerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionKickerRepository.findAll().size();
        // set the field null
        mGachaRenditionKicker.setKickerId(null);

        // Create the MGachaRenditionKicker, which fails.
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(post("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickers() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList
        restMGachaRenditionKickerMockMvc.perform(get("/api/m-gacha-rendition-kickers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionKicker.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isManySsr").value(hasItem(DEFAULT_IS_MANY_SSR)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].leftModelId").value(hasItem(DEFAULT_LEFT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].leftUniformUpId").value(hasItem(DEFAULT_LEFT_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].leftUniformBottomId").value(hasItem(DEFAULT_LEFT_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].leftUniformNumber").value(hasItem(DEFAULT_LEFT_UNIFORM_NUMBER)))
            .andExpect(jsonPath("$.[*].rightModelId").value(hasItem(DEFAULT_RIGHT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].rightUniformUpId").value(hasItem(DEFAULT_RIGHT_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].rightUniformBottomId").value(hasItem(DEFAULT_RIGHT_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].rightUniformNumber").value(hasItem(DEFAULT_RIGHT_UNIFORM_NUMBER)))
            .andExpect(jsonPath("$.[*].cutInSpriteName").value(hasItem(DEFAULT_CUT_IN_SPRITE_NAME.toString())))
            .andExpect(jsonPath("$.[*].leftMessage").value(hasItem(DEFAULT_LEFT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].rightMessage").value(hasItem(DEFAULT_RIGHT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].voiceTrigger").value(hasItem(DEFAULT_VOICE_TRIGGER.toString())))
            .andExpect(jsonPath("$.[*].voiceStartCutIn").value(hasItem(DEFAULT_VOICE_START_CUT_IN.toString())))
            .andExpect(jsonPath("$.[*].kickerId").value(hasItem(DEFAULT_KICKER_ID)));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionKicker() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get the mGachaRenditionKicker
        restMGachaRenditionKickerMockMvc.perform(get("/api/m-gacha-rendition-kickers/{id}", mGachaRenditionKicker.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionKicker.getId().intValue()))
            .andExpect(jsonPath("$.renditionId").value(DEFAULT_RENDITION_ID))
            .andExpect(jsonPath("$.isManySsr").value(DEFAULT_IS_MANY_SSR))
            .andExpect(jsonPath("$.isSsr").value(DEFAULT_IS_SSR))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.leftModelId").value(DEFAULT_LEFT_MODEL_ID))
            .andExpect(jsonPath("$.leftUniformUpId").value(DEFAULT_LEFT_UNIFORM_UP_ID))
            .andExpect(jsonPath("$.leftUniformBottomId").value(DEFAULT_LEFT_UNIFORM_BOTTOM_ID))
            .andExpect(jsonPath("$.leftUniformNumber").value(DEFAULT_LEFT_UNIFORM_NUMBER))
            .andExpect(jsonPath("$.rightModelId").value(DEFAULT_RIGHT_MODEL_ID))
            .andExpect(jsonPath("$.rightUniformUpId").value(DEFAULT_RIGHT_UNIFORM_UP_ID))
            .andExpect(jsonPath("$.rightUniformBottomId").value(DEFAULT_RIGHT_UNIFORM_BOTTOM_ID))
            .andExpect(jsonPath("$.rightUniformNumber").value(DEFAULT_RIGHT_UNIFORM_NUMBER))
            .andExpect(jsonPath("$.cutInSpriteName").value(DEFAULT_CUT_IN_SPRITE_NAME.toString()))
            .andExpect(jsonPath("$.leftMessage").value(DEFAULT_LEFT_MESSAGE.toString()))
            .andExpect(jsonPath("$.rightMessage").value(DEFAULT_RIGHT_MESSAGE.toString()))
            .andExpect(jsonPath("$.voiceTrigger").value(DEFAULT_VOICE_TRIGGER.toString()))
            .andExpect(jsonPath("$.voiceStartCutIn").value(DEFAULT_VOICE_START_CUT_IN.toString()))
            .andExpect(jsonPath("$.kickerId").value(DEFAULT_KICKER_ID));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRenditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where renditionId equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionKickerShouldBeFound("renditionId.equals=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionKickerList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionKickerShouldNotBeFound("renditionId.equals=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRenditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where renditionId in DEFAULT_RENDITION_ID or UPDATED_RENDITION_ID
        defaultMGachaRenditionKickerShouldBeFound("renditionId.in=" + DEFAULT_RENDITION_ID + "," + UPDATED_RENDITION_ID);

        // Get all the mGachaRenditionKickerList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionKickerShouldNotBeFound("renditionId.in=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRenditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where renditionId is not null
        defaultMGachaRenditionKickerShouldBeFound("renditionId.specified=true");

        // Get all the mGachaRenditionKickerList where renditionId is null
        defaultMGachaRenditionKickerShouldNotBeFound("renditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRenditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where renditionId greater than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionKickerShouldBeFound("renditionId.greaterOrEqualThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionKickerList where renditionId greater than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionKickerShouldNotBeFound("renditionId.greaterOrEqualThan=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRenditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where renditionId less than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionKickerShouldNotBeFound("renditionId.lessThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionKickerList where renditionId less than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionKickerShouldBeFound("renditionId.lessThan=" + UPDATED_RENDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsManySsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isManySsr equals to DEFAULT_IS_MANY_SSR
        defaultMGachaRenditionKickerShouldBeFound("isManySsr.equals=" + DEFAULT_IS_MANY_SSR);

        // Get all the mGachaRenditionKickerList where isManySsr equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionKickerShouldNotBeFound("isManySsr.equals=" + UPDATED_IS_MANY_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsManySsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isManySsr in DEFAULT_IS_MANY_SSR or UPDATED_IS_MANY_SSR
        defaultMGachaRenditionKickerShouldBeFound("isManySsr.in=" + DEFAULT_IS_MANY_SSR + "," + UPDATED_IS_MANY_SSR);

        // Get all the mGachaRenditionKickerList where isManySsr equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionKickerShouldNotBeFound("isManySsr.in=" + UPDATED_IS_MANY_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsManySsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isManySsr is not null
        defaultMGachaRenditionKickerShouldBeFound("isManySsr.specified=true");

        // Get all the mGachaRenditionKickerList where isManySsr is null
        defaultMGachaRenditionKickerShouldNotBeFound("isManySsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsManySsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isManySsr greater than or equals to DEFAULT_IS_MANY_SSR
        defaultMGachaRenditionKickerShouldBeFound("isManySsr.greaterOrEqualThan=" + DEFAULT_IS_MANY_SSR);

        // Get all the mGachaRenditionKickerList where isManySsr greater than or equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionKickerShouldNotBeFound("isManySsr.greaterOrEqualThan=" + UPDATED_IS_MANY_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsManySsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isManySsr less than or equals to DEFAULT_IS_MANY_SSR
        defaultMGachaRenditionKickerShouldNotBeFound("isManySsr.lessThan=" + DEFAULT_IS_MANY_SSR);

        // Get all the mGachaRenditionKickerList where isManySsr less than or equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionKickerShouldBeFound("isManySsr.lessThan=" + UPDATED_IS_MANY_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsSsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isSsr equals to DEFAULT_IS_SSR
        defaultMGachaRenditionKickerShouldBeFound("isSsr.equals=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionKickerList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionKickerShouldNotBeFound("isSsr.equals=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsSsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isSsr in DEFAULT_IS_SSR or UPDATED_IS_SSR
        defaultMGachaRenditionKickerShouldBeFound("isSsr.in=" + DEFAULT_IS_SSR + "," + UPDATED_IS_SSR);

        // Get all the mGachaRenditionKickerList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionKickerShouldNotBeFound("isSsr.in=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsSsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isSsr is not null
        defaultMGachaRenditionKickerShouldBeFound("isSsr.specified=true");

        // Get all the mGachaRenditionKickerList where isSsr is null
        defaultMGachaRenditionKickerShouldNotBeFound("isSsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsSsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isSsr greater than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionKickerShouldBeFound("isSsr.greaterOrEqualThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionKickerList where isSsr greater than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionKickerShouldNotBeFound("isSsr.greaterOrEqualThan=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByIsSsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where isSsr less than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionKickerShouldNotBeFound("isSsr.lessThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionKickerList where isSsr less than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionKickerShouldBeFound("isSsr.lessThan=" + UPDATED_IS_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionKickerShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionKickerList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionKickerShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionKickerShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionKickerList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionKickerShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where weight is not null
        defaultMGachaRenditionKickerShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionKickerList where weight is null
        defaultMGachaRenditionKickerShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionKickerShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionKickerList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionKickerShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionKickerShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionKickerList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionKickerShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftModelIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftModelId equals to DEFAULT_LEFT_MODEL_ID
        defaultMGachaRenditionKickerShouldBeFound("leftModelId.equals=" + DEFAULT_LEFT_MODEL_ID);

        // Get all the mGachaRenditionKickerList where leftModelId equals to UPDATED_LEFT_MODEL_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftModelId.equals=" + UPDATED_LEFT_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftModelIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftModelId in DEFAULT_LEFT_MODEL_ID or UPDATED_LEFT_MODEL_ID
        defaultMGachaRenditionKickerShouldBeFound("leftModelId.in=" + DEFAULT_LEFT_MODEL_ID + "," + UPDATED_LEFT_MODEL_ID);

        // Get all the mGachaRenditionKickerList where leftModelId equals to UPDATED_LEFT_MODEL_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftModelId.in=" + UPDATED_LEFT_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftModelIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftModelId is not null
        defaultMGachaRenditionKickerShouldBeFound("leftModelId.specified=true");

        // Get all the mGachaRenditionKickerList where leftModelId is null
        defaultMGachaRenditionKickerShouldNotBeFound("leftModelId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftModelIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftModelId greater than or equals to DEFAULT_LEFT_MODEL_ID
        defaultMGachaRenditionKickerShouldBeFound("leftModelId.greaterOrEqualThan=" + DEFAULT_LEFT_MODEL_ID);

        // Get all the mGachaRenditionKickerList where leftModelId greater than or equals to UPDATED_LEFT_MODEL_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftModelId.greaterOrEqualThan=" + UPDATED_LEFT_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftModelIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftModelId less than or equals to DEFAULT_LEFT_MODEL_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftModelId.lessThan=" + DEFAULT_LEFT_MODEL_ID);

        // Get all the mGachaRenditionKickerList where leftModelId less than or equals to UPDATED_LEFT_MODEL_ID
        defaultMGachaRenditionKickerShouldBeFound("leftModelId.lessThan=" + UPDATED_LEFT_MODEL_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformUpIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformUpId equals to DEFAULT_LEFT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldBeFound("leftUniformUpId.equals=" + DEFAULT_LEFT_UNIFORM_UP_ID);

        // Get all the mGachaRenditionKickerList where leftUniformUpId equals to UPDATED_LEFT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformUpId.equals=" + UPDATED_LEFT_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformUpIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformUpId in DEFAULT_LEFT_UNIFORM_UP_ID or UPDATED_LEFT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldBeFound("leftUniformUpId.in=" + DEFAULT_LEFT_UNIFORM_UP_ID + "," + UPDATED_LEFT_UNIFORM_UP_ID);

        // Get all the mGachaRenditionKickerList where leftUniformUpId equals to UPDATED_LEFT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformUpId.in=" + UPDATED_LEFT_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformUpIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformUpId is not null
        defaultMGachaRenditionKickerShouldBeFound("leftUniformUpId.specified=true");

        // Get all the mGachaRenditionKickerList where leftUniformUpId is null
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformUpId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformUpIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformUpId greater than or equals to DEFAULT_LEFT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldBeFound("leftUniformUpId.greaterOrEqualThan=" + DEFAULT_LEFT_UNIFORM_UP_ID);

        // Get all the mGachaRenditionKickerList where leftUniformUpId greater than or equals to UPDATED_LEFT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformUpId.greaterOrEqualThan=" + UPDATED_LEFT_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformUpIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformUpId less than or equals to DEFAULT_LEFT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformUpId.lessThan=" + DEFAULT_LEFT_UNIFORM_UP_ID);

        // Get all the mGachaRenditionKickerList where leftUniformUpId less than or equals to UPDATED_LEFT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldBeFound("leftUniformUpId.lessThan=" + UPDATED_LEFT_UNIFORM_UP_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformBottomIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformBottomId equals to DEFAULT_LEFT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldBeFound("leftUniformBottomId.equals=" + DEFAULT_LEFT_UNIFORM_BOTTOM_ID);

        // Get all the mGachaRenditionKickerList where leftUniformBottomId equals to UPDATED_LEFT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformBottomId.equals=" + UPDATED_LEFT_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformBottomIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformBottomId in DEFAULT_LEFT_UNIFORM_BOTTOM_ID or UPDATED_LEFT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldBeFound("leftUniformBottomId.in=" + DEFAULT_LEFT_UNIFORM_BOTTOM_ID + "," + UPDATED_LEFT_UNIFORM_BOTTOM_ID);

        // Get all the mGachaRenditionKickerList where leftUniformBottomId equals to UPDATED_LEFT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformBottomId.in=" + UPDATED_LEFT_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformBottomIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformBottomId is not null
        defaultMGachaRenditionKickerShouldBeFound("leftUniformBottomId.specified=true");

        // Get all the mGachaRenditionKickerList where leftUniformBottomId is null
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformBottomId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformBottomIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformBottomId greater than or equals to DEFAULT_LEFT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldBeFound("leftUniformBottomId.greaterOrEqualThan=" + DEFAULT_LEFT_UNIFORM_BOTTOM_ID);

        // Get all the mGachaRenditionKickerList where leftUniformBottomId greater than or equals to UPDATED_LEFT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformBottomId.greaterOrEqualThan=" + UPDATED_LEFT_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformBottomIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformBottomId less than or equals to DEFAULT_LEFT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformBottomId.lessThan=" + DEFAULT_LEFT_UNIFORM_BOTTOM_ID);

        // Get all the mGachaRenditionKickerList where leftUniformBottomId less than or equals to UPDATED_LEFT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldBeFound("leftUniformBottomId.lessThan=" + UPDATED_LEFT_UNIFORM_BOTTOM_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformNumber equals to DEFAULT_LEFT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldBeFound("leftUniformNumber.equals=" + DEFAULT_LEFT_UNIFORM_NUMBER);

        // Get all the mGachaRenditionKickerList where leftUniformNumber equals to UPDATED_LEFT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformNumber.equals=" + UPDATED_LEFT_UNIFORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformNumber in DEFAULT_LEFT_UNIFORM_NUMBER or UPDATED_LEFT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldBeFound("leftUniformNumber.in=" + DEFAULT_LEFT_UNIFORM_NUMBER + "," + UPDATED_LEFT_UNIFORM_NUMBER);

        // Get all the mGachaRenditionKickerList where leftUniformNumber equals to UPDATED_LEFT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformNumber.in=" + UPDATED_LEFT_UNIFORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformNumber is not null
        defaultMGachaRenditionKickerShouldBeFound("leftUniformNumber.specified=true");

        // Get all the mGachaRenditionKickerList where leftUniformNumber is null
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformNumber greater than or equals to DEFAULT_LEFT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldBeFound("leftUniformNumber.greaterOrEqualThan=" + DEFAULT_LEFT_UNIFORM_NUMBER);

        // Get all the mGachaRenditionKickerList where leftUniformNumber greater than or equals to UPDATED_LEFT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformNumber.greaterOrEqualThan=" + UPDATED_LEFT_UNIFORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByLeftUniformNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where leftUniformNumber less than or equals to DEFAULT_LEFT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldNotBeFound("leftUniformNumber.lessThan=" + DEFAULT_LEFT_UNIFORM_NUMBER);

        // Get all the mGachaRenditionKickerList where leftUniformNumber less than or equals to UPDATED_LEFT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldBeFound("leftUniformNumber.lessThan=" + UPDATED_LEFT_UNIFORM_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightModelIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightModelId equals to DEFAULT_RIGHT_MODEL_ID
        defaultMGachaRenditionKickerShouldBeFound("rightModelId.equals=" + DEFAULT_RIGHT_MODEL_ID);

        // Get all the mGachaRenditionKickerList where rightModelId equals to UPDATED_RIGHT_MODEL_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightModelId.equals=" + UPDATED_RIGHT_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightModelIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightModelId in DEFAULT_RIGHT_MODEL_ID or UPDATED_RIGHT_MODEL_ID
        defaultMGachaRenditionKickerShouldBeFound("rightModelId.in=" + DEFAULT_RIGHT_MODEL_ID + "," + UPDATED_RIGHT_MODEL_ID);

        // Get all the mGachaRenditionKickerList where rightModelId equals to UPDATED_RIGHT_MODEL_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightModelId.in=" + UPDATED_RIGHT_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightModelIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightModelId is not null
        defaultMGachaRenditionKickerShouldBeFound("rightModelId.specified=true");

        // Get all the mGachaRenditionKickerList where rightModelId is null
        defaultMGachaRenditionKickerShouldNotBeFound("rightModelId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightModelIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightModelId greater than or equals to DEFAULT_RIGHT_MODEL_ID
        defaultMGachaRenditionKickerShouldBeFound("rightModelId.greaterOrEqualThan=" + DEFAULT_RIGHT_MODEL_ID);

        // Get all the mGachaRenditionKickerList where rightModelId greater than or equals to UPDATED_RIGHT_MODEL_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightModelId.greaterOrEqualThan=" + UPDATED_RIGHT_MODEL_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightModelIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightModelId less than or equals to DEFAULT_RIGHT_MODEL_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightModelId.lessThan=" + DEFAULT_RIGHT_MODEL_ID);

        // Get all the mGachaRenditionKickerList where rightModelId less than or equals to UPDATED_RIGHT_MODEL_ID
        defaultMGachaRenditionKickerShouldBeFound("rightModelId.lessThan=" + UPDATED_RIGHT_MODEL_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformUpIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformUpId equals to DEFAULT_RIGHT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldBeFound("rightUniformUpId.equals=" + DEFAULT_RIGHT_UNIFORM_UP_ID);

        // Get all the mGachaRenditionKickerList where rightUniformUpId equals to UPDATED_RIGHT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformUpId.equals=" + UPDATED_RIGHT_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformUpIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformUpId in DEFAULT_RIGHT_UNIFORM_UP_ID or UPDATED_RIGHT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldBeFound("rightUniformUpId.in=" + DEFAULT_RIGHT_UNIFORM_UP_ID + "," + UPDATED_RIGHT_UNIFORM_UP_ID);

        // Get all the mGachaRenditionKickerList where rightUniformUpId equals to UPDATED_RIGHT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformUpId.in=" + UPDATED_RIGHT_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformUpIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformUpId is not null
        defaultMGachaRenditionKickerShouldBeFound("rightUniformUpId.specified=true");

        // Get all the mGachaRenditionKickerList where rightUniformUpId is null
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformUpId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformUpIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformUpId greater than or equals to DEFAULT_RIGHT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldBeFound("rightUniformUpId.greaterOrEqualThan=" + DEFAULT_RIGHT_UNIFORM_UP_ID);

        // Get all the mGachaRenditionKickerList where rightUniformUpId greater than or equals to UPDATED_RIGHT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformUpId.greaterOrEqualThan=" + UPDATED_RIGHT_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformUpIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformUpId less than or equals to DEFAULT_RIGHT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformUpId.lessThan=" + DEFAULT_RIGHT_UNIFORM_UP_ID);

        // Get all the mGachaRenditionKickerList where rightUniformUpId less than or equals to UPDATED_RIGHT_UNIFORM_UP_ID
        defaultMGachaRenditionKickerShouldBeFound("rightUniformUpId.lessThan=" + UPDATED_RIGHT_UNIFORM_UP_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformBottomIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformBottomId equals to DEFAULT_RIGHT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldBeFound("rightUniformBottomId.equals=" + DEFAULT_RIGHT_UNIFORM_BOTTOM_ID);

        // Get all the mGachaRenditionKickerList where rightUniformBottomId equals to UPDATED_RIGHT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformBottomId.equals=" + UPDATED_RIGHT_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformBottomIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformBottomId in DEFAULT_RIGHT_UNIFORM_BOTTOM_ID or UPDATED_RIGHT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldBeFound("rightUniformBottomId.in=" + DEFAULT_RIGHT_UNIFORM_BOTTOM_ID + "," + UPDATED_RIGHT_UNIFORM_BOTTOM_ID);

        // Get all the mGachaRenditionKickerList where rightUniformBottomId equals to UPDATED_RIGHT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformBottomId.in=" + UPDATED_RIGHT_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformBottomIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformBottomId is not null
        defaultMGachaRenditionKickerShouldBeFound("rightUniformBottomId.specified=true");

        // Get all the mGachaRenditionKickerList where rightUniformBottomId is null
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformBottomId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformBottomIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformBottomId greater than or equals to DEFAULT_RIGHT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldBeFound("rightUniformBottomId.greaterOrEqualThan=" + DEFAULT_RIGHT_UNIFORM_BOTTOM_ID);

        // Get all the mGachaRenditionKickerList where rightUniformBottomId greater than or equals to UPDATED_RIGHT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformBottomId.greaterOrEqualThan=" + UPDATED_RIGHT_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformBottomIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformBottomId less than or equals to DEFAULT_RIGHT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformBottomId.lessThan=" + DEFAULT_RIGHT_UNIFORM_BOTTOM_ID);

        // Get all the mGachaRenditionKickerList where rightUniformBottomId less than or equals to UPDATED_RIGHT_UNIFORM_BOTTOM_ID
        defaultMGachaRenditionKickerShouldBeFound("rightUniformBottomId.lessThan=" + UPDATED_RIGHT_UNIFORM_BOTTOM_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformNumber equals to DEFAULT_RIGHT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldBeFound("rightUniformNumber.equals=" + DEFAULT_RIGHT_UNIFORM_NUMBER);

        // Get all the mGachaRenditionKickerList where rightUniformNumber equals to UPDATED_RIGHT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformNumber.equals=" + UPDATED_RIGHT_UNIFORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformNumberIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformNumber in DEFAULT_RIGHT_UNIFORM_NUMBER or UPDATED_RIGHT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldBeFound("rightUniformNumber.in=" + DEFAULT_RIGHT_UNIFORM_NUMBER + "," + UPDATED_RIGHT_UNIFORM_NUMBER);

        // Get all the mGachaRenditionKickerList where rightUniformNumber equals to UPDATED_RIGHT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformNumber.in=" + UPDATED_RIGHT_UNIFORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformNumber is not null
        defaultMGachaRenditionKickerShouldBeFound("rightUniformNumber.specified=true");

        // Get all the mGachaRenditionKickerList where rightUniformNumber is null
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformNumber greater than or equals to DEFAULT_RIGHT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldBeFound("rightUniformNumber.greaterOrEqualThan=" + DEFAULT_RIGHT_UNIFORM_NUMBER);

        // Get all the mGachaRenditionKickerList where rightUniformNumber greater than or equals to UPDATED_RIGHT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformNumber.greaterOrEqualThan=" + UPDATED_RIGHT_UNIFORM_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByRightUniformNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where rightUniformNumber less than or equals to DEFAULT_RIGHT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldNotBeFound("rightUniformNumber.lessThan=" + DEFAULT_RIGHT_UNIFORM_NUMBER);

        // Get all the mGachaRenditionKickerList where rightUniformNumber less than or equals to UPDATED_RIGHT_UNIFORM_NUMBER
        defaultMGachaRenditionKickerShouldBeFound("rightUniformNumber.lessThan=" + UPDATED_RIGHT_UNIFORM_NUMBER);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByKickerIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where kickerId equals to DEFAULT_KICKER_ID
        defaultMGachaRenditionKickerShouldBeFound("kickerId.equals=" + DEFAULT_KICKER_ID);

        // Get all the mGachaRenditionKickerList where kickerId equals to UPDATED_KICKER_ID
        defaultMGachaRenditionKickerShouldNotBeFound("kickerId.equals=" + UPDATED_KICKER_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByKickerIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where kickerId in DEFAULT_KICKER_ID or UPDATED_KICKER_ID
        defaultMGachaRenditionKickerShouldBeFound("kickerId.in=" + DEFAULT_KICKER_ID + "," + UPDATED_KICKER_ID);

        // Get all the mGachaRenditionKickerList where kickerId equals to UPDATED_KICKER_ID
        defaultMGachaRenditionKickerShouldNotBeFound("kickerId.in=" + UPDATED_KICKER_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByKickerIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where kickerId is not null
        defaultMGachaRenditionKickerShouldBeFound("kickerId.specified=true");

        // Get all the mGachaRenditionKickerList where kickerId is null
        defaultMGachaRenditionKickerShouldNotBeFound("kickerId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByKickerIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where kickerId greater than or equals to DEFAULT_KICKER_ID
        defaultMGachaRenditionKickerShouldBeFound("kickerId.greaterOrEqualThan=" + DEFAULT_KICKER_ID);

        // Get all the mGachaRenditionKickerList where kickerId greater than or equals to UPDATED_KICKER_ID
        defaultMGachaRenditionKickerShouldNotBeFound("kickerId.greaterOrEqualThan=" + UPDATED_KICKER_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionKickersByKickerIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        // Get all the mGachaRenditionKickerList where kickerId less than or equals to DEFAULT_KICKER_ID
        defaultMGachaRenditionKickerShouldNotBeFound("kickerId.lessThan=" + DEFAULT_KICKER_ID);

        // Get all the mGachaRenditionKickerList where kickerId less than or equals to UPDATED_KICKER_ID
        defaultMGachaRenditionKickerShouldBeFound("kickerId.lessThan=" + UPDATED_KICKER_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionKickerShouldBeFound(String filter) throws Exception {
        restMGachaRenditionKickerMockMvc.perform(get("/api/m-gacha-rendition-kickers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionKicker.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isManySsr").value(hasItem(DEFAULT_IS_MANY_SSR)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].leftModelId").value(hasItem(DEFAULT_LEFT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].leftUniformUpId").value(hasItem(DEFAULT_LEFT_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].leftUniformBottomId").value(hasItem(DEFAULT_LEFT_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].leftUniformNumber").value(hasItem(DEFAULT_LEFT_UNIFORM_NUMBER)))
            .andExpect(jsonPath("$.[*].rightModelId").value(hasItem(DEFAULT_RIGHT_MODEL_ID)))
            .andExpect(jsonPath("$.[*].rightUniformUpId").value(hasItem(DEFAULT_RIGHT_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].rightUniformBottomId").value(hasItem(DEFAULT_RIGHT_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].rightUniformNumber").value(hasItem(DEFAULT_RIGHT_UNIFORM_NUMBER)))
            .andExpect(jsonPath("$.[*].cutInSpriteName").value(hasItem(DEFAULT_CUT_IN_SPRITE_NAME.toString())))
            .andExpect(jsonPath("$.[*].leftMessage").value(hasItem(DEFAULT_LEFT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].rightMessage").value(hasItem(DEFAULT_RIGHT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].voiceTrigger").value(hasItem(DEFAULT_VOICE_TRIGGER.toString())))
            .andExpect(jsonPath("$.[*].voiceStartCutIn").value(hasItem(DEFAULT_VOICE_START_CUT_IN.toString())))
            .andExpect(jsonPath("$.[*].kickerId").value(hasItem(DEFAULT_KICKER_ID)));

        // Check, that the count call also returns 1
        restMGachaRenditionKickerMockMvc.perform(get("/api/m-gacha-rendition-kickers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionKickerShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionKickerMockMvc.perform(get("/api/m-gacha-rendition-kickers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionKickerMockMvc.perform(get("/api/m-gacha-rendition-kickers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionKicker() throws Exception {
        // Get the mGachaRenditionKicker
        restMGachaRenditionKickerMockMvc.perform(get("/api/m-gacha-rendition-kickers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionKicker() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        int databaseSizeBeforeUpdate = mGachaRenditionKickerRepository.findAll().size();

        // Update the mGachaRenditionKicker
        MGachaRenditionKicker updatedMGachaRenditionKicker = mGachaRenditionKickerRepository.findById(mGachaRenditionKicker.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionKicker are not directly saved in db
        em.detach(updatedMGachaRenditionKicker);
        updatedMGachaRenditionKicker
            .renditionId(UPDATED_RENDITION_ID)
            .isManySsr(UPDATED_IS_MANY_SSR)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .leftModelId(UPDATED_LEFT_MODEL_ID)
            .leftUniformUpId(UPDATED_LEFT_UNIFORM_UP_ID)
            .leftUniformBottomId(UPDATED_LEFT_UNIFORM_BOTTOM_ID)
            .leftUniformNumber(UPDATED_LEFT_UNIFORM_NUMBER)
            .rightModelId(UPDATED_RIGHT_MODEL_ID)
            .rightUniformUpId(UPDATED_RIGHT_UNIFORM_UP_ID)
            .rightUniformBottomId(UPDATED_RIGHT_UNIFORM_BOTTOM_ID)
            .rightUniformNumber(UPDATED_RIGHT_UNIFORM_NUMBER)
            .cutInSpriteName(UPDATED_CUT_IN_SPRITE_NAME)
            .leftMessage(UPDATED_LEFT_MESSAGE)
            .rightMessage(UPDATED_RIGHT_MESSAGE)
            .voiceTrigger(UPDATED_VOICE_TRIGGER)
            .voiceStartCutIn(UPDATED_VOICE_START_CUT_IN)
            .kickerId(UPDATED_KICKER_ID);
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(updatedMGachaRenditionKicker);

        restMGachaRenditionKickerMockMvc.perform(put("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionKicker in the database
        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionKicker testMGachaRenditionKicker = mGachaRenditionKickerList.get(mGachaRenditionKickerList.size() - 1);
        assertThat(testMGachaRenditionKicker.getRenditionId()).isEqualTo(UPDATED_RENDITION_ID);
        assertThat(testMGachaRenditionKicker.getIsManySsr()).isEqualTo(UPDATED_IS_MANY_SSR);
        assertThat(testMGachaRenditionKicker.getIsSsr()).isEqualTo(UPDATED_IS_SSR);
        assertThat(testMGachaRenditionKicker.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionKicker.getLeftModelId()).isEqualTo(UPDATED_LEFT_MODEL_ID);
        assertThat(testMGachaRenditionKicker.getLeftUniformUpId()).isEqualTo(UPDATED_LEFT_UNIFORM_UP_ID);
        assertThat(testMGachaRenditionKicker.getLeftUniformBottomId()).isEqualTo(UPDATED_LEFT_UNIFORM_BOTTOM_ID);
        assertThat(testMGachaRenditionKicker.getLeftUniformNumber()).isEqualTo(UPDATED_LEFT_UNIFORM_NUMBER);
        assertThat(testMGachaRenditionKicker.getRightModelId()).isEqualTo(UPDATED_RIGHT_MODEL_ID);
        assertThat(testMGachaRenditionKicker.getRightUniformUpId()).isEqualTo(UPDATED_RIGHT_UNIFORM_UP_ID);
        assertThat(testMGachaRenditionKicker.getRightUniformBottomId()).isEqualTo(UPDATED_RIGHT_UNIFORM_BOTTOM_ID);
        assertThat(testMGachaRenditionKicker.getRightUniformNumber()).isEqualTo(UPDATED_RIGHT_UNIFORM_NUMBER);
        assertThat(testMGachaRenditionKicker.getCutInSpriteName()).isEqualTo(UPDATED_CUT_IN_SPRITE_NAME);
        assertThat(testMGachaRenditionKicker.getLeftMessage()).isEqualTo(UPDATED_LEFT_MESSAGE);
        assertThat(testMGachaRenditionKicker.getRightMessage()).isEqualTo(UPDATED_RIGHT_MESSAGE);
        assertThat(testMGachaRenditionKicker.getVoiceTrigger()).isEqualTo(UPDATED_VOICE_TRIGGER);
        assertThat(testMGachaRenditionKicker.getVoiceStartCutIn()).isEqualTo(UPDATED_VOICE_START_CUT_IN);
        assertThat(testMGachaRenditionKicker.getKickerId()).isEqualTo(UPDATED_KICKER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionKicker() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionKickerRepository.findAll().size();

        // Create the MGachaRenditionKicker
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO = mGachaRenditionKickerMapper.toDto(mGachaRenditionKicker);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionKickerMockMvc.perform(put("/api/m-gacha-rendition-kickers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionKickerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionKicker in the database
        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionKicker() throws Exception {
        // Initialize the database
        mGachaRenditionKickerRepository.saveAndFlush(mGachaRenditionKicker);

        int databaseSizeBeforeDelete = mGachaRenditionKickerRepository.findAll().size();

        // Delete the mGachaRenditionKicker
        restMGachaRenditionKickerMockMvc.perform(delete("/api/m-gacha-rendition-kickers/{id}", mGachaRenditionKicker.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionKicker> mGachaRenditionKickerList = mGachaRenditionKickerRepository.findAll();
        assertThat(mGachaRenditionKickerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionKicker.class);
        MGachaRenditionKicker mGachaRenditionKicker1 = new MGachaRenditionKicker();
        mGachaRenditionKicker1.setId(1L);
        MGachaRenditionKicker mGachaRenditionKicker2 = new MGachaRenditionKicker();
        mGachaRenditionKicker2.setId(mGachaRenditionKicker1.getId());
        assertThat(mGachaRenditionKicker1).isEqualTo(mGachaRenditionKicker2);
        mGachaRenditionKicker2.setId(2L);
        assertThat(mGachaRenditionKicker1).isNotEqualTo(mGachaRenditionKicker2);
        mGachaRenditionKicker1.setId(null);
        assertThat(mGachaRenditionKicker1).isNotEqualTo(mGachaRenditionKicker2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionKickerDTO.class);
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO1 = new MGachaRenditionKickerDTO();
        mGachaRenditionKickerDTO1.setId(1L);
        MGachaRenditionKickerDTO mGachaRenditionKickerDTO2 = new MGachaRenditionKickerDTO();
        assertThat(mGachaRenditionKickerDTO1).isNotEqualTo(mGachaRenditionKickerDTO2);
        mGachaRenditionKickerDTO2.setId(mGachaRenditionKickerDTO1.getId());
        assertThat(mGachaRenditionKickerDTO1).isEqualTo(mGachaRenditionKickerDTO2);
        mGachaRenditionKickerDTO2.setId(2L);
        assertThat(mGachaRenditionKickerDTO1).isNotEqualTo(mGachaRenditionKickerDTO2);
        mGachaRenditionKickerDTO1.setId(null);
        assertThat(mGachaRenditionKickerDTO1).isNotEqualTo(mGachaRenditionKickerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionKickerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionKickerMapper.fromId(null)).isNull();
    }
}
