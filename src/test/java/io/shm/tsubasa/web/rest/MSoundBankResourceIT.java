package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MSoundBank;
import io.shm.tsubasa.repository.MSoundBankRepository;
import io.shm.tsubasa.service.MSoundBankService;
import io.shm.tsubasa.service.dto.MSoundBankDTO;
import io.shm.tsubasa.service.mapper.MSoundBankMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MSoundBankCriteria;
import io.shm.tsubasa.service.MSoundBankQueryService;

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
 * Integration tests for the {@Link MSoundBankResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MSoundBankResourceIT {

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_PF = "AAAAAAAAAA";
    private static final String UPDATED_PF = "BBBBBBBBBB";

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final Integer DEFAULT_FILE_SIZE = 1;
    private static final Integer UPDATED_FILE_SIZE = 2;

    @Autowired
    private MSoundBankRepository mSoundBankRepository;

    @Autowired
    private MSoundBankMapper mSoundBankMapper;

    @Autowired
    private MSoundBankService mSoundBankService;

    @Autowired
    private MSoundBankQueryService mSoundBankQueryService;

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

    private MockMvc restMSoundBankMockMvc;

    private MSoundBank mSoundBank;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MSoundBankResource mSoundBankResource = new MSoundBankResource(mSoundBankService, mSoundBankQueryService);
        this.restMSoundBankMockMvc = MockMvcBuilders.standaloneSetup(mSoundBankResource)
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
    public static MSoundBank createEntity(EntityManager em) {
        MSoundBank mSoundBank = new MSoundBank()
            .path(DEFAULT_PATH)
            .pf(DEFAULT_PF)
            .version(DEFAULT_VERSION)
            .fileSize(DEFAULT_FILE_SIZE);
        return mSoundBank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MSoundBank createUpdatedEntity(EntityManager em) {
        MSoundBank mSoundBank = new MSoundBank()
            .path(UPDATED_PATH)
            .pf(UPDATED_PF)
            .version(UPDATED_VERSION)
            .fileSize(UPDATED_FILE_SIZE);
        return mSoundBank;
    }

    @BeforeEach
    public void initTest() {
        mSoundBank = createEntity(em);
    }

    @Test
    @Transactional
    public void createMSoundBank() throws Exception {
        int databaseSizeBeforeCreate = mSoundBankRepository.findAll().size();

        // Create the MSoundBank
        MSoundBankDTO mSoundBankDTO = mSoundBankMapper.toDto(mSoundBank);
        restMSoundBankMockMvc.perform(post("/api/m-sound-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankDTO)))
            .andExpect(status().isCreated());

        // Validate the MSoundBank in the database
        List<MSoundBank> mSoundBankList = mSoundBankRepository.findAll();
        assertThat(mSoundBankList).hasSize(databaseSizeBeforeCreate + 1);
        MSoundBank testMSoundBank = mSoundBankList.get(mSoundBankList.size() - 1);
        assertThat(testMSoundBank.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testMSoundBank.getPf()).isEqualTo(DEFAULT_PF);
        assertThat(testMSoundBank.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testMSoundBank.getFileSize()).isEqualTo(DEFAULT_FILE_SIZE);
    }

    @Test
    @Transactional
    public void createMSoundBankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mSoundBankRepository.findAll().size();

        // Create the MSoundBank with an existing ID
        mSoundBank.setId(1L);
        MSoundBankDTO mSoundBankDTO = mSoundBankMapper.toDto(mSoundBank);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMSoundBankMockMvc.perform(post("/api/m-sound-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSoundBank in the database
        List<MSoundBank> mSoundBankList = mSoundBankRepository.findAll();
        assertThat(mSoundBankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSoundBankRepository.findAll().size();
        // set the field null
        mSoundBank.setVersion(null);

        // Create the MSoundBank, which fails.
        MSoundBankDTO mSoundBankDTO = mSoundBankMapper.toDto(mSoundBank);

        restMSoundBankMockMvc.perform(post("/api/m-sound-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankDTO)))
            .andExpect(status().isBadRequest());

        List<MSoundBank> mSoundBankList = mSoundBankRepository.findAll();
        assertThat(mSoundBankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFileSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSoundBankRepository.findAll().size();
        // set the field null
        mSoundBank.setFileSize(null);

        // Create the MSoundBank, which fails.
        MSoundBankDTO mSoundBankDTO = mSoundBankMapper.toDto(mSoundBank);

        restMSoundBankMockMvc.perform(post("/api/m-sound-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankDTO)))
            .andExpect(status().isBadRequest());

        List<MSoundBank> mSoundBankList = mSoundBankRepository.findAll();
        assertThat(mSoundBankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMSoundBanks() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList
        restMSoundBankMockMvc.perform(get("/api/m-sound-banks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSoundBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())))
            .andExpect(jsonPath("$.[*].pf").value(hasItem(DEFAULT_PF.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE)));
    }
    
    @Test
    @Transactional
    public void getMSoundBank() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get the mSoundBank
        restMSoundBankMockMvc.perform(get("/api/m-sound-banks/{id}", mSoundBank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mSoundBank.getId().intValue()))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH.toString()))
            .andExpect(jsonPath("$.pf").value(DEFAULT_PF.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.fileSize").value(DEFAULT_FILE_SIZE));
    }

    @Test
    @Transactional
    public void getAllMSoundBanksByVersionIsEqualToSomething() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where version equals to DEFAULT_VERSION
        defaultMSoundBankShouldBeFound("version.equals=" + DEFAULT_VERSION);

        // Get all the mSoundBankList where version equals to UPDATED_VERSION
        defaultMSoundBankShouldNotBeFound("version.equals=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllMSoundBanksByVersionIsInShouldWork() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where version in DEFAULT_VERSION or UPDATED_VERSION
        defaultMSoundBankShouldBeFound("version.in=" + DEFAULT_VERSION + "," + UPDATED_VERSION);

        // Get all the mSoundBankList where version equals to UPDATED_VERSION
        defaultMSoundBankShouldNotBeFound("version.in=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllMSoundBanksByVersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where version is not null
        defaultMSoundBankShouldBeFound("version.specified=true");

        // Get all the mSoundBankList where version is null
        defaultMSoundBankShouldNotBeFound("version.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSoundBanksByVersionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where version greater than or equals to DEFAULT_VERSION
        defaultMSoundBankShouldBeFound("version.greaterOrEqualThan=" + DEFAULT_VERSION);

        // Get all the mSoundBankList where version greater than or equals to UPDATED_VERSION
        defaultMSoundBankShouldNotBeFound("version.greaterOrEqualThan=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void getAllMSoundBanksByVersionIsLessThanSomething() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where version less than or equals to DEFAULT_VERSION
        defaultMSoundBankShouldNotBeFound("version.lessThan=" + DEFAULT_VERSION);

        // Get all the mSoundBankList where version less than or equals to UPDATED_VERSION
        defaultMSoundBankShouldBeFound("version.lessThan=" + UPDATED_VERSION);
    }


    @Test
    @Transactional
    public void getAllMSoundBanksByFileSizeIsEqualToSomething() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where fileSize equals to DEFAULT_FILE_SIZE
        defaultMSoundBankShouldBeFound("fileSize.equals=" + DEFAULT_FILE_SIZE);

        // Get all the mSoundBankList where fileSize equals to UPDATED_FILE_SIZE
        defaultMSoundBankShouldNotBeFound("fileSize.equals=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    public void getAllMSoundBanksByFileSizeIsInShouldWork() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where fileSize in DEFAULT_FILE_SIZE or UPDATED_FILE_SIZE
        defaultMSoundBankShouldBeFound("fileSize.in=" + DEFAULT_FILE_SIZE + "," + UPDATED_FILE_SIZE);

        // Get all the mSoundBankList where fileSize equals to UPDATED_FILE_SIZE
        defaultMSoundBankShouldNotBeFound("fileSize.in=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    public void getAllMSoundBanksByFileSizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where fileSize is not null
        defaultMSoundBankShouldBeFound("fileSize.specified=true");

        // Get all the mSoundBankList where fileSize is null
        defaultMSoundBankShouldNotBeFound("fileSize.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSoundBanksByFileSizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where fileSize greater than or equals to DEFAULT_FILE_SIZE
        defaultMSoundBankShouldBeFound("fileSize.greaterOrEqualThan=" + DEFAULT_FILE_SIZE);

        // Get all the mSoundBankList where fileSize greater than or equals to UPDATED_FILE_SIZE
        defaultMSoundBankShouldNotBeFound("fileSize.greaterOrEqualThan=" + UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    public void getAllMSoundBanksByFileSizeIsLessThanSomething() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        // Get all the mSoundBankList where fileSize less than or equals to DEFAULT_FILE_SIZE
        defaultMSoundBankShouldNotBeFound("fileSize.lessThan=" + DEFAULT_FILE_SIZE);

        // Get all the mSoundBankList where fileSize less than or equals to UPDATED_FILE_SIZE
        defaultMSoundBankShouldBeFound("fileSize.lessThan=" + UPDATED_FILE_SIZE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMSoundBankShouldBeFound(String filter) throws Exception {
        restMSoundBankMockMvc.perform(get("/api/m-sound-banks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSoundBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())))
            .andExpect(jsonPath("$.[*].pf").value(hasItem(DEFAULT_PF.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE)));

        // Check, that the count call also returns 1
        restMSoundBankMockMvc.perform(get("/api/m-sound-banks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMSoundBankShouldNotBeFound(String filter) throws Exception {
        restMSoundBankMockMvc.perform(get("/api/m-sound-banks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMSoundBankMockMvc.perform(get("/api/m-sound-banks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMSoundBank() throws Exception {
        // Get the mSoundBank
        restMSoundBankMockMvc.perform(get("/api/m-sound-banks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMSoundBank() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        int databaseSizeBeforeUpdate = mSoundBankRepository.findAll().size();

        // Update the mSoundBank
        MSoundBank updatedMSoundBank = mSoundBankRepository.findById(mSoundBank.getId()).get();
        // Disconnect from session so that the updates on updatedMSoundBank are not directly saved in db
        em.detach(updatedMSoundBank);
        updatedMSoundBank
            .path(UPDATED_PATH)
            .pf(UPDATED_PF)
            .version(UPDATED_VERSION)
            .fileSize(UPDATED_FILE_SIZE);
        MSoundBankDTO mSoundBankDTO = mSoundBankMapper.toDto(updatedMSoundBank);

        restMSoundBankMockMvc.perform(put("/api/m-sound-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankDTO)))
            .andExpect(status().isOk());

        // Validate the MSoundBank in the database
        List<MSoundBank> mSoundBankList = mSoundBankRepository.findAll();
        assertThat(mSoundBankList).hasSize(databaseSizeBeforeUpdate);
        MSoundBank testMSoundBank = mSoundBankList.get(mSoundBankList.size() - 1);
        assertThat(testMSoundBank.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testMSoundBank.getPf()).isEqualTo(UPDATED_PF);
        assertThat(testMSoundBank.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testMSoundBank.getFileSize()).isEqualTo(UPDATED_FILE_SIZE);
    }

    @Test
    @Transactional
    public void updateNonExistingMSoundBank() throws Exception {
        int databaseSizeBeforeUpdate = mSoundBankRepository.findAll().size();

        // Create the MSoundBank
        MSoundBankDTO mSoundBankDTO = mSoundBankMapper.toDto(mSoundBank);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMSoundBankMockMvc.perform(put("/api/m-sound-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSoundBank in the database
        List<MSoundBank> mSoundBankList = mSoundBankRepository.findAll();
        assertThat(mSoundBankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMSoundBank() throws Exception {
        // Initialize the database
        mSoundBankRepository.saveAndFlush(mSoundBank);

        int databaseSizeBeforeDelete = mSoundBankRepository.findAll().size();

        // Delete the mSoundBank
        restMSoundBankMockMvc.perform(delete("/api/m-sound-banks/{id}", mSoundBank.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MSoundBank> mSoundBankList = mSoundBankRepository.findAll();
        assertThat(mSoundBankList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSoundBank.class);
        MSoundBank mSoundBank1 = new MSoundBank();
        mSoundBank1.setId(1L);
        MSoundBank mSoundBank2 = new MSoundBank();
        mSoundBank2.setId(mSoundBank1.getId());
        assertThat(mSoundBank1).isEqualTo(mSoundBank2);
        mSoundBank2.setId(2L);
        assertThat(mSoundBank1).isNotEqualTo(mSoundBank2);
        mSoundBank1.setId(null);
        assertThat(mSoundBank1).isNotEqualTo(mSoundBank2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSoundBankDTO.class);
        MSoundBankDTO mSoundBankDTO1 = new MSoundBankDTO();
        mSoundBankDTO1.setId(1L);
        MSoundBankDTO mSoundBankDTO2 = new MSoundBankDTO();
        assertThat(mSoundBankDTO1).isNotEqualTo(mSoundBankDTO2);
        mSoundBankDTO2.setId(mSoundBankDTO1.getId());
        assertThat(mSoundBankDTO1).isEqualTo(mSoundBankDTO2);
        mSoundBankDTO2.setId(2L);
        assertThat(mSoundBankDTO1).isNotEqualTo(mSoundBankDTO2);
        mSoundBankDTO1.setId(null);
        assertThat(mSoundBankDTO1).isNotEqualTo(mSoundBankDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mSoundBankMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mSoundBankMapper.fromId(null)).isNull();
    }
}
