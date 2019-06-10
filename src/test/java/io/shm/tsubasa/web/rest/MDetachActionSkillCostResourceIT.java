package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDetachActionSkillCost;
import io.shm.tsubasa.repository.MDetachActionSkillCostRepository;
import io.shm.tsubasa.service.MDetachActionSkillCostService;
import io.shm.tsubasa.service.dto.MDetachActionSkillCostDTO;
import io.shm.tsubasa.service.mapper.MDetachActionSkillCostMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDetachActionSkillCostCriteria;
import io.shm.tsubasa.service.MDetachActionSkillCostQueryService;

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
 * Integration tests for the {@Link MDetachActionSkillCostResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDetachActionSkillCostResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_COST = 1;
    private static final Integer UPDATED_COST = 2;

    @Autowired
    private MDetachActionSkillCostRepository mDetachActionSkillCostRepository;

    @Autowired
    private MDetachActionSkillCostMapper mDetachActionSkillCostMapper;

    @Autowired
    private MDetachActionSkillCostService mDetachActionSkillCostService;

    @Autowired
    private MDetachActionSkillCostQueryService mDetachActionSkillCostQueryService;

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

    private MockMvc restMDetachActionSkillCostMockMvc;

    private MDetachActionSkillCost mDetachActionSkillCost;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDetachActionSkillCostResource mDetachActionSkillCostResource = new MDetachActionSkillCostResource(mDetachActionSkillCostService, mDetachActionSkillCostQueryService);
        this.restMDetachActionSkillCostMockMvc = MockMvcBuilders.standaloneSetup(mDetachActionSkillCostResource)
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
    public static MDetachActionSkillCost createEntity(EntityManager em) {
        MDetachActionSkillCost mDetachActionSkillCost = new MDetachActionSkillCost()
            .rarity(DEFAULT_RARITY)
            .cost(DEFAULT_COST);
        return mDetachActionSkillCost;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDetachActionSkillCost createUpdatedEntity(EntityManager em) {
        MDetachActionSkillCost mDetachActionSkillCost = new MDetachActionSkillCost()
            .rarity(UPDATED_RARITY)
            .cost(UPDATED_COST);
        return mDetachActionSkillCost;
    }

    @BeforeEach
    public void initTest() {
        mDetachActionSkillCost = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDetachActionSkillCost() throws Exception {
        int databaseSizeBeforeCreate = mDetachActionSkillCostRepository.findAll().size();

        // Create the MDetachActionSkillCost
        MDetachActionSkillCostDTO mDetachActionSkillCostDTO = mDetachActionSkillCostMapper.toDto(mDetachActionSkillCost);
        restMDetachActionSkillCostMockMvc.perform(post("/api/m-detach-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDetachActionSkillCostDTO)))
            .andExpect(status().isCreated());

        // Validate the MDetachActionSkillCost in the database
        List<MDetachActionSkillCost> mDetachActionSkillCostList = mDetachActionSkillCostRepository.findAll();
        assertThat(mDetachActionSkillCostList).hasSize(databaseSizeBeforeCreate + 1);
        MDetachActionSkillCost testMDetachActionSkillCost = mDetachActionSkillCostList.get(mDetachActionSkillCostList.size() - 1);
        assertThat(testMDetachActionSkillCost.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMDetachActionSkillCost.getCost()).isEqualTo(DEFAULT_COST);
    }

    @Test
    @Transactional
    public void createMDetachActionSkillCostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDetachActionSkillCostRepository.findAll().size();

        // Create the MDetachActionSkillCost with an existing ID
        mDetachActionSkillCost.setId(1L);
        MDetachActionSkillCostDTO mDetachActionSkillCostDTO = mDetachActionSkillCostMapper.toDto(mDetachActionSkillCost);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDetachActionSkillCostMockMvc.perform(post("/api/m-detach-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDetachActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDetachActionSkillCost in the database
        List<MDetachActionSkillCost> mDetachActionSkillCostList = mDetachActionSkillCostRepository.findAll();
        assertThat(mDetachActionSkillCostList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDetachActionSkillCostRepository.findAll().size();
        // set the field null
        mDetachActionSkillCost.setRarity(null);

        // Create the MDetachActionSkillCost, which fails.
        MDetachActionSkillCostDTO mDetachActionSkillCostDTO = mDetachActionSkillCostMapper.toDto(mDetachActionSkillCost);

        restMDetachActionSkillCostMockMvc.perform(post("/api/m-detach-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDetachActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        List<MDetachActionSkillCost> mDetachActionSkillCostList = mDetachActionSkillCostRepository.findAll();
        assertThat(mDetachActionSkillCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDetachActionSkillCostRepository.findAll().size();
        // set the field null
        mDetachActionSkillCost.setCost(null);

        // Create the MDetachActionSkillCost, which fails.
        MDetachActionSkillCostDTO mDetachActionSkillCostDTO = mDetachActionSkillCostMapper.toDto(mDetachActionSkillCost);

        restMDetachActionSkillCostMockMvc.perform(post("/api/m-detach-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDetachActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        List<MDetachActionSkillCost> mDetachActionSkillCostList = mDetachActionSkillCostRepository.findAll();
        assertThat(mDetachActionSkillCostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCosts() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList
        restMDetachActionSkillCostMockMvc.perform(get("/api/m-detach-action-skill-costs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDetachActionSkillCost.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)));
    }
    
    @Test
    @Transactional
    public void getMDetachActionSkillCost() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get the mDetachActionSkillCost
        restMDetachActionSkillCostMockMvc.perform(get("/api/m-detach-action-skill-costs/{id}", mDetachActionSkillCost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDetachActionSkillCost.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST));
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where rarity equals to DEFAULT_RARITY
        defaultMDetachActionSkillCostShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mDetachActionSkillCostList where rarity equals to UPDATED_RARITY
        defaultMDetachActionSkillCostShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMDetachActionSkillCostShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mDetachActionSkillCostList where rarity equals to UPDATED_RARITY
        defaultMDetachActionSkillCostShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where rarity is not null
        defaultMDetachActionSkillCostShouldBeFound("rarity.specified=true");

        // Get all the mDetachActionSkillCostList where rarity is null
        defaultMDetachActionSkillCostShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where rarity greater than or equals to DEFAULT_RARITY
        defaultMDetachActionSkillCostShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mDetachActionSkillCostList where rarity greater than or equals to UPDATED_RARITY
        defaultMDetachActionSkillCostShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where rarity less than or equals to DEFAULT_RARITY
        defaultMDetachActionSkillCostShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mDetachActionSkillCostList where rarity less than or equals to UPDATED_RARITY
        defaultMDetachActionSkillCostShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByCostIsEqualToSomething() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where cost equals to DEFAULT_COST
        defaultMDetachActionSkillCostShouldBeFound("cost.equals=" + DEFAULT_COST);

        // Get all the mDetachActionSkillCostList where cost equals to UPDATED_COST
        defaultMDetachActionSkillCostShouldNotBeFound("cost.equals=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByCostIsInShouldWork() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where cost in DEFAULT_COST or UPDATED_COST
        defaultMDetachActionSkillCostShouldBeFound("cost.in=" + DEFAULT_COST + "," + UPDATED_COST);

        // Get all the mDetachActionSkillCostList where cost equals to UPDATED_COST
        defaultMDetachActionSkillCostShouldNotBeFound("cost.in=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where cost is not null
        defaultMDetachActionSkillCostShouldBeFound("cost.specified=true");

        // Get all the mDetachActionSkillCostList where cost is null
        defaultMDetachActionSkillCostShouldNotBeFound("cost.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByCostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where cost greater than or equals to DEFAULT_COST
        defaultMDetachActionSkillCostShouldBeFound("cost.greaterOrEqualThan=" + DEFAULT_COST);

        // Get all the mDetachActionSkillCostList where cost greater than or equals to UPDATED_COST
        defaultMDetachActionSkillCostShouldNotBeFound("cost.greaterOrEqualThan=" + UPDATED_COST);
    }

    @Test
    @Transactional
    public void getAllMDetachActionSkillCostsByCostIsLessThanSomething() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        // Get all the mDetachActionSkillCostList where cost less than or equals to DEFAULT_COST
        defaultMDetachActionSkillCostShouldNotBeFound("cost.lessThan=" + DEFAULT_COST);

        // Get all the mDetachActionSkillCostList where cost less than or equals to UPDATED_COST
        defaultMDetachActionSkillCostShouldBeFound("cost.lessThan=" + UPDATED_COST);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDetachActionSkillCostShouldBeFound(String filter) throws Exception {
        restMDetachActionSkillCostMockMvc.perform(get("/api/m-detach-action-skill-costs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDetachActionSkillCost.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)));

        // Check, that the count call also returns 1
        restMDetachActionSkillCostMockMvc.perform(get("/api/m-detach-action-skill-costs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDetachActionSkillCostShouldNotBeFound(String filter) throws Exception {
        restMDetachActionSkillCostMockMvc.perform(get("/api/m-detach-action-skill-costs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDetachActionSkillCostMockMvc.perform(get("/api/m-detach-action-skill-costs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDetachActionSkillCost() throws Exception {
        // Get the mDetachActionSkillCost
        restMDetachActionSkillCostMockMvc.perform(get("/api/m-detach-action-skill-costs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDetachActionSkillCost() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        int databaseSizeBeforeUpdate = mDetachActionSkillCostRepository.findAll().size();

        // Update the mDetachActionSkillCost
        MDetachActionSkillCost updatedMDetachActionSkillCost = mDetachActionSkillCostRepository.findById(mDetachActionSkillCost.getId()).get();
        // Disconnect from session so that the updates on updatedMDetachActionSkillCost are not directly saved in db
        em.detach(updatedMDetachActionSkillCost);
        updatedMDetachActionSkillCost
            .rarity(UPDATED_RARITY)
            .cost(UPDATED_COST);
        MDetachActionSkillCostDTO mDetachActionSkillCostDTO = mDetachActionSkillCostMapper.toDto(updatedMDetachActionSkillCost);

        restMDetachActionSkillCostMockMvc.perform(put("/api/m-detach-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDetachActionSkillCostDTO)))
            .andExpect(status().isOk());

        // Validate the MDetachActionSkillCost in the database
        List<MDetachActionSkillCost> mDetachActionSkillCostList = mDetachActionSkillCostRepository.findAll();
        assertThat(mDetachActionSkillCostList).hasSize(databaseSizeBeforeUpdate);
        MDetachActionSkillCost testMDetachActionSkillCost = mDetachActionSkillCostList.get(mDetachActionSkillCostList.size() - 1);
        assertThat(testMDetachActionSkillCost.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMDetachActionSkillCost.getCost()).isEqualTo(UPDATED_COST);
    }

    @Test
    @Transactional
    public void updateNonExistingMDetachActionSkillCost() throws Exception {
        int databaseSizeBeforeUpdate = mDetachActionSkillCostRepository.findAll().size();

        // Create the MDetachActionSkillCost
        MDetachActionSkillCostDTO mDetachActionSkillCostDTO = mDetachActionSkillCostMapper.toDto(mDetachActionSkillCost);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDetachActionSkillCostMockMvc.perform(put("/api/m-detach-action-skill-costs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDetachActionSkillCostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDetachActionSkillCost in the database
        List<MDetachActionSkillCost> mDetachActionSkillCostList = mDetachActionSkillCostRepository.findAll();
        assertThat(mDetachActionSkillCostList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDetachActionSkillCost() throws Exception {
        // Initialize the database
        mDetachActionSkillCostRepository.saveAndFlush(mDetachActionSkillCost);

        int databaseSizeBeforeDelete = mDetachActionSkillCostRepository.findAll().size();

        // Delete the mDetachActionSkillCost
        restMDetachActionSkillCostMockMvc.perform(delete("/api/m-detach-action-skill-costs/{id}", mDetachActionSkillCost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDetachActionSkillCost> mDetachActionSkillCostList = mDetachActionSkillCostRepository.findAll();
        assertThat(mDetachActionSkillCostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDetachActionSkillCost.class);
        MDetachActionSkillCost mDetachActionSkillCost1 = new MDetachActionSkillCost();
        mDetachActionSkillCost1.setId(1L);
        MDetachActionSkillCost mDetachActionSkillCost2 = new MDetachActionSkillCost();
        mDetachActionSkillCost2.setId(mDetachActionSkillCost1.getId());
        assertThat(mDetachActionSkillCost1).isEqualTo(mDetachActionSkillCost2);
        mDetachActionSkillCost2.setId(2L);
        assertThat(mDetachActionSkillCost1).isNotEqualTo(mDetachActionSkillCost2);
        mDetachActionSkillCost1.setId(null);
        assertThat(mDetachActionSkillCost1).isNotEqualTo(mDetachActionSkillCost2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDetachActionSkillCostDTO.class);
        MDetachActionSkillCostDTO mDetachActionSkillCostDTO1 = new MDetachActionSkillCostDTO();
        mDetachActionSkillCostDTO1.setId(1L);
        MDetachActionSkillCostDTO mDetachActionSkillCostDTO2 = new MDetachActionSkillCostDTO();
        assertThat(mDetachActionSkillCostDTO1).isNotEqualTo(mDetachActionSkillCostDTO2);
        mDetachActionSkillCostDTO2.setId(mDetachActionSkillCostDTO1.getId());
        assertThat(mDetachActionSkillCostDTO1).isEqualTo(mDetachActionSkillCostDTO2);
        mDetachActionSkillCostDTO2.setId(2L);
        assertThat(mDetachActionSkillCostDTO1).isNotEqualTo(mDetachActionSkillCostDTO2);
        mDetachActionSkillCostDTO1.setId(null);
        assertThat(mDetachActionSkillCostDTO1).isNotEqualTo(mDetachActionSkillCostDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDetachActionSkillCostMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDetachActionSkillCostMapper.fromId(null)).isNull();
    }
}
