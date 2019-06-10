package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCardPlayableAssets;
import io.shm.tsubasa.repository.MCardPlayableAssetsRepository;
import io.shm.tsubasa.service.MCardPlayableAssetsService;
import io.shm.tsubasa.service.dto.MCardPlayableAssetsDTO;
import io.shm.tsubasa.service.mapper.MCardPlayableAssetsMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCardPlayableAssetsCriteria;
import io.shm.tsubasa.service.MCardPlayableAssetsQueryService;

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
 * Integration tests for the {@Link MCardPlayableAssetsResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCardPlayableAssetsResourceIT {

    private static final String DEFAULT_CUTIN_IMAGE_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUTIN_IMAGE_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOUND_EVENT_SUFFIX = "AAAAAAAAAA";
    private static final String UPDATED_SOUND_EVENT_SUFFIX = "BBBBBBBBBB";

    @Autowired
    private MCardPlayableAssetsRepository mCardPlayableAssetsRepository;

    @Autowired
    private MCardPlayableAssetsMapper mCardPlayableAssetsMapper;

    @Autowired
    private MCardPlayableAssetsService mCardPlayableAssetsService;

    @Autowired
    private MCardPlayableAssetsQueryService mCardPlayableAssetsQueryService;

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

    private MockMvc restMCardPlayableAssetsMockMvc;

    private MCardPlayableAssets mCardPlayableAssets;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCardPlayableAssetsResource mCardPlayableAssetsResource = new MCardPlayableAssetsResource(mCardPlayableAssetsService, mCardPlayableAssetsQueryService);
        this.restMCardPlayableAssetsMockMvc = MockMvcBuilders.standaloneSetup(mCardPlayableAssetsResource)
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
    public static MCardPlayableAssets createEntity(EntityManager em) {
        MCardPlayableAssets mCardPlayableAssets = new MCardPlayableAssets()
            .cutinImageAssetName(DEFAULT_CUTIN_IMAGE_ASSET_NAME)
            .soundEventSuffix(DEFAULT_SOUND_EVENT_SUFFIX);
        return mCardPlayableAssets;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCardPlayableAssets createUpdatedEntity(EntityManager em) {
        MCardPlayableAssets mCardPlayableAssets = new MCardPlayableAssets()
            .cutinImageAssetName(UPDATED_CUTIN_IMAGE_ASSET_NAME)
            .soundEventSuffix(UPDATED_SOUND_EVENT_SUFFIX);
        return mCardPlayableAssets;
    }

    @BeforeEach
    public void initTest() {
        mCardPlayableAssets = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCardPlayableAssets() throws Exception {
        int databaseSizeBeforeCreate = mCardPlayableAssetsRepository.findAll().size();

        // Create the MCardPlayableAssets
        MCardPlayableAssetsDTO mCardPlayableAssetsDTO = mCardPlayableAssetsMapper.toDto(mCardPlayableAssets);
        restMCardPlayableAssetsMockMvc.perform(post("/api/m-card-playable-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPlayableAssetsDTO)))
            .andExpect(status().isCreated());

        // Validate the MCardPlayableAssets in the database
        List<MCardPlayableAssets> mCardPlayableAssetsList = mCardPlayableAssetsRepository.findAll();
        assertThat(mCardPlayableAssetsList).hasSize(databaseSizeBeforeCreate + 1);
        MCardPlayableAssets testMCardPlayableAssets = mCardPlayableAssetsList.get(mCardPlayableAssetsList.size() - 1);
        assertThat(testMCardPlayableAssets.getCutinImageAssetName()).isEqualTo(DEFAULT_CUTIN_IMAGE_ASSET_NAME);
        assertThat(testMCardPlayableAssets.getSoundEventSuffix()).isEqualTo(DEFAULT_SOUND_EVENT_SUFFIX);
    }

    @Test
    @Transactional
    public void createMCardPlayableAssetsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCardPlayableAssetsRepository.findAll().size();

        // Create the MCardPlayableAssets with an existing ID
        mCardPlayableAssets.setId(1L);
        MCardPlayableAssetsDTO mCardPlayableAssetsDTO = mCardPlayableAssetsMapper.toDto(mCardPlayableAssets);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCardPlayableAssetsMockMvc.perform(post("/api/m-card-playable-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPlayableAssetsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardPlayableAssets in the database
        List<MCardPlayableAssets> mCardPlayableAssetsList = mCardPlayableAssetsRepository.findAll();
        assertThat(mCardPlayableAssetsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMCardPlayableAssets() throws Exception {
        // Initialize the database
        mCardPlayableAssetsRepository.saveAndFlush(mCardPlayableAssets);

        // Get all the mCardPlayableAssetsList
        restMCardPlayableAssetsMockMvc.perform(get("/api/m-card-playable-assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardPlayableAssets.getId().intValue())))
            .andExpect(jsonPath("$.[*].cutinImageAssetName").value(hasItem(DEFAULT_CUTIN_IMAGE_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].soundEventSuffix").value(hasItem(DEFAULT_SOUND_EVENT_SUFFIX.toString())));
    }
    
    @Test
    @Transactional
    public void getMCardPlayableAssets() throws Exception {
        // Initialize the database
        mCardPlayableAssetsRepository.saveAndFlush(mCardPlayableAssets);

        // Get the mCardPlayableAssets
        restMCardPlayableAssetsMockMvc.perform(get("/api/m-card-playable-assets/{id}", mCardPlayableAssets.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCardPlayableAssets.getId().intValue()))
            .andExpect(jsonPath("$.cutinImageAssetName").value(DEFAULT_CUTIN_IMAGE_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.soundEventSuffix").value(DEFAULT_SOUND_EVENT_SUFFIX.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCardPlayableAssetsShouldBeFound(String filter) throws Exception {
        restMCardPlayableAssetsMockMvc.perform(get("/api/m-card-playable-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardPlayableAssets.getId().intValue())))
            .andExpect(jsonPath("$.[*].cutinImageAssetName").value(hasItem(DEFAULT_CUTIN_IMAGE_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].soundEventSuffix").value(hasItem(DEFAULT_SOUND_EVENT_SUFFIX.toString())));

        // Check, that the count call also returns 1
        restMCardPlayableAssetsMockMvc.perform(get("/api/m-card-playable-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCardPlayableAssetsShouldNotBeFound(String filter) throws Exception {
        restMCardPlayableAssetsMockMvc.perform(get("/api/m-card-playable-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCardPlayableAssetsMockMvc.perform(get("/api/m-card-playable-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCardPlayableAssets() throws Exception {
        // Get the mCardPlayableAssets
        restMCardPlayableAssetsMockMvc.perform(get("/api/m-card-playable-assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCardPlayableAssets() throws Exception {
        // Initialize the database
        mCardPlayableAssetsRepository.saveAndFlush(mCardPlayableAssets);

        int databaseSizeBeforeUpdate = mCardPlayableAssetsRepository.findAll().size();

        // Update the mCardPlayableAssets
        MCardPlayableAssets updatedMCardPlayableAssets = mCardPlayableAssetsRepository.findById(mCardPlayableAssets.getId()).get();
        // Disconnect from session so that the updates on updatedMCardPlayableAssets are not directly saved in db
        em.detach(updatedMCardPlayableAssets);
        updatedMCardPlayableAssets
            .cutinImageAssetName(UPDATED_CUTIN_IMAGE_ASSET_NAME)
            .soundEventSuffix(UPDATED_SOUND_EVENT_SUFFIX);
        MCardPlayableAssetsDTO mCardPlayableAssetsDTO = mCardPlayableAssetsMapper.toDto(updatedMCardPlayableAssets);

        restMCardPlayableAssetsMockMvc.perform(put("/api/m-card-playable-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPlayableAssetsDTO)))
            .andExpect(status().isOk());

        // Validate the MCardPlayableAssets in the database
        List<MCardPlayableAssets> mCardPlayableAssetsList = mCardPlayableAssetsRepository.findAll();
        assertThat(mCardPlayableAssetsList).hasSize(databaseSizeBeforeUpdate);
        MCardPlayableAssets testMCardPlayableAssets = mCardPlayableAssetsList.get(mCardPlayableAssetsList.size() - 1);
        assertThat(testMCardPlayableAssets.getCutinImageAssetName()).isEqualTo(UPDATED_CUTIN_IMAGE_ASSET_NAME);
        assertThat(testMCardPlayableAssets.getSoundEventSuffix()).isEqualTo(UPDATED_SOUND_EVENT_SUFFIX);
    }

    @Test
    @Transactional
    public void updateNonExistingMCardPlayableAssets() throws Exception {
        int databaseSizeBeforeUpdate = mCardPlayableAssetsRepository.findAll().size();

        // Create the MCardPlayableAssets
        MCardPlayableAssetsDTO mCardPlayableAssetsDTO = mCardPlayableAssetsMapper.toDto(mCardPlayableAssets);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCardPlayableAssetsMockMvc.perform(put("/api/m-card-playable-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPlayableAssetsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardPlayableAssets in the database
        List<MCardPlayableAssets> mCardPlayableAssetsList = mCardPlayableAssetsRepository.findAll();
        assertThat(mCardPlayableAssetsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCardPlayableAssets() throws Exception {
        // Initialize the database
        mCardPlayableAssetsRepository.saveAndFlush(mCardPlayableAssets);

        int databaseSizeBeforeDelete = mCardPlayableAssetsRepository.findAll().size();

        // Delete the mCardPlayableAssets
        restMCardPlayableAssetsMockMvc.perform(delete("/api/m-card-playable-assets/{id}", mCardPlayableAssets.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCardPlayableAssets> mCardPlayableAssetsList = mCardPlayableAssetsRepository.findAll();
        assertThat(mCardPlayableAssetsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardPlayableAssets.class);
        MCardPlayableAssets mCardPlayableAssets1 = new MCardPlayableAssets();
        mCardPlayableAssets1.setId(1L);
        MCardPlayableAssets mCardPlayableAssets2 = new MCardPlayableAssets();
        mCardPlayableAssets2.setId(mCardPlayableAssets1.getId());
        assertThat(mCardPlayableAssets1).isEqualTo(mCardPlayableAssets2);
        mCardPlayableAssets2.setId(2L);
        assertThat(mCardPlayableAssets1).isNotEqualTo(mCardPlayableAssets2);
        mCardPlayableAssets1.setId(null);
        assertThat(mCardPlayableAssets1).isNotEqualTo(mCardPlayableAssets2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardPlayableAssetsDTO.class);
        MCardPlayableAssetsDTO mCardPlayableAssetsDTO1 = new MCardPlayableAssetsDTO();
        mCardPlayableAssetsDTO1.setId(1L);
        MCardPlayableAssetsDTO mCardPlayableAssetsDTO2 = new MCardPlayableAssetsDTO();
        assertThat(mCardPlayableAssetsDTO1).isNotEqualTo(mCardPlayableAssetsDTO2);
        mCardPlayableAssetsDTO2.setId(mCardPlayableAssetsDTO1.getId());
        assertThat(mCardPlayableAssetsDTO1).isEqualTo(mCardPlayableAssetsDTO2);
        mCardPlayableAssetsDTO2.setId(2L);
        assertThat(mCardPlayableAssetsDTO1).isNotEqualTo(mCardPlayableAssetsDTO2);
        mCardPlayableAssetsDTO1.setId(null);
        assertThat(mCardPlayableAssetsDTO1).isNotEqualTo(mCardPlayableAssetsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCardPlayableAssetsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCardPlayableAssetsMapper.fromId(null)).isNull();
    }
}
