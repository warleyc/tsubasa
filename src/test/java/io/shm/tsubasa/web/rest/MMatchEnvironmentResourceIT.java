package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMatchEnvironment;
import io.shm.tsubasa.repository.MMatchEnvironmentRepository;
import io.shm.tsubasa.service.MMatchEnvironmentService;
import io.shm.tsubasa.service.dto.MMatchEnvironmentDTO;
import io.shm.tsubasa.service.mapper.MMatchEnvironmentMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMatchEnvironmentCriteria;
import io.shm.tsubasa.service.MMatchEnvironmentQueryService;

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
 * Integration tests for the {@Link MMatchEnvironmentResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMatchEnvironmentResourceIT {

    private static final Integer DEFAULT_CATEGORY = 1;
    private static final Integer UPDATED_CATEGORY = 2;

    private static final String DEFAULT_SKY_TEX = "AAAAAAAAAA";
    private static final String UPDATED_SKY_TEX = "BBBBBBBBBB";

    private static final String DEFAULT_BALL = "AAAAAAAAAA";
    private static final String UPDATED_BALL = "BBBBBBBBBB";

    private static final String DEFAULT_STADIUM = "AAAAAAAAAA";
    private static final String UPDATED_STADIUM = "BBBBBBBBBB";

    private static final String DEFAULT_RAINY_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RAINY_ASSET_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_PLAY_RAINY_SOUND = 1;
    private static final Integer UPDATED_IS_PLAY_RAINY_SOUND = 2;

    private static final String DEFAULT_OFFENSE_START_BGM = "AAAAAAAAAA";
    private static final String UPDATED_OFFENSE_START_BGM = "BBBBBBBBBB";

    private static final String DEFAULT_OFFENSE_STOP_BGM = "AAAAAAAAAA";
    private static final String UPDATED_OFFENSE_STOP_BGM = "BBBBBBBBBB";

    private static final String DEFAULT_DEFENSE_START_BGM = "AAAAAAAAAA";
    private static final String UPDATED_DEFENSE_START_BGM = "BBBBBBBBBB";

    private static final String DEFAULT_DEFENSE_STOP_BGM = "AAAAAAAAAA";
    private static final String UPDATED_DEFENSE_STOP_BGM = "BBBBBBBBBB";

    @Autowired
    private MMatchEnvironmentRepository mMatchEnvironmentRepository;

    @Autowired
    private MMatchEnvironmentMapper mMatchEnvironmentMapper;

    @Autowired
    private MMatchEnvironmentService mMatchEnvironmentService;

    @Autowired
    private MMatchEnvironmentQueryService mMatchEnvironmentQueryService;

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

    private MockMvc restMMatchEnvironmentMockMvc;

    private MMatchEnvironment mMatchEnvironment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMatchEnvironmentResource mMatchEnvironmentResource = new MMatchEnvironmentResource(mMatchEnvironmentService, mMatchEnvironmentQueryService);
        this.restMMatchEnvironmentMockMvc = MockMvcBuilders.standaloneSetup(mMatchEnvironmentResource)
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
    public static MMatchEnvironment createEntity(EntityManager em) {
        MMatchEnvironment mMatchEnvironment = new MMatchEnvironment()
            .category(DEFAULT_CATEGORY)
            .skyTex(DEFAULT_SKY_TEX)
            .ball(DEFAULT_BALL)
            .stadium(DEFAULT_STADIUM)
            .rainyAssetName(DEFAULT_RAINY_ASSET_NAME)
            .isPlayRainySound(DEFAULT_IS_PLAY_RAINY_SOUND)
            .offenseStartBgm(DEFAULT_OFFENSE_START_BGM)
            .offenseStopBgm(DEFAULT_OFFENSE_STOP_BGM)
            .defenseStartBgm(DEFAULT_DEFENSE_START_BGM)
            .defenseStopBgm(DEFAULT_DEFENSE_STOP_BGM);
        return mMatchEnvironment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMatchEnvironment createUpdatedEntity(EntityManager em) {
        MMatchEnvironment mMatchEnvironment = new MMatchEnvironment()
            .category(UPDATED_CATEGORY)
            .skyTex(UPDATED_SKY_TEX)
            .ball(UPDATED_BALL)
            .stadium(UPDATED_STADIUM)
            .rainyAssetName(UPDATED_RAINY_ASSET_NAME)
            .isPlayRainySound(UPDATED_IS_PLAY_RAINY_SOUND)
            .offenseStartBgm(UPDATED_OFFENSE_START_BGM)
            .offenseStopBgm(UPDATED_OFFENSE_STOP_BGM)
            .defenseStartBgm(UPDATED_DEFENSE_START_BGM)
            .defenseStopBgm(UPDATED_DEFENSE_STOP_BGM);
        return mMatchEnvironment;
    }

    @BeforeEach
    public void initTest() {
        mMatchEnvironment = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMatchEnvironment() throws Exception {
        int databaseSizeBeforeCreate = mMatchEnvironmentRepository.findAll().size();

        // Create the MMatchEnvironment
        MMatchEnvironmentDTO mMatchEnvironmentDTO = mMatchEnvironmentMapper.toDto(mMatchEnvironment);
        restMMatchEnvironmentMockMvc.perform(post("/api/m-match-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchEnvironmentDTO)))
            .andExpect(status().isCreated());

        // Validate the MMatchEnvironment in the database
        List<MMatchEnvironment> mMatchEnvironmentList = mMatchEnvironmentRepository.findAll();
        assertThat(mMatchEnvironmentList).hasSize(databaseSizeBeforeCreate + 1);
        MMatchEnvironment testMMatchEnvironment = mMatchEnvironmentList.get(mMatchEnvironmentList.size() - 1);
        assertThat(testMMatchEnvironment.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testMMatchEnvironment.getSkyTex()).isEqualTo(DEFAULT_SKY_TEX);
        assertThat(testMMatchEnvironment.getBall()).isEqualTo(DEFAULT_BALL);
        assertThat(testMMatchEnvironment.getStadium()).isEqualTo(DEFAULT_STADIUM);
        assertThat(testMMatchEnvironment.getRainyAssetName()).isEqualTo(DEFAULT_RAINY_ASSET_NAME);
        assertThat(testMMatchEnvironment.getIsPlayRainySound()).isEqualTo(DEFAULT_IS_PLAY_RAINY_SOUND);
        assertThat(testMMatchEnvironment.getOffenseStartBgm()).isEqualTo(DEFAULT_OFFENSE_START_BGM);
        assertThat(testMMatchEnvironment.getOffenseStopBgm()).isEqualTo(DEFAULT_OFFENSE_STOP_BGM);
        assertThat(testMMatchEnvironment.getDefenseStartBgm()).isEqualTo(DEFAULT_DEFENSE_START_BGM);
        assertThat(testMMatchEnvironment.getDefenseStopBgm()).isEqualTo(DEFAULT_DEFENSE_STOP_BGM);
    }

    @Test
    @Transactional
    public void createMMatchEnvironmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMatchEnvironmentRepository.findAll().size();

        // Create the MMatchEnvironment with an existing ID
        mMatchEnvironment.setId(1L);
        MMatchEnvironmentDTO mMatchEnvironmentDTO = mMatchEnvironmentMapper.toDto(mMatchEnvironment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMatchEnvironmentMockMvc.perform(post("/api/m-match-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMatchEnvironment in the database
        List<MMatchEnvironment> mMatchEnvironmentList = mMatchEnvironmentRepository.findAll();
        assertThat(mMatchEnvironmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchEnvironmentRepository.findAll().size();
        // set the field null
        mMatchEnvironment.setCategory(null);

        // Create the MMatchEnvironment, which fails.
        MMatchEnvironmentDTO mMatchEnvironmentDTO = mMatchEnvironmentMapper.toDto(mMatchEnvironment);

        restMMatchEnvironmentMockMvc.perform(post("/api/m-match-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchEnvironment> mMatchEnvironmentList = mMatchEnvironmentRepository.findAll();
        assertThat(mMatchEnvironmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsPlayRainySoundIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchEnvironmentRepository.findAll().size();
        // set the field null
        mMatchEnvironment.setIsPlayRainySound(null);

        // Create the MMatchEnvironment, which fails.
        MMatchEnvironmentDTO mMatchEnvironmentDTO = mMatchEnvironmentMapper.toDto(mMatchEnvironment);

        restMMatchEnvironmentMockMvc.perform(post("/api/m-match-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchEnvironment> mMatchEnvironmentList = mMatchEnvironmentRepository.findAll();
        assertThat(mMatchEnvironmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironments() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList
        restMMatchEnvironmentMockMvc.perform(get("/api/m-match-environments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMatchEnvironment.getId().intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].skyTex").value(hasItem(DEFAULT_SKY_TEX.toString())))
            .andExpect(jsonPath("$.[*].ball").value(hasItem(DEFAULT_BALL.toString())))
            .andExpect(jsonPath("$.[*].stadium").value(hasItem(DEFAULT_STADIUM.toString())))
            .andExpect(jsonPath("$.[*].rainyAssetName").value(hasItem(DEFAULT_RAINY_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].isPlayRainySound").value(hasItem(DEFAULT_IS_PLAY_RAINY_SOUND)))
            .andExpect(jsonPath("$.[*].offenseStartBgm").value(hasItem(DEFAULT_OFFENSE_START_BGM.toString())))
            .andExpect(jsonPath("$.[*].offenseStopBgm").value(hasItem(DEFAULT_OFFENSE_STOP_BGM.toString())))
            .andExpect(jsonPath("$.[*].defenseStartBgm").value(hasItem(DEFAULT_DEFENSE_START_BGM.toString())))
            .andExpect(jsonPath("$.[*].defenseStopBgm").value(hasItem(DEFAULT_DEFENSE_STOP_BGM.toString())));
    }
    
    @Test
    @Transactional
    public void getMMatchEnvironment() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get the mMatchEnvironment
        restMMatchEnvironmentMockMvc.perform(get("/api/m-match-environments/{id}", mMatchEnvironment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMatchEnvironment.getId().intValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.skyTex").value(DEFAULT_SKY_TEX.toString()))
            .andExpect(jsonPath("$.ball").value(DEFAULT_BALL.toString()))
            .andExpect(jsonPath("$.stadium").value(DEFAULT_STADIUM.toString()))
            .andExpect(jsonPath("$.rainyAssetName").value(DEFAULT_RAINY_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.isPlayRainySound").value(DEFAULT_IS_PLAY_RAINY_SOUND))
            .andExpect(jsonPath("$.offenseStartBgm").value(DEFAULT_OFFENSE_START_BGM.toString()))
            .andExpect(jsonPath("$.offenseStopBgm").value(DEFAULT_OFFENSE_STOP_BGM.toString()))
            .andExpect(jsonPath("$.defenseStartBgm").value(DEFAULT_DEFENSE_START_BGM.toString()))
            .andExpect(jsonPath("$.defenseStopBgm").value(DEFAULT_DEFENSE_STOP_BGM.toString()));
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByCategoryIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where category equals to DEFAULT_CATEGORY
        defaultMMatchEnvironmentShouldBeFound("category.equals=" + DEFAULT_CATEGORY);

        // Get all the mMatchEnvironmentList where category equals to UPDATED_CATEGORY
        defaultMMatchEnvironmentShouldNotBeFound("category.equals=" + UPDATED_CATEGORY);
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByCategoryIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where category in DEFAULT_CATEGORY or UPDATED_CATEGORY
        defaultMMatchEnvironmentShouldBeFound("category.in=" + DEFAULT_CATEGORY + "," + UPDATED_CATEGORY);

        // Get all the mMatchEnvironmentList where category equals to UPDATED_CATEGORY
        defaultMMatchEnvironmentShouldNotBeFound("category.in=" + UPDATED_CATEGORY);
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByCategoryIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where category is not null
        defaultMMatchEnvironmentShouldBeFound("category.specified=true");

        // Get all the mMatchEnvironmentList where category is null
        defaultMMatchEnvironmentShouldNotBeFound("category.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByCategoryIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where category greater than or equals to DEFAULT_CATEGORY
        defaultMMatchEnvironmentShouldBeFound("category.greaterOrEqualThan=" + DEFAULT_CATEGORY);

        // Get all the mMatchEnvironmentList where category greater than or equals to UPDATED_CATEGORY
        defaultMMatchEnvironmentShouldNotBeFound("category.greaterOrEqualThan=" + UPDATED_CATEGORY);
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByCategoryIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where category less than or equals to DEFAULT_CATEGORY
        defaultMMatchEnvironmentShouldNotBeFound("category.lessThan=" + DEFAULT_CATEGORY);

        // Get all the mMatchEnvironmentList where category less than or equals to UPDATED_CATEGORY
        defaultMMatchEnvironmentShouldBeFound("category.lessThan=" + UPDATED_CATEGORY);
    }


    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByIsPlayRainySoundIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where isPlayRainySound equals to DEFAULT_IS_PLAY_RAINY_SOUND
        defaultMMatchEnvironmentShouldBeFound("isPlayRainySound.equals=" + DEFAULT_IS_PLAY_RAINY_SOUND);

        // Get all the mMatchEnvironmentList where isPlayRainySound equals to UPDATED_IS_PLAY_RAINY_SOUND
        defaultMMatchEnvironmentShouldNotBeFound("isPlayRainySound.equals=" + UPDATED_IS_PLAY_RAINY_SOUND);
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByIsPlayRainySoundIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where isPlayRainySound in DEFAULT_IS_PLAY_RAINY_SOUND or UPDATED_IS_PLAY_RAINY_SOUND
        defaultMMatchEnvironmentShouldBeFound("isPlayRainySound.in=" + DEFAULT_IS_PLAY_RAINY_SOUND + "," + UPDATED_IS_PLAY_RAINY_SOUND);

        // Get all the mMatchEnvironmentList where isPlayRainySound equals to UPDATED_IS_PLAY_RAINY_SOUND
        defaultMMatchEnvironmentShouldNotBeFound("isPlayRainySound.in=" + UPDATED_IS_PLAY_RAINY_SOUND);
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByIsPlayRainySoundIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where isPlayRainySound is not null
        defaultMMatchEnvironmentShouldBeFound("isPlayRainySound.specified=true");

        // Get all the mMatchEnvironmentList where isPlayRainySound is null
        defaultMMatchEnvironmentShouldNotBeFound("isPlayRainySound.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByIsPlayRainySoundIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where isPlayRainySound greater than or equals to DEFAULT_IS_PLAY_RAINY_SOUND
        defaultMMatchEnvironmentShouldBeFound("isPlayRainySound.greaterOrEqualThan=" + DEFAULT_IS_PLAY_RAINY_SOUND);

        // Get all the mMatchEnvironmentList where isPlayRainySound greater than or equals to UPDATED_IS_PLAY_RAINY_SOUND
        defaultMMatchEnvironmentShouldNotBeFound("isPlayRainySound.greaterOrEqualThan=" + UPDATED_IS_PLAY_RAINY_SOUND);
    }

    @Test
    @Transactional
    public void getAllMMatchEnvironmentsByIsPlayRainySoundIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        // Get all the mMatchEnvironmentList where isPlayRainySound less than or equals to DEFAULT_IS_PLAY_RAINY_SOUND
        defaultMMatchEnvironmentShouldNotBeFound("isPlayRainySound.lessThan=" + DEFAULT_IS_PLAY_RAINY_SOUND);

        // Get all the mMatchEnvironmentList where isPlayRainySound less than or equals to UPDATED_IS_PLAY_RAINY_SOUND
        defaultMMatchEnvironmentShouldBeFound("isPlayRainySound.lessThan=" + UPDATED_IS_PLAY_RAINY_SOUND);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMatchEnvironmentShouldBeFound(String filter) throws Exception {
        restMMatchEnvironmentMockMvc.perform(get("/api/m-match-environments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMatchEnvironment.getId().intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].skyTex").value(hasItem(DEFAULT_SKY_TEX.toString())))
            .andExpect(jsonPath("$.[*].ball").value(hasItem(DEFAULT_BALL.toString())))
            .andExpect(jsonPath("$.[*].stadium").value(hasItem(DEFAULT_STADIUM.toString())))
            .andExpect(jsonPath("$.[*].rainyAssetName").value(hasItem(DEFAULT_RAINY_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].isPlayRainySound").value(hasItem(DEFAULT_IS_PLAY_RAINY_SOUND)))
            .andExpect(jsonPath("$.[*].offenseStartBgm").value(hasItem(DEFAULT_OFFENSE_START_BGM.toString())))
            .andExpect(jsonPath("$.[*].offenseStopBgm").value(hasItem(DEFAULT_OFFENSE_STOP_BGM.toString())))
            .andExpect(jsonPath("$.[*].defenseStartBgm").value(hasItem(DEFAULT_DEFENSE_START_BGM.toString())))
            .andExpect(jsonPath("$.[*].defenseStopBgm").value(hasItem(DEFAULT_DEFENSE_STOP_BGM.toString())));

        // Check, that the count call also returns 1
        restMMatchEnvironmentMockMvc.perform(get("/api/m-match-environments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMatchEnvironmentShouldNotBeFound(String filter) throws Exception {
        restMMatchEnvironmentMockMvc.perform(get("/api/m-match-environments?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMatchEnvironmentMockMvc.perform(get("/api/m-match-environments/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMatchEnvironment() throws Exception {
        // Get the mMatchEnvironment
        restMMatchEnvironmentMockMvc.perform(get("/api/m-match-environments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMatchEnvironment() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        int databaseSizeBeforeUpdate = mMatchEnvironmentRepository.findAll().size();

        // Update the mMatchEnvironment
        MMatchEnvironment updatedMMatchEnvironment = mMatchEnvironmentRepository.findById(mMatchEnvironment.getId()).get();
        // Disconnect from session so that the updates on updatedMMatchEnvironment are not directly saved in db
        em.detach(updatedMMatchEnvironment);
        updatedMMatchEnvironment
            .category(UPDATED_CATEGORY)
            .skyTex(UPDATED_SKY_TEX)
            .ball(UPDATED_BALL)
            .stadium(UPDATED_STADIUM)
            .rainyAssetName(UPDATED_RAINY_ASSET_NAME)
            .isPlayRainySound(UPDATED_IS_PLAY_RAINY_SOUND)
            .offenseStartBgm(UPDATED_OFFENSE_START_BGM)
            .offenseStopBgm(UPDATED_OFFENSE_STOP_BGM)
            .defenseStartBgm(UPDATED_DEFENSE_START_BGM)
            .defenseStopBgm(UPDATED_DEFENSE_STOP_BGM);
        MMatchEnvironmentDTO mMatchEnvironmentDTO = mMatchEnvironmentMapper.toDto(updatedMMatchEnvironment);

        restMMatchEnvironmentMockMvc.perform(put("/api/m-match-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchEnvironmentDTO)))
            .andExpect(status().isOk());

        // Validate the MMatchEnvironment in the database
        List<MMatchEnvironment> mMatchEnvironmentList = mMatchEnvironmentRepository.findAll();
        assertThat(mMatchEnvironmentList).hasSize(databaseSizeBeforeUpdate);
        MMatchEnvironment testMMatchEnvironment = mMatchEnvironmentList.get(mMatchEnvironmentList.size() - 1);
        assertThat(testMMatchEnvironment.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testMMatchEnvironment.getSkyTex()).isEqualTo(UPDATED_SKY_TEX);
        assertThat(testMMatchEnvironment.getBall()).isEqualTo(UPDATED_BALL);
        assertThat(testMMatchEnvironment.getStadium()).isEqualTo(UPDATED_STADIUM);
        assertThat(testMMatchEnvironment.getRainyAssetName()).isEqualTo(UPDATED_RAINY_ASSET_NAME);
        assertThat(testMMatchEnvironment.getIsPlayRainySound()).isEqualTo(UPDATED_IS_PLAY_RAINY_SOUND);
        assertThat(testMMatchEnvironment.getOffenseStartBgm()).isEqualTo(UPDATED_OFFENSE_START_BGM);
        assertThat(testMMatchEnvironment.getOffenseStopBgm()).isEqualTo(UPDATED_OFFENSE_STOP_BGM);
        assertThat(testMMatchEnvironment.getDefenseStartBgm()).isEqualTo(UPDATED_DEFENSE_START_BGM);
        assertThat(testMMatchEnvironment.getDefenseStopBgm()).isEqualTo(UPDATED_DEFENSE_STOP_BGM);
    }

    @Test
    @Transactional
    public void updateNonExistingMMatchEnvironment() throws Exception {
        int databaseSizeBeforeUpdate = mMatchEnvironmentRepository.findAll().size();

        // Create the MMatchEnvironment
        MMatchEnvironmentDTO mMatchEnvironmentDTO = mMatchEnvironmentMapper.toDto(mMatchEnvironment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMatchEnvironmentMockMvc.perform(put("/api/m-match-environments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchEnvironmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMatchEnvironment in the database
        List<MMatchEnvironment> mMatchEnvironmentList = mMatchEnvironmentRepository.findAll();
        assertThat(mMatchEnvironmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMatchEnvironment() throws Exception {
        // Initialize the database
        mMatchEnvironmentRepository.saveAndFlush(mMatchEnvironment);

        int databaseSizeBeforeDelete = mMatchEnvironmentRepository.findAll().size();

        // Delete the mMatchEnvironment
        restMMatchEnvironmentMockMvc.perform(delete("/api/m-match-environments/{id}", mMatchEnvironment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMatchEnvironment> mMatchEnvironmentList = mMatchEnvironmentRepository.findAll();
        assertThat(mMatchEnvironmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMatchEnvironment.class);
        MMatchEnvironment mMatchEnvironment1 = new MMatchEnvironment();
        mMatchEnvironment1.setId(1L);
        MMatchEnvironment mMatchEnvironment2 = new MMatchEnvironment();
        mMatchEnvironment2.setId(mMatchEnvironment1.getId());
        assertThat(mMatchEnvironment1).isEqualTo(mMatchEnvironment2);
        mMatchEnvironment2.setId(2L);
        assertThat(mMatchEnvironment1).isNotEqualTo(mMatchEnvironment2);
        mMatchEnvironment1.setId(null);
        assertThat(mMatchEnvironment1).isNotEqualTo(mMatchEnvironment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMatchEnvironmentDTO.class);
        MMatchEnvironmentDTO mMatchEnvironmentDTO1 = new MMatchEnvironmentDTO();
        mMatchEnvironmentDTO1.setId(1L);
        MMatchEnvironmentDTO mMatchEnvironmentDTO2 = new MMatchEnvironmentDTO();
        assertThat(mMatchEnvironmentDTO1).isNotEqualTo(mMatchEnvironmentDTO2);
        mMatchEnvironmentDTO2.setId(mMatchEnvironmentDTO1.getId());
        assertThat(mMatchEnvironmentDTO1).isEqualTo(mMatchEnvironmentDTO2);
        mMatchEnvironmentDTO2.setId(2L);
        assertThat(mMatchEnvironmentDTO1).isNotEqualTo(mMatchEnvironmentDTO2);
        mMatchEnvironmentDTO1.setId(null);
        assertThat(mMatchEnvironmentDTO1).isNotEqualTo(mMatchEnvironmentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMatchEnvironmentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMatchEnvironmentMapper.fromId(null)).isNull();
    }
}
