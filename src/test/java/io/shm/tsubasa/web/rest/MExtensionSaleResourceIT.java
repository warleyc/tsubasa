package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MExtensionSale;
import io.shm.tsubasa.repository.MExtensionSaleRepository;
import io.shm.tsubasa.service.MExtensionSaleService;
import io.shm.tsubasa.service.dto.MExtensionSaleDTO;
import io.shm.tsubasa.service.mapper.MExtensionSaleMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MExtensionSaleCriteria;
import io.shm.tsubasa.service.MExtensionSaleQueryService;

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
 * Integration tests for the {@Link MExtensionSaleResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MExtensionSaleResourceIT {

    private static final String DEFAULT_MENU_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MENU_MESSAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_ADD_EXTENSION = 1;
    private static final Integer UPDATED_ADD_EXTENSION = 2;

    @Autowired
    private MExtensionSaleRepository mExtensionSaleRepository;

    @Autowired
    private MExtensionSaleMapper mExtensionSaleMapper;

    @Autowired
    private MExtensionSaleService mExtensionSaleService;

    @Autowired
    private MExtensionSaleQueryService mExtensionSaleQueryService;

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

    private MockMvc restMExtensionSaleMockMvc;

    private MExtensionSale mExtensionSale;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MExtensionSaleResource mExtensionSaleResource = new MExtensionSaleResource(mExtensionSaleService, mExtensionSaleQueryService);
        this.restMExtensionSaleMockMvc = MockMvcBuilders.standaloneSetup(mExtensionSaleResource)
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
    public static MExtensionSale createEntity(EntityManager em) {
        MExtensionSale mExtensionSale = new MExtensionSale()
            .menuMessage(DEFAULT_MENU_MESSAGE)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT)
            .type(DEFAULT_TYPE)
            .addExtension(DEFAULT_ADD_EXTENSION);
        return mExtensionSale;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MExtensionSale createUpdatedEntity(EntityManager em) {
        MExtensionSale mExtensionSale = new MExtensionSale()
            .menuMessage(UPDATED_MENU_MESSAGE)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .type(UPDATED_TYPE)
            .addExtension(UPDATED_ADD_EXTENSION);
        return mExtensionSale;
    }

    @BeforeEach
    public void initTest() {
        mExtensionSale = createEntity(em);
    }

    @Test
    @Transactional
    public void createMExtensionSale() throws Exception {
        int databaseSizeBeforeCreate = mExtensionSaleRepository.findAll().size();

        // Create the MExtensionSale
        MExtensionSaleDTO mExtensionSaleDTO = mExtensionSaleMapper.toDto(mExtensionSale);
        restMExtensionSaleMockMvc.perform(post("/api/m-extension-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtensionSaleDTO)))
            .andExpect(status().isCreated());

        // Validate the MExtensionSale in the database
        List<MExtensionSale> mExtensionSaleList = mExtensionSaleRepository.findAll();
        assertThat(mExtensionSaleList).hasSize(databaseSizeBeforeCreate + 1);
        MExtensionSale testMExtensionSale = mExtensionSaleList.get(mExtensionSaleList.size() - 1);
        assertThat(testMExtensionSale.getMenuMessage()).isEqualTo(DEFAULT_MENU_MESSAGE);
        assertThat(testMExtensionSale.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMExtensionSale.getEndAt()).isEqualTo(DEFAULT_END_AT);
        assertThat(testMExtensionSale.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMExtensionSale.getAddExtension()).isEqualTo(DEFAULT_ADD_EXTENSION);
    }

    @Test
    @Transactional
    public void createMExtensionSaleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mExtensionSaleRepository.findAll().size();

        // Create the MExtensionSale with an existing ID
        mExtensionSale.setId(1L);
        MExtensionSaleDTO mExtensionSaleDTO = mExtensionSaleMapper.toDto(mExtensionSale);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMExtensionSaleMockMvc.perform(post("/api/m-extension-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtensionSaleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MExtensionSale in the database
        List<MExtensionSale> mExtensionSaleList = mExtensionSaleRepository.findAll();
        assertThat(mExtensionSaleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mExtensionSaleRepository.findAll().size();
        // set the field null
        mExtensionSale.setStartAt(null);

        // Create the MExtensionSale, which fails.
        MExtensionSaleDTO mExtensionSaleDTO = mExtensionSaleMapper.toDto(mExtensionSale);

        restMExtensionSaleMockMvc.perform(post("/api/m-extension-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtensionSaleDTO)))
            .andExpect(status().isBadRequest());

        List<MExtensionSale> mExtensionSaleList = mExtensionSaleRepository.findAll();
        assertThat(mExtensionSaleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mExtensionSaleRepository.findAll().size();
        // set the field null
        mExtensionSale.setEndAt(null);

        // Create the MExtensionSale, which fails.
        MExtensionSaleDTO mExtensionSaleDTO = mExtensionSaleMapper.toDto(mExtensionSale);

        restMExtensionSaleMockMvc.perform(post("/api/m-extension-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtensionSaleDTO)))
            .andExpect(status().isBadRequest());

        List<MExtensionSale> mExtensionSaleList = mExtensionSaleRepository.findAll();
        assertThat(mExtensionSaleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mExtensionSaleRepository.findAll().size();
        // set the field null
        mExtensionSale.setType(null);

        // Create the MExtensionSale, which fails.
        MExtensionSaleDTO mExtensionSaleDTO = mExtensionSaleMapper.toDto(mExtensionSale);

        restMExtensionSaleMockMvc.perform(post("/api/m-extension-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtensionSaleDTO)))
            .andExpect(status().isBadRequest());

        List<MExtensionSale> mExtensionSaleList = mExtensionSaleRepository.findAll();
        assertThat(mExtensionSaleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddExtensionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mExtensionSaleRepository.findAll().size();
        // set the field null
        mExtensionSale.setAddExtension(null);

        // Create the MExtensionSale, which fails.
        MExtensionSaleDTO mExtensionSaleDTO = mExtensionSaleMapper.toDto(mExtensionSale);

        restMExtensionSaleMockMvc.perform(post("/api/m-extension-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtensionSaleDTO)))
            .andExpect(status().isBadRequest());

        List<MExtensionSale> mExtensionSaleList = mExtensionSaleRepository.findAll();
        assertThat(mExtensionSaleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMExtensionSales() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList
        restMExtensionSaleMockMvc.perform(get("/api/m-extension-sales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mExtensionSale.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuMessage").value(hasItem(DEFAULT_MENU_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].addExtension").value(hasItem(DEFAULT_ADD_EXTENSION)));
    }
    
    @Test
    @Transactional
    public void getMExtensionSale() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get the mExtensionSale
        restMExtensionSaleMockMvc.perform(get("/api/m-extension-sales/{id}", mExtensionSale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mExtensionSale.getId().intValue()))
            .andExpect(jsonPath("$.menuMessage").value(DEFAULT_MENU_MESSAGE.toString()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.addExtension").value(DEFAULT_ADD_EXTENSION));
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where startAt equals to DEFAULT_START_AT
        defaultMExtensionSaleShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mExtensionSaleList where startAt equals to UPDATED_START_AT
        defaultMExtensionSaleShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMExtensionSaleShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mExtensionSaleList where startAt equals to UPDATED_START_AT
        defaultMExtensionSaleShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where startAt is not null
        defaultMExtensionSaleShouldBeFound("startAt.specified=true");

        // Get all the mExtensionSaleList where startAt is null
        defaultMExtensionSaleShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where startAt greater than or equals to DEFAULT_START_AT
        defaultMExtensionSaleShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mExtensionSaleList where startAt greater than or equals to UPDATED_START_AT
        defaultMExtensionSaleShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where startAt less than or equals to DEFAULT_START_AT
        defaultMExtensionSaleShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mExtensionSaleList where startAt less than or equals to UPDATED_START_AT
        defaultMExtensionSaleShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMExtensionSalesByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where endAt equals to DEFAULT_END_AT
        defaultMExtensionSaleShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mExtensionSaleList where endAt equals to UPDATED_END_AT
        defaultMExtensionSaleShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMExtensionSaleShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mExtensionSaleList where endAt equals to UPDATED_END_AT
        defaultMExtensionSaleShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where endAt is not null
        defaultMExtensionSaleShouldBeFound("endAt.specified=true");

        // Get all the mExtensionSaleList where endAt is null
        defaultMExtensionSaleShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where endAt greater than or equals to DEFAULT_END_AT
        defaultMExtensionSaleShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mExtensionSaleList where endAt greater than or equals to UPDATED_END_AT
        defaultMExtensionSaleShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where endAt less than or equals to DEFAULT_END_AT
        defaultMExtensionSaleShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mExtensionSaleList where endAt less than or equals to UPDATED_END_AT
        defaultMExtensionSaleShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }


    @Test
    @Transactional
    public void getAllMExtensionSalesByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where type equals to DEFAULT_TYPE
        defaultMExtensionSaleShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the mExtensionSaleList where type equals to UPDATED_TYPE
        defaultMExtensionSaleShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultMExtensionSaleShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the mExtensionSaleList where type equals to UPDATED_TYPE
        defaultMExtensionSaleShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where type is not null
        defaultMExtensionSaleShouldBeFound("type.specified=true");

        // Get all the mExtensionSaleList where type is null
        defaultMExtensionSaleShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where type greater than or equals to DEFAULT_TYPE
        defaultMExtensionSaleShouldBeFound("type.greaterOrEqualThan=" + DEFAULT_TYPE);

        // Get all the mExtensionSaleList where type greater than or equals to UPDATED_TYPE
        defaultMExtensionSaleShouldNotBeFound("type.greaterOrEqualThan=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where type less than or equals to DEFAULT_TYPE
        defaultMExtensionSaleShouldNotBeFound("type.lessThan=" + DEFAULT_TYPE);

        // Get all the mExtensionSaleList where type less than or equals to UPDATED_TYPE
        defaultMExtensionSaleShouldBeFound("type.lessThan=" + UPDATED_TYPE);
    }


    @Test
    @Transactional
    public void getAllMExtensionSalesByAddExtensionIsEqualToSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where addExtension equals to DEFAULT_ADD_EXTENSION
        defaultMExtensionSaleShouldBeFound("addExtension.equals=" + DEFAULT_ADD_EXTENSION);

        // Get all the mExtensionSaleList where addExtension equals to UPDATED_ADD_EXTENSION
        defaultMExtensionSaleShouldNotBeFound("addExtension.equals=" + UPDATED_ADD_EXTENSION);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByAddExtensionIsInShouldWork() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where addExtension in DEFAULT_ADD_EXTENSION or UPDATED_ADD_EXTENSION
        defaultMExtensionSaleShouldBeFound("addExtension.in=" + DEFAULT_ADD_EXTENSION + "," + UPDATED_ADD_EXTENSION);

        // Get all the mExtensionSaleList where addExtension equals to UPDATED_ADD_EXTENSION
        defaultMExtensionSaleShouldNotBeFound("addExtension.in=" + UPDATED_ADD_EXTENSION);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByAddExtensionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where addExtension is not null
        defaultMExtensionSaleShouldBeFound("addExtension.specified=true");

        // Get all the mExtensionSaleList where addExtension is null
        defaultMExtensionSaleShouldNotBeFound("addExtension.specified=false");
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByAddExtensionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where addExtension greater than or equals to DEFAULT_ADD_EXTENSION
        defaultMExtensionSaleShouldBeFound("addExtension.greaterOrEqualThan=" + DEFAULT_ADD_EXTENSION);

        // Get all the mExtensionSaleList where addExtension greater than or equals to UPDATED_ADD_EXTENSION
        defaultMExtensionSaleShouldNotBeFound("addExtension.greaterOrEqualThan=" + UPDATED_ADD_EXTENSION);
    }

    @Test
    @Transactional
    public void getAllMExtensionSalesByAddExtensionIsLessThanSomething() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        // Get all the mExtensionSaleList where addExtension less than or equals to DEFAULT_ADD_EXTENSION
        defaultMExtensionSaleShouldNotBeFound("addExtension.lessThan=" + DEFAULT_ADD_EXTENSION);

        // Get all the mExtensionSaleList where addExtension less than or equals to UPDATED_ADD_EXTENSION
        defaultMExtensionSaleShouldBeFound("addExtension.lessThan=" + UPDATED_ADD_EXTENSION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMExtensionSaleShouldBeFound(String filter) throws Exception {
        restMExtensionSaleMockMvc.perform(get("/api/m-extension-sales?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mExtensionSale.getId().intValue())))
            .andExpect(jsonPath("$.[*].menuMessage").value(hasItem(DEFAULT_MENU_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].addExtension").value(hasItem(DEFAULT_ADD_EXTENSION)));

        // Check, that the count call also returns 1
        restMExtensionSaleMockMvc.perform(get("/api/m-extension-sales/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMExtensionSaleShouldNotBeFound(String filter) throws Exception {
        restMExtensionSaleMockMvc.perform(get("/api/m-extension-sales?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMExtensionSaleMockMvc.perform(get("/api/m-extension-sales/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMExtensionSale() throws Exception {
        // Get the mExtensionSale
        restMExtensionSaleMockMvc.perform(get("/api/m-extension-sales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMExtensionSale() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        int databaseSizeBeforeUpdate = mExtensionSaleRepository.findAll().size();

        // Update the mExtensionSale
        MExtensionSale updatedMExtensionSale = mExtensionSaleRepository.findById(mExtensionSale.getId()).get();
        // Disconnect from session so that the updates on updatedMExtensionSale are not directly saved in db
        em.detach(updatedMExtensionSale);
        updatedMExtensionSale
            .menuMessage(UPDATED_MENU_MESSAGE)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .type(UPDATED_TYPE)
            .addExtension(UPDATED_ADD_EXTENSION);
        MExtensionSaleDTO mExtensionSaleDTO = mExtensionSaleMapper.toDto(updatedMExtensionSale);

        restMExtensionSaleMockMvc.perform(put("/api/m-extension-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtensionSaleDTO)))
            .andExpect(status().isOk());

        // Validate the MExtensionSale in the database
        List<MExtensionSale> mExtensionSaleList = mExtensionSaleRepository.findAll();
        assertThat(mExtensionSaleList).hasSize(databaseSizeBeforeUpdate);
        MExtensionSale testMExtensionSale = mExtensionSaleList.get(mExtensionSaleList.size() - 1);
        assertThat(testMExtensionSale.getMenuMessage()).isEqualTo(UPDATED_MENU_MESSAGE);
        assertThat(testMExtensionSale.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMExtensionSale.getEndAt()).isEqualTo(UPDATED_END_AT);
        assertThat(testMExtensionSale.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMExtensionSale.getAddExtension()).isEqualTo(UPDATED_ADD_EXTENSION);
    }

    @Test
    @Transactional
    public void updateNonExistingMExtensionSale() throws Exception {
        int databaseSizeBeforeUpdate = mExtensionSaleRepository.findAll().size();

        // Create the MExtensionSale
        MExtensionSaleDTO mExtensionSaleDTO = mExtensionSaleMapper.toDto(mExtensionSale);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMExtensionSaleMockMvc.perform(put("/api/m-extension-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtensionSaleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MExtensionSale in the database
        List<MExtensionSale> mExtensionSaleList = mExtensionSaleRepository.findAll();
        assertThat(mExtensionSaleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMExtensionSale() throws Exception {
        // Initialize the database
        mExtensionSaleRepository.saveAndFlush(mExtensionSale);

        int databaseSizeBeforeDelete = mExtensionSaleRepository.findAll().size();

        // Delete the mExtensionSale
        restMExtensionSaleMockMvc.perform(delete("/api/m-extension-sales/{id}", mExtensionSale.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MExtensionSale> mExtensionSaleList = mExtensionSaleRepository.findAll();
        assertThat(mExtensionSaleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MExtensionSale.class);
        MExtensionSale mExtensionSale1 = new MExtensionSale();
        mExtensionSale1.setId(1L);
        MExtensionSale mExtensionSale2 = new MExtensionSale();
        mExtensionSale2.setId(mExtensionSale1.getId());
        assertThat(mExtensionSale1).isEqualTo(mExtensionSale2);
        mExtensionSale2.setId(2L);
        assertThat(mExtensionSale1).isNotEqualTo(mExtensionSale2);
        mExtensionSale1.setId(null);
        assertThat(mExtensionSale1).isNotEqualTo(mExtensionSale2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MExtensionSaleDTO.class);
        MExtensionSaleDTO mExtensionSaleDTO1 = new MExtensionSaleDTO();
        mExtensionSaleDTO1.setId(1L);
        MExtensionSaleDTO mExtensionSaleDTO2 = new MExtensionSaleDTO();
        assertThat(mExtensionSaleDTO1).isNotEqualTo(mExtensionSaleDTO2);
        mExtensionSaleDTO2.setId(mExtensionSaleDTO1.getId());
        assertThat(mExtensionSaleDTO1).isEqualTo(mExtensionSaleDTO2);
        mExtensionSaleDTO2.setId(2L);
        assertThat(mExtensionSaleDTO1).isNotEqualTo(mExtensionSaleDTO2);
        mExtensionSaleDTO1.setId(null);
        assertThat(mExtensionSaleDTO1).isNotEqualTo(mExtensionSaleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mExtensionSaleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mExtensionSaleMapper.fromId(null)).isNull();
    }
}
