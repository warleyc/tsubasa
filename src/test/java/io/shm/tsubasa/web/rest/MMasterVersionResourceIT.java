package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMasterVersion;
import io.shm.tsubasa.repository.MMasterVersionRepository;
import io.shm.tsubasa.service.MMasterVersionService;
import io.shm.tsubasa.service.dto.MMasterVersionDTO;
import io.shm.tsubasa.service.mapper.MMasterVersionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMasterVersionCriteria;
import io.shm.tsubasa.service.MMasterVersionQueryService;

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
 * Integration tests for the {@Link MMasterVersionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMasterVersionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer UPDATED_SIZE = 2;

    @Autowired
    private MMasterVersionRepository mMasterVersionRepository;

    @Autowired
    private MMasterVersionMapper mMasterVersionMapper;

    @Autowired
    private MMasterVersionService mMasterVersionService;

    @Autowired
    private MMasterVersionQueryService mMasterVersionQueryService;

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

    private MockMvc restMMasterVersionMockMvc;

    private MMasterVersion mMasterVersion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMasterVersionResource mMasterVersionResource = new MMasterVersionResource(mMasterVersionService, mMasterVersionQueryService);
        this.restMMasterVersionMockMvc = MockMvcBuilders.standaloneSetup(mMasterVersionResource)
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
    public static MMasterVersion createEntity(EntityManager em) {
        MMasterVersion mMasterVersion = new MMasterVersion()
            .name(DEFAULT_NAME)
            .version(DEFAULT_VERSION)
            .path(DEFAULT_PATH)
            .size(DEFAULT_SIZE);
        return mMasterVersion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMasterVersion createUpdatedEntity(EntityManager em) {
        MMasterVersion mMasterVersion = new MMasterVersion()
            .name(UPDATED_NAME)
            .version(UPDATED_VERSION)
            .path(UPDATED_PATH)
            .size(UPDATED_SIZE);
        return mMasterVersion;
    }

    @BeforeEach
    public void initTest() {
        mMasterVersion = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMasterVersion() throws Exception {
        int databaseSizeBeforeCreate = mMasterVersionRepository.findAll().size();

        // Create the MMasterVersion
        MMasterVersionDTO mMasterVersionDTO = mMasterVersionMapper.toDto(mMasterVersion);
        restMMasterVersionMockMvc.perform(post("/api/m-master-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMasterVersionDTO)))
            .andExpect(status().isCreated());

        // Validate the MMasterVersion in the database
        List<MMasterVersion> mMasterVersionList = mMasterVersionRepository.findAll();
        assertThat(mMasterVersionList).hasSize(databaseSizeBeforeCreate + 1);
        MMasterVersion testMMasterVersion = mMasterVersionList.get(mMasterVersionList.size() - 1);
        assertThat(testMMasterVersion.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMMasterVersion.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testMMasterVersion.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testMMasterVersion.getSize()).isEqualTo(DEFAULT_SIZE);
    }

    @Test
    @Transactional
    public void createMMasterVersionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMasterVersionRepository.findAll().size();

        // Create the MMasterVersion with an existing ID
        mMasterVersion.setId(1L);
        MMasterVersionDTO mMasterVersionDTO = mMasterVersionMapper.toDto(mMasterVersion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMasterVersionMockMvc.perform(post("/api/m-master-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMasterVersionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMasterVersion in the database
        List<MMasterVersion> mMasterVersionList = mMasterVersionRepository.findAll();
        assertThat(mMasterVersionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMasterVersionRepository.findAll().size();
        // set the field null
        mMasterVersion.setVersion(null);

        // Create the MMasterVersion, which fails.
        MMasterVersionDTO mMasterVersionDTO = mMasterVersionMapper.toDto(mMasterVersion);

        restMMasterVersionMockMvc.perform(post("/api/m-master-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMasterVersionDTO)))
            .andExpect(status().isBadRequest());

        List<MMasterVersion> mMasterVersionList = mMasterVersionRepository.findAll();
        assertThat(mMasterVersionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMasterVersions() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList
        restMMasterVersionMockMvc.perform(get("/api/m-master-versions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMasterVersion.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)));
    }
    
    @Test
    @Transactional
    public void getMMasterVersion() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get the mMasterVersion
        restMMasterVersionMockMvc.perform(get("/api/m-master-versions/{id}", mMasterVersion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMasterVersion.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE));
    }

    @Test
    @Transactional
    public void getAllMMasterVersionsByVersionIsEqualToSomething() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where version equals to DEFAULT_VERSION
        defaultMMasterVersionShouldBeFound("version.equals=" + DEFAULT_VERSION);

        // Get all the mMasterVersionList where version equals to UPDATED_VERSION
        defaultMMasterVersionShouldNotBeFound("version.equals=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllMMasterVersionsByVersionIsInShouldWork() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where version in DEFAULT_VERSION or UPDATED_VERSION
        defaultMMasterVersionShouldBeFound("version.in=" + DEFAULT_VERSION + "," + UPDATED_VERSION);

        // Get all the mMasterVersionList where version equals to UPDATED_VERSION
        defaultMMasterVersionShouldNotBeFound("version.in=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllMMasterVersionsByVersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where version is not null
        defaultMMasterVersionShouldBeFound("version.specified=true");

        // Get all the mMasterVersionList where version is null
        defaultMMasterVersionShouldNotBeFound("version.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMasterVersionsByVersionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where version greater than or equals to DEFAULT_VERSION
        defaultMMasterVersionShouldBeFound("version.greaterOrEqualThan=" + DEFAULT_VERSION);

        // Get all the mMasterVersionList where version greater than or equals to UPDATED_VERSION
        defaultMMasterVersionShouldNotBeFound("version.greaterOrEqualThan=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllMMasterVersionsByVersionIsLessThanSomething() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where version less than or equals to DEFAULT_VERSION
        defaultMMasterVersionShouldNotBeFound("version.lessThan=" + DEFAULT_VERSION);

        // Get all the mMasterVersionList where version less than or equals to UPDATED_VERSION
        defaultMMasterVersionShouldBeFound("version.lessThan=" + UPDATED_VERSION);
    }


    @Test
    @Transactional
    public void getAllMMasterVersionsBySizeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where size equals to DEFAULT_SIZE
        defaultMMasterVersionShouldBeFound("size.equals=" + DEFAULT_SIZE);

        // Get all the mMasterVersionList where size equals to UPDATED_SIZE
        defaultMMasterVersionShouldNotBeFound("size.equals=" + UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void getAllMMasterVersionsBySizeIsInShouldWork() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where size in DEFAULT_SIZE or UPDATED_SIZE
        defaultMMasterVersionShouldBeFound("size.in=" + DEFAULT_SIZE + "," + UPDATED_SIZE);

        // Get all the mMasterVersionList where size equals to UPDATED_SIZE
        defaultMMasterVersionShouldNotBeFound("size.in=" + UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void getAllMMasterVersionsBySizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where size is not null
        defaultMMasterVersionShouldBeFound("size.specified=true");

        // Get all the mMasterVersionList where size is null
        defaultMMasterVersionShouldNotBeFound("size.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMasterVersionsBySizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where size greater than or equals to DEFAULT_SIZE
        defaultMMasterVersionShouldBeFound("size.greaterOrEqualThan=" + DEFAULT_SIZE);

        // Get all the mMasterVersionList where size greater than or equals to UPDATED_SIZE
        defaultMMasterVersionShouldNotBeFound("size.greaterOrEqualThan=" + UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void getAllMMasterVersionsBySizeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        // Get all the mMasterVersionList where size less than or equals to DEFAULT_SIZE
        defaultMMasterVersionShouldNotBeFound("size.lessThan=" + DEFAULT_SIZE);

        // Get all the mMasterVersionList where size less than or equals to UPDATED_SIZE
        defaultMMasterVersionShouldBeFound("size.lessThan=" + UPDATED_SIZE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMasterVersionShouldBeFound(String filter) throws Exception {
        restMMasterVersionMockMvc.perform(get("/api/m-master-versions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMasterVersion.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)));

        // Check, that the count call also returns 1
        restMMasterVersionMockMvc.perform(get("/api/m-master-versions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMasterVersionShouldNotBeFound(String filter) throws Exception {
        restMMasterVersionMockMvc.perform(get("/api/m-master-versions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMasterVersionMockMvc.perform(get("/api/m-master-versions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMasterVersion() throws Exception {
        // Get the mMasterVersion
        restMMasterVersionMockMvc.perform(get("/api/m-master-versions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMasterVersion() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        int databaseSizeBeforeUpdate = mMasterVersionRepository.findAll().size();

        // Update the mMasterVersion
        MMasterVersion updatedMMasterVersion = mMasterVersionRepository.findById(mMasterVersion.getId()).get();
        // Disconnect from session so that the updates on updatedMMasterVersion are not directly saved in db
        em.detach(updatedMMasterVersion);
        updatedMMasterVersion
            .name(UPDATED_NAME)
            .version(UPDATED_VERSION)
            .path(UPDATED_PATH)
            .size(UPDATED_SIZE);
        MMasterVersionDTO mMasterVersionDTO = mMasterVersionMapper.toDto(updatedMMasterVersion);

        restMMasterVersionMockMvc.perform(put("/api/m-master-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMasterVersionDTO)))
            .andExpect(status().isOk());

        // Validate the MMasterVersion in the database
        List<MMasterVersion> mMasterVersionList = mMasterVersionRepository.findAll();
        assertThat(mMasterVersionList).hasSize(databaseSizeBeforeUpdate);
        MMasterVersion testMMasterVersion = mMasterVersionList.get(mMasterVersionList.size() - 1);
        assertThat(testMMasterVersion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMMasterVersion.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMMasterVersion.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testMMasterVersion.getSize()).isEqualTo(UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void updateNonExistingMMasterVersion() throws Exception {
        int databaseSizeBeforeUpdate = mMasterVersionRepository.findAll().size();

        // Create the MMasterVersion
        MMasterVersionDTO mMasterVersionDTO = mMasterVersionMapper.toDto(mMasterVersion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMasterVersionMockMvc.perform(put("/api/m-master-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMasterVersionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMasterVersion in the database
        List<MMasterVersion> mMasterVersionList = mMasterVersionRepository.findAll();
        assertThat(mMasterVersionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMasterVersion() throws Exception {
        // Initialize the database
        mMasterVersionRepository.saveAndFlush(mMasterVersion);

        int databaseSizeBeforeDelete = mMasterVersionRepository.findAll().size();

        // Delete the mMasterVersion
        restMMasterVersionMockMvc.perform(delete("/api/m-master-versions/{id}", mMasterVersion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMasterVersion> mMasterVersionList = mMasterVersionRepository.findAll();
        assertThat(mMasterVersionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMasterVersion.class);
        MMasterVersion mMasterVersion1 = new MMasterVersion();
        mMasterVersion1.setId(1L);
        MMasterVersion mMasterVersion2 = new MMasterVersion();
        mMasterVersion2.setId(mMasterVersion1.getId());
        assertThat(mMasterVersion1).isEqualTo(mMasterVersion2);
        mMasterVersion2.setId(2L);
        assertThat(mMasterVersion1).isNotEqualTo(mMasterVersion2);
        mMasterVersion1.setId(null);
        assertThat(mMasterVersion1).isNotEqualTo(mMasterVersion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMasterVersionDTO.class);
        MMasterVersionDTO mMasterVersionDTO1 = new MMasterVersionDTO();
        mMasterVersionDTO1.setId(1L);
        MMasterVersionDTO mMasterVersionDTO2 = new MMasterVersionDTO();
        assertThat(mMasterVersionDTO1).isNotEqualTo(mMasterVersionDTO2);
        mMasterVersionDTO2.setId(mMasterVersionDTO1.getId());
        assertThat(mMasterVersionDTO1).isEqualTo(mMasterVersionDTO2);
        mMasterVersionDTO2.setId(2L);
        assertThat(mMasterVersionDTO1).isNotEqualTo(mMasterVersionDTO2);
        mMasterVersionDTO1.setId(null);
        assertThat(mMasterVersionDTO1).isNotEqualTo(mMasterVersionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMasterVersionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMasterVersionMapper.fromId(null)).isNull();
    }
}
