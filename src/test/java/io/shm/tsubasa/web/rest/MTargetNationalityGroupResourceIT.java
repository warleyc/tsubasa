package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTargetNationalityGroup;
import io.shm.tsubasa.domain.MNationality;
import io.shm.tsubasa.repository.MTargetNationalityGroupRepository;
import io.shm.tsubasa.service.MTargetNationalityGroupService;
import io.shm.tsubasa.service.dto.MTargetNationalityGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetNationalityGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTargetNationalityGroupCriteria;
import io.shm.tsubasa.service.MTargetNationalityGroupQueryService;

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
 * Integration tests for the {@Link MTargetNationalityGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTargetNationalityGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_NATIONALITY_ID = 1;
    private static final Integer UPDATED_NATIONALITY_ID = 2;

    @Autowired
    private MTargetNationalityGroupRepository mTargetNationalityGroupRepository;

    @Autowired
    private MTargetNationalityGroupMapper mTargetNationalityGroupMapper;

    @Autowired
    private MTargetNationalityGroupService mTargetNationalityGroupService;

    @Autowired
    private MTargetNationalityGroupQueryService mTargetNationalityGroupQueryService;

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

    private MockMvc restMTargetNationalityGroupMockMvc;

    private MTargetNationalityGroup mTargetNationalityGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTargetNationalityGroupResource mTargetNationalityGroupResource = new MTargetNationalityGroupResource(mTargetNationalityGroupService, mTargetNationalityGroupQueryService);
        this.restMTargetNationalityGroupMockMvc = MockMvcBuilders.standaloneSetup(mTargetNationalityGroupResource)
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
    public static MTargetNationalityGroup createEntity(EntityManager em) {
        MTargetNationalityGroup mTargetNationalityGroup = new MTargetNationalityGroup()
            .groupId(DEFAULT_GROUP_ID)
            .nationalityId(DEFAULT_NATIONALITY_ID);
        // Add required entity
        MNationality mNationality;
        if (TestUtil.findAll(em, MNationality.class).isEmpty()) {
            mNationality = MNationalityResourceIT.createEntity(em);
            em.persist(mNationality);
            em.flush();
        } else {
            mNationality = TestUtil.findAll(em, MNationality.class).get(0);
        }
        mTargetNationalityGroup.setId(mNationality);
        return mTargetNationalityGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTargetNationalityGroup createUpdatedEntity(EntityManager em) {
        MTargetNationalityGroup mTargetNationalityGroup = new MTargetNationalityGroup()
            .groupId(UPDATED_GROUP_ID)
            .nationalityId(UPDATED_NATIONALITY_ID);
        // Add required entity
        MNationality mNationality;
        if (TestUtil.findAll(em, MNationality.class).isEmpty()) {
            mNationality = MNationalityResourceIT.createUpdatedEntity(em);
            em.persist(mNationality);
            em.flush();
        } else {
            mNationality = TestUtil.findAll(em, MNationality.class).get(0);
        }
        mTargetNationalityGroup.setId(mNationality);
        return mTargetNationalityGroup;
    }

    @BeforeEach
    public void initTest() {
        mTargetNationalityGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTargetNationalityGroup() throws Exception {
        int databaseSizeBeforeCreate = mTargetNationalityGroupRepository.findAll().size();

        // Create the MTargetNationalityGroup
        MTargetNationalityGroupDTO mTargetNationalityGroupDTO = mTargetNationalityGroupMapper.toDto(mTargetNationalityGroup);
        restMTargetNationalityGroupMockMvc.perform(post("/api/m-target-nationality-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetNationalityGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTargetNationalityGroup in the database
        List<MTargetNationalityGroup> mTargetNationalityGroupList = mTargetNationalityGroupRepository.findAll();
        assertThat(mTargetNationalityGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTargetNationalityGroup testMTargetNationalityGroup = mTargetNationalityGroupList.get(mTargetNationalityGroupList.size() - 1);
        assertThat(testMTargetNationalityGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTargetNationalityGroup.getNationalityId()).isEqualTo(DEFAULT_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void createMTargetNationalityGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTargetNationalityGroupRepository.findAll().size();

        // Create the MTargetNationalityGroup with an existing ID
        mTargetNationalityGroup.setId(1L);
        MTargetNationalityGroupDTO mTargetNationalityGroupDTO = mTargetNationalityGroupMapper.toDto(mTargetNationalityGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTargetNationalityGroupMockMvc.perform(post("/api/m-target-nationality-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetNationalityGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetNationalityGroup in the database
        List<MTargetNationalityGroup> mTargetNationalityGroupList = mTargetNationalityGroupRepository.findAll();
        assertThat(mTargetNationalityGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetNationalityGroupRepository.findAll().size();
        // set the field null
        mTargetNationalityGroup.setGroupId(null);

        // Create the MTargetNationalityGroup, which fails.
        MTargetNationalityGroupDTO mTargetNationalityGroupDTO = mTargetNationalityGroupMapper.toDto(mTargetNationalityGroup);

        restMTargetNationalityGroupMockMvc.perform(post("/api/m-target-nationality-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetNationalityGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetNationalityGroup> mTargetNationalityGroupList = mTargetNationalityGroupRepository.findAll();
        assertThat(mTargetNationalityGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNationalityIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetNationalityGroupRepository.findAll().size();
        // set the field null
        mTargetNationalityGroup.setNationalityId(null);

        // Create the MTargetNationalityGroup, which fails.
        MTargetNationalityGroupDTO mTargetNationalityGroupDTO = mTargetNationalityGroupMapper.toDto(mTargetNationalityGroup);

        restMTargetNationalityGroupMockMvc.perform(post("/api/m-target-nationality-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetNationalityGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetNationalityGroup> mTargetNationalityGroupList = mTargetNationalityGroupRepository.findAll();
        assertThat(mTargetNationalityGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroups() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList
        restMTargetNationalityGroupMockMvc.perform(get("/api/m-target-nationality-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetNationalityGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].nationalityId").value(hasItem(DEFAULT_NATIONALITY_ID)));
    }
    
    @Test
    @Transactional
    public void getMTargetNationalityGroup() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get the mTargetNationalityGroup
        restMTargetNationalityGroupMockMvc.perform(get("/api/m-target-nationality-groups/{id}", mTargetNationalityGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTargetNationalityGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.nationalityId").value(DEFAULT_NATIONALITY_ID));
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTargetNationalityGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTargetNationalityGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetNationalityGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTargetNationalityGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTargetNationalityGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetNationalityGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where groupId is not null
        defaultMTargetNationalityGroupShouldBeFound("groupId.specified=true");

        // Get all the mTargetNationalityGroupList where groupId is null
        defaultMTargetNationalityGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTargetNationalityGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetNationalityGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTargetNationalityGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTargetNationalityGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetNationalityGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTargetNationalityGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByNationalityIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where nationalityId equals to DEFAULT_NATIONALITY_ID
        defaultMTargetNationalityGroupShouldBeFound("nationalityId.equals=" + DEFAULT_NATIONALITY_ID);

        // Get all the mTargetNationalityGroupList where nationalityId equals to UPDATED_NATIONALITY_ID
        defaultMTargetNationalityGroupShouldNotBeFound("nationalityId.equals=" + UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByNationalityIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where nationalityId in DEFAULT_NATIONALITY_ID or UPDATED_NATIONALITY_ID
        defaultMTargetNationalityGroupShouldBeFound("nationalityId.in=" + DEFAULT_NATIONALITY_ID + "," + UPDATED_NATIONALITY_ID);

        // Get all the mTargetNationalityGroupList where nationalityId equals to UPDATED_NATIONALITY_ID
        defaultMTargetNationalityGroupShouldNotBeFound("nationalityId.in=" + UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByNationalityIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where nationalityId is not null
        defaultMTargetNationalityGroupShouldBeFound("nationalityId.specified=true");

        // Get all the mTargetNationalityGroupList where nationalityId is null
        defaultMTargetNationalityGroupShouldNotBeFound("nationalityId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByNationalityIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where nationalityId greater than or equals to DEFAULT_NATIONALITY_ID
        defaultMTargetNationalityGroupShouldBeFound("nationalityId.greaterOrEqualThan=" + DEFAULT_NATIONALITY_ID);

        // Get all the mTargetNationalityGroupList where nationalityId greater than or equals to UPDATED_NATIONALITY_ID
        defaultMTargetNationalityGroupShouldNotBeFound("nationalityId.greaterOrEqualThan=" + UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByNationalityIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        // Get all the mTargetNationalityGroupList where nationalityId less than or equals to DEFAULT_NATIONALITY_ID
        defaultMTargetNationalityGroupShouldNotBeFound("nationalityId.lessThan=" + DEFAULT_NATIONALITY_ID);

        // Get all the mTargetNationalityGroupList where nationalityId less than or equals to UPDATED_NATIONALITY_ID
        defaultMTargetNationalityGroupShouldBeFound("nationalityId.lessThan=" + UPDATED_NATIONALITY_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetNationalityGroupsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MNationality id = mTargetNationalityGroup.getId();
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);
        Long idId = id.getId();

        // Get all the mTargetNationalityGroupList where id equals to idId
        defaultMTargetNationalityGroupShouldBeFound("idId.equals=" + idId);

        // Get all the mTargetNationalityGroupList where id equals to idId + 1
        defaultMTargetNationalityGroupShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTargetNationalityGroupShouldBeFound(String filter) throws Exception {
        restMTargetNationalityGroupMockMvc.perform(get("/api/m-target-nationality-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetNationalityGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].nationalityId").value(hasItem(DEFAULT_NATIONALITY_ID)));

        // Check, that the count call also returns 1
        restMTargetNationalityGroupMockMvc.perform(get("/api/m-target-nationality-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTargetNationalityGroupShouldNotBeFound(String filter) throws Exception {
        restMTargetNationalityGroupMockMvc.perform(get("/api/m-target-nationality-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTargetNationalityGroupMockMvc.perform(get("/api/m-target-nationality-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTargetNationalityGroup() throws Exception {
        // Get the mTargetNationalityGroup
        restMTargetNationalityGroupMockMvc.perform(get("/api/m-target-nationality-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTargetNationalityGroup() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        int databaseSizeBeforeUpdate = mTargetNationalityGroupRepository.findAll().size();

        // Update the mTargetNationalityGroup
        MTargetNationalityGroup updatedMTargetNationalityGroup = mTargetNationalityGroupRepository.findById(mTargetNationalityGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTargetNationalityGroup are not directly saved in db
        em.detach(updatedMTargetNationalityGroup);
        updatedMTargetNationalityGroup
            .groupId(UPDATED_GROUP_ID)
            .nationalityId(UPDATED_NATIONALITY_ID);
        MTargetNationalityGroupDTO mTargetNationalityGroupDTO = mTargetNationalityGroupMapper.toDto(updatedMTargetNationalityGroup);

        restMTargetNationalityGroupMockMvc.perform(put("/api/m-target-nationality-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetNationalityGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTargetNationalityGroup in the database
        List<MTargetNationalityGroup> mTargetNationalityGroupList = mTargetNationalityGroupRepository.findAll();
        assertThat(mTargetNationalityGroupList).hasSize(databaseSizeBeforeUpdate);
        MTargetNationalityGroup testMTargetNationalityGroup = mTargetNationalityGroupList.get(mTargetNationalityGroupList.size() - 1);
        assertThat(testMTargetNationalityGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTargetNationalityGroup.getNationalityId()).isEqualTo(UPDATED_NATIONALITY_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTargetNationalityGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTargetNationalityGroupRepository.findAll().size();

        // Create the MTargetNationalityGroup
        MTargetNationalityGroupDTO mTargetNationalityGroupDTO = mTargetNationalityGroupMapper.toDto(mTargetNationalityGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTargetNationalityGroupMockMvc.perform(put("/api/m-target-nationality-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetNationalityGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetNationalityGroup in the database
        List<MTargetNationalityGroup> mTargetNationalityGroupList = mTargetNationalityGroupRepository.findAll();
        assertThat(mTargetNationalityGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTargetNationalityGroup() throws Exception {
        // Initialize the database
        mTargetNationalityGroupRepository.saveAndFlush(mTargetNationalityGroup);

        int databaseSizeBeforeDelete = mTargetNationalityGroupRepository.findAll().size();

        // Delete the mTargetNationalityGroup
        restMTargetNationalityGroupMockMvc.perform(delete("/api/m-target-nationality-groups/{id}", mTargetNationalityGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTargetNationalityGroup> mTargetNationalityGroupList = mTargetNationalityGroupRepository.findAll();
        assertThat(mTargetNationalityGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetNationalityGroup.class);
        MTargetNationalityGroup mTargetNationalityGroup1 = new MTargetNationalityGroup();
        mTargetNationalityGroup1.setId(1L);
        MTargetNationalityGroup mTargetNationalityGroup2 = new MTargetNationalityGroup();
        mTargetNationalityGroup2.setId(mTargetNationalityGroup1.getId());
        assertThat(mTargetNationalityGroup1).isEqualTo(mTargetNationalityGroup2);
        mTargetNationalityGroup2.setId(2L);
        assertThat(mTargetNationalityGroup1).isNotEqualTo(mTargetNationalityGroup2);
        mTargetNationalityGroup1.setId(null);
        assertThat(mTargetNationalityGroup1).isNotEqualTo(mTargetNationalityGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetNationalityGroupDTO.class);
        MTargetNationalityGroupDTO mTargetNationalityGroupDTO1 = new MTargetNationalityGroupDTO();
        mTargetNationalityGroupDTO1.setId(1L);
        MTargetNationalityGroupDTO mTargetNationalityGroupDTO2 = new MTargetNationalityGroupDTO();
        assertThat(mTargetNationalityGroupDTO1).isNotEqualTo(mTargetNationalityGroupDTO2);
        mTargetNationalityGroupDTO2.setId(mTargetNationalityGroupDTO1.getId());
        assertThat(mTargetNationalityGroupDTO1).isEqualTo(mTargetNationalityGroupDTO2);
        mTargetNationalityGroupDTO2.setId(2L);
        assertThat(mTargetNationalityGroupDTO1).isNotEqualTo(mTargetNationalityGroupDTO2);
        mTargetNationalityGroupDTO1.setId(null);
        assertThat(mTargetNationalityGroupDTO1).isNotEqualTo(mTargetNationalityGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTargetNationalityGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTargetNationalityGroupMapper.fromId(null)).isNull();
    }
}
