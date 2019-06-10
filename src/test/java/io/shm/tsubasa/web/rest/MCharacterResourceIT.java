package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCharacter;
import io.shm.tsubasa.domain.MActionSkillHolderCardCt;
import io.shm.tsubasa.domain.MCombinationCutPosition;
import io.shm.tsubasa.domain.MMatchResultCutin;
import io.shm.tsubasa.domain.MNpcCard;
import io.shm.tsubasa.domain.MTargetCharacterGroup;
import io.shm.tsubasa.repository.MCharacterRepository;
import io.shm.tsubasa.service.MCharacterService;
import io.shm.tsubasa.service.dto.MCharacterDTO;
import io.shm.tsubasa.service.mapper.MCharacterMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCharacterCriteria;
import io.shm.tsubasa.service.MCharacterQueryService;

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
 * Integration tests for the {@Link MCharacterResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCharacterResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ANNOUNCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ANNOUNCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHARACTER_BOOK_PRIORITY = 1;
    private static final Integer UPDATED_CHARACTER_BOOK_PRIORITY = 2;

    @Autowired
    private MCharacterRepository mCharacterRepository;

    @Autowired
    private MCharacterMapper mCharacterMapper;

    @Autowired
    private MCharacterService mCharacterService;

    @Autowired
    private MCharacterQueryService mCharacterQueryService;

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

    private MockMvc restMCharacterMockMvc;

    private MCharacter mCharacter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCharacterResource mCharacterResource = new MCharacterResource(mCharacterService, mCharacterQueryService);
        this.restMCharacterMockMvc = MockMvcBuilders.standaloneSetup(mCharacterResource)
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
    public static MCharacter createEntity(EntityManager em) {
        MCharacter mCharacter = new MCharacter()
            .name(DEFAULT_NAME)
            .announceName(DEFAULT_ANNOUNCE_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .characterBookPriority(DEFAULT_CHARACTER_BOOK_PRIORITY);
        return mCharacter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCharacter createUpdatedEntity(EntityManager em) {
        MCharacter mCharacter = new MCharacter()
            .name(UPDATED_NAME)
            .announceName(UPDATED_ANNOUNCE_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .characterBookPriority(UPDATED_CHARACTER_BOOK_PRIORITY);
        return mCharacter;
    }

    @BeforeEach
    public void initTest() {
        mCharacter = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCharacter() throws Exception {
        int databaseSizeBeforeCreate = mCharacterRepository.findAll().size();

        // Create the MCharacter
        MCharacterDTO mCharacterDTO = mCharacterMapper.toDto(mCharacter);
        restMCharacterMockMvc.perform(post("/api/m-characters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterDTO)))
            .andExpect(status().isCreated());

        // Validate the MCharacter in the database
        List<MCharacter> mCharacterList = mCharacterRepository.findAll();
        assertThat(mCharacterList).hasSize(databaseSizeBeforeCreate + 1);
        MCharacter testMCharacter = mCharacterList.get(mCharacterList.size() - 1);
        assertThat(testMCharacter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMCharacter.getAnnounceName()).isEqualTo(DEFAULT_ANNOUNCE_NAME);
        assertThat(testMCharacter.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMCharacter.getCharacterBookPriority()).isEqualTo(DEFAULT_CHARACTER_BOOK_PRIORITY);
    }

    @Test
    @Transactional
    public void createMCharacterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCharacterRepository.findAll().size();

        // Create the MCharacter with an existing ID
        mCharacter.setId(1L);
        MCharacterDTO mCharacterDTO = mCharacterMapper.toDto(mCharacter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCharacterMockMvc.perform(post("/api/m-characters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCharacter in the database
        List<MCharacter> mCharacterList = mCharacterRepository.findAll();
        assertThat(mCharacterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCharacterBookPriorityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCharacterRepository.findAll().size();
        // set the field null
        mCharacter.setCharacterBookPriority(null);

        // Create the MCharacter, which fails.
        MCharacterDTO mCharacterDTO = mCharacterMapper.toDto(mCharacter);

        restMCharacterMockMvc.perform(post("/api/m-characters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterDTO)))
            .andExpect(status().isBadRequest());

        List<MCharacter> mCharacterList = mCharacterRepository.findAll();
        assertThat(mCharacterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCharacters() throws Exception {
        // Initialize the database
        mCharacterRepository.saveAndFlush(mCharacter);

        // Get all the mCharacterList
        restMCharacterMockMvc.perform(get("/api/m-characters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCharacter.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].announceName").value(hasItem(DEFAULT_ANNOUNCE_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].characterBookPriority").value(hasItem(DEFAULT_CHARACTER_BOOK_PRIORITY)));
    }
    
    @Test
    @Transactional
    public void getMCharacter() throws Exception {
        // Initialize the database
        mCharacterRepository.saveAndFlush(mCharacter);

        // Get the mCharacter
        restMCharacterMockMvc.perform(get("/api/m-characters/{id}", mCharacter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCharacter.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.announceName").value(DEFAULT_ANNOUNCE_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.characterBookPriority").value(DEFAULT_CHARACTER_BOOK_PRIORITY));
    }

    @Test
    @Transactional
    public void getAllMCharactersByCharacterBookPriorityIsEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterRepository.saveAndFlush(mCharacter);

        // Get all the mCharacterList where characterBookPriority equals to DEFAULT_CHARACTER_BOOK_PRIORITY
        defaultMCharacterShouldBeFound("characterBookPriority.equals=" + DEFAULT_CHARACTER_BOOK_PRIORITY);

        // Get all the mCharacterList where characterBookPriority equals to UPDATED_CHARACTER_BOOK_PRIORITY
        defaultMCharacterShouldNotBeFound("characterBookPriority.equals=" + UPDATED_CHARACTER_BOOK_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllMCharactersByCharacterBookPriorityIsInShouldWork() throws Exception {
        // Initialize the database
        mCharacterRepository.saveAndFlush(mCharacter);

        // Get all the mCharacterList where characterBookPriority in DEFAULT_CHARACTER_BOOK_PRIORITY or UPDATED_CHARACTER_BOOK_PRIORITY
        defaultMCharacterShouldBeFound("characterBookPriority.in=" + DEFAULT_CHARACTER_BOOK_PRIORITY + "," + UPDATED_CHARACTER_BOOK_PRIORITY);

        // Get all the mCharacterList where characterBookPriority equals to UPDATED_CHARACTER_BOOK_PRIORITY
        defaultMCharacterShouldNotBeFound("characterBookPriority.in=" + UPDATED_CHARACTER_BOOK_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllMCharactersByCharacterBookPriorityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCharacterRepository.saveAndFlush(mCharacter);

        // Get all the mCharacterList where characterBookPriority is not null
        defaultMCharacterShouldBeFound("characterBookPriority.specified=true");

        // Get all the mCharacterList where characterBookPriority is null
        defaultMCharacterShouldNotBeFound("characterBookPriority.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCharactersByCharacterBookPriorityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterRepository.saveAndFlush(mCharacter);

        // Get all the mCharacterList where characterBookPriority greater than or equals to DEFAULT_CHARACTER_BOOK_PRIORITY
        defaultMCharacterShouldBeFound("characterBookPriority.greaterOrEqualThan=" + DEFAULT_CHARACTER_BOOK_PRIORITY);

        // Get all the mCharacterList where characterBookPriority greater than or equals to UPDATED_CHARACTER_BOOK_PRIORITY
        defaultMCharacterShouldNotBeFound("characterBookPriority.greaterOrEqualThan=" + UPDATED_CHARACTER_BOOK_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllMCharactersByCharacterBookPriorityIsLessThanSomething() throws Exception {
        // Initialize the database
        mCharacterRepository.saveAndFlush(mCharacter);

        // Get all the mCharacterList where characterBookPriority less than or equals to DEFAULT_CHARACTER_BOOK_PRIORITY
        defaultMCharacterShouldNotBeFound("characterBookPriority.lessThan=" + DEFAULT_CHARACTER_BOOK_PRIORITY);

        // Get all the mCharacterList where characterBookPriority less than or equals to UPDATED_CHARACTER_BOOK_PRIORITY
        defaultMCharacterShouldBeFound("characterBookPriority.lessThan=" + UPDATED_CHARACTER_BOOK_PRIORITY);
    }


    @Test
    @Transactional
    public void getAllMCharactersByMActionSkillHolderCardCtIsEqualToSomething() throws Exception {
        // Initialize the database
        MActionSkillHolderCardCt mActionSkillHolderCardCt = MActionSkillHolderCardCtResourceIT.createEntity(em);
        em.persist(mActionSkillHolderCardCt);
        em.flush();
        mCharacter.addMActionSkillHolderCardCt(mActionSkillHolderCardCt);
        mCharacterRepository.saveAndFlush(mCharacter);
        Long mActionSkillHolderCardCtId = mActionSkillHolderCardCt.getId();

        // Get all the mCharacterList where mActionSkillHolderCardCt equals to mActionSkillHolderCardCtId
        defaultMCharacterShouldBeFound("mActionSkillHolderCardCtId.equals=" + mActionSkillHolderCardCtId);

        // Get all the mCharacterList where mActionSkillHolderCardCt equals to mActionSkillHolderCardCtId + 1
        defaultMCharacterShouldNotBeFound("mActionSkillHolderCardCtId.equals=" + (mActionSkillHolderCardCtId + 1));
    }


    @Test
    @Transactional
    public void getAllMCharactersByMCombinationCutPositionIsEqualToSomething() throws Exception {
        // Initialize the database
        MCombinationCutPosition mCombinationCutPosition = MCombinationCutPositionResourceIT.createEntity(em);
        em.persist(mCombinationCutPosition);
        em.flush();
        mCharacter.addMCombinationCutPosition(mCombinationCutPosition);
        mCharacterRepository.saveAndFlush(mCharacter);
        Long mCombinationCutPositionId = mCombinationCutPosition.getId();

        // Get all the mCharacterList where mCombinationCutPosition equals to mCombinationCutPositionId
        defaultMCharacterShouldBeFound("mCombinationCutPositionId.equals=" + mCombinationCutPositionId);

        // Get all the mCharacterList where mCombinationCutPosition equals to mCombinationCutPositionId + 1
        defaultMCharacterShouldNotBeFound("mCombinationCutPositionId.equals=" + (mCombinationCutPositionId + 1));
    }


    @Test
    @Transactional
    public void getAllMCharactersByMMatchResultCutinIsEqualToSomething() throws Exception {
        // Initialize the database
        MMatchResultCutin mMatchResultCutin = MMatchResultCutinResourceIT.createEntity(em);
        em.persist(mMatchResultCutin);
        em.flush();
        mCharacter.addMMatchResultCutin(mMatchResultCutin);
        mCharacterRepository.saveAndFlush(mCharacter);
        Long mMatchResultCutinId = mMatchResultCutin.getId();

        // Get all the mCharacterList where mMatchResultCutin equals to mMatchResultCutinId
        defaultMCharacterShouldBeFound("mMatchResultCutinId.equals=" + mMatchResultCutinId);

        // Get all the mCharacterList where mMatchResultCutin equals to mMatchResultCutinId + 1
        defaultMCharacterShouldNotBeFound("mMatchResultCutinId.equals=" + (mMatchResultCutinId + 1));
    }


    @Test
    @Transactional
    public void getAllMCharactersByMNpcCardIsEqualToSomething() throws Exception {
        // Initialize the database
        MNpcCard mNpcCard = MNpcCardResourceIT.createEntity(em);
        em.persist(mNpcCard);
        em.flush();
        mCharacter.addMNpcCard(mNpcCard);
        mCharacterRepository.saveAndFlush(mCharacter);
        Long mNpcCardId = mNpcCard.getId();

        // Get all the mCharacterList where mNpcCard equals to mNpcCardId
        defaultMCharacterShouldBeFound("mNpcCardId.equals=" + mNpcCardId);

        // Get all the mCharacterList where mNpcCard equals to mNpcCardId + 1
        defaultMCharacterShouldNotBeFound("mNpcCardId.equals=" + (mNpcCardId + 1));
    }


    @Test
    @Transactional
    public void getAllMCharactersByMTargetCharacterGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        MTargetCharacterGroup mTargetCharacterGroup = MTargetCharacterGroupResourceIT.createEntity(em);
        em.persist(mTargetCharacterGroup);
        em.flush();
        mCharacter.addMTargetCharacterGroup(mTargetCharacterGroup);
        mCharacterRepository.saveAndFlush(mCharacter);
        Long mTargetCharacterGroupId = mTargetCharacterGroup.getId();

        // Get all the mCharacterList where mTargetCharacterGroup equals to mTargetCharacterGroupId
        defaultMCharacterShouldBeFound("mTargetCharacterGroupId.equals=" + mTargetCharacterGroupId);

        // Get all the mCharacterList where mTargetCharacterGroup equals to mTargetCharacterGroupId + 1
        defaultMCharacterShouldNotBeFound("mTargetCharacterGroupId.equals=" + (mTargetCharacterGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCharacterShouldBeFound(String filter) throws Exception {
        restMCharacterMockMvc.perform(get("/api/m-characters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCharacter.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].announceName").value(hasItem(DEFAULT_ANNOUNCE_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].characterBookPriority").value(hasItem(DEFAULT_CHARACTER_BOOK_PRIORITY)));

        // Check, that the count call also returns 1
        restMCharacterMockMvc.perform(get("/api/m-characters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCharacterShouldNotBeFound(String filter) throws Exception {
        restMCharacterMockMvc.perform(get("/api/m-characters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCharacterMockMvc.perform(get("/api/m-characters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCharacter() throws Exception {
        // Get the mCharacter
        restMCharacterMockMvc.perform(get("/api/m-characters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCharacter() throws Exception {
        // Initialize the database
        mCharacterRepository.saveAndFlush(mCharacter);

        int databaseSizeBeforeUpdate = mCharacterRepository.findAll().size();

        // Update the mCharacter
        MCharacter updatedMCharacter = mCharacterRepository.findById(mCharacter.getId()).get();
        // Disconnect from session so that the updates on updatedMCharacter are not directly saved in db
        em.detach(updatedMCharacter);
        updatedMCharacter
            .name(UPDATED_NAME)
            .announceName(UPDATED_ANNOUNCE_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .characterBookPriority(UPDATED_CHARACTER_BOOK_PRIORITY);
        MCharacterDTO mCharacterDTO = mCharacterMapper.toDto(updatedMCharacter);

        restMCharacterMockMvc.perform(put("/api/m-characters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterDTO)))
            .andExpect(status().isOk());

        // Validate the MCharacter in the database
        List<MCharacter> mCharacterList = mCharacterRepository.findAll();
        assertThat(mCharacterList).hasSize(databaseSizeBeforeUpdate);
        MCharacter testMCharacter = mCharacterList.get(mCharacterList.size() - 1);
        assertThat(testMCharacter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMCharacter.getAnnounceName()).isEqualTo(UPDATED_ANNOUNCE_NAME);
        assertThat(testMCharacter.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMCharacter.getCharacterBookPriority()).isEqualTo(UPDATED_CHARACTER_BOOK_PRIORITY);
    }

    @Test
    @Transactional
    public void updateNonExistingMCharacter() throws Exception {
        int databaseSizeBeforeUpdate = mCharacterRepository.findAll().size();

        // Create the MCharacter
        MCharacterDTO mCharacterDTO = mCharacterMapper.toDto(mCharacter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCharacterMockMvc.perform(put("/api/m-characters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCharacter in the database
        List<MCharacter> mCharacterList = mCharacterRepository.findAll();
        assertThat(mCharacterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCharacter() throws Exception {
        // Initialize the database
        mCharacterRepository.saveAndFlush(mCharacter);

        int databaseSizeBeforeDelete = mCharacterRepository.findAll().size();

        // Delete the mCharacter
        restMCharacterMockMvc.perform(delete("/api/m-characters/{id}", mCharacter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCharacter> mCharacterList = mCharacterRepository.findAll();
        assertThat(mCharacterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCharacter.class);
        MCharacter mCharacter1 = new MCharacter();
        mCharacter1.setId(1L);
        MCharacter mCharacter2 = new MCharacter();
        mCharacter2.setId(mCharacter1.getId());
        assertThat(mCharacter1).isEqualTo(mCharacter2);
        mCharacter2.setId(2L);
        assertThat(mCharacter1).isNotEqualTo(mCharacter2);
        mCharacter1.setId(null);
        assertThat(mCharacter1).isNotEqualTo(mCharacter2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCharacterDTO.class);
        MCharacterDTO mCharacterDTO1 = new MCharacterDTO();
        mCharacterDTO1.setId(1L);
        MCharacterDTO mCharacterDTO2 = new MCharacterDTO();
        assertThat(mCharacterDTO1).isNotEqualTo(mCharacterDTO2);
        mCharacterDTO2.setId(mCharacterDTO1.getId());
        assertThat(mCharacterDTO1).isEqualTo(mCharacterDTO2);
        mCharacterDTO2.setId(2L);
        assertThat(mCharacterDTO1).isNotEqualTo(mCharacterDTO2);
        mCharacterDTO1.setId(null);
        assertThat(mCharacterDTO1).isNotEqualTo(mCharacterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCharacterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCharacterMapper.fromId(null)).isNull();
    }
}
