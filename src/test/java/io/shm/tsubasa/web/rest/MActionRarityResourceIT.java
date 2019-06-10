package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MActionRarity;
import io.shm.tsubasa.repository.MActionRarityRepository;
import io.shm.tsubasa.service.MActionRarityService;
import io.shm.tsubasa.service.dto.MActionRarityDTO;
import io.shm.tsubasa.service.mapper.MActionRarityMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MActionRarityCriteria;
import io.shm.tsubasa.service.MActionRarityQueryService;

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
 * Integration tests for the {@Link MActionRarityResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MActionRarityResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MActionRarityRepository mActionRarityRepository;

    @Autowired
    private MActionRarityMapper mActionRarityMapper;

    @Autowired
    private MActionRarityService mActionRarityService;

    @Autowired
    private MActionRarityQueryService mActionRarityQueryService;

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

    private MockMvc restMActionRarityMockMvc;

    private MActionRarity mActionRarity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MActionRarityResource mActionRarityResource = new MActionRarityResource(mActionRarityService, mActionRarityQueryService);
        this.restMActionRarityMockMvc = MockMvcBuilders.standaloneSetup(mActionRarityResource)
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
    public static MActionRarity createEntity(EntityManager em) {
        MActionRarity mActionRarity = new MActionRarity()
            .rarity(DEFAULT_RARITY)
            .name(DEFAULT_NAME);
        return mActionRarity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MActionRarity createUpdatedEntity(EntityManager em) {
        MActionRarity mActionRarity = new MActionRarity()
            .rarity(UPDATED_RARITY)
            .name(UPDATED_NAME);
        return mActionRarity;
    }

    @BeforeEach
    public void initTest() {
        mActionRarity = createEntity(em);
    }

    @Test
    @Transactional
    public void createMActionRarity() throws Exception {
        int databaseSizeBeforeCreate = mActionRarityRepository.findAll().size();

        // Create the MActionRarity
        MActionRarityDTO mActionRarityDTO = mActionRarityMapper.toDto(mActionRarity);
        restMActionRarityMockMvc.perform(post("/api/m-action-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionRarityDTO)))
            .andExpect(status().isCreated());

        // Validate the MActionRarity in the database
        List<MActionRarity> mActionRarityList = mActionRarityRepository.findAll();
        assertThat(mActionRarityList).hasSize(databaseSizeBeforeCreate + 1);
        MActionRarity testMActionRarity = mActionRarityList.get(mActionRarityList.size() - 1);
        assertThat(testMActionRarity.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMActionRarity.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMActionRarityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mActionRarityRepository.findAll().size();

        // Create the MActionRarity with an existing ID
        mActionRarity.setId(1L);
        MActionRarityDTO mActionRarityDTO = mActionRarityMapper.toDto(mActionRarity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMActionRarityMockMvc.perform(post("/api/m-action-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionRarityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionRarity in the database
        List<MActionRarity> mActionRarityList = mActionRarityRepository.findAll();
        assertThat(mActionRarityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mActionRarityRepository.findAll().size();
        // set the field null
        mActionRarity.setRarity(null);

        // Create the MActionRarity, which fails.
        MActionRarityDTO mActionRarityDTO = mActionRarityMapper.toDto(mActionRarity);

        restMActionRarityMockMvc.perform(post("/api/m-action-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionRarityDTO)))
            .andExpect(status().isBadRequest());

        List<MActionRarity> mActionRarityList = mActionRarityRepository.findAll();
        assertThat(mActionRarityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMActionRarities() throws Exception {
        // Initialize the database
        mActionRarityRepository.saveAndFlush(mActionRarity);

        // Get all the mActionRarityList
        restMActionRarityMockMvc.perform(get("/api/m-action-rarities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionRarity.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMActionRarity() throws Exception {
        // Initialize the database
        mActionRarityRepository.saveAndFlush(mActionRarity);

        // Get the mActionRarity
        restMActionRarityMockMvc.perform(get("/api/m-action-rarities/{id}", mActionRarity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mActionRarity.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMActionRaritiesByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mActionRarityRepository.saveAndFlush(mActionRarity);

        // Get all the mActionRarityList where rarity equals to DEFAULT_RARITY
        defaultMActionRarityShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mActionRarityList where rarity equals to UPDATED_RARITY
        defaultMActionRarityShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMActionRaritiesByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mActionRarityRepository.saveAndFlush(mActionRarity);

        // Get all the mActionRarityList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMActionRarityShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mActionRarityList where rarity equals to UPDATED_RARITY
        defaultMActionRarityShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMActionRaritiesByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mActionRarityRepository.saveAndFlush(mActionRarity);

        // Get all the mActionRarityList where rarity is not null
        defaultMActionRarityShouldBeFound("rarity.specified=true");

        // Get all the mActionRarityList where rarity is null
        defaultMActionRarityShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMActionRaritiesByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mActionRarityRepository.saveAndFlush(mActionRarity);

        // Get all the mActionRarityList where rarity greater than or equals to DEFAULT_RARITY
        defaultMActionRarityShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mActionRarityList where rarity greater than or equals to UPDATED_RARITY
        defaultMActionRarityShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMActionRaritiesByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mActionRarityRepository.saveAndFlush(mActionRarity);

        // Get all the mActionRarityList where rarity less than or equals to DEFAULT_RARITY
        defaultMActionRarityShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mActionRarityList where rarity less than or equals to UPDATED_RARITY
        defaultMActionRarityShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMActionRarityShouldBeFound(String filter) throws Exception {
        restMActionRarityMockMvc.perform(get("/api/m-action-rarities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mActionRarity.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));

        // Check, that the count call also returns 1
        restMActionRarityMockMvc.perform(get("/api/m-action-rarities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMActionRarityShouldNotBeFound(String filter) throws Exception {
        restMActionRarityMockMvc.perform(get("/api/m-action-rarities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMActionRarityMockMvc.perform(get("/api/m-action-rarities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMActionRarity() throws Exception {
        // Get the mActionRarity
        restMActionRarityMockMvc.perform(get("/api/m-action-rarities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMActionRarity() throws Exception {
        // Initialize the database
        mActionRarityRepository.saveAndFlush(mActionRarity);

        int databaseSizeBeforeUpdate = mActionRarityRepository.findAll().size();

        // Update the mActionRarity
        MActionRarity updatedMActionRarity = mActionRarityRepository.findById(mActionRarity.getId()).get();
        // Disconnect from session so that the updates on updatedMActionRarity are not directly saved in db
        em.detach(updatedMActionRarity);
        updatedMActionRarity
            .rarity(UPDATED_RARITY)
            .name(UPDATED_NAME);
        MActionRarityDTO mActionRarityDTO = mActionRarityMapper.toDto(updatedMActionRarity);

        restMActionRarityMockMvc.perform(put("/api/m-action-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionRarityDTO)))
            .andExpect(status().isOk());

        // Validate the MActionRarity in the database
        List<MActionRarity> mActionRarityList = mActionRarityRepository.findAll();
        assertThat(mActionRarityList).hasSize(databaseSizeBeforeUpdate);
        MActionRarity testMActionRarity = mActionRarityList.get(mActionRarityList.size() - 1);
        assertThat(testMActionRarity.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMActionRarity.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMActionRarity() throws Exception {
        int databaseSizeBeforeUpdate = mActionRarityRepository.findAll().size();

        // Create the MActionRarity
        MActionRarityDTO mActionRarityDTO = mActionRarityMapper.toDto(mActionRarity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMActionRarityMockMvc.perform(put("/api/m-action-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mActionRarityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MActionRarity in the database
        List<MActionRarity> mActionRarityList = mActionRarityRepository.findAll();
        assertThat(mActionRarityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMActionRarity() throws Exception {
        // Initialize the database
        mActionRarityRepository.saveAndFlush(mActionRarity);

        int databaseSizeBeforeDelete = mActionRarityRepository.findAll().size();

        // Delete the mActionRarity
        restMActionRarityMockMvc.perform(delete("/api/m-action-rarities/{id}", mActionRarity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MActionRarity> mActionRarityList = mActionRarityRepository.findAll();
        assertThat(mActionRarityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionRarity.class);
        MActionRarity mActionRarity1 = new MActionRarity();
        mActionRarity1.setId(1L);
        MActionRarity mActionRarity2 = new MActionRarity();
        mActionRarity2.setId(mActionRarity1.getId());
        assertThat(mActionRarity1).isEqualTo(mActionRarity2);
        mActionRarity2.setId(2L);
        assertThat(mActionRarity1).isNotEqualTo(mActionRarity2);
        mActionRarity1.setId(null);
        assertThat(mActionRarity1).isNotEqualTo(mActionRarity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MActionRarityDTO.class);
        MActionRarityDTO mActionRarityDTO1 = new MActionRarityDTO();
        mActionRarityDTO1.setId(1L);
        MActionRarityDTO mActionRarityDTO2 = new MActionRarityDTO();
        assertThat(mActionRarityDTO1).isNotEqualTo(mActionRarityDTO2);
        mActionRarityDTO2.setId(mActionRarityDTO1.getId());
        assertThat(mActionRarityDTO1).isEqualTo(mActionRarityDTO2);
        mActionRarityDTO2.setId(2L);
        assertThat(mActionRarityDTO1).isNotEqualTo(mActionRarityDTO2);
        mActionRarityDTO1.setId(null);
        assertThat(mActionRarityDTO1).isNotEqualTo(mActionRarityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mActionRarityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mActionRarityMapper.fromId(null)).isNull();
    }
}
