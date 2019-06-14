package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTargetFormationGroup;
import io.shm.tsubasa.domain.MFormation;
import io.shm.tsubasa.repository.MTargetFormationGroupRepository;
import io.shm.tsubasa.service.MTargetFormationGroupService;
import io.shm.tsubasa.service.dto.MTargetFormationGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetFormationGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTargetFormationGroupCriteria;
import io.shm.tsubasa.service.MTargetFormationGroupQueryService;

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
 * Integration tests for the {@Link MTargetFormationGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTargetFormationGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_FORMATION_ID = 1;
    private static final Integer UPDATED_FORMATION_ID = 2;

    @Autowired
    private MTargetFormationGroupRepository mTargetFormationGroupRepository;

    @Autowired
    private MTargetFormationGroupMapper mTargetFormationGroupMapper;

    @Autowired
    private MTargetFormationGroupService mTargetFormationGroupService;

    @Autowired
    private MTargetFormationGroupQueryService mTargetFormationGroupQueryService;

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

    private MockMvc restMTargetFormationGroupMockMvc;

    private MTargetFormationGroup mTargetFormationGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTargetFormationGroupResource mTargetFormationGroupResource = new MTargetFormationGroupResource(mTargetFormationGroupService, mTargetFormationGroupQueryService);
        this.restMTargetFormationGroupMockMvc = MockMvcBuilders.standaloneSetup(mTargetFormationGroupResource)
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
    public static MTargetFormationGroup createEntity(EntityManager em) {
        MTargetFormationGroup mTargetFormationGroup = new MTargetFormationGroup()
            .groupId(DEFAULT_GROUP_ID)
            .formationId(DEFAULT_FORMATION_ID);
        // Add required entity
        MFormation mFormation;
        if (TestUtil.findAll(em, MFormation.class).isEmpty()) {
            mFormation = MFormationResourceIT.createEntity(em);
            em.persist(mFormation);
            em.flush();
        } else {
            mFormation = TestUtil.findAll(em, MFormation.class).get(0);
        }
        mTargetFormationGroup.setId(mFormation);
        return mTargetFormationGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTargetFormationGroup createUpdatedEntity(EntityManager em) {
        MTargetFormationGroup mTargetFormationGroup = new MTargetFormationGroup()
            .groupId(UPDATED_GROUP_ID)
            .formationId(UPDATED_FORMATION_ID);
        // Add required entity
        MFormation mFormation;
        if (TestUtil.findAll(em, MFormation.class).isEmpty()) {
            mFormation = MFormationResourceIT.createUpdatedEntity(em);
            em.persist(mFormation);
            em.flush();
        } else {
            mFormation = TestUtil.findAll(em, MFormation.class).get(0);
        }
        mTargetFormationGroup.setId(mFormation);
        return mTargetFormationGroup;
    }

    @BeforeEach
    public void initTest() {
        mTargetFormationGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTargetFormationGroup() throws Exception {
        int databaseSizeBeforeCreate = mTargetFormationGroupRepository.findAll().size();

        // Create the MTargetFormationGroup
        MTargetFormationGroupDTO mTargetFormationGroupDTO = mTargetFormationGroupMapper.toDto(mTargetFormationGroup);
        restMTargetFormationGroupMockMvc.perform(post("/api/m-target-formation-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetFormationGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTargetFormationGroup in the database
        List<MTargetFormationGroup> mTargetFormationGroupList = mTargetFormationGroupRepository.findAll();
        assertThat(mTargetFormationGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTargetFormationGroup testMTargetFormationGroup = mTargetFormationGroupList.get(mTargetFormationGroupList.size() - 1);
        assertThat(testMTargetFormationGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTargetFormationGroup.getFormationId()).isEqualTo(DEFAULT_FORMATION_ID);
    }

    @Test
    @Transactional
    public void createMTargetFormationGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTargetFormationGroupRepository.findAll().size();

        // Create the MTargetFormationGroup with an existing ID
        mTargetFormationGroup.setId(1L);
        MTargetFormationGroupDTO mTargetFormationGroupDTO = mTargetFormationGroupMapper.toDto(mTargetFormationGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTargetFormationGroupMockMvc.perform(post("/api/m-target-formation-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetFormationGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetFormationGroup in the database
        List<MTargetFormationGroup> mTargetFormationGroupList = mTargetFormationGroupRepository.findAll();
        assertThat(mTargetFormationGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetFormationGroupRepository.findAll().size();
        // set the field null
        mTargetFormationGroup.setGroupId(null);

        // Create the MTargetFormationGroup, which fails.
        MTargetFormationGroupDTO mTargetFormationGroupDTO = mTargetFormationGroupMapper.toDto(mTargetFormationGroup);

        restMTargetFormationGroupMockMvc.perform(post("/api/m-target-formation-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetFormationGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetFormationGroup> mTargetFormationGroupList = mTargetFormationGroupRepository.findAll();
        assertThat(mTargetFormationGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFormationIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetFormationGroupRepository.findAll().size();
        // set the field null
        mTargetFormationGroup.setFormationId(null);

        // Create the MTargetFormationGroup, which fails.
        MTargetFormationGroupDTO mTargetFormationGroupDTO = mTargetFormationGroupMapper.toDto(mTargetFormationGroup);

        restMTargetFormationGroupMockMvc.perform(post("/api/m-target-formation-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetFormationGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetFormationGroup> mTargetFormationGroupList = mTargetFormationGroupRepository.findAll();
        assertThat(mTargetFormationGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroups() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList
        restMTargetFormationGroupMockMvc.perform(get("/api/m-target-formation-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetFormationGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].formationId").value(hasItem(DEFAULT_FORMATION_ID)));
    }
    
    @Test
    @Transactional
    public void getMTargetFormationGroup() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get the mTargetFormationGroup
        restMTargetFormationGroupMockMvc.perform(get("/api/m-target-formation-groups/{id}", mTargetFormationGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTargetFormationGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.formationId").value(DEFAULT_FORMATION_ID));
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTargetFormationGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTargetFormationGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetFormationGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTargetFormationGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTargetFormationGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetFormationGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where groupId is not null
        defaultMTargetFormationGroupShouldBeFound("groupId.specified=true");

        // Get all the mTargetFormationGroupList where groupId is null
        defaultMTargetFormationGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTargetFormationGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetFormationGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTargetFormationGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTargetFormationGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetFormationGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTargetFormationGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByFormationIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where formationId equals to DEFAULT_FORMATION_ID
        defaultMTargetFormationGroupShouldBeFound("formationId.equals=" + DEFAULT_FORMATION_ID);

        // Get all the mTargetFormationGroupList where formationId equals to UPDATED_FORMATION_ID
        defaultMTargetFormationGroupShouldNotBeFound("formationId.equals=" + UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByFormationIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where formationId in DEFAULT_FORMATION_ID or UPDATED_FORMATION_ID
        defaultMTargetFormationGroupShouldBeFound("formationId.in=" + DEFAULT_FORMATION_ID + "," + UPDATED_FORMATION_ID);

        // Get all the mTargetFormationGroupList where formationId equals to UPDATED_FORMATION_ID
        defaultMTargetFormationGroupShouldNotBeFound("formationId.in=" + UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByFormationIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where formationId is not null
        defaultMTargetFormationGroupShouldBeFound("formationId.specified=true");

        // Get all the mTargetFormationGroupList where formationId is null
        defaultMTargetFormationGroupShouldNotBeFound("formationId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByFormationIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where formationId greater than or equals to DEFAULT_FORMATION_ID
        defaultMTargetFormationGroupShouldBeFound("formationId.greaterOrEqualThan=" + DEFAULT_FORMATION_ID);

        // Get all the mTargetFormationGroupList where formationId greater than or equals to UPDATED_FORMATION_ID
        defaultMTargetFormationGroupShouldNotBeFound("formationId.greaterOrEqualThan=" + UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByFormationIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        // Get all the mTargetFormationGroupList where formationId less than or equals to DEFAULT_FORMATION_ID
        defaultMTargetFormationGroupShouldNotBeFound("formationId.lessThan=" + DEFAULT_FORMATION_ID);

        // Get all the mTargetFormationGroupList where formationId less than or equals to UPDATED_FORMATION_ID
        defaultMTargetFormationGroupShouldBeFound("formationId.lessThan=" + UPDATED_FORMATION_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetFormationGroupsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MFormation id = mTargetFormationGroup.getId();
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);
        Long idId = id.getId();

        // Get all the mTargetFormationGroupList where id equals to idId
        defaultMTargetFormationGroupShouldBeFound("idId.equals=" + idId);

        // Get all the mTargetFormationGroupList where id equals to idId + 1
        defaultMTargetFormationGroupShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTargetFormationGroupShouldBeFound(String filter) throws Exception {
        restMTargetFormationGroupMockMvc.perform(get("/api/m-target-formation-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetFormationGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].formationId").value(hasItem(DEFAULT_FORMATION_ID)));

        // Check, that the count call also returns 1
        restMTargetFormationGroupMockMvc.perform(get("/api/m-target-formation-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTargetFormationGroupShouldNotBeFound(String filter) throws Exception {
        restMTargetFormationGroupMockMvc.perform(get("/api/m-target-formation-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTargetFormationGroupMockMvc.perform(get("/api/m-target-formation-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTargetFormationGroup() throws Exception {
        // Get the mTargetFormationGroup
        restMTargetFormationGroupMockMvc.perform(get("/api/m-target-formation-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTargetFormationGroup() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        int databaseSizeBeforeUpdate = mTargetFormationGroupRepository.findAll().size();

        // Update the mTargetFormationGroup
        MTargetFormationGroup updatedMTargetFormationGroup = mTargetFormationGroupRepository.findById(mTargetFormationGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTargetFormationGroup are not directly saved in db
        em.detach(updatedMTargetFormationGroup);
        updatedMTargetFormationGroup
            .groupId(UPDATED_GROUP_ID)
            .formationId(UPDATED_FORMATION_ID);
        MTargetFormationGroupDTO mTargetFormationGroupDTO = mTargetFormationGroupMapper.toDto(updatedMTargetFormationGroup);

        restMTargetFormationGroupMockMvc.perform(put("/api/m-target-formation-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetFormationGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTargetFormationGroup in the database
        List<MTargetFormationGroup> mTargetFormationGroupList = mTargetFormationGroupRepository.findAll();
        assertThat(mTargetFormationGroupList).hasSize(databaseSizeBeforeUpdate);
        MTargetFormationGroup testMTargetFormationGroup = mTargetFormationGroupList.get(mTargetFormationGroupList.size() - 1);
        assertThat(testMTargetFormationGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTargetFormationGroup.getFormationId()).isEqualTo(UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTargetFormationGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTargetFormationGroupRepository.findAll().size();

        // Create the MTargetFormationGroup
        MTargetFormationGroupDTO mTargetFormationGroupDTO = mTargetFormationGroupMapper.toDto(mTargetFormationGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTargetFormationGroupMockMvc.perform(put("/api/m-target-formation-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetFormationGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetFormationGroup in the database
        List<MTargetFormationGroup> mTargetFormationGroupList = mTargetFormationGroupRepository.findAll();
        assertThat(mTargetFormationGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTargetFormationGroup() throws Exception {
        // Initialize the database
        mTargetFormationGroupRepository.saveAndFlush(mTargetFormationGroup);

        int databaseSizeBeforeDelete = mTargetFormationGroupRepository.findAll().size();

        // Delete the mTargetFormationGroup
        restMTargetFormationGroupMockMvc.perform(delete("/api/m-target-formation-groups/{id}", mTargetFormationGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTargetFormationGroup> mTargetFormationGroupList = mTargetFormationGroupRepository.findAll();
        assertThat(mTargetFormationGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetFormationGroup.class);
        MTargetFormationGroup mTargetFormationGroup1 = new MTargetFormationGroup();
        mTargetFormationGroup1.setId(1L);
        MTargetFormationGroup mTargetFormationGroup2 = new MTargetFormationGroup();
        mTargetFormationGroup2.setId(mTargetFormationGroup1.getId());
        assertThat(mTargetFormationGroup1).isEqualTo(mTargetFormationGroup2);
        mTargetFormationGroup2.setId(2L);
        assertThat(mTargetFormationGroup1).isNotEqualTo(mTargetFormationGroup2);
        mTargetFormationGroup1.setId(null);
        assertThat(mTargetFormationGroup1).isNotEqualTo(mTargetFormationGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetFormationGroupDTO.class);
        MTargetFormationGroupDTO mTargetFormationGroupDTO1 = new MTargetFormationGroupDTO();
        mTargetFormationGroupDTO1.setId(1L);
        MTargetFormationGroupDTO mTargetFormationGroupDTO2 = new MTargetFormationGroupDTO();
        assertThat(mTargetFormationGroupDTO1).isNotEqualTo(mTargetFormationGroupDTO2);
        mTargetFormationGroupDTO2.setId(mTargetFormationGroupDTO1.getId());
        assertThat(mTargetFormationGroupDTO1).isEqualTo(mTargetFormationGroupDTO2);
        mTargetFormationGroupDTO2.setId(2L);
        assertThat(mTargetFormationGroupDTO1).isNotEqualTo(mTargetFormationGroupDTO2);
        mTargetFormationGroupDTO1.setId(null);
        assertThat(mTargetFormationGroupDTO1).isNotEqualTo(mTargetFormationGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTargetFormationGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTargetFormationGroupMapper.fromId(null)).isNull();
    }
}
