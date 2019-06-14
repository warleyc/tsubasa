package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MStageStory;
import io.shm.tsubasa.repository.MStageStoryRepository;
import io.shm.tsubasa.service.MStageStoryService;
import io.shm.tsubasa.service.dto.MStageStoryDTO;
import io.shm.tsubasa.service.mapper.MStageStoryMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MStageStoryCriteria;
import io.shm.tsubasa.service.MStageStoryQueryService;

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
 * Integration tests for the {@Link MStageStoryResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MStageStoryResourceIT {

    private static final Integer DEFAULT_STAGE_ID = 1;
    private static final Integer UPDATED_STAGE_ID = 2;

    private static final Integer DEFAULT_EVENT_TYPE = 1;
    private static final Integer UPDATED_EVENT_TYPE = 2;

    private static final String DEFAULT_MAIN_STORY_ASSET = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_STORY_ASSET = "BBBBBBBBBB";

    private static final String DEFAULT_KICKOFF_STORY_ASSET = "AAAAAAAAAA";
    private static final String UPDATED_KICKOFF_STORY_ASSET = "BBBBBBBBBB";

    private static final String DEFAULT_HALFTIME_STORY_ASSET = "AAAAAAAAAA";
    private static final String UPDATED_HALFTIME_STORY_ASSET = "BBBBBBBBBB";

    private static final String DEFAULT_RESULT_STORY_ASSET = "AAAAAAAAAA";
    private static final String UPDATED_RESULT_STORY_ASSET = "BBBBBBBBBB";

    @Autowired
    private MStageStoryRepository mStageStoryRepository;

    @Autowired
    private MStageStoryMapper mStageStoryMapper;

    @Autowired
    private MStageStoryService mStageStoryService;

    @Autowired
    private MStageStoryQueryService mStageStoryQueryService;

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

    private MockMvc restMStageStoryMockMvc;

    private MStageStory mStageStory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MStageStoryResource mStageStoryResource = new MStageStoryResource(mStageStoryService, mStageStoryQueryService);
        this.restMStageStoryMockMvc = MockMvcBuilders.standaloneSetup(mStageStoryResource)
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
    public static MStageStory createEntity(EntityManager em) {
        MStageStory mStageStory = new MStageStory()
            .stageId(DEFAULT_STAGE_ID)
            .eventType(DEFAULT_EVENT_TYPE)
            .mainStoryAsset(DEFAULT_MAIN_STORY_ASSET)
            .kickoffStoryAsset(DEFAULT_KICKOFF_STORY_ASSET)
            .halftimeStoryAsset(DEFAULT_HALFTIME_STORY_ASSET)
            .resultStoryAsset(DEFAULT_RESULT_STORY_ASSET);
        return mStageStory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MStageStory createUpdatedEntity(EntityManager em) {
        MStageStory mStageStory = new MStageStory()
            .stageId(UPDATED_STAGE_ID)
            .eventType(UPDATED_EVENT_TYPE)
            .mainStoryAsset(UPDATED_MAIN_STORY_ASSET)
            .kickoffStoryAsset(UPDATED_KICKOFF_STORY_ASSET)
            .halftimeStoryAsset(UPDATED_HALFTIME_STORY_ASSET)
            .resultStoryAsset(UPDATED_RESULT_STORY_ASSET);
        return mStageStory;
    }

    @BeforeEach
    public void initTest() {
        mStageStory = createEntity(em);
    }

    @Test
    @Transactional
    public void createMStageStory() throws Exception {
        int databaseSizeBeforeCreate = mStageStoryRepository.findAll().size();

        // Create the MStageStory
        MStageStoryDTO mStageStoryDTO = mStageStoryMapper.toDto(mStageStory);
        restMStageStoryMockMvc.perform(post("/api/m-stage-stories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStageStoryDTO)))
            .andExpect(status().isCreated());

        // Validate the MStageStory in the database
        List<MStageStory> mStageStoryList = mStageStoryRepository.findAll();
        assertThat(mStageStoryList).hasSize(databaseSizeBeforeCreate + 1);
        MStageStory testMStageStory = mStageStoryList.get(mStageStoryList.size() - 1);
        assertThat(testMStageStory.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
        assertThat(testMStageStory.getEventType()).isEqualTo(DEFAULT_EVENT_TYPE);
        assertThat(testMStageStory.getMainStoryAsset()).isEqualTo(DEFAULT_MAIN_STORY_ASSET);
        assertThat(testMStageStory.getKickoffStoryAsset()).isEqualTo(DEFAULT_KICKOFF_STORY_ASSET);
        assertThat(testMStageStory.getHalftimeStoryAsset()).isEqualTo(DEFAULT_HALFTIME_STORY_ASSET);
        assertThat(testMStageStory.getResultStoryAsset()).isEqualTo(DEFAULT_RESULT_STORY_ASSET);
    }

    @Test
    @Transactional
    public void createMStageStoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mStageStoryRepository.findAll().size();

        // Create the MStageStory with an existing ID
        mStageStory.setId(1L);
        MStageStoryDTO mStageStoryDTO = mStageStoryMapper.toDto(mStageStory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMStageStoryMockMvc.perform(post("/api/m-stage-stories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStageStoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MStageStory in the database
        List<MStageStory> mStageStoryList = mStageStoryRepository.findAll();
        assertThat(mStageStoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStageIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mStageStoryRepository.findAll().size();
        // set the field null
        mStageStory.setStageId(null);

        // Create the MStageStory, which fails.
        MStageStoryDTO mStageStoryDTO = mStageStoryMapper.toDto(mStageStory);

        restMStageStoryMockMvc.perform(post("/api/m-stage-stories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStageStoryDTO)))
            .andExpect(status().isBadRequest());

        List<MStageStory> mStageStoryList = mStageStoryRepository.findAll();
        assertThat(mStageStoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEventTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mStageStoryRepository.findAll().size();
        // set the field null
        mStageStory.setEventType(null);

        // Create the MStageStory, which fails.
        MStageStoryDTO mStageStoryDTO = mStageStoryMapper.toDto(mStageStory);

        restMStageStoryMockMvc.perform(post("/api/m-stage-stories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStageStoryDTO)))
            .andExpect(status().isBadRequest());

        List<MStageStory> mStageStoryList = mStageStoryRepository.findAll();
        assertThat(mStageStoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMStageStories() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList
        restMStageStoryMockMvc.perform(get("/api/m-stage-stories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mStageStory.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].eventType").value(hasItem(DEFAULT_EVENT_TYPE)))
            .andExpect(jsonPath("$.[*].mainStoryAsset").value(hasItem(DEFAULT_MAIN_STORY_ASSET.toString())))
            .andExpect(jsonPath("$.[*].kickoffStoryAsset").value(hasItem(DEFAULT_KICKOFF_STORY_ASSET.toString())))
            .andExpect(jsonPath("$.[*].halftimeStoryAsset").value(hasItem(DEFAULT_HALFTIME_STORY_ASSET.toString())))
            .andExpect(jsonPath("$.[*].resultStoryAsset").value(hasItem(DEFAULT_RESULT_STORY_ASSET.toString())));
    }
    
    @Test
    @Transactional
    public void getMStageStory() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get the mStageStory
        restMStageStoryMockMvc.perform(get("/api/m-stage-stories/{id}", mStageStory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mStageStory.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID))
            .andExpect(jsonPath("$.eventType").value(DEFAULT_EVENT_TYPE))
            .andExpect(jsonPath("$.mainStoryAsset").value(DEFAULT_MAIN_STORY_ASSET.toString()))
            .andExpect(jsonPath("$.kickoffStoryAsset").value(DEFAULT_KICKOFF_STORY_ASSET.toString()))
            .andExpect(jsonPath("$.halftimeStoryAsset").value(DEFAULT_HALFTIME_STORY_ASSET.toString()))
            .andExpect(jsonPath("$.resultStoryAsset").value(DEFAULT_RESULT_STORY_ASSET.toString()));
    }

    @Test
    @Transactional
    public void getAllMStageStoriesByStageIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where stageId equals to DEFAULT_STAGE_ID
        defaultMStageStoryShouldBeFound("stageId.equals=" + DEFAULT_STAGE_ID);

        // Get all the mStageStoryList where stageId equals to UPDATED_STAGE_ID
        defaultMStageStoryShouldNotBeFound("stageId.equals=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMStageStoriesByStageIdIsInShouldWork() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where stageId in DEFAULT_STAGE_ID or UPDATED_STAGE_ID
        defaultMStageStoryShouldBeFound("stageId.in=" + DEFAULT_STAGE_ID + "," + UPDATED_STAGE_ID);

        // Get all the mStageStoryList where stageId equals to UPDATED_STAGE_ID
        defaultMStageStoryShouldNotBeFound("stageId.in=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMStageStoriesByStageIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where stageId is not null
        defaultMStageStoryShouldBeFound("stageId.specified=true");

        // Get all the mStageStoryList where stageId is null
        defaultMStageStoryShouldNotBeFound("stageId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMStageStoriesByStageIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where stageId greater than or equals to DEFAULT_STAGE_ID
        defaultMStageStoryShouldBeFound("stageId.greaterOrEqualThan=" + DEFAULT_STAGE_ID);

        // Get all the mStageStoryList where stageId greater than or equals to UPDATED_STAGE_ID
        defaultMStageStoryShouldNotBeFound("stageId.greaterOrEqualThan=" + UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllMStageStoriesByStageIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where stageId less than or equals to DEFAULT_STAGE_ID
        defaultMStageStoryShouldNotBeFound("stageId.lessThan=" + DEFAULT_STAGE_ID);

        // Get all the mStageStoryList where stageId less than or equals to UPDATED_STAGE_ID
        defaultMStageStoryShouldBeFound("stageId.lessThan=" + UPDATED_STAGE_ID);
    }


    @Test
    @Transactional
    public void getAllMStageStoriesByEventTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where eventType equals to DEFAULT_EVENT_TYPE
        defaultMStageStoryShouldBeFound("eventType.equals=" + DEFAULT_EVENT_TYPE);

        // Get all the mStageStoryList where eventType equals to UPDATED_EVENT_TYPE
        defaultMStageStoryShouldNotBeFound("eventType.equals=" + UPDATED_EVENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMStageStoriesByEventTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where eventType in DEFAULT_EVENT_TYPE or UPDATED_EVENT_TYPE
        defaultMStageStoryShouldBeFound("eventType.in=" + DEFAULT_EVENT_TYPE + "," + UPDATED_EVENT_TYPE);

        // Get all the mStageStoryList where eventType equals to UPDATED_EVENT_TYPE
        defaultMStageStoryShouldNotBeFound("eventType.in=" + UPDATED_EVENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMStageStoriesByEventTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where eventType is not null
        defaultMStageStoryShouldBeFound("eventType.specified=true");

        // Get all the mStageStoryList where eventType is null
        defaultMStageStoryShouldNotBeFound("eventType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMStageStoriesByEventTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where eventType greater than or equals to DEFAULT_EVENT_TYPE
        defaultMStageStoryShouldBeFound("eventType.greaterOrEqualThan=" + DEFAULT_EVENT_TYPE);

        // Get all the mStageStoryList where eventType greater than or equals to UPDATED_EVENT_TYPE
        defaultMStageStoryShouldNotBeFound("eventType.greaterOrEqualThan=" + UPDATED_EVENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMStageStoriesByEventTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        // Get all the mStageStoryList where eventType less than or equals to DEFAULT_EVENT_TYPE
        defaultMStageStoryShouldNotBeFound("eventType.lessThan=" + DEFAULT_EVENT_TYPE);

        // Get all the mStageStoryList where eventType less than or equals to UPDATED_EVENT_TYPE
        defaultMStageStoryShouldBeFound("eventType.lessThan=" + UPDATED_EVENT_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMStageStoryShouldBeFound(String filter) throws Exception {
        restMStageStoryMockMvc.perform(get("/api/m-stage-stories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mStageStory.getId().intValue())))
            .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)))
            .andExpect(jsonPath("$.[*].eventType").value(hasItem(DEFAULT_EVENT_TYPE)))
            .andExpect(jsonPath("$.[*].mainStoryAsset").value(hasItem(DEFAULT_MAIN_STORY_ASSET.toString())))
            .andExpect(jsonPath("$.[*].kickoffStoryAsset").value(hasItem(DEFAULT_KICKOFF_STORY_ASSET.toString())))
            .andExpect(jsonPath("$.[*].halftimeStoryAsset").value(hasItem(DEFAULT_HALFTIME_STORY_ASSET.toString())))
            .andExpect(jsonPath("$.[*].resultStoryAsset").value(hasItem(DEFAULT_RESULT_STORY_ASSET.toString())));

        // Check, that the count call also returns 1
        restMStageStoryMockMvc.perform(get("/api/m-stage-stories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMStageStoryShouldNotBeFound(String filter) throws Exception {
        restMStageStoryMockMvc.perform(get("/api/m-stage-stories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMStageStoryMockMvc.perform(get("/api/m-stage-stories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMStageStory() throws Exception {
        // Get the mStageStory
        restMStageStoryMockMvc.perform(get("/api/m-stage-stories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMStageStory() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        int databaseSizeBeforeUpdate = mStageStoryRepository.findAll().size();

        // Update the mStageStory
        MStageStory updatedMStageStory = mStageStoryRepository.findById(mStageStory.getId()).get();
        // Disconnect from session so that the updates on updatedMStageStory are not directly saved in db
        em.detach(updatedMStageStory);
        updatedMStageStory
            .stageId(UPDATED_STAGE_ID)
            .eventType(UPDATED_EVENT_TYPE)
            .mainStoryAsset(UPDATED_MAIN_STORY_ASSET)
            .kickoffStoryAsset(UPDATED_KICKOFF_STORY_ASSET)
            .halftimeStoryAsset(UPDATED_HALFTIME_STORY_ASSET)
            .resultStoryAsset(UPDATED_RESULT_STORY_ASSET);
        MStageStoryDTO mStageStoryDTO = mStageStoryMapper.toDto(updatedMStageStory);

        restMStageStoryMockMvc.perform(put("/api/m-stage-stories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStageStoryDTO)))
            .andExpect(status().isOk());

        // Validate the MStageStory in the database
        List<MStageStory> mStageStoryList = mStageStoryRepository.findAll();
        assertThat(mStageStoryList).hasSize(databaseSizeBeforeUpdate);
        MStageStory testMStageStory = mStageStoryList.get(mStageStoryList.size() - 1);
        assertThat(testMStageStory.getStageId()).isEqualTo(UPDATED_STAGE_ID);
        assertThat(testMStageStory.getEventType()).isEqualTo(UPDATED_EVENT_TYPE);
        assertThat(testMStageStory.getMainStoryAsset()).isEqualTo(UPDATED_MAIN_STORY_ASSET);
        assertThat(testMStageStory.getKickoffStoryAsset()).isEqualTo(UPDATED_KICKOFF_STORY_ASSET);
        assertThat(testMStageStory.getHalftimeStoryAsset()).isEqualTo(UPDATED_HALFTIME_STORY_ASSET);
        assertThat(testMStageStory.getResultStoryAsset()).isEqualTo(UPDATED_RESULT_STORY_ASSET);
    }

    @Test
    @Transactional
    public void updateNonExistingMStageStory() throws Exception {
        int databaseSizeBeforeUpdate = mStageStoryRepository.findAll().size();

        // Create the MStageStory
        MStageStoryDTO mStageStoryDTO = mStageStoryMapper.toDto(mStageStory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMStageStoryMockMvc.perform(put("/api/m-stage-stories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStageStoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MStageStory in the database
        List<MStageStory> mStageStoryList = mStageStoryRepository.findAll();
        assertThat(mStageStoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMStageStory() throws Exception {
        // Initialize the database
        mStageStoryRepository.saveAndFlush(mStageStory);

        int databaseSizeBeforeDelete = mStageStoryRepository.findAll().size();

        // Delete the mStageStory
        restMStageStoryMockMvc.perform(delete("/api/m-stage-stories/{id}", mStageStory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MStageStory> mStageStoryList = mStageStoryRepository.findAll();
        assertThat(mStageStoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MStageStory.class);
        MStageStory mStageStory1 = new MStageStory();
        mStageStory1.setId(1L);
        MStageStory mStageStory2 = new MStageStory();
        mStageStory2.setId(mStageStory1.getId());
        assertThat(mStageStory1).isEqualTo(mStageStory2);
        mStageStory2.setId(2L);
        assertThat(mStageStory1).isNotEqualTo(mStageStory2);
        mStageStory1.setId(null);
        assertThat(mStageStory1).isNotEqualTo(mStageStory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MStageStoryDTO.class);
        MStageStoryDTO mStageStoryDTO1 = new MStageStoryDTO();
        mStageStoryDTO1.setId(1L);
        MStageStoryDTO mStageStoryDTO2 = new MStageStoryDTO();
        assertThat(mStageStoryDTO1).isNotEqualTo(mStageStoryDTO2);
        mStageStoryDTO2.setId(mStageStoryDTO1.getId());
        assertThat(mStageStoryDTO1).isEqualTo(mStageStoryDTO2);
        mStageStoryDTO2.setId(2L);
        assertThat(mStageStoryDTO1).isNotEqualTo(mStageStoryDTO2);
        mStageStoryDTO1.setId(null);
        assertThat(mStageStoryDTO1).isNotEqualTo(mStageStoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mStageStoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mStageStoryMapper.fromId(null)).isNull();
    }
}
