package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTargetTriggerEffectGroup;
import io.shm.tsubasa.domain.MTriggerEffectBase;
import io.shm.tsubasa.repository.MTargetTriggerEffectGroupRepository;
import io.shm.tsubasa.service.MTargetTriggerEffectGroupService;
import io.shm.tsubasa.service.dto.MTargetTriggerEffectGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetTriggerEffectGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTargetTriggerEffectGroupCriteria;
import io.shm.tsubasa.service.MTargetTriggerEffectGroupQueryService;

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
 * Integration tests for the {@Link MTargetTriggerEffectGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTargetTriggerEffectGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_TRIGGER_EFFECT_ID = 1;
    private static final Integer UPDATED_TRIGGER_EFFECT_ID = 2;

    @Autowired
    private MTargetTriggerEffectGroupRepository mTargetTriggerEffectGroupRepository;

    @Autowired
    private MTargetTriggerEffectGroupMapper mTargetTriggerEffectGroupMapper;

    @Autowired
    private MTargetTriggerEffectGroupService mTargetTriggerEffectGroupService;

    @Autowired
    private MTargetTriggerEffectGroupQueryService mTargetTriggerEffectGroupQueryService;

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

    private MockMvc restMTargetTriggerEffectGroupMockMvc;

    private MTargetTriggerEffectGroup mTargetTriggerEffectGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTargetTriggerEffectGroupResource mTargetTriggerEffectGroupResource = new MTargetTriggerEffectGroupResource(mTargetTriggerEffectGroupService, mTargetTriggerEffectGroupQueryService);
        this.restMTargetTriggerEffectGroupMockMvc = MockMvcBuilders.standaloneSetup(mTargetTriggerEffectGroupResource)
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
    public static MTargetTriggerEffectGroup createEntity(EntityManager em) {
        MTargetTriggerEffectGroup mTargetTriggerEffectGroup = new MTargetTriggerEffectGroup()
            .groupId(DEFAULT_GROUP_ID)
            .triggerEffectId(DEFAULT_TRIGGER_EFFECT_ID);
        // Add required entity
        MTriggerEffectBase mTriggerEffectBase;
        if (TestUtil.findAll(em, MTriggerEffectBase.class).isEmpty()) {
            mTriggerEffectBase = MTriggerEffectBaseResourceIT.createEntity(em);
            em.persist(mTriggerEffectBase);
            em.flush();
        } else {
            mTriggerEffectBase = TestUtil.findAll(em, MTriggerEffectBase.class).get(0);
        }
        mTargetTriggerEffectGroup.setId(mTriggerEffectBase);
        return mTargetTriggerEffectGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTargetTriggerEffectGroup createUpdatedEntity(EntityManager em) {
        MTargetTriggerEffectGroup mTargetTriggerEffectGroup = new MTargetTriggerEffectGroup()
            .groupId(UPDATED_GROUP_ID)
            .triggerEffectId(UPDATED_TRIGGER_EFFECT_ID);
        // Add required entity
        MTriggerEffectBase mTriggerEffectBase;
        if (TestUtil.findAll(em, MTriggerEffectBase.class).isEmpty()) {
            mTriggerEffectBase = MTriggerEffectBaseResourceIT.createUpdatedEntity(em);
            em.persist(mTriggerEffectBase);
            em.flush();
        } else {
            mTriggerEffectBase = TestUtil.findAll(em, MTriggerEffectBase.class).get(0);
        }
        mTargetTriggerEffectGroup.setId(mTriggerEffectBase);
        return mTargetTriggerEffectGroup;
    }

    @BeforeEach
    public void initTest() {
        mTargetTriggerEffectGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTargetTriggerEffectGroup() throws Exception {
        int databaseSizeBeforeCreate = mTargetTriggerEffectGroupRepository.findAll().size();

        // Create the MTargetTriggerEffectGroup
        MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO = mTargetTriggerEffectGroupMapper.toDto(mTargetTriggerEffectGroup);
        restMTargetTriggerEffectGroupMockMvc.perform(post("/api/m-target-trigger-effect-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTriggerEffectGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTargetTriggerEffectGroup in the database
        List<MTargetTriggerEffectGroup> mTargetTriggerEffectGroupList = mTargetTriggerEffectGroupRepository.findAll();
        assertThat(mTargetTriggerEffectGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTargetTriggerEffectGroup testMTargetTriggerEffectGroup = mTargetTriggerEffectGroupList.get(mTargetTriggerEffectGroupList.size() - 1);
        assertThat(testMTargetTriggerEffectGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTargetTriggerEffectGroup.getTriggerEffectId()).isEqualTo(DEFAULT_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void createMTargetTriggerEffectGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTargetTriggerEffectGroupRepository.findAll().size();

        // Create the MTargetTriggerEffectGroup with an existing ID
        mTargetTriggerEffectGroup.setId(1L);
        MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO = mTargetTriggerEffectGroupMapper.toDto(mTargetTriggerEffectGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTargetTriggerEffectGroupMockMvc.perform(post("/api/m-target-trigger-effect-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTriggerEffectGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetTriggerEffectGroup in the database
        List<MTargetTriggerEffectGroup> mTargetTriggerEffectGroupList = mTargetTriggerEffectGroupRepository.findAll();
        assertThat(mTargetTriggerEffectGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetTriggerEffectGroupRepository.findAll().size();
        // set the field null
        mTargetTriggerEffectGroup.setGroupId(null);

        // Create the MTargetTriggerEffectGroup, which fails.
        MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO = mTargetTriggerEffectGroupMapper.toDto(mTargetTriggerEffectGroup);

        restMTargetTriggerEffectGroupMockMvc.perform(post("/api/m-target-trigger-effect-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTriggerEffectGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetTriggerEffectGroup> mTargetTriggerEffectGroupList = mTargetTriggerEffectGroupRepository.findAll();
        assertThat(mTargetTriggerEffectGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTriggerEffectIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetTriggerEffectGroupRepository.findAll().size();
        // set the field null
        mTargetTriggerEffectGroup.setTriggerEffectId(null);

        // Create the MTargetTriggerEffectGroup, which fails.
        MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO = mTargetTriggerEffectGroupMapper.toDto(mTargetTriggerEffectGroup);

        restMTargetTriggerEffectGroupMockMvc.perform(post("/api/m-target-trigger-effect-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTriggerEffectGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetTriggerEffectGroup> mTargetTriggerEffectGroupList = mTargetTriggerEffectGroupRepository.findAll();
        assertThat(mTargetTriggerEffectGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroups() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList
        restMTargetTriggerEffectGroupMockMvc.perform(get("/api/m-target-trigger-effect-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetTriggerEffectGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].triggerEffectId").value(hasItem(DEFAULT_TRIGGER_EFFECT_ID)));
    }
    
    @Test
    @Transactional
    public void getMTargetTriggerEffectGroup() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get the mTargetTriggerEffectGroup
        restMTargetTriggerEffectGroupMockMvc.perform(get("/api/m-target-trigger-effect-groups/{id}", mTargetTriggerEffectGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTargetTriggerEffectGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.triggerEffectId").value(DEFAULT_TRIGGER_EFFECT_ID));
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTargetTriggerEffectGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTargetTriggerEffectGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetTriggerEffectGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTargetTriggerEffectGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTargetTriggerEffectGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetTriggerEffectGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where groupId is not null
        defaultMTargetTriggerEffectGroupShouldBeFound("groupId.specified=true");

        // Get all the mTargetTriggerEffectGroupList where groupId is null
        defaultMTargetTriggerEffectGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTargetTriggerEffectGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetTriggerEffectGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTargetTriggerEffectGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTargetTriggerEffectGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetTriggerEffectGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTargetTriggerEffectGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByTriggerEffectIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId equals to DEFAULT_TRIGGER_EFFECT_ID
        defaultMTargetTriggerEffectGroupShouldBeFound("triggerEffectId.equals=" + DEFAULT_TRIGGER_EFFECT_ID);

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMTargetTriggerEffectGroupShouldNotBeFound("triggerEffectId.equals=" + UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByTriggerEffectIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId in DEFAULT_TRIGGER_EFFECT_ID or UPDATED_TRIGGER_EFFECT_ID
        defaultMTargetTriggerEffectGroupShouldBeFound("triggerEffectId.in=" + DEFAULT_TRIGGER_EFFECT_ID + "," + UPDATED_TRIGGER_EFFECT_ID);

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMTargetTriggerEffectGroupShouldNotBeFound("triggerEffectId.in=" + UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByTriggerEffectIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId is not null
        defaultMTargetTriggerEffectGroupShouldBeFound("triggerEffectId.specified=true");

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId is null
        defaultMTargetTriggerEffectGroupShouldNotBeFound("triggerEffectId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByTriggerEffectIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId greater than or equals to DEFAULT_TRIGGER_EFFECT_ID
        defaultMTargetTriggerEffectGroupShouldBeFound("triggerEffectId.greaterOrEqualThan=" + DEFAULT_TRIGGER_EFFECT_ID);

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId greater than or equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMTargetTriggerEffectGroupShouldNotBeFound("triggerEffectId.greaterOrEqualThan=" + UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByTriggerEffectIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId less than or equals to DEFAULT_TRIGGER_EFFECT_ID
        defaultMTargetTriggerEffectGroupShouldNotBeFound("triggerEffectId.lessThan=" + DEFAULT_TRIGGER_EFFECT_ID);

        // Get all the mTargetTriggerEffectGroupList where triggerEffectId less than or equals to UPDATED_TRIGGER_EFFECT_ID
        defaultMTargetTriggerEffectGroupShouldBeFound("triggerEffectId.lessThan=" + UPDATED_TRIGGER_EFFECT_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetTriggerEffectGroupsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MTriggerEffectBase id = mTargetTriggerEffectGroup.getId();
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);
        Long idId = id.getId();

        // Get all the mTargetTriggerEffectGroupList where id equals to idId
        defaultMTargetTriggerEffectGroupShouldBeFound("idId.equals=" + idId);

        // Get all the mTargetTriggerEffectGroupList where id equals to idId + 1
        defaultMTargetTriggerEffectGroupShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTargetTriggerEffectGroupShouldBeFound(String filter) throws Exception {
        restMTargetTriggerEffectGroupMockMvc.perform(get("/api/m-target-trigger-effect-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetTriggerEffectGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].triggerEffectId").value(hasItem(DEFAULT_TRIGGER_EFFECT_ID)));

        // Check, that the count call also returns 1
        restMTargetTriggerEffectGroupMockMvc.perform(get("/api/m-target-trigger-effect-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTargetTriggerEffectGroupShouldNotBeFound(String filter) throws Exception {
        restMTargetTriggerEffectGroupMockMvc.perform(get("/api/m-target-trigger-effect-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTargetTriggerEffectGroupMockMvc.perform(get("/api/m-target-trigger-effect-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTargetTriggerEffectGroup() throws Exception {
        // Get the mTargetTriggerEffectGroup
        restMTargetTriggerEffectGroupMockMvc.perform(get("/api/m-target-trigger-effect-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTargetTriggerEffectGroup() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        int databaseSizeBeforeUpdate = mTargetTriggerEffectGroupRepository.findAll().size();

        // Update the mTargetTriggerEffectGroup
        MTargetTriggerEffectGroup updatedMTargetTriggerEffectGroup = mTargetTriggerEffectGroupRepository.findById(mTargetTriggerEffectGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTargetTriggerEffectGroup are not directly saved in db
        em.detach(updatedMTargetTriggerEffectGroup);
        updatedMTargetTriggerEffectGroup
            .groupId(UPDATED_GROUP_ID)
            .triggerEffectId(UPDATED_TRIGGER_EFFECT_ID);
        MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO = mTargetTriggerEffectGroupMapper.toDto(updatedMTargetTriggerEffectGroup);

        restMTargetTriggerEffectGroupMockMvc.perform(put("/api/m-target-trigger-effect-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTriggerEffectGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTargetTriggerEffectGroup in the database
        List<MTargetTriggerEffectGroup> mTargetTriggerEffectGroupList = mTargetTriggerEffectGroupRepository.findAll();
        assertThat(mTargetTriggerEffectGroupList).hasSize(databaseSizeBeforeUpdate);
        MTargetTriggerEffectGroup testMTargetTriggerEffectGroup = mTargetTriggerEffectGroupList.get(mTargetTriggerEffectGroupList.size() - 1);
        assertThat(testMTargetTriggerEffectGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTargetTriggerEffectGroup.getTriggerEffectId()).isEqualTo(UPDATED_TRIGGER_EFFECT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTargetTriggerEffectGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTargetTriggerEffectGroupRepository.findAll().size();

        // Create the MTargetTriggerEffectGroup
        MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO = mTargetTriggerEffectGroupMapper.toDto(mTargetTriggerEffectGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTargetTriggerEffectGroupMockMvc.perform(put("/api/m-target-trigger-effect-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetTriggerEffectGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetTriggerEffectGroup in the database
        List<MTargetTriggerEffectGroup> mTargetTriggerEffectGroupList = mTargetTriggerEffectGroupRepository.findAll();
        assertThat(mTargetTriggerEffectGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTargetTriggerEffectGroup() throws Exception {
        // Initialize the database
        mTargetTriggerEffectGroupRepository.saveAndFlush(mTargetTriggerEffectGroup);

        int databaseSizeBeforeDelete = mTargetTriggerEffectGroupRepository.findAll().size();

        // Delete the mTargetTriggerEffectGroup
        restMTargetTriggerEffectGroupMockMvc.perform(delete("/api/m-target-trigger-effect-groups/{id}", mTargetTriggerEffectGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTargetTriggerEffectGroup> mTargetTriggerEffectGroupList = mTargetTriggerEffectGroupRepository.findAll();
        assertThat(mTargetTriggerEffectGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetTriggerEffectGroup.class);
        MTargetTriggerEffectGroup mTargetTriggerEffectGroup1 = new MTargetTriggerEffectGroup();
        mTargetTriggerEffectGroup1.setId(1L);
        MTargetTriggerEffectGroup mTargetTriggerEffectGroup2 = new MTargetTriggerEffectGroup();
        mTargetTriggerEffectGroup2.setId(mTargetTriggerEffectGroup1.getId());
        assertThat(mTargetTriggerEffectGroup1).isEqualTo(mTargetTriggerEffectGroup2);
        mTargetTriggerEffectGroup2.setId(2L);
        assertThat(mTargetTriggerEffectGroup1).isNotEqualTo(mTargetTriggerEffectGroup2);
        mTargetTriggerEffectGroup1.setId(null);
        assertThat(mTargetTriggerEffectGroup1).isNotEqualTo(mTargetTriggerEffectGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetTriggerEffectGroupDTO.class);
        MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO1 = new MTargetTriggerEffectGroupDTO();
        mTargetTriggerEffectGroupDTO1.setId(1L);
        MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO2 = new MTargetTriggerEffectGroupDTO();
        assertThat(mTargetTriggerEffectGroupDTO1).isNotEqualTo(mTargetTriggerEffectGroupDTO2);
        mTargetTriggerEffectGroupDTO2.setId(mTargetTriggerEffectGroupDTO1.getId());
        assertThat(mTargetTriggerEffectGroupDTO1).isEqualTo(mTargetTriggerEffectGroupDTO2);
        mTargetTriggerEffectGroupDTO2.setId(2L);
        assertThat(mTargetTriggerEffectGroupDTO1).isNotEqualTo(mTargetTriggerEffectGroupDTO2);
        mTargetTriggerEffectGroupDTO1.setId(null);
        assertThat(mTargetTriggerEffectGroupDTO1).isNotEqualTo(mTargetTriggerEffectGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTargetTriggerEffectGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTargetTriggerEffectGroupMapper.fromId(null)).isNull();
    }
}
