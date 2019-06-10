package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDictionaryFr;
import io.shm.tsubasa.repository.MDictionaryFrRepository;
import io.shm.tsubasa.service.MDictionaryFrService;
import io.shm.tsubasa.service.dto.MDictionaryFrDTO;
import io.shm.tsubasa.service.mapper.MDictionaryFrMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDictionaryFrCriteria;
import io.shm.tsubasa.service.MDictionaryFrQueryService;

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
 * Integration tests for the {@Link MDictionaryFrResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDictionaryFrResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MDictionaryFrRepository mDictionaryFrRepository;

    @Autowired
    private MDictionaryFrMapper mDictionaryFrMapper;

    @Autowired
    private MDictionaryFrService mDictionaryFrService;

    @Autowired
    private MDictionaryFrQueryService mDictionaryFrQueryService;

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

    private MockMvc restMDictionaryFrMockMvc;

    private MDictionaryFr mDictionaryFr;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDictionaryFrResource mDictionaryFrResource = new MDictionaryFrResource(mDictionaryFrService, mDictionaryFrQueryService);
        this.restMDictionaryFrMockMvc = MockMvcBuilders.standaloneSetup(mDictionaryFrResource)
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
    public static MDictionaryFr createEntity(EntityManager em) {
        MDictionaryFr mDictionaryFr = new MDictionaryFr()
            .key(DEFAULT_KEY)
            .message(DEFAULT_MESSAGE);
        return mDictionaryFr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDictionaryFr createUpdatedEntity(EntityManager em) {
        MDictionaryFr mDictionaryFr = new MDictionaryFr()
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        return mDictionaryFr;
    }

    @BeforeEach
    public void initTest() {
        mDictionaryFr = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDictionaryFr() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryFrRepository.findAll().size();

        // Create the MDictionaryFr
        MDictionaryFrDTO mDictionaryFrDTO = mDictionaryFrMapper.toDto(mDictionaryFr);
        restMDictionaryFrMockMvc.perform(post("/api/m-dictionary-frs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryFrDTO)))
            .andExpect(status().isCreated());

        // Validate the MDictionaryFr in the database
        List<MDictionaryFr> mDictionaryFrList = mDictionaryFrRepository.findAll();
        assertThat(mDictionaryFrList).hasSize(databaseSizeBeforeCreate + 1);
        MDictionaryFr testMDictionaryFr = mDictionaryFrList.get(mDictionaryFrList.size() - 1);
        assertThat(testMDictionaryFr.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDictionaryFr.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMDictionaryFrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryFrRepository.findAll().size();

        // Create the MDictionaryFr with an existing ID
        mDictionaryFr.setId(1L);
        MDictionaryFrDTO mDictionaryFrDTO = mDictionaryFrMapper.toDto(mDictionaryFr);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDictionaryFrMockMvc.perform(post("/api/m-dictionary-frs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryFrDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryFr in the database
        List<MDictionaryFr> mDictionaryFrList = mDictionaryFrRepository.findAll();
        assertThat(mDictionaryFrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDictionaryFrs() throws Exception {
        // Initialize the database
        mDictionaryFrRepository.saveAndFlush(mDictionaryFr);

        // Get all the mDictionaryFrList
        restMDictionaryFrMockMvc.perform(get("/api/m-dictionary-frs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryFr.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMDictionaryFr() throws Exception {
        // Initialize the database
        mDictionaryFrRepository.saveAndFlush(mDictionaryFr);

        // Get the mDictionaryFr
        restMDictionaryFrMockMvc.perform(get("/api/m-dictionary-frs/{id}", mDictionaryFr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDictionaryFr.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDictionaryFrShouldBeFound(String filter) throws Exception {
        restMDictionaryFrMockMvc.perform(get("/api/m-dictionary-frs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryFr.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMDictionaryFrMockMvc.perform(get("/api/m-dictionary-frs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDictionaryFrShouldNotBeFound(String filter) throws Exception {
        restMDictionaryFrMockMvc.perform(get("/api/m-dictionary-frs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDictionaryFrMockMvc.perform(get("/api/m-dictionary-frs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDictionaryFr() throws Exception {
        // Get the mDictionaryFr
        restMDictionaryFrMockMvc.perform(get("/api/m-dictionary-frs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDictionaryFr() throws Exception {
        // Initialize the database
        mDictionaryFrRepository.saveAndFlush(mDictionaryFr);

        int databaseSizeBeforeUpdate = mDictionaryFrRepository.findAll().size();

        // Update the mDictionaryFr
        MDictionaryFr updatedMDictionaryFr = mDictionaryFrRepository.findById(mDictionaryFr.getId()).get();
        // Disconnect from session so that the updates on updatedMDictionaryFr are not directly saved in db
        em.detach(updatedMDictionaryFr);
        updatedMDictionaryFr
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        MDictionaryFrDTO mDictionaryFrDTO = mDictionaryFrMapper.toDto(updatedMDictionaryFr);

        restMDictionaryFrMockMvc.perform(put("/api/m-dictionary-frs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryFrDTO)))
            .andExpect(status().isOk());

        // Validate the MDictionaryFr in the database
        List<MDictionaryFr> mDictionaryFrList = mDictionaryFrRepository.findAll();
        assertThat(mDictionaryFrList).hasSize(databaseSizeBeforeUpdate);
        MDictionaryFr testMDictionaryFr = mDictionaryFrList.get(mDictionaryFrList.size() - 1);
        assertThat(testMDictionaryFr.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDictionaryFr.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDictionaryFr() throws Exception {
        int databaseSizeBeforeUpdate = mDictionaryFrRepository.findAll().size();

        // Create the MDictionaryFr
        MDictionaryFrDTO mDictionaryFrDTO = mDictionaryFrMapper.toDto(mDictionaryFr);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDictionaryFrMockMvc.perform(put("/api/m-dictionary-frs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryFrDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryFr in the database
        List<MDictionaryFr> mDictionaryFrList = mDictionaryFrRepository.findAll();
        assertThat(mDictionaryFrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDictionaryFr() throws Exception {
        // Initialize the database
        mDictionaryFrRepository.saveAndFlush(mDictionaryFr);

        int databaseSizeBeforeDelete = mDictionaryFrRepository.findAll().size();

        // Delete the mDictionaryFr
        restMDictionaryFrMockMvc.perform(delete("/api/m-dictionary-frs/{id}", mDictionaryFr.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDictionaryFr> mDictionaryFrList = mDictionaryFrRepository.findAll();
        assertThat(mDictionaryFrList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryFr.class);
        MDictionaryFr mDictionaryFr1 = new MDictionaryFr();
        mDictionaryFr1.setId(1L);
        MDictionaryFr mDictionaryFr2 = new MDictionaryFr();
        mDictionaryFr2.setId(mDictionaryFr1.getId());
        assertThat(mDictionaryFr1).isEqualTo(mDictionaryFr2);
        mDictionaryFr2.setId(2L);
        assertThat(mDictionaryFr1).isNotEqualTo(mDictionaryFr2);
        mDictionaryFr1.setId(null);
        assertThat(mDictionaryFr1).isNotEqualTo(mDictionaryFr2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryFrDTO.class);
        MDictionaryFrDTO mDictionaryFrDTO1 = new MDictionaryFrDTO();
        mDictionaryFrDTO1.setId(1L);
        MDictionaryFrDTO mDictionaryFrDTO2 = new MDictionaryFrDTO();
        assertThat(mDictionaryFrDTO1).isNotEqualTo(mDictionaryFrDTO2);
        mDictionaryFrDTO2.setId(mDictionaryFrDTO1.getId());
        assertThat(mDictionaryFrDTO1).isEqualTo(mDictionaryFrDTO2);
        mDictionaryFrDTO2.setId(2L);
        assertThat(mDictionaryFrDTO1).isNotEqualTo(mDictionaryFrDTO2);
        mDictionaryFrDTO1.setId(null);
        assertThat(mDictionaryFrDTO1).isNotEqualTo(mDictionaryFrDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDictionaryFrMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDictionaryFrMapper.fromId(null)).isNull();
    }
}
