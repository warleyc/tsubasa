package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCardIllustAssets;
import io.shm.tsubasa.repository.MCardIllustAssetsRepository;
import io.shm.tsubasa.service.MCardIllustAssetsService;
import io.shm.tsubasa.service.dto.MCardIllustAssetsDTO;
import io.shm.tsubasa.service.mapper.MCardIllustAssetsMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCardIllustAssetsCriteria;
import io.shm.tsubasa.service.MCardIllustAssetsQueryService;

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
 * Integration tests for the {@Link MCardIllustAssetsResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCardIllustAssetsResourceIT {

    private static final String DEFAULT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OFFSET = "AAAAAAAAAA";
    private static final String UPDATED_OFFSET = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DECORATION_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DECORATION_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EFFECT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EFFECT_ASSET_NAME = "BBBBBBBBBB";

    @Autowired
    private MCardIllustAssetsRepository mCardIllustAssetsRepository;

    @Autowired
    private MCardIllustAssetsMapper mCardIllustAssetsMapper;

    @Autowired
    private MCardIllustAssetsService mCardIllustAssetsService;

    @Autowired
    private MCardIllustAssetsQueryService mCardIllustAssetsQueryService;

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

    private MockMvc restMCardIllustAssetsMockMvc;

    private MCardIllustAssets mCardIllustAssets;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCardIllustAssetsResource mCardIllustAssetsResource = new MCardIllustAssetsResource(mCardIllustAssetsService, mCardIllustAssetsQueryService);
        this.restMCardIllustAssetsMockMvc = MockMvcBuilders.standaloneSetup(mCardIllustAssetsResource)
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
    public static MCardIllustAssets createEntity(EntityManager em) {
        MCardIllustAssets mCardIllustAssets = new MCardIllustAssets()
            .assetName(DEFAULT_ASSET_NAME)
            .subAssetName(DEFAULT_SUB_ASSET_NAME)
            .offset(DEFAULT_OFFSET)
            .backgroundAssetName(DEFAULT_BACKGROUND_ASSET_NAME)
            .decorationAssetName(DEFAULT_DECORATION_ASSET_NAME)
            .effectAssetName(DEFAULT_EFFECT_ASSET_NAME);
        return mCardIllustAssets;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCardIllustAssets createUpdatedEntity(EntityManager em) {
        MCardIllustAssets mCardIllustAssets = new MCardIllustAssets()
            .assetName(UPDATED_ASSET_NAME)
            .subAssetName(UPDATED_SUB_ASSET_NAME)
            .offset(UPDATED_OFFSET)
            .backgroundAssetName(UPDATED_BACKGROUND_ASSET_NAME)
            .decorationAssetName(UPDATED_DECORATION_ASSET_NAME)
            .effectAssetName(UPDATED_EFFECT_ASSET_NAME);
        return mCardIllustAssets;
    }

    @BeforeEach
    public void initTest() {
        mCardIllustAssets = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCardIllustAssets() throws Exception {
        int databaseSizeBeforeCreate = mCardIllustAssetsRepository.findAll().size();

        // Create the MCardIllustAssets
        MCardIllustAssetsDTO mCardIllustAssetsDTO = mCardIllustAssetsMapper.toDto(mCardIllustAssets);
        restMCardIllustAssetsMockMvc.perform(post("/api/m-card-illust-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardIllustAssetsDTO)))
            .andExpect(status().isCreated());

        // Validate the MCardIllustAssets in the database
        List<MCardIllustAssets> mCardIllustAssetsList = mCardIllustAssetsRepository.findAll();
        assertThat(mCardIllustAssetsList).hasSize(databaseSizeBeforeCreate + 1);
        MCardIllustAssets testMCardIllustAssets = mCardIllustAssetsList.get(mCardIllustAssetsList.size() - 1);
        assertThat(testMCardIllustAssets.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testMCardIllustAssets.getSubAssetName()).isEqualTo(DEFAULT_SUB_ASSET_NAME);
        assertThat(testMCardIllustAssets.getOffset()).isEqualTo(DEFAULT_OFFSET);
        assertThat(testMCardIllustAssets.getBackgroundAssetName()).isEqualTo(DEFAULT_BACKGROUND_ASSET_NAME);
        assertThat(testMCardIllustAssets.getDecorationAssetName()).isEqualTo(DEFAULT_DECORATION_ASSET_NAME);
        assertThat(testMCardIllustAssets.getEffectAssetName()).isEqualTo(DEFAULT_EFFECT_ASSET_NAME);
    }

    @Test
    @Transactional
    public void createMCardIllustAssetsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCardIllustAssetsRepository.findAll().size();

        // Create the MCardIllustAssets with an existing ID
        mCardIllustAssets.setId(1L);
        MCardIllustAssetsDTO mCardIllustAssetsDTO = mCardIllustAssetsMapper.toDto(mCardIllustAssets);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCardIllustAssetsMockMvc.perform(post("/api/m-card-illust-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardIllustAssetsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardIllustAssets in the database
        List<MCardIllustAssets> mCardIllustAssetsList = mCardIllustAssetsRepository.findAll();
        assertThat(mCardIllustAssetsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMCardIllustAssets() throws Exception {
        // Initialize the database
        mCardIllustAssetsRepository.saveAndFlush(mCardIllustAssets);

        // Get all the mCardIllustAssetsList
        restMCardIllustAssetsMockMvc.perform(get("/api/m-card-illust-assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardIllustAssets.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].subAssetName").value(hasItem(DEFAULT_SUB_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].offset").value(hasItem(DEFAULT_OFFSET.toString())))
            .andExpect(jsonPath("$.[*].backgroundAssetName").value(hasItem(DEFAULT_BACKGROUND_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].decorationAssetName").value(hasItem(DEFAULT_DECORATION_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].effectAssetName").value(hasItem(DEFAULT_EFFECT_ASSET_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMCardIllustAssets() throws Exception {
        // Initialize the database
        mCardIllustAssetsRepository.saveAndFlush(mCardIllustAssets);

        // Get the mCardIllustAssets
        restMCardIllustAssetsMockMvc.perform(get("/api/m-card-illust-assets/{id}", mCardIllustAssets.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCardIllustAssets.getId().intValue()))
            .andExpect(jsonPath("$.assetName").value(DEFAULT_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.subAssetName").value(DEFAULT_SUB_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.offset").value(DEFAULT_OFFSET.toString()))
            .andExpect(jsonPath("$.backgroundAssetName").value(DEFAULT_BACKGROUND_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.decorationAssetName").value(DEFAULT_DECORATION_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.effectAssetName").value(DEFAULT_EFFECT_ASSET_NAME.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCardIllustAssetsShouldBeFound(String filter) throws Exception {
        restMCardIllustAssetsMockMvc.perform(get("/api/m-card-illust-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardIllustAssets.getId().intValue())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].subAssetName").value(hasItem(DEFAULT_SUB_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].offset").value(hasItem(DEFAULT_OFFSET.toString())))
            .andExpect(jsonPath("$.[*].backgroundAssetName").value(hasItem(DEFAULT_BACKGROUND_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].decorationAssetName").value(hasItem(DEFAULT_DECORATION_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].effectAssetName").value(hasItem(DEFAULT_EFFECT_ASSET_NAME.toString())));

        // Check, that the count call also returns 1
        restMCardIllustAssetsMockMvc.perform(get("/api/m-card-illust-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCardIllustAssetsShouldNotBeFound(String filter) throws Exception {
        restMCardIllustAssetsMockMvc.perform(get("/api/m-card-illust-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCardIllustAssetsMockMvc.perform(get("/api/m-card-illust-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCardIllustAssets() throws Exception {
        // Get the mCardIllustAssets
        restMCardIllustAssetsMockMvc.perform(get("/api/m-card-illust-assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCardIllustAssets() throws Exception {
        // Initialize the database
        mCardIllustAssetsRepository.saveAndFlush(mCardIllustAssets);

        int databaseSizeBeforeUpdate = mCardIllustAssetsRepository.findAll().size();

        // Update the mCardIllustAssets
        MCardIllustAssets updatedMCardIllustAssets = mCardIllustAssetsRepository.findById(mCardIllustAssets.getId()).get();
        // Disconnect from session so that the updates on updatedMCardIllustAssets are not directly saved in db
        em.detach(updatedMCardIllustAssets);
        updatedMCardIllustAssets
            .assetName(UPDATED_ASSET_NAME)
            .subAssetName(UPDATED_SUB_ASSET_NAME)
            .offset(UPDATED_OFFSET)
            .backgroundAssetName(UPDATED_BACKGROUND_ASSET_NAME)
            .decorationAssetName(UPDATED_DECORATION_ASSET_NAME)
            .effectAssetName(UPDATED_EFFECT_ASSET_NAME);
        MCardIllustAssetsDTO mCardIllustAssetsDTO = mCardIllustAssetsMapper.toDto(updatedMCardIllustAssets);

        restMCardIllustAssetsMockMvc.perform(put("/api/m-card-illust-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardIllustAssetsDTO)))
            .andExpect(status().isOk());

        // Validate the MCardIllustAssets in the database
        List<MCardIllustAssets> mCardIllustAssetsList = mCardIllustAssetsRepository.findAll();
        assertThat(mCardIllustAssetsList).hasSize(databaseSizeBeforeUpdate);
        MCardIllustAssets testMCardIllustAssets = mCardIllustAssetsList.get(mCardIllustAssetsList.size() - 1);
        assertThat(testMCardIllustAssets.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testMCardIllustAssets.getSubAssetName()).isEqualTo(UPDATED_SUB_ASSET_NAME);
        assertThat(testMCardIllustAssets.getOffset()).isEqualTo(UPDATED_OFFSET);
        assertThat(testMCardIllustAssets.getBackgroundAssetName()).isEqualTo(UPDATED_BACKGROUND_ASSET_NAME);
        assertThat(testMCardIllustAssets.getDecorationAssetName()).isEqualTo(UPDATED_DECORATION_ASSET_NAME);
        assertThat(testMCardIllustAssets.getEffectAssetName()).isEqualTo(UPDATED_EFFECT_ASSET_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMCardIllustAssets() throws Exception {
        int databaseSizeBeforeUpdate = mCardIllustAssetsRepository.findAll().size();

        // Create the MCardIllustAssets
        MCardIllustAssetsDTO mCardIllustAssetsDTO = mCardIllustAssetsMapper.toDto(mCardIllustAssets);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCardIllustAssetsMockMvc.perform(put("/api/m-card-illust-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardIllustAssetsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardIllustAssets in the database
        List<MCardIllustAssets> mCardIllustAssetsList = mCardIllustAssetsRepository.findAll();
        assertThat(mCardIllustAssetsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCardIllustAssets() throws Exception {
        // Initialize the database
        mCardIllustAssetsRepository.saveAndFlush(mCardIllustAssets);

        int databaseSizeBeforeDelete = mCardIllustAssetsRepository.findAll().size();

        // Delete the mCardIllustAssets
        restMCardIllustAssetsMockMvc.perform(delete("/api/m-card-illust-assets/{id}", mCardIllustAssets.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCardIllustAssets> mCardIllustAssetsList = mCardIllustAssetsRepository.findAll();
        assertThat(mCardIllustAssetsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardIllustAssets.class);
        MCardIllustAssets mCardIllustAssets1 = new MCardIllustAssets();
        mCardIllustAssets1.setId(1L);
        MCardIllustAssets mCardIllustAssets2 = new MCardIllustAssets();
        mCardIllustAssets2.setId(mCardIllustAssets1.getId());
        assertThat(mCardIllustAssets1).isEqualTo(mCardIllustAssets2);
        mCardIllustAssets2.setId(2L);
        assertThat(mCardIllustAssets1).isNotEqualTo(mCardIllustAssets2);
        mCardIllustAssets1.setId(null);
        assertThat(mCardIllustAssets1).isNotEqualTo(mCardIllustAssets2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardIllustAssetsDTO.class);
        MCardIllustAssetsDTO mCardIllustAssetsDTO1 = new MCardIllustAssetsDTO();
        mCardIllustAssetsDTO1.setId(1L);
        MCardIllustAssetsDTO mCardIllustAssetsDTO2 = new MCardIllustAssetsDTO();
        assertThat(mCardIllustAssetsDTO1).isNotEqualTo(mCardIllustAssetsDTO2);
        mCardIllustAssetsDTO2.setId(mCardIllustAssetsDTO1.getId());
        assertThat(mCardIllustAssetsDTO1).isEqualTo(mCardIllustAssetsDTO2);
        mCardIllustAssetsDTO2.setId(2L);
        assertThat(mCardIllustAssetsDTO1).isNotEqualTo(mCardIllustAssetsDTO2);
        mCardIllustAssetsDTO1.setId(null);
        assertThat(mCardIllustAssetsDTO1).isNotEqualTo(mCardIllustAssetsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCardIllustAssetsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCardIllustAssetsMapper.fromId(null)).isNull();
    }
}
