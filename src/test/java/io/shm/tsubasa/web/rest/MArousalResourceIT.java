package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MArousal;
import io.shm.tsubasa.domain.MPlayableCard;
import io.shm.tsubasa.repository.MArousalRepository;
import io.shm.tsubasa.service.MArousalService;
import io.shm.tsubasa.service.dto.MArousalDTO;
import io.shm.tsubasa.service.mapper.MArousalMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MArousalCriteria;
import io.shm.tsubasa.service.MArousalQueryService;

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
 * Integration tests for the {@Link MArousalResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MArousalResourceIT {

    private static final Integer DEFAULT_BEFORE_ID = 1;
    private static final Integer UPDATED_BEFORE_ID = 2;

    private static final Integer DEFAULT_AFTER_ID = 1;
    private static final Integer UPDATED_AFTER_ID = 2;

    private static final Integer DEFAULT_COST = 1;
    private static final Integer UPDATED_COST = 2;

    private static final Integer DEFAULT_MATERIAL_GROUP_ID = 1;
    private static final Integer UPDATED_MATERIAL_GROUP_ID = 2;

    @Autowired
    private MArousalRepository mArousalRepository;

    @Autowired
    private MArousalMapper mArousalMapper;

    @Autowired
    private MArousalService mArousalService;

    @Autowired
    private MArousalQueryService mArousalQueryService;

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

    private MockMvc restMArousalMockMvc;

    private MArousal mArousal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MArousalResource mArousalResource = new MArousalResource(mArousalService, mArousalQueryService);
        this.restMArousalMockMvc = MockMvcBuilders.standaloneSetup(mArousalResource)
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
    public static MArousal createEntity(EntityManager em) {
        MArousal mArousal = new MArousal()
            .beforeId(DEFAULT_BEFORE_ID)
            .afterId(DEFAULT_AFTER_ID)
            .cost(DEFAULT_COST)
            .materialGroupId(DEFAULT_MATERIAL_GROUP_ID);
        // Add required entity
        MPlayableCard mPlayableCard;
        if (TestUtil.findAll(em, MPlayableCard.class).isEmpty()) {
            mPlayableCard = MPlayableCardResourceIT.createEntity(em);
            em.persist(mPlayableCard);
            em.flush();
        } else {
            mPlayableCard = TestUtil.findAll(em, MPlayableCard.class).get(0);
        }
        mArousal.setMplayablecard(mPlayableCard);
        return mArousal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MArousal createUpdatedEntity(EntityManager em) {
        MArousal mArousal = new MArousal()
            .beforeId(UPDATED_BEFORE_ID)
            .afterId(UPDATED_AFTER_ID)
            .cost(UPDATED_COST)
            .materialGroupId(UPDATED_MATERIAL_GROUP_ID);
        // Add required entity
        MPlayableCard mPlayableCard;
        if (TestUtil.findAll(em, MPlayableCard.class).isEmpty()) {
            mPlayableCard = MPlayableCardResourceIT.createUpdatedEntity(em);
            em.persist(mPlayableCard);
            em.flush();
        } else {
            mPlayableCard = TestUtil.findAll(em, MPlayableCard.class).get(0);
        }
        mArousal.setMplayablecard(mPlayableCard);
        return mArousal;
    }

    @BeforeEach
    public void initTest() {
        mArousal = createEntity(em);
    }

    @Test
    @Transactional
    public void createMArousal() throws Exception {
        int databaseSizeBeforeCreate = mArousalRepository.findAll().size();

        // Create the MArousal
        MArousalDTO mArousalDTO = mArousalMapper.toDto(mArousal);
        restMArousalMockMvc.perform(post("/api/m-arousals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalDTO)))
            .andExpect(status().isCreated());

        // Validate the MArousal in the database
        List<MArousal> mArousalList = mArousalRepository.findAll();
        assertThat(mArousalList).hasSize(databaseSizeBeforeCreate + 1);
        MArousal testMArousal = mArousalList.get(mArousalList.size() - 1);
        assertThat(testMArousal.getBeforeId()).isEqualTo(DEFAULT_BEFORE_ID);
        assertThat(testMArousal.getAfterId()).isEqualTo(DEFAULT_AFTER_ID);
        assertThat(testMArousal.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testMArousal.getMaterialGroupId()).isEqualTo(DEFAULT_MATERIAL_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMArousalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mArousalRepository.findAll().size();

        // Create the MArousal with an existing ID
        mArousal.setId(1L);
        MArousalDTO mArousalDTO = mArousalMapper.toDto(mArousal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMArousalMockMvc.perform(post("/api/m-arousals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MArousal in the database
        List<MArousal> mArousalList = mArousalRepository.findAll();
        assertThat(mArousalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBeforeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mArousalRepository.findAll().size();
        // set the field null
        mArousal.setBeforeId(null);

        // Create the MArousal, which fails.
        MArousalDTO mArousalDTO = mArousalMapper.toDto(mArousal);

        restMArousalMockMvc.perform(post("/api/m-arousals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalDTO)))
            .andExpect(status().isBadRequest());

        List<MArousal> mArousalList = mArousalRepository.findAll();
        assertThat(mArousalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAfterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mArousalRepository.findAll().size();
        // set the field null
        mArousal.setAfterId(null);

        // Create the MArousal, which fails.
        MArousalDTO mArousalDTO = mArousalMapper.toDto(mArousal);

        restMArousalMockMvc.perform(post("/api/m-arousals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalDTO)))
            .andExpect(status().isBadRequest());

        List<MArousal> mArousalList = mArousalRepository.findAll();
        assertThat(mArousalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = mArousalRepository.findAll().size();
        // set the field null
        mArousal.setCost(null);

        // Create the MArousal, which fails.
        MArousalDTO mArousalDTO = mArousalMapper.toDto(mArousal);

        restMArousalMockMvc.perform(post("/api/m-arousals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalDTO)))
            .andExpect(status().isBadRequest());

        List<MArousal> mArousalList = mArousalRepository.findAll();
        assertThat(mArousalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaterialGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mArousalRepository.findAll().size();
        // set the field null
        mArousal.setMaterialGroupId(null);

        // Create the MArousal, which fails.
        MArousalDTO mArousalDTO = mArousalMapper.toDto(mArousal);

        restMArousalMockMvc.perform(post("/api/m-arousals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalDTO)))
            .andExpect(status().isBadRequest());

        List<MArousal> mArousalList = mArousalRepository.findAll();
        assertThat(mArousalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMArousals() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList
        restMArousalMockMvc.perform(get("/api/m-arousals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mArousal.getId().intValue())))
            .andExpect(jsonPath("$.[*].beforeId").value(hasItem(DEFAULT_BEFORE_ID)))
            .andExpect(jsonPath("$.[*].afterId").value(hasItem(DEFAULT_AFTER_ID)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)))
            .andExpect(jsonPath("$.[*].materialGroupId").value(hasItem(DEFAULT_MATERIAL_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMArousal() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get the mArousal
        restMArousalMockMvc.perform(get("/api/m-arousals/{id}", mArousal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mArousal.getId().intValue()))
            .andExpect(jsonPath("$.beforeId").value(DEFAULT_BEFORE_ID))
            .andExpect(jsonPath("$.afterId").value(DEFAULT_AFTER_ID))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST))
            .andExpect(jsonPath("$.materialGroupId").value(DEFAULT_MATERIAL_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMArousalsByBeforeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where beforeId equals to DEFAULT_BEFORE_ID
        defaultMArousalShouldBeFound("beforeId.equals=" + DEFAULT_BEFORE_ID);

        // Get all the mArousalList where beforeId equals to UPDATED_BEFORE_ID
        defaultMArousalShouldNotBeFound("beforeId.equals=" + UPDATED_BEFORE_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalsByBeforeIdIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where beforeId in DEFAULT_BEFORE_ID or UPDATED_BEFORE_ID
        defaultMArousalShouldBeFound("beforeId.in=" + DEFAULT_BEFORE_ID + "," + UPDATED_BEFORE_ID);

        // Get all the mArousalList where beforeId equals to UPDATED_BEFORE_ID
        defaultMArousalShouldNotBeFound("beforeId.in=" + UPDATED_BEFORE_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalsByBeforeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where beforeId is not null
        defaultMArousalShouldBeFound("beforeId.specified=true");

        // Get all the mArousalList where beforeId is null
        defaultMArousalShouldNotBeFound("beforeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalsByBeforeIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where beforeId greater than or equals to DEFAULT_BEFORE_ID
        defaultMArousalShouldBeFound("beforeId.greaterOrEqualThan=" + DEFAULT_BEFORE_ID);

        // Get all the mArousalList where beforeId greater than or equals to UPDATED_BEFORE_ID
        defaultMArousalShouldNotBeFound("beforeId.greaterOrEqualThan=" + UPDATED_BEFORE_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalsByBeforeIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where beforeId less than or equals to DEFAULT_BEFORE_ID
        defaultMArousalShouldNotBeFound("beforeId.lessThan=" + DEFAULT_BEFORE_ID);

        // Get all the mArousalList where beforeId less than or equals to UPDATED_BEFORE_ID
        defaultMArousalShouldBeFound("beforeId.lessThan=" + UPDATED_BEFORE_ID);
    }


    @Test
    @Transactional
    public void getAllMArousalsByAfterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where afterId equals to DEFAULT_AFTER_ID
        defaultMArousalShouldBeFound("afterId.equals=" + DEFAULT_AFTER_ID);

        // Get all the mArousalList where afterId equals to UPDATED_AFTER_ID
        defaultMArousalShouldNotBeFound("afterId.equals=" + UPDATED_AFTER_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalsByAfterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where afterId in DEFAULT_AFTER_ID or UPDATED_AFTER_ID
        defaultMArousalShouldBeFound("afterId.in=" + DEFAULT_AFTER_ID + "," + UPDATED_AFTER_ID);

        // Get all the mArousalList where afterId equals to UPDATED_AFTER_ID
        defaultMArousalShouldNotBeFound("afterId.in=" + UPDATED_AFTER_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalsByAfterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where afterId is not null
        defaultMArousalShouldBeFound("afterId.specified=true");

        // Get all the mArousalList where afterId is null
        defaultMArousalShouldNotBeFound("afterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalsByAfterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where afterId greater than or equals to DEFAULT_AFTER_ID
        defaultMArousalShouldBeFound("afterId.greaterOrEqualThan=" + DEFAULT_AFTER_ID);

        // Get all the mArousalList where afterId greater than or equals to UPDATED_AFTER_ID
        defaultMArousalShouldNotBeFound("afterId.greaterOrEqualThan=" + UPDATED_AFTER_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalsByAfterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where afterId less than or equals to DEFAULT_AFTER_ID
        defaultMArousalShouldNotBeFound("afterId.lessThan=" + DEFAULT_AFTER_ID);

        // Get all the mArousalList where afterId less than or equals to UPDATED_AFTER_ID
        defaultMArousalShouldBeFound("afterId.lessThan=" + UPDATED_AFTER_ID);
    }


    @Test
    @Transactional
    public void getAllMArousalsByCostIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where cost equals to DEFAULT_COST
        defaultMArousalShouldBeFound("cost.equals=" + DEFAULT_COST);

        // Get all the mArousalList where cost equals to UPDATED_COST
        defaultMArousalShouldNotBeFound("cost.equals=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMArousalsByCostIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where cost in DEFAULT_COST or UPDATED_COST
        defaultMArousalShouldBeFound("cost.in=" + DEFAULT_COST + "," + UPDATED_COST);

        // Get all the mArousalList where cost equals to UPDATED_COST
        defaultMArousalShouldNotBeFound("cost.in=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMArousalsByCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where cost is not null
        defaultMArousalShouldBeFound("cost.specified=true");

        // Get all the mArousalList where cost is null
        defaultMArousalShouldNotBeFound("cost.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalsByCostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where cost greater than or equals to DEFAULT_COST
        defaultMArousalShouldBeFound("cost.greaterOrEqualThan=" + DEFAULT_COST);

        // Get all the mArousalList where cost greater than or equals to UPDATED_COST
        defaultMArousalShouldNotBeFound("cost.greaterOrEqualThan=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMArousalsByCostIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where cost less than or equals to DEFAULT_COST
        defaultMArousalShouldNotBeFound("cost.lessThan=" + DEFAULT_COST);

        // Get all the mArousalList where cost less than or equals to UPDATED_COST
        defaultMArousalShouldBeFound("cost.lessThan=" + UPDATED_COST);
    }


    @Test
    @Transactional
    public void getAllMArousalsByMaterialGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where materialGroupId equals to DEFAULT_MATERIAL_GROUP_ID
        defaultMArousalShouldBeFound("materialGroupId.equals=" + DEFAULT_MATERIAL_GROUP_ID);

        // Get all the mArousalList where materialGroupId equals to UPDATED_MATERIAL_GROUP_ID
        defaultMArousalShouldNotBeFound("materialGroupId.equals=" + UPDATED_MATERIAL_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalsByMaterialGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where materialGroupId in DEFAULT_MATERIAL_GROUP_ID or UPDATED_MATERIAL_GROUP_ID
        defaultMArousalShouldBeFound("materialGroupId.in=" + DEFAULT_MATERIAL_GROUP_ID + "," + UPDATED_MATERIAL_GROUP_ID);

        // Get all the mArousalList where materialGroupId equals to UPDATED_MATERIAL_GROUP_ID
        defaultMArousalShouldNotBeFound("materialGroupId.in=" + UPDATED_MATERIAL_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalsByMaterialGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where materialGroupId is not null
        defaultMArousalShouldBeFound("materialGroupId.specified=true");

        // Get all the mArousalList where materialGroupId is null
        defaultMArousalShouldNotBeFound("materialGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalsByMaterialGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where materialGroupId greater than or equals to DEFAULT_MATERIAL_GROUP_ID
        defaultMArousalShouldBeFound("materialGroupId.greaterOrEqualThan=" + DEFAULT_MATERIAL_GROUP_ID);

        // Get all the mArousalList where materialGroupId greater than or equals to UPDATED_MATERIAL_GROUP_ID
        defaultMArousalShouldNotBeFound("materialGroupId.greaterOrEqualThan=" + UPDATED_MATERIAL_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMArousalsByMaterialGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        // Get all the mArousalList where materialGroupId less than or equals to DEFAULT_MATERIAL_GROUP_ID
        defaultMArousalShouldNotBeFound("materialGroupId.lessThan=" + DEFAULT_MATERIAL_GROUP_ID);

        // Get all the mArousalList where materialGroupId less than or equals to UPDATED_MATERIAL_GROUP_ID
        defaultMArousalShouldBeFound("materialGroupId.lessThan=" + UPDATED_MATERIAL_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMArousalsByMplayablecardIsEqualToSomething() throws Exception {
        // Get already existing entity
        MPlayableCard mplayablecard = mArousal.getMplayablecard();
        mArousalRepository.saveAndFlush(mArousal);
        Long mplayablecardId = mplayablecard.getId();

        // Get all the mArousalList where mplayablecard equals to mplayablecardId
        defaultMArousalShouldBeFound("mplayablecardId.equals=" + mplayablecardId);

        // Get all the mArousalList where mplayablecard equals to mplayablecardId + 1
        defaultMArousalShouldNotBeFound("mplayablecardId.equals=" + (mplayablecardId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMArousalShouldBeFound(String filter) throws Exception {
        restMArousalMockMvc.perform(get("/api/m-arousals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mArousal.getId().intValue())))
            .andExpect(jsonPath("$.[*].beforeId").value(hasItem(DEFAULT_BEFORE_ID)))
            .andExpect(jsonPath("$.[*].afterId").value(hasItem(DEFAULT_AFTER_ID)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)))
            .andExpect(jsonPath("$.[*].materialGroupId").value(hasItem(DEFAULT_MATERIAL_GROUP_ID)));

        // Check, that the count call also returns 1
        restMArousalMockMvc.perform(get("/api/m-arousals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMArousalShouldNotBeFound(String filter) throws Exception {
        restMArousalMockMvc.perform(get("/api/m-arousals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMArousalMockMvc.perform(get("/api/m-arousals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMArousal() throws Exception {
        // Get the mArousal
        restMArousalMockMvc.perform(get("/api/m-arousals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMArousal() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        int databaseSizeBeforeUpdate = mArousalRepository.findAll().size();

        // Update the mArousal
        MArousal updatedMArousal = mArousalRepository.findById(mArousal.getId()).get();
        // Disconnect from session so that the updates on updatedMArousal are not directly saved in db
        em.detach(updatedMArousal);
        updatedMArousal
            .beforeId(UPDATED_BEFORE_ID)
            .afterId(UPDATED_AFTER_ID)
            .cost(UPDATED_COST)
            .materialGroupId(UPDATED_MATERIAL_GROUP_ID);
        MArousalDTO mArousalDTO = mArousalMapper.toDto(updatedMArousal);

        restMArousalMockMvc.perform(put("/api/m-arousals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalDTO)))
            .andExpect(status().isOk());

        // Validate the MArousal in the database
        List<MArousal> mArousalList = mArousalRepository.findAll();
        assertThat(mArousalList).hasSize(databaseSizeBeforeUpdate);
        MArousal testMArousal = mArousalList.get(mArousalList.size() - 1);
        assertThat(testMArousal.getBeforeId()).isEqualTo(UPDATED_BEFORE_ID);
        assertThat(testMArousal.getAfterId()).isEqualTo(UPDATED_AFTER_ID);
        assertThat(testMArousal.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testMArousal.getMaterialGroupId()).isEqualTo(UPDATED_MATERIAL_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMArousal() throws Exception {
        int databaseSizeBeforeUpdate = mArousalRepository.findAll().size();

        // Create the MArousal
        MArousalDTO mArousalDTO = mArousalMapper.toDto(mArousal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMArousalMockMvc.perform(put("/api/m-arousals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MArousal in the database
        List<MArousal> mArousalList = mArousalRepository.findAll();
        assertThat(mArousalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMArousal() throws Exception {
        // Initialize the database
        mArousalRepository.saveAndFlush(mArousal);

        int databaseSizeBeforeDelete = mArousalRepository.findAll().size();

        // Delete the mArousal
        restMArousalMockMvc.perform(delete("/api/m-arousals/{id}", mArousal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MArousal> mArousalList = mArousalRepository.findAll();
        assertThat(mArousalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MArousal.class);
        MArousal mArousal1 = new MArousal();
        mArousal1.setId(1L);
        MArousal mArousal2 = new MArousal();
        mArousal2.setId(mArousal1.getId());
        assertThat(mArousal1).isEqualTo(mArousal2);
        mArousal2.setId(2L);
        assertThat(mArousal1).isNotEqualTo(mArousal2);
        mArousal1.setId(null);
        assertThat(mArousal1).isNotEqualTo(mArousal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MArousalDTO.class);
        MArousalDTO mArousalDTO1 = new MArousalDTO();
        mArousalDTO1.setId(1L);
        MArousalDTO mArousalDTO2 = new MArousalDTO();
        assertThat(mArousalDTO1).isNotEqualTo(mArousalDTO2);
        mArousalDTO2.setId(mArousalDTO1.getId());
        assertThat(mArousalDTO1).isEqualTo(mArousalDTO2);
        mArousalDTO2.setId(2L);
        assertThat(mArousalDTO1).isNotEqualTo(mArousalDTO2);
        mArousalDTO1.setId(null);
        assertThat(mArousalDTO1).isNotEqualTo(mArousalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mArousalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mArousalMapper.fromId(null)).isNull();
    }
}
