package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLuckRateGroup;
import io.shm.tsubasa.repository.MLuckRateGroupRepository;
import io.shm.tsubasa.service.MLuckRateGroupService;
import io.shm.tsubasa.service.dto.MLuckRateGroupDTO;
import io.shm.tsubasa.service.mapper.MLuckRateGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLuckRateGroupCriteria;
import io.shm.tsubasa.service.MLuckRateGroupQueryService;

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
 * Integration tests for the {@Link MLuckRateGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLuckRateGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    @Autowired
    private MLuckRateGroupRepository mLuckRateGroupRepository;

    @Autowired
    private MLuckRateGroupMapper mLuckRateGroupMapper;

    @Autowired
    private MLuckRateGroupService mLuckRateGroupService;

    @Autowired
    private MLuckRateGroupQueryService mLuckRateGroupQueryService;

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

    private MockMvc restMLuckRateGroupMockMvc;

    private MLuckRateGroup mLuckRateGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLuckRateGroupResource mLuckRateGroupResource = new MLuckRateGroupResource(mLuckRateGroupService, mLuckRateGroupQueryService);
        this.restMLuckRateGroupMockMvc = MockMvcBuilders.standaloneSetup(mLuckRateGroupResource)
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
    public static MLuckRateGroup createEntity(EntityManager em) {
        MLuckRateGroup mLuckRateGroup = new MLuckRateGroup()
            .groupId(DEFAULT_GROUP_ID)
            .rarity(DEFAULT_RARITY)
            .rate(DEFAULT_RATE);
        return mLuckRateGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLuckRateGroup createUpdatedEntity(EntityManager em) {
        MLuckRateGroup mLuckRateGroup = new MLuckRateGroup()
            .groupId(UPDATED_GROUP_ID)
            .rarity(UPDATED_RARITY)
            .rate(UPDATED_RATE);
        return mLuckRateGroup;
    }

    @BeforeEach
    public void initTest() {
        mLuckRateGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLuckRateGroup() throws Exception {
        int databaseSizeBeforeCreate = mLuckRateGroupRepository.findAll().size();

        // Create the MLuckRateGroup
        MLuckRateGroupDTO mLuckRateGroupDTO = mLuckRateGroupMapper.toDto(mLuckRateGroup);
        restMLuckRateGroupMockMvc.perform(post("/api/m-luck-rate-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckRateGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MLuckRateGroup in the database
        List<MLuckRateGroup> mLuckRateGroupList = mLuckRateGroupRepository.findAll();
        assertThat(mLuckRateGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MLuckRateGroup testMLuckRateGroup = mLuckRateGroupList.get(mLuckRateGroupList.size() - 1);
        assertThat(testMLuckRateGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMLuckRateGroup.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMLuckRateGroup.getRate()).isEqualTo(DEFAULT_RATE);
    }

    @Test
    @Transactional
    public void createMLuckRateGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLuckRateGroupRepository.findAll().size();

        // Create the MLuckRateGroup with an existing ID
        mLuckRateGroup.setId(1L);
        MLuckRateGroupDTO mLuckRateGroupDTO = mLuckRateGroupMapper.toDto(mLuckRateGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLuckRateGroupMockMvc.perform(post("/api/m-luck-rate-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckRateGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuckRateGroup in the database
        List<MLuckRateGroup> mLuckRateGroupList = mLuckRateGroupRepository.findAll();
        assertThat(mLuckRateGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckRateGroupRepository.findAll().size();
        // set the field null
        mLuckRateGroup.setGroupId(null);

        // Create the MLuckRateGroup, which fails.
        MLuckRateGroupDTO mLuckRateGroupDTO = mLuckRateGroupMapper.toDto(mLuckRateGroup);

        restMLuckRateGroupMockMvc.perform(post("/api/m-luck-rate-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckRateGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckRateGroup> mLuckRateGroupList = mLuckRateGroupRepository.findAll();
        assertThat(mLuckRateGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckRateGroupRepository.findAll().size();
        // set the field null
        mLuckRateGroup.setRarity(null);

        // Create the MLuckRateGroup, which fails.
        MLuckRateGroupDTO mLuckRateGroupDTO = mLuckRateGroupMapper.toDto(mLuckRateGroup);

        restMLuckRateGroupMockMvc.perform(post("/api/m-luck-rate-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckRateGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckRateGroup> mLuckRateGroupList = mLuckRateGroupRepository.findAll();
        assertThat(mLuckRateGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLuckRateGroupRepository.findAll().size();
        // set the field null
        mLuckRateGroup.setRate(null);

        // Create the MLuckRateGroup, which fails.
        MLuckRateGroupDTO mLuckRateGroupDTO = mLuckRateGroupMapper.toDto(mLuckRateGroup);

        restMLuckRateGroupMockMvc.perform(post("/api/m-luck-rate-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckRateGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MLuckRateGroup> mLuckRateGroupList = mLuckRateGroupRepository.findAll();
        assertThat(mLuckRateGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroups() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList
        restMLuckRateGroupMockMvc.perform(get("/api/m-luck-rate-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuckRateGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)));
    }
    
    @Test
    @Transactional
    public void getMLuckRateGroup() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get the mLuckRateGroup
        restMLuckRateGroupMockMvc.perform(get("/api/m-luck-rate-groups/{id}", mLuckRateGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLuckRateGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE));
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMLuckRateGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mLuckRateGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMLuckRateGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMLuckRateGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mLuckRateGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMLuckRateGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where groupId is not null
        defaultMLuckRateGroupShouldBeFound("groupId.specified=true");

        // Get all the mLuckRateGroupList where groupId is null
        defaultMLuckRateGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMLuckRateGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mLuckRateGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMLuckRateGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMLuckRateGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mLuckRateGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMLuckRateGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rarity equals to DEFAULT_RARITY
        defaultMLuckRateGroupShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mLuckRateGroupList where rarity equals to UPDATED_RARITY
        defaultMLuckRateGroupShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMLuckRateGroupShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mLuckRateGroupList where rarity equals to UPDATED_RARITY
        defaultMLuckRateGroupShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rarity is not null
        defaultMLuckRateGroupShouldBeFound("rarity.specified=true");

        // Get all the mLuckRateGroupList where rarity is null
        defaultMLuckRateGroupShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rarity greater than or equals to DEFAULT_RARITY
        defaultMLuckRateGroupShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mLuckRateGroupList where rarity greater than or equals to UPDATED_RARITY
        defaultMLuckRateGroupShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rarity less than or equals to DEFAULT_RARITY
        defaultMLuckRateGroupShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mLuckRateGroupList where rarity less than or equals to UPDATED_RARITY
        defaultMLuckRateGroupShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rate equals to DEFAULT_RATE
        defaultMLuckRateGroupShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the mLuckRateGroupList where rate equals to UPDATED_RATE
        defaultMLuckRateGroupShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRateIsInShouldWork() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultMLuckRateGroupShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the mLuckRateGroupList where rate equals to UPDATED_RATE
        defaultMLuckRateGroupShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rate is not null
        defaultMLuckRateGroupShouldBeFound("rate.specified=true");

        // Get all the mLuckRateGroupList where rate is null
        defaultMLuckRateGroupShouldNotBeFound("rate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rate greater than or equals to DEFAULT_RATE
        defaultMLuckRateGroupShouldBeFound("rate.greaterOrEqualThan=" + DEFAULT_RATE);

        // Get all the mLuckRateGroupList where rate greater than or equals to UPDATED_RATE
        defaultMLuckRateGroupShouldNotBeFound("rate.greaterOrEqualThan=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMLuckRateGroupsByRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        // Get all the mLuckRateGroupList where rate less than or equals to DEFAULT_RATE
        defaultMLuckRateGroupShouldNotBeFound("rate.lessThan=" + DEFAULT_RATE);

        // Get all the mLuckRateGroupList where rate less than or equals to UPDATED_RATE
        defaultMLuckRateGroupShouldBeFound("rate.lessThan=" + UPDATED_RATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLuckRateGroupShouldBeFound(String filter) throws Exception {
        restMLuckRateGroupMockMvc.perform(get("/api/m-luck-rate-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLuckRateGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)));

        // Check, that the count call also returns 1
        restMLuckRateGroupMockMvc.perform(get("/api/m-luck-rate-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLuckRateGroupShouldNotBeFound(String filter) throws Exception {
        restMLuckRateGroupMockMvc.perform(get("/api/m-luck-rate-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLuckRateGroupMockMvc.perform(get("/api/m-luck-rate-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLuckRateGroup() throws Exception {
        // Get the mLuckRateGroup
        restMLuckRateGroupMockMvc.perform(get("/api/m-luck-rate-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLuckRateGroup() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        int databaseSizeBeforeUpdate = mLuckRateGroupRepository.findAll().size();

        // Update the mLuckRateGroup
        MLuckRateGroup updatedMLuckRateGroup = mLuckRateGroupRepository.findById(mLuckRateGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMLuckRateGroup are not directly saved in db
        em.detach(updatedMLuckRateGroup);
        updatedMLuckRateGroup
            .groupId(UPDATED_GROUP_ID)
            .rarity(UPDATED_RARITY)
            .rate(UPDATED_RATE);
        MLuckRateGroupDTO mLuckRateGroupDTO = mLuckRateGroupMapper.toDto(updatedMLuckRateGroup);

        restMLuckRateGroupMockMvc.perform(put("/api/m-luck-rate-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckRateGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MLuckRateGroup in the database
        List<MLuckRateGroup> mLuckRateGroupList = mLuckRateGroupRepository.findAll();
        assertThat(mLuckRateGroupList).hasSize(databaseSizeBeforeUpdate);
        MLuckRateGroup testMLuckRateGroup = mLuckRateGroupList.get(mLuckRateGroupList.size() - 1);
        assertThat(testMLuckRateGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMLuckRateGroup.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMLuckRateGroup.getRate()).isEqualTo(UPDATED_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMLuckRateGroup() throws Exception {
        int databaseSizeBeforeUpdate = mLuckRateGroupRepository.findAll().size();

        // Create the MLuckRateGroup
        MLuckRateGroupDTO mLuckRateGroupDTO = mLuckRateGroupMapper.toDto(mLuckRateGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLuckRateGroupMockMvc.perform(put("/api/m-luck-rate-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLuckRateGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLuckRateGroup in the database
        List<MLuckRateGroup> mLuckRateGroupList = mLuckRateGroupRepository.findAll();
        assertThat(mLuckRateGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLuckRateGroup() throws Exception {
        // Initialize the database
        mLuckRateGroupRepository.saveAndFlush(mLuckRateGroup);

        int databaseSizeBeforeDelete = mLuckRateGroupRepository.findAll().size();

        // Delete the mLuckRateGroup
        restMLuckRateGroupMockMvc.perform(delete("/api/m-luck-rate-groups/{id}", mLuckRateGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLuckRateGroup> mLuckRateGroupList = mLuckRateGroupRepository.findAll();
        assertThat(mLuckRateGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuckRateGroup.class);
        MLuckRateGroup mLuckRateGroup1 = new MLuckRateGroup();
        mLuckRateGroup1.setId(1L);
        MLuckRateGroup mLuckRateGroup2 = new MLuckRateGroup();
        mLuckRateGroup2.setId(mLuckRateGroup1.getId());
        assertThat(mLuckRateGroup1).isEqualTo(mLuckRateGroup2);
        mLuckRateGroup2.setId(2L);
        assertThat(mLuckRateGroup1).isNotEqualTo(mLuckRateGroup2);
        mLuckRateGroup1.setId(null);
        assertThat(mLuckRateGroup1).isNotEqualTo(mLuckRateGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLuckRateGroupDTO.class);
        MLuckRateGroupDTO mLuckRateGroupDTO1 = new MLuckRateGroupDTO();
        mLuckRateGroupDTO1.setId(1L);
        MLuckRateGroupDTO mLuckRateGroupDTO2 = new MLuckRateGroupDTO();
        assertThat(mLuckRateGroupDTO1).isNotEqualTo(mLuckRateGroupDTO2);
        mLuckRateGroupDTO2.setId(mLuckRateGroupDTO1.getId());
        assertThat(mLuckRateGroupDTO1).isEqualTo(mLuckRateGroupDTO2);
        mLuckRateGroupDTO2.setId(2L);
        assertThat(mLuckRateGroupDTO1).isNotEqualTo(mLuckRateGroupDTO2);
        mLuckRateGroupDTO1.setId(null);
        assertThat(mLuckRateGroupDTO1).isNotEqualTo(mLuckRateGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLuckRateGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLuckRateGroupMapper.fromId(null)).isNull();
    }
}
