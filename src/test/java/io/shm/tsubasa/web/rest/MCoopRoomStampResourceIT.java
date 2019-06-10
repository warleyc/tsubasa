package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCoopRoomStamp;
import io.shm.tsubasa.repository.MCoopRoomStampRepository;
import io.shm.tsubasa.service.MCoopRoomStampService;
import io.shm.tsubasa.service.dto.MCoopRoomStampDTO;
import io.shm.tsubasa.service.mapper.MCoopRoomStampMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCoopRoomStampCriteria;
import io.shm.tsubasa.service.MCoopRoomStampQueryService;

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
 * Integration tests for the {@Link MCoopRoomStampResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCoopRoomStampResourceIT {

    private static final Integer DEFAULT_ROLE = 1;
    private static final Integer UPDATED_ROLE = 2;

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    private static final Integer DEFAULT_MASTER_ID = 1;
    private static final Integer UPDATED_MASTER_ID = 2;

    @Autowired
    private MCoopRoomStampRepository mCoopRoomStampRepository;

    @Autowired
    private MCoopRoomStampMapper mCoopRoomStampMapper;

    @Autowired
    private MCoopRoomStampService mCoopRoomStampService;

    @Autowired
    private MCoopRoomStampQueryService mCoopRoomStampQueryService;

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

    private MockMvc restMCoopRoomStampMockMvc;

    private MCoopRoomStamp mCoopRoomStamp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCoopRoomStampResource mCoopRoomStampResource = new MCoopRoomStampResource(mCoopRoomStampService, mCoopRoomStampQueryService);
        this.restMCoopRoomStampMockMvc = MockMvcBuilders.standaloneSetup(mCoopRoomStampResource)
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
    public static MCoopRoomStamp createEntity(EntityManager em) {
        MCoopRoomStamp mCoopRoomStamp = new MCoopRoomStamp()
            .role(DEFAULT_ROLE)
            .orderNum(DEFAULT_ORDER_NUM)
            .masterId(DEFAULT_MASTER_ID);
        return mCoopRoomStamp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCoopRoomStamp createUpdatedEntity(EntityManager em) {
        MCoopRoomStamp mCoopRoomStamp = new MCoopRoomStamp()
            .role(UPDATED_ROLE)
            .orderNum(UPDATED_ORDER_NUM)
            .masterId(UPDATED_MASTER_ID);
        return mCoopRoomStamp;
    }

    @BeforeEach
    public void initTest() {
        mCoopRoomStamp = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCoopRoomStamp() throws Exception {
        int databaseSizeBeforeCreate = mCoopRoomStampRepository.findAll().size();

        // Create the MCoopRoomStamp
        MCoopRoomStampDTO mCoopRoomStampDTO = mCoopRoomStampMapper.toDto(mCoopRoomStamp);
        restMCoopRoomStampMockMvc.perform(post("/api/m-coop-room-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCoopRoomStampDTO)))
            .andExpect(status().isCreated());

        // Validate the MCoopRoomStamp in the database
        List<MCoopRoomStamp> mCoopRoomStampList = mCoopRoomStampRepository.findAll();
        assertThat(mCoopRoomStampList).hasSize(databaseSizeBeforeCreate + 1);
        MCoopRoomStamp testMCoopRoomStamp = mCoopRoomStampList.get(mCoopRoomStampList.size() - 1);
        assertThat(testMCoopRoomStamp.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testMCoopRoomStamp.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMCoopRoomStamp.getMasterId()).isEqualTo(DEFAULT_MASTER_ID);
    }

    @Test
    @Transactional
    public void createMCoopRoomStampWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCoopRoomStampRepository.findAll().size();

        // Create the MCoopRoomStamp with an existing ID
        mCoopRoomStamp.setId(1L);
        MCoopRoomStampDTO mCoopRoomStampDTO = mCoopRoomStampMapper.toDto(mCoopRoomStamp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCoopRoomStampMockMvc.perform(post("/api/m-coop-room-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCoopRoomStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCoopRoomStamp in the database
        List<MCoopRoomStamp> mCoopRoomStampList = mCoopRoomStampRepository.findAll();
        assertThat(mCoopRoomStampList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCoopRoomStampRepository.findAll().size();
        // set the field null
        mCoopRoomStamp.setRole(null);

        // Create the MCoopRoomStamp, which fails.
        MCoopRoomStampDTO mCoopRoomStampDTO = mCoopRoomStampMapper.toDto(mCoopRoomStamp);

        restMCoopRoomStampMockMvc.perform(post("/api/m-coop-room-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCoopRoomStampDTO)))
            .andExpect(status().isBadRequest());

        List<MCoopRoomStamp> mCoopRoomStampList = mCoopRoomStampRepository.findAll();
        assertThat(mCoopRoomStampList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCoopRoomStampRepository.findAll().size();
        // set the field null
        mCoopRoomStamp.setOrderNum(null);

        // Create the MCoopRoomStamp, which fails.
        MCoopRoomStampDTO mCoopRoomStampDTO = mCoopRoomStampMapper.toDto(mCoopRoomStamp);

        restMCoopRoomStampMockMvc.perform(post("/api/m-coop-room-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCoopRoomStampDTO)))
            .andExpect(status().isBadRequest());

        List<MCoopRoomStamp> mCoopRoomStampList = mCoopRoomStampRepository.findAll();
        assertThat(mCoopRoomStampList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCoopRoomStampRepository.findAll().size();
        // set the field null
        mCoopRoomStamp.setMasterId(null);

        // Create the MCoopRoomStamp, which fails.
        MCoopRoomStampDTO mCoopRoomStampDTO = mCoopRoomStampMapper.toDto(mCoopRoomStamp);

        restMCoopRoomStampMockMvc.perform(post("/api/m-coop-room-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCoopRoomStampDTO)))
            .andExpect(status().isBadRequest());

        List<MCoopRoomStamp> mCoopRoomStampList = mCoopRoomStampRepository.findAll();
        assertThat(mCoopRoomStampList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStamps() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList
        restMCoopRoomStampMockMvc.perform(get("/api/m-coop-room-stamps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCoopRoomStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].masterId").value(hasItem(DEFAULT_MASTER_ID)));
    }
    
    @Test
    @Transactional
    public void getMCoopRoomStamp() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get the mCoopRoomStamp
        restMCoopRoomStampMockMvc.perform(get("/api/m-coop-room-stamps/{id}", mCoopRoomStamp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCoopRoomStamp.getId().intValue()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.masterId").value(DEFAULT_MASTER_ID));
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByRoleIsEqualToSomething() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where role equals to DEFAULT_ROLE
        defaultMCoopRoomStampShouldBeFound("role.equals=" + DEFAULT_ROLE);

        // Get all the mCoopRoomStampList where role equals to UPDATED_ROLE
        defaultMCoopRoomStampShouldNotBeFound("role.equals=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByRoleIsInShouldWork() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where role in DEFAULT_ROLE or UPDATED_ROLE
        defaultMCoopRoomStampShouldBeFound("role.in=" + DEFAULT_ROLE + "," + UPDATED_ROLE);

        // Get all the mCoopRoomStampList where role equals to UPDATED_ROLE
        defaultMCoopRoomStampShouldNotBeFound("role.in=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByRoleIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where role is not null
        defaultMCoopRoomStampShouldBeFound("role.specified=true");

        // Get all the mCoopRoomStampList where role is null
        defaultMCoopRoomStampShouldNotBeFound("role.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByRoleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where role greater than or equals to DEFAULT_ROLE
        defaultMCoopRoomStampShouldBeFound("role.greaterOrEqualThan=" + DEFAULT_ROLE);

        // Get all the mCoopRoomStampList where role greater than or equals to UPDATED_ROLE
        defaultMCoopRoomStampShouldNotBeFound("role.greaterOrEqualThan=" + UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByRoleIsLessThanSomething() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where role less than or equals to DEFAULT_ROLE
        defaultMCoopRoomStampShouldNotBeFound("role.lessThan=" + DEFAULT_ROLE);

        // Get all the mCoopRoomStampList where role less than or equals to UPDATED_ROLE
        defaultMCoopRoomStampShouldBeFound("role.lessThan=" + UPDATED_ROLE);
    }


    @Test
    @Transactional
    public void getAllMCoopRoomStampsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMCoopRoomStampShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mCoopRoomStampList where orderNum equals to UPDATED_ORDER_NUM
        defaultMCoopRoomStampShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMCoopRoomStampShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mCoopRoomStampList where orderNum equals to UPDATED_ORDER_NUM
        defaultMCoopRoomStampShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where orderNum is not null
        defaultMCoopRoomStampShouldBeFound("orderNum.specified=true");

        // Get all the mCoopRoomStampList where orderNum is null
        defaultMCoopRoomStampShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMCoopRoomStampShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mCoopRoomStampList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMCoopRoomStampShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMCoopRoomStampShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mCoopRoomStampList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMCoopRoomStampShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMCoopRoomStampsByMasterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where masterId equals to DEFAULT_MASTER_ID
        defaultMCoopRoomStampShouldBeFound("masterId.equals=" + DEFAULT_MASTER_ID);

        // Get all the mCoopRoomStampList where masterId equals to UPDATED_MASTER_ID
        defaultMCoopRoomStampShouldNotBeFound("masterId.equals=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByMasterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where masterId in DEFAULT_MASTER_ID or UPDATED_MASTER_ID
        defaultMCoopRoomStampShouldBeFound("masterId.in=" + DEFAULT_MASTER_ID + "," + UPDATED_MASTER_ID);

        // Get all the mCoopRoomStampList where masterId equals to UPDATED_MASTER_ID
        defaultMCoopRoomStampShouldNotBeFound("masterId.in=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByMasterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where masterId is not null
        defaultMCoopRoomStampShouldBeFound("masterId.specified=true");

        // Get all the mCoopRoomStampList where masterId is null
        defaultMCoopRoomStampShouldNotBeFound("masterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByMasterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where masterId greater than or equals to DEFAULT_MASTER_ID
        defaultMCoopRoomStampShouldBeFound("masterId.greaterOrEqualThan=" + DEFAULT_MASTER_ID);

        // Get all the mCoopRoomStampList where masterId greater than or equals to UPDATED_MASTER_ID
        defaultMCoopRoomStampShouldNotBeFound("masterId.greaterOrEqualThan=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCoopRoomStampsByMasterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        // Get all the mCoopRoomStampList where masterId less than or equals to DEFAULT_MASTER_ID
        defaultMCoopRoomStampShouldNotBeFound("masterId.lessThan=" + DEFAULT_MASTER_ID);

        // Get all the mCoopRoomStampList where masterId less than or equals to UPDATED_MASTER_ID
        defaultMCoopRoomStampShouldBeFound("masterId.lessThan=" + UPDATED_MASTER_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCoopRoomStampShouldBeFound(String filter) throws Exception {
        restMCoopRoomStampMockMvc.perform(get("/api/m-coop-room-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCoopRoomStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].masterId").value(hasItem(DEFAULT_MASTER_ID)));

        // Check, that the count call also returns 1
        restMCoopRoomStampMockMvc.perform(get("/api/m-coop-room-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCoopRoomStampShouldNotBeFound(String filter) throws Exception {
        restMCoopRoomStampMockMvc.perform(get("/api/m-coop-room-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCoopRoomStampMockMvc.perform(get("/api/m-coop-room-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCoopRoomStamp() throws Exception {
        // Get the mCoopRoomStamp
        restMCoopRoomStampMockMvc.perform(get("/api/m-coop-room-stamps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCoopRoomStamp() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        int databaseSizeBeforeUpdate = mCoopRoomStampRepository.findAll().size();

        // Update the mCoopRoomStamp
        MCoopRoomStamp updatedMCoopRoomStamp = mCoopRoomStampRepository.findById(mCoopRoomStamp.getId()).get();
        // Disconnect from session so that the updates on updatedMCoopRoomStamp are not directly saved in db
        em.detach(updatedMCoopRoomStamp);
        updatedMCoopRoomStamp
            .role(UPDATED_ROLE)
            .orderNum(UPDATED_ORDER_NUM)
            .masterId(UPDATED_MASTER_ID);
        MCoopRoomStampDTO mCoopRoomStampDTO = mCoopRoomStampMapper.toDto(updatedMCoopRoomStamp);

        restMCoopRoomStampMockMvc.perform(put("/api/m-coop-room-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCoopRoomStampDTO)))
            .andExpect(status().isOk());

        // Validate the MCoopRoomStamp in the database
        List<MCoopRoomStamp> mCoopRoomStampList = mCoopRoomStampRepository.findAll();
        assertThat(mCoopRoomStampList).hasSize(databaseSizeBeforeUpdate);
        MCoopRoomStamp testMCoopRoomStamp = mCoopRoomStampList.get(mCoopRoomStampList.size() - 1);
        assertThat(testMCoopRoomStamp.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testMCoopRoomStamp.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMCoopRoomStamp.getMasterId()).isEqualTo(UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMCoopRoomStamp() throws Exception {
        int databaseSizeBeforeUpdate = mCoopRoomStampRepository.findAll().size();

        // Create the MCoopRoomStamp
        MCoopRoomStampDTO mCoopRoomStampDTO = mCoopRoomStampMapper.toDto(mCoopRoomStamp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCoopRoomStampMockMvc.perform(put("/api/m-coop-room-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCoopRoomStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCoopRoomStamp in the database
        List<MCoopRoomStamp> mCoopRoomStampList = mCoopRoomStampRepository.findAll();
        assertThat(mCoopRoomStampList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCoopRoomStamp() throws Exception {
        // Initialize the database
        mCoopRoomStampRepository.saveAndFlush(mCoopRoomStamp);

        int databaseSizeBeforeDelete = mCoopRoomStampRepository.findAll().size();

        // Delete the mCoopRoomStamp
        restMCoopRoomStampMockMvc.perform(delete("/api/m-coop-room-stamps/{id}", mCoopRoomStamp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCoopRoomStamp> mCoopRoomStampList = mCoopRoomStampRepository.findAll();
        assertThat(mCoopRoomStampList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCoopRoomStamp.class);
        MCoopRoomStamp mCoopRoomStamp1 = new MCoopRoomStamp();
        mCoopRoomStamp1.setId(1L);
        MCoopRoomStamp mCoopRoomStamp2 = new MCoopRoomStamp();
        mCoopRoomStamp2.setId(mCoopRoomStamp1.getId());
        assertThat(mCoopRoomStamp1).isEqualTo(mCoopRoomStamp2);
        mCoopRoomStamp2.setId(2L);
        assertThat(mCoopRoomStamp1).isNotEqualTo(mCoopRoomStamp2);
        mCoopRoomStamp1.setId(null);
        assertThat(mCoopRoomStamp1).isNotEqualTo(mCoopRoomStamp2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCoopRoomStampDTO.class);
        MCoopRoomStampDTO mCoopRoomStampDTO1 = new MCoopRoomStampDTO();
        mCoopRoomStampDTO1.setId(1L);
        MCoopRoomStampDTO mCoopRoomStampDTO2 = new MCoopRoomStampDTO();
        assertThat(mCoopRoomStampDTO1).isNotEqualTo(mCoopRoomStampDTO2);
        mCoopRoomStampDTO2.setId(mCoopRoomStampDTO1.getId());
        assertThat(mCoopRoomStampDTO1).isEqualTo(mCoopRoomStampDTO2);
        mCoopRoomStampDTO2.setId(2L);
        assertThat(mCoopRoomStampDTO1).isNotEqualTo(mCoopRoomStampDTO2);
        mCoopRoomStampDTO1.setId(null);
        assertThat(mCoopRoomStampDTO1).isNotEqualTo(mCoopRoomStampDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCoopRoomStampMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCoopRoomStampMapper.fromId(null)).isNull();
    }
}
