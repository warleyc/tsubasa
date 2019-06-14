package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MSellCardMedal;
import io.shm.tsubasa.repository.MSellCardMedalRepository;
import io.shm.tsubasa.service.MSellCardMedalService;
import io.shm.tsubasa.service.dto.MSellCardMedalDTO;
import io.shm.tsubasa.service.mapper.MSellCardMedalMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MSellCardMedalCriteria;
import io.shm.tsubasa.service.MSellCardMedalQueryService;

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
 * Integration tests for the {@Link MSellCardMedalResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MSellCardMedalResourceIT {

    private static final Integer DEFAULT_MEDAL_ID = 1;
    private static final Integer UPDATED_MEDAL_ID = 2;

    private static final Integer DEFAULT_AMOUNT = 1;
    private static final Integer UPDATED_AMOUNT = 2;

    @Autowired
    private MSellCardMedalRepository mSellCardMedalRepository;

    @Autowired
    private MSellCardMedalMapper mSellCardMedalMapper;

    @Autowired
    private MSellCardMedalService mSellCardMedalService;

    @Autowired
    private MSellCardMedalQueryService mSellCardMedalQueryService;

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

    private MockMvc restMSellCardMedalMockMvc;

    private MSellCardMedal mSellCardMedal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MSellCardMedalResource mSellCardMedalResource = new MSellCardMedalResource(mSellCardMedalService, mSellCardMedalQueryService);
        this.restMSellCardMedalMockMvc = MockMvcBuilders.standaloneSetup(mSellCardMedalResource)
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
    public static MSellCardMedal createEntity(EntityManager em) {
        MSellCardMedal mSellCardMedal = new MSellCardMedal()
            .medalId(DEFAULT_MEDAL_ID)
            .amount(DEFAULT_AMOUNT);
        return mSellCardMedal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MSellCardMedal createUpdatedEntity(EntityManager em) {
        MSellCardMedal mSellCardMedal = new MSellCardMedal()
            .medalId(UPDATED_MEDAL_ID)
            .amount(UPDATED_AMOUNT);
        return mSellCardMedal;
    }

    @BeforeEach
    public void initTest() {
        mSellCardMedal = createEntity(em);
    }

    @Test
    @Transactional
    public void createMSellCardMedal() throws Exception {
        int databaseSizeBeforeCreate = mSellCardMedalRepository.findAll().size();

        // Create the MSellCardMedal
        MSellCardMedalDTO mSellCardMedalDTO = mSellCardMedalMapper.toDto(mSellCardMedal);
        restMSellCardMedalMockMvc.perform(post("/api/m-sell-card-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardMedalDTO)))
            .andExpect(status().isCreated());

        // Validate the MSellCardMedal in the database
        List<MSellCardMedal> mSellCardMedalList = mSellCardMedalRepository.findAll();
        assertThat(mSellCardMedalList).hasSize(databaseSizeBeforeCreate + 1);
        MSellCardMedal testMSellCardMedal = mSellCardMedalList.get(mSellCardMedalList.size() - 1);
        assertThat(testMSellCardMedal.getMedalId()).isEqualTo(DEFAULT_MEDAL_ID);
        assertThat(testMSellCardMedal.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createMSellCardMedalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mSellCardMedalRepository.findAll().size();

        // Create the MSellCardMedal with an existing ID
        mSellCardMedal.setId(1L);
        MSellCardMedalDTO mSellCardMedalDTO = mSellCardMedalMapper.toDto(mSellCardMedal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMSellCardMedalMockMvc.perform(post("/api/m-sell-card-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardMedalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSellCardMedal in the database
        List<MSellCardMedal> mSellCardMedalList = mSellCardMedalRepository.findAll();
        assertThat(mSellCardMedalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMedalIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSellCardMedalRepository.findAll().size();
        // set the field null
        mSellCardMedal.setMedalId(null);

        // Create the MSellCardMedal, which fails.
        MSellCardMedalDTO mSellCardMedalDTO = mSellCardMedalMapper.toDto(mSellCardMedal);

        restMSellCardMedalMockMvc.perform(post("/api/m-sell-card-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardMedalDTO)))
            .andExpect(status().isBadRequest());

        List<MSellCardMedal> mSellCardMedalList = mSellCardMedalRepository.findAll();
        assertThat(mSellCardMedalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSellCardMedalRepository.findAll().size();
        // set the field null
        mSellCardMedal.setAmount(null);

        // Create the MSellCardMedal, which fails.
        MSellCardMedalDTO mSellCardMedalDTO = mSellCardMedalMapper.toDto(mSellCardMedal);

        restMSellCardMedalMockMvc.perform(post("/api/m-sell-card-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardMedalDTO)))
            .andExpect(status().isBadRequest());

        List<MSellCardMedal> mSellCardMedalList = mSellCardMedalRepository.findAll();
        assertThat(mSellCardMedalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMSellCardMedals() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList
        restMSellCardMedalMockMvc.perform(get("/api/m-sell-card-medals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSellCardMedal.getId().intValue())))
            .andExpect(jsonPath("$.[*].medalId").value(hasItem(DEFAULT_MEDAL_ID)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getMSellCardMedal() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get the mSellCardMedal
        restMSellCardMedalMockMvc.perform(get("/api/m-sell-card-medals/{id}", mSellCardMedal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mSellCardMedal.getId().intValue()))
            .andExpect(jsonPath("$.medalId").value(DEFAULT_MEDAL_ID))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT));
    }

    @Test
    @Transactional
    public void getAllMSellCardMedalsByMedalIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where medalId equals to DEFAULT_MEDAL_ID
        defaultMSellCardMedalShouldBeFound("medalId.equals=" + DEFAULT_MEDAL_ID);

        // Get all the mSellCardMedalList where medalId equals to UPDATED_MEDAL_ID
        defaultMSellCardMedalShouldNotBeFound("medalId.equals=" + UPDATED_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMSellCardMedalsByMedalIdIsInShouldWork() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where medalId in DEFAULT_MEDAL_ID or UPDATED_MEDAL_ID
        defaultMSellCardMedalShouldBeFound("medalId.in=" + DEFAULT_MEDAL_ID + "," + UPDATED_MEDAL_ID);

        // Get all the mSellCardMedalList where medalId equals to UPDATED_MEDAL_ID
        defaultMSellCardMedalShouldNotBeFound("medalId.in=" + UPDATED_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMSellCardMedalsByMedalIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where medalId is not null
        defaultMSellCardMedalShouldBeFound("medalId.specified=true");

        // Get all the mSellCardMedalList where medalId is null
        defaultMSellCardMedalShouldNotBeFound("medalId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSellCardMedalsByMedalIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where medalId greater than or equals to DEFAULT_MEDAL_ID
        defaultMSellCardMedalShouldBeFound("medalId.greaterOrEqualThan=" + DEFAULT_MEDAL_ID);

        // Get all the mSellCardMedalList where medalId greater than or equals to UPDATED_MEDAL_ID
        defaultMSellCardMedalShouldNotBeFound("medalId.greaterOrEqualThan=" + UPDATED_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMSellCardMedalsByMedalIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where medalId less than or equals to DEFAULT_MEDAL_ID
        defaultMSellCardMedalShouldNotBeFound("medalId.lessThan=" + DEFAULT_MEDAL_ID);

        // Get all the mSellCardMedalList where medalId less than or equals to UPDATED_MEDAL_ID
        defaultMSellCardMedalShouldBeFound("medalId.lessThan=" + UPDATED_MEDAL_ID);
    }


    @Test
    @Transactional
    public void getAllMSellCardMedalsByAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where amount equals to DEFAULT_AMOUNT
        defaultMSellCardMedalShouldBeFound("amount.equals=" + DEFAULT_AMOUNT);

        // Get all the mSellCardMedalList where amount equals to UPDATED_AMOUNT
        defaultMSellCardMedalShouldNotBeFound("amount.equals=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMSellCardMedalsByAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where amount in DEFAULT_AMOUNT or UPDATED_AMOUNT
        defaultMSellCardMedalShouldBeFound("amount.in=" + DEFAULT_AMOUNT + "," + UPDATED_AMOUNT);

        // Get all the mSellCardMedalList where amount equals to UPDATED_AMOUNT
        defaultMSellCardMedalShouldNotBeFound("amount.in=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMSellCardMedalsByAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where amount is not null
        defaultMSellCardMedalShouldBeFound("amount.specified=true");

        // Get all the mSellCardMedalList where amount is null
        defaultMSellCardMedalShouldNotBeFound("amount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSellCardMedalsByAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where amount greater than or equals to DEFAULT_AMOUNT
        defaultMSellCardMedalShouldBeFound("amount.greaterOrEqualThan=" + DEFAULT_AMOUNT);

        // Get all the mSellCardMedalList where amount greater than or equals to UPDATED_AMOUNT
        defaultMSellCardMedalShouldNotBeFound("amount.greaterOrEqualThan=" + UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMSellCardMedalsByAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        // Get all the mSellCardMedalList where amount less than or equals to DEFAULT_AMOUNT
        defaultMSellCardMedalShouldNotBeFound("amount.lessThan=" + DEFAULT_AMOUNT);

        // Get all the mSellCardMedalList where amount less than or equals to UPDATED_AMOUNT
        defaultMSellCardMedalShouldBeFound("amount.lessThan=" + UPDATED_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMSellCardMedalShouldBeFound(String filter) throws Exception {
        restMSellCardMedalMockMvc.perform(get("/api/m-sell-card-medals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSellCardMedal.getId().intValue())))
            .andExpect(jsonPath("$.[*].medalId").value(hasItem(DEFAULT_MEDAL_ID)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)));

        // Check, that the count call also returns 1
        restMSellCardMedalMockMvc.perform(get("/api/m-sell-card-medals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMSellCardMedalShouldNotBeFound(String filter) throws Exception {
        restMSellCardMedalMockMvc.perform(get("/api/m-sell-card-medals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMSellCardMedalMockMvc.perform(get("/api/m-sell-card-medals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMSellCardMedal() throws Exception {
        // Get the mSellCardMedal
        restMSellCardMedalMockMvc.perform(get("/api/m-sell-card-medals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMSellCardMedal() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        int databaseSizeBeforeUpdate = mSellCardMedalRepository.findAll().size();

        // Update the mSellCardMedal
        MSellCardMedal updatedMSellCardMedal = mSellCardMedalRepository.findById(mSellCardMedal.getId()).get();
        // Disconnect from session so that the updates on updatedMSellCardMedal are not directly saved in db
        em.detach(updatedMSellCardMedal);
        updatedMSellCardMedal
            .medalId(UPDATED_MEDAL_ID)
            .amount(UPDATED_AMOUNT);
        MSellCardMedalDTO mSellCardMedalDTO = mSellCardMedalMapper.toDto(updatedMSellCardMedal);

        restMSellCardMedalMockMvc.perform(put("/api/m-sell-card-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardMedalDTO)))
            .andExpect(status().isOk());

        // Validate the MSellCardMedal in the database
        List<MSellCardMedal> mSellCardMedalList = mSellCardMedalRepository.findAll();
        assertThat(mSellCardMedalList).hasSize(databaseSizeBeforeUpdate);
        MSellCardMedal testMSellCardMedal = mSellCardMedalList.get(mSellCardMedalList.size() - 1);
        assertThat(testMSellCardMedal.getMedalId()).isEqualTo(UPDATED_MEDAL_ID);
        assertThat(testMSellCardMedal.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingMSellCardMedal() throws Exception {
        int databaseSizeBeforeUpdate = mSellCardMedalRepository.findAll().size();

        // Create the MSellCardMedal
        MSellCardMedalDTO mSellCardMedalDTO = mSellCardMedalMapper.toDto(mSellCardMedal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMSellCardMedalMockMvc.perform(put("/api/m-sell-card-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardMedalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSellCardMedal in the database
        List<MSellCardMedal> mSellCardMedalList = mSellCardMedalRepository.findAll();
        assertThat(mSellCardMedalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMSellCardMedal() throws Exception {
        // Initialize the database
        mSellCardMedalRepository.saveAndFlush(mSellCardMedal);

        int databaseSizeBeforeDelete = mSellCardMedalRepository.findAll().size();

        // Delete the mSellCardMedal
        restMSellCardMedalMockMvc.perform(delete("/api/m-sell-card-medals/{id}", mSellCardMedal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MSellCardMedal> mSellCardMedalList = mSellCardMedalRepository.findAll();
        assertThat(mSellCardMedalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSellCardMedal.class);
        MSellCardMedal mSellCardMedal1 = new MSellCardMedal();
        mSellCardMedal1.setId(1L);
        MSellCardMedal mSellCardMedal2 = new MSellCardMedal();
        mSellCardMedal2.setId(mSellCardMedal1.getId());
        assertThat(mSellCardMedal1).isEqualTo(mSellCardMedal2);
        mSellCardMedal2.setId(2L);
        assertThat(mSellCardMedal1).isNotEqualTo(mSellCardMedal2);
        mSellCardMedal1.setId(null);
        assertThat(mSellCardMedal1).isNotEqualTo(mSellCardMedal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSellCardMedalDTO.class);
        MSellCardMedalDTO mSellCardMedalDTO1 = new MSellCardMedalDTO();
        mSellCardMedalDTO1.setId(1L);
        MSellCardMedalDTO mSellCardMedalDTO2 = new MSellCardMedalDTO();
        assertThat(mSellCardMedalDTO1).isNotEqualTo(mSellCardMedalDTO2);
        mSellCardMedalDTO2.setId(mSellCardMedalDTO1.getId());
        assertThat(mSellCardMedalDTO1).isEqualTo(mSellCardMedalDTO2);
        mSellCardMedalDTO2.setId(2L);
        assertThat(mSellCardMedalDTO1).isNotEqualTo(mSellCardMedalDTO2);
        mSellCardMedalDTO1.setId(null);
        assertThat(mSellCardMedalDTO1).isNotEqualTo(mSellCardMedalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mSellCardMedalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mSellCardMedalMapper.fromId(null)).isNull();
    }
}
