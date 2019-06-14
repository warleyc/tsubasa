package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestTicket;
import io.shm.tsubasa.repository.MQuestTicketRepository;
import io.shm.tsubasa.service.MQuestTicketService;
import io.shm.tsubasa.service.dto.MQuestTicketDTO;
import io.shm.tsubasa.service.mapper.MQuestTicketMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestTicketCriteria;
import io.shm.tsubasa.service.MQuestTicketQueryService;

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
 * Integration tests for the {@Link MQuestTicketResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestTicketResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_ASSET = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET = "BBBBBBBBBB";

    @Autowired
    private MQuestTicketRepository mQuestTicketRepository;

    @Autowired
    private MQuestTicketMapper mQuestTicketMapper;

    @Autowired
    private MQuestTicketService mQuestTicketService;

    @Autowired
    private MQuestTicketQueryService mQuestTicketQueryService;

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

    private MockMvc restMQuestTicketMockMvc;

    private MQuestTicket mQuestTicket;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestTicketResource mQuestTicketResource = new MQuestTicketResource(mQuestTicketService, mQuestTicketQueryService);
        this.restMQuestTicketMockMvc = MockMvcBuilders.standaloneSetup(mQuestTicketResource)
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
    public static MQuestTicket createEntity(EntityManager em) {
        MQuestTicket mQuestTicket = new MQuestTicket()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .thumbnailAsset(DEFAULT_THUMBNAIL_ASSET);
        return mQuestTicket;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestTicket createUpdatedEntity(EntityManager em) {
        MQuestTicket mQuestTicket = new MQuestTicket()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .thumbnailAsset(UPDATED_THUMBNAIL_ASSET);
        return mQuestTicket;
    }

    @BeforeEach
    public void initTest() {
        mQuestTicket = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestTicket() throws Exception {
        int databaseSizeBeforeCreate = mQuestTicketRepository.findAll().size();

        // Create the MQuestTicket
        MQuestTicketDTO mQuestTicketDTO = mQuestTicketMapper.toDto(mQuestTicket);
        restMQuestTicketMockMvc.perform(post("/api/m-quest-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTicketDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestTicket in the database
        List<MQuestTicket> mQuestTicketList = mQuestTicketRepository.findAll();
        assertThat(mQuestTicketList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestTicket testMQuestTicket = mQuestTicketList.get(mQuestTicketList.size() - 1);
        assertThat(testMQuestTicket.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMQuestTicket.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMQuestTicket.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMQuestTicket.getThumbnailAsset()).isEqualTo(DEFAULT_THUMBNAIL_ASSET);
    }

    @Test
    @Transactional
    public void createMQuestTicketWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestTicketRepository.findAll().size();

        // Create the MQuestTicket with an existing ID
        mQuestTicket.setId(1L);
        MQuestTicketDTO mQuestTicketDTO = mQuestTicketMapper.toDto(mQuestTicket);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestTicketMockMvc.perform(post("/api/m-quest-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTicketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestTicket in the database
        List<MQuestTicket> mQuestTicketList = mQuestTicketRepository.findAll();
        assertThat(mQuestTicketList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMQuestTickets() throws Exception {
        // Initialize the database
        mQuestTicketRepository.saveAndFlush(mQuestTicket);

        // Get all the mQuestTicketList
        restMQuestTicketMockMvc.perform(get("/api/m-quest-tickets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestTicket.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAsset").value(hasItem(DEFAULT_THUMBNAIL_ASSET.toString())));
    }
    
    @Test
    @Transactional
    public void getMQuestTicket() throws Exception {
        // Initialize the database
        mQuestTicketRepository.saveAndFlush(mQuestTicket);

        // Get the mQuestTicket
        restMQuestTicketMockMvc.perform(get("/api/m-quest-tickets/{id}", mQuestTicket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestTicket.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.thumbnailAsset").value(DEFAULT_THUMBNAIL_ASSET.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestTicketShouldBeFound(String filter) throws Exception {
        restMQuestTicketMockMvc.perform(get("/api/m-quest-tickets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestTicket.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAsset").value(hasItem(DEFAULT_THUMBNAIL_ASSET.toString())));

        // Check, that the count call also returns 1
        restMQuestTicketMockMvc.perform(get("/api/m-quest-tickets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestTicketShouldNotBeFound(String filter) throws Exception {
        restMQuestTicketMockMvc.perform(get("/api/m-quest-tickets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestTicketMockMvc.perform(get("/api/m-quest-tickets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestTicket() throws Exception {
        // Get the mQuestTicket
        restMQuestTicketMockMvc.perform(get("/api/m-quest-tickets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestTicket() throws Exception {
        // Initialize the database
        mQuestTicketRepository.saveAndFlush(mQuestTicket);

        int databaseSizeBeforeUpdate = mQuestTicketRepository.findAll().size();

        // Update the mQuestTicket
        MQuestTicket updatedMQuestTicket = mQuestTicketRepository.findById(mQuestTicket.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestTicket are not directly saved in db
        em.detach(updatedMQuestTicket);
        updatedMQuestTicket
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .thumbnailAsset(UPDATED_THUMBNAIL_ASSET);
        MQuestTicketDTO mQuestTicketDTO = mQuestTicketMapper.toDto(updatedMQuestTicket);

        restMQuestTicketMockMvc.perform(put("/api/m-quest-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTicketDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestTicket in the database
        List<MQuestTicket> mQuestTicketList = mQuestTicketRepository.findAll();
        assertThat(mQuestTicketList).hasSize(databaseSizeBeforeUpdate);
        MQuestTicket testMQuestTicket = mQuestTicketList.get(mQuestTicketList.size() - 1);
        assertThat(testMQuestTicket.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMQuestTicket.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMQuestTicket.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMQuestTicket.getThumbnailAsset()).isEqualTo(UPDATED_THUMBNAIL_ASSET);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestTicket() throws Exception {
        int databaseSizeBeforeUpdate = mQuestTicketRepository.findAll().size();

        // Create the MQuestTicket
        MQuestTicketDTO mQuestTicketDTO = mQuestTicketMapper.toDto(mQuestTicket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestTicketMockMvc.perform(put("/api/m-quest-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestTicketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestTicket in the database
        List<MQuestTicket> mQuestTicketList = mQuestTicketRepository.findAll();
        assertThat(mQuestTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestTicket() throws Exception {
        // Initialize the database
        mQuestTicketRepository.saveAndFlush(mQuestTicket);

        int databaseSizeBeforeDelete = mQuestTicketRepository.findAll().size();

        // Delete the mQuestTicket
        restMQuestTicketMockMvc.perform(delete("/api/m-quest-tickets/{id}", mQuestTicket.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestTicket> mQuestTicketList = mQuestTicketRepository.findAll();
        assertThat(mQuestTicketList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestTicket.class);
        MQuestTicket mQuestTicket1 = new MQuestTicket();
        mQuestTicket1.setId(1L);
        MQuestTicket mQuestTicket2 = new MQuestTicket();
        mQuestTicket2.setId(mQuestTicket1.getId());
        assertThat(mQuestTicket1).isEqualTo(mQuestTicket2);
        mQuestTicket2.setId(2L);
        assertThat(mQuestTicket1).isNotEqualTo(mQuestTicket2);
        mQuestTicket1.setId(null);
        assertThat(mQuestTicket1).isNotEqualTo(mQuestTicket2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestTicketDTO.class);
        MQuestTicketDTO mQuestTicketDTO1 = new MQuestTicketDTO();
        mQuestTicketDTO1.setId(1L);
        MQuestTicketDTO mQuestTicketDTO2 = new MQuestTicketDTO();
        assertThat(mQuestTicketDTO1).isNotEqualTo(mQuestTicketDTO2);
        mQuestTicketDTO2.setId(mQuestTicketDTO1.getId());
        assertThat(mQuestTicketDTO1).isEqualTo(mQuestTicketDTO2);
        mQuestTicketDTO2.setId(2L);
        assertThat(mQuestTicketDTO1).isNotEqualTo(mQuestTicketDTO2);
        mQuestTicketDTO1.setId(null);
        assertThat(mQuestTicketDTO1).isNotEqualTo(mQuestTicketDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestTicketMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestTicketMapper.fromId(null)).isNull();
    }
}
