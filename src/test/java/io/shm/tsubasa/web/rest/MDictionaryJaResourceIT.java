package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDictionaryJa;
import io.shm.tsubasa.repository.MDictionaryJaRepository;
import io.shm.tsubasa.service.MDictionaryJaService;
import io.shm.tsubasa.service.dto.MDictionaryJaDTO;
import io.shm.tsubasa.service.mapper.MDictionaryJaMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDictionaryJaCriteria;
import io.shm.tsubasa.service.MDictionaryJaQueryService;

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
 * Integration tests for the {@Link MDictionaryJaResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDictionaryJaResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MDictionaryJaRepository mDictionaryJaRepository;

    @Autowired
    private MDictionaryJaMapper mDictionaryJaMapper;

    @Autowired
    private MDictionaryJaService mDictionaryJaService;

    @Autowired
    private MDictionaryJaQueryService mDictionaryJaQueryService;

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

    private MockMvc restMDictionaryJaMockMvc;

    private MDictionaryJa mDictionaryJa;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDictionaryJaResource mDictionaryJaResource = new MDictionaryJaResource(mDictionaryJaService, mDictionaryJaQueryService);
        this.restMDictionaryJaMockMvc = MockMvcBuilders.standaloneSetup(mDictionaryJaResource)
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
    public static MDictionaryJa createEntity(EntityManager em) {
        MDictionaryJa mDictionaryJa = new MDictionaryJa()
            .key(DEFAULT_KEY)
            .message(DEFAULT_MESSAGE);
        return mDictionaryJa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDictionaryJa createUpdatedEntity(EntityManager em) {
        MDictionaryJa mDictionaryJa = new MDictionaryJa()
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        return mDictionaryJa;
    }

    @BeforeEach
    public void initTest() {
        mDictionaryJa = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDictionaryJa() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryJaRepository.findAll().size();

        // Create the MDictionaryJa
        MDictionaryJaDTO mDictionaryJaDTO = mDictionaryJaMapper.toDto(mDictionaryJa);
        restMDictionaryJaMockMvc.perform(post("/api/m-dictionary-jas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryJaDTO)))
            .andExpect(status().isCreated());

        // Validate the MDictionaryJa in the database
        List<MDictionaryJa> mDictionaryJaList = mDictionaryJaRepository.findAll();
        assertThat(mDictionaryJaList).hasSize(databaseSizeBeforeCreate + 1);
        MDictionaryJa testMDictionaryJa = mDictionaryJaList.get(mDictionaryJaList.size() - 1);
        assertThat(testMDictionaryJa.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDictionaryJa.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMDictionaryJaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryJaRepository.findAll().size();

        // Create the MDictionaryJa with an existing ID
        mDictionaryJa.setId(1L);
        MDictionaryJaDTO mDictionaryJaDTO = mDictionaryJaMapper.toDto(mDictionaryJa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDictionaryJaMockMvc.perform(post("/api/m-dictionary-jas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryJaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryJa in the database
        List<MDictionaryJa> mDictionaryJaList = mDictionaryJaRepository.findAll();
        assertThat(mDictionaryJaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDictionaryJas() throws Exception {
        // Initialize the database
        mDictionaryJaRepository.saveAndFlush(mDictionaryJa);

        // Get all the mDictionaryJaList
        restMDictionaryJaMockMvc.perform(get("/api/m-dictionary-jas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryJa.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMDictionaryJa() throws Exception {
        // Initialize the database
        mDictionaryJaRepository.saveAndFlush(mDictionaryJa);

        // Get the mDictionaryJa
        restMDictionaryJaMockMvc.perform(get("/api/m-dictionary-jas/{id}", mDictionaryJa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDictionaryJa.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDictionaryJaShouldBeFound(String filter) throws Exception {
        restMDictionaryJaMockMvc.perform(get("/api/m-dictionary-jas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryJa.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMDictionaryJaMockMvc.perform(get("/api/m-dictionary-jas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDictionaryJaShouldNotBeFound(String filter) throws Exception {
        restMDictionaryJaMockMvc.perform(get("/api/m-dictionary-jas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDictionaryJaMockMvc.perform(get("/api/m-dictionary-jas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDictionaryJa() throws Exception {
        // Get the mDictionaryJa
        restMDictionaryJaMockMvc.perform(get("/api/m-dictionary-jas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDictionaryJa() throws Exception {
        // Initialize the database
        mDictionaryJaRepository.saveAndFlush(mDictionaryJa);

        int databaseSizeBeforeUpdate = mDictionaryJaRepository.findAll().size();

        // Update the mDictionaryJa
        MDictionaryJa updatedMDictionaryJa = mDictionaryJaRepository.findById(mDictionaryJa.getId()).get();
        // Disconnect from session so that the updates on updatedMDictionaryJa are not directly saved in db
        em.detach(updatedMDictionaryJa);
        updatedMDictionaryJa
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        MDictionaryJaDTO mDictionaryJaDTO = mDictionaryJaMapper.toDto(updatedMDictionaryJa);

        restMDictionaryJaMockMvc.perform(put("/api/m-dictionary-jas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryJaDTO)))
            .andExpect(status().isOk());

        // Validate the MDictionaryJa in the database
        List<MDictionaryJa> mDictionaryJaList = mDictionaryJaRepository.findAll();
        assertThat(mDictionaryJaList).hasSize(databaseSizeBeforeUpdate);
        MDictionaryJa testMDictionaryJa = mDictionaryJaList.get(mDictionaryJaList.size() - 1);
        assertThat(testMDictionaryJa.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDictionaryJa.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDictionaryJa() throws Exception {
        int databaseSizeBeforeUpdate = mDictionaryJaRepository.findAll().size();

        // Create the MDictionaryJa
        MDictionaryJaDTO mDictionaryJaDTO = mDictionaryJaMapper.toDto(mDictionaryJa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDictionaryJaMockMvc.perform(put("/api/m-dictionary-jas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryJaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryJa in the database
        List<MDictionaryJa> mDictionaryJaList = mDictionaryJaRepository.findAll();
        assertThat(mDictionaryJaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDictionaryJa() throws Exception {
        // Initialize the database
        mDictionaryJaRepository.saveAndFlush(mDictionaryJa);

        int databaseSizeBeforeDelete = mDictionaryJaRepository.findAll().size();

        // Delete the mDictionaryJa
        restMDictionaryJaMockMvc.perform(delete("/api/m-dictionary-jas/{id}", mDictionaryJa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDictionaryJa> mDictionaryJaList = mDictionaryJaRepository.findAll();
        assertThat(mDictionaryJaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryJa.class);
        MDictionaryJa mDictionaryJa1 = new MDictionaryJa();
        mDictionaryJa1.setId(1L);
        MDictionaryJa mDictionaryJa2 = new MDictionaryJa();
        mDictionaryJa2.setId(mDictionaryJa1.getId());
        assertThat(mDictionaryJa1).isEqualTo(mDictionaryJa2);
        mDictionaryJa2.setId(2L);
        assertThat(mDictionaryJa1).isNotEqualTo(mDictionaryJa2);
        mDictionaryJa1.setId(null);
        assertThat(mDictionaryJa1).isNotEqualTo(mDictionaryJa2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryJaDTO.class);
        MDictionaryJaDTO mDictionaryJaDTO1 = new MDictionaryJaDTO();
        mDictionaryJaDTO1.setId(1L);
        MDictionaryJaDTO mDictionaryJaDTO2 = new MDictionaryJaDTO();
        assertThat(mDictionaryJaDTO1).isNotEqualTo(mDictionaryJaDTO2);
        mDictionaryJaDTO2.setId(mDictionaryJaDTO1.getId());
        assertThat(mDictionaryJaDTO1).isEqualTo(mDictionaryJaDTO2);
        mDictionaryJaDTO2.setId(2L);
        assertThat(mDictionaryJaDTO1).isNotEqualTo(mDictionaryJaDTO2);
        mDictionaryJaDTO1.setId(null);
        assertThat(mDictionaryJaDTO1).isNotEqualTo(mDictionaryJaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDictionaryJaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDictionaryJaMapper.fromId(null)).isNull();
    }
}
