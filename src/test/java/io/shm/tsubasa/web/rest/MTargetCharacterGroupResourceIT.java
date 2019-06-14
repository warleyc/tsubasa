package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTargetCharacterGroup;
import io.shm.tsubasa.domain.MCharacter;
import io.shm.tsubasa.repository.MTargetCharacterGroupRepository;
import io.shm.tsubasa.service.MTargetCharacterGroupService;
import io.shm.tsubasa.service.dto.MTargetCharacterGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetCharacterGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTargetCharacterGroupCriteria;
import io.shm.tsubasa.service.MTargetCharacterGroupQueryService;

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
 * Integration tests for the {@Link MTargetCharacterGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTargetCharacterGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CHARACTER_ID = 1;
    private static final Integer UPDATED_CHARACTER_ID = 2;

    @Autowired
    private MTargetCharacterGroupRepository mTargetCharacterGroupRepository;

    @Autowired
    private MTargetCharacterGroupMapper mTargetCharacterGroupMapper;

    @Autowired
    private MTargetCharacterGroupService mTargetCharacterGroupService;

    @Autowired
    private MTargetCharacterGroupQueryService mTargetCharacterGroupQueryService;

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

    private MockMvc restMTargetCharacterGroupMockMvc;

    private MTargetCharacterGroup mTargetCharacterGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTargetCharacterGroupResource mTargetCharacterGroupResource = new MTargetCharacterGroupResource(mTargetCharacterGroupService, mTargetCharacterGroupQueryService);
        this.restMTargetCharacterGroupMockMvc = MockMvcBuilders.standaloneSetup(mTargetCharacterGroupResource)
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
    public static MTargetCharacterGroup createEntity(EntityManager em) {
        MTargetCharacterGroup mTargetCharacterGroup = new MTargetCharacterGroup()
            .groupId(DEFAULT_GROUP_ID)
            .characterId(DEFAULT_CHARACTER_ID);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mTargetCharacterGroup.setMcharacter(mCharacter);
        return mTargetCharacterGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTargetCharacterGroup createUpdatedEntity(EntityManager em) {
        MTargetCharacterGroup mTargetCharacterGroup = new MTargetCharacterGroup()
            .groupId(UPDATED_GROUP_ID)
            .characterId(UPDATED_CHARACTER_ID);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createUpdatedEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mTargetCharacterGroup.setMcharacter(mCharacter);
        return mTargetCharacterGroup;
    }

    @BeforeEach
    public void initTest() {
        mTargetCharacterGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTargetCharacterGroup() throws Exception {
        int databaseSizeBeforeCreate = mTargetCharacterGroupRepository.findAll().size();

        // Create the MTargetCharacterGroup
        MTargetCharacterGroupDTO mTargetCharacterGroupDTO = mTargetCharacterGroupMapper.toDto(mTargetCharacterGroup);
        restMTargetCharacterGroupMockMvc.perform(post("/api/m-target-character-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetCharacterGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTargetCharacterGroup in the database
        List<MTargetCharacterGroup> mTargetCharacterGroupList = mTargetCharacterGroupRepository.findAll();
        assertThat(mTargetCharacterGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTargetCharacterGroup testMTargetCharacterGroup = mTargetCharacterGroupList.get(mTargetCharacterGroupList.size() - 1);
        assertThat(testMTargetCharacterGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTargetCharacterGroup.getCharacterId()).isEqualTo(DEFAULT_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void createMTargetCharacterGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTargetCharacterGroupRepository.findAll().size();

        // Create the MTargetCharacterGroup with an existing ID
        mTargetCharacterGroup.setId(1L);
        MTargetCharacterGroupDTO mTargetCharacterGroupDTO = mTargetCharacterGroupMapper.toDto(mTargetCharacterGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTargetCharacterGroupMockMvc.perform(post("/api/m-target-character-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetCharacterGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetCharacterGroup in the database
        List<MTargetCharacterGroup> mTargetCharacterGroupList = mTargetCharacterGroupRepository.findAll();
        assertThat(mTargetCharacterGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetCharacterGroupRepository.findAll().size();
        // set the field null
        mTargetCharacterGroup.setGroupId(null);

        // Create the MTargetCharacterGroup, which fails.
        MTargetCharacterGroupDTO mTargetCharacterGroupDTO = mTargetCharacterGroupMapper.toDto(mTargetCharacterGroup);

        restMTargetCharacterGroupMockMvc.perform(post("/api/m-target-character-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetCharacterGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetCharacterGroup> mTargetCharacterGroupList = mTargetCharacterGroupRepository.findAll();
        assertThat(mTargetCharacterGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCharacterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetCharacterGroupRepository.findAll().size();
        // set the field null
        mTargetCharacterGroup.setCharacterId(null);

        // Create the MTargetCharacterGroup, which fails.
        MTargetCharacterGroupDTO mTargetCharacterGroupDTO = mTargetCharacterGroupMapper.toDto(mTargetCharacterGroup);

        restMTargetCharacterGroupMockMvc.perform(post("/api/m-target-character-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetCharacterGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetCharacterGroup> mTargetCharacterGroupList = mTargetCharacterGroupRepository.findAll();
        assertThat(mTargetCharacterGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroups() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList
        restMTargetCharacterGroupMockMvc.perform(get("/api/m-target-character-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetCharacterGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)));
    }
    
    @Test
    @Transactional
    public void getMTargetCharacterGroup() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get the mTargetCharacterGroup
        restMTargetCharacterGroupMockMvc.perform(get("/api/m-target-character-groups/{id}", mTargetCharacterGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTargetCharacterGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.characterId").value(DEFAULT_CHARACTER_ID));
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTargetCharacterGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTargetCharacterGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetCharacterGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTargetCharacterGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTargetCharacterGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetCharacterGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where groupId is not null
        defaultMTargetCharacterGroupShouldBeFound("groupId.specified=true");

        // Get all the mTargetCharacterGroupList where groupId is null
        defaultMTargetCharacterGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTargetCharacterGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetCharacterGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTargetCharacterGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTargetCharacterGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetCharacterGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTargetCharacterGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where characterId equals to DEFAULT_CHARACTER_ID
        defaultMTargetCharacterGroupShouldBeFound("characterId.equals=" + DEFAULT_CHARACTER_ID);

        // Get all the mTargetCharacterGroupList where characterId equals to UPDATED_CHARACTER_ID
        defaultMTargetCharacterGroupShouldNotBeFound("characterId.equals=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where characterId in DEFAULT_CHARACTER_ID or UPDATED_CHARACTER_ID
        defaultMTargetCharacterGroupShouldBeFound("characterId.in=" + DEFAULT_CHARACTER_ID + "," + UPDATED_CHARACTER_ID);

        // Get all the mTargetCharacterGroupList where characterId equals to UPDATED_CHARACTER_ID
        defaultMTargetCharacterGroupShouldNotBeFound("characterId.in=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where characterId is not null
        defaultMTargetCharacterGroupShouldBeFound("characterId.specified=true");

        // Get all the mTargetCharacterGroupList where characterId is null
        defaultMTargetCharacterGroupShouldNotBeFound("characterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where characterId greater than or equals to DEFAULT_CHARACTER_ID
        defaultMTargetCharacterGroupShouldBeFound("characterId.greaterOrEqualThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mTargetCharacterGroupList where characterId greater than or equals to UPDATED_CHARACTER_ID
        defaultMTargetCharacterGroupShouldNotBeFound("characterId.greaterOrEqualThan=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        // Get all the mTargetCharacterGroupList where characterId less than or equals to DEFAULT_CHARACTER_ID
        defaultMTargetCharacterGroupShouldNotBeFound("characterId.lessThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mTargetCharacterGroupList where characterId less than or equals to UPDATED_CHARACTER_ID
        defaultMTargetCharacterGroupShouldBeFound("characterId.lessThan=" + UPDATED_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetCharacterGroupsByMcharacterIsEqualToSomething() throws Exception {
        // Get already existing entity
        MCharacter mcharacter = mTargetCharacterGroup.getMcharacter();
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);
        Long mcharacterId = mcharacter.getId();

        // Get all the mTargetCharacterGroupList where mcharacter equals to mcharacterId
        defaultMTargetCharacterGroupShouldBeFound("mcharacterId.equals=" + mcharacterId);

        // Get all the mTargetCharacterGroupList where mcharacter equals to mcharacterId + 1
        defaultMTargetCharacterGroupShouldNotBeFound("mcharacterId.equals=" + (mcharacterId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTargetCharacterGroupShouldBeFound(String filter) throws Exception {
        restMTargetCharacterGroupMockMvc.perform(get("/api/m-target-character-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetCharacterGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)));

        // Check, that the count call also returns 1
        restMTargetCharacterGroupMockMvc.perform(get("/api/m-target-character-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTargetCharacterGroupShouldNotBeFound(String filter) throws Exception {
        restMTargetCharacterGroupMockMvc.perform(get("/api/m-target-character-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTargetCharacterGroupMockMvc.perform(get("/api/m-target-character-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTargetCharacterGroup() throws Exception {
        // Get the mTargetCharacterGroup
        restMTargetCharacterGroupMockMvc.perform(get("/api/m-target-character-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTargetCharacterGroup() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        int databaseSizeBeforeUpdate = mTargetCharacterGroupRepository.findAll().size();

        // Update the mTargetCharacterGroup
        MTargetCharacterGroup updatedMTargetCharacterGroup = mTargetCharacterGroupRepository.findById(mTargetCharacterGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTargetCharacterGroup are not directly saved in db
        em.detach(updatedMTargetCharacterGroup);
        updatedMTargetCharacterGroup
            .groupId(UPDATED_GROUP_ID)
            .characterId(UPDATED_CHARACTER_ID);
        MTargetCharacterGroupDTO mTargetCharacterGroupDTO = mTargetCharacterGroupMapper.toDto(updatedMTargetCharacterGroup);

        restMTargetCharacterGroupMockMvc.perform(put("/api/m-target-character-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetCharacterGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTargetCharacterGroup in the database
        List<MTargetCharacterGroup> mTargetCharacterGroupList = mTargetCharacterGroupRepository.findAll();
        assertThat(mTargetCharacterGroupList).hasSize(databaseSizeBeforeUpdate);
        MTargetCharacterGroup testMTargetCharacterGroup = mTargetCharacterGroupList.get(mTargetCharacterGroupList.size() - 1);
        assertThat(testMTargetCharacterGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTargetCharacterGroup.getCharacterId()).isEqualTo(UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMTargetCharacterGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTargetCharacterGroupRepository.findAll().size();

        // Create the MTargetCharacterGroup
        MTargetCharacterGroupDTO mTargetCharacterGroupDTO = mTargetCharacterGroupMapper.toDto(mTargetCharacterGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTargetCharacterGroupMockMvc.perform(put("/api/m-target-character-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetCharacterGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetCharacterGroup in the database
        List<MTargetCharacterGroup> mTargetCharacterGroupList = mTargetCharacterGroupRepository.findAll();
        assertThat(mTargetCharacterGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTargetCharacterGroup() throws Exception {
        // Initialize the database
        mTargetCharacterGroupRepository.saveAndFlush(mTargetCharacterGroup);

        int databaseSizeBeforeDelete = mTargetCharacterGroupRepository.findAll().size();

        // Delete the mTargetCharacterGroup
        restMTargetCharacterGroupMockMvc.perform(delete("/api/m-target-character-groups/{id}", mTargetCharacterGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTargetCharacterGroup> mTargetCharacterGroupList = mTargetCharacterGroupRepository.findAll();
        assertThat(mTargetCharacterGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetCharacterGroup.class);
        MTargetCharacterGroup mTargetCharacterGroup1 = new MTargetCharacterGroup();
        mTargetCharacterGroup1.setId(1L);
        MTargetCharacterGroup mTargetCharacterGroup2 = new MTargetCharacterGroup();
        mTargetCharacterGroup2.setId(mTargetCharacterGroup1.getId());
        assertThat(mTargetCharacterGroup1).isEqualTo(mTargetCharacterGroup2);
        mTargetCharacterGroup2.setId(2L);
        assertThat(mTargetCharacterGroup1).isNotEqualTo(mTargetCharacterGroup2);
        mTargetCharacterGroup1.setId(null);
        assertThat(mTargetCharacterGroup1).isNotEqualTo(mTargetCharacterGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetCharacterGroupDTO.class);
        MTargetCharacterGroupDTO mTargetCharacterGroupDTO1 = new MTargetCharacterGroupDTO();
        mTargetCharacterGroupDTO1.setId(1L);
        MTargetCharacterGroupDTO mTargetCharacterGroupDTO2 = new MTargetCharacterGroupDTO();
        assertThat(mTargetCharacterGroupDTO1).isNotEqualTo(mTargetCharacterGroupDTO2);
        mTargetCharacterGroupDTO2.setId(mTargetCharacterGroupDTO1.getId());
        assertThat(mTargetCharacterGroupDTO1).isEqualTo(mTargetCharacterGroupDTO2);
        mTargetCharacterGroupDTO2.setId(2L);
        assertThat(mTargetCharacterGroupDTO1).isNotEqualTo(mTargetCharacterGroupDTO2);
        mTargetCharacterGroupDTO1.setId(null);
        assertThat(mTargetCharacterGroupDTO1).isNotEqualTo(mTargetCharacterGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTargetCharacterGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTargetCharacterGroupMapper.fromId(null)).isNull();
    }
}
