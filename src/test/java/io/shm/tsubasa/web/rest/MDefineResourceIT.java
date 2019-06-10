package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDefine;
import io.shm.tsubasa.repository.MDefineRepository;
import io.shm.tsubasa.service.MDefineService;
import io.shm.tsubasa.service.dto.MDefineDTO;
import io.shm.tsubasa.service.mapper.MDefineMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDefineCriteria;
import io.shm.tsubasa.service.MDefineQueryService;

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
 * Integration tests for the {@Link MDefineResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDefineResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final Integer DEFAULT_VALUE = 1;
    private static final Integer UPDATED_VALUE = 2;

    @Autowired
    private MDefineRepository mDefineRepository;

    @Autowired
    private MDefineMapper mDefineMapper;

    @Autowired
    private MDefineService mDefineService;

    @Autowired
    private MDefineQueryService mDefineQueryService;

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

    private MockMvc restMDefineMockMvc;

    private MDefine mDefine;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDefineResource mDefineResource = new MDefineResource(mDefineService, mDefineQueryService);
        this.restMDefineMockMvc = MockMvcBuilders.standaloneSetup(mDefineResource)
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
    public static MDefine createEntity(EntityManager em) {
        MDefine mDefine = new MDefine()
            .key(DEFAULT_KEY)
            .value(DEFAULT_VALUE);
        return mDefine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDefine createUpdatedEntity(EntityManager em) {
        MDefine mDefine = new MDefine()
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE);
        return mDefine;
    }

    @BeforeEach
    public void initTest() {
        mDefine = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDefine() throws Exception {
        int databaseSizeBeforeCreate = mDefineRepository.findAll().size();

        // Create the MDefine
        MDefineDTO mDefineDTO = mDefineMapper.toDto(mDefine);
        restMDefineMockMvc.perform(post("/api/m-defines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDefineDTO)))
            .andExpect(status().isCreated());

        // Validate the MDefine in the database
        List<MDefine> mDefineList = mDefineRepository.findAll();
        assertThat(mDefineList).hasSize(databaseSizeBeforeCreate + 1);
        MDefine testMDefine = mDefineList.get(mDefineList.size() - 1);
        assertThat(testMDefine.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDefine.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createMDefineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDefineRepository.findAll().size();

        // Create the MDefine with an existing ID
        mDefine.setId(1L);
        MDefineDTO mDefineDTO = mDefineMapper.toDto(mDefine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDefineMockMvc.perform(post("/api/m-defines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDefineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDefine in the database
        List<MDefine> mDefineList = mDefineRepository.findAll();
        assertThat(mDefineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDefineRepository.findAll().size();
        // set the field null
        mDefine.setValue(null);

        // Create the MDefine, which fails.
        MDefineDTO mDefineDTO = mDefineMapper.toDto(mDefine);

        restMDefineMockMvc.perform(post("/api/m-defines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDefineDTO)))
            .andExpect(status().isBadRequest());

        List<MDefine> mDefineList = mDefineRepository.findAll();
        assertThat(mDefineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMDefines() throws Exception {
        // Initialize the database
        mDefineRepository.saveAndFlush(mDefine);

        // Get all the mDefineList
        restMDefineMockMvc.perform(get("/api/m-defines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDefine.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getMDefine() throws Exception {
        // Initialize the database
        mDefineRepository.saveAndFlush(mDefine);

        // Get the mDefine
        restMDefineMockMvc.perform(get("/api/m-defines/{id}", mDefine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDefine.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    public void getAllMDefinesByValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mDefineRepository.saveAndFlush(mDefine);

        // Get all the mDefineList where value equals to DEFAULT_VALUE
        defaultMDefineShouldBeFound("value.equals=" + DEFAULT_VALUE);

        // Get all the mDefineList where value equals to UPDATED_VALUE
        defaultMDefineShouldNotBeFound("value.equals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMDefinesByValueIsInShouldWork() throws Exception {
        // Initialize the database
        mDefineRepository.saveAndFlush(mDefine);

        // Get all the mDefineList where value in DEFAULT_VALUE or UPDATED_VALUE
        defaultMDefineShouldBeFound("value.in=" + DEFAULT_VALUE + "," + UPDATED_VALUE);

        // Get all the mDefineList where value equals to UPDATED_VALUE
        defaultMDefineShouldNotBeFound("value.in=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMDefinesByValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDefineRepository.saveAndFlush(mDefine);

        // Get all the mDefineList where value is not null
        defaultMDefineShouldBeFound("value.specified=true");

        // Get all the mDefineList where value is null
        defaultMDefineShouldNotBeFound("value.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDefinesByValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDefineRepository.saveAndFlush(mDefine);

        // Get all the mDefineList where value greater than or equals to DEFAULT_VALUE
        defaultMDefineShouldBeFound("value.greaterOrEqualThan=" + DEFAULT_VALUE);

        // Get all the mDefineList where value greater than or equals to UPDATED_VALUE
        defaultMDefineShouldNotBeFound("value.greaterOrEqualThan=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMDefinesByValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mDefineRepository.saveAndFlush(mDefine);

        // Get all the mDefineList where value less than or equals to DEFAULT_VALUE
        defaultMDefineShouldNotBeFound("value.lessThan=" + DEFAULT_VALUE);

        // Get all the mDefineList where value less than or equals to UPDATED_VALUE
        defaultMDefineShouldBeFound("value.lessThan=" + UPDATED_VALUE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDefineShouldBeFound(String filter) throws Exception {
        restMDefineMockMvc.perform(get("/api/m-defines?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDefine.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));

        // Check, that the count call also returns 1
        restMDefineMockMvc.perform(get("/api/m-defines/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDefineShouldNotBeFound(String filter) throws Exception {
        restMDefineMockMvc.perform(get("/api/m-defines?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDefineMockMvc.perform(get("/api/m-defines/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDefine() throws Exception {
        // Get the mDefine
        restMDefineMockMvc.perform(get("/api/m-defines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDefine() throws Exception {
        // Initialize the database
        mDefineRepository.saveAndFlush(mDefine);

        int databaseSizeBeforeUpdate = mDefineRepository.findAll().size();

        // Update the mDefine
        MDefine updatedMDefine = mDefineRepository.findById(mDefine.getId()).get();
        // Disconnect from session so that the updates on updatedMDefine are not directly saved in db
        em.detach(updatedMDefine);
        updatedMDefine
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE);
        MDefineDTO mDefineDTO = mDefineMapper.toDto(updatedMDefine);

        restMDefineMockMvc.perform(put("/api/m-defines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDefineDTO)))
            .andExpect(status().isOk());

        // Validate the MDefine in the database
        List<MDefine> mDefineList = mDefineRepository.findAll();
        assertThat(mDefineList).hasSize(databaseSizeBeforeUpdate);
        MDefine testMDefine = mDefineList.get(mDefineList.size() - 1);
        assertThat(testMDefine.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDefine.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDefine() throws Exception {
        int databaseSizeBeforeUpdate = mDefineRepository.findAll().size();

        // Create the MDefine
        MDefineDTO mDefineDTO = mDefineMapper.toDto(mDefine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDefineMockMvc.perform(put("/api/m-defines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDefineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDefine in the database
        List<MDefine> mDefineList = mDefineRepository.findAll();
        assertThat(mDefineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDefine() throws Exception {
        // Initialize the database
        mDefineRepository.saveAndFlush(mDefine);

        int databaseSizeBeforeDelete = mDefineRepository.findAll().size();

        // Delete the mDefine
        restMDefineMockMvc.perform(delete("/api/m-defines/{id}", mDefine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDefine> mDefineList = mDefineRepository.findAll();
        assertThat(mDefineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDefine.class);
        MDefine mDefine1 = new MDefine();
        mDefine1.setId(1L);
        MDefine mDefine2 = new MDefine();
        mDefine2.setId(mDefine1.getId());
        assertThat(mDefine1).isEqualTo(mDefine2);
        mDefine2.setId(2L);
        assertThat(mDefine1).isNotEqualTo(mDefine2);
        mDefine1.setId(null);
        assertThat(mDefine1).isNotEqualTo(mDefine2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDefineDTO.class);
        MDefineDTO mDefineDTO1 = new MDefineDTO();
        mDefineDTO1.setId(1L);
        MDefineDTO mDefineDTO2 = new MDefineDTO();
        assertThat(mDefineDTO1).isNotEqualTo(mDefineDTO2);
        mDefineDTO2.setId(mDefineDTO1.getId());
        assertThat(mDefineDTO1).isEqualTo(mDefineDTO2);
        mDefineDTO2.setId(2L);
        assertThat(mDefineDTO1).isNotEqualTo(mDefineDTO2);
        mDefineDTO1.setId(null);
        assertThat(mDefineDTO1).isNotEqualTo(mDefineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDefineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDefineMapper.fromId(null)).isNull();
    }
}
