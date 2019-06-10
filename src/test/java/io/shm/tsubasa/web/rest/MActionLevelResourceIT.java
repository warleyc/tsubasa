package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MActionLevel;
import io.shm.tsubasa.repository.MActionLevelRepository;
import io.shm.tsubasa.service.MActionLevelService;
import io.shm.tsubasa.service.dto.MActionLevelDTO;
import io.shm.tsubasa.service.mapper.MActionLevelMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MActionLevelCriteria;
import io.shm.tsubasa.service.MActionLevelQueryService;

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
 * Integration tests for the {@Link MActionLevelResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MActionLevelResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_EXP = 1;
    private static final Integer UPDATED_EXP = 2;

    @Autowired
    private MActionLevelRepository mActionLevelRepository;

    @Autowired
    private MActionLevelMapper mActionLevelMapper;

    @Autowired
    private MActionLevelService mActionLevelService;

    @Autowired
    private MActionLevelQueryService mActionLevelQueryService;

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

    private MockMvc restMActionLevelMockMvc;

    private MActionLevel mActionLevel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MActionLevelResource mActionLevelResource = new MActionLevelResource(mActionLevelService, mActionLevelQueryService);
        this.restMActionLevelMockMvc = MockMvcBuilders.standaloneSetup(mActionLevelResource)
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
    public static MActionLevel createEntity(EntityManager em) {
        MActionLevel mActionLevel = new MActionLevel()
            .rarity(DEFAULT_RARITY)
            .level(DEFAULT_LEVEL)
            .exp(DEFAULT_EXP);
        return mActionLevel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MActionLevel createUpdatedEntity(EntityManager em) {
        MActionLevel mActionLevel = new MActionLevel()
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .exp(UPDATED_EXP);
        return mActionLevel;
    }

    @BeforeEach
    public void initTest() {
        mActionLevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createMActionLevel() throws Exception {
        int databaseSizeBeforeCreate = mActionLevelRepository.findAll().size();

        // Create the MActionLevel
        MActionLevelDTO mActionLevelDTO = mActionLevelMapper.toDto(mActionLevel);
        restMActionLevelMockMvc.perform(post("/api/m-action-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionLevelDTO)))
            .andExpect(status().isCreated());

        // Validate the MActionLevel in the database
        List<MActionLevel> mActionLevelList = mActionLevelRepository.findAll();
        assertThat(mActionLevelList).hasSize(databaseSizeBeforeCreate + 1);
        MActionLevel testMActionLevel = mActionLevelList.get(mActionLevelList.size() - 1);
        assertThat(testMActionLevel.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMActionLevel.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMActionLevel.getExp()).isEqualTo(DEFAULT_EXP);
    }

    @Test
    @Transactional
    public void createMActionLevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mActionLevelRepository.findAll().size();

        // Create the MActionLevel with an existing ID
        mActionLevel.setId(1L);
        MActionLevelDTO mActionLevelDTO = mActionLevelMapper.toDto(mActionLevel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMActionLevelMockMvc.perform(post("/api/m-action-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionLevel in the database
        List<MActionLevel> mActionLevelList = mActionLevelRepository.findAll();
        assertThat(mActionLevelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionLevelRepository.findAll().size();
        // set the field null
        mActionLevel.setRarity(null);

        // Create the MActionLevel, which fails.
        MActionLevelDTO mActionLevelDTO = mActionLevelMapper.toDto(mActionLevel);

        restMActionLevelMockMvc.perform(post("/api/m-action-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MActionLevel> mActionLevelList = mActionLevelRepository.findAll();
        assertThat(mActionLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionLevelRepository.findAll().size();
        // set the field null
        mActionLevel.setLevel(null);

        // Create the MActionLevel, which fails.
        MActionLevelDTO mActionLevelDTO = mActionLevelMapper.toDto(mActionLevel);

        restMActionLevelMockMvc.perform(post("/api/m-action-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MActionLevel> mActionLevelList = mActionLevelRepository.findAll();
        assertThat(mActionLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionLevelRepository.findAll().size();
        // set the field null
        mActionLevel.setExp(null);

        // Create the MActionLevel, which fails.
        MActionLevelDTO mActionLevelDTO = mActionLevelMapper.toDto(mActionLevel);

        restMActionLevelMockMvc.perform(post("/api/m-action-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MActionLevel> mActionLevelList = mActionLevelRepository.findAll();
        assertThat(mActionLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMActionLevels() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList
        restMActionLevelMockMvc.perform(get("/api/m-action-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)));
    }
    
    @Test
    @Transactional
    public void getMActionLevel() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get the mActionLevel
        restMActionLevelMockMvc.perform(get("/api/m-action-levels/{id}", mActionLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mActionLevel.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.exp").value(DEFAULT_EXP));
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where rarity equals to DEFAULT_RARITY
        defaultMActionLevelShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mActionLevelList where rarity equals to UPDATED_RARITY
        defaultMActionLevelShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMActionLevelShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mActionLevelList where rarity equals to UPDATED_RARITY
        defaultMActionLevelShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where rarity is not null
        defaultMActionLevelShouldBeFound("rarity.specified=true");

        // Get all the mActionLevelList where rarity is null
        defaultMActionLevelShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where rarity greater than or equals to DEFAULT_RARITY
        defaultMActionLevelShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mActionLevelList where rarity greater than or equals to UPDATED_RARITY
        defaultMActionLevelShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where rarity less than or equals to DEFAULT_RARITY
        defaultMActionLevelShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mActionLevelList where rarity less than or equals to UPDATED_RARITY
        defaultMActionLevelShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMActionLevelsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where level equals to DEFAULT_LEVEL
        defaultMActionLevelShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mActionLevelList where level equals to UPDATED_LEVEL
        defaultMActionLevelShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMActionLevelShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mActionLevelList where level equals to UPDATED_LEVEL
        defaultMActionLevelShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where level is not null
        defaultMActionLevelShouldBeFound("level.specified=true");

        // Get all the mActionLevelList where level is null
        defaultMActionLevelShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where level greater than or equals to DEFAULT_LEVEL
        defaultMActionLevelShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mActionLevelList where level greater than or equals to UPDATED_LEVEL
        defaultMActionLevelShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where level less than or equals to DEFAULT_LEVEL
        defaultMActionLevelShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mActionLevelList where level less than or equals to UPDATED_LEVEL
        defaultMActionLevelShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMActionLevelsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where exp equals to DEFAULT_EXP
        defaultMActionLevelShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mActionLevelList where exp equals to UPDATED_EXP
        defaultMActionLevelShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMActionLevelShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mActionLevelList where exp equals to UPDATED_EXP
        defaultMActionLevelShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where exp is not null
        defaultMActionLevelShouldBeFound("exp.specified=true");

        // Get all the mActionLevelList where exp is null
        defaultMActionLevelShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where exp greater than or equals to DEFAULT_EXP
        defaultMActionLevelShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mActionLevelList where exp greater than or equals to UPDATED_EXP
        defaultMActionLevelShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMActionLevelsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        // Get all the mActionLevelList where exp less than or equals to DEFAULT_EXP
        defaultMActionLevelShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mActionLevelList where exp less than or equals to UPDATED_EXP
        defaultMActionLevelShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMActionLevelShouldBeFound(String filter) throws Exception {
        restMActionLevelMockMvc.perform(get("/api/m-action-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)));

        // Check, that the count call also returns 1
        restMActionLevelMockMvc.perform(get("/api/m-action-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMActionLevelShouldNotBeFound(String filter) throws Exception {
        restMActionLevelMockMvc.perform(get("/api/m-action-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMActionLevelMockMvc.perform(get("/api/m-action-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMActionLevel() throws Exception {
        // Get the mActionLevel
        restMActionLevelMockMvc.perform(get("/api/m-action-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMActionLevel() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        int databaseSizeBeforeUpdate = mActionLevelRepository.findAll().size();

        // Update the mActionLevel
        MActionLevel updatedMActionLevel = mActionLevelRepository.findById(mActionLevel.getId()).get();
        // Disconnect from session so that the updates on updatedMActionLevel are not directly saved in db
        em.detach(updatedMActionLevel);
        updatedMActionLevel
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .exp(UPDATED_EXP);
        MActionLevelDTO mActionLevelDTO = mActionLevelMapper.toDto(updatedMActionLevel);

        restMActionLevelMockMvc.perform(put("/api/m-action-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionLevelDTO)))
            .andExpect(status().isOk());

        // Validate the MActionLevel in the database
        List<MActionLevel> mActionLevelList = mActionLevelRepository.findAll();
        assertThat(mActionLevelList).hasSize(databaseSizeBeforeUpdate);
        MActionLevel testMActionLevel = mActionLevelList.get(mActionLevelList.size() - 1);
        assertThat(testMActionLevel.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMActionLevel.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMActionLevel.getExp()).isEqualTo(UPDATED_EXP);
    }

    @Test
    @Transactional
    public void updateNonExistingMActionLevel() throws Exception {
        int databaseSizeBeforeUpdate = mActionLevelRepository.findAll().size();

        // Create the MActionLevel
        MActionLevelDTO mActionLevelDTO = mActionLevelMapper.toDto(mActionLevel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMActionLevelMockMvc.perform(put("/api/m-action-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionLevel in the database
        List<MActionLevel> mActionLevelList = mActionLevelRepository.findAll();
        assertThat(mActionLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMActionLevel() throws Exception {
        // Initialize the database
        mActionLevelRepository.saveAndFlush(mActionLevel);

        int databaseSizeBeforeDelete = mActionLevelRepository.findAll().size();

        // Delete the mActionLevel
        restMActionLevelMockMvc.perform(delete("/api/m-action-levels/{id}", mActionLevel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MActionLevel> mActionLevelList = mActionLevelRepository.findAll();
        assertThat(mActionLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionLevel.class);
        MActionLevel mActionLevel1 = new MActionLevel();
        mActionLevel1.setId(1L);
        MActionLevel mActionLevel2 = new MActionLevel();
        mActionLevel2.setId(mActionLevel1.getId());
        assertThat(mActionLevel1).isEqualTo(mActionLevel2);
        mActionLevel2.setId(2L);
        assertThat(mActionLevel1).isNotEqualTo(mActionLevel2);
        mActionLevel1.setId(null);
        assertThat(mActionLevel1).isNotEqualTo(mActionLevel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionLevelDTO.class);
        MActionLevelDTO mActionLevelDTO1 = new MActionLevelDTO();
        mActionLevelDTO1.setId(1L);
        MActionLevelDTO mActionLevelDTO2 = new MActionLevelDTO();
        assertThat(mActionLevelDTO1).isNotEqualTo(mActionLevelDTO2);
        mActionLevelDTO2.setId(mActionLevelDTO1.getId());
        assertThat(mActionLevelDTO1).isEqualTo(mActionLevelDTO2);
        mActionLevelDTO2.setId(2L);
        assertThat(mActionLevelDTO1).isNotEqualTo(mActionLevelDTO2);
        mActionLevelDTO1.setId(null);
        assertThat(mActionLevelDTO1).isNotEqualTo(mActionLevelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mActionLevelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mActionLevelMapper.fromId(null)).isNull();
    }
}
