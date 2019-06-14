package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MNgWord;
import io.shm.tsubasa.repository.MNgWordRepository;
import io.shm.tsubasa.service.MNgWordService;
import io.shm.tsubasa.service.dto.MNgWordDTO;
import io.shm.tsubasa.service.mapper.MNgWordMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MNgWordCriteria;
import io.shm.tsubasa.service.MNgWordQueryService;

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
 * Integration tests for the {@Link MNgWordResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MNgWordResourceIT {

    private static final String DEFAULT_WORD = "AAAAAAAAAA";
    private static final String UPDATED_WORD = "BBBBBBBBBB";

    private static final Integer DEFAULT_JUDGE_TYPE = 1;
    private static final Integer UPDATED_JUDGE_TYPE = 2;

    @Autowired
    private MNgWordRepository mNgWordRepository;

    @Autowired
    private MNgWordMapper mNgWordMapper;

    @Autowired
    private MNgWordService mNgWordService;

    @Autowired
    private MNgWordQueryService mNgWordQueryService;

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

    private MockMvc restMNgWordMockMvc;

    private MNgWord mNgWord;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MNgWordResource mNgWordResource = new MNgWordResource(mNgWordService, mNgWordQueryService);
        this.restMNgWordMockMvc = MockMvcBuilders.standaloneSetup(mNgWordResource)
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
    public static MNgWord createEntity(EntityManager em) {
        MNgWord mNgWord = new MNgWord()
            .word(DEFAULT_WORD)
            .judgeType(DEFAULT_JUDGE_TYPE);
        return mNgWord;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MNgWord createUpdatedEntity(EntityManager em) {
        MNgWord mNgWord = new MNgWord()
            .word(UPDATED_WORD)
            .judgeType(UPDATED_JUDGE_TYPE);
        return mNgWord;
    }

    @BeforeEach
    public void initTest() {
        mNgWord = createEntity(em);
    }

    @Test
    @Transactional
    public void createMNgWord() throws Exception {
        int databaseSizeBeforeCreate = mNgWordRepository.findAll().size();

        // Create the MNgWord
        MNgWordDTO mNgWordDTO = mNgWordMapper.toDto(mNgWord);
        restMNgWordMockMvc.perform(post("/api/m-ng-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNgWordDTO)))
            .andExpect(status().isCreated());

        // Validate the MNgWord in the database
        List<MNgWord> mNgWordList = mNgWordRepository.findAll();
        assertThat(mNgWordList).hasSize(databaseSizeBeforeCreate + 1);
        MNgWord testMNgWord = mNgWordList.get(mNgWordList.size() - 1);
        assertThat(testMNgWord.getWord()).isEqualTo(DEFAULT_WORD);
        assertThat(testMNgWord.getJudgeType()).isEqualTo(DEFAULT_JUDGE_TYPE);
    }

    @Test
    @Transactional
    public void createMNgWordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mNgWordRepository.findAll().size();

        // Create the MNgWord with an existing ID
        mNgWord.setId(1L);
        MNgWordDTO mNgWordDTO = mNgWordMapper.toDto(mNgWord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMNgWordMockMvc.perform(post("/api/m-ng-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNgWordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNgWord in the database
        List<MNgWord> mNgWordList = mNgWordRepository.findAll();
        assertThat(mNgWordList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkJudgeTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNgWordRepository.findAll().size();
        // set the field null
        mNgWord.setJudgeType(null);

        // Create the MNgWord, which fails.
        MNgWordDTO mNgWordDTO = mNgWordMapper.toDto(mNgWord);

        restMNgWordMockMvc.perform(post("/api/m-ng-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNgWordDTO)))
            .andExpect(status().isBadRequest());

        List<MNgWord> mNgWordList = mNgWordRepository.findAll();
        assertThat(mNgWordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMNgWords() throws Exception {
        // Initialize the database
        mNgWordRepository.saveAndFlush(mNgWord);

        // Get all the mNgWordList
        restMNgWordMockMvc.perform(get("/api/m-ng-words?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNgWord.getId().intValue())))
            .andExpect(jsonPath("$.[*].word").value(hasItem(DEFAULT_WORD.toString())))
            .andExpect(jsonPath("$.[*].judgeType").value(hasItem(DEFAULT_JUDGE_TYPE)));
    }
    
    @Test
    @Transactional
    public void getMNgWord() throws Exception {
        // Initialize the database
        mNgWordRepository.saveAndFlush(mNgWord);

        // Get the mNgWord
        restMNgWordMockMvc.perform(get("/api/m-ng-words/{id}", mNgWord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mNgWord.getId().intValue()))
            .andExpect(jsonPath("$.word").value(DEFAULT_WORD.toString()))
            .andExpect(jsonPath("$.judgeType").value(DEFAULT_JUDGE_TYPE));
    }

    @Test
    @Transactional
    public void getAllMNgWordsByJudgeTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mNgWordRepository.saveAndFlush(mNgWord);

        // Get all the mNgWordList where judgeType equals to DEFAULT_JUDGE_TYPE
        defaultMNgWordShouldBeFound("judgeType.equals=" + DEFAULT_JUDGE_TYPE);

        // Get all the mNgWordList where judgeType equals to UPDATED_JUDGE_TYPE
        defaultMNgWordShouldNotBeFound("judgeType.equals=" + UPDATED_JUDGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllMNgWordsByJudgeTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mNgWordRepository.saveAndFlush(mNgWord);

        // Get all the mNgWordList where judgeType in DEFAULT_JUDGE_TYPE or UPDATED_JUDGE_TYPE
        defaultMNgWordShouldBeFound("judgeType.in=" + DEFAULT_JUDGE_TYPE + "," + UPDATED_JUDGE_TYPE);

        // Get all the mNgWordList where judgeType equals to UPDATED_JUDGE_TYPE
        defaultMNgWordShouldNotBeFound("judgeType.in=" + UPDATED_JUDGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllMNgWordsByJudgeTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNgWordRepository.saveAndFlush(mNgWord);

        // Get all the mNgWordList where judgeType is not null
        defaultMNgWordShouldBeFound("judgeType.specified=true");

        // Get all the mNgWordList where judgeType is null
        defaultMNgWordShouldNotBeFound("judgeType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNgWordsByJudgeTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNgWordRepository.saveAndFlush(mNgWord);

        // Get all the mNgWordList where judgeType greater than or equals to DEFAULT_JUDGE_TYPE
        defaultMNgWordShouldBeFound("judgeType.greaterOrEqualThan=" + DEFAULT_JUDGE_TYPE);

        // Get all the mNgWordList where judgeType greater than or equals to UPDATED_JUDGE_TYPE
        defaultMNgWordShouldNotBeFound("judgeType.greaterOrEqualThan=" + UPDATED_JUDGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllMNgWordsByJudgeTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mNgWordRepository.saveAndFlush(mNgWord);

        // Get all the mNgWordList where judgeType less than or equals to DEFAULT_JUDGE_TYPE
        defaultMNgWordShouldNotBeFound("judgeType.lessThan=" + DEFAULT_JUDGE_TYPE);

        // Get all the mNgWordList where judgeType less than or equals to UPDATED_JUDGE_TYPE
        defaultMNgWordShouldBeFound("judgeType.lessThan=" + UPDATED_JUDGE_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMNgWordShouldBeFound(String filter) throws Exception {
        restMNgWordMockMvc.perform(get("/api/m-ng-words?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNgWord.getId().intValue())))
            .andExpect(jsonPath("$.[*].word").value(hasItem(DEFAULT_WORD.toString())))
            .andExpect(jsonPath("$.[*].judgeType").value(hasItem(DEFAULT_JUDGE_TYPE)));

        // Check, that the count call also returns 1
        restMNgWordMockMvc.perform(get("/api/m-ng-words/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMNgWordShouldNotBeFound(String filter) throws Exception {
        restMNgWordMockMvc.perform(get("/api/m-ng-words?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMNgWordMockMvc.perform(get("/api/m-ng-words/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMNgWord() throws Exception {
        // Get the mNgWord
        restMNgWordMockMvc.perform(get("/api/m-ng-words/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMNgWord() throws Exception {
        // Initialize the database
        mNgWordRepository.saveAndFlush(mNgWord);

        int databaseSizeBeforeUpdate = mNgWordRepository.findAll().size();

        // Update the mNgWord
        MNgWord updatedMNgWord = mNgWordRepository.findById(mNgWord.getId()).get();
        // Disconnect from session so that the updates on updatedMNgWord are not directly saved in db
        em.detach(updatedMNgWord);
        updatedMNgWord
            .word(UPDATED_WORD)
            .judgeType(UPDATED_JUDGE_TYPE);
        MNgWordDTO mNgWordDTO = mNgWordMapper.toDto(updatedMNgWord);

        restMNgWordMockMvc.perform(put("/api/m-ng-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNgWordDTO)))
            .andExpect(status().isOk());

        // Validate the MNgWord in the database
        List<MNgWord> mNgWordList = mNgWordRepository.findAll();
        assertThat(mNgWordList).hasSize(databaseSizeBeforeUpdate);
        MNgWord testMNgWord = mNgWordList.get(mNgWordList.size() - 1);
        assertThat(testMNgWord.getWord()).isEqualTo(UPDATED_WORD);
        assertThat(testMNgWord.getJudgeType()).isEqualTo(UPDATED_JUDGE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMNgWord() throws Exception {
        int databaseSizeBeforeUpdate = mNgWordRepository.findAll().size();

        // Create the MNgWord
        MNgWordDTO mNgWordDTO = mNgWordMapper.toDto(mNgWord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMNgWordMockMvc.perform(put("/api/m-ng-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNgWordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNgWord in the database
        List<MNgWord> mNgWordList = mNgWordRepository.findAll();
        assertThat(mNgWordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMNgWord() throws Exception {
        // Initialize the database
        mNgWordRepository.saveAndFlush(mNgWord);

        int databaseSizeBeforeDelete = mNgWordRepository.findAll().size();

        // Delete the mNgWord
        restMNgWordMockMvc.perform(delete("/api/m-ng-words/{id}", mNgWord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MNgWord> mNgWordList = mNgWordRepository.findAll();
        assertThat(mNgWordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNgWord.class);
        MNgWord mNgWord1 = new MNgWord();
        mNgWord1.setId(1L);
        MNgWord mNgWord2 = new MNgWord();
        mNgWord2.setId(mNgWord1.getId());
        assertThat(mNgWord1).isEqualTo(mNgWord2);
        mNgWord2.setId(2L);
        assertThat(mNgWord1).isNotEqualTo(mNgWord2);
        mNgWord1.setId(null);
        assertThat(mNgWord1).isNotEqualTo(mNgWord2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNgWordDTO.class);
        MNgWordDTO mNgWordDTO1 = new MNgWordDTO();
        mNgWordDTO1.setId(1L);
        MNgWordDTO mNgWordDTO2 = new MNgWordDTO();
        assertThat(mNgWordDTO1).isNotEqualTo(mNgWordDTO2);
        mNgWordDTO2.setId(mNgWordDTO1.getId());
        assertThat(mNgWordDTO1).isEqualTo(mNgWordDTO2);
        mNgWordDTO2.setId(2L);
        assertThat(mNgWordDTO1).isNotEqualTo(mNgWordDTO2);
        mNgWordDTO1.setId(null);
        assertThat(mNgWordDTO1).isNotEqualTo(mNgWordDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mNgWordMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mNgWordMapper.fromId(null)).isNull();
    }
}
