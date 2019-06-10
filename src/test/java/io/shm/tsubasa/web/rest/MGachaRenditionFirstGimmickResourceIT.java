package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRenditionFirstGimmick;
import io.shm.tsubasa.repository.MGachaRenditionFirstGimmickRepository;
import io.shm.tsubasa.service.MGachaRenditionFirstGimmickService;
import io.shm.tsubasa.service.dto.MGachaRenditionFirstGimmickDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionFirstGimmickMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionFirstGimmickCriteria;
import io.shm.tsubasa.service.MGachaRenditionFirstGimmickQueryService;

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
 * Integration tests for the {@Link MGachaRenditionFirstGimmickResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionFirstGimmickResourceIT {

    private static final Integer DEFAULT_IS_SSR = 1;
    private static final Integer UPDATED_IS_SSR = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final Integer DEFAULT_BIRD_NUM = 1;
    private static final Integer UPDATED_BIRD_NUM = 2;

    private static final Integer DEFAULT_IS_TICKER_TAPE = 1;
    private static final Integer UPDATED_IS_TICKER_TAPE = 2;

    @Autowired
    private MGachaRenditionFirstGimmickRepository mGachaRenditionFirstGimmickRepository;

    @Autowired
    private MGachaRenditionFirstGimmickMapper mGachaRenditionFirstGimmickMapper;

    @Autowired
    private MGachaRenditionFirstGimmickService mGachaRenditionFirstGimmickService;

    @Autowired
    private MGachaRenditionFirstGimmickQueryService mGachaRenditionFirstGimmickQueryService;

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

    private MockMvc restMGachaRenditionFirstGimmickMockMvc;

    private MGachaRenditionFirstGimmick mGachaRenditionFirstGimmick;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionFirstGimmickResource mGachaRenditionFirstGimmickResource = new MGachaRenditionFirstGimmickResource(mGachaRenditionFirstGimmickService, mGachaRenditionFirstGimmickQueryService);
        this.restMGachaRenditionFirstGimmickMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionFirstGimmickResource)
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
    public static MGachaRenditionFirstGimmick createEntity(EntityManager em) {
        MGachaRenditionFirstGimmick mGachaRenditionFirstGimmick = new MGachaRenditionFirstGimmick()
            .isSsr(DEFAULT_IS_SSR)
            .weight(DEFAULT_WEIGHT)
            .birdNum(DEFAULT_BIRD_NUM)
            .isTickerTape(DEFAULT_IS_TICKER_TAPE);
        return mGachaRenditionFirstGimmick;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRenditionFirstGimmick createUpdatedEntity(EntityManager em) {
        MGachaRenditionFirstGimmick mGachaRenditionFirstGimmick = new MGachaRenditionFirstGimmick()
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .birdNum(UPDATED_BIRD_NUM)
            .isTickerTape(UPDATED_IS_TICKER_TAPE);
        return mGachaRenditionFirstGimmick;
    }

    @BeforeEach
    public void initTest() {
        mGachaRenditionFirstGimmick = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRenditionFirstGimmick() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionFirstGimmickRepository.findAll().size();

        // Create the MGachaRenditionFirstGimmick
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO = mGachaRenditionFirstGimmickMapper.toDto(mGachaRenditionFirstGimmick);
        restMGachaRenditionFirstGimmickMockMvc.perform(post("/api/m-gacha-rendition-first-gimmicks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionFirstGimmickDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRenditionFirstGimmick in the database
        List<MGachaRenditionFirstGimmick> mGachaRenditionFirstGimmickList = mGachaRenditionFirstGimmickRepository.findAll();
        assertThat(mGachaRenditionFirstGimmickList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRenditionFirstGimmick testMGachaRenditionFirstGimmick = mGachaRenditionFirstGimmickList.get(mGachaRenditionFirstGimmickList.size() - 1);
        assertThat(testMGachaRenditionFirstGimmick.getIsSsr()).isEqualTo(DEFAULT_IS_SSR);
        assertThat(testMGachaRenditionFirstGimmick.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMGachaRenditionFirstGimmick.getBirdNum()).isEqualTo(DEFAULT_BIRD_NUM);
        assertThat(testMGachaRenditionFirstGimmick.getIsTickerTape()).isEqualTo(DEFAULT_IS_TICKER_TAPE);
    }

    @Test
    @Transactional
    public void createMGachaRenditionFirstGimmickWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionFirstGimmickRepository.findAll().size();

        // Create the MGachaRenditionFirstGimmick with an existing ID
        mGachaRenditionFirstGimmick.setId(1L);
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO = mGachaRenditionFirstGimmickMapper.toDto(mGachaRenditionFirstGimmick);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionFirstGimmickMockMvc.perform(post("/api/m-gacha-rendition-first-gimmicks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionFirstGimmickDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionFirstGimmick in the database
        List<MGachaRenditionFirstGimmick> mGachaRenditionFirstGimmickList = mGachaRenditionFirstGimmickRepository.findAll();
        assertThat(mGachaRenditionFirstGimmickList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIsSsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionFirstGimmickRepository.findAll().size();
        // set the field null
        mGachaRenditionFirstGimmick.setIsSsr(null);

        // Create the MGachaRenditionFirstGimmick, which fails.
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO = mGachaRenditionFirstGimmickMapper.toDto(mGachaRenditionFirstGimmick);

        restMGachaRenditionFirstGimmickMockMvc.perform(post("/api/m-gacha-rendition-first-gimmicks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionFirstGimmickDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionFirstGimmick> mGachaRenditionFirstGimmickList = mGachaRenditionFirstGimmickRepository.findAll();
        assertThat(mGachaRenditionFirstGimmickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionFirstGimmickRepository.findAll().size();
        // set the field null
        mGachaRenditionFirstGimmick.setWeight(null);

        // Create the MGachaRenditionFirstGimmick, which fails.
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO = mGachaRenditionFirstGimmickMapper.toDto(mGachaRenditionFirstGimmick);

        restMGachaRenditionFirstGimmickMockMvc.perform(post("/api/m-gacha-rendition-first-gimmicks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionFirstGimmickDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionFirstGimmick> mGachaRenditionFirstGimmickList = mGachaRenditionFirstGimmickRepository.findAll();
        assertThat(mGachaRenditionFirstGimmickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBirdNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionFirstGimmickRepository.findAll().size();
        // set the field null
        mGachaRenditionFirstGimmick.setBirdNum(null);

        // Create the MGachaRenditionFirstGimmick, which fails.
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO = mGachaRenditionFirstGimmickMapper.toDto(mGachaRenditionFirstGimmick);

        restMGachaRenditionFirstGimmickMockMvc.perform(post("/api/m-gacha-rendition-first-gimmicks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionFirstGimmickDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionFirstGimmick> mGachaRenditionFirstGimmickList = mGachaRenditionFirstGimmickRepository.findAll();
        assertThat(mGachaRenditionFirstGimmickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsTickerTapeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaRenditionFirstGimmickRepository.findAll().size();
        // set the field null
        mGachaRenditionFirstGimmick.setIsTickerTape(null);

        // Create the MGachaRenditionFirstGimmick, which fails.
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO = mGachaRenditionFirstGimmickMapper.toDto(mGachaRenditionFirstGimmick);

        restMGachaRenditionFirstGimmickMockMvc.perform(post("/api/m-gacha-rendition-first-gimmicks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionFirstGimmickDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaRenditionFirstGimmick> mGachaRenditionFirstGimmickList = mGachaRenditionFirstGimmickRepository.findAll();
        assertThat(mGachaRenditionFirstGimmickList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicks() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList
        restMGachaRenditionFirstGimmickMockMvc.perform(get("/api/m-gacha-rendition-first-gimmicks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionFirstGimmick.getId().intValue())))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].birdNum").value(hasItem(DEFAULT_BIRD_NUM)))
            .andExpect(jsonPath("$.[*].isTickerTape").value(hasItem(DEFAULT_IS_TICKER_TAPE)));
    }
    
    @Test
    @Transactional
    public void getMGachaRenditionFirstGimmick() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get the mGachaRenditionFirstGimmick
        restMGachaRenditionFirstGimmickMockMvc.perform(get("/api/m-gacha-rendition-first-gimmicks/{id}", mGachaRenditionFirstGimmick.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRenditionFirstGimmick.getId().intValue()))
            .andExpect(jsonPath("$.isSsr").value(DEFAULT_IS_SSR))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.birdNum").value(DEFAULT_BIRD_NUM))
            .andExpect(jsonPath("$.isTickerTape").value(DEFAULT_IS_TICKER_TAPE));
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsSsrIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isSsr equals to DEFAULT_IS_SSR
        defaultMGachaRenditionFirstGimmickShouldBeFound("isSsr.equals=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionFirstGimmickList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isSsr.equals=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsSsrIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isSsr in DEFAULT_IS_SSR or UPDATED_IS_SSR
        defaultMGachaRenditionFirstGimmickShouldBeFound("isSsr.in=" + DEFAULT_IS_SSR + "," + UPDATED_IS_SSR);

        // Get all the mGachaRenditionFirstGimmickList where isSsr equals to UPDATED_IS_SSR
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isSsr.in=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsSsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isSsr is not null
        defaultMGachaRenditionFirstGimmickShouldBeFound("isSsr.specified=true");

        // Get all the mGachaRenditionFirstGimmickList where isSsr is null
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isSsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsSsrIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isSsr greater than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionFirstGimmickShouldBeFound("isSsr.greaterOrEqualThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionFirstGimmickList where isSsr greater than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isSsr.greaterOrEqualThan=" + UPDATED_IS_SSR);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsSsrIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isSsr less than or equals to DEFAULT_IS_SSR
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isSsr.lessThan=" + DEFAULT_IS_SSR);

        // Get all the mGachaRenditionFirstGimmickList where isSsr less than or equals to UPDATED_IS_SSR
        defaultMGachaRenditionFirstGimmickShouldBeFound("isSsr.lessThan=" + UPDATED_IS_SSR);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where weight equals to DEFAULT_WEIGHT
        defaultMGachaRenditionFirstGimmickShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionFirstGimmickList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMGachaRenditionFirstGimmickShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mGachaRenditionFirstGimmickList where weight equals to UPDATED_WEIGHT
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where weight is not null
        defaultMGachaRenditionFirstGimmickShouldBeFound("weight.specified=true");

        // Get all the mGachaRenditionFirstGimmickList where weight is null
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionFirstGimmickShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionFirstGimmickList where weight greater than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where weight less than or equals to DEFAULT_WEIGHT
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mGachaRenditionFirstGimmickList where weight less than or equals to UPDATED_WEIGHT
        defaultMGachaRenditionFirstGimmickShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByBirdNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where birdNum equals to DEFAULT_BIRD_NUM
        defaultMGachaRenditionFirstGimmickShouldBeFound("birdNum.equals=" + DEFAULT_BIRD_NUM);

        // Get all the mGachaRenditionFirstGimmickList where birdNum equals to UPDATED_BIRD_NUM
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("birdNum.equals=" + UPDATED_BIRD_NUM);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByBirdNumIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where birdNum in DEFAULT_BIRD_NUM or UPDATED_BIRD_NUM
        defaultMGachaRenditionFirstGimmickShouldBeFound("birdNum.in=" + DEFAULT_BIRD_NUM + "," + UPDATED_BIRD_NUM);

        // Get all the mGachaRenditionFirstGimmickList where birdNum equals to UPDATED_BIRD_NUM
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("birdNum.in=" + UPDATED_BIRD_NUM);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByBirdNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where birdNum is not null
        defaultMGachaRenditionFirstGimmickShouldBeFound("birdNum.specified=true");

        // Get all the mGachaRenditionFirstGimmickList where birdNum is null
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("birdNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByBirdNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where birdNum greater than or equals to DEFAULT_BIRD_NUM
        defaultMGachaRenditionFirstGimmickShouldBeFound("birdNum.greaterOrEqualThan=" + DEFAULT_BIRD_NUM);

        // Get all the mGachaRenditionFirstGimmickList where birdNum greater than or equals to UPDATED_BIRD_NUM
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("birdNum.greaterOrEqualThan=" + UPDATED_BIRD_NUM);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByBirdNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where birdNum less than or equals to DEFAULT_BIRD_NUM
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("birdNum.lessThan=" + DEFAULT_BIRD_NUM);

        // Get all the mGachaRenditionFirstGimmickList where birdNum less than or equals to UPDATED_BIRD_NUM
        defaultMGachaRenditionFirstGimmickShouldBeFound("birdNum.lessThan=" + UPDATED_BIRD_NUM);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsTickerTapeIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape equals to DEFAULT_IS_TICKER_TAPE
        defaultMGachaRenditionFirstGimmickShouldBeFound("isTickerTape.equals=" + DEFAULT_IS_TICKER_TAPE);

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape equals to UPDATED_IS_TICKER_TAPE
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isTickerTape.equals=" + UPDATED_IS_TICKER_TAPE);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsTickerTapeIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape in DEFAULT_IS_TICKER_TAPE or UPDATED_IS_TICKER_TAPE
        defaultMGachaRenditionFirstGimmickShouldBeFound("isTickerTape.in=" + DEFAULT_IS_TICKER_TAPE + "," + UPDATED_IS_TICKER_TAPE);

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape equals to UPDATED_IS_TICKER_TAPE
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isTickerTape.in=" + UPDATED_IS_TICKER_TAPE);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsTickerTapeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape is not null
        defaultMGachaRenditionFirstGimmickShouldBeFound("isTickerTape.specified=true");

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape is null
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isTickerTape.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsTickerTapeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape greater than or equals to DEFAULT_IS_TICKER_TAPE
        defaultMGachaRenditionFirstGimmickShouldBeFound("isTickerTape.greaterOrEqualThan=" + DEFAULT_IS_TICKER_TAPE);

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape greater than or equals to UPDATED_IS_TICKER_TAPE
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isTickerTape.greaterOrEqualThan=" + UPDATED_IS_TICKER_TAPE);
    }

    @Test
    @Transactional
    public void getAllMGachaRenditionFirstGimmicksByIsTickerTapeIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape less than or equals to DEFAULT_IS_TICKER_TAPE
        defaultMGachaRenditionFirstGimmickShouldNotBeFound("isTickerTape.lessThan=" + DEFAULT_IS_TICKER_TAPE);

        // Get all the mGachaRenditionFirstGimmickList where isTickerTape less than or equals to UPDATED_IS_TICKER_TAPE
        defaultMGachaRenditionFirstGimmickShouldBeFound("isTickerTape.lessThan=" + UPDATED_IS_TICKER_TAPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionFirstGimmickShouldBeFound(String filter) throws Exception {
        restMGachaRenditionFirstGimmickMockMvc.perform(get("/api/m-gacha-rendition-first-gimmicks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRenditionFirstGimmick.getId().intValue())))
            .andExpect(jsonPath("$.[*].isSsr").value(hasItem(DEFAULT_IS_SSR)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].birdNum").value(hasItem(DEFAULT_BIRD_NUM)))
            .andExpect(jsonPath("$.[*].isTickerTape").value(hasItem(DEFAULT_IS_TICKER_TAPE)));

        // Check, that the count call also returns 1
        restMGachaRenditionFirstGimmickMockMvc.perform(get("/api/m-gacha-rendition-first-gimmicks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionFirstGimmickShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionFirstGimmickMockMvc.perform(get("/api/m-gacha-rendition-first-gimmicks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionFirstGimmickMockMvc.perform(get("/api/m-gacha-rendition-first-gimmicks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRenditionFirstGimmick() throws Exception {
        // Get the mGachaRenditionFirstGimmick
        restMGachaRenditionFirstGimmickMockMvc.perform(get("/api/m-gacha-rendition-first-gimmicks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRenditionFirstGimmick() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        int databaseSizeBeforeUpdate = mGachaRenditionFirstGimmickRepository.findAll().size();

        // Update the mGachaRenditionFirstGimmick
        MGachaRenditionFirstGimmick updatedMGachaRenditionFirstGimmick = mGachaRenditionFirstGimmickRepository.findById(mGachaRenditionFirstGimmick.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRenditionFirstGimmick are not directly saved in db
        em.detach(updatedMGachaRenditionFirstGimmick);
        updatedMGachaRenditionFirstGimmick
            .isSsr(UPDATED_IS_SSR)
            .weight(UPDATED_WEIGHT)
            .birdNum(UPDATED_BIRD_NUM)
            .isTickerTape(UPDATED_IS_TICKER_TAPE);
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO = mGachaRenditionFirstGimmickMapper.toDto(updatedMGachaRenditionFirstGimmick);

        restMGachaRenditionFirstGimmickMockMvc.perform(put("/api/m-gacha-rendition-first-gimmicks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionFirstGimmickDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRenditionFirstGimmick in the database
        List<MGachaRenditionFirstGimmick> mGachaRenditionFirstGimmickList = mGachaRenditionFirstGimmickRepository.findAll();
        assertThat(mGachaRenditionFirstGimmickList).hasSize(databaseSizeBeforeUpdate);
        MGachaRenditionFirstGimmick testMGachaRenditionFirstGimmick = mGachaRenditionFirstGimmickList.get(mGachaRenditionFirstGimmickList.size() - 1);
        assertThat(testMGachaRenditionFirstGimmick.getIsSsr()).isEqualTo(UPDATED_IS_SSR);
        assertThat(testMGachaRenditionFirstGimmick.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMGachaRenditionFirstGimmick.getBirdNum()).isEqualTo(UPDATED_BIRD_NUM);
        assertThat(testMGachaRenditionFirstGimmick.getIsTickerTape()).isEqualTo(UPDATED_IS_TICKER_TAPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRenditionFirstGimmick() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionFirstGimmickRepository.findAll().size();

        // Create the MGachaRenditionFirstGimmick
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO = mGachaRenditionFirstGimmickMapper.toDto(mGachaRenditionFirstGimmick);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionFirstGimmickMockMvc.perform(put("/api/m-gacha-rendition-first-gimmicks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionFirstGimmickDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRenditionFirstGimmick in the database
        List<MGachaRenditionFirstGimmick> mGachaRenditionFirstGimmickList = mGachaRenditionFirstGimmickRepository.findAll();
        assertThat(mGachaRenditionFirstGimmickList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRenditionFirstGimmick() throws Exception {
        // Initialize the database
        mGachaRenditionFirstGimmickRepository.saveAndFlush(mGachaRenditionFirstGimmick);

        int databaseSizeBeforeDelete = mGachaRenditionFirstGimmickRepository.findAll().size();

        // Delete the mGachaRenditionFirstGimmick
        restMGachaRenditionFirstGimmickMockMvc.perform(delete("/api/m-gacha-rendition-first-gimmicks/{id}", mGachaRenditionFirstGimmick.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRenditionFirstGimmick> mGachaRenditionFirstGimmickList = mGachaRenditionFirstGimmickRepository.findAll();
        assertThat(mGachaRenditionFirstGimmickList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionFirstGimmick.class);
        MGachaRenditionFirstGimmick mGachaRenditionFirstGimmick1 = new MGachaRenditionFirstGimmick();
        mGachaRenditionFirstGimmick1.setId(1L);
        MGachaRenditionFirstGimmick mGachaRenditionFirstGimmick2 = new MGachaRenditionFirstGimmick();
        mGachaRenditionFirstGimmick2.setId(mGachaRenditionFirstGimmick1.getId());
        assertThat(mGachaRenditionFirstGimmick1).isEqualTo(mGachaRenditionFirstGimmick2);
        mGachaRenditionFirstGimmick2.setId(2L);
        assertThat(mGachaRenditionFirstGimmick1).isNotEqualTo(mGachaRenditionFirstGimmick2);
        mGachaRenditionFirstGimmick1.setId(null);
        assertThat(mGachaRenditionFirstGimmick1).isNotEqualTo(mGachaRenditionFirstGimmick2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionFirstGimmickDTO.class);
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO1 = new MGachaRenditionFirstGimmickDTO();
        mGachaRenditionFirstGimmickDTO1.setId(1L);
        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO2 = new MGachaRenditionFirstGimmickDTO();
        assertThat(mGachaRenditionFirstGimmickDTO1).isNotEqualTo(mGachaRenditionFirstGimmickDTO2);
        mGachaRenditionFirstGimmickDTO2.setId(mGachaRenditionFirstGimmickDTO1.getId());
        assertThat(mGachaRenditionFirstGimmickDTO1).isEqualTo(mGachaRenditionFirstGimmickDTO2);
        mGachaRenditionFirstGimmickDTO2.setId(2L);
        assertThat(mGachaRenditionFirstGimmickDTO1).isNotEqualTo(mGachaRenditionFirstGimmickDTO2);
        mGachaRenditionFirstGimmickDTO1.setId(null);
        assertThat(mGachaRenditionFirstGimmickDTO1).isNotEqualTo(mGachaRenditionFirstGimmickDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionFirstGimmickMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionFirstGimmickMapper.fromId(null)).isNull();
    }
}
