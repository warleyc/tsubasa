package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCharacterScoreCut;
import io.shm.tsubasa.repository.MCharacterScoreCutRepository;
import io.shm.tsubasa.service.MCharacterScoreCutService;
import io.shm.tsubasa.service.dto.MCharacterScoreCutDTO;
import io.shm.tsubasa.service.mapper.MCharacterScoreCutMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCharacterScoreCutCriteria;
import io.shm.tsubasa.service.MCharacterScoreCutQueryService;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MCharacterScoreCutResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCharacterScoreCutResourceIT {

    private static final Integer DEFAULT_CHARACTER_ID = 1;
    private static final Integer UPDATED_CHARACTER_ID = 2;

    private static final Integer DEFAULT_TEAM_ID = 1;
    private static final Integer UPDATED_TEAM_ID = 2;

    private static final Integer DEFAULT_SCORE_CUT_TYPE = 1;
    private static final Integer UPDATED_SCORE_CUT_TYPE = 2;

    @Autowired
    private MCharacterScoreCutRepository mCharacterScoreCutRepository;

    @Autowired
    private MCharacterScoreCutMapper mCharacterScoreCutMapper;

    @Autowired
    private MCharacterScoreCutService mCharacterScoreCutService;

    @Autowired
    private MCharacterScoreCutQueryService mCharacterScoreCutQueryService;

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

    private MockMvc restMCharacterScoreCutMockMvc;

    private MCharacterScoreCut mCharacterScoreCut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCharacterScoreCutResource mCharacterScoreCutResource = new MCharacterScoreCutResource(mCharacterScoreCutService, mCharacterScoreCutQueryService);
        this.restMCharacterScoreCutMockMvc = MockMvcBuilders.standaloneSetup(mCharacterScoreCutResource)
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
    public static MCharacterScoreCut createEntity(EntityManager em) {
        MCharacterScoreCut mCharacterScoreCut = new MCharacterScoreCut()
            .characterId(DEFAULT_CHARACTER_ID)
            .teamId(DEFAULT_TEAM_ID)
            .scoreCutType(DEFAULT_SCORE_CUT_TYPE);
        return mCharacterScoreCut;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCharacterScoreCut createUpdatedEntity(EntityManager em) {
        MCharacterScoreCut mCharacterScoreCut = new MCharacterScoreCut()
            .characterId(UPDATED_CHARACTER_ID)
            .teamId(UPDATED_TEAM_ID)
            .scoreCutType(UPDATED_SCORE_CUT_TYPE);
        return mCharacterScoreCut;
    }

    @BeforeEach
    public void initTest() {
        mCharacterScoreCut = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCharacterScoreCut() throws Exception {
        int databaseSizeBeforeCreate = mCharacterScoreCutRepository.findAll().size();

        // Create the MCharacterScoreCut
        MCharacterScoreCutDTO mCharacterScoreCutDTO = mCharacterScoreCutMapper.toDto(mCharacterScoreCut);
        restMCharacterScoreCutMockMvc.perform(post("/api/m-character-score-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterScoreCutDTO)))
            .andExpect(status().isCreated());

        // Validate the MCharacterScoreCut in the database
        List<MCharacterScoreCut> mCharacterScoreCutList = mCharacterScoreCutRepository.findAll();
        assertThat(mCharacterScoreCutList).hasSize(databaseSizeBeforeCreate + 1);
        MCharacterScoreCut testMCharacterScoreCut = mCharacterScoreCutList.get(mCharacterScoreCutList.size() - 1);
        assertThat(testMCharacterScoreCut.getCharacterId()).isEqualTo(DEFAULT_CHARACTER_ID);
        assertThat(testMCharacterScoreCut.getTeamId()).isEqualTo(DEFAULT_TEAM_ID);
        assertThat(testMCharacterScoreCut.getScoreCutType()).isEqualTo(DEFAULT_SCORE_CUT_TYPE);
    }

    @Test
    @Transactional
    public void createMCharacterScoreCutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCharacterScoreCutRepository.findAll().size();

        // Create the MCharacterScoreCut with an existing ID
        mCharacterScoreCut.setId(1L);
        MCharacterScoreCutDTO mCharacterScoreCutDTO = mCharacterScoreCutMapper.toDto(mCharacterScoreCut);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCharacterScoreCutMockMvc.perform(post("/api/m-character-score-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterScoreCutDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCharacterScoreCut in the database
        List<MCharacterScoreCut> mCharacterScoreCutList = mCharacterScoreCutRepository.findAll();
        assertThat(mCharacterScoreCutList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCharacterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCharacterScoreCutRepository.findAll().size();
        // set the field null
        mCharacterScoreCut.setCharacterId(null);

        // Create the MCharacterScoreCut, which fails.
        MCharacterScoreCutDTO mCharacterScoreCutDTO = mCharacterScoreCutMapper.toDto(mCharacterScoreCut);

        restMCharacterScoreCutMockMvc.perform(post("/api/m-character-score-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterScoreCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCharacterScoreCut> mCharacterScoreCutList = mCharacterScoreCutRepository.findAll();
        assertThat(mCharacterScoreCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeamIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCharacterScoreCutRepository.findAll().size();
        // set the field null
        mCharacterScoreCut.setTeamId(null);

        // Create the MCharacterScoreCut, which fails.
        MCharacterScoreCutDTO mCharacterScoreCutDTO = mCharacterScoreCutMapper.toDto(mCharacterScoreCut);

        restMCharacterScoreCutMockMvc.perform(post("/api/m-character-score-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterScoreCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCharacterScoreCut> mCharacterScoreCutList = mCharacterScoreCutRepository.findAll();
        assertThat(mCharacterScoreCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkScoreCutTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCharacterScoreCutRepository.findAll().size();
        // set the field null
        mCharacterScoreCut.setScoreCutType(null);

        // Create the MCharacterScoreCut, which fails.
        MCharacterScoreCutDTO mCharacterScoreCutDTO = mCharacterScoreCutMapper.toDto(mCharacterScoreCut);

        restMCharacterScoreCutMockMvc.perform(post("/api/m-character-score-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterScoreCutDTO)))
            .andExpect(status().isBadRequest());

        List<MCharacterScoreCut> mCharacterScoreCutList = mCharacterScoreCutRepository.findAll();
        assertThat(mCharacterScoreCutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCuts() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList
        restMCharacterScoreCutMockMvc.perform(get("/api/m-character-score-cuts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCharacterScoreCut.getId().intValue())))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)))
            .andExpect(jsonPath("$.[*].scoreCutType").value(hasItem(DEFAULT_SCORE_CUT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getMCharacterScoreCut() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get the mCharacterScoreCut
        restMCharacterScoreCutMockMvc.perform(get("/api/m-character-score-cuts/{id}", mCharacterScoreCut.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCharacterScoreCut.getId().intValue()))
            .andExpect(jsonPath("$.characterId").value(DEFAULT_CHARACTER_ID))
            .andExpect(jsonPath("$.teamId").value(DEFAULT_TEAM_ID))
            .andExpect(jsonPath("$.scoreCutType").value(DEFAULT_SCORE_CUT_TYPE));
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where characterId equals to DEFAULT_CHARACTER_ID
        defaultMCharacterScoreCutShouldBeFound("characterId.equals=" + DEFAULT_CHARACTER_ID);

        // Get all the mCharacterScoreCutList where characterId equals to UPDATED_CHARACTER_ID
        defaultMCharacterScoreCutShouldNotBeFound("characterId.equals=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where characterId in DEFAULT_CHARACTER_ID or UPDATED_CHARACTER_ID
        defaultMCharacterScoreCutShouldBeFound("characterId.in=" + DEFAULT_CHARACTER_ID + "," + UPDATED_CHARACTER_ID);

        // Get all the mCharacterScoreCutList where characterId equals to UPDATED_CHARACTER_ID
        defaultMCharacterScoreCutShouldNotBeFound("characterId.in=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where characterId is not null
        defaultMCharacterScoreCutShouldBeFound("characterId.specified=true");

        // Get all the mCharacterScoreCutList where characterId is null
        defaultMCharacterScoreCutShouldNotBeFound("characterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where characterId greater than or equals to DEFAULT_CHARACTER_ID
        defaultMCharacterScoreCutShouldBeFound("characterId.greaterOrEqualThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mCharacterScoreCutList where characterId greater than or equals to UPDATED_CHARACTER_ID
        defaultMCharacterScoreCutShouldNotBeFound("characterId.greaterOrEqualThan=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where characterId less than or equals to DEFAULT_CHARACTER_ID
        defaultMCharacterScoreCutShouldNotBeFound("characterId.lessThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mCharacterScoreCutList where characterId less than or equals to UPDATED_CHARACTER_ID
        defaultMCharacterScoreCutShouldBeFound("characterId.lessThan=" + UPDATED_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByTeamIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where teamId equals to DEFAULT_TEAM_ID
        defaultMCharacterScoreCutShouldBeFound("teamId.equals=" + DEFAULT_TEAM_ID);

        // Get all the mCharacterScoreCutList where teamId equals to UPDATED_TEAM_ID
        defaultMCharacterScoreCutShouldNotBeFound("teamId.equals=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByTeamIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where teamId in DEFAULT_TEAM_ID or UPDATED_TEAM_ID
        defaultMCharacterScoreCutShouldBeFound("teamId.in=" + DEFAULT_TEAM_ID + "," + UPDATED_TEAM_ID);

        // Get all the mCharacterScoreCutList where teamId equals to UPDATED_TEAM_ID
        defaultMCharacterScoreCutShouldNotBeFound("teamId.in=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByTeamIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where teamId is not null
        defaultMCharacterScoreCutShouldBeFound("teamId.specified=true");

        // Get all the mCharacterScoreCutList where teamId is null
        defaultMCharacterScoreCutShouldNotBeFound("teamId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByTeamIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where teamId greater than or equals to DEFAULT_TEAM_ID
        defaultMCharacterScoreCutShouldBeFound("teamId.greaterOrEqualThan=" + DEFAULT_TEAM_ID);

        // Get all the mCharacterScoreCutList where teamId greater than or equals to UPDATED_TEAM_ID
        defaultMCharacterScoreCutShouldNotBeFound("teamId.greaterOrEqualThan=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByTeamIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where teamId less than or equals to DEFAULT_TEAM_ID
        defaultMCharacterScoreCutShouldNotBeFound("teamId.lessThan=" + DEFAULT_TEAM_ID);

        // Get all the mCharacterScoreCutList where teamId less than or equals to UPDATED_TEAM_ID
        defaultMCharacterScoreCutShouldBeFound("teamId.lessThan=" + UPDATED_TEAM_ID);
    }


    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByScoreCutTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where scoreCutType equals to DEFAULT_SCORE_CUT_TYPE
        defaultMCharacterScoreCutShouldBeFound("scoreCutType.equals=" + DEFAULT_SCORE_CUT_TYPE);

        // Get all the mCharacterScoreCutList where scoreCutType equals to UPDATED_SCORE_CUT_TYPE
        defaultMCharacterScoreCutShouldNotBeFound("scoreCutType.equals=" + UPDATED_SCORE_CUT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByScoreCutTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where scoreCutType in DEFAULT_SCORE_CUT_TYPE or UPDATED_SCORE_CUT_TYPE
        defaultMCharacterScoreCutShouldBeFound("scoreCutType.in=" + DEFAULT_SCORE_CUT_TYPE + "," + UPDATED_SCORE_CUT_TYPE);

        // Get all the mCharacterScoreCutList where scoreCutType equals to UPDATED_SCORE_CUT_TYPE
        defaultMCharacterScoreCutShouldNotBeFound("scoreCutType.in=" + UPDATED_SCORE_CUT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByScoreCutTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where scoreCutType is not null
        defaultMCharacterScoreCutShouldBeFound("scoreCutType.specified=true");

        // Get all the mCharacterScoreCutList where scoreCutType is null
        defaultMCharacterScoreCutShouldNotBeFound("scoreCutType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByScoreCutTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where scoreCutType greater than or equals to DEFAULT_SCORE_CUT_TYPE
        defaultMCharacterScoreCutShouldBeFound("scoreCutType.greaterOrEqualThan=" + DEFAULT_SCORE_CUT_TYPE);

        // Get all the mCharacterScoreCutList where scoreCutType greater than or equals to UPDATED_SCORE_CUT_TYPE
        defaultMCharacterScoreCutShouldNotBeFound("scoreCutType.greaterOrEqualThan=" + UPDATED_SCORE_CUT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCharacterScoreCutsByScoreCutTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        // Get all the mCharacterScoreCutList where scoreCutType less than or equals to DEFAULT_SCORE_CUT_TYPE
        defaultMCharacterScoreCutShouldNotBeFound("scoreCutType.lessThan=" + DEFAULT_SCORE_CUT_TYPE);

        // Get all the mCharacterScoreCutList where scoreCutType less than or equals to UPDATED_SCORE_CUT_TYPE
        defaultMCharacterScoreCutShouldBeFound("scoreCutType.lessThan=" + UPDATED_SCORE_CUT_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCharacterScoreCutShouldBeFound(String filter) throws Exception {
        restMCharacterScoreCutMockMvc.perform(get("/api/m-character-score-cuts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCharacterScoreCut.getId().intValue())))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)))
            .andExpect(jsonPath("$.[*].scoreCutType").value(hasItem(DEFAULT_SCORE_CUT_TYPE)));

        // Check, that the count call also returns 1
        restMCharacterScoreCutMockMvc.perform(get("/api/m-character-score-cuts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCharacterScoreCutShouldNotBeFound(String filter) throws Exception {
        restMCharacterScoreCutMockMvc.perform(get("/api/m-character-score-cuts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCharacterScoreCutMockMvc.perform(get("/api/m-character-score-cuts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCharacterScoreCut() throws Exception {
        // Get the mCharacterScoreCut
        restMCharacterScoreCutMockMvc.perform(get("/api/m-character-score-cuts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCharacterScoreCut() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        int databaseSizeBeforeUpdate = mCharacterScoreCutRepository.findAll().size();

        // Update the mCharacterScoreCut
        MCharacterScoreCut updatedMCharacterScoreCut = mCharacterScoreCutRepository.findById(mCharacterScoreCut.getId()).get();
        // Disconnect from session so that the updates on updatedMCharacterScoreCut are not directly saved in db
        em.detach(updatedMCharacterScoreCut);
        updatedMCharacterScoreCut
            .characterId(UPDATED_CHARACTER_ID)
            .teamId(UPDATED_TEAM_ID)
            .scoreCutType(UPDATED_SCORE_CUT_TYPE);
        MCharacterScoreCutDTO mCharacterScoreCutDTO = mCharacterScoreCutMapper.toDto(updatedMCharacterScoreCut);

        restMCharacterScoreCutMockMvc.perform(put("/api/m-character-score-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterScoreCutDTO)))
            .andExpect(status().isOk());

        // Validate the MCharacterScoreCut in the database
        List<MCharacterScoreCut> mCharacterScoreCutList = mCharacterScoreCutRepository.findAll();
        assertThat(mCharacterScoreCutList).hasSize(databaseSizeBeforeUpdate);
        MCharacterScoreCut testMCharacterScoreCut = mCharacterScoreCutList.get(mCharacterScoreCutList.size() - 1);
        assertThat(testMCharacterScoreCut.getCharacterId()).isEqualTo(UPDATED_CHARACTER_ID);
        assertThat(testMCharacterScoreCut.getTeamId()).isEqualTo(UPDATED_TEAM_ID);
        assertThat(testMCharacterScoreCut.getScoreCutType()).isEqualTo(UPDATED_SCORE_CUT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMCharacterScoreCut() throws Exception {
        int databaseSizeBeforeUpdate = mCharacterScoreCutRepository.findAll().size();

        // Create the MCharacterScoreCut
        MCharacterScoreCutDTO mCharacterScoreCutDTO = mCharacterScoreCutMapper.toDto(mCharacterScoreCut);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCharacterScoreCutMockMvc.perform(put("/api/m-character-score-cuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterScoreCutDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCharacterScoreCut in the database
        List<MCharacterScoreCut> mCharacterScoreCutList = mCharacterScoreCutRepository.findAll();
        assertThat(mCharacterScoreCutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCharacterScoreCut() throws Exception {
        // Initialize the database
        mCharacterScoreCutRepository.saveAndFlush(mCharacterScoreCut);

        int databaseSizeBeforeDelete = mCharacterScoreCutRepository.findAll().size();

        // Delete the mCharacterScoreCut
        restMCharacterScoreCutMockMvc.perform(delete("/api/m-character-score-cuts/{id}", mCharacterScoreCut.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCharacterScoreCut> mCharacterScoreCutList = mCharacterScoreCutRepository.findAll();
        assertThat(mCharacterScoreCutList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCharacterScoreCut.class);
        MCharacterScoreCut mCharacterScoreCut1 = new MCharacterScoreCut();
        mCharacterScoreCut1.setId(1L);
        MCharacterScoreCut mCharacterScoreCut2 = new MCharacterScoreCut();
        mCharacterScoreCut2.setId(mCharacterScoreCut1.getId());
        assertThat(mCharacterScoreCut1).isEqualTo(mCharacterScoreCut2);
        mCharacterScoreCut2.setId(2L);
        assertThat(mCharacterScoreCut1).isNotEqualTo(mCharacterScoreCut2);
        mCharacterScoreCut1.setId(null);
        assertThat(mCharacterScoreCut1).isNotEqualTo(mCharacterScoreCut2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCharacterScoreCutDTO.class);
        MCharacterScoreCutDTO mCharacterScoreCutDTO1 = new MCharacterScoreCutDTO();
        mCharacterScoreCutDTO1.setId(1L);
        MCharacterScoreCutDTO mCharacterScoreCutDTO2 = new MCharacterScoreCutDTO();
        assertThat(mCharacterScoreCutDTO1).isNotEqualTo(mCharacterScoreCutDTO2);
        mCharacterScoreCutDTO2.setId(mCharacterScoreCutDTO1.getId());
        assertThat(mCharacterScoreCutDTO1).isEqualTo(mCharacterScoreCutDTO2);
        mCharacterScoreCutDTO2.setId(2L);
        assertThat(mCharacterScoreCutDTO1).isNotEqualTo(mCharacterScoreCutDTO2);
        mCharacterScoreCutDTO1.setId(null);
        assertThat(mCharacterScoreCutDTO1).isNotEqualTo(mCharacterScoreCutDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCharacterScoreCutMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCharacterScoreCutMapper.fromId(null)).isNull();
    }
}
