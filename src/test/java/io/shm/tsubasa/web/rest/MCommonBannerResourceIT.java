package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCommonBanner;
import io.shm.tsubasa.repository.MCommonBannerRepository;
import io.shm.tsubasa.service.MCommonBannerService;
import io.shm.tsubasa.service.dto.MCommonBannerDTO;
import io.shm.tsubasa.service.mapper.MCommonBannerMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCommonBannerCriteria;
import io.shm.tsubasa.service.MCommonBannerQueryService;

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
 * Integration tests for the {@Link MCommonBannerResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCommonBannerResourceIT {

    private static final String DEFAULT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    private static final Integer DEFAULT_LABEL_END_AT = 1;
    private static final Integer UPDATED_LABEL_END_AT = 2;

    private static final Integer DEFAULT_SCENE_TRANSITION = 1;
    private static final Integer UPDATED_SCENE_TRANSITION = 2;

    private static final String DEFAULT_SCENE_TRANSITION_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_SCENE_TRANSITION_PARAM = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    private static final Integer DEFAULT_APPEAL_TYPE = 1;
    private static final Integer UPDATED_APPEAL_TYPE = 2;

    private static final Integer DEFAULT_APPEAL_CONTENT_ID = 1;
    private static final Integer UPDATED_APPEAL_CONTENT_ID = 2;

    @Autowired
    private MCommonBannerRepository mCommonBannerRepository;

    @Autowired
    private MCommonBannerMapper mCommonBannerMapper;

    @Autowired
    private MCommonBannerService mCommonBannerService;

    @Autowired
    private MCommonBannerQueryService mCommonBannerQueryService;

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

    private MockMvc restMCommonBannerMockMvc;

    private MCommonBanner mCommonBanner;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCommonBannerResource mCommonBannerResource = new MCommonBannerResource(mCommonBannerService, mCommonBannerQueryService);
        this.restMCommonBannerMockMvc = MockMvcBuilders.standaloneSetup(mCommonBannerResource)
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
    public static MCommonBanner createEntity(EntityManager em) {
        MCommonBanner mCommonBanner = new MCommonBanner()
            .assetName(DEFAULT_ASSET_NAME)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT)
            .labelEndAt(DEFAULT_LABEL_END_AT)
            .sceneTransition(DEFAULT_SCENE_TRANSITION)
            .sceneTransitionParam(DEFAULT_SCENE_TRANSITION_PARAM)
            .orderNum(DEFAULT_ORDER_NUM)
            .appealType(DEFAULT_APPEAL_TYPE)
            .appealContentId(DEFAULT_APPEAL_CONTENT_ID);
        return mCommonBanner;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCommonBanner createUpdatedEntity(EntityManager em) {
        MCommonBanner mCommonBanner = new MCommonBanner()
            .assetName(UPDATED_ASSET_NAME)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .labelEndAt(UPDATED_LABEL_END_AT)
            .sceneTransition(UPDATED_SCENE_TRANSITION)
            .sceneTransitionParam(UPDATED_SCENE_TRANSITION_PARAM)
            .orderNum(UPDATED_ORDER_NUM)
            .appealType(UPDATED_APPEAL_TYPE)
            .appealContentId(UPDATED_APPEAL_CONTENT_ID);
        return mCommonBanner;
    }

    @BeforeEach
    public void initTest() {
        mCommonBanner = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCommonBanner() throws Exception {
        int databaseSizeBeforeCreate = mCommonBannerRepository.findAll().size();

        // Create the MCommonBanner
        MCommonBannerDTO mCommonBannerDTO = mCommonBannerMapper.toDto(mCommonBanner);
        restMCommonBannerMockMvc.perform(post("/api/m-common-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCommonBannerDTO)))
            .andExpect(status().isCreated());

        // Validate the MCommonBanner in the database
        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeCreate + 1);
        MCommonBanner testMCommonBanner = mCommonBannerList.get(mCommonBannerList.size() - 1);
        assertThat(testMCommonBanner.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testMCommonBanner.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMCommonBanner.getEndAt()).isEqualTo(DEFAULT_END_AT);
        assertThat(testMCommonBanner.getLabelEndAt()).isEqualTo(DEFAULT_LABEL_END_AT);
        assertThat(testMCommonBanner.getSceneTransition()).isEqualTo(DEFAULT_SCENE_TRANSITION);
        assertThat(testMCommonBanner.getSceneTransitionParam()).isEqualTo(DEFAULT_SCENE_TRANSITION_PARAM);
        assertThat(testMCommonBanner.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMCommonBanner.getAppealType()).isEqualTo(DEFAULT_APPEAL_TYPE);
        assertThat(testMCommonBanner.getAppealContentId()).isEqualTo(DEFAULT_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void createMCommonBannerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCommonBannerRepository.findAll().size();

        // Create the MCommonBanner with an existing ID
        mCommonBanner.setId(1L);
        MCommonBannerDTO mCommonBannerDTO = mCommonBannerMapper.toDto(mCommonBanner);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCommonBannerMockMvc.perform(post("/api/m-common-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCommonBannerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCommonBanner in the database
        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCommonBannerRepository.findAll().size();
        // set the field null
        mCommonBanner.setStartAt(null);

        // Create the MCommonBanner, which fails.
        MCommonBannerDTO mCommonBannerDTO = mCommonBannerMapper.toDto(mCommonBanner);

        restMCommonBannerMockMvc.perform(post("/api/m-common-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCommonBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCommonBannerRepository.findAll().size();
        // set the field null
        mCommonBanner.setEndAt(null);

        // Create the MCommonBanner, which fails.
        MCommonBannerDTO mCommonBannerDTO = mCommonBannerMapper.toDto(mCommonBanner);

        restMCommonBannerMockMvc.perform(post("/api/m-common-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCommonBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLabelEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCommonBannerRepository.findAll().size();
        // set the field null
        mCommonBanner.setLabelEndAt(null);

        // Create the MCommonBanner, which fails.
        MCommonBannerDTO mCommonBannerDTO = mCommonBannerMapper.toDto(mCommonBanner);

        restMCommonBannerMockMvc.perform(post("/api/m-common-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCommonBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSceneTransitionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCommonBannerRepository.findAll().size();
        // set the field null
        mCommonBanner.setSceneTransition(null);

        // Create the MCommonBanner, which fails.
        MCommonBannerDTO mCommonBannerDTO = mCommonBannerMapper.toDto(mCommonBanner);

        restMCommonBannerMockMvc.perform(post("/api/m-common-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCommonBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCommonBannerRepository.findAll().size();
        // set the field null
        mCommonBanner.setOrderNum(null);

        // Create the MCommonBanner, which fails.
        MCommonBannerDTO mCommonBannerDTO = mCommonBannerMapper.toDto(mCommonBanner);

        restMCommonBannerMockMvc.perform(post("/api/m-common-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCommonBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCommonBanners() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList
        restMCommonBannerMockMvc.perform(get("/api/m-common-banners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCommonBanner.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].labelEndAt").value(hasItem(DEFAULT_LABEL_END_AT)))
            .andExpect(jsonPath("$.[*].sceneTransition").value(hasItem(DEFAULT_SCENE_TRANSITION)))
            .andExpect(jsonPath("$.[*].sceneTransitionParam").value(hasItem(DEFAULT_SCENE_TRANSITION_PARAM.toString())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].appealType").value(hasItem(DEFAULT_APPEAL_TYPE)))
            .andExpect(jsonPath("$.[*].appealContentId").value(hasItem(DEFAULT_APPEAL_CONTENT_ID)));
    }
    
    @Test
    @Transactional
    public void getMCommonBanner() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get the mCommonBanner
        restMCommonBannerMockMvc.perform(get("/api/m-common-banners/{id}", mCommonBanner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCommonBanner.getId().intValue()))
            .andExpect(jsonPath("$.assetName").value(DEFAULT_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT))
            .andExpect(jsonPath("$.labelEndAt").value(DEFAULT_LABEL_END_AT))
            .andExpect(jsonPath("$.sceneTransition").value(DEFAULT_SCENE_TRANSITION))
            .andExpect(jsonPath("$.sceneTransitionParam").value(DEFAULT_SCENE_TRANSITION_PARAM.toString()))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.appealType").value(DEFAULT_APPEAL_TYPE))
            .andExpect(jsonPath("$.appealContentId").value(DEFAULT_APPEAL_CONTENT_ID));
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where startAt equals to DEFAULT_START_AT
        defaultMCommonBannerShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mCommonBannerList where startAt equals to UPDATED_START_AT
        defaultMCommonBannerShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMCommonBannerShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mCommonBannerList where startAt equals to UPDATED_START_AT
        defaultMCommonBannerShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where startAt is not null
        defaultMCommonBannerShouldBeFound("startAt.specified=true");

        // Get all the mCommonBannerList where startAt is null
        defaultMCommonBannerShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where startAt greater than or equals to DEFAULT_START_AT
        defaultMCommonBannerShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mCommonBannerList where startAt greater than or equals to UPDATED_START_AT
        defaultMCommonBannerShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where startAt less than or equals to DEFAULT_START_AT
        defaultMCommonBannerShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mCommonBannerList where startAt less than or equals to UPDATED_START_AT
        defaultMCommonBannerShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMCommonBannersByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where endAt equals to DEFAULT_END_AT
        defaultMCommonBannerShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mCommonBannerList where endAt equals to UPDATED_END_AT
        defaultMCommonBannerShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMCommonBannerShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mCommonBannerList where endAt equals to UPDATED_END_AT
        defaultMCommonBannerShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where endAt is not null
        defaultMCommonBannerShouldBeFound("endAt.specified=true");

        // Get all the mCommonBannerList where endAt is null
        defaultMCommonBannerShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where endAt greater than or equals to DEFAULT_END_AT
        defaultMCommonBannerShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mCommonBannerList where endAt greater than or equals to UPDATED_END_AT
        defaultMCommonBannerShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where endAt less than or equals to DEFAULT_END_AT
        defaultMCommonBannerShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mCommonBannerList where endAt less than or equals to UPDATED_END_AT
        defaultMCommonBannerShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }


    @Test
    @Transactional
    public void getAllMCommonBannersByLabelEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where labelEndAt equals to DEFAULT_LABEL_END_AT
        defaultMCommonBannerShouldBeFound("labelEndAt.equals=" + DEFAULT_LABEL_END_AT);

        // Get all the mCommonBannerList where labelEndAt equals to UPDATED_LABEL_END_AT
        defaultMCommonBannerShouldNotBeFound("labelEndAt.equals=" + UPDATED_LABEL_END_AT);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByLabelEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where labelEndAt in DEFAULT_LABEL_END_AT or UPDATED_LABEL_END_AT
        defaultMCommonBannerShouldBeFound("labelEndAt.in=" + DEFAULT_LABEL_END_AT + "," + UPDATED_LABEL_END_AT);

        // Get all the mCommonBannerList where labelEndAt equals to UPDATED_LABEL_END_AT
        defaultMCommonBannerShouldNotBeFound("labelEndAt.in=" + UPDATED_LABEL_END_AT);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByLabelEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where labelEndAt is not null
        defaultMCommonBannerShouldBeFound("labelEndAt.specified=true");

        // Get all the mCommonBannerList where labelEndAt is null
        defaultMCommonBannerShouldNotBeFound("labelEndAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByLabelEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where labelEndAt greater than or equals to DEFAULT_LABEL_END_AT
        defaultMCommonBannerShouldBeFound("labelEndAt.greaterOrEqualThan=" + DEFAULT_LABEL_END_AT);

        // Get all the mCommonBannerList where labelEndAt greater than or equals to UPDATED_LABEL_END_AT
        defaultMCommonBannerShouldNotBeFound("labelEndAt.greaterOrEqualThan=" + UPDATED_LABEL_END_AT);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByLabelEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where labelEndAt less than or equals to DEFAULT_LABEL_END_AT
        defaultMCommonBannerShouldNotBeFound("labelEndAt.lessThan=" + DEFAULT_LABEL_END_AT);

        // Get all the mCommonBannerList where labelEndAt less than or equals to UPDATED_LABEL_END_AT
        defaultMCommonBannerShouldBeFound("labelEndAt.lessThan=" + UPDATED_LABEL_END_AT);
    }


    @Test
    @Transactional
    public void getAllMCommonBannersBySceneTransitionIsEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where sceneTransition equals to DEFAULT_SCENE_TRANSITION
        defaultMCommonBannerShouldBeFound("sceneTransition.equals=" + DEFAULT_SCENE_TRANSITION);

        // Get all the mCommonBannerList where sceneTransition equals to UPDATED_SCENE_TRANSITION
        defaultMCommonBannerShouldNotBeFound("sceneTransition.equals=" + UPDATED_SCENE_TRANSITION);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersBySceneTransitionIsInShouldWork() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where sceneTransition in DEFAULT_SCENE_TRANSITION or UPDATED_SCENE_TRANSITION
        defaultMCommonBannerShouldBeFound("sceneTransition.in=" + DEFAULT_SCENE_TRANSITION + "," + UPDATED_SCENE_TRANSITION);

        // Get all the mCommonBannerList where sceneTransition equals to UPDATED_SCENE_TRANSITION
        defaultMCommonBannerShouldNotBeFound("sceneTransition.in=" + UPDATED_SCENE_TRANSITION);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersBySceneTransitionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where sceneTransition is not null
        defaultMCommonBannerShouldBeFound("sceneTransition.specified=true");

        // Get all the mCommonBannerList where sceneTransition is null
        defaultMCommonBannerShouldNotBeFound("sceneTransition.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCommonBannersBySceneTransitionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where sceneTransition greater than or equals to DEFAULT_SCENE_TRANSITION
        defaultMCommonBannerShouldBeFound("sceneTransition.greaterOrEqualThan=" + DEFAULT_SCENE_TRANSITION);

        // Get all the mCommonBannerList where sceneTransition greater than or equals to UPDATED_SCENE_TRANSITION
        defaultMCommonBannerShouldNotBeFound("sceneTransition.greaterOrEqualThan=" + UPDATED_SCENE_TRANSITION);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersBySceneTransitionIsLessThanSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where sceneTransition less than or equals to DEFAULT_SCENE_TRANSITION
        defaultMCommonBannerShouldNotBeFound("sceneTransition.lessThan=" + DEFAULT_SCENE_TRANSITION);

        // Get all the mCommonBannerList where sceneTransition less than or equals to UPDATED_SCENE_TRANSITION
        defaultMCommonBannerShouldBeFound("sceneTransition.lessThan=" + UPDATED_SCENE_TRANSITION);
    }


    @Test
    @Transactional
    public void getAllMCommonBannersByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMCommonBannerShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mCommonBannerList where orderNum equals to UPDATED_ORDER_NUM
        defaultMCommonBannerShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMCommonBannerShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mCommonBannerList where orderNum equals to UPDATED_ORDER_NUM
        defaultMCommonBannerShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where orderNum is not null
        defaultMCommonBannerShouldBeFound("orderNum.specified=true");

        // Get all the mCommonBannerList where orderNum is null
        defaultMCommonBannerShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMCommonBannerShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mCommonBannerList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMCommonBannerShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMCommonBannerShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mCommonBannerList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMCommonBannerShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMCommonBannersByAppealTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealType equals to DEFAULT_APPEAL_TYPE
        defaultMCommonBannerShouldBeFound("appealType.equals=" + DEFAULT_APPEAL_TYPE);

        // Get all the mCommonBannerList where appealType equals to UPDATED_APPEAL_TYPE
        defaultMCommonBannerShouldNotBeFound("appealType.equals=" + UPDATED_APPEAL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByAppealTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealType in DEFAULT_APPEAL_TYPE or UPDATED_APPEAL_TYPE
        defaultMCommonBannerShouldBeFound("appealType.in=" + DEFAULT_APPEAL_TYPE + "," + UPDATED_APPEAL_TYPE);

        // Get all the mCommonBannerList where appealType equals to UPDATED_APPEAL_TYPE
        defaultMCommonBannerShouldNotBeFound("appealType.in=" + UPDATED_APPEAL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByAppealTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealType is not null
        defaultMCommonBannerShouldBeFound("appealType.specified=true");

        // Get all the mCommonBannerList where appealType is null
        defaultMCommonBannerShouldNotBeFound("appealType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByAppealTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealType greater than or equals to DEFAULT_APPEAL_TYPE
        defaultMCommonBannerShouldBeFound("appealType.greaterOrEqualThan=" + DEFAULT_APPEAL_TYPE);

        // Get all the mCommonBannerList where appealType greater than or equals to UPDATED_APPEAL_TYPE
        defaultMCommonBannerShouldNotBeFound("appealType.greaterOrEqualThan=" + UPDATED_APPEAL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByAppealTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealType less than or equals to DEFAULT_APPEAL_TYPE
        defaultMCommonBannerShouldNotBeFound("appealType.lessThan=" + DEFAULT_APPEAL_TYPE);

        // Get all the mCommonBannerList where appealType less than or equals to UPDATED_APPEAL_TYPE
        defaultMCommonBannerShouldBeFound("appealType.lessThan=" + UPDATED_APPEAL_TYPE);
    }


    @Test
    @Transactional
    public void getAllMCommonBannersByAppealContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealContentId equals to DEFAULT_APPEAL_CONTENT_ID
        defaultMCommonBannerShouldBeFound("appealContentId.equals=" + DEFAULT_APPEAL_CONTENT_ID);

        // Get all the mCommonBannerList where appealContentId equals to UPDATED_APPEAL_CONTENT_ID
        defaultMCommonBannerShouldNotBeFound("appealContentId.equals=" + UPDATED_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByAppealContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealContentId in DEFAULT_APPEAL_CONTENT_ID or UPDATED_APPEAL_CONTENT_ID
        defaultMCommonBannerShouldBeFound("appealContentId.in=" + DEFAULT_APPEAL_CONTENT_ID + "," + UPDATED_APPEAL_CONTENT_ID);

        // Get all the mCommonBannerList where appealContentId equals to UPDATED_APPEAL_CONTENT_ID
        defaultMCommonBannerShouldNotBeFound("appealContentId.in=" + UPDATED_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByAppealContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealContentId is not null
        defaultMCommonBannerShouldBeFound("appealContentId.specified=true");

        // Get all the mCommonBannerList where appealContentId is null
        defaultMCommonBannerShouldNotBeFound("appealContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByAppealContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealContentId greater than or equals to DEFAULT_APPEAL_CONTENT_ID
        defaultMCommonBannerShouldBeFound("appealContentId.greaterOrEqualThan=" + DEFAULT_APPEAL_CONTENT_ID);

        // Get all the mCommonBannerList where appealContentId greater than or equals to UPDATED_APPEAL_CONTENT_ID
        defaultMCommonBannerShouldNotBeFound("appealContentId.greaterOrEqualThan=" + UPDATED_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMCommonBannersByAppealContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        // Get all the mCommonBannerList where appealContentId less than or equals to DEFAULT_APPEAL_CONTENT_ID
        defaultMCommonBannerShouldNotBeFound("appealContentId.lessThan=" + DEFAULT_APPEAL_CONTENT_ID);

        // Get all the mCommonBannerList where appealContentId less than or equals to UPDATED_APPEAL_CONTENT_ID
        defaultMCommonBannerShouldBeFound("appealContentId.lessThan=" + UPDATED_APPEAL_CONTENT_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCommonBannerShouldBeFound(String filter) throws Exception {
        restMCommonBannerMockMvc.perform(get("/api/m-common-banners?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCommonBanner.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].labelEndAt").value(hasItem(DEFAULT_LABEL_END_AT)))
            .andExpect(jsonPath("$.[*].sceneTransition").value(hasItem(DEFAULT_SCENE_TRANSITION)))
            .andExpect(jsonPath("$.[*].sceneTransitionParam").value(hasItem(DEFAULT_SCENE_TRANSITION_PARAM.toString())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].appealType").value(hasItem(DEFAULT_APPEAL_TYPE)))
            .andExpect(jsonPath("$.[*].appealContentId").value(hasItem(DEFAULT_APPEAL_CONTENT_ID)));

        // Check, that the count call also returns 1
        restMCommonBannerMockMvc.perform(get("/api/m-common-banners/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCommonBannerShouldNotBeFound(String filter) throws Exception {
        restMCommonBannerMockMvc.perform(get("/api/m-common-banners?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCommonBannerMockMvc.perform(get("/api/m-common-banners/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCommonBanner() throws Exception {
        // Get the mCommonBanner
        restMCommonBannerMockMvc.perform(get("/api/m-common-banners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCommonBanner() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        int databaseSizeBeforeUpdate = mCommonBannerRepository.findAll().size();

        // Update the mCommonBanner
        MCommonBanner updatedMCommonBanner = mCommonBannerRepository.findById(mCommonBanner.getId()).get();
        // Disconnect from session so that the updates on updatedMCommonBanner are not directly saved in db
        em.detach(updatedMCommonBanner);
        updatedMCommonBanner
            .assetName(UPDATED_ASSET_NAME)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .labelEndAt(UPDATED_LABEL_END_AT)
            .sceneTransition(UPDATED_SCENE_TRANSITION)
            .sceneTransitionParam(UPDATED_SCENE_TRANSITION_PARAM)
            .orderNum(UPDATED_ORDER_NUM)
            .appealType(UPDATED_APPEAL_TYPE)
            .appealContentId(UPDATED_APPEAL_CONTENT_ID);
        MCommonBannerDTO mCommonBannerDTO = mCommonBannerMapper.toDto(updatedMCommonBanner);

        restMCommonBannerMockMvc.perform(put("/api/m-common-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCommonBannerDTO)))
            .andExpect(status().isOk());

        // Validate the MCommonBanner in the database
        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeUpdate);
        MCommonBanner testMCommonBanner = mCommonBannerList.get(mCommonBannerList.size() - 1);
        assertThat(testMCommonBanner.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testMCommonBanner.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMCommonBanner.getEndAt()).isEqualTo(UPDATED_END_AT);
        assertThat(testMCommonBanner.getLabelEndAt()).isEqualTo(UPDATED_LABEL_END_AT);
        assertThat(testMCommonBanner.getSceneTransition()).isEqualTo(UPDATED_SCENE_TRANSITION);
        assertThat(testMCommonBanner.getSceneTransitionParam()).isEqualTo(UPDATED_SCENE_TRANSITION_PARAM);
        assertThat(testMCommonBanner.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMCommonBanner.getAppealType()).isEqualTo(UPDATED_APPEAL_TYPE);
        assertThat(testMCommonBanner.getAppealContentId()).isEqualTo(UPDATED_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMCommonBanner() throws Exception {
        int databaseSizeBeforeUpdate = mCommonBannerRepository.findAll().size();

        // Create the MCommonBanner
        MCommonBannerDTO mCommonBannerDTO = mCommonBannerMapper.toDto(mCommonBanner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCommonBannerMockMvc.perform(put("/api/m-common-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCommonBannerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCommonBanner in the database
        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCommonBanner() throws Exception {
        // Initialize the database
        mCommonBannerRepository.saveAndFlush(mCommonBanner);

        int databaseSizeBeforeDelete = mCommonBannerRepository.findAll().size();

        // Delete the mCommonBanner
        restMCommonBannerMockMvc.perform(delete("/api/m-common-banners/{id}", mCommonBanner.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCommonBanner> mCommonBannerList = mCommonBannerRepository.findAll();
        assertThat(mCommonBannerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCommonBanner.class);
        MCommonBanner mCommonBanner1 = new MCommonBanner();
        mCommonBanner1.setId(1L);
        MCommonBanner mCommonBanner2 = new MCommonBanner();
        mCommonBanner2.setId(mCommonBanner1.getId());
        assertThat(mCommonBanner1).isEqualTo(mCommonBanner2);
        mCommonBanner2.setId(2L);
        assertThat(mCommonBanner1).isNotEqualTo(mCommonBanner2);
        mCommonBanner1.setId(null);
        assertThat(mCommonBanner1).isNotEqualTo(mCommonBanner2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCommonBannerDTO.class);
        MCommonBannerDTO mCommonBannerDTO1 = new MCommonBannerDTO();
        mCommonBannerDTO1.setId(1L);
        MCommonBannerDTO mCommonBannerDTO2 = new MCommonBannerDTO();
        assertThat(mCommonBannerDTO1).isNotEqualTo(mCommonBannerDTO2);
        mCommonBannerDTO2.setId(mCommonBannerDTO1.getId());
        assertThat(mCommonBannerDTO1).isEqualTo(mCommonBannerDTO2);
        mCommonBannerDTO2.setId(2L);
        assertThat(mCommonBannerDTO1).isNotEqualTo(mCommonBannerDTO2);
        mCommonBannerDTO1.setId(null);
        assertThat(mCommonBannerDTO1).isNotEqualTo(mCommonBannerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCommonBannerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCommonBannerMapper.fromId(null)).isNull();
    }
}
