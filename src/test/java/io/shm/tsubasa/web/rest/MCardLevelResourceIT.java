package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCardLevel;
import io.shm.tsubasa.repository.MCardLevelRepository;
import io.shm.tsubasa.service.MCardLevelService;
import io.shm.tsubasa.service.dto.MCardLevelDTO;
import io.shm.tsubasa.service.mapper.MCardLevelMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCardLevelCriteria;
import io.shm.tsubasa.service.MCardLevelQueryService;

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
 * Integration tests for the {@Link MCardLevelResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCardLevelResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_EXP = 1;
    private static final Integer UPDATED_EXP = 2;

    @Autowired
    private MCardLevelRepository mCardLevelRepository;

    @Autowired
    private MCardLevelMapper mCardLevelMapper;

    @Autowired
    private MCardLevelService mCardLevelService;

    @Autowired
    private MCardLevelQueryService mCardLevelQueryService;

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

    private MockMvc restMCardLevelMockMvc;

    private MCardLevel mCardLevel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCardLevelResource mCardLevelResource = new MCardLevelResource(mCardLevelService, mCardLevelQueryService);
        this.restMCardLevelMockMvc = MockMvcBuilders.standaloneSetup(mCardLevelResource)
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
    public static MCardLevel createEntity(EntityManager em) {
        MCardLevel mCardLevel = new MCardLevel()
            .rarity(DEFAULT_RARITY)
            .level(DEFAULT_LEVEL)
            .groupId(DEFAULT_GROUP_ID)
            .exp(DEFAULT_EXP);
        return mCardLevel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCardLevel createUpdatedEntity(EntityManager em) {
        MCardLevel mCardLevel = new MCardLevel()
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .groupId(UPDATED_GROUP_ID)
            .exp(UPDATED_EXP);
        return mCardLevel;
    }

    @BeforeEach
    public void initTest() {
        mCardLevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCardLevel() throws Exception {
        int databaseSizeBeforeCreate = mCardLevelRepository.findAll().size();

        // Create the MCardLevel
        MCardLevelDTO mCardLevelDTO = mCardLevelMapper.toDto(mCardLevel);
        restMCardLevelMockMvc.perform(post("/api/m-card-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardLevelDTO)))
            .andExpect(status().isCreated());

        // Validate the MCardLevel in the database
        List<MCardLevel> mCardLevelList = mCardLevelRepository.findAll();
        assertThat(mCardLevelList).hasSize(databaseSizeBeforeCreate + 1);
        MCardLevel testMCardLevel = mCardLevelList.get(mCardLevelList.size() - 1);
        assertThat(testMCardLevel.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMCardLevel.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMCardLevel.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMCardLevel.getExp()).isEqualTo(DEFAULT_EXP);
    }

    @Test
    @Transactional
    public void createMCardLevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCardLevelRepository.findAll().size();

        // Create the MCardLevel with an existing ID
        mCardLevel.setId(1L);
        MCardLevelDTO mCardLevelDTO = mCardLevelMapper.toDto(mCardLevel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCardLevelMockMvc.perform(post("/api/m-card-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardLevel in the database
        List<MCardLevel> mCardLevelList = mCardLevelRepository.findAll();
        assertThat(mCardLevelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardLevelRepository.findAll().size();
        // set the field null
        mCardLevel.setRarity(null);

        // Create the MCardLevel, which fails.
        MCardLevelDTO mCardLevelDTO = mCardLevelMapper.toDto(mCardLevel);

        restMCardLevelMockMvc.perform(post("/api/m-card-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MCardLevel> mCardLevelList = mCardLevelRepository.findAll();
        assertThat(mCardLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardLevelRepository.findAll().size();
        // set the field null
        mCardLevel.setLevel(null);

        // Create the MCardLevel, which fails.
        MCardLevelDTO mCardLevelDTO = mCardLevelMapper.toDto(mCardLevel);

        restMCardLevelMockMvc.perform(post("/api/m-card-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MCardLevel> mCardLevelList = mCardLevelRepository.findAll();
        assertThat(mCardLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardLevelRepository.findAll().size();
        // set the field null
        mCardLevel.setGroupId(null);

        // Create the MCardLevel, which fails.
        MCardLevelDTO mCardLevelDTO = mCardLevelMapper.toDto(mCardLevel);

        restMCardLevelMockMvc.perform(post("/api/m-card-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MCardLevel> mCardLevelList = mCardLevelRepository.findAll();
        assertThat(mCardLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardLevelRepository.findAll().size();
        // set the field null
        mCardLevel.setExp(null);

        // Create the MCardLevel, which fails.
        MCardLevelDTO mCardLevelDTO = mCardLevelMapper.toDto(mCardLevel);

        restMCardLevelMockMvc.perform(post("/api/m-card-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardLevelDTO)))
            .andExpect(status().isBadRequest());

        List<MCardLevel> mCardLevelList = mCardLevelRepository.findAll();
        assertThat(mCardLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCardLevels() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList
        restMCardLevelMockMvc.perform(get("/api/m-card-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)));
    }
    
    @Test
    @Transactional
    public void getMCardLevel() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get the mCardLevel
        restMCardLevelMockMvc.perform(get("/api/m-card-levels/{id}", mCardLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCardLevel.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.exp").value(DEFAULT_EXP));
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where rarity equals to DEFAULT_RARITY
        defaultMCardLevelShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mCardLevelList where rarity equals to UPDATED_RARITY
        defaultMCardLevelShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMCardLevelShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mCardLevelList where rarity equals to UPDATED_RARITY
        defaultMCardLevelShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where rarity is not null
        defaultMCardLevelShouldBeFound("rarity.specified=true");

        // Get all the mCardLevelList where rarity is null
        defaultMCardLevelShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where rarity greater than or equals to DEFAULT_RARITY
        defaultMCardLevelShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mCardLevelList where rarity greater than or equals to UPDATED_RARITY
        defaultMCardLevelShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where rarity less than or equals to DEFAULT_RARITY
        defaultMCardLevelShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mCardLevelList where rarity less than or equals to UPDATED_RARITY
        defaultMCardLevelShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMCardLevelsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where level equals to DEFAULT_LEVEL
        defaultMCardLevelShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mCardLevelList where level equals to UPDATED_LEVEL
        defaultMCardLevelShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMCardLevelShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mCardLevelList where level equals to UPDATED_LEVEL
        defaultMCardLevelShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where level is not null
        defaultMCardLevelShouldBeFound("level.specified=true");

        // Get all the mCardLevelList where level is null
        defaultMCardLevelShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where level greater than or equals to DEFAULT_LEVEL
        defaultMCardLevelShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mCardLevelList where level greater than or equals to UPDATED_LEVEL
        defaultMCardLevelShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where level less than or equals to DEFAULT_LEVEL
        defaultMCardLevelShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mCardLevelList where level less than or equals to UPDATED_LEVEL
        defaultMCardLevelShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMCardLevelsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where groupId equals to DEFAULT_GROUP_ID
        defaultMCardLevelShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mCardLevelList where groupId equals to UPDATED_GROUP_ID
        defaultMCardLevelShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMCardLevelShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mCardLevelList where groupId equals to UPDATED_GROUP_ID
        defaultMCardLevelShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where groupId is not null
        defaultMCardLevelShouldBeFound("groupId.specified=true");

        // Get all the mCardLevelList where groupId is null
        defaultMCardLevelShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMCardLevelShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mCardLevelList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMCardLevelShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMCardLevelShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mCardLevelList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMCardLevelShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMCardLevelsByExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where exp equals to DEFAULT_EXP
        defaultMCardLevelShouldBeFound("exp.equals=" + DEFAULT_EXP);

        // Get all the mCardLevelList where exp equals to UPDATED_EXP
        defaultMCardLevelShouldNotBeFound("exp.equals=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByExpIsInShouldWork() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where exp in DEFAULT_EXP or UPDATED_EXP
        defaultMCardLevelShouldBeFound("exp.in=" + DEFAULT_EXP + "," + UPDATED_EXP);

        // Get all the mCardLevelList where exp equals to UPDATED_EXP
        defaultMCardLevelShouldNotBeFound("exp.in=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where exp is not null
        defaultMCardLevelShouldBeFound("exp.specified=true");

        // Get all the mCardLevelList where exp is null
        defaultMCardLevelShouldNotBeFound("exp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where exp greater than or equals to DEFAULT_EXP
        defaultMCardLevelShouldBeFound("exp.greaterOrEqualThan=" + DEFAULT_EXP);

        // Get all the mCardLevelList where exp greater than or equals to UPDATED_EXP
        defaultMCardLevelShouldNotBeFound("exp.greaterOrEqualThan=" + UPDATED_EXP);
    }

    @Test
    @Transactional
    public void getAllMCardLevelsByExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        // Get all the mCardLevelList where exp less than or equals to DEFAULT_EXP
        defaultMCardLevelShouldNotBeFound("exp.lessThan=" + DEFAULT_EXP);

        // Get all the mCardLevelList where exp less than or equals to UPDATED_EXP
        defaultMCardLevelShouldBeFound("exp.lessThan=" + UPDATED_EXP);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCardLevelShouldBeFound(String filter) throws Exception {
        restMCardLevelMockMvc.perform(get("/api/m-card-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].exp").value(hasItem(DEFAULT_EXP)));

        // Check, that the count call also returns 1
        restMCardLevelMockMvc.perform(get("/api/m-card-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCardLevelShouldNotBeFound(String filter) throws Exception {
        restMCardLevelMockMvc.perform(get("/api/m-card-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCardLevelMockMvc.perform(get("/api/m-card-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCardLevel() throws Exception {
        // Get the mCardLevel
        restMCardLevelMockMvc.perform(get("/api/m-card-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCardLevel() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        int databaseSizeBeforeUpdate = mCardLevelRepository.findAll().size();

        // Update the mCardLevel
        MCardLevel updatedMCardLevel = mCardLevelRepository.findById(mCardLevel.getId()).get();
        // Disconnect from session so that the updates on updatedMCardLevel are not directly saved in db
        em.detach(updatedMCardLevel);
        updatedMCardLevel
            .rarity(UPDATED_RARITY)
            .level(UPDATED_LEVEL)
            .groupId(UPDATED_GROUP_ID)
            .exp(UPDATED_EXP);
        MCardLevelDTO mCardLevelDTO = mCardLevelMapper.toDto(updatedMCardLevel);

        restMCardLevelMockMvc.perform(put("/api/m-card-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardLevelDTO)))
            .andExpect(status().isOk());

        // Validate the MCardLevel in the database
        List<MCardLevel> mCardLevelList = mCardLevelRepository.findAll();
        assertThat(mCardLevelList).hasSize(databaseSizeBeforeUpdate);
        MCardLevel testMCardLevel = mCardLevelList.get(mCardLevelList.size() - 1);
        assertThat(testMCardLevel.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMCardLevel.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMCardLevel.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMCardLevel.getExp()).isEqualTo(UPDATED_EXP);
    }

    @Test
    @Transactional
    public void updateNonExistingMCardLevel() throws Exception {
        int databaseSizeBeforeUpdate = mCardLevelRepository.findAll().size();

        // Create the MCardLevel
        MCardLevelDTO mCardLevelDTO = mCardLevelMapper.toDto(mCardLevel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCardLevelMockMvc.perform(put("/api/m-card-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardLevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardLevel in the database
        List<MCardLevel> mCardLevelList = mCardLevelRepository.findAll();
        assertThat(mCardLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCardLevel() throws Exception {
        // Initialize the database
        mCardLevelRepository.saveAndFlush(mCardLevel);

        int databaseSizeBeforeDelete = mCardLevelRepository.findAll().size();

        // Delete the mCardLevel
        restMCardLevelMockMvc.perform(delete("/api/m-card-levels/{id}", mCardLevel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCardLevel> mCardLevelList = mCardLevelRepository.findAll();
        assertThat(mCardLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardLevel.class);
        MCardLevel mCardLevel1 = new MCardLevel();
        mCardLevel1.setId(1L);
        MCardLevel mCardLevel2 = new MCardLevel();
        mCardLevel2.setId(mCardLevel1.getId());
        assertThat(mCardLevel1).isEqualTo(mCardLevel2);
        mCardLevel2.setId(2L);
        assertThat(mCardLevel1).isNotEqualTo(mCardLevel2);
        mCardLevel1.setId(null);
        assertThat(mCardLevel1).isNotEqualTo(mCardLevel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardLevelDTO.class);
        MCardLevelDTO mCardLevelDTO1 = new MCardLevelDTO();
        mCardLevelDTO1.setId(1L);
        MCardLevelDTO mCardLevelDTO2 = new MCardLevelDTO();
        assertThat(mCardLevelDTO1).isNotEqualTo(mCardLevelDTO2);
        mCardLevelDTO2.setId(mCardLevelDTO1.getId());
        assertThat(mCardLevelDTO1).isEqualTo(mCardLevelDTO2);
        mCardLevelDTO2.setId(2L);
        assertThat(mCardLevelDTO1).isNotEqualTo(mCardLevelDTO2);
        mCardLevelDTO1.setId(null);
        assertThat(mCardLevelDTO1).isNotEqualTo(mCardLevelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCardLevelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCardLevelMapper.fromId(null)).isNull();
    }
}
