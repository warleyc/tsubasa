package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTargetRarityGroup;
import io.shm.tsubasa.repository.MTargetRarityGroupRepository;
import io.shm.tsubasa.service.MTargetRarityGroupService;
import io.shm.tsubasa.service.dto.MTargetRarityGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetRarityGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTargetRarityGroupCriteria;
import io.shm.tsubasa.service.MTargetRarityGroupQueryService;

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
 * Integration tests for the {@Link MTargetRarityGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTargetRarityGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CARD_RARITY = 1;
    private static final Integer UPDATED_CARD_RARITY = 2;

    @Autowired
    private MTargetRarityGroupRepository mTargetRarityGroupRepository;

    @Autowired
    private MTargetRarityGroupMapper mTargetRarityGroupMapper;

    @Autowired
    private MTargetRarityGroupService mTargetRarityGroupService;

    @Autowired
    private MTargetRarityGroupQueryService mTargetRarityGroupQueryService;

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

    private MockMvc restMTargetRarityGroupMockMvc;

    private MTargetRarityGroup mTargetRarityGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTargetRarityGroupResource mTargetRarityGroupResource = new MTargetRarityGroupResource(mTargetRarityGroupService, mTargetRarityGroupQueryService);
        this.restMTargetRarityGroupMockMvc = MockMvcBuilders.standaloneSetup(mTargetRarityGroupResource)
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
    public static MTargetRarityGroup createEntity(EntityManager em) {
        MTargetRarityGroup mTargetRarityGroup = new MTargetRarityGroup()
            .groupId(DEFAULT_GROUP_ID)
            .cardRarity(DEFAULT_CARD_RARITY);
        return mTargetRarityGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTargetRarityGroup createUpdatedEntity(EntityManager em) {
        MTargetRarityGroup mTargetRarityGroup = new MTargetRarityGroup()
            .groupId(UPDATED_GROUP_ID)
            .cardRarity(UPDATED_CARD_RARITY);
        return mTargetRarityGroup;
    }

    @BeforeEach
    public void initTest() {
        mTargetRarityGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTargetRarityGroup() throws Exception {
        int databaseSizeBeforeCreate = mTargetRarityGroupRepository.findAll().size();

        // Create the MTargetRarityGroup
        MTargetRarityGroupDTO mTargetRarityGroupDTO = mTargetRarityGroupMapper.toDto(mTargetRarityGroup);
        restMTargetRarityGroupMockMvc.perform(post("/api/m-target-rarity-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetRarityGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTargetRarityGroup in the database
        List<MTargetRarityGroup> mTargetRarityGroupList = mTargetRarityGroupRepository.findAll();
        assertThat(mTargetRarityGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTargetRarityGroup testMTargetRarityGroup = mTargetRarityGroupList.get(mTargetRarityGroupList.size() - 1);
        assertThat(testMTargetRarityGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTargetRarityGroup.getCardRarity()).isEqualTo(DEFAULT_CARD_RARITY);
    }

    @Test
    @Transactional
    public void createMTargetRarityGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTargetRarityGroupRepository.findAll().size();

        // Create the MTargetRarityGroup with an existing ID
        mTargetRarityGroup.setId(1L);
        MTargetRarityGroupDTO mTargetRarityGroupDTO = mTargetRarityGroupMapper.toDto(mTargetRarityGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTargetRarityGroupMockMvc.perform(post("/api/m-target-rarity-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetRarityGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetRarityGroup in the database
        List<MTargetRarityGroup> mTargetRarityGroupList = mTargetRarityGroupRepository.findAll();
        assertThat(mTargetRarityGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetRarityGroupRepository.findAll().size();
        // set the field null
        mTargetRarityGroup.setGroupId(null);

        // Create the MTargetRarityGroup, which fails.
        MTargetRarityGroupDTO mTargetRarityGroupDTO = mTargetRarityGroupMapper.toDto(mTargetRarityGroup);

        restMTargetRarityGroupMockMvc.perform(post("/api/m-target-rarity-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetRarityGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetRarityGroup> mTargetRarityGroupList = mTargetRarityGroupRepository.findAll();
        assertThat(mTargetRarityGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetRarityGroupRepository.findAll().size();
        // set the field null
        mTargetRarityGroup.setCardRarity(null);

        // Create the MTargetRarityGroup, which fails.
        MTargetRarityGroupDTO mTargetRarityGroupDTO = mTargetRarityGroupMapper.toDto(mTargetRarityGroup);

        restMTargetRarityGroupMockMvc.perform(post("/api/m-target-rarity-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetRarityGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetRarityGroup> mTargetRarityGroupList = mTargetRarityGroupRepository.findAll();
        assertThat(mTargetRarityGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroups() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList
        restMTargetRarityGroupMockMvc.perform(get("/api/m-target-rarity-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetRarityGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].cardRarity").value(hasItem(DEFAULT_CARD_RARITY)));
    }
    
    @Test
    @Transactional
    public void getMTargetRarityGroup() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get the mTargetRarityGroup
        restMTargetRarityGroupMockMvc.perform(get("/api/m-target-rarity-groups/{id}", mTargetRarityGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTargetRarityGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.cardRarity").value(DEFAULT_CARD_RARITY));
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTargetRarityGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTargetRarityGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetRarityGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTargetRarityGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTargetRarityGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetRarityGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where groupId is not null
        defaultMTargetRarityGroupShouldBeFound("groupId.specified=true");

        // Get all the mTargetRarityGroupList where groupId is null
        defaultMTargetRarityGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTargetRarityGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetRarityGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTargetRarityGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTargetRarityGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetRarityGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTargetRarityGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByCardRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where cardRarity equals to DEFAULT_CARD_RARITY
        defaultMTargetRarityGroupShouldBeFound("cardRarity.equals=" + DEFAULT_CARD_RARITY);

        // Get all the mTargetRarityGroupList where cardRarity equals to UPDATED_CARD_RARITY
        defaultMTargetRarityGroupShouldNotBeFound("cardRarity.equals=" + UPDATED_CARD_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByCardRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where cardRarity in DEFAULT_CARD_RARITY or UPDATED_CARD_RARITY
        defaultMTargetRarityGroupShouldBeFound("cardRarity.in=" + DEFAULT_CARD_RARITY + "," + UPDATED_CARD_RARITY);

        // Get all the mTargetRarityGroupList where cardRarity equals to UPDATED_CARD_RARITY
        defaultMTargetRarityGroupShouldNotBeFound("cardRarity.in=" + UPDATED_CARD_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByCardRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where cardRarity is not null
        defaultMTargetRarityGroupShouldBeFound("cardRarity.specified=true");

        // Get all the mTargetRarityGroupList where cardRarity is null
        defaultMTargetRarityGroupShouldNotBeFound("cardRarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByCardRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where cardRarity greater than or equals to DEFAULT_CARD_RARITY
        defaultMTargetRarityGroupShouldBeFound("cardRarity.greaterOrEqualThan=" + DEFAULT_CARD_RARITY);

        // Get all the mTargetRarityGroupList where cardRarity greater than or equals to UPDATED_CARD_RARITY
        defaultMTargetRarityGroupShouldNotBeFound("cardRarity.greaterOrEqualThan=" + UPDATED_CARD_RARITY);
    }

    @Test
    @Transactional
    public void getAllMTargetRarityGroupsByCardRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        // Get all the mTargetRarityGroupList where cardRarity less than or equals to DEFAULT_CARD_RARITY
        defaultMTargetRarityGroupShouldNotBeFound("cardRarity.lessThan=" + DEFAULT_CARD_RARITY);

        // Get all the mTargetRarityGroupList where cardRarity less than or equals to UPDATED_CARD_RARITY
        defaultMTargetRarityGroupShouldBeFound("cardRarity.lessThan=" + UPDATED_CARD_RARITY);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTargetRarityGroupShouldBeFound(String filter) throws Exception {
        restMTargetRarityGroupMockMvc.perform(get("/api/m-target-rarity-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetRarityGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].cardRarity").value(hasItem(DEFAULT_CARD_RARITY)));

        // Check, that the count call also returns 1
        restMTargetRarityGroupMockMvc.perform(get("/api/m-target-rarity-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTargetRarityGroupShouldNotBeFound(String filter) throws Exception {
        restMTargetRarityGroupMockMvc.perform(get("/api/m-target-rarity-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTargetRarityGroupMockMvc.perform(get("/api/m-target-rarity-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTargetRarityGroup() throws Exception {
        // Get the mTargetRarityGroup
        restMTargetRarityGroupMockMvc.perform(get("/api/m-target-rarity-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTargetRarityGroup() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        int databaseSizeBeforeUpdate = mTargetRarityGroupRepository.findAll().size();

        // Update the mTargetRarityGroup
        MTargetRarityGroup updatedMTargetRarityGroup = mTargetRarityGroupRepository.findById(mTargetRarityGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTargetRarityGroup are not directly saved in db
        em.detach(updatedMTargetRarityGroup);
        updatedMTargetRarityGroup
            .groupId(UPDATED_GROUP_ID)
            .cardRarity(UPDATED_CARD_RARITY);
        MTargetRarityGroupDTO mTargetRarityGroupDTO = mTargetRarityGroupMapper.toDto(updatedMTargetRarityGroup);

        restMTargetRarityGroupMockMvc.perform(put("/api/m-target-rarity-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetRarityGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTargetRarityGroup in the database
        List<MTargetRarityGroup> mTargetRarityGroupList = mTargetRarityGroupRepository.findAll();
        assertThat(mTargetRarityGroupList).hasSize(databaseSizeBeforeUpdate);
        MTargetRarityGroup testMTargetRarityGroup = mTargetRarityGroupList.get(mTargetRarityGroupList.size() - 1);
        assertThat(testMTargetRarityGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTargetRarityGroup.getCardRarity()).isEqualTo(UPDATED_CARD_RARITY);
    }

    @Test
    @Transactional
    public void updateNonExistingMTargetRarityGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTargetRarityGroupRepository.findAll().size();

        // Create the MTargetRarityGroup
        MTargetRarityGroupDTO mTargetRarityGroupDTO = mTargetRarityGroupMapper.toDto(mTargetRarityGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTargetRarityGroupMockMvc.perform(put("/api/m-target-rarity-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetRarityGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetRarityGroup in the database
        List<MTargetRarityGroup> mTargetRarityGroupList = mTargetRarityGroupRepository.findAll();
        assertThat(mTargetRarityGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTargetRarityGroup() throws Exception {
        // Initialize the database
        mTargetRarityGroupRepository.saveAndFlush(mTargetRarityGroup);

        int databaseSizeBeforeDelete = mTargetRarityGroupRepository.findAll().size();

        // Delete the mTargetRarityGroup
        restMTargetRarityGroupMockMvc.perform(delete("/api/m-target-rarity-groups/{id}", mTargetRarityGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTargetRarityGroup> mTargetRarityGroupList = mTargetRarityGroupRepository.findAll();
        assertThat(mTargetRarityGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetRarityGroup.class);
        MTargetRarityGroup mTargetRarityGroup1 = new MTargetRarityGroup();
        mTargetRarityGroup1.setId(1L);
        MTargetRarityGroup mTargetRarityGroup2 = new MTargetRarityGroup();
        mTargetRarityGroup2.setId(mTargetRarityGroup1.getId());
        assertThat(mTargetRarityGroup1).isEqualTo(mTargetRarityGroup2);
        mTargetRarityGroup2.setId(2L);
        assertThat(mTargetRarityGroup1).isNotEqualTo(mTargetRarityGroup2);
        mTargetRarityGroup1.setId(null);
        assertThat(mTargetRarityGroup1).isNotEqualTo(mTargetRarityGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetRarityGroupDTO.class);
        MTargetRarityGroupDTO mTargetRarityGroupDTO1 = new MTargetRarityGroupDTO();
        mTargetRarityGroupDTO1.setId(1L);
        MTargetRarityGroupDTO mTargetRarityGroupDTO2 = new MTargetRarityGroupDTO();
        assertThat(mTargetRarityGroupDTO1).isNotEqualTo(mTargetRarityGroupDTO2);
        mTargetRarityGroupDTO2.setId(mTargetRarityGroupDTO1.getId());
        assertThat(mTargetRarityGroupDTO1).isEqualTo(mTargetRarityGroupDTO2);
        mTargetRarityGroupDTO2.setId(2L);
        assertThat(mTargetRarityGroupDTO1).isNotEqualTo(mTargetRarityGroupDTO2);
        mTargetRarityGroupDTO1.setId(null);
        assertThat(mTargetRarityGroupDTO1).isNotEqualTo(mTargetRarityGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTargetRarityGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTargetRarityGroupMapper.fromId(null)).isNull();
    }
}
