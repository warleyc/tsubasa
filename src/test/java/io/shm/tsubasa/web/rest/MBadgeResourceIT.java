package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MBadge;
import io.shm.tsubasa.repository.MBadgeRepository;
import io.shm.tsubasa.service.MBadgeService;
import io.shm.tsubasa.service.dto.MBadgeDTO;
import io.shm.tsubasa.service.mapper.MBadgeMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MBadgeCriteria;
import io.shm.tsubasa.service.MBadgeQueryService;

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
 * Integration tests for the {@Link MBadgeResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MBadgeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NAME = "BBBBBBBBBB";

    @Autowired
    private MBadgeRepository mBadgeRepository;

    @Autowired
    private MBadgeMapper mBadgeMapper;

    @Autowired
    private MBadgeService mBadgeService;

    @Autowired
    private MBadgeQueryService mBadgeQueryService;

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

    private MockMvc restMBadgeMockMvc;

    private MBadge mBadge;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MBadgeResource mBadgeResource = new MBadgeResource(mBadgeService, mBadgeQueryService);
        this.restMBadgeMockMvc = MockMvcBuilders.standaloneSetup(mBadgeResource)
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
    public static MBadge createEntity(EntityManager em) {
        MBadge mBadge = new MBadge()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .assetName(DEFAULT_ASSET_NAME);
        return mBadge;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MBadge createUpdatedEntity(EntityManager em) {
        MBadge mBadge = new MBadge()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .assetName(UPDATED_ASSET_NAME);
        return mBadge;
    }

    @BeforeEach
    public void initTest() {
        mBadge = createEntity(em);
    }

    @Test
    @Transactional
    public void createMBadge() throws Exception {
        int databaseSizeBeforeCreate = mBadgeRepository.findAll().size();

        // Create the MBadge
        MBadgeDTO mBadgeDTO = mBadgeMapper.toDto(mBadge);
        restMBadgeMockMvc.perform(post("/api/m-badges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mBadgeDTO)))
            .andExpect(status().isCreated());

        // Validate the MBadge in the database
        List<MBadge> mBadgeList = mBadgeRepository.findAll();
        assertThat(mBadgeList).hasSize(databaseSizeBeforeCreate + 1);
        MBadge testMBadge = mBadgeList.get(mBadgeList.size() - 1);
        assertThat(testMBadge.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMBadge.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMBadge.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMBadge.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
    }

    @Test
    @Transactional
    public void createMBadgeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mBadgeRepository.findAll().size();

        // Create the MBadge with an existing ID
        mBadge.setId(1L);
        MBadgeDTO mBadgeDTO = mBadgeMapper.toDto(mBadge);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMBadgeMockMvc.perform(post("/api/m-badges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mBadgeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MBadge in the database
        List<MBadge> mBadgeList = mBadgeRepository.findAll();
        assertThat(mBadgeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mBadgeRepository.findAll().size();
        // set the field null
        mBadge.setType(null);

        // Create the MBadge, which fails.
        MBadgeDTO mBadgeDTO = mBadgeMapper.toDto(mBadge);

        restMBadgeMockMvc.perform(post("/api/m-badges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mBadgeDTO)))
            .andExpect(status().isBadRequest());

        List<MBadge> mBadgeList = mBadgeRepository.findAll();
        assertThat(mBadgeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMBadges() throws Exception {
        // Initialize the database
        mBadgeRepository.saveAndFlush(mBadge);

        // Get all the mBadgeList
        restMBadgeMockMvc.perform(get("/api/m-badges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mBadge.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMBadge() throws Exception {
        // Initialize the database
        mBadgeRepository.saveAndFlush(mBadge);

        // Get the mBadge
        restMBadgeMockMvc.perform(get("/api/m-badges/{id}", mBadge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mBadge.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.assetName").value(DEFAULT_ASSET_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMBadgesByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mBadgeRepository.saveAndFlush(mBadge);

        // Get all the mBadgeList where type equals to DEFAULT_TYPE
        defaultMBadgeShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the mBadgeList where type equals to UPDATED_TYPE
        defaultMBadgeShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMBadgesByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mBadgeRepository.saveAndFlush(mBadge);

        // Get all the mBadgeList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultMBadgeShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the mBadgeList where type equals to UPDATED_TYPE
        defaultMBadgeShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMBadgesByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mBadgeRepository.saveAndFlush(mBadge);

        // Get all the mBadgeList where type is not null
        defaultMBadgeShouldBeFound("type.specified=true");

        // Get all the mBadgeList where type is null
        defaultMBadgeShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllMBadgesByTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mBadgeRepository.saveAndFlush(mBadge);

        // Get all the mBadgeList where type greater than or equals to DEFAULT_TYPE
        defaultMBadgeShouldBeFound("type.greaterOrEqualThan=" + DEFAULT_TYPE);

        // Get all the mBadgeList where type greater than or equals to UPDATED_TYPE
        defaultMBadgeShouldNotBeFound("type.greaterOrEqualThan=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMBadgesByTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mBadgeRepository.saveAndFlush(mBadge);

        // Get all the mBadgeList where type less than or equals to DEFAULT_TYPE
        defaultMBadgeShouldNotBeFound("type.lessThan=" + DEFAULT_TYPE);

        // Get all the mBadgeList where type less than or equals to UPDATED_TYPE
        defaultMBadgeShouldBeFound("type.lessThan=" + UPDATED_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMBadgeShouldBeFound(String filter) throws Exception {
        restMBadgeMockMvc.perform(get("/api/m-badges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mBadge.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())));

        // Check, that the count call also returns 1
        restMBadgeMockMvc.perform(get("/api/m-badges/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMBadgeShouldNotBeFound(String filter) throws Exception {
        restMBadgeMockMvc.perform(get("/api/m-badges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMBadgeMockMvc.perform(get("/api/m-badges/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMBadge() throws Exception {
        // Get the mBadge
        restMBadgeMockMvc.perform(get("/api/m-badges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMBadge() throws Exception {
        // Initialize the database
        mBadgeRepository.saveAndFlush(mBadge);

        int databaseSizeBeforeUpdate = mBadgeRepository.findAll().size();

        // Update the mBadge
        MBadge updatedMBadge = mBadgeRepository.findById(mBadge.getId()).get();
        // Disconnect from session so that the updates on updatedMBadge are not directly saved in db
        em.detach(updatedMBadge);
        updatedMBadge
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION)
            .assetName(UPDATED_ASSET_NAME);
        MBadgeDTO mBadgeDTO = mBadgeMapper.toDto(updatedMBadge);

        restMBadgeMockMvc.perform(put("/api/m-badges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mBadgeDTO)))
            .andExpect(status().isOk());

        // Validate the MBadge in the database
        List<MBadge> mBadgeList = mBadgeRepository.findAll();
        assertThat(mBadgeList).hasSize(databaseSizeBeforeUpdate);
        MBadge testMBadge = mBadgeList.get(mBadgeList.size() - 1);
        assertThat(testMBadge.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMBadge.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMBadge.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMBadge.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMBadge() throws Exception {
        int databaseSizeBeforeUpdate = mBadgeRepository.findAll().size();

        // Create the MBadge
        MBadgeDTO mBadgeDTO = mBadgeMapper.toDto(mBadge);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMBadgeMockMvc.perform(put("/api/m-badges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mBadgeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MBadge in the database
        List<MBadge> mBadgeList = mBadgeRepository.findAll();
        assertThat(mBadgeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMBadge() throws Exception {
        // Initialize the database
        mBadgeRepository.saveAndFlush(mBadge);

        int databaseSizeBeforeDelete = mBadgeRepository.findAll().size();

        // Delete the mBadge
        restMBadgeMockMvc.perform(delete("/api/m-badges/{id}", mBadge.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MBadge> mBadgeList = mBadgeRepository.findAll();
        assertThat(mBadgeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MBadge.class);
        MBadge mBadge1 = new MBadge();
        mBadge1.setId(1L);
        MBadge mBadge2 = new MBadge();
        mBadge2.setId(mBadge1.getId());
        assertThat(mBadge1).isEqualTo(mBadge2);
        mBadge2.setId(2L);
        assertThat(mBadge1).isNotEqualTo(mBadge2);
        mBadge1.setId(null);
        assertThat(mBadge1).isNotEqualTo(mBadge2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MBadgeDTO.class);
        MBadgeDTO mBadgeDTO1 = new MBadgeDTO();
        mBadgeDTO1.setId(1L);
        MBadgeDTO mBadgeDTO2 = new MBadgeDTO();
        assertThat(mBadgeDTO1).isNotEqualTo(mBadgeDTO2);
        mBadgeDTO2.setId(mBadgeDTO1.getId());
        assertThat(mBadgeDTO1).isEqualTo(mBadgeDTO2);
        mBadgeDTO2.setId(2L);
        assertThat(mBadgeDTO1).isNotEqualTo(mBadgeDTO2);
        mBadgeDTO1.setId(null);
        assertThat(mBadgeDTO1).isNotEqualTo(mBadgeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mBadgeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mBadgeMapper.fromId(null)).isNull();
    }
}
