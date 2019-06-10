package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCutShootOrbit;
import io.shm.tsubasa.repository.MCutShootOrbitRepository;
import io.shm.tsubasa.service.MCutShootOrbitService;
import io.shm.tsubasa.service.dto.MCutShootOrbitDTO;
import io.shm.tsubasa.service.mapper.MCutShootOrbitMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCutShootOrbitCriteria;
import io.shm.tsubasa.service.MCutShootOrbitQueryService;

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
 * Integration tests for the {@Link MCutShootOrbitResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCutShootOrbitResourceIT {

    private static final Integer DEFAULT_SHOOT_ORBIT = 1;
    private static final Integer UPDATED_SHOOT_ORBIT = 2;

    private static final Integer DEFAULT_SHOOT_RESULT = 1;
    private static final Integer UPDATED_SHOOT_RESULT = 2;

    private static final String DEFAULT_OFFENSE_SEQUENCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_OFFENSE_SEQUENCE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_DEFENSE_SEQUENCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_DEFENSE_SEQUENCE_TEXT = "BBBBBBBBBB";

    @Autowired
    private MCutShootOrbitRepository mCutShootOrbitRepository;

    @Autowired
    private MCutShootOrbitMapper mCutShootOrbitMapper;

    @Autowired
    private MCutShootOrbitService mCutShootOrbitService;

    @Autowired
    private MCutShootOrbitQueryService mCutShootOrbitQueryService;

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

    private MockMvc restMCutShootOrbitMockMvc;

    private MCutShootOrbit mCutShootOrbit;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCutShootOrbitResource mCutShootOrbitResource = new MCutShootOrbitResource(mCutShootOrbitService, mCutShootOrbitQueryService);
        this.restMCutShootOrbitMockMvc = MockMvcBuilders.standaloneSetup(mCutShootOrbitResource)
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
    public static MCutShootOrbit createEntity(EntityManager em) {
        MCutShootOrbit mCutShootOrbit = new MCutShootOrbit()
            .shootOrbit(DEFAULT_SHOOT_ORBIT)
            .shootResult(DEFAULT_SHOOT_RESULT)
            .offenseSequenceText(DEFAULT_OFFENSE_SEQUENCE_TEXT)
            .defenseSequenceText(DEFAULT_DEFENSE_SEQUENCE_TEXT);
        return mCutShootOrbit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCutShootOrbit createUpdatedEntity(EntityManager em) {
        MCutShootOrbit mCutShootOrbit = new MCutShootOrbit()
            .shootOrbit(UPDATED_SHOOT_ORBIT)
            .shootResult(UPDATED_SHOOT_RESULT)
            .offenseSequenceText(UPDATED_OFFENSE_SEQUENCE_TEXT)
            .defenseSequenceText(UPDATED_DEFENSE_SEQUENCE_TEXT);
        return mCutShootOrbit;
    }

    @BeforeEach
    public void initTest() {
        mCutShootOrbit = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCutShootOrbit() throws Exception {
        int databaseSizeBeforeCreate = mCutShootOrbitRepository.findAll().size();

        // Create the MCutShootOrbit
        MCutShootOrbitDTO mCutShootOrbitDTO = mCutShootOrbitMapper.toDto(mCutShootOrbit);
        restMCutShootOrbitMockMvc.perform(post("/api/m-cut-shoot-orbits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutShootOrbitDTO)))
            .andExpect(status().isCreated());

        // Validate the MCutShootOrbit in the database
        List<MCutShootOrbit> mCutShootOrbitList = mCutShootOrbitRepository.findAll();
        assertThat(mCutShootOrbitList).hasSize(databaseSizeBeforeCreate + 1);
        MCutShootOrbit testMCutShootOrbit = mCutShootOrbitList.get(mCutShootOrbitList.size() - 1);
        assertThat(testMCutShootOrbit.getShootOrbit()).isEqualTo(DEFAULT_SHOOT_ORBIT);
        assertThat(testMCutShootOrbit.getShootResult()).isEqualTo(DEFAULT_SHOOT_RESULT);
        assertThat(testMCutShootOrbit.getOffenseSequenceText()).isEqualTo(DEFAULT_OFFENSE_SEQUENCE_TEXT);
        assertThat(testMCutShootOrbit.getDefenseSequenceText()).isEqualTo(DEFAULT_DEFENSE_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void createMCutShootOrbitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCutShootOrbitRepository.findAll().size();

        // Create the MCutShootOrbit with an existing ID
        mCutShootOrbit.setId(1L);
        MCutShootOrbitDTO mCutShootOrbitDTO = mCutShootOrbitMapper.toDto(mCutShootOrbit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCutShootOrbitMockMvc.perform(post("/api/m-cut-shoot-orbits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutShootOrbitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCutShootOrbit in the database
        List<MCutShootOrbit> mCutShootOrbitList = mCutShootOrbitRepository.findAll();
        assertThat(mCutShootOrbitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkShootOrbitIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutShootOrbitRepository.findAll().size();
        // set the field null
        mCutShootOrbit.setShootOrbit(null);

        // Create the MCutShootOrbit, which fails.
        MCutShootOrbitDTO mCutShootOrbitDTO = mCutShootOrbitMapper.toDto(mCutShootOrbit);

        restMCutShootOrbitMockMvc.perform(post("/api/m-cut-shoot-orbits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutShootOrbitDTO)))
            .andExpect(status().isBadRequest());

        List<MCutShootOrbit> mCutShootOrbitList = mCutShootOrbitRepository.findAll();
        assertThat(mCutShootOrbitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShootResultIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutShootOrbitRepository.findAll().size();
        // set the field null
        mCutShootOrbit.setShootResult(null);

        // Create the MCutShootOrbit, which fails.
        MCutShootOrbitDTO mCutShootOrbitDTO = mCutShootOrbitMapper.toDto(mCutShootOrbit);

        restMCutShootOrbitMockMvc.perform(post("/api/m-cut-shoot-orbits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutShootOrbitDTO)))
            .andExpect(status().isBadRequest());

        List<MCutShootOrbit> mCutShootOrbitList = mCutShootOrbitRepository.findAll();
        assertThat(mCutShootOrbitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbits() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList
        restMCutShootOrbitMockMvc.perform(get("/api/m-cut-shoot-orbits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCutShootOrbit.getId().intValue())))
            .andExpect(jsonPath("$.[*].shootOrbit").value(hasItem(DEFAULT_SHOOT_ORBIT)))
            .andExpect(jsonPath("$.[*].shootResult").value(hasItem(DEFAULT_SHOOT_RESULT)))
            .andExpect(jsonPath("$.[*].offenseSequenceText").value(hasItem(DEFAULT_OFFENSE_SEQUENCE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].defenseSequenceText").value(hasItem(DEFAULT_DEFENSE_SEQUENCE_TEXT.toString())));
    }
    
    @Test
    @Transactional
    public void getMCutShootOrbit() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get the mCutShootOrbit
        restMCutShootOrbitMockMvc.perform(get("/api/m-cut-shoot-orbits/{id}", mCutShootOrbit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCutShootOrbit.getId().intValue()))
            .andExpect(jsonPath("$.shootOrbit").value(DEFAULT_SHOOT_ORBIT))
            .andExpect(jsonPath("$.shootResult").value(DEFAULT_SHOOT_RESULT))
            .andExpect(jsonPath("$.offenseSequenceText").value(DEFAULT_OFFENSE_SEQUENCE_TEXT.toString()))
            .andExpect(jsonPath("$.defenseSequenceText").value(DEFAULT_DEFENSE_SEQUENCE_TEXT.toString()));
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootOrbitIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootOrbit equals to DEFAULT_SHOOT_ORBIT
        defaultMCutShootOrbitShouldBeFound("shootOrbit.equals=" + DEFAULT_SHOOT_ORBIT);

        // Get all the mCutShootOrbitList where shootOrbit equals to UPDATED_SHOOT_ORBIT
        defaultMCutShootOrbitShouldNotBeFound("shootOrbit.equals=" + UPDATED_SHOOT_ORBIT);
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootOrbitIsInShouldWork() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootOrbit in DEFAULT_SHOOT_ORBIT or UPDATED_SHOOT_ORBIT
        defaultMCutShootOrbitShouldBeFound("shootOrbit.in=" + DEFAULT_SHOOT_ORBIT + "," + UPDATED_SHOOT_ORBIT);

        // Get all the mCutShootOrbitList where shootOrbit equals to UPDATED_SHOOT_ORBIT
        defaultMCutShootOrbitShouldNotBeFound("shootOrbit.in=" + UPDATED_SHOOT_ORBIT);
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootOrbitIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootOrbit is not null
        defaultMCutShootOrbitShouldBeFound("shootOrbit.specified=true");

        // Get all the mCutShootOrbitList where shootOrbit is null
        defaultMCutShootOrbitShouldNotBeFound("shootOrbit.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootOrbitIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootOrbit greater than or equals to DEFAULT_SHOOT_ORBIT
        defaultMCutShootOrbitShouldBeFound("shootOrbit.greaterOrEqualThan=" + DEFAULT_SHOOT_ORBIT);

        // Get all the mCutShootOrbitList where shootOrbit greater than or equals to UPDATED_SHOOT_ORBIT
        defaultMCutShootOrbitShouldNotBeFound("shootOrbit.greaterOrEqualThan=" + UPDATED_SHOOT_ORBIT);
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootOrbitIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootOrbit less than or equals to DEFAULT_SHOOT_ORBIT
        defaultMCutShootOrbitShouldNotBeFound("shootOrbit.lessThan=" + DEFAULT_SHOOT_ORBIT);

        // Get all the mCutShootOrbitList where shootOrbit less than or equals to UPDATED_SHOOT_ORBIT
        defaultMCutShootOrbitShouldBeFound("shootOrbit.lessThan=" + UPDATED_SHOOT_ORBIT);
    }


    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootResultIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootResult equals to DEFAULT_SHOOT_RESULT
        defaultMCutShootOrbitShouldBeFound("shootResult.equals=" + DEFAULT_SHOOT_RESULT);

        // Get all the mCutShootOrbitList where shootResult equals to UPDATED_SHOOT_RESULT
        defaultMCutShootOrbitShouldNotBeFound("shootResult.equals=" + UPDATED_SHOOT_RESULT);
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootResultIsInShouldWork() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootResult in DEFAULT_SHOOT_RESULT or UPDATED_SHOOT_RESULT
        defaultMCutShootOrbitShouldBeFound("shootResult.in=" + DEFAULT_SHOOT_RESULT + "," + UPDATED_SHOOT_RESULT);

        // Get all the mCutShootOrbitList where shootResult equals to UPDATED_SHOOT_RESULT
        defaultMCutShootOrbitShouldNotBeFound("shootResult.in=" + UPDATED_SHOOT_RESULT);
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootResultIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootResult is not null
        defaultMCutShootOrbitShouldBeFound("shootResult.specified=true");

        // Get all the mCutShootOrbitList where shootResult is null
        defaultMCutShootOrbitShouldNotBeFound("shootResult.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootResultIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootResult greater than or equals to DEFAULT_SHOOT_RESULT
        defaultMCutShootOrbitShouldBeFound("shootResult.greaterOrEqualThan=" + DEFAULT_SHOOT_RESULT);

        // Get all the mCutShootOrbitList where shootResult greater than or equals to UPDATED_SHOOT_RESULT
        defaultMCutShootOrbitShouldNotBeFound("shootResult.greaterOrEqualThan=" + UPDATED_SHOOT_RESULT);
    }

    @Test
    @Transactional
    public void getAllMCutShootOrbitsByShootResultIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        // Get all the mCutShootOrbitList where shootResult less than or equals to DEFAULT_SHOOT_RESULT
        defaultMCutShootOrbitShouldNotBeFound("shootResult.lessThan=" + DEFAULT_SHOOT_RESULT);

        // Get all the mCutShootOrbitList where shootResult less than or equals to UPDATED_SHOOT_RESULT
        defaultMCutShootOrbitShouldBeFound("shootResult.lessThan=" + UPDATED_SHOOT_RESULT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCutShootOrbitShouldBeFound(String filter) throws Exception {
        restMCutShootOrbitMockMvc.perform(get("/api/m-cut-shoot-orbits?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCutShootOrbit.getId().intValue())))
            .andExpect(jsonPath("$.[*].shootOrbit").value(hasItem(DEFAULT_SHOOT_ORBIT)))
            .andExpect(jsonPath("$.[*].shootResult").value(hasItem(DEFAULT_SHOOT_RESULT)))
            .andExpect(jsonPath("$.[*].offenseSequenceText").value(hasItem(DEFAULT_OFFENSE_SEQUENCE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].defenseSequenceText").value(hasItem(DEFAULT_DEFENSE_SEQUENCE_TEXT.toString())));

        // Check, that the count call also returns 1
        restMCutShootOrbitMockMvc.perform(get("/api/m-cut-shoot-orbits/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCutShootOrbitShouldNotBeFound(String filter) throws Exception {
        restMCutShootOrbitMockMvc.perform(get("/api/m-cut-shoot-orbits?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCutShootOrbitMockMvc.perform(get("/api/m-cut-shoot-orbits/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCutShootOrbit() throws Exception {
        // Get the mCutShootOrbit
        restMCutShootOrbitMockMvc.perform(get("/api/m-cut-shoot-orbits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCutShootOrbit() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        int databaseSizeBeforeUpdate = mCutShootOrbitRepository.findAll().size();

        // Update the mCutShootOrbit
        MCutShootOrbit updatedMCutShootOrbit = mCutShootOrbitRepository.findById(mCutShootOrbit.getId()).get();
        // Disconnect from session so that the updates on updatedMCutShootOrbit are not directly saved in db
        em.detach(updatedMCutShootOrbit);
        updatedMCutShootOrbit
            .shootOrbit(UPDATED_SHOOT_ORBIT)
            .shootResult(UPDATED_SHOOT_RESULT)
            .offenseSequenceText(UPDATED_OFFENSE_SEQUENCE_TEXT)
            .defenseSequenceText(UPDATED_DEFENSE_SEQUENCE_TEXT);
        MCutShootOrbitDTO mCutShootOrbitDTO = mCutShootOrbitMapper.toDto(updatedMCutShootOrbit);

        restMCutShootOrbitMockMvc.perform(put("/api/m-cut-shoot-orbits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutShootOrbitDTO)))
            .andExpect(status().isOk());

        // Validate the MCutShootOrbit in the database
        List<MCutShootOrbit> mCutShootOrbitList = mCutShootOrbitRepository.findAll();
        assertThat(mCutShootOrbitList).hasSize(databaseSizeBeforeUpdate);
        MCutShootOrbit testMCutShootOrbit = mCutShootOrbitList.get(mCutShootOrbitList.size() - 1);
        assertThat(testMCutShootOrbit.getShootOrbit()).isEqualTo(UPDATED_SHOOT_ORBIT);
        assertThat(testMCutShootOrbit.getShootResult()).isEqualTo(UPDATED_SHOOT_RESULT);
        assertThat(testMCutShootOrbit.getOffenseSequenceText()).isEqualTo(UPDATED_OFFENSE_SEQUENCE_TEXT);
        assertThat(testMCutShootOrbit.getDefenseSequenceText()).isEqualTo(UPDATED_DEFENSE_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void updateNonExistingMCutShootOrbit() throws Exception {
        int databaseSizeBeforeUpdate = mCutShootOrbitRepository.findAll().size();

        // Create the MCutShootOrbit
        MCutShootOrbitDTO mCutShootOrbitDTO = mCutShootOrbitMapper.toDto(mCutShootOrbit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCutShootOrbitMockMvc.perform(put("/api/m-cut-shoot-orbits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutShootOrbitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCutShootOrbit in the database
        List<MCutShootOrbit> mCutShootOrbitList = mCutShootOrbitRepository.findAll();
        assertThat(mCutShootOrbitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCutShootOrbit() throws Exception {
        // Initialize the database
        mCutShootOrbitRepository.saveAndFlush(mCutShootOrbit);

        int databaseSizeBeforeDelete = mCutShootOrbitRepository.findAll().size();

        // Delete the mCutShootOrbit
        restMCutShootOrbitMockMvc.perform(delete("/api/m-cut-shoot-orbits/{id}", mCutShootOrbit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCutShootOrbit> mCutShootOrbitList = mCutShootOrbitRepository.findAll();
        assertThat(mCutShootOrbitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCutShootOrbit.class);
        MCutShootOrbit mCutShootOrbit1 = new MCutShootOrbit();
        mCutShootOrbit1.setId(1L);
        MCutShootOrbit mCutShootOrbit2 = new MCutShootOrbit();
        mCutShootOrbit2.setId(mCutShootOrbit1.getId());
        assertThat(mCutShootOrbit1).isEqualTo(mCutShootOrbit2);
        mCutShootOrbit2.setId(2L);
        assertThat(mCutShootOrbit1).isNotEqualTo(mCutShootOrbit2);
        mCutShootOrbit1.setId(null);
        assertThat(mCutShootOrbit1).isNotEqualTo(mCutShootOrbit2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCutShootOrbitDTO.class);
        MCutShootOrbitDTO mCutShootOrbitDTO1 = new MCutShootOrbitDTO();
        mCutShootOrbitDTO1.setId(1L);
        MCutShootOrbitDTO mCutShootOrbitDTO2 = new MCutShootOrbitDTO();
        assertThat(mCutShootOrbitDTO1).isNotEqualTo(mCutShootOrbitDTO2);
        mCutShootOrbitDTO2.setId(mCutShootOrbitDTO1.getId());
        assertThat(mCutShootOrbitDTO1).isEqualTo(mCutShootOrbitDTO2);
        mCutShootOrbitDTO2.setId(2L);
        assertThat(mCutShootOrbitDTO1).isNotEqualTo(mCutShootOrbitDTO2);
        mCutShootOrbitDTO1.setId(null);
        assertThat(mCutShootOrbitDTO1).isNotEqualTo(mCutShootOrbitDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCutShootOrbitMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCutShootOrbitMapper.fromId(null)).isNull();
    }
}
