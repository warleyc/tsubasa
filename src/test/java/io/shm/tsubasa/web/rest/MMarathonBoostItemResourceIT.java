package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MMarathonBoostItem;
import io.shm.tsubasa.repository.MMarathonBoostItemRepository;
import io.shm.tsubasa.service.MMarathonBoostItemService;
import io.shm.tsubasa.service.dto.MMarathonBoostItemDTO;
import io.shm.tsubasa.service.mapper.MMarathonBoostItemMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MMarathonBoostItemCriteria;
import io.shm.tsubasa.service.MMarathonBoostItemQueryService;

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
 * Integration tests for the {@Link MMarathonBoostItemResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MMarathonBoostItemResourceIT {

    private static final Integer DEFAULT_EVENT_ID = 1;
    private static final Integer UPDATED_EVENT_ID = 2;

    private static final Integer DEFAULT_BOOST_RATIO = 1;
    private static final Integer UPDATED_BOOST_RATIO = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MMarathonBoostItemRepository mMarathonBoostItemRepository;

    @Autowired
    private MMarathonBoostItemMapper mMarathonBoostItemMapper;

    @Autowired
    private MMarathonBoostItemService mMarathonBoostItemService;

    @Autowired
    private MMarathonBoostItemQueryService mMarathonBoostItemQueryService;

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

    private MockMvc restMMarathonBoostItemMockMvc;

    private MMarathonBoostItem mMarathonBoostItem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MMarathonBoostItemResource mMarathonBoostItemResource = new MMarathonBoostItemResource(mMarathonBoostItemService, mMarathonBoostItemQueryService);
        this.restMMarathonBoostItemMockMvc = MockMvcBuilders.standaloneSetup(mMarathonBoostItemResource)
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
    public static MMarathonBoostItem createEntity(EntityManager em) {
        MMarathonBoostItem mMarathonBoostItem = new MMarathonBoostItem()
            .eventId(DEFAULT_EVENT_ID)
            .boostRatio(DEFAULT_BOOST_RATIO)
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME)
            .description(DEFAULT_DESCRIPTION);
        return mMarathonBoostItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MMarathonBoostItem createUpdatedEntity(EntityManager em) {
        MMarathonBoostItem mMarathonBoostItem = new MMarathonBoostItem()
            .eventId(UPDATED_EVENT_ID)
            .boostRatio(UPDATED_BOOST_RATIO)
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .description(UPDATED_DESCRIPTION);
        return mMarathonBoostItem;
    }

    @BeforeEach
    public void initTest() {
        mMarathonBoostItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createMMarathonBoostItem() throws Exception {
        int databaseSizeBeforeCreate = mMarathonBoostItemRepository.findAll().size();

        // Create the MMarathonBoostItem
        MMarathonBoostItemDTO mMarathonBoostItemDTO = mMarathonBoostItemMapper.toDto(mMarathonBoostItem);
        restMMarathonBoostItemMockMvc.perform(post("/api/m-marathon-boost-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostItemDTO)))
            .andExpect(status().isCreated());

        // Validate the MMarathonBoostItem in the database
        List<MMarathonBoostItem> mMarathonBoostItemList = mMarathonBoostItemRepository.findAll();
        assertThat(mMarathonBoostItemList).hasSize(databaseSizeBeforeCreate + 1);
        MMarathonBoostItem testMMarathonBoostItem = mMarathonBoostItemList.get(mMarathonBoostItemList.size() - 1);
        assertThat(testMMarathonBoostItem.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testMMarathonBoostItem.getBoostRatio()).isEqualTo(DEFAULT_BOOST_RATIO);
        assertThat(testMMarathonBoostItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMMarathonBoostItem.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testMMarathonBoostItem.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
        assertThat(testMMarathonBoostItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMMarathonBoostItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mMarathonBoostItemRepository.findAll().size();

        // Create the MMarathonBoostItem with an existing ID
        mMarathonBoostItem.setId(1L);
        MMarathonBoostItemDTO mMarathonBoostItemDTO = mMarathonBoostItemMapper.toDto(mMarathonBoostItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMMarathonBoostItemMockMvc.perform(post("/api/m-marathon-boost-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonBoostItem in the database
        List<MMarathonBoostItem> mMarathonBoostItemList = mMarathonBoostItemRepository.findAll();
        assertThat(mMarathonBoostItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBoostRatioIsRequired() throws Exception {
        int databaseSizeBeforeTest = mMarathonBoostItemRepository.findAll().size();
        // set the field null
        mMarathonBoostItem.setBoostRatio(null);

        // Create the MMarathonBoostItem, which fails.
        MMarathonBoostItemDTO mMarathonBoostItemDTO = mMarathonBoostItemMapper.toDto(mMarathonBoostItem);

        restMMarathonBoostItemMockMvc.perform(post("/api/m-marathon-boost-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostItemDTO)))
            .andExpect(status().isBadRequest());

        List<MMarathonBoostItem> mMarathonBoostItemList = mMarathonBoostItemRepository.findAll();
        assertThat(mMarathonBoostItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItems() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList
        restMMarathonBoostItemMockMvc.perform(get("/api/m-marathon-boost-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonBoostItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].boostRatio").value(hasItem(DEFAULT_BOOST_RATIO)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getMMarathonBoostItem() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get the mMarathonBoostItem
        restMMarathonBoostItemMockMvc.perform(get("/api/m-marathon-boost-items/{id}", mMarathonBoostItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mMarathonBoostItem.getId().intValue()))
            .andExpect(jsonPath("$.eventId").value(DEFAULT_EVENT_ID))
            .andExpect(jsonPath("$.boostRatio").value(DEFAULT_BOOST_RATIO))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByEventIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where eventId equals to DEFAULT_EVENT_ID
        defaultMMarathonBoostItemShouldBeFound("eventId.equals=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonBoostItemList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonBoostItemShouldNotBeFound("eventId.equals=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByEventIdIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where eventId in DEFAULT_EVENT_ID or UPDATED_EVENT_ID
        defaultMMarathonBoostItemShouldBeFound("eventId.in=" + DEFAULT_EVENT_ID + "," + UPDATED_EVENT_ID);

        // Get all the mMarathonBoostItemList where eventId equals to UPDATED_EVENT_ID
        defaultMMarathonBoostItemShouldNotBeFound("eventId.in=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByEventIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where eventId is not null
        defaultMMarathonBoostItemShouldBeFound("eventId.specified=true");

        // Get all the mMarathonBoostItemList where eventId is null
        defaultMMarathonBoostItemShouldNotBeFound("eventId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByEventIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where eventId greater than or equals to DEFAULT_EVENT_ID
        defaultMMarathonBoostItemShouldBeFound("eventId.greaterOrEqualThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonBoostItemList where eventId greater than or equals to UPDATED_EVENT_ID
        defaultMMarathonBoostItemShouldNotBeFound("eventId.greaterOrEqualThan=" + UPDATED_EVENT_ID);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByEventIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where eventId less than or equals to DEFAULT_EVENT_ID
        defaultMMarathonBoostItemShouldNotBeFound("eventId.lessThan=" + DEFAULT_EVENT_ID);

        // Get all the mMarathonBoostItemList where eventId less than or equals to UPDATED_EVENT_ID
        defaultMMarathonBoostItemShouldBeFound("eventId.lessThan=" + UPDATED_EVENT_ID);
    }


    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByBoostRatioIsEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where boostRatio equals to DEFAULT_BOOST_RATIO
        defaultMMarathonBoostItemShouldBeFound("boostRatio.equals=" + DEFAULT_BOOST_RATIO);

        // Get all the mMarathonBoostItemList where boostRatio equals to UPDATED_BOOST_RATIO
        defaultMMarathonBoostItemShouldNotBeFound("boostRatio.equals=" + UPDATED_BOOST_RATIO);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByBoostRatioIsInShouldWork() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where boostRatio in DEFAULT_BOOST_RATIO or UPDATED_BOOST_RATIO
        defaultMMarathonBoostItemShouldBeFound("boostRatio.in=" + DEFAULT_BOOST_RATIO + "," + UPDATED_BOOST_RATIO);

        // Get all the mMarathonBoostItemList where boostRatio equals to UPDATED_BOOST_RATIO
        defaultMMarathonBoostItemShouldNotBeFound("boostRatio.in=" + UPDATED_BOOST_RATIO);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByBoostRatioIsNullOrNotNull() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where boostRatio is not null
        defaultMMarathonBoostItemShouldBeFound("boostRatio.specified=true");

        // Get all the mMarathonBoostItemList where boostRatio is null
        defaultMMarathonBoostItemShouldNotBeFound("boostRatio.specified=false");
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByBoostRatioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where boostRatio greater than or equals to DEFAULT_BOOST_RATIO
        defaultMMarathonBoostItemShouldBeFound("boostRatio.greaterOrEqualThan=" + DEFAULT_BOOST_RATIO);

        // Get all the mMarathonBoostItemList where boostRatio greater than or equals to UPDATED_BOOST_RATIO
        defaultMMarathonBoostItemShouldNotBeFound("boostRatio.greaterOrEqualThan=" + UPDATED_BOOST_RATIO);
    }

    @Test
    @Transactional
    public void getAllMMarathonBoostItemsByBoostRatioIsLessThanSomething() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        // Get all the mMarathonBoostItemList where boostRatio less than or equals to DEFAULT_BOOST_RATIO
        defaultMMarathonBoostItemShouldNotBeFound("boostRatio.lessThan=" + DEFAULT_BOOST_RATIO);

        // Get all the mMarathonBoostItemList where boostRatio less than or equals to UPDATED_BOOST_RATIO
        defaultMMarathonBoostItemShouldBeFound("boostRatio.lessThan=" + UPDATED_BOOST_RATIO);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMMarathonBoostItemShouldBeFound(String filter) throws Exception {
        restMMarathonBoostItemMockMvc.perform(get("/api/m-marathon-boost-items?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mMarathonBoostItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID)))
            .andExpect(jsonPath("$.[*].boostRatio").value(hasItem(DEFAULT_BOOST_RATIO)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restMMarathonBoostItemMockMvc.perform(get("/api/m-marathon-boost-items/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMMarathonBoostItemShouldNotBeFound(String filter) throws Exception {
        restMMarathonBoostItemMockMvc.perform(get("/api/m-marathon-boost-items?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMMarathonBoostItemMockMvc.perform(get("/api/m-marathon-boost-items/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMMarathonBoostItem() throws Exception {
        // Get the mMarathonBoostItem
        restMMarathonBoostItemMockMvc.perform(get("/api/m-marathon-boost-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMMarathonBoostItem() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        int databaseSizeBeforeUpdate = mMarathonBoostItemRepository.findAll().size();

        // Update the mMarathonBoostItem
        MMarathonBoostItem updatedMMarathonBoostItem = mMarathonBoostItemRepository.findById(mMarathonBoostItem.getId()).get();
        // Disconnect from session so that the updates on updatedMMarathonBoostItem are not directly saved in db
        em.detach(updatedMMarathonBoostItem);
        updatedMMarathonBoostItem
            .eventId(UPDATED_EVENT_ID)
            .boostRatio(UPDATED_BOOST_RATIO)
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .description(UPDATED_DESCRIPTION);
        MMarathonBoostItemDTO mMarathonBoostItemDTO = mMarathonBoostItemMapper.toDto(updatedMMarathonBoostItem);

        restMMarathonBoostItemMockMvc.perform(put("/api/m-marathon-boost-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostItemDTO)))
            .andExpect(status().isOk());

        // Validate the MMarathonBoostItem in the database
        List<MMarathonBoostItem> mMarathonBoostItemList = mMarathonBoostItemRepository.findAll();
        assertThat(mMarathonBoostItemList).hasSize(databaseSizeBeforeUpdate);
        MMarathonBoostItem testMMarathonBoostItem = mMarathonBoostItemList.get(mMarathonBoostItemList.size() - 1);
        assertThat(testMMarathonBoostItem.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testMMarathonBoostItem.getBoostRatio()).isEqualTo(UPDATED_BOOST_RATIO);
        assertThat(testMMarathonBoostItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMMarathonBoostItem.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testMMarathonBoostItem.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
        assertThat(testMMarathonBoostItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMMarathonBoostItem() throws Exception {
        int databaseSizeBeforeUpdate = mMarathonBoostItemRepository.findAll().size();

        // Create the MMarathonBoostItem
        MMarathonBoostItemDTO mMarathonBoostItemDTO = mMarathonBoostItemMapper.toDto(mMarathonBoostItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMMarathonBoostItemMockMvc.perform(put("/api/m-marathon-boost-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mMarathonBoostItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MMarathonBoostItem in the database
        List<MMarathonBoostItem> mMarathonBoostItemList = mMarathonBoostItemRepository.findAll();
        assertThat(mMarathonBoostItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMMarathonBoostItem() throws Exception {
        // Initialize the database
        mMarathonBoostItemRepository.saveAndFlush(mMarathonBoostItem);

        int databaseSizeBeforeDelete = mMarathonBoostItemRepository.findAll().size();

        // Delete the mMarathonBoostItem
        restMMarathonBoostItemMockMvc.perform(delete("/api/m-marathon-boost-items/{id}", mMarathonBoostItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MMarathonBoostItem> mMarathonBoostItemList = mMarathonBoostItemRepository.findAll();
        assertThat(mMarathonBoostItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonBoostItem.class);
        MMarathonBoostItem mMarathonBoostItem1 = new MMarathonBoostItem();
        mMarathonBoostItem1.setId(1L);
        MMarathonBoostItem mMarathonBoostItem2 = new MMarathonBoostItem();
        mMarathonBoostItem2.setId(mMarathonBoostItem1.getId());
        assertThat(mMarathonBoostItem1).isEqualTo(mMarathonBoostItem2);
        mMarathonBoostItem2.setId(2L);
        assertThat(mMarathonBoostItem1).isNotEqualTo(mMarathonBoostItem2);
        mMarathonBoostItem1.setId(null);
        assertThat(mMarathonBoostItem1).isNotEqualTo(mMarathonBoostItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MMarathonBoostItemDTO.class);
        MMarathonBoostItemDTO mMarathonBoostItemDTO1 = new MMarathonBoostItemDTO();
        mMarathonBoostItemDTO1.setId(1L);
        MMarathonBoostItemDTO mMarathonBoostItemDTO2 = new MMarathonBoostItemDTO();
        assertThat(mMarathonBoostItemDTO1).isNotEqualTo(mMarathonBoostItemDTO2);
        mMarathonBoostItemDTO2.setId(mMarathonBoostItemDTO1.getId());
        assertThat(mMarathonBoostItemDTO1).isEqualTo(mMarathonBoostItemDTO2);
        mMarathonBoostItemDTO2.setId(2L);
        assertThat(mMarathonBoostItemDTO1).isNotEqualTo(mMarathonBoostItemDTO2);
        mMarathonBoostItemDTO1.setId(null);
        assertThat(mMarathonBoostItemDTO1).isNotEqualTo(mMarathonBoostItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mMarathonBoostItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mMarathonBoostItemMapper.fromId(null)).isNull();
    }
}
