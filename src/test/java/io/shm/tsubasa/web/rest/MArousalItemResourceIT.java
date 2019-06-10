package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MArousalItem;
import io.shm.tsubasa.repository.MArousalItemRepository;
import io.shm.tsubasa.service.MArousalItemService;
import io.shm.tsubasa.service.dto.MArousalItemDTO;
import io.shm.tsubasa.service.mapper.MArousalItemMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MArousalItemCriteria;
import io.shm.tsubasa.service.MArousalItemQueryService;

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
 * Integration tests for the {@Link MArousalItemResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MArousalItemResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_BG_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_BG_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_FRAME_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_FRAME_ASSET_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_AROUSAL_ITEM_TYPE = 1;
    private static final Integer UPDATED_AROUSAL_ITEM_TYPE = 2;

    @Autowired
    private MArousalItemRepository mArousalItemRepository;

    @Autowired
    private MArousalItemMapper mArousalItemMapper;

    @Autowired
    private MArousalItemService mArousalItemService;

    @Autowired
    private MArousalItemQueryService mArousalItemQueryService;

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

    private MockMvc restMArousalItemMockMvc;

    private MArousalItem mArousalItem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MArousalItemResource mArousalItemResource = new MArousalItemResource(mArousalItemService, mArousalItemQueryService);
        this.restMArousalItemMockMvc = MockMvcBuilders.standaloneSetup(mArousalItemResource)
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
    public static MArousalItem createEntity(EntityManager em) {
        MArousalItem mArousalItem = new MArousalItem()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME)
            .thumbnailBgAssetName(DEFAULT_THUMBNAIL_BG_ASSET_NAME)
            .thumbnailFrameAssetName(DEFAULT_THUMBNAIL_FRAME_ASSET_NAME)
            .arousalItemType(DEFAULT_AROUSAL_ITEM_TYPE);
        return mArousalItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MArousalItem createUpdatedEntity(EntityManager em) {
        MArousalItem mArousalItem = new MArousalItem()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .thumbnailBgAssetName(UPDATED_THUMBNAIL_BG_ASSET_NAME)
            .thumbnailFrameAssetName(UPDATED_THUMBNAIL_FRAME_ASSET_NAME)
            .arousalItemType(UPDATED_AROUSAL_ITEM_TYPE);
        return mArousalItem;
    }

    @BeforeEach
    public void initTest() {
        mArousalItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createMArousalItem() throws Exception {
        int databaseSizeBeforeCreate = mArousalItemRepository.findAll().size();

        // Create the MArousalItem
        MArousalItemDTO mArousalItemDTO = mArousalItemMapper.toDto(mArousalItem);
        restMArousalItemMockMvc.perform(post("/api/m-arousal-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalItemDTO)))
            .andExpect(status().isCreated());

        // Validate the MArousalItem in the database
        List<MArousalItem> mArousalItemList = mArousalItemRepository.findAll();
        assertThat(mArousalItemList).hasSize(databaseSizeBeforeCreate + 1);
        MArousalItem testMArousalItem = mArousalItemList.get(mArousalItemList.size() - 1);
        assertThat(testMArousalItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMArousalItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMArousalItem.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
        assertThat(testMArousalItem.getThumbnailBgAssetName()).isEqualTo(DEFAULT_THUMBNAIL_BG_ASSET_NAME);
        assertThat(testMArousalItem.getThumbnailFrameAssetName()).isEqualTo(DEFAULT_THUMBNAIL_FRAME_ASSET_NAME);
        assertThat(testMArousalItem.getArousalItemType()).isEqualTo(DEFAULT_AROUSAL_ITEM_TYPE);
    }

    @Test
    @Transactional
    public void createMArousalItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mArousalItemRepository.findAll().size();

        // Create the MArousalItem with an existing ID
        mArousalItem.setId(1L);
        MArousalItemDTO mArousalItemDTO = mArousalItemMapper.toDto(mArousalItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMArousalItemMockMvc.perform(post("/api/m-arousal-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MArousalItem in the database
        List<MArousalItem> mArousalItemList = mArousalItemRepository.findAll();
        assertThat(mArousalItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkArousalItemTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mArousalItemRepository.findAll().size();
        // set the field null
        mArousalItem.setArousalItemType(null);

        // Create the MArousalItem, which fails.
        MArousalItemDTO mArousalItemDTO = mArousalItemMapper.toDto(mArousalItem);

        restMArousalItemMockMvc.perform(post("/api/m-arousal-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalItemDTO)))
            .andExpect(status().isBadRequest());

        List<MArousalItem> mArousalItemList = mArousalItemRepository.findAll();
        assertThat(mArousalItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMArousalItems() throws Exception {
        // Initialize the database
        mArousalItemRepository.saveAndFlush(mArousalItem);

        // Get all the mArousalItemList
        restMArousalItemMockMvc.perform(get("/api/m-arousal-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mArousalItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailBgAssetName").value(hasItem(DEFAULT_THUMBNAIL_BG_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailFrameAssetName").value(hasItem(DEFAULT_THUMBNAIL_FRAME_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].arousalItemType").value(hasItem(DEFAULT_AROUSAL_ITEM_TYPE)));
    }
    
    @Test
    @Transactional
    public void getMArousalItem() throws Exception {
        // Initialize the database
        mArousalItemRepository.saveAndFlush(mArousalItem);

        // Get the mArousalItem
        restMArousalItemMockMvc.perform(get("/api/m-arousal-items/{id}", mArousalItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mArousalItem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.thumbnailBgAssetName").value(DEFAULT_THUMBNAIL_BG_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.thumbnailFrameAssetName").value(DEFAULT_THUMBNAIL_FRAME_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.arousalItemType").value(DEFAULT_AROUSAL_ITEM_TYPE));
    }

    @Test
    @Transactional
    public void getAllMArousalItemsByArousalItemTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mArousalItemRepository.saveAndFlush(mArousalItem);

        // Get all the mArousalItemList where arousalItemType equals to DEFAULT_AROUSAL_ITEM_TYPE
        defaultMArousalItemShouldBeFound("arousalItemType.equals=" + DEFAULT_AROUSAL_ITEM_TYPE);

        // Get all the mArousalItemList where arousalItemType equals to UPDATED_AROUSAL_ITEM_TYPE
        defaultMArousalItemShouldNotBeFound("arousalItemType.equals=" + UPDATED_AROUSAL_ITEM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMArousalItemsByArousalItemTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mArousalItemRepository.saveAndFlush(mArousalItem);

        // Get all the mArousalItemList where arousalItemType in DEFAULT_AROUSAL_ITEM_TYPE or UPDATED_AROUSAL_ITEM_TYPE
        defaultMArousalItemShouldBeFound("arousalItemType.in=" + DEFAULT_AROUSAL_ITEM_TYPE + "," + UPDATED_AROUSAL_ITEM_TYPE);

        // Get all the mArousalItemList where arousalItemType equals to UPDATED_AROUSAL_ITEM_TYPE
        defaultMArousalItemShouldNotBeFound("arousalItemType.in=" + UPDATED_AROUSAL_ITEM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMArousalItemsByArousalItemTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mArousalItemRepository.saveAndFlush(mArousalItem);

        // Get all the mArousalItemList where arousalItemType is not null
        defaultMArousalItemShouldBeFound("arousalItemType.specified=true");

        // Get all the mArousalItemList where arousalItemType is null
        defaultMArousalItemShouldNotBeFound("arousalItemType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMArousalItemsByArousalItemTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mArousalItemRepository.saveAndFlush(mArousalItem);

        // Get all the mArousalItemList where arousalItemType greater than or equals to DEFAULT_AROUSAL_ITEM_TYPE
        defaultMArousalItemShouldBeFound("arousalItemType.greaterOrEqualThan=" + DEFAULT_AROUSAL_ITEM_TYPE);

        // Get all the mArousalItemList where arousalItemType greater than or equals to UPDATED_AROUSAL_ITEM_TYPE
        defaultMArousalItemShouldNotBeFound("arousalItemType.greaterOrEqualThan=" + UPDATED_AROUSAL_ITEM_TYPE);
    }

    @Test
    @Transactional
    public void getAllMArousalItemsByArousalItemTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mArousalItemRepository.saveAndFlush(mArousalItem);

        // Get all the mArousalItemList where arousalItemType less than or equals to DEFAULT_AROUSAL_ITEM_TYPE
        defaultMArousalItemShouldNotBeFound("arousalItemType.lessThan=" + DEFAULT_AROUSAL_ITEM_TYPE);

        // Get all the mArousalItemList where arousalItemType less than or equals to UPDATED_AROUSAL_ITEM_TYPE
        defaultMArousalItemShouldBeFound("arousalItemType.lessThan=" + UPDATED_AROUSAL_ITEM_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMArousalItemShouldBeFound(String filter) throws Exception {
        restMArousalItemMockMvc.perform(get("/api/m-arousal-items?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mArousalItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailBgAssetName").value(hasItem(DEFAULT_THUMBNAIL_BG_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].thumbnailFrameAssetName").value(hasItem(DEFAULT_THUMBNAIL_FRAME_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].arousalItemType").value(hasItem(DEFAULT_AROUSAL_ITEM_TYPE)));

        // Check, that the count call also returns 1
        restMArousalItemMockMvc.perform(get("/api/m-arousal-items/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMArousalItemShouldNotBeFound(String filter) throws Exception {
        restMArousalItemMockMvc.perform(get("/api/m-arousal-items?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMArousalItemMockMvc.perform(get("/api/m-arousal-items/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMArousalItem() throws Exception {
        // Get the mArousalItem
        restMArousalItemMockMvc.perform(get("/api/m-arousal-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMArousalItem() throws Exception {
        // Initialize the database
        mArousalItemRepository.saveAndFlush(mArousalItem);

        int databaseSizeBeforeUpdate = mArousalItemRepository.findAll().size();

        // Update the mArousalItem
        MArousalItem updatedMArousalItem = mArousalItemRepository.findById(mArousalItem.getId()).get();
        // Disconnect from session so that the updates on updatedMArousalItem are not directly saved in db
        em.detach(updatedMArousalItem);
        updatedMArousalItem
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .thumbnailBgAssetName(UPDATED_THUMBNAIL_BG_ASSET_NAME)
            .thumbnailFrameAssetName(UPDATED_THUMBNAIL_FRAME_ASSET_NAME)
            .arousalItemType(UPDATED_AROUSAL_ITEM_TYPE);
        MArousalItemDTO mArousalItemDTO = mArousalItemMapper.toDto(updatedMArousalItem);

        restMArousalItemMockMvc.perform(put("/api/m-arousal-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalItemDTO)))
            .andExpect(status().isOk());

        // Validate the MArousalItem in the database
        List<MArousalItem> mArousalItemList = mArousalItemRepository.findAll();
        assertThat(mArousalItemList).hasSize(databaseSizeBeforeUpdate);
        MArousalItem testMArousalItem = mArousalItemList.get(mArousalItemList.size() - 1);
        assertThat(testMArousalItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMArousalItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMArousalItem.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
        assertThat(testMArousalItem.getThumbnailBgAssetName()).isEqualTo(UPDATED_THUMBNAIL_BG_ASSET_NAME);
        assertThat(testMArousalItem.getThumbnailFrameAssetName()).isEqualTo(UPDATED_THUMBNAIL_FRAME_ASSET_NAME);
        assertThat(testMArousalItem.getArousalItemType()).isEqualTo(UPDATED_AROUSAL_ITEM_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMArousalItem() throws Exception {
        int databaseSizeBeforeUpdate = mArousalItemRepository.findAll().size();

        // Create the MArousalItem
        MArousalItemDTO mArousalItemDTO = mArousalItemMapper.toDto(mArousalItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMArousalItemMockMvc.perform(put("/api/m-arousal-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mArousalItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MArousalItem in the database
        List<MArousalItem> mArousalItemList = mArousalItemRepository.findAll();
        assertThat(mArousalItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMArousalItem() throws Exception {
        // Initialize the database
        mArousalItemRepository.saveAndFlush(mArousalItem);

        int databaseSizeBeforeDelete = mArousalItemRepository.findAll().size();

        // Delete the mArousalItem
        restMArousalItemMockMvc.perform(delete("/api/m-arousal-items/{id}", mArousalItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MArousalItem> mArousalItemList = mArousalItemRepository.findAll();
        assertThat(mArousalItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MArousalItem.class);
        MArousalItem mArousalItem1 = new MArousalItem();
        mArousalItem1.setId(1L);
        MArousalItem mArousalItem2 = new MArousalItem();
        mArousalItem2.setId(mArousalItem1.getId());
        assertThat(mArousalItem1).isEqualTo(mArousalItem2);
        mArousalItem2.setId(2L);
        assertThat(mArousalItem1).isNotEqualTo(mArousalItem2);
        mArousalItem1.setId(null);
        assertThat(mArousalItem1).isNotEqualTo(mArousalItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MArousalItemDTO.class);
        MArousalItemDTO mArousalItemDTO1 = new MArousalItemDTO();
        mArousalItemDTO1.setId(1L);
        MArousalItemDTO mArousalItemDTO2 = new MArousalItemDTO();
        assertThat(mArousalItemDTO1).isNotEqualTo(mArousalItemDTO2);
        mArousalItemDTO2.setId(mArousalItemDTO1.getId());
        assertThat(mArousalItemDTO1).isEqualTo(mArousalItemDTO2);
        mArousalItemDTO2.setId(2L);
        assertThat(mArousalItemDTO1).isNotEqualTo(mArousalItemDTO2);
        mArousalItemDTO1.setId(null);
        assertThat(mArousalItemDTO1).isNotEqualTo(mArousalItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mArousalItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mArousalItemMapper.fromId(null)).isNull();
    }
}
