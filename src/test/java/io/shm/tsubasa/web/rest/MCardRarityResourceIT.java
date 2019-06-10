package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCardRarity;
import io.shm.tsubasa.repository.MCardRarityRepository;
import io.shm.tsubasa.service.MCardRarityService;
import io.shm.tsubasa.service.dto.MCardRarityDTO;
import io.shm.tsubasa.service.mapper.MCardRarityMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCardRarityCriteria;
import io.shm.tsubasa.service.MCardRarityQueryService;

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
 * Integration tests for the {@Link MCardRarityResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCardRarityResourceIT {

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MCardRarityRepository mCardRarityRepository;

    @Autowired
    private MCardRarityMapper mCardRarityMapper;

    @Autowired
    private MCardRarityService mCardRarityService;

    @Autowired
    private MCardRarityQueryService mCardRarityQueryService;

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

    private MockMvc restMCardRarityMockMvc;

    private MCardRarity mCardRarity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCardRarityResource mCardRarityResource = new MCardRarityResource(mCardRarityService, mCardRarityQueryService);
        this.restMCardRarityMockMvc = MockMvcBuilders.standaloneSetup(mCardRarityResource)
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
    public static MCardRarity createEntity(EntityManager em) {
        MCardRarity mCardRarity = new MCardRarity()
            .rarity(DEFAULT_RARITY)
            .name(DEFAULT_NAME);
        return mCardRarity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCardRarity createUpdatedEntity(EntityManager em) {
        MCardRarity mCardRarity = new MCardRarity()
            .rarity(UPDATED_RARITY)
            .name(UPDATED_NAME);
        return mCardRarity;
    }

    @BeforeEach
    public void initTest() {
        mCardRarity = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCardRarity() throws Exception {
        int databaseSizeBeforeCreate = mCardRarityRepository.findAll().size();

        // Create the MCardRarity
        MCardRarityDTO mCardRarityDTO = mCardRarityMapper.toDto(mCardRarity);
        restMCardRarityMockMvc.perform(post("/api/m-card-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardRarityDTO)))
            .andExpect(status().isCreated());

        // Validate the MCardRarity in the database
        List<MCardRarity> mCardRarityList = mCardRarityRepository.findAll();
        assertThat(mCardRarityList).hasSize(databaseSizeBeforeCreate + 1);
        MCardRarity testMCardRarity = mCardRarityList.get(mCardRarityList.size() - 1);
        assertThat(testMCardRarity.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMCardRarity.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMCardRarityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCardRarityRepository.findAll().size();

        // Create the MCardRarity with an existing ID
        mCardRarity.setId(1L);
        MCardRarityDTO mCardRarityDTO = mCardRarityMapper.toDto(mCardRarity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCardRarityMockMvc.perform(post("/api/m-card-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardRarityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardRarity in the database
        List<MCardRarity> mCardRarityList = mCardRarityRepository.findAll();
        assertThat(mCardRarityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardRarityRepository.findAll().size();
        // set the field null
        mCardRarity.setRarity(null);

        // Create the MCardRarity, which fails.
        MCardRarityDTO mCardRarityDTO = mCardRarityMapper.toDto(mCardRarity);

        restMCardRarityMockMvc.perform(post("/api/m-card-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardRarityDTO)))
            .andExpect(status().isBadRequest());

        List<MCardRarity> mCardRarityList = mCardRarityRepository.findAll();
        assertThat(mCardRarityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCardRarities() throws Exception {
        // Initialize the database
        mCardRarityRepository.saveAndFlush(mCardRarity);

        // Get all the mCardRarityList
        restMCardRarityMockMvc.perform(get("/api/m-card-rarities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardRarity.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMCardRarity() throws Exception {
        // Initialize the database
        mCardRarityRepository.saveAndFlush(mCardRarity);

        // Get the mCardRarity
        restMCardRarityMockMvc.perform(get("/api/m-card-rarities/{id}", mCardRarity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCardRarity.getId().intValue()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMCardRaritiesByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardRarityRepository.saveAndFlush(mCardRarity);

        // Get all the mCardRarityList where rarity equals to DEFAULT_RARITY
        defaultMCardRarityShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mCardRarityList where rarity equals to UPDATED_RARITY
        defaultMCardRarityShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardRaritiesByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mCardRarityRepository.saveAndFlush(mCardRarity);

        // Get all the mCardRarityList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMCardRarityShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mCardRarityList where rarity equals to UPDATED_RARITY
        defaultMCardRarityShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardRaritiesByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardRarityRepository.saveAndFlush(mCardRarity);

        // Get all the mCardRarityList where rarity is not null
        defaultMCardRarityShouldBeFound("rarity.specified=true");

        // Get all the mCardRarityList where rarity is null
        defaultMCardRarityShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardRaritiesByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardRarityRepository.saveAndFlush(mCardRarity);

        // Get all the mCardRarityList where rarity greater than or equals to DEFAULT_RARITY
        defaultMCardRarityShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mCardRarityList where rarity greater than or equals to UPDATED_RARITY
        defaultMCardRarityShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardRaritiesByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardRarityRepository.saveAndFlush(mCardRarity);

        // Get all the mCardRarityList where rarity less than or equals to DEFAULT_RARITY
        defaultMCardRarityShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mCardRarityList where rarity less than or equals to UPDATED_RARITY
        defaultMCardRarityShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCardRarityShouldBeFound(String filter) throws Exception {
        restMCardRarityMockMvc.perform(get("/api/m-card-rarities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardRarity.getId().intValue())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));

        // Check, that the count call also returns 1
        restMCardRarityMockMvc.perform(get("/api/m-card-rarities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCardRarityShouldNotBeFound(String filter) throws Exception {
        restMCardRarityMockMvc.perform(get("/api/m-card-rarities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCardRarityMockMvc.perform(get("/api/m-card-rarities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCardRarity() throws Exception {
        // Get the mCardRarity
        restMCardRarityMockMvc.perform(get("/api/m-card-rarities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCardRarity() throws Exception {
        // Initialize the database
        mCardRarityRepository.saveAndFlush(mCardRarity);

        int databaseSizeBeforeUpdate = mCardRarityRepository.findAll().size();

        // Update the mCardRarity
        MCardRarity updatedMCardRarity = mCardRarityRepository.findById(mCardRarity.getId()).get();
        // Disconnect from session so that the updates on updatedMCardRarity are not directly saved in db
        em.detach(updatedMCardRarity);
        updatedMCardRarity
            .rarity(UPDATED_RARITY)
            .name(UPDATED_NAME);
        MCardRarityDTO mCardRarityDTO = mCardRarityMapper.toDto(updatedMCardRarity);

        restMCardRarityMockMvc.perform(put("/api/m-card-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardRarityDTO)))
            .andExpect(status().isOk());

        // Validate the MCardRarity in the database
        List<MCardRarity> mCardRarityList = mCardRarityRepository.findAll();
        assertThat(mCardRarityList).hasSize(databaseSizeBeforeUpdate);
        MCardRarity testMCardRarity = mCardRarityList.get(mCardRarityList.size() - 1);
        assertThat(testMCardRarity.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMCardRarity.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMCardRarity() throws Exception {
        int databaseSizeBeforeUpdate = mCardRarityRepository.findAll().size();

        // Create the MCardRarity
        MCardRarityDTO mCardRarityDTO = mCardRarityMapper.toDto(mCardRarity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCardRarityMockMvc.perform(put("/api/m-card-rarities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardRarityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardRarity in the database
        List<MCardRarity> mCardRarityList = mCardRarityRepository.findAll();
        assertThat(mCardRarityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCardRarity() throws Exception {
        // Initialize the database
        mCardRarityRepository.saveAndFlush(mCardRarity);

        int databaseSizeBeforeDelete = mCardRarityRepository.findAll().size();

        // Delete the mCardRarity
        restMCardRarityMockMvc.perform(delete("/api/m-card-rarities/{id}", mCardRarity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCardRarity> mCardRarityList = mCardRarityRepository.findAll();
        assertThat(mCardRarityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardRarity.class);
        MCardRarity mCardRarity1 = new MCardRarity();
        mCardRarity1.setId(1L);
        MCardRarity mCardRarity2 = new MCardRarity();
        mCardRarity2.setId(mCardRarity1.getId());
        assertThat(mCardRarity1).isEqualTo(mCardRarity2);
        mCardRarity2.setId(2L);
        assertThat(mCardRarity1).isNotEqualTo(mCardRarity2);
        mCardRarity1.setId(null);
        assertThat(mCardRarity1).isNotEqualTo(mCardRarity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardRarityDTO.class);
        MCardRarityDTO mCardRarityDTO1 = new MCardRarityDTO();
        mCardRarityDTO1.setId(1L);
        MCardRarityDTO mCardRarityDTO2 = new MCardRarityDTO();
        assertThat(mCardRarityDTO1).isNotEqualTo(mCardRarityDTO2);
        mCardRarityDTO2.setId(mCardRarityDTO1.getId());
        assertThat(mCardRarityDTO1).isEqualTo(mCardRarityDTO2);
        mCardRarityDTO2.setId(2L);
        assertThat(mCardRarityDTO1).isNotEqualTo(mCardRarityDTO2);
        mCardRarityDTO1.setId(null);
        assertThat(mCardRarityDTO1).isNotEqualTo(mCardRarityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCardRarityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCardRarityMapper.fromId(null)).isNull();
    }
}
