package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MModelQuestStage;
import io.shm.tsubasa.repository.MModelQuestStageRepository;
import io.shm.tsubasa.service.MModelQuestStageService;
import io.shm.tsubasa.service.dto.MModelQuestStageDTO;
import io.shm.tsubasa.service.mapper.MModelQuestStageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MModelQuestStageCriteria;
import io.shm.tsubasa.service.MModelQuestStageQueryService;

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
 * Integration tests for the {@Link MModelQuestStageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MModelQuestStageResourceIT {

    private static final Integer DEFAULT_STAGE_ID = 1;
    private static final Integer UPDATED_STAGE_ID = 2;

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BGM_OFFENCING = "AAAAAAAAAA";
    private static final String UPDATED_BGM_OFFENCING = "BBBBBBBBBB";

    private static final String DEFAULT_BGM_DEFENCING = "AAAAAAAAAA";
    private static final String UPDATED_BGM_DEFENCING = "BBBBBBBBBB";

    private static final String DEFAULT_BGM_HURRYING = "AAAAAAAAAA";
    private static final String UPDATED_BGM_HURRYING = "BBBBBBBBBB";

    @Autowired
    private MModelQuestStageRepository mModelQuestStageRepository;

    @Autowired
    private MModelQuestStageMapper mModelQuestStageMapper;

    @Autowired
    private MModelQuestStageService mModelQuestStageService;

    @Autowired
    private MModelQuestStageQueryService mModelQuestStageQueryService;

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

    private MockMvc restMModelQuestStageMockMvc;

    private MModelQuestStage mModelQuestStage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MModelQuestStageResource mModelQuestStageResource = new MModelQuestStageResource(mModelQuestStageService, mModelQuestStageQueryService);
        this.restMModelQuestStageMockMvc = MockMvcBuilders.standaloneSetup(mModelQuestStageResource)
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
    public static MModelQuestStage createEntity(EntityManager em) {
        MModelQuestStage mModelQuestStage = new MModelQuestStage()
            .stageId(DEFAULT_STAGE_ID)
            .image(DEFAULT_IMAGE)
            .modelName(DEFAULT_MODEL_NAME)
            .bgmOffencing(DEFAULT_BGM_OFFENCING)
            .bgmDefencing(DEFAULT_BGM_DEFENCING)
            .bgmHurrying(DEFAULT_BGM_HURRYING);
        return mModelQuestStage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MModelQuestStage createUpdatedEntity(EntityManager em) {
        MModelQuestStage mModelQuestStage = new MModelQuestStage()
            .stageId(UPDATED_STAGE_ID)
            .image(UPDATED_IMAGE)
            .modelName(UPDATED_MODEL_NAME)
            .bgmOffencing(UPDATED_BGM_OFFENCING)
            .bgmDefencing(UPDATED_BGM_DEFENCING)
            .bgmHurrying(UPDATED_BGM_HURRYING);
        return mModelQuestStage;
    }

    @BeforeEach
    public void initTest() {
        mModelQuestStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMModelQuestStage() throws Exception {
        int databaseSizeBeforeCreate = mModelQuestStageRepository.findAll().size();

        // Create the MModelQuestStage
        MModelQuestStageDTO mModelQuestStageDTO = mModelQuestStageMapper.toDto(mModelQuestStage);
        restMModelQuestStageMockMvc.perform(post("/api/m-model-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelQuestStageDTO)))
            .andExpect(status().isCreated());

        // Validate the MModelQuestStage in the database
        List<MModelQuestStage> mModelQuestStageList = mModelQuestStageRepository.findAll();
        assertThat(mModelQuestStageList).hasSize(databaseSizeBeforeCreate + 1);
        MModelQuestStage testMModelQuestStage = mModelQuestStageList.get(mModelQuestStageList.size() - 1);
        assertThat(testMModelQuestStage.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMModelQuestStage.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testMModelQuestStage.getModelName()).isEqualTo(DEFAULT_MODEL_NAME);
        assertThat(testMModelQuestStage.getBgmOffencing()).isEqualTo(DEFAULT_BGM_OFFENCING);
        assertThat(testMModelQuestStage.getBgmDefencing()).isEqualTo(DEFAULT_BGM_DEFENCING);
        assertThat(testMModelQuestStage.getBgmHurrying()).isEqualTo(DEFAULT_BGM_HURRYING);
    }

    @Test
    @Transactional
    public void createMModelQuestStageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mModelQuestStageRepository.findAll().size();

        // Create the MModelQuestStage with an existing ID
        mModelQuestStage.setId(1L);
        MModelQuestStageDTO mModelQuestStageDTO = mModelQuestStageMapper.toDto(mModelQuestStage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMModelQuestStageMockMvc.perform(post("/api/m-model-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelQuestStage in the database
        List<MModelQuestStage> mModelQuestStageList = mModelQuestStageRepository.findAll();
        assertThat(mModelQuestStageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelQuestStageRepository.findAll().size();
        // set the field null
        mModelQuestStage.setStageId(null);

        // Create the MModelQuestStage, which fails.
        MModelQuestStageDTO mModelQuestStageDTO = mModelQuestStageMapper.toDto(mModelQuestStage);

        restMModelQuestStageMockMvc.perform(post("/api/m-model-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelQuestStageDTO)))
            .andExpect(status().isBadRequest());

        List<MModelQuestStage> mModelQuestStageList = mModelQuestStageRepository.findAll();
        assertThat(mModelQuestStageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMModelQuestStages() throws Exception {
        // Initialize the database
        mModelQuestStageRepository.saveAndFlush(mModelQuestStage);

        // Get all the mModelQuestStageList
        restMModelQuestStageMockMvc.perform(get("/api/m-model-quest-stages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelQuestStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].modelName").value(hasItem(DEFAULT_MODEL_NAME.toString())))
            .andExpect(jsonPath("$.[*].bgmOffencing").value(hasItem(DEFAULT_BGM_OFFENCING.toString())))
            .andExpect(jsonPath("$.[*].bgmDefencing").value(hasItem(DEFAULT_BGM_DEFENCING.toString())))
            .andExpect(jsonPath("$.[*].bgmHurrying").value(hasItem(DEFAULT_BGM_HURRYING.toString())));
    }
    
    @Test
    @Transactional
    public void getMModelQuestStage() throws Exception {
        // Initialize the database
        mModelQuestStageRepository.saveAndFlush(mModelQuestStage);

        // Get the mModelQuestStage
        restMModelQuestStageMockMvc.perform(get("/api/m-model-quest-stages/{id}", mModelQuestStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mModelQuestStage.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.modelName").value(DEFAULT_MODEL_NAME.toString()))
            .andExpect(jsonPath("$.bgmOffencing").value(DEFAULT_BGM_OFFENCING.toString()))
            .andExpect(jsonPath("$.bgmDefencing").value(DEFAULT_BGM_DEFENCING.toString()))
            .andExpect(jsonPath("$.bgmHurrying").value(DEFAULT_BGM_HURRYING.toString()));
    }

    @Test
    @Transactional
    public void getAllMModelQuestStagesByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelQuestStageRepository.saveAndFlush(mModelQuestStage);

        // Get all the mModelQuestStageList where stageId equals to DEFAULT_STAGE_ID
        defaultMModelQuestStageShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mModelQuestStageList where stageId equals to UPDATED_STAGE_ID
        defaultMModelQuestStageShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMModelQuestStagesByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mModelQuestStageRepository.saveAndFlush(mModelQuestStage);

        // Get all the mModelQuestStageList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMModelQuestStageShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mModelQuestStageList where stageId equals to UPDATED_STAGE_ID
        defaultMModelQuestStageShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMModelQuestStagesByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelQuestStageRepository.saveAndFlush(mModelQuestStage);

        // Get all the mModelQuestStageList where stageId is not null
        defaultMModelQuestStageShouldBeFound("stageId.specified=true");

        // Get all the mModelQuestStageList where stageId is null
        defaultMModelQuestStageShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelQuestStagesByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelQuestStageRepository.saveAndFlush(mModelQuestStage);

        // Get all the mModelQuestStageList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMModelQuestStageShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mModelQuestStageList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMModelQuestStageShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMModelQuestStagesByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelQuestStageRepository.saveAndFlush(mModelQuestStage);

        // Get all the mModelQuestStageList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMModelQuestStageShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mModelQuestStageList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMModelQuestStageShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMModelQuestStageShouldBeFound(String filter) throws Exception {
        restMModelQuestStageMockMvc.perform(get("/api/m-model-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelQuestStage.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].modelName").value(hasItem(DEFAULT_MODEL_NAME.toString())))
            .andExpect(jsonPath("$.[*].bgmOffencing").value(hasItem(DEFAULT_BGM_OFFENCING.toString())))
            .andExpect(jsonPath("$.[*].bgmDefencing").value(hasItem(DEFAULT_BGM_DEFENCING.toString())))
            .andExpect(jsonPath("$.[*].bgmHurrying").value(hasItem(DEFAULT_BGM_HURRYING.toString())));

        // Check, that the count call also returns 1
        restMModelQuestStageMockMvc.perform(get("/api/m-model-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMModelQuestStageShouldNotBeFound(String filter) throws Exception {
        restMModelQuestStageMockMvc.perform(get("/api/m-model-quest-stages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMModelQuestStageMockMvc.perform(get("/api/m-model-quest-stages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMModelQuestStage() throws Exception {
        // Get the mModelQuestStage
        restMModelQuestStageMockMvc.perform(get("/api/m-model-quest-stages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMModelQuestStage() throws Exception {
        // Initialize the database
        mModelQuestStageRepository.saveAndFlush(mModelQuestStage);

        int databaseSizeBeforeUpdate = mModelQuestStageRepository.findAll().size();

        // Update the mModelQuestStage
        MModelQuestStage updatedMModelQuestStage = mModelQuestStageRepository.findById(mModelQuestStage.getId()).get();
        // Disconnect from session so that the updates on updatedMModelQuestStage are not directly saved in db
        em.detach(updatedMModelQuestStage);
        updatedMModelQuestStage
            .stageId(UPDATED_STAGE_ID)
            .image(UPDATED_IMAGE)
            .modelName(UPDATED_MODEL_NAME)
            .bgmOffencing(UPDATED_BGM_OFFENCING)
            .bgmDefencing(UPDATED_BGM_DEFENCING)
            .bgmHurrying(UPDATED_BGM_HURRYING);
        MModelQuestStageDTO mModelQuestStageDTO = mModelQuestStageMapper.toDto(updatedMModelQuestStage);

        restMModelQuestStageMockMvc.perform(put("/api/m-model-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelQuestStageDTO)))
            .andExpect(status().isOk());

        // Validate the MModelQuestStage in the database
        List<MModelQuestStage> mModelQuestStageList = mModelQuestStageRepository.findAll();
        assertThat(mModelQuestStageList).hasSize(databaseSizeBeforeUpdate);
        MModelQuestStage testMModelQuestStage = mModelQuestStageList.get(mModelQuestStageList.size() - 1);
        assertThat(testMModelQuestStage.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMModelQuestStage.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testMModelQuestStage.getModelName()).isEqualTo(UPDATED_MODEL_NAME);
        assertThat(testMModelQuestStage.getBgmOffencing()).isEqualTo(UPDATED_BGM_OFFENCING);
        assertThat(testMModelQuestStage.getBgmDefencing()).isEqualTo(UPDATED_BGM_DEFENCING);
        assertThat(testMModelQuestStage.getBgmHurrying()).isEqualTo(UPDATED_BGM_HURRYING);
    }

    @Test
    @Transactional
    public void updateNonExistingMModelQuestStage() throws Exception {
        int databaseSizeBeforeUpdate = mModelQuestStageRepository.findAll().size();

        // Create the MModelQuestStage
        MModelQuestStageDTO mModelQuestStageDTO = mModelQuestStageMapper.toDto(mModelQuestStage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMModelQuestStageMockMvc.perform(put("/api/m-model-quest-stages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelQuestStageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelQuestStage in the database
        List<MModelQuestStage> mModelQuestStageList = mModelQuestStageRepository.findAll();
        assertThat(mModelQuestStageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMModelQuestStage() throws Exception {
        // Initialize the database
        mModelQuestStageRepository.saveAndFlush(mModelQuestStage);

        int databaseSizeBeforeDelete = mModelQuestStageRepository.findAll().size();

        // Delete the mModelQuestStage
        restMModelQuestStageMockMvc.perform(delete("/api/m-model-quest-stages/{id}", mModelQuestStage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MModelQuestStage> mModelQuestStageList = mModelQuestStageRepository.findAll();
        assertThat(mModelQuestStageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelQuestStage.class);
        MModelQuestStage mModelQuestStage1 = new MModelQuestStage();
        mModelQuestStage1.setId(1L);
        MModelQuestStage mModelQuestStage2 = new MModelQuestStage();
        mModelQuestStage2.setId(mModelQuestStage1.getId());
        assertThat(mModelQuestStage1).isEqualTo(mModelQuestStage2);
        mModelQuestStage2.setId(2L);
        assertThat(mModelQuestStage1).isNotEqualTo(mModelQuestStage2);
        mModelQuestStage1.setId(null);
        assertThat(mModelQuestStage1).isNotEqualTo(mModelQuestStage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelQuestStageDTO.class);
        MModelQuestStageDTO mModelQuestStageDTO1 = new MModelQuestStageDTO();
        mModelQuestStageDTO1.setId(1L);
        MModelQuestStageDTO mModelQuestStageDTO2 = new MModelQuestStageDTO();
        assertThat(mModelQuestStageDTO1).isNotEqualTo(mModelQuestStageDTO2);
        mModelQuestStageDTO2.setId(mModelQuestStageDTO1.getId());
        assertThat(mModelQuestStageDTO1).isEqualTo(mModelQuestStageDTO2);
        mModelQuestStageDTO2.setId(2L);
        assertThat(mModelQuestStageDTO1).isNotEqualTo(mModelQuestStageDTO2);
        mModelQuestStageDTO1.setId(null);
        assertThat(mModelQuestStageDTO1).isNotEqualTo(mModelQuestStageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mModelQuestStageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mModelQuestStageMapper.fromId(null)).isNull();
    }
}
