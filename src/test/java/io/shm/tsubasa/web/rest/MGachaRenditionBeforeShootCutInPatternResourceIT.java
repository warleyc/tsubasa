package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInPattern;
import io.shm.tsubasa.repository.MGachaRenditionBeforeShootCutInPatternRepository;
import io.shm.tsubasa.service.MGachaRenditionBeforeShootCutInPatternService;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInPatternDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionBeforeShootCutInPatternMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInPatternCriteria;
import io.shm.tsubasa.service.MGachaRenditionBeforeShootCutInPatternQueryService;

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
 * Integration tests for the {@Link MGachaRenditionBeforeShootCutInPatternResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionBeforeShootCutInPatternResourceIT {

    private static final Integer DEFAULT_RENDITION_ID = 1;
    private static final Integer UPDATED_RENDITION_ID = 2;

    private static final Integer DEFAULT_IS_MANY_SSR = 1;
    private static final Integer UPDATED_IS_MANY_SSR = 2;

    private static final Integer DEFAULT_IS_SSR = 1;
    private static final Integer UPDATED_IS_SSR = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Integer DEFAULT_PATTERN = 1;
    private static final Integer UPDATED_PATTERN = 2;

    private static final String DEFAULT_NORMAL_PREFAB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NORMAL_PREFAB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FLASH_BACK_PREFAB_NAME_1 = "AAAAAAAAAA";
    private static final String UPDATED_FLASH_BACK_PREFAB_NAME_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FLASH_BACK_PREFAB_NAME_2 = "AAAAAAAAAA";
    private static final String UPDATED_FLASH_BACK_PREFAB_NAME_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FLASH_BACK_PREFAB_NAME_3 = "AAAAAAAAAA";
    private static final String UPDATED_FLASH_BACK_PREFAB_NAME_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FLASH_BACK_PREFAB_NAME_4 = "AAAAAAAAAA";
    private static final String UPDATED_FLASH_BACK_PREFAB_NAME_4 = "BBBBBBBBBB";

    private static final String DEFAULT_VOICE_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_VOICE_PREFIX = "BBBBBBBBBB";

    private static final String DEFAULT_SE_NORMAL = "AAAAAAAAAA";
    private static final String UPDATED_SE_NORMAL = "BBBBBBBBBB";

    private static final String DEFAULT_SE_FLASH_BACK = "AAAAAAAAAA";
    private static final String UPDATED_SE_FLASH_BACK = "BBBBBBBBBB";

    private static final Integer DEFAULT_EXCEPT_KICKER_ID = 1;
    private static final Integer UPDATED_EXCEPT_KICKER_ID = 2;

    @Autowired
    private MGachaRenditionBeforeShootCutInPatternRepository mGachaRenditionBeforeShootCutInPatternRepository;

    @Autowired
    private MGachaRenditionBeforeShootCutInPatternMapper mGachaRenditionBeforeShootCutInPatternMapper;

    @Autowired
    private MGachaRenditionBeforeShootCutInPatternService mGachaRenditionBeforeShootCutInPatternService;

    @Autowired
    private MGachaRenditionBeforeShootCutInPatternQueryService mGachaRenditionBeforeShootCutInPatternQueryService;

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

    private MockMvc restMGachaRenditionBeforeShootCutInPatternMockMvc;

    private MGachaRenditionBeforeShootCutInPattern mGachaRenditionBeforeShootCutInPattern;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionBeforeShootCutInPatternResource mGachaRenditionBeforeShootCutInPatternResource = new MGachaRenditionBeforeShootCutInPatternResource(mGachaRenditionBeforeShootCutInPatternService, mGachaRenditionBeforeShootCutInPatternQueryService);
        this.restMGachaRenditionBeforeShootCutInPatternMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionBeforeShootCutInPatternResource)
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
    public static MGachaRenditionBeforeShootCutInPattern createEntity(EntityManager em) {
        MGachaRenditionBeforeShootCutInPattern mGachaRenditionBeforeShootCutInPattern = new MGachaRenditionBeforeShootCutInPattern()
            .renditionId(DEFAULT_RENDITION_ID)
            .isManySsr(DEFAULT_IS_MANY_SSR)
            .isSsr(DEFAULT_IS_SSR)
            .weight(DEFAULT_WEIGHT)
            .pattern(DEFAULT_PATTERN)
            .normalPrefabName(DEFAULT_NORMAL_PREFAB_NAME)
            .flashBackPrefabName1(DEFAULT_FLASH_BACK_PREFAB_NAME_1)
            .flashBackPrefabName2(DEFAULT_FLASH_BACK_PREFAB_NAME_2)
            .flashBackPrefabName3(DEFAULT_FLASH_BACK_PREFAB_NAME_3)
            .flashBackPrefabName4(DEFAULT_FLASH_BACK_PREFAB_NAME_4)
            .voicePrefix(DEFAULT_VOICE_PREFIX)
            .seNormal(DEFAULT_SE_NORMAL)
            .seFlashBack(DEFAULT_SE_FLASH_BACK)
            .exceptKickerId(DEFAULT_EXCEPT_KICKER_ID);
        return mGachaRenditionBeforeShootCutInPattern;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionBeforeShootCutInPattern createUpdatedEntity(EntityManager em) {
        MGachaRenditionBeforeShootCutInPattern mGachaRenditionBeforeShootCutInPattern = new MGachaRenditionBeforeShootCutInPattern()
            .renditionId(UPDATED_RENDITION_ID)
            .isManySsr(UPDATED_IS_MANY_SSR)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .pattern(UPDATED_PATTERN)
            .normalPrefabName(UPDATED_NORMAL_PREFAB_NAME)
            .flashBackPrefabName1(UPDATED_FLASH_BACK_PREFAB_NAME_1)
            .flashBackPrefabName2(UPDATED_FLASH_BACK_PREFAB_NAME_2)
            .flashBackPrefabName3(UPDATED_FLASH_BACK_PREFAB_NAME_3)
            .flashBackPrefabName4(UPDATED_FLASH_BACK_PREFAB_NAME_4)
            .voicePrefix(UPDATED_VOICE_PREFIX)
            .seNormal(UPDATED_SE_NORMAL)
            .seFlashBack(UPDATED_SE_FLASH_BACK)
            .exceptKickerId(UPDATED_EXCEPT_KICKER_ID);
        return mGachaRenditionBeforeShootCutInPattern;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionBeforeShootCutInPattern = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionBeforeShootCutInPattern() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();

        // Create the MGachaRenditionBeforeShootCutInPattern
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionBeforeShootCutInPattern in the database
        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionBeforeShootCutInPattern testMGachaRenditionBeforeShootCutInPattern = mGachaRenditionBeforeShootCutInPatternList.get(mGachaRenditionBeforeShootCutInPatternList.size() - 1);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getRenditionId()).isEqualTo(DEFAULT_RENDITION_ID);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getIsManySsr()).isEqualTo(DEFAULT_IS_MANY_SSR);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getIsSsr()).isEqualTo(DEFAULT_IS_SSR);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getPattern()).isEqualTo(DEFAULT_PATTERN);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getNormalPrefabName()).isEqualTo(DEFAULT_NORMAL_PREFAB_NAME);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getFlashBackPrefabName1()).isEqualTo(DEFAULT_FLASH_BACK_PREFAB_NAME_1);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getFlashBackPrefabName2()).isEqualTo(DEFAULT_FLASH_BACK_PREFAB_NAME_2);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getFlashBackPrefabName3()).isEqualTo(DEFAULT_FLASH_BACK_PREFAB_NAME_3);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getFlashBackPrefabName4()).isEqualTo(DEFAULT_FLASH_BACK_PREFAB_NAME_4);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getVoicePrefix()).isEqualTo(DEFAULT_VOICE_PREFIX);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getSeNormal()).isEqualTo(DEFAULT_SE_NORMAL);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getSeFlashBack()).isEqualTo(DEFAULT_SE_FLASH_BACK);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getExceptKickerId()).isEqualTo(DEFAULT_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void createMGachaRenditionBeforeShootCutInPatternWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();

        // Create the MGachaRenditionBeforeShootCutInPattern with an existing ID
        mGachaRenditionBeforeShootCutInPattern.setId(1L);
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionBeforeShootCutInPattern in the database
        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRenditionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInPattern.setRenditionId(null);

        // Create the MGachaRenditionBeforeShootCutInPattern, which fails.
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);

        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsManySsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInPattern.setIsManySsr(null);

        // Create the MGachaRenditionBeforeShootCutInPattern, which fails.
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);

        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsSsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInPattern.setIsSsr(null);

        // Create the MGachaRenditionBeforeShootCutInPattern, which fails.
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);

        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInPattern.setWeight(null);

        // Create the MGachaRenditionBeforeShootCutInPattern, which fails.
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);

        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInPattern.setPattern(null);

        // Create the MGachaRenditionBeforeShootCutInPattern, which fails.
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);

        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExceptKickerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInPattern.setExceptKickerId(null);

        // Create the MGachaRenditionBeforeShootCutInPattern, which fails.
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);

        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatterns() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-patterns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionBeforeShootCutInPattern.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isManySsr").value(hasItem(DEFAULT_IS_MANY_SSR)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].pattern").value(hasItem(DEFAULT_PATTERN)))
            .andExpect(jsonPath("$.[*].normalPrefabName").value(hasItem(DEFAULT_NORMAL_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].flashBackPrefabName1").value(hasItem(DEFAULT_FLASH_BACK_PREFAB_NAME_1.toString())))
            .andExpect(jsonPath("$.[*].flashBackPrefabName2").value(hasItem(DEFAULT_FLASH_BACK_PREFAB_NAME_2.toString())))
            .andExpect(jsonPath("$.[*].flashBackPrefabName3").value(hasItem(DEFAULT_FLASH_BACK_PREFAB_NAME_3.toString())))
            .andExpect(jsonPath("$.[*].flashBackPrefabName4").value(hasItem(DEFAULT_FLASH_BACK_PREFAB_NAME_4.toString())))
            .andExpect(jsonPath("$.[*].voicePrefix").value(hasItem(DEFAULT_VOICE_PREFIX.toString())))
            .andExpect(jsonPath("$.[*].seNormal").value(hasItem(DEFAULT_SE_NORMAL.toString())))
            .andExpect(jsonPath("$.[*].seFlashBack").value(hasItem(DEFAULT_SE_FLASH_BACK.toString())))
            .andExpect(jsonPath("$.[*].exceptKickerId").value(hasItem(DEFAULT_EXCEPT_KICKER_ID)));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionBeforeShootCutInPattern() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get the mGachaRenditionBeforeShootCutInPattern
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-patterns/{id}", mGachaRenditionBeforeShootCutInPattern.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionBeforeShootCutInPattern.getId().intValue()))
            .andExpect(jsonPath("$.renditionId").value(DEFAULT_RENDITION_ID))
            .andExpect(jsonPath("$.isManySsr").value(DEFAULT_IS_MANY_SSR))
            .andExpect(jsonPath("$.isSsr").value(DEFAULT_IS_SSR))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.pattern").value(DEFAULT_PATTERN))
            .andExpect(jsonPath("$.normalPrefabName").value(DEFAULT_NORMAL_PREFAB_NAME.toString()))
            .andExpect(jsonPath("$.flashBackPrefabName1").value(DEFAULT_FLASH_BACK_PREFAB_NAME_1.toString()))
            .andExpect(jsonPath("$.flashBackPrefabName2").value(DEFAULT_FLASH_BACK_PREFAB_NAME_2.toString()))
            .andExpect(jsonPath("$.flashBackPrefabName3").value(DEFAULT_FLASH_BACK_PREFAB_NAME_3.toString()))
            .andExpect(jsonPath("$.flashBackPrefabName4").value(DEFAULT_FLASH_BACK_PREFAB_NAME_4.toString()))
            .andExpect(jsonPath("$.voicePrefix").value(DEFAULT_VOICE_PREFIX.toString()))
            .andExpect(jsonPath("$.seNormal").value(DEFAULT_SE_NORMAL.toString()))
            .andExpect(jsonPath("$.seFlashBack").value(DEFAULT_SE_FLASH_BACK.toString()))
            .andExpect(jsonPath("$.exceptKickerId").value(DEFAULT_EXCEPT_KICKER_ID));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByRenditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("renditionId.equals=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("renditionId.equals=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByRenditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId in DEFAULT_RENDITION_ID or UPDATED_RENDITION_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("renditionId.in=" + DEFAULT_RENDITION_ID + "," + UPDATED_RENDITION_ID);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("renditionId.in=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByRenditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId is not null
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("renditionId.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId is null
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("renditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByRenditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId greater than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("renditionId.greaterOrEqualThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId greater than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("renditionId.greaterOrEqualThan=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByRenditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId less than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("renditionId.lessThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where renditionId less than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("renditionId.lessThan=" + UPDATED_RENDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsManySsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr equals to DEFAULT_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isManySsr.equals=" + DEFAULT_IS_MANY_SSR);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isManySsr.equals=" + UPDATED_IS_MANY_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsManySsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr in DEFAULT_IS_MANY_SSR or UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isManySsr.in=" + DEFAULT_IS_MANY_SSR + "," + UPDATED_IS_MANY_SSR);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isManySsr.in=" + UPDATED_IS_MANY_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsManySsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr is not null
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isManySsr.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr is null
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isManySsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsManySsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr greater than or equals to DEFAULT_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isManySsr.greaterOrEqualThan=" + DEFAULT_IS_MANY_SSR);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr greater than or equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isManySsr.greaterOrEqualThan=" + UPDATED_IS_MANY_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsManySsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr less than or equals to DEFAULT_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isManySsr.lessThan=" + DEFAULT_IS_MANY_SSR);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isManySsr less than or equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isManySsr.lessThan=" + UPDATED_IS_MANY_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsSsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr equals to DEFAULT_IS_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isSsr.equals=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isSsr.equals=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsSsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr in DEFAULT_IS_SSR or UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isSsr.in=" + DEFAULT_IS_SSR + "," + UPDATED_IS_SSR);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isSsr.in=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsSsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr is not null
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isSsr.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr is null
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isSsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsSsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr greater than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isSsr.greaterOrEqualThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr greater than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isSsr.greaterOrEqualThan=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByIsSsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr less than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("isSsr.lessThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where isSsr less than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("isSsr.lessThan=" + UPDATED_IS_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight is not null
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight is null
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern equals to DEFAULT_PATTERN
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("pattern.equals=" + DEFAULT_PATTERN);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern equals to UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("pattern.equals=" + UPDATED_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern in DEFAULT_PATTERN or UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("pattern.in=" + DEFAULT_PATTERN + "," + UPDATED_PATTERN);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern equals to UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("pattern.in=" + UPDATED_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern is not null
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("pattern.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern is null
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("pattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern greater than or equals to DEFAULT_PATTERN
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("pattern.greaterOrEqualThan=" + DEFAULT_PATTERN);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern greater than or equals to UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("pattern.greaterOrEqualThan=" + UPDATED_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern less than or equals to DEFAULT_PATTERN
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("pattern.lessThan=" + DEFAULT_PATTERN);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where pattern less than or equals to UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("pattern.lessThan=" + UPDATED_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByExceptKickerIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId equals to DEFAULT_EXCEPT_KICKER_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("exceptKickerId.equals=" + DEFAULT_EXCEPT_KICKER_ID);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId equals to UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("exceptKickerId.equals=" + UPDATED_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByExceptKickerIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId in DEFAULT_EXCEPT_KICKER_ID or UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("exceptKickerId.in=" + DEFAULT_EXCEPT_KICKER_ID + "," + UPDATED_EXCEPT_KICKER_ID);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId equals to UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("exceptKickerId.in=" + UPDATED_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByExceptKickerIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId is not null
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("exceptKickerId.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId is null
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("exceptKickerId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByExceptKickerIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId greater than or equals to DEFAULT_EXCEPT_KICKER_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("exceptKickerId.greaterOrEqualThan=" + DEFAULT_EXCEPT_KICKER_ID);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId greater than or equals to UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("exceptKickerId.greaterOrEqualThan=" + UPDATED_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInPatternsByExceptKickerIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId less than or equals to DEFAULT_EXCEPT_KICKER_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound("exceptKickerId.lessThan=" + DEFAULT_EXCEPT_KICKER_ID);

        // Get all the mGachaRenditionBeforeShootCutInPatternList where exceptKickerId less than or equals to UPDATED_EXCEPT_KICKER_ID
        defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound("exceptKickerId.lessThan=" + UPDATED_EXCEPT_KICKER_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionBeforeShootCutInPatternShouldBeFound(String filter) throws Exception {
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-patterns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionBeforeShootCutInPattern.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isManySsr").value(hasItem(DEFAULT_IS_MANY_SSR)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].pattern").value(hasItem(DEFAULT_PATTERN)))
            .andExpect(jsonPath("$.[*].normalPrefabName").value(hasItem(DEFAULT_NORMAL_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].flashBackPrefabName1").value(hasItem(DEFAULT_FLASH_BACK_PREFAB_NAME_1.toString())))
            .andExpect(jsonPath("$.[*].flashBackPrefabName2").value(hasItem(DEFAULT_FLASH_BACK_PREFAB_NAME_2.toString())))
            .andExpect(jsonPath("$.[*].flashBackPrefabName3").value(hasItem(DEFAULT_FLASH_BACK_PREFAB_NAME_3.toString())))
            .andExpect(jsonPath("$.[*].flashBackPrefabName4").value(hasItem(DEFAULT_FLASH_BACK_PREFAB_NAME_4.toString())))
            .andExpect(jsonPath("$.[*].voicePrefix").value(hasItem(DEFAULT_VOICE_PREFIX.toString())))
            .andExpect(jsonPath("$.[*].seNormal").value(hasItem(DEFAULT_SE_NORMAL.toString())))
            .andExpect(jsonPath("$.[*].seFlashBack").value(hasItem(DEFAULT_SE_FLASH_BACK.toString())))
            .andExpect(jsonPath("$.[*].exceptKickerId").value(hasItem(DEFAULT_EXCEPT_KICKER_ID)));

        // Check, that the count call also returns 1
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-patterns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionBeforeShootCutInPatternShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-patterns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-patterns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionBeforeShootCutInPattern() throws Exception {
        // Get the mGachaRenditionBeforeShootCutInPattern
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-patterns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionBeforeShootCutInPattern() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        int databaseSizeBeforeUpdate = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();

        // Update the mGachaRenditionBeforeShootCutInPattern
        MGachaRenditionBeforeShootCutInPattern updatedMGachaRenditionBeforeShootCutInPattern = mGachaRenditionBeforeShootCutInPatternRepository.findById(mGachaRenditionBeforeShootCutInPattern.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionBeforeShootCutInPattern are not directly saved in db
        em.detach(updatedMGachaRenditionBeforeShootCutInPattern);
        updatedMGachaRenditionBeforeShootCutInPattern
            .renditionId(UPDATED_RENDITION_ID)
            .isManySsr(UPDATED_IS_MANY_SSR)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .pattern(UPDATED_PATTERN)
            .normalPrefabName(UPDATED_NORMAL_PREFAB_NAME)
            .flashBackPrefabName1(UPDATED_FLASH_BACK_PREFAB_NAME_1)
            .flashBackPrefabName2(UPDATED_FLASH_BACK_PREFAB_NAME_2)
            .flashBackPrefabName3(UPDATED_FLASH_BACK_PREFAB_NAME_3)
            .flashBackPrefabName4(UPDATED_FLASH_BACK_PREFAB_NAME_4)
            .voicePrefix(UPDATED_VOICE_PREFIX)
            .seNormal(UPDATED_SE_NORMAL)
            .seFlashBack(UPDATED_SE_FLASH_BACK)
            .exceptKickerId(UPDATED_EXCEPT_KICKER_ID);
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(updatedMGachaRenditionBeforeShootCutInPattern);

        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(put("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionBeforeShootCutInPattern in the database
        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionBeforeShootCutInPattern testMGachaRenditionBeforeShootCutInPattern = mGachaRenditionBeforeShootCutInPatternList.get(mGachaRenditionBeforeShootCutInPatternList.size() - 1);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getRenditionId()).isEqualTo(UPDATED_RENDITION_ID);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getIsManySsr()).isEqualTo(UPDATED_IS_MANY_SSR);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getIsSsr()).isEqualTo(UPDATED_IS_SSR);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getPattern()).isEqualTo(UPDATED_PATTERN);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getNormalPrefabName()).isEqualTo(UPDATED_NORMAL_PREFAB_NAME);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getFlashBackPrefabName1()).isEqualTo(UPDATED_FLASH_BACK_PREFAB_NAME_1);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getFlashBackPrefabName2()).isEqualTo(UPDATED_FLASH_BACK_PREFAB_NAME_2);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getFlashBackPrefabName3()).isEqualTo(UPDATED_FLASH_BACK_PREFAB_NAME_3);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getFlashBackPrefabName4()).isEqualTo(UPDATED_FLASH_BACK_PREFAB_NAME_4);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getVoicePrefix()).isEqualTo(UPDATED_VOICE_PREFIX);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getSeNormal()).isEqualTo(UPDATED_SE_NORMAL);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getSeFlashBack()).isEqualTo(UPDATED_SE_FLASH_BACK);
        assertThat(testMGachaRenditionBeforeShootCutInPattern.getExceptKickerId()).isEqualTo(UPDATED_EXCEPT_KICKER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionBeforeShootCutInPattern() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();

        // Create the MGachaRenditionBeforeShootCutInPattern
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO = mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(put("/api/m-gacha-rendition-before-shoot-cut-in-patterns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInPatternDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionBeforeShootCutInPattern in the database
        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionBeforeShootCutInPattern() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInPatternRepository.saveAndFlush(mGachaRenditionBeforeShootCutInPattern);

        int databaseSizeBeforeDelete = mGachaRenditionBeforeShootCutInPatternRepository.findAll().size();

        // Delete the mGachaRenditionBeforeShootCutInPattern
        restMGachaRenditionBeforeShootCutInPatternMockMvc.perform(delete("/api/m-gacha-rendition-before-shoot-cut-in-patterns/{id}", mGachaRenditionBeforeShootCutInPattern.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionBeforeShootCutInPattern> mGachaRenditionBeforeShootCutInPatternList = mGachaRenditionBeforeShootCutInPatternRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInPatternList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionBeforeShootCutInPattern.class);
        MGachaRenditionBeforeShootCutInPattern mGachaRenditionBeforeShootCutInPattern1 = new MGachaRenditionBeforeShootCutInPattern();
        mGachaRenditionBeforeShootCutInPattern1.setId(1L);
        MGachaRenditionBeforeShootCutInPattern mGachaRenditionBeforeShootCutInPattern2 = new MGachaRenditionBeforeShootCutInPattern();
        mGachaRenditionBeforeShootCutInPattern2.setId(mGachaRenditionBeforeShootCutInPattern1.getId());
        assertThat(mGachaRenditionBeforeShootCutInPattern1).isEqualTo(mGachaRenditionBeforeShootCutInPattern2);
        mGachaRenditionBeforeShootCutInPattern2.setId(2L);
        assertThat(mGachaRenditionBeforeShootCutInPattern1).isNotEqualTo(mGachaRenditionBeforeShootCutInPattern2);
        mGachaRenditionBeforeShootCutInPattern1.setId(null);
        assertThat(mGachaRenditionBeforeShootCutInPattern1).isNotEqualTo(mGachaRenditionBeforeShootCutInPattern2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionBeforeShootCutInPatternDTO.class);
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO1 = new MGachaRenditionBeforeShootCutInPatternDTO();
        mGachaRenditionBeforeShootCutInPatternDTO1.setId(1L);
        MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO2 = new MGachaRenditionBeforeShootCutInPatternDTO();
        assertThat(mGachaRenditionBeforeShootCutInPatternDTO1).isNotEqualTo(mGachaRenditionBeforeShootCutInPatternDTO2);
        mGachaRenditionBeforeShootCutInPatternDTO2.setId(mGachaRenditionBeforeShootCutInPatternDTO1.getId());
        assertThat(mGachaRenditionBeforeShootCutInPatternDTO1).isEqualTo(mGachaRenditionBeforeShootCutInPatternDTO2);
        mGachaRenditionBeforeShootCutInPatternDTO2.setId(2L);
        assertThat(mGachaRenditionBeforeShootCutInPatternDTO1).isNotEqualTo(mGachaRenditionBeforeShootCutInPatternDTO2);
        mGachaRenditionBeforeShootCutInPatternDTO1.setId(null);
        assertThat(mGachaRenditionBeforeShootCutInPatternDTO1).isNotEqualTo(mGachaRenditionBeforeShootCutInPatternDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionBeforeShootCutInPatternMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionBeforeShootCutInPatternMapper.fromId(null)).isNull();
    }
}
