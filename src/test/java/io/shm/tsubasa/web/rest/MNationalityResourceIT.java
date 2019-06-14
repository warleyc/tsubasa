package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MNationality;
import io.shm.tsubasa.domain.MTargetNationalityGroup;
import io.shm.tsubasa.repository.MNationalityRepository;
import io.shm.tsubasa.service.MNationalityService;
import io.shm.tsubasa.service.dto.MNationalityDTO;
import io.shm.tsubasa.service.mapper.MNationalityMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MNationalityCriteria;
import io.shm.tsubasa.service.MNationalityQueryService;

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
 * Integration tests for the {@Link MNationalityResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MNationalityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    @Autowired
    private MNationalityRepository mNationalityRepository;

    @Autowired
    private MNationalityMapper mNationalityMapper;

    @Autowired
    private MNationalityService mNationalityService;

    @Autowired
    private MNationalityQueryService mNationalityQueryService;

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

    private MockMvc restMNationalityMockMvc;

    private MNationality mNationality;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MNationalityResource mNationalityResource = new MNationalityResource(mNationalityService, mNationalityQueryService);
        this.restMNationalityMockMvc = MockMvcBuilders.standaloneSetup(mNationalityResource)
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
    public static MNationality createEntity(EntityManager em) {
        MNationality mNationality = new MNationality()
            .name(DEFAULT_NAME)
            .icon(DEFAULT_ICON);
        return mNationality;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MNationality createUpdatedEntity(EntityManager em) {
        MNationality mNationality = new MNationality()
            .name(UPDATED_NAME)
            .icon(UPDATED_ICON);
        return mNationality;
    }

    @BeforeEach
    public void initTest() {
        mNationality = createEntity(em);
    }

    @Test
    @Transactional
    public void createMNationality() throws Exception {
        int databaseSizeBeforeCreate = mNationalityRepository.findAll().size();

        // Create the MNationality
        MNationalityDTO mNationalityDTO = mNationalityMapper.toDto(mNationality);
        restMNationalityMockMvc.perform(post("/api/m-nationalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNationalityDTO)))
            .andExpect(status().isCreated());

        // Validate the MNationality in the database
        List<MNationality> mNationalityList = mNationalityRepository.findAll();
        assertThat(mNationalityList).hasSize(databaseSizeBeforeCreate + 1);
        MNationality testMNationality = mNationalityList.get(mNationalityList.size() - 1);
        assertThat(testMNationality.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMNationality.getIcon()).isEqualTo(DEFAULT_ICON);
    }

    @Test
    @Transactional
    public void createMNationalityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mNationalityRepository.findAll().size();

        // Create the MNationality with an existing ID
        mNationality.setId(1L);
        MNationalityDTO mNationalityDTO = mNationalityMapper.toDto(mNationality);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMNationalityMockMvc.perform(post("/api/m-nationalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNationalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNationality in the database
        List<MNationality> mNationalityList = mNationalityRepository.findAll();
        assertThat(mNationalityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMNationalities() throws Exception {
        // Initialize the database
        mNationalityRepository.saveAndFlush(mNationality);

        // Get all the mNationalityList
        restMNationalityMockMvc.perform(get("/api/m-nationalities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNationality.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())));
    }
    
    @Test
    @Transactional
    public void getMNationality() throws Exception {
        // Initialize the database
        mNationalityRepository.saveAndFlush(mNationality);

        // Get the mNationality
        restMNationalityMockMvc.perform(get("/api/m-nationalities/{id}", mNationality.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mNationality.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON.toString()));
    }

    @Test
    @Transactional
    public void getAllMNationalitiesByMTargetNationalityGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        MTargetNationalityGroup mTargetNationalityGroup = MTargetNationalityGroupResourceIT.createEntity(em);
        em.persist(mTargetNationalityGroup);
        em.flush();
        mNationality.addMTargetNationalityGroup(mTargetNationalityGroup);
        mNationalityRepository.saveAndFlush(mNationality);
        Long mTargetNationalityGroupId = mTargetNationalityGroup.getId();

        // Get all the mNationalityList where mTargetNationalityGroup equals to mTargetNationalityGroupId
        defaultMNationalityShouldBeFound("mTargetNationalityGroupId.equals=" + mTargetNationalityGroupId);

        // Get all the mNationalityList where mTargetNationalityGroup equals to mTargetNationalityGroupId + 1
        defaultMNationalityShouldNotBeFound("mTargetNationalityGroupId.equals=" + (mTargetNationalityGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMNationalityShouldBeFound(String filter) throws Exception {
        restMNationalityMockMvc.perform(get("/api/m-nationalities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNationality.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())));

        // Check, that the count call also returns 1
        restMNationalityMockMvc.perform(get("/api/m-nationalities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMNationalityShouldNotBeFound(String filter) throws Exception {
        restMNationalityMockMvc.perform(get("/api/m-nationalities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMNationalityMockMvc.perform(get("/api/m-nationalities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMNationality() throws Exception {
        // Get the mNationality
        restMNationalityMockMvc.perform(get("/api/m-nationalities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMNationality() throws Exception {
        // Initialize the database
        mNationalityRepository.saveAndFlush(mNationality);

        int databaseSizeBeforeUpdate = mNationalityRepository.findAll().size();

        // Update the mNationality
        MNationality updatedMNationality = mNationalityRepository.findById(mNationality.getId()).get();
        // Disconnect from session so that the updates on updatedMNationality are not directly saved in db
        em.detach(updatedMNationality);
        updatedMNationality
            .name(UPDATED_NAME)
            .icon(UPDATED_ICON);
        MNationalityDTO mNationalityDTO = mNationalityMapper.toDto(updatedMNationality);

        restMNationalityMockMvc.perform(put("/api/m-nationalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNationalityDTO)))
            .andExpect(status().isOk());

        // Validate the MNationality in the database
        List<MNationality> mNationalityList = mNationalityRepository.findAll();
        assertThat(mNationalityList).hasSize(databaseSizeBeforeUpdate);
        MNationality testMNationality = mNationalityList.get(mNationalityList.size() - 1);
        assertThat(testMNationality.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMNationality.getIcon()).isEqualTo(UPDATED_ICON);
    }

    @Test
    @Transactional
    public void updateNonExistingMNationality() throws Exception {
        int databaseSizeBeforeUpdate = mNationalityRepository.findAll().size();

        // Create the MNationality
        MNationalityDTO mNationalityDTO = mNationalityMapper.toDto(mNationality);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMNationalityMockMvc.perform(put("/api/m-nationalities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNationalityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNationality in the database
        List<MNationality> mNationalityList = mNationalityRepository.findAll();
        assertThat(mNationalityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMNationality() throws Exception {
        // Initialize the database
        mNationalityRepository.saveAndFlush(mNationality);

        int databaseSizeBeforeDelete = mNationalityRepository.findAll().size();

        // Delete the mNationality
        restMNationalityMockMvc.perform(delete("/api/m-nationalities/{id}", mNationality.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MNationality> mNationalityList = mNationalityRepository.findAll();
        assertThat(mNationalityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNationality.class);
        MNationality mNationality1 = new MNationality();
        mNationality1.setId(1L);
        MNationality mNationality2 = new MNationality();
        mNationality2.setId(mNationality1.getId());
        assertThat(mNationality1).isEqualTo(mNationality2);
        mNationality2.setId(2L);
        assertThat(mNationality1).isNotEqualTo(mNationality2);
        mNationality1.setId(null);
        assertThat(mNationality1).isNotEqualTo(mNationality2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNationalityDTO.class);
        MNationalityDTO mNationalityDTO1 = new MNationalityDTO();
        mNationalityDTO1.setId(1L);
        MNationalityDTO mNationalityDTO2 = new MNationalityDTO();
        assertThat(mNationalityDTO1).isNotEqualTo(mNationalityDTO2);
        mNationalityDTO2.setId(mNationalityDTO1.getId());
        assertThat(mNationalityDTO1).isEqualTo(mNationalityDTO2);
        mNationalityDTO2.setId(2L);
        assertThat(mNationalityDTO1).isNotEqualTo(mNationalityDTO2);
        mNationalityDTO1.setId(null);
        assertThat(mNationalityDTO1).isNotEqualTo(mNationalityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mNationalityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mNationalityMapper.fromId(null)).isNull();
    }
}
