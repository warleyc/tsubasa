package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLoginBonusSerif;
import io.shm.tsubasa.repository.MLoginBonusSerifRepository;
import io.shm.tsubasa.service.MLoginBonusSerifService;
import io.shm.tsubasa.service.dto.MLoginBonusSerifDTO;
import io.shm.tsubasa.service.mapper.MLoginBonusSerifMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLoginBonusSerifCriteria;
import io.shm.tsubasa.service.MLoginBonusSerifQueryService;

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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MLoginBonusSerifResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLoginBonusSerifResourceIT {

    private static final Integer DEFAULT_SERIF_ID = 1;
    private static final Integer UPDATED_SERIF_ID = 2;

    private static final String DEFAULT_SERIF_1 = "AAAAAAAAAA";
    private static final String UPDATED_SERIF_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SERIF_2 = "AAAAAAAAAA";
    private static final String UPDATED_SERIF_2 = "BBBBBBBBBB";

    @Autowired
    private MLoginBonusSerifRepository mLoginBonusSerifRepository;

    @Autowired
    private MLoginBonusSerifMapper mLoginBonusSerifMapper;

    @Autowired
    private MLoginBonusSerifService mLoginBonusSerifService;

    @Autowired
    private MLoginBonusSerifQueryService mLoginBonusSerifQueryService;

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

    private MockMvc restMLoginBonusSerifMockMvc;

    private MLoginBonusSerif mLoginBonusSerif;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLoginBonusSerifResource mLoginBonusSerifResource = new MLoginBonusSerifResource(mLoginBonusSerifService, mLoginBonusSerifQueryService);
        this.restMLoginBonusSerifMockMvc = MockMvcBuilders.standaloneSetup(mLoginBonusSerifResource)
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
    public static MLoginBonusSerif createEntity(EntityManager em) {
        MLoginBonusSerif mLoginBonusSerif = new MLoginBonusSerif()
            .serifId(DEFAULT_SERIF_ID)
            .serif1(DEFAULT_SERIF_1)
            .serif2(DEFAULT_SERIF_2);
        return mLoginBonusSerif;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLoginBonusSerif createUpdatedEntity(EntityManager em) {
        MLoginBonusSerif mLoginBonusSerif = new MLoginBonusSerif()
            .serifId(UPDATED_SERIF_ID)
            .serif1(UPDATED_SERIF_1)
            .serif2(UPDATED_SERIF_2);
        return mLoginBonusSerif;
    }

    @BeforeEach
    public void initTest() {
        mLoginBonusSerif = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLoginBonusSerif() throws Exception {
        int databaseSizeBeforeCreate = mLoginBonusSerifRepository.findAll().size();

        // Create the MLoginBonusSerif
        MLoginBonusSerifDTO mLoginBonusSerifDTO = mLoginBonusSerifMapper.toDto(mLoginBonusSerif);
        restMLoginBonusSerifMockMvc.perform(post("/api/m-login-bonus-serifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusSerifDTO)))
            .andExpect(status().isCreated());

        // Validate the MLoginBonusSerif in the database
        List<MLoginBonusSerif> mLoginBonusSerifList = mLoginBonusSerifRepository.findAll();
        assertThat(mLoginBonusSerifList).hasSize(databaseSizeBeforeCreate + 1);
        MLoginBonusSerif testMLoginBonusSerif = mLoginBonusSerifList.get(mLoginBonusSerifList.size() - 1);
        assertThat(testMLoginBonusSerif.getSerifId()).isEqualTo(DEFAULT_SERIF_ID);
        assertThat(testMLoginBonusSerif.getSerif1()).isEqualTo(DEFAULT_SERIF_1);
        assertThat(testMLoginBonusSerif.getSerif2()).isEqualTo(DEFAULT_SERIF_2);
    }

    @Test
    @Transactional
    public void createMLoginBonusSerifWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLoginBonusSerifRepository.findAll().size();

        // Create the MLoginBonusSerif with an existing ID
        mLoginBonusSerif.setId(1L);
        MLoginBonusSerifDTO mLoginBonusSerifDTO = mLoginBonusSerifMapper.toDto(mLoginBonusSerif);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLoginBonusSerifMockMvc.perform(post("/api/m-login-bonus-serifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusSerifDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLoginBonusSerif in the database
        List<MLoginBonusSerif> mLoginBonusSerifList = mLoginBonusSerifRepository.findAll();
        assertThat(mLoginBonusSerifList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSerifIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusSerifRepository.findAll().size();
        // set the field null
        mLoginBonusSerif.setSerifId(null);

        // Create the MLoginBonusSerif, which fails.
        MLoginBonusSerifDTO mLoginBonusSerifDTO = mLoginBonusSerifMapper.toDto(mLoginBonusSerif);

        restMLoginBonusSerifMockMvc.perform(post("/api/m-login-bonus-serifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusSerifDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusSerif> mLoginBonusSerifList = mLoginBonusSerifRepository.findAll();
        assertThat(mLoginBonusSerifList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusSerifs() throws Exception {
        // Initialize the database
        mLoginBonusSerifRepository.saveAndFlush(mLoginBonusSerif);

        // Get all the mLoginBonusSerifList
        restMLoginBonusSerifMockMvc.perform(get("/api/m-login-bonus-serifs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLoginBonusSerif.getId().intValue())))
            .andExpect(jsonPath("$.[*].serifId").value(hasItem(DEFAULT_SERIF_ID)))
            .andExpect(jsonPath("$.[*].serif1").value(hasItem(DEFAULT_SERIF_1.toString())))
            .andExpect(jsonPath("$.[*].serif2").value(hasItem(DEFAULT_SERIF_2.toString())));
    }
    
    @Test
    @Transactional
    public void getMLoginBonusSerif() throws Exception {
        // Initialize the database
        mLoginBonusSerifRepository.saveAndFlush(mLoginBonusSerif);

        // Get the mLoginBonusSerif
        restMLoginBonusSerifMockMvc.perform(get("/api/m-login-bonus-serifs/{id}", mLoginBonusSerif.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLoginBonusSerif.getId().intValue()))
            .andExpect(jsonPath("$.serifId").value(DEFAULT_SERIF_ID))
            .andExpect(jsonPath("$.serif1").value(DEFAULT_SERIF_1.toString()))
            .andExpect(jsonPath("$.serif2").value(DEFAULT_SERIF_2.toString()));
    }

    @Test
    @Transactional
    public void getAllMLoginBonusSerifsBySerifIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusSerifRepository.saveAndFlush(mLoginBonusSerif);

        // Get all the mLoginBonusSerifList where serifId equals to DEFAULT_SERIF_ID
        defaultMLoginBonusSerifShouldBeFound("serifId.equals=" + DEFAULT_SERIF_ID);

        // Get all the mLoginBonusSerifList where serifId equals to UPDATED_SERIF_ID
        defaultMLoginBonusSerifShouldNotBeFound("serifId.equals=" + UPDATED_SERIF_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusSerifsBySerifIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusSerifRepository.saveAndFlush(mLoginBonusSerif);

        // Get all the mLoginBonusSerifList where serifId in DEFAULT_SERIF_ID or UPDATED_SERIF_ID
        defaultMLoginBonusSerifShouldBeFound("serifId.in=" + DEFAULT_SERIF_ID + "," + UPDATED_SERIF_ID);

        // Get all the mLoginBonusSerifList where serifId equals to UPDATED_SERIF_ID
        defaultMLoginBonusSerifShouldNotBeFound("serifId.in=" + UPDATED_SERIF_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusSerifsBySerifIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusSerifRepository.saveAndFlush(mLoginBonusSerif);

        // Get all the mLoginBonusSerifList where serifId is not null
        defaultMLoginBonusSerifShouldBeFound("serifId.specified=true");

        // Get all the mLoginBonusSerifList where serifId is null
        defaultMLoginBonusSerifShouldNotBeFound("serifId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusSerifsBySerifIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusSerifRepository.saveAndFlush(mLoginBonusSerif);

        // Get all the mLoginBonusSerifList where serifId greater than or equals to DEFAULT_SERIF_ID
        defaultMLoginBonusSerifShouldBeFound("serifId.greaterOrEqualThan=" + DEFAULT_SERIF_ID);

        // Get all the mLoginBonusSerifList where serifId greater than or equals to UPDATED_SERIF_ID
        defaultMLoginBonusSerifShouldNotBeFound("serifId.greaterOrEqualThan=" + UPDATED_SERIF_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusSerifsBySerifIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusSerifRepository.saveAndFlush(mLoginBonusSerif);

        // Get all the mLoginBonusSerifList where serifId less than or equals to DEFAULT_SERIF_ID
        defaultMLoginBonusSerifShouldNotBeFound("serifId.lessThan=" + DEFAULT_SERIF_ID);

        // Get all the mLoginBonusSerifList where serifId less than or equals to UPDATED_SERIF_ID
        defaultMLoginBonusSerifShouldBeFound("serifId.lessThan=" + UPDATED_SERIF_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLoginBonusSerifShouldBeFound(String filter) throws Exception {
        restMLoginBonusSerifMockMvc.perform(get("/api/m-login-bonus-serifs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLoginBonusSerif.getId().intValue())))
            .andExpect(jsonPath("$.[*].serifId").value(hasItem(DEFAULT_SERIF_ID)))
            .andExpect(jsonPath("$.[*].serif1").value(hasItem(DEFAULT_SERIF_1.toString())))
            .andExpect(jsonPath("$.[*].serif2").value(hasItem(DEFAULT_SERIF_2.toString())));

        // Check, that the count call also returns 1
        restMLoginBonusSerifMockMvc.perform(get("/api/m-login-bonus-serifs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLoginBonusSerifShouldNotBeFound(String filter) throws Exception {
        restMLoginBonusSerifMockMvc.perform(get("/api/m-login-bonus-serifs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLoginBonusSerifMockMvc.perform(get("/api/m-login-bonus-serifs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLoginBonusSerif() throws Exception {
        // Get the mLoginBonusSerif
        restMLoginBonusSerifMockMvc.perform(get("/api/m-login-bonus-serifs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLoginBonusSerif() throws Exception {
        // Initialize the database
        mLoginBonusSerifRepository.saveAndFlush(mLoginBonusSerif);

        int databaseSizeBeforeUpdate = mLoginBonusSerifRepository.findAll().size();

        // Update the mLoginBonusSerif
        MLoginBonusSerif updatedMLoginBonusSerif = mLoginBonusSerifRepository.findById(mLoginBonusSerif.getId()).get();
        // Disconnect from session so that the updates on updatedMLoginBonusSerif are not directly saved in db
        em.detach(updatedMLoginBonusSerif);
        updatedMLoginBonusSerif
            .serifId(UPDATED_SERIF_ID)
            .serif1(UPDATED_SERIF_1)
            .serif2(UPDATED_SERIF_2);
        MLoginBonusSerifDTO mLoginBonusSerifDTO = mLoginBonusSerifMapper.toDto(updatedMLoginBonusSerif);

        restMLoginBonusSerifMockMvc.perform(put("/api/m-login-bonus-serifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusSerifDTO)))
            .andExpect(status().isOk());

        // Validate the MLoginBonusSerif in the database
        List<MLoginBonusSerif> mLoginBonusSerifList = mLoginBonusSerifRepository.findAll();
        assertThat(mLoginBonusSerifList).hasSize(databaseSizeBeforeUpdate);
        MLoginBonusSerif testMLoginBonusSerif = mLoginBonusSerifList.get(mLoginBonusSerifList.size() - 1);
        assertThat(testMLoginBonusSerif.getSerifId()).isEqualTo(UPDATED_SERIF_ID);
        assertThat(testMLoginBonusSerif.getSerif1()).isEqualTo(UPDATED_SERIF_1);
        assertThat(testMLoginBonusSerif.getSerif2()).isEqualTo(UPDATED_SERIF_2);
    }

    @Test
    @Transactional
    public void updateNonExistingMLoginBonusSerif() throws Exception {
        int databaseSizeBeforeUpdate = mLoginBonusSerifRepository.findAll().size();

        // Create the MLoginBonusSerif
        MLoginBonusSerifDTO mLoginBonusSerifDTO = mLoginBonusSerifMapper.toDto(mLoginBonusSerif);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLoginBonusSerifMockMvc.perform(put("/api/m-login-bonus-serifs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusSerifDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLoginBonusSerif in the database
        List<MLoginBonusSerif> mLoginBonusSerifList = mLoginBonusSerifRepository.findAll();
        assertThat(mLoginBonusSerifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLoginBonusSerif() throws Exception {
        // Initialize the database
        mLoginBonusSerifRepository.saveAndFlush(mLoginBonusSerif);

        int databaseSizeBeforeDelete = mLoginBonusSerifRepository.findAll().size();

        // Delete the mLoginBonusSerif
        restMLoginBonusSerifMockMvc.perform(delete("/api/m-login-bonus-serifs/{id}", mLoginBonusSerif.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLoginBonusSerif> mLoginBonusSerifList = mLoginBonusSerifRepository.findAll();
        assertThat(mLoginBonusSerifList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLoginBonusSerif.class);
        MLoginBonusSerif mLoginBonusSerif1 = new MLoginBonusSerif();
        mLoginBonusSerif1.setId(1L);
        MLoginBonusSerif mLoginBonusSerif2 = new MLoginBonusSerif();
        mLoginBonusSerif2.setId(mLoginBonusSerif1.getId());
        assertThat(mLoginBonusSerif1).isEqualTo(mLoginBonusSerif2);
        mLoginBonusSerif2.setId(2L);
        assertThat(mLoginBonusSerif1).isNotEqualTo(mLoginBonusSerif2);
        mLoginBonusSerif1.setId(null);
        assertThat(mLoginBonusSerif1).isNotEqualTo(mLoginBonusSerif2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLoginBonusSerifDTO.class);
        MLoginBonusSerifDTO mLoginBonusSerifDTO1 = new MLoginBonusSerifDTO();
        mLoginBonusSerifDTO1.setId(1L);
        MLoginBonusSerifDTO mLoginBonusSerifDTO2 = new MLoginBonusSerifDTO();
        assertThat(mLoginBonusSerifDTO1).isNotEqualTo(mLoginBonusSerifDTO2);
        mLoginBonusSerifDTO2.setId(mLoginBonusSerifDTO1.getId());
        assertThat(mLoginBonusSerifDTO1).isEqualTo(mLoginBonusSerifDTO2);
        mLoginBonusSerifDTO2.setId(2L);
        assertThat(mLoginBonusSerifDTO1).isNotEqualTo(mLoginBonusSerifDTO2);
        mLoginBonusSerifDTO1.setId(null);
        assertThat(mLoginBonusSerifDTO1).isNotEqualTo(mLoginBonusSerifDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLoginBonusSerifMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLoginBonusSerifMapper.fromId(null)).isNull();
    }
}
