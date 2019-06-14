package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MShoot;
import io.shm.tsubasa.repository.MShootRepository;
import io.shm.tsubasa.service.MShootService;
import io.shm.tsubasa.service.dto.MShootDTO;
import io.shm.tsubasa.service.mapper.MShootMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MShootCriteria;
import io.shm.tsubasa.service.MShootQueryService;

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
 * Integration tests for the {@Link MShootResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MShootResourceIT {

    private static final Integer DEFAULT_ANGLE_DECAY_TYPE = 1;
    private static final Integer UPDATED_ANGLE_DECAY_TYPE = 2;

    private static final Integer DEFAULT_SHOOT_ORBIT = 1;
    private static final Integer UPDATED_SHOOT_ORBIT = 2;

    private static final Integer DEFAULT_JUDGEMENT_ID = 1;
    private static final Integer UPDATED_JUDGEMENT_ID = 2;

    @Autowired
    private MShootRepository mShootRepository;

    @Autowired
    private MShootMapper mShootMapper;

    @Autowired
    private MShootService mShootService;

    @Autowired
    private MShootQueryService mShootQueryService;

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

    private MockMvc restMShootMockMvc;

    private MShoot mShoot;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MShootResource mShootResource = new MShootResource(mShootService, mShootQueryService);
        this.restMShootMockMvc = MockMvcBuilders.standaloneSetup(mShootResource)
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
    public static MShoot createEntity(EntityManager em) {
        MShoot mShoot = new MShoot()
            .angleDecayType(DEFAULT_ANGLE_DECAY_TYPE)
            .shootOrbit(DEFAULT_SHOOT_ORBIT)
            .judgementId(DEFAULT_JUDGEMENT_ID);
        return mShoot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MShoot createUpdatedEntity(EntityManager em) {
        MShoot mShoot = new MShoot()
            .angleDecayType(UPDATED_ANGLE_DECAY_TYPE)
            .shootOrbit(UPDATED_SHOOT_ORBIT)
            .judgementId(UPDATED_JUDGEMENT_ID);
        return mShoot;
    }

    @BeforeEach
    public void initTest() {
        mShoot = createEntity(em);
    }

    @Test
    @Transactional
    public void createMShoot() throws Exception {
        int databaseSizeBeforeCreate = mShootRepository.findAll().size();

        // Create the MShoot
        MShootDTO mShootDTO = mShootMapper.toDto(mShoot);
        restMShootMockMvc.perform(post("/api/m-shoots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mShootDTO)))
            .andExpect(status().isCreated());

        // Validate the MShoot in the database
        List<MShoot> mShootList = mShootRepository.findAll();
        assertThat(mShootList).hasSize(databaseSizeBeforeCreate + 1);
        MShoot testMShoot = mShootList.get(mShootList.size() - 1);
        assertThat(testMShoot.getAngleDecayType()).isEqualTo(DEFAULT_ANGLE_DECAY_TYPE);
        assertThat(testMShoot.getShootOrbit()).isEqualTo(DEFAULT_SHOOT_ORBIT);
        assertThat(testMShoot.getJudgementId()).isEqualTo(DEFAULT_JUDGEMENT_ID);
    }

    @Test
    @Transactional
    public void createMShootWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mShootRepository.findAll().size();

        // Create the MShoot with an existing ID
        mShoot.setId(1L);
        MShootDTO mShootDTO = mShootMapper.toDto(mShoot);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMShootMockMvc.perform(post("/api/m-shoots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mShootDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MShoot in the database
        List<MShoot> mShootList = mShootRepository.findAll();
        assertThat(mShootList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAngleDecayTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mShootRepository.findAll().size();
        // set the field null
        mShoot.setAngleDecayType(null);

        // Create the MShoot, which fails.
        MShootDTO mShootDTO = mShootMapper.toDto(mShoot);

        restMShootMockMvc.perform(post("/api/m-shoots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mShootDTO)))
            .andExpect(status().isBadRequest());

        List<MShoot> mShootList = mShootRepository.findAll();
        assertThat(mShootList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShootOrbitIsRequired() throws Exception {
        int databaseSizeBeforeTest = mShootRepository.findAll().size();
        // set the field null
        mShoot.setShootOrbit(null);

        // Create the MShoot, which fails.
        MShootDTO mShootDTO = mShootMapper.toDto(mShoot);

        restMShootMockMvc.perform(post("/api/m-shoots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mShootDTO)))
            .andExpect(status().isBadRequest());

        List<MShoot> mShootList = mShootRepository.findAll();
        assertThat(mShootList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJudgementIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mShootRepository.findAll().size();
        // set the field null
        mShoot.setJudgementId(null);

        // Create the MShoot, which fails.
        MShootDTO mShootDTO = mShootMapper.toDto(mShoot);

        restMShootMockMvc.perform(post("/api/m-shoots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mShootDTO)))
            .andExpect(status().isBadRequest());

        List<MShoot> mShootList = mShootRepository.findAll();
        assertThat(mShootList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMShoots() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList
        restMShootMockMvc.perform(get("/api/m-shoots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mShoot.getId().intValue())))
            .andExpect(jsonPath("$.[*].angleDecayType").value(hasItem(DEFAULT_ANGLE_DECAY_TYPE)))
            .andExpect(jsonPath("$.[*].shootOrbit").value(hasItem(DEFAULT_SHOOT_ORBIT)))
            .andExpect(jsonPath("$.[*].judgementId").value(hasItem(DEFAULT_JUDGEMENT_ID)));
    }
    
    @Test
    @Transactional
    public void getMShoot() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get the mShoot
        restMShootMockMvc.perform(get("/api/m-shoots/{id}", mShoot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mShoot.getId().intValue()))
            .andExpect(jsonPath("$.angleDecayType").value(DEFAULT_ANGLE_DECAY_TYPE))
            .andExpect(jsonPath("$.shootOrbit").value(DEFAULT_SHOOT_ORBIT))
            .andExpect(jsonPath("$.judgementId").value(DEFAULT_JUDGEMENT_ID));
    }

    @Test
    @Transactional
    public void getAllMShootsByAngleDecayTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where angleDecayType equals to DEFAULT_ANGLE_DECAY_TYPE
        defaultMShootShouldBeFound("angleDecayType.equals=" + DEFAULT_ANGLE_DECAY_TYPE);

        // Get all the mShootList where angleDecayType equals to UPDATED_ANGLE_DECAY_TYPE
        defaultMShootShouldNotBeFound("angleDecayType.equals=" + UPDATED_ANGLE_DECAY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMShootsByAngleDecayTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where angleDecayType in DEFAULT_ANGLE_DECAY_TYPE or UPDATED_ANGLE_DECAY_TYPE
        defaultMShootShouldBeFound("angleDecayType.in=" + DEFAULT_ANGLE_DECAY_TYPE + "," + UPDATED_ANGLE_DECAY_TYPE);

        // Get all the mShootList where angleDecayType equals to UPDATED_ANGLE_DECAY_TYPE
        defaultMShootShouldNotBeFound("angleDecayType.in=" + UPDATED_ANGLE_DECAY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMShootsByAngleDecayTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where angleDecayType is not null
        defaultMShootShouldBeFound("angleDecayType.specified=true");

        // Get all the mShootList where angleDecayType is null
        defaultMShootShouldNotBeFound("angleDecayType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMShootsByAngleDecayTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where angleDecayType greater than or equals to DEFAULT_ANGLE_DECAY_TYPE
        defaultMShootShouldBeFound("angleDecayType.greaterOrEqualThan=" + DEFAULT_ANGLE_DECAY_TYPE);

        // Get all the mShootList where angleDecayType greater than or equals to UPDATED_ANGLE_DECAY_TYPE
        defaultMShootShouldNotBeFound("angleDecayType.greaterOrEqualThan=" + UPDATED_ANGLE_DECAY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMShootsByAngleDecayTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where angleDecayType less than or equals to DEFAULT_ANGLE_DECAY_TYPE
        defaultMShootShouldNotBeFound("angleDecayType.lessThan=" + DEFAULT_ANGLE_DECAY_TYPE);

        // Get all the mShootList where angleDecayType less than or equals to UPDATED_ANGLE_DECAY_TYPE
        defaultMShootShouldBeFound("angleDecayType.lessThan=" + UPDATED_ANGLE_DECAY_TYPE);
    }


    @Test
    @Transactional
    public void getAllMShootsByShootOrbitIsEqualToSomething() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where shootOrbit equals to DEFAULT_SHOOT_ORBIT
        defaultMShootShouldBeFound("shootOrbit.equals=" + DEFAULT_SHOOT_ORBIT);

        // Get all the mShootList where shootOrbit equals to UPDATED_SHOOT_ORBIT
        defaultMShootShouldNotBeFound("shootOrbit.equals=" + UPDATED_SHOOT_ORBIT);
    }

    @Test
    @Transactional
    public void getAllMShootsByShootOrbitIsInShouldWork() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where shootOrbit in DEFAULT_SHOOT_ORBIT or UPDATED_SHOOT_ORBIT
        defaultMShootShouldBeFound("shootOrbit.in=" + DEFAULT_SHOOT_ORBIT + "," + UPDATED_SHOOT_ORBIT);

        // Get all the mShootList where shootOrbit equals to UPDATED_SHOOT_ORBIT
        defaultMShootShouldNotBeFound("shootOrbit.in=" + UPDATED_SHOOT_ORBIT);
    }

    @Test
    @Transactional
    public void getAllMShootsByShootOrbitIsNullOrNotNull() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where shootOrbit is not null
        defaultMShootShouldBeFound("shootOrbit.specified=true");

        // Get all the mShootList where shootOrbit is null
        defaultMShootShouldNotBeFound("shootOrbit.specified=false");
    }

    @Test
    @Transactional
    public void getAllMShootsByShootOrbitIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where shootOrbit greater than or equals to DEFAULT_SHOOT_ORBIT
        defaultMShootShouldBeFound("shootOrbit.greaterOrEqualThan=" + DEFAULT_SHOOT_ORBIT);

        // Get all the mShootList where shootOrbit greater than or equals to UPDATED_SHOOT_ORBIT
        defaultMShootShouldNotBeFound("shootOrbit.greaterOrEqualThan=" + UPDATED_SHOOT_ORBIT);
    }

    @Test
    @Transactional
    public void getAllMShootsByShootOrbitIsLessThanSomething() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where shootOrbit less than or equals to DEFAULT_SHOOT_ORBIT
        defaultMShootShouldNotBeFound("shootOrbit.lessThan=" + DEFAULT_SHOOT_ORBIT);

        // Get all the mShootList where shootOrbit less than or equals to UPDATED_SHOOT_ORBIT
        defaultMShootShouldBeFound("shootOrbit.lessThan=" + UPDATED_SHOOT_ORBIT);
    }


    @Test
    @Transactional
    public void getAllMShootsByJudgementIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where judgementId equals to DEFAULT_JUDGEMENT_ID
        defaultMShootShouldBeFound("judgementId.equals=" + DEFAULT_JUDGEMENT_ID);

        // Get all the mShootList where judgementId equals to UPDATED_JUDGEMENT_ID
        defaultMShootShouldNotBeFound("judgementId.equals=" + UPDATED_JUDGEMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMShootsByJudgementIdIsInShouldWork() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where judgementId in DEFAULT_JUDGEMENT_ID or UPDATED_JUDGEMENT_ID
        defaultMShootShouldBeFound("judgementId.in=" + DEFAULT_JUDGEMENT_ID + "," + UPDATED_JUDGEMENT_ID);

        // Get all the mShootList where judgementId equals to UPDATED_JUDGEMENT_ID
        defaultMShootShouldNotBeFound("judgementId.in=" + UPDATED_JUDGEMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMShootsByJudgementIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where judgementId is not null
        defaultMShootShouldBeFound("judgementId.specified=true");

        // Get all the mShootList where judgementId is null
        defaultMShootShouldNotBeFound("judgementId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMShootsByJudgementIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where judgementId greater than or equals to DEFAULT_JUDGEMENT_ID
        defaultMShootShouldBeFound("judgementId.greaterOrEqualThan=" + DEFAULT_JUDGEMENT_ID);

        // Get all the mShootList where judgementId greater than or equals to UPDATED_JUDGEMENT_ID
        defaultMShootShouldNotBeFound("judgementId.greaterOrEqualThan=" + UPDATED_JUDGEMENT_ID);
    }

    @Test
    @Transactional
    public void getAllMShootsByJudgementIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        // Get all the mShootList where judgementId less than or equals to DEFAULT_JUDGEMENT_ID
        defaultMShootShouldNotBeFound("judgementId.lessThan=" + DEFAULT_JUDGEMENT_ID);

        // Get all the mShootList where judgementId less than or equals to UPDATED_JUDGEMENT_ID
        defaultMShootShouldBeFound("judgementId.lessThan=" + UPDATED_JUDGEMENT_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMShootShouldBeFound(String filter) throws Exception {
        restMShootMockMvc.perform(get("/api/m-shoots?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mShoot.getId().intValue())))
            .andExpect(jsonPath("$.[*].angleDecayType").value(hasItem(DEFAULT_ANGLE_DECAY_TYPE)))
            .andExpect(jsonPath("$.[*].shootOrbit").value(hasItem(DEFAULT_SHOOT_ORBIT)))
            .andExpect(jsonPath("$.[*].judgementId").value(hasItem(DEFAULT_JUDGEMENT_ID)));

        // Check, that the count call also returns 1
        restMShootMockMvc.perform(get("/api/m-shoots/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMShootShouldNotBeFound(String filter) throws Exception {
        restMShootMockMvc.perform(get("/api/m-shoots?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMShootMockMvc.perform(get("/api/m-shoots/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMShoot() throws Exception {
        // Get the mShoot
        restMShootMockMvc.perform(get("/api/m-shoots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMShoot() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        int databaseSizeBeforeUpdate = mShootRepository.findAll().size();

        // Update the mShoot
        MShoot updatedMShoot = mShootRepository.findById(mShoot.getId()).get();
        // Disconnect from session so that the updates on updatedMShoot are not directly saved in db
        em.detach(updatedMShoot);
        updatedMShoot
            .angleDecayType(UPDATED_ANGLE_DECAY_TYPE)
            .shootOrbit(UPDATED_SHOOT_ORBIT)
            .judgementId(UPDATED_JUDGEMENT_ID);
        MShootDTO mShootDTO = mShootMapper.toDto(updatedMShoot);

        restMShootMockMvc.perform(put("/api/m-shoots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mShootDTO)))
            .andExpect(status().isOk());

        // Validate the MShoot in the database
        List<MShoot> mShootList = mShootRepository.findAll();
        assertThat(mShootList).hasSize(databaseSizeBeforeUpdate);
        MShoot testMShoot = mShootList.get(mShootList.size() - 1);
        assertThat(testMShoot.getAngleDecayType()).isEqualTo(UPDATED_ANGLE_DECAY_TYPE);
        assertThat(testMShoot.getShootOrbit()).isEqualTo(UPDATED_SHOOT_ORBIT);
        assertThat(testMShoot.getJudgementId()).isEqualTo(UPDATED_JUDGEMENT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMShoot() throws Exception {
        int databaseSizeBeforeUpdate = mShootRepository.findAll().size();

        // Create the MShoot
        MShootDTO mShootDTO = mShootMapper.toDto(mShoot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMShootMockMvc.perform(put("/api/m-shoots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mShootDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MShoot in the database
        List<MShoot> mShootList = mShootRepository.findAll();
        assertThat(mShootList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMShoot() throws Exception {
        // Initialize the database
        mShootRepository.saveAndFlush(mShoot);

        int databaseSizeBeforeDelete = mShootRepository.findAll().size();

        // Delete the mShoot
        restMShootMockMvc.perform(delete("/api/m-shoots/{id}", mShoot.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MShoot> mShootList = mShootRepository.findAll();
        assertThat(mShootList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MShoot.class);
        MShoot mShoot1 = new MShoot();
        mShoot1.setId(1L);
        MShoot mShoot2 = new MShoot();
        mShoot2.setId(mShoot1.getId());
        assertThat(mShoot1).isEqualTo(mShoot2);
        mShoot2.setId(2L);
        assertThat(mShoot1).isNotEqualTo(mShoot2);
        mShoot1.setId(null);
        assertThat(mShoot1).isNotEqualTo(mShoot2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MShootDTO.class);
        MShootDTO mShootDTO1 = new MShootDTO();
        mShootDTO1.setId(1L);
        MShootDTO mShootDTO2 = new MShootDTO();
        assertThat(mShootDTO1).isNotEqualTo(mShootDTO2);
        mShootDTO2.setId(mShootDTO1.getId());
        assertThat(mShootDTO1).isEqualTo(mShootDTO2);
        mShootDTO2.setId(2L);
        assertThat(mShootDTO1).isNotEqualTo(mShootDTO2);
        mShootDTO1.setId(null);
        assertThat(mShootDTO1).isNotEqualTo(mShootDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mShootMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mShootMapper.fromId(null)).isNull();
    }
}
