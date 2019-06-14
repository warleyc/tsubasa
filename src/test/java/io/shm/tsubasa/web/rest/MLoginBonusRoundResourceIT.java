package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MLoginBonusRound;
import io.shm.tsubasa.repository.MLoginBonusRoundRepository;
import io.shm.tsubasa.service.MLoginBonusRoundService;
import io.shm.tsubasa.service.dto.MLoginBonusRoundDTO;
import io.shm.tsubasa.service.mapper.MLoginBonusRoundMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MLoginBonusRoundCriteria;
import io.shm.tsubasa.service.MLoginBonusRoundQueryService;

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
 * Integration tests for the {@Link MLoginBonusRoundResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MLoginBonusRoundResourceIT {

    private static final Integer DEFAULT_ROUND_ID = 1;
    private static final Integer UPDATED_ROUND_ID = 2;

    private static final Integer DEFAULT_BONUS_TYPE = 1;
    private static final Integer UPDATED_BONUS_TYPE = 2;

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    private static final String DEFAULT_SERIF_SANAE = "AAAAAAAAAA";
    private static final String UPDATED_SERIF_SANAE = "BBBBBBBBBB";

    private static final String DEFAULT_SERIF_YAYOI = "AAAAAAAAAA";
    private static final String UPDATED_SERIF_YAYOI = "BBBBBBBBBB";

    private static final String DEFAULT_SERIF_YOSHIKO = "AAAAAAAAAA";
    private static final String UPDATED_SERIF_YOSHIKO = "BBBBBBBBBB";

    private static final String DEFAULT_SERIF_MAKI = "AAAAAAAAAA";
    private static final String UPDATED_SERIF_MAKI = "BBBBBBBBBB";

    private static final String DEFAULT_SANAE_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_SANAE_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_YAYOI_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_YAYOI_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_YOSHIKO_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_YOSHIKO_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_MAKI_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_MAKI_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_SOUND_SWITCH_EVENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOUND_SWITCH_EVENT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NEXT_ID = 1;
    private static final Integer UPDATED_NEXT_ID = 2;

    private static final String DEFAULT_LAST_DAY_APPEAL_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_LAST_DAY_APPEAL_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND_IMAGE = "BBBBBBBBBB";

    @Autowired
    private MLoginBonusRoundRepository mLoginBonusRoundRepository;

    @Autowired
    private MLoginBonusRoundMapper mLoginBonusRoundMapper;

    @Autowired
    private MLoginBonusRoundService mLoginBonusRoundService;

    @Autowired
    private MLoginBonusRoundQueryService mLoginBonusRoundQueryService;

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

    private MockMvc restMLoginBonusRoundMockMvc;

    private MLoginBonusRound mLoginBonusRound;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MLoginBonusRoundResource mLoginBonusRoundResource = new MLoginBonusRoundResource(mLoginBonusRoundService, mLoginBonusRoundQueryService);
        this.restMLoginBonusRoundMockMvc = MockMvcBuilders.standaloneSetup(mLoginBonusRoundResource)
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
    public static MLoginBonusRound createEntity(EntityManager em) {
        MLoginBonusRound mLoginBonusRound = new MLoginBonusRound()
            .roundId(DEFAULT_ROUND_ID)
            .bonusType(DEFAULT_BONUS_TYPE)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT)
            .serifSanae(DEFAULT_SERIF_SANAE)
            .serifYayoi(DEFAULT_SERIF_YAYOI)
            .serifYoshiko(DEFAULT_SERIF_YOSHIKO)
            .serifMaki(DEFAULT_SERIF_MAKI)
            .sanaeImage(DEFAULT_SANAE_IMAGE)
            .yayoiImage(DEFAULT_YAYOI_IMAGE)
            .yoshikoImage(DEFAULT_YOSHIKO_IMAGE)
            .makiImage(DEFAULT_MAKI_IMAGE)
            .soundSwitchEventName(DEFAULT_SOUND_SWITCH_EVENT_NAME)
            .nextId(DEFAULT_NEXT_ID)
            .lastDayAppealComment(DEFAULT_LAST_DAY_APPEAL_COMMENT)
            .backgroundImage(DEFAULT_BACKGROUND_IMAGE);
        return mLoginBonusRound;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLoginBonusRound createUpdatedEntity(EntityManager em) {
        MLoginBonusRound mLoginBonusRound = new MLoginBonusRound()
            .roundId(UPDATED_ROUND_ID)
            .bonusType(UPDATED_BONUS_TYPE)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .serifSanae(UPDATED_SERIF_SANAE)
            .serifYayoi(UPDATED_SERIF_YAYOI)
            .serifYoshiko(UPDATED_SERIF_YOSHIKO)
            .serifMaki(UPDATED_SERIF_MAKI)
            .sanaeImage(UPDATED_SANAE_IMAGE)
            .yayoiImage(UPDATED_YAYOI_IMAGE)
            .yoshikoImage(UPDATED_YOSHIKO_IMAGE)
            .makiImage(UPDATED_MAKI_IMAGE)
            .soundSwitchEventName(UPDATED_SOUND_SWITCH_EVENT_NAME)
            .nextId(UPDATED_NEXT_ID)
            .lastDayAppealComment(UPDATED_LAST_DAY_APPEAL_COMMENT)
            .backgroundImage(UPDATED_BACKGROUND_IMAGE);
        return mLoginBonusRound;
    }

    @BeforeEach
    public void initTest() {
        mLoginBonusRound = createEntity(em);
    }

    @Test
    @Transactional
    public void createMLoginBonusRound() throws Exception {
        int databaseSizeBeforeCreate = mLoginBonusRoundRepository.findAll().size();

        // Create the MLoginBonusRound
        MLoginBonusRoundDTO mLoginBonusRoundDTO = mLoginBonusRoundMapper.toDto(mLoginBonusRound);
        restMLoginBonusRoundMockMvc.perform(post("/api/m-login-bonus-rounds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusRoundDTO)))
            .andExpect(status().isCreated());

        // Validate the MLoginBonusRound in the database
        List<MLoginBonusRound> mLoginBonusRoundList = mLoginBonusRoundRepository.findAll();
        assertThat(mLoginBonusRoundList).hasSize(databaseSizeBeforeCreate + 1);
        MLoginBonusRound testMLoginBonusRound = mLoginBonusRoundList.get(mLoginBonusRoundList.size() - 1);
        assertThat(testMLoginBonusRound.getRoundId()).isEqualTo(DEFAULT_ROUND_ID);
        assertThat(testMLoginBonusRound.getBonusType()).isEqualTo(DEFAULT_BONUS_TYPE);
        assertThat(testMLoginBonusRound.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMLoginBonusRound.getEndAt()).isEqualTo(DEFAULT_END_AT);
        assertThat(testMLoginBonusRound.getSerifSanae()).isEqualTo(DEFAULT_SERIF_SANAE);
        assertThat(testMLoginBonusRound.getSerifYayoi()).isEqualTo(DEFAULT_SERIF_YAYOI);
        assertThat(testMLoginBonusRound.getSerifYoshiko()).isEqualTo(DEFAULT_SERIF_YOSHIKO);
        assertThat(testMLoginBonusRound.getSerifMaki()).isEqualTo(DEFAULT_SERIF_MAKI);
        assertThat(testMLoginBonusRound.getSanaeImage()).isEqualTo(DEFAULT_SANAE_IMAGE);
        assertThat(testMLoginBonusRound.getYayoiImage()).isEqualTo(DEFAULT_YAYOI_IMAGE);
        assertThat(testMLoginBonusRound.getYoshikoImage()).isEqualTo(DEFAULT_YOSHIKO_IMAGE);
        assertThat(testMLoginBonusRound.getMakiImage()).isEqualTo(DEFAULT_MAKI_IMAGE);
        assertThat(testMLoginBonusRound.getSoundSwitchEventName()).isEqualTo(DEFAULT_SOUND_SWITCH_EVENT_NAME);
        assertThat(testMLoginBonusRound.getNextId()).isEqualTo(DEFAULT_NEXT_ID);
        assertThat(testMLoginBonusRound.getLastDayAppealComment()).isEqualTo(DEFAULT_LAST_DAY_APPEAL_COMMENT);
        assertThat(testMLoginBonusRound.getBackgroundImage()).isEqualTo(DEFAULT_BACKGROUND_IMAGE);
    }

    @Test
    @Transactional
    public void createMLoginBonusRoundWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mLoginBonusRoundRepository.findAll().size();

        // Create the MLoginBonusRound with an existing ID
        mLoginBonusRound.setId(1L);
        MLoginBonusRoundDTO mLoginBonusRoundDTO = mLoginBonusRoundMapper.toDto(mLoginBonusRound);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLoginBonusRoundMockMvc.perform(post("/api/m-login-bonus-rounds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusRoundDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLoginBonusRound in the database
        List<MLoginBonusRound> mLoginBonusRoundList = mLoginBonusRoundRepository.findAll();
        assertThat(mLoginBonusRoundList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRoundIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusRoundRepository.findAll().size();
        // set the field null
        mLoginBonusRound.setRoundId(null);

        // Create the MLoginBonusRound, which fails.
        MLoginBonusRoundDTO mLoginBonusRoundDTO = mLoginBonusRoundMapper.toDto(mLoginBonusRound);

        restMLoginBonusRoundMockMvc.perform(post("/api/m-login-bonus-rounds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusRoundDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusRound> mLoginBonusRoundList = mLoginBonusRoundRepository.findAll();
        assertThat(mLoginBonusRoundList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBonusTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusRoundRepository.findAll().size();
        // set the field null
        mLoginBonusRound.setBonusType(null);

        // Create the MLoginBonusRound, which fails.
        MLoginBonusRoundDTO mLoginBonusRoundDTO = mLoginBonusRoundMapper.toDto(mLoginBonusRound);

        restMLoginBonusRoundMockMvc.perform(post("/api/m-login-bonus-rounds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusRoundDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusRound> mLoginBonusRoundList = mLoginBonusRoundRepository.findAll();
        assertThat(mLoginBonusRoundList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusRoundRepository.findAll().size();
        // set the field null
        mLoginBonusRound.setStartAt(null);

        // Create the MLoginBonusRound, which fails.
        MLoginBonusRoundDTO mLoginBonusRoundDTO = mLoginBonusRoundMapper.toDto(mLoginBonusRound);

        restMLoginBonusRoundMockMvc.perform(post("/api/m-login-bonus-rounds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusRoundDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusRound> mLoginBonusRoundList = mLoginBonusRoundRepository.findAll();
        assertThat(mLoginBonusRoundList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLoginBonusRoundRepository.findAll().size();
        // set the field null
        mLoginBonusRound.setEndAt(null);

        // Create the MLoginBonusRound, which fails.
        MLoginBonusRoundDTO mLoginBonusRoundDTO = mLoginBonusRoundMapper.toDto(mLoginBonusRound);

        restMLoginBonusRoundMockMvc.perform(post("/api/m-login-bonus-rounds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusRoundDTO)))
            .andExpect(status().isBadRequest());

        List<MLoginBonusRound> mLoginBonusRoundList = mLoginBonusRoundRepository.findAll();
        assertThat(mLoginBonusRoundList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRounds() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList
        restMLoginBonusRoundMockMvc.perform(get("/api/m-login-bonus-rounds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLoginBonusRound.getId().intValue())))
            .andExpect(jsonPath("$.[*].roundId").value(hasItem(DEFAULT_ROUND_ID)))
            .andExpect(jsonPath("$.[*].bonusType").value(hasItem(DEFAULT_BONUS_TYPE)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].serifSanae").value(hasItem(DEFAULT_SERIF_SANAE.toString())))
            .andExpect(jsonPath("$.[*].serifYayoi").value(hasItem(DEFAULT_SERIF_YAYOI.toString())))
            .andExpect(jsonPath("$.[*].serifYoshiko").value(hasItem(DEFAULT_SERIF_YOSHIKO.toString())))
            .andExpect(jsonPath("$.[*].serifMaki").value(hasItem(DEFAULT_SERIF_MAKI.toString())))
            .andExpect(jsonPath("$.[*].sanaeImage").value(hasItem(DEFAULT_SANAE_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].yayoiImage").value(hasItem(DEFAULT_YAYOI_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].yoshikoImage").value(hasItem(DEFAULT_YOSHIKO_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].makiImage").value(hasItem(DEFAULT_MAKI_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].soundSwitchEventName").value(hasItem(DEFAULT_SOUND_SWITCH_EVENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].nextId").value(hasItem(DEFAULT_NEXT_ID)))
            .andExpect(jsonPath("$.[*].lastDayAppealComment").value(hasItem(DEFAULT_LAST_DAY_APPEAL_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].backgroundImage").value(hasItem(DEFAULT_BACKGROUND_IMAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMLoginBonusRound() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get the mLoginBonusRound
        restMLoginBonusRoundMockMvc.perform(get("/api/m-login-bonus-rounds/{id}", mLoginBonusRound.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mLoginBonusRound.getId().intValue()))
            .andExpect(jsonPath("$.roundId").value(DEFAULT_ROUND_ID))
            .andExpect(jsonPath("$.bonusType").value(DEFAULT_BONUS_TYPE))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT))
            .andExpect(jsonPath("$.serifSanae").value(DEFAULT_SERIF_SANAE.toString()))
            .andExpect(jsonPath("$.serifYayoi").value(DEFAULT_SERIF_YAYOI.toString()))
            .andExpect(jsonPath("$.serifYoshiko").value(DEFAULT_SERIF_YOSHIKO.toString()))
            .andExpect(jsonPath("$.serifMaki").value(DEFAULT_SERIF_MAKI.toString()))
            .andExpect(jsonPath("$.sanaeImage").value(DEFAULT_SANAE_IMAGE.toString()))
            .andExpect(jsonPath("$.yayoiImage").value(DEFAULT_YAYOI_IMAGE.toString()))
            .andExpect(jsonPath("$.yoshikoImage").value(DEFAULT_YOSHIKO_IMAGE.toString()))
            .andExpect(jsonPath("$.makiImage").value(DEFAULT_MAKI_IMAGE.toString()))
            .andExpect(jsonPath("$.soundSwitchEventName").value(DEFAULT_SOUND_SWITCH_EVENT_NAME.toString()))
            .andExpect(jsonPath("$.nextId").value(DEFAULT_NEXT_ID))
            .andExpect(jsonPath("$.lastDayAppealComment").value(DEFAULT_LAST_DAY_APPEAL_COMMENT.toString()))
            .andExpect(jsonPath("$.backgroundImage").value(DEFAULT_BACKGROUND_IMAGE.toString()));
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByRoundIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where roundId equals to DEFAULT_ROUND_ID
        defaultMLoginBonusRoundShouldBeFound("roundId.equals=" + DEFAULT_ROUND_ID);

        // Get all the mLoginBonusRoundList where roundId equals to UPDATED_ROUND_ID
        defaultMLoginBonusRoundShouldNotBeFound("roundId.equals=" + UPDATED_ROUND_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByRoundIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where roundId in DEFAULT_ROUND_ID or UPDATED_ROUND_ID
        defaultMLoginBonusRoundShouldBeFound("roundId.in=" + DEFAULT_ROUND_ID + "," + UPDATED_ROUND_ID);

        // Get all the mLoginBonusRoundList where roundId equals to UPDATED_ROUND_ID
        defaultMLoginBonusRoundShouldNotBeFound("roundId.in=" + UPDATED_ROUND_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByRoundIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where roundId is not null
        defaultMLoginBonusRoundShouldBeFound("roundId.specified=true");

        // Get all the mLoginBonusRoundList where roundId is null
        defaultMLoginBonusRoundShouldNotBeFound("roundId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByRoundIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where roundId greater than or equals to DEFAULT_ROUND_ID
        defaultMLoginBonusRoundShouldBeFound("roundId.greaterOrEqualThan=" + DEFAULT_ROUND_ID);

        // Get all the mLoginBonusRoundList where roundId greater than or equals to UPDATED_ROUND_ID
        defaultMLoginBonusRoundShouldNotBeFound("roundId.greaterOrEqualThan=" + UPDATED_ROUND_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByRoundIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where roundId less than or equals to DEFAULT_ROUND_ID
        defaultMLoginBonusRoundShouldNotBeFound("roundId.lessThan=" + DEFAULT_ROUND_ID);

        // Get all the mLoginBonusRoundList where roundId less than or equals to UPDATED_ROUND_ID
        defaultMLoginBonusRoundShouldBeFound("roundId.lessThan=" + UPDATED_ROUND_ID);
    }


    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByBonusTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where bonusType equals to DEFAULT_BONUS_TYPE
        defaultMLoginBonusRoundShouldBeFound("bonusType.equals=" + DEFAULT_BONUS_TYPE);

        // Get all the mLoginBonusRoundList where bonusType equals to UPDATED_BONUS_TYPE
        defaultMLoginBonusRoundShouldNotBeFound("bonusType.equals=" + UPDATED_BONUS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByBonusTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where bonusType in DEFAULT_BONUS_TYPE or UPDATED_BONUS_TYPE
        defaultMLoginBonusRoundShouldBeFound("bonusType.in=" + DEFAULT_BONUS_TYPE + "," + UPDATED_BONUS_TYPE);

        // Get all the mLoginBonusRoundList where bonusType equals to UPDATED_BONUS_TYPE
        defaultMLoginBonusRoundShouldNotBeFound("bonusType.in=" + UPDATED_BONUS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByBonusTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where bonusType is not null
        defaultMLoginBonusRoundShouldBeFound("bonusType.specified=true");

        // Get all the mLoginBonusRoundList where bonusType is null
        defaultMLoginBonusRoundShouldNotBeFound("bonusType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByBonusTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where bonusType greater than or equals to DEFAULT_BONUS_TYPE
        defaultMLoginBonusRoundShouldBeFound("bonusType.greaterOrEqualThan=" + DEFAULT_BONUS_TYPE);

        // Get all the mLoginBonusRoundList where bonusType greater than or equals to UPDATED_BONUS_TYPE
        defaultMLoginBonusRoundShouldNotBeFound("bonusType.greaterOrEqualThan=" + UPDATED_BONUS_TYPE);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByBonusTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where bonusType less than or equals to DEFAULT_BONUS_TYPE
        defaultMLoginBonusRoundShouldNotBeFound("bonusType.lessThan=" + DEFAULT_BONUS_TYPE);

        // Get all the mLoginBonusRoundList where bonusType less than or equals to UPDATED_BONUS_TYPE
        defaultMLoginBonusRoundShouldBeFound("bonusType.lessThan=" + UPDATED_BONUS_TYPE);
    }


    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where startAt equals to DEFAULT_START_AT
        defaultMLoginBonusRoundShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mLoginBonusRoundList where startAt equals to UPDATED_START_AT
        defaultMLoginBonusRoundShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMLoginBonusRoundShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mLoginBonusRoundList where startAt equals to UPDATED_START_AT
        defaultMLoginBonusRoundShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where startAt is not null
        defaultMLoginBonusRoundShouldBeFound("startAt.specified=true");

        // Get all the mLoginBonusRoundList where startAt is null
        defaultMLoginBonusRoundShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where startAt greater than or equals to DEFAULT_START_AT
        defaultMLoginBonusRoundShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mLoginBonusRoundList where startAt greater than or equals to UPDATED_START_AT
        defaultMLoginBonusRoundShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where startAt less than or equals to DEFAULT_START_AT
        defaultMLoginBonusRoundShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mLoginBonusRoundList where startAt less than or equals to UPDATED_START_AT
        defaultMLoginBonusRoundShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where endAt equals to DEFAULT_END_AT
        defaultMLoginBonusRoundShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mLoginBonusRoundList where endAt equals to UPDATED_END_AT
        defaultMLoginBonusRoundShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMLoginBonusRoundShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mLoginBonusRoundList where endAt equals to UPDATED_END_AT
        defaultMLoginBonusRoundShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where endAt is not null
        defaultMLoginBonusRoundShouldBeFound("endAt.specified=true");

        // Get all the mLoginBonusRoundList where endAt is null
        defaultMLoginBonusRoundShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where endAt greater than or equals to DEFAULT_END_AT
        defaultMLoginBonusRoundShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mLoginBonusRoundList where endAt greater than or equals to UPDATED_END_AT
        defaultMLoginBonusRoundShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where endAt less than or equals to DEFAULT_END_AT
        defaultMLoginBonusRoundShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mLoginBonusRoundList where endAt less than or equals to UPDATED_END_AT
        defaultMLoginBonusRoundShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }


    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByNextIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where nextId equals to DEFAULT_NEXT_ID
        defaultMLoginBonusRoundShouldBeFound("nextId.equals=" + DEFAULT_NEXT_ID);

        // Get all the mLoginBonusRoundList where nextId equals to UPDATED_NEXT_ID
        defaultMLoginBonusRoundShouldNotBeFound("nextId.equals=" + UPDATED_NEXT_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByNextIdIsInShouldWork() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where nextId in DEFAULT_NEXT_ID or UPDATED_NEXT_ID
        defaultMLoginBonusRoundShouldBeFound("nextId.in=" + DEFAULT_NEXT_ID + "," + UPDATED_NEXT_ID);

        // Get all the mLoginBonusRoundList where nextId equals to UPDATED_NEXT_ID
        defaultMLoginBonusRoundShouldNotBeFound("nextId.in=" + UPDATED_NEXT_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByNextIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where nextId is not null
        defaultMLoginBonusRoundShouldBeFound("nextId.specified=true");

        // Get all the mLoginBonusRoundList where nextId is null
        defaultMLoginBonusRoundShouldNotBeFound("nextId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByNextIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where nextId greater than or equals to DEFAULT_NEXT_ID
        defaultMLoginBonusRoundShouldBeFound("nextId.greaterOrEqualThan=" + DEFAULT_NEXT_ID);

        // Get all the mLoginBonusRoundList where nextId greater than or equals to UPDATED_NEXT_ID
        defaultMLoginBonusRoundShouldNotBeFound("nextId.greaterOrEqualThan=" + UPDATED_NEXT_ID);
    }

    @Test
    @Transactional
    public void getAllMLoginBonusRoundsByNextIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        // Get all the mLoginBonusRoundList where nextId less than or equals to DEFAULT_NEXT_ID
        defaultMLoginBonusRoundShouldNotBeFound("nextId.lessThan=" + DEFAULT_NEXT_ID);

        // Get all the mLoginBonusRoundList where nextId less than or equals to UPDATED_NEXT_ID
        defaultMLoginBonusRoundShouldBeFound("nextId.lessThan=" + UPDATED_NEXT_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMLoginBonusRoundShouldBeFound(String filter) throws Exception {
        restMLoginBonusRoundMockMvc.perform(get("/api/m-login-bonus-rounds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLoginBonusRound.getId().intValue())))
            .andExpect(jsonPath("$.[*].roundId").value(hasItem(DEFAULT_ROUND_ID)))
            .andExpect(jsonPath("$.[*].bonusType").value(hasItem(DEFAULT_BONUS_TYPE)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)))
            .andExpect(jsonPath("$.[*].serifSanae").value(hasItem(DEFAULT_SERIF_SANAE.toString())))
            .andExpect(jsonPath("$.[*].serifYayoi").value(hasItem(DEFAULT_SERIF_YAYOI.toString())))
            .andExpect(jsonPath("$.[*].serifYoshiko").value(hasItem(DEFAULT_SERIF_YOSHIKO.toString())))
            .andExpect(jsonPath("$.[*].serifMaki").value(hasItem(DEFAULT_SERIF_MAKI.toString())))
            .andExpect(jsonPath("$.[*].sanaeImage").value(hasItem(DEFAULT_SANAE_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].yayoiImage").value(hasItem(DEFAULT_YAYOI_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].yoshikoImage").value(hasItem(DEFAULT_YOSHIKO_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].makiImage").value(hasItem(DEFAULT_MAKI_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].soundSwitchEventName").value(hasItem(DEFAULT_SOUND_SWITCH_EVENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].nextId").value(hasItem(DEFAULT_NEXT_ID)))
            .andExpect(jsonPath("$.[*].lastDayAppealComment").value(hasItem(DEFAULT_LAST_DAY_APPEAL_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].backgroundImage").value(hasItem(DEFAULT_BACKGROUND_IMAGE.toString())));

        // Check, that the count call also returns 1
        restMLoginBonusRoundMockMvc.perform(get("/api/m-login-bonus-rounds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMLoginBonusRoundShouldNotBeFound(String filter) throws Exception {
        restMLoginBonusRoundMockMvc.perform(get("/api/m-login-bonus-rounds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMLoginBonusRoundMockMvc.perform(get("/api/m-login-bonus-rounds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMLoginBonusRound() throws Exception {
        // Get the mLoginBonusRound
        restMLoginBonusRoundMockMvc.perform(get("/api/m-login-bonus-rounds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMLoginBonusRound() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        int databaseSizeBeforeUpdate = mLoginBonusRoundRepository.findAll().size();

        // Update the mLoginBonusRound
        MLoginBonusRound updatedMLoginBonusRound = mLoginBonusRoundRepository.findById(mLoginBonusRound.getId()).get();
        // Disconnect from session so that the updates on updatedMLoginBonusRound are not directly saved in db
        em.detach(updatedMLoginBonusRound);
        updatedMLoginBonusRound
            .roundId(UPDATED_ROUND_ID)
            .bonusType(UPDATED_BONUS_TYPE)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT)
            .serifSanae(UPDATED_SERIF_SANAE)
            .serifYayoi(UPDATED_SERIF_YAYOI)
            .serifYoshiko(UPDATED_SERIF_YOSHIKO)
            .serifMaki(UPDATED_SERIF_MAKI)
            .sanaeImage(UPDATED_SANAE_IMAGE)
            .yayoiImage(UPDATED_YAYOI_IMAGE)
            .yoshikoImage(UPDATED_YOSHIKO_IMAGE)
            .makiImage(UPDATED_MAKI_IMAGE)
            .soundSwitchEventName(UPDATED_SOUND_SWITCH_EVENT_NAME)
            .nextId(UPDATED_NEXT_ID)
            .lastDayAppealComment(UPDATED_LAST_DAY_APPEAL_COMMENT)
            .backgroundImage(UPDATED_BACKGROUND_IMAGE);
        MLoginBonusRoundDTO mLoginBonusRoundDTO = mLoginBonusRoundMapper.toDto(updatedMLoginBonusRound);

        restMLoginBonusRoundMockMvc.perform(put("/api/m-login-bonus-rounds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusRoundDTO)))
            .andExpect(status().isOk());

        // Validate the MLoginBonusRound in the database
        List<MLoginBonusRound> mLoginBonusRoundList = mLoginBonusRoundRepository.findAll();
        assertThat(mLoginBonusRoundList).hasSize(databaseSizeBeforeUpdate);
        MLoginBonusRound testMLoginBonusRound = mLoginBonusRoundList.get(mLoginBonusRoundList.size() - 1);
        assertThat(testMLoginBonusRound.getRoundId()).isEqualTo(UPDATED_ROUND_ID);
        assertThat(testMLoginBonusRound.getBonusType()).isEqualTo(UPDATED_BONUS_TYPE);
        assertThat(testMLoginBonusRound.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMLoginBonusRound.getEndAt()).isEqualTo(UPDATED_END_AT);
        assertThat(testMLoginBonusRound.getSerifSanae()).isEqualTo(UPDATED_SERIF_SANAE);
        assertThat(testMLoginBonusRound.getSerifYayoi()).isEqualTo(UPDATED_SERIF_YAYOI);
        assertThat(testMLoginBonusRound.getSerifYoshiko()).isEqualTo(UPDATED_SERIF_YOSHIKO);
        assertThat(testMLoginBonusRound.getSerifMaki()).isEqualTo(UPDATED_SERIF_MAKI);
        assertThat(testMLoginBonusRound.getSanaeImage()).isEqualTo(UPDATED_SANAE_IMAGE);
        assertThat(testMLoginBonusRound.getYayoiImage()).isEqualTo(UPDATED_YAYOI_IMAGE);
        assertThat(testMLoginBonusRound.getYoshikoImage()).isEqualTo(UPDATED_YOSHIKO_IMAGE);
        assertThat(testMLoginBonusRound.getMakiImage()).isEqualTo(UPDATED_MAKI_IMAGE);
        assertThat(testMLoginBonusRound.getSoundSwitchEventName()).isEqualTo(UPDATED_SOUND_SWITCH_EVENT_NAME);
        assertThat(testMLoginBonusRound.getNextId()).isEqualTo(UPDATED_NEXT_ID);
        assertThat(testMLoginBonusRound.getLastDayAppealComment()).isEqualTo(UPDATED_LAST_DAY_APPEAL_COMMENT);
        assertThat(testMLoginBonusRound.getBackgroundImage()).isEqualTo(UPDATED_BACKGROUND_IMAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMLoginBonusRound() throws Exception {
        int databaseSizeBeforeUpdate = mLoginBonusRoundRepository.findAll().size();

        // Create the MLoginBonusRound
        MLoginBonusRoundDTO mLoginBonusRoundDTO = mLoginBonusRoundMapper.toDto(mLoginBonusRound);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLoginBonusRoundMockMvc.perform(put("/api/m-login-bonus-rounds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mLoginBonusRoundDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MLoginBonusRound in the database
        List<MLoginBonusRound> mLoginBonusRoundList = mLoginBonusRoundRepository.findAll();
        assertThat(mLoginBonusRoundList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMLoginBonusRound() throws Exception {
        // Initialize the database
        mLoginBonusRoundRepository.saveAndFlush(mLoginBonusRound);

        int databaseSizeBeforeDelete = mLoginBonusRoundRepository.findAll().size();

        // Delete the mLoginBonusRound
        restMLoginBonusRoundMockMvc.perform(delete("/api/m-login-bonus-rounds/{id}", mLoginBonusRound.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MLoginBonusRound> mLoginBonusRoundList = mLoginBonusRoundRepository.findAll();
        assertThat(mLoginBonusRoundList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLoginBonusRound.class);
        MLoginBonusRound mLoginBonusRound1 = new MLoginBonusRound();
        mLoginBonusRound1.setId(1L);
        MLoginBonusRound mLoginBonusRound2 = new MLoginBonusRound();
        mLoginBonusRound2.setId(mLoginBonusRound1.getId());
        assertThat(mLoginBonusRound1).isEqualTo(mLoginBonusRound2);
        mLoginBonusRound2.setId(2L);
        assertThat(mLoginBonusRound1).isNotEqualTo(mLoginBonusRound2);
        mLoginBonusRound1.setId(null);
        assertThat(mLoginBonusRound1).isNotEqualTo(mLoginBonusRound2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MLoginBonusRoundDTO.class);
        MLoginBonusRoundDTO mLoginBonusRoundDTO1 = new MLoginBonusRoundDTO();
        mLoginBonusRoundDTO1.setId(1L);
        MLoginBonusRoundDTO mLoginBonusRoundDTO2 = new MLoginBonusRoundDTO();
        assertThat(mLoginBonusRoundDTO1).isNotEqualTo(mLoginBonusRoundDTO2);
        mLoginBonusRoundDTO2.setId(mLoginBonusRoundDTO1.getId());
        assertThat(mLoginBonusRoundDTO1).isEqualTo(mLoginBonusRoundDTO2);
        mLoginBonusRoundDTO2.setId(2L);
        assertThat(mLoginBonusRoundDTO1).isNotEqualTo(mLoginBonusRoundDTO2);
        mLoginBonusRoundDTO1.setId(null);
        assertThat(mLoginBonusRoundDTO1).isNotEqualTo(mLoginBonusRoundDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mLoginBonusRoundMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mLoginBonusRoundMapper.fromId(null)).isNull();
    }
}
