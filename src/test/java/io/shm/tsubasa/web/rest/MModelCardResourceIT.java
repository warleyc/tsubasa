package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MModelCard;
import io.shm.tsubasa.domain.MPlayableCard;
import io.shm.tsubasa.repository.MModelCardRepository;
import io.shm.tsubasa.service.MModelCardService;
import io.shm.tsubasa.service.dto.MModelCardDTO;
import io.shm.tsubasa.service.mapper.MModelCardMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MModelCardCriteria;
import io.shm.tsubasa.service.MModelCardQueryService;

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
 * Integration tests for the {@Link MModelCardResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MModelCardResourceIT {

    private static final Integer DEFAULT_HAIR_MODEL = 1;
    private static final Integer UPDATED_HAIR_MODEL = 2;

    private static final Integer DEFAULT_HAIR_TEXTURE = 1;
    private static final Integer UPDATED_HAIR_TEXTURE = 2;

    private static final Integer DEFAULT_HEAD_MODEL = 1;
    private static final Integer UPDATED_HEAD_MODEL = 2;

    private static final Integer DEFAULT_HEAD_TEXTURE = 1;
    private static final Integer UPDATED_HEAD_TEXTURE = 2;

    private static final Integer DEFAULT_SKIN_TEXTURE = 1;
    private static final Integer UPDATED_SKIN_TEXTURE = 2;

    private static final Integer DEFAULT_SHOES_MODEL = 1;
    private static final Integer UPDATED_SHOES_MODEL = 2;

    private static final Integer DEFAULT_SHOES_TEXTURE = 1;
    private static final Integer UPDATED_SHOES_TEXTURE = 2;

    private static final Integer DEFAULT_GLOBE_TEXTURE = 1;
    private static final Integer UPDATED_GLOBE_TEXTURE = 2;

    private static final Integer DEFAULT_UNIQUE_MODEL = 1;
    private static final Integer UPDATED_UNIQUE_MODEL = 2;

    private static final Integer DEFAULT_UNIQUE_TEXTURE = 1;
    private static final Integer UPDATED_UNIQUE_TEXTURE = 2;

    private static final Integer DEFAULT_DRESSING_TYPE_UP = 1;
    private static final Integer UPDATED_DRESSING_TYPE_UP = 2;

    private static final Integer DEFAULT_DRESSING_TYPE_BOTTOM = 1;
    private static final Integer UPDATED_DRESSING_TYPE_BOTTOM = 2;

    private static final Integer DEFAULT_HEIGHT = 1;
    private static final Integer UPDATED_HEIGHT = 2;

    private static final Integer DEFAULT_WIDTH = 1;
    private static final Integer UPDATED_WIDTH = 2;

    @Autowired
    private MModelCardRepository mModelCardRepository;

    @Autowired
    private MModelCardMapper mModelCardMapper;

    @Autowired
    private MModelCardService mModelCardService;

    @Autowired
    private MModelCardQueryService mModelCardQueryService;

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

    private MockMvc restMModelCardMockMvc;

    private MModelCard mModelCard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MModelCardResource mModelCardResource = new MModelCardResource(mModelCardService, mModelCardQueryService);
        this.restMModelCardMockMvc = MockMvcBuilders.standaloneSetup(mModelCardResource)
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
    public static MModelCard createEntity(EntityManager em) {
        MModelCard mModelCard = new MModelCard()
            .hairModel(DEFAULT_HAIR_MODEL)
            .hairTexture(DEFAULT_HAIR_TEXTURE)
            .headModel(DEFAULT_HEAD_MODEL)
            .headTexture(DEFAULT_HEAD_TEXTURE)
            .skinTexture(DEFAULT_SKIN_TEXTURE)
            .shoesModel(DEFAULT_SHOES_MODEL)
            .shoesTexture(DEFAULT_SHOES_TEXTURE)
            .globeTexture(DEFAULT_GLOBE_TEXTURE)
            .uniqueModel(DEFAULT_UNIQUE_MODEL)
            .uniqueTexture(DEFAULT_UNIQUE_TEXTURE)
            .dressingTypeUp(DEFAULT_DRESSING_TYPE_UP)
            .dressingTypeBottom(DEFAULT_DRESSING_TYPE_BOTTOM)
            .height(DEFAULT_HEIGHT)
            .width(DEFAULT_WIDTH);
        return mModelCard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MModelCard createUpdatedEntity(EntityManager em) {
        MModelCard mModelCard = new MModelCard()
            .hairModel(UPDATED_HAIR_MODEL)
            .hairTexture(UPDATED_HAIR_TEXTURE)
            .headModel(UPDATED_HEAD_MODEL)
            .headTexture(UPDATED_HEAD_TEXTURE)
            .skinTexture(UPDATED_SKIN_TEXTURE)
            .shoesModel(UPDATED_SHOES_MODEL)
            .shoesTexture(UPDATED_SHOES_TEXTURE)
            .globeTexture(UPDATED_GLOBE_TEXTURE)
            .uniqueModel(UPDATED_UNIQUE_MODEL)
            .uniqueTexture(UPDATED_UNIQUE_TEXTURE)
            .dressingTypeUp(UPDATED_DRESSING_TYPE_UP)
            .dressingTypeBottom(UPDATED_DRESSING_TYPE_BOTTOM)
            .height(UPDATED_HEIGHT)
            .width(UPDATED_WIDTH);
        return mModelCard;
    }

    @BeforeEach
    public void initTest() {
        mModelCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createMModelCard() throws Exception {
        int databaseSizeBeforeCreate = mModelCardRepository.findAll().size();

        // Create the MModelCard
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);
        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isCreated());

        // Validate the MModelCard in the database
        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeCreate + 1);
        MModelCard testMModelCard = mModelCardList.get(mModelCardList.size() - 1);
        assertThat(testMModelCard.getHairModel()).isEqualTo(DEFAULT_HAIR_MODEL);
        assertThat(testMModelCard.getHairTexture()).isEqualTo(DEFAULT_HAIR_TEXTURE);
        assertThat(testMModelCard.getHeadModel()).isEqualTo(DEFAULT_HEAD_MODEL);
        assertThat(testMModelCard.getHeadTexture()).isEqualTo(DEFAULT_HEAD_TEXTURE);
        assertThat(testMModelCard.getSkinTexture()).isEqualTo(DEFAULT_SKIN_TEXTURE);
        assertThat(testMModelCard.getShoesModel()).isEqualTo(DEFAULT_SHOES_MODEL);
        assertThat(testMModelCard.getShoesTexture()).isEqualTo(DEFAULT_SHOES_TEXTURE);
        assertThat(testMModelCard.getGlobeTexture()).isEqualTo(DEFAULT_GLOBE_TEXTURE);
        assertThat(testMModelCard.getUniqueModel()).isEqualTo(DEFAULT_UNIQUE_MODEL);
        assertThat(testMModelCard.getUniqueTexture()).isEqualTo(DEFAULT_UNIQUE_TEXTURE);
        assertThat(testMModelCard.getDressingTypeUp()).isEqualTo(DEFAULT_DRESSING_TYPE_UP);
        assertThat(testMModelCard.getDressingTypeBottom()).isEqualTo(DEFAULT_DRESSING_TYPE_BOTTOM);
        assertThat(testMModelCard.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testMModelCard.getWidth()).isEqualTo(DEFAULT_WIDTH);
    }

    @Test
    @Transactional
    public void createMModelCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mModelCardRepository.findAll().size();

        // Create the MModelCard with an existing ID
        mModelCard.setId(1L);
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelCard in the database
        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkHairModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setHairModel(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHairTextureIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setHairTexture(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeadModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setHeadModel(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeadTextureIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setHeadTexture(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSkinTextureIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setSkinTexture(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShoesModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setShoesModel(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShoesTextureIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setShoesTexture(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDressingTypeUpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setDressingTypeUp(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDressingTypeBottomIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setDressingTypeBottom(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setHeight(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelCardRepository.findAll().size();
        // set the field null
        mModelCard.setWidth(null);

        // Create the MModelCard, which fails.
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        restMModelCardMockMvc.perform(post("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMModelCards() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList
        restMModelCardMockMvc.perform(get("/api/m-model-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].hairModel").value(hasItem(DEFAULT_HAIR_MODEL)))
            .andExpect(jsonPath("$.[*].hairTexture").value(hasItem(DEFAULT_HAIR_TEXTURE)))
            .andExpect(jsonPath("$.[*].headModel").value(hasItem(DEFAULT_HEAD_MODEL)))
            .andExpect(jsonPath("$.[*].headTexture").value(hasItem(DEFAULT_HEAD_TEXTURE)))
            .andExpect(jsonPath("$.[*].skinTexture").value(hasItem(DEFAULT_SKIN_TEXTURE)))
            .andExpect(jsonPath("$.[*].shoesModel").value(hasItem(DEFAULT_SHOES_MODEL)))
            .andExpect(jsonPath("$.[*].shoesTexture").value(hasItem(DEFAULT_SHOES_TEXTURE)))
            .andExpect(jsonPath("$.[*].globeTexture").value(hasItem(DEFAULT_GLOBE_TEXTURE)))
            .andExpect(jsonPath("$.[*].uniqueModel").value(hasItem(DEFAULT_UNIQUE_MODEL)))
            .andExpect(jsonPath("$.[*].uniqueTexture").value(hasItem(DEFAULT_UNIQUE_TEXTURE)))
            .andExpect(jsonPath("$.[*].dressingTypeUp").value(hasItem(DEFAULT_DRESSING_TYPE_UP)))
            .andExpect(jsonPath("$.[*].dressingTypeBottom").value(hasItem(DEFAULT_DRESSING_TYPE_BOTTOM)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)));
    }
    
    @Test
    @Transactional
    public void getMModelCard() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get the mModelCard
        restMModelCardMockMvc.perform(get("/api/m-model-cards/{id}", mModelCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mModelCard.getId().intValue()))
            .andExpect(jsonPath("$.hairModel").value(DEFAULT_HAIR_MODEL))
            .andExpect(jsonPath("$.hairTexture").value(DEFAULT_HAIR_TEXTURE))
            .andExpect(jsonPath("$.headModel").value(DEFAULT_HEAD_MODEL))
            .andExpect(jsonPath("$.headTexture").value(DEFAULT_HEAD_TEXTURE))
            .andExpect(jsonPath("$.skinTexture").value(DEFAULT_SKIN_TEXTURE))
            .andExpect(jsonPath("$.shoesModel").value(DEFAULT_SHOES_MODEL))
            .andExpect(jsonPath("$.shoesTexture").value(DEFAULT_SHOES_TEXTURE))
            .andExpect(jsonPath("$.globeTexture").value(DEFAULT_GLOBE_TEXTURE))
            .andExpect(jsonPath("$.uniqueModel").value(DEFAULT_UNIQUE_MODEL))
            .andExpect(jsonPath("$.uniqueTexture").value(DEFAULT_UNIQUE_TEXTURE))
            .andExpect(jsonPath("$.dressingTypeUp").value(DEFAULT_DRESSING_TYPE_UP))
            .andExpect(jsonPath("$.dressingTypeBottom").value(DEFAULT_DRESSING_TYPE_BOTTOM))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH));
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHairModelIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairModel equals to DEFAULT_HAIR_MODEL
        defaultMModelCardShouldBeFound("hairModel.equals=" + DEFAULT_HAIR_MODEL);

        // Get all the mModelCardList where hairModel equals to UPDATED_HAIR_MODEL
        defaultMModelCardShouldNotBeFound("hairModel.equals=" + UPDATED_HAIR_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHairModelIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairModel in DEFAULT_HAIR_MODEL or UPDATED_HAIR_MODEL
        defaultMModelCardShouldBeFound("hairModel.in=" + DEFAULT_HAIR_MODEL + "," + UPDATED_HAIR_MODEL);

        // Get all the mModelCardList where hairModel equals to UPDATED_HAIR_MODEL
        defaultMModelCardShouldNotBeFound("hairModel.in=" + UPDATED_HAIR_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHairModelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairModel is not null
        defaultMModelCardShouldBeFound("hairModel.specified=true");

        // Get all the mModelCardList where hairModel is null
        defaultMModelCardShouldNotBeFound("hairModel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHairModelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairModel greater than or equals to DEFAULT_HAIR_MODEL
        defaultMModelCardShouldBeFound("hairModel.greaterOrEqualThan=" + DEFAULT_HAIR_MODEL);

        // Get all the mModelCardList where hairModel greater than or equals to UPDATED_HAIR_MODEL
        defaultMModelCardShouldNotBeFound("hairModel.greaterOrEqualThan=" + UPDATED_HAIR_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHairModelIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairModel less than or equals to DEFAULT_HAIR_MODEL
        defaultMModelCardShouldNotBeFound("hairModel.lessThan=" + DEFAULT_HAIR_MODEL);

        // Get all the mModelCardList where hairModel less than or equals to UPDATED_HAIR_MODEL
        defaultMModelCardShouldBeFound("hairModel.lessThan=" + UPDATED_HAIR_MODEL);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByHairTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairTexture equals to DEFAULT_HAIR_TEXTURE
        defaultMModelCardShouldBeFound("hairTexture.equals=" + DEFAULT_HAIR_TEXTURE);

        // Get all the mModelCardList where hairTexture equals to UPDATED_HAIR_TEXTURE
        defaultMModelCardShouldNotBeFound("hairTexture.equals=" + UPDATED_HAIR_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHairTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairTexture in DEFAULT_HAIR_TEXTURE or UPDATED_HAIR_TEXTURE
        defaultMModelCardShouldBeFound("hairTexture.in=" + DEFAULT_HAIR_TEXTURE + "," + UPDATED_HAIR_TEXTURE);

        // Get all the mModelCardList where hairTexture equals to UPDATED_HAIR_TEXTURE
        defaultMModelCardShouldNotBeFound("hairTexture.in=" + UPDATED_HAIR_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHairTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairTexture is not null
        defaultMModelCardShouldBeFound("hairTexture.specified=true");

        // Get all the mModelCardList where hairTexture is null
        defaultMModelCardShouldNotBeFound("hairTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHairTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairTexture greater than or equals to DEFAULT_HAIR_TEXTURE
        defaultMModelCardShouldBeFound("hairTexture.greaterOrEqualThan=" + DEFAULT_HAIR_TEXTURE);

        // Get all the mModelCardList where hairTexture greater than or equals to UPDATED_HAIR_TEXTURE
        defaultMModelCardShouldNotBeFound("hairTexture.greaterOrEqualThan=" + UPDATED_HAIR_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHairTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where hairTexture less than or equals to DEFAULT_HAIR_TEXTURE
        defaultMModelCardShouldNotBeFound("hairTexture.lessThan=" + DEFAULT_HAIR_TEXTURE);

        // Get all the mModelCardList where hairTexture less than or equals to UPDATED_HAIR_TEXTURE
        defaultMModelCardShouldBeFound("hairTexture.lessThan=" + UPDATED_HAIR_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByHeadModelIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headModel equals to DEFAULT_HEAD_MODEL
        defaultMModelCardShouldBeFound("headModel.equals=" + DEFAULT_HEAD_MODEL);

        // Get all the mModelCardList where headModel equals to UPDATED_HEAD_MODEL
        defaultMModelCardShouldNotBeFound("headModel.equals=" + UPDATED_HEAD_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeadModelIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headModel in DEFAULT_HEAD_MODEL or UPDATED_HEAD_MODEL
        defaultMModelCardShouldBeFound("headModel.in=" + DEFAULT_HEAD_MODEL + "," + UPDATED_HEAD_MODEL);

        // Get all the mModelCardList where headModel equals to UPDATED_HEAD_MODEL
        defaultMModelCardShouldNotBeFound("headModel.in=" + UPDATED_HEAD_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeadModelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headModel is not null
        defaultMModelCardShouldBeFound("headModel.specified=true");

        // Get all the mModelCardList where headModel is null
        defaultMModelCardShouldNotBeFound("headModel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeadModelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headModel greater than or equals to DEFAULT_HEAD_MODEL
        defaultMModelCardShouldBeFound("headModel.greaterOrEqualThan=" + DEFAULT_HEAD_MODEL);

        // Get all the mModelCardList where headModel greater than or equals to UPDATED_HEAD_MODEL
        defaultMModelCardShouldNotBeFound("headModel.greaterOrEqualThan=" + UPDATED_HEAD_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeadModelIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headModel less than or equals to DEFAULT_HEAD_MODEL
        defaultMModelCardShouldNotBeFound("headModel.lessThan=" + DEFAULT_HEAD_MODEL);

        // Get all the mModelCardList where headModel less than or equals to UPDATED_HEAD_MODEL
        defaultMModelCardShouldBeFound("headModel.lessThan=" + UPDATED_HEAD_MODEL);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByHeadTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headTexture equals to DEFAULT_HEAD_TEXTURE
        defaultMModelCardShouldBeFound("headTexture.equals=" + DEFAULT_HEAD_TEXTURE);

        // Get all the mModelCardList where headTexture equals to UPDATED_HEAD_TEXTURE
        defaultMModelCardShouldNotBeFound("headTexture.equals=" + UPDATED_HEAD_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeadTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headTexture in DEFAULT_HEAD_TEXTURE or UPDATED_HEAD_TEXTURE
        defaultMModelCardShouldBeFound("headTexture.in=" + DEFAULT_HEAD_TEXTURE + "," + UPDATED_HEAD_TEXTURE);

        // Get all the mModelCardList where headTexture equals to UPDATED_HEAD_TEXTURE
        defaultMModelCardShouldNotBeFound("headTexture.in=" + UPDATED_HEAD_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeadTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headTexture is not null
        defaultMModelCardShouldBeFound("headTexture.specified=true");

        // Get all the mModelCardList where headTexture is null
        defaultMModelCardShouldNotBeFound("headTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeadTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headTexture greater than or equals to DEFAULT_HEAD_TEXTURE
        defaultMModelCardShouldBeFound("headTexture.greaterOrEqualThan=" + DEFAULT_HEAD_TEXTURE);

        // Get all the mModelCardList where headTexture greater than or equals to UPDATED_HEAD_TEXTURE
        defaultMModelCardShouldNotBeFound("headTexture.greaterOrEqualThan=" + UPDATED_HEAD_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeadTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where headTexture less than or equals to DEFAULT_HEAD_TEXTURE
        defaultMModelCardShouldNotBeFound("headTexture.lessThan=" + DEFAULT_HEAD_TEXTURE);

        // Get all the mModelCardList where headTexture less than or equals to UPDATED_HEAD_TEXTURE
        defaultMModelCardShouldBeFound("headTexture.lessThan=" + UPDATED_HEAD_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelCardsBySkinTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where skinTexture equals to DEFAULT_SKIN_TEXTURE
        defaultMModelCardShouldBeFound("skinTexture.equals=" + DEFAULT_SKIN_TEXTURE);

        // Get all the mModelCardList where skinTexture equals to UPDATED_SKIN_TEXTURE
        defaultMModelCardShouldNotBeFound("skinTexture.equals=" + UPDATED_SKIN_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsBySkinTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where skinTexture in DEFAULT_SKIN_TEXTURE or UPDATED_SKIN_TEXTURE
        defaultMModelCardShouldBeFound("skinTexture.in=" + DEFAULT_SKIN_TEXTURE + "," + UPDATED_SKIN_TEXTURE);

        // Get all the mModelCardList where skinTexture equals to UPDATED_SKIN_TEXTURE
        defaultMModelCardShouldNotBeFound("skinTexture.in=" + UPDATED_SKIN_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsBySkinTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where skinTexture is not null
        defaultMModelCardShouldBeFound("skinTexture.specified=true");

        // Get all the mModelCardList where skinTexture is null
        defaultMModelCardShouldNotBeFound("skinTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsBySkinTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where skinTexture greater than or equals to DEFAULT_SKIN_TEXTURE
        defaultMModelCardShouldBeFound("skinTexture.greaterOrEqualThan=" + DEFAULT_SKIN_TEXTURE);

        // Get all the mModelCardList where skinTexture greater than or equals to UPDATED_SKIN_TEXTURE
        defaultMModelCardShouldNotBeFound("skinTexture.greaterOrEqualThan=" + UPDATED_SKIN_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsBySkinTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where skinTexture less than or equals to DEFAULT_SKIN_TEXTURE
        defaultMModelCardShouldNotBeFound("skinTexture.lessThan=" + DEFAULT_SKIN_TEXTURE);

        // Get all the mModelCardList where skinTexture less than or equals to UPDATED_SKIN_TEXTURE
        defaultMModelCardShouldBeFound("skinTexture.lessThan=" + UPDATED_SKIN_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByShoesModelIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesModel equals to DEFAULT_SHOES_MODEL
        defaultMModelCardShouldBeFound("shoesModel.equals=" + DEFAULT_SHOES_MODEL);

        // Get all the mModelCardList where shoesModel equals to UPDATED_SHOES_MODEL
        defaultMModelCardShouldNotBeFound("shoesModel.equals=" + UPDATED_SHOES_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByShoesModelIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesModel in DEFAULT_SHOES_MODEL or UPDATED_SHOES_MODEL
        defaultMModelCardShouldBeFound("shoesModel.in=" + DEFAULT_SHOES_MODEL + "," + UPDATED_SHOES_MODEL);

        // Get all the mModelCardList where shoesModel equals to UPDATED_SHOES_MODEL
        defaultMModelCardShouldNotBeFound("shoesModel.in=" + UPDATED_SHOES_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByShoesModelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesModel is not null
        defaultMModelCardShouldBeFound("shoesModel.specified=true");

        // Get all the mModelCardList where shoesModel is null
        defaultMModelCardShouldNotBeFound("shoesModel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByShoesModelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesModel greater than or equals to DEFAULT_SHOES_MODEL
        defaultMModelCardShouldBeFound("shoesModel.greaterOrEqualThan=" + DEFAULT_SHOES_MODEL);

        // Get all the mModelCardList where shoesModel greater than or equals to UPDATED_SHOES_MODEL
        defaultMModelCardShouldNotBeFound("shoesModel.greaterOrEqualThan=" + UPDATED_SHOES_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByShoesModelIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesModel less than or equals to DEFAULT_SHOES_MODEL
        defaultMModelCardShouldNotBeFound("shoesModel.lessThan=" + DEFAULT_SHOES_MODEL);

        // Get all the mModelCardList where shoesModel less than or equals to UPDATED_SHOES_MODEL
        defaultMModelCardShouldBeFound("shoesModel.lessThan=" + UPDATED_SHOES_MODEL);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByShoesTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesTexture equals to DEFAULT_SHOES_TEXTURE
        defaultMModelCardShouldBeFound("shoesTexture.equals=" + DEFAULT_SHOES_TEXTURE);

        // Get all the mModelCardList where shoesTexture equals to UPDATED_SHOES_TEXTURE
        defaultMModelCardShouldNotBeFound("shoesTexture.equals=" + UPDATED_SHOES_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByShoesTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesTexture in DEFAULT_SHOES_TEXTURE or UPDATED_SHOES_TEXTURE
        defaultMModelCardShouldBeFound("shoesTexture.in=" + DEFAULT_SHOES_TEXTURE + "," + UPDATED_SHOES_TEXTURE);

        // Get all the mModelCardList where shoesTexture equals to UPDATED_SHOES_TEXTURE
        defaultMModelCardShouldNotBeFound("shoesTexture.in=" + UPDATED_SHOES_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByShoesTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesTexture is not null
        defaultMModelCardShouldBeFound("shoesTexture.specified=true");

        // Get all the mModelCardList where shoesTexture is null
        defaultMModelCardShouldNotBeFound("shoesTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByShoesTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesTexture greater than or equals to DEFAULT_SHOES_TEXTURE
        defaultMModelCardShouldBeFound("shoesTexture.greaterOrEqualThan=" + DEFAULT_SHOES_TEXTURE);

        // Get all the mModelCardList where shoesTexture greater than or equals to UPDATED_SHOES_TEXTURE
        defaultMModelCardShouldNotBeFound("shoesTexture.greaterOrEqualThan=" + UPDATED_SHOES_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByShoesTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where shoesTexture less than or equals to DEFAULT_SHOES_TEXTURE
        defaultMModelCardShouldNotBeFound("shoesTexture.lessThan=" + DEFAULT_SHOES_TEXTURE);

        // Get all the mModelCardList where shoesTexture less than or equals to UPDATED_SHOES_TEXTURE
        defaultMModelCardShouldBeFound("shoesTexture.lessThan=" + UPDATED_SHOES_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByGlobeTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where globeTexture equals to DEFAULT_GLOBE_TEXTURE
        defaultMModelCardShouldBeFound("globeTexture.equals=" + DEFAULT_GLOBE_TEXTURE);

        // Get all the mModelCardList where globeTexture equals to UPDATED_GLOBE_TEXTURE
        defaultMModelCardShouldNotBeFound("globeTexture.equals=" + UPDATED_GLOBE_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByGlobeTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where globeTexture in DEFAULT_GLOBE_TEXTURE or UPDATED_GLOBE_TEXTURE
        defaultMModelCardShouldBeFound("globeTexture.in=" + DEFAULT_GLOBE_TEXTURE + "," + UPDATED_GLOBE_TEXTURE);

        // Get all the mModelCardList where globeTexture equals to UPDATED_GLOBE_TEXTURE
        defaultMModelCardShouldNotBeFound("globeTexture.in=" + UPDATED_GLOBE_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByGlobeTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where globeTexture is not null
        defaultMModelCardShouldBeFound("globeTexture.specified=true");

        // Get all the mModelCardList where globeTexture is null
        defaultMModelCardShouldNotBeFound("globeTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByGlobeTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where globeTexture greater than or equals to DEFAULT_GLOBE_TEXTURE
        defaultMModelCardShouldBeFound("globeTexture.greaterOrEqualThan=" + DEFAULT_GLOBE_TEXTURE);

        // Get all the mModelCardList where globeTexture greater than or equals to UPDATED_GLOBE_TEXTURE
        defaultMModelCardShouldNotBeFound("globeTexture.greaterOrEqualThan=" + UPDATED_GLOBE_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByGlobeTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where globeTexture less than or equals to DEFAULT_GLOBE_TEXTURE
        defaultMModelCardShouldNotBeFound("globeTexture.lessThan=" + DEFAULT_GLOBE_TEXTURE);

        // Get all the mModelCardList where globeTexture less than or equals to UPDATED_GLOBE_TEXTURE
        defaultMModelCardShouldBeFound("globeTexture.lessThan=" + UPDATED_GLOBE_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByUniqueModelIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueModel equals to DEFAULT_UNIQUE_MODEL
        defaultMModelCardShouldBeFound("uniqueModel.equals=" + DEFAULT_UNIQUE_MODEL);

        // Get all the mModelCardList where uniqueModel equals to UPDATED_UNIQUE_MODEL
        defaultMModelCardShouldNotBeFound("uniqueModel.equals=" + UPDATED_UNIQUE_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByUniqueModelIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueModel in DEFAULT_UNIQUE_MODEL or UPDATED_UNIQUE_MODEL
        defaultMModelCardShouldBeFound("uniqueModel.in=" + DEFAULT_UNIQUE_MODEL + "," + UPDATED_UNIQUE_MODEL);

        // Get all the mModelCardList where uniqueModel equals to UPDATED_UNIQUE_MODEL
        defaultMModelCardShouldNotBeFound("uniqueModel.in=" + UPDATED_UNIQUE_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByUniqueModelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueModel is not null
        defaultMModelCardShouldBeFound("uniqueModel.specified=true");

        // Get all the mModelCardList where uniqueModel is null
        defaultMModelCardShouldNotBeFound("uniqueModel.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByUniqueModelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueModel greater than or equals to DEFAULT_UNIQUE_MODEL
        defaultMModelCardShouldBeFound("uniqueModel.greaterOrEqualThan=" + DEFAULT_UNIQUE_MODEL);

        // Get all the mModelCardList where uniqueModel greater than or equals to UPDATED_UNIQUE_MODEL
        defaultMModelCardShouldNotBeFound("uniqueModel.greaterOrEqualThan=" + UPDATED_UNIQUE_MODEL);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByUniqueModelIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueModel less than or equals to DEFAULT_UNIQUE_MODEL
        defaultMModelCardShouldNotBeFound("uniqueModel.lessThan=" + DEFAULT_UNIQUE_MODEL);

        // Get all the mModelCardList where uniqueModel less than or equals to UPDATED_UNIQUE_MODEL
        defaultMModelCardShouldBeFound("uniqueModel.lessThan=" + UPDATED_UNIQUE_MODEL);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByUniqueTextureIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueTexture equals to DEFAULT_UNIQUE_TEXTURE
        defaultMModelCardShouldBeFound("uniqueTexture.equals=" + DEFAULT_UNIQUE_TEXTURE);

        // Get all the mModelCardList where uniqueTexture equals to UPDATED_UNIQUE_TEXTURE
        defaultMModelCardShouldNotBeFound("uniqueTexture.equals=" + UPDATED_UNIQUE_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByUniqueTextureIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueTexture in DEFAULT_UNIQUE_TEXTURE or UPDATED_UNIQUE_TEXTURE
        defaultMModelCardShouldBeFound("uniqueTexture.in=" + DEFAULT_UNIQUE_TEXTURE + "," + UPDATED_UNIQUE_TEXTURE);

        // Get all the mModelCardList where uniqueTexture equals to UPDATED_UNIQUE_TEXTURE
        defaultMModelCardShouldNotBeFound("uniqueTexture.in=" + UPDATED_UNIQUE_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByUniqueTextureIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueTexture is not null
        defaultMModelCardShouldBeFound("uniqueTexture.specified=true");

        // Get all the mModelCardList where uniqueTexture is null
        defaultMModelCardShouldNotBeFound("uniqueTexture.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByUniqueTextureIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueTexture greater than or equals to DEFAULT_UNIQUE_TEXTURE
        defaultMModelCardShouldBeFound("uniqueTexture.greaterOrEqualThan=" + DEFAULT_UNIQUE_TEXTURE);

        // Get all the mModelCardList where uniqueTexture greater than or equals to UPDATED_UNIQUE_TEXTURE
        defaultMModelCardShouldNotBeFound("uniqueTexture.greaterOrEqualThan=" + UPDATED_UNIQUE_TEXTURE);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByUniqueTextureIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where uniqueTexture less than or equals to DEFAULT_UNIQUE_TEXTURE
        defaultMModelCardShouldNotBeFound("uniqueTexture.lessThan=" + DEFAULT_UNIQUE_TEXTURE);

        // Get all the mModelCardList where uniqueTexture less than or equals to UPDATED_UNIQUE_TEXTURE
        defaultMModelCardShouldBeFound("uniqueTexture.lessThan=" + UPDATED_UNIQUE_TEXTURE);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeUpIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeUp equals to DEFAULT_DRESSING_TYPE_UP
        defaultMModelCardShouldBeFound("dressingTypeUp.equals=" + DEFAULT_DRESSING_TYPE_UP);

        // Get all the mModelCardList where dressingTypeUp equals to UPDATED_DRESSING_TYPE_UP
        defaultMModelCardShouldNotBeFound("dressingTypeUp.equals=" + UPDATED_DRESSING_TYPE_UP);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeUpIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeUp in DEFAULT_DRESSING_TYPE_UP or UPDATED_DRESSING_TYPE_UP
        defaultMModelCardShouldBeFound("dressingTypeUp.in=" + DEFAULT_DRESSING_TYPE_UP + "," + UPDATED_DRESSING_TYPE_UP);

        // Get all the mModelCardList where dressingTypeUp equals to UPDATED_DRESSING_TYPE_UP
        defaultMModelCardShouldNotBeFound("dressingTypeUp.in=" + UPDATED_DRESSING_TYPE_UP);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeUpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeUp is not null
        defaultMModelCardShouldBeFound("dressingTypeUp.specified=true");

        // Get all the mModelCardList where dressingTypeUp is null
        defaultMModelCardShouldNotBeFound("dressingTypeUp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeUpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeUp greater than or equals to DEFAULT_DRESSING_TYPE_UP
        defaultMModelCardShouldBeFound("dressingTypeUp.greaterOrEqualThan=" + DEFAULT_DRESSING_TYPE_UP);

        // Get all the mModelCardList where dressingTypeUp greater than or equals to UPDATED_DRESSING_TYPE_UP
        defaultMModelCardShouldNotBeFound("dressingTypeUp.greaterOrEqualThan=" + UPDATED_DRESSING_TYPE_UP);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeUpIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeUp less than or equals to DEFAULT_DRESSING_TYPE_UP
        defaultMModelCardShouldNotBeFound("dressingTypeUp.lessThan=" + DEFAULT_DRESSING_TYPE_UP);

        // Get all the mModelCardList where dressingTypeUp less than or equals to UPDATED_DRESSING_TYPE_UP
        defaultMModelCardShouldBeFound("dressingTypeUp.lessThan=" + UPDATED_DRESSING_TYPE_UP);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeBottomIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeBottom equals to DEFAULT_DRESSING_TYPE_BOTTOM
        defaultMModelCardShouldBeFound("dressingTypeBottom.equals=" + DEFAULT_DRESSING_TYPE_BOTTOM);

        // Get all the mModelCardList where dressingTypeBottom equals to UPDATED_DRESSING_TYPE_BOTTOM
        defaultMModelCardShouldNotBeFound("dressingTypeBottom.equals=" + UPDATED_DRESSING_TYPE_BOTTOM);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeBottomIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeBottom in DEFAULT_DRESSING_TYPE_BOTTOM or UPDATED_DRESSING_TYPE_BOTTOM
        defaultMModelCardShouldBeFound("dressingTypeBottom.in=" + DEFAULT_DRESSING_TYPE_BOTTOM + "," + UPDATED_DRESSING_TYPE_BOTTOM);

        // Get all the mModelCardList where dressingTypeBottom equals to UPDATED_DRESSING_TYPE_BOTTOM
        defaultMModelCardShouldNotBeFound("dressingTypeBottom.in=" + UPDATED_DRESSING_TYPE_BOTTOM);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeBottomIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeBottom is not null
        defaultMModelCardShouldBeFound("dressingTypeBottom.specified=true");

        // Get all the mModelCardList where dressingTypeBottom is null
        defaultMModelCardShouldNotBeFound("dressingTypeBottom.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeBottomIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeBottom greater than or equals to DEFAULT_DRESSING_TYPE_BOTTOM
        defaultMModelCardShouldBeFound("dressingTypeBottom.greaterOrEqualThan=" + DEFAULT_DRESSING_TYPE_BOTTOM);

        // Get all the mModelCardList where dressingTypeBottom greater than or equals to UPDATED_DRESSING_TYPE_BOTTOM
        defaultMModelCardShouldNotBeFound("dressingTypeBottom.greaterOrEqualThan=" + UPDATED_DRESSING_TYPE_BOTTOM);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByDressingTypeBottomIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where dressingTypeBottom less than or equals to DEFAULT_DRESSING_TYPE_BOTTOM
        defaultMModelCardShouldNotBeFound("dressingTypeBottom.lessThan=" + DEFAULT_DRESSING_TYPE_BOTTOM);

        // Get all the mModelCardList where dressingTypeBottom less than or equals to UPDATED_DRESSING_TYPE_BOTTOM
        defaultMModelCardShouldBeFound("dressingTypeBottom.lessThan=" + UPDATED_DRESSING_TYPE_BOTTOM);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByHeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where height equals to DEFAULT_HEIGHT
        defaultMModelCardShouldBeFound("height.equals=" + DEFAULT_HEIGHT);

        // Get all the mModelCardList where height equals to UPDATED_HEIGHT
        defaultMModelCardShouldNotBeFound("height.equals=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeightIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where height in DEFAULT_HEIGHT or UPDATED_HEIGHT
        defaultMModelCardShouldBeFound("height.in=" + DEFAULT_HEIGHT + "," + UPDATED_HEIGHT);

        // Get all the mModelCardList where height equals to UPDATED_HEIGHT
        defaultMModelCardShouldNotBeFound("height.in=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where height is not null
        defaultMModelCardShouldBeFound("height.specified=true");

        // Get all the mModelCardList where height is null
        defaultMModelCardShouldNotBeFound("height.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where height greater than or equals to DEFAULT_HEIGHT
        defaultMModelCardShouldBeFound("height.greaterOrEqualThan=" + DEFAULT_HEIGHT);

        // Get all the mModelCardList where height greater than or equals to UPDATED_HEIGHT
        defaultMModelCardShouldNotBeFound("height.greaterOrEqualThan=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByHeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where height less than or equals to DEFAULT_HEIGHT
        defaultMModelCardShouldNotBeFound("height.lessThan=" + DEFAULT_HEIGHT);

        // Get all the mModelCardList where height less than or equals to UPDATED_HEIGHT
        defaultMModelCardShouldBeFound("height.lessThan=" + UPDATED_HEIGHT);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByWidthIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where width equals to DEFAULT_WIDTH
        defaultMModelCardShouldBeFound("width.equals=" + DEFAULT_WIDTH);

        // Get all the mModelCardList where width equals to UPDATED_WIDTH
        defaultMModelCardShouldNotBeFound("width.equals=" + UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByWidthIsInShouldWork() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where width in DEFAULT_WIDTH or UPDATED_WIDTH
        defaultMModelCardShouldBeFound("width.in=" + DEFAULT_WIDTH + "," + UPDATED_WIDTH);

        // Get all the mModelCardList where width equals to UPDATED_WIDTH
        defaultMModelCardShouldNotBeFound("width.in=" + UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByWidthIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where width is not null
        defaultMModelCardShouldBeFound("width.specified=true");

        // Get all the mModelCardList where width is null
        defaultMModelCardShouldNotBeFound("width.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelCardsByWidthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where width greater than or equals to DEFAULT_WIDTH
        defaultMModelCardShouldBeFound("width.greaterOrEqualThan=" + DEFAULT_WIDTH);

        // Get all the mModelCardList where width greater than or equals to UPDATED_WIDTH
        defaultMModelCardShouldNotBeFound("width.greaterOrEqualThan=" + UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void getAllMModelCardsByWidthIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        // Get all the mModelCardList where width less than or equals to DEFAULT_WIDTH
        defaultMModelCardShouldNotBeFound("width.lessThan=" + DEFAULT_WIDTH);

        // Get all the mModelCardList where width less than or equals to UPDATED_WIDTH
        defaultMModelCardShouldBeFound("width.lessThan=" + UPDATED_WIDTH);
    }


    @Test
    @Transactional
    public void getAllMModelCardsByMPlayableCardIsEqualToSomething() throws Exception {
        // Initialize the database
        MPlayableCard mPlayableCard = MPlayableCardResourceIT.createEntity(em);
        em.persist(mPlayableCard);
        em.flush();
        mModelCard.addMPlayableCard(mPlayableCard);
        mModelCardRepository.saveAndFlush(mModelCard);
        Long mPlayableCardId = mPlayableCard.getId();

        // Get all the mModelCardList where mPlayableCard equals to mPlayableCardId
        defaultMModelCardShouldBeFound("mPlayableCardId.equals=" + mPlayableCardId);

        // Get all the mModelCardList where mPlayableCard equals to mPlayableCardId + 1
        defaultMModelCardShouldNotBeFound("mPlayableCardId.equals=" + (mPlayableCardId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMModelCardShouldBeFound(String filter) throws Exception {
        restMModelCardMockMvc.perform(get("/api/m-model-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].hairModel").value(hasItem(DEFAULT_HAIR_MODEL)))
            .andExpect(jsonPath("$.[*].hairTexture").value(hasItem(DEFAULT_HAIR_TEXTURE)))
            .andExpect(jsonPath("$.[*].headModel").value(hasItem(DEFAULT_HEAD_MODEL)))
            .andExpect(jsonPath("$.[*].headTexture").value(hasItem(DEFAULT_HEAD_TEXTURE)))
            .andExpect(jsonPath("$.[*].skinTexture").value(hasItem(DEFAULT_SKIN_TEXTURE)))
            .andExpect(jsonPath("$.[*].shoesModel").value(hasItem(DEFAULT_SHOES_MODEL)))
            .andExpect(jsonPath("$.[*].shoesTexture").value(hasItem(DEFAULT_SHOES_TEXTURE)))
            .andExpect(jsonPath("$.[*].globeTexture").value(hasItem(DEFAULT_GLOBE_TEXTURE)))
            .andExpect(jsonPath("$.[*].uniqueModel").value(hasItem(DEFAULT_UNIQUE_MODEL)))
            .andExpect(jsonPath("$.[*].uniqueTexture").value(hasItem(DEFAULT_UNIQUE_TEXTURE)))
            .andExpect(jsonPath("$.[*].dressingTypeUp").value(hasItem(DEFAULT_DRESSING_TYPE_UP)))
            .andExpect(jsonPath("$.[*].dressingTypeBottom").value(hasItem(DEFAULT_DRESSING_TYPE_BOTTOM)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)));

        // Check, that the count call also returns 1
        restMModelCardMockMvc.perform(get("/api/m-model-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMModelCardShouldNotBeFound(String filter) throws Exception {
        restMModelCardMockMvc.perform(get("/api/m-model-cards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMModelCardMockMvc.perform(get("/api/m-model-cards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMModelCard() throws Exception {
        // Get the mModelCard
        restMModelCardMockMvc.perform(get("/api/m-model-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMModelCard() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        int databaseSizeBeforeUpdate = mModelCardRepository.findAll().size();

        // Update the mModelCard
        MModelCard updatedMModelCard = mModelCardRepository.findById(mModelCard.getId()).get();
        // Disconnect from session so that the updates on updatedMModelCard are not directly saved in db
        em.detach(updatedMModelCard);
        updatedMModelCard
            .hairModel(UPDATED_HAIR_MODEL)
            .hairTexture(UPDATED_HAIR_TEXTURE)
            .headModel(UPDATED_HEAD_MODEL)
            .headTexture(UPDATED_HEAD_TEXTURE)
            .skinTexture(UPDATED_SKIN_TEXTURE)
            .shoesModel(UPDATED_SHOES_MODEL)
            .shoesTexture(UPDATED_SHOES_TEXTURE)
            .globeTexture(UPDATED_GLOBE_TEXTURE)
            .uniqueModel(UPDATED_UNIQUE_MODEL)
            .uniqueTexture(UPDATED_UNIQUE_TEXTURE)
            .dressingTypeUp(UPDATED_DRESSING_TYPE_UP)
            .dressingTypeBottom(UPDATED_DRESSING_TYPE_BOTTOM)
            .height(UPDATED_HEIGHT)
            .width(UPDATED_WIDTH);
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(updatedMModelCard);

        restMModelCardMockMvc.perform(put("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isOk());

        // Validate the MModelCard in the database
        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeUpdate);
        MModelCard testMModelCard = mModelCardList.get(mModelCardList.size() - 1);
        assertThat(testMModelCard.getHairModel()).isEqualTo(UPDATED_HAIR_MODEL);
        assertThat(testMModelCard.getHairTexture()).isEqualTo(UPDATED_HAIR_TEXTURE);
        assertThat(testMModelCard.getHeadModel()).isEqualTo(UPDATED_HEAD_MODEL);
        assertThat(testMModelCard.getHeadTexture()).isEqualTo(UPDATED_HEAD_TEXTURE);
        assertThat(testMModelCard.getSkinTexture()).isEqualTo(UPDATED_SKIN_TEXTURE);
        assertThat(testMModelCard.getShoesModel()).isEqualTo(UPDATED_SHOES_MODEL);
        assertThat(testMModelCard.getShoesTexture()).isEqualTo(UPDATED_SHOES_TEXTURE);
        assertThat(testMModelCard.getGlobeTexture()).isEqualTo(UPDATED_GLOBE_TEXTURE);
        assertThat(testMModelCard.getUniqueModel()).isEqualTo(UPDATED_UNIQUE_MODEL);
        assertThat(testMModelCard.getUniqueTexture()).isEqualTo(UPDATED_UNIQUE_TEXTURE);
        assertThat(testMModelCard.getDressingTypeUp()).isEqualTo(UPDATED_DRESSING_TYPE_UP);
        assertThat(testMModelCard.getDressingTypeBottom()).isEqualTo(UPDATED_DRESSING_TYPE_BOTTOM);
        assertThat(testMModelCard.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testMModelCard.getWidth()).isEqualTo(UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void updateNonExistingMModelCard() throws Exception {
        int databaseSizeBeforeUpdate = mModelCardRepository.findAll().size();

        // Create the MModelCard
        MModelCardDTO mModelCardDTO = mModelCardMapper.toDto(mModelCard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMModelCardMockMvc.perform(put("/api/m-model-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelCard in the database
        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMModelCard() throws Exception {
        // Initialize the database
        mModelCardRepository.saveAndFlush(mModelCard);

        int databaseSizeBeforeDelete = mModelCardRepository.findAll().size();

        // Delete the mModelCard
        restMModelCardMockMvc.perform(delete("/api/m-model-cards/{id}", mModelCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MModelCard> mModelCardList = mModelCardRepository.findAll();
        assertThat(mModelCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelCard.class);
        MModelCard mModelCard1 = new MModelCard();
        mModelCard1.setId(1L);
        MModelCard mModelCard2 = new MModelCard();
        mModelCard2.setId(mModelCard1.getId());
        assertThat(mModelCard1).isEqualTo(mModelCard2);
        mModelCard2.setId(2L);
        assertThat(mModelCard1).isNotEqualTo(mModelCard2);
        mModelCard1.setId(null);
        assertThat(mModelCard1).isNotEqualTo(mModelCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelCardDTO.class);
        MModelCardDTO mModelCardDTO1 = new MModelCardDTO();
        mModelCardDTO1.setId(1L);
        MModelCardDTO mModelCardDTO2 = new MModelCardDTO();
        assertThat(mModelCardDTO1).isNotEqualTo(mModelCardDTO2);
        mModelCardDTO2.setId(mModelCardDTO1.getId());
        assertThat(mModelCardDTO1).isEqualTo(mModelCardDTO2);
        mModelCardDTO2.setId(2L);
        assertThat(mModelCardDTO1).isNotEqualTo(mModelCardDTO2);
        mModelCardDTO1.setId(null);
        assertThat(mModelCardDTO1).isNotEqualTo(mModelCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mModelCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mModelCardMapper.fromId(null)).isNull();
    }
}
