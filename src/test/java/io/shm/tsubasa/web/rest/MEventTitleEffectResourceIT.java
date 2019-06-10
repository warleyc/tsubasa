package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MEventTitleEffect;
import io.shm.tsubasa.repository.MEventTitleEffectRepository;
import io.shm.tsubasa.service.MEventTitleEffectService;
import io.shm.tsubasa.service.dto.MEventTitleEffectDTO;
import io.shm.tsubasa.service.mapper.MEventTitleEffectMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MEventTitleEffectCriteria;
import io.shm.tsubasa.service.MEventTitleEffectQueryService;

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
 * Integration tests for the {@Link MEventTitleEffectResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MEventTitleEffectResourceIT {

    private static final String DEFAULT_EFFECT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_EFFECT_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_BGM_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_BGM_SOUND_EVENT = "BBBBBBBBBB";

    private static final String DEFAULT_SE_SOUND_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_SE_SOUND_EVENT = "BBBBBBBBBB";

    private static final String DEFAULT_VOICE_SOUND_EVENT_SUFFIXES = "AAAAAAAAAA";
    private static final String UPDATED_VOICE_SOUND_EVENT_SUFFIXES = "BBBBBBBBBB";

    private static final String DEFAULT_COPYRIGHT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_COPYRIGHT_TEXT = "BBBBBBBBBB";

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    @Autowired
    private MEventTitleEffectRepository mEventTitleEffectRepository;

    @Autowired
    private MEventTitleEffectMapper mEventTitleEffectMapper;

    @Autowired
    private MEventTitleEffectService mEventTitleEffectService;

    @Autowired
    private MEventTitleEffectQueryService mEventTitleEffectQueryService;

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

    private MockMvc restMEventTitleEffectMockMvc;

    private MEventTitleEffect mEventTitleEffect;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MEventTitleEffectResource mEventTitleEffectResource = new MEventTitleEffectResource(mEventTitleEffectService, mEventTitleEffectQueryService);
        this.restMEventTitleEffectMockMvc = MockMvcBuilders.standaloneSetup(mEventTitleEffectResource)
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
    public static MEventTitleEffect createEntity(EntityManager em) {
        MEventTitleEffect mEventTitleEffect = new MEventTitleEffect()
            .effectPath(DEFAULT_EFFECT_PATH)
            .bgmSoundEvent(DEFAULT_BGM_SOUND_EVENT)
            .seSoundEvent(DEFAULT_SE_SOUND_EVENT)
            .voiceSoundEventSuffixes(DEFAULT_VOICE_SOUND_EVENT_SUFFIXES)
            .copyrightText(DEFAULT_COPYRIGHT_TEXT)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT);
        return mEventTitleEffect;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MEventTitleEffect createUpdatedEntity(EntityManager em) {
        MEventTitleEffect mEventTitleEffect = new MEventTitleEffect()
            .effectPath(UPDATED_EFFECT_PATH)
            .bgmSoundEvent(UPDATED_BGM_SOUND_EVENT)
            .seSoundEvent(UPDATED_SE_SOUND_EVENT)
            .voiceSoundEventSuffixes(UPDATED_VOICE_SOUND_EVENT_SUFFIXES)
            .copyrightText(UPDATED_COPYRIGHT_TEXT)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        return mEventTitleEffect;
    }

    @BeforeEach
    public void initTest() {
        mEventTitleEffect = createEntity(em);
    }

    @Test
    @Transactional
    public void createMEventTitleEffect() throws Exception {
        int databaseSizeBeforeCreate = mEventTitleEffectRepository.findAll().size();

        // Create the MEventTitleEffect
        MEventTitleEffectDTO mEventTitleEffectDTO = mEventTitleEffectMapper.toDto(mEventTitleEffect);
        restMEventTitleEffectMockMvc.perform(post("/api/m-event-title-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEventTitleEffectDTO)))
            .andExpect(status().isCreated());

        // Validate the MEventTitleEffect in the database
        List<MEventTitleEffect> mEventTitleEffectList = mEventTitleEffectRepository.findAll();
        assertThat(mEventTitleEffectList).hasSize(databaseSizeBeforeCreate + 1);
        MEventTitleEffect testMEventTitleEffect = mEventTitleEffectList.get(mEventTitleEffectList.size() - 1);
        assertThat(testMEventTitleEffect.getEffectPath()).isEqualTo(DEFAULT_EFFECT_PATH);
        assertThat(testMEventTitleEffect.getBgmSoundEvent()).isEqualTo(DEFAULT_BGM_SOUND_EVENT);
        assertThat(testMEventTitleEffect.getSeSoundEvent()).isEqualTo(DEFAULT_SE_SOUND_EVENT);
        assertThat(testMEventTitleEffect.getVoiceSoundEventSuffixes()).isEqualTo(DEFAULT_VOICE_SOUND_EVENT_SUFFIXES);
        assertThat(testMEventTitleEffect.getCopyrightText()).isEqualTo(DEFAULT_COPYRIGHT_TEXT);
        assertThat(testMEventTitleEffect.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMEventTitleEffect.getEndAt()).isEqualTo(DEFAULT_END_AT);
    }

    @Test
    @Transactional
    public void createMEventTitleEffectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mEventTitleEffectRepository.findAll().size();

        // Create the MEventTitleEffect with an existing ID
        mEventTitleEffect.setId(1L);
        MEventTitleEffectDTO mEventTitleEffectDTO = mEventTitleEffectMapper.toDto(mEventTitleEffect);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMEventTitleEffectMockMvc.perform(post("/api/m-event-title-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEventTitleEffectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEventTitleEffect in the database
        List<MEventTitleEffect> mEventTitleEffectList = mEventTitleEffectRepository.findAll();
        assertThat(mEventTitleEffectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEventTitleEffectRepository.findAll().size();
        // set the field null
        mEventTitleEffect.setStartAt(null);

        // Create the MEventTitleEffect, which fails.
        MEventTitleEffectDTO mEventTitleEffectDTO = mEventTitleEffectMapper.toDto(mEventTitleEffect);

        restMEventTitleEffectMockMvc.perform(post("/api/m-event-title-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEventTitleEffectDTO)))
            .andExpect(status().isBadRequest());

        List<MEventTitleEffect> mEventTitleEffectList = mEventTitleEffectRepository.findAll();
        assertThat(mEventTitleEffectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mEventTitleEffectRepository.findAll().size();
        // set the field null
        mEventTitleEffect.setEndAt(null);

        // Create the MEventTitleEffect, which fails.
        MEventTitleEffectDTO mEventTitleEffectDTO = mEventTitleEffectMapper.toDto(mEventTitleEffect);

        restMEventTitleEffectMockMvc.perform(post("/api/m-event-title-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEventTitleEffectDTO)))
            .andExpect(status().isBadRequest());

        List<MEventTitleEffect> mEventTitleEffectList = mEventTitleEffectRepository.findAll();
        assertThat(mEventTitleEffectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffects() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList
        restMEventTitleEffectMockMvc.perform(get("/api/m-event-title-effects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEventTitleEffect.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectPath").value(hasItem(DEFAULT_EFFECT_PATH.toString())))
            .andExpect(jsonPath("$.[*].bgmSoundEvent").value(hasItem(DEFAULT_BGM_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].seSoundEvent").value(hasItem(DEFAULT_SE_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].voiceSoundEventSuffixes").value(hasItem(DEFAULT_VOICE_SOUND_EVENT_SUFFIXES.toString())))
            .andExpect(jsonPath("$.[*].copyrightText").value(hasItem(DEFAULT_COPYRIGHT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));
    }
    
    @Test
    @Transactional
    public void getMEventTitleEffect() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get the mEventTitleEffect
        restMEventTitleEffectMockMvc.perform(get("/api/m-event-title-effects/{id}", mEventTitleEffect.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mEventTitleEffect.getId().intValue()))
            .andExpect(jsonPath("$.effectPath").value(DEFAULT_EFFECT_PATH.toString()))
            .andExpect(jsonPath("$.bgmSoundEvent").value(DEFAULT_BGM_SOUND_EVENT.toString()))
            .andExpect(jsonPath("$.seSoundEvent").value(DEFAULT_SE_SOUND_EVENT.toString()))
            .andExpect(jsonPath("$.voiceSoundEventSuffixes").value(DEFAULT_VOICE_SOUND_EVENT_SUFFIXES.toString()))
            .andExpect(jsonPath("$.copyrightText").value(DEFAULT_COPYRIGHT_TEXT.toString()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT));
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffectsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where startAt equals to DEFAULT_START_AT
        defaultMEventTitleEffectShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mEventTitleEffectList where startAt equals to UPDATED_START_AT
        defaultMEventTitleEffectShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffectsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMEventTitleEffectShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mEventTitleEffectList where startAt equals to UPDATED_START_AT
        defaultMEventTitleEffectShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffectsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where startAt is not null
        defaultMEventTitleEffectShouldBeFound("startAt.specified=true");

        // Get all the mEventTitleEffectList where startAt is null
        defaultMEventTitleEffectShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffectsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where startAt greater than or equals to DEFAULT_START_AT
        defaultMEventTitleEffectShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mEventTitleEffectList where startAt greater than or equals to UPDATED_START_AT
        defaultMEventTitleEffectShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffectsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where startAt less than or equals to DEFAULT_START_AT
        defaultMEventTitleEffectShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mEventTitleEffectList where startAt less than or equals to UPDATED_START_AT
        defaultMEventTitleEffectShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMEventTitleEffectsByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where endAt equals to DEFAULT_END_AT
        defaultMEventTitleEffectShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mEventTitleEffectList where endAt equals to UPDATED_END_AT
        defaultMEventTitleEffectShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffectsByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMEventTitleEffectShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mEventTitleEffectList where endAt equals to UPDATED_END_AT
        defaultMEventTitleEffectShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffectsByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where endAt is not null
        defaultMEventTitleEffectShouldBeFound("endAt.specified=true");

        // Get all the mEventTitleEffectList where endAt is null
        defaultMEventTitleEffectShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffectsByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where endAt greater than or equals to DEFAULT_END_AT
        defaultMEventTitleEffectShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mEventTitleEffectList where endAt greater than or equals to UPDATED_END_AT
        defaultMEventTitleEffectShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMEventTitleEffectsByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        // Get all the mEventTitleEffectList where endAt less than or equals to DEFAULT_END_AT
        defaultMEventTitleEffectShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mEventTitleEffectList where endAt less than or equals to UPDATED_END_AT
        defaultMEventTitleEffectShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMEventTitleEffectShouldBeFound(String filter) throws Exception {
        restMEventTitleEffectMockMvc.perform(get("/api/m-event-title-effects?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mEventTitleEffect.getId().intValue())))
            .andExpect(jsonPath("$.[*].effectPath").value(hasItem(DEFAULT_EFFECT_PATH.toString())))
            .andExpect(jsonPath("$.[*].bgmSoundEvent").value(hasItem(DEFAULT_BGM_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].seSoundEvent").value(hasItem(DEFAULT_SE_SOUND_EVENT.toString())))
            .andExpect(jsonPath("$.[*].voiceSoundEventSuffixes").value(hasItem(DEFAULT_VOICE_SOUND_EVENT_SUFFIXES.toString())))
            .andExpect(jsonPath("$.[*].copyrightText").value(hasItem(DEFAULT_COPYRIGHT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));

        // Check, that the count call also returns 1
        restMEventTitleEffectMockMvc.perform(get("/api/m-event-title-effects/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMEventTitleEffectShouldNotBeFound(String filter) throws Exception {
        restMEventTitleEffectMockMvc.perform(get("/api/m-event-title-effects?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMEventTitleEffectMockMvc.perform(get("/api/m-event-title-effects/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMEventTitleEffect() throws Exception {
        // Get the mEventTitleEffect
        restMEventTitleEffectMockMvc.perform(get("/api/m-event-title-effects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMEventTitleEffect() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        int databaseSizeBeforeUpdate = mEventTitleEffectRepository.findAll().size();

        // Update the mEventTitleEffect
        MEventTitleEffect updatedMEventTitleEffect = mEventTitleEffectRepository.findById(mEventTitleEffect.getId()).get();
        // Disconnect from session so that the updates on updatedMEventTitleEffect are not directly saved in db
        em.detach(updatedMEventTitleEffect);
        updatedMEventTitleEffect
            .effectPath(UPDATED_EFFECT_PATH)
            .bgmSoundEvent(UPDATED_BGM_SOUND_EVENT)
            .seSoundEvent(UPDATED_SE_SOUND_EVENT)
            .voiceSoundEventSuffixes(UPDATED_VOICE_SOUND_EVENT_SUFFIXES)
            .copyrightText(UPDATED_COPYRIGHT_TEXT)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        MEventTitleEffectDTO mEventTitleEffectDTO = mEventTitleEffectMapper.toDto(updatedMEventTitleEffect);

        restMEventTitleEffectMockMvc.perform(put("/api/m-event-title-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEventTitleEffectDTO)))
            .andExpect(status().isOk());

        // Validate the MEventTitleEffect in the database
        List<MEventTitleEffect> mEventTitleEffectList = mEventTitleEffectRepository.findAll();
        assertThat(mEventTitleEffectList).hasSize(databaseSizeBeforeUpdate);
        MEventTitleEffect testMEventTitleEffect = mEventTitleEffectList.get(mEventTitleEffectList.size() - 1);
        assertThat(testMEventTitleEffect.getEffectPath()).isEqualTo(UPDATED_EFFECT_PATH);
        assertThat(testMEventTitleEffect.getBgmSoundEvent()).isEqualTo(UPDATED_BGM_SOUND_EVENT);
        assertThat(testMEventTitleEffect.getSeSoundEvent()).isEqualTo(UPDATED_SE_SOUND_EVENT);
        assertThat(testMEventTitleEffect.getVoiceSoundEventSuffixes()).isEqualTo(UPDATED_VOICE_SOUND_EVENT_SUFFIXES);
        assertThat(testMEventTitleEffect.getCopyrightText()).isEqualTo(UPDATED_COPYRIGHT_TEXT);
        assertThat(testMEventTitleEffect.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMEventTitleEffect.getEndAt()).isEqualTo(UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMEventTitleEffect() throws Exception {
        int databaseSizeBeforeUpdate = mEventTitleEffectRepository.findAll().size();

        // Create the MEventTitleEffect
        MEventTitleEffectDTO mEventTitleEffectDTO = mEventTitleEffectMapper.toDto(mEventTitleEffect);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMEventTitleEffectMockMvc.perform(put("/api/m-event-title-effects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mEventTitleEffectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MEventTitleEffect in the database
        List<MEventTitleEffect> mEventTitleEffectList = mEventTitleEffectRepository.findAll();
        assertThat(mEventTitleEffectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMEventTitleEffect() throws Exception {
        // Initialize the database
        mEventTitleEffectRepository.saveAndFlush(mEventTitleEffect);

        int databaseSizeBeforeDelete = mEventTitleEffectRepository.findAll().size();

        // Delete the mEventTitleEffect
        restMEventTitleEffectMockMvc.perform(delete("/api/m-event-title-effects/{id}", mEventTitleEffect.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MEventTitleEffect> mEventTitleEffectList = mEventTitleEffectRepository.findAll();
        assertThat(mEventTitleEffectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEventTitleEffect.class);
        MEventTitleEffect mEventTitleEffect1 = new MEventTitleEffect();
        mEventTitleEffect1.setId(1L);
        MEventTitleEffect mEventTitleEffect2 = new MEventTitleEffect();
        mEventTitleEffect2.setId(mEventTitleEffect1.getId());
        assertThat(mEventTitleEffect1).isEqualTo(mEventTitleEffect2);
        mEventTitleEffect2.setId(2L);
        assertThat(mEventTitleEffect1).isNotEqualTo(mEventTitleEffect2);
        mEventTitleEffect1.setId(null);
        assertThat(mEventTitleEffect1).isNotEqualTo(mEventTitleEffect2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MEventTitleEffectDTO.class);
        MEventTitleEffectDTO mEventTitleEffectDTO1 = new MEventTitleEffectDTO();
        mEventTitleEffectDTO1.setId(1L);
        MEventTitleEffectDTO mEventTitleEffectDTO2 = new MEventTitleEffectDTO();
        assertThat(mEventTitleEffectDTO1).isNotEqualTo(mEventTitleEffectDTO2);
        mEventTitleEffectDTO2.setId(mEventTitleEffectDTO1.getId());
        assertThat(mEventTitleEffectDTO1).isEqualTo(mEventTitleEffectDTO2);
        mEventTitleEffectDTO2.setId(2L);
        assertThat(mEventTitleEffectDTO1).isNotEqualTo(mEventTitleEffectDTO2);
        mEventTitleEffectDTO1.setId(null);
        assertThat(mEventTitleEffectDTO1).isNotEqualTo(mEventTitleEffectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mEventTitleEffectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mEventTitleEffectMapper.fromId(null)).isNull();
    }
}
