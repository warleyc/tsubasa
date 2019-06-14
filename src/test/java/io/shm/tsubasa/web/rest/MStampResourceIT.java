package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MStamp;
import io.shm.tsubasa.repository.MStampRepository;
import io.shm.tsubasa.service.MStampService;
import io.shm.tsubasa.service.dto.MStampDTO;
import io.shm.tsubasa.service.mapper.MStampMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MStampCriteria;
import io.shm.tsubasa.service.MStampQueryService;

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
 * Integration tests for the {@Link MStampResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MStampResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_ASSET = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET = "BBBBBBBBBB";

    private static final String DEFAULT_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_SOUND_EVENT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MStampRepository mStampRepository;

    @Autowired
    private MStampMapper mStampMapper;

    @Autowired
    private MStampService mStampService;

    @Autowired
    private MStampQueryService mStampQueryService;

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

    private MockMvc restMStampMockMvc;

    private MStamp mStamp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MStampResource mStampResource = new MStampResource(mStampService, mStampQueryService);
        this.restMStampMockMvc = MockMvcBuilders.standaloneSetup(mStampResource)
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
    public static MStamp createEntity(EntityManager em) {
        MStamp mStamp = new MStamp()
            .name(DEFAULT_NAME)
            .thumbnailAsset(DEFAULT_THUMBNAIL_ASSET)
            .soundEvent(DEFAULT_SOUND_EVENT)
            .description(DEFAULT_DESCRIPTION);
        return mStamp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MStamp createUpdatedEntity(EntityManager em) {
        MStamp mStamp = new MStamp()
            .name(UPDATED_NAME)
            .thumbnailAsset(UPDATED_THUMBNAIL_ASSET)
            .soundEvent(UPDATED_SOUND_EVENT)
            .description(UPDATED_DESCRIPTION);
        return mStamp;
    }

    @BeforeEach
    public void initTest() {
        mStamp = createEntity(em);
    }

    @Test
    @Transactional
    public void createMStamp() throws Exception {
        int databaseSizeBeforeCreate = mStampRepository.findAll().size();

        // Create the MStamp
        MStampDTO mStampDTO = mStampMapper.toDto(mStamp);
        restMStampMockMvc.perform(post("/api/m-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStampDTO)))
            .andExpect(status().isCreated());

        // Validate the MStamp in the database
        List<MStamp> mStampList = mStampRepository.findAll();
        assertThat(mStampList).hasSize(databaseSizeBeforeCreate + 1);
        MStamp testMStamp = mStampList.get(mStampList.size() - 1);
        assertThat(testMStamp.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMStamp.getThumbnailAsset()).isEqualTo(DEFAULT_THUMBNAIL_ASSET);
        assertThat(testMStamp.getSoundEvent()).isEqualTo(DEFAULT_SOUND_EVENT);
        assertThat(testMStamp.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMStampWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mStampRepository.findAll().size();

        // Create the MStamp with an existing ID
        mStamp.setId(1L);
        MStampDTO mStampDTO = mStampMapper.toDto(mStamp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMStampMockMvc.perform(post("/api/m-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MStamp in the database
        List<MStamp> mStampList = mStampRepository.findAll();
        assertThat(mStampList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMStamps() throws Exception {
        // Initialize the database
        mStampRepository.saveAndFlush(mStamp);

        // Get all the mStampList
        restMStampMockMvc.perform(get("/api/m-stamps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAsset").value(hasItem(DEFAULT_THUMBNAIL_ASSET.toString())))
            .andExpect(jsonPath("$.[*].soundEvent").value(hasItem(DEFAULT_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMStamp() throws Exception {
        // Initialize the database
        mStampRepository.saveAndFlush(mStamp);

        // Get the mStamp
        restMStampMockMvc.perform(get("/api/m-stamps/{id}", mStamp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mStamp.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.thumbnailAsset").value(DEFAULT_THUMBNAIL_ASSET.toString()))
            .andExpect(jsonPath("$.soundEvent").value(DEFAULT_SOUND_EVENT.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMStampShouldBeFound(String filter) throws Exception {
        restMStampMockMvc.perform(get("/api/m-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAsset").value(hasItem(DEFAULT_THUMBNAIL_ASSET.toString())))
            .andExpect(jsonPath("$.[*].soundEvent").value(hasItem(DEFAULT_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMStampMockMvc.perform(get("/api/m-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMStampShouldNotBeFound(String filter) throws Exception {
        restMStampMockMvc.perform(get("/api/m-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMStampMockMvc.perform(get("/api/m-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMStamp() throws Exception {
        // Get the mStamp
        restMStampMockMvc.perform(get("/api/m-stamps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMStamp() throws Exception {
        // Initialize the database
        mStampRepository.saveAndFlush(mStamp);

        int databaseSizeBeforeUpdate = mStampRepository.findAll().size();

        // Update the mStamp
        MStamp updatedMStamp = mStampRepository.findById(mStamp.getId()).get();
        // Disconnect from session so that the updates on updatedMStamp are not directly saved in db
        em.detach(updatedMStamp);
        updatedMStamp
            .name(UPDATED_NAME)
            .thumbnailAsset(UPDATED_THUMBNAIL_ASSET)
            .soundEvent(UPDATED_SOUND_EVENT)
            .description(UPDATED_DESCRIPTION);
        MStampDTO mStampDTO = mStampMapper.toDto(updatedMStamp);

        restMStampMockMvc.perform(put("/api/m-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStampDTO)))
            .andExpect(status().isOk());

        // Validate the MStamp in the database
        List<MStamp> mStampList = mStampRepository.findAll();
        assertThat(mStampList).hasSize(databaseSizeBeforeUpdate);
        MStamp testMStamp = mStampList.get(mStampList.size() - 1);
        assertThat(testMStamp.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMStamp.getThumbnailAsset()).isEqualTo(UPDATED_THUMBNAIL_ASSET);
        assertThat(testMStamp.getSoundEvent()).isEqualTo(UPDATED_SOUND_EVENT);
        assertThat(testMStamp.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMStamp() throws Exception {
        int databaseSizeBeforeUpdate = mStampRepository.findAll().size();

        // Create the MStamp
        MStampDTO mStampDTO = mStampMapper.toDto(mStamp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMStampMockMvc.perform(put("/api/m-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MStamp in the database
        List<MStamp> mStampList = mStampRepository.findAll();
        assertThat(mStampList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMStamp() throws Exception {
        // Initialize the database
        mStampRepository.saveAndFlush(mStamp);

        int databaseSizeBeforeDelete = mStampRepository.findAll().size();

        // Delete the mStamp
        restMStampMockMvc.perform(delete("/api/m-stamps/{id}", mStamp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MStamp> mStampList = mStampRepository.findAll();
        assertThat(mStampList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MStamp.class);
        MStamp mStamp1 = new MStamp();
        mStamp1.setId(1L);
        MStamp mStamp2 = new MStamp();
        mStamp2.setId(mStamp1.getId());
        assertThat(mStamp1).isEqualTo(mStamp2);
        mStamp2.setId(2L);
        assertThat(mStamp1).isNotEqualTo(mStamp2);
        mStamp1.setId(null);
        assertThat(mStamp1).isNotEqualTo(mStamp2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MStampDTO.class);
        MStampDTO mStampDTO1 = new MStampDTO();
        mStampDTO1.setId(1L);
        MStampDTO mStampDTO2 = new MStampDTO();
        assertThat(mStampDTO1).isNotEqualTo(mStampDTO2);
        mStampDTO2.setId(mStampDTO1.getId());
        assertThat(mStampDTO1).isEqualTo(mStampDTO2);
        mStampDTO2.setId(2L);
        assertThat(mStampDTO1).isNotEqualTo(mStampDTO2);
        mStampDTO1.setId(null);
        assertThat(mStampDTO1).isNotEqualTo(mStampDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mStampMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mStampMapper.fromId(null)).isNull();
    }
}
