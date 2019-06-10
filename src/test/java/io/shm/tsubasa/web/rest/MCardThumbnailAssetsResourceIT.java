package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCardThumbnailAssets;
import io.shm.tsubasa.domain.MCardPowerupActionSkill;
import io.shm.tsubasa.domain.MTrainingCard;
import io.shm.tsubasa.repository.MCardThumbnailAssetsRepository;
import io.shm.tsubasa.service.MCardThumbnailAssetsService;
import io.shm.tsubasa.service.dto.MCardThumbnailAssetsDTO;
import io.shm.tsubasa.service.mapper.MCardThumbnailAssetsMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCardThumbnailAssetsCriteria;
import io.shm.tsubasa.service.MCardThumbnailAssetsQueryService;

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
 * Integration tests for the {@Link MCardThumbnailAssetsResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCardThumbnailAssetsResourceIT {

    private static final String DEFAULT_THUMBNAIL_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_FRAME_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_FRAME_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_BACKGOUND_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_BACKGOUND_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_EFFECT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_EFFECT_ASSET_NAME = "BBBBBBBBBB";

    @Autowired
    private MCardThumbnailAssetsRepository mCardThumbnailAssetsRepository;

    @Autowired
    private MCardThumbnailAssetsMapper mCardThumbnailAssetsMapper;

    @Autowired
    private MCardThumbnailAssetsService mCardThumbnailAssetsService;

    @Autowired
    private MCardThumbnailAssetsQueryService mCardThumbnailAssetsQueryService;

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

    private MockMvc restMCardThumbnailAssetsMockMvc;

    private MCardThumbnailAssets mCardThumbnailAssets;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCardThumbnailAssetsResource mCardThumbnailAssetsResource = new MCardThumbnailAssetsResource(mCardThumbnailAssetsService, mCardThumbnailAssetsQueryService);
        this.restMCardThumbnailAssetsMockMvc = MockMvcBuilders.standaloneSetup(mCardThumbnailAssetsResource)
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
    public static MCardThumbnailAssets createEntity(EntityManager em) {
        MCardThumbnailAssets mCardThumbnailAssets = new MCardThumbnailAssets()
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME)
            .thumbnailFrameName(DEFAULT_THUMBNAIL_FRAME_NAME)
            .thumbnailBackgoundAssetName(DEFAULT_THUMBNAIL_BACKGOUND_ASSET_NAME)
            .thumbnailEffectAssetName(DEFAULT_THUMBNAIL_EFFECT_ASSET_NAME);
        return mCardThumbnailAssets;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCardThumbnailAssets createUpdatedEntity(EntityManager em) {
        MCardThumbnailAssets mCardThumbnailAssets = new MCardThumbnailAssets()
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .thumbnailFrameName(UPDATED_THUMBNAIL_FRAME_NAME)
            .thumbnailBackgoundAssetName(UPDATED_THUMBNAIL_BACKGOUND_ASSET_NAME)
            .thumbnailEffectAssetName(UPDATED_THUMBNAIL_EFFECT_ASSET_NAME);
        return mCardThumbnailAssets;
    }

    @BeforeEach
    public void initTest() {
        mCardThumbnailAssets = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCardThumbnailAssets() throws Exception {
        int databaseSizeBeforeCreate = mCardThumbnailAssetsRepository.findAll().size();

        // Create the MCardThumbnailAssets
        MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO = mCardThumbnailAssetsMapper.toDto(mCardThumbnailAssets);
        restMCardThumbnailAssetsMockMvc.perform(post("/api/m-card-thumbnail-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardThumbnailAssetsDTO)))
            .andExpect(status().isCreated());

        // Validate the MCardThumbnailAssets in the database
        List<MCardThumbnailAssets> mCardThumbnailAssetsList = mCardThumbnailAssetsRepository.findAll();
        assertThat(mCardThumbnailAssetsList).hasSize(databaseSizeBeforeCreate + 1);
        MCardThumbnailAssets testMCardThumbnailAssets = mCardThumbnailAssetsList.get(mCardThumbnailAssetsList.size() - 1);
        assertThat(testMCardThumbnailAssets.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
        assertThat(testMCardThumbnailAssets.getThumbnailFrameName()).isEqualTo(DEFAULT_THUMBNAIL_FRAME_NAME);
        assertThat(testMCardThumbnailAssets.getThumbnailBackgoundAssetName()).isEqualTo(DEFAULT_THUMBNAIL_BACKGOUND_ASSET_NAME);
        assertThat(testMCardThumbnailAssets.getThumbnailEffectAssetName()).isEqualTo(DEFAULT_THUMBNAIL_EFFECT_ASSET_NAME);
    }

    @Test
    @Transactional
    public void createMCardThumbnailAssetsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCardThumbnailAssetsRepository.findAll().size();

        // Create the MCardThumbnailAssets with an existing ID
        mCardThumbnailAssets.setId(1L);
        MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO = mCardThumbnailAssetsMapper.toDto(mCardThumbnailAssets);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCardThumbnailAssetsMockMvc.perform(post("/api/m-card-thumbnail-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardThumbnailAssetsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardThumbnailAssets in the database
        List<MCardThumbnailAssets> mCardThumbnailAssetsList = mCardThumbnailAssetsRepository.findAll();
        assertThat(mCardThumbnailAssetsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMCardThumbnailAssets() throws Exception {
        // Initialize the database
        mCardThumbnailAssetsRepository.saveAndFlush(mCardThumbnailAssets);

        // Get all the mCardThumbnailAssetsList
        restMCardThumbnailAssetsMockMvc.perform(get("/api/m-card-thumbnail-assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardThumbnailAssets.getId().intValue())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailFrameName").value(hasItem(DEFAULT_THUMBNAIL_FRAME_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailBackgoundAssetName").value(hasItem(DEFAULT_THUMBNAIL_BACKGOUND_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailEffectAssetName").value(hasItem(DEFAULT_THUMBNAIL_EFFECT_ASSET_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMCardThumbnailAssets() throws Exception {
        // Initialize the database
        mCardThumbnailAssetsRepository.saveAndFlush(mCardThumbnailAssets);

        // Get the mCardThumbnailAssets
        restMCardThumbnailAssetsMockMvc.perform(get("/api/m-card-thumbnail-assets/{id}", mCardThumbnailAssets.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCardThumbnailAssets.getId().intValue()))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.thumbnailFrameName").value(DEFAULT_THUMBNAIL_FRAME_NAME.toString()))
            .andExpect(jsonPath("$.thumbnailBackgoundAssetName").value(DEFAULT_THUMBNAIL_BACKGOUND_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.thumbnailEffectAssetName").value(DEFAULT_THUMBNAIL_EFFECT_ASSET_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMCardThumbnailAssetsByMCardPowerupActionSkillIsEqualToSomething() throws Exception {
        // Initialize the database
        MCardPowerupActionSkill mCardPowerupActionSkill = MCardPowerupActionSkillResourceIT.createEntity(em);
        em.persist(mCardPowerupActionSkill);
        em.flush();
        mCardThumbnailAssets.addMCardPowerupActionSkill(mCardPowerupActionSkill);
        mCardThumbnailAssetsRepository.saveAndFlush(mCardThumbnailAssets);
        Long mCardPowerupActionSkillId = mCardPowerupActionSkill.getId();

        // Get all the mCardThumbnailAssetsList where mCardPowerupActionSkill equals to mCardPowerupActionSkillId
        defaultMCardThumbnailAssetsShouldBeFound("mCardPowerupActionSkillId.equals=" + mCardPowerupActionSkillId);

        // Get all the mCardThumbnailAssetsList where mCardPowerupActionSkill equals to mCardPowerupActionSkillId + 1
        defaultMCardThumbnailAssetsShouldNotBeFound("mCardPowerupActionSkillId.equals=" + (mCardPowerupActionSkillId + 1));
    }


    @Test
    @Transactional
    public void getAllMCardThumbnailAssetsByMTrainingCardIsEqualToSomething() throws Exception {
        // Initialize the database
        MTrainingCard mTrainingCard = MTrainingCardResourceIT.createEntity(em);
        em.persist(mTrainingCard);
        em.flush();
        mCardThumbnailAssets.addMTrainingCard(mTrainingCard);
        mCardThumbnailAssetsRepository.saveAndFlush(mCardThumbnailAssets);
        Long mTrainingCardId = mTrainingCard.getId();

        // Get all the mCardThumbnailAssetsList where mTrainingCard equals to mTrainingCardId
        defaultMCardThumbnailAssetsShouldBeFound("mTrainingCardId.equals=" + mTrainingCardId);

        // Get all the mCardThumbnailAssetsList where mTrainingCard equals to mTrainingCardId + 1
        defaultMCardThumbnailAssetsShouldNotBeFound("mTrainingCardId.equals=" + (mTrainingCardId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCardThumbnailAssetsShouldBeFound(String filter) throws Exception {
        restMCardThumbnailAssetsMockMvc.perform(get("/api/m-card-thumbnail-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardThumbnailAssets.getId().intValue())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailFrameName").value(hasItem(DEFAULT_THUMBNAIL_FRAME_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailBackgoundAssetName").value(hasItem(DEFAULT_THUMBNAIL_BACKGOUND_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailEffectAssetName").value(hasItem(DEFAULT_THUMBNAIL_EFFECT_ASSET_NAME.toString())));

        // Check, that the count call also returns 1
        restMCardThumbnailAssetsMockMvc.perform(get("/api/m-card-thumbnail-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCardThumbnailAssetsShouldNotBeFound(String filter) throws Exception {
        restMCardThumbnailAssetsMockMvc.perform(get("/api/m-card-thumbnail-assets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCardThumbnailAssetsMockMvc.perform(get("/api/m-card-thumbnail-assets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCardThumbnailAssets() throws Exception {
        // Get the mCardThumbnailAssets
        restMCardThumbnailAssetsMockMvc.perform(get("/api/m-card-thumbnail-assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCardThumbnailAssets() throws Exception {
        // Initialize the database
        mCardThumbnailAssetsRepository.saveAndFlush(mCardThumbnailAssets);

        int databaseSizeBeforeUpdate = mCardThumbnailAssetsRepository.findAll().size();

        // Update the mCardThumbnailAssets
        MCardThumbnailAssets updatedMCardThumbnailAssets = mCardThumbnailAssetsRepository.findById(mCardThumbnailAssets.getId()).get();
        // Disconnect from session so that the updates on updatedMCardThumbnailAssets are not directly saved in db
        em.detach(updatedMCardThumbnailAssets);
        updatedMCardThumbnailAssets
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .thumbnailFrameName(UPDATED_THUMBNAIL_FRAME_NAME)
            .thumbnailBackgoundAssetName(UPDATED_THUMBNAIL_BACKGOUND_ASSET_NAME)
            .thumbnailEffectAssetName(UPDATED_THUMBNAIL_EFFECT_ASSET_NAME);
        MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO = mCardThumbnailAssetsMapper.toDto(updatedMCardThumbnailAssets);

        restMCardThumbnailAssetsMockMvc.perform(put("/api/m-card-thumbnail-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardThumbnailAssetsDTO)))
            .andExpect(status().isOk());

        // Validate the MCardThumbnailAssets in the database
        List<MCardThumbnailAssets> mCardThumbnailAssetsList = mCardThumbnailAssetsRepository.findAll();
        assertThat(mCardThumbnailAssetsList).hasSize(databaseSizeBeforeUpdate);
        MCardThumbnailAssets testMCardThumbnailAssets = mCardThumbnailAssetsList.get(mCardThumbnailAssetsList.size() - 1);
        assertThat(testMCardThumbnailAssets.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
        assertThat(testMCardThumbnailAssets.getThumbnailFrameName()).isEqualTo(UPDATED_THUMBNAIL_FRAME_NAME);
        assertThat(testMCardThumbnailAssets.getThumbnailBackgoundAssetName()).isEqualTo(UPDATED_THUMBNAIL_BACKGOUND_ASSET_NAME);
        assertThat(testMCardThumbnailAssets.getThumbnailEffectAssetName()).isEqualTo(UPDATED_THUMBNAIL_EFFECT_ASSET_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMCardThumbnailAssets() throws Exception {
        int databaseSizeBeforeUpdate = mCardThumbnailAssetsRepository.findAll().size();

        // Create the MCardThumbnailAssets
        MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO = mCardThumbnailAssetsMapper.toDto(mCardThumbnailAssets);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCardThumbnailAssetsMockMvc.perform(put("/api/m-card-thumbnail-assets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardThumbnailAssetsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardThumbnailAssets in the database
        List<MCardThumbnailAssets> mCardThumbnailAssetsList = mCardThumbnailAssetsRepository.findAll();
        assertThat(mCardThumbnailAssetsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCardThumbnailAssets() throws Exception {
        // Initialize the database
        mCardThumbnailAssetsRepository.saveAndFlush(mCardThumbnailAssets);

        int databaseSizeBeforeDelete = mCardThumbnailAssetsRepository.findAll().size();

        // Delete the mCardThumbnailAssets
        restMCardThumbnailAssetsMockMvc.perform(delete("/api/m-card-thumbnail-assets/{id}", mCardThumbnailAssets.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCardThumbnailAssets> mCardThumbnailAssetsList = mCardThumbnailAssetsRepository.findAll();
        assertThat(mCardThumbnailAssetsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardThumbnailAssets.class);
        MCardThumbnailAssets mCardThumbnailAssets1 = new MCardThumbnailAssets();
        mCardThumbnailAssets1.setId(1L);
        MCardThumbnailAssets mCardThumbnailAssets2 = new MCardThumbnailAssets();
        mCardThumbnailAssets2.setId(mCardThumbnailAssets1.getId());
        assertThat(mCardThumbnailAssets1).isEqualTo(mCardThumbnailAssets2);
        mCardThumbnailAssets2.setId(2L);
        assertThat(mCardThumbnailAssets1).isNotEqualTo(mCardThumbnailAssets2);
        mCardThumbnailAssets1.setId(null);
        assertThat(mCardThumbnailAssets1).isNotEqualTo(mCardThumbnailAssets2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardThumbnailAssetsDTO.class);
        MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO1 = new MCardThumbnailAssetsDTO();
        mCardThumbnailAssetsDTO1.setId(1L);
        MCardThumbnailAssetsDTO mCardThumbnailAssetsDTO2 = new MCardThumbnailAssetsDTO();
        assertThat(mCardThumbnailAssetsDTO1).isNotEqualTo(mCardThumbnailAssetsDTO2);
        mCardThumbnailAssetsDTO2.setId(mCardThumbnailAssetsDTO1.getId());
        assertThat(mCardThumbnailAssetsDTO1).isEqualTo(mCardThumbnailAssetsDTO2);
        mCardThumbnailAssetsDTO2.setId(2L);
        assertThat(mCardThumbnailAssetsDTO1).isNotEqualTo(mCardThumbnailAssetsDTO2);
        mCardThumbnailAssetsDTO1.setId(null);
        assertThat(mCardThumbnailAssetsDTO1).isNotEqualTo(mCardThumbnailAssetsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCardThumbnailAssetsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCardThumbnailAssetsMapper.fromId(null)).isNull();
    }
}
