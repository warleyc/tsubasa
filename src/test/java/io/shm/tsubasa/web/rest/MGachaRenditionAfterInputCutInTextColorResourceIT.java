package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionAfterInputCutInTextColor;
import io.shm.tsubasa.repository.MGachaRenditionAfterInputCutInTextColorRepository;
import io.shm.tsubasa.service.MGachaRenditionAfterInputCutInTextColorService;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInTextColorDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionAfterInputCutInTextColorMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionAfterInputCutInTextColorCriteria;
import io.shm.tsubasa.service.MGachaRenditionAfterInputCutInTextColorQueryService;

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
 * Integration tests for the {@Link MGachaRenditionAfterInputCutInTextColorResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionAfterInputCutInTextColorResourceIT {

    private static final Integer DEFAULT_IS_SSR = 1;
    private static final Integer UPDATED_IS_SSR = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    @Autowired
    private MGachaRenditionAfterInputCutInTextColorRepository mGachaRenditionAfterInputCutInTextColorRepository;

    @Autowired
    private MGachaRenditionAfterInputCutInTextColorMapper mGachaRenditionAfterInputCutInTextColorMapper;

    @Autowired
    private MGachaRenditionAfterInputCutInTextColorService mGachaRenditionAfterInputCutInTextColorService;

    @Autowired
    private MGachaRenditionAfterInputCutInTextColorQueryService mGachaRenditionAfterInputCutInTextColorQueryService;

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

    private MockMvc restMGachaRenditionAfterInputCutInTextColorMockMvc;

    private MGachaRenditionAfterInputCutInTextColor mGachaRenditionAfterInputCutInTextColor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionAfterInputCutInTextColorResource mGachaRenditionAfterInputCutInTextColorResource = new MGachaRenditionAfterInputCutInTextColorResource(mGachaRenditionAfterInputCutInTextColorService, mGachaRenditionAfterInputCutInTextColorQueryService);
        this.restMGachaRenditionAfterInputCutInTextColorMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionAfterInputCutInTextColorResource)
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
    public static MGachaRenditionAfterInputCutInTextColor createEntity(EntityManager em) {
        MGachaRenditionAfterInputCutInTextColor mGachaRenditionAfterInputCutInTextColor = new MGachaRenditionAfterInputCutInTextColor()
            .isSsr(DEFAULT_IS_SSR)
            .weight(DEFAULT_WEIGHT)
            .color(DEFAULT_COLOR);
        return mGachaRenditionAfterInputCutInTextColor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionAfterInputCutInTextColor createUpdatedEntity(EntityManager em) {
        MGachaRenditionAfterInputCutInTextColor mGachaRenditionAfterInputCutInTextColor = new MGachaRenditionAfterInputCutInTextColor()
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .color(UPDATED_COLOR);
        return mGachaRenditionAfterInputCutInTextColor;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionAfterInputCutInTextColor = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionAfterInputCutInTextColor() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionAfterInputCutInTextColorRepository.findAll().size();

        // Create the MGachaRenditionAfterInputCutInTextColor
        MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO = mGachaRenditionAfterInputCutInTextColorMapper.toDto(mGachaRenditionAfterInputCutInTextColor);
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(post("/api/m-gacha-rendition-after-input-cut-in-text-colors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInTextColorDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionAfterInputCutInTextColor in the database
        List<MGachaRenditionAfterInputCutInTextColor> mGachaRenditionAfterInputCutInTextColorList = mGachaRenditionAfterInputCutInTextColorRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInTextColorList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionAfterInputCutInTextColor testMGachaRenditionAfterInputCutInTextColor = mGachaRenditionAfterInputCutInTextColorList.get(mGachaRenditionAfterInputCutInTextColorList.size() - 1);
        assertThat(testMGachaRenditionAfterInputCutInTextColor.getIsSsr()).isEqualTo(DEFAULT_IS_SSR);
        assertThat(testMGachaRenditionAfterInputCutInTextColor.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionAfterInputCutInTextColor.getColor()).isEqualTo(DEFAULT_COLOR);
    }

    @Test
    @Transactional
    public void createMGachaRenditionAfterInputCutInTextColorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionAfterInputCutInTextColorRepository.findAll().size();

        // Create the MGachaRenditionAfterInputCutInTextColor with an existing ID
        mGachaRenditionAfterInputCutInTextColor.setId(1L);
        MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO = mGachaRenditionAfterInputCutInTextColorMapper.toDto(mGachaRenditionAfterInputCutInTextColor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(post("/api/m-gacha-rendition-after-input-cut-in-text-colors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInTextColorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionAfterInputCutInTextColor in the database
        List<MGachaRenditionAfterInputCutInTextColor> mGachaRenditionAfterInputCutInTextColorList = mGachaRenditionAfterInputCutInTextColorRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInTextColorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIsSsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionAfterInputCutInTextColorRepository.findAll().size();
        // set the field null
        mGachaRenditionAfterInputCutInTextColor.setIsSsr(null);

        // Create the MGachaRenditionAfterInputCutInTextColor, which fails.
        MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO = mGachaRenditionAfterInputCutInTextColorMapper.toDto(mGachaRenditionAfterInputCutInTextColor);

        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(post("/api/m-gacha-rendition-after-input-cut-in-text-colors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInTextColorDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionAfterInputCutInTextColor> mGachaRenditionAfterInputCutInTextColorList = mGachaRenditionAfterInputCutInTextColorRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInTextColorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionAfterInputCutInTextColorRepository.findAll().size();
        // set the field null
        mGachaRenditionAfterInputCutInTextColor.setWeight(null);

        // Create the MGachaRenditionAfterInputCutInTextColor, which fails.
        MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO = mGachaRenditionAfterInputCutInTextColorMapper.toDto(mGachaRenditionAfterInputCutInTextColor);

        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(post("/api/m-gacha-rendition-after-input-cut-in-text-colors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInTextColorDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionAfterInputCutInTextColor> mGachaRenditionAfterInputCutInTextColorList = mGachaRenditionAfterInputCutInTextColorRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInTextColorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColors() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-in-text-colors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionAfterInputCutInTextColor.getId().intValue())))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR.toString())));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionAfterInputCutInTextColor() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get the mGachaRenditionAfterInputCutInTextColor
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-in-text-colors/{id}", mGachaRenditionAfterInputCutInTextColor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionAfterInputCutInTextColor.getId().intValue()))
            .andExpect(jsonPath("$.isSsr").value(DEFAULT_IS_SSR))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR.toString()));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByIsSsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr equals to DEFAULT_IS_SSR
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("isSsr.equals=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("isSsr.equals=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByIsSsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr in DEFAULT_IS_SSR or UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("isSsr.in=" + DEFAULT_IS_SSR + "," + UPDATED_IS_SSR);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("isSsr.in=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByIsSsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr is not null
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("isSsr.specified=true");

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr is null
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("isSsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByIsSsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr greater than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("isSsr.greaterOrEqualThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr greater than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("isSsr.greaterOrEqualThan=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByIsSsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr less than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("isSsr.lessThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where isSsr less than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("isSsr.lessThan=" + UPDATED_IS_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight is not null
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight is null
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionAfterInputCutInTextColorsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionAfterInputCutInTextColorList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionAfterInputCutInTextColorShouldBeFound(String filter) throws Exception {
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-in-text-colors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionAfterInputCutInTextColor.getId().intValue())))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR.toString())));

        // Check, that the count call also returns 1
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-in-text-colors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionAfterInputCutInTextColorShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-in-text-colors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-in-text-colors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionAfterInputCutInTextColor() throws Exception {
        // Get the mGachaRenditionAfterInputCutInTextColor
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(get("/api/m-gacha-rendition-after-input-cut-in-text-colors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionAfterInputCutInTextColor() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        int databaseSizeBeforeUpdate = mGachaRenditionAfterInputCutInTextColorRepository.findAll().size();

        // Update the mGachaRenditionAfterInputCutInTextColor
        MGachaRenditionAfterInputCutInTextColor updatedMGachaRenditionAfterInputCutInTextColor = mGachaRenditionAfterInputCutInTextColorRepository.findById(mGachaRenditionAfterInputCutInTextColor.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionAfterInputCutInTextColor are not directly saved in db
        em.detach(updatedMGachaRenditionAfterInputCutInTextColor);
        updatedMGachaRenditionAfterInputCutInTextColor
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .color(UPDATED_COLOR);
        MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO = mGachaRenditionAfterInputCutInTextColorMapper.toDto(updatedMGachaRenditionAfterInputCutInTextColor);

        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(put("/api/m-gacha-rendition-after-input-cut-in-text-colors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInTextColorDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionAfterInputCutInTextColor in the database
        List<MGachaRenditionAfterInputCutInTextColor> mGachaRenditionAfterInputCutInTextColorList = mGachaRenditionAfterInputCutInTextColorRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInTextColorList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionAfterInputCutInTextColor testMGachaRenditionAfterInputCutInTextColor = mGachaRenditionAfterInputCutInTextColorList.get(mGachaRenditionAfterInputCutInTextColorList.size() - 1);
        assertThat(testMGachaRenditionAfterInputCutInTextColor.getIsSsr()).isEqualTo(UPDATED_IS_SSR);
        assertThat(testMGachaRenditionAfterInputCutInTextColor.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionAfterInputCutInTextColor.getColor()).isEqualTo(UPDATED_COLOR);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionAfterInputCutInTextColor() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionAfterInputCutInTextColorRepository.findAll().size();

        // Create the MGachaRenditionAfterInputCutInTextColor
        MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO = mGachaRenditionAfterInputCutInTextColorMapper.toDto(mGachaRenditionAfterInputCutInTextColor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(put("/api/m-gacha-rendition-after-input-cut-in-text-colors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionAfterInputCutInTextColorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionAfterInputCutInTextColor in the database
        List<MGachaRenditionAfterInputCutInTextColor> mGachaRenditionAfterInputCutInTextColorList = mGachaRenditionAfterInputCutInTextColorRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInTextColorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionAfterInputCutInTextColor() throws Exception {
        // Initialize the database
        mGachaRenditionAfterInputCutInTextColorRepository.saveAndFlush(mGachaRenditionAfterInputCutInTextColor);

        int databaseSizeBeforeDelete = mGachaRenditionAfterInputCutInTextColorRepository.findAll().size();

        // Delete the mGachaRenditionAfterInputCutInTextColor
        restMGachaRenditionAfterInputCutInTextColorMockMvc.perform(delete("/api/m-gacha-rendition-after-input-cut-in-text-colors/{id}", mGachaRenditionAfterInputCutInTextColor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionAfterInputCutInTextColor> mGachaRenditionAfterInputCutInTextColorList = mGachaRenditionAfterInputCutInTextColorRepository.findAll();
        assertThat(mGachaRenditionAfterInputCutInTextColorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionAfterInputCutInTextColor.class);
        MGachaRenditionAfterInputCutInTextColor mGachaRenditionAfterInputCutInTextColor1 = new MGachaRenditionAfterInputCutInTextColor();
        mGachaRenditionAfterInputCutInTextColor1.setId(1L);
        MGachaRenditionAfterInputCutInTextColor mGachaRenditionAfterInputCutInTextColor2 = new MGachaRenditionAfterInputCutInTextColor();
        mGachaRenditionAfterInputCutInTextColor2.setId(mGachaRenditionAfterInputCutInTextColor1.getId());
        assertThat(mGachaRenditionAfterInputCutInTextColor1).isEqualTo(mGachaRenditionAfterInputCutInTextColor2);
        mGachaRenditionAfterInputCutInTextColor2.setId(2L);
        assertThat(mGachaRenditionAfterInputCutInTextColor1).isNotEqualTo(mGachaRenditionAfterInputCutInTextColor2);
        mGachaRenditionAfterInputCutInTextColor1.setId(null);
        assertThat(mGachaRenditionAfterInputCutInTextColor1).isNotEqualTo(mGachaRenditionAfterInputCutInTextColor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionAfterInputCutInTextColorDTO.class);
        MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO1 = new MGachaRenditionAfterInputCutInTextColorDTO();
        mGachaRenditionAfterInputCutInTextColorDTO1.setId(1L);
        MGachaRenditionAfterInputCutInTextColorDTO mGachaRenditionAfterInputCutInTextColorDTO2 = new MGachaRenditionAfterInputCutInTextColorDTO();
        assertThat(mGachaRenditionAfterInputCutInTextColorDTO1).isNotEqualTo(mGachaRenditionAfterInputCutInTextColorDTO2);
        mGachaRenditionAfterInputCutInTextColorDTO2.setId(mGachaRenditionAfterInputCutInTextColorDTO1.getId());
        assertThat(mGachaRenditionAfterInputCutInTextColorDTO1).isEqualTo(mGachaRenditionAfterInputCutInTextColorDTO2);
        mGachaRenditionAfterInputCutInTextColorDTO2.setId(2L);
        assertThat(mGachaRenditionAfterInputCutInTextColorDTO1).isNotEqualTo(mGachaRenditionAfterInputCutInTextColorDTO2);
        mGachaRenditionAfterInputCutInTextColorDTO1.setId(null);
        assertThat(mGachaRenditionAfterInputCutInTextColorDTO1).isNotEqualTo(mGachaRenditionAfterInputCutInTextColorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionAfterInputCutInTextColorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionAfterInputCutInTextColorMapper.fromId(null)).isNull();
    }
}
