package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGuildChatDefaultStamp;
import io.shm.tsubasa.repository.MGuildChatDefaultStampRepository;
import io.shm.tsubasa.service.MGuildChatDefaultStampService;
import io.shm.tsubasa.service.dto.MGuildChatDefaultStampDTO;
import io.shm.tsubasa.service.mapper.MGuildChatDefaultStampMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGuildChatDefaultStampCriteria;
import io.shm.tsubasa.service.MGuildChatDefaultStampQueryService;

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
 * Integration tests for the {@Link MGuildChatDefaultStampResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGuildChatDefaultStampResourceIT {

    private static final Integer DEFAULT_MASTER_ID = 1;
    private static final Integer UPDATED_MASTER_ID = 2;

    @Autowired
    private MGuildChatDefaultStampRepository mGuildChatDefaultStampRepository;

    @Autowired
    private MGuildChatDefaultStampMapper mGuildChatDefaultStampMapper;

    @Autowired
    private MGuildChatDefaultStampService mGuildChatDefaultStampService;

    @Autowired
    private MGuildChatDefaultStampQueryService mGuildChatDefaultStampQueryService;

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

    private MockMvc restMGuildChatDefaultStampMockMvc;

    private MGuildChatDefaultStamp mGuildChatDefaultStamp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGuildChatDefaultStampResource mGuildChatDefaultStampResource = new MGuildChatDefaultStampResource(mGuildChatDefaultStampService, mGuildChatDefaultStampQueryService);
        this.restMGuildChatDefaultStampMockMvc = MockMvcBuilders.standaloneSetup(mGuildChatDefaultStampResource)
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
    public static MGuildChatDefaultStamp createEntity(EntityManager em) {
        MGuildChatDefaultStamp mGuildChatDefaultStamp = new MGuildChatDefaultStamp()
            .masterId(DEFAULT_MASTER_ID);
        return mGuildChatDefaultStamp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGuildChatDefaultStamp createUpdatedEntity(EntityManager em) {
        MGuildChatDefaultStamp mGuildChatDefaultStamp = new MGuildChatDefaultStamp()
            .masterId(UPDATED_MASTER_ID);
        return mGuildChatDefaultStamp;
    }

    @BeforeEach
    public void initTest() {
        mGuildChatDefaultStamp = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGuildChatDefaultStamp() throws Exception {
        int databaseSizeBeforeCreate = mGuildChatDefaultStampRepository.findAll().size();

        // Create the MGuildChatDefaultStamp
        MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO = mGuildChatDefaultStampMapper.toDto(mGuildChatDefaultStamp);
        restMGuildChatDefaultStampMockMvc.perform(post("/api/m-guild-chat-default-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildChatDefaultStampDTO)))
            .andExpect(status().isCreated());

        // Validate the MGuildChatDefaultStamp in the database
        List<MGuildChatDefaultStamp> mGuildChatDefaultStampList = mGuildChatDefaultStampRepository.findAll();
        assertThat(mGuildChatDefaultStampList).hasSize(databaseSizeBeforeCreate + 1);
        MGuildChatDefaultStamp testMGuildChatDefaultStamp = mGuildChatDefaultStampList.get(mGuildChatDefaultStampList.size() - 1);
        assertThat(testMGuildChatDefaultStamp.getMasterId()).isEqualTo(DEFAULT_MASTER_ID);
    }

    @Test
    @Transactional
    public void createMGuildChatDefaultStampWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGuildChatDefaultStampRepository.findAll().size();

        // Create the MGuildChatDefaultStamp with an existing ID
        mGuildChatDefaultStamp.setId(1L);
        MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO = mGuildChatDefaultStampMapper.toDto(mGuildChatDefaultStamp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGuildChatDefaultStampMockMvc.perform(post("/api/m-guild-chat-default-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildChatDefaultStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildChatDefaultStamp in the database
        List<MGuildChatDefaultStamp> mGuildChatDefaultStampList = mGuildChatDefaultStampRepository.findAll();
        assertThat(mGuildChatDefaultStampList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMasterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGuildChatDefaultStampRepository.findAll().size();
        // set the field null
        mGuildChatDefaultStamp.setMasterId(null);

        // Create the MGuildChatDefaultStamp, which fails.
        MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO = mGuildChatDefaultStampMapper.toDto(mGuildChatDefaultStamp);

        restMGuildChatDefaultStampMockMvc.perform(post("/api/m-guild-chat-default-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildChatDefaultStampDTO)))
            .andExpect(status().isBadRequest());

        List<MGuildChatDefaultStamp> mGuildChatDefaultStampList = mGuildChatDefaultStampRepository.findAll();
        assertThat(mGuildChatDefaultStampList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGuildChatDefaultStamps() throws Exception {
        // Initialize the database
        mGuildChatDefaultStampRepository.saveAndFlush(mGuildChatDefaultStamp);

        // Get all the mGuildChatDefaultStampList
        restMGuildChatDefaultStampMockMvc.perform(get("/api/m-guild-chat-default-stamps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildChatDefaultStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].masterId").value(hasItem(DEFAULT_MASTER_ID)));
    }
    
    @Test
    @Transactional
    public void getMGuildChatDefaultStamp() throws Exception {
        // Initialize the database
        mGuildChatDefaultStampRepository.saveAndFlush(mGuildChatDefaultStamp);

        // Get the mGuildChatDefaultStamp
        restMGuildChatDefaultStampMockMvc.perform(get("/api/m-guild-chat-default-stamps/{id}", mGuildChatDefaultStamp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGuildChatDefaultStamp.getId().intValue()))
            .andExpect(jsonPath("$.masterId").value(DEFAULT_MASTER_ID));
    }

    @Test
    @Transactional
    public void getAllMGuildChatDefaultStampsByMasterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGuildChatDefaultStampRepository.saveAndFlush(mGuildChatDefaultStamp);

        // Get all the mGuildChatDefaultStampList where masterId equals to DEFAULT_MASTER_ID
        defaultMGuildChatDefaultStampShouldBeFound("masterId.equals=" + DEFAULT_MASTER_ID);

        // Get all the mGuildChatDefaultStampList where masterId equals to UPDATED_MASTER_ID
        defaultMGuildChatDefaultStampShouldNotBeFound("masterId.equals=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildChatDefaultStampsByMasterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGuildChatDefaultStampRepository.saveAndFlush(mGuildChatDefaultStamp);

        // Get all the mGuildChatDefaultStampList where masterId in DEFAULT_MASTER_ID or UPDATED_MASTER_ID
        defaultMGuildChatDefaultStampShouldBeFound("masterId.in=" + DEFAULT_MASTER_ID + "," + UPDATED_MASTER_ID);

        // Get all the mGuildChatDefaultStampList where masterId equals to UPDATED_MASTER_ID
        defaultMGuildChatDefaultStampShouldNotBeFound("masterId.in=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildChatDefaultStampsByMasterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGuildChatDefaultStampRepository.saveAndFlush(mGuildChatDefaultStamp);

        // Get all the mGuildChatDefaultStampList where masterId is not null
        defaultMGuildChatDefaultStampShouldBeFound("masterId.specified=true");

        // Get all the mGuildChatDefaultStampList where masterId is null
        defaultMGuildChatDefaultStampShouldNotBeFound("masterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGuildChatDefaultStampsByMasterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGuildChatDefaultStampRepository.saveAndFlush(mGuildChatDefaultStamp);

        // Get all the mGuildChatDefaultStampList where masterId greater than or equals to DEFAULT_MASTER_ID
        defaultMGuildChatDefaultStampShouldBeFound("masterId.greaterOrEqualThan=" + DEFAULT_MASTER_ID);

        // Get all the mGuildChatDefaultStampList where masterId greater than or equals to UPDATED_MASTER_ID
        defaultMGuildChatDefaultStampShouldNotBeFound("masterId.greaterOrEqualThan=" + UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void getAllMGuildChatDefaultStampsByMasterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGuildChatDefaultStampRepository.saveAndFlush(mGuildChatDefaultStamp);

        // Get all the mGuildChatDefaultStampList where masterId less than or equals to DEFAULT_MASTER_ID
        defaultMGuildChatDefaultStampShouldNotBeFound("masterId.lessThan=" + DEFAULT_MASTER_ID);

        // Get all the mGuildChatDefaultStampList where masterId less than or equals to UPDATED_MASTER_ID
        defaultMGuildChatDefaultStampShouldBeFound("masterId.lessThan=" + UPDATED_MASTER_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGuildChatDefaultStampShouldBeFound(String filter) throws Exception {
        restMGuildChatDefaultStampMockMvc.perform(get("/api/m-guild-chat-default-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGuildChatDefaultStamp.getId().intValue())))
            .andExpect(jsonPath("$.[*].masterId").value(hasItem(DEFAULT_MASTER_ID)));

        // Check, that the count call also returns 1
        restMGuildChatDefaultStampMockMvc.perform(get("/api/m-guild-chat-default-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGuildChatDefaultStampShouldNotBeFound(String filter) throws Exception {
        restMGuildChatDefaultStampMockMvc.perform(get("/api/m-guild-chat-default-stamps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGuildChatDefaultStampMockMvc.perform(get("/api/m-guild-chat-default-stamps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGuildChatDefaultStamp() throws Exception {
        // Get the mGuildChatDefaultStamp
        restMGuildChatDefaultStampMockMvc.perform(get("/api/m-guild-chat-default-stamps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGuildChatDefaultStamp() throws Exception {
        // Initialize the database
        mGuildChatDefaultStampRepository.saveAndFlush(mGuildChatDefaultStamp);

        int databaseSizeBeforeUpdate = mGuildChatDefaultStampRepository.findAll().size();

        // Update the mGuildChatDefaultStamp
        MGuildChatDefaultStamp updatedMGuildChatDefaultStamp = mGuildChatDefaultStampRepository.findById(mGuildChatDefaultStamp.getId()).get();
        // Disconnect from session so that the updates on updatedMGuildChatDefaultStamp are not directly saved in db
        em.detach(updatedMGuildChatDefaultStamp);
        updatedMGuildChatDefaultStamp
            .masterId(UPDATED_MASTER_ID);
        MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO = mGuildChatDefaultStampMapper.toDto(updatedMGuildChatDefaultStamp);

        restMGuildChatDefaultStampMockMvc.perform(put("/api/m-guild-chat-default-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildChatDefaultStampDTO)))
            .andExpect(status().isOk());

        // Validate the MGuildChatDefaultStamp in the database
        List<MGuildChatDefaultStamp> mGuildChatDefaultStampList = mGuildChatDefaultStampRepository.findAll();
        assertThat(mGuildChatDefaultStampList).hasSize(databaseSizeBeforeUpdate);
        MGuildChatDefaultStamp testMGuildChatDefaultStamp = mGuildChatDefaultStampList.get(mGuildChatDefaultStampList.size() - 1);
        assertThat(testMGuildChatDefaultStamp.getMasterId()).isEqualTo(UPDATED_MASTER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMGuildChatDefaultStamp() throws Exception {
        int databaseSizeBeforeUpdate = mGuildChatDefaultStampRepository.findAll().size();

        // Create the MGuildChatDefaultStamp
        MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO = mGuildChatDefaultStampMapper.toDto(mGuildChatDefaultStamp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGuildChatDefaultStampMockMvc.perform(put("/api/m-guild-chat-default-stamps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGuildChatDefaultStampDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGuildChatDefaultStamp in the database
        List<MGuildChatDefaultStamp> mGuildChatDefaultStampList = mGuildChatDefaultStampRepository.findAll();
        assertThat(mGuildChatDefaultStampList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGuildChatDefaultStamp() throws Exception {
        // Initialize the database
        mGuildChatDefaultStampRepository.saveAndFlush(mGuildChatDefaultStamp);

        int databaseSizeBeforeDelete = mGuildChatDefaultStampRepository.findAll().size();

        // Delete the mGuildChatDefaultStamp
        restMGuildChatDefaultStampMockMvc.perform(delete("/api/m-guild-chat-default-stamps/{id}", mGuildChatDefaultStamp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGuildChatDefaultStamp> mGuildChatDefaultStampList = mGuildChatDefaultStampRepository.findAll();
        assertThat(mGuildChatDefaultStampList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildChatDefaultStamp.class);
        MGuildChatDefaultStamp mGuildChatDefaultStamp1 = new MGuildChatDefaultStamp();
        mGuildChatDefaultStamp1.setId(1L);
        MGuildChatDefaultStamp mGuildChatDefaultStamp2 = new MGuildChatDefaultStamp();
        mGuildChatDefaultStamp2.setId(mGuildChatDefaultStamp1.getId());
        assertThat(mGuildChatDefaultStamp1).isEqualTo(mGuildChatDefaultStamp2);
        mGuildChatDefaultStamp2.setId(2L);
        assertThat(mGuildChatDefaultStamp1).isNotEqualTo(mGuildChatDefaultStamp2);
        mGuildChatDefaultStamp1.setId(null);
        assertThat(mGuildChatDefaultStamp1).isNotEqualTo(mGuildChatDefaultStamp2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGuildChatDefaultStampDTO.class);
        MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO1 = new MGuildChatDefaultStampDTO();
        mGuildChatDefaultStampDTO1.setId(1L);
        MGuildChatDefaultStampDTO mGuildChatDefaultStampDTO2 = new MGuildChatDefaultStampDTO();
        assertThat(mGuildChatDefaultStampDTO1).isNotEqualTo(mGuildChatDefaultStampDTO2);
        mGuildChatDefaultStampDTO2.setId(mGuildChatDefaultStampDTO1.getId());
        assertThat(mGuildChatDefaultStampDTO1).isEqualTo(mGuildChatDefaultStampDTO2);
        mGuildChatDefaultStampDTO2.setId(2L);
        assertThat(mGuildChatDefaultStampDTO1).isNotEqualTo(mGuildChatDefaultStampDTO2);
        mGuildChatDefaultStampDTO1.setId(null);
        assertThat(mGuildChatDefaultStampDTO1).isNotEqualTo(mGuildChatDefaultStampDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGuildChatDefaultStampMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGuildChatDefaultStampMapper.fromId(null)).isNull();
    }
}
