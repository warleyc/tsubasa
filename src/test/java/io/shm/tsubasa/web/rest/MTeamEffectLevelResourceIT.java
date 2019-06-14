package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTeamEffectLevel;
import io.shm.tsubasa.repository.MTeamEffectLevelRepository;
import io.shm.tsubasa.service.MTeamEffectLevelService;
import io.shm.tsubasa.service.dto.MTeamEffectLevelDTO;
import io.shm.tsubasa.service.mapper.MTeamEffectLevelMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTeamEffectLevelCriteria;
import io.shm.tsubasa.service.MTeamEffectLevelQueryService;

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
 * Integration tests for the {@Link MTeamEffectLevelResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTeamEffectLevelResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_EXP = 1;
    private static final Integer UPDATED_EXP = 2;

    @Autowired
    private MTeamEffectLevelRepository mTeamEffectLevelRepository;

    @Autowired
    private MTeamEffectLevelMapper mTeamEffectLevelMapper;

    @Autowired
    private MTeamEffectLevelService mTeamEffectLevelService;

    @Autowired
    private MTeamEffectLevelQueryService mTeamEffectLevelQueryService;

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

    private MockMvc restMTeamEffectLevelMockMvc;

    private MTeamEffectLevel mTeamEffectLevel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTeamEffectLevelResource mTeamEffectLevelResource = new MTeamEffectLevelResource(mTeamEffectLevelService, mTeamEffectLevelQueryService);
        this.restMTeamEffectLevelMockMvc = MockMvcBuilders.standaloneSetup(mTeamEffectLevelResource)
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
    public static MTeamEffectLevel createEntity(EntityManager em) {
        MTeamEffectLevel mTeamEffectLevel = new MTeamEffectLevel()
            .rarity(DEFAULT_RARITY)
            .level(DEFAULT_LEVEL)
            .exp(DEFAULT_EXP);
        return mTeamEffectLevel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTeamEffectLevel createUpdatedEntity(EntityManager em) {
        MTeamEffectLevel mTeamEffectLevel = new MTeamEffectLevel()
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .exp(UPDATED_EXP);
        return mTeamEffectLevel;
    }

    @BeforeEach
    public void initTest() {
        mTeamEffectLevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTeamEffectLevel() throws Exception {
        int databaseSizeBeforeCreate = mTeamEffectLevelRepository.findAll().size();

        // Create the MTeamEffectLevel
        MTeamEffectLevelDTO mTeamEffectLevelDTO = mTeamEffectLevelMapper.toDto(mTeamEffectLevel);
        restMTeamEffectLevelMockMvc.perform(post("/api/m-team-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectLevelDTO)))
            .andExpect(status().isCreated());

        // Validate the MTeamEffectLevel in the database
        List<MTeamEffectLevel> mTeamEffectLevelList = mTeamEffectLevelRepository.findAll();
        assertThat(mTeamEffectLevelList).hasSize(databaseSizeBeforeCreate + 1);
        MTeamEffectLevel testMTeamEffectLevel = mTeamEffectLevelList.get(mTeamEffectLevelList.size() - 1);
        assertThat(testMTeamEffectLevel.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMTeamEffectLevel.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMTeamEffectLevel.getExp()).isEqualTo(DEFAULT_EXP);
    }

    @Test
    @Transactional
    public void createMTeamEffectLevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTeamEffectLevelRepository.findAll().size();

        // Create the MTeamEffectLevel with an existing ID
        mTeamEffectLevel.setId(1L);
        MTeamEffectLevelDTO mTeamEffectLevelDTO = mTeamEffectLevelMapper.toDto(mTeamEffectLevel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTeamEffectLevelMockMvc.perform(post("/api/m-team-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTeamEffectLevel in the database
        List<MTeamEffectLevel> mTeamEffectLevelList = mTeamEffectLevelRepository.findAll();
        assertThat(mTeamEffectLevelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectLevelRepository.findAll().size();
        // set the field null
        mTeamEffectLevel.setRarity(null);

        // Create the MTeamEffectLevel, which fails.
        MTeamEffectLevelDTO mTeamEffectLevelDTO = mTeamEffectLevelMapper.toDto(mTeamEffectLevel);

        restMTeamEffectLevelMockMvc.perform(post("/api/m-team-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectLevel> mTeamEffectLevelList = mTeamEffectLevelRepository.findAll();
        assertThat(mTeamEffectLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectLevelRepository.findAll().size();
        // set the field null
        mTeamEffectLevel.setLevel(null);

        // Create the MTeamEffectLevel, which fails.
        MTeamEffectLevelDTO mTeamEffectLevelDTO = mTeamEffectLevelMapper.toDto(mTeamEffectLevel);

        restMTeamEffectLevelMockMvc.perform(post("/api/m-team-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectLevel> mTeamEffectLevelList = mTeamEffectLevelRepository.findAll();
        assertThat(mTeamEffectLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTeamEffectLevelRepository.findAll().size();
        // set the field null
        mTeamEffectLevel.setExp(null);

        // Create the MTeamEffectLevel, which fails.
        MTeamEffectLevelDTO mTeamEffectLevelDTO = mTeamEffectLevelMapper.toDto(mTeamEffectLevel);

        restMTeamEffectLevelMockMvc.perform(post("/api/m-team-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MTeamEffectLevel> mTeamEffectLevelList = mTeamEffectLevelRepository.findAll();
        assertThat(mTeamEffectLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevels() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList
        restMTeamEffectLevelMockMvc.perform(get("/api/m-team-effect-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTeamEffectLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)));
    }
    
    @Test
    @Transactional
    public void getMTeamEffectLevel() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get the mTeamEffectLevel
        restMTeamEffectLevelMockMvc.perform(get("/api/m-team-effect-levels/{id}", mTeamEffectLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTeamEffectLevel.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.exp").value(DEFAULT_EXP));
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where rarity equals to DEFAULT_RARITY
        defaultMTeamEffectLevelShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mTeamEffectLevelList where rarity equals to UPDATED_RARITY
        defaultMTeamEffectLevelShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMTeamEffectLevelShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mTeamEffectLevelList where rarity equals to UPDATED_RARITY
        defaultMTeamEffectLevelShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where rarity is not null
        defaultMTeamEffectLevelShouldBeFound("rarity.specified=true");

        // Get all the mTeamEffectLevelList where rarity is null
        defaultMTeamEffectLevelShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where rarity greater than or equals to DEFAULT_RARITY
        defaultMTeamEffectLevelShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mTeamEffectLevelList where rarity greater than or equals to UPDATED_RARITY
        defaultMTeamEffectLevelShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where rarity less than or equals to DEFAULT_RARITY
        defaultMTeamEffectLevelShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mTeamEffectLevelList where rarity less than or equals to UPDATED_RARITY
        defaultMTeamEffectLevelShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where level equals to DEFAULT_LEVEL
        defaultMTeamEffectLevelShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mTeamEffectLevelList where level equals to UPDATED_LEVEL
        defaultMTeamEffectLevelShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMTeamEffectLevelShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mTeamEffectLevelList where level equals to UPDATED_LEVEL
        defaultMTeamEffectLevelShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where level is not null
        defaultMTeamEffectLevelShouldBeFound("level.specified=true");

        // Get all the mTeamEffectLevelList where level is null
        defaultMTeamEffectLevelShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where level greater than or equals to DEFAULT_LEVEL
        defaultMTeamEffectLevelShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mTeamEffectLevelList where level greater than or equals to UPDATED_LEVEL
        defaultMTeamEffectLevelShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where level less than or equals to DEFAULT_LEVEL
        defaultMTeamEffectLevelShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mTeamEffectLevelList where level less than or equals to UPDATED_LEVEL
        defaultMTeamEffectLevelShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where exp equals to DEFAULT_EXP
        defaultMTeamEffectLevelShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mTeamEffectLevelList where exp equals to UPDATED_EXP
        defaultMTeamEffectLevelShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMTeamEffectLevelShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mTeamEffectLevelList where exp equals to UPDATED_EXP
        defaultMTeamEffectLevelShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where exp is not null
        defaultMTeamEffectLevelShouldBeFound("exp.specified=true");

        // Get all the mTeamEffectLevelList where exp is null
        defaultMTeamEffectLevelShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where exp greater than or equals to DEFAULT_EXP
        defaultMTeamEffectLevelShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mTeamEffectLevelList where exp greater than or equals to UPDATED_EXP
        defaultMTeamEffectLevelShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMTeamEffectLevelsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        // Get all the mTeamEffectLevelList where exp less than or equals to DEFAULT_EXP
        defaultMTeamEffectLevelShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mTeamEffectLevelList where exp less than or equals to UPDATED_EXP
        defaultMTeamEffectLevelShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTeamEffectLevelShouldBeFound(String filter) throws Exception {
        restMTeamEffectLevelMockMvc.perform(get("/api/m-team-effect-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTeamEffectLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)));

        // Check, that the count call also returns 1
        restMTeamEffectLevelMockMvc.perform(get("/api/m-team-effect-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTeamEffectLevelShouldNotBeFound(String filter) throws Exception {
        restMTeamEffectLevelMockMvc.perform(get("/api/m-team-effect-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTeamEffectLevelMockMvc.perform(get("/api/m-team-effect-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTeamEffectLevel() throws Exception {
        // Get the mTeamEffectLevel
        restMTeamEffectLevelMockMvc.perform(get("/api/m-team-effect-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTeamEffectLevel() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        int databaseSizeBeforeUpdate = mTeamEffectLevelRepository.findAll().size();

        // Update the mTeamEffectLevel
        MTeamEffectLevel updatedMTeamEffectLevel = mTeamEffectLevelRepository.findById(mTeamEffectLevel.getId()).get();
        // Disconnect from session so that the updates on updatedMTeamEffectLevel are not directly saved in db
        em.detach(updatedMTeamEffectLevel);
        updatedMTeamEffectLevel
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .exp(UPDATED_EXP);
        MTeamEffectLevelDTO mTeamEffectLevelDTO = mTeamEffectLevelMapper.toDto(updatedMTeamEffectLevel);

        restMTeamEffectLevelMockMvc.perform(put("/api/m-team-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectLevelDTO)))
            .andExpect(status().isOk());

        // Validate the MTeamEffectLevel in the database
        List<MTeamEffectLevel> mTeamEffectLevelList = mTeamEffectLevelRepository.findAll();
        assertThat(mTeamEffectLevelList).hasSize(databaseSizeBeforeUpdate);
        MTeamEffectLevel testMTeamEffectLevel = mTeamEffectLevelList.get(mTeamEffectLevelList.size() - 1);
        assertThat(testMTeamEffectLevel.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMTeamEffectLevel.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMTeamEffectLevel.getExp()).isEqualTo(UPDATED_EXP);
    }

    @Test
    @Transactional
    public void updateNonExistingMTeamEffectLevel() throws Exception {
        int databaseSizeBeforeUpdate = mTeamEffectLevelRepository.findAll().size();

        // Create the MTeamEffectLevel
        MTeamEffectLevelDTO mTeamEffectLevelDTO = mTeamEffectLevelMapper.toDto(mTeamEffectLevel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTeamEffectLevelMockMvc.perform(put("/api/m-team-effect-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamEffectLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTeamEffectLevel in the database
        List<MTeamEffectLevel> mTeamEffectLevelList = mTeamEffectLevelRepository.findAll();
        assertThat(mTeamEffectLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTeamEffectLevel() throws Exception {
        // Initialize the database
        mTeamEffectLevelRepository.saveAndFlush(mTeamEffectLevel);

        int databaseSizeBeforeDelete = mTeamEffectLevelRepository.findAll().size();

        // Delete the mTeamEffectLevel
        restMTeamEffectLevelMockMvc.perform(delete("/api/m-team-effect-levels/{id}", mTeamEffectLevel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTeamEffectLevel> mTeamEffectLevelList = mTeamEffectLevelRepository.findAll();
        assertThat(mTeamEffectLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTeamEffectLevel.class);
        MTeamEffectLevel mTeamEffectLevel1 = new MTeamEffectLevel();
        mTeamEffectLevel1.setId(1L);
        MTeamEffectLevel mTeamEffectLevel2 = new MTeamEffectLevel();
        mTeamEffectLevel2.setId(mTeamEffectLevel1.getId());
        assertThat(mTeamEffectLevel1).isEqualTo(mTeamEffectLevel2);
        mTeamEffectLevel2.setId(2L);
        assertThat(mTeamEffectLevel1).isNotEqualTo(mTeamEffectLevel2);
        mTeamEffectLevel1.setId(null);
        assertThat(mTeamEffectLevel1).isNotEqualTo(mTeamEffectLevel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTeamEffectLevelDTO.class);
        MTeamEffectLevelDTO mTeamEffectLevelDTO1 = new MTeamEffectLevelDTO();
        mTeamEffectLevelDTO1.setId(1L);
        MTeamEffectLevelDTO mTeamEffectLevelDTO2 = new MTeamEffectLevelDTO();
        assertThat(mTeamEffectLevelDTO1).isNotEqualTo(mTeamEffectLevelDTO2);
        mTeamEffectLevelDTO2.setId(mTeamEffectLevelDTO1.getId());
        assertThat(mTeamEffectLevelDTO1).isEqualTo(mTeamEffectLevelDTO2);
        mTeamEffectLevelDTO2.setId(2L);
        assertThat(mTeamEffectLevelDTO1).isNotEqualTo(mTeamEffectLevelDTO2);
        mTeamEffectLevelDTO1.setId(null);
        assertThat(mTeamEffectLevelDTO1).isNotEqualTo(mTeamEffectLevelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTeamEffectLevelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTeamEffectLevelMapper.fromId(null)).isNull();
    }
}
