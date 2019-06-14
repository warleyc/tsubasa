package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTargetActionGroup;
import io.shm.tsubasa.domain.MAction;
import io.shm.tsubasa.repository.MTargetActionGroupRepository;
import io.shm.tsubasa.service.MTargetActionGroupService;
import io.shm.tsubasa.service.dto.MTargetActionGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetActionGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTargetActionGroupCriteria;
import io.shm.tsubasa.service.MTargetActionGroupQueryService;

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
 * Integration tests for the {@Link MTargetActionGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTargetActionGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_ACTION_ID = 1;
    private static final Integer UPDATED_ACTION_ID = 2;

    @Autowired
    private MTargetActionGroupRepository mTargetActionGroupRepository;

    @Autowired
    private MTargetActionGroupMapper mTargetActionGroupMapper;

    @Autowired
    private MTargetActionGroupService mTargetActionGroupService;

    @Autowired
    private MTargetActionGroupQueryService mTargetActionGroupQueryService;

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

    private MockMvc restMTargetActionGroupMockMvc;

    private MTargetActionGroup mTargetActionGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTargetActionGroupResource mTargetActionGroupResource = new MTargetActionGroupResource(mTargetActionGroupService, mTargetActionGroupQueryService);
        this.restMTargetActionGroupMockMvc = MockMvcBuilders.standaloneSetup(mTargetActionGroupResource)
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
    public static MTargetActionGroup createEntity(EntityManager em) {
        MTargetActionGroup mTargetActionGroup = new MTargetActionGroup()
            .groupId(DEFAULT_GROUP_ID)
            .actionId(DEFAULT_ACTION_ID);
        // Add required entity
        MAction mAction;
        if (TestUtil.findAll(em, MAction.class).isEmpty()) {
            mAction = MActionResourceIT.createEntity(em);
            em.persist(mAction);
            em.flush();
        } else {
            mAction = TestUtil.findAll(em, MAction.class).get(0);
        }
        mTargetActionGroup.setId(mAction);
        return mTargetActionGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTargetActionGroup createUpdatedEntity(EntityManager em) {
        MTargetActionGroup mTargetActionGroup = new MTargetActionGroup()
            .groupId(UPDATED_GROUP_ID)
            .actionId(UPDATED_ACTION_ID);
        // Add required entity
        MAction mAction;
        if (TestUtil.findAll(em, MAction.class).isEmpty()) {
            mAction = MActionResourceIT.createUpdatedEntity(em);
            em.persist(mAction);
            em.flush();
        } else {
            mAction = TestUtil.findAll(em, MAction.class).get(0);
        }
        mTargetActionGroup.setId(mAction);
        return mTargetActionGroup;
    }

    @BeforeEach
    public void initTest() {
        mTargetActionGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTargetActionGroup() throws Exception {
        int databaseSizeBeforeCreate = mTargetActionGroupRepository.findAll().size();

        // Create the MTargetActionGroup
        MTargetActionGroupDTO mTargetActionGroupDTO = mTargetActionGroupMapper.toDto(mTargetActionGroup);
        restMTargetActionGroupMockMvc.perform(post("/api/m-target-action-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTargetActionGroup in the database
        List<MTargetActionGroup> mTargetActionGroupList = mTargetActionGroupRepository.findAll();
        assertThat(mTargetActionGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTargetActionGroup testMTargetActionGroup = mTargetActionGroupList.get(mTargetActionGroupList.size() - 1);
        assertThat(testMTargetActionGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTargetActionGroup.getActionId()).isEqualTo(DEFAULT_ACTION_ID);
    }

    @Test
    @Transactional
    public void createMTargetActionGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTargetActionGroupRepository.findAll().size();

        // Create the MTargetActionGroup with an existing ID
        mTargetActionGroup.setId(1L);
        MTargetActionGroupDTO mTargetActionGroupDTO = mTargetActionGroupMapper.toDto(mTargetActionGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTargetActionGroupMockMvc.perform(post("/api/m-target-action-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetActionGroup in the database
        List<MTargetActionGroup> mTargetActionGroupList = mTargetActionGroupRepository.findAll();
        assertThat(mTargetActionGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetActionGroupRepository.findAll().size();
        // set the field null
        mTargetActionGroup.setGroupId(null);

        // Create the MTargetActionGroup, which fails.
        MTargetActionGroupDTO mTargetActionGroupDTO = mTargetActionGroupMapper.toDto(mTargetActionGroup);

        restMTargetActionGroupMockMvc.perform(post("/api/m-target-action-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetActionGroup> mTargetActionGroupList = mTargetActionGroupRepository.findAll();
        assertThat(mTargetActionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetActionGroupRepository.findAll().size();
        // set the field null
        mTargetActionGroup.setActionId(null);

        // Create the MTargetActionGroup, which fails.
        MTargetActionGroupDTO mTargetActionGroupDTO = mTargetActionGroupMapper.toDto(mTargetActionGroup);

        restMTargetActionGroupMockMvc.perform(post("/api/m-target-action-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetActionGroup> mTargetActionGroupList = mTargetActionGroupRepository.findAll();
        assertThat(mTargetActionGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroups() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList
        restMTargetActionGroupMockMvc.perform(get("/api/m-target-action-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetActionGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].actionId").value(hasItem(DEFAULT_ACTION_ID)));
    }
    
    @Test
    @Transactional
    public void getMTargetActionGroup() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get the mTargetActionGroup
        restMTargetActionGroupMockMvc.perform(get("/api/m-target-action-groups/{id}", mTargetActionGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTargetActionGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.actionId").value(DEFAULT_ACTION_ID));
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTargetActionGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTargetActionGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetActionGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTargetActionGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTargetActionGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetActionGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where groupId is not null
        defaultMTargetActionGroupShouldBeFound("groupId.specified=true");

        // Get all the mTargetActionGroupList where groupId is null
        defaultMTargetActionGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTargetActionGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetActionGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTargetActionGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTargetActionGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetActionGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTargetActionGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetActionGroupsByActionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where actionId equals to DEFAULT_ACTION_ID
        defaultMTargetActionGroupShouldBeFound("actionId.equals=" + DEFAULT_ACTION_ID);

        // Get all the mTargetActionGroupList where actionId equals to UPDATED_ACTION_ID
        defaultMTargetActionGroupShouldNotBeFound("actionId.equals=" + UPDATED_ACTION_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroupsByActionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where actionId in DEFAULT_ACTION_ID or UPDATED_ACTION_ID
        defaultMTargetActionGroupShouldBeFound("actionId.in=" + DEFAULT_ACTION_ID + "," + UPDATED_ACTION_ID);

        // Get all the mTargetActionGroupList where actionId equals to UPDATED_ACTION_ID
        defaultMTargetActionGroupShouldNotBeFound("actionId.in=" + UPDATED_ACTION_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroupsByActionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where actionId is not null
        defaultMTargetActionGroupShouldBeFound("actionId.specified=true");

        // Get all the mTargetActionGroupList where actionId is null
        defaultMTargetActionGroupShouldNotBeFound("actionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroupsByActionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where actionId greater than or equals to DEFAULT_ACTION_ID
        defaultMTargetActionGroupShouldBeFound("actionId.greaterOrEqualThan=" + DEFAULT_ACTION_ID);

        // Get all the mTargetActionGroupList where actionId greater than or equals to UPDATED_ACTION_ID
        defaultMTargetActionGroupShouldNotBeFound("actionId.greaterOrEqualThan=" + UPDATED_ACTION_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetActionGroupsByActionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        // Get all the mTargetActionGroupList where actionId less than or equals to DEFAULT_ACTION_ID
        defaultMTargetActionGroupShouldNotBeFound("actionId.lessThan=" + DEFAULT_ACTION_ID);

        // Get all the mTargetActionGroupList where actionId less than or equals to UPDATED_ACTION_ID
        defaultMTargetActionGroupShouldBeFound("actionId.lessThan=" + UPDATED_ACTION_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetActionGroupsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MAction id = mTargetActionGroup.getId();
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);
        Long idId = id.getId();

        // Get all the mTargetActionGroupList where id equals to idId
        defaultMTargetActionGroupShouldBeFound("idId.equals=" + idId);

        // Get all the mTargetActionGroupList where id equals to idId + 1
        defaultMTargetActionGroupShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTargetActionGroupShouldBeFound(String filter) throws Exception {
        restMTargetActionGroupMockMvc.perform(get("/api/m-target-action-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetActionGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].actionId").value(hasItem(DEFAULT_ACTION_ID)));

        // Check, that the count call also returns 1
        restMTargetActionGroupMockMvc.perform(get("/api/m-target-action-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTargetActionGroupShouldNotBeFound(String filter) throws Exception {
        restMTargetActionGroupMockMvc.perform(get("/api/m-target-action-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTargetActionGroupMockMvc.perform(get("/api/m-target-action-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTargetActionGroup() throws Exception {
        // Get the mTargetActionGroup
        restMTargetActionGroupMockMvc.perform(get("/api/m-target-action-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTargetActionGroup() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        int databaseSizeBeforeUpdate = mTargetActionGroupRepository.findAll().size();

        // Update the mTargetActionGroup
        MTargetActionGroup updatedMTargetActionGroup = mTargetActionGroupRepository.findById(mTargetActionGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTargetActionGroup are not directly saved in db
        em.detach(updatedMTargetActionGroup);
        updatedMTargetActionGroup
            .groupId(UPDATED_GROUP_ID)
            .actionId(UPDATED_ACTION_ID);
        MTargetActionGroupDTO mTargetActionGroupDTO = mTargetActionGroupMapper.toDto(updatedMTargetActionGroup);

        restMTargetActionGroupMockMvc.perform(put("/api/m-target-action-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTargetActionGroup in the database
        List<MTargetActionGroup> mTargetActionGroupList = mTargetActionGroupRepository.findAll();
        assertThat(mTargetActionGroupList).hasSize(databaseSizeBeforeUpdate);
        MTargetActionGroup testMTargetActionGroup = mTargetActionGroupList.get(mTargetActionGroupList.size() - 1);
        assertThat(testMTargetActionGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTargetActionGroup.getActionId()).isEqualTo(UPDATED_ACTION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTargetActionGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTargetActionGroupRepository.findAll().size();

        // Create the MTargetActionGroup
        MTargetActionGroupDTO mTargetActionGroupDTO = mTargetActionGroupMapper.toDto(mTargetActionGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTargetActionGroupMockMvc.perform(put("/api/m-target-action-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetActionGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetActionGroup in the database
        List<MTargetActionGroup> mTargetActionGroupList = mTargetActionGroupRepository.findAll();
        assertThat(mTargetActionGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTargetActionGroup() throws Exception {
        // Initialize the database
        mTargetActionGroupRepository.saveAndFlush(mTargetActionGroup);

        int databaseSizeBeforeDelete = mTargetActionGroupRepository.findAll().size();

        // Delete the mTargetActionGroup
        restMTargetActionGroupMockMvc.perform(delete("/api/m-target-action-groups/{id}", mTargetActionGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTargetActionGroup> mTargetActionGroupList = mTargetActionGroupRepository.findAll();
        assertThat(mTargetActionGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetActionGroup.class);
        MTargetActionGroup mTargetActionGroup1 = new MTargetActionGroup();
        mTargetActionGroup1.setId(1L);
        MTargetActionGroup mTargetActionGroup2 = new MTargetActionGroup();
        mTargetActionGroup2.setId(mTargetActionGroup1.getId());
        assertThat(mTargetActionGroup1).isEqualTo(mTargetActionGroup2);
        mTargetActionGroup2.setId(2L);
        assertThat(mTargetActionGroup1).isNotEqualTo(mTargetActionGroup2);
        mTargetActionGroup1.setId(null);
        assertThat(mTargetActionGroup1).isNotEqualTo(mTargetActionGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetActionGroupDTO.class);
        MTargetActionGroupDTO mTargetActionGroupDTO1 = new MTargetActionGroupDTO();
        mTargetActionGroupDTO1.setId(1L);
        MTargetActionGroupDTO mTargetActionGroupDTO2 = new MTargetActionGroupDTO();
        assertThat(mTargetActionGroupDTO1).isNotEqualTo(mTargetActionGroupDTO2);
        mTargetActionGroupDTO2.setId(mTargetActionGroupDTO1.getId());
        assertThat(mTargetActionGroupDTO1).isEqualTo(mTargetActionGroupDTO2);
        mTargetActionGroupDTO2.setId(2L);
        assertThat(mTargetActionGroupDTO1).isNotEqualTo(mTargetActionGroupDTO2);
        mTargetActionGroupDTO1.setId(null);
        assertThat(mTargetActionGroupDTO1).isNotEqualTo(mTargetActionGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTargetActionGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTargetActionGroupMapper.fromId(null)).isNull();
    }
}
