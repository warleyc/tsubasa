package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MConstant;
import io.shm.tsubasa.repository.MConstantRepository;
import io.shm.tsubasa.service.MConstantService;
import io.shm.tsubasa.service.dto.MConstantDTO;
import io.shm.tsubasa.service.mapper.MConstantMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MConstantCriteria;
import io.shm.tsubasa.service.MConstantQueryService;

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
 * Integration tests for the {@Link MConstantResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MConstantResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final Integer DEFAULT_VALUE = 1;
    private static final Integer UPDATED_VALUE = 2;

    @Autowired
    private MConstantRepository mConstantRepository;

    @Autowired
    private MConstantMapper mConstantMapper;

    @Autowired
    private MConstantService mConstantService;

    @Autowired
    private MConstantQueryService mConstantQueryService;

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

    private MockMvc restMConstantMockMvc;

    private MConstant mConstant;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MConstantResource mConstantResource = new MConstantResource(mConstantService, mConstantQueryService);
        this.restMConstantMockMvc = MockMvcBuilders.standaloneSetup(mConstantResource)
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
    public static MConstant createEntity(EntityManager em) {
        MConstant mConstant = new MConstant()
            .key(DEFAULT_KEY)
            .value(DEFAULT_VALUE);
        return mConstant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MConstant createUpdatedEntity(EntityManager em) {
        MConstant mConstant = new MConstant()
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE);
        return mConstant;
    }

    @BeforeEach
    public void initTest() {
        mConstant = createEntity(em);
    }

    @Test
    @Transactional
    public void createMConstant() throws Exception {
        int databaseSizeBeforeCreate = mConstantRepository.findAll().size();

        // Create the MConstant
        MConstantDTO mConstantDTO = mConstantMapper.toDto(mConstant);
        restMConstantMockMvc.perform(post("/api/m-constants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mConstantDTO)))
            .andExpect(status().isCreated());

        // Validate the MConstant in the database
        List<MConstant> mConstantList = mConstantRepository.findAll();
        assertThat(mConstantList).hasSize(databaseSizeBeforeCreate + 1);
        MConstant testMConstant = mConstantList.get(mConstantList.size() - 1);
        assertThat(testMConstant.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMConstant.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createMConstantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mConstantRepository.findAll().size();

        // Create the MConstant with an existing ID
        mConstant.setId(1L);
        MConstantDTO mConstantDTO = mConstantMapper.toDto(mConstant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMConstantMockMvc.perform(post("/api/m-constants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mConstantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MConstant in the database
        List<MConstant> mConstantList = mConstantRepository.findAll();
        assertThat(mConstantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mConstantRepository.findAll().size();
        // set the field null
        mConstant.setValue(null);

        // Create the MConstant, which fails.
        MConstantDTO mConstantDTO = mConstantMapper.toDto(mConstant);

        restMConstantMockMvc.perform(post("/api/m-constants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mConstantDTO)))
            .andExpect(status().isBadRequest());

        List<MConstant> mConstantList = mConstantRepository.findAll();
        assertThat(mConstantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMConstants() throws Exception {
        // Initialize the database
        mConstantRepository.saveAndFlush(mConstant);

        // Get all the mConstantList
        restMConstantMockMvc.perform(get("/api/m-constants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mConstant.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getMConstant() throws Exception {
        // Initialize the database
        mConstantRepository.saveAndFlush(mConstant);

        // Get the mConstant
        restMConstantMockMvc.perform(get("/api/m-constants/{id}", mConstant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mConstant.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    public void getAllMConstantsByValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mConstantRepository.saveAndFlush(mConstant);

        // Get all the mConstantList where value equals to DEFAULT_VALUE
        defaultMConstantShouldBeFound("value.equals=" + DEFAULT_VALUE);

        // Get all the mConstantList where value equals to UPDATED_VALUE
        defaultMConstantShouldNotBeFound("value.equals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMConstantsByValueIsInShouldWork() throws Exception {
        // Initialize the database
        mConstantRepository.saveAndFlush(mConstant);

        // Get all the mConstantList where value in DEFAULT_VALUE or UPDATED_VALUE
        defaultMConstantShouldBeFound("value.in=" + DEFAULT_VALUE + "," + UPDATED_VALUE);

        // Get all the mConstantList where value equals to UPDATED_VALUE
        defaultMConstantShouldNotBeFound("value.in=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMConstantsByValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mConstantRepository.saveAndFlush(mConstant);

        // Get all the mConstantList where value is not null
        defaultMConstantShouldBeFound("value.specified=true");

        // Get all the mConstantList where value is null
        defaultMConstantShouldNotBeFound("value.specified=false");
    }

    @Test
    @Transactional
    public void getAllMConstantsByValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mConstantRepository.saveAndFlush(mConstant);

        // Get all the mConstantList where value greater than or equals to DEFAULT_VALUE
        defaultMConstantShouldBeFound("value.greaterOrEqualThan=" + DEFAULT_VALUE);

        // Get all the mConstantList where value greater than or equals to UPDATED_VALUE
        defaultMConstantShouldNotBeFound("value.greaterOrEqualThan=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMConstantsByValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mConstantRepository.saveAndFlush(mConstant);

        // Get all the mConstantList where value less than or equals to DEFAULT_VALUE
        defaultMConstantShouldNotBeFound("value.lessThan=" + DEFAULT_VALUE);

        // Get all the mConstantList where value less than or equals to UPDATED_VALUE
        defaultMConstantShouldBeFound("value.lessThan=" + UPDATED_VALUE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMConstantShouldBeFound(String filter) throws Exception {
        restMConstantMockMvc.perform(get("/api/m-constants?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mConstant.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));

        // Check, that the count call also returns 1
        restMConstantMockMvc.perform(get("/api/m-constants/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMConstantShouldNotBeFound(String filter) throws Exception {
        restMConstantMockMvc.perform(get("/api/m-constants?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMConstantMockMvc.perform(get("/api/m-constants/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMConstant() throws Exception {
        // Get the mConstant
        restMConstantMockMvc.perform(get("/api/m-constants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMConstant() throws Exception {
        // Initialize the database
        mConstantRepository.saveAndFlush(mConstant);

        int databaseSizeBeforeUpdate = mConstantRepository.findAll().size();

        // Update the mConstant
        MConstant updatedMConstant = mConstantRepository.findById(mConstant.getId()).get();
        // Disconnect from session so that the updates on updatedMConstant are not directly saved in db
        em.detach(updatedMConstant);
        updatedMConstant
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE);
        MConstantDTO mConstantDTO = mConstantMapper.toDto(updatedMConstant);

        restMConstantMockMvc.perform(put("/api/m-constants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mConstantDTO)))
            .andExpect(status().isOk());

        // Validate the MConstant in the database
        List<MConstant> mConstantList = mConstantRepository.findAll();
        assertThat(mConstantList).hasSize(databaseSizeBeforeUpdate);
        MConstant testMConstant = mConstantList.get(mConstantList.size() - 1);
        assertThat(testMConstant.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMConstant.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingMConstant() throws Exception {
        int databaseSizeBeforeUpdate = mConstantRepository.findAll().size();

        // Create the MConstant
        MConstantDTO mConstantDTO = mConstantMapper.toDto(mConstant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMConstantMockMvc.perform(put("/api/m-constants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mConstantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MConstant in the database
        List<MConstant> mConstantList = mConstantRepository.findAll();
        assertThat(mConstantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMConstant() throws Exception {
        // Initialize the database
        mConstantRepository.saveAndFlush(mConstant);

        int databaseSizeBeforeDelete = mConstantRepository.findAll().size();

        // Delete the mConstant
        restMConstantMockMvc.perform(delete("/api/m-constants/{id}", mConstant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MConstant> mConstantList = mConstantRepository.findAll();
        assertThat(mConstantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MConstant.class);
        MConstant mConstant1 = new MConstant();
        mConstant1.setId(1L);
        MConstant mConstant2 = new MConstant();
        mConstant2.setId(mConstant1.getId());
        assertThat(mConstant1).isEqualTo(mConstant2);
        mConstant2.setId(2L);
        assertThat(mConstant1).isNotEqualTo(mConstant2);
        mConstant1.setId(null);
        assertThat(mConstant1).isNotEqualTo(mConstant2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MConstantDTO.class);
        MConstantDTO mConstantDTO1 = new MConstantDTO();
        mConstantDTO1.setId(1L);
        MConstantDTO mConstantDTO2 = new MConstantDTO();
        assertThat(mConstantDTO1).isNotEqualTo(mConstantDTO2);
        mConstantDTO2.setId(mConstantDTO1.getId());
        assertThat(mConstantDTO1).isEqualTo(mConstantDTO2);
        mConstantDTO2.setId(2L);
        assertThat(mConstantDTO1).isNotEqualTo(mConstantDTO2);
        mConstantDTO1.setId(null);
        assertThat(mConstantDTO1).isNotEqualTo(mConstantDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mConstantMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mConstantMapper.fromId(null)).isNull();
    }
}
