package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDictionaryAr;
import io.shm.tsubasa.repository.MDictionaryArRepository;
import io.shm.tsubasa.service.MDictionaryArService;
import io.shm.tsubasa.service.dto.MDictionaryArDTO;
import io.shm.tsubasa.service.mapper.MDictionaryArMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDictionaryArCriteria;
import io.shm.tsubasa.service.MDictionaryArQueryService;

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
 * Integration tests for the {@Link MDictionaryArResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDictionaryArResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MDictionaryArRepository mDictionaryArRepository;

    @Autowired
    private MDictionaryArMapper mDictionaryArMapper;

    @Autowired
    private MDictionaryArService mDictionaryArService;

    @Autowired
    private MDictionaryArQueryService mDictionaryArQueryService;

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

    private MockMvc restMDictionaryArMockMvc;

    private MDictionaryAr mDictionaryAr;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDictionaryArResource mDictionaryArResource = new MDictionaryArResource(mDictionaryArService, mDictionaryArQueryService);
        this.restMDictionaryArMockMvc = MockMvcBuilders.standaloneSetup(mDictionaryArResource)
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
    public static MDictionaryAr createEntity(EntityManager em) {
        MDictionaryAr mDictionaryAr = new MDictionaryAr()
            .key(DEFAULT_KEY)
            .message(DEFAULT_MESSAGE);
        return mDictionaryAr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDictionaryAr createUpdatedEntity(EntityManager em) {
        MDictionaryAr mDictionaryAr = new MDictionaryAr()
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        return mDictionaryAr;
    }

    @BeforeEach
    public void initTest() {
        mDictionaryAr = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDictionaryAr() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryArRepository.findAll().size();

        // Create the MDictionaryAr
        MDictionaryArDTO mDictionaryArDTO = mDictionaryArMapper.toDto(mDictionaryAr);
        restMDictionaryArMockMvc.perform(post("/api/m-dictionary-ars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryArDTO)))
            .andExpect(status().isCreated());

        // Validate the MDictionaryAr in the database
        List<MDictionaryAr> mDictionaryArList = mDictionaryArRepository.findAll();
        assertThat(mDictionaryArList).hasSize(databaseSizeBeforeCreate + 1);
        MDictionaryAr testMDictionaryAr = mDictionaryArList.get(mDictionaryArList.size() - 1);
        assertThat(testMDictionaryAr.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDictionaryAr.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMDictionaryArWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryArRepository.findAll().size();

        // Create the MDictionaryAr with an existing ID
        mDictionaryAr.setId(1L);
        MDictionaryArDTO mDictionaryArDTO = mDictionaryArMapper.toDto(mDictionaryAr);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDictionaryArMockMvc.perform(post("/api/m-dictionary-ars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryArDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryAr in the database
        List<MDictionaryAr> mDictionaryArList = mDictionaryArRepository.findAll();
        assertThat(mDictionaryArList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDictionaryArs() throws Exception {
        // Initialize the database
        mDictionaryArRepository.saveAndFlush(mDictionaryAr);

        // Get all the mDictionaryArList
        restMDictionaryArMockMvc.perform(get("/api/m-dictionary-ars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryAr.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMDictionaryAr() throws Exception {
        // Initialize the database
        mDictionaryArRepository.saveAndFlush(mDictionaryAr);

        // Get the mDictionaryAr
        restMDictionaryArMockMvc.perform(get("/api/m-dictionary-ars/{id}", mDictionaryAr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDictionaryAr.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDictionaryArShouldBeFound(String filter) throws Exception {
        restMDictionaryArMockMvc.perform(get("/api/m-dictionary-ars?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryAr.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMDictionaryArMockMvc.perform(get("/api/m-dictionary-ars/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDictionaryArShouldNotBeFound(String filter) throws Exception {
        restMDictionaryArMockMvc.perform(get("/api/m-dictionary-ars?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDictionaryArMockMvc.perform(get("/api/m-dictionary-ars/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDictionaryAr() throws Exception {
        // Get the mDictionaryAr
        restMDictionaryArMockMvc.perform(get("/api/m-dictionary-ars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDictionaryAr() throws Exception {
        // Initialize the database
        mDictionaryArRepository.saveAndFlush(mDictionaryAr);

        int databaseSizeBeforeUpdate = mDictionaryArRepository.findAll().size();

        // Update the mDictionaryAr
        MDictionaryAr updatedMDictionaryAr = mDictionaryArRepository.findById(mDictionaryAr.getId()).get();
        // Disconnect from session so that the updates on updatedMDictionaryAr are not directly saved in db
        em.detach(updatedMDictionaryAr);
        updatedMDictionaryAr
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        MDictionaryArDTO mDictionaryArDTO = mDictionaryArMapper.toDto(updatedMDictionaryAr);

        restMDictionaryArMockMvc.perform(put("/api/m-dictionary-ars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryArDTO)))
            .andExpect(status().isOk());

        // Validate the MDictionaryAr in the database
        List<MDictionaryAr> mDictionaryArList = mDictionaryArRepository.findAll();
        assertThat(mDictionaryArList).hasSize(databaseSizeBeforeUpdate);
        MDictionaryAr testMDictionaryAr = mDictionaryArList.get(mDictionaryArList.size() - 1);
        assertThat(testMDictionaryAr.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDictionaryAr.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDictionaryAr() throws Exception {
        int databaseSizeBeforeUpdate = mDictionaryArRepository.findAll().size();

        // Create the MDictionaryAr
        MDictionaryArDTO mDictionaryArDTO = mDictionaryArMapper.toDto(mDictionaryAr);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDictionaryArMockMvc.perform(put("/api/m-dictionary-ars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryArDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryAr in the database
        List<MDictionaryAr> mDictionaryArList = mDictionaryArRepository.findAll();
        assertThat(mDictionaryArList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDictionaryAr() throws Exception {
        // Initialize the database
        mDictionaryArRepository.saveAndFlush(mDictionaryAr);

        int databaseSizeBeforeDelete = mDictionaryArRepository.findAll().size();

        // Delete the mDictionaryAr
        restMDictionaryArMockMvc.perform(delete("/api/m-dictionary-ars/{id}", mDictionaryAr.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDictionaryAr> mDictionaryArList = mDictionaryArRepository.findAll();
        assertThat(mDictionaryArList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryAr.class);
        MDictionaryAr mDictionaryAr1 = new MDictionaryAr();
        mDictionaryAr1.setId(1L);
        MDictionaryAr mDictionaryAr2 = new MDictionaryAr();
        mDictionaryAr2.setId(mDictionaryAr1.getId());
        assertThat(mDictionaryAr1).isEqualTo(mDictionaryAr2);
        mDictionaryAr2.setId(2L);
        assertThat(mDictionaryAr1).isNotEqualTo(mDictionaryAr2);
        mDictionaryAr1.setId(null);
        assertThat(mDictionaryAr1).isNotEqualTo(mDictionaryAr2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryArDTO.class);
        MDictionaryArDTO mDictionaryArDTO1 = new MDictionaryArDTO();
        mDictionaryArDTO1.setId(1L);
        MDictionaryArDTO mDictionaryArDTO2 = new MDictionaryArDTO();
        assertThat(mDictionaryArDTO1).isNotEqualTo(mDictionaryArDTO2);
        mDictionaryArDTO2.setId(mDictionaryArDTO1.getId());
        assertThat(mDictionaryArDTO1).isEqualTo(mDictionaryArDTO2);
        mDictionaryArDTO2.setId(2L);
        assertThat(mDictionaryArDTO1).isNotEqualTo(mDictionaryArDTO2);
        mDictionaryArDTO1.setId(null);
        assertThat(mDictionaryArDTO1).isNotEqualTo(mDictionaryArDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDictionaryArMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDictionaryArMapper.fromId(null)).isNull();
    }
}
