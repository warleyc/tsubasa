package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MUserPolicyUpdateDate;
import io.shm.tsubasa.repository.MUserPolicyUpdateDateRepository;
import io.shm.tsubasa.service.MUserPolicyUpdateDateService;
import io.shm.tsubasa.service.dto.MUserPolicyUpdateDateDTO;
import io.shm.tsubasa.service.mapper.MUserPolicyUpdateDateMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MUserPolicyUpdateDateCriteria;
import io.shm.tsubasa.service.MUserPolicyUpdateDateQueryService;

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
 * Integration tests for the {@Link MUserPolicyUpdateDateResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MUserPolicyUpdateDateResourceIT {

    private static final Integer DEFAULT_UPDATE_DATE = 1;
    private static final Integer UPDATED_UPDATE_DATE = 2;

    @Autowired
    private MUserPolicyUpdateDateRepository mUserPolicyUpdateDateRepository;

    @Autowired
    private MUserPolicyUpdateDateMapper mUserPolicyUpdateDateMapper;

    @Autowired
    private MUserPolicyUpdateDateService mUserPolicyUpdateDateService;

    @Autowired
    private MUserPolicyUpdateDateQueryService mUserPolicyUpdateDateQueryService;

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

    private MockMvc restMUserPolicyUpdateDateMockMvc;

    private MUserPolicyUpdateDate mUserPolicyUpdateDate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MUserPolicyUpdateDateResource mUserPolicyUpdateDateResource = new MUserPolicyUpdateDateResource(mUserPolicyUpdateDateService, mUserPolicyUpdateDateQueryService);
        this.restMUserPolicyUpdateDateMockMvc = MockMvcBuilders.standaloneSetup(mUserPolicyUpdateDateResource)
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
    public static MUserPolicyUpdateDate createEntity(EntityManager em) {
        MUserPolicyUpdateDate mUserPolicyUpdateDate = new MUserPolicyUpdateDate()
            .updateDate(DEFAULT_UPDATE_DATE);
        return mUserPolicyUpdateDate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MUserPolicyUpdateDate createUpdatedEntity(EntityManager em) {
        MUserPolicyUpdateDate mUserPolicyUpdateDate = new MUserPolicyUpdateDate()
            .updateDate(UPDATED_UPDATE_DATE);
        return mUserPolicyUpdateDate;
    }

    @BeforeEach
    public void initTest() {
        mUserPolicyUpdateDate = createEntity(em);
    }

    @Test
    @Transactional
    public void createMUserPolicyUpdateDate() throws Exception {
        int databaseSizeBeforeCreate = mUserPolicyUpdateDateRepository.findAll().size();

        // Create the MUserPolicyUpdateDate
        MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO = mUserPolicyUpdateDateMapper.toDto(mUserPolicyUpdateDate);
        restMUserPolicyUpdateDateMockMvc.perform(post("/api/m-user-policy-update-dates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserPolicyUpdateDateDTO)))
            .andExpect(status().isCreated());

        // Validate the MUserPolicyUpdateDate in the database
        List<MUserPolicyUpdateDate> mUserPolicyUpdateDateList = mUserPolicyUpdateDateRepository.findAll();
        assertThat(mUserPolicyUpdateDateList).hasSize(databaseSizeBeforeCreate + 1);
        MUserPolicyUpdateDate testMUserPolicyUpdateDate = mUserPolicyUpdateDateList.get(mUserPolicyUpdateDateList.size() - 1);
        assertThat(testMUserPolicyUpdateDate.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void createMUserPolicyUpdateDateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mUserPolicyUpdateDateRepository.findAll().size();

        // Create the MUserPolicyUpdateDate with an existing ID
        mUserPolicyUpdateDate.setId(1L);
        MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO = mUserPolicyUpdateDateMapper.toDto(mUserPolicyUpdateDate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMUserPolicyUpdateDateMockMvc.perform(post("/api/m-user-policy-update-dates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserPolicyUpdateDateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUserPolicyUpdateDate in the database
        List<MUserPolicyUpdateDate> mUserPolicyUpdateDateList = mUserPolicyUpdateDateRepository.findAll();
        assertThat(mUserPolicyUpdateDateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mUserPolicyUpdateDateRepository.findAll().size();
        // set the field null
        mUserPolicyUpdateDate.setUpdateDate(null);

        // Create the MUserPolicyUpdateDate, which fails.
        MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO = mUserPolicyUpdateDateMapper.toDto(mUserPolicyUpdateDate);

        restMUserPolicyUpdateDateMockMvc.perform(post("/api/m-user-policy-update-dates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserPolicyUpdateDateDTO)))
            .andExpect(status().isBadRequest());

        List<MUserPolicyUpdateDate> mUserPolicyUpdateDateList = mUserPolicyUpdateDateRepository.findAll();
        assertThat(mUserPolicyUpdateDateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMUserPolicyUpdateDates() throws Exception {
        // Initialize the database
        mUserPolicyUpdateDateRepository.saveAndFlush(mUserPolicyUpdateDate);

        // Get all the mUserPolicyUpdateDateList
        restMUserPolicyUpdateDateMockMvc.perform(get("/api/m-user-policy-update-dates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUserPolicyUpdateDate.getId().intValue())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE)));
    }
    
    @Test
    @Transactional
    public void getMUserPolicyUpdateDate() throws Exception {
        // Initialize the database
        mUserPolicyUpdateDateRepository.saveAndFlush(mUserPolicyUpdateDate);

        // Get the mUserPolicyUpdateDate
        restMUserPolicyUpdateDateMockMvc.perform(get("/api/m-user-policy-update-dates/{id}", mUserPolicyUpdateDate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mUserPolicyUpdateDate.getId().intValue()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE));
    }

    @Test
    @Transactional
    public void getAllMUserPolicyUpdateDatesByUpdateDateIsEqualToSomething() throws Exception {
        // Initialize the database
        mUserPolicyUpdateDateRepository.saveAndFlush(mUserPolicyUpdateDate);

        // Get all the mUserPolicyUpdateDateList where updateDate equals to DEFAULT_UPDATE_DATE
        defaultMUserPolicyUpdateDateShouldBeFound("updateDate.equals=" + DEFAULT_UPDATE_DATE);

        // Get all the mUserPolicyUpdateDateList where updateDate equals to UPDATED_UPDATE_DATE
        defaultMUserPolicyUpdateDateShouldNotBeFound("updateDate.equals=" + UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void getAllMUserPolicyUpdateDatesByUpdateDateIsInShouldWork() throws Exception {
        // Initialize the database
        mUserPolicyUpdateDateRepository.saveAndFlush(mUserPolicyUpdateDate);

        // Get all the mUserPolicyUpdateDateList where updateDate in DEFAULT_UPDATE_DATE or UPDATED_UPDATE_DATE
        defaultMUserPolicyUpdateDateShouldBeFound("updateDate.in=" + DEFAULT_UPDATE_DATE + "," + UPDATED_UPDATE_DATE);

        // Get all the mUserPolicyUpdateDateList where updateDate equals to UPDATED_UPDATE_DATE
        defaultMUserPolicyUpdateDateShouldNotBeFound("updateDate.in=" + UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void getAllMUserPolicyUpdateDatesByUpdateDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mUserPolicyUpdateDateRepository.saveAndFlush(mUserPolicyUpdateDate);

        // Get all the mUserPolicyUpdateDateList where updateDate is not null
        defaultMUserPolicyUpdateDateShouldBeFound("updateDate.specified=true");

        // Get all the mUserPolicyUpdateDateList where updateDate is null
        defaultMUserPolicyUpdateDateShouldNotBeFound("updateDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMUserPolicyUpdateDatesByUpdateDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mUserPolicyUpdateDateRepository.saveAndFlush(mUserPolicyUpdateDate);

        // Get all the mUserPolicyUpdateDateList where updateDate greater than or equals to DEFAULT_UPDATE_DATE
        defaultMUserPolicyUpdateDateShouldBeFound("updateDate.greaterOrEqualThan=" + DEFAULT_UPDATE_DATE);

        // Get all the mUserPolicyUpdateDateList where updateDate greater than or equals to UPDATED_UPDATE_DATE
        defaultMUserPolicyUpdateDateShouldNotBeFound("updateDate.greaterOrEqualThan=" + UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void getAllMUserPolicyUpdateDatesByUpdateDateIsLessThanSomething() throws Exception {
        // Initialize the database
        mUserPolicyUpdateDateRepository.saveAndFlush(mUserPolicyUpdateDate);

        // Get all the mUserPolicyUpdateDateList where updateDate less than or equals to DEFAULT_UPDATE_DATE
        defaultMUserPolicyUpdateDateShouldNotBeFound("updateDate.lessThan=" + DEFAULT_UPDATE_DATE);

        // Get all the mUserPolicyUpdateDateList where updateDate less than or equals to UPDATED_UPDATE_DATE
        defaultMUserPolicyUpdateDateShouldBeFound("updateDate.lessThan=" + UPDATED_UPDATE_DATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMUserPolicyUpdateDateShouldBeFound(String filter) throws Exception {
        restMUserPolicyUpdateDateMockMvc.perform(get("/api/m-user-policy-update-dates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mUserPolicyUpdateDate.getId().intValue())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE)));

        // Check, that the count call also returns 1
        restMUserPolicyUpdateDateMockMvc.perform(get("/api/m-user-policy-update-dates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMUserPolicyUpdateDateShouldNotBeFound(String filter) throws Exception {
        restMUserPolicyUpdateDateMockMvc.perform(get("/api/m-user-policy-update-dates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMUserPolicyUpdateDateMockMvc.perform(get("/api/m-user-policy-update-dates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMUserPolicyUpdateDate() throws Exception {
        // Get the mUserPolicyUpdateDate
        restMUserPolicyUpdateDateMockMvc.perform(get("/api/m-user-policy-update-dates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMUserPolicyUpdateDate() throws Exception {
        // Initialize the database
        mUserPolicyUpdateDateRepository.saveAndFlush(mUserPolicyUpdateDate);

        int databaseSizeBeforeUpdate = mUserPolicyUpdateDateRepository.findAll().size();

        // Update the mUserPolicyUpdateDate
        MUserPolicyUpdateDate updatedMUserPolicyUpdateDate = mUserPolicyUpdateDateRepository.findById(mUserPolicyUpdateDate.getId()).get();
        // Disconnect from session so that the updates on updatedMUserPolicyUpdateDate are not directly saved in db
        em.detach(updatedMUserPolicyUpdateDate);
        updatedMUserPolicyUpdateDate
            .updateDate(UPDATED_UPDATE_DATE);
        MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO = mUserPolicyUpdateDateMapper.toDto(updatedMUserPolicyUpdateDate);

        restMUserPolicyUpdateDateMockMvc.perform(put("/api/m-user-policy-update-dates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserPolicyUpdateDateDTO)))
            .andExpect(status().isOk());

        // Validate the MUserPolicyUpdateDate in the database
        List<MUserPolicyUpdateDate> mUserPolicyUpdateDateList = mUserPolicyUpdateDateRepository.findAll();
        assertThat(mUserPolicyUpdateDateList).hasSize(databaseSizeBeforeUpdate);
        MUserPolicyUpdateDate testMUserPolicyUpdateDate = mUserPolicyUpdateDateList.get(mUserPolicyUpdateDateList.size() - 1);
        assertThat(testMUserPolicyUpdateDate.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMUserPolicyUpdateDate() throws Exception {
        int databaseSizeBeforeUpdate = mUserPolicyUpdateDateRepository.findAll().size();

        // Create the MUserPolicyUpdateDate
        MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO = mUserPolicyUpdateDateMapper.toDto(mUserPolicyUpdateDate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMUserPolicyUpdateDateMockMvc.perform(put("/api/m-user-policy-update-dates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mUserPolicyUpdateDateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MUserPolicyUpdateDate in the database
        List<MUserPolicyUpdateDate> mUserPolicyUpdateDateList = mUserPolicyUpdateDateRepository.findAll();
        assertThat(mUserPolicyUpdateDateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMUserPolicyUpdateDate() throws Exception {
        // Initialize the database
        mUserPolicyUpdateDateRepository.saveAndFlush(mUserPolicyUpdateDate);

        int databaseSizeBeforeDelete = mUserPolicyUpdateDateRepository.findAll().size();

        // Delete the mUserPolicyUpdateDate
        restMUserPolicyUpdateDateMockMvc.perform(delete("/api/m-user-policy-update-dates/{id}", mUserPolicyUpdateDate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MUserPolicyUpdateDate> mUserPolicyUpdateDateList = mUserPolicyUpdateDateRepository.findAll();
        assertThat(mUserPolicyUpdateDateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUserPolicyUpdateDate.class);
        MUserPolicyUpdateDate mUserPolicyUpdateDate1 = new MUserPolicyUpdateDate();
        mUserPolicyUpdateDate1.setId(1L);
        MUserPolicyUpdateDate mUserPolicyUpdateDate2 = new MUserPolicyUpdateDate();
        mUserPolicyUpdateDate2.setId(mUserPolicyUpdateDate1.getId());
        assertThat(mUserPolicyUpdateDate1).isEqualTo(mUserPolicyUpdateDate2);
        mUserPolicyUpdateDate2.setId(2L);
        assertThat(mUserPolicyUpdateDate1).isNotEqualTo(mUserPolicyUpdateDate2);
        mUserPolicyUpdateDate1.setId(null);
        assertThat(mUserPolicyUpdateDate1).isNotEqualTo(mUserPolicyUpdateDate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MUserPolicyUpdateDateDTO.class);
        MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO1 = new MUserPolicyUpdateDateDTO();
        mUserPolicyUpdateDateDTO1.setId(1L);
        MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO2 = new MUserPolicyUpdateDateDTO();
        assertThat(mUserPolicyUpdateDateDTO1).isNotEqualTo(mUserPolicyUpdateDateDTO2);
        mUserPolicyUpdateDateDTO2.setId(mUserPolicyUpdateDateDTO1.getId());
        assertThat(mUserPolicyUpdateDateDTO1).isEqualTo(mUserPolicyUpdateDateDTO2);
        mUserPolicyUpdateDateDTO2.setId(2L);
        assertThat(mUserPolicyUpdateDateDTO1).isNotEqualTo(mUserPolicyUpdateDateDTO2);
        mUserPolicyUpdateDateDTO1.setId(null);
        assertThat(mUserPolicyUpdateDateDTO1).isNotEqualTo(mUserPolicyUpdateDateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mUserPolicyUpdateDateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mUserPolicyUpdateDateMapper.fromId(null)).isNull();
    }
}
