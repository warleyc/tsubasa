package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDbMapping;
import io.shm.tsubasa.repository.MDbMappingRepository;
import io.shm.tsubasa.service.MDbMappingService;
import io.shm.tsubasa.service.dto.MDbMappingDTO;
import io.shm.tsubasa.service.mapper.MDbMappingMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDbMappingCriteria;
import io.shm.tsubasa.service.MDbMappingQueryService;

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
 * Integration tests for the {@Link MDbMappingResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDbMappingResourceIT {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    @Autowired
    private MDbMappingRepository mDbMappingRepository;

    @Autowired
    private MDbMappingMapper mDbMappingMapper;

    @Autowired
    private MDbMappingService mDbMappingService;

    @Autowired
    private MDbMappingQueryService mDbMappingQueryService;

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

    private MockMvc restMDbMappingMockMvc;

    private MDbMapping mDbMapping;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDbMappingResource mDbMappingResource = new MDbMappingResource(mDbMappingService, mDbMappingQueryService);
        this.restMDbMappingMockMvc = MockMvcBuilders.standaloneSetup(mDbMappingResource)
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
    public static MDbMapping createEntity(EntityManager em) {
        MDbMapping mDbMapping = new MDbMapping()
            .fileName(DEFAULT_FILE_NAME)
            .dbName(DEFAULT_DB_NAME)
            .path(DEFAULT_PATH);
        return mDbMapping;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDbMapping createUpdatedEntity(EntityManager em) {
        MDbMapping mDbMapping = new MDbMapping()
            .fileName(UPDATED_FILE_NAME)
            .dbName(UPDATED_DB_NAME)
            .path(UPDATED_PATH);
        return mDbMapping;
    }

    @BeforeEach
    public void initTest() {
        mDbMapping = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDbMapping() throws Exception {
        int databaseSizeBeforeCreate = mDbMappingRepository.findAll().size();

        // Create the MDbMapping
        MDbMappingDTO mDbMappingDTO = mDbMappingMapper.toDto(mDbMapping);
        restMDbMappingMockMvc.perform(post("/api/m-db-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDbMappingDTO)))
            .andExpect(status().isCreated());

        // Validate the MDbMapping in the database
        List<MDbMapping> mDbMappingList = mDbMappingRepository.findAll();
        assertThat(mDbMappingList).hasSize(databaseSizeBeforeCreate + 1);
        MDbMapping testMDbMapping = mDbMappingList.get(mDbMappingList.size() - 1);
        assertThat(testMDbMapping.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testMDbMapping.getDbName()).isEqualTo(DEFAULT_DB_NAME);
        assertThat(testMDbMapping.getPath()).isEqualTo(DEFAULT_PATH);
    }

    @Test
    @Transactional
    public void createMDbMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDbMappingRepository.findAll().size();

        // Create the MDbMapping with an existing ID
        mDbMapping.setId(1L);
        MDbMappingDTO mDbMappingDTO = mDbMappingMapper.toDto(mDbMapping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDbMappingMockMvc.perform(post("/api/m-db-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDbMappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDbMapping in the database
        List<MDbMapping> mDbMappingList = mDbMappingRepository.findAll();
        assertThat(mDbMappingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDbMappings() throws Exception {
        // Initialize the database
        mDbMappingRepository.saveAndFlush(mDbMapping);

        // Get all the mDbMappingList
        restMDbMappingMockMvc.perform(get("/api/m-db-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDbMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].dbName").value(hasItem(DEFAULT_DB_NAME.toString())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())));
    }
    
    @Test
    @Transactional
    public void getMDbMapping() throws Exception {
        // Initialize the database
        mDbMappingRepository.saveAndFlush(mDbMapping);

        // Get the mDbMapping
        restMDbMappingMockMvc.perform(get("/api/m-db-mappings/{id}", mDbMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDbMapping.getId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.dbName").value(DEFAULT_DB_NAME.toString()))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDbMappingShouldBeFound(String filter) throws Exception {
        restMDbMappingMockMvc.perform(get("/api/m-db-mappings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDbMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].dbName").value(hasItem(DEFAULT_DB_NAME.toString())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())));

        // Check, that the count call also returns 1
        restMDbMappingMockMvc.perform(get("/api/m-db-mappings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDbMappingShouldNotBeFound(String filter) throws Exception {
        restMDbMappingMockMvc.perform(get("/api/m-db-mappings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDbMappingMockMvc.perform(get("/api/m-db-mappings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDbMapping() throws Exception {
        // Get the mDbMapping
        restMDbMappingMockMvc.perform(get("/api/m-db-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDbMapping() throws Exception {
        // Initialize the database
        mDbMappingRepository.saveAndFlush(mDbMapping);

        int databaseSizeBeforeUpdate = mDbMappingRepository.findAll().size();

        // Update the mDbMapping
        MDbMapping updatedMDbMapping = mDbMappingRepository.findById(mDbMapping.getId()).get();
        // Disconnect from session so that the updates on updatedMDbMapping are not directly saved in db
        em.detach(updatedMDbMapping);
        updatedMDbMapping
            .fileName(UPDATED_FILE_NAME)
            .dbName(UPDATED_DB_NAME)
            .path(UPDATED_PATH);
        MDbMappingDTO mDbMappingDTO = mDbMappingMapper.toDto(updatedMDbMapping);

        restMDbMappingMockMvc.perform(put("/api/m-db-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDbMappingDTO)))
            .andExpect(status().isOk());

        // Validate the MDbMapping in the database
        List<MDbMapping> mDbMappingList = mDbMappingRepository.findAll();
        assertThat(mDbMappingList).hasSize(databaseSizeBeforeUpdate);
        MDbMapping testMDbMapping = mDbMappingList.get(mDbMappingList.size() - 1);
        assertThat(testMDbMapping.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testMDbMapping.getDbName()).isEqualTo(UPDATED_DB_NAME);
        assertThat(testMDbMapping.getPath()).isEqualTo(UPDATED_PATH);
    }

    @Test
    @Transactional
    public void updateNonExistingMDbMapping() throws Exception {
        int databaseSizeBeforeUpdate = mDbMappingRepository.findAll().size();

        // Create the MDbMapping
        MDbMappingDTO mDbMappingDTO = mDbMappingMapper.toDto(mDbMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDbMappingMockMvc.perform(put("/api/m-db-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDbMappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDbMapping in the database
        List<MDbMapping> mDbMappingList = mDbMappingRepository.findAll();
        assertThat(mDbMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDbMapping() throws Exception {
        // Initialize the database
        mDbMappingRepository.saveAndFlush(mDbMapping);

        int databaseSizeBeforeDelete = mDbMappingRepository.findAll().size();

        // Delete the mDbMapping
        restMDbMappingMockMvc.perform(delete("/api/m-db-mappings/{id}", mDbMapping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDbMapping> mDbMappingList = mDbMappingRepository.findAll();
        assertThat(mDbMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDbMapping.class);
        MDbMapping mDbMapping1 = new MDbMapping();
        mDbMapping1.setId(1L);
        MDbMapping mDbMapping2 = new MDbMapping();
        mDbMapping2.setId(mDbMapping1.getId());
        assertThat(mDbMapping1).isEqualTo(mDbMapping2);
        mDbMapping2.setId(2L);
        assertThat(mDbMapping1).isNotEqualTo(mDbMapping2);
        mDbMapping1.setId(null);
        assertThat(mDbMapping1).isNotEqualTo(mDbMapping2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDbMappingDTO.class);
        MDbMappingDTO mDbMappingDTO1 = new MDbMappingDTO();
        mDbMappingDTO1.setId(1L);
        MDbMappingDTO mDbMappingDTO2 = new MDbMappingDTO();
        assertThat(mDbMappingDTO1).isNotEqualTo(mDbMappingDTO2);
        mDbMappingDTO2.setId(mDbMappingDTO1.getId());
        assertThat(mDbMappingDTO1).isEqualTo(mDbMappingDTO2);
        mDbMappingDTO2.setId(2L);
        assertThat(mDbMappingDTO1).isNotEqualTo(mDbMappingDTO2);
        mDbMappingDTO1.setId(null);
        assertThat(mDbMappingDTO1).isNotEqualTo(mDbMappingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDbMappingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDbMappingMapper.fromId(null)).isNull();
    }
}
