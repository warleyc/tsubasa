package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMatchResultCutin;
import io.shm.tsubasa.domain.MCharacter;
import io.shm.tsubasa.repository.MMatchResultCutinRepository;
import io.shm.tsubasa.service.MMatchResultCutinService;
import io.shm.tsubasa.service.dto.MMatchResultCutinDTO;
import io.shm.tsubasa.service.mapper.MMatchResultCutinMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMatchResultCutinCriteria;
import io.shm.tsubasa.service.MMatchResultCutinQueryService;

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
 * Integration tests for the {@Link MMatchResultCutinResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMatchResultCutinResourceIT {

    private static final Integer DEFAULT_CHARACTER_ID = 1;
    private static final Integer UPDATED_CHARACTER_ID = 2;

    private static final Integer DEFAULT_TEAM_ID = 1;
    private static final Integer UPDATED_TEAM_ID = 2;

    private static final Integer DEFAULT_IS_WIN = 1;
    private static final Integer UPDATED_IS_WIN = 2;

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_SOUND_EVENT = "BBBBBBBBBB";

    @Autowired
    private MMatchResultCutinRepository mMatchResultCutinRepository;

    @Autowired
    private MMatchResultCutinMapper mMatchResultCutinMapper;

    @Autowired
    private MMatchResultCutinService mMatchResultCutinService;

    @Autowired
    private MMatchResultCutinQueryService mMatchResultCutinQueryService;

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

    private MockMvc restMMatchResultCutinMockMvc;

    private MMatchResultCutin mMatchResultCutin;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMatchResultCutinResource mMatchResultCutinResource = new MMatchResultCutinResource(mMatchResultCutinService, mMatchResultCutinQueryService);
        this.restMMatchResultCutinMockMvc = MockMvcBuilders.standaloneSetup(mMatchResultCutinResource)
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
    public static MMatchResultCutin createEntity(EntityManager em) {
        MMatchResultCutin mMatchResultCutin = new MMatchResultCutin()
            .characterId(DEFAULT_CHARACTER_ID)
            .teamId(DEFAULT_TEAM_ID)
            .isWin(DEFAULT_IS_WIN)
            .text(DEFAULT_TEXT)
            .soundEvent(DEFAULT_SOUND_EVENT);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mMatchResultCutin.setMcharacter(mCharacter);
        return mMatchResultCutin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMatchResultCutin createUpdatedEntity(EntityManager em) {
        MMatchResultCutin mMatchResultCutin = new MMatchResultCutin()
            .characterId(UPDATED_CHARACTER_ID)
            .teamId(UPDATED_TEAM_ID)
            .isWin(UPDATED_IS_WIN)
            .text(UPDATED_TEXT)
            .soundEvent(UPDATED_SOUND_EVENT);
        // Add required entity
        MCharacter mCharacter;
        if (TestUtil.findAll(em, MCharacter.class).isEmpty()) {
            mCharacter = MCharacterResourceIT.createUpdatedEntity(em);
            em.persist(mCharacter);
            em.flush();
        } else {
            mCharacter = TestUtil.findAll(em, MCharacter.class).get(0);
        }
        mMatchResultCutin.setMcharacter(mCharacter);
        return mMatchResultCutin;
    }

    @BeforeEach
    public void initTest() {
        mMatchResultCutin = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMatchResultCutin() throws Exception {
        int databaseSizeBeforeCreate = mMatchResultCutinRepository.findAll().size();

        // Create the MMatchResultCutin
        MMatchResultCutinDTO mMatchResultCutinDTO = mMatchResultCutinMapper.toDto(mMatchResultCutin);
        restMMatchResultCutinMockMvc.perform(post("/api/m-match-result-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchResultCutinDTO)))
            .andExpect(status().isCreated());

        // Validate the MMatchResultCutin in the database
        List<MMatchResultCutin> mMatchResultCutinList = mMatchResultCutinRepository.findAll();
        assertThat(mMatchResultCutinList).hasSize(databaseSizeBeforeCreate + 1);
        MMatchResultCutin testMMatchResultCutin = mMatchResultCutinList.get(mMatchResultCutinList.size() - 1);
        assertThat(testMMatchResultCutin.getCharacterId()).isEqualTo(DEFAULT_CHARACTER_ID);
        assertThat(testMMatchResultCutin.getTeamId()).isEqualTo(DEFAULT_TEAM_ID);
        assertThat(testMMatchResultCutin.getIsWin()).isEqualTo(DEFAULT_IS_WIN);
        assertThat(testMMatchResultCutin.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testMMatchResultCutin.getSoundEvent()).isEqualTo(DEFAULT_SOUND_EVENT);
    }

    @Test
    @Transactional
    public void createMMatchResultCutinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMatchResultCutinRepository.findAll().size();

        // Create the MMatchResultCutin with an existing ID
        mMatchResultCutin.setId(1L);
        MMatchResultCutinDTO mMatchResultCutinDTO = mMatchResultCutinMapper.toDto(mMatchResultCutin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMatchResultCutinMockMvc.perform(post("/api/m-match-result-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchResultCutinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMatchResultCutin in the database
        List<MMatchResultCutin> mMatchResultCutinList = mMatchResultCutinRepository.findAll();
        assertThat(mMatchResultCutinList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCharacterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchResultCutinRepository.findAll().size();
        // set the field null
        mMatchResultCutin.setCharacterId(null);

        // Create the MMatchResultCutin, which fails.
        MMatchResultCutinDTO mMatchResultCutinDTO = mMatchResultCutinMapper.toDto(mMatchResultCutin);

        restMMatchResultCutinMockMvc.perform(post("/api/m-match-result-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchResultCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchResultCutin> mMatchResultCutinList = mMatchResultCutinRepository.findAll();
        assertThat(mMatchResultCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeamIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchResultCutinRepository.findAll().size();
        // set the field null
        mMatchResultCutin.setTeamId(null);

        // Create the MMatchResultCutin, which fails.
        MMatchResultCutinDTO mMatchResultCutinDTO = mMatchResultCutinMapper.toDto(mMatchResultCutin);

        restMMatchResultCutinMockMvc.perform(post("/api/m-match-result-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchResultCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchResultCutin> mMatchResultCutinList = mMatchResultCutinRepository.findAll();
        assertThat(mMatchResultCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsWinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMatchResultCutinRepository.findAll().size();
        // set the field null
        mMatchResultCutin.setIsWin(null);

        // Create the MMatchResultCutin, which fails.
        MMatchResultCutinDTO mMatchResultCutinDTO = mMatchResultCutinMapper.toDto(mMatchResultCutin);

        restMMatchResultCutinMockMvc.perform(post("/api/m-match-result-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchResultCutinDTO)))
            .andExpect(status().isBadRequest());

        List<MMatchResultCutin> mMatchResultCutinList = mMatchResultCutinRepository.findAll();
        assertThat(mMatchResultCutinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutins() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList
        restMMatchResultCutinMockMvc.perform(get("/api/m-match-result-cutins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMatchResultCutin.getId().intValue())))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)))
            .andExpect(jsonPath("$.[*].isWin").value(hasItem(DEFAULT_IS_WIN)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].soundEvent").value(hasItem(DEFAULT_SOUND_EVENT.toString())));
    }
    
    @Test
    @Transactional
    public void getMMatchResultCutin() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get the mMatchResultCutin
        restMMatchResultCutinMockMvc.perform(get("/api/m-match-result-cutins/{id}", mMatchResultCutin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMatchResultCutin.getId().intValue()))
            .andExpect(jsonPath("$.characterId").value(DEFAULT_CHARACTER_ID))
            .andExpect(jsonPath("$.teamId").value(DEFAULT_TEAM_ID))
            .andExpect(jsonPath("$.isWin").value(DEFAULT_IS_WIN))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()))
            .andExpect(jsonPath("$.soundEvent").value(DEFAULT_SOUND_EVENT.toString()));
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByCharacterIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where characterId equals to DEFAULT_CHARACTER_ID
        defaultMMatchResultCutinShouldBeFound("characterId.equals=" + DEFAULT_CHARACTER_ID);

        // Get all the mMatchResultCutinList where characterId equals to UPDATED_CHARACTER_ID
        defaultMMatchResultCutinShouldNotBeFound("characterId.equals=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByCharacterIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where characterId in DEFAULT_CHARACTER_ID or UPDATED_CHARACTER_ID
        defaultMMatchResultCutinShouldBeFound("characterId.in=" + DEFAULT_CHARACTER_ID + "," + UPDATED_CHARACTER_ID);

        // Get all the mMatchResultCutinList where characterId equals to UPDATED_CHARACTER_ID
        defaultMMatchResultCutinShouldNotBeFound("characterId.in=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByCharacterIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where characterId is not null
        defaultMMatchResultCutinShouldBeFound("characterId.specified=true");

        // Get all the mMatchResultCutinList where characterId is null
        defaultMMatchResultCutinShouldNotBeFound("characterId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByCharacterIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where characterId greater than or equals to DEFAULT_CHARACTER_ID
        defaultMMatchResultCutinShouldBeFound("characterId.greaterOrEqualThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mMatchResultCutinList where characterId greater than or equals to UPDATED_CHARACTER_ID
        defaultMMatchResultCutinShouldNotBeFound("characterId.greaterOrEqualThan=" + UPDATED_CHARACTER_ID);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByCharacterIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where characterId less than or equals to DEFAULT_CHARACTER_ID
        defaultMMatchResultCutinShouldNotBeFound("characterId.lessThan=" + DEFAULT_CHARACTER_ID);

        // Get all the mMatchResultCutinList where characterId less than or equals to UPDATED_CHARACTER_ID
        defaultMMatchResultCutinShouldBeFound("characterId.lessThan=" + UPDATED_CHARACTER_ID);
    }


    @Test
    @Transactional
    public void getAllMMatchResultCutinsByTeamIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where teamId equals to DEFAULT_TEAM_ID
        defaultMMatchResultCutinShouldBeFound("teamId.equals=" + DEFAULT_TEAM_ID);

        // Get all the mMatchResultCutinList where teamId equals to UPDATED_TEAM_ID
        defaultMMatchResultCutinShouldNotBeFound("teamId.equals=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByTeamIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where teamId in DEFAULT_TEAM_ID or UPDATED_TEAM_ID
        defaultMMatchResultCutinShouldBeFound("teamId.in=" + DEFAULT_TEAM_ID + "," + UPDATED_TEAM_ID);

        // Get all the mMatchResultCutinList where teamId equals to UPDATED_TEAM_ID
        defaultMMatchResultCutinShouldNotBeFound("teamId.in=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByTeamIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where teamId is not null
        defaultMMatchResultCutinShouldBeFound("teamId.specified=true");

        // Get all the mMatchResultCutinList where teamId is null
        defaultMMatchResultCutinShouldNotBeFound("teamId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByTeamIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where teamId greater than or equals to DEFAULT_TEAM_ID
        defaultMMatchResultCutinShouldBeFound("teamId.greaterOrEqualThan=" + DEFAULT_TEAM_ID);

        // Get all the mMatchResultCutinList where teamId greater than or equals to UPDATED_TEAM_ID
        defaultMMatchResultCutinShouldNotBeFound("teamId.greaterOrEqualThan=" + UPDATED_TEAM_ID);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByTeamIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where teamId less than or equals to DEFAULT_TEAM_ID
        defaultMMatchResultCutinShouldNotBeFound("teamId.lessThan=" + DEFAULT_TEAM_ID);

        // Get all the mMatchResultCutinList where teamId less than or equals to UPDATED_TEAM_ID
        defaultMMatchResultCutinShouldBeFound("teamId.lessThan=" + UPDATED_TEAM_ID);
    }


    @Test
    @Transactional
    public void getAllMMatchResultCutinsByIsWinIsEqualToSomething() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where isWin equals to DEFAULT_IS_WIN
        defaultMMatchResultCutinShouldBeFound("isWin.equals=" + DEFAULT_IS_WIN);

        // Get all the mMatchResultCutinList where isWin equals to UPDATED_IS_WIN
        defaultMMatchResultCutinShouldNotBeFound("isWin.equals=" + UPDATED_IS_WIN);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByIsWinIsInShouldWork() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where isWin in DEFAULT_IS_WIN or UPDATED_IS_WIN
        defaultMMatchResultCutinShouldBeFound("isWin.in=" + DEFAULT_IS_WIN + "," + UPDATED_IS_WIN);

        // Get all the mMatchResultCutinList where isWin equals to UPDATED_IS_WIN
        defaultMMatchResultCutinShouldNotBeFound("isWin.in=" + UPDATED_IS_WIN);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByIsWinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where isWin is not null
        defaultMMatchResultCutinShouldBeFound("isWin.specified=true");

        // Get all the mMatchResultCutinList where isWin is null
        defaultMMatchResultCutinShouldNotBeFound("isWin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByIsWinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where isWin greater than or equals to DEFAULT_IS_WIN
        defaultMMatchResultCutinShouldBeFound("isWin.greaterOrEqualThan=" + DEFAULT_IS_WIN);

        // Get all the mMatchResultCutinList where isWin greater than or equals to UPDATED_IS_WIN
        defaultMMatchResultCutinShouldNotBeFound("isWin.greaterOrEqualThan=" + UPDATED_IS_WIN);
    }

    @Test
    @Transactional
    public void getAllMMatchResultCutinsByIsWinIsLessThanSomething() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        // Get all the mMatchResultCutinList where isWin less than or equals to DEFAULT_IS_WIN
        defaultMMatchResultCutinShouldNotBeFound("isWin.lessThan=" + DEFAULT_IS_WIN);

        // Get all the mMatchResultCutinList where isWin less than or equals to UPDATED_IS_WIN
        defaultMMatchResultCutinShouldBeFound("isWin.lessThan=" + UPDATED_IS_WIN);
    }


    @Test
    @Transactional
    public void getAllMMatchResultCutinsByMcharacterIsEqualToSomething() throws Exception {
        // Get already existing entity
        MCharacter mcharacter = mMatchResultCutin.getMcharacter();
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);
        Long mcharacterId = mcharacter.getId();

        // Get all the mMatchResultCutinList where mcharacter equals to mcharacterId
        defaultMMatchResultCutinShouldBeFound("mcharacterId.equals=" + mcharacterId);

        // Get all the mMatchResultCutinList where mcharacter equals to mcharacterId + 1
        defaultMMatchResultCutinShouldNotBeFound("mcharacterId.equals=" + (mcharacterId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMatchResultCutinShouldBeFound(String filter) throws Exception {
        restMMatchResultCutinMockMvc.perform(get("/api/m-match-result-cutins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMatchResultCutin.getId().intValue())))
            .andExpect(jsonPath("$.[*].characterId").value(hasItem(DEFAULT_CHARACTER_ID)))
            .andExpect(jsonPath("$.[*].teamId").value(hasItem(DEFAULT_TEAM_ID)))
            .andExpect(jsonPath("$.[*].isWin").value(hasItem(DEFAULT_IS_WIN)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].soundEvent").value(hasItem(DEFAULT_SOUND_EVENT.toString())));

        // Check, that the count call also returns 1
        restMMatchResultCutinMockMvc.perform(get("/api/m-match-result-cutins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMatchResultCutinShouldNotBeFound(String filter) throws Exception {
        restMMatchResultCutinMockMvc.perform(get("/api/m-match-result-cutins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMatchResultCutinMockMvc.perform(get("/api/m-match-result-cutins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMatchResultCutin() throws Exception {
        // Get the mMatchResultCutin
        restMMatchResultCutinMockMvc.perform(get("/api/m-match-result-cutins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMatchResultCutin() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        int databaseSizeBeforeUpdate = mMatchResultCutinRepository.findAll().size();

        // Update the mMatchResultCutin
        MMatchResultCutin updatedMMatchResultCutin = mMatchResultCutinRepository.findById(mMatchResultCutin.getId()).get();
        // Disconnect from session so that the updates on updatedMMatchResultCutin are not directly saved in db
        em.detach(updatedMMatchResultCutin);
        updatedMMatchResultCutin
            .characterId(UPDATED_CHARACTER_ID)
            .teamId(UPDATED_TEAM_ID)
            .isWin(UPDATED_IS_WIN)
            .text(UPDATED_TEXT)
            .soundEvent(UPDATED_SOUND_EVENT);
        MMatchResultCutinDTO mMatchResultCutinDTO = mMatchResultCutinMapper.toDto(updatedMMatchResultCutin);

        restMMatchResultCutinMockMvc.perform(put("/api/m-match-result-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchResultCutinDTO)))
            .andExpect(status().isOk());

        // Validate the MMatchResultCutin in the database
        List<MMatchResultCutin> mMatchResultCutinList = mMatchResultCutinRepository.findAll();
        assertThat(mMatchResultCutinList).hasSize(databaseSizeBeforeUpdate);
        MMatchResultCutin testMMatchResultCutin = mMatchResultCutinList.get(mMatchResultCutinList.size() - 1);
        assertThat(testMMatchResultCutin.getCharacterId()).isEqualTo(UPDATED_CHARACTER_ID);
        assertThat(testMMatchResultCutin.getTeamId()).isEqualTo(UPDATED_TEAM_ID);
        assertThat(testMMatchResultCutin.getIsWin()).isEqualTo(UPDATED_IS_WIN);
        assertThat(testMMatchResultCutin.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testMMatchResultCutin.getSoundEvent()).isEqualTo(UPDATED_SOUND_EVENT);
    }

    @Test
    @Transactional
    public void updateNonExistingMMatchResultCutin() throws Exception {
        int databaseSizeBeforeUpdate = mMatchResultCutinRepository.findAll().size();

        // Create the MMatchResultCutin
        MMatchResultCutinDTO mMatchResultCutinDTO = mMatchResultCutinMapper.toDto(mMatchResultCutin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMatchResultCutinMockMvc.perform(put("/api/m-match-result-cutins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMatchResultCutinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMatchResultCutin in the database
        List<MMatchResultCutin> mMatchResultCutinList = mMatchResultCutinRepository.findAll();
        assertThat(mMatchResultCutinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMatchResultCutin() throws Exception {
        // Initialize the database
        mMatchResultCutinRepository.saveAndFlush(mMatchResultCutin);

        int databaseSizeBeforeDelete = mMatchResultCutinRepository.findAll().size();

        // Delete the mMatchResultCutin
        restMMatchResultCutinMockMvc.perform(delete("/api/m-match-result-cutins/{id}", mMatchResultCutin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMatchResultCutin> mMatchResultCutinList = mMatchResultCutinRepository.findAll();
        assertThat(mMatchResultCutinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMatchResultCutin.class);
        MMatchResultCutin mMatchResultCutin1 = new MMatchResultCutin();
        mMatchResultCutin1.setId(1L);
        MMatchResultCutin mMatchResultCutin2 = new MMatchResultCutin();
        mMatchResultCutin2.setId(mMatchResultCutin1.getId());
        assertThat(mMatchResultCutin1).isEqualTo(mMatchResultCutin2);
        mMatchResultCutin2.setId(2L);
        assertThat(mMatchResultCutin1).isNotEqualTo(mMatchResultCutin2);
        mMatchResultCutin1.setId(null);
        assertThat(mMatchResultCutin1).isNotEqualTo(mMatchResultCutin2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMatchResultCutinDTO.class);
        MMatchResultCutinDTO mMatchResultCutinDTO1 = new MMatchResultCutinDTO();
        mMatchResultCutinDTO1.setId(1L);
        MMatchResultCutinDTO mMatchResultCutinDTO2 = new MMatchResultCutinDTO();
        assertThat(mMatchResultCutinDTO1).isNotEqualTo(mMatchResultCutinDTO2);
        mMatchResultCutinDTO2.setId(mMatchResultCutinDTO1.getId());
        assertThat(mMatchResultCutinDTO1).isEqualTo(mMatchResultCutinDTO2);
        mMatchResultCutinDTO2.setId(2L);
        assertThat(mMatchResultCutinDTO1).isNotEqualTo(mMatchResultCutinDTO2);
        mMatchResultCutinDTO1.setId(null);
        assertThat(mMatchResultCutinDTO1).isNotEqualTo(mMatchResultCutinDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMatchResultCutinMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMatchResultCutinMapper.fromId(null)).isNull();
    }
}
