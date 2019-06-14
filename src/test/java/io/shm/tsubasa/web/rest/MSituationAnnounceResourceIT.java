package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MSituationAnnounce;
import io.shm.tsubasa.repository.MSituationAnnounceRepository;
import io.shm.tsubasa.service.MSituationAnnounceService;
import io.shm.tsubasa.service.dto.MSituationAnnounceDTO;
import io.shm.tsubasa.service.mapper.MSituationAnnounceMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MSituationAnnounceCriteria;
import io.shm.tsubasa.service.MSituationAnnounceQueryService;

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
 * Integration tests for the {@Link MSituationAnnounceResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MSituationAnnounceResourceIT {

    private static final Integer DEFAULT_SITUATION_ID = 1;
    private static final Integer UPDATED_SITUATION_ID = 2;

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    @Autowired
    private MSituationAnnounceRepository mSituationAnnounceRepository;

    @Autowired
    private MSituationAnnounceMapper mSituationAnnounceMapper;

    @Autowired
    private MSituationAnnounceService mSituationAnnounceService;

    @Autowired
    private MSituationAnnounceQueryService mSituationAnnounceQueryService;

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

    private MockMvc restMSituationAnnounceMockMvc;

    private MSituationAnnounce mSituationAnnounce;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MSituationAnnounceResource mSituationAnnounceResource = new MSituationAnnounceResource(mSituationAnnounceService, mSituationAnnounceQueryService);
        this.restMSituationAnnounceMockMvc = MockMvcBuilders.standaloneSetup(mSituationAnnounceResource)
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
    public static MSituationAnnounce createEntity(EntityManager em) {
        MSituationAnnounce mSituationAnnounce = new MSituationAnnounce()
            .situationId(DEFAULT_SITUATION_ID)
            .groupId(DEFAULT_GROUP_ID);
        return mSituationAnnounce;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MSituationAnnounce createUpdatedEntity(EntityManager em) {
        MSituationAnnounce mSituationAnnounce = new MSituationAnnounce()
            .situationId(UPDATED_SITUATION_ID)
            .groupId(UPDATED_GROUP_ID);
        return mSituationAnnounce;
    }

    @BeforeEach
    public void initTest() {
        mSituationAnnounce = createEntity(em);
    }

    @Test
    @Transactional
    public void createMSituationAnnounce() throws Exception {
        int databaseSizeBeforeCreate = mSituationAnnounceRepository.findAll().size();

        // Create the MSituationAnnounce
        MSituationAnnounceDTO mSituationAnnounceDTO = mSituationAnnounceMapper.toDto(mSituationAnnounce);
        restMSituationAnnounceMockMvc.perform(post("/api/m-situation-announces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationAnnounceDTO)))
            .andExpect(status().isCreated());

        // Validate the MSituationAnnounce in the database
        List<MSituationAnnounce> mSituationAnnounceList = mSituationAnnounceRepository.findAll();
        assertThat(mSituationAnnounceList).hasSize(databaseSizeBeforeCreate + 1);
        MSituationAnnounce testMSituationAnnounce = mSituationAnnounceList.get(mSituationAnnounceList.size() - 1);
        assertThat(testMSituationAnnounce.getSituationId()).isEqualTo(DEFAULT_SITUATION_ID);
        assertThat(testMSituationAnnounce.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
    }

    @Test
    @Transactional
    public void createMSituationAnnounceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mSituationAnnounceRepository.findAll().size();

        // Create the MSituationAnnounce with an existing ID
        mSituationAnnounce.setId(1L);
        MSituationAnnounceDTO mSituationAnnounceDTO = mSituationAnnounceMapper.toDto(mSituationAnnounce);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMSituationAnnounceMockMvc.perform(post("/api/m-situation-announces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationAnnounceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSituationAnnounce in the database
        List<MSituationAnnounce> mSituationAnnounceList = mSituationAnnounceRepository.findAll();
        assertThat(mSituationAnnounceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSituationIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationAnnounceRepository.findAll().size();
        // set the field null
        mSituationAnnounce.setSituationId(null);

        // Create the MSituationAnnounce, which fails.
        MSituationAnnounceDTO mSituationAnnounceDTO = mSituationAnnounceMapper.toDto(mSituationAnnounce);

        restMSituationAnnounceMockMvc.perform(post("/api/m-situation-announces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationAnnounceDTO)))
            .andExpect(status().isBadRequest());

        List<MSituationAnnounce> mSituationAnnounceList = mSituationAnnounceRepository.findAll();
        assertThat(mSituationAnnounceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSituationAnnounceRepository.findAll().size();
        // set the field null
        mSituationAnnounce.setGroupId(null);

        // Create the MSituationAnnounce, which fails.
        MSituationAnnounceDTO mSituationAnnounceDTO = mSituationAnnounceMapper.toDto(mSituationAnnounce);

        restMSituationAnnounceMockMvc.perform(post("/api/m-situation-announces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationAnnounceDTO)))
            .andExpect(status().isBadRequest());

        List<MSituationAnnounce> mSituationAnnounceList = mSituationAnnounceRepository.findAll();
        assertThat(mSituationAnnounceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMSituationAnnounces() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList
        restMSituationAnnounceMockMvc.perform(get("/api/m-situation-announces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSituationAnnounce.getId().intValue())))
            .andExpect(jsonPath("$.[*].situationId").value(hasItem(DEFAULT_SITUATION_ID)))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)));
    }
    
    @Test
    @Transactional
    public void getMSituationAnnounce() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get the mSituationAnnounce
        restMSituationAnnounceMockMvc.perform(get("/api/m-situation-announces/{id}", mSituationAnnounce.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mSituationAnnounce.getId().intValue()))
            .andExpect(jsonPath("$.situationId").value(DEFAULT_SITUATION_ID))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID));
    }

    @Test
    @Transactional
    public void getAllMSituationAnnouncesBySituationIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where situationId equals to DEFAULT_SITUATION_ID
        defaultMSituationAnnounceShouldBeFound("situationId.equals=" + DEFAULT_SITUATION_ID);

        // Get all the mSituationAnnounceList where situationId equals to UPDATED_SITUATION_ID
        defaultMSituationAnnounceShouldNotBeFound("situationId.equals=" + UPDATED_SITUATION_ID);
    }

    @Test
    @Transactional
    public void getAllMSituationAnnouncesBySituationIdIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where situationId in DEFAULT_SITUATION_ID or UPDATED_SITUATION_ID
        defaultMSituationAnnounceShouldBeFound("situationId.in=" + DEFAULT_SITUATION_ID + "," + UPDATED_SITUATION_ID);

        // Get all the mSituationAnnounceList where situationId equals to UPDATED_SITUATION_ID
        defaultMSituationAnnounceShouldNotBeFound("situationId.in=" + UPDATED_SITUATION_ID);
    }

    @Test
    @Transactional
    public void getAllMSituationAnnouncesBySituationIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where situationId is not null
        defaultMSituationAnnounceShouldBeFound("situationId.specified=true");

        // Get all the mSituationAnnounceList where situationId is null
        defaultMSituationAnnounceShouldNotBeFound("situationId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationAnnouncesBySituationIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where situationId greater than or equals to DEFAULT_SITUATION_ID
        defaultMSituationAnnounceShouldBeFound("situationId.greaterOrEqualThan=" + DEFAULT_SITUATION_ID);

        // Get all the mSituationAnnounceList where situationId greater than or equals to UPDATED_SITUATION_ID
        defaultMSituationAnnounceShouldNotBeFound("situationId.greaterOrEqualThan=" + UPDATED_SITUATION_ID);
    }

    @Test
    @Transactional
    public void getAllMSituationAnnouncesBySituationIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where situationId less than or equals to DEFAULT_SITUATION_ID
        defaultMSituationAnnounceShouldNotBeFound("situationId.lessThan=" + DEFAULT_SITUATION_ID);

        // Get all the mSituationAnnounceList where situationId less than or equals to UPDATED_SITUATION_ID
        defaultMSituationAnnounceShouldBeFound("situationId.lessThan=" + UPDATED_SITUATION_ID);
    }


    @Test
    @Transactional
    public void getAllMSituationAnnouncesByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where groupId equals to DEFAULT_GROUP_ID
        defaultMSituationAnnounceShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mSituationAnnounceList where groupId equals to UPDATED_GROUP_ID
        defaultMSituationAnnounceShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMSituationAnnouncesByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMSituationAnnounceShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mSituationAnnounceList where groupId equals to UPDATED_GROUP_ID
        defaultMSituationAnnounceShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMSituationAnnouncesByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where groupId is not null
        defaultMSituationAnnounceShouldBeFound("groupId.specified=true");

        // Get all the mSituationAnnounceList where groupId is null
        defaultMSituationAnnounceShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSituationAnnouncesByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMSituationAnnounceShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mSituationAnnounceList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMSituationAnnounceShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMSituationAnnouncesByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        // Get all the mSituationAnnounceList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMSituationAnnounceShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mSituationAnnounceList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMSituationAnnounceShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMSituationAnnounceShouldBeFound(String filter) throws Exception {
        restMSituationAnnounceMockMvc.perform(get("/api/m-situation-announces?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSituationAnnounce.getId().intValue())))
            .andExpect(jsonPath("$.[*].situationId").value(hasItem(DEFAULT_SITUATION_ID)))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)));

        // Check, that the count call also returns 1
        restMSituationAnnounceMockMvc.perform(get("/api/m-situation-announces/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMSituationAnnounceShouldNotBeFound(String filter) throws Exception {
        restMSituationAnnounceMockMvc.perform(get("/api/m-situation-announces?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMSituationAnnounceMockMvc.perform(get("/api/m-situation-announces/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMSituationAnnounce() throws Exception {
        // Get the mSituationAnnounce
        restMSituationAnnounceMockMvc.perform(get("/api/m-situation-announces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMSituationAnnounce() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        int databaseSizeBeforeUpdate = mSituationAnnounceRepository.findAll().size();

        // Update the mSituationAnnounce
        MSituationAnnounce updatedMSituationAnnounce = mSituationAnnounceRepository.findById(mSituationAnnounce.getId()).get();
        // Disconnect from session so that the updates on updatedMSituationAnnounce are not directly saved in db
        em.detach(updatedMSituationAnnounce);
        updatedMSituationAnnounce
            .situationId(UPDATED_SITUATION_ID)
            .groupId(UPDATED_GROUP_ID);
        MSituationAnnounceDTO mSituationAnnounceDTO = mSituationAnnounceMapper.toDto(updatedMSituationAnnounce);

        restMSituationAnnounceMockMvc.perform(put("/api/m-situation-announces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationAnnounceDTO)))
            .andExpect(status().isOk());

        // Validate the MSituationAnnounce in the database
        List<MSituationAnnounce> mSituationAnnounceList = mSituationAnnounceRepository.findAll();
        assertThat(mSituationAnnounceList).hasSize(databaseSizeBeforeUpdate);
        MSituationAnnounce testMSituationAnnounce = mSituationAnnounceList.get(mSituationAnnounceList.size() - 1);
        assertThat(testMSituationAnnounce.getSituationId()).isEqualTo(UPDATED_SITUATION_ID);
        assertThat(testMSituationAnnounce.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMSituationAnnounce() throws Exception {
        int databaseSizeBeforeUpdate = mSituationAnnounceRepository.findAll().size();

        // Create the MSituationAnnounce
        MSituationAnnounceDTO mSituationAnnounceDTO = mSituationAnnounceMapper.toDto(mSituationAnnounce);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMSituationAnnounceMockMvc.perform(put("/api/m-situation-announces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSituationAnnounceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSituationAnnounce in the database
        List<MSituationAnnounce> mSituationAnnounceList = mSituationAnnounceRepository.findAll();
        assertThat(mSituationAnnounceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMSituationAnnounce() throws Exception {
        // Initialize the database
        mSituationAnnounceRepository.saveAndFlush(mSituationAnnounce);

        int databaseSizeBeforeDelete = mSituationAnnounceRepository.findAll().size();

        // Delete the mSituationAnnounce
        restMSituationAnnounceMockMvc.perform(delete("/api/m-situation-announces/{id}", mSituationAnnounce.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MSituationAnnounce> mSituationAnnounceList = mSituationAnnounceRepository.findAll();
        assertThat(mSituationAnnounceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSituationAnnounce.class);
        MSituationAnnounce mSituationAnnounce1 = new MSituationAnnounce();
        mSituationAnnounce1.setId(1L);
        MSituationAnnounce mSituationAnnounce2 = new MSituationAnnounce();
        mSituationAnnounce2.setId(mSituationAnnounce1.getId());
        assertThat(mSituationAnnounce1).isEqualTo(mSituationAnnounce2);
        mSituationAnnounce2.setId(2L);
        assertThat(mSituationAnnounce1).isNotEqualTo(mSituationAnnounce2);
        mSituationAnnounce1.setId(null);
        assertThat(mSituationAnnounce1).isNotEqualTo(mSituationAnnounce2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSituationAnnounceDTO.class);
        MSituationAnnounceDTO mSituationAnnounceDTO1 = new MSituationAnnounceDTO();
        mSituationAnnounceDTO1.setId(1L);
        MSituationAnnounceDTO mSituationAnnounceDTO2 = new MSituationAnnounceDTO();
        assertThat(mSituationAnnounceDTO1).isNotEqualTo(mSituationAnnounceDTO2);
        mSituationAnnounceDTO2.setId(mSituationAnnounceDTO1.getId());
        assertThat(mSituationAnnounceDTO1).isEqualTo(mSituationAnnounceDTO2);
        mSituationAnnounceDTO2.setId(2L);
        assertThat(mSituationAnnounceDTO1).isNotEqualTo(mSituationAnnounceDTO2);
        mSituationAnnounceDTO1.setId(null);
        assertThat(mSituationAnnounceDTO1).isNotEqualTo(mSituationAnnounceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mSituationAnnounceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mSituationAnnounceMapper.fromId(null)).isNull();
    }
}
