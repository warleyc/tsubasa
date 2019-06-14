package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionTrajectoryPhoenix;
import io.shm.tsubasa.repository.MGachaRenditionTrajectoryPhoenixRepository;
import io.shm.tsubasa.service.MGachaRenditionTrajectoryPhoenixService;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryPhoenixDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTrajectoryPhoenixMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryPhoenixCriteria;
import io.shm.tsubasa.service.MGachaRenditionTrajectoryPhoenixQueryService;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MGachaRenditionTrajectoryPhoenixResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionTrajectoryPhoenixResourceIT {

    private static final Integer DEFAULT_IS_PHOENIX = 1;
    private static final Integer UPDATED_IS_PHOENIX = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    @Autowired
    private MGachaRenditionTrajectoryPhoenixRepository mGachaRenditionTrajectoryPhoenixRepository;

    @Autowired
    private MGachaRenditionTrajectoryPhoenixMapper mGachaRenditionTrajectoryPhoenixMapper;

    @Autowired
    private MGachaRenditionTrajectoryPhoenixService mGachaRenditionTrajectoryPhoenixService;

    @Autowired
    private MGachaRenditionTrajectoryPhoenixQueryService mGachaRenditionTrajectoryPhoenixQueryService;

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

    private MockMvc restMGachaRenditionTrajectoryPhoenixMockMvc;

    private MGachaRenditionTrajectoryPhoenix mGachaRenditionTrajectoryPhoenix;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionTrajectoryPhoenixResource mGachaRenditionTrajectoryPhoenixResource = new MGachaRenditionTrajectoryPhoenixResource(mGachaRenditionTrajectoryPhoenixService, mGachaRenditionTrajectoryPhoenixQueryService);
        this.restMGachaRenditionTrajectoryPhoenixMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionTrajectoryPhoenixResource)
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
    public static MGachaRenditionTrajectoryPhoenix createEntity(EntityManager em) {
        MGachaRenditionTrajectoryPhoenix mGachaRenditionTrajectoryPhoenix = new MGachaRenditionTrajectoryPhoenix()
            .isPhoenix(DEFAULT_IS_PHOENIX)
            .weight(DEFAULT_WEIGHT);
        return mGachaRenditionTrajectoryPhoenix;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionTrajectoryPhoenix createUpdatedEntity(EntityManager em) {
        MGachaRenditionTrajectoryPhoenix mGachaRenditionTrajectoryPhoenix = new MGachaRenditionTrajectoryPhoenix()
            .isPhoenix(UPDATED_IS_PHOENIX)
            .weight(UPDATED_WEIGHT);
        return mGachaRenditionTrajectoryPhoenix;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionTrajectoryPhoenix = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionTrajectoryPhoenix() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionTrajectoryPhoenixRepository.findAll().size();

        // Create the MGachaRenditionTrajectoryPhoenix
        MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO = mGachaRenditionTrajectoryPhoenixMapper.toDto(mGachaRenditionTrajectoryPhoenix);
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(post("/api/m-gacha-rendition-trajectory-phoenixes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryPhoenixDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionTrajectoryPhoenix in the database
        List<MGachaRenditionTrajectoryPhoenix> mGachaRenditionTrajectoryPhoenixList = mGachaRenditionTrajectoryPhoenixRepository.findAll();
        assertThat(mGachaRenditionTrajectoryPhoenixList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionTrajectoryPhoenix testMGachaRenditionTrajectoryPhoenix = mGachaRenditionTrajectoryPhoenixList.get(mGachaRenditionTrajectoryPhoenixList.size() - 1);
        assertThat(testMGachaRenditionTrajectoryPhoenix.getIsPhoenix()).isEqualTo(DEFAULT_IS_PHOENIX);
        assertThat(testMGachaRenditionTrajectoryPhoenix.getWeight()).isEqualTo(DEFAULT_WEIGHT);
    }

    @Test
    @Transactional
    public void createMGachaRenditionTrajectoryPhoenixWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionTrajectoryPhoenixRepository.findAll().size();

        // Create the MGachaRenditionTrajectoryPhoenix with an existing ID
        mGachaRenditionTrajectoryPhoenix.setId(1L);
        MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO = mGachaRenditionTrajectoryPhoenixMapper.toDto(mGachaRenditionTrajectoryPhoenix);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(post("/api/m-gacha-rendition-trajectory-phoenixes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryPhoenixDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionTrajectoryPhoenix in the database
        List<MGachaRenditionTrajectoryPhoenix> mGachaRenditionTrajectoryPhoenixList = mGachaRenditionTrajectoryPhoenixRepository.findAll();
        assertThat(mGachaRenditionTrajectoryPhoenixList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIsPhoenixIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTrajectoryPhoenixRepository.findAll().size();
        // set the field null
        mGachaRenditionTrajectoryPhoenix.setIsPhoenix(null);

        // Create the MGachaRenditionTrajectoryPhoenix, which fails.
        MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO = mGachaRenditionTrajectoryPhoenixMapper.toDto(mGachaRenditionTrajectoryPhoenix);

        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(post("/api/m-gacha-rendition-trajectory-phoenixes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryPhoenixDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTrajectoryPhoenix> mGachaRenditionTrajectoryPhoenixList = mGachaRenditionTrajectoryPhoenixRepository.findAll();
        assertThat(mGachaRenditionTrajectoryPhoenixList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTrajectoryPhoenixRepository.findAll().size();
        // set the field null
        mGachaRenditionTrajectoryPhoenix.setWeight(null);

        // Create the MGachaRenditionTrajectoryPhoenix, which fails.
        MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO = mGachaRenditionTrajectoryPhoenixMapper.toDto(mGachaRenditionTrajectoryPhoenix);

        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(post("/api/m-gacha-rendition-trajectory-phoenixes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryPhoenixDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTrajectoryPhoenix> mGachaRenditionTrajectoryPhoenixList = mGachaRenditionTrajectoryPhoenixRepository.findAll();
        assertThat(mGachaRenditionTrajectoryPhoenixList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixes() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(get("/api/m-gacha-rendition-trajectory-phoenixes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionTrajectoryPhoenix.getId().intValue())))
            .andExpect(jsonPath("$.[*].isPhoenix").value(hasItem(DEFAULT_IS_PHOENIX)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionTrajectoryPhoenix() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get the mGachaRenditionTrajectoryPhoenix
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(get("/api/m-gacha-rendition-trajectory-phoenixes/{id}", mGachaRenditionTrajectoryPhoenix.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionTrajectoryPhoenix.getId().intValue()))
            .andExpect(jsonPath("$.isPhoenix").value(DEFAULT_IS_PHOENIX))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByIsPhoenixIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix equals to DEFAULT_IS_PHOENIX
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("isPhoenix.equals=" + DEFAULT_IS_PHOENIX);

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix equals to UPDATED_IS_PHOENIX
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("isPhoenix.equals=" + UPDATED_IS_PHOENIX);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByIsPhoenixIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix in DEFAULT_IS_PHOENIX or UPDATED_IS_PHOENIX
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("isPhoenix.in=" + DEFAULT_IS_PHOENIX + "," + UPDATED_IS_PHOENIX);

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix equals to UPDATED_IS_PHOENIX
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("isPhoenix.in=" + UPDATED_IS_PHOENIX);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByIsPhoenixIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix is not null
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("isPhoenix.specified=true");

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix is null
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("isPhoenix.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByIsPhoenixIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix greater than or equals to DEFAULT_IS_PHOENIX
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("isPhoenix.greaterOrEqualThan=" + DEFAULT_IS_PHOENIX);

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix greater than or equals to UPDATED_IS_PHOENIX
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("isPhoenix.greaterOrEqualThan=" + UPDATED_IS_PHOENIX);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByIsPhoenixIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix less than or equals to DEFAULT_IS_PHOENIX
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("isPhoenix.lessThan=" + DEFAULT_IS_PHOENIX);

        // Get all the mGachaRenditionTrajectoryPhoenixList where isPhoenix less than or equals to UPDATED_IS_PHOENIX
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("isPhoenix.lessThan=" + UPDATED_IS_PHOENIX);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight is not null
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight is null
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoryPhoenixesByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTrajectoryPhoenixList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryPhoenixShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionTrajectoryPhoenixShouldBeFound(String filter) throws Exception {
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(get("/api/m-gacha-rendition-trajectory-phoenixes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionTrajectoryPhoenix.getId().intValue())))
            .andExpect(jsonPath("$.[*].isPhoenix").value(hasItem(DEFAULT_IS_PHOENIX)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)));

        // Check, that the count call also returns 1
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(get("/api/m-gacha-rendition-trajectory-phoenixes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionTrajectoryPhoenixShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(get("/api/m-gacha-rendition-trajectory-phoenixes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(get("/api/m-gacha-rendition-trajectory-phoenixes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionTrajectoryPhoenix() throws Exception {
        // Get the mGachaRenditionTrajectoryPhoenix
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(get("/api/m-gacha-rendition-trajectory-phoenixes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionTrajectoryPhoenix() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        int databaseSizeBeforeUpdate = mGachaRenditionTrajectoryPhoenixRepository.findAll().size();

        // Update the mGachaRenditionTrajectoryPhoenix
        MGachaRenditionTrajectoryPhoenix updatedMGachaRenditionTrajectoryPhoenix = mGachaRenditionTrajectoryPhoenixRepository.findById(mGachaRenditionTrajectoryPhoenix.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionTrajectoryPhoenix are not directly saved in db
        em.detach(updatedMGachaRenditionTrajectoryPhoenix);
        updatedMGachaRenditionTrajectoryPhoenix
            .isPhoenix(UPDATED_IS_PHOENIX)
            .weight(UPDATED_WEIGHT);
        MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO = mGachaRenditionTrajectoryPhoenixMapper.toDto(updatedMGachaRenditionTrajectoryPhoenix);

        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(put("/api/m-gacha-rendition-trajectory-phoenixes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryPhoenixDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionTrajectoryPhoenix in the database
        List<MGachaRenditionTrajectoryPhoenix> mGachaRenditionTrajectoryPhoenixList = mGachaRenditionTrajectoryPhoenixRepository.findAll();
        assertThat(mGachaRenditionTrajectoryPhoenixList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionTrajectoryPhoenix testMGachaRenditionTrajectoryPhoenix = mGachaRenditionTrajectoryPhoenixList.get(mGachaRenditionTrajectoryPhoenixList.size() - 1);
        assertThat(testMGachaRenditionTrajectoryPhoenix.getIsPhoenix()).isEqualTo(UPDATED_IS_PHOENIX);
        assertThat(testMGachaRenditionTrajectoryPhoenix.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionTrajectoryPhoenix() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionTrajectoryPhoenixRepository.findAll().size();

        // Create the MGachaRenditionTrajectoryPhoenix
        MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO = mGachaRenditionTrajectoryPhoenixMapper.toDto(mGachaRenditionTrajectoryPhoenix);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(put("/api/m-gacha-rendition-trajectory-phoenixes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryPhoenixDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionTrajectoryPhoenix in the database
        List<MGachaRenditionTrajectoryPhoenix> mGachaRenditionTrajectoryPhoenixList = mGachaRenditionTrajectoryPhoenixRepository.findAll();
        assertThat(mGachaRenditionTrajectoryPhoenixList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionTrajectoryPhoenix() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryPhoenixRepository.saveAndFlush(mGachaRenditionTrajectoryPhoenix);

        int databaseSizeBeforeDelete = mGachaRenditionTrajectoryPhoenixRepository.findAll().size();

        // Delete the mGachaRenditionTrajectoryPhoenix
        restMGachaRenditionTrajectoryPhoenixMockMvc.perform(delete("/api/m-gacha-rendition-trajectory-phoenixes/{id}", mGachaRenditionTrajectoryPhoenix.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionTrajectoryPhoenix> mGachaRenditionTrajectoryPhoenixList = mGachaRenditionTrajectoryPhoenixRepository.findAll();
        assertThat(mGachaRenditionTrajectoryPhoenixList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionTrajectoryPhoenix.class);
        MGachaRenditionTrajectoryPhoenix mGachaRenditionTrajectoryPhoenix1 = new MGachaRenditionTrajectoryPhoenix();
        mGachaRenditionTrajectoryPhoenix1.setId(1L);
        MGachaRenditionTrajectoryPhoenix mGachaRenditionTrajectoryPhoenix2 = new MGachaRenditionTrajectoryPhoenix();
        mGachaRenditionTrajectoryPhoenix2.setId(mGachaRenditionTrajectoryPhoenix1.getId());
        assertThat(mGachaRenditionTrajectoryPhoenix1).isEqualTo(mGachaRenditionTrajectoryPhoenix2);
        mGachaRenditionTrajectoryPhoenix2.setId(2L);
        assertThat(mGachaRenditionTrajectoryPhoenix1).isNotEqualTo(mGachaRenditionTrajectoryPhoenix2);
        mGachaRenditionTrajectoryPhoenix1.setId(null);
        assertThat(mGachaRenditionTrajectoryPhoenix1).isNotEqualTo(mGachaRenditionTrajectoryPhoenix2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionTrajectoryPhoenixDTO.class);
        MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO1 = new MGachaRenditionTrajectoryPhoenixDTO();
        mGachaRenditionTrajectoryPhoenixDTO1.setId(1L);
        MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO2 = new MGachaRenditionTrajectoryPhoenixDTO();
        assertThat(mGachaRenditionTrajectoryPhoenixDTO1).isNotEqualTo(mGachaRenditionTrajectoryPhoenixDTO2);
        mGachaRenditionTrajectoryPhoenixDTO2.setId(mGachaRenditionTrajectoryPhoenixDTO1.getId());
        assertThat(mGachaRenditionTrajectoryPhoenixDTO1).isEqualTo(mGachaRenditionTrajectoryPhoenixDTO2);
        mGachaRenditionTrajectoryPhoenixDTO2.setId(2L);
        assertThat(mGachaRenditionTrajectoryPhoenixDTO1).isNotEqualTo(mGachaRenditionTrajectoryPhoenixDTO2);
        mGachaRenditionTrajectoryPhoenixDTO1.setId(null);
        assertThat(mGachaRenditionTrajectoryPhoenixDTO1).isNotEqualTo(mGachaRenditionTrajectoryPhoenixDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionTrajectoryPhoenixMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionTrajectoryPhoenixMapper.fromId(null)).isNull();
    }
}
