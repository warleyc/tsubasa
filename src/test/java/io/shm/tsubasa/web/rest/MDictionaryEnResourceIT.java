package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDictionaryEn;
import io.shm.tsubasa.repository.MDictionaryEnRepository;
import io.shm.tsubasa.service.MDictionaryEnService;
import io.shm.tsubasa.service.dto.MDictionaryEnDTO;
import io.shm.tsubasa.service.mapper.MDictionaryEnMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDictionaryEnCriteria;
import io.shm.tsubasa.service.MDictionaryEnQueryService;

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
 * Integration tests for the {@Link MDictionaryEnResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDictionaryEnResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MDictionaryEnRepository mDictionaryEnRepository;

    @Autowired
    private MDictionaryEnMapper mDictionaryEnMapper;

    @Autowired
    private MDictionaryEnService mDictionaryEnService;

    @Autowired
    private MDictionaryEnQueryService mDictionaryEnQueryService;

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

    private MockMvc restMDictionaryEnMockMvc;

    private MDictionaryEn mDictionaryEn;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDictionaryEnResource mDictionaryEnResource = new MDictionaryEnResource(mDictionaryEnService, mDictionaryEnQueryService);
        this.restMDictionaryEnMockMvc = MockMvcBuilders.standaloneSetup(mDictionaryEnResource)
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
    public static MDictionaryEn createEntity(EntityManager em) {
        MDictionaryEn mDictionaryEn = new MDictionaryEn()
            .key(DEFAULT_KEY)
            .message(DEFAULT_MESSAGE);
        return mDictionaryEn;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDictionaryEn createUpdatedEntity(EntityManager em) {
        MDictionaryEn mDictionaryEn = new MDictionaryEn()
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        return mDictionaryEn;
    }

    @BeforeEach
    public void initTest() {
        mDictionaryEn = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDictionaryEn() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryEnRepository.findAll().size();

        // Create the MDictionaryEn
        MDictionaryEnDTO mDictionaryEnDTO = mDictionaryEnMapper.toDto(mDictionaryEn);
        restMDictionaryEnMockMvc.perform(post("/api/m-dictionary-ens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryEnDTO)))
            .andExpect(status().isCreated());

        // Validate the MDictionaryEn in the database
        List<MDictionaryEn> mDictionaryEnList = mDictionaryEnRepository.findAll();
        assertThat(mDictionaryEnList).hasSize(databaseSizeBeforeCreate + 1);
        MDictionaryEn testMDictionaryEn = mDictionaryEnList.get(mDictionaryEnList.size() - 1);
        assertThat(testMDictionaryEn.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDictionaryEn.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMDictionaryEnWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryEnRepository.findAll().size();

        // Create the MDictionaryEn with an existing ID
        mDictionaryEn.setId(1L);
        MDictionaryEnDTO mDictionaryEnDTO = mDictionaryEnMapper.toDto(mDictionaryEn);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDictionaryEnMockMvc.perform(post("/api/m-dictionary-ens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryEnDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryEn in the database
        List<MDictionaryEn> mDictionaryEnList = mDictionaryEnRepository.findAll();
        assertThat(mDictionaryEnList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDictionaryEns() throws Exception {
        // Initialize the database
        mDictionaryEnRepository.saveAndFlush(mDictionaryEn);

        // Get all the mDictionaryEnList
        restMDictionaryEnMockMvc.perform(get("/api/m-dictionary-ens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryEn.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMDictionaryEn() throws Exception {
        // Initialize the database
        mDictionaryEnRepository.saveAndFlush(mDictionaryEn);

        // Get the mDictionaryEn
        restMDictionaryEnMockMvc.perform(get("/api/m-dictionary-ens/{id}", mDictionaryEn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDictionaryEn.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDictionaryEnShouldBeFound(String filter) throws Exception {
        restMDictionaryEnMockMvc.perform(get("/api/m-dictionary-ens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryEn.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMDictionaryEnMockMvc.perform(get("/api/m-dictionary-ens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDictionaryEnShouldNotBeFound(String filter) throws Exception {
        restMDictionaryEnMockMvc.perform(get("/api/m-dictionary-ens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDictionaryEnMockMvc.perform(get("/api/m-dictionary-ens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDictionaryEn() throws Exception {
        // Get the mDictionaryEn
        restMDictionaryEnMockMvc.perform(get("/api/m-dictionary-ens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDictionaryEn() throws Exception {
        // Initialize the database
        mDictionaryEnRepository.saveAndFlush(mDictionaryEn);

        int databaseSizeBeforeUpdate = mDictionaryEnRepository.findAll().size();

        // Update the mDictionaryEn
        MDictionaryEn updatedMDictionaryEn = mDictionaryEnRepository.findById(mDictionaryEn.getId()).get();
        // Disconnect from session so that the updates on updatedMDictionaryEn are not directly saved in db
        em.detach(updatedMDictionaryEn);
        updatedMDictionaryEn
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        MDictionaryEnDTO mDictionaryEnDTO = mDictionaryEnMapper.toDto(updatedMDictionaryEn);

        restMDictionaryEnMockMvc.perform(put("/api/m-dictionary-ens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryEnDTO)))
            .andExpect(status().isOk());

        // Validate the MDictionaryEn in the database
        List<MDictionaryEn> mDictionaryEnList = mDictionaryEnRepository.findAll();
        assertThat(mDictionaryEnList).hasSize(databaseSizeBeforeUpdate);
        MDictionaryEn testMDictionaryEn = mDictionaryEnList.get(mDictionaryEnList.size() - 1);
        assertThat(testMDictionaryEn.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDictionaryEn.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDictionaryEn() throws Exception {
        int databaseSizeBeforeUpdate = mDictionaryEnRepository.findAll().size();

        // Create the MDictionaryEn
        MDictionaryEnDTO mDictionaryEnDTO = mDictionaryEnMapper.toDto(mDictionaryEn);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDictionaryEnMockMvc.perform(put("/api/m-dictionary-ens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryEnDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryEn in the database
        List<MDictionaryEn> mDictionaryEnList = mDictionaryEnRepository.findAll();
        assertThat(mDictionaryEnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDictionaryEn() throws Exception {
        // Initialize the database
        mDictionaryEnRepository.saveAndFlush(mDictionaryEn);

        int databaseSizeBeforeDelete = mDictionaryEnRepository.findAll().size();

        // Delete the mDictionaryEn
        restMDictionaryEnMockMvc.perform(delete("/api/m-dictionary-ens/{id}", mDictionaryEn.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDictionaryEn> mDictionaryEnList = mDictionaryEnRepository.findAll();
        assertThat(mDictionaryEnList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryEn.class);
        MDictionaryEn mDictionaryEn1 = new MDictionaryEn();
        mDictionaryEn1.setId(1L);
        MDictionaryEn mDictionaryEn2 = new MDictionaryEn();
        mDictionaryEn2.setId(mDictionaryEn1.getId());
        assertThat(mDictionaryEn1).isEqualTo(mDictionaryEn2);
        mDictionaryEn2.setId(2L);
        assertThat(mDictionaryEn1).isNotEqualTo(mDictionaryEn2);
        mDictionaryEn1.setId(null);
        assertThat(mDictionaryEn1).isNotEqualTo(mDictionaryEn2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryEnDTO.class);
        MDictionaryEnDTO mDictionaryEnDTO1 = new MDictionaryEnDTO();
        mDictionaryEnDTO1.setId(1L);
        MDictionaryEnDTO mDictionaryEnDTO2 = new MDictionaryEnDTO();
        assertThat(mDictionaryEnDTO1).isNotEqualTo(mDictionaryEnDTO2);
        mDictionaryEnDTO2.setId(mDictionaryEnDTO1.getId());
        assertThat(mDictionaryEnDTO1).isEqualTo(mDictionaryEnDTO2);
        mDictionaryEnDTO2.setId(2L);
        assertThat(mDictionaryEnDTO1).isNotEqualTo(mDictionaryEnDTO2);
        mDictionaryEnDTO1.setId(null);
        assertThat(mDictionaryEnDTO1).isNotEqualTo(mDictionaryEnDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDictionaryEnMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDictionaryEnMapper.fromId(null)).isNull();
    }
}
