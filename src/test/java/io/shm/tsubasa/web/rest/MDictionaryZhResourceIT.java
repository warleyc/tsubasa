package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDictionaryZh;
import io.shm.tsubasa.repository.MDictionaryZhRepository;
import io.shm.tsubasa.service.MDictionaryZhService;
import io.shm.tsubasa.service.dto.MDictionaryZhDTO;
import io.shm.tsubasa.service.mapper.MDictionaryZhMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDictionaryZhCriteria;
import io.shm.tsubasa.service.MDictionaryZhQueryService;

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
 * Integration tests for the {@Link MDictionaryZhResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDictionaryZhResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MDictionaryZhRepository mDictionaryZhRepository;

    @Autowired
    private MDictionaryZhMapper mDictionaryZhMapper;

    @Autowired
    private MDictionaryZhService mDictionaryZhService;

    @Autowired
    private MDictionaryZhQueryService mDictionaryZhQueryService;

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

    private MockMvc restMDictionaryZhMockMvc;

    private MDictionaryZh mDictionaryZh;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDictionaryZhResource mDictionaryZhResource = new MDictionaryZhResource(mDictionaryZhService, mDictionaryZhQueryService);
        this.restMDictionaryZhMockMvc = MockMvcBuilders.standaloneSetup(mDictionaryZhResource)
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
    public static MDictionaryZh createEntity(EntityManager em) {
        MDictionaryZh mDictionaryZh = new MDictionaryZh()
            .key(DEFAULT_KEY)
            .message(DEFAULT_MESSAGE);
        return mDictionaryZh;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDictionaryZh createUpdatedEntity(EntityManager em) {
        MDictionaryZh mDictionaryZh = new MDictionaryZh()
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        return mDictionaryZh;
    }

    @BeforeEach
    public void initTest() {
        mDictionaryZh = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDictionaryZh() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryZhRepository.findAll().size();

        // Create the MDictionaryZh
        MDictionaryZhDTO mDictionaryZhDTO = mDictionaryZhMapper.toDto(mDictionaryZh);
        restMDictionaryZhMockMvc.perform(post("/api/m-dictionary-zhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryZhDTO)))
            .andExpect(status().isCreated());

        // Validate the MDictionaryZh in the database
        List<MDictionaryZh> mDictionaryZhList = mDictionaryZhRepository.findAll();
        assertThat(mDictionaryZhList).hasSize(databaseSizeBeforeCreate + 1);
        MDictionaryZh testMDictionaryZh = mDictionaryZhList.get(mDictionaryZhList.size() - 1);
        assertThat(testMDictionaryZh.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDictionaryZh.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMDictionaryZhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryZhRepository.findAll().size();

        // Create the MDictionaryZh with an existing ID
        mDictionaryZh.setId(1L);
        MDictionaryZhDTO mDictionaryZhDTO = mDictionaryZhMapper.toDto(mDictionaryZh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDictionaryZhMockMvc.perform(post("/api/m-dictionary-zhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryZhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryZh in the database
        List<MDictionaryZh> mDictionaryZhList = mDictionaryZhRepository.findAll();
        assertThat(mDictionaryZhList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDictionaryZhs() throws Exception {
        // Initialize the database
        mDictionaryZhRepository.saveAndFlush(mDictionaryZh);

        // Get all the mDictionaryZhList
        restMDictionaryZhMockMvc.perform(get("/api/m-dictionary-zhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryZh.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMDictionaryZh() throws Exception {
        // Initialize the database
        mDictionaryZhRepository.saveAndFlush(mDictionaryZh);

        // Get the mDictionaryZh
        restMDictionaryZhMockMvc.perform(get("/api/m-dictionary-zhs/{id}", mDictionaryZh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDictionaryZh.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDictionaryZhShouldBeFound(String filter) throws Exception {
        restMDictionaryZhMockMvc.perform(get("/api/m-dictionary-zhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryZh.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMDictionaryZhMockMvc.perform(get("/api/m-dictionary-zhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDictionaryZhShouldNotBeFound(String filter) throws Exception {
        restMDictionaryZhMockMvc.perform(get("/api/m-dictionary-zhs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDictionaryZhMockMvc.perform(get("/api/m-dictionary-zhs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDictionaryZh() throws Exception {
        // Get the mDictionaryZh
        restMDictionaryZhMockMvc.perform(get("/api/m-dictionary-zhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDictionaryZh() throws Exception {
        // Initialize the database
        mDictionaryZhRepository.saveAndFlush(mDictionaryZh);

        int databaseSizeBeforeUpdate = mDictionaryZhRepository.findAll().size();

        // Update the mDictionaryZh
        MDictionaryZh updatedMDictionaryZh = mDictionaryZhRepository.findById(mDictionaryZh.getId()).get();
        // Disconnect from session so that the updates on updatedMDictionaryZh are not directly saved in db
        em.detach(updatedMDictionaryZh);
        updatedMDictionaryZh
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        MDictionaryZhDTO mDictionaryZhDTO = mDictionaryZhMapper.toDto(updatedMDictionaryZh);

        restMDictionaryZhMockMvc.perform(put("/api/m-dictionary-zhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryZhDTO)))
            .andExpect(status().isOk());

        // Validate the MDictionaryZh in the database
        List<MDictionaryZh> mDictionaryZhList = mDictionaryZhRepository.findAll();
        assertThat(mDictionaryZhList).hasSize(databaseSizeBeforeUpdate);
        MDictionaryZh testMDictionaryZh = mDictionaryZhList.get(mDictionaryZhList.size() - 1);
        assertThat(testMDictionaryZh.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDictionaryZh.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDictionaryZh() throws Exception {
        int databaseSizeBeforeUpdate = mDictionaryZhRepository.findAll().size();

        // Create the MDictionaryZh
        MDictionaryZhDTO mDictionaryZhDTO = mDictionaryZhMapper.toDto(mDictionaryZh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDictionaryZhMockMvc.perform(put("/api/m-dictionary-zhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryZhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryZh in the database
        List<MDictionaryZh> mDictionaryZhList = mDictionaryZhRepository.findAll();
        assertThat(mDictionaryZhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDictionaryZh() throws Exception {
        // Initialize the database
        mDictionaryZhRepository.saveAndFlush(mDictionaryZh);

        int databaseSizeBeforeDelete = mDictionaryZhRepository.findAll().size();

        // Delete the mDictionaryZh
        restMDictionaryZhMockMvc.perform(delete("/api/m-dictionary-zhs/{id}", mDictionaryZh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDictionaryZh> mDictionaryZhList = mDictionaryZhRepository.findAll();
        assertThat(mDictionaryZhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryZh.class);
        MDictionaryZh mDictionaryZh1 = new MDictionaryZh();
        mDictionaryZh1.setId(1L);
        MDictionaryZh mDictionaryZh2 = new MDictionaryZh();
        mDictionaryZh2.setId(mDictionaryZh1.getId());
        assertThat(mDictionaryZh1).isEqualTo(mDictionaryZh2);
        mDictionaryZh2.setId(2L);
        assertThat(mDictionaryZh1).isNotEqualTo(mDictionaryZh2);
        mDictionaryZh1.setId(null);
        assertThat(mDictionaryZh1).isNotEqualTo(mDictionaryZh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryZhDTO.class);
        MDictionaryZhDTO mDictionaryZhDTO1 = new MDictionaryZhDTO();
        mDictionaryZhDTO1.setId(1L);
        MDictionaryZhDTO mDictionaryZhDTO2 = new MDictionaryZhDTO();
        assertThat(mDictionaryZhDTO1).isNotEqualTo(mDictionaryZhDTO2);
        mDictionaryZhDTO2.setId(mDictionaryZhDTO1.getId());
        assertThat(mDictionaryZhDTO1).isEqualTo(mDictionaryZhDTO2);
        mDictionaryZhDTO2.setId(2L);
        assertThat(mDictionaryZhDTO1).isNotEqualTo(mDictionaryZhDTO2);
        mDictionaryZhDTO1.setId(null);
        assertThat(mDictionaryZhDTO1).isNotEqualTo(mDictionaryZhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDictionaryZhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDictionaryZhMapper.fromId(null)).isNull();
    }
}
