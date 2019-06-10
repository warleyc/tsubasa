package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionTradeSign;
import io.shm.tsubasa.repository.MGachaRenditionTradeSignRepository;
import io.shm.tsubasa.service.MGachaRenditionTradeSignService;
import io.shm.tsubasa.service.dto.MGachaRenditionTradeSignDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTradeSignMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionTradeSignCriteria;
import io.shm.tsubasa.service.MGachaRenditionTradeSignQueryService;

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
 * Integration tests for the {@Link MGachaRenditionTradeSignResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionTradeSignResourceIT {

    private static final Integer DEFAULT_RENDITION_ID = 1;
    private static final Integer UPDATED_RENDITION_ID = 2;

    private static final Integer DEFAULT_IS_SSR = 1;
    private static final Integer UPDATED_IS_SSR = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_SIGN_TEXTURE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SIGN_TEXTURE_NAME = "BBBBBBBBBB";

    @Autowired
    private MGachaRenditionTradeSignRepository mGachaRenditionTradeSignRepository;

    @Autowired
    private MGachaRenditionTradeSignMapper mGachaRenditionTradeSignMapper;

    @Autowired
    private MGachaRenditionTradeSignService mGachaRenditionTradeSignService;

    @Autowired
    private MGachaRenditionTradeSignQueryService mGachaRenditionTradeSignQueryService;

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

    private MockMvc restMGachaRenditionTradeSignMockMvc;

    private MGachaRenditionTradeSign mGachaRenditionTradeSign;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionTradeSignResource mGachaRenditionTradeSignResource = new MGachaRenditionTradeSignResource(mGachaRenditionTradeSignService, mGachaRenditionTradeSignQueryService);
        this.restMGachaRenditionTradeSignMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionTradeSignResource)
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
    public static MGachaRenditionTradeSign createEntity(EntityManager em) {
        MGachaRenditionTradeSign mGachaRenditionTradeSign = new MGachaRenditionTradeSign()
            .renditionId(DEFAULT_RENDITION_ID)
            .isSsr(DEFAULT_IS_SSR)
            .weight(DEFAULT_WEIGHT)
            .signTextureName(DEFAULT_SIGN_TEXTURE_NAME);
        return mGachaRenditionTradeSign;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionTradeSign createUpdatedEntity(EntityManager em) {
        MGachaRenditionTradeSign mGachaRenditionTradeSign = new MGachaRenditionTradeSign()
            .renditionId(UPDATED_RENDITION_ID)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .signTextureName(UPDATED_SIGN_TEXTURE_NAME);
        return mGachaRenditionTradeSign;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionTradeSign = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionTradeSign() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionTradeSignRepository.findAll().size();

        // Create the MGachaRenditionTradeSign
        MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO = mGachaRenditionTradeSignMapper.toDto(mGachaRenditionTradeSign);
        restMGachaRenditionTradeSignMockMvc.perform(post("/api/m-gacha-rendition-trade-signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTradeSignDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionTradeSign in the database
        List<MGachaRenditionTradeSign> mGachaRenditionTradeSignList = mGachaRenditionTradeSignRepository.findAll();
        assertThat(mGachaRenditionTradeSignList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionTradeSign testMGachaRenditionTradeSign = mGachaRenditionTradeSignList.get(mGachaRenditionTradeSignList.size() - 1);
        assertThat(testMGachaRenditionTradeSign.getRenditionId()).isEqualTo(DEFAULT_RENDITION_ID);
        assertThat(testMGachaRenditionTradeSign.getIsSsr()).isEqualTo(DEFAULT_IS_SSR);
        assertThat(testMGachaRenditionTradeSign.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionTradeSign.getSignTextureName()).isEqualTo(DEFAULT_SIGN_TEXTURE_NAME);
    }

    @Test
    @Transactional
    public void createMGachaRenditionTradeSignWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionTradeSignRepository.findAll().size();

        // Create the MGachaRenditionTradeSign with an existing ID
        mGachaRenditionTradeSign.setId(1L);
        MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO = mGachaRenditionTradeSignMapper.toDto(mGachaRenditionTradeSign);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionTradeSignMockMvc.perform(post("/api/m-gacha-rendition-trade-signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTradeSignDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionTradeSign in the database
        List<MGachaRenditionTradeSign> mGachaRenditionTradeSignList = mGachaRenditionTradeSignRepository.findAll();
        assertThat(mGachaRenditionTradeSignList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRenditionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTradeSignRepository.findAll().size();
        // set the field null
        mGachaRenditionTradeSign.setRenditionId(null);

        // Create the MGachaRenditionTradeSign, which fails.
        MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO = mGachaRenditionTradeSignMapper.toDto(mGachaRenditionTradeSign);

        restMGachaRenditionTradeSignMockMvc.perform(post("/api/m-gacha-rendition-trade-signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTradeSignDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTradeSign> mGachaRenditionTradeSignList = mGachaRenditionTradeSignRepository.findAll();
        assertThat(mGachaRenditionTradeSignList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsSsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTradeSignRepository.findAll().size();
        // set the field null
        mGachaRenditionTradeSign.setIsSsr(null);

        // Create the MGachaRenditionTradeSign, which fails.
        MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO = mGachaRenditionTradeSignMapper.toDto(mGachaRenditionTradeSign);

        restMGachaRenditionTradeSignMockMvc.perform(post("/api/m-gacha-rendition-trade-signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTradeSignDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTradeSign> mGachaRenditionTradeSignList = mGachaRenditionTradeSignRepository.findAll();
        assertThat(mGachaRenditionTradeSignList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionTradeSignRepository.findAll().size();
        // set the field null
        mGachaRenditionTradeSign.setWeight(null);

        // Create the MGachaRenditionTradeSign, which fails.
        MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO = mGachaRenditionTradeSignMapper.toDto(mGachaRenditionTradeSign);

        restMGachaRenditionTradeSignMockMvc.perform(post("/api/m-gacha-rendition-trade-signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTradeSignDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionTradeSign> mGachaRenditionTradeSignList = mGachaRenditionTradeSignRepository.findAll();
        assertThat(mGachaRenditionTradeSignList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSigns() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList
        restMGachaRenditionTradeSignMockMvc.perform(get("/api/m-gacha-rendition-trade-signs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionTradeSign.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].signTextureName").value(hasItem(DEFAULT_SIGN_TEXTURE_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionTradeSign() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get the mGachaRenditionTradeSign
        restMGachaRenditionTradeSignMockMvc.perform(get("/api/m-gacha-rendition-trade-signs/{id}", mGachaRenditionTradeSign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionTradeSign.getId().intValue()))
            .andExpect(jsonPath("$.renditionId").value(DEFAULT_RENDITION_ID))
            .andExpect(jsonPath("$.isSsr").value(DEFAULT_IS_SSR))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.signTextureName").value(DEFAULT_SIGN_TEXTURE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByRenditionIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where renditionId equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionTradeSignShouldBeFound("renditionId.equals=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionTradeSignList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionTradeSignShouldNotBeFound("renditionId.equals=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByRenditionIdIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where renditionId in DEFAULT_RENDITION_ID or UPDATED_RENDITION_ID
        defaultMGachaRenditionTradeSignShouldBeFound("renditionId.in=" + DEFAULT_RENDITION_ID + "," + UPDATED_RENDITION_ID);

        // Get all the mGachaRenditionTradeSignList where renditionId equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionTradeSignShouldNotBeFound("renditionId.in=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByRenditionIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where renditionId is not null
        defaultMGachaRenditionTradeSignShouldBeFound("renditionId.specified=true");

        // Get all the mGachaRenditionTradeSignList where renditionId is null
        defaultMGachaRenditionTradeSignShouldNotBeFound("renditionId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByRenditionIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where renditionId greater than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionTradeSignShouldBeFound("renditionId.greaterOrEqualThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionTradeSignList where renditionId greater than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionTradeSignShouldNotBeFound("renditionId.greaterOrEqualThan=" + UPDATED_RENDITION_ID);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByRenditionIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where renditionId less than or equals to DEFAULT_RENDITION_ID
        defaultMGachaRenditionTradeSignShouldNotBeFound("renditionId.lessThan=" + DEFAULT_RENDITION_ID);

        // Get all the mGachaRenditionTradeSignList where renditionId less than or equals to UPDATED_RENDITION_ID
        defaultMGachaRenditionTradeSignShouldBeFound("renditionId.lessThan=" + UPDATED_RENDITION_ID);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByIsSsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where isSsr equals to DEFAULT_IS_SSR
        defaultMGachaRenditionTradeSignShouldBeFound("isSsr.equals=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionTradeSignList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionTradeSignShouldNotBeFound("isSsr.equals=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByIsSsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where isSsr in DEFAULT_IS_SSR or UPDATED_IS_SSR
        defaultMGachaRenditionTradeSignShouldBeFound("isSsr.in=" + DEFAULT_IS_SSR + "," + UPDATED_IS_SSR);

        // Get all the mGachaRenditionTradeSignList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionTradeSignShouldNotBeFound("isSsr.in=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByIsSsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where isSsr is not null
        defaultMGachaRenditionTradeSignShouldBeFound("isSsr.specified=true");

        // Get all the mGachaRenditionTradeSignList where isSsr is null
        defaultMGachaRenditionTradeSignShouldNotBeFound("isSsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByIsSsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where isSsr greater than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionTradeSignShouldBeFound("isSsr.greaterOrEqualThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionTradeSignList where isSsr greater than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionTradeSignShouldNotBeFound("isSsr.greaterOrEqualThan=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByIsSsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where isSsr less than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionTradeSignShouldNotBeFound("isSsr.lessThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionTradeSignList where isSsr less than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionTradeSignShouldBeFound("isSsr.lessThan=" + UPDATED_IS_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTradeSignShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTradeSignList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionTradeSignShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionTradeSignShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionTradeSignList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionTradeSignShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where weight is not null
        defaultMGachaRenditionTradeSignShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionTradeSignList where weight is null
        defaultMGachaRenditionTradeSignShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTradeSignShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTradeSignList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionTradeSignShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionTradeSignsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        // Get all the mGachaRenditionTradeSignList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionTradeSignShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionTradeSignList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionTradeSignShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionTradeSignShouldBeFound(String filter) throws Exception {
        restMGachaRenditionTradeSignMockMvc.perform(get("/api/m-gacha-rendition-trade-signs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionTradeSign.getId().intValue())))
            .andExpect(jsonPath("$.[*].renditionId").value(hasItem(DEFAULT_RENDITION_ID)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].signTextureName").value(hasItem(DEFAULT_SIGN_TEXTURE_NAME.toString())));

        // Check, that the count call also returns 1
        restMGachaRenditionTradeSignMockMvc.perform(get("/api/m-gacha-rendition-trade-signs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionTradeSignShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionTradeSignMockMvc.perform(get("/api/m-gacha-rendition-trade-signs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionTradeSignMockMvc.perform(get("/api/m-gacha-rendition-trade-signs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionTradeSign() throws Exception {
        // Get the mGachaRenditionTradeSign
        restMGachaRenditionTradeSignMockMvc.perform(get("/api/m-gacha-rendition-trade-signs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionTradeSign() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        int databaseSizeBeforeUpdate = mGachaRenditionTradeSignRepository.findAll().size();

        // Update the mGachaRenditionTradeSign
        MGachaRenditionTradeSign updatedMGachaRenditionTradeSign = mGachaRenditionTradeSignRepository.findById(mGachaRenditionTradeSign.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionTradeSign are not directly saved in db
        em.detach(updatedMGachaRenditionTradeSign);
        updatedMGachaRenditionTradeSign
            .renditionId(UPDATED_RENDITION_ID)
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .signTextureName(UPDATED_SIGN_TEXTURE_NAME);
        MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO = mGachaRenditionTradeSignMapper.toDto(updatedMGachaRenditionTradeSign);

        restMGachaRenditionTradeSignMockMvc.perform(put("/api/m-gacha-rendition-trade-signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTradeSignDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionTradeSign in the database
        List<MGachaRenditionTradeSign> mGachaRenditionTradeSignList = mGachaRenditionTradeSignRepository.findAll();
        assertThat(mGachaRenditionTradeSignList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionTradeSign testMGachaRenditionTradeSign = mGachaRenditionTradeSignList.get(mGachaRenditionTradeSignList.size() - 1);
        assertThat(testMGachaRenditionTradeSign.getRenditionId()).isEqualTo(UPDATED_RENDITION_ID);
        assertThat(testMGachaRenditionTradeSign.getIsSsr()).isEqualTo(UPDATED_IS_SSR);
        assertThat(testMGachaRenditionTradeSign.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionTradeSign.getSignTextureName()).isEqualTo(UPDATED_SIGN_TEXTURE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionTradeSign() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionTradeSignRepository.findAll().size();

        // Create the MGachaRenditionTradeSign
        MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO = mGachaRenditionTradeSignMapper.toDto(mGachaRenditionTradeSign);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionTradeSignMockMvc.perform(put("/api/m-gacha-rendition-trade-signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionTradeSignDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionTradeSign in the database
        List<MGachaRenditionTradeSign> mGachaRenditionTradeSignList = mGachaRenditionTradeSignRepository.findAll();
        assertThat(mGachaRenditionTradeSignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionTradeSign() throws Exception {
        // Initialize the database
        mGachaRenditionTradeSignRepository.saveAndFlush(mGachaRenditionTradeSign);

        int databaseSizeBeforeDelete = mGachaRenditionTradeSignRepository.findAll().size();

        // Delete the mGachaRenditionTradeSign
        restMGachaRenditionTradeSignMockMvc.perform(delete("/api/m-gacha-rendition-trade-signs/{id}", mGachaRenditionTradeSign.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionTradeSign> mGachaRenditionTradeSignList = mGachaRenditionTradeSignRepository.findAll();
        assertThat(mGachaRenditionTradeSignList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionTradeSign.class);
        MGachaRenditionTradeSign mGachaRenditionTradeSign1 = new MGachaRenditionTradeSign();
        mGachaRenditionTradeSign1.setId(1L);
        MGachaRenditionTradeSign mGachaRenditionTradeSign2 = new MGachaRenditionTradeSign();
        mGachaRenditionTradeSign2.setId(mGachaRenditionTradeSign1.getId());
        assertThat(mGachaRenditionTradeSign1).isEqualTo(mGachaRenditionTradeSign2);
        mGachaRenditionTradeSign2.setId(2L);
        assertThat(mGachaRenditionTradeSign1).isNotEqualTo(mGachaRenditionTradeSign2);
        mGachaRenditionTradeSign1.setId(null);
        assertThat(mGachaRenditionTradeSign1).isNotEqualTo(mGachaRenditionTradeSign2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionTradeSignDTO.class);
        MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO1 = new MGachaRenditionTradeSignDTO();
        mGachaRenditionTradeSignDTO1.setId(1L);
        MGachaRenditionTradeSignDTO mGachaRenditionTradeSignDTO2 = new MGachaRenditionTradeSignDTO();
        assertThat(mGachaRenditionTradeSignDTO1).isNotEqualTo(mGachaRenditionTradeSignDTO2);
        mGachaRenditionTradeSignDTO2.setId(mGachaRenditionTradeSignDTO1.getId());
        assertThat(mGachaRenditionTradeSignDTO1).isEqualTo(mGachaRenditionTradeSignDTO2);
        mGachaRenditionTradeSignDTO2.setId(2L);
        assertThat(mGachaRenditionTradeSignDTO1).isNotEqualTo(mGachaRenditionTradeSignDTO2);
        mGachaRenditionTradeSignDTO1.setId(null);
        assertThat(mGachaRenditionTradeSignDTO1).isNotEqualTo(mGachaRenditionTradeSignDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionTradeSignMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionTradeSignMapper.fromId(null)).isNull();
    }
}
