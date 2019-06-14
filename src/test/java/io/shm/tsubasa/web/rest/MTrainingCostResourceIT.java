package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTrainingCost;
import io.shm.tsubasa.repository.MTrainingCostRepository;
import io.shm.tsubasa.service.MTrainingCostService;
import io.shm.tsubasa.service.dto.MTrainingCostDTO;
import io.shm.tsubasa.service.mapper.MTrainingCostMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTrainingCostCriteria;
import io.shm.tsubasa.service.MTrainingCostQueryService;

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
 * Integration tests for the {@Link MTrainingCostResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTrainingCostResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_COST = 1;
    private static final Integer UPDATED_COST = 2;

    @Autowired
    private MTrainingCostRepository mTrainingCostRepository;

    @Autowired
    private MTrainingCostMapper mTrainingCostMapper;

    @Autowired
    private MTrainingCostService mTrainingCostService;

    @Autowired
    private MTrainingCostQueryService mTrainingCostQueryService;

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

    private MockMvc restMTrainingCostMockMvc;

    private MTrainingCost mTrainingCost;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTrainingCostResource mTrainingCostResource = new MTrainingCostResource(mTrainingCostService, mTrainingCostQueryService);
        this.restMTrainingCostMockMvc = MockMvcBuilders.standaloneSetup(mTrainingCostResource)
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
    public static MTrainingCost createEntity(EntityManager em) {
        MTrainingCost mTrainingCost = new MTrainingCost()
            .rarity(DEFAULT_RARITY)
            .level(DEFAULT_LEVEL)
            .cost(DEFAULT_COST);
        return mTrainingCost;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTrainingCost createUpdatedEntity(EntityManager em) {
        MTrainingCost mTrainingCost = new MTrainingCost()
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .cost(UPDATED_COST);
        return mTrainingCost;
    }

    @BeforeEach
    public void initTest() {
        mTrainingCost = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTrainingCost() throws Exception {
        int databaseSizeBeforeCreate = mTrainingCostRepository.findAll().size();

        // Create the MTrainingCost
        MTrainingCostDTO mTrainingCostDTO = mTrainingCostMapper.toDto(mTrainingCost);
        restMTrainingCostMockMvc.perform(post("/api/m-training-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCostDTO)))
            .andExpect(status().isCreated());

        // Validate the MTrainingCost in the database
        List<MTrainingCost> mTrainingCostList = mTrainingCostRepository.findAll();
        assertThat(mTrainingCostList).hasSize(databaseSizeBeforeCreate + 1);
        MTrainingCost testMTrainingCost = mTrainingCostList.get(mTrainingCostList.size() - 1);
        assertThat(testMTrainingCost.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMTrainingCost.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMTrainingCost.getCost()).isEqualTo(DEFAULT_COST);
    }

    @Test
    @Transactional
    public void createMTrainingCostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTrainingCostRepository.findAll().size();

        // Create the MTrainingCost with an existing ID
        mTrainingCost.setId(1L);
        MTrainingCostDTO mTrainingCostDTO = mTrainingCostMapper.toDto(mTrainingCost);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTrainingCostMockMvc.perform(post("/api/m-training-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTrainingCost in the database
        List<MTrainingCost> mTrainingCostList = mTrainingCostRepository.findAll();
        assertThat(mTrainingCostList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCostRepository.findAll().size();
        // set the field null
        mTrainingCost.setRarity(null);

        // Create the MTrainingCost, which fails.
        MTrainingCostDTO mTrainingCostDTO = mTrainingCostMapper.toDto(mTrainingCost);

        restMTrainingCostMockMvc.perform(post("/api/m-training-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCostDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCost> mTrainingCostList = mTrainingCostRepository.findAll();
        assertThat(mTrainingCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCostRepository.findAll().size();
        // set the field null
        mTrainingCost.setLevel(null);

        // Create the MTrainingCost, which fails.
        MTrainingCostDTO mTrainingCostDTO = mTrainingCostMapper.toDto(mTrainingCost);

        restMTrainingCostMockMvc.perform(post("/api/m-training-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCostDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCost> mTrainingCostList = mTrainingCostRepository.findAll();
        assertThat(mTrainingCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTrainingCostRepository.findAll().size();
        // set the field null
        mTrainingCost.setCost(null);

        // Create the MTrainingCost, which fails.
        MTrainingCostDTO mTrainingCostDTO = mTrainingCostMapper.toDto(mTrainingCost);

        restMTrainingCostMockMvc.perform(post("/api/m-training-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCostDTO)))
            .andExpect(status().isBadRequest());

        List<MTrainingCost> mTrainingCostList = mTrainingCostRepository.findAll();
        assertThat(mTrainingCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTrainingCosts() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList
        restMTrainingCostMockMvc.perform(get("/api/m-training-costs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTrainingCost.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)));
    }
    
    @Test
    @Transactional
    public void getMTrainingCost() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get the mTrainingCost
        restMTrainingCostMockMvc.perform(get("/api/m-training-costs/{id}", mTrainingCost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTrainingCost.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST));
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where rarity equals to DEFAULT_RARITY
        defaultMTrainingCostShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mTrainingCostList where rarity equals to UPDATED_RARITY
        defaultMTrainingCostShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMTrainingCostShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mTrainingCostList where rarity equals to UPDATED_RARITY
        defaultMTrainingCostShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where rarity is not null
        defaultMTrainingCostShouldBeFound("rarity.specified=true");

        // Get all the mTrainingCostList where rarity is null
        defaultMTrainingCostShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where rarity greater than or equals to DEFAULT_RARITY
        defaultMTrainingCostShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mTrainingCostList where rarity greater than or equals to UPDATED_RARITY
        defaultMTrainingCostShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where rarity less than or equals to DEFAULT_RARITY
        defaultMTrainingCostShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mTrainingCostList where rarity less than or equals to UPDATED_RARITY
        defaultMTrainingCostShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMTrainingCostsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where level equals to DEFAULT_LEVEL
        defaultMTrainingCostShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mTrainingCostList where level equals to UPDATED_LEVEL
        defaultMTrainingCostShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMTrainingCostShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mTrainingCostList where level equals to UPDATED_LEVEL
        defaultMTrainingCostShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where level is not null
        defaultMTrainingCostShouldBeFound("level.specified=true");

        // Get all the mTrainingCostList where level is null
        defaultMTrainingCostShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where level greater than or equals to DEFAULT_LEVEL
        defaultMTrainingCostShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mTrainingCostList where level greater than or equals to UPDATED_LEVEL
        defaultMTrainingCostShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where level less than or equals to DEFAULT_LEVEL
        defaultMTrainingCostShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mTrainingCostList where level less than or equals to UPDATED_LEVEL
        defaultMTrainingCostShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMTrainingCostsByCostIsEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where cost equals to DEFAULT_COST
        defaultMTrainingCostShouldBeFound("cost.equals=" + DEFAULT_COST);

        // Get all the mTrainingCostList where cost equals to UPDATED_COST
        defaultMTrainingCostShouldNotBeFound("cost.equals=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByCostIsInShouldWork() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where cost in DEFAULT_COST or UPDATED_COST
        defaultMTrainingCostShouldBeFound("cost.in=" + DEFAULT_COST + "," + UPDATED_COST);

        // Get all the mTrainingCostList where cost equals to UPDATED_COST
        defaultMTrainingCostShouldNotBeFound("cost.in=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where cost is not null
        defaultMTrainingCostShouldBeFound("cost.specified=true");

        // Get all the mTrainingCostList where cost is null
        defaultMTrainingCostShouldNotBeFound("cost.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByCostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where cost greater than or equals to DEFAULT_COST
        defaultMTrainingCostShouldBeFound("cost.greaterOrEqualThan=" + DEFAULT_COST);

        // Get all the mTrainingCostList where cost greater than or equals to UPDATED_COST
        defaultMTrainingCostShouldNotBeFound("cost.greaterOrEqualThan=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMTrainingCostsByCostIsLessThanSomething() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        // Get all the mTrainingCostList where cost less than or equals to DEFAULT_COST
        defaultMTrainingCostShouldNotBeFound("cost.lessThan=" + DEFAULT_COST);

        // Get all the mTrainingCostList where cost less than or equals to UPDATED_COST
        defaultMTrainingCostShouldBeFound("cost.lessThan=" + UPDATED_COST);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTrainingCostShouldBeFound(String filter) throws Exception {
        restMTrainingCostMockMvc.perform(get("/api/m-training-costs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTrainingCost.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)));

        // Check, that the count call also returns 1
        restMTrainingCostMockMvc.perform(get("/api/m-training-costs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTrainingCostShouldNotBeFound(String filter) throws Exception {
        restMTrainingCostMockMvc.perform(get("/api/m-training-costs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTrainingCostMockMvc.perform(get("/api/m-training-costs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTrainingCost() throws Exception {
        // Get the mTrainingCost
        restMTrainingCostMockMvc.perform(get("/api/m-training-costs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTrainingCost() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        int databaseSizeBeforeUpdate = mTrainingCostRepository.findAll().size();

        // Update the mTrainingCost
        MTrainingCost updatedMTrainingCost = mTrainingCostRepository.findById(mTrainingCost.getId()).get();
        // Disconnect from session so that the updates on updatedMTrainingCost are not directly saved in db
        em.detach(updatedMTrainingCost);
        updatedMTrainingCost
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .cost(UPDATED_COST);
        MTrainingCostDTO mTrainingCostDTO = mTrainingCostMapper.toDto(updatedMTrainingCost);

        restMTrainingCostMockMvc.perform(put("/api/m-training-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCostDTO)))
            .andExpect(status().isOk());

        // Validate the MTrainingCost in the database
        List<MTrainingCost> mTrainingCostList = mTrainingCostRepository.findAll();
        assertThat(mTrainingCostList).hasSize(databaseSizeBeforeUpdate);
        MTrainingCost testMTrainingCost = mTrainingCostList.get(mTrainingCostList.size() - 1);
        assertThat(testMTrainingCost.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMTrainingCost.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMTrainingCost.getCost()).isEqualTo(UPDATED_COST);
    }

    @Test
    @Transactional
    public void updateNonExistingMTrainingCost() throws Exception {
        int databaseSizeBeforeUpdate = mTrainingCostRepository.findAll().size();

        // Create the MTrainingCost
        MTrainingCostDTO mTrainingCostDTO = mTrainingCostMapper.toDto(mTrainingCost);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTrainingCostMockMvc.perform(put("/api/m-training-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTrainingCostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTrainingCost in the database
        List<MTrainingCost> mTrainingCostList = mTrainingCostRepository.findAll();
        assertThat(mTrainingCostList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTrainingCost() throws Exception {
        // Initialize the database
        mTrainingCostRepository.saveAndFlush(mTrainingCost);

        int databaseSizeBeforeDelete = mTrainingCostRepository.findAll().size();

        // Delete the mTrainingCost
        restMTrainingCostMockMvc.perform(delete("/api/m-training-costs/{id}", mTrainingCost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTrainingCost> mTrainingCostList = mTrainingCostRepository.findAll();
        assertThat(mTrainingCostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTrainingCost.class);
        MTrainingCost mTrainingCost1 = new MTrainingCost();
        mTrainingCost1.setId(1L);
        MTrainingCost mTrainingCost2 = new MTrainingCost();
        mTrainingCost2.setId(mTrainingCost1.getId());
        assertThat(mTrainingCost1).isEqualTo(mTrainingCost2);
        mTrainingCost2.setId(2L);
        assertThat(mTrainingCost1).isNotEqualTo(mTrainingCost2);
        mTrainingCost1.setId(null);
        assertThat(mTrainingCost1).isNotEqualTo(mTrainingCost2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTrainingCostDTO.class);
        MTrainingCostDTO mTrainingCostDTO1 = new MTrainingCostDTO();
        mTrainingCostDTO1.setId(1L);
        MTrainingCostDTO mTrainingCostDTO2 = new MTrainingCostDTO();
        assertThat(mTrainingCostDTO1).isNotEqualTo(mTrainingCostDTO2);
        mTrainingCostDTO2.setId(mTrainingCostDTO1.getId());
        assertThat(mTrainingCostDTO1).isEqualTo(mTrainingCostDTO2);
        mTrainingCostDTO2.setId(2L);
        assertThat(mTrainingCostDTO1).isNotEqualTo(mTrainingCostDTO2);
        mTrainingCostDTO1.setId(null);
        assertThat(mTrainingCostDTO1).isNotEqualTo(mTrainingCostDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTrainingCostMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTrainingCostMapper.fromId(null)).isNull();
    }
}
