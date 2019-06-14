package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTeamEffectRarity;
import io.shm.tsubasa.repository.MTeamEffectRarityRepository;
import io.shm.tsubasa.service.MTeamEffectRarityService;
import io.shm.tsubasa.service.dto.MTeamEffectRarityDTO;
import io.shm.tsubasa.service.mapper.MTeamEffectRarityMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTeamEffectRarityCriteria;
import io.shm.tsubasa.service.MTeamEffectRarityQueryService;

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
 * Integration tests for the {@Link MTeamEffectRarityResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTeamEffectRarityResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_LEVEL = 1;
    private static final Integer UPDATED_MAX_LEVEL = 2;

    @Autowired
    private MTeamEffectRarityRepository mTeamEffectRarityRepository;

    @Autowired
    private MTeamEffectRarityMapper mTeamEffectRarityMapper;

    @Autowired
    private MTeamEffectRarityService mTeamEffectRarityService;

    @Autowired
    private MTeamEffectRarityQueryService mTeamEffectRarityQueryService;

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

    private MockMvc restMTeamEffectRarityMockMvc;

    private MTeamEffectRarity mTeamEffectRarity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTeamEffectRarityResource mTeamEffectRarityResource = new MTeamEffectRarityResource(mTeamEffectRarityService, mTeamEffectRarityQueryService);
        this.restMTeamEffectRarityMockMvc = MockMvcBuilders.standaloneSetup(mTeamEffectRarityResource)
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
    public static MTeamEffectRarity createEntity(EntityManager em) {
        MTeamEffectRarity mTeamEffectRarity = new MTeamEffectRarity()
            .rarity(DEFAULT_RARITY)
            .name(DEFAULT_NAME)
            .maxLevel(DEFAULT_MAX_LEVEL);
        return mTeamEffectRarity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTeamEffectRarity createUpdatedEntity(EntityManager em) {
        MTeamEffectRarity mTeamEffectRarity = new MTeamEffectRarity()
            .rarity(UPDATED_RARITY)
            .name(UPDATED_NAME)
            .maxLevel(UPDATED_MAX_LEVEL);
        return mTeamEffectRarity;
    }

    @BeforeEach
    public void initTest() {
        mTeamEffectRarity = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTeamEffectRarity() throws Exception {
        int databaseSizeBeforeCreate = mTeamEffectRarityRepository.findAll().size();

        // Create the MTeamEffectRarity
        MTeamEffectRarityDTO mTeamEffectRarityDTO = mTeamEffectRarityMapper.toDto(mTeamEffectRarity);
        restMTeamEffectRarityMockMvc.perform(post("/api/m-team-effect-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectRarityDTO)))
            .andExpect(status().isCreated());

        // Validate the MTeamEffectRarity in the database
        List<MTeamEffectRarity> mTeamEffectRarityList = mTeamEffectRarityRepository.findAll();
        assertThat(mTeamEffectRarityList).hasSize(databaseSizeBeforeCreate + 1);
        MTeamEffectRarity testMTeamEffectRarity = mTeamEffectRarityList.get(mTeamEffectRarityList.size() - 1);
        assertThat(testMTeamEffectRarity.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMTeamEffectRarity.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMTeamEffectRarity.getMaxLevel()).isEqualTo(DEFAULT_MAX_LEVEL);
    }

    @Test
    @Transactional
    public void createMTeamEffectRarityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTeamEffectRarityRepository.findAll().size();

        // Create the MTeamEffectRarity with an existing ID
        mTeamEffectRarity.setId(1L);
        MTeamEffectRarityDTO mTeamEffectRarityDTO = mTeamEffectRarityMapper.toDto(mTeamEffectRarity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTeamEffectRarityMockMvc.perform(post("/api/m-team-effect-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectRarityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTeamEffectRarity in the database
        List<MTeamEffectRarity> mTeamEffectRarityList = mTeamEffectRarityRepository.findAll();
        assertThat(mTeamEffectRarityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectRarityRepository.findAll().size();
        // set the field null
        mTeamEffectRarity.setRarity(null);

        // Create the MTeamEffectRarity, which fails.
        MTeamEffectRarityDTO mTeamEffectRarityDTO = mTeamEffectRarityMapper.toDto(mTeamEffectRarity);

        restMTeamEffectRarityMockMvc.perform(post("/api/m-team-effect-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectRarityDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectRarity> mTeamEffectRarityList = mTeamEffectRarityRepository.findAll();
        assertThat(mTeamEffectRarityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectRarityRepository.findAll().size();
        // set the field null
        mTeamEffectRarity.setMaxLevel(null);

        // Create the MTeamEffectRarity, which fails.
        MTeamEffectRarityDTO mTeamEffectRarityDTO = mTeamEffectRarityMapper.toDto(mTeamEffectRarity);

        restMTeamEffectRarityMockMvc.perform(post("/api/m-team-effect-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectRarityDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectRarity> mTeamEffectRarityList = mTeamEffectRarityRepository.findAll();
        assertThat(mTeamEffectRarityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRarities() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList
        restMTeamEffectRarityMockMvc.perform(get("/api/m-team-effect-rarities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTeamEffectRarity.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].maxLevel").value(hasItem(DEFAULT_MAX_LEVEL)));
    }
    
    @Test
    @Transactional
    public void getMTeamEffectRarity() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get the mTeamEffectRarity
        restMTeamEffectRarityMockMvc.perform(get("/api/m-team-effect-rarities/{id}", mTeamEffectRarity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTeamEffectRarity.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.maxLevel").value(DEFAULT_MAX_LEVEL));
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where rarity equals to DEFAULT_RARITY
        defaultMTeamEffectRarityShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mTeamEffectRarityList where rarity equals to UPDATED_RARITY
        defaultMTeamEffectRarityShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMTeamEffectRarityShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mTeamEffectRarityList where rarity equals to UPDATED_RARITY
        defaultMTeamEffectRarityShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where rarity is not null
        defaultMTeamEffectRarityShouldBeFound("rarity.specified=true");

        // Get all the mTeamEffectRarityList where rarity is null
        defaultMTeamEffectRarityShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where rarity greater than or equals to DEFAULT_RARITY
        defaultMTeamEffectRarityShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mTeamEffectRarityList where rarity greater than or equals to UPDATED_RARITY
        defaultMTeamEffectRarityShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where rarity less than or equals to DEFAULT_RARITY
        defaultMTeamEffectRarityShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mTeamEffectRarityList where rarity less than or equals to UPDATED_RARITY
        defaultMTeamEffectRarityShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByMaxLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where maxLevel equals to DEFAULT_MAX_LEVEL
        defaultMTeamEffectRarityShouldBeFound("maxLevel.equals=" + DEFAULT_MAX_LEVEL);

        // Get all the mTeamEffectRarityList where maxLevel equals to UPDATED_MAX_LEVEL
        defaultMTeamEffectRarityShouldNotBeFound("maxLevel.equals=" + UPDATED_MAX_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByMaxLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where maxLevel in DEFAULT_MAX_LEVEL or UPDATED_MAX_LEVEL
        defaultMTeamEffectRarityShouldBeFound("maxLevel.in=" + DEFAULT_MAX_LEVEL + "," + UPDATED_MAX_LEVEL);

        // Get all the mTeamEffectRarityList where maxLevel equals to UPDATED_MAX_LEVEL
        defaultMTeamEffectRarityShouldNotBeFound("maxLevel.in=" + UPDATED_MAX_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByMaxLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where maxLevel is not null
        defaultMTeamEffectRarityShouldBeFound("maxLevel.specified=true");

        // Get all the mTeamEffectRarityList where maxLevel is null
        defaultMTeamEffectRarityShouldNotBeFound("maxLevel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByMaxLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where maxLevel greater than or equals to DEFAULT_MAX_LEVEL
        defaultMTeamEffectRarityShouldBeFound("maxLevel.greaterOrEqualThan=" + DEFAULT_MAX_LEVEL);

        // Get all the mTeamEffectRarityList where maxLevel greater than or equals to UPDATED_MAX_LEVEL
        defaultMTeamEffectRarityShouldNotBeFound("maxLevel.greaterOrEqualThan=" + UPDATED_MAX_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectRaritiesByMaxLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        // Get all the mTeamEffectRarityList where maxLevel less than or equals to DEFAULT_MAX_LEVEL
        defaultMTeamEffectRarityShouldNotBeFound("maxLevel.lessThan=" + DEFAULT_MAX_LEVEL);

        // Get all the mTeamEffectRarityList where maxLevel less than or equals to UPDATED_MAX_LEVEL
        defaultMTeamEffectRarityShouldBeFound("maxLevel.lessThan=" + UPDATED_MAX_LEVEL);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTeamEffectRarityShouldBeFound(String filter) throws Exception {
        restMTeamEffectRarityMockMvc.perform(get("/api/m-team-effect-rarities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTeamEffectRarity.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].maxLevel").value(hasItem(DEFAULT_MAX_LEVEL)));

        // Check, that the count call also returns 1
        restMTeamEffectRarityMockMvc.perform(get("/api/m-team-effect-rarities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTeamEffectRarityShouldNotBeFound(String filter) throws Exception {
        restMTeamEffectRarityMockMvc.perform(get("/api/m-team-effect-rarities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTeamEffectRarityMockMvc.perform(get("/api/m-team-effect-rarities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTeamEffectRarity() throws Exception {
        // Get the mTeamEffectRarity
        restMTeamEffectRarityMockMvc.perform(get("/api/m-team-effect-rarities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTeamEffectRarity() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        int databaseSizeBeforeUpdate = mTeamEffectRarityRepository.findAll().size();

        // Update the mTeamEffectRarity
        MTeamEffectRarity updatedMTeamEffectRarity = mTeamEffectRarityRepository.findById(mTeamEffectRarity.getId()).get();
        // Disconnect from session so that the updates on updatedMTeamEffectRarity are not directly saved in db
        em.detach(updatedMTeamEffectRarity);
        updatedMTeamEffectRarity
            .rarity(UPDATED_RARITY)
            .name(UPDATED_NAME)
            .maxLevel(UPDATED_MAX_LEVEL);
        MTeamEffectRarityDTO mTeamEffectRarityDTO = mTeamEffectRarityMapper.toDto(updatedMTeamEffectRarity);

        restMTeamEffectRarityMockMvc.perform(put("/api/m-team-effect-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectRarityDTO)))
            .andExpect(status().isOk());

        // Validate the MTeamEffectRarity in the database
        List<MTeamEffectRarity> mTeamEffectRarityList = mTeamEffectRarityRepository.findAll();
        assertThat(mTeamEffectRarityList).hasSize(databaseSizeBeforeUpdate);
        MTeamEffectRarity testMTeamEffectRarity = mTeamEffectRarityList.get(mTeamEffectRarityList.size() - 1);
        assertThat(testMTeamEffectRarity.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMTeamEffectRarity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMTeamEffectRarity.getMaxLevel()).isEqualTo(UPDATED_MAX_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingMTeamEffectRarity() throws Exception {
        int databaseSizeBeforeUpdate = mTeamEffectRarityRepository.findAll().size();

        // Create the MTeamEffectRarity
        MTeamEffectRarityDTO mTeamEffectRarityDTO = mTeamEffectRarityMapper.toDto(mTeamEffectRarity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTeamEffectRarityMockMvc.perform(put("/api/m-team-effect-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectRarityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTeamEffectRarity in the database
        List<MTeamEffectRarity> mTeamEffectRarityList = mTeamEffectRarityRepository.findAll();
        assertThat(mTeamEffectRarityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTeamEffectRarity() throws Exception {
        // Initialize the database
        mTeamEffectRarityRepository.saveAndFlush(mTeamEffectRarity);

        int databaseSizeBeforeDelete = mTeamEffectRarityRepository.findAll().size();

        // Delete the mTeamEffectRarity
        restMTeamEffectRarityMockMvc.perform(delete("/api/m-team-effect-rarities/{id}", mTeamEffectRarity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTeamEffectRarity> mTeamEffectRarityList = mTeamEffectRarityRepository.findAll();
        assertThat(mTeamEffectRarityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTeamEffectRarity.class);
        MTeamEffectRarity mTeamEffectRarity1 = new MTeamEffectRarity();
        mTeamEffectRarity1.setId(1L);
        MTeamEffectRarity mTeamEffectRarity2 = new MTeamEffectRarity();
        mTeamEffectRarity2.setId(mTeamEffectRarity1.getId());
        assertThat(mTeamEffectRarity1).isEqualTo(mTeamEffectRarity2);
        mTeamEffectRarity2.setId(2L);
        assertThat(mTeamEffectRarity1).isNotEqualTo(mTeamEffectRarity2);
        mTeamEffectRarity1.setId(null);
        assertThat(mTeamEffectRarity1).isNotEqualTo(mTeamEffectRarity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTeamEffectRarityDTO.class);
        MTeamEffectRarityDTO mTeamEffectRarityDTO1 = new MTeamEffectRarityDTO();
        mTeamEffectRarityDTO1.setId(1L);
        MTeamEffectRarityDTO mTeamEffectRarityDTO2 = new MTeamEffectRarityDTO();
        assertThat(mTeamEffectRarityDTO1).isNotEqualTo(mTeamEffectRarityDTO2);
        mTeamEffectRarityDTO2.setId(mTeamEffectRarityDTO1.getId());
        assertThat(mTeamEffectRarityDTO1).isEqualTo(mTeamEffectRarityDTO2);
        mTeamEffectRarityDTO2.setId(2L);
        assertThat(mTeamEffectRarityDTO1).isNotEqualTo(mTeamEffectRarityDTO2);
        mTeamEffectRarityDTO1.setId(null);
        assertThat(mTeamEffectRarityDTO1).isNotEqualTo(mTeamEffectRarityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTeamEffectRarityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTeamEffectRarityMapper.fromId(null)).isNull();
    }
}
