package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDictionaryEs;
import io.shm.tsubasa.repository.MDictionaryEsRepository;
import io.shm.tsubasa.service.MDictionaryEsService;
import io.shm.tsubasa.service.dto.MDictionaryEsDTO;
import io.shm.tsubasa.service.mapper.MDictionaryEsMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDictionaryEsCriteria;
import io.shm.tsubasa.service.MDictionaryEsQueryService;

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
 * Integration tests for the {@Link MDictionaryEsResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDictionaryEsResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MDictionaryEsRepository mDictionaryEsRepository;

    @Autowired
    private MDictionaryEsMapper mDictionaryEsMapper;

    @Autowired
    private MDictionaryEsService mDictionaryEsService;

    @Autowired
    private MDictionaryEsQueryService mDictionaryEsQueryService;

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

    private MockMvc restMDictionaryEsMockMvc;

    private MDictionaryEs mDictionaryEs;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDictionaryEsResource mDictionaryEsResource = new MDictionaryEsResource(mDictionaryEsService, mDictionaryEsQueryService);
        this.restMDictionaryEsMockMvc = MockMvcBuilders.standaloneSetup(mDictionaryEsResource)
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
    public static MDictionaryEs createEntity(EntityManager em) {
        MDictionaryEs mDictionaryEs = new MDictionaryEs()
            .key(DEFAULT_KEY)
            .message(DEFAULT_MESSAGE);
        return mDictionaryEs;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDictionaryEs createUpdatedEntity(EntityManager em) {
        MDictionaryEs mDictionaryEs = new MDictionaryEs()
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        return mDictionaryEs;
    }

    @BeforeEach
    public void initTest() {
        mDictionaryEs = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDictionaryEs() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryEsRepository.findAll().size();

        // Create the MDictionaryEs
        MDictionaryEsDTO mDictionaryEsDTO = mDictionaryEsMapper.toDto(mDictionaryEs);
        restMDictionaryEsMockMvc.perform(post("/api/m-dictionary-es")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryEsDTO)))
            .andExpect(status().isCreated());

        // Validate the MDictionaryEs in the database
        List<MDictionaryEs> mDictionaryEsList = mDictionaryEsRepository.findAll();
        assertThat(mDictionaryEsList).hasSize(databaseSizeBeforeCreate + 1);
        MDictionaryEs testMDictionaryEs = mDictionaryEsList.get(mDictionaryEsList.size() - 1);
        assertThat(testMDictionaryEs.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDictionaryEs.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMDictionaryEsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryEsRepository.findAll().size();

        // Create the MDictionaryEs with an existing ID
        mDictionaryEs.setId(1L);
        MDictionaryEsDTO mDictionaryEsDTO = mDictionaryEsMapper.toDto(mDictionaryEs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDictionaryEsMockMvc.perform(post("/api/m-dictionary-es")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryEsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryEs in the database
        List<MDictionaryEs> mDictionaryEsList = mDictionaryEsRepository.findAll();
        assertThat(mDictionaryEsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDictionaryEs() throws Exception {
        // Initialize the database
        mDictionaryEsRepository.saveAndFlush(mDictionaryEs);

        // Get all the mDictionaryEsList
        restMDictionaryEsMockMvc.perform(get("/api/m-dictionary-es?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryEs.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMDictionaryEs() throws Exception {
        // Initialize the database
        mDictionaryEsRepository.saveAndFlush(mDictionaryEs);

        // Get the mDictionaryEs
        restMDictionaryEsMockMvc.perform(get("/api/m-dictionary-es/{id}", mDictionaryEs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDictionaryEs.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDictionaryEsShouldBeFound(String filter) throws Exception {
        restMDictionaryEsMockMvc.perform(get("/api/m-dictionary-es?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryEs.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMDictionaryEsMockMvc.perform(get("/api/m-dictionary-es/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDictionaryEsShouldNotBeFound(String filter) throws Exception {
        restMDictionaryEsMockMvc.perform(get("/api/m-dictionary-es?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDictionaryEsMockMvc.perform(get("/api/m-dictionary-es/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDictionaryEs() throws Exception {
        // Get the mDictionaryEs
        restMDictionaryEsMockMvc.perform(get("/api/m-dictionary-es/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDictionaryEs() throws Exception {
        // Initialize the database
        mDictionaryEsRepository.saveAndFlush(mDictionaryEs);

        int databaseSizeBeforeUpdate = mDictionaryEsRepository.findAll().size();

        // Update the mDictionaryEs
        MDictionaryEs updatedMDictionaryEs = mDictionaryEsRepository.findById(mDictionaryEs.getId()).get();
        // Disconnect from session so that the updates on updatedMDictionaryEs are not directly saved in db
        em.detach(updatedMDictionaryEs);
        updatedMDictionaryEs
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        MDictionaryEsDTO mDictionaryEsDTO = mDictionaryEsMapper.toDto(updatedMDictionaryEs);

        restMDictionaryEsMockMvc.perform(put("/api/m-dictionary-es")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryEsDTO)))
            .andExpect(status().isOk());

        // Validate the MDictionaryEs in the database
        List<MDictionaryEs> mDictionaryEsList = mDictionaryEsRepository.findAll();
        assertThat(mDictionaryEsList).hasSize(databaseSizeBeforeUpdate);
        MDictionaryEs testMDictionaryEs = mDictionaryEsList.get(mDictionaryEsList.size() - 1);
        assertThat(testMDictionaryEs.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDictionaryEs.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDictionaryEs() throws Exception {
        int databaseSizeBeforeUpdate = mDictionaryEsRepository.findAll().size();

        // Create the MDictionaryEs
        MDictionaryEsDTO mDictionaryEsDTO = mDictionaryEsMapper.toDto(mDictionaryEs);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDictionaryEsMockMvc.perform(put("/api/m-dictionary-es")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryEsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryEs in the database
        List<MDictionaryEs> mDictionaryEsList = mDictionaryEsRepository.findAll();
        assertThat(mDictionaryEsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDictionaryEs() throws Exception {
        // Initialize the database
        mDictionaryEsRepository.saveAndFlush(mDictionaryEs);

        int databaseSizeBeforeDelete = mDictionaryEsRepository.findAll().size();

        // Delete the mDictionaryEs
        restMDictionaryEsMockMvc.perform(delete("/api/m-dictionary-es/{id}", mDictionaryEs.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDictionaryEs> mDictionaryEsList = mDictionaryEsRepository.findAll();
        assertThat(mDictionaryEsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryEs.class);
        MDictionaryEs mDictionaryEs1 = new MDictionaryEs();
        mDictionaryEs1.setId(1L);
        MDictionaryEs mDictionaryEs2 = new MDictionaryEs();
        mDictionaryEs2.setId(mDictionaryEs1.getId());
        assertThat(mDictionaryEs1).isEqualTo(mDictionaryEs2);
        mDictionaryEs2.setId(2L);
        assertThat(mDictionaryEs1).isNotEqualTo(mDictionaryEs2);
        mDictionaryEs1.setId(null);
        assertThat(mDictionaryEs1).isNotEqualTo(mDictionaryEs2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryEsDTO.class);
        MDictionaryEsDTO mDictionaryEsDTO1 = new MDictionaryEsDTO();
        mDictionaryEsDTO1.setId(1L);
        MDictionaryEsDTO mDictionaryEsDTO2 = new MDictionaryEsDTO();
        assertThat(mDictionaryEsDTO1).isNotEqualTo(mDictionaryEsDTO2);
        mDictionaryEsDTO2.setId(mDictionaryEsDTO1.getId());
        assertThat(mDictionaryEsDTO1).isEqualTo(mDictionaryEsDTO2);
        mDictionaryEsDTO2.setId(2L);
        assertThat(mDictionaryEsDTO1).isNotEqualTo(mDictionaryEsDTO2);
        mDictionaryEsDTO1.setId(null);
        assertThat(mDictionaryEsDTO1).isNotEqualTo(mDictionaryEsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDictionaryEsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDictionaryEsMapper.fromId(null)).isNull();
    }
}
