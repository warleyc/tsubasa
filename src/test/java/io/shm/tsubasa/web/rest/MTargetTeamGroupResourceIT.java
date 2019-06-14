package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTargetTeamGroup;
import io.shm.tsubasa.domain.MTeam;
import io.shm.tsubasa.repository.MTargetTeamGroupRepository;
import io.shm.tsubasa.service.MTargetTeamGroupService;
import io.shm.tsubasa.service.dto.MTargetTeamGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetTeamGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTargetTeamGroupCriteria;
import io.shm.tsubasa.service.MTargetTeamGroupQueryService;

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
 * Integration tests for the {@Link MTargetTeamGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTargetTeamGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_TEAM_ID = 1;
    private static final Integer UPDATED_TEAM_ID = 2;

    @Autowired
    private MTargetTeamGroupRepository mTargetTeamGroupRepository;

    @Autowired
    private MTargetTeamGroupMapper mTargetTeamGroupMapper;

    @Autowired
    private MTargetTeamGroupService mTargetTeamGroupService;

    @Autowired
    private MTargetTeamGroupQueryService mTargetTeamGroupQueryService;

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

    private MockMvc restMTargetTeamGroupMockMvc;

    private MTargetTeamGroup mTargetTeamGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTargetTeamGroupResource mTargetTeamGroupResource = new MTargetTeamGroupResource(mTargetTeamGroupService, mTargetTeamGroupQueryService);
        this.restMTargetTeamGroupMockMvc = MockMvcBuilders.standaloneSetup(mTargetTeamGroupResource)
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
    public static MTargetTeamGroup createEntity(EntityManager em) {
        MTargetTeamGroup mTargetTeamGroup = new MTargetTeamGroup()
            .groupId(DEFAULT_GROUP_ID)
            .teamId(DEFAULT_TEAM_ID);
        // Add required entity
        MTeam mTeam;
        if (TestUtil.findAll(em, MTeam.class).isEmpty()) {
            mTeam = MTeamResourceIT.createEntity(em);
            em.persist(mTeam);
            em.flush();
        } else {
            mTeam = TestUtil.findAll(em, MTeam.class).get(0);
        }
        mTargetTeamGroup.setId(mTeam);
        return mTargetTeamGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTargetTeamGroup createUpdatedEntity(EntityManager em) {
        MTargetTeamGroup mTargetTeamGroup = new MTargetTeamGroup()
            .groupId(UPDATED_GROUP_ID)
            .teamId(UPDATED_TEAM_ID);
        // Add required entity
        MTeam mTeam;
        if (TestUtil.findAll(em, MTeam.class).isEmpty()) {
            mTeam = MTeamResourceIT.createUpdatedEntity(em);
            em.persist(mTeam);
            em.flush();
        } else {
            mTeam = TestUtil.findAll(em, MTeam.class).get(0);
        }
        mTargetTeamGroup.setId(mTeam);
        return mTargetTeamGroup;
    }

    @BeforeEach
    public void initTest() {
        mTargetTeamGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTargetTeamGroup() throws Exception {
        int databaseSizeBeforeCreate = mTargetTeamGroupRepository.findAll().size();

        // Create the MTargetTeamGroup
        MTargetTeamGroupDTO mTargetTeamGroupDTO = mTargetTeamGroupMapper.toDto(mTargetTeamGroup);
        restMTargetTeamGroupMockMvc.perform(post("/api/m-target-team-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTeamGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTargetTeamGroup in the database
        List<MTargetTeamGroup> mTargetTeamGroupList = mTargetTeamGroupRepository.findAll();
        assertThat(mTargetTeamGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTargetTeamGroup testMTargetTeamGroup = mTargetTeamGroupList.get(mTargetTeamGroupList.size() - 1);
        assertThat(testMTargetTeamGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTargetTeamGroup.getTeamId()).isEqualTo(DEFAULT_TEAM_ID);
    }

    @Test
    @Transactional
    public void createMTargetTeamGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTargetTeamGroupRepository.findAll().size();

        // Create the MTargetTeamGroup with an existing ID
        mTargetTeamGroup.setId(1L);
        MTargetTeamGroupDTO mTargetTeamGroupDTO = mTargetTeamGroupMapper.toDto(mTargetTeamGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTargetTeamGroupMockMvc.perform(post("/api/m-target-team-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTeamGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetTeamGroup in the database
        List<MTargetTeamGroup> mTargetTeamGroupList = mTargetTeamGroupRepository.findAll();
        assertThat(mTargetTeamGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetTeamGroupRepository.findAll().size();
        // set the field null
        mTargetTeamGroup.setGroupId(null);

        // Create the MTargetTeamGroup, which fails.
        MTargetTeamGroupDTO mTargetTeamGroupDTO = mTargetTeamGroupMapper.toDto(mTargetTeamGroup);

        restMTargetTeamGroupMockMvc.perform(post("/api/m-target-team-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTeamGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetTeamGroup> mTargetTeamGroupList = mTargetTeamGroupRepository.findAll();
        assertThat(mTargetTeamGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeamIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetTeamGroupRepository.findAll().size();
        // set the field null
        mTargetTeamGroup.setTeamId(null);

        // Create the MTargetTeamGroup, which fails.
        MTargetTeamGroupDTO mTargetTeamGroupDTO = mTargetTeamGroupMapper.toDto(mTargetTeamGroup);

        restMTargetTeamGroupMockMvc.perform(post("/api/m-target-team-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTeamGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetTeamGroup> mTargetTeamGroupList = mTargetTeamGroupRepository.findAll();
        assertThat(mTargetTeamGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroups() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList
        restMTargetTeamGroupMockMvc.perform(get("/api/m-target-team-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetTeamGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)));
    }
    
    @Test
    @Transactional
    public void getMTargetTeamGroup() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get the mTargetTeamGroup
        restMTargetTeamGroupMockMvc.perform(get("/api/m-target-team-groups/{id}", mTargetTeamGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTargetTeamGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.teamId").value(DEFAULT_TEAM_ID));
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTargetTeamGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTargetTeamGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetTeamGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTargetTeamGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTargetTeamGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetTeamGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where groupId is not null
        defaultMTargetTeamGroupShouldBeFound("groupId.specified=true");

        // Get all the mTargetTeamGroupList where groupId is null
        defaultMTargetTeamGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTargetTeamGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetTeamGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTargetTeamGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTargetTeamGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetTeamGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTargetTeamGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByTeamIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where teamId equals to DEFAULT_TEAM_ID
        defaultMTargetTeamGroupShouldBeFound("teamId.equals=" + DEFAULT_TEAM_ID);

        // Get all the mTargetTeamGroupList where teamId equals to UPDATED_TEAM_ID
        defaultMTargetTeamGroupShouldNotBeFound("teamId.equals=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByTeamIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where teamId in DEFAULT_TEAM_ID or UPDATED_TEAM_ID
        defaultMTargetTeamGroupShouldBeFound("teamId.in=" + DEFAULT_TEAM_ID + "," + UPDATED_TEAM_ID);

        // Get all the mTargetTeamGroupList where teamId equals to UPDATED_TEAM_ID
        defaultMTargetTeamGroupShouldNotBeFound("teamId.in=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByTeamIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where teamId is not null
        defaultMTargetTeamGroupShouldBeFound("teamId.specified=true");

        // Get all the mTargetTeamGroupList where teamId is null
        defaultMTargetTeamGroupShouldNotBeFound("teamId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByTeamIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where teamId greater than or equals to DEFAULT_TEAM_ID
        defaultMTargetTeamGroupShouldBeFound("teamId.greaterOrEqualThan=" + DEFAULT_TEAM_ID);

        // Get all the mTargetTeamGroupList where teamId greater than or equals to UPDATED_TEAM_ID
        defaultMTargetTeamGroupShouldNotBeFound("teamId.greaterOrEqualThan=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByTeamIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        // Get all the mTargetTeamGroupList where teamId less than or equals to DEFAULT_TEAM_ID
        defaultMTargetTeamGroupShouldNotBeFound("teamId.lessThan=" + DEFAULT_TEAM_ID);

        // Get all the mTargetTeamGroupList where teamId less than or equals to UPDATED_TEAM_ID
        defaultMTargetTeamGroupShouldBeFound("teamId.lessThan=" + UPDATED_TEAM_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetTeamGroupsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MTeam id = mTargetTeamGroup.getId();
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);
        Long idId = id.getId();

        // Get all the mTargetTeamGroupList where id equals to idId
        defaultMTargetTeamGroupShouldBeFound("idId.equals=" + idId);

        // Get all the mTargetTeamGroupList where id equals to idId + 1
        defaultMTargetTeamGroupShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTargetTeamGroupShouldBeFound(String filter) throws Exception {
        restMTargetTeamGroupMockMvc.perform(get("/api/m-target-team-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetTeamGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)));

        // Check, that the count call also returns 1
        restMTargetTeamGroupMockMvc.perform(get("/api/m-target-team-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTargetTeamGroupShouldNotBeFound(String filter) throws Exception {
        restMTargetTeamGroupMockMvc.perform(get("/api/m-target-team-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTargetTeamGroupMockMvc.perform(get("/api/m-target-team-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTargetTeamGroup() throws Exception {
        // Get the mTargetTeamGroup
        restMTargetTeamGroupMockMvc.perform(get("/api/m-target-team-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTargetTeamGroup() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        int databaseSizeBeforeUpdate = mTargetTeamGroupRepository.findAll().size();

        // Update the mTargetTeamGroup
        MTargetTeamGroup updatedMTargetTeamGroup = mTargetTeamGroupRepository.findById(mTargetTeamGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTargetTeamGroup are not directly saved in db
        em.detach(updatedMTargetTeamGroup);
        updatedMTargetTeamGroup
            .groupId(UPDATED_GROUP_ID)
            .teamId(UPDATED_TEAM_ID);
        MTargetTeamGroupDTO mTargetTeamGroupDTO = mTargetTeamGroupMapper.toDto(updatedMTargetTeamGroup);

        restMTargetTeamGroupMockMvc.perform(put("/api/m-target-team-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTeamGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTargetTeamGroup in the database
        List<MTargetTeamGroup> mTargetTeamGroupList = mTargetTeamGroupRepository.findAll();
        assertThat(mTargetTeamGroupList).hasSize(databaseSizeBeforeUpdate);
        MTargetTeamGroup testMTargetTeamGroup = mTargetTeamGroupList.get(mTargetTeamGroupList.size() - 1);
        assertThat(testMTargetTeamGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTargetTeamGroup.getTeamId()).isEqualTo(UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTargetTeamGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTargetTeamGroupRepository.findAll().size();

        // Create the MTargetTeamGroup
        MTargetTeamGroupDTO mTargetTeamGroupDTO = mTargetTeamGroupMapper.toDto(mTargetTeamGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTargetTeamGroupMockMvc.perform(put("/api/m-target-team-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTeamGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetTeamGroup in the database
        List<MTargetTeamGroup> mTargetTeamGroupList = mTargetTeamGroupRepository.findAll();
        assertThat(mTargetTeamGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTargetTeamGroup() throws Exception {
        // Initialize the database
        mTargetTeamGroupRepository.saveAndFlush(mTargetTeamGroup);

        int databaseSizeBeforeDelete = mTargetTeamGroupRepository.findAll().size();

        // Delete the mTargetTeamGroup
        restMTargetTeamGroupMockMvc.perform(delete("/api/m-target-team-groups/{id}", mTargetTeamGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTargetTeamGroup> mTargetTeamGroupList = mTargetTeamGroupRepository.findAll();
        assertThat(mTargetTeamGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetTeamGroup.class);
        MTargetTeamGroup mTargetTeamGroup1 = new MTargetTeamGroup();
        mTargetTeamGroup1.setId(1L);
        MTargetTeamGroup mTargetTeamGroup2 = new MTargetTeamGroup();
        mTargetTeamGroup2.setId(mTargetTeamGroup1.getId());
        assertThat(mTargetTeamGroup1).isEqualTo(mTargetTeamGroup2);
        mTargetTeamGroup2.setId(2L);
        assertThat(mTargetTeamGroup1).isNotEqualTo(mTargetTeamGroup2);
        mTargetTeamGroup1.setId(null);
        assertThat(mTargetTeamGroup1).isNotEqualTo(mTargetTeamGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetTeamGroupDTO.class);
        MTargetTeamGroupDTO mTargetTeamGroupDTO1 = new MTargetTeamGroupDTO();
        mTargetTeamGroupDTO1.setId(1L);
        MTargetTeamGroupDTO mTargetTeamGroupDTO2 = new MTargetTeamGroupDTO();
        assertThat(mTargetTeamGroupDTO1).isNotEqualTo(mTargetTeamGroupDTO2);
        mTargetTeamGroupDTO2.setId(mTargetTeamGroupDTO1.getId());
        assertThat(mTargetTeamGroupDTO1).isEqualTo(mTargetTeamGroupDTO2);
        mTargetTeamGroupDTO2.setId(2L);
        assertThat(mTargetTeamGroupDTO1).isNotEqualTo(mTargetTeamGroupDTO2);
        mTargetTeamGroupDTO1.setId(null);
        assertThat(mTargetTeamGroupDTO1).isNotEqualTo(mTargetTeamGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTargetTeamGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTargetTeamGroupMapper.fromId(null)).isNull();
    }
}
