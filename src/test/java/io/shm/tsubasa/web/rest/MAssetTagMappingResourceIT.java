package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAssetTagMapping;
import io.shm.tsubasa.repository.MAssetTagMappingRepository;
import io.shm.tsubasa.service.MAssetTagMappingService;
import io.shm.tsubasa.service.dto.MAssetTagMappingDTO;
import io.shm.tsubasa.service.mapper.MAssetTagMappingMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MAssetTagMappingCriteria;
import io.shm.tsubasa.service.MAssetTagMappingQueryService;

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
 * Integration tests for the {@Link MAssetTagMappingResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MAssetTagMappingResourceIT {

    private static final Integer DEFAULT_TAG = 1;
    private static final Integer UPDATED_TAG = 2;

    private static final String DEFAULT_IDS = "AAAAAAAAAA";
    private static final String UPDATED_IDS = "BBBBBBBBBB";

    @Autowired
    private MAssetTagMappingRepository mAssetTagMappingRepository;

    @Autowired
    private MAssetTagMappingMapper mAssetTagMappingMapper;

    @Autowired
    private MAssetTagMappingService mAssetTagMappingService;

    @Autowired
    private MAssetTagMappingQueryService mAssetTagMappingQueryService;

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

    private MockMvc restMAssetTagMappingMockMvc;

    private MAssetTagMapping mAssetTagMapping;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MAssetTagMappingResource mAssetTagMappingResource = new MAssetTagMappingResource(mAssetTagMappingService, mAssetTagMappingQueryService);
        this.restMAssetTagMappingMockMvc = MockMvcBuilders.standaloneSetup(mAssetTagMappingResource)
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
    public static MAssetTagMapping createEntity(EntityManager em) {
        MAssetTagMapping mAssetTagMapping = new MAssetTagMapping()
            .tag(DEFAULT_TAG)
            .ids(DEFAULT_IDS);
        return mAssetTagMapping;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAssetTagMapping createUpdatedEntity(EntityManager em) {
        MAssetTagMapping mAssetTagMapping = new MAssetTagMapping()
            .tag(UPDATED_TAG)
            .ids(UPDATED_IDS);
        return mAssetTagMapping;
    }

    @BeforeEach
    public void initTest() {
        mAssetTagMapping = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAssetTagMapping() throws Exception {
        int databaseSizeBeforeCreate = mAssetTagMappingRepository.findAll().size();

        // Create the MAssetTagMapping
        MAssetTagMappingDTO mAssetTagMappingDTO = mAssetTagMappingMapper.toDto(mAssetTagMapping);
        restMAssetTagMappingMockMvc.perform(post("/api/m-asset-tag-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetTagMappingDTO)))
            .andExpect(status().isCreated());

        // Validate the MAssetTagMapping in the database
        List<MAssetTagMapping> mAssetTagMappingList = mAssetTagMappingRepository.findAll();
        assertThat(mAssetTagMappingList).hasSize(databaseSizeBeforeCreate + 1);
        MAssetTagMapping testMAssetTagMapping = mAssetTagMappingList.get(mAssetTagMappingList.size() - 1);
        assertThat(testMAssetTagMapping.getTag()).isEqualTo(DEFAULT_TAG);
        assertThat(testMAssetTagMapping.getIds()).isEqualTo(DEFAULT_IDS);
    }

    @Test
    @Transactional
    public void createMAssetTagMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mAssetTagMappingRepository.findAll().size();

        // Create the MAssetTagMapping with an existing ID
        mAssetTagMapping.setId(1L);
        MAssetTagMappingDTO mAssetTagMappingDTO = mAssetTagMappingMapper.toDto(mAssetTagMapping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMAssetTagMappingMockMvc.perform(post("/api/m-asset-tag-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetTagMappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAssetTagMapping in the database
        List<MAssetTagMapping> mAssetTagMappingList = mAssetTagMappingRepository.findAll();
        assertThat(mAssetTagMappingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTagIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAssetTagMappingRepository.findAll().size();
        // set the field null
        mAssetTagMapping.setTag(null);

        // Create the MAssetTagMapping, which fails.
        MAssetTagMappingDTO mAssetTagMappingDTO = mAssetTagMappingMapper.toDto(mAssetTagMapping);

        restMAssetTagMappingMockMvc.perform(post("/api/m-asset-tag-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetTagMappingDTO)))
            .andExpect(status().isBadRequest());

        List<MAssetTagMapping> mAssetTagMappingList = mAssetTagMappingRepository.findAll();
        assertThat(mAssetTagMappingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMAssetTagMappings() throws Exception {
        // Initialize the database
        mAssetTagMappingRepository.saveAndFlush(mAssetTagMapping);

        // Get all the mAssetTagMappingList
        restMAssetTagMappingMockMvc.perform(get("/api/m-asset-tag-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAssetTagMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].ids").value(hasItem(DEFAULT_IDS.toString())));
    }
    
    @Test
    @Transactional
    public void getMAssetTagMapping() throws Exception {
        // Initialize the database
        mAssetTagMappingRepository.saveAndFlush(mAssetTagMapping);

        // Get the mAssetTagMapping
        restMAssetTagMappingMockMvc.perform(get("/api/m-asset-tag-mappings/{id}", mAssetTagMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAssetTagMapping.getId().intValue()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG))
            .andExpect(jsonPath("$.ids").value(DEFAULT_IDS.toString()));
    }

    @Test
    @Transactional
    public void getAllMAssetTagMappingsByTagIsEqualToSomething() throws Exception {
        // Initialize the database
        mAssetTagMappingRepository.saveAndFlush(mAssetTagMapping);

        // Get all the mAssetTagMappingList where tag equals to DEFAULT_TAG
        defaultMAssetTagMappingShouldBeFound("tag.equals=" + DEFAULT_TAG);

        // Get all the mAssetTagMappingList where tag equals to UPDATED_TAG
        defaultMAssetTagMappingShouldNotBeFound("tag.equals=" + UPDATED_TAG);
    }

    @Test
    @Transactional
    public void getAllMAssetTagMappingsByTagIsInShouldWork() throws Exception {
        // Initialize the database
        mAssetTagMappingRepository.saveAndFlush(mAssetTagMapping);

        // Get all the mAssetTagMappingList where tag in DEFAULT_TAG or UPDATED_TAG
        defaultMAssetTagMappingShouldBeFound("tag.in=" + DEFAULT_TAG + "," + UPDATED_TAG);

        // Get all the mAssetTagMappingList where tag equals to UPDATED_TAG
        defaultMAssetTagMappingShouldNotBeFound("tag.in=" + UPDATED_TAG);
    }

    @Test
    @Transactional
    public void getAllMAssetTagMappingsByTagIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAssetTagMappingRepository.saveAndFlush(mAssetTagMapping);

        // Get all the mAssetTagMappingList where tag is not null
        defaultMAssetTagMappingShouldBeFound("tag.specified=true");

        // Get all the mAssetTagMappingList where tag is null
        defaultMAssetTagMappingShouldNotBeFound("tag.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAssetTagMappingsByTagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAssetTagMappingRepository.saveAndFlush(mAssetTagMapping);

        // Get all the mAssetTagMappingList where tag greater than or equals to DEFAULT_TAG
        defaultMAssetTagMappingShouldBeFound("tag.greaterOrEqualThan=" + DEFAULT_TAG);

        // Get all the mAssetTagMappingList where tag greater than or equals to UPDATED_TAG
        defaultMAssetTagMappingShouldNotBeFound("tag.greaterOrEqualThan=" + UPDATED_TAG);
    }

    @Test
    @Transactional
    public void getAllMAssetTagMappingsByTagIsLessThanSomething() throws Exception {
        // Initialize the database
        mAssetTagMappingRepository.saveAndFlush(mAssetTagMapping);

        // Get all the mAssetTagMappingList where tag less than or equals to DEFAULT_TAG
        defaultMAssetTagMappingShouldNotBeFound("tag.lessThan=" + DEFAULT_TAG);

        // Get all the mAssetTagMappingList where tag less than or equals to UPDATED_TAG
        defaultMAssetTagMappingShouldBeFound("tag.lessThan=" + UPDATED_TAG);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMAssetTagMappingShouldBeFound(String filter) throws Exception {
        restMAssetTagMappingMockMvc.perform(get("/api/m-asset-tag-mappings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAssetTagMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].ids").value(hasItem(DEFAULT_IDS.toString())));

        // Check, that the count call also returns 1
        restMAssetTagMappingMockMvc.perform(get("/api/m-asset-tag-mappings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMAssetTagMappingShouldNotBeFound(String filter) throws Exception {
        restMAssetTagMappingMockMvc.perform(get("/api/m-asset-tag-mappings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMAssetTagMappingMockMvc.perform(get("/api/m-asset-tag-mappings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAssetTagMapping() throws Exception {
        // Get the mAssetTagMapping
        restMAssetTagMappingMockMvc.perform(get("/api/m-asset-tag-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAssetTagMapping() throws Exception {
        // Initialize the database
        mAssetTagMappingRepository.saveAndFlush(mAssetTagMapping);

        int databaseSizeBeforeUpdate = mAssetTagMappingRepository.findAll().size();

        // Update the mAssetTagMapping
        MAssetTagMapping updatedMAssetTagMapping = mAssetTagMappingRepository.findById(mAssetTagMapping.getId()).get();
        // Disconnect from session so that the updates on updatedMAssetTagMapping are not directly saved in db
        em.detach(updatedMAssetTagMapping);
        updatedMAssetTagMapping
            .tag(UPDATED_TAG)
            .ids(UPDATED_IDS);
        MAssetTagMappingDTO mAssetTagMappingDTO = mAssetTagMappingMapper.toDto(updatedMAssetTagMapping);

        restMAssetTagMappingMockMvc.perform(put("/api/m-asset-tag-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetTagMappingDTO)))
            .andExpect(status().isOk());

        // Validate the MAssetTagMapping in the database
        List<MAssetTagMapping> mAssetTagMappingList = mAssetTagMappingRepository.findAll();
        assertThat(mAssetTagMappingList).hasSize(databaseSizeBeforeUpdate);
        MAssetTagMapping testMAssetTagMapping = mAssetTagMappingList.get(mAssetTagMappingList.size() - 1);
        assertThat(testMAssetTagMapping.getTag()).isEqualTo(UPDATED_TAG);
        assertThat(testMAssetTagMapping.getIds()).isEqualTo(UPDATED_IDS);
    }

    @Test
    @Transactional
    public void updateNonExistingMAssetTagMapping() throws Exception {
        int databaseSizeBeforeUpdate = mAssetTagMappingRepository.findAll().size();

        // Create the MAssetTagMapping
        MAssetTagMappingDTO mAssetTagMappingDTO = mAssetTagMappingMapper.toDto(mAssetTagMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMAssetTagMappingMockMvc.perform(put("/api/m-asset-tag-mappings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAssetTagMappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAssetTagMapping in the database
        List<MAssetTagMapping> mAssetTagMappingList = mAssetTagMappingRepository.findAll();
        assertThat(mAssetTagMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAssetTagMapping() throws Exception {
        // Initialize the database
        mAssetTagMappingRepository.saveAndFlush(mAssetTagMapping);

        int databaseSizeBeforeDelete = mAssetTagMappingRepository.findAll().size();

        // Delete the mAssetTagMapping
        restMAssetTagMappingMockMvc.perform(delete("/api/m-asset-tag-mappings/{id}", mAssetTagMapping.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAssetTagMapping> mAssetTagMappingList = mAssetTagMappingRepository.findAll();
        assertThat(mAssetTagMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAssetTagMapping.class);
        MAssetTagMapping mAssetTagMapping1 = new MAssetTagMapping();
        mAssetTagMapping1.setId(1L);
        MAssetTagMapping mAssetTagMapping2 = new MAssetTagMapping();
        mAssetTagMapping2.setId(mAssetTagMapping1.getId());
        assertThat(mAssetTagMapping1).isEqualTo(mAssetTagMapping2);
        mAssetTagMapping2.setId(2L);
        assertThat(mAssetTagMapping1).isNotEqualTo(mAssetTagMapping2);
        mAssetTagMapping1.setId(null);
        assertThat(mAssetTagMapping1).isNotEqualTo(mAssetTagMapping2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAssetTagMappingDTO.class);
        MAssetTagMappingDTO mAssetTagMappingDTO1 = new MAssetTagMappingDTO();
        mAssetTagMappingDTO1.setId(1L);
        MAssetTagMappingDTO mAssetTagMappingDTO2 = new MAssetTagMappingDTO();
        assertThat(mAssetTagMappingDTO1).isNotEqualTo(mAssetTagMappingDTO2);
        mAssetTagMappingDTO2.setId(mAssetTagMappingDTO1.getId());
        assertThat(mAssetTagMappingDTO1).isEqualTo(mAssetTagMappingDTO2);
        mAssetTagMappingDTO2.setId(2L);
        assertThat(mAssetTagMappingDTO1).isNotEqualTo(mAssetTagMappingDTO2);
        mAssetTagMappingDTO1.setId(null);
        assertThat(mAssetTagMappingDTO1).isNotEqualTo(mAssetTagMappingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mAssetTagMappingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mAssetTagMappingMapper.fromId(null)).isNull();
    }
}
