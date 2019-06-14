package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MNpcDeck;
import io.shm.tsubasa.domain.MFormation;
import io.shm.tsubasa.domain.MDummyOpponent;
import io.shm.tsubasa.repository.MNpcDeckRepository;
import io.shm.tsubasa.service.MNpcDeckService;
import io.shm.tsubasa.service.dto.MNpcDeckDTO;
import io.shm.tsubasa.service.mapper.MNpcDeckMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MNpcDeckCriteria;
import io.shm.tsubasa.service.MNpcDeckQueryService;

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
 * Integration tests for the {@Link MNpcDeckResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MNpcDeckResourceIT {

    private static final String DEFAULT_TEAM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TEAM_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_UNIFORM_BOTTOM_FP = 1;
    private static final Integer UPDATED_UNIFORM_BOTTOM_FP = 2;

    private static final Integer DEFAULT_UNIFORM_UP_FP = 1;
    private static final Integer UPDATED_UNIFORM_UP_FP = 2;

    private static final Integer DEFAULT_UNIFORM_BOTTOM_GK = 1;
    private static final Integer UPDATED_UNIFORM_BOTTOM_GK = 2;

    private static final Integer DEFAULT_UNIFORM_UP_GK = 1;
    private static final Integer UPDATED_UNIFORM_UP_GK = 2;

    private static final Integer DEFAULT_FORMATION_ID = 1;
    private static final Integer UPDATED_FORMATION_ID = 2;

    private static final Integer DEFAULT_CAPTAIN_CARD_ID = 1;
    private static final Integer UPDATED_CAPTAIN_CARD_ID = 2;

    private static final Integer DEFAULT_TEAM_EFFECT_1_CARD_ID = 1;
    private static final Integer UPDATED_TEAM_EFFECT_1_CARD_ID = 2;

    private static final Integer DEFAULT_TEAM_EFFECT_2_CARD_ID = 1;
    private static final Integer UPDATED_TEAM_EFFECT_2_CARD_ID = 2;

    private static final Integer DEFAULT_TEAM_EFFECT_3_CARD_ID = 1;
    private static final Integer UPDATED_TEAM_EFFECT_3_CARD_ID = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_1 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_1 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_2 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_2 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_3 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_3 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_4 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_4 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_5 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_5 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_6 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_6 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_7 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_7 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_8 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_8 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_9 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_9 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_10 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_10 = 2;

    private static final Integer DEFAULT_NPC_CARD_ID_11 = 1;
    private static final Integer UPDATED_NPC_CARD_ID_11 = 2;

    private static final Integer DEFAULT_TICK = 1;
    private static final Integer UPDATED_TICK = 2;

    @Autowired
    private MNpcDeckRepository mNpcDeckRepository;

    @Autowired
    private MNpcDeckMapper mNpcDeckMapper;

    @Autowired
    private MNpcDeckService mNpcDeckService;

    @Autowired
    private MNpcDeckQueryService mNpcDeckQueryService;

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

    private MockMvc restMNpcDeckMockMvc;

    private MNpcDeck mNpcDeck;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MNpcDeckResource mNpcDeckResource = new MNpcDeckResource(mNpcDeckService, mNpcDeckQueryService);
        this.restMNpcDeckMockMvc = MockMvcBuilders.standaloneSetup(mNpcDeckResource)
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
    public static MNpcDeck createEntity(EntityManager em) {
        MNpcDeck mNpcDeck = new MNpcDeck()
            .teamName(DEFAULT_TEAM_NAME)
            .uniformBottomFp(DEFAULT_UNIFORM_BOTTOM_FP)
            .uniformUpFp(DEFAULT_UNIFORM_UP_FP)
            .uniformBottomGk(DEFAULT_UNIFORM_BOTTOM_GK)
            .uniformUpGk(DEFAULT_UNIFORM_UP_GK)
            .formationId(DEFAULT_FORMATION_ID)
            .captainCardId(DEFAULT_CAPTAIN_CARD_ID)
            .teamEffect1CardId(DEFAULT_TEAM_EFFECT_1_CARD_ID)
            .teamEffect2CardId(DEFAULT_TEAM_EFFECT_2_CARD_ID)
            .teamEffect3CardId(DEFAULT_TEAM_EFFECT_3_CARD_ID)
            .npcCardId1(DEFAULT_NPC_CARD_ID_1)
            .npcCardId2(DEFAULT_NPC_CARD_ID_2)
            .npcCardId3(DEFAULT_NPC_CARD_ID_3)
            .npcCardId4(DEFAULT_NPC_CARD_ID_4)
            .npcCardId5(DEFAULT_NPC_CARD_ID_5)
            .npcCardId6(DEFAULT_NPC_CARD_ID_6)
            .npcCardId7(DEFAULT_NPC_CARD_ID_7)
            .npcCardId8(DEFAULT_NPC_CARD_ID_8)
            .npcCardId9(DEFAULT_NPC_CARD_ID_9)
            .npcCardId10(DEFAULT_NPC_CARD_ID_10)
            .npcCardId11(DEFAULT_NPC_CARD_ID_11)
            .tick(DEFAULT_TICK);
        // Add required entity
        MFormation mFormation;
        if (TestUtil.findAll(em, MFormation.class).isEmpty()) {
            mFormation = MFormationResourceIT.createEntity(em);
            em.persist(mFormation);
            em.flush();
        } else {
            mFormation = TestUtil.findAll(em, MFormation.class).get(0);
        }
        mNpcDeck.setMformation(mFormation);
        return mNpcDeck;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MNpcDeck createUpdatedEntity(EntityManager em) {
        MNpcDeck mNpcDeck = new MNpcDeck()
            .teamName(UPDATED_TEAM_NAME)
            .uniformBottomFp(UPDATED_UNIFORM_BOTTOM_FP)
            .uniformUpFp(UPDATED_UNIFORM_UP_FP)
            .uniformBottomGk(UPDATED_UNIFORM_BOTTOM_GK)
            .uniformUpGk(UPDATED_UNIFORM_UP_GK)
            .formationId(UPDATED_FORMATION_ID)
            .captainCardId(UPDATED_CAPTAIN_CARD_ID)
            .teamEffect1CardId(UPDATED_TEAM_EFFECT_1_CARD_ID)
            .teamEffect2CardId(UPDATED_TEAM_EFFECT_2_CARD_ID)
            .teamEffect3CardId(UPDATED_TEAM_EFFECT_3_CARD_ID)
            .npcCardId1(UPDATED_NPC_CARD_ID_1)
            .npcCardId2(UPDATED_NPC_CARD_ID_2)
            .npcCardId3(UPDATED_NPC_CARD_ID_3)
            .npcCardId4(UPDATED_NPC_CARD_ID_4)
            .npcCardId5(UPDATED_NPC_CARD_ID_5)
            .npcCardId6(UPDATED_NPC_CARD_ID_6)
            .npcCardId7(UPDATED_NPC_CARD_ID_7)
            .npcCardId8(UPDATED_NPC_CARD_ID_8)
            .npcCardId9(UPDATED_NPC_CARD_ID_9)
            .npcCardId10(UPDATED_NPC_CARD_ID_10)
            .npcCardId11(UPDATED_NPC_CARD_ID_11)
            .tick(UPDATED_TICK);
        // Add required entity
        MFormation mFormation;
        if (TestUtil.findAll(em, MFormation.class).isEmpty()) {
            mFormation = MFormationResourceIT.createUpdatedEntity(em);
            em.persist(mFormation);
            em.flush();
        } else {
            mFormation = TestUtil.findAll(em, MFormation.class).get(0);
        }
        mNpcDeck.setMformation(mFormation);
        return mNpcDeck;
    }

    @BeforeEach
    public void initTest() {
        mNpcDeck = createEntity(em);
    }

    @Test
    @Transactional
    public void createMNpcDeck() throws Exception {
        int databaseSizeBeforeCreate = mNpcDeckRepository.findAll().size();

        // Create the MNpcDeck
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);
        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isCreated());

        // Validate the MNpcDeck in the database
        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeCreate + 1);
        MNpcDeck testMNpcDeck = mNpcDeckList.get(mNpcDeckList.size() - 1);
        assertThat(testMNpcDeck.getTeamName()).isEqualTo(DEFAULT_TEAM_NAME);
        assertThat(testMNpcDeck.getUniformBottomFp()).isEqualTo(DEFAULT_UNIFORM_BOTTOM_FP);
        assertThat(testMNpcDeck.getUniformUpFp()).isEqualTo(DEFAULT_UNIFORM_UP_FP);
        assertThat(testMNpcDeck.getUniformBottomGk()).isEqualTo(DEFAULT_UNIFORM_BOTTOM_GK);
        assertThat(testMNpcDeck.getUniformUpGk()).isEqualTo(DEFAULT_UNIFORM_UP_GK);
        assertThat(testMNpcDeck.getFormationId()).isEqualTo(DEFAULT_FORMATION_ID);
        assertThat(testMNpcDeck.getCaptainCardId()).isEqualTo(DEFAULT_CAPTAIN_CARD_ID);
        assertThat(testMNpcDeck.getTeamEffect1CardId()).isEqualTo(DEFAULT_TEAM_EFFECT_1_CARD_ID);
        assertThat(testMNpcDeck.getTeamEffect2CardId()).isEqualTo(DEFAULT_TEAM_EFFECT_2_CARD_ID);
        assertThat(testMNpcDeck.getTeamEffect3CardId()).isEqualTo(DEFAULT_TEAM_EFFECT_3_CARD_ID);
        assertThat(testMNpcDeck.getNpcCardId1()).isEqualTo(DEFAULT_NPC_CARD_ID_1);
        assertThat(testMNpcDeck.getNpcCardId2()).isEqualTo(DEFAULT_NPC_CARD_ID_2);
        assertThat(testMNpcDeck.getNpcCardId3()).isEqualTo(DEFAULT_NPC_CARD_ID_3);
        assertThat(testMNpcDeck.getNpcCardId4()).isEqualTo(DEFAULT_NPC_CARD_ID_4);
        assertThat(testMNpcDeck.getNpcCardId5()).isEqualTo(DEFAULT_NPC_CARD_ID_5);
        assertThat(testMNpcDeck.getNpcCardId6()).isEqualTo(DEFAULT_NPC_CARD_ID_6);
        assertThat(testMNpcDeck.getNpcCardId7()).isEqualTo(DEFAULT_NPC_CARD_ID_7);
        assertThat(testMNpcDeck.getNpcCardId8()).isEqualTo(DEFAULT_NPC_CARD_ID_8);
        assertThat(testMNpcDeck.getNpcCardId9()).isEqualTo(DEFAULT_NPC_CARD_ID_9);
        assertThat(testMNpcDeck.getNpcCardId10()).isEqualTo(DEFAULT_NPC_CARD_ID_10);
        assertThat(testMNpcDeck.getNpcCardId11()).isEqualTo(DEFAULT_NPC_CARD_ID_11);
        assertThat(testMNpcDeck.getTick()).isEqualTo(DEFAULT_TICK);
    }

    @Test
    @Transactional
    public void createMNpcDeckWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mNpcDeckRepository.findAll().size();

        // Create the MNpcDeck with an existing ID
        mNpcDeck.setId(1L);
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNpcDeck in the database
        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUniformBottomFpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setUniformBottomFp(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformUpFpIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setUniformUpFp(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformBottomGkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setUniformBottomGk(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniformUpGkIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setUniformUpGk(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFormationIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setFormationId(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCaptainCardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setCaptainCardId(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeamEffect1CardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setTeamEffect1CardId(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeamEffect2CardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setTeamEffect2CardId(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeamEffect3CardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setTeamEffect3CardId(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId1IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId1(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId2IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId2(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId3IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId3(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId4IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId4(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId5IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId5(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId6IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId6(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId7IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId7(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId8IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId8(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId9IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId9(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId10IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId10(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNpcCardId11IsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setNpcCardId11(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTickIsRequired() throws Exception {
        int databaseSizeBeforeTest = mNpcDeckRepository.findAll().size();
        // set the field null
        mNpcDeck.setTick(null);

        // Create the MNpcDeck, which fails.
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        restMNpcDeckMockMvc.perform(post("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMNpcDecks() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList
        restMNpcDeckMockMvc.perform(get("/api/m-npc-decks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNpcDeck.getId().intValue())))
            .andExpect(jsonPath("$.[*].teamName").value(hasItem(DEFAULT_TEAM_NAME.toString())))
            .andExpect(jsonPath("$.[*].uniformBottomFp").value(hasItem(DEFAULT_UNIFORM_BOTTOM_FP)))
            .andExpect(jsonPath("$.[*].uniformUpFp").value(hasItem(DEFAULT_UNIFORM_UP_FP)))
            .andExpect(jsonPath("$.[*].uniformBottomGk").value(hasItem(DEFAULT_UNIFORM_BOTTOM_GK)))
            .andExpect(jsonPath("$.[*].uniformUpGk").value(hasItem(DEFAULT_UNIFORM_UP_GK)))
            .andExpect(jsonPath("$.[*].formationId").value(hasItem(DEFAULT_FORMATION_ID)))
            .andExpect(jsonPath("$.[*].captainCardId").value(hasItem(DEFAULT_CAPTAIN_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect1CardId").value(hasItem(DEFAULT_TEAM_EFFECT_1_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect2CardId").value(hasItem(DEFAULT_TEAM_EFFECT_2_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect3CardId").value(hasItem(DEFAULT_TEAM_EFFECT_3_CARD_ID)))
            .andExpect(jsonPath("$.[*].npcCardId1").value(hasItem(DEFAULT_NPC_CARD_ID_1)))
            .andExpect(jsonPath("$.[*].npcCardId2").value(hasItem(DEFAULT_NPC_CARD_ID_2)))
            .andExpect(jsonPath("$.[*].npcCardId3").value(hasItem(DEFAULT_NPC_CARD_ID_3)))
            .andExpect(jsonPath("$.[*].npcCardId4").value(hasItem(DEFAULT_NPC_CARD_ID_4)))
            .andExpect(jsonPath("$.[*].npcCardId5").value(hasItem(DEFAULT_NPC_CARD_ID_5)))
            .andExpect(jsonPath("$.[*].npcCardId6").value(hasItem(DEFAULT_NPC_CARD_ID_6)))
            .andExpect(jsonPath("$.[*].npcCardId7").value(hasItem(DEFAULT_NPC_CARD_ID_7)))
            .andExpect(jsonPath("$.[*].npcCardId8").value(hasItem(DEFAULT_NPC_CARD_ID_8)))
            .andExpect(jsonPath("$.[*].npcCardId9").value(hasItem(DEFAULT_NPC_CARD_ID_9)))
            .andExpect(jsonPath("$.[*].npcCardId10").value(hasItem(DEFAULT_NPC_CARD_ID_10)))
            .andExpect(jsonPath("$.[*].npcCardId11").value(hasItem(DEFAULT_NPC_CARD_ID_11)))
            .andExpect(jsonPath("$.[*].tick").value(hasItem(DEFAULT_TICK)));
    }
    
    @Test
    @Transactional
    public void getMNpcDeck() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get the mNpcDeck
        restMNpcDeckMockMvc.perform(get("/api/m-npc-decks/{id}", mNpcDeck.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mNpcDeck.getId().intValue()))
            .andExpect(jsonPath("$.teamName").value(DEFAULT_TEAM_NAME.toString()))
            .andExpect(jsonPath("$.uniformBottomFp").value(DEFAULT_UNIFORM_BOTTOM_FP))
            .andExpect(jsonPath("$.uniformUpFp").value(DEFAULT_UNIFORM_UP_FP))
            .andExpect(jsonPath("$.uniformBottomGk").value(DEFAULT_UNIFORM_BOTTOM_GK))
            .andExpect(jsonPath("$.uniformUpGk").value(DEFAULT_UNIFORM_UP_GK))
            .andExpect(jsonPath("$.formationId").value(DEFAULT_FORMATION_ID))
            .andExpect(jsonPath("$.captainCardId").value(DEFAULT_CAPTAIN_CARD_ID))
            .andExpect(jsonPath("$.teamEffect1CardId").value(DEFAULT_TEAM_EFFECT_1_CARD_ID))
            .andExpect(jsonPath("$.teamEffect2CardId").value(DEFAULT_TEAM_EFFECT_2_CARD_ID))
            .andExpect(jsonPath("$.teamEffect3CardId").value(DEFAULT_TEAM_EFFECT_3_CARD_ID))
            .andExpect(jsonPath("$.npcCardId1").value(DEFAULT_NPC_CARD_ID_1))
            .andExpect(jsonPath("$.npcCardId2").value(DEFAULT_NPC_CARD_ID_2))
            .andExpect(jsonPath("$.npcCardId3").value(DEFAULT_NPC_CARD_ID_3))
            .andExpect(jsonPath("$.npcCardId4").value(DEFAULT_NPC_CARD_ID_4))
            .andExpect(jsonPath("$.npcCardId5").value(DEFAULT_NPC_CARD_ID_5))
            .andExpect(jsonPath("$.npcCardId6").value(DEFAULT_NPC_CARD_ID_6))
            .andExpect(jsonPath("$.npcCardId7").value(DEFAULT_NPC_CARD_ID_7))
            .andExpect(jsonPath("$.npcCardId8").value(DEFAULT_NPC_CARD_ID_8))
            .andExpect(jsonPath("$.npcCardId9").value(DEFAULT_NPC_CARD_ID_9))
            .andExpect(jsonPath("$.npcCardId10").value(DEFAULT_NPC_CARD_ID_10))
            .andExpect(jsonPath("$.npcCardId11").value(DEFAULT_NPC_CARD_ID_11))
            .andExpect(jsonPath("$.tick").value(DEFAULT_TICK));
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomFpIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomFp equals to DEFAULT_UNIFORM_BOTTOM_FP
        defaultMNpcDeckShouldBeFound("uniformBottomFp.equals=" + DEFAULT_UNIFORM_BOTTOM_FP);

        // Get all the mNpcDeckList where uniformBottomFp equals to UPDATED_UNIFORM_BOTTOM_FP
        defaultMNpcDeckShouldNotBeFound("uniformBottomFp.equals=" + UPDATED_UNIFORM_BOTTOM_FP);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomFpIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomFp in DEFAULT_UNIFORM_BOTTOM_FP or UPDATED_UNIFORM_BOTTOM_FP
        defaultMNpcDeckShouldBeFound("uniformBottomFp.in=" + DEFAULT_UNIFORM_BOTTOM_FP + "," + UPDATED_UNIFORM_BOTTOM_FP);

        // Get all the mNpcDeckList where uniformBottomFp equals to UPDATED_UNIFORM_BOTTOM_FP
        defaultMNpcDeckShouldNotBeFound("uniformBottomFp.in=" + UPDATED_UNIFORM_BOTTOM_FP);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomFpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomFp is not null
        defaultMNpcDeckShouldBeFound("uniformBottomFp.specified=true");

        // Get all the mNpcDeckList where uniformBottomFp is null
        defaultMNpcDeckShouldNotBeFound("uniformBottomFp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomFpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomFp greater than or equals to DEFAULT_UNIFORM_BOTTOM_FP
        defaultMNpcDeckShouldBeFound("uniformBottomFp.greaterOrEqualThan=" + DEFAULT_UNIFORM_BOTTOM_FP);

        // Get all the mNpcDeckList where uniformBottomFp greater than or equals to UPDATED_UNIFORM_BOTTOM_FP
        defaultMNpcDeckShouldNotBeFound("uniformBottomFp.greaterOrEqualThan=" + UPDATED_UNIFORM_BOTTOM_FP);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomFpIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomFp less than or equals to DEFAULT_UNIFORM_BOTTOM_FP
        defaultMNpcDeckShouldNotBeFound("uniformBottomFp.lessThan=" + DEFAULT_UNIFORM_BOTTOM_FP);

        // Get all the mNpcDeckList where uniformBottomFp less than or equals to UPDATED_UNIFORM_BOTTOM_FP
        defaultMNpcDeckShouldBeFound("uniformBottomFp.lessThan=" + UPDATED_UNIFORM_BOTTOM_FP);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpFpIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpFp equals to DEFAULT_UNIFORM_UP_FP
        defaultMNpcDeckShouldBeFound("uniformUpFp.equals=" + DEFAULT_UNIFORM_UP_FP);

        // Get all the mNpcDeckList where uniformUpFp equals to UPDATED_UNIFORM_UP_FP
        defaultMNpcDeckShouldNotBeFound("uniformUpFp.equals=" + UPDATED_UNIFORM_UP_FP);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpFpIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpFp in DEFAULT_UNIFORM_UP_FP or UPDATED_UNIFORM_UP_FP
        defaultMNpcDeckShouldBeFound("uniformUpFp.in=" + DEFAULT_UNIFORM_UP_FP + "," + UPDATED_UNIFORM_UP_FP);

        // Get all the mNpcDeckList where uniformUpFp equals to UPDATED_UNIFORM_UP_FP
        defaultMNpcDeckShouldNotBeFound("uniformUpFp.in=" + UPDATED_UNIFORM_UP_FP);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpFpIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpFp is not null
        defaultMNpcDeckShouldBeFound("uniformUpFp.specified=true");

        // Get all the mNpcDeckList where uniformUpFp is null
        defaultMNpcDeckShouldNotBeFound("uniformUpFp.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpFpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpFp greater than or equals to DEFAULT_UNIFORM_UP_FP
        defaultMNpcDeckShouldBeFound("uniformUpFp.greaterOrEqualThan=" + DEFAULT_UNIFORM_UP_FP);

        // Get all the mNpcDeckList where uniformUpFp greater than or equals to UPDATED_UNIFORM_UP_FP
        defaultMNpcDeckShouldNotBeFound("uniformUpFp.greaterOrEqualThan=" + UPDATED_UNIFORM_UP_FP);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpFpIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpFp less than or equals to DEFAULT_UNIFORM_UP_FP
        defaultMNpcDeckShouldNotBeFound("uniformUpFp.lessThan=" + DEFAULT_UNIFORM_UP_FP);

        // Get all the mNpcDeckList where uniformUpFp less than or equals to UPDATED_UNIFORM_UP_FP
        defaultMNpcDeckShouldBeFound("uniformUpFp.lessThan=" + UPDATED_UNIFORM_UP_FP);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomGkIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomGk equals to DEFAULT_UNIFORM_BOTTOM_GK
        defaultMNpcDeckShouldBeFound("uniformBottomGk.equals=" + DEFAULT_UNIFORM_BOTTOM_GK);

        // Get all the mNpcDeckList where uniformBottomGk equals to UPDATED_UNIFORM_BOTTOM_GK
        defaultMNpcDeckShouldNotBeFound("uniformBottomGk.equals=" + UPDATED_UNIFORM_BOTTOM_GK);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomGkIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomGk in DEFAULT_UNIFORM_BOTTOM_GK or UPDATED_UNIFORM_BOTTOM_GK
        defaultMNpcDeckShouldBeFound("uniformBottomGk.in=" + DEFAULT_UNIFORM_BOTTOM_GK + "," + UPDATED_UNIFORM_BOTTOM_GK);

        // Get all the mNpcDeckList where uniformBottomGk equals to UPDATED_UNIFORM_BOTTOM_GK
        defaultMNpcDeckShouldNotBeFound("uniformBottomGk.in=" + UPDATED_UNIFORM_BOTTOM_GK);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomGkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomGk is not null
        defaultMNpcDeckShouldBeFound("uniformBottomGk.specified=true");

        // Get all the mNpcDeckList where uniformBottomGk is null
        defaultMNpcDeckShouldNotBeFound("uniformBottomGk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomGkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomGk greater than or equals to DEFAULT_UNIFORM_BOTTOM_GK
        defaultMNpcDeckShouldBeFound("uniformBottomGk.greaterOrEqualThan=" + DEFAULT_UNIFORM_BOTTOM_GK);

        // Get all the mNpcDeckList where uniformBottomGk greater than or equals to UPDATED_UNIFORM_BOTTOM_GK
        defaultMNpcDeckShouldNotBeFound("uniformBottomGk.greaterOrEqualThan=" + UPDATED_UNIFORM_BOTTOM_GK);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformBottomGkIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformBottomGk less than or equals to DEFAULT_UNIFORM_BOTTOM_GK
        defaultMNpcDeckShouldNotBeFound("uniformBottomGk.lessThan=" + DEFAULT_UNIFORM_BOTTOM_GK);

        // Get all the mNpcDeckList where uniformBottomGk less than or equals to UPDATED_UNIFORM_BOTTOM_GK
        defaultMNpcDeckShouldBeFound("uniformBottomGk.lessThan=" + UPDATED_UNIFORM_BOTTOM_GK);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpGkIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpGk equals to DEFAULT_UNIFORM_UP_GK
        defaultMNpcDeckShouldBeFound("uniformUpGk.equals=" + DEFAULT_UNIFORM_UP_GK);

        // Get all the mNpcDeckList where uniformUpGk equals to UPDATED_UNIFORM_UP_GK
        defaultMNpcDeckShouldNotBeFound("uniformUpGk.equals=" + UPDATED_UNIFORM_UP_GK);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpGkIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpGk in DEFAULT_UNIFORM_UP_GK or UPDATED_UNIFORM_UP_GK
        defaultMNpcDeckShouldBeFound("uniformUpGk.in=" + DEFAULT_UNIFORM_UP_GK + "," + UPDATED_UNIFORM_UP_GK);

        // Get all the mNpcDeckList where uniformUpGk equals to UPDATED_UNIFORM_UP_GK
        defaultMNpcDeckShouldNotBeFound("uniformUpGk.in=" + UPDATED_UNIFORM_UP_GK);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpGkIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpGk is not null
        defaultMNpcDeckShouldBeFound("uniformUpGk.specified=true");

        // Get all the mNpcDeckList where uniformUpGk is null
        defaultMNpcDeckShouldNotBeFound("uniformUpGk.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpGkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpGk greater than or equals to DEFAULT_UNIFORM_UP_GK
        defaultMNpcDeckShouldBeFound("uniformUpGk.greaterOrEqualThan=" + DEFAULT_UNIFORM_UP_GK);

        // Get all the mNpcDeckList where uniformUpGk greater than or equals to UPDATED_UNIFORM_UP_GK
        defaultMNpcDeckShouldNotBeFound("uniformUpGk.greaterOrEqualThan=" + UPDATED_UNIFORM_UP_GK);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByUniformUpGkIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where uniformUpGk less than or equals to DEFAULT_UNIFORM_UP_GK
        defaultMNpcDeckShouldNotBeFound("uniformUpGk.lessThan=" + DEFAULT_UNIFORM_UP_GK);

        // Get all the mNpcDeckList where uniformUpGk less than or equals to UPDATED_UNIFORM_UP_GK
        defaultMNpcDeckShouldBeFound("uniformUpGk.lessThan=" + UPDATED_UNIFORM_UP_GK);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByFormationIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where formationId equals to DEFAULT_FORMATION_ID
        defaultMNpcDeckShouldBeFound("formationId.equals=" + DEFAULT_FORMATION_ID);

        // Get all the mNpcDeckList where formationId equals to UPDATED_FORMATION_ID
        defaultMNpcDeckShouldNotBeFound("formationId.equals=" + UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByFormationIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where formationId in DEFAULT_FORMATION_ID or UPDATED_FORMATION_ID
        defaultMNpcDeckShouldBeFound("formationId.in=" + DEFAULT_FORMATION_ID + "," + UPDATED_FORMATION_ID);

        // Get all the mNpcDeckList where formationId equals to UPDATED_FORMATION_ID
        defaultMNpcDeckShouldNotBeFound("formationId.in=" + UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByFormationIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where formationId is not null
        defaultMNpcDeckShouldBeFound("formationId.specified=true");

        // Get all the mNpcDeckList where formationId is null
        defaultMNpcDeckShouldNotBeFound("formationId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByFormationIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where formationId greater than or equals to DEFAULT_FORMATION_ID
        defaultMNpcDeckShouldBeFound("formationId.greaterOrEqualThan=" + DEFAULT_FORMATION_ID);

        // Get all the mNpcDeckList where formationId greater than or equals to UPDATED_FORMATION_ID
        defaultMNpcDeckShouldNotBeFound("formationId.greaterOrEqualThan=" + UPDATED_FORMATION_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByFormationIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where formationId less than or equals to DEFAULT_FORMATION_ID
        defaultMNpcDeckShouldNotBeFound("formationId.lessThan=" + DEFAULT_FORMATION_ID);

        // Get all the mNpcDeckList where formationId less than or equals to UPDATED_FORMATION_ID
        defaultMNpcDeckShouldBeFound("formationId.lessThan=" + UPDATED_FORMATION_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByCaptainCardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where captainCardId equals to DEFAULT_CAPTAIN_CARD_ID
        defaultMNpcDeckShouldBeFound("captainCardId.equals=" + DEFAULT_CAPTAIN_CARD_ID);

        // Get all the mNpcDeckList where captainCardId equals to UPDATED_CAPTAIN_CARD_ID
        defaultMNpcDeckShouldNotBeFound("captainCardId.equals=" + UPDATED_CAPTAIN_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByCaptainCardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where captainCardId in DEFAULT_CAPTAIN_CARD_ID or UPDATED_CAPTAIN_CARD_ID
        defaultMNpcDeckShouldBeFound("captainCardId.in=" + DEFAULT_CAPTAIN_CARD_ID + "," + UPDATED_CAPTAIN_CARD_ID);

        // Get all the mNpcDeckList where captainCardId equals to UPDATED_CAPTAIN_CARD_ID
        defaultMNpcDeckShouldNotBeFound("captainCardId.in=" + UPDATED_CAPTAIN_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByCaptainCardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where captainCardId is not null
        defaultMNpcDeckShouldBeFound("captainCardId.specified=true");

        // Get all the mNpcDeckList where captainCardId is null
        defaultMNpcDeckShouldNotBeFound("captainCardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByCaptainCardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where captainCardId greater than or equals to DEFAULT_CAPTAIN_CARD_ID
        defaultMNpcDeckShouldBeFound("captainCardId.greaterOrEqualThan=" + DEFAULT_CAPTAIN_CARD_ID);

        // Get all the mNpcDeckList where captainCardId greater than or equals to UPDATED_CAPTAIN_CARD_ID
        defaultMNpcDeckShouldNotBeFound("captainCardId.greaterOrEqualThan=" + UPDATED_CAPTAIN_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByCaptainCardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where captainCardId less than or equals to DEFAULT_CAPTAIN_CARD_ID
        defaultMNpcDeckShouldNotBeFound("captainCardId.lessThan=" + DEFAULT_CAPTAIN_CARD_ID);

        // Get all the mNpcDeckList where captainCardId less than or equals to UPDATED_CAPTAIN_CARD_ID
        defaultMNpcDeckShouldBeFound("captainCardId.lessThan=" + UPDATED_CAPTAIN_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect1CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect1CardId equals to DEFAULT_TEAM_EFFECT_1_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect1CardId.equals=" + DEFAULT_TEAM_EFFECT_1_CARD_ID);

        // Get all the mNpcDeckList where teamEffect1CardId equals to UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect1CardId.equals=" + UPDATED_TEAM_EFFECT_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect1CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect1CardId in DEFAULT_TEAM_EFFECT_1_CARD_ID or UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect1CardId.in=" + DEFAULT_TEAM_EFFECT_1_CARD_ID + "," + UPDATED_TEAM_EFFECT_1_CARD_ID);

        // Get all the mNpcDeckList where teamEffect1CardId equals to UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect1CardId.in=" + UPDATED_TEAM_EFFECT_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect1CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect1CardId is not null
        defaultMNpcDeckShouldBeFound("teamEffect1CardId.specified=true");

        // Get all the mNpcDeckList where teamEffect1CardId is null
        defaultMNpcDeckShouldNotBeFound("teamEffect1CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect1CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect1CardId greater than or equals to DEFAULT_TEAM_EFFECT_1_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect1CardId.greaterOrEqualThan=" + DEFAULT_TEAM_EFFECT_1_CARD_ID);

        // Get all the mNpcDeckList where teamEffect1CardId greater than or equals to UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect1CardId.greaterOrEqualThan=" + UPDATED_TEAM_EFFECT_1_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect1CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect1CardId less than or equals to DEFAULT_TEAM_EFFECT_1_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect1CardId.lessThan=" + DEFAULT_TEAM_EFFECT_1_CARD_ID);

        // Get all the mNpcDeckList where teamEffect1CardId less than or equals to UPDATED_TEAM_EFFECT_1_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect1CardId.lessThan=" + UPDATED_TEAM_EFFECT_1_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect2CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect2CardId equals to DEFAULT_TEAM_EFFECT_2_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect2CardId.equals=" + DEFAULT_TEAM_EFFECT_2_CARD_ID);

        // Get all the mNpcDeckList where teamEffect2CardId equals to UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect2CardId.equals=" + UPDATED_TEAM_EFFECT_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect2CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect2CardId in DEFAULT_TEAM_EFFECT_2_CARD_ID or UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect2CardId.in=" + DEFAULT_TEAM_EFFECT_2_CARD_ID + "," + UPDATED_TEAM_EFFECT_2_CARD_ID);

        // Get all the mNpcDeckList where teamEffect2CardId equals to UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect2CardId.in=" + UPDATED_TEAM_EFFECT_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect2CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect2CardId is not null
        defaultMNpcDeckShouldBeFound("teamEffect2CardId.specified=true");

        // Get all the mNpcDeckList where teamEffect2CardId is null
        defaultMNpcDeckShouldNotBeFound("teamEffect2CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect2CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect2CardId greater than or equals to DEFAULT_TEAM_EFFECT_2_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect2CardId.greaterOrEqualThan=" + DEFAULT_TEAM_EFFECT_2_CARD_ID);

        // Get all the mNpcDeckList where teamEffect2CardId greater than or equals to UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect2CardId.greaterOrEqualThan=" + UPDATED_TEAM_EFFECT_2_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect2CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect2CardId less than or equals to DEFAULT_TEAM_EFFECT_2_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect2CardId.lessThan=" + DEFAULT_TEAM_EFFECT_2_CARD_ID);

        // Get all the mNpcDeckList where teamEffect2CardId less than or equals to UPDATED_TEAM_EFFECT_2_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect2CardId.lessThan=" + UPDATED_TEAM_EFFECT_2_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect3CardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect3CardId equals to DEFAULT_TEAM_EFFECT_3_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect3CardId.equals=" + DEFAULT_TEAM_EFFECT_3_CARD_ID);

        // Get all the mNpcDeckList where teamEffect3CardId equals to UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect3CardId.equals=" + UPDATED_TEAM_EFFECT_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect3CardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect3CardId in DEFAULT_TEAM_EFFECT_3_CARD_ID or UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect3CardId.in=" + DEFAULT_TEAM_EFFECT_3_CARD_ID + "," + UPDATED_TEAM_EFFECT_3_CARD_ID);

        // Get all the mNpcDeckList where teamEffect3CardId equals to UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect3CardId.in=" + UPDATED_TEAM_EFFECT_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect3CardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect3CardId is not null
        defaultMNpcDeckShouldBeFound("teamEffect3CardId.specified=true");

        // Get all the mNpcDeckList where teamEffect3CardId is null
        defaultMNpcDeckShouldNotBeFound("teamEffect3CardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect3CardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect3CardId greater than or equals to DEFAULT_TEAM_EFFECT_3_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect3CardId.greaterOrEqualThan=" + DEFAULT_TEAM_EFFECT_3_CARD_ID);

        // Get all the mNpcDeckList where teamEffect3CardId greater than or equals to UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect3CardId.greaterOrEqualThan=" + UPDATED_TEAM_EFFECT_3_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTeamEffect3CardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where teamEffect3CardId less than or equals to DEFAULT_TEAM_EFFECT_3_CARD_ID
        defaultMNpcDeckShouldNotBeFound("teamEffect3CardId.lessThan=" + DEFAULT_TEAM_EFFECT_3_CARD_ID);

        // Get all the mNpcDeckList where teamEffect3CardId less than or equals to UPDATED_TEAM_EFFECT_3_CARD_ID
        defaultMNpcDeckShouldBeFound("teamEffect3CardId.lessThan=" + UPDATED_TEAM_EFFECT_3_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId1IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId1 equals to DEFAULT_NPC_CARD_ID_1
        defaultMNpcDeckShouldBeFound("npcCardId1.equals=" + DEFAULT_NPC_CARD_ID_1);

        // Get all the mNpcDeckList where npcCardId1 equals to UPDATED_NPC_CARD_ID_1
        defaultMNpcDeckShouldNotBeFound("npcCardId1.equals=" + UPDATED_NPC_CARD_ID_1);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId1IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId1 in DEFAULT_NPC_CARD_ID_1 or UPDATED_NPC_CARD_ID_1
        defaultMNpcDeckShouldBeFound("npcCardId1.in=" + DEFAULT_NPC_CARD_ID_1 + "," + UPDATED_NPC_CARD_ID_1);

        // Get all the mNpcDeckList where npcCardId1 equals to UPDATED_NPC_CARD_ID_1
        defaultMNpcDeckShouldNotBeFound("npcCardId1.in=" + UPDATED_NPC_CARD_ID_1);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId1IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId1 is not null
        defaultMNpcDeckShouldBeFound("npcCardId1.specified=true");

        // Get all the mNpcDeckList where npcCardId1 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId1.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId1IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId1 greater than or equals to DEFAULT_NPC_CARD_ID_1
        defaultMNpcDeckShouldBeFound("npcCardId1.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_1);

        // Get all the mNpcDeckList where npcCardId1 greater than or equals to UPDATED_NPC_CARD_ID_1
        defaultMNpcDeckShouldNotBeFound("npcCardId1.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_1);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId1IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId1 less than or equals to DEFAULT_NPC_CARD_ID_1
        defaultMNpcDeckShouldNotBeFound("npcCardId1.lessThan=" + DEFAULT_NPC_CARD_ID_1);

        // Get all the mNpcDeckList where npcCardId1 less than or equals to UPDATED_NPC_CARD_ID_1
        defaultMNpcDeckShouldBeFound("npcCardId1.lessThan=" + UPDATED_NPC_CARD_ID_1);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId2IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId2 equals to DEFAULT_NPC_CARD_ID_2
        defaultMNpcDeckShouldBeFound("npcCardId2.equals=" + DEFAULT_NPC_CARD_ID_2);

        // Get all the mNpcDeckList where npcCardId2 equals to UPDATED_NPC_CARD_ID_2
        defaultMNpcDeckShouldNotBeFound("npcCardId2.equals=" + UPDATED_NPC_CARD_ID_2);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId2IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId2 in DEFAULT_NPC_CARD_ID_2 or UPDATED_NPC_CARD_ID_2
        defaultMNpcDeckShouldBeFound("npcCardId2.in=" + DEFAULT_NPC_CARD_ID_2 + "," + UPDATED_NPC_CARD_ID_2);

        // Get all the mNpcDeckList where npcCardId2 equals to UPDATED_NPC_CARD_ID_2
        defaultMNpcDeckShouldNotBeFound("npcCardId2.in=" + UPDATED_NPC_CARD_ID_2);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId2IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId2 is not null
        defaultMNpcDeckShouldBeFound("npcCardId2.specified=true");

        // Get all the mNpcDeckList where npcCardId2 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId2 greater than or equals to DEFAULT_NPC_CARD_ID_2
        defaultMNpcDeckShouldBeFound("npcCardId2.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_2);

        // Get all the mNpcDeckList where npcCardId2 greater than or equals to UPDATED_NPC_CARD_ID_2
        defaultMNpcDeckShouldNotBeFound("npcCardId2.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_2);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId2IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId2 less than or equals to DEFAULT_NPC_CARD_ID_2
        defaultMNpcDeckShouldNotBeFound("npcCardId2.lessThan=" + DEFAULT_NPC_CARD_ID_2);

        // Get all the mNpcDeckList where npcCardId2 less than or equals to UPDATED_NPC_CARD_ID_2
        defaultMNpcDeckShouldBeFound("npcCardId2.lessThan=" + UPDATED_NPC_CARD_ID_2);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId3IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId3 equals to DEFAULT_NPC_CARD_ID_3
        defaultMNpcDeckShouldBeFound("npcCardId3.equals=" + DEFAULT_NPC_CARD_ID_3);

        // Get all the mNpcDeckList where npcCardId3 equals to UPDATED_NPC_CARD_ID_3
        defaultMNpcDeckShouldNotBeFound("npcCardId3.equals=" + UPDATED_NPC_CARD_ID_3);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId3IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId3 in DEFAULT_NPC_CARD_ID_3 or UPDATED_NPC_CARD_ID_3
        defaultMNpcDeckShouldBeFound("npcCardId3.in=" + DEFAULT_NPC_CARD_ID_3 + "," + UPDATED_NPC_CARD_ID_3);

        // Get all the mNpcDeckList where npcCardId3 equals to UPDATED_NPC_CARD_ID_3
        defaultMNpcDeckShouldNotBeFound("npcCardId3.in=" + UPDATED_NPC_CARD_ID_3);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId3IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId3 is not null
        defaultMNpcDeckShouldBeFound("npcCardId3.specified=true");

        // Get all the mNpcDeckList where npcCardId3 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId3.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId3 greater than or equals to DEFAULT_NPC_CARD_ID_3
        defaultMNpcDeckShouldBeFound("npcCardId3.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_3);

        // Get all the mNpcDeckList where npcCardId3 greater than or equals to UPDATED_NPC_CARD_ID_3
        defaultMNpcDeckShouldNotBeFound("npcCardId3.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_3);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId3IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId3 less than or equals to DEFAULT_NPC_CARD_ID_3
        defaultMNpcDeckShouldNotBeFound("npcCardId3.lessThan=" + DEFAULT_NPC_CARD_ID_3);

        // Get all the mNpcDeckList where npcCardId3 less than or equals to UPDATED_NPC_CARD_ID_3
        defaultMNpcDeckShouldBeFound("npcCardId3.lessThan=" + UPDATED_NPC_CARD_ID_3);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId4IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId4 equals to DEFAULT_NPC_CARD_ID_4
        defaultMNpcDeckShouldBeFound("npcCardId4.equals=" + DEFAULT_NPC_CARD_ID_4);

        // Get all the mNpcDeckList where npcCardId4 equals to UPDATED_NPC_CARD_ID_4
        defaultMNpcDeckShouldNotBeFound("npcCardId4.equals=" + UPDATED_NPC_CARD_ID_4);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId4IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId4 in DEFAULT_NPC_CARD_ID_4 or UPDATED_NPC_CARD_ID_4
        defaultMNpcDeckShouldBeFound("npcCardId4.in=" + DEFAULT_NPC_CARD_ID_4 + "," + UPDATED_NPC_CARD_ID_4);

        // Get all the mNpcDeckList where npcCardId4 equals to UPDATED_NPC_CARD_ID_4
        defaultMNpcDeckShouldNotBeFound("npcCardId4.in=" + UPDATED_NPC_CARD_ID_4);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId4IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId4 is not null
        defaultMNpcDeckShouldBeFound("npcCardId4.specified=true");

        // Get all the mNpcDeckList where npcCardId4 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId4.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId4 greater than or equals to DEFAULT_NPC_CARD_ID_4
        defaultMNpcDeckShouldBeFound("npcCardId4.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_4);

        // Get all the mNpcDeckList where npcCardId4 greater than or equals to UPDATED_NPC_CARD_ID_4
        defaultMNpcDeckShouldNotBeFound("npcCardId4.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_4);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId4IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId4 less than or equals to DEFAULT_NPC_CARD_ID_4
        defaultMNpcDeckShouldNotBeFound("npcCardId4.lessThan=" + DEFAULT_NPC_CARD_ID_4);

        // Get all the mNpcDeckList where npcCardId4 less than or equals to UPDATED_NPC_CARD_ID_4
        defaultMNpcDeckShouldBeFound("npcCardId4.lessThan=" + UPDATED_NPC_CARD_ID_4);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId5IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId5 equals to DEFAULT_NPC_CARD_ID_5
        defaultMNpcDeckShouldBeFound("npcCardId5.equals=" + DEFAULT_NPC_CARD_ID_5);

        // Get all the mNpcDeckList where npcCardId5 equals to UPDATED_NPC_CARD_ID_5
        defaultMNpcDeckShouldNotBeFound("npcCardId5.equals=" + UPDATED_NPC_CARD_ID_5);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId5IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId5 in DEFAULT_NPC_CARD_ID_5 or UPDATED_NPC_CARD_ID_5
        defaultMNpcDeckShouldBeFound("npcCardId5.in=" + DEFAULT_NPC_CARD_ID_5 + "," + UPDATED_NPC_CARD_ID_5);

        // Get all the mNpcDeckList where npcCardId5 equals to UPDATED_NPC_CARD_ID_5
        defaultMNpcDeckShouldNotBeFound("npcCardId5.in=" + UPDATED_NPC_CARD_ID_5);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId5IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId5 is not null
        defaultMNpcDeckShouldBeFound("npcCardId5.specified=true");

        // Get all the mNpcDeckList where npcCardId5 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId5.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId5 greater than or equals to DEFAULT_NPC_CARD_ID_5
        defaultMNpcDeckShouldBeFound("npcCardId5.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_5);

        // Get all the mNpcDeckList where npcCardId5 greater than or equals to UPDATED_NPC_CARD_ID_5
        defaultMNpcDeckShouldNotBeFound("npcCardId5.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_5);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId5IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId5 less than or equals to DEFAULT_NPC_CARD_ID_5
        defaultMNpcDeckShouldNotBeFound("npcCardId5.lessThan=" + DEFAULT_NPC_CARD_ID_5);

        // Get all the mNpcDeckList where npcCardId5 less than or equals to UPDATED_NPC_CARD_ID_5
        defaultMNpcDeckShouldBeFound("npcCardId5.lessThan=" + UPDATED_NPC_CARD_ID_5);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId6IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId6 equals to DEFAULT_NPC_CARD_ID_6
        defaultMNpcDeckShouldBeFound("npcCardId6.equals=" + DEFAULT_NPC_CARD_ID_6);

        // Get all the mNpcDeckList where npcCardId6 equals to UPDATED_NPC_CARD_ID_6
        defaultMNpcDeckShouldNotBeFound("npcCardId6.equals=" + UPDATED_NPC_CARD_ID_6);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId6IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId6 in DEFAULT_NPC_CARD_ID_6 or UPDATED_NPC_CARD_ID_6
        defaultMNpcDeckShouldBeFound("npcCardId6.in=" + DEFAULT_NPC_CARD_ID_6 + "," + UPDATED_NPC_CARD_ID_6);

        // Get all the mNpcDeckList where npcCardId6 equals to UPDATED_NPC_CARD_ID_6
        defaultMNpcDeckShouldNotBeFound("npcCardId6.in=" + UPDATED_NPC_CARD_ID_6);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId6IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId6 is not null
        defaultMNpcDeckShouldBeFound("npcCardId6.specified=true");

        // Get all the mNpcDeckList where npcCardId6 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId6.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId6 greater than or equals to DEFAULT_NPC_CARD_ID_6
        defaultMNpcDeckShouldBeFound("npcCardId6.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_6);

        // Get all the mNpcDeckList where npcCardId6 greater than or equals to UPDATED_NPC_CARD_ID_6
        defaultMNpcDeckShouldNotBeFound("npcCardId6.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_6);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId6IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId6 less than or equals to DEFAULT_NPC_CARD_ID_6
        defaultMNpcDeckShouldNotBeFound("npcCardId6.lessThan=" + DEFAULT_NPC_CARD_ID_6);

        // Get all the mNpcDeckList where npcCardId6 less than or equals to UPDATED_NPC_CARD_ID_6
        defaultMNpcDeckShouldBeFound("npcCardId6.lessThan=" + UPDATED_NPC_CARD_ID_6);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId7IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId7 equals to DEFAULT_NPC_CARD_ID_7
        defaultMNpcDeckShouldBeFound("npcCardId7.equals=" + DEFAULT_NPC_CARD_ID_7);

        // Get all the mNpcDeckList where npcCardId7 equals to UPDATED_NPC_CARD_ID_7
        defaultMNpcDeckShouldNotBeFound("npcCardId7.equals=" + UPDATED_NPC_CARD_ID_7);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId7IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId7 in DEFAULT_NPC_CARD_ID_7 or UPDATED_NPC_CARD_ID_7
        defaultMNpcDeckShouldBeFound("npcCardId7.in=" + DEFAULT_NPC_CARD_ID_7 + "," + UPDATED_NPC_CARD_ID_7);

        // Get all the mNpcDeckList where npcCardId7 equals to UPDATED_NPC_CARD_ID_7
        defaultMNpcDeckShouldNotBeFound("npcCardId7.in=" + UPDATED_NPC_CARD_ID_7);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId7IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId7 is not null
        defaultMNpcDeckShouldBeFound("npcCardId7.specified=true");

        // Get all the mNpcDeckList where npcCardId7 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId7.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId7IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId7 greater than or equals to DEFAULT_NPC_CARD_ID_7
        defaultMNpcDeckShouldBeFound("npcCardId7.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_7);

        // Get all the mNpcDeckList where npcCardId7 greater than or equals to UPDATED_NPC_CARD_ID_7
        defaultMNpcDeckShouldNotBeFound("npcCardId7.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_7);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId7IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId7 less than or equals to DEFAULT_NPC_CARD_ID_7
        defaultMNpcDeckShouldNotBeFound("npcCardId7.lessThan=" + DEFAULT_NPC_CARD_ID_7);

        // Get all the mNpcDeckList where npcCardId7 less than or equals to UPDATED_NPC_CARD_ID_7
        defaultMNpcDeckShouldBeFound("npcCardId7.lessThan=" + UPDATED_NPC_CARD_ID_7);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId8IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId8 equals to DEFAULT_NPC_CARD_ID_8
        defaultMNpcDeckShouldBeFound("npcCardId8.equals=" + DEFAULT_NPC_CARD_ID_8);

        // Get all the mNpcDeckList where npcCardId8 equals to UPDATED_NPC_CARD_ID_8
        defaultMNpcDeckShouldNotBeFound("npcCardId8.equals=" + UPDATED_NPC_CARD_ID_8);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId8IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId8 in DEFAULT_NPC_CARD_ID_8 or UPDATED_NPC_CARD_ID_8
        defaultMNpcDeckShouldBeFound("npcCardId8.in=" + DEFAULT_NPC_CARD_ID_8 + "," + UPDATED_NPC_CARD_ID_8);

        // Get all the mNpcDeckList where npcCardId8 equals to UPDATED_NPC_CARD_ID_8
        defaultMNpcDeckShouldNotBeFound("npcCardId8.in=" + UPDATED_NPC_CARD_ID_8);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId8IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId8 is not null
        defaultMNpcDeckShouldBeFound("npcCardId8.specified=true");

        // Get all the mNpcDeckList where npcCardId8 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId8.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId8IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId8 greater than or equals to DEFAULT_NPC_CARD_ID_8
        defaultMNpcDeckShouldBeFound("npcCardId8.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_8);

        // Get all the mNpcDeckList where npcCardId8 greater than or equals to UPDATED_NPC_CARD_ID_8
        defaultMNpcDeckShouldNotBeFound("npcCardId8.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_8);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId8IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId8 less than or equals to DEFAULT_NPC_CARD_ID_8
        defaultMNpcDeckShouldNotBeFound("npcCardId8.lessThan=" + DEFAULT_NPC_CARD_ID_8);

        // Get all the mNpcDeckList where npcCardId8 less than or equals to UPDATED_NPC_CARD_ID_8
        defaultMNpcDeckShouldBeFound("npcCardId8.lessThan=" + UPDATED_NPC_CARD_ID_8);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId9IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId9 equals to DEFAULT_NPC_CARD_ID_9
        defaultMNpcDeckShouldBeFound("npcCardId9.equals=" + DEFAULT_NPC_CARD_ID_9);

        // Get all the mNpcDeckList where npcCardId9 equals to UPDATED_NPC_CARD_ID_9
        defaultMNpcDeckShouldNotBeFound("npcCardId9.equals=" + UPDATED_NPC_CARD_ID_9);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId9IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId9 in DEFAULT_NPC_CARD_ID_9 or UPDATED_NPC_CARD_ID_9
        defaultMNpcDeckShouldBeFound("npcCardId9.in=" + DEFAULT_NPC_CARD_ID_9 + "," + UPDATED_NPC_CARD_ID_9);

        // Get all the mNpcDeckList where npcCardId9 equals to UPDATED_NPC_CARD_ID_9
        defaultMNpcDeckShouldNotBeFound("npcCardId9.in=" + UPDATED_NPC_CARD_ID_9);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId9IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId9 is not null
        defaultMNpcDeckShouldBeFound("npcCardId9.specified=true");

        // Get all the mNpcDeckList where npcCardId9 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId9.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId9IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId9 greater than or equals to DEFAULT_NPC_CARD_ID_9
        defaultMNpcDeckShouldBeFound("npcCardId9.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_9);

        // Get all the mNpcDeckList where npcCardId9 greater than or equals to UPDATED_NPC_CARD_ID_9
        defaultMNpcDeckShouldNotBeFound("npcCardId9.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_9);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId9IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId9 less than or equals to DEFAULT_NPC_CARD_ID_9
        defaultMNpcDeckShouldNotBeFound("npcCardId9.lessThan=" + DEFAULT_NPC_CARD_ID_9);

        // Get all the mNpcDeckList where npcCardId9 less than or equals to UPDATED_NPC_CARD_ID_9
        defaultMNpcDeckShouldBeFound("npcCardId9.lessThan=" + UPDATED_NPC_CARD_ID_9);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId10IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId10 equals to DEFAULT_NPC_CARD_ID_10
        defaultMNpcDeckShouldBeFound("npcCardId10.equals=" + DEFAULT_NPC_CARD_ID_10);

        // Get all the mNpcDeckList where npcCardId10 equals to UPDATED_NPC_CARD_ID_10
        defaultMNpcDeckShouldNotBeFound("npcCardId10.equals=" + UPDATED_NPC_CARD_ID_10);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId10IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId10 in DEFAULT_NPC_CARD_ID_10 or UPDATED_NPC_CARD_ID_10
        defaultMNpcDeckShouldBeFound("npcCardId10.in=" + DEFAULT_NPC_CARD_ID_10 + "," + UPDATED_NPC_CARD_ID_10);

        // Get all the mNpcDeckList where npcCardId10 equals to UPDATED_NPC_CARD_ID_10
        defaultMNpcDeckShouldNotBeFound("npcCardId10.in=" + UPDATED_NPC_CARD_ID_10);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId10IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId10 is not null
        defaultMNpcDeckShouldBeFound("npcCardId10.specified=true");

        // Get all the mNpcDeckList where npcCardId10 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId10.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId10IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId10 greater than or equals to DEFAULT_NPC_CARD_ID_10
        defaultMNpcDeckShouldBeFound("npcCardId10.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_10);

        // Get all the mNpcDeckList where npcCardId10 greater than or equals to UPDATED_NPC_CARD_ID_10
        defaultMNpcDeckShouldNotBeFound("npcCardId10.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_10);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId10IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId10 less than or equals to DEFAULT_NPC_CARD_ID_10
        defaultMNpcDeckShouldNotBeFound("npcCardId10.lessThan=" + DEFAULT_NPC_CARD_ID_10);

        // Get all the mNpcDeckList where npcCardId10 less than or equals to UPDATED_NPC_CARD_ID_10
        defaultMNpcDeckShouldBeFound("npcCardId10.lessThan=" + UPDATED_NPC_CARD_ID_10);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId11IsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId11 equals to DEFAULT_NPC_CARD_ID_11
        defaultMNpcDeckShouldBeFound("npcCardId11.equals=" + DEFAULT_NPC_CARD_ID_11);

        // Get all the mNpcDeckList where npcCardId11 equals to UPDATED_NPC_CARD_ID_11
        defaultMNpcDeckShouldNotBeFound("npcCardId11.equals=" + UPDATED_NPC_CARD_ID_11);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId11IsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId11 in DEFAULT_NPC_CARD_ID_11 or UPDATED_NPC_CARD_ID_11
        defaultMNpcDeckShouldBeFound("npcCardId11.in=" + DEFAULT_NPC_CARD_ID_11 + "," + UPDATED_NPC_CARD_ID_11);

        // Get all the mNpcDeckList where npcCardId11 equals to UPDATED_NPC_CARD_ID_11
        defaultMNpcDeckShouldNotBeFound("npcCardId11.in=" + UPDATED_NPC_CARD_ID_11);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId11IsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId11 is not null
        defaultMNpcDeckShouldBeFound("npcCardId11.specified=true");

        // Get all the mNpcDeckList where npcCardId11 is null
        defaultMNpcDeckShouldNotBeFound("npcCardId11.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId11IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId11 greater than or equals to DEFAULT_NPC_CARD_ID_11
        defaultMNpcDeckShouldBeFound("npcCardId11.greaterOrEqualThan=" + DEFAULT_NPC_CARD_ID_11);

        // Get all the mNpcDeckList where npcCardId11 greater than or equals to UPDATED_NPC_CARD_ID_11
        defaultMNpcDeckShouldNotBeFound("npcCardId11.greaterOrEqualThan=" + UPDATED_NPC_CARD_ID_11);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByNpcCardId11IsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where npcCardId11 less than or equals to DEFAULT_NPC_CARD_ID_11
        defaultMNpcDeckShouldNotBeFound("npcCardId11.lessThan=" + DEFAULT_NPC_CARD_ID_11);

        // Get all the mNpcDeckList where npcCardId11 less than or equals to UPDATED_NPC_CARD_ID_11
        defaultMNpcDeckShouldBeFound("npcCardId11.lessThan=" + UPDATED_NPC_CARD_ID_11);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByTickIsEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where tick equals to DEFAULT_TICK
        defaultMNpcDeckShouldBeFound("tick.equals=" + DEFAULT_TICK);

        // Get all the mNpcDeckList where tick equals to UPDATED_TICK
        defaultMNpcDeckShouldNotBeFound("tick.equals=" + UPDATED_TICK);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTickIsInShouldWork() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where tick in DEFAULT_TICK or UPDATED_TICK
        defaultMNpcDeckShouldBeFound("tick.in=" + DEFAULT_TICK + "," + UPDATED_TICK);

        // Get all the mNpcDeckList where tick equals to UPDATED_TICK
        defaultMNpcDeckShouldNotBeFound("tick.in=" + UPDATED_TICK);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTickIsNullOrNotNull() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where tick is not null
        defaultMNpcDeckShouldBeFound("tick.specified=true");

        // Get all the mNpcDeckList where tick is null
        defaultMNpcDeckShouldNotBeFound("tick.specified=false");
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTickIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where tick greater than or equals to DEFAULT_TICK
        defaultMNpcDeckShouldBeFound("tick.greaterOrEqualThan=" + DEFAULT_TICK);

        // Get all the mNpcDeckList where tick greater than or equals to UPDATED_TICK
        defaultMNpcDeckShouldNotBeFound("tick.greaterOrEqualThan=" + UPDATED_TICK);
    }

    @Test
    @Transactional
    public void getAllMNpcDecksByTickIsLessThanSomething() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        // Get all the mNpcDeckList where tick less than or equals to DEFAULT_TICK
        defaultMNpcDeckShouldNotBeFound("tick.lessThan=" + DEFAULT_TICK);

        // Get all the mNpcDeckList where tick less than or equals to UPDATED_TICK
        defaultMNpcDeckShouldBeFound("tick.lessThan=" + UPDATED_TICK);
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByMformationIsEqualToSomething() throws Exception {
        // Get already existing entity
        MFormation mformation = mNpcDeck.getMformation();
        mNpcDeckRepository.saveAndFlush(mNpcDeck);
        Long mformationId = mformation.getId();

        // Get all the mNpcDeckList where mformation equals to mformationId
        defaultMNpcDeckShouldBeFound("mformationId.equals=" + mformationId);

        // Get all the mNpcDeckList where mformation equals to mformationId + 1
        defaultMNpcDeckShouldNotBeFound("mformationId.equals=" + (mformationId + 1));
    }


    @Test
    @Transactional
    public void getAllMNpcDecksByMDummyOpponentIsEqualToSomething() throws Exception {
        // Initialize the database
        MDummyOpponent mDummyOpponent = MDummyOpponentResourceIT.createEntity(em);
        em.persist(mDummyOpponent);
        em.flush();
        mNpcDeck.addMDummyOpponent(mDummyOpponent);
        mNpcDeckRepository.saveAndFlush(mNpcDeck);
        Long mDummyOpponentId = mDummyOpponent.getId();

        // Get all the mNpcDeckList where mDummyOpponent equals to mDummyOpponentId
        defaultMNpcDeckShouldBeFound("mDummyOpponentId.equals=" + mDummyOpponentId);

        // Get all the mNpcDeckList where mDummyOpponent equals to mDummyOpponentId + 1
        defaultMNpcDeckShouldNotBeFound("mDummyOpponentId.equals=" + (mDummyOpponentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMNpcDeckShouldBeFound(String filter) throws Exception {
        restMNpcDeckMockMvc.perform(get("/api/m-npc-decks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mNpcDeck.getId().intValue())))
            .andExpect(jsonPath("$.[*].teamName").value(hasItem(DEFAULT_TEAM_NAME.toString())))
            .andExpect(jsonPath("$.[*].uniformBottomFp").value(hasItem(DEFAULT_UNIFORM_BOTTOM_FP)))
            .andExpect(jsonPath("$.[*].uniformUpFp").value(hasItem(DEFAULT_UNIFORM_UP_FP)))
            .andExpect(jsonPath("$.[*].uniformBottomGk").value(hasItem(DEFAULT_UNIFORM_BOTTOM_GK)))
            .andExpect(jsonPath("$.[*].uniformUpGk").value(hasItem(DEFAULT_UNIFORM_UP_GK)))
            .andExpect(jsonPath("$.[*].formationId").value(hasItem(DEFAULT_FORMATION_ID)))
            .andExpect(jsonPath("$.[*].captainCardId").value(hasItem(DEFAULT_CAPTAIN_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect1CardId").value(hasItem(DEFAULT_TEAM_EFFECT_1_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect2CardId").value(hasItem(DEFAULT_TEAM_EFFECT_2_CARD_ID)))
            .andExpect(jsonPath("$.[*].teamEffect3CardId").value(hasItem(DEFAULT_TEAM_EFFECT_3_CARD_ID)))
            .andExpect(jsonPath("$.[*].npcCardId1").value(hasItem(DEFAULT_NPC_CARD_ID_1)))
            .andExpect(jsonPath("$.[*].npcCardId2").value(hasItem(DEFAULT_NPC_CARD_ID_2)))
            .andExpect(jsonPath("$.[*].npcCardId3").value(hasItem(DEFAULT_NPC_CARD_ID_3)))
            .andExpect(jsonPath("$.[*].npcCardId4").value(hasItem(DEFAULT_NPC_CARD_ID_4)))
            .andExpect(jsonPath("$.[*].npcCardId5").value(hasItem(DEFAULT_NPC_CARD_ID_5)))
            .andExpect(jsonPath("$.[*].npcCardId6").value(hasItem(DEFAULT_NPC_CARD_ID_6)))
            .andExpect(jsonPath("$.[*].npcCardId7").value(hasItem(DEFAULT_NPC_CARD_ID_7)))
            .andExpect(jsonPath("$.[*].npcCardId8").value(hasItem(DEFAULT_NPC_CARD_ID_8)))
            .andExpect(jsonPath("$.[*].npcCardId9").value(hasItem(DEFAULT_NPC_CARD_ID_9)))
            .andExpect(jsonPath("$.[*].npcCardId10").value(hasItem(DEFAULT_NPC_CARD_ID_10)))
            .andExpect(jsonPath("$.[*].npcCardId11").value(hasItem(DEFAULT_NPC_CARD_ID_11)))
            .andExpect(jsonPath("$.[*].tick").value(hasItem(DEFAULT_TICK)));

        // Check, that the count call also returns 1
        restMNpcDeckMockMvc.perform(get("/api/m-npc-decks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMNpcDeckShouldNotBeFound(String filter) throws Exception {
        restMNpcDeckMockMvc.perform(get("/api/m-npc-decks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMNpcDeckMockMvc.perform(get("/api/m-npc-decks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMNpcDeck() throws Exception {
        // Get the mNpcDeck
        restMNpcDeckMockMvc.perform(get("/api/m-npc-decks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMNpcDeck() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        int databaseSizeBeforeUpdate = mNpcDeckRepository.findAll().size();

        // Update the mNpcDeck
        MNpcDeck updatedMNpcDeck = mNpcDeckRepository.findById(mNpcDeck.getId()).get();
        // Disconnect from session so that the updates on updatedMNpcDeck are not directly saved in db
        em.detach(updatedMNpcDeck);
        updatedMNpcDeck
            .teamName(UPDATED_TEAM_NAME)
            .uniformBottomFp(UPDATED_UNIFORM_BOTTOM_FP)
            .uniformUpFp(UPDATED_UNIFORM_UP_FP)
            .uniformBottomGk(UPDATED_UNIFORM_BOTTOM_GK)
            .uniformUpGk(UPDATED_UNIFORM_UP_GK)
            .formationId(UPDATED_FORMATION_ID)
            .captainCardId(UPDATED_CAPTAIN_CARD_ID)
            .teamEffect1CardId(UPDATED_TEAM_EFFECT_1_CARD_ID)
            .teamEffect2CardId(UPDATED_TEAM_EFFECT_2_CARD_ID)
            .teamEffect3CardId(UPDATED_TEAM_EFFECT_3_CARD_ID)
            .npcCardId1(UPDATED_NPC_CARD_ID_1)
            .npcCardId2(UPDATED_NPC_CARD_ID_2)
            .npcCardId3(UPDATED_NPC_CARD_ID_3)
            .npcCardId4(UPDATED_NPC_CARD_ID_4)
            .npcCardId5(UPDATED_NPC_CARD_ID_5)
            .npcCardId6(UPDATED_NPC_CARD_ID_6)
            .npcCardId7(UPDATED_NPC_CARD_ID_7)
            .npcCardId8(UPDATED_NPC_CARD_ID_8)
            .npcCardId9(UPDATED_NPC_CARD_ID_9)
            .npcCardId10(UPDATED_NPC_CARD_ID_10)
            .npcCardId11(UPDATED_NPC_CARD_ID_11)
            .tick(UPDATED_TICK);
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(updatedMNpcDeck);

        restMNpcDeckMockMvc.perform(put("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isOk());

        // Validate the MNpcDeck in the database
        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeUpdate);
        MNpcDeck testMNpcDeck = mNpcDeckList.get(mNpcDeckList.size() - 1);
        assertThat(testMNpcDeck.getTeamName()).isEqualTo(UPDATED_TEAM_NAME);
        assertThat(testMNpcDeck.getUniformBottomFp()).isEqualTo(UPDATED_UNIFORM_BOTTOM_FP);
        assertThat(testMNpcDeck.getUniformUpFp()).isEqualTo(UPDATED_UNIFORM_UP_FP);
        assertThat(testMNpcDeck.getUniformBottomGk()).isEqualTo(UPDATED_UNIFORM_BOTTOM_GK);
        assertThat(testMNpcDeck.getUniformUpGk()).isEqualTo(UPDATED_UNIFORM_UP_GK);
        assertThat(testMNpcDeck.getFormationId()).isEqualTo(UPDATED_FORMATION_ID);
        assertThat(testMNpcDeck.getCaptainCardId()).isEqualTo(UPDATED_CAPTAIN_CARD_ID);
        assertThat(testMNpcDeck.getTeamEffect1CardId()).isEqualTo(UPDATED_TEAM_EFFECT_1_CARD_ID);
        assertThat(testMNpcDeck.getTeamEffect2CardId()).isEqualTo(UPDATED_TEAM_EFFECT_2_CARD_ID);
        assertThat(testMNpcDeck.getTeamEffect3CardId()).isEqualTo(UPDATED_TEAM_EFFECT_3_CARD_ID);
        assertThat(testMNpcDeck.getNpcCardId1()).isEqualTo(UPDATED_NPC_CARD_ID_1);
        assertThat(testMNpcDeck.getNpcCardId2()).isEqualTo(UPDATED_NPC_CARD_ID_2);
        assertThat(testMNpcDeck.getNpcCardId3()).isEqualTo(UPDATED_NPC_CARD_ID_3);
        assertThat(testMNpcDeck.getNpcCardId4()).isEqualTo(UPDATED_NPC_CARD_ID_4);
        assertThat(testMNpcDeck.getNpcCardId5()).isEqualTo(UPDATED_NPC_CARD_ID_5);
        assertThat(testMNpcDeck.getNpcCardId6()).isEqualTo(UPDATED_NPC_CARD_ID_6);
        assertThat(testMNpcDeck.getNpcCardId7()).isEqualTo(UPDATED_NPC_CARD_ID_7);
        assertThat(testMNpcDeck.getNpcCardId8()).isEqualTo(UPDATED_NPC_CARD_ID_8);
        assertThat(testMNpcDeck.getNpcCardId9()).isEqualTo(UPDATED_NPC_CARD_ID_9);
        assertThat(testMNpcDeck.getNpcCardId10()).isEqualTo(UPDATED_NPC_CARD_ID_10);
        assertThat(testMNpcDeck.getNpcCardId11()).isEqualTo(UPDATED_NPC_CARD_ID_11);
        assertThat(testMNpcDeck.getTick()).isEqualTo(UPDATED_TICK);
    }

    @Test
    @Transactional
    public void updateNonExistingMNpcDeck() throws Exception {
        int databaseSizeBeforeUpdate = mNpcDeckRepository.findAll().size();

        // Create the MNpcDeck
        MNpcDeckDTO mNpcDeckDTO = mNpcDeckMapper.toDto(mNpcDeck);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMNpcDeckMockMvc.perform(put("/api/m-npc-decks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mNpcDeckDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MNpcDeck in the database
        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMNpcDeck() throws Exception {
        // Initialize the database
        mNpcDeckRepository.saveAndFlush(mNpcDeck);

        int databaseSizeBeforeDelete = mNpcDeckRepository.findAll().size();

        // Delete the mNpcDeck
        restMNpcDeckMockMvc.perform(delete("/api/m-npc-decks/{id}", mNpcDeck.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MNpcDeck> mNpcDeckList = mNpcDeckRepository.findAll();
        assertThat(mNpcDeckList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNpcDeck.class);
        MNpcDeck mNpcDeck1 = new MNpcDeck();
        mNpcDeck1.setId(1L);
        MNpcDeck mNpcDeck2 = new MNpcDeck();
        mNpcDeck2.setId(mNpcDeck1.getId());
        assertThat(mNpcDeck1).isEqualTo(mNpcDeck2);
        mNpcDeck2.setId(2L);
        assertThat(mNpcDeck1).isNotEqualTo(mNpcDeck2);
        mNpcDeck1.setId(null);
        assertThat(mNpcDeck1).isNotEqualTo(mNpcDeck2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MNpcDeckDTO.class);
        MNpcDeckDTO mNpcDeckDTO1 = new MNpcDeckDTO();
        mNpcDeckDTO1.setId(1L);
        MNpcDeckDTO mNpcDeckDTO2 = new MNpcDeckDTO();
        assertThat(mNpcDeckDTO1).isNotEqualTo(mNpcDeckDTO2);
        mNpcDeckDTO2.setId(mNpcDeckDTO1.getId());
        assertThat(mNpcDeckDTO1).isEqualTo(mNpcDeckDTO2);
        mNpcDeckDTO2.setId(2L);
        assertThat(mNpcDeckDTO1).isNotEqualTo(mNpcDeckDTO2);
        mNpcDeckDTO1.setId(null);
        assertThat(mNpcDeckDTO1).isNotEqualTo(mNpcDeckDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mNpcDeckMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mNpcDeckMapper.fromId(null)).isNull();
    }
}
