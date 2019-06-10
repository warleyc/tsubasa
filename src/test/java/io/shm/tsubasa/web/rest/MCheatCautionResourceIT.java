package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCheatCaution;
import io.shm.tsubasa.repository.MCheatCautionRepository;
import io.shm.tsubasa.service.MCheatCautionService;
import io.shm.tsubasa.service.dto.MCheatCautionDTO;
import io.shm.tsubasa.service.mapper.MCheatCautionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCheatCautionCriteria;
import io.shm.tsubasa.service.MCheatCautionQueryService;

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
 * Integration tests for the {@Link MCheatCautionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCheatCautionResourceIT {

    private static final Integer DEFAULT_CAUTION = 1;
    private static final Integer UPDATED_CAUTION = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_ASSET_NAME = "BBBBBBBBBB";

    @Autowired
    private MCheatCautionRepository mCheatCautionRepository;

    @Autowired
    private MCheatCautionMapper mCheatCautionMapper;

    @Autowired
    private MCheatCautionService mCheatCautionService;

    @Autowired
    private MCheatCautionQueryService mCheatCautionQueryService;

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

    private MockMvc restMCheatCautionMockMvc;

    private MCheatCaution mCheatCaution;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCheatCautionResource mCheatCautionResource = new MCheatCautionResource(mCheatCautionService, mCheatCautionQueryService);
        this.restMCheatCautionMockMvc = MockMvcBuilders.standaloneSetup(mCheatCautionResource)
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
    public static MCheatCaution createEntity(EntityManager em) {
        MCheatCaution mCheatCaution = new MCheatCaution()
            .caution(DEFAULT_CAUTION)
            .description(DEFAULT_DESCRIPTION)
            .imageAssetName(DEFAULT_IMAGE_ASSET_NAME);
        return mCheatCaution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCheatCaution createUpdatedEntity(EntityManager em) {
        MCheatCaution mCheatCaution = new MCheatCaution()
            .caution(UPDATED_CAUTION)
            .description(UPDATED_DESCRIPTION)
            .imageAssetName(UPDATED_IMAGE_ASSET_NAME);
        return mCheatCaution;
    }

    @BeforeEach
    public void initTest() {
        mCheatCaution = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCheatCaution() throws Exception {
        int databaseSizeBeforeCreate = mCheatCautionRepository.findAll().size();

        // Create the MCheatCaution
        MCheatCautionDTO mCheatCautionDTO = mCheatCautionMapper.toDto(mCheatCaution);
        restMCheatCautionMockMvc.perform(post("/api/m-cheat-cautions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCheatCautionDTO)))
            .andExpect(status().isCreated());

        // Validate the MCheatCaution in the database
        List<MCheatCaution> mCheatCautionList = mCheatCautionRepository.findAll();
        assertThat(mCheatCautionList).hasSize(databaseSizeBeforeCreate + 1);
        MCheatCaution testMCheatCaution = mCheatCautionList.get(mCheatCautionList.size() - 1);
        assertThat(testMCheatCaution.getCaution()).isEqualTo(DEFAULT_CAUTION);
        assertThat(testMCheatCaution.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMCheatCaution.getImageAssetName()).isEqualTo(DEFAULT_IMAGE_ASSET_NAME);
    }

    @Test
    @Transactional
    public void createMCheatCautionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCheatCautionRepository.findAll().size();

        // Create the MCheatCaution with an existing ID
        mCheatCaution.setId(1L);
        MCheatCautionDTO mCheatCautionDTO = mCheatCautionMapper.toDto(mCheatCaution);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCheatCautionMockMvc.perform(post("/api/m-cheat-cautions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCheatCautionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCheatCaution in the database
        List<MCheatCaution> mCheatCautionList = mCheatCautionRepository.findAll();
        assertThat(mCheatCautionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCautionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCheatCautionRepository.findAll().size();
        // set the field null
        mCheatCaution.setCaution(null);

        // Create the MCheatCaution, which fails.
        MCheatCautionDTO mCheatCautionDTO = mCheatCautionMapper.toDto(mCheatCaution);

        restMCheatCautionMockMvc.perform(post("/api/m-cheat-cautions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCheatCautionDTO)))
            .andExpect(status().isBadRequest());

        List<MCheatCaution> mCheatCautionList = mCheatCautionRepository.findAll();
        assertThat(mCheatCautionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCheatCautions() throws Exception {
        // Initialize the database
        mCheatCautionRepository.saveAndFlush(mCheatCaution);

        // Get all the mCheatCautionList
        restMCheatCautionMockMvc.perform(get("/api/m-cheat-cautions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCheatCaution.getId().intValue())))
            .andExpect(jsonPath("$.[*].caution").value(hasItem(DEFAULT_CAUTION)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].imageAssetName").value(hasItem(DEFAULT_IMAGE_ASSET_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMCheatCaution() throws Exception {
        // Initialize the database
        mCheatCautionRepository.saveAndFlush(mCheatCaution);

        // Get the mCheatCaution
        restMCheatCautionMockMvc.perform(get("/api/m-cheat-cautions/{id}", mCheatCaution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCheatCaution.getId().intValue()))
            .andExpect(jsonPath("$.caution").value(DEFAULT_CAUTION))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.imageAssetName").value(DEFAULT_IMAGE_ASSET_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMCheatCautionsByCautionIsEqualToSomething() throws Exception {
        // Initialize the database
        mCheatCautionRepository.saveAndFlush(mCheatCaution);

        // Get all the mCheatCautionList where caution equals to DEFAULT_CAUTION
        defaultMCheatCautionShouldBeFound("caution.equals=" + DEFAULT_CAUTION);

        // Get all the mCheatCautionList where caution equals to UPDATED_CAUTION
        defaultMCheatCautionShouldNotBeFound("caution.equals=" + UPDATED_CAUTION);
    }

    @Test
    @Transactional
    public void getAllMCheatCautionsByCautionIsInShouldWork() throws Exception {
        // Initialize the database
        mCheatCautionRepository.saveAndFlush(mCheatCaution);

        // Get all the mCheatCautionList where caution in DEFAULT_CAUTION or UPDATED_CAUTION
        defaultMCheatCautionShouldBeFound("caution.in=" + DEFAULT_CAUTION + "," + UPDATED_CAUTION);

        // Get all the mCheatCautionList where caution equals to UPDATED_CAUTION
        defaultMCheatCautionShouldNotBeFound("caution.in=" + UPDATED_CAUTION);
    }

    @Test
    @Transactional
    public void getAllMCheatCautionsByCautionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCheatCautionRepository.saveAndFlush(mCheatCaution);

        // Get all the mCheatCautionList where caution is not null
        defaultMCheatCautionShouldBeFound("caution.specified=true");

        // Get all the mCheatCautionList where caution is null
        defaultMCheatCautionShouldNotBeFound("caution.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCheatCautionsByCautionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCheatCautionRepository.saveAndFlush(mCheatCaution);

        // Get all the mCheatCautionList where caution greater than or equals to DEFAULT_CAUTION
        defaultMCheatCautionShouldBeFound("caution.greaterOrEqualThan=" + DEFAULT_CAUTION);

        // Get all the mCheatCautionList where caution greater than or equals to UPDATED_CAUTION
        defaultMCheatCautionShouldNotBeFound("caution.greaterOrEqualThan=" + UPDATED_CAUTION);
    }

    @Test
    @Transactional
    public void getAllMCheatCautionsByCautionIsLessThanSomething() throws Exception {
        // Initialize the database
        mCheatCautionRepository.saveAndFlush(mCheatCaution);

        // Get all the mCheatCautionList where caution less than or equals to DEFAULT_CAUTION
        defaultMCheatCautionShouldNotBeFound("caution.lessThan=" + DEFAULT_CAUTION);

        // Get all the mCheatCautionList where caution less than or equals to UPDATED_CAUTION
        defaultMCheatCautionShouldBeFound("caution.lessThan=" + UPDATED_CAUTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCheatCautionShouldBeFound(String filter) throws Exception {
        restMCheatCautionMockMvc.perform(get("/api/m-cheat-cautions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCheatCaution.getId().intValue())))
            .andExpect(jsonPath("$.[*].caution").value(hasItem(DEFAULT_CAUTION)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].imageAssetName").value(hasItem(DEFAULT_IMAGE_ASSET_NAME.toString())));

        // Check, that the count call also returns 1
        restMCheatCautionMockMvc.perform(get("/api/m-cheat-cautions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCheatCautionShouldNotBeFound(String filter) throws Exception {
        restMCheatCautionMockMvc.perform(get("/api/m-cheat-cautions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCheatCautionMockMvc.perform(get("/api/m-cheat-cautions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCheatCaution() throws Exception {
        // Get the mCheatCaution
        restMCheatCautionMockMvc.perform(get("/api/m-cheat-cautions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCheatCaution() throws Exception {
        // Initialize the database
        mCheatCautionRepository.saveAndFlush(mCheatCaution);

        int databaseSizeBeforeUpdate = mCheatCautionRepository.findAll().size();

        // Update the mCheatCaution
        MCheatCaution updatedMCheatCaution = mCheatCautionRepository.findById(mCheatCaution.getId()).get();
        // Disconnect from session so that the updates on updatedMCheatCaution are not directly saved in db
        em.detach(updatedMCheatCaution);
        updatedMCheatCaution
            .caution(UPDATED_CAUTION)
            .description(UPDATED_DESCRIPTION)
            .imageAssetName(UPDATED_IMAGE_ASSET_NAME);
        MCheatCautionDTO mCheatCautionDTO = mCheatCautionMapper.toDto(updatedMCheatCaution);

        restMCheatCautionMockMvc.perform(put("/api/m-cheat-cautions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCheatCautionDTO)))
            .andExpect(status().isOk());

        // Validate the MCheatCaution in the database
        List<MCheatCaution> mCheatCautionList = mCheatCautionRepository.findAll();
        assertThat(mCheatCautionList).hasSize(databaseSizeBeforeUpdate);
        MCheatCaution testMCheatCaution = mCheatCautionList.get(mCheatCautionList.size() - 1);
        assertThat(testMCheatCaution.getCaution()).isEqualTo(UPDATED_CAUTION);
        assertThat(testMCheatCaution.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMCheatCaution.getImageAssetName()).isEqualTo(UPDATED_IMAGE_ASSET_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMCheatCaution() throws Exception {
        int databaseSizeBeforeUpdate = mCheatCautionRepository.findAll().size();

        // Create the MCheatCaution
        MCheatCautionDTO mCheatCautionDTO = mCheatCautionMapper.toDto(mCheatCaution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCheatCautionMockMvc.perform(put("/api/m-cheat-cautions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCheatCautionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCheatCaution in the database
        List<MCheatCaution> mCheatCautionList = mCheatCautionRepository.findAll();
        assertThat(mCheatCautionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCheatCaution() throws Exception {
        // Initialize the database
        mCheatCautionRepository.saveAndFlush(mCheatCaution);

        int databaseSizeBeforeDelete = mCheatCautionRepository.findAll().size();

        // Delete the mCheatCaution
        restMCheatCautionMockMvc.perform(delete("/api/m-cheat-cautions/{id}", mCheatCaution.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCheatCaution> mCheatCautionList = mCheatCautionRepository.findAll();
        assertThat(mCheatCautionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCheatCaution.class);
        MCheatCaution mCheatCaution1 = new MCheatCaution();
        mCheatCaution1.setId(1L);
        MCheatCaution mCheatCaution2 = new MCheatCaution();
        mCheatCaution2.setId(mCheatCaution1.getId());
        assertThat(mCheatCaution1).isEqualTo(mCheatCaution2);
        mCheatCaution2.setId(2L);
        assertThat(mCheatCaution1).isNotEqualTo(mCheatCaution2);
        mCheatCaution1.setId(null);
        assertThat(mCheatCaution1).isNotEqualTo(mCheatCaution2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCheatCautionDTO.class);
        MCheatCautionDTO mCheatCautionDTO1 = new MCheatCautionDTO();
        mCheatCautionDTO1.setId(1L);
        MCheatCautionDTO mCheatCautionDTO2 = new MCheatCautionDTO();
        assertThat(mCheatCautionDTO1).isNotEqualTo(mCheatCautionDTO2);
        mCheatCautionDTO2.setId(mCheatCautionDTO1.getId());
        assertThat(mCheatCautionDTO1).isEqualTo(mCheatCautionDTO2);
        mCheatCautionDTO2.setId(2L);
        assertThat(mCheatCautionDTO1).isNotEqualTo(mCheatCautionDTO2);
        mCheatCautionDTO1.setId(null);
        assertThat(mCheatCautionDTO1).isNotEqualTo(mCheatCautionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCheatCautionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCheatCautionMapper.fromId(null)).isNull();
    }
}
