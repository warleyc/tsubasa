package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MInheritActionSkillCost;
import io.shm.tsubasa.repository.MInheritActionSkillCostRepository;
import io.shm.tsubasa.service.MInheritActionSkillCostService;
import io.shm.tsubasa.service.dto.MInheritActionSkillCostDTO;
import io.shm.tsubasa.service.mapper.MInheritActionSkillCostMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MInheritActionSkillCostCriteria;
import io.shm.tsubasa.service.MInheritActionSkillCostQueryService;

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
 * Integration tests for the {@Link MInheritActionSkillCostResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MInheritActionSkillCostResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_COST = 1;
    private static final Integer UPDATED_COST = 2;

    @Autowired
    private MInheritActionSkillCostRepository mInheritActionSkillCostRepository;

    @Autowired
    private MInheritActionSkillCostMapper mInheritActionSkillCostMapper;

    @Autowired
    private MInheritActionSkillCostService mInheritActionSkillCostService;

    @Autowired
    private MInheritActionSkillCostQueryService mInheritActionSkillCostQueryService;

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

    private MockMvc restMInheritActionSkillCostMockMvc;

    private MInheritActionSkillCost mInheritActionSkillCost;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MInheritActionSkillCostResource mInheritActionSkillCostResource = new MInheritActionSkillCostResource(mInheritActionSkillCostService, mInheritActionSkillCostQueryService);
        this.restMInheritActionSkillCostMockMvc = MockMvcBuilders.standaloneSetup(mInheritActionSkillCostResource)
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
    public static MInheritActionSkillCost createEntity(EntityManager em) {
        MInheritActionSkillCost mInheritActionSkillCost = new MInheritActionSkillCost()
            .rarity(DEFAULT_RARITY)
            .level(DEFAULT_LEVEL)
            .cost(DEFAULT_COST);
        return mInheritActionSkillCost;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MInheritActionSkillCost createUpdatedEntity(EntityManager em) {
        MInheritActionSkillCost mInheritActionSkillCost = new MInheritActionSkillCost()
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .cost(UPDATED_COST);
        return mInheritActionSkillCost;
    }

    @BeforeEach
    public void initTest() {
        mInheritActionSkillCost = createEntity(em);
    }

    @Test
    @Transactional
    public void createMInheritActionSkillCost() throws Exception {
        int databaseSizeBeforeCreate = mInheritActionSkillCostRepository.findAll().size();

        // Create the MInheritActionSkillCost
        MInheritActionSkillCostDTO mInheritActionSkillCostDTO = mInheritActionSkillCostMapper.toDto(mInheritActionSkillCost);
        restMInheritActionSkillCostMockMvc.perform(post("/api/m-inherit-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInheritActionSkillCostDTO)))
            .andExpect(status().isCreated());

        // Validate the MInheritActionSkillCost in the database
        List<MInheritActionSkillCost> mInheritActionSkillCostList = mInheritActionSkillCostRepository.findAll();
        assertThat(mInheritActionSkillCostList).hasSize(databaseSizeBeforeCreate + 1);
        MInheritActionSkillCost testMInheritActionSkillCost = mInheritActionSkillCostList.get(mInheritActionSkillCostList.size() - 1);
        assertThat(testMInheritActionSkillCost.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMInheritActionSkillCost.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMInheritActionSkillCost.getCost()).isEqualTo(DEFAULT_COST);
    }

    @Test
    @Transactional
    public void createMInheritActionSkillCostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mInheritActionSkillCostRepository.findAll().size();

        // Create the MInheritActionSkillCost with an existing ID
        mInheritActionSkillCost.setId(1L);
        MInheritActionSkillCostDTO mInheritActionSkillCostDTO = mInheritActionSkillCostMapper.toDto(mInheritActionSkillCost);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMInheritActionSkillCostMockMvc.perform(post("/api/m-inherit-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInheritActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MInheritActionSkillCost in the database
        List<MInheritActionSkillCost> mInheritActionSkillCostList = mInheritActionSkillCostRepository.findAll();
        assertThat(mInheritActionSkillCostList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mInheritActionSkillCostRepository.findAll().size();
        // set the field null
        mInheritActionSkillCost.setRarity(null);

        // Create the MInheritActionSkillCost, which fails.
        MInheritActionSkillCostDTO mInheritActionSkillCostDTO = mInheritActionSkillCostMapper.toDto(mInheritActionSkillCost);

        restMInheritActionSkillCostMockMvc.perform(post("/api/m-inherit-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInheritActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        List<MInheritActionSkillCost> mInheritActionSkillCostList = mInheritActionSkillCostRepository.findAll();
        assertThat(mInheritActionSkillCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mInheritActionSkillCostRepository.findAll().size();
        // set the field null
        mInheritActionSkillCost.setLevel(null);

        // Create the MInheritActionSkillCost, which fails.
        MInheritActionSkillCostDTO mInheritActionSkillCostDTO = mInheritActionSkillCostMapper.toDto(mInheritActionSkillCost);

        restMInheritActionSkillCostMockMvc.perform(post("/api/m-inherit-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInheritActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        List<MInheritActionSkillCost> mInheritActionSkillCostList = mInheritActionSkillCostRepository.findAll();
        assertThat(mInheritActionSkillCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = mInheritActionSkillCostRepository.findAll().size();
        // set the field null
        mInheritActionSkillCost.setCost(null);

        // Create the MInheritActionSkillCost, which fails.
        MInheritActionSkillCostDTO mInheritActionSkillCostDTO = mInheritActionSkillCostMapper.toDto(mInheritActionSkillCost);

        restMInheritActionSkillCostMockMvc.perform(post("/api/m-inherit-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInheritActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        List<MInheritActionSkillCost> mInheritActionSkillCostList = mInheritActionSkillCostRepository.findAll();
        assertThat(mInheritActionSkillCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCosts() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList
        restMInheritActionSkillCostMockMvc.perform(get("/api/m-inherit-action-skill-costs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mInheritActionSkillCost.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)));
    }
    
    @Test
    @Transactional
    public void getMInheritActionSkillCost() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get the mInheritActionSkillCost
        restMInheritActionSkillCostMockMvc.perform(get("/api/m-inherit-action-skill-costs/{id}", mInheritActionSkillCost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mInheritActionSkillCost.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST));
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where rarity equals to DEFAULT_RARITY
        defaultMInheritActionSkillCostShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mInheritActionSkillCostList where rarity equals to UPDATED_RARITY
        defaultMInheritActionSkillCostShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMInheritActionSkillCostShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mInheritActionSkillCostList where rarity equals to UPDATED_RARITY
        defaultMInheritActionSkillCostShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where rarity is not null
        defaultMInheritActionSkillCostShouldBeFound("rarity.specified=true");

        // Get all the mInheritActionSkillCostList where rarity is null
        defaultMInheritActionSkillCostShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where rarity greater than or equals to DEFAULT_RARITY
        defaultMInheritActionSkillCostShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mInheritActionSkillCostList where rarity greater than or equals to UPDATED_RARITY
        defaultMInheritActionSkillCostShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where rarity less than or equals to DEFAULT_RARITY
        defaultMInheritActionSkillCostShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mInheritActionSkillCostList where rarity less than or equals to UPDATED_RARITY
        defaultMInheritActionSkillCostShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where level equals to DEFAULT_LEVEL
        defaultMInheritActionSkillCostShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mInheritActionSkillCostList where level equals to UPDATED_LEVEL
        defaultMInheritActionSkillCostShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMInheritActionSkillCostShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mInheritActionSkillCostList where level equals to UPDATED_LEVEL
        defaultMInheritActionSkillCostShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where level is not null
        defaultMInheritActionSkillCostShouldBeFound("level.specified=true");

        // Get all the mInheritActionSkillCostList where level is null
        defaultMInheritActionSkillCostShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where level greater than or equals to DEFAULT_LEVEL
        defaultMInheritActionSkillCostShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mInheritActionSkillCostList where level greater than or equals to UPDATED_LEVEL
        defaultMInheritActionSkillCostShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where level less than or equals to DEFAULT_LEVEL
        defaultMInheritActionSkillCostShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mInheritActionSkillCostList where level less than or equals to UPDATED_LEVEL
        defaultMInheritActionSkillCostShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByCostIsEqualToSomething() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where cost equals to DEFAULT_COST
        defaultMInheritActionSkillCostShouldBeFound("cost.equals=" + DEFAULT_COST);

        // Get all the mInheritActionSkillCostList where cost equals to UPDATED_COST
        defaultMInheritActionSkillCostShouldNotBeFound("cost.equals=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByCostIsInShouldWork() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where cost in DEFAULT_COST or UPDATED_COST
        defaultMInheritActionSkillCostShouldBeFound("cost.in=" + DEFAULT_COST + "," + UPDATED_COST);

        // Get all the mInheritActionSkillCostList where cost equals to UPDATED_COST
        defaultMInheritActionSkillCostShouldNotBeFound("cost.in=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where cost is not null
        defaultMInheritActionSkillCostShouldBeFound("cost.specified=true");

        // Get all the mInheritActionSkillCostList where cost is null
        defaultMInheritActionSkillCostShouldNotBeFound("cost.specified=false");
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByCostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where cost greater than or equals to DEFAULT_COST
        defaultMInheritActionSkillCostShouldBeFound("cost.greaterOrEqualThan=" + DEFAULT_COST);

        // Get all the mInheritActionSkillCostList where cost greater than or equals to UPDATED_COST
        defaultMInheritActionSkillCostShouldNotBeFound("cost.greaterOrEqualThan=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMInheritActionSkillCostsByCostIsLessThanSomething() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        // Get all the mInheritActionSkillCostList where cost less than or equals to DEFAULT_COST
        defaultMInheritActionSkillCostShouldNotBeFound("cost.lessThan=" + DEFAULT_COST);

        // Get all the mInheritActionSkillCostList where cost less than or equals to UPDATED_COST
        defaultMInheritActionSkillCostShouldBeFound("cost.lessThan=" + UPDATED_COST);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMInheritActionSkillCostShouldBeFound(String filter) throws Exception {
        restMInheritActionSkillCostMockMvc.perform(get("/api/m-inherit-action-skill-costs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mInheritActionSkillCost.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)));

        // Check, that the count call also returns 1
        restMInheritActionSkillCostMockMvc.perform(get("/api/m-inherit-action-skill-costs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMInheritActionSkillCostShouldNotBeFound(String filter) throws Exception {
        restMInheritActionSkillCostMockMvc.perform(get("/api/m-inherit-action-skill-costs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMInheritActionSkillCostMockMvc.perform(get("/api/m-inherit-action-skill-costs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMInheritActionSkillCost() throws Exception {
        // Get the mInheritActionSkillCost
        restMInheritActionSkillCostMockMvc.perform(get("/api/m-inherit-action-skill-costs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMInheritActionSkillCost() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        int databaseSizeBeforeUpdate = mInheritActionSkillCostRepository.findAll().size();

        // Update the mInheritActionSkillCost
        MInheritActionSkillCost updatedMInheritActionSkillCost = mInheritActionSkillCostRepository.findById(mInheritActionSkillCost.getId()).get();
        // Disconnect from session so that the updates on updatedMInheritActionSkillCost are not directly saved in db
        em.detach(updatedMInheritActionSkillCost);
        updatedMInheritActionSkillCost
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .cost(UPDATED_COST);
        MInheritActionSkillCostDTO mInheritActionSkillCostDTO = mInheritActionSkillCostMapper.toDto(updatedMInheritActionSkillCost);

        restMInheritActionSkillCostMockMvc.perform(put("/api/m-inherit-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInheritActionSkillCostDTO)))
            .andExpect(status().isOk());

        // Validate the MInheritActionSkillCost in the database
        List<MInheritActionSkillCost> mInheritActionSkillCostList = mInheritActionSkillCostRepository.findAll();
        assertThat(mInheritActionSkillCostList).hasSize(databaseSizeBeforeUpdate);
        MInheritActionSkillCost testMInheritActionSkillCost = mInheritActionSkillCostList.get(mInheritActionSkillCostList.size() - 1);
        assertThat(testMInheritActionSkillCost.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMInheritActionSkillCost.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMInheritActionSkillCost.getCost()).isEqualTo(UPDATED_COST);
    }

    @Test
    @Transactional
    public void updateNonExistingMInheritActionSkillCost() throws Exception {
        int databaseSizeBeforeUpdate = mInheritActionSkillCostRepository.findAll().size();

        // Create the MInheritActionSkillCost
        MInheritActionSkillCostDTO mInheritActionSkillCostDTO = mInheritActionSkillCostMapper.toDto(mInheritActionSkillCost);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMInheritActionSkillCostMockMvc.perform(put("/api/m-inherit-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mInheritActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MInheritActionSkillCost in the database
        List<MInheritActionSkillCost> mInheritActionSkillCostList = mInheritActionSkillCostRepository.findAll();
        assertThat(mInheritActionSkillCostList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMInheritActionSkillCost() throws Exception {
        // Initialize the database
        mInheritActionSkillCostRepository.saveAndFlush(mInheritActionSkillCost);

        int databaseSizeBeforeDelete = mInheritActionSkillCostRepository.findAll().size();

        // Delete the mInheritActionSkillCost
        restMInheritActionSkillCostMockMvc.perform(delete("/api/m-inherit-action-skill-costs/{id}", mInheritActionSkillCost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MInheritActionSkillCost> mInheritActionSkillCostList = mInheritActionSkillCostRepository.findAll();
        assertThat(mInheritActionSkillCostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MInheritActionSkillCost.class);
        MInheritActionSkillCost mInheritActionSkillCost1 = new MInheritActionSkillCost();
        mInheritActionSkillCost1.setId(1L);
        MInheritActionSkillCost mInheritActionSkillCost2 = new MInheritActionSkillCost();
        mInheritActionSkillCost2.setId(mInheritActionSkillCost1.getId());
        assertThat(mInheritActionSkillCost1).isEqualTo(mInheritActionSkillCost2);
        mInheritActionSkillCost2.setId(2L);
        assertThat(mInheritActionSkillCost1).isNotEqualTo(mInheritActionSkillCost2);
        mInheritActionSkillCost1.setId(null);
        assertThat(mInheritActionSkillCost1).isNotEqualTo(mInheritActionSkillCost2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MInheritActionSkillCostDTO.class);
        MInheritActionSkillCostDTO mInheritActionSkillCostDTO1 = new MInheritActionSkillCostDTO();
        mInheritActionSkillCostDTO1.setId(1L);
        MInheritActionSkillCostDTO mInheritActionSkillCostDTO2 = new MInheritActionSkillCostDTO();
        assertThat(mInheritActionSkillCostDTO1).isNotEqualTo(mInheritActionSkillCostDTO2);
        mInheritActionSkillCostDTO2.setId(mInheritActionSkillCostDTO1.getId());
        assertThat(mInheritActionSkillCostDTO1).isEqualTo(mInheritActionSkillCostDTO2);
        mInheritActionSkillCostDTO2.setId(2L);
        assertThat(mInheritActionSkillCostDTO1).isNotEqualTo(mInheritActionSkillCostDTO2);
        mInheritActionSkillCostDTO1.setId(null);
        assertThat(mInheritActionSkillCostDTO1).isNotEqualTo(mInheritActionSkillCostDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mInheritActionSkillCostMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mInheritActionSkillCostMapper.fromId(null)).isNull();
    }
}
