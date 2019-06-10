package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAchievement;
import io.shm.tsubasa.domain.MMission;
import io.shm.tsubasa.repository.MAchievementRepository;
import io.shm.tsubasa.service.MAchievementService;
import io.shm.tsubasa.service.dto.MAchievementDTO;
import io.shm.tsubasa.service.mapper.MAchievementMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MAchievementCriteria;
import io.shm.tsubasa.service.MAchievementQueryService;

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
 * Integration tests for the {@Link MAchievementResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MAchievementResourceIT {

    private static final String DEFAULT_ACHIEVEMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACHIEVEMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_MISSION_ID = 1;
    private static final Integer UPDATED_MISSION_ID = 2;

    @Autowired
    private MAchievementRepository mAchievementRepository;

    @Autowired
    private MAchievementMapper mAchievementMapper;

    @Autowired
    private MAchievementService mAchievementService;

    @Autowired
    private MAchievementQueryService mAchievementQueryService;

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

    private MockMvc restMAchievementMockMvc;

    private MAchievement mAchievement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MAchievementResource mAchievementResource = new MAchievementResource(mAchievementService, mAchievementQueryService);
        this.restMAchievementMockMvc = MockMvcBuilders.standaloneSetup(mAchievementResource)
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
    public static MAchievement createEntity(EntityManager em) {
        MAchievement mAchievement = new MAchievement()
            .achievementId(DEFAULT_ACHIEVEMENT_ID)
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .missionId(DEFAULT_MISSION_ID);
        // Add required entity
        MMission mMission;
        if (TestUtil.findAll(em, MMission.class).isEmpty()) {
            mMission = MMissionResourceIT.createEntity(em);
            em.persist(mMission);
            em.flush();
        } else {
            mMission = TestUtil.findAll(em, MMission.class).get(0);
        }
        mAchievement.setId(mMission);
        return mAchievement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAchievement createUpdatedEntity(EntityManager em) {
        MAchievement mAchievement = new MAchievement()
            .achievementId(UPDATED_ACHIEVEMENT_ID)
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .missionId(UPDATED_MISSION_ID);
        // Add required entity
        MMission mMission;
        if (TestUtil.findAll(em, MMission.class).isEmpty()) {
            mMission = MMissionResourceIT.createUpdatedEntity(em);
            em.persist(mMission);
            em.flush();
        } else {
            mMission = TestUtil.findAll(em, MMission.class).get(0);
        }
        mAchievement.setId(mMission);
        return mAchievement;
    }

    @BeforeEach
    public void initTest() {
        mAchievement = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAchievement() throws Exception {
        int databaseSizeBeforeCreate = mAchievementRepository.findAll().size();

        // Create the MAchievement
        MAchievementDTO mAchievementDTO = mAchievementMapper.toDto(mAchievement);
        restMAchievementMockMvc.perform(post("/api/m-achievements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAchievementDTO)))
            .andExpect(status().isCreated());

        // Validate the MAchievement in the database
        List<MAchievement> mAchievementList = mAchievementRepository.findAll();
        assertThat(mAchievementList).hasSize(databaseSizeBeforeCreate + 1);
        MAchievement testMAchievement = mAchievementList.get(mAchievementList.size() - 1);
        assertThat(testMAchievement.getAchievementId()).isEqualTo(DEFAULT_ACHIEVEMENT_ID);
        assertThat(testMAchievement.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMAchievement.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMAchievement.getMissionId()).isEqualTo(DEFAULT_MISSION_ID);
    }

    @Test
    @Transactional
    public void createMAchievementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mAchievementRepository.findAll().size();

        // Create the MAchievement with an existing ID
        mAchievement.setId(1L);
        MAchievementDTO mAchievementDTO = mAchievementMapper.toDto(mAchievement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMAchievementMockMvc.perform(post("/api/m-achievements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAchievementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAchievement in the database
        List<MAchievement> mAchievementList = mAchievementRepository.findAll();
        assertThat(mAchievementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAchievementRepository.findAll().size();
        // set the field null
        mAchievement.setType(null);

        // Create the MAchievement, which fails.
        MAchievementDTO mAchievementDTO = mAchievementMapper.toDto(mAchievement);

        restMAchievementMockMvc.perform(post("/api/m-achievements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAchievementDTO)))
            .andExpect(status().isBadRequest());

        List<MAchievement> mAchievementList = mAchievementRepository.findAll();
        assertThat(mAchievementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMissionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAchievementRepository.findAll().size();
        // set the field null
        mAchievement.setMissionId(null);

        // Create the MAchievement, which fails.
        MAchievementDTO mAchievementDTO = mAchievementMapper.toDto(mAchievement);

        restMAchievementMockMvc.perform(post("/api/m-achievements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAchievementDTO)))
            .andExpect(status().isBadRequest());

        List<MAchievement> mAchievementList = mAchievementRepository.findAll();
        assertThat(mAchievementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMAchievements() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList
        restMAchievementMockMvc.perform(get("/api/m-achievements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAchievement.getId().intValue())))
            .andExpect(jsonPath("$.[*].achievementId").value(hasItem(DEFAULT_ACHIEVEMENT_ID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].missionId").value(hasItem(DEFAULT_MISSION_ID)));
    }
    
    @Test
    @Transactional
    public void getMAchievement() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get the mAchievement
        restMAchievementMockMvc.perform(get("/api/m-achievements/{id}", mAchievement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAchievement.getId().intValue()))
            .andExpect(jsonPath("$.achievementId").value(DEFAULT_ACHIEVEMENT_ID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.missionId").value(DEFAULT_MISSION_ID));
    }

    @Test
    @Transactional
    public void getAllMAchievementsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where type equals to DEFAULT_TYPE
        defaultMAchievementShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the mAchievementList where type equals to UPDATED_TYPE
        defaultMAchievementShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAchievementsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultMAchievementShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the mAchievementList where type equals to UPDATED_TYPE
        defaultMAchievementShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAchievementsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where type is not null
        defaultMAchievementShouldBeFound("type.specified=true");

        // Get all the mAchievementList where type is null
        defaultMAchievementShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAchievementsByTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where type greater than or equals to DEFAULT_TYPE
        defaultMAchievementShouldBeFound("type.greaterOrEqualThan=" + DEFAULT_TYPE);

        // Get all the mAchievementList where type greater than or equals to UPDATED_TYPE
        defaultMAchievementShouldNotBeFound("type.greaterOrEqualThan=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMAchievementsByTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where type less than or equals to DEFAULT_TYPE
        defaultMAchievementShouldNotBeFound("type.lessThan=" + DEFAULT_TYPE);

        // Get all the mAchievementList where type less than or equals to UPDATED_TYPE
        defaultMAchievementShouldBeFound("type.lessThan=" + UPDATED_TYPE);
    }


    @Test
    @Transactional
    public void getAllMAchievementsByMissionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where missionId equals to DEFAULT_MISSION_ID
        defaultMAchievementShouldBeFound("missionId.equals=" + DEFAULT_MISSION_ID);

        // Get all the mAchievementList where missionId equals to UPDATED_MISSION_ID
        defaultMAchievementShouldNotBeFound("missionId.equals=" + UPDATED_MISSION_ID);
    }

    @Test
    @Transactional
    public void getAllMAchievementsByMissionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where missionId in DEFAULT_MISSION_ID or UPDATED_MISSION_ID
        defaultMAchievementShouldBeFound("missionId.in=" + DEFAULT_MISSION_ID + "," + UPDATED_MISSION_ID);

        // Get all the mAchievementList where missionId equals to UPDATED_MISSION_ID
        defaultMAchievementShouldNotBeFound("missionId.in=" + UPDATED_MISSION_ID);
    }

    @Test
    @Transactional
    public void getAllMAchievementsByMissionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where missionId is not null
        defaultMAchievementShouldBeFound("missionId.specified=true");

        // Get all the mAchievementList where missionId is null
        defaultMAchievementShouldNotBeFound("missionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAchievementsByMissionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where missionId greater than or equals to DEFAULT_MISSION_ID
        defaultMAchievementShouldBeFound("missionId.greaterOrEqualThan=" + DEFAULT_MISSION_ID);

        // Get all the mAchievementList where missionId greater than or equals to UPDATED_MISSION_ID
        defaultMAchievementShouldNotBeFound("missionId.greaterOrEqualThan=" + UPDATED_MISSION_ID);
    }

    @Test
    @Transactional
    public void getAllMAchievementsByMissionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        // Get all the mAchievementList where missionId less than or equals to DEFAULT_MISSION_ID
        defaultMAchievementShouldNotBeFound("missionId.lessThan=" + DEFAULT_MISSION_ID);

        // Get all the mAchievementList where missionId less than or equals to UPDATED_MISSION_ID
        defaultMAchievementShouldBeFound("missionId.lessThan=" + UPDATED_MISSION_ID);
    }


    @Test
    @Transactional
    public void getAllMAchievementsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MMission id = mAchievement.getId();
        mAchievementRepository.saveAndFlush(mAchievement);
        Long idId = id.getId();

        // Get all the mAchievementList where id equals to idId
        defaultMAchievementShouldBeFound("idId.equals=" + idId);

        // Get all the mAchievementList where id equals to idId + 1
        defaultMAchievementShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMAchievementShouldBeFound(String filter) throws Exception {
        restMAchievementMockMvc.perform(get("/api/m-achievements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAchievement.getId().intValue())))
            .andExpect(jsonPath("$.[*].achievementId").value(hasItem(DEFAULT_ACHIEVEMENT_ID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].missionId").value(hasItem(DEFAULT_MISSION_ID)));

        // Check, that the count call also returns 1
        restMAchievementMockMvc.perform(get("/api/m-achievements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMAchievementShouldNotBeFound(String filter) throws Exception {
        restMAchievementMockMvc.perform(get("/api/m-achievements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMAchievementMockMvc.perform(get("/api/m-achievements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAchievement() throws Exception {
        // Get the mAchievement
        restMAchievementMockMvc.perform(get("/api/m-achievements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAchievement() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        int databaseSizeBeforeUpdate = mAchievementRepository.findAll().size();

        // Update the mAchievement
        MAchievement updatedMAchievement = mAchievementRepository.findById(mAchievement.getId()).get();
        // Disconnect from session so that the updates on updatedMAchievement are not directly saved in db
        em.detach(updatedMAchievement);
        updatedMAchievement
            .achievementId(UPDATED_ACHIEVEMENT_ID)
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .missionId(UPDATED_MISSION_ID);
        MAchievementDTO mAchievementDTO = mAchievementMapper.toDto(updatedMAchievement);

        restMAchievementMockMvc.perform(put("/api/m-achievements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAchievementDTO)))
            .andExpect(status().isOk());

        // Validate the MAchievement in the database
        List<MAchievement> mAchievementList = mAchievementRepository.findAll();
        assertThat(mAchievementList).hasSize(databaseSizeBeforeUpdate);
        MAchievement testMAchievement = mAchievementList.get(mAchievementList.size() - 1);
        assertThat(testMAchievement.getAchievementId()).isEqualTo(UPDATED_ACHIEVEMENT_ID);
        assertThat(testMAchievement.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMAchievement.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMAchievement.getMissionId()).isEqualTo(UPDATED_MISSION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMAchievement() throws Exception {
        int databaseSizeBeforeUpdate = mAchievementRepository.findAll().size();

        // Create the MAchievement
        MAchievementDTO mAchievementDTO = mAchievementMapper.toDto(mAchievement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMAchievementMockMvc.perform(put("/api/m-achievements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAchievementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAchievement in the database
        List<MAchievement> mAchievementList = mAchievementRepository.findAll();
        assertThat(mAchievementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAchievement() throws Exception {
        // Initialize the database
        mAchievementRepository.saveAndFlush(mAchievement);

        int databaseSizeBeforeDelete = mAchievementRepository.findAll().size();

        // Delete the mAchievement
        restMAchievementMockMvc.perform(delete("/api/m-achievements/{id}", mAchievement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAchievement> mAchievementList = mAchievementRepository.findAll();
        assertThat(mAchievementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAchievement.class);
        MAchievement mAchievement1 = new MAchievement();
        mAchievement1.setId(1L);
        MAchievement mAchievement2 = new MAchievement();
        mAchievement2.setId(mAchievement1.getId());
        assertThat(mAchievement1).isEqualTo(mAchievement2);
        mAchievement2.setId(2L);
        assertThat(mAchievement1).isNotEqualTo(mAchievement2);
        mAchievement1.setId(null);
        assertThat(mAchievement1).isNotEqualTo(mAchievement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAchievementDTO.class);
        MAchievementDTO mAchievementDTO1 = new MAchievementDTO();
        mAchievementDTO1.setId(1L);
        MAchievementDTO mAchievementDTO2 = new MAchievementDTO();
        assertThat(mAchievementDTO1).isNotEqualTo(mAchievementDTO2);
        mAchievementDTO2.setId(mAchievementDTO1.getId());
        assertThat(mAchievementDTO1).isEqualTo(mAchievementDTO2);
        mAchievementDTO2.setId(2L);
        assertThat(mAchievementDTO1).isNotEqualTo(mAchievementDTO2);
        mAchievementDTO1.setId(null);
        assertThat(mAchievementDTO1).isNotEqualTo(mAchievementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mAchievementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mAchievementMapper.fromId(null)).isNull();
    }
}
