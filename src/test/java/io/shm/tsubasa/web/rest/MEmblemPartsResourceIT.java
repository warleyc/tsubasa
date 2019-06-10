package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MEmblemParts;
import io.shm.tsubasa.domain.MDummyEmblem;
import io.shm.tsubasa.repository.MEmblemPartsRepository;
import io.shm.tsubasa.service.MEmblemPartsService;
import io.shm.tsubasa.service.dto.MEmblemPartsDTO;
import io.shm.tsubasa.service.mapper.MEmblemPartsMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MEmblemPartsCriteria;
import io.shm.tsubasa.service.MEmblemPartsQueryService;

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
 * Integration tests for the {@Link MEmblemPartsResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MEmblemPartsResourceIT {

    private static final String DEFAULT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PARTS_TYPE = 1;
    private static final Integer UPDATED_PARTS_TYPE = 2;

    @Autowired
    private MEmblemPartsRepository mEmblemPartsRepository;

    @Autowired
    private MEmblemPartsMapper mEmblemPartsMapper;

    @Autowired
    private MEmblemPartsService mEmblemPartsService;

    @Autowired
    private MEmblemPartsQueryService mEmblemPartsQueryService;

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

    private MockMvc restMEmblemPartsMockMvc;

    private MEmblemParts mEmblemParts;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MEmblemPartsResource mEmblemPartsResource = new MEmblemPartsResource(mEmblemPartsService, mEmblemPartsQueryService);
        this.restMEmblemPartsMockMvc = MockMvcBuilders.standaloneSetup(mEmblemPartsResource)
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
    public static MEmblemParts createEntity(EntityManager em) {
        MEmblemParts mEmblemParts = new MEmblemParts()
            .assetName(DEFAULT_ASSET_NAME)
            .partsType(DEFAULT_PARTS_TYPE);
        return mEmblemParts;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MEmblemParts createUpdatedEntity(EntityManager em) {
        MEmblemParts mEmblemParts = new MEmblemParts()
            .assetName(UPDATED_ASSET_NAME)
            .partsType(UPDATED_PARTS_TYPE);
        return mEmblemParts;
    }

    @BeforeEach
    public void initTest() {
        mEmblemParts = createEntity(em);
    }

    @Test
    @Transactional
    public void createMEmblemParts() throws Exception {
        int databaseSizeBeforeCreate = mEmblemPartsRepository.findAll().size();

        // Create the MEmblemParts
        MEmblemPartsDTO mEmblemPartsDTO = mEmblemPartsMapper.toDto(mEmblemParts);
        restMEmblemPartsMockMvc.perform(post("/api/m-emblem-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEmblemPartsDTO)))
            .andExpect(status().isCreated());

        // Validate the MEmblemParts in the database
        List<MEmblemParts> mEmblemPartsList = mEmblemPartsRepository.findAll();
        assertThat(mEmblemPartsList).hasSize(databaseSizeBeforeCreate + 1);
        MEmblemParts testMEmblemParts = mEmblemPartsList.get(mEmblemPartsList.size() - 1);
        assertThat(testMEmblemParts.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testMEmblemParts.getPartsType()).isEqualTo(DEFAULT_PARTS_TYPE);
    }

    @Test
    @Transactional
    public void createMEmblemPartsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mEmblemPartsRepository.findAll().size();

        // Create the MEmblemParts with an existing ID
        mEmblemParts.setId(1L);
        MEmblemPartsDTO mEmblemPartsDTO = mEmblemPartsMapper.toDto(mEmblemParts);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMEmblemPartsMockMvc.perform(post("/api/m-emblem-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEmblemPartsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEmblemParts in the database
        List<MEmblemParts> mEmblemPartsList = mEmblemPartsRepository.findAll();
        assertThat(mEmblemPartsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPartsTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEmblemPartsRepository.findAll().size();
        // set the field null
        mEmblemParts.setPartsType(null);

        // Create the MEmblemParts, which fails.
        MEmblemPartsDTO mEmblemPartsDTO = mEmblemPartsMapper.toDto(mEmblemParts);

        restMEmblemPartsMockMvc.perform(post("/api/m-emblem-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEmblemPartsDTO)))
            .andExpect(status().isBadRequest());

        List<MEmblemParts> mEmblemPartsList = mEmblemPartsRepository.findAll();
        assertThat(mEmblemPartsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMEmblemParts() throws Exception {
        // Initialize the database
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);

        // Get all the mEmblemPartsList
        restMEmblemPartsMockMvc.perform(get("/api/m-emblem-parts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEmblemParts.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].partsType").value(hasItem(DEFAULT_PARTS_TYPE)));
    }
    
    @Test
    @Transactional
    public void getMEmblemParts() throws Exception {
        // Initialize the database
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);

        // Get the mEmblemParts
        restMEmblemPartsMockMvc.perform(get("/api/m-emblem-parts/{id}", mEmblemParts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mEmblemParts.getId().intValue()))
            .andExpect(jsonPath("$.assetName").value(DEFAULT_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.partsType").value(DEFAULT_PARTS_TYPE));
    }

    @Test
    @Transactional
    public void getAllMEmblemPartsByPartsTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);

        // Get all the mEmblemPartsList where partsType equals to DEFAULT_PARTS_TYPE
        defaultMEmblemPartsShouldBeFound("partsType.equals=" + DEFAULT_PARTS_TYPE);

        // Get all the mEmblemPartsList where partsType equals to UPDATED_PARTS_TYPE
        defaultMEmblemPartsShouldNotBeFound("partsType.equals=" + UPDATED_PARTS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEmblemPartsByPartsTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);

        // Get all the mEmblemPartsList where partsType in DEFAULT_PARTS_TYPE or UPDATED_PARTS_TYPE
        defaultMEmblemPartsShouldBeFound("partsType.in=" + DEFAULT_PARTS_TYPE + "," + UPDATED_PARTS_TYPE);

        // Get all the mEmblemPartsList where partsType equals to UPDATED_PARTS_TYPE
        defaultMEmblemPartsShouldNotBeFound("partsType.in=" + UPDATED_PARTS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEmblemPartsByPartsTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);

        // Get all the mEmblemPartsList where partsType is not null
        defaultMEmblemPartsShouldBeFound("partsType.specified=true");

        // Get all the mEmblemPartsList where partsType is null
        defaultMEmblemPartsShouldNotBeFound("partsType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEmblemPartsByPartsTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);

        // Get all the mEmblemPartsList where partsType greater than or equals to DEFAULT_PARTS_TYPE
        defaultMEmblemPartsShouldBeFound("partsType.greaterOrEqualThan=" + DEFAULT_PARTS_TYPE);

        // Get all the mEmblemPartsList where partsType greater than or equals to UPDATED_PARTS_TYPE
        defaultMEmblemPartsShouldNotBeFound("partsType.greaterOrEqualThan=" + UPDATED_PARTS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMEmblemPartsByPartsTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);

        // Get all the mEmblemPartsList where partsType less than or equals to DEFAULT_PARTS_TYPE
        defaultMEmblemPartsShouldNotBeFound("partsType.lessThan=" + DEFAULT_PARTS_TYPE);

        // Get all the mEmblemPartsList where partsType less than or equals to UPDATED_PARTS_TYPE
        defaultMEmblemPartsShouldBeFound("partsType.lessThan=" + UPDATED_PARTS_TYPE);
    }


    @Test
    @Transactional
    public void getAllMEmblemPartsByMDummyEmblemIsEqualToSomething() throws Exception {
        // Initialize the database
        MDummyEmblem mDummyEmblem = MDummyEmblemResourceIT.createEntity(em);
        em.persist(mDummyEmblem);
        em.flush();
        mEmblemParts.addMDummyEmblem(mDummyEmblem);
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);
        Long mDummyEmblemId = mDummyEmblem.getId();

        // Get all the mEmblemPartsList where mDummyEmblem equals to mDummyEmblemId
        defaultMEmblemPartsShouldBeFound("mDummyEmblemId.equals=" + mDummyEmblemId);

        // Get all the mEmblemPartsList where mDummyEmblem equals to mDummyEmblemId + 1
        defaultMEmblemPartsShouldNotBeFound("mDummyEmblemId.equals=" + (mDummyEmblemId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMEmblemPartsShouldBeFound(String filter) throws Exception {
        restMEmblemPartsMockMvc.perform(get("/api/m-emblem-parts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEmblemParts.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].partsType").value(hasItem(DEFAULT_PARTS_TYPE)));

        // Check, that the count call also returns 1
        restMEmblemPartsMockMvc.perform(get("/api/m-emblem-parts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMEmblemPartsShouldNotBeFound(String filter) throws Exception {
        restMEmblemPartsMockMvc.perform(get("/api/m-emblem-parts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMEmblemPartsMockMvc.perform(get("/api/m-emblem-parts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMEmblemParts() throws Exception {
        // Get the mEmblemParts
        restMEmblemPartsMockMvc.perform(get("/api/m-emblem-parts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMEmblemParts() throws Exception {
        // Initialize the database
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);

        int databaseSizeBeforeUpdate = mEmblemPartsRepository.findAll().size();

        // Update the mEmblemParts
        MEmblemParts updatedMEmblemParts = mEmblemPartsRepository.findById(mEmblemParts.getId()).get();
        // Disconnect from session so that the updates on updatedMEmblemParts are not directly saved in db
        em.detach(updatedMEmblemParts);
        updatedMEmblemParts
            .assetName(UPDATED_ASSET_NAME)
            .partsType(UPDATED_PARTS_TYPE);
        MEmblemPartsDTO mEmblemPartsDTO = mEmblemPartsMapper.toDto(updatedMEmblemParts);

        restMEmblemPartsMockMvc.perform(put("/api/m-emblem-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEmblemPartsDTO)))
            .andExpect(status().isOk());

        // Validate the MEmblemParts in the database
        List<MEmblemParts> mEmblemPartsList = mEmblemPartsRepository.findAll();
        assertThat(mEmblemPartsList).hasSize(databaseSizeBeforeUpdate);
        MEmblemParts testMEmblemParts = mEmblemPartsList.get(mEmblemPartsList.size() - 1);
        assertThat(testMEmblemParts.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testMEmblemParts.getPartsType()).isEqualTo(UPDATED_PARTS_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMEmblemParts() throws Exception {
        int databaseSizeBeforeUpdate = mEmblemPartsRepository.findAll().size();

        // Create the MEmblemParts
        MEmblemPartsDTO mEmblemPartsDTO = mEmblemPartsMapper.toDto(mEmblemParts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMEmblemPartsMockMvc.perform(put("/api/m-emblem-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEmblemPartsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEmblemParts in the database
        List<MEmblemParts> mEmblemPartsList = mEmblemPartsRepository.findAll();
        assertThat(mEmblemPartsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMEmblemParts() throws Exception {
        // Initialize the database
        mEmblemPartsRepository.saveAndFlush(mEmblemParts);

        int databaseSizeBeforeDelete = mEmblemPartsRepository.findAll().size();

        // Delete the mEmblemParts
        restMEmblemPartsMockMvc.perform(delete("/api/m-emblem-parts/{id}", mEmblemParts.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MEmblemParts> mEmblemPartsList = mEmblemPartsRepository.findAll();
        assertThat(mEmblemPartsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEmblemParts.class);
        MEmblemParts mEmblemParts1 = new MEmblemParts();
        mEmblemParts1.setId(1L);
        MEmblemParts mEmblemParts2 = new MEmblemParts();
        mEmblemParts2.setId(mEmblemParts1.getId());
        assertThat(mEmblemParts1).isEqualTo(mEmblemParts2);
        mEmblemParts2.setId(2L);
        assertThat(mEmblemParts1).isNotEqualTo(mEmblemParts2);
        mEmblemParts1.setId(null);
        assertThat(mEmblemParts1).isNotEqualTo(mEmblemParts2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEmblemPartsDTO.class);
        MEmblemPartsDTO mEmblemPartsDTO1 = new MEmblemPartsDTO();
        mEmblemPartsDTO1.setId(1L);
        MEmblemPartsDTO mEmblemPartsDTO2 = new MEmblemPartsDTO();
        assertThat(mEmblemPartsDTO1).isNotEqualTo(mEmblemPartsDTO2);
        mEmblemPartsDTO2.setId(mEmblemPartsDTO1.getId());
        assertThat(mEmblemPartsDTO1).isEqualTo(mEmblemPartsDTO2);
        mEmblemPartsDTO2.setId(2L);
        assertThat(mEmblemPartsDTO1).isNotEqualTo(mEmblemPartsDTO2);
        mEmblemPartsDTO1.setId(null);
        assertThat(mEmblemPartsDTO1).isNotEqualTo(mEmblemPartsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mEmblemPartsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mEmblemPartsMapper.fromId(null)).isNull();
    }
}
