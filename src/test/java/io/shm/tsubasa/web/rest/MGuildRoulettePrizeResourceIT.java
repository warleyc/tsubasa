package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuildRoulettePrize;
import io.shm.tsubasa.repository.MGuildRoulettePrizeRepository;
import io.shm.tsubasa.service.MGuildRoulettePrizeService;
import io.shm.tsubasa.service.dto.MGuildRoulettePrizeDTO;
import io.shm.tsubasa.service.mapper.MGuildRoulettePrizeMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuildRoulettePrizeCriteria;
import io.shm.tsubasa.service.MGuildRoulettePrizeQueryService;

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
 * Integration tests for the {@Link MGuildRoulettePrizeResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuildRoulettePrizeResourceIT {

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MGuildRoulettePrizeRepository mGuildRoulettePrizeRepository;

    @Autowired
    private MGuildRoulettePrizeMapper mGuildRoulettePrizeMapper;

    @Autowired
    private MGuildRoulettePrizeService mGuildRoulettePrizeService;

    @Autowired
    private MGuildRoulettePrizeQueryService mGuildRoulettePrizeQueryService;

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

    private MockMvc restMGuildRoulettePrizeMockMvc;

    private MGuildRoulettePrize mGuildRoulettePrize;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuildRoulettePrizeResource mGuildRoulettePrizeResource = new MGuildRoulettePrizeResource(mGuildRoulettePrizeService, mGuildRoulettePrizeQueryService);
        this.restMGuildRoulettePrizeMockMvc = MockMvcBuilders.standaloneSetup(mGuildRoulettePrizeResource)
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
    public static MGuildRoulettePrize createEntity(EntityManager em) {
        MGuildRoulettePrize mGuildRoulettePrize = new MGuildRoulettePrize()
            .rank(DEFAULT_RANK)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mGuildRoulettePrize;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuildRoulettePrize createUpdatedEntity(EntityManager em) {
        MGuildRoulettePrize mGuildRoulettePrize = new MGuildRoulettePrize()
            .rank(UPDATED_RANK)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mGuildRoulettePrize;
    }

    @BeforeEach
    public void initTest() {
        mGuildRoulettePrize = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuildRoulettePrize() throws Exception {
        int databaseSizeBeforeCreate = mGuildRoulettePrizeRepository.findAll().size();

        // Create the MGuildRoulettePrize
        MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO = mGuildRoulettePrizeMapper.toDto(mGuildRoulettePrize);
        restMGuildRoulettePrizeMockMvc.perform(post("/api/m-guild-roulette-prizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildRoulettePrizeDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuildRoulettePrize in the database
        List<MGuildRoulettePrize> mGuildRoulettePrizeList = mGuildRoulettePrizeRepository.findAll();
        assertThat(mGuildRoulettePrizeList).hasSize(databaseSizeBeforeCreate + 1);
        MGuildRoulettePrize testMGuildRoulettePrize = mGuildRoulettePrizeList.get(mGuildRoulettePrizeList.size() - 1);
        assertThat(testMGuildRoulettePrize.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testMGuildRoulettePrize.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMGuildRoulettePrize.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMGuildRoulettePrize.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMGuildRoulettePrizeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuildRoulettePrizeRepository.findAll().size();

        // Create the MGuildRoulettePrize with an existing ID
        mGuildRoulettePrize.setId(1L);
        MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO = mGuildRoulettePrizeMapper.toDto(mGuildRoulettePrize);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuildRoulettePrizeMockMvc.perform(post("/api/m-guild-roulette-prizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildRoulettePrizeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildRoulettePrize in the database
        List<MGuildRoulettePrize> mGuildRoulettePrizeList = mGuildRoulettePrizeRepository.findAll();
        assertThat(mGuildRoulettePrizeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRankIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildRoulettePrizeRepository.findAll().size();
        // set the field null
        mGuildRoulettePrize.setRank(null);

        // Create the MGuildRoulettePrize, which fails.
        MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO = mGuildRoulettePrizeMapper.toDto(mGuildRoulettePrize);

        restMGuildRoulettePrizeMockMvc.perform(post("/api/m-guild-roulette-prizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildRoulettePrizeDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildRoulettePrize> mGuildRoulettePrizeList = mGuildRoulettePrizeRepository.findAll();
        assertThat(mGuildRoulettePrizeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildRoulettePrizeRepository.findAll().size();
        // set the field null
        mGuildRoulettePrize.setContentType(null);

        // Create the MGuildRoulettePrize, which fails.
        MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO = mGuildRoulettePrizeMapper.toDto(mGuildRoulettePrize);

        restMGuildRoulettePrizeMockMvc.perform(post("/api/m-guild-roulette-prizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildRoulettePrizeDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildRoulettePrize> mGuildRoulettePrizeList = mGuildRoulettePrizeRepository.findAll();
        assertThat(mGuildRoulettePrizeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildRoulettePrizeRepository.findAll().size();
        // set the field null
        mGuildRoulettePrize.setContentAmount(null);

        // Create the MGuildRoulettePrize, which fails.
        MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO = mGuildRoulettePrizeMapper.toDto(mGuildRoulettePrize);

        restMGuildRoulettePrizeMockMvc.perform(post("/api/m-guild-roulette-prizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildRoulettePrizeDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildRoulettePrize> mGuildRoulettePrizeList = mGuildRoulettePrizeRepository.findAll();
        assertThat(mGuildRoulettePrizeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizes() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList
        restMGuildRoulettePrizeMockMvc.perform(get("/api/m-guild-roulette-prizes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildRoulettePrize.getId().intValue())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMGuildRoulettePrize() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get the mGuildRoulettePrize
        restMGuildRoulettePrizeMockMvc.perform(get("/api/m-guild-roulette-prizes/{id}", mGuildRoulettePrize.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuildRoulettePrize.getId().intValue()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByRankIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where rank equals to DEFAULT_RANK
        defaultMGuildRoulettePrizeShouldBeFound("rank.equals=" + DEFAULT_RANK);

        // Get all the mGuildRoulettePrizeList where rank equals to UPDATED_RANK
        defaultMGuildRoulettePrizeShouldNotBeFound("rank.equals=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByRankIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where rank in DEFAULT_RANK or UPDATED_RANK
        defaultMGuildRoulettePrizeShouldBeFound("rank.in=" + DEFAULT_RANK + "," + UPDATED_RANK);

        // Get all the mGuildRoulettePrizeList where rank equals to UPDATED_RANK
        defaultMGuildRoulettePrizeShouldNotBeFound("rank.in=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByRankIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where rank is not null
        defaultMGuildRoulettePrizeShouldBeFound("rank.specified=true");

        // Get all the mGuildRoulettePrizeList where rank is null
        defaultMGuildRoulettePrizeShouldNotBeFound("rank.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByRankIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where rank greater than or equals to DEFAULT_RANK
        defaultMGuildRoulettePrizeShouldBeFound("rank.greaterOrEqualThan=" + DEFAULT_RANK);

        // Get all the mGuildRoulettePrizeList where rank greater than or equals to UPDATED_RANK
        defaultMGuildRoulettePrizeShouldNotBeFound("rank.greaterOrEqualThan=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByRankIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where rank less than or equals to DEFAULT_RANK
        defaultMGuildRoulettePrizeShouldNotBeFound("rank.lessThan=" + DEFAULT_RANK);

        // Get all the mGuildRoulettePrizeList where rank less than or equals to UPDATED_RANK
        defaultMGuildRoulettePrizeShouldBeFound("rank.lessThan=" + UPDATED_RANK);
    }


    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMGuildRoulettePrizeShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mGuildRoulettePrizeList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMGuildRoulettePrizeShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMGuildRoulettePrizeShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mGuildRoulettePrizeList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMGuildRoulettePrizeShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentType is not null
        defaultMGuildRoulettePrizeShouldBeFound("contentType.specified=true");

        // Get all the mGuildRoulettePrizeList where contentType is null
        defaultMGuildRoulettePrizeShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMGuildRoulettePrizeShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mGuildRoulettePrizeList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMGuildRoulettePrizeShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMGuildRoulettePrizeShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mGuildRoulettePrizeList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMGuildRoulettePrizeShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentId equals to DEFAULT_CONTENT_ID
        defaultMGuildRoulettePrizeShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mGuildRoulettePrizeList where contentId equals to UPDATED_CONTENT_ID
        defaultMGuildRoulettePrizeShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMGuildRoulettePrizeShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mGuildRoulettePrizeList where contentId equals to UPDATED_CONTENT_ID
        defaultMGuildRoulettePrizeShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentId is not null
        defaultMGuildRoulettePrizeShouldBeFound("contentId.specified=true");

        // Get all the mGuildRoulettePrizeList where contentId is null
        defaultMGuildRoulettePrizeShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMGuildRoulettePrizeShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mGuildRoulettePrizeList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMGuildRoulettePrizeShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMGuildRoulettePrizeShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mGuildRoulettePrizeList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMGuildRoulettePrizeShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMGuildRoulettePrizeShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mGuildRoulettePrizeList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMGuildRoulettePrizeShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMGuildRoulettePrizeShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mGuildRoulettePrizeList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMGuildRoulettePrizeShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentAmount is not null
        defaultMGuildRoulettePrizeShouldBeFound("contentAmount.specified=true");

        // Get all the mGuildRoulettePrizeList where contentAmount is null
        defaultMGuildRoulettePrizeShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMGuildRoulettePrizeShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mGuildRoulettePrizeList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMGuildRoulettePrizeShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGuildRoulettePrizesByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        // Get all the mGuildRoulettePrizeList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMGuildRoulettePrizeShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mGuildRoulettePrizeList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMGuildRoulettePrizeShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuildRoulettePrizeShouldBeFound(String filter) throws Exception {
        restMGuildRoulettePrizeMockMvc.perform(get("/api/m-guild-roulette-prizes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildRoulettePrize.getId().intValue())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMGuildRoulettePrizeMockMvc.perform(get("/api/m-guild-roulette-prizes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuildRoulettePrizeShouldNotBeFound(String filter) throws Exception {
        restMGuildRoulettePrizeMockMvc.perform(get("/api/m-guild-roulette-prizes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuildRoulettePrizeMockMvc.perform(get("/api/m-guild-roulette-prizes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuildRoulettePrize() throws Exception {
        // Get the mGuildRoulettePrize
        restMGuildRoulettePrizeMockMvc.perform(get("/api/m-guild-roulette-prizes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuildRoulettePrize() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        int databaseSizeBeforeUpdate = mGuildRoulettePrizeRepository.findAll().size();

        // Update the mGuildRoulettePrize
        MGuildRoulettePrize updatedMGuildRoulettePrize = mGuildRoulettePrizeRepository.findById(mGuildRoulettePrize.getId()).get();
        // Disconnect from session so that the updates on updatedMGuildRoulettePrize are not directly saved in db
        em.detach(updatedMGuildRoulettePrize);
        updatedMGuildRoulettePrize
            .rank(UPDATED_RANK)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO = mGuildRoulettePrizeMapper.toDto(updatedMGuildRoulettePrize);

        restMGuildRoulettePrizeMockMvc.perform(put("/api/m-guild-roulette-prizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildRoulettePrizeDTO)))
            .andExpect(status().isOk());

        // Validate the MGuildRoulettePrize in the database
        List<MGuildRoulettePrize> mGuildRoulettePrizeList = mGuildRoulettePrizeRepository.findAll();
        assertThat(mGuildRoulettePrizeList).hasSize(databaseSizeBeforeUpdate);
        MGuildRoulettePrize testMGuildRoulettePrize = mGuildRoulettePrizeList.get(mGuildRoulettePrizeList.size() - 1);
        assertThat(testMGuildRoulettePrize.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testMGuildRoulettePrize.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMGuildRoulettePrize.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMGuildRoulettePrize.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuildRoulettePrize() throws Exception {
        int databaseSizeBeforeUpdate = mGuildRoulettePrizeRepository.findAll().size();

        // Create the MGuildRoulettePrize
        MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO = mGuildRoulettePrizeMapper.toDto(mGuildRoulettePrize);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuildRoulettePrizeMockMvc.perform(put("/api/m-guild-roulette-prizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildRoulettePrizeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildRoulettePrize in the database
        List<MGuildRoulettePrize> mGuildRoulettePrizeList = mGuildRoulettePrizeRepository.findAll();
        assertThat(mGuildRoulettePrizeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuildRoulettePrize() throws Exception {
        // Initialize the database
        mGuildRoulettePrizeRepository.saveAndFlush(mGuildRoulettePrize);

        int databaseSizeBeforeDelete = mGuildRoulettePrizeRepository.findAll().size();

        // Delete the mGuildRoulettePrize
        restMGuildRoulettePrizeMockMvc.perform(delete("/api/m-guild-roulette-prizes/{id}", mGuildRoulettePrize.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuildRoulettePrize> mGuildRoulettePrizeList = mGuildRoulettePrizeRepository.findAll();
        assertThat(mGuildRoulettePrizeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildRoulettePrize.class);
        MGuildRoulettePrize mGuildRoulettePrize1 = new MGuildRoulettePrize();
        mGuildRoulettePrize1.setId(1L);
        MGuildRoulettePrize mGuildRoulettePrize2 = new MGuildRoulettePrize();
        mGuildRoulettePrize2.setId(mGuildRoulettePrize1.getId());
        assertThat(mGuildRoulettePrize1).isEqualTo(mGuildRoulettePrize2);
        mGuildRoulettePrize2.setId(2L);
        assertThat(mGuildRoulettePrize1).isNotEqualTo(mGuildRoulettePrize2);
        mGuildRoulettePrize1.setId(null);
        assertThat(mGuildRoulettePrize1).isNotEqualTo(mGuildRoulettePrize2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildRoulettePrizeDTO.class);
        MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO1 = new MGuildRoulettePrizeDTO();
        mGuildRoulettePrizeDTO1.setId(1L);
        MGuildRoulettePrizeDTO mGuildRoulettePrizeDTO2 = new MGuildRoulettePrizeDTO();
        assertThat(mGuildRoulettePrizeDTO1).isNotEqualTo(mGuildRoulettePrizeDTO2);
        mGuildRoulettePrizeDTO2.setId(mGuildRoulettePrizeDTO1.getId());
        assertThat(mGuildRoulettePrizeDTO1).isEqualTo(mGuildRoulettePrizeDTO2);
        mGuildRoulettePrizeDTO2.setId(2L);
        assertThat(mGuildRoulettePrizeDTO1).isNotEqualTo(mGuildRoulettePrizeDTO2);
        mGuildRoulettePrizeDTO1.setId(null);
        assertThat(mGuildRoulettePrizeDTO1).isNotEqualTo(mGuildRoulettePrizeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuildRoulettePrizeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuildRoulettePrizeMapper.fromId(null)).isNull();
    }
}
