package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCardPowerupActionSkill;
import io.shm.tsubasa.domain.MCardThumbnailAssets;
import io.shm.tsubasa.repository.MCardPowerupActionSkillRepository;
import io.shm.tsubasa.service.MCardPowerupActionSkillService;
import io.shm.tsubasa.service.dto.MCardPowerupActionSkillDTO;
import io.shm.tsubasa.service.mapper.MCardPowerupActionSkillMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCardPowerupActionSkillCriteria;
import io.shm.tsubasa.service.MCardPowerupActionSkillQueryService;

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
 * Integration tests for the {@Link MCardPowerupActionSkillResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCardPowerupActionSkillResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_RARITY = 1;
    private static final Integer UPDATED_RARITY = 2;

    private static final Integer DEFAULT_ATTRIBUTE = 1;
    private static final Integer UPDATED_ATTRIBUTE = 2;

    private static final Integer DEFAULT_ACTION_RARITY = 1;
    private static final Integer UPDATED_ACTION_RARITY = 2;

    private static final Integer DEFAULT_GAIN_ACTION_EXP = 1;
    private static final Integer UPDATED_GAIN_ACTION_EXP = 2;

    private static final Integer DEFAULT_COIN = 1;
    private static final Integer UPDATED_COIN = 2;

    private static final Integer DEFAULT_SELL_MEDAL_ID = 1;
    private static final Integer UPDATED_SELL_MEDAL_ID = 2;

    private static final Integer DEFAULT_THUMBNAIL_ASSETS_ID = 1;
    private static final Integer UPDATED_THUMBNAIL_ASSETS_ID = 2;

    private static final Integer DEFAULT_CARD_ILLUST_ASSETS_ID = 1;
    private static final Integer UPDATED_CARD_ILLUST_ASSETS_ID = 2;

    private static final Integer DEFAULT_TARGET_ACTION_COMMAND_TYPE = 1;
    private static final Integer UPDATED_TARGET_ACTION_COMMAND_TYPE = 2;

    private static final Integer DEFAULT_TARGET_CHARACTER_ID = 1;
    private static final Integer UPDATED_TARGET_CHARACTER_ID = 2;

    @Autowired
    private MCardPowerupActionSkillRepository mCardPowerupActionSkillRepository;

    @Autowired
    private MCardPowerupActionSkillMapper mCardPowerupActionSkillMapper;

    @Autowired
    private MCardPowerupActionSkillService mCardPowerupActionSkillService;

    @Autowired
    private MCardPowerupActionSkillQueryService mCardPowerupActionSkillQueryService;

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

    private MockMvc restMCardPowerupActionSkillMockMvc;

    private MCardPowerupActionSkill mCardPowerupActionSkill;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCardPowerupActionSkillResource mCardPowerupActionSkillResource = new MCardPowerupActionSkillResource(mCardPowerupActionSkillService, mCardPowerupActionSkillQueryService);
        this.restMCardPowerupActionSkillMockMvc = MockMvcBuilders.standaloneSetup(mCardPowerupActionSkillResource)
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
    public static MCardPowerupActionSkill createEntity(EntityManager em) {
        MCardPowerupActionSkill mCardPowerupActionSkill = new MCardPowerupActionSkill()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .rarity(DEFAULT_RARITY)
            .attribute(DEFAULT_ATTRIBUTE)
            .actionRarity(DEFAULT_ACTION_RARITY)
            .gainActionExp(DEFAULT_GAIN_ACTION_EXP)
            .coin(DEFAULT_COIN)
            .sellMedalId(DEFAULT_SELL_MEDAL_ID)
            .thumbnailAssetsId(DEFAULT_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(DEFAULT_CARD_ILLUST_ASSETS_ID)
            .targetActionCommandType(DEFAULT_TARGET_ACTION_COMMAND_TYPE)
            .targetCharacterId(DEFAULT_TARGET_CHARACTER_ID);
        // Add required entity
        MCardThumbnailAssets mCardThumbnailAssets;
        if (TestUtil.findAll(em, MCardThumbnailAssets.class).isEmpty()) {
            mCardThumbnailAssets = MCardThumbnailAssetsResourceIT.createEntity(em);
            em.persist(mCardThumbnailAssets);
            em.flush();
        } else {
            mCardThumbnailAssets = TestUtil.findAll(em, MCardThumbnailAssets.class).get(0);
        }
        mCardPowerupActionSkill.setMcardthumbnailassets(mCardThumbnailAssets);
        return mCardPowerupActionSkill;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCardPowerupActionSkill createUpdatedEntity(EntityManager em) {
        MCardPowerupActionSkill mCardPowerupActionSkill = new MCardPowerupActionSkill()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .rarity(UPDATED_RARITY)
            .attribute(UPDATED_ATTRIBUTE)
            .actionRarity(UPDATED_ACTION_RARITY)
            .gainActionExp(UPDATED_GAIN_ACTION_EXP)
            .coin(UPDATED_COIN)
            .sellMedalId(UPDATED_SELL_MEDAL_ID)
            .thumbnailAssetsId(UPDATED_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(UPDATED_CARD_ILLUST_ASSETS_ID)
            .targetActionCommandType(UPDATED_TARGET_ACTION_COMMAND_TYPE)
            .targetCharacterId(UPDATED_TARGET_CHARACTER_ID);
        // Add required entity
        MCardThumbnailAssets mCardThumbnailAssets;
        if (TestUtil.findAll(em, MCardThumbnailAssets.class).isEmpty()) {
            mCardThumbnailAssets = MCardThumbnailAssetsResourceIT.createUpdatedEntity(em);
            em.persist(mCardThumbnailAssets);
            em.flush();
        } else {
            mCardThumbnailAssets = TestUtil.findAll(em, MCardThumbnailAssets.class).get(0);
        }
        mCardPowerupActionSkill.setMcardthumbnailassets(mCardThumbnailAssets);
        return mCardPowerupActionSkill;
    }

    @BeforeEach
    public void initTest() {
        mCardPowerupActionSkill = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCardPowerupActionSkill() throws Exception {
        int databaseSizeBeforeCreate = mCardPowerupActionSkillRepository.findAll().size();

        // Create the MCardPowerupActionSkill
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);
        restMCardPowerupActionSkillMockMvc.perform(post("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isCreated());

        // Validate the MCardPowerupActionSkill in the database
        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeCreate + 1);
        MCardPowerupActionSkill testMCardPowerupActionSkill = mCardPowerupActionSkillList.get(mCardPowerupActionSkillList.size() - 1);
        assertThat(testMCardPowerupActionSkill.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMCardPowerupActionSkill.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMCardPowerupActionSkill.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMCardPowerupActionSkill.getRarity()).isEqualTo(DEFAULT_RARITY);
        assertThat(testMCardPowerupActionSkill.getAttribute()).isEqualTo(DEFAULT_ATTRIBUTE);
        assertThat(testMCardPowerupActionSkill.getActionRarity()).isEqualTo(DEFAULT_ACTION_RARITY);
        assertThat(testMCardPowerupActionSkill.getGainActionExp()).isEqualTo(DEFAULT_GAIN_ACTION_EXP);
        assertThat(testMCardPowerupActionSkill.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMCardPowerupActionSkill.getSellMedalId()).isEqualTo(DEFAULT_SELL_MEDAL_ID);
        assertThat(testMCardPowerupActionSkill.getThumbnailAssetsId()).isEqualTo(DEFAULT_THUMBNAIL_ASSETS_ID);
        assertThat(testMCardPowerupActionSkill.getCardIllustAssetsId()).isEqualTo(DEFAULT_CARD_ILLUST_ASSETS_ID);
        assertThat(testMCardPowerupActionSkill.getTargetActionCommandType()).isEqualTo(DEFAULT_TARGET_ACTION_COMMAND_TYPE);
        assertThat(testMCardPowerupActionSkill.getTargetCharacterId()).isEqualTo(DEFAULT_TARGET_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void createMCardPowerupActionSkillWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCardPowerupActionSkillRepository.findAll().size();

        // Create the MCardPowerupActionSkill with an existing ID
        mCardPowerupActionSkill.setId(1L);
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCardPowerupActionSkillMockMvc.perform(post("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardPowerupActionSkill in the database
        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardPowerupActionSkillRepository.findAll().size();
        // set the field null
        mCardPowerupActionSkill.setRarity(null);

        // Create the MCardPowerupActionSkill, which fails.
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);

        restMCardPowerupActionSkillMockMvc.perform(post("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isBadRequest());

        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAttributeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardPowerupActionSkillRepository.findAll().size();
        // set the field null
        mCardPowerupActionSkill.setAttribute(null);

        // Create the MCardPowerupActionSkill, which fails.
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);

        restMCardPowerupActionSkillMockMvc.perform(post("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isBadRequest());

        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionRarityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardPowerupActionSkillRepository.findAll().size();
        // set the field null
        mCardPowerupActionSkill.setActionRarity(null);

        // Create the MCardPowerupActionSkill, which fails.
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);

        restMCardPowerupActionSkillMockMvc.perform(post("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isBadRequest());

        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGainActionExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardPowerupActionSkillRepository.findAll().size();
        // set the field null
        mCardPowerupActionSkill.setGainActionExp(null);

        // Create the MCardPowerupActionSkill, which fails.
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);

        restMCardPowerupActionSkillMockMvc.perform(post("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isBadRequest());

        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardPowerupActionSkillRepository.findAll().size();
        // set the field null
        mCardPowerupActionSkill.setCoin(null);

        // Create the MCardPowerupActionSkill, which fails.
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);

        restMCardPowerupActionSkillMockMvc.perform(post("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isBadRequest());

        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkThumbnailAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardPowerupActionSkillRepository.findAll().size();
        // set the field null
        mCardPowerupActionSkill.setThumbnailAssetsId(null);

        // Create the MCardPowerupActionSkill, which fails.
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);

        restMCardPowerupActionSkillMockMvc.perform(post("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isBadRequest());

        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardIllustAssetsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCardPowerupActionSkillRepository.findAll().size();
        // set the field null
        mCardPowerupActionSkill.setCardIllustAssetsId(null);

        // Create the MCardPowerupActionSkill, which fails.
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);

        restMCardPowerupActionSkillMockMvc.perform(post("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isBadRequest());

        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkills() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList
        restMCardPowerupActionSkillMockMvc.perform(get("/api/m-card-powerup-action-skills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardPowerupActionSkill.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].attribute").value(hasItem(DEFAULT_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].actionRarity").value(hasItem(DEFAULT_ACTION_RARITY)))
            .andExpect(jsonPath("$.[*].gainActionExp").value(hasItem(DEFAULT_GAIN_ACTION_EXP)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].sellMedalId").value(hasItem(DEFAULT_SELL_MEDAL_ID)))
            .andExpect(jsonPath("$.[*].thumbnailAssetsId").value(hasItem(DEFAULT_THUMBNAIL_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].cardIllustAssetsId").value(hasItem(DEFAULT_CARD_ILLUST_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].targetActionCommandType").value(hasItem(DEFAULT_TARGET_ACTION_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].targetCharacterId").value(hasItem(DEFAULT_TARGET_CHARACTER_ID)));
    }
    
    @Test
    @Transactional
    public void getMCardPowerupActionSkill() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get the mCardPowerupActionSkill
        restMCardPowerupActionSkillMockMvc.perform(get("/api/m-card-powerup-action-skills/{id}", mCardPowerupActionSkill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCardPowerupActionSkill.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY))
            .andExpect(jsonPath("$.attribute").value(DEFAULT_ATTRIBUTE))
            .andExpect(jsonPath("$.actionRarity").value(DEFAULT_ACTION_RARITY))
            .andExpect(jsonPath("$.gainActionExp").value(DEFAULT_GAIN_ACTION_EXP))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN))
            .andExpect(jsonPath("$.sellMedalId").value(DEFAULT_SELL_MEDAL_ID))
            .andExpect(jsonPath("$.thumbnailAssetsId").value(DEFAULT_THUMBNAIL_ASSETS_ID))
            .andExpect(jsonPath("$.cardIllustAssetsId").value(DEFAULT_CARD_ILLUST_ASSETS_ID))
            .andExpect(jsonPath("$.targetActionCommandType").value(DEFAULT_TARGET_ACTION_COMMAND_TYPE))
            .andExpect(jsonPath("$.targetCharacterId").value(DEFAULT_TARGET_CHARACTER_ID));
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where rarity equals to DEFAULT_RARITY
        defaultMCardPowerupActionSkillShouldBeFound("rarity.equals=" + DEFAULT_RARITY);

        // Get all the mCardPowerupActionSkillList where rarity equals to UPDATED_RARITY
        defaultMCardPowerupActionSkillShouldNotBeFound("rarity.equals=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where rarity in DEFAULT_RARITY or UPDATED_RARITY
        defaultMCardPowerupActionSkillShouldBeFound("rarity.in=" + DEFAULT_RARITY + "," + UPDATED_RARITY);

        // Get all the mCardPowerupActionSkillList where rarity equals to UPDATED_RARITY
        defaultMCardPowerupActionSkillShouldNotBeFound("rarity.in=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where rarity is not null
        defaultMCardPowerupActionSkillShouldBeFound("rarity.specified=true");

        // Get all the mCardPowerupActionSkillList where rarity is null
        defaultMCardPowerupActionSkillShouldNotBeFound("rarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where rarity greater than or equals to DEFAULT_RARITY
        defaultMCardPowerupActionSkillShouldBeFound("rarity.greaterOrEqualThan=" + DEFAULT_RARITY);

        // Get all the mCardPowerupActionSkillList where rarity greater than or equals to UPDATED_RARITY
        defaultMCardPowerupActionSkillShouldNotBeFound("rarity.greaterOrEqualThan=" + UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where rarity less than or equals to DEFAULT_RARITY
        defaultMCardPowerupActionSkillShouldNotBeFound("rarity.lessThan=" + DEFAULT_RARITY);

        // Get all the mCardPowerupActionSkillList where rarity less than or equals to UPDATED_RARITY
        defaultMCardPowerupActionSkillShouldBeFound("rarity.lessThan=" + UPDATED_RARITY);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByAttributeIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where attribute equals to DEFAULT_ATTRIBUTE
        defaultMCardPowerupActionSkillShouldBeFound("attribute.equals=" + DEFAULT_ATTRIBUTE);

        // Get all the mCardPowerupActionSkillList where attribute equals to UPDATED_ATTRIBUTE
        defaultMCardPowerupActionSkillShouldNotBeFound("attribute.equals=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByAttributeIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where attribute in DEFAULT_ATTRIBUTE or UPDATED_ATTRIBUTE
        defaultMCardPowerupActionSkillShouldBeFound("attribute.in=" + DEFAULT_ATTRIBUTE + "," + UPDATED_ATTRIBUTE);

        // Get all the mCardPowerupActionSkillList where attribute equals to UPDATED_ATTRIBUTE
        defaultMCardPowerupActionSkillShouldNotBeFound("attribute.in=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByAttributeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where attribute is not null
        defaultMCardPowerupActionSkillShouldBeFound("attribute.specified=true");

        // Get all the mCardPowerupActionSkillList where attribute is null
        defaultMCardPowerupActionSkillShouldNotBeFound("attribute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByAttributeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where attribute greater than or equals to DEFAULT_ATTRIBUTE
        defaultMCardPowerupActionSkillShouldBeFound("attribute.greaterOrEqualThan=" + DEFAULT_ATTRIBUTE);

        // Get all the mCardPowerupActionSkillList where attribute greater than or equals to UPDATED_ATTRIBUTE
        defaultMCardPowerupActionSkillShouldNotBeFound("attribute.greaterOrEqualThan=" + UPDATED_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByAttributeIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where attribute less than or equals to DEFAULT_ATTRIBUTE
        defaultMCardPowerupActionSkillShouldNotBeFound("attribute.lessThan=" + DEFAULT_ATTRIBUTE);

        // Get all the mCardPowerupActionSkillList where attribute less than or equals to UPDATED_ATTRIBUTE
        defaultMCardPowerupActionSkillShouldBeFound("attribute.lessThan=" + UPDATED_ATTRIBUTE);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByActionRarityIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where actionRarity equals to DEFAULT_ACTION_RARITY
        defaultMCardPowerupActionSkillShouldBeFound("actionRarity.equals=" + DEFAULT_ACTION_RARITY);

        // Get all the mCardPowerupActionSkillList where actionRarity equals to UPDATED_ACTION_RARITY
        defaultMCardPowerupActionSkillShouldNotBeFound("actionRarity.equals=" + UPDATED_ACTION_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByActionRarityIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where actionRarity in DEFAULT_ACTION_RARITY or UPDATED_ACTION_RARITY
        defaultMCardPowerupActionSkillShouldBeFound("actionRarity.in=" + DEFAULT_ACTION_RARITY + "," + UPDATED_ACTION_RARITY);

        // Get all the mCardPowerupActionSkillList where actionRarity equals to UPDATED_ACTION_RARITY
        defaultMCardPowerupActionSkillShouldNotBeFound("actionRarity.in=" + UPDATED_ACTION_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByActionRarityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where actionRarity is not null
        defaultMCardPowerupActionSkillShouldBeFound("actionRarity.specified=true");

        // Get all the mCardPowerupActionSkillList where actionRarity is null
        defaultMCardPowerupActionSkillShouldNotBeFound("actionRarity.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByActionRarityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where actionRarity greater than or equals to DEFAULT_ACTION_RARITY
        defaultMCardPowerupActionSkillShouldBeFound("actionRarity.greaterOrEqualThan=" + DEFAULT_ACTION_RARITY);

        // Get all the mCardPowerupActionSkillList where actionRarity greater than or equals to UPDATED_ACTION_RARITY
        defaultMCardPowerupActionSkillShouldNotBeFound("actionRarity.greaterOrEqualThan=" + UPDATED_ACTION_RARITY);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByActionRarityIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where actionRarity less than or equals to DEFAULT_ACTION_RARITY
        defaultMCardPowerupActionSkillShouldNotBeFound("actionRarity.lessThan=" + DEFAULT_ACTION_RARITY);

        // Get all the mCardPowerupActionSkillList where actionRarity less than or equals to UPDATED_ACTION_RARITY
        defaultMCardPowerupActionSkillShouldBeFound("actionRarity.lessThan=" + UPDATED_ACTION_RARITY);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByGainActionExpIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where gainActionExp equals to DEFAULT_GAIN_ACTION_EXP
        defaultMCardPowerupActionSkillShouldBeFound("gainActionExp.equals=" + DEFAULT_GAIN_ACTION_EXP);

        // Get all the mCardPowerupActionSkillList where gainActionExp equals to UPDATED_GAIN_ACTION_EXP
        defaultMCardPowerupActionSkillShouldNotBeFound("gainActionExp.equals=" + UPDATED_GAIN_ACTION_EXP);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByGainActionExpIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where gainActionExp in DEFAULT_GAIN_ACTION_EXP or UPDATED_GAIN_ACTION_EXP
        defaultMCardPowerupActionSkillShouldBeFound("gainActionExp.in=" + DEFAULT_GAIN_ACTION_EXP + "," + UPDATED_GAIN_ACTION_EXP);

        // Get all the mCardPowerupActionSkillList where gainActionExp equals to UPDATED_GAIN_ACTION_EXP
        defaultMCardPowerupActionSkillShouldNotBeFound("gainActionExp.in=" + UPDATED_GAIN_ACTION_EXP);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByGainActionExpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where gainActionExp is not null
        defaultMCardPowerupActionSkillShouldBeFound("gainActionExp.specified=true");

        // Get all the mCardPowerupActionSkillList where gainActionExp is null
        defaultMCardPowerupActionSkillShouldNotBeFound("gainActionExp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByGainActionExpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where gainActionExp greater than or equals to DEFAULT_GAIN_ACTION_EXP
        defaultMCardPowerupActionSkillShouldBeFound("gainActionExp.greaterOrEqualThan=" + DEFAULT_GAIN_ACTION_EXP);

        // Get all the mCardPowerupActionSkillList where gainActionExp greater than or equals to UPDATED_GAIN_ACTION_EXP
        defaultMCardPowerupActionSkillShouldNotBeFound("gainActionExp.greaterOrEqualThan=" + UPDATED_GAIN_ACTION_EXP);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByGainActionExpIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where gainActionExp less than or equals to DEFAULT_GAIN_ACTION_EXP
        defaultMCardPowerupActionSkillShouldNotBeFound("gainActionExp.lessThan=" + DEFAULT_GAIN_ACTION_EXP);

        // Get all the mCardPowerupActionSkillList where gainActionExp less than or equals to UPDATED_GAIN_ACTION_EXP
        defaultMCardPowerupActionSkillShouldBeFound("gainActionExp.lessThan=" + UPDATED_GAIN_ACTION_EXP);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where coin equals to DEFAULT_COIN
        defaultMCardPowerupActionSkillShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mCardPowerupActionSkillList where coin equals to UPDATED_COIN
        defaultMCardPowerupActionSkillShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMCardPowerupActionSkillShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mCardPowerupActionSkillList where coin equals to UPDATED_COIN
        defaultMCardPowerupActionSkillShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where coin is not null
        defaultMCardPowerupActionSkillShouldBeFound("coin.specified=true");

        // Get all the mCardPowerupActionSkillList where coin is null
        defaultMCardPowerupActionSkillShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where coin greater than or equals to DEFAULT_COIN
        defaultMCardPowerupActionSkillShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mCardPowerupActionSkillList where coin greater than or equals to UPDATED_COIN
        defaultMCardPowerupActionSkillShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where coin less than or equals to DEFAULT_COIN
        defaultMCardPowerupActionSkillShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mCardPowerupActionSkillList where coin less than or equals to UPDATED_COIN
        defaultMCardPowerupActionSkillShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsBySellMedalIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where sellMedalId equals to DEFAULT_SELL_MEDAL_ID
        defaultMCardPowerupActionSkillShouldBeFound("sellMedalId.equals=" + DEFAULT_SELL_MEDAL_ID);

        // Get all the mCardPowerupActionSkillList where sellMedalId equals to UPDATED_SELL_MEDAL_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("sellMedalId.equals=" + UPDATED_SELL_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsBySellMedalIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where sellMedalId in DEFAULT_SELL_MEDAL_ID or UPDATED_SELL_MEDAL_ID
        defaultMCardPowerupActionSkillShouldBeFound("sellMedalId.in=" + DEFAULT_SELL_MEDAL_ID + "," + UPDATED_SELL_MEDAL_ID);

        // Get all the mCardPowerupActionSkillList where sellMedalId equals to UPDATED_SELL_MEDAL_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("sellMedalId.in=" + UPDATED_SELL_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsBySellMedalIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where sellMedalId is not null
        defaultMCardPowerupActionSkillShouldBeFound("sellMedalId.specified=true");

        // Get all the mCardPowerupActionSkillList where sellMedalId is null
        defaultMCardPowerupActionSkillShouldNotBeFound("sellMedalId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsBySellMedalIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where sellMedalId greater than or equals to DEFAULT_SELL_MEDAL_ID
        defaultMCardPowerupActionSkillShouldBeFound("sellMedalId.greaterOrEqualThan=" + DEFAULT_SELL_MEDAL_ID);

        // Get all the mCardPowerupActionSkillList where sellMedalId greater than or equals to UPDATED_SELL_MEDAL_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("sellMedalId.greaterOrEqualThan=" + UPDATED_SELL_MEDAL_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsBySellMedalIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where sellMedalId less than or equals to DEFAULT_SELL_MEDAL_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("sellMedalId.lessThan=" + DEFAULT_SELL_MEDAL_ID);

        // Get all the mCardPowerupActionSkillList where sellMedalId less than or equals to UPDATED_SELL_MEDAL_ID
        defaultMCardPowerupActionSkillShouldBeFound("sellMedalId.lessThan=" + UPDATED_SELL_MEDAL_ID);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByThumbnailAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMCardPowerupActionSkillShouldBeFound("thumbnailAssetsId.equals=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("thumbnailAssetsId.equals=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByThumbnailAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId in DEFAULT_THUMBNAIL_ASSETS_ID or UPDATED_THUMBNAIL_ASSETS_ID
        defaultMCardPowerupActionSkillShouldBeFound("thumbnailAssetsId.in=" + DEFAULT_THUMBNAIL_ASSETS_ID + "," + UPDATED_THUMBNAIL_ASSETS_ID);

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("thumbnailAssetsId.in=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByThumbnailAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId is not null
        defaultMCardPowerupActionSkillShouldBeFound("thumbnailAssetsId.specified=true");

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId is null
        defaultMCardPowerupActionSkillShouldNotBeFound("thumbnailAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByThumbnailAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId greater than or equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMCardPowerupActionSkillShouldBeFound("thumbnailAssetsId.greaterOrEqualThan=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId greater than or equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("thumbnailAssetsId.greaterOrEqualThan=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByThumbnailAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId less than or equals to DEFAULT_THUMBNAIL_ASSETS_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("thumbnailAssetsId.lessThan=" + DEFAULT_THUMBNAIL_ASSETS_ID);

        // Get all the mCardPowerupActionSkillList where thumbnailAssetsId less than or equals to UPDATED_THUMBNAIL_ASSETS_ID
        defaultMCardPowerupActionSkillShouldBeFound("thumbnailAssetsId.lessThan=" + UPDATED_THUMBNAIL_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCardIllustAssetsIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMCardPowerupActionSkillShouldBeFound("cardIllustAssetsId.equals=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("cardIllustAssetsId.equals=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCardIllustAssetsIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId in DEFAULT_CARD_ILLUST_ASSETS_ID or UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMCardPowerupActionSkillShouldBeFound("cardIllustAssetsId.in=" + DEFAULT_CARD_ILLUST_ASSETS_ID + "," + UPDATED_CARD_ILLUST_ASSETS_ID);

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("cardIllustAssetsId.in=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCardIllustAssetsIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId is not null
        defaultMCardPowerupActionSkillShouldBeFound("cardIllustAssetsId.specified=true");

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId is null
        defaultMCardPowerupActionSkillShouldNotBeFound("cardIllustAssetsId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCardIllustAssetsIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId greater than or equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMCardPowerupActionSkillShouldBeFound("cardIllustAssetsId.greaterOrEqualThan=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId greater than or equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("cardIllustAssetsId.greaterOrEqualThan=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByCardIllustAssetsIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId less than or equals to DEFAULT_CARD_ILLUST_ASSETS_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("cardIllustAssetsId.lessThan=" + DEFAULT_CARD_ILLUST_ASSETS_ID);

        // Get all the mCardPowerupActionSkillList where cardIllustAssetsId less than or equals to UPDATED_CARD_ILLUST_ASSETS_ID
        defaultMCardPowerupActionSkillShouldBeFound("cardIllustAssetsId.lessThan=" + UPDATED_CARD_ILLUST_ASSETS_ID);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetActionCommandTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetActionCommandType equals to DEFAULT_TARGET_ACTION_COMMAND_TYPE
        defaultMCardPowerupActionSkillShouldBeFound("targetActionCommandType.equals=" + DEFAULT_TARGET_ACTION_COMMAND_TYPE);

        // Get all the mCardPowerupActionSkillList where targetActionCommandType equals to UPDATED_TARGET_ACTION_COMMAND_TYPE
        defaultMCardPowerupActionSkillShouldNotBeFound("targetActionCommandType.equals=" + UPDATED_TARGET_ACTION_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetActionCommandTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetActionCommandType in DEFAULT_TARGET_ACTION_COMMAND_TYPE or UPDATED_TARGET_ACTION_COMMAND_TYPE
        defaultMCardPowerupActionSkillShouldBeFound("targetActionCommandType.in=" + DEFAULT_TARGET_ACTION_COMMAND_TYPE + "," + UPDATED_TARGET_ACTION_COMMAND_TYPE);

        // Get all the mCardPowerupActionSkillList where targetActionCommandType equals to UPDATED_TARGET_ACTION_COMMAND_TYPE
        defaultMCardPowerupActionSkillShouldNotBeFound("targetActionCommandType.in=" + UPDATED_TARGET_ACTION_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetActionCommandTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetActionCommandType is not null
        defaultMCardPowerupActionSkillShouldBeFound("targetActionCommandType.specified=true");

        // Get all the mCardPowerupActionSkillList where targetActionCommandType is null
        defaultMCardPowerupActionSkillShouldNotBeFound("targetActionCommandType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetActionCommandTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetActionCommandType greater than or equals to DEFAULT_TARGET_ACTION_COMMAND_TYPE
        defaultMCardPowerupActionSkillShouldBeFound("targetActionCommandType.greaterOrEqualThan=" + DEFAULT_TARGET_ACTION_COMMAND_TYPE);

        // Get all the mCardPowerupActionSkillList where targetActionCommandType greater than or equals to UPDATED_TARGET_ACTION_COMMAND_TYPE
        defaultMCardPowerupActionSkillShouldNotBeFound("targetActionCommandType.greaterOrEqualThan=" + UPDATED_TARGET_ACTION_COMMAND_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetActionCommandTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetActionCommandType less than or equals to DEFAULT_TARGET_ACTION_COMMAND_TYPE
        defaultMCardPowerupActionSkillShouldNotBeFound("targetActionCommandType.lessThan=" + DEFAULT_TARGET_ACTION_COMMAND_TYPE);

        // Get all the mCardPowerupActionSkillList where targetActionCommandType less than or equals to UPDATED_TARGET_ACTION_COMMAND_TYPE
        defaultMCardPowerupActionSkillShouldBeFound("targetActionCommandType.lessThan=" + UPDATED_TARGET_ACTION_COMMAND_TYPE);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetCharacterId equals to DEFAULT_TARGET_CHARACTER_ID
        defaultMCardPowerupActionSkillShouldBeFound("targetCharacterId.equals=" + DEFAULT_TARGET_CHARACTER_ID);

        // Get all the mCardPowerupActionSkillList where targetCharacterId equals to UPDATED_TARGET_CHARACTER_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("targetCharacterId.equals=" + UPDATED_TARGET_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetCharacterId in DEFAULT_TARGET_CHARACTER_ID or UPDATED_TARGET_CHARACTER_ID
        defaultMCardPowerupActionSkillShouldBeFound("targetCharacterId.in=" + DEFAULT_TARGET_CHARACTER_ID + "," + UPDATED_TARGET_CHARACTER_ID);

        // Get all the mCardPowerupActionSkillList where targetCharacterId equals to UPDATED_TARGET_CHARACTER_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("targetCharacterId.in=" + UPDATED_TARGET_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetCharacterId is not null
        defaultMCardPowerupActionSkillShouldBeFound("targetCharacterId.specified=true");

        // Get all the mCardPowerupActionSkillList where targetCharacterId is null
        defaultMCardPowerupActionSkillShouldNotBeFound("targetCharacterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetCharacterId greater than or equals to DEFAULT_TARGET_CHARACTER_ID
        defaultMCardPowerupActionSkillShouldBeFound("targetCharacterId.greaterOrEqualThan=" + DEFAULT_TARGET_CHARACTER_ID);

        // Get all the mCardPowerupActionSkillList where targetCharacterId greater than or equals to UPDATED_TARGET_CHARACTER_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("targetCharacterId.greaterOrEqualThan=" + UPDATED_TARGET_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByTargetCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        // Get all the mCardPowerupActionSkillList where targetCharacterId less than or equals to DEFAULT_TARGET_CHARACTER_ID
        defaultMCardPowerupActionSkillShouldNotBeFound("targetCharacterId.lessThan=" + DEFAULT_TARGET_CHARACTER_ID);

        // Get all the mCardPowerupActionSkillList where targetCharacterId less than or equals to UPDATED_TARGET_CHARACTER_ID
        defaultMCardPowerupActionSkillShouldBeFound("targetCharacterId.lessThan=" + UPDATED_TARGET_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMCardPowerupActionSkillsByMcardthumbnailassetsIsEqualToSomething() throws Exception {
        // Get already existing entity
        MCardThumbnailAssets mcardthumbnailassets = mCardPowerupActionSkill.getMcardthumbnailassets();
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);
        Long mcardthumbnailassetsId = mcardthumbnailassets.getId();

        // Get all the mCardPowerupActionSkillList where mcardthumbnailassets equals to mcardthumbnailassetsId
        defaultMCardPowerupActionSkillShouldBeFound("mcardthumbnailassetsId.equals=" + mcardthumbnailassetsId);

        // Get all the mCardPowerupActionSkillList where mcardthumbnailassets equals to mcardthumbnailassetsId + 1
        defaultMCardPowerupActionSkillShouldNotBeFound("mcardthumbnailassetsId.equals=" + (mcardthumbnailassetsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCardPowerupActionSkillShouldBeFound(String filter) throws Exception {
        restMCardPowerupActionSkillMockMvc.perform(get("/api/m-card-powerup-action-skills?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCardPowerupActionSkill.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY)))
            .andExpect(jsonPath("$.[*].attribute").value(hasItem(DEFAULT_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].actionRarity").value(hasItem(DEFAULT_ACTION_RARITY)))
            .andExpect(jsonPath("$.[*].gainActionExp").value(hasItem(DEFAULT_GAIN_ACTION_EXP)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].sellMedalId").value(hasItem(DEFAULT_SELL_MEDAL_ID)))
            .andExpect(jsonPath("$.[*].thumbnailAssetsId").value(hasItem(DEFAULT_THUMBNAIL_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].cardIllustAssetsId").value(hasItem(DEFAULT_CARD_ILLUST_ASSETS_ID)))
            .andExpect(jsonPath("$.[*].targetActionCommandType").value(hasItem(DEFAULT_TARGET_ACTION_COMMAND_TYPE)))
            .andExpect(jsonPath("$.[*].targetCharacterId").value(hasItem(DEFAULT_TARGET_CHARACTER_ID)));

        // Check, that the count call also returns 1
        restMCardPowerupActionSkillMockMvc.perform(get("/api/m-card-powerup-action-skills/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCardPowerupActionSkillShouldNotBeFound(String filter) throws Exception {
        restMCardPowerupActionSkillMockMvc.perform(get("/api/m-card-powerup-action-skills?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCardPowerupActionSkillMockMvc.perform(get("/api/m-card-powerup-action-skills/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCardPowerupActionSkill() throws Exception {
        // Get the mCardPowerupActionSkill
        restMCardPowerupActionSkillMockMvc.perform(get("/api/m-card-powerup-action-skills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCardPowerupActionSkill() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        int databaseSizeBeforeUpdate = mCardPowerupActionSkillRepository.findAll().size();

        // Update the mCardPowerupActionSkill
        MCardPowerupActionSkill updatedMCardPowerupActionSkill = mCardPowerupActionSkillRepository.findById(mCardPowerupActionSkill.getId()).get();
        // Disconnect from session so that the updates on updatedMCardPowerupActionSkill are not directly saved in db
        em.detach(updatedMCardPowerupActionSkill);
        updatedMCardPowerupActionSkill
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .rarity(UPDATED_RARITY)
            .attribute(UPDATED_ATTRIBUTE)
            .actionRarity(UPDATED_ACTION_RARITY)
            .gainActionExp(UPDATED_GAIN_ACTION_EXP)
            .coin(UPDATED_COIN)
            .sellMedalId(UPDATED_SELL_MEDAL_ID)
            .thumbnailAssetsId(UPDATED_THUMBNAIL_ASSETS_ID)
            .cardIllustAssetsId(UPDATED_CARD_ILLUST_ASSETS_ID)
            .targetActionCommandType(UPDATED_TARGET_ACTION_COMMAND_TYPE)
            .targetCharacterId(UPDATED_TARGET_CHARACTER_ID);
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(updatedMCardPowerupActionSkill);

        restMCardPowerupActionSkillMockMvc.perform(put("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isOk());

        // Validate the MCardPowerupActionSkill in the database
        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeUpdate);
        MCardPowerupActionSkill testMCardPowerupActionSkill = mCardPowerupActionSkillList.get(mCardPowerupActionSkillList.size() - 1);
        assertThat(testMCardPowerupActionSkill.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMCardPowerupActionSkill.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMCardPowerupActionSkill.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMCardPowerupActionSkill.getRarity()).isEqualTo(UPDATED_RARITY);
        assertThat(testMCardPowerupActionSkill.getAttribute()).isEqualTo(UPDATED_ATTRIBUTE);
        assertThat(testMCardPowerupActionSkill.getActionRarity()).isEqualTo(UPDATED_ACTION_RARITY);
        assertThat(testMCardPowerupActionSkill.getGainActionExp()).isEqualTo(UPDATED_GAIN_ACTION_EXP);
        assertThat(testMCardPowerupActionSkill.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMCardPowerupActionSkill.getSellMedalId()).isEqualTo(UPDATED_SELL_MEDAL_ID);
        assertThat(testMCardPowerupActionSkill.getThumbnailAssetsId()).isEqualTo(UPDATED_THUMBNAIL_ASSETS_ID);
        assertThat(testMCardPowerupActionSkill.getCardIllustAssetsId()).isEqualTo(UPDATED_CARD_ILLUST_ASSETS_ID);
        assertThat(testMCardPowerupActionSkill.getTargetActionCommandType()).isEqualTo(UPDATED_TARGET_ACTION_COMMAND_TYPE);
        assertThat(testMCardPowerupActionSkill.getTargetCharacterId()).isEqualTo(UPDATED_TARGET_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMCardPowerupActionSkill() throws Exception {
        int databaseSizeBeforeUpdate = mCardPowerupActionSkillRepository.findAll().size();

        // Create the MCardPowerupActionSkill
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO = mCardPowerupActionSkillMapper.toDto(mCardPowerupActionSkill);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCardPowerupActionSkillMockMvc.perform(put("/api/m-card-powerup-action-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCardPowerupActionSkillDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCardPowerupActionSkill in the database
        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCardPowerupActionSkill() throws Exception {
        // Initialize the database
        mCardPowerupActionSkillRepository.saveAndFlush(mCardPowerupActionSkill);

        int databaseSizeBeforeDelete = mCardPowerupActionSkillRepository.findAll().size();

        // Delete the mCardPowerupActionSkill
        restMCardPowerupActionSkillMockMvc.perform(delete("/api/m-card-powerup-action-skills/{id}", mCardPowerupActionSkill.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCardPowerupActionSkill> mCardPowerupActionSkillList = mCardPowerupActionSkillRepository.findAll();
        assertThat(mCardPowerupActionSkillList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardPowerupActionSkill.class);
        MCardPowerupActionSkill mCardPowerupActionSkill1 = new MCardPowerupActionSkill();
        mCardPowerupActionSkill1.setId(1L);
        MCardPowerupActionSkill mCardPowerupActionSkill2 = new MCardPowerupActionSkill();
        mCardPowerupActionSkill2.setId(mCardPowerupActionSkill1.getId());
        assertThat(mCardPowerupActionSkill1).isEqualTo(mCardPowerupActionSkill2);
        mCardPowerupActionSkill2.setId(2L);
        assertThat(mCardPowerupActionSkill1).isNotEqualTo(mCardPowerupActionSkill2);
        mCardPowerupActionSkill1.setId(null);
        assertThat(mCardPowerupActionSkill1).isNotEqualTo(mCardPowerupActionSkill2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCardPowerupActionSkillDTO.class);
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO1 = new MCardPowerupActionSkillDTO();
        mCardPowerupActionSkillDTO1.setId(1L);
        MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO2 = new MCardPowerupActionSkillDTO();
        assertThat(mCardPowerupActionSkillDTO1).isNotEqualTo(mCardPowerupActionSkillDTO2);
        mCardPowerupActionSkillDTO2.setId(mCardPowerupActionSkillDTO1.getId());
        assertThat(mCardPowerupActionSkillDTO1).isEqualTo(mCardPowerupActionSkillDTO2);
        mCardPowerupActionSkillDTO2.setId(2L);
        assertThat(mCardPowerupActionSkillDTO1).isNotEqualTo(mCardPowerupActionSkillDTO2);
        mCardPowerupActionSkillDTO1.setId(null);
        assertThat(mCardPowerupActionSkillDTO1).isNotEqualTo(mCardPowerupActionSkillDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCardPowerupActionSkillMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCardPowerupActionSkillMapper.fromId(null)).isNull();
    }
}
