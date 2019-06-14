package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MModelUniformBottom;
import io.shm.tsubasa.repository.MModelUniformBottomRepository;
import io.shm.tsubasa.service.MModelUniformBottomService;
import io.shm.tsubasa.service.dto.MModelUniformBottomDTO;
import io.shm.tsubasa.service.mapper.MModelUniformBottomMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MModelUniformBottomCriteria;
import io.shm.tsubasa.service.MModelUniformBottomQueryService;

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
 * Integration tests for the {@Link MModelUniformBottomResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MModelUniformBottomResourceIT {

    private static final Integer DEFAULT_UNIFORM_BOTTOM_ID = 1;
    private static final Integer UPDATED_UNIFORM_BOTTOM_ID = 2;

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

    private static final Integer DEFAULT_NUMBER_RIGHT_LEG = 1;
    private static final Integer UPDATED_NUMBER_RIGHT_LEG = 2;

    private static final Integer DEFAULT_NUMBER_LEFT_LEG = 1;
    private static final Integer UPDATED_NUMBER_LEFT_LEG = 2;

    private static final Integer DEFAULT_UNIFORM_NO_POSITION_X = 1;
    private static final Integer UPDATED_UNIFORM_NO_POSITION_X = 2;

    private static final Integer DEFAULT_UNIFORM_NO_POSITION_Y = 1;
    private static final Integer UPDATED_UNIFORM_NO_POSITION_Y = 2;

    @Autowired
    private MModelUniformBottomRepository mModelUniformBottomRepository;

    @Autowired
    private MModelUniformBottomMapper mModelUniformBottomMapper;

    @Autowired
    private MModelUniformBottomService mModelUniformBottomService;

    @Autowired
    private MModelUniformBottomQueryService mModelUniformBottomQueryService;

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

    private MockMvc restMModelUniformBottomMockMvc;

    private MModelUniformBottom mModelUniformBottom;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MModelUniformBottomResource mModelUniformBottomResource = new MModelUniformBottomResource(mModelUniformBottomService, mModelUniformBottomQueryService);
        this.restMModelUniformBottomMockMvc = MockMvcBuilders.standaloneSetup(mModelUniformBottomResource)
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
    public static MModelUniformBottom createEntity(EntityManager em) {
        MModelUniformBottom mModelUniformBottom = new MModelUniformBottom()
            .uniformBottomId(DEFAULT_UNIFORM_BOTTOM_ID)
            .defaultDressingType(DEFAULT_DEFAULT_DRESSING_TYPE)
            .uniformModel(DEFAULT_UNIFORM_MODEL)
            .uniformTexture(DEFAULT_UNIFORM_TEXTURE)
            .uniformNoTexture(DEFAULT_UNIFORM_NO_TEXTURE)
            .defaultColor(DEFAULT_DEFAULT_COLOR)
            .uniformNoColor(DEFAULT_UNIFORM_NO_COLOR)
            .numberRightLeg(DEFAULT_NUMBER_RIGHT_LEG)
            .numberLeftLeg(DEFAULT_NUMBER_LEFT_LEG)
            .uniformNoPositionX(DEFAULT_UNIFORM_NO_POSITION_X)
            .uniformNoPositionY(DEFAULT_UNIFORM_NO_POSITION_Y);
        return mModelUniformBottom;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MModelUniformBottom createUpdatedEntity(EntityManager em) {
        MModelUniformBottom mModelUniformBottom = new MModelUniformBottom()
            .uniformBottomId(UPDATED_UNIFORM_BOTTOM_ID)
            .defaultDressingType(UPDATED_DEFAULT_DRESSING_TYPE)
            .uniformModel(UPDATED_UNIFORM_MODEL)
            .uniformTexture(UPDATED_UNIFORM_TEXTURE)
            .uniformNoTexture(UPDATED_UNIFORM_NO_TEXTURE)
            .defaultColor(UPDATED_DEFAULT_COLOR)
            .uniformNoColor(UPDATED_UNIFORM_NO_COLOR)
            .numberRightLeg(UPDATED_NUMBER_RIGHT_LEG)
            .numberLeftLeg(UPDATED_NUMBER_LEFT_LEG)
            .uniformNoPositionX(UPDATED_UNIFORM_NO_POSITION_X)
            .uniformNoPositionY(UPDATED_UNIFORM_NO_POSITION_Y);
        return mModelUniformBottom;
    }

    @BeforeEach
    public void initTest() {
        mModelUniformBottom = createEntity(em);
    }

    @Test
    @Transactional
    public void createMModelUniformBottom() throws Exception {
        int databaseSizeBeforeCreate = mModelUniformBottomRepository.findAll().size();

        // Create the MModelUniformBottom
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);
        restMModelUniformBottomMockMvc.perform(post("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isCreated());

        // Validate the MModelUniformBottom in the database
        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeCreate + 1);
        MModelUniformBottom testMModelUniformBottom = mModelUniformBottomList.get(mModelUniformBottomList.size() - 1);
        assertThat(testMModelUniformBottom.getUniformBottomId()).isEqualTo(DEFAULT_UNIFORM_BOTTOM_ID);
        assertThat(testMModelUniformBottom.getDefaultDressingType()).isEqualTo(DEFAULT_DEFAULT_DRESSING_TYPE);
        assertThat(testMModelUniformBottom.getUniformModel()).isEqualTo(DEFAULT_UNIFORM_MODEL);
        assertThat(testMModelUniformBottom.getUniformTexture()).isEqualTo(DEFAULT_UNIFORM_TEXTURE);
        assertThat(testMModelUniformBottom.getUniformNoTexture()).isEqualTo(DEFAULT_UNIFORM_NO_TEXTURE);
        assertThat(testMModelUniformBottom.getDefaultColor()).isEqualTo(DEFAULT_DEFAULT_COLOR);
        assertThat(testMModelUniformBottom.getUniformNoColor()).isEqualTo(DEFAULT_UNIFORM_NO_COLOR);
        assertThat(testMModelUniformBottom.getNumberRightLeg()).isEqualTo(DEFAULT_NUMBER_RIGHT_LEG);
        assertThat(testMModelUniformBottom.getNumberLeftLeg()).isEqualTo(DEFAULT_NUMBER_LEFT_LEG);
        assertThat(testMModelUniformBottom.getUniformNoPositionX()).isEqualTo(DEFAULT_UNIFORM_NO_POSITION_X);
        assertThat(testMModelUniformBottom.getUniformNoPositionY()).isEqualTo(DEFAULT_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void createMModelUniformBottomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mModelUniformBottomRepository.findAll().size();

        // Create the MModelUniformBottom with an existing ID
        mModelUniformBottom.setId(1L);
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMModelUniformBottomMockMvc.perform(post("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelUniformBottom in the database
        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUniformBottomIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomRepository.findAll().size();
        // set the field null
        mModelUniformBottom.setUniformBottomId(null);

        // Create the MModelUniformBottom, which fails.
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);

        restMModelUniformBottomMockMvc.perform(post("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDefaultDressingTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomRepository.findAll().size();
        // set the field null
        mModelUniformBottom.setDefaultDressingType(null);

        // Create the MModelUniformBottom, which fails.
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);

        restMModelUniformBottomMockMvc.perform(post("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomRepository.findAll().size();
        // set the field null
        mModelUniformBottom.setUniformModel(null);

        // Create the MModelUniformBottom, which fails.
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);

        restMModelUniformBottomMockMvc.perform(post("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformTextureIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomRepository.findAll().size();
        // set the field null
        mModelUniformBottom.setUniformTexture(null);

        // Create the MModelUniformBottom, which fails.
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);

        restMModelUniformBottomMockMvc.perform(post("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformNoTextureIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomRepository.findAll().size();
        // set the field null
        mModelUniformBottom.setUniformNoTexture(null);

        // Create the MModelUniformBottom, which fails.
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);

        restMModelUniformBottomMockMvc.perform(post("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberRightLegIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomRepository.findAll().size();
        // set the field null
        mModelUniformBottom.setNumberRightLeg(null);

        // Create the MModelUniformBottom, which fails.
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);

        restMModelUniformBottomMockMvc.perform(post("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberLeftLegIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomRepository.findAll().size();
        // set the field null
        mModelUniformBottom.setNumberLeftLeg(null);

        // Create the MModelUniformBottom, which fails.
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);

        restMModelUniformBottomMockMvc.perform(post("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottoms() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList
        restMModelUniformBottomMockMvc.perform(get("/api/m-model-uniform-bottoms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelUniformBottom.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniformBottomId").value(hasItem(DEFAULT_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].defaultDressingType").value(hasItem(DEFAULT_DEFAULT_DRESSING_TYPE)))
            .andExpect(jsonPath("$.[*].uniformModel").value(hasItem(DEFAULT_UNIFORM_MODEL)))
            .andExpect(jsonPath("$.[*].uniformTexture").value(hasItem(DEFAULT_UNIFORM_TEXTURE)))
            .andExpect(jsonPath("$.[*].uniformNoTexture").value(hasItem(DEFAULT_UNIFORM_NO_TEXTURE)))
            .andExpect(jsonPath("$.[*].defaultColor").value(hasItem(DEFAULT_DEFAULT_COLOR.toString())))
            .andExpect(jsonPath("$.[*].uniformNoColor").value(hasItem(DEFAULT_UNIFORM_NO_COLOR.toString())))
            .andExpect(jsonPath("$.[*].numberRightLeg").value(hasItem(DEFAULT_NUMBER_RIGHT_LEG)))
            .andExpect(jsonPath("$.[*].numberLeftLeg").value(hasItem(DEFAULT_NUMBER_LEFT_LEG)))
            .andExpect(jsonPath("$.[*].uniformNoPositionX").value(hasItem(DEFAULT_UNIFORM_NO_POSITION_X)))
            .andExpect(jsonPath("$.[*].uniformNoPositionY").value(hasItem(DEFAULT_UNIFORM_NO_POSITION_Y)));
    }
    
    @Test
    @Transactional
    public void getMModelUniformBottom() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get the mModelUniformBottom
        restMModelUniformBottomMockMvc.perform(get("/api/m-model-uniform-bottoms/{id}", mModelUniformBottom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mModelUniformBottom.getId().intValue()))
            .andExpect(jsonPath("$.uniformBottomId").value(DEFAULT_UNIFORM_BOTTOM_ID))
            .andExpect(jsonPath("$.defaultDressingType").value(DEFAULT_DEFAULT_DRESSING_TYPE))
            .andExpect(jsonPath("$.uniformModel").value(DEFAULT_UNIFORM_MODEL))
            .andExpect(jsonPath("$.uniformTexture").value(DEFAULT_UNIFORM_TEXTURE))
            .andExpect(jsonPath("$.uniformNoTexture").value(DEFAULT_UNIFORM_NO_TEXTURE))
            .andExpect(jsonPath("$.defaultColor").value(DEFAULT_DEFAULT_COLOR.toString()))
            .andExpect(jsonPath("$.uniformNoColor").value(DEFAULT_UNIFORM_NO_COLOR.toString()))
            .andExpect(jsonPath("$.numberRightLeg").value(DEFAULT_NUMBER_RIGHT_LEG))
            .andExpect(jsonPath("$.numberLeftLeg").value(DEFAULT_NUMBER_LEFT_LEG))
            .andExpect(jsonPath("$.uniformNoPositionX").value(DEFAULT_UNIFORM_NO_POSITION_X))
            .andExpect(jsonPath("$.uniformNoPositionY").value(DEFAULT_UNIFORM_NO_POSITION_Y));
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformBottomIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformBottomId equals to DEFAULT_UNIFORM_BOTTOM_ID
        defaultMModelUniformBottomShouldBeFound("uniformBottomId.equals=" + DEFAULT_UNIFORM_BOTTOM_ID);

        // Get all the mModelUniformBottomList where uniformBottomId equals to UPDATED_UNIFORM_BOTTOM_ID
        defaultMModelUniformBottomShouldNotBeFound("uniformBottomId.equals=" + UPDATED_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformBottomIdIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformBottomId in DEFAULT_UNIFORM_BOTTOM_ID or UPDATED_UNIFORM_BOTTOM_ID
        defaultMModelUniformBottomShouldBeFound("uniformBottomId.in=" + DEFAULT_UNIFORM_BOTTOM_ID + "," + UPDATED_UNIFORM_BOTTOM_ID);

        // Get all the mModelUniformBottomList where uniformBottomId equals to UPDATED_UNIFORM_BOTTOM_ID
        defaultMModelUniformBottomShouldNotBeFound("uniformBottomId.in=" + UPDATED_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformBottomIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformBottomId is not null
        defaultMModelUniformBottomShouldBeFound("uniformBottomId.specified=true");

        // Get all the mModelUniformBottomList where uniformBottomId is null
        defaultMModelUniformBottomShouldNotBeFound("uniformBottomId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformBottomIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformBottomId greater than or equals to DEFAULT_UNIFORM_BOTTOM_ID
        defaultMModelUniformBottomShouldBeFound("uniformBottomId.greaterOrEqualThan=" + DEFAULT_UNIFORM_BOTTOM_ID);

        // Get all the mModelUniformBottomList where uniformBottomId greater than or equals to UPDATED_UNIFORM_BOTTOM_ID
        defaultMModelUniformBottomShouldNotBeFound("uniformBottomId.greaterOrEqualThan=" + UPDATED_UNIFORM_BOTTOM_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformBottomIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformBottomId less than or equals to DEFAULT_UNIFORM_BOTTOM_ID
        defaultMModelUniformBottomShouldNotBeFound("uniformBottomId.lessThan=" + DEFAULT_UNIFORM_BOTTOM_ID);

        // Get all the mModelUniformBottomList where uniformBottomId less than or equals to UPDATED_UNIFORM_BOTTOM_ID
        defaultMModelUniformBottomShouldBeFound("uniformBottomId.lessThan=" + UPDATED_UNIFORM_BOTTOM_ID);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomsByDefaultDressingTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where defaultDressingType equals to DEFAULT_DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomShouldBeFound("defaultDressingType.equals=" + DEFAULT_DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformBottomList where defaultDressingType equals to UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomShouldNotBeFound("defaultDressingType.equals=" + UPDATED_DEFAULT_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByDefaultDressingTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where defaultDressingType in DEFAULT_DEFAULT_DRESSING_TYPE or UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomShouldBeFound("defaultDressingType.in=" + DEFAULT_DEFAULT_DRESSING_TYPE + "," + UPDATED_DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformBottomList where defaultDressingType equals to UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomShouldNotBeFound("defaultDressingType.in=" + UPDATED_DEFAULT_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByDefaultDressingTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where defaultDressingType is not null
        defaultMModelUniformBottomShouldBeFound("defaultDressingType.specified=true");

        // Get all the mModelUniformBottomList where defaultDressingType is null
        defaultMModelUniformBottomShouldNotBeFound("defaultDressingType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByDefaultDressingTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where defaultDressingType greater than or equals to DEFAULT_DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomShouldBeFound("defaultDressingType.greaterOrEqualThan=" + DEFAULT_DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformBottomList where defaultDressingType greater than or equals to UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomShouldNotBeFound("defaultDressingType.greaterOrEqualThan=" + UPDATED_DEFAULT_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByDefaultDressingTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where defaultDressingType less than or equals to DEFAULT_DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomShouldNotBeFound("defaultDressingType.lessThan=" + DEFAULT_DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformBottomList where defaultDressingType less than or equals to UPDATED_DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomShouldBeFound("defaultDressingType.lessThan=" + UPDATED_DEFAULT_DRESSING_TYPE);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformModelIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformModel equals to DEFAULT_UNIFORM_MODEL
        defaultMModelUniformBottomShouldBeFound("uniformModel.equals=" + DEFAULT_UNIFORM_MODEL);

        // Get all the mModelUniformBottomList where uniformModel equals to UPDATED_UNIFORM_MODEL
        defaultMModelUniformBottomShouldNotBeFound("uniformModel.equals=" + UPDATED_UNIFORM_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformModelIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformModel in DEFAULT_UNIFORM_MODEL or UPDATED_UNIFORM_MODEL
        defaultMModelUniformBottomShouldBeFound("uniformModel.in=" + DEFAULT_UNIFORM_MODEL + "," + UPDATED_UNIFORM_MODEL);

        // Get all the mModelUniformBottomList where uniformModel equals to UPDATED_UNIFORM_MODEL
        defaultMModelUniformBottomShouldNotBeFound("uniformModel.in=" + UPDATED_UNIFORM_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformModelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformModel is not null
        defaultMModelUniformBottomShouldBeFound("uniformModel.specified=true");

        // Get all the mModelUniformBottomList where uniformModel is null
        defaultMModelUniformBottomShouldNotBeFound("uniformModel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformModelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformModel greater than or equals to DEFAULT_UNIFORM_MODEL
        defaultMModelUniformBottomShouldBeFound("uniformModel.greaterOrEqualThan=" + DEFAULT_UNIFORM_MODEL);

        // Get all the mModelUniformBottomList where uniformModel greater than or equals to UPDATED_UNIFORM_MODEL
        defaultMModelUniformBottomShouldNotBeFound("uniformModel.greaterOrEqualThan=" + UPDATED_UNIFORM_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformModelIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformModel less than or equals to DEFAULT_UNIFORM_MODEL
        defaultMModelUniformBottomShouldNotBeFound("uniformModel.lessThan=" + DEFAULT_UNIFORM_MODEL);

        // Get all the mModelUniformBottomList where uniformModel less than or equals to UPDATED_UNIFORM_MODEL
        defaultMModelUniformBottomShouldBeFound("uniformModel.lessThan=" + UPDATED_UNIFORM_MODEL);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformTexture equals to DEFAULT_UNIFORM_TEXTURE
        defaultMModelUniformBottomShouldBeFound("uniformTexture.equals=" + DEFAULT_UNIFORM_TEXTURE);

        // Get all the mModelUniformBottomList where uniformTexture equals to UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformBottomShouldNotBeFound("uniformTexture.equals=" + UPDATED_UNIFORM_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformTexture in DEFAULT_UNIFORM_TEXTURE or UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformBottomShouldBeFound("uniformTexture.in=" + DEFAULT_UNIFORM_TEXTURE + "," + UPDATED_UNIFORM_TEXTURE);

        // Get all the mModelUniformBottomList where uniformTexture equals to UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformBottomShouldNotBeFound("uniformTexture.in=" + UPDATED_UNIFORM_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformTexture is not null
        defaultMModelUniformBottomShouldBeFound("uniformTexture.specified=true");

        // Get all the mModelUniformBottomList where uniformTexture is null
        defaultMModelUniformBottomShouldNotBeFound("uniformTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformTexture greater than or equals to DEFAULT_UNIFORM_TEXTURE
        defaultMModelUniformBottomShouldBeFound("uniformTexture.greaterOrEqualThan=" + DEFAULT_UNIFORM_TEXTURE);

        // Get all the mModelUniformBottomList where uniformTexture greater than or equals to UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformBottomShouldNotBeFound("uniformTexture.greaterOrEqualThan=" + UPDATED_UNIFORM_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformTexture less than or equals to DEFAULT_UNIFORM_TEXTURE
        defaultMModelUniformBottomShouldNotBeFound("uniformTexture.lessThan=" + DEFAULT_UNIFORM_TEXTURE);

        // Get all the mModelUniformBottomList where uniformTexture less than or equals to UPDATED_UNIFORM_TEXTURE
        defaultMModelUniformBottomShouldBeFound("uniformTexture.lessThan=" + UPDATED_UNIFORM_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoTexture equals to DEFAULT_UNIFORM_NO_TEXTURE
        defaultMModelUniformBottomShouldBeFound("uniformNoTexture.equals=" + DEFAULT_UNIFORM_NO_TEXTURE);

        // Get all the mModelUniformBottomList where uniformNoTexture equals to UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformBottomShouldNotBeFound("uniformNoTexture.equals=" + UPDATED_UNIFORM_NO_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoTexture in DEFAULT_UNIFORM_NO_TEXTURE or UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformBottomShouldBeFound("uniformNoTexture.in=" + DEFAULT_UNIFORM_NO_TEXTURE + "," + UPDATED_UNIFORM_NO_TEXTURE);

        // Get all the mModelUniformBottomList where uniformNoTexture equals to UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformBottomShouldNotBeFound("uniformNoTexture.in=" + UPDATED_UNIFORM_NO_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoTexture is not null
        defaultMModelUniformBottomShouldBeFound("uniformNoTexture.specified=true");

        // Get all the mModelUniformBottomList where uniformNoTexture is null
        defaultMModelUniformBottomShouldNotBeFound("uniformNoTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoTexture greater than or equals to DEFAULT_UNIFORM_NO_TEXTURE
        defaultMModelUniformBottomShouldBeFound("uniformNoTexture.greaterOrEqualThan=" + DEFAULT_UNIFORM_NO_TEXTURE);

        // Get all the mModelUniformBottomList where uniformNoTexture greater than or equals to UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformBottomShouldNotBeFound("uniformNoTexture.greaterOrEqualThan=" + UPDATED_UNIFORM_NO_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoTexture less than or equals to DEFAULT_UNIFORM_NO_TEXTURE
        defaultMModelUniformBottomShouldNotBeFound("uniformNoTexture.lessThan=" + DEFAULT_UNIFORM_NO_TEXTURE);

        // Get all the mModelUniformBottomList where uniformNoTexture less than or equals to UPDATED_UNIFORM_NO_TEXTURE
        defaultMModelUniformBottomShouldBeFound("uniformNoTexture.lessThan=" + UPDATED_UNIFORM_NO_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberRightLegIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberRightLeg equals to DEFAULT_NUMBER_RIGHT_LEG
        defaultMModelUniformBottomShouldBeFound("numberRightLeg.equals=" + DEFAULT_NUMBER_RIGHT_LEG);

        // Get all the mModelUniformBottomList where numberRightLeg equals to UPDATED_NUMBER_RIGHT_LEG
        defaultMModelUniformBottomShouldNotBeFound("numberRightLeg.equals=" + UPDATED_NUMBER_RIGHT_LEG);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberRightLegIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberRightLeg in DEFAULT_NUMBER_RIGHT_LEG or UPDATED_NUMBER_RIGHT_LEG
        defaultMModelUniformBottomShouldBeFound("numberRightLeg.in=" + DEFAULT_NUMBER_RIGHT_LEG + "," + UPDATED_NUMBER_RIGHT_LEG);

        // Get all the mModelUniformBottomList where numberRightLeg equals to UPDATED_NUMBER_RIGHT_LEG
        defaultMModelUniformBottomShouldNotBeFound("numberRightLeg.in=" + UPDATED_NUMBER_RIGHT_LEG);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberRightLegIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberRightLeg is not null
        defaultMModelUniformBottomShouldBeFound("numberRightLeg.specified=true");

        // Get all the mModelUniformBottomList where numberRightLeg is null
        defaultMModelUniformBottomShouldNotBeFound("numberRightLeg.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberRightLegIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberRightLeg greater than or equals to DEFAULT_NUMBER_RIGHT_LEG
        defaultMModelUniformBottomShouldBeFound("numberRightLeg.greaterOrEqualThan=" + DEFAULT_NUMBER_RIGHT_LEG);

        // Get all the mModelUniformBottomList where numberRightLeg greater than or equals to UPDATED_NUMBER_RIGHT_LEG
        defaultMModelUniformBottomShouldNotBeFound("numberRightLeg.greaterOrEqualThan=" + UPDATED_NUMBER_RIGHT_LEG);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberRightLegIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberRightLeg less than or equals to DEFAULT_NUMBER_RIGHT_LEG
        defaultMModelUniformBottomShouldNotBeFound("numberRightLeg.lessThan=" + DEFAULT_NUMBER_RIGHT_LEG);

        // Get all the mModelUniformBottomList where numberRightLeg less than or equals to UPDATED_NUMBER_RIGHT_LEG
        defaultMModelUniformBottomShouldBeFound("numberRightLeg.lessThan=" + UPDATED_NUMBER_RIGHT_LEG);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberLeftLegIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberLeftLeg equals to DEFAULT_NUMBER_LEFT_LEG
        defaultMModelUniformBottomShouldBeFound("numberLeftLeg.equals=" + DEFAULT_NUMBER_LEFT_LEG);

        // Get all the mModelUniformBottomList where numberLeftLeg equals to UPDATED_NUMBER_LEFT_LEG
        defaultMModelUniformBottomShouldNotBeFound("numberLeftLeg.equals=" + UPDATED_NUMBER_LEFT_LEG);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberLeftLegIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberLeftLeg in DEFAULT_NUMBER_LEFT_LEG or UPDATED_NUMBER_LEFT_LEG
        defaultMModelUniformBottomShouldBeFound("numberLeftLeg.in=" + DEFAULT_NUMBER_LEFT_LEG + "," + UPDATED_NUMBER_LEFT_LEG);

        // Get all the mModelUniformBottomList where numberLeftLeg equals to UPDATED_NUMBER_LEFT_LEG
        defaultMModelUniformBottomShouldNotBeFound("numberLeftLeg.in=" + UPDATED_NUMBER_LEFT_LEG);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberLeftLegIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberLeftLeg is not null
        defaultMModelUniformBottomShouldBeFound("numberLeftLeg.specified=true");

        // Get all the mModelUniformBottomList where numberLeftLeg is null
        defaultMModelUniformBottomShouldNotBeFound("numberLeftLeg.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberLeftLegIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberLeftLeg greater than or equals to DEFAULT_NUMBER_LEFT_LEG
        defaultMModelUniformBottomShouldBeFound("numberLeftLeg.greaterOrEqualThan=" + DEFAULT_NUMBER_LEFT_LEG);

        // Get all the mModelUniformBottomList where numberLeftLeg greater than or equals to UPDATED_NUMBER_LEFT_LEG
        defaultMModelUniformBottomShouldNotBeFound("numberLeftLeg.greaterOrEqualThan=" + UPDATED_NUMBER_LEFT_LEG);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByNumberLeftLegIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where numberLeftLeg less than or equals to DEFAULT_NUMBER_LEFT_LEG
        defaultMModelUniformBottomShouldNotBeFound("numberLeftLeg.lessThan=" + DEFAULT_NUMBER_LEFT_LEG);

        // Get all the mModelUniformBottomList where numberLeftLeg less than or equals to UPDATED_NUMBER_LEFT_LEG
        defaultMModelUniformBottomShouldBeFound("numberLeftLeg.lessThan=" + UPDATED_NUMBER_LEFT_LEG);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionXIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionX equals to DEFAULT_UNIFORM_NO_POSITION_X
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionX.equals=" + DEFAULT_UNIFORM_NO_POSITION_X);

        // Get all the mModelUniformBottomList where uniformNoPositionX equals to UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionX.equals=" + UPDATED_UNIFORM_NO_POSITION_X);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionXIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionX in DEFAULT_UNIFORM_NO_POSITION_X or UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionX.in=" + DEFAULT_UNIFORM_NO_POSITION_X + "," + UPDATED_UNIFORM_NO_POSITION_X);

        // Get all the mModelUniformBottomList where uniformNoPositionX equals to UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionX.in=" + UPDATED_UNIFORM_NO_POSITION_X);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionXIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionX is not null
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionX.specified=true");

        // Get all the mModelUniformBottomList where uniformNoPositionX is null
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionX.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionX greater than or equals to DEFAULT_UNIFORM_NO_POSITION_X
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionX.greaterOrEqualThan=" + DEFAULT_UNIFORM_NO_POSITION_X);

        // Get all the mModelUniformBottomList where uniformNoPositionX greater than or equals to UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionX.greaterOrEqualThan=" + UPDATED_UNIFORM_NO_POSITION_X);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionXIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionX less than or equals to DEFAULT_UNIFORM_NO_POSITION_X
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionX.lessThan=" + DEFAULT_UNIFORM_NO_POSITION_X);

        // Get all the mModelUniformBottomList where uniformNoPositionX less than or equals to UPDATED_UNIFORM_NO_POSITION_X
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionX.lessThan=" + UPDATED_UNIFORM_NO_POSITION_X);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionYIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionY equals to DEFAULT_UNIFORM_NO_POSITION_Y
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionY.equals=" + DEFAULT_UNIFORM_NO_POSITION_Y);

        // Get all the mModelUniformBottomList where uniformNoPositionY equals to UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionY.equals=" + UPDATED_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionYIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionY in DEFAULT_UNIFORM_NO_POSITION_Y or UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionY.in=" + DEFAULT_UNIFORM_NO_POSITION_Y + "," + UPDATED_UNIFORM_NO_POSITION_Y);

        // Get all the mModelUniformBottomList where uniformNoPositionY equals to UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionY.in=" + UPDATED_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionYIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionY is not null
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionY.specified=true");

        // Get all the mModelUniformBottomList where uniformNoPositionY is null
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionY.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionY greater than or equals to DEFAULT_UNIFORM_NO_POSITION_Y
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionY.greaterOrEqualThan=" + DEFAULT_UNIFORM_NO_POSITION_Y);

        // Get all the mModelUniformBottomList where uniformNoPositionY greater than or equals to UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionY.greaterOrEqualThan=" + UPDATED_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomsByUniformNoPositionYIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        // Get all the mModelUniformBottomList where uniformNoPositionY less than or equals to DEFAULT_UNIFORM_NO_POSITION_Y
        defaultMModelUniformBottomShouldNotBeFound("uniformNoPositionY.lessThan=" + DEFAULT_UNIFORM_NO_POSITION_Y);

        // Get all the mModelUniformBottomList where uniformNoPositionY less than or equals to UPDATED_UNIFORM_NO_POSITION_Y
        defaultMModelUniformBottomShouldBeFound("uniformNoPositionY.lessThan=" + UPDATED_UNIFORM_NO_POSITION_Y);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMModelUniformBottomShouldBeFound(String filter) throws Exception {
        restMModelUniformBottomMockMvc.perform(get("/api/m-model-uniform-bottoms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelUniformBottom.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniformBottomId").value(hasItem(DEFAULT_UNIFORM_BOTTOM_ID)))
            .andExpect(jsonPath("$.[*].defaultDressingType").value(hasItem(DEFAULT_DEFAULT_DRESSING_TYPE)))
            .andExpect(jsonPath("$.[*].uniformModel").value(hasItem(DEFAULT_UNIFORM_MODEL)))
            .andExpect(jsonPath("$.[*].uniformTexture").value(hasItem(DEFAULT_UNIFORM_TEXTURE)))
            .andExpect(jsonPath("$.[*].uniformNoTexture").value(hasItem(DEFAULT_UNIFORM_NO_TEXTURE)))
            .andExpect(jsonPath("$.[*].defaultColor").value(hasItem(DEFAULT_DEFAULT_COLOR.toString())))
            .andExpect(jsonPath("$.[*].uniformNoColor").value(hasItem(DEFAULT_UNIFORM_NO_COLOR.toString())))
            .andExpect(jsonPath("$.[*].numberRightLeg").value(hasItem(DEFAULT_NUMBER_RIGHT_LEG)))
            .andExpect(jsonPath("$.[*].numberLeftLeg").value(hasItem(DEFAULT_NUMBER_LEFT_LEG)))
            .andExpect(jsonPath("$.[*].uniformNoPositionX").value(hasItem(DEFAULT_UNIFORM_NO_POSITION_X)))
            .andExpect(jsonPath("$.[*].uniformNoPositionY").value(hasItem(DEFAULT_UNIFORM_NO_POSITION_Y)));

        // Check, that the count call also returns 1
        restMModelUniformBottomMockMvc.perform(get("/api/m-model-uniform-bottoms/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMModelUniformBottomShouldNotBeFound(String filter) throws Exception {
        restMModelUniformBottomMockMvc.perform(get("/api/m-model-uniform-bottoms?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMModelUniformBottomMockMvc.perform(get("/api/m-model-uniform-bottoms/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMModelUniformBottom() throws Exception {
        // Get the mModelUniformBottom
        restMModelUniformBottomMockMvc.perform(get("/api/m-model-uniform-bottoms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMModelUniformBottom() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        int databaseSizeBeforeUpdate = mModelUniformBottomRepository.findAll().size();

        // Update the mModelUniformBottom
        MModelUniformBottom updatedMModelUniformBottom = mModelUniformBottomRepository.findById(mModelUniformBottom.getId()).get();
        // Disconnect from session so that the updates on updatedMModelUniformBottom are not directly saved in db
        em.detach(updatedMModelUniformBottom);
        updatedMModelUniformBottom
            .uniformBottomId(UPDATED_UNIFORM_BOTTOM_ID)
            .defaultDressingType(UPDATED_DEFAULT_DRESSING_TYPE)
            .uniformModel(UPDATED_UNIFORM_MODEL)
            .uniformTexture(UPDATED_UNIFORM_TEXTURE)
            .uniformNoTexture(UPDATED_UNIFORM_NO_TEXTURE)
            .defaultColor(UPDATED_DEFAULT_COLOR)
            .uniformNoColor(UPDATED_UNIFORM_NO_COLOR)
            .numberRightLeg(UPDATED_NUMBER_RIGHT_LEG)
            .numberLeftLeg(UPDATED_NUMBER_LEFT_LEG)
            .uniformNoPositionX(UPDATED_UNIFORM_NO_POSITION_X)
            .uniformNoPositionY(UPDATED_UNIFORM_NO_POSITION_Y);
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(updatedMModelUniformBottom);

        restMModelUniformBottomMockMvc.perform(put("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isOk());

        // Validate the MModelUniformBottom in the database
        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeUpdate);
        MModelUniformBottom testMModelUniformBottom = mModelUniformBottomList.get(mModelUniformBottomList.size() - 1);
        assertThat(testMModelUniformBottom.getUniformBottomId()).isEqualTo(UPDATED_UNIFORM_BOTTOM_ID);
        assertThat(testMModelUniformBottom.getDefaultDressingType()).isEqualTo(UPDATED_DEFAULT_DRESSING_TYPE);
        assertThat(testMModelUniformBottom.getUniformModel()).isEqualTo(UPDATED_UNIFORM_MODEL);
        assertThat(testMModelUniformBottom.getUniformTexture()).isEqualTo(UPDATED_UNIFORM_TEXTURE);
        assertThat(testMModelUniformBottom.getUniformNoTexture()).isEqualTo(UPDATED_UNIFORM_NO_TEXTURE);
        assertThat(testMModelUniformBottom.getDefaultColor()).isEqualTo(UPDATED_DEFAULT_COLOR);
        assertThat(testMModelUniformBottom.getUniformNoColor()).isEqualTo(UPDATED_UNIFORM_NO_COLOR);
        assertThat(testMModelUniformBottom.getNumberRightLeg()).isEqualTo(UPDATED_NUMBER_RIGHT_LEG);
        assertThat(testMModelUniformBottom.getNumberLeftLeg()).isEqualTo(UPDATED_NUMBER_LEFT_LEG);
        assertThat(testMModelUniformBottom.getUniformNoPositionX()).isEqualTo(UPDATED_UNIFORM_NO_POSITION_X);
        assertThat(testMModelUniformBottom.getUniformNoPositionY()).isEqualTo(UPDATED_UNIFORM_NO_POSITION_Y);
    }

    @Test
    @Transactional
    public void updateNonExistingMModelUniformBottom() throws Exception {
        int databaseSizeBeforeUpdate = mModelUniformBottomRepository.findAll().size();

        // Create the MModelUniformBottom
        MModelUniformBottomDTO mModelUniformBottomDTO = mModelUniformBottomMapper.toDto(mModelUniformBottom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMModelUniformBottomMockMvc.perform(put("/api/m-model-uniform-bottoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelUniformBottom in the database
        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMModelUniformBottom() throws Exception {
        // Initialize the database
        mModelUniformBottomRepository.saveAndFlush(mModelUniformBottom);

        int databaseSizeBeforeDelete = mModelUniformBottomRepository.findAll().size();

        // Delete the mModelUniformBottom
        restMModelUniformBottomMockMvc.perform(delete("/api/m-model-uniform-bottoms/{id}", mModelUniformBottom.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MModelUniformBottom> mModelUniformBottomList = mModelUniformBottomRepository.findAll();
        assertThat(mModelUniformBottomList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelUniformBottom.class);
        MModelUniformBottom mModelUniformBottom1 = new MModelUniformBottom();
        mModelUniformBottom1.setId(1L);
        MModelUniformBottom mModelUniformBottom2 = new MModelUniformBottom();
        mModelUniformBottom2.setId(mModelUniformBottom1.getId());
        assertThat(mModelUniformBottom1).isEqualTo(mModelUniformBottom2);
        mModelUniformBottom2.setId(2L);
        assertThat(mModelUniformBottom1).isNotEqualTo(mModelUniformBottom2);
        mModelUniformBottom1.setId(null);
        assertThat(mModelUniformBottom1).isNotEqualTo(mModelUniformBottom2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelUniformBottomDTO.class);
        MModelUniformBottomDTO mModelUniformBottomDTO1 = new MModelUniformBottomDTO();
        mModelUniformBottomDTO1.setId(1L);
        MModelUniformBottomDTO mModelUniformBottomDTO2 = new MModelUniformBottomDTO();
        assertThat(mModelUniformBottomDTO1).isNotEqualTo(mModelUniformBottomDTO2);
        mModelUniformBottomDTO2.setId(mModelUniformBottomDTO1.getId());
        assertThat(mModelUniformBottomDTO1).isEqualTo(mModelUniformBottomDTO2);
        mModelUniformBottomDTO2.setId(2L);
        assertThat(mModelUniformBottomDTO1).isNotEqualTo(mModelUniformBottomDTO2);
        mModelUniformBottomDTO1.setId(null);
        assertThat(mModelUniformBottomDTO1).isNotEqualTo(mModelUniformBottomDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mModelUniformBottomMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mModelUniformBottomMapper.fromId(null)).isNull();
    }
}
