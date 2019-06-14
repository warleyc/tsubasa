package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MModelUniformUp;
import io.shm.tsubasa.repository.MModelUniformUpRepository;
import io.shm.tsubasa.service.MModelUniformUpService;
import io.shm.tsubasa.service.dto.MModelUniformUpDTO;
import io.shm.tsubasa.service.mapper.MModelUniformUpMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MModelUniformUpCriteria;
import io.shm.tsubasa.service.MModelUniformUpQueryService;

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
 * Integration tests for the {@Link MModelUniformUpResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MModelUniformUpResourceIT {

    private static final Integer DEFAULT_UNIFORM_UP_ID = 1;
    private static final Integer UPDATED_UNIFORM_UP_ID = 2;

    private static final Integer DEFAULT_DEFAULT_DRESSING_TYPE = 1;
    private static final Integer UPDATED_DEFAULT_DRESSING_TYPE = 2;

    private static final Integer DEFAULT_UNIFORM_MODEL = 1;
    private static final Integer UPDATED_UNIFORM_MODEL = 2;

    private static final Integer DEFAULT_UNIFORM_TEXTURE = 1;
    private static final Integer UPDATED_UNIFORM_TEXTURE = 2;

    private static final Integer DEFAULT_UNIFORM_NO_TEXTURE = 1;
    private static final Integer UPDATED_UNIFORM_NO_TEXTURE = 2;

    private static final String DEFAULT_DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_UNIFORM_NO_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_UNIFORM_NO_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_CHEST = 1;
    private static final Integer UPDATED_NUMBER_CHEST = 2;

    private static final Integer DEFAULT_NUMBER_BELLY = 1;
    private static final Integer UPDATED_NUMBER_BELLY = 2;

    private static final Integer DEFAULT_NUMBER_BACK = 1;
    private static final Integer UPDATED_NUMBER_BACK = 2;

    private static final Integer DEFAULT_UNIFORM_NO_POSITION_X = 1;
    private static final Integer UPDATED_UNIFORM_NO_POSITION_X = 2;

    private static final Integer DEFAULT_UNIFORM_NO_POSITION_Y = 1;
    private static final Integer UPDATED_UNIFORM_NO_POSITION_Y = 2;

    @Autowired
    private MModelUniformUpRepository mModelUniformUpRepository;

    @Autowired
    private MModelUniformUpMapper mModelUniformUpMapper;

    @Autowired
    private MModelUniformUpService mModelUniformUpService;

    @Autowired
    private MModelUniformUpQueryService mModelUniformUpQueryService;

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

    private MockMvc restMModelUniformUpMockMvc;

    private MModelUniformUp mModelUniformUp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MModelUniformUpResource mModelUniformUpResource = new MModelUniformUpResource(mModelUniformUpService, mModelUniformUpQueryService);
        this.restMModelUniformUpMockMvc = MockMvcBuilders.standaloneSetup(mModelUniformUpResource)
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
    public static MModelUniformUp createEntity(EntityManager em) {
        MModelUniformUp mModelUniformUp = new MModelUniformUp()
            .uniformUpId(DEFAULT_UNIFORM_UP_ID)
            .defaultDressingType(DEFAULT_DEFAULT_DRESSING_TYPE)
            .uniformModel(DEFAULT_UNIFORM_MODEL)
            .uniformTexture(DEFAULT_UNIFORM_TEXTURE)
            .uniformNoTexture(DEFAULT_UNIFORM_NO_TEXTURE)
            .defaultColor(DEFAULT_DEFAULT_COLOR)
            .uniformNoColor(DEFAULT_UNIFORM_NO_COLOR)
            .numberChest(DEFAULT_NUMBER_CHEST)
            .numberBelly(DEFAULT_NUMBER_BELLY)
            .numberBack(DEFAULT_NUMBER_BACK)
            .uniformNoPositionX(DEFAULT_UNIFORM_NO_POSITION_X)
            .uniformNoPositionY(DEFAULT_UNIFORM_NO_POSITION_Y);
        return mModelUniformUp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MModelUniformUp createUpdatedEntity(EntityManager em) {
        MModelUniformUp mModelUniformUp = new MModelUniformUp()
            .uniformUpId(UPDATED_UNIFORM_UP_ID)
            .defaultDressingType(UPDATED_DEFAULT_DRESSING_TYPE)
            .uniformModel(UPDATED_UNIFORM_MODEL)
            .uniformTexture(UPDATED_UNIFORM_TEXTURE)
            .uniformNoTexture(UPDATED_UNIFORM_NO_TEXTURE)
            .defaultColor(UPDATED_DEFAULT_COLOR)
            .uniformNoColor(UPDATED_UNIFORM_NO_COLOR)
            .numberChest(UPDATED_NUMBER_CHEST)
            .numberBelly(UPDATED_NUMBER_BELLY)
            .numberBack(UPDATED_NUMBER_BACK)
            .uniformNoPositionX(UPDATED_UNIFORM_NO_POSITION_X)
            .uniformNoPositionY(UPDATED_UNIFORM_NO_POSITION_Y);
        return mModelUniformUp;
    }

    @BeforeEach
    public void initTest() {
        mModelUniformUp = createEntity(em);
    }

    @Test
    @Transactional
    public void createMModelUniformUp() throws Exception {
        int databaseSizeBeforeCreate = mModelUniformUpRepository.findAll().size();

        // Create the MModelUniformUp
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);
        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isCreated());

        // Validate the MModelUniformUp in the database
        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeCreate + 1);
        MModelUniformUp testMModelUniformUp = mModelUniformUpList.get(mModelUniformUpList.size() - 1);
        assertThat(testMModelUniformUp.getUniformUpId()).isEqualTo(DEFAULT_UNIFORM_UP_ID);
        assertThat(testMModelUniformUp.getDefaultDressingType()).isEqualTo(DEFAULT_DEFAULT_DRESSING_TYPE);
        assertThat(testMModelUniformUp.getUniformModel()).isEqualTo(DEFAULT_UNIFORM_MODEL);
        assertThat(testMModelUniformUp.getUniformTexture()).isEqualTo(DEFAULT_UNIFORM_TEXTURE);
        assertThat(testMModelUniformUp.getUniformNoTexture()).isEqualTo(DEFAULT_UNIFORM_NO_TEXTURE);
        assertThat(testMModelUniformUp.getDefaultColor()).isEqualTo(DEFAULT_DEFAULT_COLOR);
        assertThat(testMModelUniformUp.getUniformNoColor()).isEqualTo(DEFAULT_UNIFORM_NO_COLOR);
        assertThat(testMModelUniformUp.getNumberChest()).isEqualTo(DEFAULT_NUMBER_CHEST);
        assertThat(testMModelUniformUp.getNumberBelly()).isEqualTo(DEFAULT_NUMBER_BELLY);
        assertThat(testMModelUniformUp.getNumberBack()).isEqualTo(DEFAULT_NUMBER_BACK);
        assertThat(testMModelUniformUp.getUniformNoPositionX()).isEqualTo(DEFAULT_UNIFORM_NO_POSITION_X);
        assertThat(testMModelUniformUp.getUniformNoPositionY()).isEqualTo(DEFAULT_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void createMModelUniformUpWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mModelUniformUpRepository.findAll().size();

        // Create the MModelUniformUp with an existing ID
        mModelUniformUp.setId(1L);
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelUniformUp in the database
        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUniformUpIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpRepository.findAll().size();
        // set the field null
        mModelUniformUp.setUniformUpId(null);

        // Create the MModelUniformUp, which fails.
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefaultDressingTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpRepository.findAll().size();
        // set the field null
        mModelUniformUp.setDefaultDressingType(null);

        // Create the MModelUniformUp, which fails.
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpRepository.findAll().size();
        // set the field null
        mModelUniformUp.setUniformModel(null);

        // Create the MModelUniformUp, which fails.
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformTextureIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpRepository.findAll().size();
        // set the field null
        mModelUniformUp.setUniformTexture(null);

        // Create the MModelUniformUp, which fails.
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformNoTextureIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpRepository.findAll().size();
        // set the field null
        mModelUniformUp.setUniformNoTexture(null);

        // Create the MModelUniformUp, which fails.
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberChestIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpRepository.findAll().size();
        // set the field null
        mModelUniformUp.setNumberChest(null);

        // Create the MModelUniformUp, which fails.
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberBellyIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpRepository.findAll().size();
        // set the field null
        mModelUniformUp.setNumberBelly(null);

        // Create the MModelUniformUp, which fails.
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberBackIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpRepository.findAll().size();
        // set the field null
        mModelUniformUp.setNumberBack(null);

        // Create the MModelUniformUp, which fails.
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        restMModelUniformUpMockMvc.perform(post("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUps() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList
        restMModelUniformUpMockMvc.perform(get("/api/m-model-uniform-ups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelUniformUp.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniformUpId").value(hasItem(DEFAULT_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].defaultDressingType").value(hasItem(DEFAULT_DEFAULT_DRESSING_TYPE)))
            .andExpect(jsonPath("$.[*].uniformModel").value(hasItem(DEFAULT_UNIFORM_MODEL)))
            .andExpect(jsonPath("$.[*].uniformTexture").value(hasItem(DEFAULT_UNIFORM_TEXTURE)))
            .andExpect(jsonPath("$.[*].uniformNoTexture").value(hasItem(DEFAULT_UNIFORM_NO_TEXTURE)))
            .andExpect(jsonPath("$.[*].defaultColor").value(hasItem(DEFAULT_DEFAULT_COLOR.toString())))
            .andExpect(jsonPath("$.[*].uniformNoColor").value(hasItem(DEFAULT_UNIFORM_NO_COLOR.toString())))
            .andExpect(jsonPath("$.[*].numberChest").value(hasItem(DEFAULT_NUMBER_CHEST)))
            .andExpect(jsonPath("$.[*].numberBelly").value(hasItem(DEFAULT_NUMBER_BELLY)))
            .andExpect(jsonPath("$.[*].numberBack").value(hasItem(DEFAULT_NUMBER_BACK)))
            .andExpect(jsonPath("$.[*].uniformNoPositionX").value(hasItem(DEFAULT_UNIFORM_NO_POSITION_X)))
            .andExpect(jsonPath("$.[*].uniformNoPositionY").value(hasItem(DEFAULT_UNIFORM_NO_POSITION_Y)));
    }
    
    @Test
    @Transactional
    public void getMModelUniformUp() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get the mModelUniformUp
        restMModelUniformUpMockMvc.perform(get("/api/m-model-uniform-ups/{id}", mModelUniformUp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mModelUniformUp.getId().intValue()))
            .andExpect(jsonPath("$.uniformUpId").value(DEFAULT_UNIFORM_UP_ID))
            .andExpect(jsonPath("$.defaultDressingType").value(DEFAULT_DEFAULT_DRESSING_TYPE))
            .andExpect(jsonPath("$.uniformModel").value(DEFAULT_UNIFORM_MODEL))
            .andExpect(jsonPath("$.uniformTexture").value(DEFAULT_UNIFORM_TEXTURE))
            .andExpect(jsonPath("$.uniformNoTexture").value(DEFAULT_UNIFORM_NO_TEXTURE))
            .andExpect(jsonPath("$.defaultColor").value(DEFAULT_DEFAULT_COLOR.toString()))
            .andExpect(jsonPath("$.uniformNoColor").value(DEFAULT_UNIFORM_NO_COLOR.toString()))
            .andExpect(jsonPath("$.numberChest").value(DEFAULT_NUMBER_CHEST))
            .andExpect(jsonPath("$.numberBelly").value(DEFAULT_NUMBER_BELLY))
            .andExpect(jsonPath("$.numberBack").value(DEFAULT_NUMBER_BACK))
            .andExpect(jsonPath("$.uniformNoPositionX").value(DEFAULT_UNIFORM_NO_POSITION_X))
            .andExpect(jsonPath("$.uniformNoPositionY").value(DEFAULT_UNIFORM_NO_POSITION_Y));
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformUpIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformUpId equals to DEFAULT_UNIFORM_UP_ID
        defaultMModelUniformUpShouldBeFound("uniformUpId.equals=" + DEFAULT_UNIFORM_UP_ID);

        // Get all the mModelUniformUpList where uniformUpId equals to UPDATED_UNIFORM_UP_ID
        defaultMModelUniformUpShouldNotBeFound("uniformUpId.equals=" + UPDATED_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformUpIdIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformUpId in DEFAULT_UNIFORM_UP_ID or UPDATED_UNIFORM_UP_ID
        defaultMModelUniformUpShouldBeFound("uniformUpId.in=" + DEFAULT_UNIFORM_UP_ID + "," + UPDATED_UNIFORM_UP_ID);

        // Get all the mModelUniformUpList where uniformUpId equals to UPDATED_UNIFORM_UP_ID
        defaultMModelUniformUpShouldNotBeFound("uniformUpId.in=" + UPDATED_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformUpIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformUpId is not null
        defaultMModelUniformUpShouldBeFound("uniformUpId.specified=true");

        // Get all the mModelUniformUpList where uniformUpId is null
        defaultMModelUniformUpShouldNotBeFound("uniformUpId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformUpIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformUpId greater than or equals to DEFAULT_UNIFORM_UP_ID
        defaultMModelUniformUpShouldBeFound("uniformUpId.greaterOrEqualThan=" + DEFAULT_UNIFORM_UP_ID);

        // Get all the mModelUniformUpList where uniformUpId greater than or equals to UPDATED_UNIFORM_UP_ID
        defaultMModelUniformUpShouldNotBeFound("uniformUpId.greaterOrEqualThan=" + UPDATED_UNIFORM_UP_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformUpIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformUpId less than or equals to DEFAULT_UNIFORM_UP_ID
        defaultMModelUniformUpShouldNotBeFound("uniformUpId.lessThan=" + DEFAULT_UNIFORM_UP_ID);

        // Get all the mModelUniformUpList where uniformUpId less than or equals to UPDATED_UNIFORM_UP_ID
        defaultMModelUniformUpShouldBeFound("uniformUpId.lessThan=" + UPDATED_UNIFORM_UP_ID);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpsByDefaultDressingTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where defaultDressingType equals to DEFAULT_DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpShouldBeFound("defaultDressingType.equals=" + DEFAULT_DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformUpList where defaultDressingType equals to UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpShouldNotBeFound("defaultDressingType.equals=" + UPDATED_DEFAULT_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByDefaultDressingTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where defaultDressingType in DEFAULT_DEFAULT_DRESSING_TYPE or UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpShouldBeFound("defaultDressingType.in=" + DEFAULT_DEFAULT_DRESSING_TYPE + "," + UPDATED_DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformUpList where defaultDressingType equals to UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpShouldNotBeFound("defaultDressingType.in=" + UPDATED_DEFAULT_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByDefaultDressingTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where defaultDressingType is not null
        defaultMModelUniformUpShouldBeFound("defaultDressingType.specified=true");

        // Get all the mModelUniformUpList where defaultDressingType is null
        defaultMModelUniformUpShouldNotBeFound("defaultDressingType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByDefaultDressingTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where defaultDressingType greater than or equals to DEFAULT_DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpShouldBeFound("defaultDressingType.greaterOrEqualThan=" + DEFAULT_DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformUpList where defaultDressingType greater than or equals to UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpShouldNotBeFound("defaultDressingType.greaterOrEqualThan=" + UPDATED_DEFAULT_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByDefaultDressingTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where defaultDressingType less than or equals to DEFAULT_DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpShouldNotBeFound("defaultDressingType.lessThan=" + DEFAULT_DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformUpList where defaultDressingType less than or equals to UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpShouldBeFound("defaultDressingType.lessThan=" + UPDATED_DEFAULT_DRESSING_TYPE);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformModelIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformModel equals to DEFAULT_UNIFORM_MODEL
        defaultMModelUniformUpShouldBeFound("uniformModel.equals=" + DEFAULT_UNIFORM_MODEL);

        // Get all the mModelUniformUpList where uniformModel equals to UPDATED_UNIFORM_MODEL
        defaultMModelUniformUpShouldNotBeFound("uniformModel.equals=" + UPDATED_UNIFORM_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformModelIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformModel in DEFAULT_UNIFORM_MODEL or UPDATED_UNIFORM_MODEL
        defaultMModelUniformUpShouldBeFound("uniformModel.in=" + DEFAULT_UNIFORM_MODEL + "," + UPDATED_UNIFORM_MODEL);

        // Get all the mModelUniformUpList where uniformModel equals to UPDATED_UNIFORM_MODEL
        defaultMModelUniformUpShouldNotBeFound("uniformModel.in=" + UPDATED_UNIFORM_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformModelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformModel is not null
        defaultMModelUniformUpShouldBeFound("uniformModel.specified=true");

        // Get all the mModelUniformUpList where uniformModel is null
        defaultMModelUniformUpShouldNotBeFound("uniformModel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformModelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformModel greater than or equals to DEFAULT_UNIFORM_MODEL
        defaultMModelUniformUpShouldBeFound("uniformModel.greaterOrEqualThan=" + DEFAULT_UNIFORM_MODEL);

        // Get all the mModelUniformUpList where uniformModel greater than or equals to UPDATED_UNIFORM_MODEL
        defaultMModelUniformUpShouldNotBeFound("uniformModel.greaterOrEqualThan=" + UPDATED_UNIFORM_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformModelIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformModel less than or equals to DEFAULT_UNIFORM_MODEL
        defaultMModelUniformUpShouldNotBeFound("uniformModel.lessThan=" + DEFAULT_UNIFORM_MODEL);

        // Get all the mModelUniformUpList where uniformModel less than or equals to UPDATED_UNIFORM_MODEL
        defaultMModelUniformUpShouldBeFound("uniformModel.lessThan=" + UPDATED_UNIFORM_MODEL);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformTexture equals to DEFAULT_UNIFORM_TEXTURE
        defaultMModelUniformUpShouldBeFound("uniformTexture.equals=" + DEFAULT_UNIFORM_TEXTURE);

        // Get all the mModelUniformUpList where uniformTexture equals to UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformUpShouldNotBeFound("uniformTexture.equals=" + UPDATED_UNIFORM_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformTexture in DEFAULT_UNIFORM_TEXTURE or UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformUpShouldBeFound("uniformTexture.in=" + DEFAULT_UNIFORM_TEXTURE + "," + UPDATED_UNIFORM_TEXTURE);

        // Get all the mModelUniformUpList where uniformTexture equals to UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformUpShouldNotBeFound("uniformTexture.in=" + UPDATED_UNIFORM_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformTexture is not null
        defaultMModelUniformUpShouldBeFound("uniformTexture.specified=true");

        // Get all the mModelUniformUpList where uniformTexture is null
        defaultMModelUniformUpShouldNotBeFound("uniformTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformTexture greater than or equals to DEFAULT_UNIFORM_TEXTURE
        defaultMModelUniformUpShouldBeFound("uniformTexture.greaterOrEqualThan=" + DEFAULT_UNIFORM_TEXTURE);

        // Get all the mModelUniformUpList where uniformTexture greater than or equals to UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformUpShouldNotBeFound("uniformTexture.greaterOrEqualThan=" + UPDATED_UNIFORM_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformTexture less than or equals to DEFAULT_UNIFORM_TEXTURE
        defaultMModelUniformUpShouldNotBeFound("uniformTexture.lessThan=" + DEFAULT_UNIFORM_TEXTURE);

        // Get all the mModelUniformUpList where uniformTexture less than or equals to UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformUpShouldBeFound("uniformTexture.lessThan=" + UPDATED_UNIFORM_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoTexture equals to DEFAULT_UNIFORM_NO_TEXTURE
        defaultMModelUniformUpShouldBeFound("uniformNoTexture.equals=" + DEFAULT_UNIFORM_NO_TEXTURE);

        // Get all the mModelUniformUpList where uniformNoTexture equals to UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformUpShouldNotBeFound("uniformNoTexture.equals=" + UPDATED_UNIFORM_NO_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoTexture in DEFAULT_UNIFORM_NO_TEXTURE or UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformUpShouldBeFound("uniformNoTexture.in=" + DEFAULT_UNIFORM_NO_TEXTURE + "," + UPDATED_UNIFORM_NO_TEXTURE);

        // Get all the mModelUniformUpList where uniformNoTexture equals to UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformUpShouldNotBeFound("uniformNoTexture.in=" + UPDATED_UNIFORM_NO_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoTexture is not null
        defaultMModelUniformUpShouldBeFound("uniformNoTexture.specified=true");

        // Get all the mModelUniformUpList where uniformNoTexture is null
        defaultMModelUniformUpShouldNotBeFound("uniformNoTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoTexture greater than or equals to DEFAULT_UNIFORM_NO_TEXTURE
        defaultMModelUniformUpShouldBeFound("uniformNoTexture.greaterOrEqualThan=" + DEFAULT_UNIFORM_NO_TEXTURE);

        // Get all the mModelUniformUpList where uniformNoTexture greater than or equals to UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformUpShouldNotBeFound("uniformNoTexture.greaterOrEqualThan=" + UPDATED_UNIFORM_NO_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoTexture less than or equals to DEFAULT_UNIFORM_NO_TEXTURE
        defaultMModelUniformUpShouldNotBeFound("uniformNoTexture.lessThan=" + DEFAULT_UNIFORM_NO_TEXTURE);

        // Get all the mModelUniformUpList where uniformNoTexture less than or equals to UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformUpShouldBeFound("uniformNoTexture.lessThan=" + UPDATED_UNIFORM_NO_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberChestIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberChest equals to DEFAULT_NUMBER_CHEST
        defaultMModelUniformUpShouldBeFound("numberChest.equals=" + DEFAULT_NUMBER_CHEST);

        // Get all the mModelUniformUpList where numberChest equals to UPDATED_NUMBER_CHEST
        defaultMModelUniformUpShouldNotBeFound("numberChest.equals=" + UPDATED_NUMBER_CHEST);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberChestIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberChest in DEFAULT_NUMBER_CHEST or UPDATED_NUMBER_CHEST
        defaultMModelUniformUpShouldBeFound("numberChest.in=" + DEFAULT_NUMBER_CHEST + "," + UPDATED_NUMBER_CHEST);

        // Get all the mModelUniformUpList where numberChest equals to UPDATED_NUMBER_CHEST
        defaultMModelUniformUpShouldNotBeFound("numberChest.in=" + UPDATED_NUMBER_CHEST);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberChestIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberChest is not null
        defaultMModelUniformUpShouldBeFound("numberChest.specified=true");

        // Get all the mModelUniformUpList where numberChest is null
        defaultMModelUniformUpShouldNotBeFound("numberChest.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberChestIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberChest greater than or equals to DEFAULT_NUMBER_CHEST
        defaultMModelUniformUpShouldBeFound("numberChest.greaterOrEqualThan=" + DEFAULT_NUMBER_CHEST);

        // Get all the mModelUniformUpList where numberChest greater than or equals to UPDATED_NUMBER_CHEST
        defaultMModelUniformUpShouldNotBeFound("numberChest.greaterOrEqualThan=" + UPDATED_NUMBER_CHEST);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberChestIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberChest less than or equals to DEFAULT_NUMBER_CHEST
        defaultMModelUniformUpShouldNotBeFound("numberChest.lessThan=" + DEFAULT_NUMBER_CHEST);

        // Get all the mModelUniformUpList where numberChest less than or equals to UPDATED_NUMBER_CHEST
        defaultMModelUniformUpShouldBeFound("numberChest.lessThan=" + UPDATED_NUMBER_CHEST);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBellyIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBelly equals to DEFAULT_NUMBER_BELLY
        defaultMModelUniformUpShouldBeFound("numberBelly.equals=" + DEFAULT_NUMBER_BELLY);

        // Get all the mModelUniformUpList where numberBelly equals to UPDATED_NUMBER_BELLY
        defaultMModelUniformUpShouldNotBeFound("numberBelly.equals=" + UPDATED_NUMBER_BELLY);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBellyIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBelly in DEFAULT_NUMBER_BELLY or UPDATED_NUMBER_BELLY
        defaultMModelUniformUpShouldBeFound("numberBelly.in=" + DEFAULT_NUMBER_BELLY + "," + UPDATED_NUMBER_BELLY);

        // Get all the mModelUniformUpList where numberBelly equals to UPDATED_NUMBER_BELLY
        defaultMModelUniformUpShouldNotBeFound("numberBelly.in=" + UPDATED_NUMBER_BELLY);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBellyIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBelly is not null
        defaultMModelUniformUpShouldBeFound("numberBelly.specified=true");

        // Get all the mModelUniformUpList where numberBelly is null
        defaultMModelUniformUpShouldNotBeFound("numberBelly.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBellyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBelly greater than or equals to DEFAULT_NUMBER_BELLY
        defaultMModelUniformUpShouldBeFound("numberBelly.greaterOrEqualThan=" + DEFAULT_NUMBER_BELLY);

        // Get all the mModelUniformUpList where numberBelly greater than or equals to UPDATED_NUMBER_BELLY
        defaultMModelUniformUpShouldNotBeFound("numberBelly.greaterOrEqualThan=" + UPDATED_NUMBER_BELLY);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBellyIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBelly less than or equals to DEFAULT_NUMBER_BELLY
        defaultMModelUniformUpShouldNotBeFound("numberBelly.lessThan=" + DEFAULT_NUMBER_BELLY);

        // Get all the mModelUniformUpList where numberBelly less than or equals to UPDATED_NUMBER_BELLY
        defaultMModelUniformUpShouldBeFound("numberBelly.lessThan=" + UPDATED_NUMBER_BELLY);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBackIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBack equals to DEFAULT_NUMBER_BACK
        defaultMModelUniformUpShouldBeFound("numberBack.equals=" + DEFAULT_NUMBER_BACK);

        // Get all the mModelUniformUpList where numberBack equals to UPDATED_NUMBER_BACK
        defaultMModelUniformUpShouldNotBeFound("numberBack.equals=" + UPDATED_NUMBER_BACK);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBackIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBack in DEFAULT_NUMBER_BACK or UPDATED_NUMBER_BACK
        defaultMModelUniformUpShouldBeFound("numberBack.in=" + DEFAULT_NUMBER_BACK + "," + UPDATED_NUMBER_BACK);

        // Get all the mModelUniformUpList where numberBack equals to UPDATED_NUMBER_BACK
        defaultMModelUniformUpShouldNotBeFound("numberBack.in=" + UPDATED_NUMBER_BACK);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBackIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBack is not null
        defaultMModelUniformUpShouldBeFound("numberBack.specified=true");

        // Get all the mModelUniformUpList where numberBack is null
        defaultMModelUniformUpShouldNotBeFound("numberBack.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBackIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBack greater than or equals to DEFAULT_NUMBER_BACK
        defaultMModelUniformUpShouldBeFound("numberBack.greaterOrEqualThan=" + DEFAULT_NUMBER_BACK);

        // Get all the mModelUniformUpList where numberBack greater than or equals to UPDATED_NUMBER_BACK
        defaultMModelUniformUpShouldNotBeFound("numberBack.greaterOrEqualThan=" + UPDATED_NUMBER_BACK);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByNumberBackIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where numberBack less than or equals to DEFAULT_NUMBER_BACK
        defaultMModelUniformUpShouldNotBeFound("numberBack.lessThan=" + DEFAULT_NUMBER_BACK);

        // Get all the mModelUniformUpList where numberBack less than or equals to UPDATED_NUMBER_BACK
        defaultMModelUniformUpShouldBeFound("numberBack.lessThan=" + UPDATED_NUMBER_BACK);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionXIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionX equals to DEFAULT_UNIFORM_NO_POSITION_X
        defaultMModelUniformUpShouldBeFound("uniformNoPositionX.equals=" + DEFAULT_UNIFORM_NO_POSITION_X);

        // Get all the mModelUniformUpList where uniformNoPositionX equals to UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionX.equals=" + UPDATED_UNIFORM_NO_POSITION_X);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionXIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionX in DEFAULT_UNIFORM_NO_POSITION_X or UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformUpShouldBeFound("uniformNoPositionX.in=" + DEFAULT_UNIFORM_NO_POSITION_X + "," + UPDATED_UNIFORM_NO_POSITION_X);

        // Get all the mModelUniformUpList where uniformNoPositionX equals to UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionX.in=" + UPDATED_UNIFORM_NO_POSITION_X);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionXIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionX is not null
        defaultMModelUniformUpShouldBeFound("uniformNoPositionX.specified=true");

        // Get all the mModelUniformUpList where uniformNoPositionX is null
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionX.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionX greater than or equals to DEFAULT_UNIFORM_NO_POSITION_X
        defaultMModelUniformUpShouldBeFound("uniformNoPositionX.greaterOrEqualThan=" + DEFAULT_UNIFORM_NO_POSITION_X);

        // Get all the mModelUniformUpList where uniformNoPositionX greater than or equals to UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionX.greaterOrEqualThan=" + UPDATED_UNIFORM_NO_POSITION_X);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionXIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionX less than or equals to DEFAULT_UNIFORM_NO_POSITION_X
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionX.lessThan=" + DEFAULT_UNIFORM_NO_POSITION_X);

        // Get all the mModelUniformUpList where uniformNoPositionX less than or equals to UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformUpShouldBeFound("uniformNoPositionX.lessThan=" + UPDATED_UNIFORM_NO_POSITION_X);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionYIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionY equals to DEFAULT_UNIFORM_NO_POSITION_Y
        defaultMModelUniformUpShouldBeFound("uniformNoPositionY.equals=" + DEFAULT_UNIFORM_NO_POSITION_Y);

        // Get all the mModelUniformUpList where uniformNoPositionY equals to UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionY.equals=" + UPDATED_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionYIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionY in DEFAULT_UNIFORM_NO_POSITION_Y or UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformUpShouldBeFound("uniformNoPositionY.in=" + DEFAULT_UNIFORM_NO_POSITION_Y + "," + UPDATED_UNIFORM_NO_POSITION_Y);

        // Get all the mModelUniformUpList where uniformNoPositionY equals to UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionY.in=" + UPDATED_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionYIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionY is not null
        defaultMModelUniformUpShouldBeFound("uniformNoPositionY.specified=true");

        // Get all the mModelUniformUpList where uniformNoPositionY is null
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionY.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionY greater than or equals to DEFAULT_UNIFORM_NO_POSITION_Y
        defaultMModelUniformUpShouldBeFound("uniformNoPositionY.greaterOrEqualThan=" + DEFAULT_UNIFORM_NO_POSITION_Y);

        // Get all the mModelUniformUpList where uniformNoPositionY greater than or equals to UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionY.greaterOrEqualThan=" + UPDATED_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpsByUniformNoPositionYIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        // Get all the mModelUniformUpList where uniformNoPositionY less than or equals to DEFAULT_UNIFORM_NO_POSITION_Y
        defaultMModelUniformUpShouldNotBeFound("uniformNoPositionY.lessThan=" + DEFAULT_UNIFORM_NO_POSITION_Y);

        // Get all the mModelUniformUpList where uniformNoPositionY less than or equals to UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformUpShouldBeFound("uniformNoPositionY.lessThan=" + UPDATED_UNIFORM_NO_POSITION_Y);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMModelUniformUpShouldBeFound(String filter) throws Exception {
        restMModelUniformUpMockMvc.perform(get("/api/m-model-uniform-ups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelUniformUp.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniformUpId").value(hasItem(DEFAULT_UNIFORM_UP_ID)))
            .andExpect(jsonPath("$.[*].defaultDressingType").value(hasItem(DEFAULT_DEFAULT_DRESSING_TYPE)))
            .andExpect(jsonPath("$.[*].uniformModel").value(hasItem(DEFAULT_UNIFORM_MODEL)))
            .andExpect(jsonPath("$.[*].uniformTexture").value(hasItem(DEFAULT_UNIFORM_TEXTURE)))
            .andExpect(jsonPath("$.[*].uniformNoTexture").value(hasItem(DEFAULT_UNIFORM_NO_TEXTURE)))
            .andExpect(jsonPath("$.[*].defaultColor").value(hasItem(DEFAULT_DEFAULT_COLOR.toString())))
            .andExpect(jsonPath("$.[*].uniformNoColor").value(hasItem(DEFAULT_UNIFORM_NO_COLOR.toString())))
            .andExpect(jsonPath("$.[*].numberChest").value(hasItem(DEFAULT_NUMBER_CHEST)))
            .andExpect(jsonPath("$.[*].numberBelly").value(hasItem(DEFAULT_NUMBER_BELLY)))
            .andExpect(jsonPath("$.[*].numberBack").value(hasItem(DEFAULT_NUMBER_BACK)))
            .andExpect(jsonPath("$.[*].uniformNoPositionX").value(hasItem(DEFAULT_UNIFORM_NO_POSITION_X)))
            .andExpect(jsonPath("$.[*].uniformNoPositionY").value(hasItem(DEFAULT_UNIFORM_NO_POSITION_Y)));

        // Check, that the count call also returns 1
        restMModelUniformUpMockMvc.perform(get("/api/m-model-uniform-ups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMModelUniformUpShouldNotBeFound(String filter) throws Exception {
        restMModelUniformUpMockMvc.perform(get("/api/m-model-uniform-ups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMModelUniformUpMockMvc.perform(get("/api/m-model-uniform-ups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMModelUniformUp() throws Exception {
        // Get the mModelUniformUp
        restMModelUniformUpMockMvc.perform(get("/api/m-model-uniform-ups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMModelUniformUp() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        int databaseSizeBeforeUpdate = mModelUniformUpRepository.findAll().size();

        // Update the mModelUniformUp
        MModelUniformUp updatedMModelUniformUp = mModelUniformUpRepository.findById(mModelUniformUp.getId()).get();
        // Disconnect from session so that the updates on updatedMModelUniformUp are not directly saved in db
        em.detach(updatedMModelUniformUp);
        updatedMModelUniformUp
            .uniformUpId(UPDATED_UNIFORM_UP_ID)
            .defaultDressingType(UPDATED_DEFAULT_DRESSING_TYPE)
            .uniformModel(UPDATED_UNIFORM_MODEL)
            .uniformTexture(UPDATED_UNIFORM_TEXTURE)
            .uniformNoTexture(UPDATED_UNIFORM_NO_TEXTURE)
            .defaultColor(UPDATED_DEFAULT_COLOR)
            .uniformNoColor(UPDATED_UNIFORM_NO_COLOR)
            .numberChest(UPDATED_NUMBER_CHEST)
            .numberBelly(UPDATED_NUMBER_BELLY)
            .numberBack(UPDATED_NUMBER_BACK)
            .uniformNoPositionX(UPDATED_UNIFORM_NO_POSITION_X)
            .uniformNoPositionY(UPDATED_UNIFORM_NO_POSITION_Y);
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(updatedMModelUniformUp);

        restMModelUniformUpMockMvc.perform(put("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isOk());

        // Validate the MModelUniformUp in the database
        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeUpdate);
        MModelUniformUp testMModelUniformUp = mModelUniformUpList.get(mModelUniformUpList.size() - 1);
        assertThat(testMModelUniformUp.getUniformUpId()).isEqualTo(UPDATED_UNIFORM_UP_ID);
        assertThat(testMModelUniformUp.getDefaultDressingType()).isEqualTo(UPDATED_DEFAULT_DRESSING_TYPE);
        assertThat(testMModelUniformUp.getUniformModel()).isEqualTo(UPDATED_UNIFORM_MODEL);
        assertThat(testMModelUniformUp.getUniformTexture()).isEqualTo(UPDATED_UNIFORM_TEXTURE);
        assertThat(testMModelUniformUp.getUniformNoTexture()).isEqualTo(UPDATED_UNIFORM_NO_TEXTURE);
        assertThat(testMModelUniformUp.getDefaultColor()).isEqualTo(UPDATED_DEFAULT_COLOR);
        assertThat(testMModelUniformUp.getUniformNoColor()).isEqualTo(UPDATED_UNIFORM_NO_COLOR);
        assertThat(testMModelUniformUp.getNumberChest()).isEqualTo(UPDATED_NUMBER_CHEST);
        assertThat(testMModelUniformUp.getNumberBelly()).isEqualTo(UPDATED_NUMBER_BELLY);
        assertThat(testMModelUniformUp.getNumberBack()).isEqualTo(UPDATED_NUMBER_BACK);
        assertThat(testMModelUniformUp.getUniformNoPositionX()).isEqualTo(UPDATED_UNIFORM_NO_POSITION_X);
        assertThat(testMModelUniformUp.getUniformNoPositionY()).isEqualTo(UPDATED_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void updateNonExistingMModelUniformUp() throws Exception {
        int databaseSizeBeforeUpdate = mModelUniformUpRepository.findAll().size();

        // Create the MModelUniformUp
        MModelUniformUpDTO mModelUniformUpDTO = mModelUniformUpMapper.toDto(mModelUniformUp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMModelUniformUpMockMvc.perform(put("/api/m-model-uniform-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelUniformUp in the database
        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMModelUniformUp() throws Exception {
        // Initialize the database
        mModelUniformUpRepository.saveAndFlush(mModelUniformUp);

        int databaseSizeBeforeDelete = mModelUniformUpRepository.findAll().size();

        // Delete the mModelUniformUp
        restMModelUniformUpMockMvc.perform(delete("/api/m-model-uniform-ups/{id}", mModelUniformUp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MModelUniformUp> mModelUniformUpList = mModelUniformUpRepository.findAll();
        assertThat(mModelUniformUpList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelUniformUp.class);
        MModelUniformUp mModelUniformUp1 = new MModelUniformUp();
        mModelUniformUp1.setId(1L);
        MModelUniformUp mModelUniformUp2 = new MModelUniformUp();
        mModelUniformUp2.setId(mModelUniformUp1.getId());
        assertThat(mModelUniformUp1).isEqualTo(mModelUniformUp2);
        mModelUniformUp2.setId(2L);
        assertThat(mModelUniformUp1).isNotEqualTo(mModelUniformUp2);
        mModelUniformUp1.setId(null);
        assertThat(mModelUniformUp1).isNotEqualTo(mModelUniformUp2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelUniformUpDTO.class);
        MModelUniformUpDTO mModelUniformUpDTO1 = new MModelUniformUpDTO();
        mModelUniformUpDTO1.setId(1L);
        MModelUniformUpDTO mModelUniformUpDTO2 = new MModelUniformUpDTO();
        assertThat(mModelUniformUpDTO1).isNotEqualTo(mModelUniformUpDTO2);
        mModelUniformUpDTO2.setId(mModelUniformUpDTO1.getId());
        assertThat(mModelUniformUpDTO1).isEqualTo(mModelUniformUpDTO2);
        mModelUniformUpDTO2.setId(2L);
        assertThat(mModelUniformUpDTO1).isNotEqualTo(mModelUniformUpDTO2);
        mModelUniformUpDTO1.setId(null);
        assertThat(mModelUniformUpDTO1).isNotEqualTo(mModelUniformUpDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mModelUniformUpMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mModelUniformUpMapper.fromId(null)).isNull();
    }
}
