package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPvpPlayerStamp;
import io.shm.tsubasa.repository.MPvpPlayerStampRepository;
import io.shm.tsubasa.service.MPvpPlayerStampService;
import io.shm.tsubasa.service.dto.MPvpPlayerStampDTO;
import io.shm.tsubasa.service.mapper.MPvpPlayerStampMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPvpPlayerStampCriteria;
import io.shm.tsubasa.service.MPvpPlayerStampQueryService;

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
 * Integration tests for the {@Link MPvpPlayerStampResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPvpPlayerStampResourceIT {

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    private static final Integer DEFAULT_MASTER_ID = 1;
    private static final Integer UPDATED_MASTER_ID = 2;

    @Autowired
    private MPvpPlayerStampRepository mPvpPlayerStampRepository;

    @Autowired
    private MPvpPlayerStampMapper mPvpPlayerStampMapper;

    @Autowired
    private MPvpPlayerStampService mPvpPlayerStampService;

    @Autowired
    private MPvpPlayerStampQueryService mPvpPlayerStampQueryService;

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

    private MockMvc restMPvpPlayerStampMockMvc;

    private MPvpPlayerStamp mPvpPlayerStamp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPvpPlayerStampResource mPvpPlayerStampResource = new MPvpPlayerStampResource(mPvpPlayerStampService, mPvpPlayerStampQueryService);
        this.restMPvpPlayerStampMockMvc = MockMvcBuilders.standaloneSetup(mPvpPlayerStampResource)
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
    public static MPvpPlayerStamp createEntity(EntityManager em) {
        MPvpPlayerStamp mPvpPlayerStamp = new MPvpPlayerStamp()
            .orderNum(DEFAULT_ORDER_NUM)
            .masterId(DEFAULT_MASTER_ID);
        return mPvpPlayerStamp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPvpPlayerStamp createUpdatedEntity(EntityManager em) {
        MPvpPlayerStamp mPvpPlayerStamp = new MPvpPlayerStamp()
            .orderNum(UPDATED_ORDER_NUM)
            .masterId(UPDATED_MASTER_ID);
        return mPvpPlayerStamp;
    }

    @BeforeEach
    public void initTest() {
        mPvpPlayerStamp = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPvpPlayerStamp() throws Exception {
        int databaseSizeBeforeCreate = mPvpPlayerStampRepository.findAll().size();

        // Create the MPvpPlayerStamp
        MPvpPlayerStampDTO mPvpPlayerStampDTO = mPvpPlayerStampMapper.toDto(mPvpPlayerStamp);
        restMPvpPlayerStampMockMvc.perform(post("/api/m-pvp-player-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpPlayerStampDTO)))
            .andExpect(status().isCreated());

        // Validate the MPvpPlayerStamp in the database
        List<MPvpPlayerStamp> mPvpPlayerStampList = mPvpPlayerStampRepository.findAll();
        assertThat(mPvpPlayerStampList).hasSize(databaseSizeBeforeCreate + 1);
        MPvpPlayerStamp testMPvpPlayerStamp = mPvpPlayerStampList.get(mPvpPlayerStampList.size() - 1);
        assertThat(testMPvpPlayerStamp.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMPvpPlayerStamp.getMasterId()).isEqualTo(DEFAULT_MASTER_ID);
    }

    @Test
    @Transactional
    public void createMPvpPlayerStampWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPvpPlayerStampRepository.findAll().size();

        // Create the MPvpPlayerStamp with an existing ID
        mPvpPlayerStamp.setId(1L);
        MPvpPlayerStampDTO mPvpPlayerStampDTO = mPvpPlayerStampMapper.toDto(mPvpPlayerStamp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPvpPlayerStampMockMvc.perform(post("/api/m-pvp-player-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpPlayerStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpPlayerStamp in the database
        List<MPvpPlayerStamp> mPvpPlayerStampList = mPvpPlayerStampRepository.findAll();
        assertThat(mPvpPlayerStampList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpPlayerStampRepository.findAll().size();
        // set the field null
        mPvpPlayerStamp.setOrderNum(null);

        // Create the MPvpPlayerStamp, which fails.
        MPvpPlayerStampDTO mPvpPlayerStampDTO = mPvpPlayerStampMapper.toDto(mPvpPlayerStamp);

        restMPvpPlayerStampMockMvc.perform(post("/api/m-pvp-player-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpPlayerStampDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpPlayerStamp> mPvpPlayerStampList = mPvpPlayerStampRepository.findAll();
        assertThat(mPvpPlayerStampList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpPlayerStampRepository.findAll().size();
        // set the field null
        mPvpPlayerStamp.setMasterId(null);

        // Create the MPvpPlayerStamp, which fails.
        MPvpPlayerStampDTO mPvpPlayerStampDTO = mPvpPlayerStampMapper.toDto(mPvpPlayerStamp);

        restMPvpPlayerStampMockMvc.perform(post("/api/m-pvp-player-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpPlayerStampDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpPlayerStamp> mPvpPlayerStampList = mPvpPlayerStampRepository.findAll();
        assertThat(mPvpPlayerStampList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStamps() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList
        restMPvpPlayerStampMockMvc.perform(get("/api/m-pvp-player-stamps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpPlayerStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].masterId").value(hasItem(DEFAULT_MASTER_ID)));
    }
    
    @Test
    @Transactional
    public void getMPvpPlayerStamp() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get the mPvpPlayerStamp
        restMPvpPlayerStampMockMvc.perform(get("/api/m-pvp-player-stamps/{id}", mPvpPlayerStamp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPvpPlayerStamp.getId().intValue()))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.masterId").value(DEFAULT_MASTER_ID));
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMPvpPlayerStampShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mPvpPlayerStampList where orderNum equals to UPDATED_ORDER_NUM
        defaultMPvpPlayerStampShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMPvpPlayerStampShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mPvpPlayerStampList where orderNum equals to UPDATED_ORDER_NUM
        defaultMPvpPlayerStampShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where orderNum is not null
        defaultMPvpPlayerStampShouldBeFound("orderNum.specified=true");

        // Get all the mPvpPlayerStampList where orderNum is null
        defaultMPvpPlayerStampShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMPvpPlayerStampShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mPvpPlayerStampList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMPvpPlayerStampShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMPvpPlayerStampShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mPvpPlayerStampList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMPvpPlayerStampShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByMasterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where masterId equals to DEFAULT_MASTER_ID
        defaultMPvpPlayerStampShouldBeFound("masterId.equals=" + DEFAULT_MASTER_ID);

        // Get all the mPvpPlayerStampList where masterId equals to UPDATED_MASTER_ID
        defaultMPvpPlayerStampShouldNotBeFound("masterId.equals=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByMasterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where masterId in DEFAULT_MASTER_ID or UPDATED_MASTER_ID
        defaultMPvpPlayerStampShouldBeFound("masterId.in=" + DEFAULT_MASTER_ID + "," + UPDATED_MASTER_ID);

        // Get all the mPvpPlayerStampList where masterId equals to UPDATED_MASTER_ID
        defaultMPvpPlayerStampShouldNotBeFound("masterId.in=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByMasterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where masterId is not null
        defaultMPvpPlayerStampShouldBeFound("masterId.specified=true");

        // Get all the mPvpPlayerStampList where masterId is null
        defaultMPvpPlayerStampShouldNotBeFound("masterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByMasterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where masterId greater than or equals to DEFAULT_MASTER_ID
        defaultMPvpPlayerStampShouldBeFound("masterId.greaterOrEqualThan=" + DEFAULT_MASTER_ID);

        // Get all the mPvpPlayerStampList where masterId greater than or equals to UPDATED_MASTER_ID
        defaultMPvpPlayerStampShouldNotBeFound("masterId.greaterOrEqualThan=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpPlayerStampsByMasterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        // Get all the mPvpPlayerStampList where masterId less than or equals to DEFAULT_MASTER_ID
        defaultMPvpPlayerStampShouldNotBeFound("masterId.lessThan=" + DEFAULT_MASTER_ID);

        // Get all the mPvpPlayerStampList where masterId less than or equals to UPDATED_MASTER_ID
        defaultMPvpPlayerStampShouldBeFound("masterId.lessThan=" + UPDATED_MASTER_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPvpPlayerStampShouldBeFound(String filter) throws Exception {
        restMPvpPlayerStampMockMvc.perform(get("/api/m-pvp-player-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpPlayerStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].masterId").value(hasItem(DEFAULT_MASTER_ID)));

        // Check, that the count call also returns 1
        restMPvpPlayerStampMockMvc.perform(get("/api/m-pvp-player-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPvpPlayerStampShouldNotBeFound(String filter) throws Exception {
        restMPvpPlayerStampMockMvc.perform(get("/api/m-pvp-player-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPvpPlayerStampMockMvc.perform(get("/api/m-pvp-player-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPvpPlayerStamp() throws Exception {
        // Get the mPvpPlayerStamp
        restMPvpPlayerStampMockMvc.perform(get("/api/m-pvp-player-stamps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPvpPlayerStamp() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        int databaseSizeBeforeUpdate = mPvpPlayerStampRepository.findAll().size();

        // Update the mPvpPlayerStamp
        MPvpPlayerStamp updatedMPvpPlayerStamp = mPvpPlayerStampRepository.findById(mPvpPlayerStamp.getId()).get();
        // Disconnect from session so that the updates on updatedMPvpPlayerStamp are not directly saved in db
        em.detach(updatedMPvpPlayerStamp);
        updatedMPvpPlayerStamp
            .orderNum(UPDATED_ORDER_NUM)
            .masterId(UPDATED_MASTER_ID);
        MPvpPlayerStampDTO mPvpPlayerStampDTO = mPvpPlayerStampMapper.toDto(updatedMPvpPlayerStamp);

        restMPvpPlayerStampMockMvc.perform(put("/api/m-pvp-player-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpPlayerStampDTO)))
            .andExpect(status().isOk());

        // Validate the MPvpPlayerStamp in the database
        List<MPvpPlayerStamp> mPvpPlayerStampList = mPvpPlayerStampRepository.findAll();
        assertThat(mPvpPlayerStampList).hasSize(databaseSizeBeforeUpdate);
        MPvpPlayerStamp testMPvpPlayerStamp = mPvpPlayerStampList.get(mPvpPlayerStampList.size() - 1);
        assertThat(testMPvpPlayerStamp.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMPvpPlayerStamp.getMasterId()).isEqualTo(UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMPvpPlayerStamp() throws Exception {
        int databaseSizeBeforeUpdate = mPvpPlayerStampRepository.findAll().size();

        // Create the MPvpPlayerStamp
        MPvpPlayerStampDTO mPvpPlayerStampDTO = mPvpPlayerStampMapper.toDto(mPvpPlayerStamp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPvpPlayerStampMockMvc.perform(put("/api/m-pvp-player-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpPlayerStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpPlayerStamp in the database
        List<MPvpPlayerStamp> mPvpPlayerStampList = mPvpPlayerStampRepository.findAll();
        assertThat(mPvpPlayerStampList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPvpPlayerStamp() throws Exception {
        // Initialize the database
        mPvpPlayerStampRepository.saveAndFlush(mPvpPlayerStamp);

        int databaseSizeBeforeDelete = mPvpPlayerStampRepository.findAll().size();

        // Delete the mPvpPlayerStamp
        restMPvpPlayerStampMockMvc.perform(delete("/api/m-pvp-player-stamps/{id}", mPvpPlayerStamp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPvpPlayerStamp> mPvpPlayerStampList = mPvpPlayerStampRepository.findAll();
        assertThat(mPvpPlayerStampList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpPlayerStamp.class);
        MPvpPlayerStamp mPvpPlayerStamp1 = new MPvpPlayerStamp();
        mPvpPlayerStamp1.setId(1L);
        MPvpPlayerStamp mPvpPlayerStamp2 = new MPvpPlayerStamp();
        mPvpPlayerStamp2.setId(mPvpPlayerStamp1.getId());
        assertThat(mPvpPlayerStamp1).isEqualTo(mPvpPlayerStamp2);
        mPvpPlayerStamp2.setId(2L);
        assertThat(mPvpPlayerStamp1).isNotEqualTo(mPvpPlayerStamp2);
        mPvpPlayerStamp1.setId(null);
        assertThat(mPvpPlayerStamp1).isNotEqualTo(mPvpPlayerStamp2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpPlayerStampDTO.class);
        MPvpPlayerStampDTO mPvpPlayerStampDTO1 = new MPvpPlayerStampDTO();
        mPvpPlayerStampDTO1.setId(1L);
        MPvpPlayerStampDTO mPvpPlayerStampDTO2 = new MPvpPlayerStampDTO();
        assertThat(mPvpPlayerStampDTO1).isNotEqualTo(mPvpPlayerStampDTO2);
        mPvpPlayerStampDTO2.setId(mPvpPlayerStampDTO1.getId());
        assertThat(mPvpPlayerStampDTO1).isEqualTo(mPvpPlayerStampDTO2);
        mPvpPlayerStampDTO2.setId(2L);
        assertThat(mPvpPlayerStampDTO1).isNotEqualTo(mPvpPlayerStampDTO2);
        mPvpPlayerStampDTO1.setId(null);
        assertThat(mPvpPlayerStampDTO1).isNotEqualTo(mPvpPlayerStampDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPvpPlayerStampMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPvpPlayerStampMapper.fromId(null)).isNull();
    }
}
