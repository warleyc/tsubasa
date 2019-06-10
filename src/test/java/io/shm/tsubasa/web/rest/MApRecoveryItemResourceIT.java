package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MApRecoveryItem;
import io.shm.tsubasa.repository.MApRecoveryItemRepository;
import io.shm.tsubasa.service.MApRecoveryItemService;
import io.shm.tsubasa.service.dto.MApRecoveryItemDTO;
import io.shm.tsubasa.service.mapper.MApRecoveryItemMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MApRecoveryItemCriteria;
import io.shm.tsubasa.service.MApRecoveryItemQueryService;

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
 * Integration tests for the {@Link MApRecoveryItemResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MApRecoveryItemResourceIT {

    private static final Integer DEFAULT_AP_RECOVERY_ITEM_TYPE = 1;
    private static final Integer UPDATED_AP_RECOVERY_ITEM_TYPE = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET_NAME = "BBBBBBBBBB";

    @Autowired
    private MApRecoveryItemRepository mApRecoveryItemRepository;

    @Autowired
    private MApRecoveryItemMapper mApRecoveryItemMapper;

    @Autowired
    private MApRecoveryItemService mApRecoveryItemService;

    @Autowired
    private MApRecoveryItemQueryService mApRecoveryItemQueryService;

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

    private MockMvc restMApRecoveryItemMockMvc;

    private MApRecoveryItem mApRecoveryItem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MApRecoveryItemResource mApRecoveryItemResource = new MApRecoveryItemResource(mApRecoveryItemService, mApRecoveryItemQueryService);
        this.restMApRecoveryItemMockMvc = MockMvcBuilders.standaloneSetup(mApRecoveryItemResource)
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
    public static MApRecoveryItem createEntity(EntityManager em) {
        MApRecoveryItem mApRecoveryItem = new MApRecoveryItem()
            .apRecoveryItemType(DEFAULT_AP_RECOVERY_ITEM_TYPE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME);
        return mApRecoveryItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MApRecoveryItem createUpdatedEntity(EntityManager em) {
        MApRecoveryItem mApRecoveryItem = new MApRecoveryItem()
            .apRecoveryItemType(UPDATED_AP_RECOVERY_ITEM_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME);
        return mApRecoveryItem;
    }

    @BeforeEach
    public void initTest() {
        mApRecoveryItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createMApRecoveryItem() throws Exception {
        int databaseSizeBeforeCreate = mApRecoveryItemRepository.findAll().size();

        // Create the MApRecoveryItem
        MApRecoveryItemDTO mApRecoveryItemDTO = mApRecoveryItemMapper.toDto(mApRecoveryItem);
        restMApRecoveryItemMockMvc.perform(post("/api/m-ap-recovery-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mApRecoveryItemDTO)))
            .andExpect(status().isCreated());

        // Validate the MApRecoveryItem in the database
        List<MApRecoveryItem> mApRecoveryItemList = mApRecoveryItemRepository.findAll();
        assertThat(mApRecoveryItemList).hasSize(databaseSizeBeforeCreate + 1);
        MApRecoveryItem testMApRecoveryItem = mApRecoveryItemList.get(mApRecoveryItemList.size() - 1);
        assertThat(testMApRecoveryItem.getApRecoveryItemType()).isEqualTo(DEFAULT_AP_RECOVERY_ITEM_TYPE);
        assertThat(testMApRecoveryItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMApRecoveryItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMApRecoveryItem.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
    }

    @Test
    @Transactional
    public void createMApRecoveryItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mApRecoveryItemRepository.findAll().size();

        // Create the MApRecoveryItem with an existing ID
        mApRecoveryItem.setId(1L);
        MApRecoveryItemDTO mApRecoveryItemDTO = mApRecoveryItemMapper.toDto(mApRecoveryItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMApRecoveryItemMockMvc.perform(post("/api/m-ap-recovery-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mApRecoveryItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MApRecoveryItem in the database
        List<MApRecoveryItem> mApRecoveryItemList = mApRecoveryItemRepository.findAll();
        assertThat(mApRecoveryItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkApRecoveryItemTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mApRecoveryItemRepository.findAll().size();
        // set the field null
        mApRecoveryItem.setApRecoveryItemType(null);

        // Create the MApRecoveryItem, which fails.
        MApRecoveryItemDTO mApRecoveryItemDTO = mApRecoveryItemMapper.toDto(mApRecoveryItem);

        restMApRecoveryItemMockMvc.perform(post("/api/m-ap-recovery-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mApRecoveryItemDTO)))
            .andExpect(status().isBadRequest());

        List<MApRecoveryItem> mApRecoveryItemList = mApRecoveryItemRepository.findAll();
        assertThat(mApRecoveryItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMApRecoveryItems() throws Exception {
        // Initialize the database
        mApRecoveryItemRepository.saveAndFlush(mApRecoveryItem);

        // Get all the mApRecoveryItemList
        restMApRecoveryItemMockMvc.perform(get("/api/m-ap-recovery-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mApRecoveryItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].apRecoveryItemType").value(hasItem(DEFAULT_AP_RECOVERY_ITEM_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMApRecoveryItem() throws Exception {
        // Initialize the database
        mApRecoveryItemRepository.saveAndFlush(mApRecoveryItem);

        // Get the mApRecoveryItem
        restMApRecoveryItemMockMvc.perform(get("/api/m-ap-recovery-items/{id}", mApRecoveryItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mApRecoveryItem.getId().intValue()))
            .andExpect(jsonPath("$.apRecoveryItemType").value(DEFAULT_AP_RECOVERY_ITEM_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMApRecoveryItemsByApRecoveryItemTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mApRecoveryItemRepository.saveAndFlush(mApRecoveryItem);

        // Get all the mApRecoveryItemList where apRecoveryItemType equals to DEFAULT_AP_RECOVERY_ITEM_TYPE
        defaultMApRecoveryItemShouldBeFound("apRecoveryItemType.equals=" + DEFAULT_AP_RECOVERY_ITEM_TYPE);

        // Get all the mApRecoveryItemList where apRecoveryItemType equals to UPDATED_AP_RECOVERY_ITEM_TYPE
        defaultMApRecoveryItemShouldNotBeFound("apRecoveryItemType.equals=" + UPDATED_AP_RECOVERY_ITEM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMApRecoveryItemsByApRecoveryItemTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mApRecoveryItemRepository.saveAndFlush(mApRecoveryItem);

        // Get all the mApRecoveryItemList where apRecoveryItemType in DEFAULT_AP_RECOVERY_ITEM_TYPE or UPDATED_AP_RECOVERY_ITEM_TYPE
        defaultMApRecoveryItemShouldBeFound("apRecoveryItemType.in=" + DEFAULT_AP_RECOVERY_ITEM_TYPE + "," + UPDATED_AP_RECOVERY_ITEM_TYPE);

        // Get all the mApRecoveryItemList where apRecoveryItemType equals to UPDATED_AP_RECOVERY_ITEM_TYPE
        defaultMApRecoveryItemShouldNotBeFound("apRecoveryItemType.in=" + UPDATED_AP_RECOVERY_ITEM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMApRecoveryItemsByApRecoveryItemTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mApRecoveryItemRepository.saveAndFlush(mApRecoveryItem);

        // Get all the mApRecoveryItemList where apRecoveryItemType is not null
        defaultMApRecoveryItemShouldBeFound("apRecoveryItemType.specified=true");

        // Get all the mApRecoveryItemList where apRecoveryItemType is null
        defaultMApRecoveryItemShouldNotBeFound("apRecoveryItemType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMApRecoveryItemsByApRecoveryItemTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mApRecoveryItemRepository.saveAndFlush(mApRecoveryItem);

        // Get all the mApRecoveryItemList where apRecoveryItemType greater than or equals to DEFAULT_AP_RECOVERY_ITEM_TYPE
        defaultMApRecoveryItemShouldBeFound("apRecoveryItemType.greaterOrEqualThan=" + DEFAULT_AP_RECOVERY_ITEM_TYPE);

        // Get all the mApRecoveryItemList where apRecoveryItemType greater than or equals to UPDATED_AP_RECOVERY_ITEM_TYPE
        defaultMApRecoveryItemShouldNotBeFound("apRecoveryItemType.greaterOrEqualThan=" + UPDATED_AP_RECOVERY_ITEM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMApRecoveryItemsByApRecoveryItemTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mApRecoveryItemRepository.saveAndFlush(mApRecoveryItem);

        // Get all the mApRecoveryItemList where apRecoveryItemType less than or equals to DEFAULT_AP_RECOVERY_ITEM_TYPE
        defaultMApRecoveryItemShouldNotBeFound("apRecoveryItemType.lessThan=" + DEFAULT_AP_RECOVERY_ITEM_TYPE);

        // Get all the mApRecoveryItemList where apRecoveryItemType less than or equals to UPDATED_AP_RECOVERY_ITEM_TYPE
        defaultMApRecoveryItemShouldBeFound("apRecoveryItemType.lessThan=" + UPDATED_AP_RECOVERY_ITEM_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMApRecoveryItemShouldBeFound(String filter) throws Exception {
        restMApRecoveryItemMockMvc.perform(get("/api/m-ap-recovery-items?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mApRecoveryItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].apRecoveryItemType").value(hasItem(DEFAULT_AP_RECOVERY_ITEM_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())));

        // Check, that the count call also returns 1
        restMApRecoveryItemMockMvc.perform(get("/api/m-ap-recovery-items/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMApRecoveryItemShouldNotBeFound(String filter) throws Exception {
        restMApRecoveryItemMockMvc.perform(get("/api/m-ap-recovery-items?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMApRecoveryItemMockMvc.perform(get("/api/m-ap-recovery-items/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMApRecoveryItem() throws Exception {
        // Get the mApRecoveryItem
        restMApRecoveryItemMockMvc.perform(get("/api/m-ap-recovery-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMApRecoveryItem() throws Exception {
        // Initialize the database
        mApRecoveryItemRepository.saveAndFlush(mApRecoveryItem);

        int databaseSizeBeforeUpdate = mApRecoveryItemRepository.findAll().size();

        // Update the mApRecoveryItem
        MApRecoveryItem updatedMApRecoveryItem = mApRecoveryItemRepository.findById(mApRecoveryItem.getId()).get();
        // Disconnect from session so that the updates on updatedMApRecoveryItem are not directly saved in db
        em.detach(updatedMApRecoveryItem);
        updatedMApRecoveryItem
            .apRecoveryItemType(UPDATED_AP_RECOVERY_ITEM_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME);
        MApRecoveryItemDTO mApRecoveryItemDTO = mApRecoveryItemMapper.toDto(updatedMApRecoveryItem);

        restMApRecoveryItemMockMvc.perform(put("/api/m-ap-recovery-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mApRecoveryItemDTO)))
            .andExpect(status().isOk());

        // Validate the MApRecoveryItem in the database
        List<MApRecoveryItem> mApRecoveryItemList = mApRecoveryItemRepository.findAll();
        assertThat(mApRecoveryItemList).hasSize(databaseSizeBeforeUpdate);
        MApRecoveryItem testMApRecoveryItem = mApRecoveryItemList.get(mApRecoveryItemList.size() - 1);
        assertThat(testMApRecoveryItem.getApRecoveryItemType()).isEqualTo(UPDATED_AP_RECOVERY_ITEM_TYPE);
        assertThat(testMApRecoveryItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMApRecoveryItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMApRecoveryItem.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMApRecoveryItem() throws Exception {
        int databaseSizeBeforeUpdate = mApRecoveryItemRepository.findAll().size();

        // Create the MApRecoveryItem
        MApRecoveryItemDTO mApRecoveryItemDTO = mApRecoveryItemMapper.toDto(mApRecoveryItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMApRecoveryItemMockMvc.perform(put("/api/m-ap-recovery-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mApRecoveryItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MApRecoveryItem in the database
        List<MApRecoveryItem> mApRecoveryItemList = mApRecoveryItemRepository.findAll();
        assertThat(mApRecoveryItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMApRecoveryItem() throws Exception {
        // Initialize the database
        mApRecoveryItemRepository.saveAndFlush(mApRecoveryItem);

        int databaseSizeBeforeDelete = mApRecoveryItemRepository.findAll().size();

        // Delete the mApRecoveryItem
        restMApRecoveryItemMockMvc.perform(delete("/api/m-ap-recovery-items/{id}", mApRecoveryItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MApRecoveryItem> mApRecoveryItemList = mApRecoveryItemRepository.findAll();
        assertThat(mApRecoveryItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MApRecoveryItem.class);
        MApRecoveryItem mApRecoveryItem1 = new MApRecoveryItem();
        mApRecoveryItem1.setId(1L);
        MApRecoveryItem mApRecoveryItem2 = new MApRecoveryItem();
        mApRecoveryItem2.setId(mApRecoveryItem1.getId());
        assertThat(mApRecoveryItem1).isEqualTo(mApRecoveryItem2);
        mApRecoveryItem2.setId(2L);
        assertThat(mApRecoveryItem1).isNotEqualTo(mApRecoveryItem2);
        mApRecoveryItem1.setId(null);
        assertThat(mApRecoveryItem1).isNotEqualTo(mApRecoveryItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MApRecoveryItemDTO.class);
        MApRecoveryItemDTO mApRecoveryItemDTO1 = new MApRecoveryItemDTO();
        mApRecoveryItemDTO1.setId(1L);
        MApRecoveryItemDTO mApRecoveryItemDTO2 = new MApRecoveryItemDTO();
        assertThat(mApRecoveryItemDTO1).isNotEqualTo(mApRecoveryItemDTO2);
        mApRecoveryItemDTO2.setId(mApRecoveryItemDTO1.getId());
        assertThat(mApRecoveryItemDTO1).isEqualTo(mApRecoveryItemDTO2);
        mApRecoveryItemDTO2.setId(2L);
        assertThat(mApRecoveryItemDTO1).isNotEqualTo(mApRecoveryItemDTO2);
        mApRecoveryItemDTO1.setId(null);
        assertThat(mApRecoveryItemDTO1).isNotEqualTo(mApRecoveryItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mApRecoveryItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mApRecoveryItemMapper.fromId(null)).isNull();
    }
}
