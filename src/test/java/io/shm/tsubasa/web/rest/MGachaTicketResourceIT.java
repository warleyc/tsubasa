package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaTicket;
import io.shm.tsubasa.repository.MGachaTicketRepository;
import io.shm.tsubasa.service.MGachaTicketService;
import io.shm.tsubasa.service.dto.MGachaTicketDTO;
import io.shm.tsubasa.service.mapper.MGachaTicketMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaTicketCriteria;
import io.shm.tsubasa.service.MGachaTicketQueryService;

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
 * Integration tests for the {@Link MGachaTicketResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaTicketResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_AMOUNT = 1;
    private static final Integer UPDATED_MAX_AMOUNT = 2;

    private static final String DEFAULT_THUMBNAIL_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    @Autowired
    private MGachaTicketRepository mGachaTicketRepository;

    @Autowired
    private MGachaTicketMapper mGachaTicketMapper;

    @Autowired
    private MGachaTicketService mGachaTicketService;

    @Autowired
    private MGachaTicketQueryService mGachaTicketQueryService;

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

    private MockMvc restMGachaTicketMockMvc;

    private MGachaTicket mGachaTicket;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaTicketResource mGachaTicketResource = new MGachaTicketResource(mGachaTicketService, mGachaTicketQueryService);
        this.restMGachaTicketMockMvc = MockMvcBuilders.standaloneSetup(mGachaTicketResource)
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
    public static MGachaTicket createEntity(EntityManager em) {
        MGachaTicket mGachaTicket = new MGachaTicket()
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .maxAmount(DEFAULT_MAX_AMOUNT)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME)
            .endAt(DEFAULT_END_AT);
        return mGachaTicket;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaTicket createUpdatedEntity(EntityManager em) {
        MGachaTicket mGachaTicket = new MGachaTicket()
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .maxAmount(UPDATED_MAX_AMOUNT)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .endAt(UPDATED_END_AT);
        return mGachaTicket;
    }

    @BeforeEach
    public void initTest() {
        mGachaTicket = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaTicket() throws Exception {
        int databaseSizeBeforeCreate = mGachaTicketRepository.findAll().size();

        // Create the MGachaTicket
        MGachaTicketDTO mGachaTicketDTO = mGachaTicketMapper.toDto(mGachaTicket);
        restMGachaTicketMockMvc.perform(post("/api/m-gacha-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaTicketDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaTicket in the database
        List<MGachaTicket> mGachaTicketList = mGachaTicketRepository.findAll();
        assertThat(mGachaTicketList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaTicket testMGachaTicket = mGachaTicketList.get(mGachaTicketList.size() - 1);
        assertThat(testMGachaTicket.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMGachaTicket.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMGachaTicket.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMGachaTicket.getMaxAmount()).isEqualTo(DEFAULT_MAX_AMOUNT);
        assertThat(testMGachaTicket.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
        assertThat(testMGachaTicket.getEndAt()).isEqualTo(DEFAULT_END_AT);
    }

    @Test
    @Transactional
    public void createMGachaTicketWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaTicketRepository.findAll().size();

        // Create the MGachaTicket with an existing ID
        mGachaTicket.setId(1L);
        MGachaTicketDTO mGachaTicketDTO = mGachaTicketMapper.toDto(mGachaTicket);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaTicketMockMvc.perform(post("/api/m-gacha-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaTicketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaTicket in the database
        List<MGachaTicket> mGachaTicketList = mGachaTicketRepository.findAll();
        assertThat(mGachaTicketList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaxAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaTicketRepository.findAll().size();
        // set the field null
        mGachaTicket.setMaxAmount(null);

        // Create the MGachaTicket, which fails.
        MGachaTicketDTO mGachaTicketDTO = mGachaTicketMapper.toDto(mGachaTicket);

        restMGachaTicketMockMvc.perform(post("/api/m-gacha-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaTicketDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaTicket> mGachaTicketList = mGachaTicketRepository.findAll();
        assertThat(mGachaTicketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mGachaTicketRepository.findAll().size();
        // set the field null
        mGachaTicket.setEndAt(null);

        // Create the MGachaTicket, which fails.
        MGachaTicketDTO mGachaTicketDTO = mGachaTicketMapper.toDto(mGachaTicket);

        restMGachaTicketMockMvc.perform(post("/api/m-gacha-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaTicketDTO)))
            .andExpect(status().isBadRequest());

        List<MGachaTicket> mGachaTicketList = mGachaTicketRepository.findAll();
        assertThat(mGachaTicketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMGachaTickets() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList
        restMGachaTicketMockMvc.perform(get("/api/m-gacha-tickets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaTicket.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].maxAmount").value(hasItem(DEFAULT_MAX_AMOUNT)))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));
    }
    
    @Test
    @Transactional
    public void getMGachaTicket() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get the mGachaTicket
        restMGachaTicketMockMvc.perform(get("/api/m-gacha-tickets/{id}", mGachaTicket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaTicket.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.maxAmount").value(DEFAULT_MAX_AMOUNT))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT));
    }

    @Test
    @Transactional
    public void getAllMGachaTicketsByMaxAmountIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where maxAmount equals to DEFAULT_MAX_AMOUNT
        defaultMGachaTicketShouldBeFound("maxAmount.equals=" + DEFAULT_MAX_AMOUNT);

        // Get all the mGachaTicketList where maxAmount equals to UPDATED_MAX_AMOUNT
        defaultMGachaTicketShouldNotBeFound("maxAmount.equals=" + UPDATED_MAX_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGachaTicketsByMaxAmountIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where maxAmount in DEFAULT_MAX_AMOUNT or UPDATED_MAX_AMOUNT
        defaultMGachaTicketShouldBeFound("maxAmount.in=" + DEFAULT_MAX_AMOUNT + "," + UPDATED_MAX_AMOUNT);

        // Get all the mGachaTicketList where maxAmount equals to UPDATED_MAX_AMOUNT
        defaultMGachaTicketShouldNotBeFound("maxAmount.in=" + UPDATED_MAX_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGachaTicketsByMaxAmountIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where maxAmount is not null
        defaultMGachaTicketShouldBeFound("maxAmount.specified=true");

        // Get all the mGachaTicketList where maxAmount is null
        defaultMGachaTicketShouldNotBeFound("maxAmount.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaTicketsByMaxAmountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where maxAmount greater than or equals to DEFAULT_MAX_AMOUNT
        defaultMGachaTicketShouldBeFound("maxAmount.greaterOrEqualThan=" + DEFAULT_MAX_AMOUNT);

        // Get all the mGachaTicketList where maxAmount greater than or equals to UPDATED_MAX_AMOUNT
        defaultMGachaTicketShouldNotBeFound("maxAmount.greaterOrEqualThan=" + UPDATED_MAX_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllMGachaTicketsByMaxAmountIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where maxAmount less than or equals to DEFAULT_MAX_AMOUNT
        defaultMGachaTicketShouldNotBeFound("maxAmount.lessThan=" + DEFAULT_MAX_AMOUNT);

        // Get all the mGachaTicketList where maxAmount less than or equals to UPDATED_MAX_AMOUNT
        defaultMGachaTicketShouldBeFound("maxAmount.lessThan=" + UPDATED_MAX_AMOUNT);
    }


    @Test
    @Transactional
    public void getAllMGachaTicketsByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where endAt equals to DEFAULT_END_AT
        defaultMGachaTicketShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mGachaTicketList where endAt equals to UPDATED_END_AT
        defaultMGachaTicketShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMGachaTicketsByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMGachaTicketShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mGachaTicketList where endAt equals to UPDATED_END_AT
        defaultMGachaTicketShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMGachaTicketsByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where endAt is not null
        defaultMGachaTicketShouldBeFound("endAt.specified=true");

        // Get all the mGachaTicketList where endAt is null
        defaultMGachaTicketShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMGachaTicketsByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where endAt greater than or equals to DEFAULT_END_AT
        defaultMGachaTicketShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mGachaTicketList where endAt greater than or equals to UPDATED_END_AT
        defaultMGachaTicketShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMGachaTicketsByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        // Get all the mGachaTicketList where endAt less than or equals to DEFAULT_END_AT
        defaultMGachaTicketShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mGachaTicketList where endAt less than or equals to UPDATED_END_AT
        defaultMGachaTicketShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaTicketShouldBeFound(String filter) throws Exception {
        restMGachaTicketMockMvc.perform(get("/api/m-gacha-tickets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaTicket.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].maxAmount").value(hasItem(DEFAULT_MAX_AMOUNT)))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));

        // Check, that the count call also returns 1
        restMGachaTicketMockMvc.perform(get("/api/m-gacha-tickets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaTicketShouldNotBeFound(String filter) throws Exception {
        restMGachaTicketMockMvc.perform(get("/api/m-gacha-tickets?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaTicketMockMvc.perform(get("/api/m-gacha-tickets/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaTicket() throws Exception {
        // Get the mGachaTicket
        restMGachaTicketMockMvc.perform(get("/api/m-gacha-tickets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaTicket() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        int databaseSizeBeforeUpdate = mGachaTicketRepository.findAll().size();

        // Update the mGachaTicket
        MGachaTicket updatedMGachaTicket = mGachaTicketRepository.findById(mGachaTicket.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaTicket are not directly saved in db
        em.detach(updatedMGachaTicket);
        updatedMGachaTicket
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .description(UPDATED_DESCRIPTION)
            .maxAmount(UPDATED_MAX_AMOUNT)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .endAt(UPDATED_END_AT);
        MGachaTicketDTO mGachaTicketDTO = mGachaTicketMapper.toDto(updatedMGachaTicket);

        restMGachaTicketMockMvc.perform(put("/api/m-gacha-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaTicketDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaTicket in the database
        List<MGachaTicket> mGachaTicketList = mGachaTicketRepository.findAll();
        assertThat(mGachaTicketList).hasSize(databaseSizeBeforeUpdate);
        MGachaTicket testMGachaTicket = mGachaTicketList.get(mGachaTicketList.size() - 1);
        assertThat(testMGachaTicket.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMGachaTicket.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMGachaTicket.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMGachaTicket.getMaxAmount()).isEqualTo(UPDATED_MAX_AMOUNT);
        assertThat(testMGachaTicket.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
        assertThat(testMGachaTicket.getEndAt()).isEqualTo(UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaTicket() throws Exception {
        int databaseSizeBeforeUpdate = mGachaTicketRepository.findAll().size();

        // Create the MGachaTicket
        MGachaTicketDTO mGachaTicketDTO = mGachaTicketMapper.toDto(mGachaTicket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaTicketMockMvc.perform(put("/api/m-gacha-tickets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaTicketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaTicket in the database
        List<MGachaTicket> mGachaTicketList = mGachaTicketRepository.findAll();
        assertThat(mGachaTicketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaTicket() throws Exception {
        // Initialize the database
        mGachaTicketRepository.saveAndFlush(mGachaTicket);

        int databaseSizeBeforeDelete = mGachaTicketRepository.findAll().size();

        // Delete the mGachaTicket
        restMGachaTicketMockMvc.perform(delete("/api/m-gacha-tickets/{id}", mGachaTicket.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaTicket> mGachaTicketList = mGachaTicketRepository.findAll();
        assertThat(mGachaTicketList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaTicket.class);
        MGachaTicket mGachaTicket1 = new MGachaTicket();
        mGachaTicket1.setId(1L);
        MGachaTicket mGachaTicket2 = new MGachaTicket();
        mGachaTicket2.setId(mGachaTicket1.getId());
        assertThat(mGachaTicket1).isEqualTo(mGachaTicket2);
        mGachaTicket2.setId(2L);
        assertThat(mGachaTicket1).isNotEqualTo(mGachaTicket2);
        mGachaTicket1.setId(null);
        assertThat(mGachaTicket1).isNotEqualTo(mGachaTicket2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaTicketDTO.class);
        MGachaTicketDTO mGachaTicketDTO1 = new MGachaTicketDTO();
        mGachaTicketDTO1.setId(1L);
        MGachaTicketDTO mGachaTicketDTO2 = new MGachaTicketDTO();
        assertThat(mGachaTicketDTO1).isNotEqualTo(mGachaTicketDTO2);
        mGachaTicketDTO2.setId(mGachaTicketDTO1.getId());
        assertThat(mGachaTicketDTO1).isEqualTo(mGachaTicketDTO2);
        mGachaTicketDTO2.setId(2L);
        assertThat(mGachaTicketDTO1).isNotEqualTo(mGachaTicketDTO2);
        mGachaTicketDTO1.setId(null);
        assertThat(mGachaTicketDTO1).isNotEqualTo(mGachaTicketDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaTicketMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaTicketMapper.fromId(null)).isNull();
    }
}
