package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestCoop;
import io.shm.tsubasa.repository.MQuestCoopRepository;
import io.shm.tsubasa.service.MQuestCoopService;
import io.shm.tsubasa.service.dto.MQuestCoopDTO;
import io.shm.tsubasa.service.mapper.MQuestCoopMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestCoopCriteria;
import io.shm.tsubasa.service.MQuestCoopQueryService;

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
 * Integration tests for the {@Link MQuestCoopResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestCoopResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_EFFECT_RARITY = 1;
    private static final Integer UPDATED_EFFECT_RARITY = 2;

    private static final Integer DEFAULT_EFFECT_LEVEL_FROM = 1;
    private static final Integer UPDATED_EFFECT_LEVEL_FROM = 2;

    private static final Integer DEFAULT_EFFECT_LEVEL_TO = 1;
    private static final Integer UPDATED_EFFECT_LEVEL_TO = 2;

    private static final Integer DEFAULT_CHOOSE_1_WEIGHT = 1;
    private static final Integer UPDATED_CHOOSE_1_WEIGHT = 2;

    private static final Integer DEFAULT_CHOOSE_2_WEIGHT = 1;
    private static final Integer UPDATED_CHOOSE_2_WEIGHT = 2;

    @Autowired
    private MQuestCoopRepository mQuestCoopRepository;

    @Autowired
    private MQuestCoopMapper mQuestCoopMapper;

    @Autowired
    private MQuestCoopService mQuestCoopService;

    @Autowired
    private MQuestCoopQueryService mQuestCoopQueryService;

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

    private MockMvc restMQuestCoopMockMvc;

    private MQuestCoop mQuestCoop;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestCoopResource mQuestCoopResource = new MQuestCoopResource(mQuestCoopService, mQuestCoopQueryService);
        this.restMQuestCoopMockMvc = MockMvcBuilders.standaloneSetup(mQuestCoopResource)
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
    public static MQuestCoop createEntity(EntityManager em) {
        MQuestCoop mQuestCoop = new MQuestCoop()
            .groupId(DEFAULT_GROUP_ID)
            .effectRarity(DEFAULT_EFFECT_RARITY)
            .effectLevelFrom(DEFAULT_EFFECT_LEVEL_FROM)
            .effectLevelTo(DEFAULT_EFFECT_LEVEL_TO)
            .choose1Weight(DEFAULT_CHOOSE_1_WEIGHT)
            .choose2Weight(DEFAULT_CHOOSE_2_WEIGHT);
        return mQuestCoop;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestCoop createUpdatedEntity(EntityManager em) {
        MQuestCoop mQuestCoop = new MQuestCoop()
            .groupId(UPDATED_GROUP_ID)
            .effectRarity(UPDATED_EFFECT_RARITY)
            .effectLevelFrom(UPDATED_EFFECT_LEVEL_FROM)
            .effectLevelTo(UPDATED_EFFECT_LEVEL_TO)
            .choose1Weight(UPDATED_CHOOSE_1_WEIGHT)
            .choose2Weight(UPDATED_CHOOSE_2_WEIGHT);
        return mQuestCoop;
    }

    @BeforeEach
    public void initTest() {
        mQuestCoop = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestCoop() throws Exception {
        int databaseSizeBeforeCreate = mQuestCoopRepository.findAll().size();

        // Create the MQuestCoop
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(mQuestCoop);
        restMQuestCoopMockMvc.perform(post("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestCoop in the database
        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestCoop testMQuestCoop = mQuestCoopList.get(mQuestCoopList.size() - 1);
        assertThat(testMQuestCoop.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMQuestCoop.getEffectRarity()).isEqualTo(DEFAULT_EFFECT_RARITY);
        assertThat(testMQuestCoop.getEffectLevelFrom()).isEqualTo(DEFAULT_EFFECT_LEVEL_FROM);
        assertThat(testMQuestCoop.getEffectLevelTo()).isEqualTo(DEFAULT_EFFECT_LEVEL_TO);
        assertThat(testMQuestCoop.getChoose1Weight()).isEqualTo(DEFAULT_CHOOSE_1_WEIGHT);
        assertThat(testMQuestCoop.getChoose2Weight()).isEqualTo(DEFAULT_CHOOSE_2_WEIGHT);
    }

    @Test
    @Transactional
    public void createMQuestCoopWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestCoopRepository.findAll().size();

        // Create the MQuestCoop with an existing ID
        mQuestCoop.setId(1L);
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(mQuestCoop);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestCoopMockMvc.perform(post("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestCoop in the database
        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRepository.findAll().size();
        // set the field null
        mQuestCoop.setGroupId(null);

        // Create the MQuestCoop, which fails.
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(mQuestCoop);

        restMQuestCoopMockMvc.perform(post("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRepository.findAll().size();
        // set the field null
        mQuestCoop.setEffectRarity(null);

        // Create the MQuestCoop, which fails.
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(mQuestCoop);

        restMQuestCoopMockMvc.perform(post("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectLevelFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRepository.findAll().size();
        // set the field null
        mQuestCoop.setEffectLevelFrom(null);

        // Create the MQuestCoop, which fails.
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(mQuestCoop);

        restMQuestCoopMockMvc.perform(post("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectLevelToIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRepository.findAll().size();
        // set the field null
        mQuestCoop.setEffectLevelTo(null);

        // Create the MQuestCoop, which fails.
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(mQuestCoop);

        restMQuestCoopMockMvc.perform(post("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChoose1WeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRepository.findAll().size();
        // set the field null
        mQuestCoop.setChoose1Weight(null);

        // Create the MQuestCoop, which fails.
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(mQuestCoop);

        restMQuestCoopMockMvc.perform(post("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChoose2WeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestCoopRepository.findAll().size();
        // set the field null
        mQuestCoop.setChoose2Weight(null);

        // Create the MQuestCoop, which fails.
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(mQuestCoop);

        restMQuestCoopMockMvc.perform(post("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestCoops() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList
        restMQuestCoopMockMvc.perform(get("/api/m-quest-coops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestCoop.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].effectRarity").value(hasItem(DEFAULT_EFFECT_RARITY)))
            .andExpect(jsonPath("$.[*].effectLevelFrom").value(hasItem(DEFAULT_EFFECT_LEVEL_FROM)))
            .andExpect(jsonPath("$.[*].effectLevelTo").value(hasItem(DEFAULT_EFFECT_LEVEL_TO)))
            .andExpect(jsonPath("$.[*].choose1Weight").value(hasItem(DEFAULT_CHOOSE_1_WEIGHT)))
            .andExpect(jsonPath("$.[*].choose2Weight").value(hasItem(DEFAULT_CHOOSE_2_WEIGHT)));
    }
    
    @Test
    @Transactional
    public void getMQuestCoop() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get the mQuestCoop
        restMQuestCoopMockMvc.perform(get("/api/m-quest-coops/{id}", mQuestCoop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestCoop.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.effectRarity").value(DEFAULT_EFFECT_RARITY))
            .andExpect(jsonPath("$.effectLevelFrom").value(DEFAULT_EFFECT_LEVEL_FROM))
            .andExpect(jsonPath("$.effectLevelTo").value(DEFAULT_EFFECT_LEVEL_TO))
            .andExpect(jsonPath("$.choose1Weight").value(DEFAULT_CHOOSE_1_WEIGHT))
            .andExpect(jsonPath("$.choose2Weight").value(DEFAULT_CHOOSE_2_WEIGHT));
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where groupId equals to DEFAULT_GROUP_ID
        defaultMQuestCoopShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mQuestCoopList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestCoopShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMQuestCoopShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mQuestCoopList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestCoopShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where groupId is not null
        defaultMQuestCoopShouldBeFound("groupId.specified=true");

        // Get all the mQuestCoopList where groupId is null
        defaultMQuestCoopShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMQuestCoopShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestCoopList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMQuestCoopShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMQuestCoopShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestCoopList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMQuestCoopShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectRarity equals to DEFAULT_EFFECT_RARITY
        defaultMQuestCoopShouldBeFound("effectRarity.equals=" + DEFAULT_EFFECT_RARITY);

        // Get all the mQuestCoopList where effectRarity equals to UPDATED_EFFECT_RARITY
        defaultMQuestCoopShouldNotBeFound("effectRarity.equals=" + UPDATED_EFFECT_RARITY);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectRarity in DEFAULT_EFFECT_RARITY or UPDATED_EFFECT_RARITY
        defaultMQuestCoopShouldBeFound("effectRarity.in=" + DEFAULT_EFFECT_RARITY + "," + UPDATED_EFFECT_RARITY);

        // Get all the mQuestCoopList where effectRarity equals to UPDATED_EFFECT_RARITY
        defaultMQuestCoopShouldNotBeFound("effectRarity.in=" + UPDATED_EFFECT_RARITY);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectRarity is not null
        defaultMQuestCoopShouldBeFound("effectRarity.specified=true");

        // Get all the mQuestCoopList where effectRarity is null
        defaultMQuestCoopShouldNotBeFound("effectRarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectRarity greater than or equals to DEFAULT_EFFECT_RARITY
        defaultMQuestCoopShouldBeFound("effectRarity.greaterOrEqualThan=" + DEFAULT_EFFECT_RARITY);

        // Get all the mQuestCoopList where effectRarity greater than or equals to UPDATED_EFFECT_RARITY
        defaultMQuestCoopShouldNotBeFound("effectRarity.greaterOrEqualThan=" + UPDATED_EFFECT_RARITY);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectRarity less than or equals to DEFAULT_EFFECT_RARITY
        defaultMQuestCoopShouldNotBeFound("effectRarity.lessThan=" + DEFAULT_EFFECT_RARITY);

        // Get all the mQuestCoopList where effectRarity less than or equals to UPDATED_EFFECT_RARITY
        defaultMQuestCoopShouldBeFound("effectRarity.lessThan=" + UPDATED_EFFECT_RARITY);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelFromIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelFrom equals to DEFAULT_EFFECT_LEVEL_FROM
        defaultMQuestCoopShouldBeFound("effectLevelFrom.equals=" + DEFAULT_EFFECT_LEVEL_FROM);

        // Get all the mQuestCoopList where effectLevelFrom equals to UPDATED_EFFECT_LEVEL_FROM
        defaultMQuestCoopShouldNotBeFound("effectLevelFrom.equals=" + UPDATED_EFFECT_LEVEL_FROM);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelFromIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelFrom in DEFAULT_EFFECT_LEVEL_FROM or UPDATED_EFFECT_LEVEL_FROM
        defaultMQuestCoopShouldBeFound("effectLevelFrom.in=" + DEFAULT_EFFECT_LEVEL_FROM + "," + UPDATED_EFFECT_LEVEL_FROM);

        // Get all the mQuestCoopList where effectLevelFrom equals to UPDATED_EFFECT_LEVEL_FROM
        defaultMQuestCoopShouldNotBeFound("effectLevelFrom.in=" + UPDATED_EFFECT_LEVEL_FROM);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelFrom is not null
        defaultMQuestCoopShouldBeFound("effectLevelFrom.specified=true");

        // Get all the mQuestCoopList where effectLevelFrom is null
        defaultMQuestCoopShouldNotBeFound("effectLevelFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelFrom greater than or equals to DEFAULT_EFFECT_LEVEL_FROM
        defaultMQuestCoopShouldBeFound("effectLevelFrom.greaterOrEqualThan=" + DEFAULT_EFFECT_LEVEL_FROM);

        // Get all the mQuestCoopList where effectLevelFrom greater than or equals to UPDATED_EFFECT_LEVEL_FROM
        defaultMQuestCoopShouldNotBeFound("effectLevelFrom.greaterOrEqualThan=" + UPDATED_EFFECT_LEVEL_FROM);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelFromIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelFrom less than or equals to DEFAULT_EFFECT_LEVEL_FROM
        defaultMQuestCoopShouldNotBeFound("effectLevelFrom.lessThan=" + DEFAULT_EFFECT_LEVEL_FROM);

        // Get all the mQuestCoopList where effectLevelFrom less than or equals to UPDATED_EFFECT_LEVEL_FROM
        defaultMQuestCoopShouldBeFound("effectLevelFrom.lessThan=" + UPDATED_EFFECT_LEVEL_FROM);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelToIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelTo equals to DEFAULT_EFFECT_LEVEL_TO
        defaultMQuestCoopShouldBeFound("effectLevelTo.equals=" + DEFAULT_EFFECT_LEVEL_TO);

        // Get all the mQuestCoopList where effectLevelTo equals to UPDATED_EFFECT_LEVEL_TO
        defaultMQuestCoopShouldNotBeFound("effectLevelTo.equals=" + UPDATED_EFFECT_LEVEL_TO);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelToIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelTo in DEFAULT_EFFECT_LEVEL_TO or UPDATED_EFFECT_LEVEL_TO
        defaultMQuestCoopShouldBeFound("effectLevelTo.in=" + DEFAULT_EFFECT_LEVEL_TO + "," + UPDATED_EFFECT_LEVEL_TO);

        // Get all the mQuestCoopList where effectLevelTo equals to UPDATED_EFFECT_LEVEL_TO
        defaultMQuestCoopShouldNotBeFound("effectLevelTo.in=" + UPDATED_EFFECT_LEVEL_TO);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelToIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelTo is not null
        defaultMQuestCoopShouldBeFound("effectLevelTo.specified=true");

        // Get all the mQuestCoopList where effectLevelTo is null
        defaultMQuestCoopShouldNotBeFound("effectLevelTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelTo greater than or equals to DEFAULT_EFFECT_LEVEL_TO
        defaultMQuestCoopShouldBeFound("effectLevelTo.greaterOrEqualThan=" + DEFAULT_EFFECT_LEVEL_TO);

        // Get all the mQuestCoopList where effectLevelTo greater than or equals to UPDATED_EFFECT_LEVEL_TO
        defaultMQuestCoopShouldNotBeFound("effectLevelTo.greaterOrEqualThan=" + UPDATED_EFFECT_LEVEL_TO);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByEffectLevelToIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where effectLevelTo less than or equals to DEFAULT_EFFECT_LEVEL_TO
        defaultMQuestCoopShouldNotBeFound("effectLevelTo.lessThan=" + DEFAULT_EFFECT_LEVEL_TO);

        // Get all the mQuestCoopList where effectLevelTo less than or equals to UPDATED_EFFECT_LEVEL_TO
        defaultMQuestCoopShouldBeFound("effectLevelTo.lessThan=" + UPDATED_EFFECT_LEVEL_TO);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose1WeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose1Weight equals to DEFAULT_CHOOSE_1_WEIGHT
        defaultMQuestCoopShouldBeFound("choose1Weight.equals=" + DEFAULT_CHOOSE_1_WEIGHT);

        // Get all the mQuestCoopList where choose1Weight equals to UPDATED_CHOOSE_1_WEIGHT
        defaultMQuestCoopShouldNotBeFound("choose1Weight.equals=" + UPDATED_CHOOSE_1_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose1WeightIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose1Weight in DEFAULT_CHOOSE_1_WEIGHT or UPDATED_CHOOSE_1_WEIGHT
        defaultMQuestCoopShouldBeFound("choose1Weight.in=" + DEFAULT_CHOOSE_1_WEIGHT + "," + UPDATED_CHOOSE_1_WEIGHT);

        // Get all the mQuestCoopList where choose1Weight equals to UPDATED_CHOOSE_1_WEIGHT
        defaultMQuestCoopShouldNotBeFound("choose1Weight.in=" + UPDATED_CHOOSE_1_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose1WeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose1Weight is not null
        defaultMQuestCoopShouldBeFound("choose1Weight.specified=true");

        // Get all the mQuestCoopList where choose1Weight is null
        defaultMQuestCoopShouldNotBeFound("choose1Weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose1WeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose1Weight greater than or equals to DEFAULT_CHOOSE_1_WEIGHT
        defaultMQuestCoopShouldBeFound("choose1Weight.greaterOrEqualThan=" + DEFAULT_CHOOSE_1_WEIGHT);

        // Get all the mQuestCoopList where choose1Weight greater than or equals to UPDATED_CHOOSE_1_WEIGHT
        defaultMQuestCoopShouldNotBeFound("choose1Weight.greaterOrEqualThan=" + UPDATED_CHOOSE_1_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose1WeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose1Weight less than or equals to DEFAULT_CHOOSE_1_WEIGHT
        defaultMQuestCoopShouldNotBeFound("choose1Weight.lessThan=" + DEFAULT_CHOOSE_1_WEIGHT);

        // Get all the mQuestCoopList where choose1Weight less than or equals to UPDATED_CHOOSE_1_WEIGHT
        defaultMQuestCoopShouldBeFound("choose1Weight.lessThan=" + UPDATED_CHOOSE_1_WEIGHT);
    }


    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose2WeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose2Weight equals to DEFAULT_CHOOSE_2_WEIGHT
        defaultMQuestCoopShouldBeFound("choose2Weight.equals=" + DEFAULT_CHOOSE_2_WEIGHT);

        // Get all the mQuestCoopList where choose2Weight equals to UPDATED_CHOOSE_2_WEIGHT
        defaultMQuestCoopShouldNotBeFound("choose2Weight.equals=" + UPDATED_CHOOSE_2_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose2WeightIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose2Weight in DEFAULT_CHOOSE_2_WEIGHT or UPDATED_CHOOSE_2_WEIGHT
        defaultMQuestCoopShouldBeFound("choose2Weight.in=" + DEFAULT_CHOOSE_2_WEIGHT + "," + UPDATED_CHOOSE_2_WEIGHT);

        // Get all the mQuestCoopList where choose2Weight equals to UPDATED_CHOOSE_2_WEIGHT
        defaultMQuestCoopShouldNotBeFound("choose2Weight.in=" + UPDATED_CHOOSE_2_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose2WeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose2Weight is not null
        defaultMQuestCoopShouldBeFound("choose2Weight.specified=true");

        // Get all the mQuestCoopList where choose2Weight is null
        defaultMQuestCoopShouldNotBeFound("choose2Weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose2WeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose2Weight greater than or equals to DEFAULT_CHOOSE_2_WEIGHT
        defaultMQuestCoopShouldBeFound("choose2Weight.greaterOrEqualThan=" + DEFAULT_CHOOSE_2_WEIGHT);

        // Get all the mQuestCoopList where choose2Weight greater than or equals to UPDATED_CHOOSE_2_WEIGHT
        defaultMQuestCoopShouldNotBeFound("choose2Weight.greaterOrEqualThan=" + UPDATED_CHOOSE_2_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMQuestCoopsByChoose2WeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        // Get all the mQuestCoopList where choose2Weight less than or equals to DEFAULT_CHOOSE_2_WEIGHT
        defaultMQuestCoopShouldNotBeFound("choose2Weight.lessThan=" + DEFAULT_CHOOSE_2_WEIGHT);

        // Get all the mQuestCoopList where choose2Weight less than or equals to UPDATED_CHOOSE_2_WEIGHT
        defaultMQuestCoopShouldBeFound("choose2Weight.lessThan=" + UPDATED_CHOOSE_2_WEIGHT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestCoopShouldBeFound(String filter) throws Exception {
        restMQuestCoopMockMvc.perform(get("/api/m-quest-coops?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestCoop.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].effectRarity").value(hasItem(DEFAULT_EFFECT_RARITY)))
            .andExpect(jsonPath("$.[*].effectLevelFrom").value(hasItem(DEFAULT_EFFECT_LEVEL_FROM)))
            .andExpect(jsonPath("$.[*].effectLevelTo").value(hasItem(DEFAULT_EFFECT_LEVEL_TO)))
            .andExpect(jsonPath("$.[*].choose1Weight").value(hasItem(DEFAULT_CHOOSE_1_WEIGHT)))
            .andExpect(jsonPath("$.[*].choose2Weight").value(hasItem(DEFAULT_CHOOSE_2_WEIGHT)));

        // Check, that the count call also returns 1
        restMQuestCoopMockMvc.perform(get("/api/m-quest-coops/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestCoopShouldNotBeFound(String filter) throws Exception {
        restMQuestCoopMockMvc.perform(get("/api/m-quest-coops?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestCoopMockMvc.perform(get("/api/m-quest-coops/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestCoop() throws Exception {
        // Get the mQuestCoop
        restMQuestCoopMockMvc.perform(get("/api/m-quest-coops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestCoop() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        int databaseSizeBeforeUpdate = mQuestCoopRepository.findAll().size();

        // Update the mQuestCoop
        MQuestCoop updatedMQuestCoop = mQuestCoopRepository.findById(mQuestCoop.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestCoop are not directly saved in db
        em.detach(updatedMQuestCoop);
        updatedMQuestCoop
            .groupId(UPDATED_GROUP_ID)
            .effectRarity(UPDATED_EFFECT_RARITY)
            .effectLevelFrom(UPDATED_EFFECT_LEVEL_FROM)
            .effectLevelTo(UPDATED_EFFECT_LEVEL_TO)
            .choose1Weight(UPDATED_CHOOSE_1_WEIGHT)
            .choose2Weight(UPDATED_CHOOSE_2_WEIGHT);
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(updatedMQuestCoop);

        restMQuestCoopMockMvc.perform(put("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestCoop in the database
        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeUpdate);
        MQuestCoop testMQuestCoop = mQuestCoopList.get(mQuestCoopList.size() - 1);
        assertThat(testMQuestCoop.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMQuestCoop.getEffectRarity()).isEqualTo(UPDATED_EFFECT_RARITY);
        assertThat(testMQuestCoop.getEffectLevelFrom()).isEqualTo(UPDATED_EFFECT_LEVEL_FROM);
        assertThat(testMQuestCoop.getEffectLevelTo()).isEqualTo(UPDATED_EFFECT_LEVEL_TO);
        assertThat(testMQuestCoop.getChoose1Weight()).isEqualTo(UPDATED_CHOOSE_1_WEIGHT);
        assertThat(testMQuestCoop.getChoose2Weight()).isEqualTo(UPDATED_CHOOSE_2_WEIGHT);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestCoop() throws Exception {
        int databaseSizeBeforeUpdate = mQuestCoopRepository.findAll().size();

        // Create the MQuestCoop
        MQuestCoopDTO mQuestCoopDTO = mQuestCoopMapper.toDto(mQuestCoop);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestCoopMockMvc.perform(put("/api/m-quest-coops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestCoopDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestCoop in the database
        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestCoop() throws Exception {
        // Initialize the database
        mQuestCoopRepository.saveAndFlush(mQuestCoop);

        int databaseSizeBeforeDelete = mQuestCoopRepository.findAll().size();

        // Delete the mQuestCoop
        restMQuestCoopMockMvc.perform(delete("/api/m-quest-coops/{id}", mQuestCoop.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestCoop> mQuestCoopList = mQuestCoopRepository.findAll();
        assertThat(mQuestCoopList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestCoop.class);
        MQuestCoop mQuestCoop1 = new MQuestCoop();
        mQuestCoop1.setId(1L);
        MQuestCoop mQuestCoop2 = new MQuestCoop();
        mQuestCoop2.setId(mQuestCoop1.getId());
        assertThat(mQuestCoop1).isEqualTo(mQuestCoop2);
        mQuestCoop2.setId(2L);
        assertThat(mQuestCoop1).isNotEqualTo(mQuestCoop2);
        mQuestCoop1.setId(null);
        assertThat(mQuestCoop1).isNotEqualTo(mQuestCoop2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestCoopDTO.class);
        MQuestCoopDTO mQuestCoopDTO1 = new MQuestCoopDTO();
        mQuestCoopDTO1.setId(1L);
        MQuestCoopDTO mQuestCoopDTO2 = new MQuestCoopDTO();
        assertThat(mQuestCoopDTO1).isNotEqualTo(mQuestCoopDTO2);
        mQuestCoopDTO2.setId(mQuestCoopDTO1.getId());
        assertThat(mQuestCoopDTO1).isEqualTo(mQuestCoopDTO2);
        mQuestCoopDTO2.setId(2L);
        assertThat(mQuestCoopDTO1).isNotEqualTo(mQuestCoopDTO2);
        mQuestCoopDTO1.setId(null);
        assertThat(mQuestCoopDTO1).isNotEqualTo(mQuestCoopDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestCoopMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestCoopMapper.fromId(null)).isNull();
    }
}
