package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTargetActionTypeGroup;
import io.shm.tsubasa.repository.MTargetActionTypeGroupRepository;
import io.shm.tsubasa.service.MTargetActionTypeGroupService;
import io.shm.tsubasa.service.dto.MTargetActionTypeGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetActionTypeGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTargetActionTypeGroupCriteria;
import io.shm.tsubasa.service.MTargetActionTypeGroupQueryService;

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
 * Integration tests for the {@Link MTargetActionTypeGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTargetActionTypeGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_COMMAND_TYPE = 1;
    private static final Integer UPDATED_COMMAND_TYPE = 2;

    @Autowired
    private MTargetActionTypeGroupRepository mTargetActionTypeGroupRepository;

    @Autowired
    private MTargetActionTypeGroupMapper mTargetActionTypeGroupMapper;

    @Autowired
    private MTargetActionTypeGroupService mTargetActionTypeGroupService;

    @Autowired
    private MTargetActionTypeGroupQueryService mTargetActionTypeGroupQueryService;

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

    private MockMvc restMTargetActionTypeGroupMockMvc;

    private MTargetActionTypeGroup mTargetActionTypeGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTargetActionTypeGroupResource mTargetActionTypeGroupResource = new MTargetActionTypeGroupResource(mTargetActionTypeGroupService, mTargetActionTypeGroupQueryService);
        this.restMTargetActionTypeGroupMockMvc = MockMvcBuilders.standaloneSetup(mTargetActionTypeGroupResource)
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
    public static MTargetActionTypeGroup createEntity(EntityManager em) {
        MTargetActionTypeGroup mTargetActionTypeGroup = new MTargetActionTypeGroup()
            .groupId(DEFAULT_GROUP_ID)
            .commandType(DEFAULT_COMMAND_TYPE);
        return mTargetActionTypeGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTargetActionTypeGroup createUpdatedEntity(EntityManager em) {
        MTargetActionTypeGroup mTargetActionTypeGroup = new MTargetActionTypeGroup()
            .groupId(UPDATED_GROUP_ID)
            .commandType(UPDATED_COMMAND_TYPE);
        return mTargetActionTypeGroup;
    }

    @BeforeEach
    public void initTest() {
        mTargetActionTypeGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTargetActionTypeGroup() throws Exception {
        int databaseSizeBeforeCreate = mTargetActionTypeGroupRepository.findAll().size();

        // Create the MTargetActionTypeGroup
        MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO = mTargetActionTypeGroupMapper.toDto(mTargetActionTypeGroup);
        restMTargetActionTypeGroupMockMvc.perform(post("/api/m-target-action-type-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionTypeGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTargetActionTypeGroup in the database
        List<MTargetActionTypeGroup> mTargetActionTypeGroupList = mTargetActionTypeGroupRepository.findAll();
        assertThat(mTargetActionTypeGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTargetActionTypeGroup testMTargetActionTypeGroup = mTargetActionTypeGroupList.get(mTargetActionTypeGroupList.size() - 1);
        assertThat(testMTargetActionTypeGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTargetActionTypeGroup.getCommandType()).isEqualTo(DEFAULT_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void createMTargetActionTypeGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTargetActionTypeGroupRepository.findAll().size();

        // Create the MTargetActionTypeGroup with an existing ID
        mTargetActionTypeGroup.setId(1L);
        MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO = mTargetActionTypeGroupMapper.toDto(mTargetActionTypeGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTargetActionTypeGroupMockMvc.perform(post("/api/m-target-action-type-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionTypeGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetActionTypeGroup in the database
        List<MTargetActionTypeGroup> mTargetActionTypeGroupList = mTargetActionTypeGroupRepository.findAll();
        assertThat(mTargetActionTypeGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetActionTypeGroupRepository.findAll().size();
        // set the field null
        mTargetActionTypeGroup.setGroupId(null);

        // Create the MTargetActionTypeGroup, which fails.
        MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO = mTargetActionTypeGroupMapper.toDto(mTargetActionTypeGroup);

        restMTargetActionTypeGroupMockMvc.perform(post("/api/m-target-action-type-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionTypeGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetActionTypeGroup> mTargetActionTypeGroupList = mTargetActionTypeGroupRepository.findAll();
        assertThat(mTargetActionTypeGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommandTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetActionTypeGroupRepository.findAll().size();
        // set the field null
        mTargetActionTypeGroup.setCommandType(null);

        // Create the MTargetActionTypeGroup, which fails.
        MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO = mTargetActionTypeGroupMapper.toDto(mTargetActionTypeGroup);

        restMTargetActionTypeGroupMockMvc.perform(post("/api/m-target-action-type-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionTypeGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetActionTypeGroup> mTargetActionTypeGroupList = mTargetActionTypeGroupRepository.findAll();
        assertThat(mTargetActionTypeGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroups() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList
        restMTargetActionTypeGroupMockMvc.perform(get("/api/m-target-action-type-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetActionTypeGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].commandType").value(hasItem(DEFAULT_COMMAND_TYPE)));
    }
    
    @Test
    @Transactional
    public void getMTargetActionTypeGroup() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get the mTargetActionTypeGroup
        restMTargetActionTypeGroupMockMvc.perform(get("/api/m-target-action-type-groups/{id}", mTargetActionTypeGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTargetActionTypeGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.commandType").value(DEFAULT_COMMAND_TYPE));
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTargetActionTypeGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTargetActionTypeGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetActionTypeGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTargetActionTypeGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTargetActionTypeGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetActionTypeGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where groupId is not null
        defaultMTargetActionTypeGroupShouldBeFound("groupId.specified=true");

        // Get all the mTargetActionTypeGroupList where groupId is null
        defaultMTargetActionTypeGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTargetActionTypeGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetActionTypeGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTargetActionTypeGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTargetActionTypeGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetActionTypeGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTargetActionTypeGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByCommandTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where commandType equals to DEFAULT_COMMAND_TYPE
        defaultMTargetActionTypeGroupShouldBeFound("commandType.equals=" + DEFAULT_COMMAND_TYPE);

        // Get all the mTargetActionTypeGroupList where commandType equals to UPDATED_COMMAND_TYPE
        defaultMTargetActionTypeGroupShouldNotBeFound("commandType.equals=" + UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByCommandTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where commandType in DEFAULT_COMMAND_TYPE or UPDATED_COMMAND_TYPE
        defaultMTargetActionTypeGroupShouldBeFound("commandType.in=" + DEFAULT_COMMAND_TYPE + "," + UPDATED_COMMAND_TYPE);

        // Get all the mTargetActionTypeGroupList where commandType equals to UPDATED_COMMAND_TYPE
        defaultMTargetActionTypeGroupShouldNotBeFound("commandType.in=" + UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByCommandTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where commandType is not null
        defaultMTargetActionTypeGroupShouldBeFound("commandType.specified=true");

        // Get all the mTargetActionTypeGroupList where commandType is null
        defaultMTargetActionTypeGroupShouldNotBeFound("commandType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByCommandTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where commandType greater than or equals to DEFAULT_COMMAND_TYPE
        defaultMTargetActionTypeGroupShouldBeFound("commandType.greaterOrEqualThan=" + DEFAULT_COMMAND_TYPE);

        // Get all the mTargetActionTypeGroupList where commandType greater than or equals to UPDATED_COMMAND_TYPE
        defaultMTargetActionTypeGroupShouldNotBeFound("commandType.greaterOrEqualThan=" + UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMTargetActionTypeGroupsByCommandTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        // Get all the mTargetActionTypeGroupList where commandType less than or equals to DEFAULT_COMMAND_TYPE
        defaultMTargetActionTypeGroupShouldNotBeFound("commandType.lessThan=" + DEFAULT_COMMAND_TYPE);

        // Get all the mTargetActionTypeGroupList where commandType less than or equals to UPDATED_COMMAND_TYPE
        defaultMTargetActionTypeGroupShouldBeFound("commandType.lessThan=" + UPDATED_COMMAND_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTargetActionTypeGroupShouldBeFound(String filter) throws Exception {
        restMTargetActionTypeGroupMockMvc.perform(get("/api/m-target-action-type-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetActionTypeGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].commandType").value(hasItem(DEFAULT_COMMAND_TYPE)));

        // Check, that the count call also returns 1
        restMTargetActionTypeGroupMockMvc.perform(get("/api/m-target-action-type-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTargetActionTypeGroupShouldNotBeFound(String filter) throws Exception {
        restMTargetActionTypeGroupMockMvc.perform(get("/api/m-target-action-type-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTargetActionTypeGroupMockMvc.perform(get("/api/m-target-action-type-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTargetActionTypeGroup() throws Exception {
        // Get the mTargetActionTypeGroup
        restMTargetActionTypeGroupMockMvc.perform(get("/api/m-target-action-type-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTargetActionTypeGroup() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        int databaseSizeBeforeUpdate = mTargetActionTypeGroupRepository.findAll().size();

        // Update the mTargetActionTypeGroup
        MTargetActionTypeGroup updatedMTargetActionTypeGroup = mTargetActionTypeGroupRepository.findById(mTargetActionTypeGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTargetActionTypeGroup are not directly saved in db
        em.detach(updatedMTargetActionTypeGroup);
        updatedMTargetActionTypeGroup
            .groupId(UPDATED_GROUP_ID)
            .commandType(UPDATED_COMMAND_TYPE);
        MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO = mTargetActionTypeGroupMapper.toDto(updatedMTargetActionTypeGroup);

        restMTargetActionTypeGroupMockMvc.perform(put("/api/m-target-action-type-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionTypeGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTargetActionTypeGroup in the database
        List<MTargetActionTypeGroup> mTargetActionTypeGroupList = mTargetActionTypeGroupRepository.findAll();
        assertThat(mTargetActionTypeGroupList).hasSize(databaseSizeBeforeUpdate);
        MTargetActionTypeGroup testMTargetActionTypeGroup = mTargetActionTypeGroupList.get(mTargetActionTypeGroupList.size() - 1);
        assertThat(testMTargetActionTypeGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTargetActionTypeGroup.getCommandType()).isEqualTo(UPDATED_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMTargetActionTypeGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTargetActionTypeGroupRepository.findAll().size();

        // Create the MTargetActionTypeGroup
        MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO = mTargetActionTypeGroupMapper.toDto(mTargetActionTypeGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTargetActionTypeGroupMockMvc.perform(put("/api/m-target-action-type-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionTypeGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetActionTypeGroup in the database
        List<MTargetActionTypeGroup> mTargetActionTypeGroupList = mTargetActionTypeGroupRepository.findAll();
        assertThat(mTargetActionTypeGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTargetActionTypeGroup() throws Exception {
        // Initialize the database
        mTargetActionTypeGroupRepository.saveAndFlush(mTargetActionTypeGroup);

        int databaseSizeBeforeDelete = mTargetActionTypeGroupRepository.findAll().size();

        // Delete the mTargetActionTypeGroup
        restMTargetActionTypeGroupMockMvc.perform(delete("/api/m-target-action-type-groups/{id}", mTargetActionTypeGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTargetActionTypeGroup> mTargetActionTypeGroupList = mTargetActionTypeGroupRepository.findAll();
        assertThat(mTargetActionTypeGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetActionTypeGroup.class);
        MTargetActionTypeGroup mTargetActionTypeGroup1 = new MTargetActionTypeGroup();
        mTargetActionTypeGroup1.setId(1L);
        MTargetActionTypeGroup mTargetActionTypeGroup2 = new MTargetActionTypeGroup();
        mTargetActionTypeGroup2.setId(mTargetActionTypeGroup1.getId());
        assertThat(mTargetActionTypeGroup1).isEqualTo(mTargetActionTypeGroup2);
        mTargetActionTypeGroup2.setId(2L);
        assertThat(mTargetActionTypeGroup1).isNotEqualTo(mTargetActionTypeGroup2);
        mTargetActionTypeGroup1.setId(null);
        assertThat(mTargetActionTypeGroup1).isNotEqualTo(mTargetActionTypeGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetActionTypeGroupDTO.class);
        MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO1 = new MTargetActionTypeGroupDTO();
        mTargetActionTypeGroupDTO1.setId(1L);
        MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO2 = new MTargetActionTypeGroupDTO();
        assertThat(mTargetActionTypeGroupDTO1).isNotEqualTo(mTargetActionTypeGroupDTO2);
        mTargetActionTypeGroupDTO2.setId(mTargetActionTypeGroupDTO1.getId());
        assertThat(mTargetActionTypeGroupDTO1).isEqualTo(mTargetActionTypeGroupDTO2);
        mTargetActionTypeGroupDTO2.setId(2L);
        assertThat(mTargetActionTypeGroupDTO1).isNotEqualTo(mTargetActionTypeGroupDTO2);
        mTargetActionTypeGroupDTO1.setId(null);
        assertThat(mTargetActionTypeGroupDTO1).isNotEqualTo(mTargetActionTypeGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTargetActionTypeGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTargetActionTypeGroupMapper.fromId(null)).isNull();
    }
}
