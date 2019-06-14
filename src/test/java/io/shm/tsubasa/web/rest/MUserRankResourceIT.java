package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MUserRank;
import io.shm.tsubasa.repository.MUserRankRepository;
import io.shm.tsubasa.service.MUserRankService;
import io.shm.tsubasa.service.dto.MUserRankDTO;
import io.shm.tsubasa.service.mapper.MUserRankMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MUserRankCriteria;
import io.shm.tsubasa.service.MUserRankQueryService;

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
 * Integration tests for the {@Link MUserRankResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MUserRankResourceIT {

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Integer DEFAULT_EXP = 1;
    private static final Integer UPDATED_EXP = 2;

    private static final Integer DEFAULT_QUEST_AP = 1;
    private static final Integer UPDATED_QUEST_AP = 2;

    private static final Integer DEFAULT_MAX_FRIEND_COUNT = 1;
    private static final Integer UPDATED_MAX_FRIEND_COUNT = 2;

    private static final String DEFAULT_RANKUP_SERIF = "AAAAAAAAAA";
    private static final String UPDATED_RANKUP_SERIF = "BBBBBBBBBB";

    private static final String DEFAULT_CHARA_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHARA_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VOICE_CHARA_ID = "AAAAAAAAAA";
    private static final String UPDATED_VOICE_CHARA_ID = "BBBBBBBBBB";

    @Autowired
    private MUserRankRepository mUserRankRepository;

    @Autowired
    private MUserRankMapper mUserRankMapper;

    @Autowired
    private MUserRankService mUserRankService;

    @Autowired
    private MUserRankQueryService mUserRankQueryService;

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

    private MockMvc restMUserRankMockMvc;

    private MUserRank mUserRank;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MUserRankResource mUserRankResource = new MUserRankResource(mUserRankService, mUserRankQueryService);
        this.restMUserRankMockMvc = MockMvcBuilders.standaloneSetup(mUserRankResource)
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
    public static MUserRank createEntity(EntityManager em) {
        MUserRank mUserRank = new MUserRank()
            .rank(DEFAULT_RANK)
            .exp(DEFAULT_EXP)
            .questAp(DEFAULT_QUEST_AP)
            .maxFriendCount(DEFAULT_MAX_FRIEND_COUNT)
            .rankupSerif(DEFAULT_RANKUP_SERIF)
            .charaAssetName(DEFAULT_CHARA_ASSET_NAME)
            .voiceCharaId(DEFAULT_VOICE_CHARA_ID);
        return mUserRank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MUserRank createUpdatedEntity(EntityManager em) {
        MUserRank mUserRank = new MUserRank()
            .rank(UPDATED_RANK)
            .exp(UPDATED_EXP)
            .questAp(UPDATED_QUEST_AP)
            .maxFriendCount(UPDATED_MAX_FRIEND_COUNT)
            .rankupSerif(UPDATED_RANKUP_SERIF)
            .charaAssetName(UPDATED_CHARA_ASSET_NAME)
            .voiceCharaId(UPDATED_VOICE_CHARA_ID);
        return mUserRank;
    }

    @BeforeEach
    public void initTest() {
        mUserRank = createEntity(em);
    }

    @Test
    @Transactional
    public void createMUserRank() throws Exception {
        int databaseSizeBeforeCreate = mUserRankRepository.findAll().size();

        // Create the MUserRank
        MUserRankDTO mUserRankDTO = mUserRankMapper.toDto(mUserRank);
        restMUserRankMockMvc.perform(post("/api/m-user-ranks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserRankDTO)))
            .andExpect(status().isCreated());

        // Validate the MUserRank in the database
        List<MUserRank> mUserRankList = mUserRankRepository.findAll();
        assertThat(mUserRankList).hasSize(databaseSizeBeforeCreate + 1);
        MUserRank testMUserRank = mUserRankList.get(mUserRankList.size() - 1);
        assertThat(testMUserRank.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testMUserRank.getExp()).isEqualTo(DEFAULT_EXP);
        assertThat(testMUserRank.getQuestAp()).isEqualTo(DEFAULT_QUEST_AP);
        assertThat(testMUserRank.getMaxFriendCount()).isEqualTo(DEFAULT_MAX_FRIEND_COUNT);
        assertThat(testMUserRank.getRankupSerif()).isEqualTo(DEFAULT_RANKUP_SERIF);
        assertThat(testMUserRank.getCharaAssetName()).isEqualTo(DEFAULT_CHARA_ASSET_NAME);
        assertThat(testMUserRank.getVoiceCharaId()).isEqualTo(DEFAULT_VOICE_CHARA_ID);
    }

    @Test
    @Transactional
    public void createMUserRankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mUserRankRepository.findAll().size();

        // Create the MUserRank with an existing ID
        mUserRank.setId(1L);
        MUserRankDTO mUserRankDTO = mUserRankMapper.toDto(mUserRank);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMUserRankMockMvc.perform(post("/api/m-user-ranks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserRankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUserRank in the database
        List<MUserRank> mUserRankList = mUserRankRepository.findAll();
        assertThat(mUserRankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRankIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUserRankRepository.findAll().size();
        // set the field null
        mUserRank.setRank(null);

        // Create the MUserRank, which fails.
        MUserRankDTO mUserRankDTO = mUserRankMapper.toDto(mUserRank);

        restMUserRankMockMvc.perform(post("/api/m-user-ranks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserRankDTO)))
            .andExpect(status().isBadRequest());

        List<MUserRank> mUserRankList = mUserRankRepository.findAll();
        assertThat(mUserRankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUserRankRepository.findAll().size();
        // set the field null
        mUserRank.setExp(null);

        // Create the MUserRank, which fails.
        MUserRankDTO mUserRankDTO = mUserRankMapper.toDto(mUserRank);

        restMUserRankMockMvc.perform(post("/api/m-user-ranks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserRankDTO)))
            .andExpect(status().isBadRequest());

        List<MUserRank> mUserRankList = mUserRankRepository.findAll();
        assertThat(mUserRankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuestApIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUserRankRepository.findAll().size();
        // set the field null
        mUserRank.setQuestAp(null);

        // Create the MUserRank, which fails.
        MUserRankDTO mUserRankDTO = mUserRankMapper.toDto(mUserRank);

        restMUserRankMockMvc.perform(post("/api/m-user-ranks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserRankDTO)))
            .andExpect(status().isBadRequest());

        List<MUserRank> mUserRankList = mUserRankRepository.findAll();
        assertThat(mUserRankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxFriendCountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUserRankRepository.findAll().size();
        // set the field null
        mUserRank.setMaxFriendCount(null);

        // Create the MUserRank, which fails.
        MUserRankDTO mUserRankDTO = mUserRankMapper.toDto(mUserRank);

        restMUserRankMockMvc.perform(post("/api/m-user-ranks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserRankDTO)))
            .andExpect(status().isBadRequest());

        List<MUserRank> mUserRankList = mUserRankRepository.findAll();
        assertThat(mUserRankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMUserRanks() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList
        restMUserRankMockMvc.perform(get("/api/m-user-ranks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUserRank.getId().intValue())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)))
            .andExpect(jsonPath("$.[*].questAp").value(hasItem(DEFAULT_QUEST_AP)))
            .andExpect(jsonPath("$.[*].maxFriendCount").value(hasItem(DEFAULT_MAX_FRIEND_COUNT)))
            .andExpect(jsonPath("$.[*].rankupSerif").value(hasItem(DEFAULT_RANKUP_SERIF.toString())))
            .andExpect(jsonPath("$.[*].charaAssetName").value(hasItem(DEFAULT_CHARA_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].voiceCharaId").value(hasItem(DEFAULT_VOICE_CHARA_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getMUserRank() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get the mUserRank
        restMUserRankMockMvc.perform(get("/api/m-user-ranks/{id}", mUserRank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mUserRank.getId().intValue()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.exp").value(DEFAULT_EXP))
            .andExpect(jsonPath("$.questAp").value(DEFAULT_QUEST_AP))
            .andExpect(jsonPath("$.maxFriendCount").value(DEFAULT_MAX_FRIEND_COUNT))
            .andExpect(jsonPath("$.rankupSerif").value(DEFAULT_RANKUP_SERIF.toString()))
            .andExpect(jsonPath("$.charaAssetName").value(DEFAULT_CHARA_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.voiceCharaId").value(DEFAULT_VOICE_CHARA_ID.toString()));
    }

    @Test
    @Transactional
    public void getAllMUserRanksByRankIsEqualToSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where rank equals to DEFAULT_RANK
        defaultMUserRankShouldBeFound("rank.equals=" + DEFAULT_RANK);

        // Get all the mUserRankList where rank equals to UPDATED_RANK
        defaultMUserRankShouldNotBeFound("rank.equals=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByRankIsInShouldWork() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where rank in DEFAULT_RANK or UPDATED_RANK
        defaultMUserRankShouldBeFound("rank.in=" + DEFAULT_RANK + "," + UPDATED_RANK);

        // Get all the mUserRankList where rank equals to UPDATED_RANK
        defaultMUserRankShouldNotBeFound("rank.in=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByRankIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where rank is not null
        defaultMUserRankShouldBeFound("rank.specified=true");

        // Get all the mUserRankList where rank is null
        defaultMUserRankShouldNotBeFound("rank.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUserRanksByRankIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where rank greater than or equals to DEFAULT_RANK
        defaultMUserRankShouldBeFound("rank.greaterOrEqualThan=" + DEFAULT_RANK);

        // Get all the mUserRankList where rank greater than or equals to UPDATED_RANK
        defaultMUserRankShouldNotBeFound("rank.greaterOrEqualThan=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByRankIsLessThanSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where rank less than or equals to DEFAULT_RANK
        defaultMUserRankShouldNotBeFound("rank.lessThan=" + DEFAULT_RANK);

        // Get all the mUserRankList where rank less than or equals to UPDATED_RANK
        defaultMUserRankShouldBeFound("rank.lessThan=" + UPDATED_RANK);
    }


    @Test
    @Transactional
    public void getAllMUserRanksByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where exp equals to DEFAULT_EXP
        defaultMUserRankShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mUserRankList where exp equals to UPDATED_EXP
        defaultMUserRankShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMUserRankShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mUserRankList where exp equals to UPDATED_EXP
        defaultMUserRankShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where exp is not null
        defaultMUserRankShouldBeFound("exp.specified=true");

        // Get all the mUserRankList where exp is null
        defaultMUserRankShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUserRanksByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where exp greater than or equals to DEFAULT_EXP
        defaultMUserRankShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mUserRankList where exp greater than or equals to UPDATED_EXP
        defaultMUserRankShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where exp less than or equals to DEFAULT_EXP
        defaultMUserRankShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mUserRankList where exp less than or equals to UPDATED_EXP
        defaultMUserRankShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }


    @Test
    @Transactional
    public void getAllMUserRanksByQuestApIsEqualToSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where questAp equals to DEFAULT_QUEST_AP
        defaultMUserRankShouldBeFound("questAp.equals=" + DEFAULT_QUEST_AP);

        // Get all the mUserRankList where questAp equals to UPDATED_QUEST_AP
        defaultMUserRankShouldNotBeFound("questAp.equals=" + UPDATED_QUEST_AP);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByQuestApIsInShouldWork() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where questAp in DEFAULT_QUEST_AP or UPDATED_QUEST_AP
        defaultMUserRankShouldBeFound("questAp.in=" + DEFAULT_QUEST_AP + "," + UPDATED_QUEST_AP);

        // Get all the mUserRankList where questAp equals to UPDATED_QUEST_AP
        defaultMUserRankShouldNotBeFound("questAp.in=" + UPDATED_QUEST_AP);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByQuestApIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where questAp is not null
        defaultMUserRankShouldBeFound("questAp.specified=true");

        // Get all the mUserRankList where questAp is null
        defaultMUserRankShouldNotBeFound("questAp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUserRanksByQuestApIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where questAp greater than or equals to DEFAULT_QUEST_AP
        defaultMUserRankShouldBeFound("questAp.greaterOrEqualThan=" + DEFAULT_QUEST_AP);

        // Get all the mUserRankList where questAp greater than or equals to UPDATED_QUEST_AP
        defaultMUserRankShouldNotBeFound("questAp.greaterOrEqualThan=" + UPDATED_QUEST_AP);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByQuestApIsLessThanSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where questAp less than or equals to DEFAULT_QUEST_AP
        defaultMUserRankShouldNotBeFound("questAp.lessThan=" + DEFAULT_QUEST_AP);

        // Get all the mUserRankList where questAp less than or equals to UPDATED_QUEST_AP
        defaultMUserRankShouldBeFound("questAp.lessThan=" + UPDATED_QUEST_AP);
    }


    @Test
    @Transactional
    public void getAllMUserRanksByMaxFriendCountIsEqualToSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where maxFriendCount equals to DEFAULT_MAX_FRIEND_COUNT
        defaultMUserRankShouldBeFound("maxFriendCount.equals=" + DEFAULT_MAX_FRIEND_COUNT);

        // Get all the mUserRankList where maxFriendCount equals to UPDATED_MAX_FRIEND_COUNT
        defaultMUserRankShouldNotBeFound("maxFriendCount.equals=" + UPDATED_MAX_FRIEND_COUNT);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByMaxFriendCountIsInShouldWork() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where maxFriendCount in DEFAULT_MAX_FRIEND_COUNT or UPDATED_MAX_FRIEND_COUNT
        defaultMUserRankShouldBeFound("maxFriendCount.in=" + DEFAULT_MAX_FRIEND_COUNT + "," + UPDATED_MAX_FRIEND_COUNT);

        // Get all the mUserRankList where maxFriendCount equals to UPDATED_MAX_FRIEND_COUNT
        defaultMUserRankShouldNotBeFound("maxFriendCount.in=" + UPDATED_MAX_FRIEND_COUNT);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByMaxFriendCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where maxFriendCount is not null
        defaultMUserRankShouldBeFound("maxFriendCount.specified=true");

        // Get all the mUserRankList where maxFriendCount is null
        defaultMUserRankShouldNotBeFound("maxFriendCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUserRanksByMaxFriendCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where maxFriendCount greater than or equals to DEFAULT_MAX_FRIEND_COUNT
        defaultMUserRankShouldBeFound("maxFriendCount.greaterOrEqualThan=" + DEFAULT_MAX_FRIEND_COUNT);

        // Get all the mUserRankList where maxFriendCount greater than or equals to UPDATED_MAX_FRIEND_COUNT
        defaultMUserRankShouldNotBeFound("maxFriendCount.greaterOrEqualThan=" + UPDATED_MAX_FRIEND_COUNT);
    }

    @Test
    @Transactional
    public void getAllMUserRanksByMaxFriendCountIsLessThanSomething() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        // Get all the mUserRankList where maxFriendCount less than or equals to DEFAULT_MAX_FRIEND_COUNT
        defaultMUserRankShouldNotBeFound("maxFriendCount.lessThan=" + DEFAULT_MAX_FRIEND_COUNT);

        // Get all the mUserRankList where maxFriendCount less than or equals to UPDATED_MAX_FRIEND_COUNT
        defaultMUserRankShouldBeFound("maxFriendCount.lessThan=" + UPDATED_MAX_FRIEND_COUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMUserRankShouldBeFound(String filter) throws Exception {
        restMUserRankMockMvc.perform(get("/api/m-user-ranks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUserRank.getId().intValue())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)))
            .andExpect(jsonPath("$.[*].questAp").value(hasItem(DEFAULT_QUEST_AP)))
            .andExpect(jsonPath("$.[*].maxFriendCount").value(hasItem(DEFAULT_MAX_FRIEND_COUNT)))
            .andExpect(jsonPath("$.[*].rankupSerif").value(hasItem(DEFAULT_RANKUP_SERIF.toString())))
            .andExpect(jsonPath("$.[*].charaAssetName").value(hasItem(DEFAULT_CHARA_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].voiceCharaId").value(hasItem(DEFAULT_VOICE_CHARA_ID.toString())));

        // Check, that the count call also returns 1
        restMUserRankMockMvc.perform(get("/api/m-user-ranks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMUserRankShouldNotBeFound(String filter) throws Exception {
        restMUserRankMockMvc.perform(get("/api/m-user-ranks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMUserRankMockMvc.perform(get("/api/m-user-ranks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMUserRank() throws Exception {
        // Get the mUserRank
        restMUserRankMockMvc.perform(get("/api/m-user-ranks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMUserRank() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        int databaseSizeBeforeUpdate = mUserRankRepository.findAll().size();

        // Update the mUserRank
        MUserRank updatedMUserRank = mUserRankRepository.findById(mUserRank.getId()).get();
        // Disconnect from session so that the updates on updatedMUserRank are not directly saved in db
        em.detach(updatedMUserRank);
        updatedMUserRank
            .rank(UPDATED_RANK)
            .exp(UPDATED_EXP)
            .questAp(UPDATED_QUEST_AP)
            .maxFriendCount(UPDATED_MAX_FRIEND_COUNT)
            .rankupSerif(UPDATED_RANKUP_SERIF)
            .charaAssetName(UPDATED_CHARA_ASSET_NAME)
            .voiceCharaId(UPDATED_VOICE_CHARA_ID);
        MUserRankDTO mUserRankDTO = mUserRankMapper.toDto(updatedMUserRank);

        restMUserRankMockMvc.perform(put("/api/m-user-ranks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserRankDTO)))
            .andExpect(status().isOk());

        // Validate the MUserRank in the database
        List<MUserRank> mUserRankList = mUserRankRepository.findAll();
        assertThat(mUserRankList).hasSize(databaseSizeBeforeUpdate);
        MUserRank testMUserRank = mUserRankList.get(mUserRankList.size() - 1);
        assertThat(testMUserRank.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testMUserRank.getExp()).isEqualTo(UPDATED_EXP);
        assertThat(testMUserRank.getQuestAp()).isEqualTo(UPDATED_QUEST_AP);
        assertThat(testMUserRank.getMaxFriendCount()).isEqualTo(UPDATED_MAX_FRIEND_COUNT);
        assertThat(testMUserRank.getRankupSerif()).isEqualTo(UPDATED_RANKUP_SERIF);
        assertThat(testMUserRank.getCharaAssetName()).isEqualTo(UPDATED_CHARA_ASSET_NAME);
        assertThat(testMUserRank.getVoiceCharaId()).isEqualTo(UPDATED_VOICE_CHARA_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMUserRank() throws Exception {
        int databaseSizeBeforeUpdate = mUserRankRepository.findAll().size();

        // Create the MUserRank
        MUserRankDTO mUserRankDTO = mUserRankMapper.toDto(mUserRank);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMUserRankMockMvc.perform(put("/api/m-user-ranks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserRankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUserRank in the database
        List<MUserRank> mUserRankList = mUserRankRepository.findAll();
        assertThat(mUserRankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMUserRank() throws Exception {
        // Initialize the database
        mUserRankRepository.saveAndFlush(mUserRank);

        int databaseSizeBeforeDelete = mUserRankRepository.findAll().size();

        // Delete the mUserRank
        restMUserRankMockMvc.perform(delete("/api/m-user-ranks/{id}", mUserRank.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MUserRank> mUserRankList = mUserRankRepository.findAll();
        assertThat(mUserRankList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUserRank.class);
        MUserRank mUserRank1 = new MUserRank();
        mUserRank1.setId(1L);
        MUserRank mUserRank2 = new MUserRank();
        mUserRank2.setId(mUserRank1.getId());
        assertThat(mUserRank1).isEqualTo(mUserRank2);
        mUserRank2.setId(2L);
        assertThat(mUserRank1).isNotEqualTo(mUserRank2);
        mUserRank1.setId(null);
        assertThat(mUserRank1).isNotEqualTo(mUserRank2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUserRankDTO.class);
        MUserRankDTO mUserRankDTO1 = new MUserRankDTO();
        mUserRankDTO1.setId(1L);
        MUserRankDTO mUserRankDTO2 = new MUserRankDTO();
        assertThat(mUserRankDTO1).isNotEqualTo(mUserRankDTO2);
        mUserRankDTO2.setId(mUserRankDTO1.getId());
        assertThat(mUserRankDTO1).isEqualTo(mUserRankDTO2);
        mUserRankDTO2.setId(2L);
        assertThat(mUserRankDTO1).isNotEqualTo(mUserRankDTO2);
        mUserRankDTO1.setId(null);
        assertThat(mUserRankDTO1).isNotEqualTo(mUserRankDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mUserRankMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mUserRankMapper.fromId(null)).isNull();
    }
}
