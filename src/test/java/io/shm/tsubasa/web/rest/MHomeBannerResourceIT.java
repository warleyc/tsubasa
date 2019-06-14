package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MHomeBanner;
import io.shm.tsubasa.repository.MHomeBannerRepository;
import io.shm.tsubasa.service.MHomeBannerService;
import io.shm.tsubasa.service.dto.MHomeBannerDTO;
import io.shm.tsubasa.service.mapper.MHomeBannerMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MHomeBannerCriteria;
import io.shm.tsubasa.service.MHomeBannerQueryService;

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
 * Integration tests for the {@Link MHomeBannerResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MHomeBannerResourceIT {

    private static final Integer DEFAULT_BANNER_TYPE = 1;
    private static final Integer UPDATED_BANNER_TYPE = 2;

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
    private MHomeBannerRepository mHomeBannerRepository;

    @Autowired
    private MHomeBannerMapper mHomeBannerMapper;

    @Autowired
    private MHomeBannerService mHomeBannerService;

    @Autowired
    private MHomeBannerQueryService mHomeBannerQueryService;

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

    private MockMvc restMHomeBannerMockMvc;

    private MHomeBanner mHomeBanner;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MHomeBannerResource mHomeBannerResource = new MHomeBannerResource(mHomeBannerService, mHomeBannerQueryService);
        this.restMHomeBannerMockMvc = MockMvcBuilders.standaloneSetup(mHomeBannerResource)
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
    public static MHomeBanner createEntity(EntityManager em) {
        MHomeBanner mHomeBanner = new MHomeBanner()
            .bannerType(DEFAULT_BANNER_TYPE)
            .assetName(DEFAULT_ASSET_NAME)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT)
            .labelEndAt(DEFAULT_LABEL_END_AT)
            .sceneTransition(DEFAULT_SCENE_TRANSITION)
            .sceneTransitionParam(DEFAULT_SCENE_TRANSITION_PARAM)
            .orderNum(DEFAULT_ORDER_NUM)
            .appealType(DEFAULT_APPEAL_TYPE)
            .appealContentId(DEFAULT_APPEAL_CONTENT_ID);
        return mHomeBanner;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MHomeBanner createUpdatedEntity(EntityManager em) {
        MHomeBanner mHomeBanner = new MHomeBanner()
            .bannerType(UPDATED_BANNER_TYPE)
            .assetName(UPDATED_ASSET_NAME)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .labelEndAt(UPDATED_LABEL_END_AT)
            .sceneTransition(UPDATED_SCENE_TRANSITION)
            .sceneTransitionParam(UPDATED_SCENE_TRANSITION_PARAM)
            .orderNum(UPDATED_ORDER_NUM)
            .appealType(UPDATED_APPEAL_TYPE)
            .appealContentId(UPDATED_APPEAL_CONTENT_ID);
        return mHomeBanner;
    }

    @BeforeEach
    public void initTest() {
        mHomeBanner = createEntity(em);
    }

    @Test
    @Transactional
    public void createMHomeBanner() throws Exception {
        int databaseSizeBeforeCreate = mHomeBannerRepository.findAll().size();

        // Create the MHomeBanner
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(mHomeBanner);
        restMHomeBannerMockMvc.perform(post("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isCreated());

        // Validate the MHomeBanner in the database
        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeCreate + 1);
        MHomeBanner testMHomeBanner = mHomeBannerList.get(mHomeBannerList.size() - 1);
        assertThat(testMHomeBanner.getBannerType()).isEqualTo(DEFAULT_BANNER_TYPE);
        assertThat(testMHomeBanner.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testMHomeBanner.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMHomeBanner.getEndAt()).isEqualTo(DEFAULT_END_AT);
        assertThat(testMHomeBanner.getLabelEndAt()).isEqualTo(DEFAULT_LABEL_END_AT);
        assertThat(testMHomeBanner.getSceneTransition()).isEqualTo(DEFAULT_SCENE_TRANSITION);
        assertThat(testMHomeBanner.getSceneTransitionParam()).isEqualTo(DEFAULT_SCENE_TRANSITION_PARAM);
        assertThat(testMHomeBanner.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMHomeBanner.getAppealType()).isEqualTo(DEFAULT_APPEAL_TYPE);
        assertThat(testMHomeBanner.getAppealContentId()).isEqualTo(DEFAULT_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void createMHomeBannerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mHomeBannerRepository.findAll().size();

        // Create the MHomeBanner with an existing ID
        mHomeBanner.setId(1L);
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(mHomeBanner);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMHomeBannerMockMvc.perform(post("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MHomeBanner in the database
        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBannerTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mHomeBannerRepository.findAll().size();
        // set the field null
        mHomeBanner.setBannerType(null);

        // Create the MHomeBanner, which fails.
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(mHomeBanner);

        restMHomeBannerMockMvc.perform(post("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mHomeBannerRepository.findAll().size();
        // set the field null
        mHomeBanner.setStartAt(null);

        // Create the MHomeBanner, which fails.
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(mHomeBanner);

        restMHomeBannerMockMvc.perform(post("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mHomeBannerRepository.findAll().size();
        // set the field null
        mHomeBanner.setEndAt(null);

        // Create the MHomeBanner, which fails.
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(mHomeBanner);

        restMHomeBannerMockMvc.perform(post("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLabelEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mHomeBannerRepository.findAll().size();
        // set the field null
        mHomeBanner.setLabelEndAt(null);

        // Create the MHomeBanner, which fails.
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(mHomeBanner);

        restMHomeBannerMockMvc.perform(post("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSceneTransitionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mHomeBannerRepository.findAll().size();
        // set the field null
        mHomeBanner.setSceneTransition(null);

        // Create the MHomeBanner, which fails.
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(mHomeBanner);

        restMHomeBannerMockMvc.perform(post("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mHomeBannerRepository.findAll().size();
        // set the field null
        mHomeBanner.setOrderNum(null);

        // Create the MHomeBanner, which fails.
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(mHomeBanner);

        restMHomeBannerMockMvc.perform(post("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isBadRequest());

        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMHomeBanners() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList
        restMHomeBannerMockMvc.perform(get("/api/m-home-banners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mHomeBanner.getId().intValue())))
            .andExpect(jsonPath("$.[*].bannerType").value(hasItem(DEFAULT_BANNER_TYPE)))
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
    public void getMHomeBanner() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get the mHomeBanner
        restMHomeBannerMockMvc.perform(get("/api/m-home-banners/{id}", mHomeBanner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mHomeBanner.getId().intValue()))
            .andExpect(jsonPath("$.bannerType").value(DEFAULT_BANNER_TYPE))
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
    public void getAllMHomeBannersByBannerTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where bannerType equals to DEFAULT_BANNER_TYPE
        defaultMHomeBannerShouldBeFound("bannerType.equals=" + DEFAULT_BANNER_TYPE);

        // Get all the mHomeBannerList where bannerType equals to UPDATED_BANNER_TYPE
        defaultMHomeBannerShouldNotBeFound("bannerType.equals=" + UPDATED_BANNER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByBannerTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where bannerType in DEFAULT_BANNER_TYPE or UPDATED_BANNER_TYPE
        defaultMHomeBannerShouldBeFound("bannerType.in=" + DEFAULT_BANNER_TYPE + "," + UPDATED_BANNER_TYPE);

        // Get all the mHomeBannerList where bannerType equals to UPDATED_BANNER_TYPE
        defaultMHomeBannerShouldNotBeFound("bannerType.in=" + UPDATED_BANNER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByBannerTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where bannerType is not null
        defaultMHomeBannerShouldBeFound("bannerType.specified=true");

        // Get all the mHomeBannerList where bannerType is null
        defaultMHomeBannerShouldNotBeFound("bannerType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByBannerTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where bannerType greater than or equals to DEFAULT_BANNER_TYPE
        defaultMHomeBannerShouldBeFound("bannerType.greaterOrEqualThan=" + DEFAULT_BANNER_TYPE);

        // Get all the mHomeBannerList where bannerType greater than or equals to UPDATED_BANNER_TYPE
        defaultMHomeBannerShouldNotBeFound("bannerType.greaterOrEqualThan=" + UPDATED_BANNER_TYPE);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByBannerTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where bannerType less than or equals to DEFAULT_BANNER_TYPE
        defaultMHomeBannerShouldNotBeFound("bannerType.lessThan=" + DEFAULT_BANNER_TYPE);

        // Get all the mHomeBannerList where bannerType less than or equals to UPDATED_BANNER_TYPE
        defaultMHomeBannerShouldBeFound("bannerType.lessThan=" + UPDATED_BANNER_TYPE);
    }


    @Test
    @Transactional
    public void getAllMHomeBannersByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where startAt equals to DEFAULT_START_AT
        defaultMHomeBannerShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mHomeBannerList where startAt equals to UPDATED_START_AT
        defaultMHomeBannerShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMHomeBannerShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mHomeBannerList where startAt equals to UPDATED_START_AT
        defaultMHomeBannerShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where startAt is not null
        defaultMHomeBannerShouldBeFound("startAt.specified=true");

        // Get all the mHomeBannerList where startAt is null
        defaultMHomeBannerShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where startAt greater than or equals to DEFAULT_START_AT
        defaultMHomeBannerShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mHomeBannerList where startAt greater than or equals to UPDATED_START_AT
        defaultMHomeBannerShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where startAt less than or equals to DEFAULT_START_AT
        defaultMHomeBannerShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mHomeBannerList where startAt less than or equals to UPDATED_START_AT
        defaultMHomeBannerShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMHomeBannersByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where endAt equals to DEFAULT_END_AT
        defaultMHomeBannerShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mHomeBannerList where endAt equals to UPDATED_END_AT
        defaultMHomeBannerShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMHomeBannerShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mHomeBannerList where endAt equals to UPDATED_END_AT
        defaultMHomeBannerShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where endAt is not null
        defaultMHomeBannerShouldBeFound("endAt.specified=true");

        // Get all the mHomeBannerList where endAt is null
        defaultMHomeBannerShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where endAt greater than or equals to DEFAULT_END_AT
        defaultMHomeBannerShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mHomeBannerList where endAt greater than or equals to UPDATED_END_AT
        defaultMHomeBannerShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where endAt less than or equals to DEFAULT_END_AT
        defaultMHomeBannerShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mHomeBannerList where endAt less than or equals to UPDATED_END_AT
        defaultMHomeBannerShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }


    @Test
    @Transactional
    public void getAllMHomeBannersByLabelEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where labelEndAt equals to DEFAULT_LABEL_END_AT
        defaultMHomeBannerShouldBeFound("labelEndAt.equals=" + DEFAULT_LABEL_END_AT);

        // Get all the mHomeBannerList where labelEndAt equals to UPDATED_LABEL_END_AT
        defaultMHomeBannerShouldNotBeFound("labelEndAt.equals=" + UPDATED_LABEL_END_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByLabelEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where labelEndAt in DEFAULT_LABEL_END_AT or UPDATED_LABEL_END_AT
        defaultMHomeBannerShouldBeFound("labelEndAt.in=" + DEFAULT_LABEL_END_AT + "," + UPDATED_LABEL_END_AT);

        // Get all the mHomeBannerList where labelEndAt equals to UPDATED_LABEL_END_AT
        defaultMHomeBannerShouldNotBeFound("labelEndAt.in=" + UPDATED_LABEL_END_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByLabelEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where labelEndAt is not null
        defaultMHomeBannerShouldBeFound("labelEndAt.specified=true");

        // Get all the mHomeBannerList where labelEndAt is null
        defaultMHomeBannerShouldNotBeFound("labelEndAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByLabelEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where labelEndAt greater than or equals to DEFAULT_LABEL_END_AT
        defaultMHomeBannerShouldBeFound("labelEndAt.greaterOrEqualThan=" + DEFAULT_LABEL_END_AT);

        // Get all the mHomeBannerList where labelEndAt greater than or equals to UPDATED_LABEL_END_AT
        defaultMHomeBannerShouldNotBeFound("labelEndAt.greaterOrEqualThan=" + UPDATED_LABEL_END_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByLabelEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where labelEndAt less than or equals to DEFAULT_LABEL_END_AT
        defaultMHomeBannerShouldNotBeFound("labelEndAt.lessThan=" + DEFAULT_LABEL_END_AT);

        // Get all the mHomeBannerList where labelEndAt less than or equals to UPDATED_LABEL_END_AT
        defaultMHomeBannerShouldBeFound("labelEndAt.lessThan=" + UPDATED_LABEL_END_AT);
    }


    @Test
    @Transactional
    public void getAllMHomeBannersBySceneTransitionIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where sceneTransition equals to DEFAULT_SCENE_TRANSITION
        defaultMHomeBannerShouldBeFound("sceneTransition.equals=" + DEFAULT_SCENE_TRANSITION);

        // Get all the mHomeBannerList where sceneTransition equals to UPDATED_SCENE_TRANSITION
        defaultMHomeBannerShouldNotBeFound("sceneTransition.equals=" + UPDATED_SCENE_TRANSITION);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersBySceneTransitionIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where sceneTransition in DEFAULT_SCENE_TRANSITION or UPDATED_SCENE_TRANSITION
        defaultMHomeBannerShouldBeFound("sceneTransition.in=" + DEFAULT_SCENE_TRANSITION + "," + UPDATED_SCENE_TRANSITION);

        // Get all the mHomeBannerList where sceneTransition equals to UPDATED_SCENE_TRANSITION
        defaultMHomeBannerShouldNotBeFound("sceneTransition.in=" + UPDATED_SCENE_TRANSITION);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersBySceneTransitionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where sceneTransition is not null
        defaultMHomeBannerShouldBeFound("sceneTransition.specified=true");

        // Get all the mHomeBannerList where sceneTransition is null
        defaultMHomeBannerShouldNotBeFound("sceneTransition.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeBannersBySceneTransitionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where sceneTransition greater than or equals to DEFAULT_SCENE_TRANSITION
        defaultMHomeBannerShouldBeFound("sceneTransition.greaterOrEqualThan=" + DEFAULT_SCENE_TRANSITION);

        // Get all the mHomeBannerList where sceneTransition greater than or equals to UPDATED_SCENE_TRANSITION
        defaultMHomeBannerShouldNotBeFound("sceneTransition.greaterOrEqualThan=" + UPDATED_SCENE_TRANSITION);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersBySceneTransitionIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where sceneTransition less than or equals to DEFAULT_SCENE_TRANSITION
        defaultMHomeBannerShouldNotBeFound("sceneTransition.lessThan=" + DEFAULT_SCENE_TRANSITION);

        // Get all the mHomeBannerList where sceneTransition less than or equals to UPDATED_SCENE_TRANSITION
        defaultMHomeBannerShouldBeFound("sceneTransition.lessThan=" + UPDATED_SCENE_TRANSITION);
    }


    @Test
    @Transactional
    public void getAllMHomeBannersByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMHomeBannerShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mHomeBannerList where orderNum equals to UPDATED_ORDER_NUM
        defaultMHomeBannerShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMHomeBannerShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mHomeBannerList where orderNum equals to UPDATED_ORDER_NUM
        defaultMHomeBannerShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where orderNum is not null
        defaultMHomeBannerShouldBeFound("orderNum.specified=true");

        // Get all the mHomeBannerList where orderNum is null
        defaultMHomeBannerShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMHomeBannerShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mHomeBannerList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMHomeBannerShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMHomeBannerShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mHomeBannerList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMHomeBannerShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMHomeBannersByAppealTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealType equals to DEFAULT_APPEAL_TYPE
        defaultMHomeBannerShouldBeFound("appealType.equals=" + DEFAULT_APPEAL_TYPE);

        // Get all the mHomeBannerList where appealType equals to UPDATED_APPEAL_TYPE
        defaultMHomeBannerShouldNotBeFound("appealType.equals=" + UPDATED_APPEAL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByAppealTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealType in DEFAULT_APPEAL_TYPE or UPDATED_APPEAL_TYPE
        defaultMHomeBannerShouldBeFound("appealType.in=" + DEFAULT_APPEAL_TYPE + "," + UPDATED_APPEAL_TYPE);

        // Get all the mHomeBannerList where appealType equals to UPDATED_APPEAL_TYPE
        defaultMHomeBannerShouldNotBeFound("appealType.in=" + UPDATED_APPEAL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByAppealTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealType is not null
        defaultMHomeBannerShouldBeFound("appealType.specified=true");

        // Get all the mHomeBannerList where appealType is null
        defaultMHomeBannerShouldNotBeFound("appealType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByAppealTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealType greater than or equals to DEFAULT_APPEAL_TYPE
        defaultMHomeBannerShouldBeFound("appealType.greaterOrEqualThan=" + DEFAULT_APPEAL_TYPE);

        // Get all the mHomeBannerList where appealType greater than or equals to UPDATED_APPEAL_TYPE
        defaultMHomeBannerShouldNotBeFound("appealType.greaterOrEqualThan=" + UPDATED_APPEAL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByAppealTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealType less than or equals to DEFAULT_APPEAL_TYPE
        defaultMHomeBannerShouldNotBeFound("appealType.lessThan=" + DEFAULT_APPEAL_TYPE);

        // Get all the mHomeBannerList where appealType less than or equals to UPDATED_APPEAL_TYPE
        defaultMHomeBannerShouldBeFound("appealType.lessThan=" + UPDATED_APPEAL_TYPE);
    }


    @Test
    @Transactional
    public void getAllMHomeBannersByAppealContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealContentId equals to DEFAULT_APPEAL_CONTENT_ID
        defaultMHomeBannerShouldBeFound("appealContentId.equals=" + DEFAULT_APPEAL_CONTENT_ID);

        // Get all the mHomeBannerList where appealContentId equals to UPDATED_APPEAL_CONTENT_ID
        defaultMHomeBannerShouldNotBeFound("appealContentId.equals=" + UPDATED_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByAppealContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealContentId in DEFAULT_APPEAL_CONTENT_ID or UPDATED_APPEAL_CONTENT_ID
        defaultMHomeBannerShouldBeFound("appealContentId.in=" + DEFAULT_APPEAL_CONTENT_ID + "," + UPDATED_APPEAL_CONTENT_ID);

        // Get all the mHomeBannerList where appealContentId equals to UPDATED_APPEAL_CONTENT_ID
        defaultMHomeBannerShouldNotBeFound("appealContentId.in=" + UPDATED_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByAppealContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealContentId is not null
        defaultMHomeBannerShouldBeFound("appealContentId.specified=true");

        // Get all the mHomeBannerList where appealContentId is null
        defaultMHomeBannerShouldNotBeFound("appealContentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByAppealContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealContentId greater than or equals to DEFAULT_APPEAL_CONTENT_ID
        defaultMHomeBannerShouldBeFound("appealContentId.greaterOrEqualThan=" + DEFAULT_APPEAL_CONTENT_ID);

        // Get all the mHomeBannerList where appealContentId greater than or equals to UPDATED_APPEAL_CONTENT_ID
        defaultMHomeBannerShouldNotBeFound("appealContentId.greaterOrEqualThan=" + UPDATED_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMHomeBannersByAppealContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        // Get all the mHomeBannerList where appealContentId less than or equals to DEFAULT_APPEAL_CONTENT_ID
        defaultMHomeBannerShouldNotBeFound("appealContentId.lessThan=" + DEFAULT_APPEAL_CONTENT_ID);

        // Get all the mHomeBannerList where appealContentId less than or equals to UPDATED_APPEAL_CONTENT_ID
        defaultMHomeBannerShouldBeFound("appealContentId.lessThan=" + UPDATED_APPEAL_CONTENT_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMHomeBannerShouldBeFound(String filter) throws Exception {
        restMHomeBannerMockMvc.perform(get("/api/m-home-banners?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mHomeBanner.getId().intValue())))
            .andExpect(jsonPath("$.[*].bannerType").value(hasItem(DEFAULT_BANNER_TYPE)))
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
        restMHomeBannerMockMvc.perform(get("/api/m-home-banners/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMHomeBannerShouldNotBeFound(String filter) throws Exception {
        restMHomeBannerMockMvc.perform(get("/api/m-home-banners?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMHomeBannerMockMvc.perform(get("/api/m-home-banners/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMHomeBanner() throws Exception {
        // Get the mHomeBanner
        restMHomeBannerMockMvc.perform(get("/api/m-home-banners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMHomeBanner() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        int databaseSizeBeforeUpdate = mHomeBannerRepository.findAll().size();

        // Update the mHomeBanner
        MHomeBanner updatedMHomeBanner = mHomeBannerRepository.findById(mHomeBanner.getId()).get();
        // Disconnect from session so that the updates on updatedMHomeBanner are not directly saved in db
        em.detach(updatedMHomeBanner);
        updatedMHomeBanner
            .bannerType(UPDATED_BANNER_TYPE)
            .assetName(UPDATED_ASSET_NAME)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .labelEndAt(UPDATED_LABEL_END_AT)
            .sceneTransition(UPDATED_SCENE_TRANSITION)
            .sceneTransitionParam(UPDATED_SCENE_TRANSITION_PARAM)
            .orderNum(UPDATED_ORDER_NUM)
            .appealType(UPDATED_APPEAL_TYPE)
            .appealContentId(UPDATED_APPEAL_CONTENT_ID);
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(updatedMHomeBanner);

        restMHomeBannerMockMvc.perform(put("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isOk());

        // Validate the MHomeBanner in the database
        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeUpdate);
        MHomeBanner testMHomeBanner = mHomeBannerList.get(mHomeBannerList.size() - 1);
        assertThat(testMHomeBanner.getBannerType()).isEqualTo(UPDATED_BANNER_TYPE);
        assertThat(testMHomeBanner.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testMHomeBanner.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMHomeBanner.getEndAt()).isEqualTo(UPDATED_END_AT);
        assertThat(testMHomeBanner.getLabelEndAt()).isEqualTo(UPDATED_LABEL_END_AT);
        assertThat(testMHomeBanner.getSceneTransition()).isEqualTo(UPDATED_SCENE_TRANSITION);
        assertThat(testMHomeBanner.getSceneTransitionParam()).isEqualTo(UPDATED_SCENE_TRANSITION_PARAM);
        assertThat(testMHomeBanner.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMHomeBanner.getAppealType()).isEqualTo(UPDATED_APPEAL_TYPE);
        assertThat(testMHomeBanner.getAppealContentId()).isEqualTo(UPDATED_APPEAL_CONTENT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMHomeBanner() throws Exception {
        int databaseSizeBeforeUpdate = mHomeBannerRepository.findAll().size();

        // Create the MHomeBanner
        MHomeBannerDTO mHomeBannerDTO = mHomeBannerMapper.toDto(mHomeBanner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMHomeBannerMockMvc.perform(put("/api/m-home-banners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeBannerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MHomeBanner in the database
        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMHomeBanner() throws Exception {
        // Initialize the database
        mHomeBannerRepository.saveAndFlush(mHomeBanner);

        int databaseSizeBeforeDelete = mHomeBannerRepository.findAll().size();

        // Delete the mHomeBanner
        restMHomeBannerMockMvc.perform(delete("/api/m-home-banners/{id}", mHomeBanner.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MHomeBanner> mHomeBannerList = mHomeBannerRepository.findAll();
        assertThat(mHomeBannerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MHomeBanner.class);
        MHomeBanner mHomeBanner1 = new MHomeBanner();
        mHomeBanner1.setId(1L);
        MHomeBanner mHomeBanner2 = new MHomeBanner();
        mHomeBanner2.setId(mHomeBanner1.getId());
        assertThat(mHomeBanner1).isEqualTo(mHomeBanner2);
        mHomeBanner2.setId(2L);
        assertThat(mHomeBanner1).isNotEqualTo(mHomeBanner2);
        mHomeBanner1.setId(null);
        assertThat(mHomeBanner1).isNotEqualTo(mHomeBanner2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MHomeBannerDTO.class);
        MHomeBannerDTO mHomeBannerDTO1 = new MHomeBannerDTO();
        mHomeBannerDTO1.setId(1L);
        MHomeBannerDTO mHomeBannerDTO2 = new MHomeBannerDTO();
        assertThat(mHomeBannerDTO1).isNotEqualTo(mHomeBannerDTO2);
        mHomeBannerDTO2.setId(mHomeBannerDTO1.getId());
        assertThat(mHomeBannerDTO1).isEqualTo(mHomeBannerDTO2);
        mHomeBannerDTO2.setId(2L);
        assertThat(mHomeBannerDTO1).isNotEqualTo(mHomeBannerDTO2);
        mHomeBannerDTO1.setId(null);
        assertThat(mHomeBannerDTO1).isNotEqualTo(mHomeBannerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mHomeBannerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mHomeBannerMapper.fromId(null)).isNull();
    }
}
