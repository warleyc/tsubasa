package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDistributeCardParameterStep;
import io.shm.tsubasa.repository.MDistributeCardParameterStepRepository;
import io.shm.tsubasa.service.MDistributeCardParameterStepService;
import io.shm.tsubasa.service.dto.MDistributeCardParameterStepDTO;
import io.shm.tsubasa.service.mapper.MDistributeCardParameterStepMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDistributeCardParameterStepCriteria;
import io.shm.tsubasa.service.MDistributeCardParameterStepQueryService;

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
 * Integration tests for the {@Link MDistributeCardParameterStepResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDistributeCardParameterStepResourceIT {

    private static final Integer DEFAULT_IS_GK = 1;
    private static final Integer UPDATED_IS_GK = 2;

    private static final Integer DEFAULT_STEP = 1;
    private static final Integer UPDATED_STEP = 2;

    private static final Integer DEFAULT_PARAM = 1;
    private static final Integer UPDATED_PARAM = 2;

    @Autowired
    private MDistributeCardParameterStepRepository mDistributeCardParameterStepRepository;

    @Autowired
    private MDistributeCardParameterStepMapper mDistributeCardParameterStepMapper;

    @Autowired
    private MDistributeCardParameterStepService mDistributeCardParameterStepService;

    @Autowired
    private MDistributeCardParameterStepQueryService mDistributeCardParameterStepQueryService;

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

    private MockMvc restMDistributeCardParameterStepMockMvc;

    private MDistributeCardParameterStep mDistributeCardParameterStep;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDistributeCardParameterStepResource mDistributeCardParameterStepResource = new MDistributeCardParameterStepResource(mDistributeCardParameterStepService, mDistributeCardParameterStepQueryService);
        this.restMDistributeCardParameterStepMockMvc = MockMvcBuilders.standaloneSetup(mDistributeCardParameterStepResource)
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
    public static MDistributeCardParameterStep createEntity(EntityManager em) {
        MDistributeCardParameterStep mDistributeCardParameterStep = new MDistributeCardParameterStep()
            .isGk(DEFAULT_IS_GK)
            .step(DEFAULT_STEP)
            .param(DEFAULT_PARAM);
        return mDistributeCardParameterStep;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDistributeCardParameterStep createUpdatedEntity(EntityManager em) {
        MDistributeCardParameterStep mDistributeCardParameterStep = new MDistributeCardParameterStep()
            .isGk(UPDATED_IS_GK)
            .step(UPDATED_STEP)
            .param(UPDATED_PARAM);
        return mDistributeCardParameterStep;
    }

    @BeforeEach
    public void initTest() {
        mDistributeCardParameterStep = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDistributeCardParameterStep() throws Exception {
        int databaseSizeBeforeCreate = mDistributeCardParameterStepRepository.findAll().size();

        // Create the MDistributeCardParameterStep
        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO = mDistributeCardParameterStepMapper.toDto(mDistributeCardParameterStep);
        restMDistributeCardParameterStepMockMvc.perform(post("/api/m-distribute-card-parameter-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterStepDTO)))
            .andExpect(status().isCreated());

        // Validate the MDistributeCardParameterStep in the database
        List<MDistributeCardParameterStep> mDistributeCardParameterStepList = mDistributeCardParameterStepRepository.findAll();
        assertThat(mDistributeCardParameterStepList).hasSize(databaseSizeBeforeCreate + 1);
        MDistributeCardParameterStep testMDistributeCardParameterStep = mDistributeCardParameterStepList.get(mDistributeCardParameterStepList.size() - 1);
        assertThat(testMDistributeCardParameterStep.getIsGk()).isEqualTo(DEFAULT_IS_GK);
        assertThat(testMDistributeCardParameterStep.getStep()).isEqualTo(DEFAULT_STEP);
        assertThat(testMDistributeCardParameterStep.getParam()).isEqualTo(DEFAULT_PARAM);
    }

    @Test
    @Transactional
    public void createMDistributeCardParameterStepWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDistributeCardParameterStepRepository.findAll().size();

        // Create the MDistributeCardParameterStep with an existing ID
        mDistributeCardParameterStep.setId(1L);
        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO = mDistributeCardParameterStepMapper.toDto(mDistributeCardParameterStep);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDistributeCardParameterStepMockMvc.perform(post("/api/m-distribute-card-parameter-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterStepDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDistributeCardParameterStep in the database
        List<MDistributeCardParameterStep> mDistributeCardParameterStepList = mDistributeCardParameterStepRepository.findAll();
        assertThat(mDistributeCardParameterStepList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIsGkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeCardParameterStepRepository.findAll().size();
        // set the field null
        mDistributeCardParameterStep.setIsGk(null);

        // Create the MDistributeCardParameterStep, which fails.
        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO = mDistributeCardParameterStepMapper.toDto(mDistributeCardParameterStep);

        restMDistributeCardParameterStepMockMvc.perform(post("/api/m-distribute-card-parameter-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterStepDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeCardParameterStep> mDistributeCardParameterStepList = mDistributeCardParameterStepRepository.findAll();
        assertThat(mDistributeCardParameterStepList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStepIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeCardParameterStepRepository.findAll().size();
        // set the field null
        mDistributeCardParameterStep.setStep(null);

        // Create the MDistributeCardParameterStep, which fails.
        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO = mDistributeCardParameterStepMapper.toDto(mDistributeCardParameterStep);

        restMDistributeCardParameterStepMockMvc.perform(post("/api/m-distribute-card-parameter-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterStepDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeCardParameterStep> mDistributeCardParameterStepList = mDistributeCardParameterStepRepository.findAll();
        assertThat(mDistributeCardParameterStepList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParamIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeCardParameterStepRepository.findAll().size();
        // set the field null
        mDistributeCardParameterStep.setParam(null);

        // Create the MDistributeCardParameterStep, which fails.
        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO = mDistributeCardParameterStepMapper.toDto(mDistributeCardParameterStep);

        restMDistributeCardParameterStepMockMvc.perform(post("/api/m-distribute-card-parameter-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterStepDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeCardParameterStep> mDistributeCardParameterStepList = mDistributeCardParameterStepRepository.findAll();
        assertThat(mDistributeCardParameterStepList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterSteps() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList
        restMDistributeCardParameterStepMockMvc.perform(get("/api/m-distribute-card-parameter-steps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDistributeCardParameterStep.getId().intValue())))
            .andExpect(jsonPath("$.[*].isGk").value(hasItem(DEFAULT_IS_GK)))
            .andExpect(jsonPath("$.[*].step").value(hasItem(DEFAULT_STEP)))
            .andExpect(jsonPath("$.[*].param").value(hasItem(DEFAULT_PARAM)));
    }
    
    @Test
    @Transactional
    public void getMDistributeCardParameterStep() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get the mDistributeCardParameterStep
        restMDistributeCardParameterStepMockMvc.perform(get("/api/m-distribute-card-parameter-steps/{id}", mDistributeCardParameterStep.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDistributeCardParameterStep.getId().intValue()))
            .andExpect(jsonPath("$.isGk").value(DEFAULT_IS_GK))
            .andExpect(jsonPath("$.step").value(DEFAULT_STEP))
            .andExpect(jsonPath("$.param").value(DEFAULT_PARAM));
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByIsGkIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where isGk equals to DEFAULT_IS_GK
        defaultMDistributeCardParameterStepShouldBeFound("isGk.equals=" + DEFAULT_IS_GK);

        // Get all the mDistributeCardParameterStepList where isGk equals to UPDATED_IS_GK
        defaultMDistributeCardParameterStepShouldNotBeFound("isGk.equals=" + UPDATED_IS_GK);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByIsGkIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where isGk in DEFAULT_IS_GK or UPDATED_IS_GK
        defaultMDistributeCardParameterStepShouldBeFound("isGk.in=" + DEFAULT_IS_GK + "," + UPDATED_IS_GK);

        // Get all the mDistributeCardParameterStepList where isGk equals to UPDATED_IS_GK
        defaultMDistributeCardParameterStepShouldNotBeFound("isGk.in=" + UPDATED_IS_GK);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByIsGkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where isGk is not null
        defaultMDistributeCardParameterStepShouldBeFound("isGk.specified=true");

        // Get all the mDistributeCardParameterStepList where isGk is null
        defaultMDistributeCardParameterStepShouldNotBeFound("isGk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByIsGkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where isGk greater than or equals to DEFAULT_IS_GK
        defaultMDistributeCardParameterStepShouldBeFound("isGk.greaterOrEqualThan=" + DEFAULT_IS_GK);

        // Get all the mDistributeCardParameterStepList where isGk greater than or equals to UPDATED_IS_GK
        defaultMDistributeCardParameterStepShouldNotBeFound("isGk.greaterOrEqualThan=" + UPDATED_IS_GK);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByIsGkIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where isGk less than or equals to DEFAULT_IS_GK
        defaultMDistributeCardParameterStepShouldNotBeFound("isGk.lessThan=" + DEFAULT_IS_GK);

        // Get all the mDistributeCardParameterStepList where isGk less than or equals to UPDATED_IS_GK
        defaultMDistributeCardParameterStepShouldBeFound("isGk.lessThan=" + UPDATED_IS_GK);
    }


    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByStepIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where step equals to DEFAULT_STEP
        defaultMDistributeCardParameterStepShouldBeFound("step.equals=" + DEFAULT_STEP);

        // Get all the mDistributeCardParameterStepList where step equals to UPDATED_STEP
        defaultMDistributeCardParameterStepShouldNotBeFound("step.equals=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByStepIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where step in DEFAULT_STEP or UPDATED_STEP
        defaultMDistributeCardParameterStepShouldBeFound("step.in=" + DEFAULT_STEP + "," + UPDATED_STEP);

        // Get all the mDistributeCardParameterStepList where step equals to UPDATED_STEP
        defaultMDistributeCardParameterStepShouldNotBeFound("step.in=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByStepIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where step is not null
        defaultMDistributeCardParameterStepShouldBeFound("step.specified=true");

        // Get all the mDistributeCardParameterStepList where step is null
        defaultMDistributeCardParameterStepShouldNotBeFound("step.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByStepIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where step greater than or equals to DEFAULT_STEP
        defaultMDistributeCardParameterStepShouldBeFound("step.greaterOrEqualThan=" + DEFAULT_STEP);

        // Get all the mDistributeCardParameterStepList where step greater than or equals to UPDATED_STEP
        defaultMDistributeCardParameterStepShouldNotBeFound("step.greaterOrEqualThan=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByStepIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where step less than or equals to DEFAULT_STEP
        defaultMDistributeCardParameterStepShouldNotBeFound("step.lessThan=" + DEFAULT_STEP);

        // Get all the mDistributeCardParameterStepList where step less than or equals to UPDATED_STEP
        defaultMDistributeCardParameterStepShouldBeFound("step.lessThan=" + UPDATED_STEP);
    }


    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByParamIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where param equals to DEFAULT_PARAM
        defaultMDistributeCardParameterStepShouldBeFound("param.equals=" + DEFAULT_PARAM);

        // Get all the mDistributeCardParameterStepList where param equals to UPDATED_PARAM
        defaultMDistributeCardParameterStepShouldNotBeFound("param.equals=" + UPDATED_PARAM);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByParamIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where param in DEFAULT_PARAM or UPDATED_PARAM
        defaultMDistributeCardParameterStepShouldBeFound("param.in=" + DEFAULT_PARAM + "," + UPDATED_PARAM);

        // Get all the mDistributeCardParameterStepList where param equals to UPDATED_PARAM
        defaultMDistributeCardParameterStepShouldNotBeFound("param.in=" + UPDATED_PARAM);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByParamIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where param is not null
        defaultMDistributeCardParameterStepShouldBeFound("param.specified=true");

        // Get all the mDistributeCardParameterStepList where param is null
        defaultMDistributeCardParameterStepShouldNotBeFound("param.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByParamIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where param greater than or equals to DEFAULT_PARAM
        defaultMDistributeCardParameterStepShouldBeFound("param.greaterOrEqualThan=" + DEFAULT_PARAM);

        // Get all the mDistributeCardParameterStepList where param greater than or equals to UPDATED_PARAM
        defaultMDistributeCardParameterStepShouldNotBeFound("param.greaterOrEqualThan=" + UPDATED_PARAM);
    }

    @Test
    @Transactional
    public void getAllMDistributeCardParameterStepsByParamIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        // Get all the mDistributeCardParameterStepList where param less than or equals to DEFAULT_PARAM
        defaultMDistributeCardParameterStepShouldNotBeFound("param.lessThan=" + DEFAULT_PARAM);

        // Get all the mDistributeCardParameterStepList where param less than or equals to UPDATED_PARAM
        defaultMDistributeCardParameterStepShouldBeFound("param.lessThan=" + UPDATED_PARAM);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDistributeCardParameterStepShouldBeFound(String filter) throws Exception {
        restMDistributeCardParameterStepMockMvc.perform(get("/api/m-distribute-card-parameter-steps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDistributeCardParameterStep.getId().intValue())))
            .andExpect(jsonPath("$.[*].isGk").value(hasItem(DEFAULT_IS_GK)))
            .andExpect(jsonPath("$.[*].step").value(hasItem(DEFAULT_STEP)))
            .andExpect(jsonPath("$.[*].param").value(hasItem(DEFAULT_PARAM)));

        // Check, that the count call also returns 1
        restMDistributeCardParameterStepMockMvc.perform(get("/api/m-distribute-card-parameter-steps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDistributeCardParameterStepShouldNotBeFound(String filter) throws Exception {
        restMDistributeCardParameterStepMockMvc.perform(get("/api/m-distribute-card-parameter-steps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDistributeCardParameterStepMockMvc.perform(get("/api/m-distribute-card-parameter-steps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDistributeCardParameterStep() throws Exception {
        // Get the mDistributeCardParameterStep
        restMDistributeCardParameterStepMockMvc.perform(get("/api/m-distribute-card-parameter-steps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDistributeCardParameterStep() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        int databaseSizeBeforeUpdate = mDistributeCardParameterStepRepository.findAll().size();

        // Update the mDistributeCardParameterStep
        MDistributeCardParameterStep updatedMDistributeCardParameterStep = mDistributeCardParameterStepRepository.findById(mDistributeCardParameterStep.getId()).get();
        // Disconnect from session so that the updates on updatedMDistributeCardParameterStep are not directly saved in db
        em.detach(updatedMDistributeCardParameterStep);
        updatedMDistributeCardParameterStep
            .isGk(UPDATED_IS_GK)
            .step(UPDATED_STEP)
            .param(UPDATED_PARAM);
        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO = mDistributeCardParameterStepMapper.toDto(updatedMDistributeCardParameterStep);

        restMDistributeCardParameterStepMockMvc.perform(put("/api/m-distribute-card-parameter-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterStepDTO)))
            .andExpect(status().isOk());

        // Validate the MDistributeCardParameterStep in the database
        List<MDistributeCardParameterStep> mDistributeCardParameterStepList = mDistributeCardParameterStepRepository.findAll();
        assertThat(mDistributeCardParameterStepList).hasSize(databaseSizeBeforeUpdate);
        MDistributeCardParameterStep testMDistributeCardParameterStep = mDistributeCardParameterStepList.get(mDistributeCardParameterStepList.size() - 1);
        assertThat(testMDistributeCardParameterStep.getIsGk()).isEqualTo(UPDATED_IS_GK);
        assertThat(testMDistributeCardParameterStep.getStep()).isEqualTo(UPDATED_STEP);
        assertThat(testMDistributeCardParameterStep.getParam()).isEqualTo(UPDATED_PARAM);
    }

    @Test
    @Transactional
    public void updateNonExistingMDistributeCardParameterStep() throws Exception {
        int databaseSizeBeforeUpdate = mDistributeCardParameterStepRepository.findAll().size();

        // Create the MDistributeCardParameterStep
        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO = mDistributeCardParameterStepMapper.toDto(mDistributeCardParameterStep);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDistributeCardParameterStepMockMvc.perform(put("/api/m-distribute-card-parameter-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeCardParameterStepDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDistributeCardParameterStep in the database
        List<MDistributeCardParameterStep> mDistributeCardParameterStepList = mDistributeCardParameterStepRepository.findAll();
        assertThat(mDistributeCardParameterStepList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDistributeCardParameterStep() throws Exception {
        // Initialize the database
        mDistributeCardParameterStepRepository.saveAndFlush(mDistributeCardParameterStep);

        int databaseSizeBeforeDelete = mDistributeCardParameterStepRepository.findAll().size();

        // Delete the mDistributeCardParameterStep
        restMDistributeCardParameterStepMockMvc.perform(delete("/api/m-distribute-card-parameter-steps/{id}", mDistributeCardParameterStep.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDistributeCardParameterStep> mDistributeCardParameterStepList = mDistributeCardParameterStepRepository.findAll();
        assertThat(mDistributeCardParameterStepList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDistributeCardParameterStep.class);
        MDistributeCardParameterStep mDistributeCardParameterStep1 = new MDistributeCardParameterStep();
        mDistributeCardParameterStep1.setId(1L);
        MDistributeCardParameterStep mDistributeCardParameterStep2 = new MDistributeCardParameterStep();
        mDistributeCardParameterStep2.setId(mDistributeCardParameterStep1.getId());
        assertThat(mDistributeCardParameterStep1).isEqualTo(mDistributeCardParameterStep2);
        mDistributeCardParameterStep2.setId(2L);
        assertThat(mDistributeCardParameterStep1).isNotEqualTo(mDistributeCardParameterStep2);
        mDistributeCardParameterStep1.setId(null);
        assertThat(mDistributeCardParameterStep1).isNotEqualTo(mDistributeCardParameterStep2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDistributeCardParameterStepDTO.class);
        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO1 = new MDistributeCardParameterStepDTO();
        mDistributeCardParameterStepDTO1.setId(1L);
        MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO2 = new MDistributeCardParameterStepDTO();
        assertThat(mDistributeCardParameterStepDTO1).isNotEqualTo(mDistributeCardParameterStepDTO2);
        mDistributeCardParameterStepDTO2.setId(mDistributeCardParameterStepDTO1.getId());
        assertThat(mDistributeCardParameterStepDTO1).isEqualTo(mDistributeCardParameterStepDTO2);
        mDistributeCardParameterStepDTO2.setId(2L);
        assertThat(mDistributeCardParameterStepDTO1).isNotEqualTo(mDistributeCardParameterStepDTO2);
        mDistributeCardParameterStepDTO1.setId(null);
        assertThat(mDistributeCardParameterStepDTO1).isNotEqualTo(mDistributeCardParameterStepDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDistributeCardParameterStepMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDistributeCardParameterStepMapper.fromId(null)).isNull();
    }
}
