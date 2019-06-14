package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MSoundBankEvent;
import io.shm.tsubasa.repository.MSoundBankEventRepository;
import io.shm.tsubasa.service.MSoundBankEventService;
import io.shm.tsubasa.service.dto.MSoundBankEventDTO;
import io.shm.tsubasa.service.mapper.MSoundBankEventMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MSoundBankEventCriteria;
import io.shm.tsubasa.service.MSoundBankEventQueryService;

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
 * Integration tests for the {@Link MSoundBankEventResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MSoundBankEventResourceIT {

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EVENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_ID = "BBBBBBBBBB";

    @Autowired
    private MSoundBankEventRepository mSoundBankEventRepository;

    @Autowired
    private MSoundBankEventMapper mSoundBankEventMapper;

    @Autowired
    private MSoundBankEventService mSoundBankEventService;

    @Autowired
    private MSoundBankEventQueryService mSoundBankEventQueryService;

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

    private MockMvc restMSoundBankEventMockMvc;

    private MSoundBankEvent mSoundBankEvent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MSoundBankEventResource mSoundBankEventResource = new MSoundBankEventResource(mSoundBankEventService, mSoundBankEventQueryService);
        this.restMSoundBankEventMockMvc = MockMvcBuilders.standaloneSetup(mSoundBankEventResource)
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
    public static MSoundBankEvent createEntity(EntityManager em) {
        MSoundBankEvent mSoundBankEvent = new MSoundBankEvent()
            .path(DEFAULT_PATH)
            .name(DEFAULT_NAME)
            .eventId(DEFAULT_EVENT_ID);
        return mSoundBankEvent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MSoundBankEvent createUpdatedEntity(EntityManager em) {
        MSoundBankEvent mSoundBankEvent = new MSoundBankEvent()
            .path(UPDATED_PATH)
            .name(UPDATED_NAME)
            .eventId(UPDATED_EVENT_ID);
        return mSoundBankEvent;
    }

    @BeforeEach
    public void initTest() {
        mSoundBankEvent = createEntity(em);
    }

    @Test
    @Transactional
    public void createMSoundBankEvent() throws Exception {
        int databaseSizeBeforeCreate = mSoundBankEventRepository.findAll().size();

        // Create the MSoundBankEvent
        MSoundBankEventDTO mSoundBankEventDTO = mSoundBankEventMapper.toDto(mSoundBankEvent);
        restMSoundBankEventMockMvc.perform(post("/api/m-sound-bank-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankEventDTO)))
            .andExpect(status().isCreated());

        // Validate the MSoundBankEvent in the database
        List<MSoundBankEvent> mSoundBankEventList = mSoundBankEventRepository.findAll();
        assertThat(mSoundBankEventList).hasSize(databaseSizeBeforeCreate + 1);
        MSoundBankEvent testMSoundBankEvent = mSoundBankEventList.get(mSoundBankEventList.size() - 1);
        assertThat(testMSoundBankEvent.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testMSoundBankEvent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMSoundBankEvent.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
    }

    @Test
    @Transactional
    public void createMSoundBankEventWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mSoundBankEventRepository.findAll().size();

        // Create the MSoundBankEvent with an existing ID
        mSoundBankEvent.setId(1L);
        MSoundBankEventDTO mSoundBankEventDTO = mSoundBankEventMapper.toDto(mSoundBankEvent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMSoundBankEventMockMvc.perform(post("/api/m-sound-bank-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankEventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSoundBankEvent in the database
        List<MSoundBankEvent> mSoundBankEventList = mSoundBankEventRepository.findAll();
        assertThat(mSoundBankEventList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMSoundBankEvents() throws Exception {
        // Initialize the database
        mSoundBankEventRepository.saveAndFlush(mSoundBankEvent);

        // Get all the mSoundBankEventList
        restMSoundBankEventMockMvc.perform(get("/api/m-sound-bank-events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSoundBankEvent.getId().intValue())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getMSoundBankEvent() throws Exception {
        // Initialize the database
        mSoundBankEventRepository.saveAndFlush(mSoundBankEvent);

        // Get the mSoundBankEvent
        restMSoundBankEventMockMvc.perform(get("/api/m-sound-bank-events/{id}", mSoundBankEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mSoundBankEvent.getId().intValue()))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMSoundBankEventShouldBeFound(String filter) throws Exception {
        restMSoundBankEventMockMvc.perform(get("/api/m-sound-bank-events?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSoundBankEvent.getId().intValue())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID.toString())));

        // Check, that the count call also returns 1
        restMSoundBankEventMockMvc.perform(get("/api/m-sound-bank-events/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMSoundBankEventShouldNotBeFound(String filter) throws Exception {
        restMSoundBankEventMockMvc.perform(get("/api/m-sound-bank-events?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMSoundBankEventMockMvc.perform(get("/api/m-sound-bank-events/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMSoundBankEvent() throws Exception {
        // Get the mSoundBankEvent
        restMSoundBankEventMockMvc.perform(get("/api/m-sound-bank-events/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMSoundBankEvent() throws Exception {
        // Initialize the database
        mSoundBankEventRepository.saveAndFlush(mSoundBankEvent);

        int databaseSizeBeforeUpdate = mSoundBankEventRepository.findAll().size();

        // Update the mSoundBankEvent
        MSoundBankEvent updatedMSoundBankEvent = mSoundBankEventRepository.findById(mSoundBankEvent.getId()).get();
        // Disconnect from session so that the updates on updatedMSoundBankEvent are not directly saved in db
        em.detach(updatedMSoundBankEvent);
        updatedMSoundBankEvent
            .path(UPDATED_PATH)
            .name(UPDATED_NAME)
            .eventId(UPDATED_EVENT_ID);
        MSoundBankEventDTO mSoundBankEventDTO = mSoundBankEventMapper.toDto(updatedMSoundBankEvent);

        restMSoundBankEventMockMvc.perform(put("/api/m-sound-bank-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankEventDTO)))
            .andExpect(status().isOk());

        // Validate the MSoundBankEvent in the database
        List<MSoundBankEvent> mSoundBankEventList = mSoundBankEventRepository.findAll();
        assertThat(mSoundBankEventList).hasSize(databaseSizeBeforeUpdate);
        MSoundBankEvent testMSoundBankEvent = mSoundBankEventList.get(mSoundBankEventList.size() - 1);
        assertThat(testMSoundBankEvent.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testMSoundBankEvent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMSoundBankEvent.getEventId()).isEqualTo(UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMSoundBankEvent() throws Exception {
        int databaseSizeBeforeUpdate = mSoundBankEventRepository.findAll().size();

        // Create the MSoundBankEvent
        MSoundBankEventDTO mSoundBankEventDTO = mSoundBankEventMapper.toDto(mSoundBankEvent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMSoundBankEventMockMvc.perform(put("/api/m-sound-bank-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSoundBankEventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSoundBankEvent in the database
        List<MSoundBankEvent> mSoundBankEventList = mSoundBankEventRepository.findAll();
        assertThat(mSoundBankEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMSoundBankEvent() throws Exception {
        // Initialize the database
        mSoundBankEventRepository.saveAndFlush(mSoundBankEvent);

        int databaseSizeBeforeDelete = mSoundBankEventRepository.findAll().size();

        // Delete the mSoundBankEvent
        restMSoundBankEventMockMvc.perform(delete("/api/m-sound-bank-events/{id}", mSoundBankEvent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MSoundBankEvent> mSoundBankEventList = mSoundBankEventRepository.findAll();
        assertThat(mSoundBankEventList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSoundBankEvent.class);
        MSoundBankEvent mSoundBankEvent1 = new MSoundBankEvent();
        mSoundBankEvent1.setId(1L);
        MSoundBankEvent mSoundBankEvent2 = new MSoundBankEvent();
        mSoundBankEvent2.setId(mSoundBankEvent1.getId());
        assertThat(mSoundBankEvent1).isEqualTo(mSoundBankEvent2);
        mSoundBankEvent2.setId(2L);
        assertThat(mSoundBankEvent1).isNotEqualTo(mSoundBankEvent2);
        mSoundBankEvent1.setId(null);
        assertThat(mSoundBankEvent1).isNotEqualTo(mSoundBankEvent2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSoundBankEventDTO.class);
        MSoundBankEventDTO mSoundBankEventDTO1 = new MSoundBankEventDTO();
        mSoundBankEventDTO1.setId(1L);
        MSoundBankEventDTO mSoundBankEventDTO2 = new MSoundBankEventDTO();
        assertThat(mSoundBankEventDTO1).isNotEqualTo(mSoundBankEventDTO2);
        mSoundBankEventDTO2.setId(mSoundBankEventDTO1.getId());
        assertThat(mSoundBankEventDTO1).isEqualTo(mSoundBankEventDTO2);
        mSoundBankEventDTO2.setId(2L);
        assertThat(mSoundBankEventDTO1).isNotEqualTo(mSoundBankEventDTO2);
        mSoundBankEventDTO1.setId(null);
        assertThat(mSoundBankEventDTO1).isNotEqualTo(mSoundBankEventDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mSoundBankEventMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mSoundBankEventMapper.fromId(null)).isNull();
    }
}
