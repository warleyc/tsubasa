package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDictionaryPt;
import io.shm.tsubasa.repository.MDictionaryPtRepository;
import io.shm.tsubasa.service.MDictionaryPtService;
import io.shm.tsubasa.service.dto.MDictionaryPtDTO;
import io.shm.tsubasa.service.mapper.MDictionaryPtMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDictionaryPtCriteria;
import io.shm.tsubasa.service.MDictionaryPtQueryService;

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
 * Integration tests for the {@Link MDictionaryPtResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDictionaryPtResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MDictionaryPtRepository mDictionaryPtRepository;

    @Autowired
    private MDictionaryPtMapper mDictionaryPtMapper;

    @Autowired
    private MDictionaryPtService mDictionaryPtService;

    @Autowired
    private MDictionaryPtQueryService mDictionaryPtQueryService;

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

    private MockMvc restMDictionaryPtMockMvc;

    private MDictionaryPt mDictionaryPt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDictionaryPtResource mDictionaryPtResource = new MDictionaryPtResource(mDictionaryPtService, mDictionaryPtQueryService);
        this.restMDictionaryPtMockMvc = MockMvcBuilders.standaloneSetup(mDictionaryPtResource)
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
    public static MDictionaryPt createEntity(EntityManager em) {
        MDictionaryPt mDictionaryPt = new MDictionaryPt()
            .key(DEFAULT_KEY)
            .message(DEFAULT_MESSAGE);
        return mDictionaryPt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDictionaryPt createUpdatedEntity(EntityManager em) {
        MDictionaryPt mDictionaryPt = new MDictionaryPt()
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        return mDictionaryPt;
    }

    @BeforeEach
    public void initTest() {
        mDictionaryPt = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDictionaryPt() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryPtRepository.findAll().size();

        // Create the MDictionaryPt
        MDictionaryPtDTO mDictionaryPtDTO = mDictionaryPtMapper.toDto(mDictionaryPt);
        restMDictionaryPtMockMvc.perform(post("/api/m-dictionary-pts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryPtDTO)))
            .andExpect(status().isCreated());

        // Validate the MDictionaryPt in the database
        List<MDictionaryPt> mDictionaryPtList = mDictionaryPtRepository.findAll();
        assertThat(mDictionaryPtList).hasSize(databaseSizeBeforeCreate + 1);
        MDictionaryPt testMDictionaryPt = mDictionaryPtList.get(mDictionaryPtList.size() - 1);
        assertThat(testMDictionaryPt.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDictionaryPt.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMDictionaryPtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryPtRepository.findAll().size();

        // Create the MDictionaryPt with an existing ID
        mDictionaryPt.setId(1L);
        MDictionaryPtDTO mDictionaryPtDTO = mDictionaryPtMapper.toDto(mDictionaryPt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDictionaryPtMockMvc.perform(post("/api/m-dictionary-pts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryPtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryPt in the database
        List<MDictionaryPt> mDictionaryPtList = mDictionaryPtRepository.findAll();
        assertThat(mDictionaryPtList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDictionaryPts() throws Exception {
        // Initialize the database
        mDictionaryPtRepository.saveAndFlush(mDictionaryPt);

        // Get all the mDictionaryPtList
        restMDictionaryPtMockMvc.perform(get("/api/m-dictionary-pts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryPt.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMDictionaryPt() throws Exception {
        // Initialize the database
        mDictionaryPtRepository.saveAndFlush(mDictionaryPt);

        // Get the mDictionaryPt
        restMDictionaryPtMockMvc.perform(get("/api/m-dictionary-pts/{id}", mDictionaryPt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDictionaryPt.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDictionaryPtShouldBeFound(String filter) throws Exception {
        restMDictionaryPtMockMvc.perform(get("/api/m-dictionary-pts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryPt.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMDictionaryPtMockMvc.perform(get("/api/m-dictionary-pts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDictionaryPtShouldNotBeFound(String filter) throws Exception {
        restMDictionaryPtMockMvc.perform(get("/api/m-dictionary-pts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDictionaryPtMockMvc.perform(get("/api/m-dictionary-pts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDictionaryPt() throws Exception {
        // Get the mDictionaryPt
        restMDictionaryPtMockMvc.perform(get("/api/m-dictionary-pts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDictionaryPt() throws Exception {
        // Initialize the database
        mDictionaryPtRepository.saveAndFlush(mDictionaryPt);

        int databaseSizeBeforeUpdate = mDictionaryPtRepository.findAll().size();

        // Update the mDictionaryPt
        MDictionaryPt updatedMDictionaryPt = mDictionaryPtRepository.findById(mDictionaryPt.getId()).get();
        // Disconnect from session so that the updates on updatedMDictionaryPt are not directly saved in db
        em.detach(updatedMDictionaryPt);
        updatedMDictionaryPt
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        MDictionaryPtDTO mDictionaryPtDTO = mDictionaryPtMapper.toDto(updatedMDictionaryPt);

        restMDictionaryPtMockMvc.perform(put("/api/m-dictionary-pts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryPtDTO)))
            .andExpect(status().isOk());

        // Validate the MDictionaryPt in the database
        List<MDictionaryPt> mDictionaryPtList = mDictionaryPtRepository.findAll();
        assertThat(mDictionaryPtList).hasSize(databaseSizeBeforeUpdate);
        MDictionaryPt testMDictionaryPt = mDictionaryPtList.get(mDictionaryPtList.size() - 1);
        assertThat(testMDictionaryPt.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDictionaryPt.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDictionaryPt() throws Exception {
        int databaseSizeBeforeUpdate = mDictionaryPtRepository.findAll().size();

        // Create the MDictionaryPt
        MDictionaryPtDTO mDictionaryPtDTO = mDictionaryPtMapper.toDto(mDictionaryPt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDictionaryPtMockMvc.perform(put("/api/m-dictionary-pts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryPtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryPt in the database
        List<MDictionaryPt> mDictionaryPtList = mDictionaryPtRepository.findAll();
        assertThat(mDictionaryPtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDictionaryPt() throws Exception {
        // Initialize the database
        mDictionaryPtRepository.saveAndFlush(mDictionaryPt);

        int databaseSizeBeforeDelete = mDictionaryPtRepository.findAll().size();

        // Delete the mDictionaryPt
        restMDictionaryPtMockMvc.perform(delete("/api/m-dictionary-pts/{id}", mDictionaryPt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDictionaryPt> mDictionaryPtList = mDictionaryPtRepository.findAll();
        assertThat(mDictionaryPtList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryPt.class);
        MDictionaryPt mDictionaryPt1 = new MDictionaryPt();
        mDictionaryPt1.setId(1L);
        MDictionaryPt mDictionaryPt2 = new MDictionaryPt();
        mDictionaryPt2.setId(mDictionaryPt1.getId());
        assertThat(mDictionaryPt1).isEqualTo(mDictionaryPt2);
        mDictionaryPt2.setId(2L);
        assertThat(mDictionaryPt1).isNotEqualTo(mDictionaryPt2);
        mDictionaryPt1.setId(null);
        assertThat(mDictionaryPt1).isNotEqualTo(mDictionaryPt2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryPtDTO.class);
        MDictionaryPtDTO mDictionaryPtDTO1 = new MDictionaryPtDTO();
        mDictionaryPtDTO1.setId(1L);
        MDictionaryPtDTO mDictionaryPtDTO2 = new MDictionaryPtDTO();
        assertThat(mDictionaryPtDTO1).isNotEqualTo(mDictionaryPtDTO2);
        mDictionaryPtDTO2.setId(mDictionaryPtDTO1.getId());
        assertThat(mDictionaryPtDTO1).isEqualTo(mDictionaryPtDTO2);
        mDictionaryPtDTO2.setId(2L);
        assertThat(mDictionaryPtDTO1).isNotEqualTo(mDictionaryPtDTO2);
        mDictionaryPtDTO1.setId(null);
        assertThat(mDictionaryPtDTO1).isNotEqualTo(mDictionaryPtDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDictionaryPtMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDictionaryPtMapper.fromId(null)).isNull();
    }
}
