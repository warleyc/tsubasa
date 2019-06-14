package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPowerupActionSkillCost;
import io.shm.tsubasa.repository.MPowerupActionSkillCostRepository;
import io.shm.tsubasa.service.MPowerupActionSkillCostService;
import io.shm.tsubasa.service.dto.MPowerupActionSkillCostDTO;
import io.shm.tsubasa.service.mapper.MPowerupActionSkillCostMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPowerupActionSkillCostCriteria;
import io.shm.tsubasa.service.MPowerupActionSkillCostQueryService;

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
 * Integration tests for the {@Link MPowerupActionSkillCostResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPowerupActionSkillCostResourceIT {

    private static final Integer DEFAULT_ACTION_RARITY = 1;
    private static final Integer UPDATED_ACTION_RARITY = 2;

    private static final Integer DEFAULT_ACTION_LEVEL = 1;
    private static final Integer UPDATED_ACTION_LEVEL = 2;

    private static final Integer DEFAULT_COST = 1;
    private static final Integer UPDATED_COST = 2;

    @Autowired
    private MPowerupActionSkillCostRepository mPowerupActionSkillCostRepository;

    @Autowired
    private MPowerupActionSkillCostMapper mPowerupActionSkillCostMapper;

    @Autowired
    private MPowerupActionSkillCostService mPowerupActionSkillCostService;

    @Autowired
    private MPowerupActionSkillCostQueryService mPowerupActionSkillCostQueryService;

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

    private MockMvc restMPowerupActionSkillCostMockMvc;

    private MPowerupActionSkillCost mPowerupActionSkillCost;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPowerupActionSkillCostResource mPowerupActionSkillCostResource = new MPowerupActionSkillCostResource(mPowerupActionSkillCostService, mPowerupActionSkillCostQueryService);
        this.restMPowerupActionSkillCostMockMvc = MockMvcBuilders.standaloneSetup(mPowerupActionSkillCostResource)
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
    public static MPowerupActionSkillCost createEntity(EntityManager em) {
        MPowerupActionSkillCost mPowerupActionSkillCost = new MPowerupActionSkillCost()
            .actionRarity(DEFAULT_ACTION_RARITY)
            .actionLevel(DEFAULT_ACTION_LEVEL)
            .cost(DEFAULT_COST);
        return mPowerupActionSkillCost;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPowerupActionSkillCost createUpdatedEntity(EntityManager em) {
        MPowerupActionSkillCost mPowerupActionSkillCost = new MPowerupActionSkillCost()
            .actionRarity(UPDATED_ACTION_RARITY)
            .actionLevel(UPDATED_ACTION_LEVEL)
            .cost(UPDATED_COST);
        return mPowerupActionSkillCost;
    }

    @BeforeEach
    public void initTest() {
        mPowerupActionSkillCost = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPowerupActionSkillCost() throws Exception {
        int databaseSizeBeforeCreate = mPowerupActionSkillCostRepository.findAll().size();

        // Create the MPowerupActionSkillCost
        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO = mPowerupActionSkillCostMapper.toDto(mPowerupActionSkillCost);
        restMPowerupActionSkillCostMockMvc.perform(post("/api/m-powerup-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPowerupActionSkillCostDTO)))
            .andExpect(status().isCreated());

        // Validate the MPowerupActionSkillCost in the database
        List<MPowerupActionSkillCost> mPowerupActionSkillCostList = mPowerupActionSkillCostRepository.findAll();
        assertThat(mPowerupActionSkillCostList).hasSize(databaseSizeBeforeCreate + 1);
        MPowerupActionSkillCost testMPowerupActionSkillCost = mPowerupActionSkillCostList.get(mPowerupActionSkillCostList.size() - 1);
        assertThat(testMPowerupActionSkillCost.getActionRarity()).isEqualTo(DEFAULT_ACTION_RARITY);
        assertThat(testMPowerupActionSkillCost.getActionLevel()).isEqualTo(DEFAULT_ACTION_LEVEL);
        assertThat(testMPowerupActionSkillCost.getCost()).isEqualTo(DEFAULT_COST);
    }

    @Test
    @Transactional
    public void createMPowerupActionSkillCostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPowerupActionSkillCostRepository.findAll().size();

        // Create the MPowerupActionSkillCost with an existing ID
        mPowerupActionSkillCost.setId(1L);
        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO = mPowerupActionSkillCostMapper.toDto(mPowerupActionSkillCost);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPowerupActionSkillCostMockMvc.perform(post("/api/m-powerup-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPowerupActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPowerupActionSkillCost in the database
        List<MPowerupActionSkillCost> mPowerupActionSkillCostList = mPowerupActionSkillCostRepository.findAll();
        assertThat(mPowerupActionSkillCostList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActionRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPowerupActionSkillCostRepository.findAll().size();
        // set the field null
        mPowerupActionSkillCost.setActionRarity(null);

        // Create the MPowerupActionSkillCost, which fails.
        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO = mPowerupActionSkillCostMapper.toDto(mPowerupActionSkillCost);

        restMPowerupActionSkillCostMockMvc.perform(post("/api/m-powerup-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPowerupActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        List<MPowerupActionSkillCost> mPowerupActionSkillCostList = mPowerupActionSkillCostRepository.findAll();
        assertThat(mPowerupActionSkillCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPowerupActionSkillCostRepository.findAll().size();
        // set the field null
        mPowerupActionSkillCost.setActionLevel(null);

        // Create the MPowerupActionSkillCost, which fails.
        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO = mPowerupActionSkillCostMapper.toDto(mPowerupActionSkillCost);

        restMPowerupActionSkillCostMockMvc.perform(post("/api/m-powerup-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPowerupActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        List<MPowerupActionSkillCost> mPowerupActionSkillCostList = mPowerupActionSkillCostRepository.findAll();
        assertThat(mPowerupActionSkillCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPowerupActionSkillCostRepository.findAll().size();
        // set the field null
        mPowerupActionSkillCost.setCost(null);

        // Create the MPowerupActionSkillCost, which fails.
        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO = mPowerupActionSkillCostMapper.toDto(mPowerupActionSkillCost);

        restMPowerupActionSkillCostMockMvc.perform(post("/api/m-powerup-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPowerupActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        List<MPowerupActionSkillCost> mPowerupActionSkillCostList = mPowerupActionSkillCostRepository.findAll();
        assertThat(mPowerupActionSkillCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCosts() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList
        restMPowerupActionSkillCostMockMvc.perform(get("/api/m-powerup-action-skill-costs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPowerupActionSkillCost.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionRarity").value(hasItem(DEFAULT_ACTION_RARITY)))
            .andExpect(jsonPath("$.[*].actionLevel").value(hasItem(DEFAULT_ACTION_LEVEL)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)));
    }
    
    @Test
    @Transactional
    public void getMPowerupActionSkillCost() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get the mPowerupActionSkillCost
        restMPowerupActionSkillCostMockMvc.perform(get("/api/m-powerup-action-skill-costs/{id}", mPowerupActionSkillCost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPowerupActionSkillCost.getId().intValue()))
            .andExpect(jsonPath("$.actionRarity").value(DEFAULT_ACTION_RARITY))
            .andExpect(jsonPath("$.actionLevel").value(DEFAULT_ACTION_LEVEL))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST));
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionRarity equals to DEFAULT_ACTION_RARITY
        defaultMPowerupActionSkillCostShouldBeFound("actionRarity.equals=" + DEFAULT_ACTION_RARITY);

        // Get all the mPowerupActionSkillCostList where actionRarity equals to UPDATED_ACTION_RARITY
        defaultMPowerupActionSkillCostShouldNotBeFound("actionRarity.equals=" + UPDATED_ACTION_RARITY);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionRarity in DEFAULT_ACTION_RARITY or UPDATED_ACTION_RARITY
        defaultMPowerupActionSkillCostShouldBeFound("actionRarity.in=" + DEFAULT_ACTION_RARITY + "," + UPDATED_ACTION_RARITY);

        // Get all the mPowerupActionSkillCostList where actionRarity equals to UPDATED_ACTION_RARITY
        defaultMPowerupActionSkillCostShouldNotBeFound("actionRarity.in=" + UPDATED_ACTION_RARITY);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionRarity is not null
        defaultMPowerupActionSkillCostShouldBeFound("actionRarity.specified=true");

        // Get all the mPowerupActionSkillCostList where actionRarity is null
        defaultMPowerupActionSkillCostShouldNotBeFound("actionRarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionRarity greater than or equals to DEFAULT_ACTION_RARITY
        defaultMPowerupActionSkillCostShouldBeFound("actionRarity.greaterOrEqualThan=" + DEFAULT_ACTION_RARITY);

        // Get all the mPowerupActionSkillCostList where actionRarity greater than or equals to UPDATED_ACTION_RARITY
        defaultMPowerupActionSkillCostShouldNotBeFound("actionRarity.greaterOrEqualThan=" + UPDATED_ACTION_RARITY);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionRarity less than or equals to DEFAULT_ACTION_RARITY
        defaultMPowerupActionSkillCostShouldNotBeFound("actionRarity.lessThan=" + DEFAULT_ACTION_RARITY);

        // Get all the mPowerupActionSkillCostList where actionRarity less than or equals to UPDATED_ACTION_RARITY
        defaultMPowerupActionSkillCostShouldBeFound("actionRarity.lessThan=" + UPDATED_ACTION_RARITY);
    }


    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionLevel equals to DEFAULT_ACTION_LEVEL
        defaultMPowerupActionSkillCostShouldBeFound("actionLevel.equals=" + DEFAULT_ACTION_LEVEL);

        // Get all the mPowerupActionSkillCostList where actionLevel equals to UPDATED_ACTION_LEVEL
        defaultMPowerupActionSkillCostShouldNotBeFound("actionLevel.equals=" + UPDATED_ACTION_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionLevel in DEFAULT_ACTION_LEVEL or UPDATED_ACTION_LEVEL
        defaultMPowerupActionSkillCostShouldBeFound("actionLevel.in=" + DEFAULT_ACTION_LEVEL + "," + UPDATED_ACTION_LEVEL);

        // Get all the mPowerupActionSkillCostList where actionLevel equals to UPDATED_ACTION_LEVEL
        defaultMPowerupActionSkillCostShouldNotBeFound("actionLevel.in=" + UPDATED_ACTION_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionLevel is not null
        defaultMPowerupActionSkillCostShouldBeFound("actionLevel.specified=true");

        // Get all the mPowerupActionSkillCostList where actionLevel is null
        defaultMPowerupActionSkillCostShouldNotBeFound("actionLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionLevel greater than or equals to DEFAULT_ACTION_LEVEL
        defaultMPowerupActionSkillCostShouldBeFound("actionLevel.greaterOrEqualThan=" + DEFAULT_ACTION_LEVEL);

        // Get all the mPowerupActionSkillCostList where actionLevel greater than or equals to UPDATED_ACTION_LEVEL
        defaultMPowerupActionSkillCostShouldNotBeFound("actionLevel.greaterOrEqualThan=" + UPDATED_ACTION_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByActionLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where actionLevel less than or equals to DEFAULT_ACTION_LEVEL
        defaultMPowerupActionSkillCostShouldNotBeFound("actionLevel.lessThan=" + DEFAULT_ACTION_LEVEL);

        // Get all the mPowerupActionSkillCostList where actionLevel less than or equals to UPDATED_ACTION_LEVEL
        defaultMPowerupActionSkillCostShouldBeFound("actionLevel.lessThan=" + UPDATED_ACTION_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByCostIsEqualToSomething() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where cost equals to DEFAULT_COST
        defaultMPowerupActionSkillCostShouldBeFound("cost.equals=" + DEFAULT_COST);

        // Get all the mPowerupActionSkillCostList where cost equals to UPDATED_COST
        defaultMPowerupActionSkillCostShouldNotBeFound("cost.equals=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByCostIsInShouldWork() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where cost in DEFAULT_COST or UPDATED_COST
        defaultMPowerupActionSkillCostShouldBeFound("cost.in=" + DEFAULT_COST + "," + UPDATED_COST);

        // Get all the mPowerupActionSkillCostList where cost equals to UPDATED_COST
        defaultMPowerupActionSkillCostShouldNotBeFound("cost.in=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where cost is not null
        defaultMPowerupActionSkillCostShouldBeFound("cost.specified=true");

        // Get all the mPowerupActionSkillCostList where cost is null
        defaultMPowerupActionSkillCostShouldNotBeFound("cost.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByCostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where cost greater than or equals to DEFAULT_COST
        defaultMPowerupActionSkillCostShouldBeFound("cost.greaterOrEqualThan=" + DEFAULT_COST);

        // Get all the mPowerupActionSkillCostList where cost greater than or equals to UPDATED_COST
        defaultMPowerupActionSkillCostShouldNotBeFound("cost.greaterOrEqualThan=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMPowerupActionSkillCostsByCostIsLessThanSomething() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        // Get all the mPowerupActionSkillCostList where cost less than or equals to DEFAULT_COST
        defaultMPowerupActionSkillCostShouldNotBeFound("cost.lessThan=" + DEFAULT_COST);

        // Get all the mPowerupActionSkillCostList where cost less than or equals to UPDATED_COST
        defaultMPowerupActionSkillCostShouldBeFound("cost.lessThan=" + UPDATED_COST);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPowerupActionSkillCostShouldBeFound(String filter) throws Exception {
        restMPowerupActionSkillCostMockMvc.perform(get("/api/m-powerup-action-skill-costs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPowerupActionSkillCost.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionRarity").value(hasItem(DEFAULT_ACTION_RARITY)))
            .andExpect(jsonPath("$.[*].actionLevel").value(hasItem(DEFAULT_ACTION_LEVEL)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)));

        // Check, that the count call also returns 1
        restMPowerupActionSkillCostMockMvc.perform(get("/api/m-powerup-action-skill-costs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPowerupActionSkillCostShouldNotBeFound(String filter) throws Exception {
        restMPowerupActionSkillCostMockMvc.perform(get("/api/m-powerup-action-skill-costs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPowerupActionSkillCostMockMvc.perform(get("/api/m-powerup-action-skill-costs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPowerupActionSkillCost() throws Exception {
        // Get the mPowerupActionSkillCost
        restMPowerupActionSkillCostMockMvc.perform(get("/api/m-powerup-action-skill-costs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPowerupActionSkillCost() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        int databaseSizeBeforeUpdate = mPowerupActionSkillCostRepository.findAll().size();

        // Update the mPowerupActionSkillCost
        MPowerupActionSkillCost updatedMPowerupActionSkillCost = mPowerupActionSkillCostRepository.findById(mPowerupActionSkillCost.getId()).get();
        // Disconnect from session so that the updates on updatedMPowerupActionSkillCost are not directly saved in db
        em.detach(updatedMPowerupActionSkillCost);
        updatedMPowerupActionSkillCost
            .actionRarity(UPDATED_ACTION_RARITY)
            .actionLevel(UPDATED_ACTION_LEVEL)
            .cost(UPDATED_COST);
        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO = mPowerupActionSkillCostMapper.toDto(updatedMPowerupActionSkillCost);

        restMPowerupActionSkillCostMockMvc.perform(put("/api/m-powerup-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPowerupActionSkillCostDTO)))
            .andExpect(status().isOk());

        // Validate the MPowerupActionSkillCost in the database
        List<MPowerupActionSkillCost> mPowerupActionSkillCostList = mPowerupActionSkillCostRepository.findAll();
        assertThat(mPowerupActionSkillCostList).hasSize(databaseSizeBeforeUpdate);
        MPowerupActionSkillCost testMPowerupActionSkillCost = mPowerupActionSkillCostList.get(mPowerupActionSkillCostList.size() - 1);
        assertThat(testMPowerupActionSkillCost.getActionRarity()).isEqualTo(UPDATED_ACTION_RARITY);
        assertThat(testMPowerupActionSkillCost.getActionLevel()).isEqualTo(UPDATED_ACTION_LEVEL);
        assertThat(testMPowerupActionSkillCost.getCost()).isEqualTo(UPDATED_COST);
    }

    @Test
    @Transactional
    public void updateNonExistingMPowerupActionSkillCost() throws Exception {
        int databaseSizeBeforeUpdate = mPowerupActionSkillCostRepository.findAll().size();

        // Create the MPowerupActionSkillCost
        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO = mPowerupActionSkillCostMapper.toDto(mPowerupActionSkillCost);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPowerupActionSkillCostMockMvc.perform(put("/api/m-powerup-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPowerupActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPowerupActionSkillCost in the database
        List<MPowerupActionSkillCost> mPowerupActionSkillCostList = mPowerupActionSkillCostRepository.findAll();
        assertThat(mPowerupActionSkillCostList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPowerupActionSkillCost() throws Exception {
        // Initialize the database
        mPowerupActionSkillCostRepository.saveAndFlush(mPowerupActionSkillCost);

        int databaseSizeBeforeDelete = mPowerupActionSkillCostRepository.findAll().size();

        // Delete the mPowerupActionSkillCost
        restMPowerupActionSkillCostMockMvc.perform(delete("/api/m-powerup-action-skill-costs/{id}", mPowerupActionSkillCost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPowerupActionSkillCost> mPowerupActionSkillCostList = mPowerupActionSkillCostRepository.findAll();
        assertThat(mPowerupActionSkillCostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPowerupActionSkillCost.class);
        MPowerupActionSkillCost mPowerupActionSkillCost1 = new MPowerupActionSkillCost();
        mPowerupActionSkillCost1.setId(1L);
        MPowerupActionSkillCost mPowerupActionSkillCost2 = new MPowerupActionSkillCost();
        mPowerupActionSkillCost2.setId(mPowerupActionSkillCost1.getId());
        assertThat(mPowerupActionSkillCost1).isEqualTo(mPowerupActionSkillCost2);
        mPowerupActionSkillCost2.setId(2L);
        assertThat(mPowerupActionSkillCost1).isNotEqualTo(mPowerupActionSkillCost2);
        mPowerupActionSkillCost1.setId(null);
        assertThat(mPowerupActionSkillCost1).isNotEqualTo(mPowerupActionSkillCost2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPowerupActionSkillCostDTO.class);
        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO1 = new MPowerupActionSkillCostDTO();
        mPowerupActionSkillCostDTO1.setId(1L);
        MPowerupActionSkillCostDTO mPowerupActionSkillCostDTO2 = new MPowerupActionSkillCostDTO();
        assertThat(mPowerupActionSkillCostDTO1).isNotEqualTo(mPowerupActionSkillCostDTO2);
        mPowerupActionSkillCostDTO2.setId(mPowerupActionSkillCostDTO1.getId());
        assertThat(mPowerupActionSkillCostDTO1).isEqualTo(mPowerupActionSkillCostDTO2);
        mPowerupActionSkillCostDTO2.setId(2L);
        assertThat(mPowerupActionSkillCostDTO1).isNotEqualTo(mPowerupActionSkillCostDTO2);
        mPowerupActionSkillCostDTO1.setId(null);
        assertThat(mPowerupActionSkillCostDTO1).isNotEqualTo(mPowerupActionSkillCostDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPowerupActionSkillCostMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPowerupActionSkillCostMapper.fromId(null)).isNull();
    }
}
