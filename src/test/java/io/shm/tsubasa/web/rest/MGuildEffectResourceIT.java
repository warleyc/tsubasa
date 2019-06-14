package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuildEffect;
import io.shm.tsubasa.repository.MGuildEffectRepository;
import io.shm.tsubasa.service.MGuildEffectService;
import io.shm.tsubasa.service.dto.MGuildEffectDTO;
import io.shm.tsubasa.service.mapper.MGuildEffectMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuildEffectCriteria;
import io.shm.tsubasa.service.MGuildEffectQueryService;

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
 * Integration tests for the {@Link MGuildEffectResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuildEffectResourceIT {

    private static final Integer DEFAULT_EFFECT_TYPE = 1;
    private static final Integer UPDATED_EFFECT_TYPE = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_EFFECT_ID = 1;
    private static final Integer UPDATED_EFFECT_ID = 2;

    private static final String DEFAULT_THUMBNAIL_PATH = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_PATH = "BBBBBBBBBB";

    @Autowired
    private MGuildEffectRepository mGuildEffectRepository;

    @Autowired
    private MGuildEffectMapper mGuildEffectMapper;

    @Autowired
    private MGuildEffectService mGuildEffectService;

    @Autowired
    private MGuildEffectQueryService mGuildEffectQueryService;

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

    private MockMvc restMGuildEffectMockMvc;

    private MGuildEffect mGuildEffect;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuildEffectResource mGuildEffectResource = new MGuildEffectResource(mGuildEffectService, mGuildEffectQueryService);
        this.restMGuildEffectMockMvc = MockMvcBuilders.standaloneSetup(mGuildEffectResource)
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
    public static MGuildEffect createEntity(EntityManager em) {
        MGuildEffect mGuildEffect = new MGuildEffect()
            .effectType(DEFAULT_EFFECT_TYPE)
            .name(DEFAULT_NAME)
            .effectId(DEFAULT_EFFECT_ID)
            .thumbnailPath(DEFAULT_THUMBNAIL_PATH);
        return mGuildEffect;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuildEffect createUpdatedEntity(EntityManager em) {
        MGuildEffect mGuildEffect = new MGuildEffect()
            .effectType(UPDATED_EFFECT_TYPE)
            .name(UPDATED_NAME)
            .effectId(UPDATED_EFFECT_ID)
            .thumbnailPath(UPDATED_THUMBNAIL_PATH);
        return mGuildEffect;
    }

    @BeforeEach
    public void initTest() {
        mGuildEffect = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuildEffect() throws Exception {
        int databaseSizeBeforeCreate = mGuildEffectRepository.findAll().size();

        // Create the MGuildEffect
        MGuildEffectDTO mGuildEffectDTO = mGuildEffectMapper.toDto(mGuildEffect);
        restMGuildEffectMockMvc.perform(post("/api/m-guild-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuildEffect in the database
        List<MGuildEffect> mGuildEffectList = mGuildEffectRepository.findAll();
        assertThat(mGuildEffectList).hasSize(databaseSizeBeforeCreate + 1);
        MGuildEffect testMGuildEffect = mGuildEffectList.get(mGuildEffectList.size() - 1);
        assertThat(testMGuildEffect.getEffectType()).isEqualTo(DEFAULT_EFFECT_TYPE);
        assertThat(testMGuildEffect.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMGuildEffect.getEffectId()).isEqualTo(DEFAULT_EFFECT_ID);
        assertThat(testMGuildEffect.getThumbnailPath()).isEqualTo(DEFAULT_THUMBNAIL_PATH);
    }

    @Test
    @Transactional
    public void createMGuildEffectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuildEffectRepository.findAll().size();

        // Create the MGuildEffect with an existing ID
        mGuildEffect.setId(1L);
        MGuildEffectDTO mGuildEffectDTO = mGuildEffectMapper.toDto(mGuildEffect);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuildEffectMockMvc.perform(post("/api/m-guild-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildEffect in the database
        List<MGuildEffect> mGuildEffectList = mGuildEffectRepository.findAll();
        assertThat(mGuildEffectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildEffectRepository.findAll().size();
        // set the field null
        mGuildEffect.setEffectType(null);

        // Create the MGuildEffect, which fails.
        MGuildEffectDTO mGuildEffectDTO = mGuildEffectMapper.toDto(mGuildEffect);

        restMGuildEffectMockMvc.perform(post("/api/m-guild-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildEffect> mGuildEffectList = mGuildEffectRepository.findAll();
        assertThat(mGuildEffectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuildEffects() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList
        restMGuildEffectMockMvc.perform(get("/api/m-guild-effects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildEffect.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectType").value(hasItem(DEFAULT_EFFECT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].effectId").value(hasItem(DEFAULT_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].thumbnailPath").value(hasItem(DEFAULT_THUMBNAIL_PATH.toString())));
    }
    
    @Test
    @Transactional
    public void getMGuildEffect() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get the mGuildEffect
        restMGuildEffectMockMvc.perform(get("/api/m-guild-effects/{id}", mGuildEffect.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuildEffect.getId().intValue()))
            .andExpect(jsonPath("$.effectType").value(DEFAULT_EFFECT_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.effectId").value(DEFAULT_EFFECT_ID))
            .andExpect(jsonPath("$.thumbnailPath").value(DEFAULT_THUMBNAIL_PATH.toString()));
    }

    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectType equals to DEFAULT_EFFECT_TYPE
        defaultMGuildEffectShouldBeFound("effectType.equals=" + DEFAULT_EFFECT_TYPE);

        // Get all the mGuildEffectList where effectType equals to UPDATED_EFFECT_TYPE
        defaultMGuildEffectShouldNotBeFound("effectType.equals=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectType in DEFAULT_EFFECT_TYPE or UPDATED_EFFECT_TYPE
        defaultMGuildEffectShouldBeFound("effectType.in=" + DEFAULT_EFFECT_TYPE + "," + UPDATED_EFFECT_TYPE);

        // Get all the mGuildEffectList where effectType equals to UPDATED_EFFECT_TYPE
        defaultMGuildEffectShouldNotBeFound("effectType.in=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectType is not null
        defaultMGuildEffectShouldBeFound("effectType.specified=true");

        // Get all the mGuildEffectList where effectType is null
        defaultMGuildEffectShouldNotBeFound("effectType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectType greater than or equals to DEFAULT_EFFECT_TYPE
        defaultMGuildEffectShouldBeFound("effectType.greaterOrEqualThan=" + DEFAULT_EFFECT_TYPE);

        // Get all the mGuildEffectList where effectType greater than or equals to UPDATED_EFFECT_TYPE
        defaultMGuildEffectShouldNotBeFound("effectType.greaterOrEqualThan=" + UPDATED_EFFECT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectType less than or equals to DEFAULT_EFFECT_TYPE
        defaultMGuildEffectShouldNotBeFound("effectType.lessThan=" + DEFAULT_EFFECT_TYPE);

        // Get all the mGuildEffectList where effectType less than or equals to UPDATED_EFFECT_TYPE
        defaultMGuildEffectShouldBeFound("effectType.lessThan=" + UPDATED_EFFECT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectId equals to DEFAULT_EFFECT_ID
        defaultMGuildEffectShouldBeFound("effectId.equals=" + DEFAULT_EFFECT_ID);

        // Get all the mGuildEffectList where effectId equals to UPDATED_EFFECT_ID
        defaultMGuildEffectShouldNotBeFound("effectId.equals=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectId in DEFAULT_EFFECT_ID or UPDATED_EFFECT_ID
        defaultMGuildEffectShouldBeFound("effectId.in=" + DEFAULT_EFFECT_ID + "," + UPDATED_EFFECT_ID);

        // Get all the mGuildEffectList where effectId equals to UPDATED_EFFECT_ID
        defaultMGuildEffectShouldNotBeFound("effectId.in=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectId is not null
        defaultMGuildEffectShouldBeFound("effectId.specified=true");

        // Get all the mGuildEffectList where effectId is null
        defaultMGuildEffectShouldNotBeFound("effectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectId greater than or equals to DEFAULT_EFFECT_ID
        defaultMGuildEffectShouldBeFound("effectId.greaterOrEqualThan=" + DEFAULT_EFFECT_ID);

        // Get all the mGuildEffectList where effectId greater than or equals to UPDATED_EFFECT_ID
        defaultMGuildEffectShouldNotBeFound("effectId.greaterOrEqualThan=" + UPDATED_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildEffectsByEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        // Get all the mGuildEffectList where effectId less than or equals to DEFAULT_EFFECT_ID
        defaultMGuildEffectShouldNotBeFound("effectId.lessThan=" + DEFAULT_EFFECT_ID);

        // Get all the mGuildEffectList where effectId less than or equals to UPDATED_EFFECT_ID
        defaultMGuildEffectShouldBeFound("effectId.lessThan=" + UPDATED_EFFECT_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuildEffectShouldBeFound(String filter) throws Exception {
        restMGuildEffectMockMvc.perform(get("/api/m-guild-effects?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildEffect.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectType").value(hasItem(DEFAULT_EFFECT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].effectId").value(hasItem(DEFAULT_EFFECT_ID)))
            .andExpect(jsonPath("$.[*].thumbnailPath").value(hasItem(DEFAULT_THUMBNAIL_PATH.toString())));

        // Check, that the count call also returns 1
        restMGuildEffectMockMvc.perform(get("/api/m-guild-effects/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuildEffectShouldNotBeFound(String filter) throws Exception {
        restMGuildEffectMockMvc.perform(get("/api/m-guild-effects?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuildEffectMockMvc.perform(get("/api/m-guild-effects/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuildEffect() throws Exception {
        // Get the mGuildEffect
        restMGuildEffectMockMvc.perform(get("/api/m-guild-effects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuildEffect() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        int databaseSizeBeforeUpdate = mGuildEffectRepository.findAll().size();

        // Update the mGuildEffect
        MGuildEffect updatedMGuildEffect = mGuildEffectRepository.findById(mGuildEffect.getId()).get();
        // Disconnect from session so that the updates on updatedMGuildEffect are not directly saved in db
        em.detach(updatedMGuildEffect);
        updatedMGuildEffect
            .effectType(UPDATED_EFFECT_TYPE)
            .name(UPDATED_NAME)
            .effectId(UPDATED_EFFECT_ID)
            .thumbnailPath(UPDATED_THUMBNAIL_PATH);
        MGuildEffectDTO mGuildEffectDTO = mGuildEffectMapper.toDto(updatedMGuildEffect);

        restMGuildEffectMockMvc.perform(put("/api/m-guild-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectDTO)))
            .andExpect(status().isOk());

        // Validate the MGuildEffect in the database
        List<MGuildEffect> mGuildEffectList = mGuildEffectRepository.findAll();
        assertThat(mGuildEffectList).hasSize(databaseSizeBeforeUpdate);
        MGuildEffect testMGuildEffect = mGuildEffectList.get(mGuildEffectList.size() - 1);
        assertThat(testMGuildEffect.getEffectType()).isEqualTo(UPDATED_EFFECT_TYPE);
        assertThat(testMGuildEffect.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMGuildEffect.getEffectId()).isEqualTo(UPDATED_EFFECT_ID);
        assertThat(testMGuildEffect.getThumbnailPath()).isEqualTo(UPDATED_THUMBNAIL_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuildEffect() throws Exception {
        int databaseSizeBeforeUpdate = mGuildEffectRepository.findAll().size();

        // Create the MGuildEffect
        MGuildEffectDTO mGuildEffectDTO = mGuildEffectMapper.toDto(mGuildEffect);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuildEffectMockMvc.perform(put("/api/m-guild-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildEffectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildEffect in the database
        List<MGuildEffect> mGuildEffectList = mGuildEffectRepository.findAll();
        assertThat(mGuildEffectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuildEffect() throws Exception {
        // Initialize the database
        mGuildEffectRepository.saveAndFlush(mGuildEffect);

        int databaseSizeBeforeDelete = mGuildEffectRepository.findAll().size();

        // Delete the mGuildEffect
        restMGuildEffectMockMvc.perform(delete("/api/m-guild-effects/{id}", mGuildEffect.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuildEffect> mGuildEffectList = mGuildEffectRepository.findAll();
        assertThat(mGuildEffectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildEffect.class);
        MGuildEffect mGuildEffect1 = new MGuildEffect();
        mGuildEffect1.setId(1L);
        MGuildEffect mGuildEffect2 = new MGuildEffect();
        mGuildEffect2.setId(mGuildEffect1.getId());
        assertThat(mGuildEffect1).isEqualTo(mGuildEffect2);
        mGuildEffect2.setId(2L);
        assertThat(mGuildEffect1).isNotEqualTo(mGuildEffect2);
        mGuildEffect1.setId(null);
        assertThat(mGuildEffect1).isNotEqualTo(mGuildEffect2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildEffectDTO.class);
        MGuildEffectDTO mGuildEffectDTO1 = new MGuildEffectDTO();
        mGuildEffectDTO1.setId(1L);
        MGuildEffectDTO mGuildEffectDTO2 = new MGuildEffectDTO();
        assertThat(mGuildEffectDTO1).isNotEqualTo(mGuildEffectDTO2);
        mGuildEffectDTO2.setId(mGuildEffectDTO1.getId());
        assertThat(mGuildEffectDTO1).isEqualTo(mGuildEffectDTO2);
        mGuildEffectDTO2.setId(2L);
        assertThat(mGuildEffectDTO1).isNotEqualTo(mGuildEffectDTO2);
        mGuildEffectDTO1.setId(null);
        assertThat(mGuildEffectDTO1).isNotEqualTo(mGuildEffectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuildEffectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuildEffectMapper.fromId(null)).isNull();
    }
}
