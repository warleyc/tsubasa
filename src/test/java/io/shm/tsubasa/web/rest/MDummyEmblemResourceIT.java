package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDummyEmblem;
import io.shm.tsubasa.domain.MEmblemParts;
import io.shm.tsubasa.repository.MDummyEmblemRepository;
import io.shm.tsubasa.service.MDummyEmblemService;
import io.shm.tsubasa.service.dto.MDummyEmblemDTO;
import io.shm.tsubasa.service.mapper.MDummyEmblemMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDummyEmblemCriteria;
import io.shm.tsubasa.service.MDummyEmblemQueryService;

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
 * Integration tests for the {@Link MDummyEmblemResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDummyEmblemResourceIT {

    private static final Integer DEFAULT_BACKGROUND_ID = 1;
    private static final Integer UPDATED_BACKGROUND_ID = 2;

    private static final String DEFAULT_BACKGROUND_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_UPPER_ID = 1;
    private static final Integer UPDATED_UPPER_ID = 2;

    private static final String DEFAULT_UPPER_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_UPPER_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_MIDDLE_ID = 1;
    private static final Integer UPDATED_MIDDLE_ID = 2;

    private static final String DEFAULT_MIDDLE_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOWER_ID = 1;
    private static final Integer UPDATED_LOWER_ID = 2;

    private static final String DEFAULT_LOWER_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_LOWER_COLOR = "BBBBBBBBBB";

    @Autowired
    private MDummyEmblemRepository mDummyEmblemRepository;

    @Autowired
    private MDummyEmblemMapper mDummyEmblemMapper;

    @Autowired
    private MDummyEmblemService mDummyEmblemService;

    @Autowired
    private MDummyEmblemQueryService mDummyEmblemQueryService;

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

    private MockMvc restMDummyEmblemMockMvc;

    private MDummyEmblem mDummyEmblem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDummyEmblemResource mDummyEmblemResource = new MDummyEmblemResource(mDummyEmblemService, mDummyEmblemQueryService);
        this.restMDummyEmblemMockMvc = MockMvcBuilders.standaloneSetup(mDummyEmblemResource)
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
    public static MDummyEmblem createEntity(EntityManager em) {
        MDummyEmblem mDummyEmblem = new MDummyEmblem()
            .backgroundId(DEFAULT_BACKGROUND_ID)
            .backgroundColor(DEFAULT_BACKGROUND_COLOR)
            .upperId(DEFAULT_UPPER_ID)
            .upperColor(DEFAULT_UPPER_COLOR)
            .middleId(DEFAULT_MIDDLE_ID)
            .middleColor(DEFAULT_MIDDLE_COLOR)
            .lowerId(DEFAULT_LOWER_ID)
            .lowerColor(DEFAULT_LOWER_COLOR);
        // Add required entity
        MEmblemParts mEmblemParts;
        if (TestUtil.findAll(em, MEmblemParts.class).isEmpty()) {
            mEmblemParts = MEmblemPartsResourceIT.createEntity(em);
            em.persist(mEmblemParts);
            em.flush();
        } else {
            mEmblemParts = TestUtil.findAll(em, MEmblemParts.class).get(0);
        }
        mDummyEmblem.setMemblemparts(mEmblemParts);
        return mDummyEmblem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDummyEmblem createUpdatedEntity(EntityManager em) {
        MDummyEmblem mDummyEmblem = new MDummyEmblem()
            .backgroundId(UPDATED_BACKGROUND_ID)
            .backgroundColor(UPDATED_BACKGROUND_COLOR)
            .upperId(UPDATED_UPPER_ID)
            .upperColor(UPDATED_UPPER_COLOR)
            .middleId(UPDATED_MIDDLE_ID)
            .middleColor(UPDATED_MIDDLE_COLOR)
            .lowerId(UPDATED_LOWER_ID)
            .lowerColor(UPDATED_LOWER_COLOR);
        // Add required entity
        MEmblemParts mEmblemParts;
        if (TestUtil.findAll(em, MEmblemParts.class).isEmpty()) {
            mEmblemParts = MEmblemPartsResourceIT.createUpdatedEntity(em);
            em.persist(mEmblemParts);
            em.flush();
        } else {
            mEmblemParts = TestUtil.findAll(em, MEmblemParts.class).get(0);
        }
        mDummyEmblem.setMemblemparts(mEmblemParts);
        return mDummyEmblem;
    }

    @BeforeEach
    public void initTest() {
        mDummyEmblem = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDummyEmblem() throws Exception {
        int databaseSizeBeforeCreate = mDummyEmblemRepository.findAll().size();

        // Create the MDummyEmblem
        MDummyEmblemDTO mDummyEmblemDTO = mDummyEmblemMapper.toDto(mDummyEmblem);
        restMDummyEmblemMockMvc.perform(post("/api/m-dummy-emblems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyEmblemDTO)))
            .andExpect(status().isCreated());

        // Validate the MDummyEmblem in the database
        List<MDummyEmblem> mDummyEmblemList = mDummyEmblemRepository.findAll();
        assertThat(mDummyEmblemList).hasSize(databaseSizeBeforeCreate + 1);
        MDummyEmblem testMDummyEmblem = mDummyEmblemList.get(mDummyEmblemList.size() - 1);
        assertThat(testMDummyEmblem.getBackgroundId()).isEqualTo(DEFAULT_BACKGROUND_ID);
        assertThat(testMDummyEmblem.getBackgroundColor()).isEqualTo(DEFAULT_BACKGROUND_COLOR);
        assertThat(testMDummyEmblem.getUpperId()).isEqualTo(DEFAULT_UPPER_ID);
        assertThat(testMDummyEmblem.getUpperColor()).isEqualTo(DEFAULT_UPPER_COLOR);
        assertThat(testMDummyEmblem.getMiddleId()).isEqualTo(DEFAULT_MIDDLE_ID);
        assertThat(testMDummyEmblem.getMiddleColor()).isEqualTo(DEFAULT_MIDDLE_COLOR);
        assertThat(testMDummyEmblem.getLowerId()).isEqualTo(DEFAULT_LOWER_ID);
        assertThat(testMDummyEmblem.getLowerColor()).isEqualTo(DEFAULT_LOWER_COLOR);
    }

    @Test
    @Transactional
    public void createMDummyEmblemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDummyEmblemRepository.findAll().size();

        // Create the MDummyEmblem with an existing ID
        mDummyEmblem.setId(1L);
        MDummyEmblemDTO mDummyEmblemDTO = mDummyEmblemMapper.toDto(mDummyEmblem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDummyEmblemMockMvc.perform(post("/api/m-dummy-emblems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyEmblemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDummyEmblem in the database
        List<MDummyEmblem> mDummyEmblemList = mDummyEmblemRepository.findAll();
        assertThat(mDummyEmblemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBackgroundIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyEmblemRepository.findAll().size();
        // set the field null
        mDummyEmblem.setBackgroundId(null);

        // Create the MDummyEmblem, which fails.
        MDummyEmblemDTO mDummyEmblemDTO = mDummyEmblemMapper.toDto(mDummyEmblem);

        restMDummyEmblemMockMvc.perform(post("/api/m-dummy-emblems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyEmblemDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyEmblem> mDummyEmblemList = mDummyEmblemRepository.findAll();
        assertThat(mDummyEmblemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMiddleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDummyEmblemRepository.findAll().size();
        // set the field null
        mDummyEmblem.setMiddleId(null);

        // Create the MDummyEmblem, which fails.
        MDummyEmblemDTO mDummyEmblemDTO = mDummyEmblemMapper.toDto(mDummyEmblem);

        restMDummyEmblemMockMvc.perform(post("/api/m-dummy-emblems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyEmblemDTO)))
            .andExpect(status().isBadRequest());

        List<MDummyEmblem> mDummyEmblemList = mDummyEmblemRepository.findAll();
        assertThat(mDummyEmblemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblems() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList
        restMDummyEmblemMockMvc.perform(get("/api/m-dummy-emblems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDummyEmblem.getId().intValue())))
            .andExpect(jsonPath("$.[*].backgroundId").value(hasItem(DEFAULT_BACKGROUND_ID)))
            .andExpect(jsonPath("$.[*].backgroundColor").value(hasItem(DEFAULT_BACKGROUND_COLOR.toString())))
            .andExpect(jsonPath("$.[*].upperId").value(hasItem(DEFAULT_UPPER_ID)))
            .andExpect(jsonPath("$.[*].upperColor").value(hasItem(DEFAULT_UPPER_COLOR.toString())))
            .andExpect(jsonPath("$.[*].middleId").value(hasItem(DEFAULT_MIDDLE_ID)))
            .andExpect(jsonPath("$.[*].middleColor").value(hasItem(DEFAULT_MIDDLE_COLOR.toString())))
            .andExpect(jsonPath("$.[*].lowerId").value(hasItem(DEFAULT_LOWER_ID)))
            .andExpect(jsonPath("$.[*].lowerColor").value(hasItem(DEFAULT_LOWER_COLOR.toString())));
    }
    
    @Test
    @Transactional
    public void getMDummyEmblem() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get the mDummyEmblem
        restMDummyEmblemMockMvc.perform(get("/api/m-dummy-emblems/{id}", mDummyEmblem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDummyEmblem.getId().intValue()))
            .andExpect(jsonPath("$.backgroundId").value(DEFAULT_BACKGROUND_ID))
            .andExpect(jsonPath("$.backgroundColor").value(DEFAULT_BACKGROUND_COLOR.toString()))
            .andExpect(jsonPath("$.upperId").value(DEFAULT_UPPER_ID))
            .andExpect(jsonPath("$.upperColor").value(DEFAULT_UPPER_COLOR.toString()))
            .andExpect(jsonPath("$.middleId").value(DEFAULT_MIDDLE_ID))
            .andExpect(jsonPath("$.middleColor").value(DEFAULT_MIDDLE_COLOR.toString()))
            .andExpect(jsonPath("$.lowerId").value(DEFAULT_LOWER_ID))
            .andExpect(jsonPath("$.lowerColor").value(DEFAULT_LOWER_COLOR.toString()));
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByBackgroundIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where backgroundId equals to DEFAULT_BACKGROUND_ID
        defaultMDummyEmblemShouldBeFound("backgroundId.equals=" + DEFAULT_BACKGROUND_ID);

        // Get all the mDummyEmblemList where backgroundId equals to UPDATED_BACKGROUND_ID
        defaultMDummyEmblemShouldNotBeFound("backgroundId.equals=" + UPDATED_BACKGROUND_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByBackgroundIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where backgroundId in DEFAULT_BACKGROUND_ID or UPDATED_BACKGROUND_ID
        defaultMDummyEmblemShouldBeFound("backgroundId.in=" + DEFAULT_BACKGROUND_ID + "," + UPDATED_BACKGROUND_ID);

        // Get all the mDummyEmblemList where backgroundId equals to UPDATED_BACKGROUND_ID
        defaultMDummyEmblemShouldNotBeFound("backgroundId.in=" + UPDATED_BACKGROUND_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByBackgroundIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where backgroundId is not null
        defaultMDummyEmblemShouldBeFound("backgroundId.specified=true");

        // Get all the mDummyEmblemList where backgroundId is null
        defaultMDummyEmblemShouldNotBeFound("backgroundId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByBackgroundIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where backgroundId greater than or equals to DEFAULT_BACKGROUND_ID
        defaultMDummyEmblemShouldBeFound("backgroundId.greaterOrEqualThan=" + DEFAULT_BACKGROUND_ID);

        // Get all the mDummyEmblemList where backgroundId greater than or equals to UPDATED_BACKGROUND_ID
        defaultMDummyEmblemShouldNotBeFound("backgroundId.greaterOrEqualThan=" + UPDATED_BACKGROUND_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByBackgroundIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where backgroundId less than or equals to DEFAULT_BACKGROUND_ID
        defaultMDummyEmblemShouldNotBeFound("backgroundId.lessThan=" + DEFAULT_BACKGROUND_ID);

        // Get all the mDummyEmblemList where backgroundId less than or equals to UPDATED_BACKGROUND_ID
        defaultMDummyEmblemShouldBeFound("backgroundId.lessThan=" + UPDATED_BACKGROUND_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyEmblemsByUpperIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where upperId equals to DEFAULT_UPPER_ID
        defaultMDummyEmblemShouldBeFound("upperId.equals=" + DEFAULT_UPPER_ID);

        // Get all the mDummyEmblemList where upperId equals to UPDATED_UPPER_ID
        defaultMDummyEmblemShouldNotBeFound("upperId.equals=" + UPDATED_UPPER_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByUpperIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where upperId in DEFAULT_UPPER_ID or UPDATED_UPPER_ID
        defaultMDummyEmblemShouldBeFound("upperId.in=" + DEFAULT_UPPER_ID + "," + UPDATED_UPPER_ID);

        // Get all the mDummyEmblemList where upperId equals to UPDATED_UPPER_ID
        defaultMDummyEmblemShouldNotBeFound("upperId.in=" + UPDATED_UPPER_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByUpperIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where upperId is not null
        defaultMDummyEmblemShouldBeFound("upperId.specified=true");

        // Get all the mDummyEmblemList where upperId is null
        defaultMDummyEmblemShouldNotBeFound("upperId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByUpperIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where upperId greater than or equals to DEFAULT_UPPER_ID
        defaultMDummyEmblemShouldBeFound("upperId.greaterOrEqualThan=" + DEFAULT_UPPER_ID);

        // Get all the mDummyEmblemList where upperId greater than or equals to UPDATED_UPPER_ID
        defaultMDummyEmblemShouldNotBeFound("upperId.greaterOrEqualThan=" + UPDATED_UPPER_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByUpperIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where upperId less than or equals to DEFAULT_UPPER_ID
        defaultMDummyEmblemShouldNotBeFound("upperId.lessThan=" + DEFAULT_UPPER_ID);

        // Get all the mDummyEmblemList where upperId less than or equals to UPDATED_UPPER_ID
        defaultMDummyEmblemShouldBeFound("upperId.lessThan=" + UPDATED_UPPER_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyEmblemsByMiddleIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where middleId equals to DEFAULT_MIDDLE_ID
        defaultMDummyEmblemShouldBeFound("middleId.equals=" + DEFAULT_MIDDLE_ID);

        // Get all the mDummyEmblemList where middleId equals to UPDATED_MIDDLE_ID
        defaultMDummyEmblemShouldNotBeFound("middleId.equals=" + UPDATED_MIDDLE_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByMiddleIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where middleId in DEFAULT_MIDDLE_ID or UPDATED_MIDDLE_ID
        defaultMDummyEmblemShouldBeFound("middleId.in=" + DEFAULT_MIDDLE_ID + "," + UPDATED_MIDDLE_ID);

        // Get all the mDummyEmblemList where middleId equals to UPDATED_MIDDLE_ID
        defaultMDummyEmblemShouldNotBeFound("middleId.in=" + UPDATED_MIDDLE_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByMiddleIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where middleId is not null
        defaultMDummyEmblemShouldBeFound("middleId.specified=true");

        // Get all the mDummyEmblemList where middleId is null
        defaultMDummyEmblemShouldNotBeFound("middleId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByMiddleIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where middleId greater than or equals to DEFAULT_MIDDLE_ID
        defaultMDummyEmblemShouldBeFound("middleId.greaterOrEqualThan=" + DEFAULT_MIDDLE_ID);

        // Get all the mDummyEmblemList where middleId greater than or equals to UPDATED_MIDDLE_ID
        defaultMDummyEmblemShouldNotBeFound("middleId.greaterOrEqualThan=" + UPDATED_MIDDLE_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByMiddleIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where middleId less than or equals to DEFAULT_MIDDLE_ID
        defaultMDummyEmblemShouldNotBeFound("middleId.lessThan=" + DEFAULT_MIDDLE_ID);

        // Get all the mDummyEmblemList where middleId less than or equals to UPDATED_MIDDLE_ID
        defaultMDummyEmblemShouldBeFound("middleId.lessThan=" + UPDATED_MIDDLE_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyEmblemsByLowerIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where lowerId equals to DEFAULT_LOWER_ID
        defaultMDummyEmblemShouldBeFound("lowerId.equals=" + DEFAULT_LOWER_ID);

        // Get all the mDummyEmblemList where lowerId equals to UPDATED_LOWER_ID
        defaultMDummyEmblemShouldNotBeFound("lowerId.equals=" + UPDATED_LOWER_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByLowerIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where lowerId in DEFAULT_LOWER_ID or UPDATED_LOWER_ID
        defaultMDummyEmblemShouldBeFound("lowerId.in=" + DEFAULT_LOWER_ID + "," + UPDATED_LOWER_ID);

        // Get all the mDummyEmblemList where lowerId equals to UPDATED_LOWER_ID
        defaultMDummyEmblemShouldNotBeFound("lowerId.in=" + UPDATED_LOWER_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByLowerIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where lowerId is not null
        defaultMDummyEmblemShouldBeFound("lowerId.specified=true");

        // Get all the mDummyEmblemList where lowerId is null
        defaultMDummyEmblemShouldNotBeFound("lowerId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByLowerIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where lowerId greater than or equals to DEFAULT_LOWER_ID
        defaultMDummyEmblemShouldBeFound("lowerId.greaterOrEqualThan=" + DEFAULT_LOWER_ID);

        // Get all the mDummyEmblemList where lowerId greater than or equals to UPDATED_LOWER_ID
        defaultMDummyEmblemShouldNotBeFound("lowerId.greaterOrEqualThan=" + UPDATED_LOWER_ID);
    }

    @Test
    @Transactional
    public void getAllMDummyEmblemsByLowerIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        // Get all the mDummyEmblemList where lowerId less than or equals to DEFAULT_LOWER_ID
        defaultMDummyEmblemShouldNotBeFound("lowerId.lessThan=" + DEFAULT_LOWER_ID);

        // Get all the mDummyEmblemList where lowerId less than or equals to UPDATED_LOWER_ID
        defaultMDummyEmblemShouldBeFound("lowerId.lessThan=" + UPDATED_LOWER_ID);
    }


    @Test
    @Transactional
    public void getAllMDummyEmblemsByMemblempartsIsEqualToSomething() throws Exception {
        // Get already existing entity
        MEmblemParts memblemparts = mDummyEmblem.getMemblemparts();
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);
        Long memblempartsId = memblemparts.getId();

        // Get all the mDummyEmblemList where memblemparts equals to memblempartsId
        defaultMDummyEmblemShouldBeFound("memblempartsId.equals=" + memblempartsId);

        // Get all the mDummyEmblemList where memblemparts equals to memblempartsId + 1
        defaultMDummyEmblemShouldNotBeFound("memblempartsId.equals=" + (memblempartsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDummyEmblemShouldBeFound(String filter) throws Exception {
        restMDummyEmblemMockMvc.perform(get("/api/m-dummy-emblems?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDummyEmblem.getId().intValue())))
            .andExpect(jsonPath("$.[*].backgroundId").value(hasItem(DEFAULT_BACKGROUND_ID)))
            .andExpect(jsonPath("$.[*].backgroundColor").value(hasItem(DEFAULT_BACKGROUND_COLOR.toString())))
            .andExpect(jsonPath("$.[*].upperId").value(hasItem(DEFAULT_UPPER_ID)))
            .andExpect(jsonPath("$.[*].upperColor").value(hasItem(DEFAULT_UPPER_COLOR.toString())))
            .andExpect(jsonPath("$.[*].middleId").value(hasItem(DEFAULT_MIDDLE_ID)))
            .andExpect(jsonPath("$.[*].middleColor").value(hasItem(DEFAULT_MIDDLE_COLOR.toString())))
            .andExpect(jsonPath("$.[*].lowerId").value(hasItem(DEFAULT_LOWER_ID)))
            .andExpect(jsonPath("$.[*].lowerColor").value(hasItem(DEFAULT_LOWER_COLOR.toString())));

        // Check, that the count call also returns 1
        restMDummyEmblemMockMvc.perform(get("/api/m-dummy-emblems/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDummyEmblemShouldNotBeFound(String filter) throws Exception {
        restMDummyEmblemMockMvc.perform(get("/api/m-dummy-emblems?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDummyEmblemMockMvc.perform(get("/api/m-dummy-emblems/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDummyEmblem() throws Exception {
        // Get the mDummyEmblem
        restMDummyEmblemMockMvc.perform(get("/api/m-dummy-emblems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDummyEmblem() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        int databaseSizeBeforeUpdate = mDummyEmblemRepository.findAll().size();

        // Update the mDummyEmblem
        MDummyEmblem updatedMDummyEmblem = mDummyEmblemRepository.findById(mDummyEmblem.getId()).get();
        // Disconnect from session so that the updates on updatedMDummyEmblem are not directly saved in db
        em.detach(updatedMDummyEmblem);
        updatedMDummyEmblem
            .backgroundId(UPDATED_BACKGROUND_ID)
            .backgroundColor(UPDATED_BACKGROUND_COLOR)
            .upperId(UPDATED_UPPER_ID)
            .upperColor(UPDATED_UPPER_COLOR)
            .middleId(UPDATED_MIDDLE_ID)
            .middleColor(UPDATED_MIDDLE_COLOR)
            .lowerId(UPDATED_LOWER_ID)
            .lowerColor(UPDATED_LOWER_COLOR);
        MDummyEmblemDTO mDummyEmblemDTO = mDummyEmblemMapper.toDto(updatedMDummyEmblem);

        restMDummyEmblemMockMvc.perform(put("/api/m-dummy-emblems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyEmblemDTO)))
            .andExpect(status().isOk());

        // Validate the MDummyEmblem in the database
        List<MDummyEmblem> mDummyEmblemList = mDummyEmblemRepository.findAll();
        assertThat(mDummyEmblemList).hasSize(databaseSizeBeforeUpdate);
        MDummyEmblem testMDummyEmblem = mDummyEmblemList.get(mDummyEmblemList.size() - 1);
        assertThat(testMDummyEmblem.getBackgroundId()).isEqualTo(UPDATED_BACKGROUND_ID);
        assertThat(testMDummyEmblem.getBackgroundColor()).isEqualTo(UPDATED_BACKGROUND_COLOR);
        assertThat(testMDummyEmblem.getUpperId()).isEqualTo(UPDATED_UPPER_ID);
        assertThat(testMDummyEmblem.getUpperColor()).isEqualTo(UPDATED_UPPER_COLOR);
        assertThat(testMDummyEmblem.getMiddleId()).isEqualTo(UPDATED_MIDDLE_ID);
        assertThat(testMDummyEmblem.getMiddleColor()).isEqualTo(UPDATED_MIDDLE_COLOR);
        assertThat(testMDummyEmblem.getLowerId()).isEqualTo(UPDATED_LOWER_ID);
        assertThat(testMDummyEmblem.getLowerColor()).isEqualTo(UPDATED_LOWER_COLOR);
    }

    @Test
    @Transactional
    public void updateNonExistingMDummyEmblem() throws Exception {
        int databaseSizeBeforeUpdate = mDummyEmblemRepository.findAll().size();

        // Create the MDummyEmblem
        MDummyEmblemDTO mDummyEmblemDTO = mDummyEmblemMapper.toDto(mDummyEmblem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDummyEmblemMockMvc.perform(put("/api/m-dummy-emblems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDummyEmblemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDummyEmblem in the database
        List<MDummyEmblem> mDummyEmblemList = mDummyEmblemRepository.findAll();
        assertThat(mDummyEmblemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDummyEmblem() throws Exception {
        // Initialize the database
        mDummyEmblemRepository.saveAndFlush(mDummyEmblem);

        int databaseSizeBeforeDelete = mDummyEmblemRepository.findAll().size();

        // Delete the mDummyEmblem
        restMDummyEmblemMockMvc.perform(delete("/api/m-dummy-emblems/{id}", mDummyEmblem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDummyEmblem> mDummyEmblemList = mDummyEmblemRepository.findAll();
        assertThat(mDummyEmblemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDummyEmblem.class);
        MDummyEmblem mDummyEmblem1 = new MDummyEmblem();
        mDummyEmblem1.setId(1L);
        MDummyEmblem mDummyEmblem2 = new MDummyEmblem();
        mDummyEmblem2.setId(mDummyEmblem1.getId());
        assertThat(mDummyEmblem1).isEqualTo(mDummyEmblem2);
        mDummyEmblem2.setId(2L);
        assertThat(mDummyEmblem1).isNotEqualTo(mDummyEmblem2);
        mDummyEmblem1.setId(null);
        assertThat(mDummyEmblem1).isNotEqualTo(mDummyEmblem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDummyEmblemDTO.class);
        MDummyEmblemDTO mDummyEmblemDTO1 = new MDummyEmblemDTO();
        mDummyEmblemDTO1.setId(1L);
        MDummyEmblemDTO mDummyEmblemDTO2 = new MDummyEmblemDTO();
        assertThat(mDummyEmblemDTO1).isNotEqualTo(mDummyEmblemDTO2);
        mDummyEmblemDTO2.setId(mDummyEmblemDTO1.getId());
        assertThat(mDummyEmblemDTO1).isEqualTo(mDummyEmblemDTO2);
        mDummyEmblemDTO2.setId(2L);
        assertThat(mDummyEmblemDTO1).isNotEqualTo(mDummyEmblemDTO2);
        mDummyEmblemDTO1.setId(null);
        assertThat(mDummyEmblemDTO1).isNotEqualTo(mDummyEmblemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDummyEmblemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDummyEmblemMapper.fromId(null)).isNull();
    }
}
