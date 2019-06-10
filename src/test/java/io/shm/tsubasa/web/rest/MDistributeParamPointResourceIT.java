package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MDistributeParamPoint;
import io.shm.tsubasa.repository.MDistributeParamPointRepository;
import io.shm.tsubasa.service.MDistributeParamPointService;
import io.shm.tsubasa.service.dto.MDistributeParamPointDTO;
import io.shm.tsubasa.service.mapper.MDistributeParamPointMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MDistributeParamPointCriteria;
import io.shm.tsubasa.service.MDistributeParamPointQueryService;

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
 * Integration tests for the {@Link MDistributeParamPointResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MDistributeParamPointResourceIT {

    private static final Integer DEFAULT_TARGET_ATTRIBUTE = 1;
    private static final Integer UPDATED_TARGET_ATTRIBUTE = 2;

    private static final Integer DEFAULT_TARGET_NATIONALITY_GROUP_ID = 1;
    private static final Integer UPDATED_TARGET_NATIONALITY_GROUP_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ICON_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ICON_ASSET_NAME = "BBBBBBBBBB";

    @Autowired
    private MDistributeParamPointRepository mDistributeParamPointRepository;

    @Autowired
    private MDistributeParamPointMapper mDistributeParamPointMapper;

    @Autowired
    private MDistributeParamPointService mDistributeParamPointService;

    @Autowired
    private MDistributeParamPointQueryService mDistributeParamPointQueryService;

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

    private MockMvc restMDistributeParamPointMockMvc;

    private MDistributeParamPoint mDistributeParamPoint;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MDistributeParamPointResource mDistributeParamPointResource = new MDistributeParamPointResource(mDistributeParamPointService, mDistributeParamPointQueryService);
        this.restMDistributeParamPointMockMvc = MockMvcBuilders.standaloneSetup(mDistributeParamPointResource)
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
    public static MDistributeParamPoint createEntity(EntityManager em) {
        MDistributeParamPoint mDistributeParamPoint = new MDistributeParamPoint()
            .targetAttribute(DEFAULT_TARGET_ATTRIBUTE)
            .targetNationalityGroupId(DEFAULT_TARGET_NATIONALITY_GROUP_ID)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .thumbnailAssetName(DEFAULT_THUMBNAIL_ASSET_NAME)
            .iconAssetName(DEFAULT_ICON_ASSET_NAME);
        return mDistributeParamPoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MDistributeParamPoint createUpdatedEntity(EntityManager em) {
        MDistributeParamPoint mDistributeParamPoint = new MDistributeParamPoint()
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetNationalityGroupId(UPDATED_TARGET_NATIONALITY_GROUP_ID)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .iconAssetName(UPDATED_ICON_ASSET_NAME);
        return mDistributeParamPoint;
    }

    @BeforeEach
    public void initTest() {
        mDistributeParamPoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createMDistributeParamPoint() throws Exception {
        int databaseSizeBeforeCreate = mDistributeParamPointRepository.findAll().size();

        // Create the MDistributeParamPoint
        MDistributeParamPointDTO mDistributeParamPointDTO = mDistributeParamPointMapper.toDto(mDistributeParamPoint);
        restMDistributeParamPointMockMvc.perform(post("/api/m-distribute-param-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeParamPointDTO)))
            .andExpect(status().isCreated());

        // Validate the MDistributeParamPoint in the database
        List<MDistributeParamPoint> mDistributeParamPointList = mDistributeParamPointRepository.findAll();
        assertThat(mDistributeParamPointList).hasSize(databaseSizeBeforeCreate + 1);
        MDistributeParamPoint testMDistributeParamPoint = mDistributeParamPointList.get(mDistributeParamPointList.size() - 1);
        assertThat(testMDistributeParamPoint.getTargetAttribute()).isEqualTo(DEFAULT_TARGET_ATTRIBUTE);
        assertThat(testMDistributeParamPoint.getTargetNationalityGroupId()).isEqualTo(DEFAULT_TARGET_NATIONALITY_GROUP_ID);
        assertThat(testMDistributeParamPoint.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMDistributeParamPoint.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMDistributeParamPoint.getThumbnailAssetName()).isEqualTo(DEFAULT_THUMBNAIL_ASSET_NAME);
        assertThat(testMDistributeParamPoint.getIconAssetName()).isEqualTo(DEFAULT_ICON_ASSET_NAME);
    }

    @Test
    @Transactional
    public void createMDistributeParamPointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mDistributeParamPointRepository.findAll().size();

        // Create the MDistributeParamPoint with an existing ID
        mDistributeParamPoint.setId(1L);
        MDistributeParamPointDTO mDistributeParamPointDTO = mDistributeParamPointMapper.toDto(mDistributeParamPoint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMDistributeParamPointMockMvc.perform(post("/api/m-distribute-param-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeParamPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDistributeParamPoint in the database
        List<MDistributeParamPoint> mDistributeParamPointList = mDistributeParamPointRepository.findAll();
        assertThat(mDistributeParamPointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTargetAttributeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeParamPointRepository.findAll().size();
        // set the field null
        mDistributeParamPoint.setTargetAttribute(null);

        // Create the MDistributeParamPoint, which fails.
        MDistributeParamPointDTO mDistributeParamPointDTO = mDistributeParamPointMapper.toDto(mDistributeParamPoint);

        restMDistributeParamPointMockMvc.perform(post("/api/m-distribute-param-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeParamPointDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeParamPoint> mDistributeParamPointList = mDistributeParamPointRepository.findAll();
        assertThat(mDistributeParamPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTargetNationalityGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mDistributeParamPointRepository.findAll().size();
        // set the field null
        mDistributeParamPoint.setTargetNationalityGroupId(null);

        // Create the MDistributeParamPoint, which fails.
        MDistributeParamPointDTO mDistributeParamPointDTO = mDistributeParamPointMapper.toDto(mDistributeParamPoint);

        restMDistributeParamPointMockMvc.perform(post("/api/m-distribute-param-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeParamPointDTO)))
            .andExpect(status().isBadRequest());

        List<MDistributeParamPoint> mDistributeParamPointList = mDistributeParamPointRepository.findAll();
        assertThat(mDistributeParamPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPoints() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList
        restMDistributeParamPointMockMvc.perform(get("/api/m-distribute-param-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDistributeParamPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].iconAssetName").value(hasItem(DEFAULT_ICON_ASSET_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMDistributeParamPoint() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get the mDistributeParamPoint
        restMDistributeParamPointMockMvc.perform(get("/api/m-distribute-param-points/{id}", mDistributeParamPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mDistributeParamPoint.getId().intValue()))
            .andExpect(jsonPath("$.targetAttribute").value(DEFAULT_TARGET_ATTRIBUTE))
            .andExpect(jsonPath("$.targetNationalityGroupId").value(DEFAULT_TARGET_NATIONALITY_GROUP_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.thumbnailAssetName").value(DEFAULT_THUMBNAIL_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.iconAssetName").value(DEFAULT_ICON_ASSET_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetAttributeIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetAttribute equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMDistributeParamPointShouldBeFound("targetAttribute.equals=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mDistributeParamPointList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMDistributeParamPointShouldNotBeFound("targetAttribute.equals=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetAttributeIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetAttribute in DEFAULT_TARGET_ATTRIBUTE or UPDATED_TARGET_ATTRIBUTE
        defaultMDistributeParamPointShouldBeFound("targetAttribute.in=" + DEFAULT_TARGET_ATTRIBUTE + "," + UPDATED_TARGET_ATTRIBUTE);

        // Get all the mDistributeParamPointList where targetAttribute equals to UPDATED_TARGET_ATTRIBUTE
        defaultMDistributeParamPointShouldNotBeFound("targetAttribute.in=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetAttributeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetAttribute is not null
        defaultMDistributeParamPointShouldBeFound("targetAttribute.specified=true");

        // Get all the mDistributeParamPointList where targetAttribute is null
        defaultMDistributeParamPointShouldNotBeFound("targetAttribute.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetAttributeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetAttribute greater than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMDistributeParamPointShouldBeFound("targetAttribute.greaterOrEqualThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mDistributeParamPointList where targetAttribute greater than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMDistributeParamPointShouldNotBeFound("targetAttribute.greaterOrEqualThan=" + UPDATED_TARGET_ATTRIBUTE);
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetAttributeIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetAttribute less than or equals to DEFAULT_TARGET_ATTRIBUTE
        defaultMDistributeParamPointShouldNotBeFound("targetAttribute.lessThan=" + DEFAULT_TARGET_ATTRIBUTE);

        // Get all the mDistributeParamPointList where targetAttribute less than or equals to UPDATED_TARGET_ATTRIBUTE
        defaultMDistributeParamPointShouldBeFound("targetAttribute.lessThan=" + UPDATED_TARGET_ATTRIBUTE);
    }


    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetNationalityGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetNationalityGroupId equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMDistributeParamPointShouldBeFound("targetNationalityGroupId.equals=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mDistributeParamPointList where targetNationalityGroupId equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMDistributeParamPointShouldNotBeFound("targetNationalityGroupId.equals=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetNationalityGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetNationalityGroupId in DEFAULT_TARGET_NATIONALITY_GROUP_ID or UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMDistributeParamPointShouldBeFound("targetNationalityGroupId.in=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID + "," + UPDATED_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mDistributeParamPointList where targetNationalityGroupId equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMDistributeParamPointShouldNotBeFound("targetNationalityGroupId.in=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetNationalityGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetNationalityGroupId is not null
        defaultMDistributeParamPointShouldBeFound("targetNationalityGroupId.specified=true");

        // Get all the mDistributeParamPointList where targetNationalityGroupId is null
        defaultMDistributeParamPointShouldNotBeFound("targetNationalityGroupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetNationalityGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetNationalityGroupId greater than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMDistributeParamPointShouldBeFound("targetNationalityGroupId.greaterOrEqualThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mDistributeParamPointList where targetNationalityGroupId greater than or equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMDistributeParamPointShouldNotBeFound("targetNationalityGroupId.greaterOrEqualThan=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMDistributeParamPointsByTargetNationalityGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        // Get all the mDistributeParamPointList where targetNationalityGroupId less than or equals to DEFAULT_TARGET_NATIONALITY_GROUP_ID
        defaultMDistributeParamPointShouldNotBeFound("targetNationalityGroupId.lessThan=" + DEFAULT_TARGET_NATIONALITY_GROUP_ID);

        // Get all the mDistributeParamPointList where targetNationalityGroupId less than or equals to UPDATED_TARGET_NATIONALITY_GROUP_ID
        defaultMDistributeParamPointShouldBeFound("targetNationalityGroupId.lessThan=" + UPDATED_TARGET_NATIONALITY_GROUP_ID);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMDistributeParamPointShouldBeFound(String filter) throws Exception {
        restMDistributeParamPointMockMvc.perform(get("/api/m-distribute-param-points?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mDistributeParamPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].targetAttribute").value(hasItem(DEFAULT_TARGET_ATTRIBUTE)))
            .andExpect(jsonPath("$.[*].targetNationalityGroupId").value(hasItem(DEFAULT_TARGET_NATIONALITY_GROUP_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].thumbnailAssetName").value(hasItem(DEFAULT_THUMBNAIL_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].iconAssetName").value(hasItem(DEFAULT_ICON_ASSET_NAME.toString())));

        // Check, that the count call also returns 1
        restMDistributeParamPointMockMvc.perform(get("/api/m-distribute-param-points/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMDistributeParamPointShouldNotBeFound(String filter) throws Exception {
        restMDistributeParamPointMockMvc.perform(get("/api/m-distribute-param-points?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMDistributeParamPointMockMvc.perform(get("/api/m-distribute-param-points/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMDistributeParamPoint() throws Exception {
        // Get the mDistributeParamPoint
        restMDistributeParamPointMockMvc.perform(get("/api/m-distribute-param-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMDistributeParamPoint() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        int databaseSizeBeforeUpdate = mDistributeParamPointRepository.findAll().size();

        // Update the mDistributeParamPoint
        MDistributeParamPoint updatedMDistributeParamPoint = mDistributeParamPointRepository.findById(mDistributeParamPoint.getId()).get();
        // Disconnect from session so that the updates on updatedMDistributeParamPoint are not directly saved in db
        em.detach(updatedMDistributeParamPoint);
        updatedMDistributeParamPoint
            .targetAttribute(UPDATED_TARGET_ATTRIBUTE)
            .targetNationalityGroupId(UPDATED_TARGET_NATIONALITY_GROUP_ID)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .thumbnailAssetName(UPDATED_THUMBNAIL_ASSET_NAME)
            .iconAssetName(UPDATED_ICON_ASSET_NAME);
        MDistributeParamPointDTO mDistributeParamPointDTO = mDistributeParamPointMapper.toDto(updatedMDistributeParamPoint);

        restMDistributeParamPointMockMvc.perform(put("/api/m-distribute-param-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeParamPointDTO)))
            .andExpect(status().isOk());

        // Validate the MDistributeParamPoint in the database
        List<MDistributeParamPoint> mDistributeParamPointList = mDistributeParamPointRepository.findAll();
        assertThat(mDistributeParamPointList).hasSize(databaseSizeBeforeUpdate);
        MDistributeParamPoint testMDistributeParamPoint = mDistributeParamPointList.get(mDistributeParamPointList.size() - 1);
        assertThat(testMDistributeParamPoint.getTargetAttribute()).isEqualTo(UPDATED_TARGET_ATTRIBUTE);
        assertThat(testMDistributeParamPoint.getTargetNationalityGroupId()).isEqualTo(UPDATED_TARGET_NATIONALITY_GROUP_ID);
        assertThat(testMDistributeParamPoint.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMDistributeParamPoint.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMDistributeParamPoint.getThumbnailAssetName()).isEqualTo(UPDATED_THUMBNAIL_ASSET_NAME);
        assertThat(testMDistributeParamPoint.getIconAssetName()).isEqualTo(UPDATED_ICON_ASSET_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMDistributeParamPoint() throws Exception {
        int databaseSizeBeforeUpdate = mDistributeParamPointRepository.findAll().size();

        // Create the MDistributeParamPoint
        MDistributeParamPointDTO mDistributeParamPointDTO = mDistributeParamPointMapper.toDto(mDistributeParamPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMDistributeParamPointMockMvc.perform(put("/api/m-distribute-param-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mDistributeParamPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MDistributeParamPoint in the database
        List<MDistributeParamPoint> mDistributeParamPointList = mDistributeParamPointRepository.findAll();
        assertThat(mDistributeParamPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMDistributeParamPoint() throws Exception {
        // Initialize the database
        mDistributeParamPointRepository.saveAndFlush(mDistributeParamPoint);

        int databaseSizeBeforeDelete = mDistributeParamPointRepository.findAll().size();

        // Delete the mDistributeParamPoint
        restMDistributeParamPointMockMvc.perform(delete("/api/m-distribute-param-points/{id}", mDistributeParamPoint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MDistributeParamPoint> mDistributeParamPointList = mDistributeParamPointRepository.findAll();
        assertThat(mDistributeParamPointList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDistributeParamPoint.class);
        MDistributeParamPoint mDistributeParamPoint1 = new MDistributeParamPoint();
        mDistributeParamPoint1.setId(1L);
        MDistributeParamPoint mDistributeParamPoint2 = new MDistributeParamPoint();
        mDistributeParamPoint2.setId(mDistributeParamPoint1.getId());
        assertThat(mDistributeParamPoint1).isEqualTo(mDistributeParamPoint2);
        mDistributeParamPoint2.setId(2L);
        assertThat(mDistributeParamPoint1).isNotEqualTo(mDistributeParamPoint2);
        mDistributeParamPoint1.setId(null);
        assertThat(mDistributeParamPoint1).isNotEqualTo(mDistributeParamPoint2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MDistributeParamPointDTO.class);
        MDistributeParamPointDTO mDistributeParamPointDTO1 = new MDistributeParamPointDTO();
        mDistributeParamPointDTO1.setId(1L);
        MDistributeParamPointDTO mDistributeParamPointDTO2 = new MDistributeParamPointDTO();
        assertThat(mDistributeParamPointDTO1).isNotEqualTo(mDistributeParamPointDTO2);
        mDistributeParamPointDTO2.setId(mDistributeParamPointDTO1.getId());
        assertThat(mDistributeParamPointDTO1).isEqualTo(mDistributeParamPointDTO2);
        mDistributeParamPointDTO2.setId(2L);
        assertThat(mDistributeParamPointDTO1).isNotEqualTo(mDistributeParamPointDTO2);
        mDistributeParamPointDTO1.setId(null);
        assertThat(mDistributeParamPointDTO1).isNotEqualTo(mDistributeParamPointDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mDistributeParamPointMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mDistributeParamPointMapper.fromId(null)).isNull();
    }
}
