package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMissionReward;
import io.shm.tsubasa.domain.MGuildMission;
import io.shm.tsubasa.domain.MMission;
import io.shm.tsubasa.repository.MMissionRewardRepository;
import io.shm.tsubasa.service.MMissionRewardService;
import io.shm.tsubasa.service.dto.MMissionRewardDTO;
import io.shm.tsubasa.service.mapper.MMissionRewardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMissionRewardCriteria;
import io.shm.tsubasa.service.MMissionRewardQueryService;

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
 * Integration tests for the {@Link MMissionRewardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMissionRewardResourceIT {

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_CONTENT_AMOUNT = 1;
    private static final Integer UPDATED_CONTENT_AMOUNT = 2;

    @Autowired
    private MMissionRewardRepository mMissionRewardRepository;

    @Autowired
    private MMissionRewardMapper mMissionRewardMapper;

    @Autowired
    private MMissionRewardService mMissionRewardService;

    @Autowired
    private MMissionRewardQueryService mMissionRewardQueryService;

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

    private MockMvc restMMissionRewardMockMvc;

    private MMissionReward mMissionReward;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMissionRewardResource mMissionRewardResource = new MMissionRewardResource(mMissionRewardService, mMissionRewardQueryService);
        this.restMMissionRewardMockMvc = MockMvcBuilders.standaloneSetup(mMissionRewardResource)
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
    public static MMissionReward createEntity(EntityManager em) {
        MMissionReward mMissionReward = new MMissionReward()
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .contentAmount(DEFAULT_CONTENT_AMOUNT);
        return mMissionReward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMissionReward createUpdatedEntity(EntityManager em) {
        MMissionReward mMissionReward = new MMissionReward()
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        return mMissionReward;
    }

    @BeforeEach
    public void initTest() {
        mMissionReward = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMissionReward() throws Exception {
        int databaseSizeBeforeCreate = mMissionRewardRepository.findAll().size();

        // Create the MMissionReward
        MMissionRewardDTO mMissionRewardDTO = mMissionRewardMapper.toDto(mMissionReward);
        restMMissionRewardMockMvc.perform(post("/api/m-mission-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionRewardDTO)))
            .andExpect(status().isCreated());

        // Validate the MMissionReward in the database
        List<MMissionReward> mMissionRewardList = mMissionRewardRepository.findAll();
        assertThat(mMissionRewardList).hasSize(databaseSizeBeforeCreate + 1);
        MMissionReward testMMissionReward = mMissionRewardList.get(mMissionRewardList.size() - 1);
        assertThat(testMMissionReward.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMMissionReward.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMMissionReward.getContentAmount()).isEqualTo(DEFAULT_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMMissionRewardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMissionRewardRepository.findAll().size();

        // Create the MMissionReward with an existing ID
        mMissionReward.setId(1L);
        MMissionRewardDTO mMissionRewardDTO = mMissionRewardMapper.toDto(mMissionReward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMissionRewardMockMvc.perform(post("/api/m-mission-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMissionReward in the database
        List<MMissionReward> mMissionRewardList = mMissionRewardRepository.findAll();
        assertThat(mMissionRewardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRewardRepository.findAll().size();
        // set the field null
        mMissionReward.setContentType(null);

        // Create the MMissionReward, which fails.
        MMissionRewardDTO mMissionRewardDTO = mMissionRewardMapper.toDto(mMissionReward);

        restMMissionRewardMockMvc.perform(post("/api/m-mission-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMissionReward> mMissionRewardList = mMissionRewardRepository.findAll();
        assertThat(mMissionRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMissionRewardRepository.findAll().size();
        // set the field null
        mMissionReward.setContentAmount(null);

        // Create the MMissionReward, which fails.
        MMissionRewardDTO mMissionRewardDTO = mMissionRewardMapper.toDto(mMissionReward);

        restMMissionRewardMockMvc.perform(post("/api/m-mission-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionRewardDTO)))
            .andExpect(status().isBadRequest());

        List<MMissionReward> mMissionRewardList = mMissionRewardRepository.findAll();
        assertThat(mMissionRewardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMissionRewards() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList
        restMMissionRewardMockMvc.perform(get("/api/m-mission-rewards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMissionReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMMissionReward() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get the mMissionReward
        restMMissionRewardMockMvc.perform(get("/api/m-mission-rewards/{id}", mMissionReward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMissionReward.getId().intValue()))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.contentAmount").value(DEFAULT_CONTENT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMMissionRewardShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMissionRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMissionRewardShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMMissionRewardShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mMissionRewardList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMMissionRewardShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentType is not null
        defaultMMissionRewardShouldBeFound("contentType.specified=true");

        // Get all the mMissionRewardList where contentType is null
        defaultMMissionRewardShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMMissionRewardShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMissionRewardList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMMissionRewardShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMMissionRewardShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mMissionRewardList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMMissionRewardShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMissionRewardsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentId equals to DEFAULT_CONTENT_ID
        defaultMMissionRewardShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mMissionRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMMissionRewardShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMMissionRewardShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mMissionRewardList where contentId equals to UPDATED_CONTENT_ID
        defaultMMissionRewardShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentId is not null
        defaultMMissionRewardShouldBeFound("contentId.specified=true");

        // Get all the mMissionRewardList where contentId is null
        defaultMMissionRewardShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMMissionRewardShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMissionRewardList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMMissionRewardShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMMissionRewardShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mMissionRewardList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMMissionRewardShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMissionRewardsByContentAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentAmount equals to DEFAULT_CONTENT_AMOUNT
        defaultMMissionRewardShouldBeFound("contentAmount.equals=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMissionRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMissionRewardShouldNotBeFound("contentAmount.equals=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentAmount in DEFAULT_CONTENT_AMOUNT or UPDATED_CONTENT_AMOUNT
        defaultMMissionRewardShouldBeFound("contentAmount.in=" + DEFAULT_CONTENT_AMOUNT + "," + UPDATED_CONTENT_AMOUNT);

        // Get all the mMissionRewardList where contentAmount equals to UPDATED_CONTENT_AMOUNT
        defaultMMissionRewardShouldNotBeFound("contentAmount.in=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentAmount is not null
        defaultMMissionRewardShouldBeFound("contentAmount.specified=true");

        // Get all the mMissionRewardList where contentAmount is null
        defaultMMissionRewardShouldNotBeFound("contentAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentAmount greater than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMissionRewardShouldBeFound("contentAmount.greaterOrEqualThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMissionRewardList where contentAmount greater than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMissionRewardShouldNotBeFound("contentAmount.greaterOrEqualThan=" + UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMissionRewardsByContentAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        // Get all the mMissionRewardList where contentAmount less than or equals to DEFAULT_CONTENT_AMOUNT
        defaultMMissionRewardShouldNotBeFound("contentAmount.lessThan=" + DEFAULT_CONTENT_AMOUNT);

        // Get all the mMissionRewardList where contentAmount less than or equals to UPDATED_CONTENT_AMOUNT
        defaultMMissionRewardShouldBeFound("contentAmount.lessThan=" + UPDATED_CONTENT_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMMissionRewardsByMGuildMissionIsEqualToSomething() throws Exception {
        // Initialize the database
        MGuildMission mGuildMission = MGuildMissionResourceIT.createEntity(em);
        em.persist(mGuildMission);
        em.flush();
        mMissionReward.addMGuildMission(mGuildMission);
        mMissionRewardRepository.saveAndFlush(mMissionReward);
        Long mGuildMissionId = mGuildMission.getId();

        // Get all the mMissionRewardList where mGuildMission equals to mGuildMissionId
        defaultMMissionRewardShouldBeFound("mGuildMissionId.equals=" + mGuildMissionId);

        // Get all the mMissionRewardList where mGuildMission equals to mGuildMissionId + 1
        defaultMMissionRewardShouldNotBeFound("mGuildMissionId.equals=" + (mGuildMissionId + 1));
    }


    @Test
    @Transactional
    public void getAllMMissionRewardsByMMissionIsEqualToSomething() throws Exception {
        // Initialize the database
        MMission mMission = MMissionResourceIT.createEntity(em);
        em.persist(mMission);
        em.flush();
        mMissionReward.addMMission(mMission);
        mMissionRewardRepository.saveAndFlush(mMissionReward);
        Long mMissionId = mMission.getId();

        // Get all the mMissionRewardList where mMission equals to mMissionId
        defaultMMissionRewardShouldBeFound("mMissionId.equals=" + mMissionId);

        // Get all the mMissionRewardList where mMission equals to mMissionId + 1
        defaultMMissionRewardShouldNotBeFound("mMissionId.equals=" + (mMissionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMissionRewardShouldBeFound(String filter) throws Exception {
        restMMissionRewardMockMvc.perform(get("/api/m-mission-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMissionReward.getId().intValue())))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].contentAmount").value(hasItem(DEFAULT_CONTENT_AMOUNT)));

        // Check, that the count call also returns 1
        restMMissionRewardMockMvc.perform(get("/api/m-mission-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMissionRewardShouldNotBeFound(String filter) throws Exception {
        restMMissionRewardMockMvc.perform(get("/api/m-mission-rewards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMissionRewardMockMvc.perform(get("/api/m-mission-rewards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMissionReward() throws Exception {
        // Get the mMissionReward
        restMMissionRewardMockMvc.perform(get("/api/m-mission-rewards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMissionReward() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        int databaseSizeBeforeUpdate = mMissionRewardRepository.findAll().size();

        // Update the mMissionReward
        MMissionReward updatedMMissionReward = mMissionRewardRepository.findById(mMissionReward.getId()).get();
        // Disconnect from session so that the updates on updatedMMissionReward are not directly saved in db
        em.detach(updatedMMissionReward);
        updatedMMissionReward
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .contentAmount(UPDATED_CONTENT_AMOUNT);
        MMissionRewardDTO mMissionRewardDTO = mMissionRewardMapper.toDto(updatedMMissionReward);

        restMMissionRewardMockMvc.perform(put("/api/m-mission-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionRewardDTO)))
            .andExpect(status().isOk());

        // Validate the MMissionReward in the database
        List<MMissionReward> mMissionRewardList = mMissionRewardRepository.findAll();
        assertThat(mMissionRewardList).hasSize(databaseSizeBeforeUpdate);
        MMissionReward testMMissionReward = mMissionRewardList.get(mMissionRewardList.size() - 1);
        assertThat(testMMissionReward.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMMissionReward.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMMissionReward.getContentAmount()).isEqualTo(UPDATED_CONTENT_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMMissionReward() throws Exception {
        int databaseSizeBeforeUpdate = mMissionRewardRepository.findAll().size();

        // Create the MMissionReward
        MMissionRewardDTO mMissionRewardDTO = mMissionRewardMapper.toDto(mMissionReward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMissionRewardMockMvc.perform(put("/api/m-mission-rewards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMissionRewardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMissionReward in the database
        List<MMissionReward> mMissionRewardList = mMissionRewardRepository.findAll();
        assertThat(mMissionRewardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMissionReward() throws Exception {
        // Initialize the database
        mMissionRewardRepository.saveAndFlush(mMissionReward);

        int databaseSizeBeforeDelete = mMissionRewardRepository.findAll().size();

        // Delete the mMissionReward
        restMMissionRewardMockMvc.perform(delete("/api/m-mission-rewards/{id}", mMissionReward.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMissionReward> mMissionRewardList = mMissionRewardRepository.findAll();
        assertThat(mMissionRewardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMissionReward.class);
        MMissionReward mMissionReward1 = new MMissionReward();
        mMissionReward1.setId(1L);
        MMissionReward mMissionReward2 = new MMissionReward();
        mMissionReward2.setId(mMissionReward1.getId());
        assertThat(mMissionReward1).isEqualTo(mMissionReward2);
        mMissionReward2.setId(2L);
        assertThat(mMissionReward1).isNotEqualTo(mMissionReward2);
        mMissionReward1.setId(null);
        assertThat(mMissionReward1).isNotEqualTo(mMissionReward2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMissionRewardDTO.class);
        MMissionRewardDTO mMissionRewardDTO1 = new MMissionRewardDTO();
        mMissionRewardDTO1.setId(1L);
        MMissionRewardDTO mMissionRewardDTO2 = new MMissionRewardDTO();
        assertThat(mMissionRewardDTO1).isNotEqualTo(mMissionRewardDTO2);
        mMissionRewardDTO2.setId(mMissionRewardDTO1.getId());
        assertThat(mMissionRewardDTO1).isEqualTo(mMissionRewardDTO2);
        mMissionRewardDTO2.setId(2L);
        assertThat(mMissionRewardDTO1).isNotEqualTo(mMissionRewardDTO2);
        mMissionRewardDTO1.setId(null);
        assertThat(mMissionRewardDTO1).isNotEqualTo(mMissionRewardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMissionRewardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMissionRewardMapper.fromId(null)).isNull();
    }
}
