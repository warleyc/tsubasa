package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionExtraCutin;
import io.shm.tsubasa.repository.MGachaRenditionExtraCutinRepository;
import io.shm.tsubasa.service.MGachaRenditionExtraCutinService;
import io.shm.tsubasa.service.dto.MGachaRenditionExtraCutinDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionExtraCutinMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionExtraCutinCriteria;
import io.shm.tsubasa.service.MGachaRenditionExtraCutinQueryService;

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
 * Integration tests for the {@Link MGachaRenditionExtraCutinResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionExtraCutinResourceIT {

    private static final Integer DEFAULT_RENDITION_ID = 1;
    private static final Integer UPDATED_RENDITION_ID = 2;

    private static final String DEFAULT_MAIN_PREFAB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_PREFAB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VOICE_START_CUT_IN = "AAAAAAAAAA";
    private static final String UPDATED_VOICE_START_CUT_IN = "BBBBBBBBBB";

    private static final String DEFAULT_SERIF = "AAAAAAAAAA";
    private static final String UPDATED_SERIF = "BBBBBBBBBB";

    @Autowired
    private MGachaRenditionExtraCutinRepository mGachaRenditionExtraCutinRepository;

    @Autowired
    private MGachaRenditionExtraCutinMapper mGachaRenditionExtraCutinMapper;

    @Autowired
    private MGachaRenditionExtraCutinService mGachaRenditionExtraCutinService;

    @Autowired
    private MGachaRenditionExtraCutinQueryService mGachaRenditionExtraCutinQueryService;

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

    private MockMvc restMGachaRenditionExtraCutinMockMvc;

    private MGachaRenditionExtraCutin mGachaRenditionExtraCutin;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionExtraCutinResource mGachaRenditionExtraCutinResource = new MGachaRenditionExtraCutinResource(mGachaRenditionExtraCutinService, mGachaRenditionExtraCutinQueryService);
        this.restMGachaRenditionExtraCutinMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionExtraCutinResource)
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
    public static MGachaRenditionExtraCutin createEntity(EntityManager em) {
        MGachaRenditionExtraCutin mGachaRenditionExtraCutin = new MGachaRenditionExtraCutin()
            .renditionId(DEFAULT_RENDITION_ID)
            .mainPrefabName(DEFAULT_MAIN_PREFAB_NAME)
            .voiceStartCutIn(DEFAULT_VOICE_START_CUT_IN)
            .serif(DEFAULT_SERIF);
        return mGachaRenditionExtraCutin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionExtraCutin createUpdatedEntity(EntityManager em) {
        MGachaRenditionExtraCutin mGachaRenditionExtraCutin = new MGachaRenditionExtraCutin()
            .renditionId(UPDATED_RENDITION_ID)
            .mainPrefabName(UPDATED_MAIN_PREFAB_NAME)
            .voiceStartCutIn(UPDATED_VOICE_START_CUT_IN)
            .serif(UPDATED_SERIF);
        return mGachaRenditionExtraCutin;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionExtraCutin = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionExtraCutin() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionExtraCutinRepository.findAll().size();

        // Create the MGachaRenditionExtraCutin
        MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO = mGachaRenditionExtraCutinMapper.toDto(mGachaRenditionExtraCutin);
        restMGachaRenditionExtraCutinMockMvc.perform(post("/api/m-gacha-rendition-extra-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionExtraCutinDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionExtraCutin in the database
        List<MGachaRenditionExtraCutin> mGachaRenditionExtraCutinList = mGachaRenditionExtraCutinRepository.findAll();
        assertThat(mGachaRenditionExtraCutinList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionExtraCutin testMGachaRenditionExtraCutin = mGachaRenditionExtraCutinList.get(mGachaRenditionExtraCutinList.size() - 1);
        assertThat(testMGachaRenditionExtraCutin.getRenditionId()).isEqualTo(DEFAULT_RENDITION_ID);
        assertThat(testMGachaRenditionExtraCutin.getMainPrefabName()).isEqualTo(DEFAULT_MAIN_PREFAB_NAME);
        assertThat(testMGachaRenditionExtraCutin.getVoiceStartCutIn()).isEqualTo(DEFAULT_VOICE_START_CUT_IN);
        assertThat(testMGachaRenditionExtraCutin.getSerif()).isEqualTo(DEFAULT_SERIF);
    }

    @Test
    @Transactional
    public void createMGachaRenditionExtraCutinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionExtraCutinRepository.findAll().size();

        // Create the MGachaRenditionExtraCutin with an existing ID
        mGachaRenditionExtraCutin.setId(1L);
        MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO = mGachaRenditionExtraCutinMapper.toDto(mGachaRenditionExtraCutin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionExtraCutinMockMvc.perform(post("/api/m-gacha-rendition-extra-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionExtraCutinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionExtraCutin in the database
        List<MGachaRenditionExtraCutin> mGachaRenditionExtraCutinList = mGachaRenditionExtraCutinRepository.findAll();
        assertThat(mGachaRenditionExtraCutinList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRenditionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionExtraCutinRepository.findAll().size();
        // set the field null
        mGachaRenditionExtraCutin.setRenditionId(null);

        // Create the MGachaRenditionExtraCutin, which fails.
        MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO = mGachaRenditionExtraCutinMapper.toDto(mGachaRenditionExtraCutin);

        restMGachaRenditionExtraCutinMockMvc.perform(post("/api/m-gacha-rendition-extra-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionExtraCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionExtraCutin> mGachaRenditionExtraCutinList = mGachaRenditionExtraCutinRepository.findAll();
        assertThat(mGachaRenditionExtraCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionExtraCutins() throws Exception {
        // Initialize the database
        mGachaRenditionExtraCutinRepository.saveAndFlush(mGachaRenditionExtraCutin);

        // Get all the mGachaRenditionExtraCutinList
        restMGachaRenditionExtraCutinMockMvc.perform(get("/api/m-gacha-rendition-extra-cutins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionExtraCutin.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].mainPrefabName").value(hasItem(DEFAULT_MAIN_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].voiceStartCutIn").value(hasItem(DEFAULT_VOICE_START_CUT_IN.toString())))
            .andExpect(jsonPath("$.[*].serif").value(hasItem(DEFAULT_SERIF.toString())));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionExtraCutin() throws Exception {
        // Initialize the database
        mGachaRenditionExtraCutinRepository.saveAndFlush(mGachaRenditionExtraCutin);

        // Get the mGachaRenditionExtraCutin
        restMGachaRenditionExtraCutinMockMvc.perform(get("/api/m-gacha-rendition-extra-cutins/{id}", mGachaRenditionExtraCutin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionExtraCutin.getId().intValue()))
            .andExpect(jsonPath("$.renditionId").value(DEFAULT_RENDITION_ID))
            .andExpect(jsonPath("$.mainPrefabName").value(DEFAULT_MAIN_PREFAB_NAME.toString()))
            .andExpect(jsonPath("$.voiceStartCutIn").value(DEFAULT_VOICE_START_CUT_IN.toString()))
            .andExpect(jsonPath("$.serif").value(DEFAULT_SERIF.toString()));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionExtraCutinsByRenditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionExtraCutinRepository.saveAndFlush(mGachaRenditionExtraCutin);

        // Get all the mGachaRenditionExtraCutinList where renditionId equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionExtraCutinShouldBeFound("renditionId.equals=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionExtraCutinList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionExtraCutinShouldNotBeFound("renditionId.equals=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionExtraCutinsByRenditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionExtraCutinRepository.saveAndFlush(mGachaRenditionExtraCutin);

        // Get all the mGachaRenditionExtraCutinList where renditionId in DEFAULT_RENDITION_ID or UPDATED_RENDITION_ID
        defaultMGachaRenditionExtraCutinShouldBeFound("renditionId.in=" + DEFAULT_RENDITION_ID + "," + UPDATED_RENDITION_ID);

        // Get all the mGachaRenditionExtraCutinList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionExtraCutinShouldNotBeFound("renditionId.in=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionExtraCutinsByRenditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionExtraCutinRepository.saveAndFlush(mGachaRenditionExtraCutin);

        // Get all the mGachaRenditionExtraCutinList where renditionId is not null
        defaultMGachaRenditionExtraCutinShouldBeFound("renditionId.specified=true");

        // Get all the mGachaRenditionExtraCutinList where renditionId is null
        defaultMGachaRenditionExtraCutinShouldNotBeFound("renditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionExtraCutinsByRenditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionExtraCutinRepository.saveAndFlush(mGachaRenditionExtraCutin);

        // Get all the mGachaRenditionExtraCutinList where renditionId greater than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionExtraCutinShouldBeFound("renditionId.greaterOrEqualThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionExtraCutinList where renditionId greater than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionExtraCutinShouldNotBeFound("renditionId.greaterOrEqualThan=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionExtraCutinsByRenditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionExtraCutinRepository.saveAndFlush(mGachaRenditionExtraCutin);

        // Get all the mGachaRenditionExtraCutinList where renditionId less than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionExtraCutinShouldNotBeFound("renditionId.lessThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionExtraCutinList where renditionId less than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionExtraCutinShouldBeFound("renditionId.lessThan=" + UPDATED_RENDITION_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionExtraCutinShouldBeFound(String filter) throws Exception {
        restMGachaRenditionExtraCutinMockMvc.perform(get("/api/m-gacha-rendition-extra-cutins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionExtraCutin.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].mainPrefabName").value(hasItem(DEFAULT_MAIN_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].voiceStartCutIn").value(hasItem(DEFAULT_VOICE_START_CUT_IN.toString())))
            .andExpect(jsonPath("$.[*].serif").value(hasItem(DEFAULT_SERIF.toString())));

        // Check, that the count call also returns 1
        restMGachaRenditionExtraCutinMockMvc.perform(get("/api/m-gacha-rendition-extra-cutins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionExtraCutinShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionExtraCutinMockMvc.perform(get("/api/m-gacha-rendition-extra-cutins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionExtraCutinMockMvc.perform(get("/api/m-gacha-rendition-extra-cutins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionExtraCutin() throws Exception {
        // Get the mGachaRenditionExtraCutin
        restMGachaRenditionExtraCutinMockMvc.perform(get("/api/m-gacha-rendition-extra-cutins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionExtraCutin() throws Exception {
        // Initialize the database
        mGachaRenditionExtraCutinRepository.saveAndFlush(mGachaRenditionExtraCutin);

        int databaseSizeBeforeUpdate = mGachaRenditionExtraCutinRepository.findAll().size();

        // Update the mGachaRenditionExtraCutin
        MGachaRenditionExtraCutin updatedMGachaRenditionExtraCutin = mGachaRenditionExtraCutinRepository.findById(mGachaRenditionExtraCutin.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionExtraCutin are not directly saved in db
        em.detach(updatedMGachaRenditionExtraCutin);
        updatedMGachaRenditionExtraCutin
            .renditionId(UPDATED_RENDITION_ID)
            .mainPrefabName(UPDATED_MAIN_PREFAB_NAME)
            .voiceStartCutIn(UPDATED_VOICE_START_CUT_IN)
            .serif(UPDATED_SERIF);
        MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO = mGachaRenditionExtraCutinMapper.toDto(updatedMGachaRenditionExtraCutin);

        restMGachaRenditionExtraCutinMockMvc.perform(put("/api/m-gacha-rendition-extra-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionExtraCutinDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionExtraCutin in the database
        List<MGachaRenditionExtraCutin> mGachaRenditionExtraCutinList = mGachaRenditionExtraCutinRepository.findAll();
        assertThat(mGachaRenditionExtraCutinList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionExtraCutin testMGachaRenditionExtraCutin = mGachaRenditionExtraCutinList.get(mGachaRenditionExtraCutinList.size() - 1);
        assertThat(testMGachaRenditionExtraCutin.getRenditionId()).isEqualTo(UPDATED_RENDITION_ID);
        assertThat(testMGachaRenditionExtraCutin.getMainPrefabName()).isEqualTo(UPDATED_MAIN_PREFAB_NAME);
        assertThat(testMGachaRenditionExtraCutin.getVoiceStartCutIn()).isEqualTo(UPDATED_VOICE_START_CUT_IN);
        assertThat(testMGachaRenditionExtraCutin.getSerif()).isEqualTo(UPDATED_SERIF);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionExtraCutin() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionExtraCutinRepository.findAll().size();

        // Create the MGachaRenditionExtraCutin
        MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO = mGachaRenditionExtraCutinMapper.toDto(mGachaRenditionExtraCutin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionExtraCutinMockMvc.perform(put("/api/m-gacha-rendition-extra-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionExtraCutinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionExtraCutin in the database
        List<MGachaRenditionExtraCutin> mGachaRenditionExtraCutinList = mGachaRenditionExtraCutinRepository.findAll();
        assertThat(mGachaRenditionExtraCutinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionExtraCutin() throws Exception {
        // Initialize the database
        mGachaRenditionExtraCutinRepository.saveAndFlush(mGachaRenditionExtraCutin);

        int databaseSizeBeforeDelete = mGachaRenditionExtraCutinRepository.findAll().size();

        // Delete the mGachaRenditionExtraCutin
        restMGachaRenditionExtraCutinMockMvc.perform(delete("/api/m-gacha-rendition-extra-cutins/{id}", mGachaRenditionExtraCutin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionExtraCutin> mGachaRenditionExtraCutinList = mGachaRenditionExtraCutinRepository.findAll();
        assertThat(mGachaRenditionExtraCutinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionExtraCutin.class);
        MGachaRenditionExtraCutin mGachaRenditionExtraCutin1 = new MGachaRenditionExtraCutin();
        mGachaRenditionExtraCutin1.setId(1L);
        MGachaRenditionExtraCutin mGachaRenditionExtraCutin2 = new MGachaRenditionExtraCutin();
        mGachaRenditionExtraCutin2.setId(mGachaRenditionExtraCutin1.getId());
        assertThat(mGachaRenditionExtraCutin1).isEqualTo(mGachaRenditionExtraCutin2);
        mGachaRenditionExtraCutin2.setId(2L);
        assertThat(mGachaRenditionExtraCutin1).isNotEqualTo(mGachaRenditionExtraCutin2);
        mGachaRenditionExtraCutin1.setId(null);
        assertThat(mGachaRenditionExtraCutin1).isNotEqualTo(mGachaRenditionExtraCutin2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionExtraCutinDTO.class);
        MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO1 = new MGachaRenditionExtraCutinDTO();
        mGachaRenditionExtraCutinDTO1.setId(1L);
        MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO2 = new MGachaRenditionExtraCutinDTO();
        assertThat(mGachaRenditionExtraCutinDTO1).isNotEqualTo(mGachaRenditionExtraCutinDTO2);
        mGachaRenditionExtraCutinDTO2.setId(mGachaRenditionExtraCutinDTO1.getId());
        assertThat(mGachaRenditionExtraCutinDTO1).isEqualTo(mGachaRenditionExtraCutinDTO2);
        mGachaRenditionExtraCutinDTO2.setId(2L);
        assertThat(mGachaRenditionExtraCutinDTO1).isNotEqualTo(mGachaRenditionExtraCutinDTO2);
        mGachaRenditionExtraCutinDTO1.setId(null);
        assertThat(mGachaRenditionExtraCutinDTO1).isNotEqualTo(mGachaRenditionExtraCutinDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionExtraCutinMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionExtraCutinMapper.fromId(null)).isNull();
    }
}
