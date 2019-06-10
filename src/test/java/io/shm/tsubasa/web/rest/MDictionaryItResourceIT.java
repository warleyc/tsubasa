package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDictionaryIt;
import io.shm.tsubasa.repository.MDictionaryItRepository;
import io.shm.tsubasa.service.MDictionaryItService;
import io.shm.tsubasa.service.dto.MDictionaryItDTO;
import io.shm.tsubasa.service.mapper.MDictionaryItMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDictionaryItCriteria;
import io.shm.tsubasa.service.MDictionaryItQueryService;

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
 * Integration tests for the {@Link MDictionaryItResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDictionaryItResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MDictionaryItRepository mDictionaryItRepository;

    @Autowired
    private MDictionaryItMapper mDictionaryItMapper;

    @Autowired
    private MDictionaryItService mDictionaryItService;

    @Autowired
    private MDictionaryItQueryService mDictionaryItQueryService;

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

    private MockMvc restMDictionaryItMockMvc;

    private MDictionaryIt mDictionaryIt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDictionaryItResource mDictionaryItResource = new MDictionaryItResource(mDictionaryItService, mDictionaryItQueryService);
        this.restMDictionaryItMockMvc = MockMvcBuilders.standaloneSetup(mDictionaryItResource)
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
    public static MDictionaryIt createEntity(EntityManager em) {
        MDictionaryIt mDictionaryIt = new MDictionaryIt()
            .key(DEFAULT_KEY)
            .message(DEFAULT_MESSAGE);
        return mDictionaryIt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDictionaryIt createUpdatedEntity(EntityManager em) {
        MDictionaryIt mDictionaryIt = new MDictionaryIt()
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        return mDictionaryIt;
    }

    @BeforeEach
    public void initTest() {
        mDictionaryIt = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDictionaryIt() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryItRepository.findAll().size();

        // Create the MDictionaryIt
        MDictionaryItDTO mDictionaryItDTO = mDictionaryItMapper.toDto(mDictionaryIt);
        restMDictionaryItMockMvc.perform(post("/api/m-dictionary-its")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryItDTO)))
            .andExpect(status().isCreated());

        // Validate the MDictionaryIt in the database
        List<MDictionaryIt> mDictionaryItList = mDictionaryItRepository.findAll();
        assertThat(mDictionaryItList).hasSize(databaseSizeBeforeCreate + 1);
        MDictionaryIt testMDictionaryIt = mDictionaryItList.get(mDictionaryItList.size() - 1);
        assertThat(testMDictionaryIt.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMDictionaryIt.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMDictionaryItWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDictionaryItRepository.findAll().size();

        // Create the MDictionaryIt with an existing ID
        mDictionaryIt.setId(1L);
        MDictionaryItDTO mDictionaryItDTO = mDictionaryItMapper.toDto(mDictionaryIt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDictionaryItMockMvc.perform(post("/api/m-dictionary-its")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryItDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryIt in the database
        List<MDictionaryIt> mDictionaryItList = mDictionaryItRepository.findAll();
        assertThat(mDictionaryItList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMDictionaryIts() throws Exception {
        // Initialize the database
        mDictionaryItRepository.saveAndFlush(mDictionaryIt);

        // Get all the mDictionaryItList
        restMDictionaryItMockMvc.perform(get("/api/m-dictionary-its?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryIt.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMDictionaryIt() throws Exception {
        // Initialize the database
        mDictionaryItRepository.saveAndFlush(mDictionaryIt);

        // Get the mDictionaryIt
        restMDictionaryItMockMvc.perform(get("/api/m-dictionary-its/{id}", mDictionaryIt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDictionaryIt.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDictionaryItShouldBeFound(String filter) throws Exception {
        restMDictionaryItMockMvc.perform(get("/api/m-dictionary-its?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDictionaryIt.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMDictionaryItMockMvc.perform(get("/api/m-dictionary-its/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDictionaryItShouldNotBeFound(String filter) throws Exception {
        restMDictionaryItMockMvc.perform(get("/api/m-dictionary-its?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDictionaryItMockMvc.perform(get("/api/m-dictionary-its/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDictionaryIt() throws Exception {
        // Get the mDictionaryIt
        restMDictionaryItMockMvc.perform(get("/api/m-dictionary-its/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDictionaryIt() throws Exception {
        // Initialize the database
        mDictionaryItRepository.saveAndFlush(mDictionaryIt);

        int databaseSizeBeforeUpdate = mDictionaryItRepository.findAll().size();

        // Update the mDictionaryIt
        MDictionaryIt updatedMDictionaryIt = mDictionaryItRepository.findById(mDictionaryIt.getId()).get();
        // Disconnect from session so that the updates on updatedMDictionaryIt are not directly saved in db
        em.detach(updatedMDictionaryIt);
        updatedMDictionaryIt
            .key(UPDATED_KEY)
            .message(UPDATED_MESSAGE);
        MDictionaryItDTO mDictionaryItDTO = mDictionaryItMapper.toDto(updatedMDictionaryIt);

        restMDictionaryItMockMvc.perform(put("/api/m-dictionary-its")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryItDTO)))
            .andExpect(status().isOk());

        // Validate the MDictionaryIt in the database
        List<MDictionaryIt> mDictionaryItList = mDictionaryItRepository.findAll();
        assertThat(mDictionaryItList).hasSize(databaseSizeBeforeUpdate);
        MDictionaryIt testMDictionaryIt = mDictionaryItList.get(mDictionaryItList.size() - 1);
        assertThat(testMDictionaryIt.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMDictionaryIt.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMDictionaryIt() throws Exception {
        int databaseSizeBeforeUpdate = mDictionaryItRepository.findAll().size();

        // Create the MDictionaryIt
        MDictionaryItDTO mDictionaryItDTO = mDictionaryItMapper.toDto(mDictionaryIt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDictionaryItMockMvc.perform(put("/api/m-dictionary-its")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDictionaryItDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDictionaryIt in the database
        List<MDictionaryIt> mDictionaryItList = mDictionaryItRepository.findAll();
        assertThat(mDictionaryItList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDictionaryIt() throws Exception {
        // Initialize the database
        mDictionaryItRepository.saveAndFlush(mDictionaryIt);

        int databaseSizeBeforeDelete = mDictionaryItRepository.findAll().size();

        // Delete the mDictionaryIt
        restMDictionaryItMockMvc.perform(delete("/api/m-dictionary-its/{id}", mDictionaryIt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDictionaryIt> mDictionaryItList = mDictionaryItRepository.findAll();
        assertThat(mDictionaryItList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryIt.class);
        MDictionaryIt mDictionaryIt1 = new MDictionaryIt();
        mDictionaryIt1.setId(1L);
        MDictionaryIt mDictionaryIt2 = new MDictionaryIt();
        mDictionaryIt2.setId(mDictionaryIt1.getId());
        assertThat(mDictionaryIt1).isEqualTo(mDictionaryIt2);
        mDictionaryIt2.setId(2L);
        assertThat(mDictionaryIt1).isNotEqualTo(mDictionaryIt2);
        mDictionaryIt1.setId(null);
        assertThat(mDictionaryIt1).isNotEqualTo(mDictionaryIt2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDictionaryItDTO.class);
        MDictionaryItDTO mDictionaryItDTO1 = new MDictionaryItDTO();
        mDictionaryItDTO1.setId(1L);
        MDictionaryItDTO mDictionaryItDTO2 = new MDictionaryItDTO();
        assertThat(mDictionaryItDTO1).isNotEqualTo(mDictionaryItDTO2);
        mDictionaryItDTO2.setId(mDictionaryItDTO1.getId());
        assertThat(mDictionaryItDTO1).isEqualTo(mDictionaryItDTO2);
        mDictionaryItDTO2.setId(2L);
        assertThat(mDictionaryItDTO1).isNotEqualTo(mDictionaryItDTO2);
        mDictionaryItDTO1.setId(null);
        assertThat(mDictionaryItDTO1).isNotEqualTo(mDictionaryItDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDictionaryItMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDictionaryItMapper.fromId(null)).isNull();
    }
}
