package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionTrajectory;
import io.shm.tsubasa.repository.MGachaRenditionTrajectoryRepository;
import io.shm.tsubasa.service.MGachaRenditionTrajectoryService;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTrajectoryMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryCriteria;
import io.shm.tsubasa.service.MGachaRenditionTrajectoryQueryService;

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
 * Integration tests for the {@Link MGachaRenditionTrajectoryResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionTrajectoryResourceIT {

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Integer DEFAULT_TRAJECTORY_TYPE = 1;
    private static final Integer UPDATED_TRAJECTORY_TYPE = 2;

    @Autowired
    private MGachaRenditionTrajectoryRepository mGachaRenditionTrajectoryRepository;

    @Autowired
    private MGachaRenditionTrajectoryMapper mGachaRenditionTrajectoryMapper;

    @Autowired
    private MGachaRenditionTrajectoryService mGachaRenditionTrajectoryService;

    @Autowired
    private MGachaRenditionTrajectoryQueryService mGachaRenditionTrajectoryQueryService;

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

    private MockMvc restMGachaRenditionTrajectoryMockMvc;

    private MGachaRenditionTrajectory mGachaRenditionTrajectory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionTrajectoryResource mGachaRenditionTrajectoryResource = new MGachaRenditionTrajectoryResource(mGachaRenditionTrajectoryService, mGachaRenditionTrajectoryQueryService);
        this.restMGachaRenditionTrajectoryMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionTrajectoryResource)
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
    public static MGachaRenditionTrajectory createEntity(EntityManager em) {
        MGachaRenditionTrajectory mGachaRenditionTrajectory = new MGachaRenditionTrajectory()
            .weight(DEFAULT_WEIGHT)
            .trajectoryType(DEFAULT_TRAJECTORY_TYPE);
        return mGachaRenditionTrajectory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionTrajectory createUpdatedEntity(EntityManager em) {
        MGachaRenditionTrajectory mGachaRenditionTrajectory = new MGachaRenditionTrajectory()
            .weight(UPDATED_WEIGHT)
            .trajectoryType(UPDATED_TRAJECTORY_TYPE);
        return mGachaRenditionTrajectory;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionTrajectory = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionTrajectory() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionTrajectoryRepository.findAll().size();

        // Create the MGachaRenditionTrajectory
        MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO = mGachaRenditionTrajectoryMapper.toDto(mGachaRenditionTrajectory);
        restMGachaRenditionTrajectoryMockMvc.perform(post("/api/m-gacha-rendition-trajectories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionTrajectory in the database
        List<MGachaRenditionTrajectory> mGachaRenditionTrajectoryList = mGachaRenditionTrajectoryRepository.findAll();
        assertThat(mGachaRenditionTrajectoryList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionTrajectory testMGachaRenditionTrajectory = mGachaRenditionTrajectoryList.get(mGachaRenditionTrajectoryList.size() - 1);
        assertThat(testMGachaRenditionTrajectory.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionTrajectory.getTrajectoryType()).isEqualTo(DEFAULT_TRAJECTORY_TYPE);
    }

    @Test
    @Transactional
    public void createMGachaRenditionTrajectoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionTrajectoryRepository.findAll().size();

        // Create the MGachaRenditionTrajectory with an existing ID
        mGachaRenditionTrajectory.setId(1L);
        MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO = mGachaRenditionTrajectoryMapper.toDto(mGachaRenditionTrajectory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionTrajectoryMockMvc.perform(post("/api/m-gacha-rendition-trajectories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionTrajectory in the database
        List<MGachaRenditionTrajectory> mGachaRenditionTrajectoryList = mGachaRenditionTrajectoryRepository.findAll();
        assertThat(mGachaRenditionTrajectoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTrajectoryRepository.findAll().size();
        // set the field null
        mGachaRenditionTrajectory.setWeight(null);

        // Create the MGachaRenditionTrajectory, which fails.
        MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO = mGachaRenditionTrajectoryMapper.toDto(mGachaRenditionTrajectory);

        restMGachaRenditionTrajectoryMockMvc.perform(post("/api/m-gacha-rendition-trajectories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTrajectory> mGachaRenditionTrajectoryList = mGachaRenditionTrajectoryRepository.findAll();
        assertThat(mGachaRenditionTrajectoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrajectoryTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTrajectoryRepository.findAll().size();
        // set the field null
        mGachaRenditionTrajectory.setTrajectoryType(null);

        // Create the MGachaRenditionTrajectory, which fails.
        MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO = mGachaRenditionTrajectoryMapper.toDto(mGachaRenditionTrajectory);

        restMGachaRenditionTrajectoryMockMvc.perform(post("/api/m-gacha-rendition-trajectories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTrajectory> mGachaRenditionTrajectoryList = mGachaRenditionTrajectoryRepository.findAll();
        assertThat(mGachaRenditionTrajectoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectories() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList
        restMGachaRenditionTrajectoryMockMvc.perform(get("/api/m-gacha-rendition-trajectories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionTrajectory.getId().intValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].trajectoryType").value(hasItem(DEFAULT_TRAJECTORY_TYPE)));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionTrajectory() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get the mGachaRenditionTrajectory
        restMGachaRenditionTrajectoryMockMvc.perform(get("/api/m-gacha-rendition-trajectories/{id}", mGachaRenditionTrajectory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionTrajectory.getId().intValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.trajectoryType").value(DEFAULT_TRAJECTORY_TYPE));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTrajectoryShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTrajectoryList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionTrajectoryList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where weight is not null
        defaultMGachaRenditionTrajectoryShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionTrajectoryList where weight is null
        defaultMGachaRenditionTrajectoryShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTrajectoryShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTrajectoryList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTrajectoryShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTrajectoryList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionTrajectoryShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByTrajectoryTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where trajectoryType equals to DEFAULT_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryShouldBeFound("trajectoryType.equals=" + DEFAULT_TRAJECTORY_TYPE);

        // Get all the mGachaRenditionTrajectoryList where trajectoryType equals to UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryShouldNotBeFound("trajectoryType.equals=" + UPDATED_TRAJECTORY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByTrajectoryTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where trajectoryType in DEFAULT_TRAJECTORY_TYPE or UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryShouldBeFound("trajectoryType.in=" + DEFAULT_TRAJECTORY_TYPE + "," + UPDATED_TRAJECTORY_TYPE);

        // Get all the mGachaRenditionTrajectoryList where trajectoryType equals to UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryShouldNotBeFound("trajectoryType.in=" + UPDATED_TRAJECTORY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByTrajectoryTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where trajectoryType is not null
        defaultMGachaRenditionTrajectoryShouldBeFound("trajectoryType.specified=true");

        // Get all the mGachaRenditionTrajectoryList where trajectoryType is null
        defaultMGachaRenditionTrajectoryShouldNotBeFound("trajectoryType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByTrajectoryTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where trajectoryType greater than or equals to DEFAULT_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryShouldBeFound("trajectoryType.greaterOrEqualThan=" + DEFAULT_TRAJECTORY_TYPE);

        // Get all the mGachaRenditionTrajectoryList where trajectoryType greater than or equals to UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryShouldNotBeFound("trajectoryType.greaterOrEqualThan=" + UPDATED_TRAJECTORY_TYPE);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTrajectoriesByTrajectoryTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        // Get all the mGachaRenditionTrajectoryList where trajectoryType less than or equals to DEFAULT_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryShouldNotBeFound("trajectoryType.lessThan=" + DEFAULT_TRAJECTORY_TYPE);

        // Get all the mGachaRenditionTrajectoryList where trajectoryType less than or equals to UPDATED_TRAJECTORY_TYPE
        defaultMGachaRenditionTrajectoryShouldBeFound("trajectoryType.lessThan=" + UPDATED_TRAJECTORY_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionTrajectoryShouldBeFound(String filter) throws Exception {
        restMGachaRenditionTrajectoryMockMvc.perform(get("/api/m-gacha-rendition-trajectories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionTrajectory.getId().intValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].trajectoryType").value(hasItem(DEFAULT_TRAJECTORY_TYPE)));

        // Check, that the count call also returns 1
        restMGachaRenditionTrajectoryMockMvc.perform(get("/api/m-gacha-rendition-trajectories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionTrajectoryShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionTrajectoryMockMvc.perform(get("/api/m-gacha-rendition-trajectories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionTrajectoryMockMvc.perform(get("/api/m-gacha-rendition-trajectories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionTrajectory() throws Exception {
        // Get the mGachaRenditionTrajectory
        restMGachaRenditionTrajectoryMockMvc.perform(get("/api/m-gacha-rendition-trajectories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionTrajectory() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        int databaseSizeBeforeUpdate = mGachaRenditionTrajectoryRepository.findAll().size();

        // Update the mGachaRenditionTrajectory
        MGachaRenditionTrajectory updatedMGachaRenditionTrajectory = mGachaRenditionTrajectoryRepository.findById(mGachaRenditionTrajectory.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionTrajectory are not directly saved in db
        em.detach(updatedMGachaRenditionTrajectory);
        updatedMGachaRenditionTrajectory
            .weight(UPDATED_WEIGHT)
            .trajectoryType(UPDATED_TRAJECTORY_TYPE);
        MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO = mGachaRenditionTrajectoryMapper.toDto(updatedMGachaRenditionTrajectory);

        restMGachaRenditionTrajectoryMockMvc.perform(put("/api/m-gacha-rendition-trajectories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionTrajectory in the database
        List<MGachaRenditionTrajectory> mGachaRenditionTrajectoryList = mGachaRenditionTrajectoryRepository.findAll();
        assertThat(mGachaRenditionTrajectoryList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionTrajectory testMGachaRenditionTrajectory = mGachaRenditionTrajectoryList.get(mGachaRenditionTrajectoryList.size() - 1);
        assertThat(testMGachaRenditionTrajectory.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionTrajectory.getTrajectoryType()).isEqualTo(UPDATED_TRAJECTORY_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionTrajectory() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionTrajectoryRepository.findAll().size();

        // Create the MGachaRenditionTrajectory
        MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO = mGachaRenditionTrajectoryMapper.toDto(mGachaRenditionTrajectory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionTrajectoryMockMvc.perform(put("/api/m-gacha-rendition-trajectories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTrajectoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionTrajectory in the database
        List<MGachaRenditionTrajectory> mGachaRenditionTrajectoryList = mGachaRenditionTrajectoryRepository.findAll();
        assertThat(mGachaRenditionTrajectoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionTrajectory() throws Exception {
        // Initialize the database
        mGachaRenditionTrajectoryRepository.saveAndFlush(mGachaRenditionTrajectory);

        int databaseSizeBeforeDelete = mGachaRenditionTrajectoryRepository.findAll().size();

        // Delete the mGachaRenditionTrajectory
        restMGachaRenditionTrajectoryMockMvc.perform(delete("/api/m-gacha-rendition-trajectories/{id}", mGachaRenditionTrajectory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionTrajectory> mGachaRenditionTrajectoryList = mGachaRenditionTrajectoryRepository.findAll();
        assertThat(mGachaRenditionTrajectoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionTrajectory.class);
        MGachaRenditionTrajectory mGachaRenditionTrajectory1 = new MGachaRenditionTrajectory();
        mGachaRenditionTrajectory1.setId(1L);
        MGachaRenditionTrajectory mGachaRenditionTrajectory2 = new MGachaRenditionTrajectory();
        mGachaRenditionTrajectory2.setId(mGachaRenditionTrajectory1.getId());
        assertThat(mGachaRenditionTrajectory1).isEqualTo(mGachaRenditionTrajectory2);
        mGachaRenditionTrajectory2.setId(2L);
        assertThat(mGachaRenditionTrajectory1).isNotEqualTo(mGachaRenditionTrajectory2);
        mGachaRenditionTrajectory1.setId(null);
        assertThat(mGachaRenditionTrajectory1).isNotEqualTo(mGachaRenditionTrajectory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionTrajectoryDTO.class);
        MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO1 = new MGachaRenditionTrajectoryDTO();
        mGachaRenditionTrajectoryDTO1.setId(1L);
        MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO2 = new MGachaRenditionTrajectoryDTO();
        assertThat(mGachaRenditionTrajectoryDTO1).isNotEqualTo(mGachaRenditionTrajectoryDTO2);
        mGachaRenditionTrajectoryDTO2.setId(mGachaRenditionTrajectoryDTO1.getId());
        assertThat(mGachaRenditionTrajectoryDTO1).isEqualTo(mGachaRenditionTrajectoryDTO2);
        mGachaRenditionTrajectoryDTO2.setId(2L);
        assertThat(mGachaRenditionTrajectoryDTO1).isNotEqualTo(mGachaRenditionTrajectoryDTO2);
        mGachaRenditionTrajectoryDTO1.setId(null);
        assertThat(mGachaRenditionTrajectoryDTO1).isNotEqualTo(mGachaRenditionTrajectoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionTrajectoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionTrajectoryMapper.fromId(null)).isNull();
    }
}
