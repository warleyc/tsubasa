package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInCharacterNum;
import io.shm.tsubasa.repository.MGachaRenditionBeforeShootCutInCharacterNumRepository;
import io.shm.tsubasa.service.MGachaRenditionBeforeShootCutInCharacterNumService;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInCharacterNumDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionBeforeShootCutInCharacterNumMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInCharacterNumCriteria;
import io.shm.tsubasa.service.MGachaRenditionBeforeShootCutInCharacterNumQueryService;

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
 * Integration tests for the {@Link MGachaRenditionBeforeShootCutInCharacterNumResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionBeforeShootCutInCharacterNumResourceIT {

    private static final Integer DEFAULT_IS_MANY_SSR = 1;
    private static final Integer UPDATED_IS_MANY_SSR = 2;

    private static final Integer DEFAULT_IS_SSR = 1;
    private static final Integer UPDATED_IS_SSR = 2;

    private static final Integer DEFAULT_PATTERN = 1;
    private static final Integer UPDATED_PATTERN = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Integer DEFAULT_NUM = 1;
    private static final Integer UPDATED_NUM = 2;

    @Autowired
    private MGachaRenditionBeforeShootCutInCharacterNumRepository mGachaRenditionBeforeShootCutInCharacterNumRepository;

    @Autowired
    private MGachaRenditionBeforeShootCutInCharacterNumMapper mGachaRenditionBeforeShootCutInCharacterNumMapper;

    @Autowired
    private MGachaRenditionBeforeShootCutInCharacterNumService mGachaRenditionBeforeShootCutInCharacterNumService;

    @Autowired
    private MGachaRenditionBeforeShootCutInCharacterNumQueryService mGachaRenditionBeforeShootCutInCharacterNumQueryService;

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

    private MockMvc restMGachaRenditionBeforeShootCutInCharacterNumMockMvc;

    private MGachaRenditionBeforeShootCutInCharacterNum mGachaRenditionBeforeShootCutInCharacterNum;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionBeforeShootCutInCharacterNumResource mGachaRenditionBeforeShootCutInCharacterNumResource = new MGachaRenditionBeforeShootCutInCharacterNumResource(mGachaRenditionBeforeShootCutInCharacterNumService, mGachaRenditionBeforeShootCutInCharacterNumQueryService);
        this.restMGachaRenditionBeforeShootCutInCharacterNumMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionBeforeShootCutInCharacterNumResource)
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
    public static MGachaRenditionBeforeShootCutInCharacterNum createEntity(EntityManager em) {
        MGachaRenditionBeforeShootCutInCharacterNum mGachaRenditionBeforeShootCutInCharacterNum = new MGachaRenditionBeforeShootCutInCharacterNum()
            .isManySsr(DEFAULT_IS_MANY_SSR)
            .isSsr(DEFAULT_IS_SSR)
            .pattern(DEFAULT_PATTERN)
            .weight(DEFAULT_WEIGHT)
            .num(DEFAULT_NUM);
        return mGachaRenditionBeforeShootCutInCharacterNum;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionBeforeShootCutInCharacterNum createUpdatedEntity(EntityManager em) {
        MGachaRenditionBeforeShootCutInCharacterNum mGachaRenditionBeforeShootCutInCharacterNum = new MGachaRenditionBeforeShootCutInCharacterNum()
            .isManySsr(UPDATED_IS_MANY_SSR)
            .isSsr(UPDATED_IS_SSR)
            .pattern(UPDATED_PATTERN)
            .weight(UPDATED_WEIGHT)
            .num(UPDATED_NUM);
        return mGachaRenditionBeforeShootCutInCharacterNum;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionBeforeShootCutInCharacterNum = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionBeforeShootCutInCharacterNum() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();

        // Create the MGachaRenditionBeforeShootCutInCharacterNum
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNum);
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-character-nums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInCharacterNumDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionBeforeShootCutInCharacterNum in the database
        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionBeforeShootCutInCharacterNum testMGachaRenditionBeforeShootCutInCharacterNum = mGachaRenditionBeforeShootCutInCharacterNumList.get(mGachaRenditionBeforeShootCutInCharacterNumList.size() - 1);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getIsManySsr()).isEqualTo(DEFAULT_IS_MANY_SSR);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getIsSsr()).isEqualTo(DEFAULT_IS_SSR);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getPattern()).isEqualTo(DEFAULT_PATTERN);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getNum()).isEqualTo(DEFAULT_NUM);
    }

    @Test
    @Transactional
    public void createMGachaRenditionBeforeShootCutInCharacterNumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();

        // Create the MGachaRenditionBeforeShootCutInCharacterNum with an existing ID
        mGachaRenditionBeforeShootCutInCharacterNum.setId(1L);
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNum);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-character-nums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInCharacterNumDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionBeforeShootCutInCharacterNum in the database
        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIsManySsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInCharacterNum.setIsManySsr(null);

        // Create the MGachaRenditionBeforeShootCutInCharacterNum, which fails.
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNum);

        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-character-nums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInCharacterNumDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsSsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInCharacterNum.setIsSsr(null);

        // Create the MGachaRenditionBeforeShootCutInCharacterNum, which fails.
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNum);

        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-character-nums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInCharacterNumDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPatternIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInCharacterNum.setPattern(null);

        // Create the MGachaRenditionBeforeShootCutInCharacterNum, which fails.
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNum);

        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-character-nums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInCharacterNumDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInCharacterNum.setWeight(null);

        // Create the MGachaRenditionBeforeShootCutInCharacterNum, which fails.
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNum);

        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-character-nums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInCharacterNumDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();
        // set the field null
        mGachaRenditionBeforeShootCutInCharacterNum.setNum(null);

        // Create the MGachaRenditionBeforeShootCutInCharacterNum, which fails.
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNum);

        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(post("/api/m-gacha-rendition-before-shoot-cut-in-character-nums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInCharacterNumDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNums() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-character-nums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionBeforeShootCutInCharacterNum.getId().intValue())))
            .andExpect(jsonPath("$.[*].isManySsr").value(hasItem(DEFAULT_IS_MANY_SSR)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].pattern").value(hasItem(DEFAULT_PATTERN)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionBeforeShootCutInCharacterNum() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get the mGachaRenditionBeforeShootCutInCharacterNum
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-character-nums/{id}", mGachaRenditionBeforeShootCutInCharacterNum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionBeforeShootCutInCharacterNum.getId().intValue()))
            .andExpect(jsonPath("$.isManySsr").value(DEFAULT_IS_MANY_SSR))
            .andExpect(jsonPath("$.isSsr").value(DEFAULT_IS_SSR))
            .andExpect(jsonPath("$.pattern").value(DEFAULT_PATTERN))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsManySsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr equals to DEFAULT_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isManySsr.equals=" + DEFAULT_IS_MANY_SSR);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isManySsr.equals=" + UPDATED_IS_MANY_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsManySsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr in DEFAULT_IS_MANY_SSR or UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isManySsr.in=" + DEFAULT_IS_MANY_SSR + "," + UPDATED_IS_MANY_SSR);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isManySsr.in=" + UPDATED_IS_MANY_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsManySsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr is not null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isManySsr.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr is null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isManySsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsManySsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr greater than or equals to DEFAULT_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isManySsr.greaterOrEqualThan=" + DEFAULT_IS_MANY_SSR);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr greater than or equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isManySsr.greaterOrEqualThan=" + UPDATED_IS_MANY_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsManySsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr less than or equals to DEFAULT_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isManySsr.lessThan=" + DEFAULT_IS_MANY_SSR);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isManySsr less than or equals to UPDATED_IS_MANY_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isManySsr.lessThan=" + UPDATED_IS_MANY_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsSsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr equals to DEFAULT_IS_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isSsr.equals=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isSsr.equals=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsSsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr in DEFAULT_IS_SSR or UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isSsr.in=" + DEFAULT_IS_SSR + "," + UPDATED_IS_SSR);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isSsr.in=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsSsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr is not null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isSsr.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr is null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isSsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsSsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr greater than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isSsr.greaterOrEqualThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr greater than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isSsr.greaterOrEqualThan=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByIsSsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr less than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("isSsr.lessThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where isSsr less than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("isSsr.lessThan=" + UPDATED_IS_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByPatternIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern equals to DEFAULT_PATTERN
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("pattern.equals=" + DEFAULT_PATTERN);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern equals to UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("pattern.equals=" + UPDATED_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByPatternIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern in DEFAULT_PATTERN or UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("pattern.in=" + DEFAULT_PATTERN + "," + UPDATED_PATTERN);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern equals to UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("pattern.in=" + UPDATED_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByPatternIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern is not null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("pattern.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern is null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("pattern.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByPatternIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern greater than or equals to DEFAULT_PATTERN
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("pattern.greaterOrEqualThan=" + DEFAULT_PATTERN);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern greater than or equals to UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("pattern.greaterOrEqualThan=" + UPDATED_PATTERN);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByPatternIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern less than or equals to DEFAULT_PATTERN
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("pattern.lessThan=" + DEFAULT_PATTERN);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where pattern less than or equals to UPDATED_PATTERN
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("pattern.lessThan=" + UPDATED_PATTERN);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight is not null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight is null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num equals to DEFAULT_NUM
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("num.equals=" + DEFAULT_NUM);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num equals to UPDATED_NUM
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("num.equals=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByNumIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num in DEFAULT_NUM or UPDATED_NUM
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("num.in=" + DEFAULT_NUM + "," + UPDATED_NUM);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num equals to UPDATED_NUM
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("num.in=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num is not null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("num.specified=true");

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num is null
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("num.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num greater than or equals to DEFAULT_NUM
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("num.greaterOrEqualThan=" + DEFAULT_NUM);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num greater than or equals to UPDATED_NUM
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("num.greaterOrEqualThan=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionBeforeShootCutInCharacterNumsByNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num less than or equals to DEFAULT_NUM
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound("num.lessThan=" + DEFAULT_NUM);

        // Get all the mGachaRenditionBeforeShootCutInCharacterNumList where num less than or equals to UPDATED_NUM
        defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound("num.lessThan=" + UPDATED_NUM);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionBeforeShootCutInCharacterNumShouldBeFound(String filter) throws Exception {
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-character-nums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionBeforeShootCutInCharacterNum.getId().intValue())))
            .andExpect(jsonPath("$.[*].isManySsr").value(hasItem(DEFAULT_IS_MANY_SSR)))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].pattern").value(hasItem(DEFAULT_PATTERN)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)));

        // Check, that the count call also returns 1
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-character-nums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionBeforeShootCutInCharacterNumShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-character-nums?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-character-nums/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionBeforeShootCutInCharacterNum() throws Exception {
        // Get the mGachaRenditionBeforeShootCutInCharacterNum
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(get("/api/m-gacha-rendition-before-shoot-cut-in-character-nums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionBeforeShootCutInCharacterNum() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        int databaseSizeBeforeUpdate = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();

        // Update the mGachaRenditionBeforeShootCutInCharacterNum
        MGachaRenditionBeforeShootCutInCharacterNum updatedMGachaRenditionBeforeShootCutInCharacterNum = mGachaRenditionBeforeShootCutInCharacterNumRepository.findById(mGachaRenditionBeforeShootCutInCharacterNum.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionBeforeShootCutInCharacterNum are not directly saved in db
        em.detach(updatedMGachaRenditionBeforeShootCutInCharacterNum);
        updatedMGachaRenditionBeforeShootCutInCharacterNum
            .isManySsr(UPDATED_IS_MANY_SSR)
            .isSsr(UPDATED_IS_SSR)
            .pattern(UPDATED_PATTERN)
            .weight(UPDATED_WEIGHT)
            .num(UPDATED_NUM);
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(updatedMGachaRenditionBeforeShootCutInCharacterNum);

        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(put("/api/m-gacha-rendition-before-shoot-cut-in-character-nums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInCharacterNumDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionBeforeShootCutInCharacterNum in the database
        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionBeforeShootCutInCharacterNum testMGachaRenditionBeforeShootCutInCharacterNum = mGachaRenditionBeforeShootCutInCharacterNumList.get(mGachaRenditionBeforeShootCutInCharacterNumList.size() - 1);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getIsManySsr()).isEqualTo(UPDATED_IS_MANY_SSR);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getIsSsr()).isEqualTo(UPDATED_IS_SSR);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getPattern()).isEqualTo(UPDATED_PATTERN);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionBeforeShootCutInCharacterNum.getNum()).isEqualTo(UPDATED_NUM);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionBeforeShootCutInCharacterNum() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();

        // Create the MGachaRenditionBeforeShootCutInCharacterNum
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO = mGachaRenditionBeforeShootCutInCharacterNumMapper.toDto(mGachaRenditionBeforeShootCutInCharacterNum);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(put("/api/m-gacha-rendition-before-shoot-cut-in-character-nums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionBeforeShootCutInCharacterNumDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionBeforeShootCutInCharacterNum in the database
        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionBeforeShootCutInCharacterNum() throws Exception {
        // Initialize the database
        mGachaRenditionBeforeShootCutInCharacterNumRepository.saveAndFlush(mGachaRenditionBeforeShootCutInCharacterNum);

        int databaseSizeBeforeDelete = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll().size();

        // Delete the mGachaRenditionBeforeShootCutInCharacterNum
        restMGachaRenditionBeforeShootCutInCharacterNumMockMvc.perform(delete("/api/m-gacha-rendition-before-shoot-cut-in-character-nums/{id}", mGachaRenditionBeforeShootCutInCharacterNum.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionBeforeShootCutInCharacterNum> mGachaRenditionBeforeShootCutInCharacterNumList = mGachaRenditionBeforeShootCutInCharacterNumRepository.findAll();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionBeforeShootCutInCharacterNum.class);
        MGachaRenditionBeforeShootCutInCharacterNum mGachaRenditionBeforeShootCutInCharacterNum1 = new MGachaRenditionBeforeShootCutInCharacterNum();
        mGachaRenditionBeforeShootCutInCharacterNum1.setId(1L);
        MGachaRenditionBeforeShootCutInCharacterNum mGachaRenditionBeforeShootCutInCharacterNum2 = new MGachaRenditionBeforeShootCutInCharacterNum();
        mGachaRenditionBeforeShootCutInCharacterNum2.setId(mGachaRenditionBeforeShootCutInCharacterNum1.getId());
        assertThat(mGachaRenditionBeforeShootCutInCharacterNum1).isEqualTo(mGachaRenditionBeforeShootCutInCharacterNum2);
        mGachaRenditionBeforeShootCutInCharacterNum2.setId(2L);
        assertThat(mGachaRenditionBeforeShootCutInCharacterNum1).isNotEqualTo(mGachaRenditionBeforeShootCutInCharacterNum2);
        mGachaRenditionBeforeShootCutInCharacterNum1.setId(null);
        assertThat(mGachaRenditionBeforeShootCutInCharacterNum1).isNotEqualTo(mGachaRenditionBeforeShootCutInCharacterNum2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionBeforeShootCutInCharacterNumDTO.class);
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO1 = new MGachaRenditionBeforeShootCutInCharacterNumDTO();
        mGachaRenditionBeforeShootCutInCharacterNumDTO1.setId(1L);
        MGachaRenditionBeforeShootCutInCharacterNumDTO mGachaRenditionBeforeShootCutInCharacterNumDTO2 = new MGachaRenditionBeforeShootCutInCharacterNumDTO();
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumDTO1).isNotEqualTo(mGachaRenditionBeforeShootCutInCharacterNumDTO2);
        mGachaRenditionBeforeShootCutInCharacterNumDTO2.setId(mGachaRenditionBeforeShootCutInCharacterNumDTO1.getId());
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumDTO1).isEqualTo(mGachaRenditionBeforeShootCutInCharacterNumDTO2);
        mGachaRenditionBeforeShootCutInCharacterNumDTO2.setId(2L);
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumDTO1).isNotEqualTo(mGachaRenditionBeforeShootCutInCharacterNumDTO2);
        mGachaRenditionBeforeShootCutInCharacterNumDTO1.setId(null);
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumDTO1).isNotEqualTo(mGachaRenditionBeforeShootCutInCharacterNumDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionBeforeShootCutInCharacterNumMapper.fromId(null)).isNull();
    }
}
