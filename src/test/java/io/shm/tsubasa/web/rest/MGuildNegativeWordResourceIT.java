package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuildNegativeWord;
import io.shm.tsubasa.repository.MGuildNegativeWordRepository;
import io.shm.tsubasa.service.MGuildNegativeWordService;
import io.shm.tsubasa.service.dto.MGuildNegativeWordDTO;
import io.shm.tsubasa.service.mapper.MGuildNegativeWordMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuildNegativeWordCriteria;
import io.shm.tsubasa.service.MGuildNegativeWordQueryService;

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
 * Integration tests for the {@Link MGuildNegativeWordResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuildNegativeWordResourceIT {

    private static final String DEFAULT_WORD = "AAAAAAAAAA";
    private static final String UPDATED_WORD = "BBBBBBBBBB";

    @Autowired
    private MGuildNegativeWordRepository mGuildNegativeWordRepository;

    @Autowired
    private MGuildNegativeWordMapper mGuildNegativeWordMapper;

    @Autowired
    private MGuildNegativeWordService mGuildNegativeWordService;

    @Autowired
    private MGuildNegativeWordQueryService mGuildNegativeWordQueryService;

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

    private MockMvc restMGuildNegativeWordMockMvc;

    private MGuildNegativeWord mGuildNegativeWord;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuildNegativeWordResource mGuildNegativeWordResource = new MGuildNegativeWordResource(mGuildNegativeWordService, mGuildNegativeWordQueryService);
        this.restMGuildNegativeWordMockMvc = MockMvcBuilders.standaloneSetup(mGuildNegativeWordResource)
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
    public static MGuildNegativeWord createEntity(EntityManager em) {
        MGuildNegativeWord mGuildNegativeWord = new MGuildNegativeWord()
            .word(DEFAULT_WORD);
        return mGuildNegativeWord;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuildNegativeWord createUpdatedEntity(EntityManager em) {
        MGuildNegativeWord mGuildNegativeWord = new MGuildNegativeWord()
            .word(UPDATED_WORD);
        return mGuildNegativeWord;
    }

    @BeforeEach
    public void initTest() {
        mGuildNegativeWord = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuildNegativeWord() throws Exception {
        int databaseSizeBeforeCreate = mGuildNegativeWordRepository.findAll().size();

        // Create the MGuildNegativeWord
        MGuildNegativeWordDTO mGuildNegativeWordDTO = mGuildNegativeWordMapper.toDto(mGuildNegativeWord);
        restMGuildNegativeWordMockMvc.perform(post("/api/m-guild-negative-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildNegativeWordDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuildNegativeWord in the database
        List<MGuildNegativeWord> mGuildNegativeWordList = mGuildNegativeWordRepository.findAll();
        assertThat(mGuildNegativeWordList).hasSize(databaseSizeBeforeCreate + 1);
        MGuildNegativeWord testMGuildNegativeWord = mGuildNegativeWordList.get(mGuildNegativeWordList.size() - 1);
        assertThat(testMGuildNegativeWord.getWord()).isEqualTo(DEFAULT_WORD);
    }

    @Test
    @Transactional
    public void createMGuildNegativeWordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuildNegativeWordRepository.findAll().size();

        // Create the MGuildNegativeWord with an existing ID
        mGuildNegativeWord.setId(1L);
        MGuildNegativeWordDTO mGuildNegativeWordDTO = mGuildNegativeWordMapper.toDto(mGuildNegativeWord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuildNegativeWordMockMvc.perform(post("/api/m-guild-negative-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildNegativeWordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildNegativeWord in the database
        List<MGuildNegativeWord> mGuildNegativeWordList = mGuildNegativeWordRepository.findAll();
        assertThat(mGuildNegativeWordList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMGuildNegativeWords() throws Exception {
        // Initialize the database
        mGuildNegativeWordRepository.saveAndFlush(mGuildNegativeWord);

        // Get all the mGuildNegativeWordList
        restMGuildNegativeWordMockMvc.perform(get("/api/m-guild-negative-words?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildNegativeWord.getId().intValue())))
            .andExpect(jsonPath("$.[*].word").value(hasItem(DEFAULT_WORD.toString())));
    }
    
    @Test
    @Transactional
    public void getMGuildNegativeWord() throws Exception {
        // Initialize the database
        mGuildNegativeWordRepository.saveAndFlush(mGuildNegativeWord);

        // Get the mGuildNegativeWord
        restMGuildNegativeWordMockMvc.perform(get("/api/m-guild-negative-words/{id}", mGuildNegativeWord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuildNegativeWord.getId().intValue()))
            .andExpect(jsonPath("$.word").value(DEFAULT_WORD.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuildNegativeWordShouldBeFound(String filter) throws Exception {
        restMGuildNegativeWordMockMvc.perform(get("/api/m-guild-negative-words?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildNegativeWord.getId().intValue())))
            .andExpect(jsonPath("$.[*].word").value(hasItem(DEFAULT_WORD.toString())));

        // Check, that the count call also returns 1
        restMGuildNegativeWordMockMvc.perform(get("/api/m-guild-negative-words/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuildNegativeWordShouldNotBeFound(String filter) throws Exception {
        restMGuildNegativeWordMockMvc.perform(get("/api/m-guild-negative-words?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuildNegativeWordMockMvc.perform(get("/api/m-guild-negative-words/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuildNegativeWord() throws Exception {
        // Get the mGuildNegativeWord
        restMGuildNegativeWordMockMvc.perform(get("/api/m-guild-negative-words/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuildNegativeWord() throws Exception {
        // Initialize the database
        mGuildNegativeWordRepository.saveAndFlush(mGuildNegativeWord);

        int databaseSizeBeforeUpdate = mGuildNegativeWordRepository.findAll().size();

        // Update the mGuildNegativeWord
        MGuildNegativeWord updatedMGuildNegativeWord = mGuildNegativeWordRepository.findById(mGuildNegativeWord.getId()).get();
        // Disconnect from session so that the updates on updatedMGuildNegativeWord are not directly saved in db
        em.detach(updatedMGuildNegativeWord);
        updatedMGuildNegativeWord
            .word(UPDATED_WORD);
        MGuildNegativeWordDTO mGuildNegativeWordDTO = mGuildNegativeWordMapper.toDto(updatedMGuildNegativeWord);

        restMGuildNegativeWordMockMvc.perform(put("/api/m-guild-negative-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildNegativeWordDTO)))
            .andExpect(status().isOk());

        // Validate the MGuildNegativeWord in the database
        List<MGuildNegativeWord> mGuildNegativeWordList = mGuildNegativeWordRepository.findAll();
        assertThat(mGuildNegativeWordList).hasSize(databaseSizeBeforeUpdate);
        MGuildNegativeWord testMGuildNegativeWord = mGuildNegativeWordList.get(mGuildNegativeWordList.size() - 1);
        assertThat(testMGuildNegativeWord.getWord()).isEqualTo(UPDATED_WORD);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuildNegativeWord() throws Exception {
        int databaseSizeBeforeUpdate = mGuildNegativeWordRepository.findAll().size();

        // Create the MGuildNegativeWord
        MGuildNegativeWordDTO mGuildNegativeWordDTO = mGuildNegativeWordMapper.toDto(mGuildNegativeWord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuildNegativeWordMockMvc.perform(put("/api/m-guild-negative-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildNegativeWordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildNegativeWord in the database
        List<MGuildNegativeWord> mGuildNegativeWordList = mGuildNegativeWordRepository.findAll();
        assertThat(mGuildNegativeWordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuildNegativeWord() throws Exception {
        // Initialize the database
        mGuildNegativeWordRepository.saveAndFlush(mGuildNegativeWord);

        int databaseSizeBeforeDelete = mGuildNegativeWordRepository.findAll().size();

        // Delete the mGuildNegativeWord
        restMGuildNegativeWordMockMvc.perform(delete("/api/m-guild-negative-words/{id}", mGuildNegativeWord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuildNegativeWord> mGuildNegativeWordList = mGuildNegativeWordRepository.findAll();
        assertThat(mGuildNegativeWordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildNegativeWord.class);
        MGuildNegativeWord mGuildNegativeWord1 = new MGuildNegativeWord();
        mGuildNegativeWord1.setId(1L);
        MGuildNegativeWord mGuildNegativeWord2 = new MGuildNegativeWord();
        mGuildNegativeWord2.setId(mGuildNegativeWord1.getId());
        assertThat(mGuildNegativeWord1).isEqualTo(mGuildNegativeWord2);
        mGuildNegativeWord2.setId(2L);
        assertThat(mGuildNegativeWord1).isNotEqualTo(mGuildNegativeWord2);
        mGuildNegativeWord1.setId(null);
        assertThat(mGuildNegativeWord1).isNotEqualTo(mGuildNegativeWord2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildNegativeWordDTO.class);
        MGuildNegativeWordDTO mGuildNegativeWordDTO1 = new MGuildNegativeWordDTO();
        mGuildNegativeWordDTO1.setId(1L);
        MGuildNegativeWordDTO mGuildNegativeWordDTO2 = new MGuildNegativeWordDTO();
        assertThat(mGuildNegativeWordDTO1).isNotEqualTo(mGuildNegativeWordDTO2);
        mGuildNegativeWordDTO2.setId(mGuildNegativeWordDTO1.getId());
        assertThat(mGuildNegativeWordDTO1).isEqualTo(mGuildNegativeWordDTO2);
        mGuildNegativeWordDTO2.setId(2L);
        assertThat(mGuildNegativeWordDTO1).isNotEqualTo(mGuildNegativeWordDTO2);
        mGuildNegativeWordDTO1.setId(null);
        assertThat(mGuildNegativeWordDTO1).isNotEqualTo(mGuildNegativeWordDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuildNegativeWordMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuildNegativeWordMapper.fromId(null)).isNull();
    }
}
