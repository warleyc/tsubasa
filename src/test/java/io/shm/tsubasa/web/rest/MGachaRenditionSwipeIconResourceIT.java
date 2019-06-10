package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionSwipeIcon;
import io.shm.tsubasa.repository.MGachaRenditionSwipeIconRepository;
import io.shm.tsubasa.service.MGachaRenditionSwipeIconService;
import io.shm.tsubasa.service.dto.MGachaRenditionSwipeIconDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionSwipeIconMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionSwipeIconCriteria;
import io.shm.tsubasa.service.MGachaRenditionSwipeIconQueryService;

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
 * Integration tests for the {@Link MGachaRenditionSwipeIconResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionSwipeIconResourceIT {

    private static final Integer DEFAULT_IS_SSR = 1;
    private static final Integer UPDATED_IS_SSR = 2;

    private static final Integer DEFAULT_IS_R_OR_LESS = 1;
    private static final Integer UPDATED_IS_R_OR_LESS = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_SWIPE_ICON_PREFAB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SWIPE_ICON_PREFAB_NAME = "BBBBBBBBBB";

    @Autowired
    private MGachaRenditionSwipeIconRepository mGachaRenditionSwipeIconRepository;

    @Autowired
    private MGachaRenditionSwipeIconMapper mGachaRenditionSwipeIconMapper;

    @Autowired
    private MGachaRenditionSwipeIconService mGachaRenditionSwipeIconService;

    @Autowired
    private MGachaRenditionSwipeIconQueryService mGachaRenditionSwipeIconQueryService;

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

    private MockMvc restMGachaRenditionSwipeIconMockMvc;

    private MGachaRenditionSwipeIcon mGachaRenditionSwipeIcon;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionSwipeIconResource mGachaRenditionSwipeIconResource = new MGachaRenditionSwipeIconResource(mGachaRenditionSwipeIconService, mGachaRenditionSwipeIconQueryService);
        this.restMGachaRenditionSwipeIconMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionSwipeIconResource)
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
    public static MGachaRenditionSwipeIcon createEntity(EntityManager em) {
        MGachaRenditionSwipeIcon mGachaRenditionSwipeIcon = new MGachaRenditionSwipeIcon()
            .isSsr(DEFAULT_IS_SSR)
            .isROrLess(DEFAULT_IS_R_OR_LESS)
            .weight(DEFAULT_WEIGHT)
            .swipeIconPrefabName(DEFAULT_SWIPE_ICON_PREFAB_NAME);
        return mGachaRenditionSwipeIcon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionSwipeIcon createUpdatedEntity(EntityManager em) {
        MGachaRenditionSwipeIcon mGachaRenditionSwipeIcon = new MGachaRenditionSwipeIcon()
            .isSsr(UPDATED_IS_SSR)
            .isROrLess(UPDATED_IS_R_OR_LESS)
            .weight(UPDATED_WEIGHT)
            .swipeIconPrefabName(UPDATED_SWIPE_ICON_PREFAB_NAME);
        return mGachaRenditionSwipeIcon;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionSwipeIcon = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionSwipeIcon() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionSwipeIconRepository.findAll().size();

        // Create the MGachaRenditionSwipeIcon
        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO = mGachaRenditionSwipeIconMapper.toDto(mGachaRenditionSwipeIcon);
        restMGachaRenditionSwipeIconMockMvc.perform(post("/api/m-gacha-rendition-swipe-icons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionSwipeIconDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionSwipeIcon in the database
        List<MGachaRenditionSwipeIcon> mGachaRenditionSwipeIconList = mGachaRenditionSwipeIconRepository.findAll();
        assertThat(mGachaRenditionSwipeIconList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionSwipeIcon testMGachaRenditionSwipeIcon = mGachaRenditionSwipeIconList.get(mGachaRenditionSwipeIconList.size() - 1);
        assertThat(testMGachaRenditionSwipeIcon.getIsSsr()).isEqualTo(DEFAULT_IS_SSR);
        assertThat(testMGachaRenditionSwipeIcon.getIsROrLess()).isEqualTo(DEFAULT_IS_R_OR_LESS);
        assertThat(testMGachaRenditionSwipeIcon.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionSwipeIcon.getSwipeIconPrefabName()).isEqualTo(DEFAULT_SWIPE_ICON_PREFAB_NAME);
    }

    @Test
    @Transactional
    public void createMGachaRenditionSwipeIconWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionSwipeIconRepository.findAll().size();

        // Create the MGachaRenditionSwipeIcon with an existing ID
        mGachaRenditionSwipeIcon.setId(1L);
        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO = mGachaRenditionSwipeIconMapper.toDto(mGachaRenditionSwipeIcon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionSwipeIconMockMvc.perform(post("/api/m-gacha-rendition-swipe-icons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionSwipeIconDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionSwipeIcon in the database
        List<MGachaRenditionSwipeIcon> mGachaRenditionSwipeIconList = mGachaRenditionSwipeIconRepository.findAll();
        assertThat(mGachaRenditionSwipeIconList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIsSsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionSwipeIconRepository.findAll().size();
        // set the field null
        mGachaRenditionSwipeIcon.setIsSsr(null);

        // Create the MGachaRenditionSwipeIcon, which fails.
        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO = mGachaRenditionSwipeIconMapper.toDto(mGachaRenditionSwipeIcon);

        restMGachaRenditionSwipeIconMockMvc.perform(post("/api/m-gacha-rendition-swipe-icons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionSwipeIconDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionSwipeIcon> mGachaRenditionSwipeIconList = mGachaRenditionSwipeIconRepository.findAll();
        assertThat(mGachaRenditionSwipeIconList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsROrLessIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionSwipeIconRepository.findAll().size();
        // set the field null
        mGachaRenditionSwipeIcon.setIsROrLess(null);

        // Create the MGachaRenditionSwipeIcon, which fails.
        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO = mGachaRenditionSwipeIconMapper.toDto(mGachaRenditionSwipeIcon);

        restMGachaRenditionSwipeIconMockMvc.perform(post("/api/m-gacha-rendition-swipe-icons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionSwipeIconDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionSwipeIcon> mGachaRenditionSwipeIconList = mGachaRenditionSwipeIconRepository.findAll();
        assertThat(mGachaRenditionSwipeIconList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionSwipeIconRepository.findAll().size();
        // set the field null
        mGachaRenditionSwipeIcon.setWeight(null);

        // Create the MGachaRenditionSwipeIcon, which fails.
        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO = mGachaRenditionSwipeIconMapper.toDto(mGachaRenditionSwipeIcon);

        restMGachaRenditionSwipeIconMockMvc.perform(post("/api/m-gacha-rendition-swipe-icons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionSwipeIconDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionSwipeIcon> mGachaRenditionSwipeIconList = mGachaRenditionSwipeIconRepository.findAll();
        assertThat(mGachaRenditionSwipeIconList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIcons() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList
        restMGachaRenditionSwipeIconMockMvc.perform(get("/api/m-gacha-rendition-swipe-icons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionSwipeIcon.getId().intValue())))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].isROrLess").value(hasItem(DEFAULT_IS_R_OR_LESS)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].swipeIconPrefabName").value(hasItem(DEFAULT_SWIPE_ICON_PREFAB_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionSwipeIcon() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get the mGachaRenditionSwipeIcon
        restMGachaRenditionSwipeIconMockMvc.perform(get("/api/m-gacha-rendition-swipe-icons/{id}", mGachaRenditionSwipeIcon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionSwipeIcon.getId().intValue()))
            .andExpect(jsonPath("$.isSsr").value(DEFAULT_IS_SSR))
            .andExpect(jsonPath("$.isROrLess").value(DEFAULT_IS_R_OR_LESS))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.swipeIconPrefabName").value(DEFAULT_SWIPE_ICON_PREFAB_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsSsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isSsr equals to DEFAULT_IS_SSR
        defaultMGachaRenditionSwipeIconShouldBeFound("isSsr.equals=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionSwipeIconList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isSsr.equals=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsSsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isSsr in DEFAULT_IS_SSR or UPDATED_IS_SSR
        defaultMGachaRenditionSwipeIconShouldBeFound("isSsr.in=" + DEFAULT_IS_SSR + "," + UPDATED_IS_SSR);

        // Get all the mGachaRenditionSwipeIconList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isSsr.in=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsSsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isSsr is not null
        defaultMGachaRenditionSwipeIconShouldBeFound("isSsr.specified=true");

        // Get all the mGachaRenditionSwipeIconList where isSsr is null
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isSsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsSsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isSsr greater than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionSwipeIconShouldBeFound("isSsr.greaterOrEqualThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionSwipeIconList where isSsr greater than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isSsr.greaterOrEqualThan=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsSsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isSsr less than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isSsr.lessThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionSwipeIconList where isSsr less than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionSwipeIconShouldBeFound("isSsr.lessThan=" + UPDATED_IS_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsROrLessIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isROrLess equals to DEFAULT_IS_R_OR_LESS
        defaultMGachaRenditionSwipeIconShouldBeFound("isROrLess.equals=" + DEFAULT_IS_R_OR_LESS);

        // Get all the mGachaRenditionSwipeIconList where isROrLess equals to UPDATED_IS_R_OR_LESS
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isROrLess.equals=" + UPDATED_IS_R_OR_LESS);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsROrLessIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isROrLess in DEFAULT_IS_R_OR_LESS or UPDATED_IS_R_OR_LESS
        defaultMGachaRenditionSwipeIconShouldBeFound("isROrLess.in=" + DEFAULT_IS_R_OR_LESS + "," + UPDATED_IS_R_OR_LESS);

        // Get all the mGachaRenditionSwipeIconList where isROrLess equals to UPDATED_IS_R_OR_LESS
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isROrLess.in=" + UPDATED_IS_R_OR_LESS);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsROrLessIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isROrLess is not null
        defaultMGachaRenditionSwipeIconShouldBeFound("isROrLess.specified=true");

        // Get all the mGachaRenditionSwipeIconList where isROrLess is null
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isROrLess.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsROrLessIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isROrLess greater than or equals to DEFAULT_IS_R_OR_LESS
        defaultMGachaRenditionSwipeIconShouldBeFound("isROrLess.greaterOrEqualThan=" + DEFAULT_IS_R_OR_LESS);

        // Get all the mGachaRenditionSwipeIconList where isROrLess greater than or equals to UPDATED_IS_R_OR_LESS
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isROrLess.greaterOrEqualThan=" + UPDATED_IS_R_OR_LESS);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByIsROrLessIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where isROrLess less than or equals to DEFAULT_IS_R_OR_LESS
        defaultMGachaRenditionSwipeIconShouldNotBeFound("isROrLess.lessThan=" + DEFAULT_IS_R_OR_LESS);

        // Get all the mGachaRenditionSwipeIconList where isROrLess less than or equals to UPDATED_IS_R_OR_LESS
        defaultMGachaRenditionSwipeIconShouldBeFound("isROrLess.lessThan=" + UPDATED_IS_R_OR_LESS);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionSwipeIconShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionSwipeIconList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionSwipeIconShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionSwipeIconShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionSwipeIconList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionSwipeIconShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where weight is not null
        defaultMGachaRenditionSwipeIconShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionSwipeIconList where weight is null
        defaultMGachaRenditionSwipeIconShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionSwipeIconShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionSwipeIconList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionSwipeIconShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionSwipeIconsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        // Get all the mGachaRenditionSwipeIconList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionSwipeIconShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionSwipeIconList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionSwipeIconShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionSwipeIconShouldBeFound(String filter) throws Exception {
        restMGachaRenditionSwipeIconMockMvc.perform(get("/api/m-gacha-rendition-swipe-icons?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionSwipeIcon.getId().intValue())))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].isROrLess").value(hasItem(DEFAULT_IS_R_OR_LESS)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].swipeIconPrefabName").value(hasItem(DEFAULT_SWIPE_ICON_PREFAB_NAME.toString())));

        // Check, that the count call also returns 1
        restMGachaRenditionSwipeIconMockMvc.perform(get("/api/m-gacha-rendition-swipe-icons/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionSwipeIconShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionSwipeIconMockMvc.perform(get("/api/m-gacha-rendition-swipe-icons?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionSwipeIconMockMvc.perform(get("/api/m-gacha-rendition-swipe-icons/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionSwipeIcon() throws Exception {
        // Get the mGachaRenditionSwipeIcon
        restMGachaRenditionSwipeIconMockMvc.perform(get("/api/m-gacha-rendition-swipe-icons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionSwipeIcon() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        int databaseSizeBeforeUpdate = mGachaRenditionSwipeIconRepository.findAll().size();

        // Update the mGachaRenditionSwipeIcon
        MGachaRenditionSwipeIcon updatedMGachaRenditionSwipeIcon = mGachaRenditionSwipeIconRepository.findById(mGachaRenditionSwipeIcon.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionSwipeIcon are not directly saved in db
        em.detach(updatedMGachaRenditionSwipeIcon);
        updatedMGachaRenditionSwipeIcon
            .isSsr(UPDATED_IS_SSR)
            .isROrLess(UPDATED_IS_R_OR_LESS)
            .weight(UPDATED_WEIGHT)
            .swipeIconPrefabName(UPDATED_SWIPE_ICON_PREFAB_NAME);
        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO = mGachaRenditionSwipeIconMapper.toDto(updatedMGachaRenditionSwipeIcon);

        restMGachaRenditionSwipeIconMockMvc.perform(put("/api/m-gacha-rendition-swipe-icons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionSwipeIconDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionSwipeIcon in the database
        List<MGachaRenditionSwipeIcon> mGachaRenditionSwipeIconList = mGachaRenditionSwipeIconRepository.findAll();
        assertThat(mGachaRenditionSwipeIconList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionSwipeIcon testMGachaRenditionSwipeIcon = mGachaRenditionSwipeIconList.get(mGachaRenditionSwipeIconList.size() - 1);
        assertThat(testMGachaRenditionSwipeIcon.getIsSsr()).isEqualTo(UPDATED_IS_SSR);
        assertThat(testMGachaRenditionSwipeIcon.getIsROrLess()).isEqualTo(UPDATED_IS_R_OR_LESS);
        assertThat(testMGachaRenditionSwipeIcon.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionSwipeIcon.getSwipeIconPrefabName()).isEqualTo(UPDATED_SWIPE_ICON_PREFAB_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionSwipeIcon() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionSwipeIconRepository.findAll().size();

        // Create the MGachaRenditionSwipeIcon
        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO = mGachaRenditionSwipeIconMapper.toDto(mGachaRenditionSwipeIcon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionSwipeIconMockMvc.perform(put("/api/m-gacha-rendition-swipe-icons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionSwipeIconDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionSwipeIcon in the database
        List<MGachaRenditionSwipeIcon> mGachaRenditionSwipeIconList = mGachaRenditionSwipeIconRepository.findAll();
        assertThat(mGachaRenditionSwipeIconList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionSwipeIcon() throws Exception {
        // Initialize the database
        mGachaRenditionSwipeIconRepository.saveAndFlush(mGachaRenditionSwipeIcon);

        int databaseSizeBeforeDelete = mGachaRenditionSwipeIconRepository.findAll().size();

        // Delete the mGachaRenditionSwipeIcon
        restMGachaRenditionSwipeIconMockMvc.perform(delete("/api/m-gacha-rendition-swipe-icons/{id}", mGachaRenditionSwipeIcon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionSwipeIcon> mGachaRenditionSwipeIconList = mGachaRenditionSwipeIconRepository.findAll();
        assertThat(mGachaRenditionSwipeIconList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionSwipeIcon.class);
        MGachaRenditionSwipeIcon mGachaRenditionSwipeIcon1 = new MGachaRenditionSwipeIcon();
        mGachaRenditionSwipeIcon1.setId(1L);
        MGachaRenditionSwipeIcon mGachaRenditionSwipeIcon2 = new MGachaRenditionSwipeIcon();
        mGachaRenditionSwipeIcon2.setId(mGachaRenditionSwipeIcon1.getId());
        assertThat(mGachaRenditionSwipeIcon1).isEqualTo(mGachaRenditionSwipeIcon2);
        mGachaRenditionSwipeIcon2.setId(2L);
        assertThat(mGachaRenditionSwipeIcon1).isNotEqualTo(mGachaRenditionSwipeIcon2);
        mGachaRenditionSwipeIcon1.setId(null);
        assertThat(mGachaRenditionSwipeIcon1).isNotEqualTo(mGachaRenditionSwipeIcon2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionSwipeIconDTO.class);
        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO1 = new MGachaRenditionSwipeIconDTO();
        mGachaRenditionSwipeIconDTO1.setId(1L);
        MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO2 = new MGachaRenditionSwipeIconDTO();
        assertThat(mGachaRenditionSwipeIconDTO1).isNotEqualTo(mGachaRenditionSwipeIconDTO2);
        mGachaRenditionSwipeIconDTO2.setId(mGachaRenditionSwipeIconDTO1.getId());
        assertThat(mGachaRenditionSwipeIconDTO1).isEqualTo(mGachaRenditionSwipeIconDTO2);
        mGachaRenditionSwipeIconDTO2.setId(2L);
        assertThat(mGachaRenditionSwipeIconDTO1).isNotEqualTo(mGachaRenditionSwipeIconDTO2);
        mGachaRenditionSwipeIconDTO1.setId(null);
        assertThat(mGachaRenditionSwipeIconDTO1).isNotEqualTo(mGachaRenditionSwipeIconDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionSwipeIconMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionSwipeIconMapper.fromId(null)).isNull();
    }
}
