package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MStoryResourceMapping;
import io.shm.tsubasa.repository.MStoryResourceMappingRepository;
import io.shm.tsubasa.service.MStoryResourceMappingService;
import io.shm.tsubasa.service.dto.MStoryResourceMappingDTO;
import io.shm.tsubasa.service.mapper.MStoryResourceMappingMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MStoryResourceMappingCriteria;
import io.shm.tsubasa.service.MStoryResourceMappingQueryService;

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
 * Integration tests for the {@Link MStoryResourceMappingResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MStoryResourceMappingResourceIT {

    private static final Integer DEFAULT_LANG = 1;
    private static final Integer UPDATED_LANG = 2;

    private static final String DEFAULT_SCRIPT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SCRIPT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IDS = "AAAAAAAAAA";
    private static final String UPDATED_IDS = "BBBBBBBBBB";

    @Autowired
    private MStoryResourceMappingRepository mStoryResourceMappingRepository;

    @Autowired
    private MStoryResourceMappingMapper mStoryResourceMappingMapper;

    @Autowired
    private MStoryResourceMappingService mStoryResourceMappingService;

    @Autowired
    private MStoryResourceMappingQueryService mStoryResourceMappingQueryService;

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

    private MockMvc restMStoryResourceMappingMockMvc;

    private MStoryResourceMapping mStoryResourceMapping;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MStoryResourceMappingResource mStoryResourceMappingResource = new MStoryResourceMappingResource(mStoryResourceMappingService, mStoryResourceMappingQueryService);
        this.restMStoryResourceMappingMockMvc = MockMvcBuilders.standaloneSetup(mStoryResourceMappingResource)
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
    public static MStoryResourceMapping createEntity(EntityManager em) {
        MStoryResourceMapping mStoryResourceMapping = new MStoryResourceMapping()
            .lang(DEFAULT_LANG)
            .scriptName(DEFAULT_SCRIPT_NAME)
            .ids(DEFAULT_IDS);
        return mStoryResourceMapping;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MStoryResourceMapping createUpdatedEntity(EntityManager em) {
        MStoryResourceMapping mStoryResourceMapping = new MStoryResourceMapping()
            .lang(UPDATED_LANG)
            .scriptName(UPDATED_SCRIPT_NAME)
            .ids(UPDATED_IDS);
        return mStoryResourceMapping;
    }

    @BeforeEach
    public void initTest() {
        mStoryResourceMapping = createEntity(em);
    }

    @Test
    @Transactional
    public void createMStoryResourceMapping() throws Exception {
        int databaseSizeBeforeCreate = mStoryResourceMappingRepository.findAll().size();

        // Create the MStoryResourceMapping
        MStoryResourceMappingDTO mStoryResourceMappingDTO = mStoryResourceMappingMapper.toDto(mStoryResourceMapping);
        restMStoryResourceMappingMockMvc.perform(post("/api/m-story-resource-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoryResourceMappingDTO)))
            .andExpect(status().isCreated());

        // Validate the MStoryResourceMapping in the database
        List<MStoryResourceMapping> mStoryResourceMappingList = mStoryResourceMappingRepository.findAll();
        assertThat(mStoryResourceMappingList).hasSize(databaseSizeBeforeCreate + 1);
        MStoryResourceMapping testMStoryResourceMapping = mStoryResourceMappingList.get(mStoryResourceMappingList.size() - 1);
        assertThat(testMStoryResourceMapping.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testMStoryResourceMapping.getScriptName()).isEqualTo(DEFAULT_SCRIPT_NAME);
        assertThat(testMStoryResourceMapping.getIds()).isEqualTo(DEFAULT_IDS);
    }

    @Test
    @Transactional
    public void createMStoryResourceMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mStoryResourceMappingRepository.findAll().size();

        // Create the MStoryResourceMapping with an existing ID
        mStoryResourceMapping.setId(1L);
        MStoryResourceMappingDTO mStoryResourceMappingDTO = mStoryResourceMappingMapper.toDto(mStoryResourceMapping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMStoryResourceMappingMockMvc.perform(post("/api/m-story-resource-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoryResourceMappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MStoryResourceMapping in the database
        List<MStoryResourceMapping> mStoryResourceMappingList = mStoryResourceMappingRepository.findAll();
        assertThat(mStoryResourceMappingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLangIsRequired() throws Exception {
        int databaseSizeBeforeTest = mStoryResourceMappingRepository.findAll().size();
        // set the field null
        mStoryResourceMapping.setLang(null);

        // Create the MStoryResourceMapping, which fails.
        MStoryResourceMappingDTO mStoryResourceMappingDTO = mStoryResourceMappingMapper.toDto(mStoryResourceMapping);

        restMStoryResourceMappingMockMvc.perform(post("/api/m-story-resource-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoryResourceMappingDTO)))
            .andExpect(status().isBadRequest());

        List<MStoryResourceMapping> mStoryResourceMappingList = mStoryResourceMappingRepository.findAll();
        assertThat(mStoryResourceMappingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMStoryResourceMappings() throws Exception {
        // Initialize the database
        mStoryResourceMappingRepository.saveAndFlush(mStoryResourceMapping);

        // Get all the mStoryResourceMappingList
        restMStoryResourceMappingMockMvc.perform(get("/api/m-story-resource-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mStoryResourceMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)))
            .andExpect(jsonPath("$.[*].scriptName").value(hasItem(DEFAULT_SCRIPT_NAME.toString())))
            .andExpect(jsonPath("$.[*].ids").value(hasItem(DEFAULT_IDS.toString())));
    }
    
    @Test
    @Transactional
    public void getMStoryResourceMapping() throws Exception {
        // Initialize the database
        mStoryResourceMappingRepository.saveAndFlush(mStoryResourceMapping);

        // Get the mStoryResourceMapping
        restMStoryResourceMappingMockMvc.perform(get("/api/m-story-resource-mappings/{id}", mStoryResourceMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mStoryResourceMapping.getId().intValue()))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG))
            .andExpect(jsonPath("$.scriptName").value(DEFAULT_SCRIPT_NAME.toString()))
            .andExpect(jsonPath("$.ids").value(DEFAULT_IDS.toString()));
    }

    @Test
    @Transactional
    public void getAllMStoryResourceMappingsByLangIsEqualToSomething() throws Exception {
        // Initialize the database
        mStoryResourceMappingRepository.saveAndFlush(mStoryResourceMapping);

        // Get all the mStoryResourceMappingList where lang equals to DEFAULT_LANG
        defaultMStoryResourceMappingShouldBeFound("lang.equals=" + DEFAULT_LANG);

        // Get all the mStoryResourceMappingList where lang equals to UPDATED_LANG
        defaultMStoryResourceMappingShouldNotBeFound("lang.equals=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllMStoryResourceMappingsByLangIsInShouldWork() throws Exception {
        // Initialize the database
        mStoryResourceMappingRepository.saveAndFlush(mStoryResourceMapping);

        // Get all the mStoryResourceMappingList where lang in DEFAULT_LANG or UPDATED_LANG
        defaultMStoryResourceMappingShouldBeFound("lang.in=" + DEFAULT_LANG + "," + UPDATED_LANG);

        // Get all the mStoryResourceMappingList where lang equals to UPDATED_LANG
        defaultMStoryResourceMappingShouldNotBeFound("lang.in=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllMStoryResourceMappingsByLangIsNullOrNotNull() throws Exception {
        // Initialize the database
        mStoryResourceMappingRepository.saveAndFlush(mStoryResourceMapping);

        // Get all the mStoryResourceMappingList where lang is not null
        defaultMStoryResourceMappingShouldBeFound("lang.specified=true");

        // Get all the mStoryResourceMappingList where lang is null
        defaultMStoryResourceMappingShouldNotBeFound("lang.specified=false");
    }

    @Test
    @Transactional
    public void getAllMStoryResourceMappingsByLangIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mStoryResourceMappingRepository.saveAndFlush(mStoryResourceMapping);

        // Get all the mStoryResourceMappingList where lang greater than or equals to DEFAULT_LANG
        defaultMStoryResourceMappingShouldBeFound("lang.greaterOrEqualThan=" + DEFAULT_LANG);

        // Get all the mStoryResourceMappingList where lang greater than or equals to UPDATED_LANG
        defaultMStoryResourceMappingShouldNotBeFound("lang.greaterOrEqualThan=" + UPDATED_LANG);
    }

    @Test
    @Transactional
    public void getAllMStoryResourceMappingsByLangIsLessThanSomething() throws Exception {
        // Initialize the database
        mStoryResourceMappingRepository.saveAndFlush(mStoryResourceMapping);

        // Get all the mStoryResourceMappingList where lang less than or equals to DEFAULT_LANG
        defaultMStoryResourceMappingShouldNotBeFound("lang.lessThan=" + DEFAULT_LANG);

        // Get all the mStoryResourceMappingList where lang less than or equals to UPDATED_LANG
        defaultMStoryResourceMappingShouldBeFound("lang.lessThan=" + UPDATED_LANG);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMStoryResourceMappingShouldBeFound(String filter) throws Exception {
        restMStoryResourceMappingMockMvc.perform(get("/api/m-story-resource-mappings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mStoryResourceMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)))
            .andExpect(jsonPath("$.[*].scriptName").value(hasItem(DEFAULT_SCRIPT_NAME.toString())))
            .andExpect(jsonPath("$.[*].ids").value(hasItem(DEFAULT_IDS.toString())));

        // Check, that the count call also returns 1
        restMStoryResourceMappingMockMvc.perform(get("/api/m-story-resource-mappings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMStoryResourceMappingShouldNotBeFound(String filter) throws Exception {
        restMStoryResourceMappingMockMvc.perform(get("/api/m-story-resource-mappings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMStoryResourceMappingMockMvc.perform(get("/api/m-story-resource-mappings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMStoryResourceMapping() throws Exception {
        // Get the mStoryResourceMapping
        restMStoryResourceMappingMockMvc.perform(get("/api/m-story-resource-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMStoryResourceMapping() throws Exception {
        // Initialize the database
        mStoryResourceMappingRepository.saveAndFlush(mStoryResourceMapping);

        int databaseSizeBeforeUpdate = mStoryResourceMappingRepository.findAll().size();

        // Update the mStoryResourceMapping
        MStoryResourceMapping updatedMStoryResourceMapping = mStoryResourceMappingRepository.findById(mStoryResourceMapping.getId()).get();
        // Disconnect from session so that the updates on updatedMStoryResourceMapping are not directly saved in db
        em.detach(updatedMStoryResourceMapping);
        updatedMStoryResourceMapping
            .lang(UPDATED_LANG)
            .scriptName(UPDATED_SCRIPT_NAME)
            .ids(UPDATED_IDS);
        MStoryResourceMappingDTO mStoryResourceMappingDTO = mStoryResourceMappingMapper.toDto(updatedMStoryResourceMapping);

        restMStoryResourceMappingMockMvc.perform(put("/api/m-story-resource-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoryResourceMappingDTO)))
            .andExpect(status().isOk());

        // Validate the MStoryResourceMapping in the database
        List<MStoryResourceMapping> mStoryResourceMappingList = mStoryResourceMappingRepository.findAll();
        assertThat(mStoryResourceMappingList).hasSize(databaseSizeBeforeUpdate);
        MStoryResourceMapping testMStoryResourceMapping = mStoryResourceMappingList.get(mStoryResourceMappingList.size() - 1);
        assertThat(testMStoryResourceMapping.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testMStoryResourceMapping.getScriptName()).isEqualTo(UPDATED_SCRIPT_NAME);
        assertThat(testMStoryResourceMapping.getIds()).isEqualTo(UPDATED_IDS);
    }

    @Test
    @Transactional
    public void updateNonExistingMStoryResourceMapping() throws Exception {
        int databaseSizeBeforeUpdate = mStoryResourceMappingRepository.findAll().size();

        // Create the MStoryResourceMapping
        MStoryResourceMappingDTO mStoryResourceMappingDTO = mStoryResourceMappingMapper.toDto(mStoryResourceMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMStoryResourceMappingMockMvc.perform(put("/api/m-story-resource-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoryResourceMappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MStoryResourceMapping in the database
        List<MStoryResourceMapping> mStoryResourceMappingList = mStoryResourceMappingRepository.findAll();
        assertThat(mStoryResourceMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMStoryResourceMapping() throws Exception {
        // Initialize the database
        mStoryResourceMappingRepository.saveAndFlush(mStoryResourceMapping);

        int databaseSizeBeforeDelete = mStoryResourceMappingRepository.findAll().size();

        // Delete the mStoryResourceMapping
        restMStoryResourceMappingMockMvc.perform(delete("/api/m-story-resource-mappings/{id}", mStoryResourceMapping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MStoryResourceMapping> mStoryResourceMappingList = mStoryResourceMappingRepository.findAll();
        assertThat(mStoryResourceMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MStoryResourceMapping.class);
        MStoryResourceMapping mStoryResourceMapping1 = new MStoryResourceMapping();
        mStoryResourceMapping1.setId(1L);
        MStoryResourceMapping mStoryResourceMapping2 = new MStoryResourceMapping();
        mStoryResourceMapping2.setId(mStoryResourceMapping1.getId());
        assertThat(mStoryResourceMapping1).isEqualTo(mStoryResourceMapping2);
        mStoryResourceMapping2.setId(2L);
        assertThat(mStoryResourceMapping1).isNotEqualTo(mStoryResourceMapping2);
        mStoryResourceMapping1.setId(null);
        assertThat(mStoryResourceMapping1).isNotEqualTo(mStoryResourceMapping2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MStoryResourceMappingDTO.class);
        MStoryResourceMappingDTO mStoryResourceMappingDTO1 = new MStoryResourceMappingDTO();
        mStoryResourceMappingDTO1.setId(1L);
        MStoryResourceMappingDTO mStoryResourceMappingDTO2 = new MStoryResourceMappingDTO();
        assertThat(mStoryResourceMappingDTO1).isNotEqualTo(mStoryResourceMappingDTO2);
        mStoryResourceMappingDTO2.setId(mStoryResourceMappingDTO1.getId());
        assertThat(mStoryResourceMappingDTO1).isEqualTo(mStoryResourceMappingDTO2);
        mStoryResourceMappingDTO2.setId(2L);
        assertThat(mStoryResourceMappingDTO1).isNotEqualTo(mStoryResourceMappingDTO2);
        mStoryResourceMappingDTO1.setId(null);
        assertThat(mStoryResourceMappingDTO1).isNotEqualTo(mStoryResourceMappingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mStoryResourceMappingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mStoryResourceMappingMapper.fromId(null)).isNull();
    }
}
