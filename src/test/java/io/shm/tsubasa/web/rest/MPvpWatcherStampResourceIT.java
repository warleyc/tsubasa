package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MPvpWatcherStamp;
import io.shm.tsubasa.repository.MPvpWatcherStampRepository;
import io.shm.tsubasa.service.MPvpWatcherStampService;
import io.shm.tsubasa.service.dto.MPvpWatcherStampDTO;
import io.shm.tsubasa.service.mapper.MPvpWatcherStampMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MPvpWatcherStampCriteria;
import io.shm.tsubasa.service.MPvpWatcherStampQueryService;

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
 * Integration tests for the {@Link MPvpWatcherStampResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MPvpWatcherStampResourceIT {

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    private static final Integer DEFAULT_MASTER_ID = 1;
    private static final Integer UPDATED_MASTER_ID = 2;

    @Autowired
    private MPvpWatcherStampRepository mPvpWatcherStampRepository;

    @Autowired
    private MPvpWatcherStampMapper mPvpWatcherStampMapper;

    @Autowired
    private MPvpWatcherStampService mPvpWatcherStampService;

    @Autowired
    private MPvpWatcherStampQueryService mPvpWatcherStampQueryService;

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

    private MockMvc restMPvpWatcherStampMockMvc;

    private MPvpWatcherStamp mPvpWatcherStamp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MPvpWatcherStampResource mPvpWatcherStampResource = new MPvpWatcherStampResource(mPvpWatcherStampService, mPvpWatcherStampQueryService);
        this.restMPvpWatcherStampMockMvc = MockMvcBuilders.standaloneSetup(mPvpWatcherStampResource)
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
    public static MPvpWatcherStamp createEntity(EntityManager em) {
        MPvpWatcherStamp mPvpWatcherStamp = new MPvpWatcherStamp()
            .orderNum(DEFAULT_ORDER_NUM)
            .masterId(DEFAULT_MASTER_ID);
        return mPvpWatcherStamp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MPvpWatcherStamp createUpdatedEntity(EntityManager em) {
        MPvpWatcherStamp mPvpWatcherStamp = new MPvpWatcherStamp()
            .orderNum(UPDATED_ORDER_NUM)
            .masterId(UPDATED_MASTER_ID);
        return mPvpWatcherStamp;
    }

    @BeforeEach
    public void initTest() {
        mPvpWatcherStamp = createEntity(em);
    }

    @Test
    @Transactional
    public void createMPvpWatcherStamp() throws Exception {
        int databaseSizeBeforeCreate = mPvpWatcherStampRepository.findAll().size();

        // Create the MPvpWatcherStamp
        MPvpWatcherStampDTO mPvpWatcherStampDTO = mPvpWatcherStampMapper.toDto(mPvpWatcherStamp);
        restMPvpWatcherStampMockMvc.perform(post("/api/m-pvp-watcher-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWatcherStampDTO)))
            .andExpect(status().isCreated());

        // Validate the MPvpWatcherStamp in the database
        List<MPvpWatcherStamp> mPvpWatcherStampList = mPvpWatcherStampRepository.findAll();
        assertThat(mPvpWatcherStampList).hasSize(databaseSizeBeforeCreate + 1);
        MPvpWatcherStamp testMPvpWatcherStamp = mPvpWatcherStampList.get(mPvpWatcherStampList.size() - 1);
        assertThat(testMPvpWatcherStamp.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMPvpWatcherStamp.getMasterId()).isEqualTo(DEFAULT_MASTER_ID);
    }

    @Test
    @Transactional
    public void createMPvpWatcherStampWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mPvpWatcherStampRepository.findAll().size();

        // Create the MPvpWatcherStamp with an existing ID
        mPvpWatcherStamp.setId(1L);
        MPvpWatcherStampDTO mPvpWatcherStampDTO = mPvpWatcherStampMapper.toDto(mPvpWatcherStamp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMPvpWatcherStampMockMvc.perform(post("/api/m-pvp-watcher-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWatcherStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpWatcherStamp in the database
        List<MPvpWatcherStamp> mPvpWatcherStampList = mPvpWatcherStampRepository.findAll();
        assertThat(mPvpWatcherStampList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpWatcherStampRepository.findAll().size();
        // set the field null
        mPvpWatcherStamp.setOrderNum(null);

        // Create the MPvpWatcherStamp, which fails.
        MPvpWatcherStampDTO mPvpWatcherStampDTO = mPvpWatcherStampMapper.toDto(mPvpWatcherStamp);

        restMPvpWatcherStampMockMvc.perform(post("/api/m-pvp-watcher-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWatcherStampDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpWatcherStamp> mPvpWatcherStampList = mPvpWatcherStampRepository.findAll();
        assertThat(mPvpWatcherStampList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mPvpWatcherStampRepository.findAll().size();
        // set the field null
        mPvpWatcherStamp.setMasterId(null);

        // Create the MPvpWatcherStamp, which fails.
        MPvpWatcherStampDTO mPvpWatcherStampDTO = mPvpWatcherStampMapper.toDto(mPvpWatcherStamp);

        restMPvpWatcherStampMockMvc.perform(post("/api/m-pvp-watcher-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWatcherStampDTO)))
            .andExpect(status().isBadRequest());

        List<MPvpWatcherStamp> mPvpWatcherStampList = mPvpWatcherStampRepository.findAll();
        assertThat(mPvpWatcherStampList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStamps() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList
        restMPvpWatcherStampMockMvc.perform(get("/api/m-pvp-watcher-stamps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpWatcherStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].masterId").value(hasItem(DEFAULT_MASTER_ID)));
    }
    
    @Test
    @Transactional
    public void getMPvpWatcherStamp() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get the mPvpWatcherStamp
        restMPvpWatcherStampMockMvc.perform(get("/api/m-pvp-watcher-stamps/{id}", mPvpWatcherStamp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mPvpWatcherStamp.getId().intValue()))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.masterId").value(DEFAULT_MASTER_ID));
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMPvpWatcherStampShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mPvpWatcherStampList where orderNum equals to UPDATED_ORDER_NUM
        defaultMPvpWatcherStampShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMPvpWatcherStampShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mPvpWatcherStampList where orderNum equals to UPDATED_ORDER_NUM
        defaultMPvpWatcherStampShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where orderNum is not null
        defaultMPvpWatcherStampShouldBeFound("orderNum.specified=true");

        // Get all the mPvpWatcherStampList where orderNum is null
        defaultMPvpWatcherStampShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMPvpWatcherStampShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mPvpWatcherStampList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMPvpWatcherStampShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMPvpWatcherStampShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mPvpWatcherStampList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMPvpWatcherStampShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByMasterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where masterId equals to DEFAULT_MASTER_ID
        defaultMPvpWatcherStampShouldBeFound("masterId.equals=" + DEFAULT_MASTER_ID);

        // Get all the mPvpWatcherStampList where masterId equals to UPDATED_MASTER_ID
        defaultMPvpWatcherStampShouldNotBeFound("masterId.equals=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByMasterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where masterId in DEFAULT_MASTER_ID or UPDATED_MASTER_ID
        defaultMPvpWatcherStampShouldBeFound("masterId.in=" + DEFAULT_MASTER_ID + "," + UPDATED_MASTER_ID);

        // Get all the mPvpWatcherStampList where masterId equals to UPDATED_MASTER_ID
        defaultMPvpWatcherStampShouldNotBeFound("masterId.in=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByMasterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where masterId is not null
        defaultMPvpWatcherStampShouldBeFound("masterId.specified=true");

        // Get all the mPvpWatcherStampList where masterId is null
        defaultMPvpWatcherStampShouldNotBeFound("masterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByMasterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where masterId greater than or equals to DEFAULT_MASTER_ID
        defaultMPvpWatcherStampShouldBeFound("masterId.greaterOrEqualThan=" + DEFAULT_MASTER_ID);

        // Get all the mPvpWatcherStampList where masterId greater than or equals to UPDATED_MASTER_ID
        defaultMPvpWatcherStampShouldNotBeFound("masterId.greaterOrEqualThan=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMPvpWatcherStampsByMasterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        // Get all the mPvpWatcherStampList where masterId less than or equals to DEFAULT_MASTER_ID
        defaultMPvpWatcherStampShouldNotBeFound("masterId.lessThan=" + DEFAULT_MASTER_ID);

        // Get all the mPvpWatcherStampList where masterId less than or equals to UPDATED_MASTER_ID
        defaultMPvpWatcherStampShouldBeFound("masterId.lessThan=" + UPDATED_MASTER_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMPvpWatcherStampShouldBeFound(String filter) throws Exception {
        restMPvpWatcherStampMockMvc.perform(get("/api/m-pvp-watcher-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mPvpWatcherStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].masterId").value(hasItem(DEFAULT_MASTER_ID)));

        // Check, that the count call also returns 1
        restMPvpWatcherStampMockMvc.perform(get("/api/m-pvp-watcher-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMPvpWatcherStampShouldNotBeFound(String filter) throws Exception {
        restMPvpWatcherStampMockMvc.perform(get("/api/m-pvp-watcher-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMPvpWatcherStampMockMvc.perform(get("/api/m-pvp-watcher-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMPvpWatcherStamp() throws Exception {
        // Get the mPvpWatcherStamp
        restMPvpWatcherStampMockMvc.perform(get("/api/m-pvp-watcher-stamps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMPvpWatcherStamp() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        int databaseSizeBeforeUpdate = mPvpWatcherStampRepository.findAll().size();

        // Update the mPvpWatcherStamp
        MPvpWatcherStamp updatedMPvpWatcherStamp = mPvpWatcherStampRepository.findById(mPvpWatcherStamp.getId()).get();
        // Disconnect from session so that the updates on updatedMPvpWatcherStamp are not directly saved in db
        em.detach(updatedMPvpWatcherStamp);
        updatedMPvpWatcherStamp
            .orderNum(UPDATED_ORDER_NUM)
            .masterId(UPDATED_MASTER_ID);
        MPvpWatcherStampDTO mPvpWatcherStampDTO = mPvpWatcherStampMapper.toDto(updatedMPvpWatcherStamp);

        restMPvpWatcherStampMockMvc.perform(put("/api/m-pvp-watcher-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWatcherStampDTO)))
            .andExpect(status().isOk());

        // Validate the MPvpWatcherStamp in the database
        List<MPvpWatcherStamp> mPvpWatcherStampList = mPvpWatcherStampRepository.findAll();
        assertThat(mPvpWatcherStampList).hasSize(databaseSizeBeforeUpdate);
        MPvpWatcherStamp testMPvpWatcherStamp = mPvpWatcherStampList.get(mPvpWatcherStampList.size() - 1);
        assertThat(testMPvpWatcherStamp.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMPvpWatcherStamp.getMasterId()).isEqualTo(UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMPvpWatcherStamp() throws Exception {
        int databaseSizeBeforeUpdate = mPvpWatcherStampRepository.findAll().size();

        // Create the MPvpWatcherStamp
        MPvpWatcherStampDTO mPvpWatcherStampDTO = mPvpWatcherStampMapper.toDto(mPvpWatcherStamp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMPvpWatcherStampMockMvc.perform(put("/api/m-pvp-watcher-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mPvpWatcherStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MPvpWatcherStamp in the database
        List<MPvpWatcherStamp> mPvpWatcherStampList = mPvpWatcherStampRepository.findAll();
        assertThat(mPvpWatcherStampList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMPvpWatcherStamp() throws Exception {
        // Initialize the database
        mPvpWatcherStampRepository.saveAndFlush(mPvpWatcherStamp);

        int databaseSizeBeforeDelete = mPvpWatcherStampRepository.findAll().size();

        // Delete the mPvpWatcherStamp
        restMPvpWatcherStampMockMvc.perform(delete("/api/m-pvp-watcher-stamps/{id}", mPvpWatcherStamp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MPvpWatcherStamp> mPvpWatcherStampList = mPvpWatcherStampRepository.findAll();
        assertThat(mPvpWatcherStampList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpWatcherStamp.class);
        MPvpWatcherStamp mPvpWatcherStamp1 = new MPvpWatcherStamp();
        mPvpWatcherStamp1.setId(1L);
        MPvpWatcherStamp mPvpWatcherStamp2 = new MPvpWatcherStamp();
        mPvpWatcherStamp2.setId(mPvpWatcherStamp1.getId());
        assertThat(mPvpWatcherStamp1).isEqualTo(mPvpWatcherStamp2);
        mPvpWatcherStamp2.setId(2L);
        assertThat(mPvpWatcherStamp1).isNotEqualTo(mPvpWatcherStamp2);
        mPvpWatcherStamp1.setId(null);
        assertThat(mPvpWatcherStamp1).isNotEqualTo(mPvpWatcherStamp2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MPvpWatcherStampDTO.class);
        MPvpWatcherStampDTO mPvpWatcherStampDTO1 = new MPvpWatcherStampDTO();
        mPvpWatcherStampDTO1.setId(1L);
        MPvpWatcherStampDTO mPvpWatcherStampDTO2 = new MPvpWatcherStampDTO();
        assertThat(mPvpWatcherStampDTO1).isNotEqualTo(mPvpWatcherStampDTO2);
        mPvpWatcherStampDTO2.setId(mPvpWatcherStampDTO1.getId());
        assertThat(mPvpWatcherStampDTO1).isEqualTo(mPvpWatcherStampDTO2);
        mPvpWatcherStampDTO2.setId(2L);
        assertThat(mPvpWatcherStampDTO1).isNotEqualTo(mPvpWatcherStampDTO2);
        mPvpWatcherStampDTO1.setId(null);
        assertThat(mPvpWatcherStampDTO1).isNotEqualTo(mPvpWatcherStampDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mPvpWatcherStampMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mPvpWatcherStampMapper.fromId(null)).isNull();
    }
}
