package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDictionaryDe;
import io.shm.tsubasa.repository.MDictionaryDeRepository;
import io.shm.tsubasa.service.MDictionaryDeService;
import io.shm.tsubasa.service.dto.MDictionaryDeDTO;
import io.shm.tsubasa.service.mapper.MDictionaryDeMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDictionaryDeCriteria;
import io.shm.tsubasa.service.MDictionaryDeQueryService;

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
 * Integration tests for the {@Link MDictionaryDeResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDictionaryDeResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MDictionaryDeRepository mDictionaryDeRepository;

    @Autowired
    private MDictionaryDeMapper mDictionaryDeMapper;

    @Autowired
    private MDictionaryDeService mDictionaryDeService;

    @Autowired
    private MDictionaryDeQueryService mDictionaryDeQueryService;

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

    private MockMvc restMDictionaryDeMockMvc;

    private MDictionaryDe mDictionaryDe;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDictionaryDeResource mDictionaryDeResource = new MDictionaryDeResource(mDictionaryDeService, mDictionaryDeQueryService);
        this.restMDictionaryDeMockMvc = MockMvcBuilders.standaloneSetup(mDictionaryDeResource)
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
    public static MDictionaryDe createEntity(EntityManager em) {
        MDictionaryDe mDictionaryDe = new MDictionaryDe()
            .key(DEFAULT_KEY)
            .message(DEFAULT_MESSAGE);
        return mDictionaryDe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDictionaryDe createUpdatedEntity(EntityManager em) {
        MDictionaryDe mDictionaryDe = new MDictionaryDe()
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        return mDictionaryDe;
    }

    @BeforeEach
    public void initTest() {
        mDictionaryDe = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDictionaryDe() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryDeRepository.findAll().size();

        // Create the MDictionaryDe
        MDictionaryDeDTO mDictionaryDeDTO = mDictionaryDeMapper.toDto(mDictionaryDe);
        restMDictionaryDeMockMvc.perform(post("/api/m-dictionary-des")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryDeDTO)))
            .andExpect(status().isCreated());

        // Validate the MDictionaryDe in the database
        List<MDictionaryDe> mDictionaryDeList = mDictionaryDeRepository.findAll();
        assertThat(mDictionaryDeList).hasSize(databaseSizeBeforeCreate + 1);
        MDictionaryDe testMDictionaryDe = mDictionaryDeList.get(mDictionaryDeList.size() - 1);
        assertThat(testMDictionaryDe.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDictionaryDe.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMDictionaryDeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryDeRepository.findAll().size();

        // Create the MDictionaryDe with an existing ID
        mDictionaryDe.setId(1L);
        MDictionaryDeDTO mDictionaryDeDTO = mDictionaryDeMapper.toDto(mDictionaryDe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDictionaryDeMockMvc.perform(post("/api/m-dictionary-des")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryDeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryDe in the database
        List<MDictionaryDe> mDictionaryDeList = mDictionaryDeRepository.findAll();
        assertThat(mDictionaryDeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDictionaryDes() throws Exception {
        // Initialize the database
        mDictionaryDeRepository.saveAndFlush(mDictionaryDe);

        // Get all the mDictionaryDeList
        restMDictionaryDeMockMvc.perform(get("/api/m-dictionary-des?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryDe.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMDictionaryDe() throws Exception {
        // Initialize the database
        mDictionaryDeRepository.saveAndFlush(mDictionaryDe);

        // Get the mDictionaryDe
        restMDictionaryDeMockMvc.perform(get("/api/m-dictionary-des/{id}", mDictionaryDe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDictionaryDe.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDictionaryDeShouldBeFound(String filter) throws Exception {
        restMDictionaryDeMockMvc.perform(get("/api/m-dictionary-des?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryDe.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMDictionaryDeMockMvc.perform(get("/api/m-dictionary-des/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDictionaryDeShouldNotBeFound(String filter) throws Exception {
        restMDictionaryDeMockMvc.perform(get("/api/m-dictionary-des?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDictionaryDeMockMvc.perform(get("/api/m-dictionary-des/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDictionaryDe() throws Exception {
        // Get the mDictionaryDe
        restMDictionaryDeMockMvc.perform(get("/api/m-dictionary-des/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDictionaryDe() throws Exception {
        // Initialize the database
        mDictionaryDeRepository.saveAndFlush(mDictionaryDe);

        int databaseSizeBeforeUpdate = mDictionaryDeRepository.findAll().size();

        // Update the mDictionaryDe
        MDictionaryDe updatedMDictionaryDe = mDictionaryDeRepository.findById(mDictionaryDe.getId()).get();
        // Disconnect from session so that the updates on updatedMDictionaryDe are not directly saved in db
        em.detach(updatedMDictionaryDe);
        updatedMDictionaryDe
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        MDictionaryDeDTO mDictionaryDeDTO = mDictionaryDeMapper.toDto(updatedMDictionaryDe);

        restMDictionaryDeMockMvc.perform(put("/api/m-dictionary-des")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryDeDTO)))
            .andExpect(status().isOk());

        // Validate the MDictionaryDe in the database
        List<MDictionaryDe> mDictionaryDeList = mDictionaryDeRepository.findAll();
        assertThat(mDictionaryDeList).hasSize(databaseSizeBeforeUpdate);
        MDictionaryDe testMDictionaryDe = mDictionaryDeList.get(mDictionaryDeList.size() - 1);
        assertThat(testMDictionaryDe.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDictionaryDe.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDictionaryDe() throws Exception {
        int databaseSizeBeforeUpdate = mDictionaryDeRepository.findAll().size();

        // Create the MDictionaryDe
        MDictionaryDeDTO mDictionaryDeDTO = mDictionaryDeMapper.toDto(mDictionaryDe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDictionaryDeMockMvc.perform(put("/api/m-dictionary-des")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryDeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryDe in the database
        List<MDictionaryDe> mDictionaryDeList = mDictionaryDeRepository.findAll();
        assertThat(mDictionaryDeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDictionaryDe() throws Exception {
        // Initialize the database
        mDictionaryDeRepository.saveAndFlush(mDictionaryDe);

        int databaseSizeBeforeDelete = mDictionaryDeRepository.findAll().size();

        // Delete the mDictionaryDe
        restMDictionaryDeMockMvc.perform(delete("/api/m-dictionary-des/{id}", mDictionaryDe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDictionaryDe> mDictionaryDeList = mDictionaryDeRepository.findAll();
        assertThat(mDictionaryDeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryDe.class);
        MDictionaryDe mDictionaryDe1 = new MDictionaryDe();
        mDictionaryDe1.setId(1L);
        MDictionaryDe mDictionaryDe2 = new MDictionaryDe();
        mDictionaryDe2.setId(mDictionaryDe1.getId());
        assertThat(mDictionaryDe1).isEqualTo(mDictionaryDe2);
        mDictionaryDe2.setId(2L);
        assertThat(mDictionaryDe1).isNotEqualTo(mDictionaryDe2);
        mDictionaryDe1.setId(null);
        assertThat(mDictionaryDe1).isNotEqualTo(mDictionaryDe2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryDeDTO.class);
        MDictionaryDeDTO mDictionaryDeDTO1 = new MDictionaryDeDTO();
        mDictionaryDeDTO1.setId(1L);
        MDictionaryDeDTO mDictionaryDeDTO2 = new MDictionaryDeDTO();
        assertThat(mDictionaryDeDTO1).isNotEqualTo(mDictionaryDeDTO2);
        mDictionaryDeDTO2.setId(mDictionaryDeDTO1.getId());
        assertThat(mDictionaryDeDTO1).isEqualTo(mDictionaryDeDTO2);
        mDictionaryDeDTO2.setId(2L);
        assertThat(mDictionaryDeDTO1).isNotEqualTo(mDictionaryDeDTO2);
        mDictionaryDeDTO1.setId(null);
        assertThat(mDictionaryDeDTO1).isNotEqualTo(mDictionaryDeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDictionaryDeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDictionaryDeMapper.fromId(null)).isNull();
    }
}
