package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPvpWave;
import io.shm.tsubasa.domain.MPvpRankingReward;
import io.shm.tsubasa.repository.MPvpWaveRepository;
import io.shm.tsubasa.service.MPvpWaveService;
import io.shm.tsubasa.service.dto.MPvpWaveDTO;
import io.shm.tsubasa.service.mapper.MPvpWaveMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPvpWaveCriteria;
import io.shm.tsubasa.service.MPvpWaveQueryService;

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
 * Integration tests for the {@Link MPvpWaveResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPvpWaveResourceIT {

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    private static final Integer DEFAULT_IS_RANKING = 1;
    private static final Integer UPDATED_IS_RANKING = 2;

    @Autowired
    private MPvpWaveRepository mPvpWaveRepository;

    @Autowired
    private MPvpWaveMapper mPvpWaveMapper;

    @Autowired
    private MPvpWaveService mPvpWaveService;

    @Autowired
    private MPvpWaveQueryService mPvpWaveQueryService;

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

    private MockMvc restMPvpWaveMockMvc;

    private MPvpWave mPvpWave;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPvpWaveResource mPvpWaveResource = new MPvpWaveResource(mPvpWaveService, mPvpWaveQueryService);
        this.restMPvpWaveMockMvc = MockMvcBuilders.standaloneSetup(mPvpWaveResource)
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
    public static MPvpWave createEntity(EntityManager em) {
        MPvpWave mPvpWave = new MPvpWave()
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT)
            .isRanking(DEFAULT_IS_RANKING);
        return mPvpWave;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPvpWave createUpdatedEntity(EntityManager em) {
        MPvpWave mPvpWave = new MPvpWave()
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .isRanking(UPDATED_IS_RANKING);
        return mPvpWave;
    }

    @BeforeEach
    public void initTest() {
        mPvpWave = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPvpWave() throws Exception {
        int databaseSizeBeforeCreate = mPvpWaveRepository.findAll().size();

        // Create the MPvpWave
        MPvpWaveDTO mPvpWaveDTO = mPvpWaveMapper.toDto(mPvpWave);
        restMPvpWaveMockMvc.perform(post("/api/m-pvp-waves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWaveDTO)))
            .andExpect(status().isCreated());

        // Validate the MPvpWave in the database
        List<MPvpWave> mPvpWaveList = mPvpWaveRepository.findAll();
        assertThat(mPvpWaveList).hasSize(databaseSizeBeforeCreate + 1);
        MPvpWave testMPvpWave = mPvpWaveList.get(mPvpWaveList.size() - 1);
        assertThat(testMPvpWave.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMPvpWave.getEndAt()).isEqualTo(DEFAULT_END_AT);
        assertThat(testMPvpWave.getIsRanking()).isEqualTo(DEFAULT_IS_RANKING);
    }

    @Test
    @Transactional
    public void createMPvpWaveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPvpWaveRepository.findAll().size();

        // Create the MPvpWave with an existing ID
        mPvpWave.setId(1L);
        MPvpWaveDTO mPvpWaveDTO = mPvpWaveMapper.toDto(mPvpWave);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPvpWaveMockMvc.perform(post("/api/m-pvp-waves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWaveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpWave in the database
        List<MPvpWave> mPvpWaveList = mPvpWaveRepository.findAll();
        assertThat(mPvpWaveList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpWaveRepository.findAll().size();
        // set the field null
        mPvpWave.setStartAt(null);

        // Create the MPvpWave, which fails.
        MPvpWaveDTO mPvpWaveDTO = mPvpWaveMapper.toDto(mPvpWave);

        restMPvpWaveMockMvc.perform(post("/api/m-pvp-waves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWaveDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpWave> mPvpWaveList = mPvpWaveRepository.findAll();
        assertThat(mPvpWaveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpWaveRepository.findAll().size();
        // set the field null
        mPvpWave.setEndAt(null);

        // Create the MPvpWave, which fails.
        MPvpWaveDTO mPvpWaveDTO = mPvpWaveMapper.toDto(mPvpWave);

        restMPvpWaveMockMvc.perform(post("/api/m-pvp-waves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWaveDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpWave> mPvpWaveList = mPvpWaveRepository.findAll();
        assertThat(mPvpWaveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsRankingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpWaveRepository.findAll().size();
        // set the field null
        mPvpWave.setIsRanking(null);

        // Create the MPvpWave, which fails.
        MPvpWaveDTO mPvpWaveDTO = mPvpWaveMapper.toDto(mPvpWave);

        restMPvpWaveMockMvc.perform(post("/api/m-pvp-waves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWaveDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpWave> mPvpWaveList = mPvpWaveRepository.findAll();
        assertThat(mPvpWaveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPvpWaves() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList
        restMPvpWaveMockMvc.perform(get("/api/m-pvp-waves?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpWave.getId().intValue())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].isRanking").value(hasItem(DEFAULT_IS_RANKING)));
    }
    
    @Test
    @Transactional
    public void getMPvpWave() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get the mPvpWave
        restMPvpWaveMockMvc.perform(get("/api/m-pvp-waves/{id}", mPvpWave.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPvpWave.getId().intValue()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT))
            .andExpect(jsonPath("$.isRanking").value(DEFAULT_IS_RANKING));
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where startAt equals to DEFAULT_START_AT
        defaultMPvpWaveShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mPvpWaveList where startAt equals to UPDATED_START_AT
        defaultMPvpWaveShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMPvpWaveShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mPvpWaveList where startAt equals to UPDATED_START_AT
        defaultMPvpWaveShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where startAt is not null
        defaultMPvpWaveShouldBeFound("startAt.specified=true");

        // Get all the mPvpWaveList where startAt is null
        defaultMPvpWaveShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where startAt greater than or equals to DEFAULT_START_AT
        defaultMPvpWaveShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mPvpWaveList where startAt greater than or equals to UPDATED_START_AT
        defaultMPvpWaveShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where startAt less than or equals to DEFAULT_START_AT
        defaultMPvpWaveShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mPvpWaveList where startAt less than or equals to UPDATED_START_AT
        defaultMPvpWaveShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMPvpWavesByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where endAt equals to DEFAULT_END_AT
        defaultMPvpWaveShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mPvpWaveList where endAt equals to UPDATED_END_AT
        defaultMPvpWaveShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMPvpWaveShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mPvpWaveList where endAt equals to UPDATED_END_AT
        defaultMPvpWaveShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where endAt is not null
        defaultMPvpWaveShouldBeFound("endAt.specified=true");

        // Get all the mPvpWaveList where endAt is null
        defaultMPvpWaveShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where endAt greater than or equals to DEFAULT_END_AT
        defaultMPvpWaveShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mPvpWaveList where endAt greater than or equals to UPDATED_END_AT
        defaultMPvpWaveShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where endAt less than or equals to DEFAULT_END_AT
        defaultMPvpWaveShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mPvpWaveList where endAt less than or equals to UPDATED_END_AT
        defaultMPvpWaveShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }


    @Test
    @Transactional
    public void getAllMPvpWavesByIsRankingIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where isRanking equals to DEFAULT_IS_RANKING
        defaultMPvpWaveShouldBeFound("isRanking.equals=" + DEFAULT_IS_RANKING);

        // Get all the mPvpWaveList where isRanking equals to UPDATED_IS_RANKING
        defaultMPvpWaveShouldNotBeFound("isRanking.equals=" + UPDATED_IS_RANKING);
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByIsRankingIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where isRanking in DEFAULT_IS_RANKING or UPDATED_IS_RANKING
        defaultMPvpWaveShouldBeFound("isRanking.in=" + DEFAULT_IS_RANKING + "," + UPDATED_IS_RANKING);

        // Get all the mPvpWaveList where isRanking equals to UPDATED_IS_RANKING
        defaultMPvpWaveShouldNotBeFound("isRanking.in=" + UPDATED_IS_RANKING);
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByIsRankingIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where isRanking is not null
        defaultMPvpWaveShouldBeFound("isRanking.specified=true");

        // Get all the mPvpWaveList where isRanking is null
        defaultMPvpWaveShouldNotBeFound("isRanking.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByIsRankingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where isRanking greater than or equals to DEFAULT_IS_RANKING
        defaultMPvpWaveShouldBeFound("isRanking.greaterOrEqualThan=" + DEFAULT_IS_RANKING);

        // Get all the mPvpWaveList where isRanking greater than or equals to UPDATED_IS_RANKING
        defaultMPvpWaveShouldNotBeFound("isRanking.greaterOrEqualThan=" + UPDATED_IS_RANKING);
    }

    @Test
    @Transactional
    public void getAllMPvpWavesByIsRankingIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        // Get all the mPvpWaveList where isRanking less than or equals to DEFAULT_IS_RANKING
        defaultMPvpWaveShouldNotBeFound("isRanking.lessThan=" + DEFAULT_IS_RANKING);

        // Get all the mPvpWaveList where isRanking less than or equals to UPDATED_IS_RANKING
        defaultMPvpWaveShouldBeFound("isRanking.lessThan=" + UPDATED_IS_RANKING);
    }


    @Test
    @Transactional
    public void getAllMPvpWavesByMPvpRankingRewardIsEqualToSomething() throws Exception {
        // Initialize the database
        MPvpRankingReward mPvpRankingReward = MPvpRankingRewardResourceIT.createEntity(em);
        em.persist(mPvpRankingReward);
        em.flush();
        mPvpWave.addMPvpRankingReward(mPvpRankingReward);
        mPvpWaveRepository.saveAndFlush(mPvpWave);
        Long mPvpRankingRewardId = mPvpRankingReward.getId();

        // Get all the mPvpWaveList where mPvpRankingReward equals to mPvpRankingRewardId
        defaultMPvpWaveShouldBeFound("mPvpRankingRewardId.equals=" + mPvpRankingRewardId);

        // Get all the mPvpWaveList where mPvpRankingReward equals to mPvpRankingRewardId + 1
        defaultMPvpWaveShouldNotBeFound("mPvpRankingRewardId.equals=" + (mPvpRankingRewardId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPvpWaveShouldBeFound(String filter) throws Exception {
        restMPvpWaveMockMvc.perform(get("/api/m-pvp-waves?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpWave.getId().intValue())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].isRanking").value(hasItem(DEFAULT_IS_RANKING)));

        // Check, that the count call also returns 1
        restMPvpWaveMockMvc.perform(get("/api/m-pvp-waves/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPvpWaveShouldNotBeFound(String filter) throws Exception {
        restMPvpWaveMockMvc.perform(get("/api/m-pvp-waves?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPvpWaveMockMvc.perform(get("/api/m-pvp-waves/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPvpWave() throws Exception {
        // Get the mPvpWave
        restMPvpWaveMockMvc.perform(get("/api/m-pvp-waves/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPvpWave() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        int databaseSizeBeforeUpdate = mPvpWaveRepository.findAll().size();

        // Update the mPvpWave
        MPvpWave updatedMPvpWave = mPvpWaveRepository.findById(mPvpWave.getId()).get();
        // Disconnect from session so that the updates on updatedMPvpWave are not directly saved in db
        em.detach(updatedMPvpWave);
        updatedMPvpWave
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .isRanking(UPDATED_IS_RANKING);
        MPvpWaveDTO mPvpWaveDTO = mPvpWaveMapper.toDto(updatedMPvpWave);

        restMPvpWaveMockMvc.perform(put("/api/m-pvp-waves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWaveDTO)))
            .andExpect(status().isOk());

        // Validate the MPvpWave in the database
        List<MPvpWave> mPvpWaveList = mPvpWaveRepository.findAll();
        assertThat(mPvpWaveList).hasSize(databaseSizeBeforeUpdate);
        MPvpWave testMPvpWave = mPvpWaveList.get(mPvpWaveList.size() - 1);
        assertThat(testMPvpWave.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMPvpWave.getEndAt()).isEqualTo(UPDATED_END_AT);
        assertThat(testMPvpWave.getIsRanking()).isEqualTo(UPDATED_IS_RANKING);
    }

    @Test
    @Transactional
    public void updateNonExistingMPvpWave() throws Exception {
        int databaseSizeBeforeUpdate = mPvpWaveRepository.findAll().size();

        // Create the MPvpWave
        MPvpWaveDTO mPvpWaveDTO = mPvpWaveMapper.toDto(mPvpWave);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPvpWaveMockMvc.perform(put("/api/m-pvp-waves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWaveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpWave in the database
        List<MPvpWave> mPvpWaveList = mPvpWaveRepository.findAll();
        assertThat(mPvpWaveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPvpWave() throws Exception {
        // Initialize the database
        mPvpWaveRepository.saveAndFlush(mPvpWave);

        int databaseSizeBeforeDelete = mPvpWaveRepository.findAll().size();

        // Delete the mPvpWave
        restMPvpWaveMockMvc.perform(delete("/api/m-pvp-waves/{id}", mPvpWave.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPvpWave> mPvpWaveList = mPvpWaveRepository.findAll();
        assertThat(mPvpWaveList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpWave.class);
        MPvpWave mPvpWave1 = new MPvpWave();
        mPvpWave1.setId(1L);
        MPvpWave mPvpWave2 = new MPvpWave();
        mPvpWave2.setId(mPvpWave1.getId());
        assertThat(mPvpWave1).isEqualTo(mPvpWave2);
        mPvpWave2.setId(2L);
        assertThat(mPvpWave1).isNotEqualTo(mPvpWave2);
        mPvpWave1.setId(null);
        assertThat(mPvpWave1).isNotEqualTo(mPvpWave2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpWaveDTO.class);
        MPvpWaveDTO mPvpWaveDTO1 = new MPvpWaveDTO();
        mPvpWaveDTO1.setId(1L);
        MPvpWaveDTO mPvpWaveDTO2 = new MPvpWaveDTO();
        assertThat(mPvpWaveDTO1).isNotEqualTo(mPvpWaveDTO2);
        mPvpWaveDTO2.setId(mPvpWaveDTO1.getId());
        assertThat(mPvpWaveDTO1).isEqualTo(mPvpWaveDTO2);
        mPvpWaveDTO2.setId(2L);
        assertThat(mPvpWaveDTO1).isNotEqualTo(mPvpWaveDTO2);
        mPvpWaveDTO1.setId(null);
        assertThat(mPvpWaveDTO1).isNotEqualTo(mPvpWaveDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPvpWaveMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPvpWaveMapper.fromId(null)).isNull();
    }
}
