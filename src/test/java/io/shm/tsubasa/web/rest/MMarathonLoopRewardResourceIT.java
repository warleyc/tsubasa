package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonLoopReward;
import io.shm.tsubasa.repository.MMarathonLoopRewardRepository;
import io.shm.tsubasa.service.MMarathonLoopRewardService;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonLoopRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardCriteria;
import io.shm.tsubasa.service.MMarathonLoopRewardQueryService;

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
 * Integration tests for the {@Link MMarathonLoopRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonLoopRewardResourceIT {

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final Integer DEFAULT_LOOP_POINT = 1;
    private static final Integer UPDATED_LOOP_POINT = 2;

    @Autowired
    private MMarathonLoopRewardRepository mMarathonLoopRewardRepository;

    @Autowired
    private MMarathonLoopRewardMapper mMarathonLoopRewardMapper;

    @Autowired
    private MMarathonLoopRewardService mMarathonLoopRewardService;

    @Autowired
    private MMarathonLoopRewardQueryService mMarathonLoopRewardQueryService;

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

    private MockMvc restMMarathonLoopRewardMockMvc;

    private MMarathonLoopReward mMarathonLoopReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonLoopRewardResource mMarathonLoopRewardResource = new MMarathonLoopRewardResource(mMarathonLoopRewardService, mMarathonLoopRewardQueryService);
        this.restMMarathonLoopRewardMockMvc = MockMvcBuilders.standaloneSetup(mMarathonLoopRewardResource)
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
    public static MMarathonLoopReward createEntity(EntityManager em) {
        MMarathonLoopReward mMarathonLoopReward = new MMarathonLoopReward()
            .eventId(DEFAULT_EVENT_ID)
            .loopPoint(DEFAULT_LOOP_POINT);
        return mMarathonLoopReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonLoopReward createUpdatedEntity(EntityManager em) {
        MMarathonLoopReward mMarathonLoopReward = new MMarathonLoopReward()
            .eventId(UPDATED_EVENT_ID)
            .loopPoint(UPDATED_LOOP_POINT);
        return mMarathonLoopReward;
    }

    @BeforeEach
    public void initTest() {
        mMarathonLoopReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonLoopReward() throws Exception {
        int databaseSizeBeforeCreate = mMarathonLoopRewardRepository.findAll().size();

        // Create the MMarathonLoopReward
        MMarathonLoopRewardDTO mMarathonLoopRewardDTO = mMarathonLoopRewardMapper.toDto(mMarathonLoopReward);
        restMMarathonLoopRewardMockMvc.perform(post("/api/m-marathon-loop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonLoopReward in the database
        List<MMarathonLoopReward> mMarathonLoopRewardList = mMarathonLoopRewardRepository.findAll();
        assertThat(mMarathonLoopRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonLoopReward testMMarathonLoopReward = mMarathonLoopRewardList.get(mMarathonLoopRewardList.size() - 1);
        assertThat(testMMarathonLoopReward.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testMMarathonLoopReward.getLoopPoint()).isEqualTo(DEFAULT_LOOP_POINT);
    }

    @Test
    @Transactional
    public void createMMarathonLoopRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonLoopRewardRepository.findAll().size();

        // Create the MMarathonLoopReward with an existing ID
        mMarathonLoopReward.setId(1L);
        MMarathonLoopRewardDTO mMarathonLoopRewardDTO = mMarathonLoopRewardMapper.toDto(mMarathonLoopReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonLoopRewardMockMvc.perform(post("/api/m-marathon-loop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonLoopReward in the database
        List<MMarathonLoopReward> mMarathonLoopRewardList = mMarathonLoopRewardRepository.findAll();
        assertThat(mMarathonLoopRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEventIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonLoopRewardRepository.findAll().size();
        // set the field null
        mMarathonLoopReward.setEventId(null);

        // Create the MMarathonLoopReward, which fails.
        MMarathonLoopRewardDTO mMarathonLoopRewardDTO = mMarathonLoopRewardMapper.toDto(mMarathonLoopReward);

        restMMarathonLoopRewardMockMvc.perform(post("/api/m-marathon-loop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonLoopReward> mMarathonLoopRewardList = mMarathonLoopRewardRepository.findAll();
        assertThat(mMarathonLoopRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoopPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonLoopRewardRepository.findAll().size();
        // set the field null
        mMarathonLoopReward.setLoopPoint(null);

        // Create the MMarathonLoopReward, which fails.
        MMarathonLoopRewardDTO mMarathonLoopRewardDTO = mMarathonLoopRewardMapper.toDto(mMarathonLoopReward);

        restMMarathonLoopRewardMockMvc.perform(post("/api/m-marathon-loop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonLoopReward> mMarathonLoopRewardList = mMarathonLoopRewardRepository.findAll();
        assertThat(mMarathonLoopRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewards() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList
        restMMarathonLoopRewardMockMvc.perform(get("/api/m-marathon-loop-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonLoopReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].loopPoint").value(hasItem(DEFAULT_LOOP_POINT)));
    }
    
    @Test
    @Transactional
    public void getMMarathonLoopReward() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get the mMarathonLoopReward
        restMMarathonLoopRewardMockMvc.perform(get("/api/m-marathon-loop-rewards/{id}", mMarathonLoopReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonLoopReward.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.loopPoint").value(DEFAULT_LOOP_POINT));
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByEventIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where eventId equals to DEFAULT_EVENT_ID
        defaultMMarathonLoopRewardShouldBeFound("eventId.equals=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonLoopRewardList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonLoopRewardShouldNotBeFound("eventId.equals=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByEventIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where eventId in DEFAULT_EVENT_ID or UPDATED_EVENT_ID
        defaultMMarathonLoopRewardShouldBeFound("eventId.in=" + DEFAULT_EVENT_ID + "," + UPDATED_EVENT_ID);

        // Get all the mMarathonLoopRewardList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonLoopRewardShouldNotBeFound("eventId.in=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByEventIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where eventId is not null
        defaultMMarathonLoopRewardShouldBeFound("eventId.specified=true");

        // Get all the mMarathonLoopRewardList where eventId is null
        defaultMMarathonLoopRewardShouldNotBeFound("eventId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByEventIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where eventId greater than or equals to DEFAULT_EVENT_ID
        defaultMMarathonLoopRewardShouldBeFound("eventId.greaterOrEqualThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonLoopRewardList where eventId greater than or equals to UPDATED_EVENT_ID
        defaultMMarathonLoopRewardShouldNotBeFound("eventId.greaterOrEqualThan=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByEventIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where eventId less than or equals to DEFAULT_EVENT_ID
        defaultMMarathonLoopRewardShouldNotBeFound("eventId.lessThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonLoopRewardList where eventId less than or equals to UPDATED_EVENT_ID
        defaultMMarathonLoopRewardShouldBeFound("eventId.lessThan=" + UPDATED_EVENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByLoopPointIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where loopPoint equals to DEFAULT_LOOP_POINT
        defaultMMarathonLoopRewardShouldBeFound("loopPoint.equals=" + DEFAULT_LOOP_POINT);

        // Get all the mMarathonLoopRewardList where loopPoint equals to UPDATED_LOOP_POINT
        defaultMMarathonLoopRewardShouldNotBeFound("loopPoint.equals=" + UPDATED_LOOP_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByLoopPointIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where loopPoint in DEFAULT_LOOP_POINT or UPDATED_LOOP_POINT
        defaultMMarathonLoopRewardShouldBeFound("loopPoint.in=" + DEFAULT_LOOP_POINT + "," + UPDATED_LOOP_POINT);

        // Get all the mMarathonLoopRewardList where loopPoint equals to UPDATED_LOOP_POINT
        defaultMMarathonLoopRewardShouldNotBeFound("loopPoint.in=" + UPDATED_LOOP_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByLoopPointIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where loopPoint is not null
        defaultMMarathonLoopRewardShouldBeFound("loopPoint.specified=true");

        // Get all the mMarathonLoopRewardList where loopPoint is null
        defaultMMarathonLoopRewardShouldNotBeFound("loopPoint.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByLoopPointIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where loopPoint greater than or equals to DEFAULT_LOOP_POINT
        defaultMMarathonLoopRewardShouldBeFound("loopPoint.greaterOrEqualThan=" + DEFAULT_LOOP_POINT);

        // Get all the mMarathonLoopRewardList where loopPoint greater than or equals to UPDATED_LOOP_POINT
        defaultMMarathonLoopRewardShouldNotBeFound("loopPoint.greaterOrEqualThan=" + UPDATED_LOOP_POINT);
    }

    @Test
    @Transactional
    public void getAllMMarathonLoopRewardsByLoopPointIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        // Get all the mMarathonLoopRewardList where loopPoint less than or equals to DEFAULT_LOOP_POINT
        defaultMMarathonLoopRewardShouldNotBeFound("loopPoint.lessThan=" + DEFAULT_LOOP_POINT);

        // Get all the mMarathonLoopRewardList where loopPoint less than or equals to UPDATED_LOOP_POINT
        defaultMMarathonLoopRewardShouldBeFound("loopPoint.lessThan=" + UPDATED_LOOP_POINT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonLoopRewardShouldBeFound(String filter) throws Exception {
        restMMarathonLoopRewardMockMvc.perform(get("/api/m-marathon-loop-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonLoopReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].loopPoint").value(hasItem(DEFAULT_LOOP_POINT)));

        // Check, that the count call also returns 1
        restMMarathonLoopRewardMockMvc.perform(get("/api/m-marathon-loop-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonLoopRewardShouldNotBeFound(String filter) throws Exception {
        restMMarathonLoopRewardMockMvc.perform(get("/api/m-marathon-loop-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonLoopRewardMockMvc.perform(get("/api/m-marathon-loop-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonLoopReward() throws Exception {
        // Get the mMarathonLoopReward
        restMMarathonLoopRewardMockMvc.perform(get("/api/m-marathon-loop-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonLoopReward() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        int databaseSizeBeforeUpdate = mMarathonLoopRewardRepository.findAll().size();

        // Update the mMarathonLoopReward
        MMarathonLoopReward updatedMMarathonLoopReward = mMarathonLoopRewardRepository.findById(mMarathonLoopReward.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonLoopReward are not directly saved in db
        em.detach(updatedMMarathonLoopReward);
        updatedMMarathonLoopReward
            .eventId(UPDATED_EVENT_ID)
            .loopPoint(UPDATED_LOOP_POINT);
        MMarathonLoopRewardDTO mMarathonLoopRewardDTO = mMarathonLoopRewardMapper.toDto(updatedMMarathonLoopReward);

        restMMarathonLoopRewardMockMvc.perform(put("/api/m-marathon-loop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonLoopReward in the database
        List<MMarathonLoopReward> mMarathonLoopRewardList = mMarathonLoopRewardRepository.findAll();
        assertThat(mMarathonLoopRewardList).hasSize(databaseSizeBeforeUpdate);
        MMarathonLoopReward testMMarathonLoopReward = mMarathonLoopRewardList.get(mMarathonLoopRewardList.size() - 1);
        assertThat(testMMarathonLoopReward.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testMMarathonLoopReward.getLoopPoint()).isEqualTo(UPDATED_LOOP_POINT);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonLoopReward() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonLoopRewardRepository.findAll().size();

        // Create the MMarathonLoopReward
        MMarathonLoopRewardDTO mMarathonLoopRewardDTO = mMarathonLoopRewardMapper.toDto(mMarathonLoopReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonLoopRewardMockMvc.perform(put("/api/m-marathon-loop-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonLoopRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonLoopReward in the database
        List<MMarathonLoopReward> mMarathonLoopRewardList = mMarathonLoopRewardRepository.findAll();
        assertThat(mMarathonLoopRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonLoopReward() throws Exception {
        // Initialize the database
        mMarathonLoopRewardRepository.saveAndFlush(mMarathonLoopReward);

        int databaseSizeBeforeDelete = mMarathonLoopRewardRepository.findAll().size();

        // Delete the mMarathonLoopReward
        restMMarathonLoopRewardMockMvc.perform(delete("/api/m-marathon-loop-rewards/{id}", mMarathonLoopReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonLoopReward> mMarathonLoopRewardList = mMarathonLoopRewardRepository.findAll();
        assertThat(mMarathonLoopRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonLoopReward.class);
        MMarathonLoopReward mMarathonLoopReward1 = new MMarathonLoopReward();
        mMarathonLoopReward1.setId(1L);
        MMarathonLoopReward mMarathonLoopReward2 = new MMarathonLoopReward();
        mMarathonLoopReward2.setId(mMarathonLoopReward1.getId());
        assertThat(mMarathonLoopReward1).isEqualTo(mMarathonLoopReward2);
        mMarathonLoopReward2.setId(2L);
        assertThat(mMarathonLoopReward1).isNotEqualTo(mMarathonLoopReward2);
        mMarathonLoopReward1.setId(null);
        assertThat(mMarathonLoopReward1).isNotEqualTo(mMarathonLoopReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonLoopRewardDTO.class);
        MMarathonLoopRewardDTO mMarathonLoopRewardDTO1 = new MMarathonLoopRewardDTO();
        mMarathonLoopRewardDTO1.setId(1L);
        MMarathonLoopRewardDTO mMarathonLoopRewardDTO2 = new MMarathonLoopRewardDTO();
        assertThat(mMarathonLoopRewardDTO1).isNotEqualTo(mMarathonLoopRewardDTO2);
        mMarathonLoopRewardDTO2.setId(mMarathonLoopRewardDTO1.getId());
        assertThat(mMarathonLoopRewardDTO1).isEqualTo(mMarathonLoopRewardDTO2);
        mMarathonLoopRewardDTO2.setId(2L);
        assertThat(mMarathonLoopRewardDTO1).isNotEqualTo(mMarathonLoopRewardDTO2);
        mMarathonLoopRewardDTO1.setId(null);
        assertThat(mMarathonLoopRewardDTO1).isNotEqualTo(mMarathonLoopRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonLoopRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonLoopRewardMapper.fromId(null)).isNull();
    }
}
