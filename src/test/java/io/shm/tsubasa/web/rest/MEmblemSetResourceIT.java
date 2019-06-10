package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MEmblemSet;
import io.shm.tsubasa.repository.MEmblemSetRepository;
import io.shm.tsubasa.service.MEmblemSetService;
import io.shm.tsubasa.service.dto.MEmblemSetDTO;
import io.shm.tsubasa.service.mapper.MEmblemSetMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MEmblemSetCriteria;
import io.shm.tsubasa.service.MEmblemSetQueryService;

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
 * Integration tests for the {@Link MEmblemSetResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MEmblemSetResourceIT {

    private static final String DEFAULT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MEmblemSetRepository mEmblemSetRepository;

    @Autowired
    private MEmblemSetMapper mEmblemSetMapper;

    @Autowired
    private MEmblemSetService mEmblemSetService;

    @Autowired
    private MEmblemSetQueryService mEmblemSetQueryService;

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

    private MockMvc restMEmblemSetMockMvc;

    private MEmblemSet mEmblemSet;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MEmblemSetResource mEmblemSetResource = new MEmblemSetResource(mEmblemSetService, mEmblemSetQueryService);
        this.restMEmblemSetMockMvc = MockMvcBuilders.standaloneSetup(mEmblemSetResource)
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
    public static MEmblemSet createEntity(EntityManager em) {
        MEmblemSet mEmblemSet = new MEmblemSet()
            .assetName(DEFAULT_ASSET_NAME)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return mEmblemSet;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MEmblemSet createUpdatedEntity(EntityManager em) {
        MEmblemSet mEmblemSet = new MEmblemSet()
            .assetName(UPDATED_ASSET_NAME)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return mEmblemSet;
    }

    @BeforeEach
    public void initTest() {
        mEmblemSet = createEntity(em);
    }

    @Test
    @Transactional
    public void createMEmblemSet() throws Exception {
        int databaseSizeBeforeCreate = mEmblemSetRepository.findAll().size();

        // Create the MEmblemSet
        MEmblemSetDTO mEmblemSetDTO = mEmblemSetMapper.toDto(mEmblemSet);
        restMEmblemSetMockMvc.perform(post("/api/m-emblem-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEmblemSetDTO)))
            .andExpect(status().isCreated());

        // Validate the MEmblemSet in the database
        List<MEmblemSet> mEmblemSetList = mEmblemSetRepository.findAll();
        assertThat(mEmblemSetList).hasSize(databaseSizeBeforeCreate + 1);
        MEmblemSet testMEmblemSet = mEmblemSetList.get(mEmblemSetList.size() - 1);
        assertThat(testMEmblemSet.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testMEmblemSet.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMEmblemSet.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMEmblemSetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mEmblemSetRepository.findAll().size();

        // Create the MEmblemSet with an existing ID
        mEmblemSet.setId(1L);
        MEmblemSetDTO mEmblemSetDTO = mEmblemSetMapper.toDto(mEmblemSet);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMEmblemSetMockMvc.perform(post("/api/m-emblem-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEmblemSetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEmblemSet in the database
        List<MEmblemSet> mEmblemSetList = mEmblemSetRepository.findAll();
        assertThat(mEmblemSetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMEmblemSets() throws Exception {
        // Initialize the database
        mEmblemSetRepository.saveAndFlush(mEmblemSet);

        // Get all the mEmblemSetList
        restMEmblemSetMockMvc.perform(get("/api/m-emblem-sets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEmblemSet.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMEmblemSet() throws Exception {
        // Initialize the database
        mEmblemSetRepository.saveAndFlush(mEmblemSet);

        // Get the mEmblemSet
        restMEmblemSetMockMvc.perform(get("/api/m-emblem-sets/{id}", mEmblemSet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mEmblemSet.getId().intValue()))
            .andExpect(jsonPath("$.assetName").value(DEFAULT_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMEmblemSetShouldBeFound(String filter) throws Exception {
        restMEmblemSetMockMvc.perform(get("/api/m-emblem-sets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEmblemSet.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMEmblemSetMockMvc.perform(get("/api/m-emblem-sets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMEmblemSetShouldNotBeFound(String filter) throws Exception {
        restMEmblemSetMockMvc.perform(get("/api/m-emblem-sets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMEmblemSetMockMvc.perform(get("/api/m-emblem-sets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMEmblemSet() throws Exception {
        // Get the mEmblemSet
        restMEmblemSetMockMvc.perform(get("/api/m-emblem-sets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMEmblemSet() throws Exception {
        // Initialize the database
        mEmblemSetRepository.saveAndFlush(mEmblemSet);

        int databaseSizeBeforeUpdate = mEmblemSetRepository.findAll().size();

        // Update the mEmblemSet
        MEmblemSet updatedMEmblemSet = mEmblemSetRepository.findById(mEmblemSet.getId()).get();
        // Disconnect from session so that the updates on updatedMEmblemSet are not directly saved in db
        em.detach(updatedMEmblemSet);
        updatedMEmblemSet
            .assetName(UPDATED_ASSET_NAME)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        MEmblemSetDTO mEmblemSetDTO = mEmblemSetMapper.toDto(updatedMEmblemSet);

        restMEmblemSetMockMvc.perform(put("/api/m-emblem-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEmblemSetDTO)))
            .andExpect(status().isOk());

        // Validate the MEmblemSet in the database
        List<MEmblemSet> mEmblemSetList = mEmblemSetRepository.findAll();
        assertThat(mEmblemSetList).hasSize(databaseSizeBeforeUpdate);
        MEmblemSet testMEmblemSet = mEmblemSetList.get(mEmblemSetList.size() - 1);
        assertThat(testMEmblemSet.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testMEmblemSet.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMEmblemSet.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMEmblemSet() throws Exception {
        int databaseSizeBeforeUpdate = mEmblemSetRepository.findAll().size();

        // Create the MEmblemSet
        MEmblemSetDTO mEmblemSetDTO = mEmblemSetMapper.toDto(mEmblemSet);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMEmblemSetMockMvc.perform(put("/api/m-emblem-sets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEmblemSetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEmblemSet in the database
        List<MEmblemSet> mEmblemSetList = mEmblemSetRepository.findAll();
        assertThat(mEmblemSetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMEmblemSet() throws Exception {
        // Initialize the database
        mEmblemSetRepository.saveAndFlush(mEmblemSet);

        int databaseSizeBeforeDelete = mEmblemSetRepository.findAll().size();

        // Delete the mEmblemSet
        restMEmblemSetMockMvc.perform(delete("/api/m-emblem-sets/{id}", mEmblemSet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MEmblemSet> mEmblemSetList = mEmblemSetRepository.findAll();
        assertThat(mEmblemSetList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEmblemSet.class);
        MEmblemSet mEmblemSet1 = new MEmblemSet();
        mEmblemSet1.setId(1L);
        MEmblemSet mEmblemSet2 = new MEmblemSet();
        mEmblemSet2.setId(mEmblemSet1.getId());
        assertThat(mEmblemSet1).isEqualTo(mEmblemSet2);
        mEmblemSet2.setId(2L);
        assertThat(mEmblemSet1).isNotEqualTo(mEmblemSet2);
        mEmblemSet1.setId(null);
        assertThat(mEmblemSet1).isNotEqualTo(mEmblemSet2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEmblemSetDTO.class);
        MEmblemSetDTO mEmblemSetDTO1 = new MEmblemSetDTO();
        mEmblemSetDTO1.setId(1L);
        MEmblemSetDTO mEmblemSetDTO2 = new MEmblemSetDTO();
        assertThat(mEmblemSetDTO1).isNotEqualTo(mEmblemSetDTO2);
        mEmblemSetDTO2.setId(mEmblemSetDTO1.getId());
        assertThat(mEmblemSetDTO1).isEqualTo(mEmblemSetDTO2);
        mEmblemSetDTO2.setId(2L);
        assertThat(mEmblemSetDTO1).isNotEqualTo(mEmblemSetDTO2);
        mEmblemSetDTO1.setId(null);
        assertThat(mEmblemSetDTO1).isNotEqualTo(mEmblemSetDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mEmblemSetMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mEmblemSetMapper.fromId(null)).isNull();
    }
}
