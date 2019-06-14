package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTips;
import io.shm.tsubasa.repository.MTipsRepository;
import io.shm.tsubasa.service.MTipsService;
import io.shm.tsubasa.service.dto.MTipsDTO;
import io.shm.tsubasa.service.mapper.MTipsMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTipsCriteria;
import io.shm.tsubasa.service.MTipsQueryService;

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
 * Integration tests for the {@Link MTipsResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTipsResourceIT {

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_ASSET_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_COLOR_TYPE = 1;
    private static final Integer UPDATED_COLOR_TYPE = 2;

    private static final Integer DEFAULT_IS_TIPS = 1;
    private static final Integer UPDATED_IS_TIPS = 2;

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    @Autowired
    private MTipsRepository mTipsRepository;

    @Autowired
    private MTipsMapper mTipsMapper;

    @Autowired
    private MTipsService mTipsService;

    @Autowired
    private MTipsQueryService mTipsQueryService;

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

    private MockMvc restMTipsMockMvc;

    private MTips mTips;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTipsResource mTipsResource = new MTipsResource(mTipsService, mTipsQueryService);
        this.restMTipsMockMvc = MockMvcBuilders.standaloneSetup(mTipsResource)
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
    public static MTips createEntity(EntityManager em) {
        MTips mTips = new MTips()
            .priority(DEFAULT_PRIORITY)
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .imageAssetName(DEFAULT_IMAGE_ASSET_NAME)
            .colorType(DEFAULT_COLOR_TYPE)
            .isTips(DEFAULT_IS_TIPS)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT);
        return mTips;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTips createUpdatedEntity(EntityManager em) {
        MTips mTips = new MTips()
            .priority(UPDATED_PRIORITY)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .imageAssetName(UPDATED_IMAGE_ASSET_NAME)
            .colorType(UPDATED_COLOR_TYPE)
            .isTips(UPDATED_IS_TIPS)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        return mTips;
    }

    @BeforeEach
    public void initTest() {
        mTips = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTips() throws Exception {
        int databaseSizeBeforeCreate = mTipsRepository.findAll().size();

        // Create the MTips
        MTipsDTO mTipsDTO = mTipsMapper.toDto(mTips);
        restMTipsMockMvc.perform(post("/api/m-tips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTipsDTO)))
            .andExpect(status().isCreated());

        // Validate the MTips in the database
        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeCreate + 1);
        MTips testMTips = mTipsList.get(mTipsList.size() - 1);
        assertThat(testMTips.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testMTips.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMTips.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMTips.getImageAssetName()).isEqualTo(DEFAULT_IMAGE_ASSET_NAME);
        assertThat(testMTips.getColorType()).isEqualTo(DEFAULT_COLOR_TYPE);
        assertThat(testMTips.getIsTips()).isEqualTo(DEFAULT_IS_TIPS);
        assertThat(testMTips.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMTips.getEndAt()).isEqualTo(DEFAULT_END_AT);
    }

    @Test
    @Transactional
    public void createMTipsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTipsRepository.findAll().size();

        // Create the MTips with an existing ID
        mTips.setId(1L);
        MTipsDTO mTipsDTO = mTipsMapper.toDto(mTips);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTipsMockMvc.perform(post("/api/m-tips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTipsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTips in the database
        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPriorityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTipsRepository.findAll().size();
        // set the field null
        mTips.setPriority(null);

        // Create the MTips, which fails.
        MTipsDTO mTipsDTO = mTipsMapper.toDto(mTips);

        restMTipsMockMvc.perform(post("/api/m-tips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTipsDTO)))
            .andExpect(status().isBadRequest());

        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkColorTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTipsRepository.findAll().size();
        // set the field null
        mTips.setColorType(null);

        // Create the MTips, which fails.
        MTipsDTO mTipsDTO = mTipsMapper.toDto(mTips);

        restMTipsMockMvc.perform(post("/api/m-tips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTipsDTO)))
            .andExpect(status().isBadRequest());

        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsTipsIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTipsRepository.findAll().size();
        // set the field null
        mTips.setIsTips(null);

        // Create the MTips, which fails.
        MTipsDTO mTipsDTO = mTipsMapper.toDto(mTips);

        restMTipsMockMvc.perform(post("/api/m-tips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTipsDTO)))
            .andExpect(status().isBadRequest());

        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTipsRepository.findAll().size();
        // set the field null
        mTips.setStartAt(null);

        // Create the MTips, which fails.
        MTipsDTO mTipsDTO = mTipsMapper.toDto(mTips);

        restMTipsMockMvc.perform(post("/api/m-tips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTipsDTO)))
            .andExpect(status().isBadRequest());

        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTipsRepository.findAll().size();
        // set the field null
        mTips.setEndAt(null);

        // Create the MTips, which fails.
        MTipsDTO mTipsDTO = mTipsMapper.toDto(mTips);

        restMTipsMockMvc.perform(post("/api/m-tips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTipsDTO)))
            .andExpect(status().isBadRequest());

        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTips() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList
        restMTipsMockMvc.perform(get("/api/m-tips?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTips.getId().intValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].imageAssetName").value(hasItem(DEFAULT_IMAGE_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].colorType").value(hasItem(DEFAULT_COLOR_TYPE)))
            .andExpect(jsonPath("$.[*].isTips").value(hasItem(DEFAULT_IS_TIPS)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));
    }
    
    @Test
    @Transactional
    public void getMTips() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get the mTips
        restMTipsMockMvc.perform(get("/api/m-tips/{id}", mTips.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTips.getId().intValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.imageAssetName").value(DEFAULT_IMAGE_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.colorType").value(DEFAULT_COLOR_TYPE))
            .andExpect(jsonPath("$.isTips").value(DEFAULT_IS_TIPS))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT));
    }

    @Test
    @Transactional
    public void getAllMTipsByPriorityIsEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where priority equals to DEFAULT_PRIORITY
        defaultMTipsShouldBeFound("priority.equals=" + DEFAULT_PRIORITY);

        // Get all the mTipsList where priority equals to UPDATED_PRIORITY
        defaultMTipsShouldNotBeFound("priority.equals=" + UPDATED_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllMTipsByPriorityIsInShouldWork() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where priority in DEFAULT_PRIORITY or UPDATED_PRIORITY
        defaultMTipsShouldBeFound("priority.in=" + DEFAULT_PRIORITY + "," + UPDATED_PRIORITY);

        // Get all the mTipsList where priority equals to UPDATED_PRIORITY
        defaultMTipsShouldNotBeFound("priority.in=" + UPDATED_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllMTipsByPriorityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where priority is not null
        defaultMTipsShouldBeFound("priority.specified=true");

        // Get all the mTipsList where priority is null
        defaultMTipsShouldNotBeFound("priority.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTipsByPriorityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where priority greater than or equals to DEFAULT_PRIORITY
        defaultMTipsShouldBeFound("priority.greaterOrEqualThan=" + DEFAULT_PRIORITY);

        // Get all the mTipsList where priority greater than or equals to UPDATED_PRIORITY
        defaultMTipsShouldNotBeFound("priority.greaterOrEqualThan=" + UPDATED_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllMTipsByPriorityIsLessThanSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where priority less than or equals to DEFAULT_PRIORITY
        defaultMTipsShouldNotBeFound("priority.lessThan=" + DEFAULT_PRIORITY);

        // Get all the mTipsList where priority less than or equals to UPDATED_PRIORITY
        defaultMTipsShouldBeFound("priority.lessThan=" + UPDATED_PRIORITY);
    }


    @Test
    @Transactional
    public void getAllMTipsByColorTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where colorType equals to DEFAULT_COLOR_TYPE
        defaultMTipsShouldBeFound("colorType.equals=" + DEFAULT_COLOR_TYPE);

        // Get all the mTipsList where colorType equals to UPDATED_COLOR_TYPE
        defaultMTipsShouldNotBeFound("colorType.equals=" + UPDATED_COLOR_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTipsByColorTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where colorType in DEFAULT_COLOR_TYPE or UPDATED_COLOR_TYPE
        defaultMTipsShouldBeFound("colorType.in=" + DEFAULT_COLOR_TYPE + "," + UPDATED_COLOR_TYPE);

        // Get all the mTipsList where colorType equals to UPDATED_COLOR_TYPE
        defaultMTipsShouldNotBeFound("colorType.in=" + UPDATED_COLOR_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTipsByColorTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where colorType is not null
        defaultMTipsShouldBeFound("colorType.specified=true");

        // Get all the mTipsList where colorType is null
        defaultMTipsShouldNotBeFound("colorType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTipsByColorTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where colorType greater than or equals to DEFAULT_COLOR_TYPE
        defaultMTipsShouldBeFound("colorType.greaterOrEqualThan=" + DEFAULT_COLOR_TYPE);

        // Get all the mTipsList where colorType greater than or equals to UPDATED_COLOR_TYPE
        defaultMTipsShouldNotBeFound("colorType.greaterOrEqualThan=" + UPDATED_COLOR_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTipsByColorTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where colorType less than or equals to DEFAULT_COLOR_TYPE
        defaultMTipsShouldNotBeFound("colorType.lessThan=" + DEFAULT_COLOR_TYPE);

        // Get all the mTipsList where colorType less than or equals to UPDATED_COLOR_TYPE
        defaultMTipsShouldBeFound("colorType.lessThan=" + UPDATED_COLOR_TYPE);
    }


    @Test
    @Transactional
    public void getAllMTipsByIsTipsIsEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where isTips equals to DEFAULT_IS_TIPS
        defaultMTipsShouldBeFound("isTips.equals=" + DEFAULT_IS_TIPS);

        // Get all the mTipsList where isTips equals to UPDATED_IS_TIPS
        defaultMTipsShouldNotBeFound("isTips.equals=" + UPDATED_IS_TIPS);
    }

    @Test
    @Transactional
    public void getAllMTipsByIsTipsIsInShouldWork() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where isTips in DEFAULT_IS_TIPS or UPDATED_IS_TIPS
        defaultMTipsShouldBeFound("isTips.in=" + DEFAULT_IS_TIPS + "," + UPDATED_IS_TIPS);

        // Get all the mTipsList where isTips equals to UPDATED_IS_TIPS
        defaultMTipsShouldNotBeFound("isTips.in=" + UPDATED_IS_TIPS);
    }

    @Test
    @Transactional
    public void getAllMTipsByIsTipsIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where isTips is not null
        defaultMTipsShouldBeFound("isTips.specified=true");

        // Get all the mTipsList where isTips is null
        defaultMTipsShouldNotBeFound("isTips.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTipsByIsTipsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where isTips greater than or equals to DEFAULT_IS_TIPS
        defaultMTipsShouldBeFound("isTips.greaterOrEqualThan=" + DEFAULT_IS_TIPS);

        // Get all the mTipsList where isTips greater than or equals to UPDATED_IS_TIPS
        defaultMTipsShouldNotBeFound("isTips.greaterOrEqualThan=" + UPDATED_IS_TIPS);
    }

    @Test
    @Transactional
    public void getAllMTipsByIsTipsIsLessThanSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where isTips less than or equals to DEFAULT_IS_TIPS
        defaultMTipsShouldNotBeFound("isTips.lessThan=" + DEFAULT_IS_TIPS);

        // Get all the mTipsList where isTips less than or equals to UPDATED_IS_TIPS
        defaultMTipsShouldBeFound("isTips.lessThan=" + UPDATED_IS_TIPS);
    }


    @Test
    @Transactional
    public void getAllMTipsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where startAt equals to DEFAULT_START_AT
        defaultMTipsShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mTipsList where startAt equals to UPDATED_START_AT
        defaultMTipsShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMTipsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMTipsShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mTipsList where startAt equals to UPDATED_START_AT
        defaultMTipsShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMTipsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where startAt is not null
        defaultMTipsShouldBeFound("startAt.specified=true");

        // Get all the mTipsList where startAt is null
        defaultMTipsShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTipsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where startAt greater than or equals to DEFAULT_START_AT
        defaultMTipsShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mTipsList where startAt greater than or equals to UPDATED_START_AT
        defaultMTipsShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMTipsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where startAt less than or equals to DEFAULT_START_AT
        defaultMTipsShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mTipsList where startAt less than or equals to UPDATED_START_AT
        defaultMTipsShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMTipsByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where endAt equals to DEFAULT_END_AT
        defaultMTipsShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mTipsList where endAt equals to UPDATED_END_AT
        defaultMTipsShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMTipsByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMTipsShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mTipsList where endAt equals to UPDATED_END_AT
        defaultMTipsShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMTipsByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where endAt is not null
        defaultMTipsShouldBeFound("endAt.specified=true");

        // Get all the mTipsList where endAt is null
        defaultMTipsShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTipsByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where endAt greater than or equals to DEFAULT_END_AT
        defaultMTipsShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mTipsList where endAt greater than or equals to UPDATED_END_AT
        defaultMTipsShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMTipsByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        // Get all the mTipsList where endAt less than or equals to DEFAULT_END_AT
        defaultMTipsShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mTipsList where endAt less than or equals to UPDATED_END_AT
        defaultMTipsShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTipsShouldBeFound(String filter) throws Exception {
        restMTipsMockMvc.perform(get("/api/m-tips?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTips.getId().intValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].imageAssetName").value(hasItem(DEFAULT_IMAGE_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].colorType").value(hasItem(DEFAULT_COLOR_TYPE)))
            .andExpect(jsonPath("$.[*].isTips").value(hasItem(DEFAULT_IS_TIPS)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));

        // Check, that the count call also returns 1
        restMTipsMockMvc.perform(get("/api/m-tips/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTipsShouldNotBeFound(String filter) throws Exception {
        restMTipsMockMvc.perform(get("/api/m-tips?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTipsMockMvc.perform(get("/api/m-tips/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTips() throws Exception {
        // Get the mTips
        restMTipsMockMvc.perform(get("/api/m-tips/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTips() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        int databaseSizeBeforeUpdate = mTipsRepository.findAll().size();

        // Update the mTips
        MTips updatedMTips = mTipsRepository.findById(mTips.getId()).get();
        // Disconnect from session so that the updates on updatedMTips are not directly saved in db
        em.detach(updatedMTips);
        updatedMTips
            .priority(UPDATED_PRIORITY)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .imageAssetName(UPDATED_IMAGE_ASSET_NAME)
            .colorType(UPDATED_COLOR_TYPE)
            .isTips(UPDATED_IS_TIPS)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        MTipsDTO mTipsDTO = mTipsMapper.toDto(updatedMTips);

        restMTipsMockMvc.perform(put("/api/m-tips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTipsDTO)))
            .andExpect(status().isOk());

        // Validate the MTips in the database
        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeUpdate);
        MTips testMTips = mTipsList.get(mTipsList.size() - 1);
        assertThat(testMTips.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testMTips.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMTips.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMTips.getImageAssetName()).isEqualTo(UPDATED_IMAGE_ASSET_NAME);
        assertThat(testMTips.getColorType()).isEqualTo(UPDATED_COLOR_TYPE);
        assertThat(testMTips.getIsTips()).isEqualTo(UPDATED_IS_TIPS);
        assertThat(testMTips.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMTips.getEndAt()).isEqualTo(UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMTips() throws Exception {
        int databaseSizeBeforeUpdate = mTipsRepository.findAll().size();

        // Create the MTips
        MTipsDTO mTipsDTO = mTipsMapper.toDto(mTips);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTipsMockMvc.perform(put("/api/m-tips")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTipsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTips in the database
        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTips() throws Exception {
        // Initialize the database
        mTipsRepository.saveAndFlush(mTips);

        int databaseSizeBeforeDelete = mTipsRepository.findAll().size();

        // Delete the mTips
        restMTipsMockMvc.perform(delete("/api/m-tips/{id}", mTips.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTips> mTipsList = mTipsRepository.findAll();
        assertThat(mTipsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTips.class);
        MTips mTips1 = new MTips();
        mTips1.setId(1L);
        MTips mTips2 = new MTips();
        mTips2.setId(mTips1.getId());
        assertThat(mTips1).isEqualTo(mTips2);
        mTips2.setId(2L);
        assertThat(mTips1).isNotEqualTo(mTips2);
        mTips1.setId(null);
        assertThat(mTips1).isNotEqualTo(mTips2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTipsDTO.class);
        MTipsDTO mTipsDTO1 = new MTipsDTO();
        mTipsDTO1.setId(1L);
        MTipsDTO mTipsDTO2 = new MTipsDTO();
        assertThat(mTipsDTO1).isNotEqualTo(mTipsDTO2);
        mTipsDTO2.setId(mTipsDTO1.getId());
        assertThat(mTipsDTO1).isEqualTo(mTipsDTO2);
        mTipsDTO2.setId(2L);
        assertThat(mTipsDTO1).isNotEqualTo(mTipsDTO2);
        mTipsDTO1.setId(null);
        assertThat(mTipsDTO1).isNotEqualTo(mTipsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTipsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTipsMapper.fromId(null)).isNull();
    }
}
