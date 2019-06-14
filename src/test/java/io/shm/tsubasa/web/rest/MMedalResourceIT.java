package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMedal;
import io.shm.tsubasa.repository.MMedalRepository;
import io.shm.tsubasa.service.MMedalService;
import io.shm.tsubasa.service.dto.MMedalDTO;
import io.shm.tsubasa.service.mapper.MMedalMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMedalCriteria;
import io.shm.tsubasa.service.MMedalQueryService;

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
 * Integration tests for the {@Link MMedalResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMedalResourceIT {

    private static final Integer DEFAULT_MEDAL_TYPE = 1;
    private static final Integer UPDATED_MEDAL_TYPE = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_AMOUNT = 1;
    private static final Integer UPDATED_MAX_AMOUNT = 2;

    private static final String DEFAULT_ICON_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ICON_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET_NAME = "BBBBBBBBBB";

    @Autowired
    private MMedalRepository mMedalRepository;

    @Autowired
    private MMedalMapper mMedalMapper;

    @Autowired
    private MMedalService mMedalService;

    @Autowired
    private MMedalQueryService mMedalQueryService;

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

    private MockMvc restMMedalMockMvc;

    private MMedal mMedal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMedalResource mMedalResource = new MMedalResource(mMedalService, mMedalQueryService);
        this.restMMedalMockMvc = MockMvcBuilders.standaloneSetup(mMedalResource)
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
    public static MMedal createEntity(EntityManager em) {
        MMedal mMedal = new MMedal()
            .medalType(DEFAULT_MEDAL_TYPE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .maxAmount(DEFAULT_MAX_AMOUNT)
            .iconAssetName(DEFAULT_ICON_ASSET_NAME)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME);
        return mMedal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMedal createUpdatedEntity(EntityManager em) {
        MMedal mMedal = new MMedal()
            .medalType(UPDATED_MEDAL_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .maxAmount(UPDATED_MAX_AMOUNT)
            .iconAssetName(UPDATED_ICON_ASSET_NAME)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME);
        return mMedal;
    }

    @BeforeEach
    public void initTest() {
        mMedal = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMedal() throws Exception {
        int databaseSizeBeforeCreate = mMedalRepository.findAll().size();

        // Create the MMedal
        MMedalDTO mMedalDTO = mMedalMapper.toDto(mMedal);
        restMMedalMockMvc.perform(post("/api/m-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMedalDTO)))
            .andExpect(status().isCreated());

        // Validate the MMedal in the database
        List<MMedal> mMedalList = mMedalRepository.findAll();
        assertThat(mMedalList).hasSize(databaseSizeBeforeCreate + 1);
        MMedal testMMedal = mMedalList.get(mMedalList.size() - 1);
        assertThat(testMMedal.getMedalType()).isEqualTo(DEFAULT_MEDAL_TYPE);
        assertThat(testMMedal.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMMedal.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMMedal.getMaxAmount()).isEqualTo(DEFAULT_MAX_AMOUNT);
        assertThat(testMMedal.getIconAssetName()).isEqualTo(DEFAULT_ICON_ASSET_NAME);
        assertThat(testMMedal.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
    }

    @Test
    @Transactional
    public void createMMedalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMedalRepository.findAll().size();

        // Create the MMedal with an existing ID
        mMedal.setId(1L);
        MMedalDTO mMedalDTO = mMedalMapper.toDto(mMedal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMedalMockMvc.perform(post("/api/m-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMedalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMedal in the database
        List<MMedal> mMedalList = mMedalRepository.findAll();
        assertThat(mMedalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMedalTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMedalRepository.findAll().size();
        // set the field null
        mMedal.setMedalType(null);

        // Create the MMedal, which fails.
        MMedalDTO mMedalDTO = mMedalMapper.toDto(mMedal);

        restMMedalMockMvc.perform(post("/api/m-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMedalDTO)))
            .andExpect(status().isBadRequest());

        List<MMedal> mMedalList = mMedalRepository.findAll();
        assertThat(mMedalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMedalRepository.findAll().size();
        // set the field null
        mMedal.setMaxAmount(null);

        // Create the MMedal, which fails.
        MMedalDTO mMedalDTO = mMedalMapper.toDto(mMedal);

        restMMedalMockMvc.perform(post("/api/m-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMedalDTO)))
            .andExpect(status().isBadRequest());

        List<MMedal> mMedalList = mMedalRepository.findAll();
        assertThat(mMedalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMedals() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList
        restMMedalMockMvc.perform(get("/api/m-medals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMedal.getId().intValue())))
            .andExpect(jsonPath("$.[*].medalType").value(hasItem(DEFAULT_MEDAL_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].maxAmount").value(hasItem(DEFAULT_MAX_AMOUNT)))
            .andExpect(jsonPath("$.[*].iconAssetName").value(hasItem(DEFAULT_ICON_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMMedal() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get the mMedal
        restMMedalMockMvc.perform(get("/api/m-medals/{id}", mMedal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMedal.getId().intValue()))
            .andExpect(jsonPath("$.medalType").value(DEFAULT_MEDAL_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.maxAmount").value(DEFAULT_MAX_AMOUNT))
            .andExpect(jsonPath("$.iconAssetName").value(DEFAULT_ICON_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMMedalsByMedalTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where medalType equals to DEFAULT_MEDAL_TYPE
        defaultMMedalShouldBeFound("medalType.equals=" + DEFAULT_MEDAL_TYPE);

        // Get all the mMedalList where medalType equals to UPDATED_MEDAL_TYPE
        defaultMMedalShouldNotBeFound("medalType.equals=" + UPDATED_MEDAL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMedalsByMedalTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where medalType in DEFAULT_MEDAL_TYPE or UPDATED_MEDAL_TYPE
        defaultMMedalShouldBeFound("medalType.in=" + DEFAULT_MEDAL_TYPE + "," + UPDATED_MEDAL_TYPE);

        // Get all the mMedalList where medalType equals to UPDATED_MEDAL_TYPE
        defaultMMedalShouldNotBeFound("medalType.in=" + UPDATED_MEDAL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMedalsByMedalTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where medalType is not null
        defaultMMedalShouldBeFound("medalType.specified=true");

        // Get all the mMedalList where medalType is null
        defaultMMedalShouldNotBeFound("medalType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMedalsByMedalTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where medalType greater than or equals to DEFAULT_MEDAL_TYPE
        defaultMMedalShouldBeFound("medalType.greaterOrEqualThan=" + DEFAULT_MEDAL_TYPE);

        // Get all the mMedalList where medalType greater than or equals to UPDATED_MEDAL_TYPE
        defaultMMedalShouldNotBeFound("medalType.greaterOrEqualThan=" + UPDATED_MEDAL_TYPE);
    }

    @Test
    @Transactional
    public void getAllMMedalsByMedalTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where medalType less than or equals to DEFAULT_MEDAL_TYPE
        defaultMMedalShouldNotBeFound("medalType.lessThan=" + DEFAULT_MEDAL_TYPE);

        // Get all the mMedalList where medalType less than or equals to UPDATED_MEDAL_TYPE
        defaultMMedalShouldBeFound("medalType.lessThan=" + UPDATED_MEDAL_TYPE);
    }


    @Test
    @Transactional
    public void getAllMMedalsByMaxAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where maxAmount equals to DEFAULT_MAX_AMOUNT
        defaultMMedalShouldBeFound("maxAmount.equals=" + DEFAULT_MAX_AMOUNT);

        // Get all the mMedalList where maxAmount equals to UPDATED_MAX_AMOUNT
        defaultMMedalShouldNotBeFound("maxAmount.equals=" + UPDATED_MAX_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMedalsByMaxAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where maxAmount in DEFAULT_MAX_AMOUNT or UPDATED_MAX_AMOUNT
        defaultMMedalShouldBeFound("maxAmount.in=" + DEFAULT_MAX_AMOUNT + "," + UPDATED_MAX_AMOUNT);

        // Get all the mMedalList where maxAmount equals to UPDATED_MAX_AMOUNT
        defaultMMedalShouldNotBeFound("maxAmount.in=" + UPDATED_MAX_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMedalsByMaxAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where maxAmount is not null
        defaultMMedalShouldBeFound("maxAmount.specified=true");

        // Get all the mMedalList where maxAmount is null
        defaultMMedalShouldNotBeFound("maxAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMedalsByMaxAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where maxAmount greater than or equals to DEFAULT_MAX_AMOUNT
        defaultMMedalShouldBeFound("maxAmount.greaterOrEqualThan=" + DEFAULT_MAX_AMOUNT);

        // Get all the mMedalList where maxAmount greater than or equals to UPDATED_MAX_AMOUNT
        defaultMMedalShouldNotBeFound("maxAmount.greaterOrEqualThan=" + UPDATED_MAX_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMMedalsByMaxAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        // Get all the mMedalList where maxAmount less than or equals to DEFAULT_MAX_AMOUNT
        defaultMMedalShouldNotBeFound("maxAmount.lessThan=" + DEFAULT_MAX_AMOUNT);

        // Get all the mMedalList where maxAmount less than or equals to UPDATED_MAX_AMOUNT
        defaultMMedalShouldBeFound("maxAmount.lessThan=" + UPDATED_MAX_AMOUNT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMedalShouldBeFound(String filter) throws Exception {
        restMMedalMockMvc.perform(get("/api/m-medals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMedal.getId().intValue())))
            .andExpect(jsonPath("$.[*].medalType").value(hasItem(DEFAULT_MEDAL_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].maxAmount").value(hasItem(DEFAULT_MAX_AMOUNT)))
            .andExpect(jsonPath("$.[*].iconAssetName").value(hasItem(DEFAULT_ICON_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())));

        // Check, that the count call also returns 1
        restMMedalMockMvc.perform(get("/api/m-medals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMedalShouldNotBeFound(String filter) throws Exception {
        restMMedalMockMvc.perform(get("/api/m-medals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMedalMockMvc.perform(get("/api/m-medals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMedal() throws Exception {
        // Get the mMedal
        restMMedalMockMvc.perform(get("/api/m-medals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMedal() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        int databaseSizeBeforeUpdate = mMedalRepository.findAll().size();

        // Update the mMedal
        MMedal updatedMMedal = mMedalRepository.findById(mMedal.getId()).get();
        // Disconnect from session so that the updates on updatedMMedal are not directly saved in db
        em.detach(updatedMMedal);
        updatedMMedal
            .medalType(UPDATED_MEDAL_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .maxAmount(UPDATED_MAX_AMOUNT)
            .iconAssetName(UPDATED_ICON_ASSET_NAME)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME);
        MMedalDTO mMedalDTO = mMedalMapper.toDto(updatedMMedal);

        restMMedalMockMvc.perform(put("/api/m-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMedalDTO)))
            .andExpect(status().isOk());

        // Validate the MMedal in the database
        List<MMedal> mMedalList = mMedalRepository.findAll();
        assertThat(mMedalList).hasSize(databaseSizeBeforeUpdate);
        MMedal testMMedal = mMedalList.get(mMedalList.size() - 1);
        assertThat(testMMedal.getMedalType()).isEqualTo(UPDATED_MEDAL_TYPE);
        assertThat(testMMedal.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMMedal.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMMedal.getMaxAmount()).isEqualTo(UPDATED_MAX_AMOUNT);
        assertThat(testMMedal.getIconAssetName()).isEqualTo(UPDATED_ICON_ASSET_NAME);
        assertThat(testMMedal.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMMedal() throws Exception {
        int databaseSizeBeforeUpdate = mMedalRepository.findAll().size();

        // Create the MMedal
        MMedalDTO mMedalDTO = mMedalMapper.toDto(mMedal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMedalMockMvc.perform(put("/api/m-medals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMedalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMedal in the database
        List<MMedal> mMedalList = mMedalRepository.findAll();
        assertThat(mMedalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMedal() throws Exception {
        // Initialize the database
        mMedalRepository.saveAndFlush(mMedal);

        int databaseSizeBeforeDelete = mMedalRepository.findAll().size();

        // Delete the mMedal
        restMMedalMockMvc.perform(delete("/api/m-medals/{id}", mMedal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMedal> mMedalList = mMedalRepository.findAll();
        assertThat(mMedalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMedal.class);
        MMedal mMedal1 = new MMedal();
        mMedal1.setId(1L);
        MMedal mMedal2 = new MMedal();
        mMedal2.setId(mMedal1.getId());
        assertThat(mMedal1).isEqualTo(mMedal2);
        mMedal2.setId(2L);
        assertThat(mMedal1).isNotEqualTo(mMedal2);
        mMedal1.setId(null);
        assertThat(mMedal1).isNotEqualTo(mMedal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMedalDTO.class);
        MMedalDTO mMedalDTO1 = new MMedalDTO();
        mMedalDTO1.setId(1L);
        MMedalDTO mMedalDTO2 = new MMedalDTO();
        assertThat(mMedalDTO1).isNotEqualTo(mMedalDTO2);
        mMedalDTO2.setId(mMedalDTO1.getId());
        assertThat(mMedalDTO1).isEqualTo(mMedalDTO2);
        mMedalDTO2.setId(2L);
        assertThat(mMedalDTO1).isNotEqualTo(mMedalDTO2);
        mMedalDTO1.setId(null);
        assertThat(mMedalDTO1).isNotEqualTo(mMedalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMedalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMedalMapper.fromId(null)).isNull();
    }
}
