package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MModelUniformBottomResource;
import io.shm.tsubasa.repository.MModelUniformBottomResourceRepository;
import io.shm.tsubasa.service.MModelUniformBottomResourceService;
import io.shm.tsubasa.service.dto.MModelUniformBottomResourceDTO;
import io.shm.tsubasa.service.mapper.MModelUniformBottomResourceMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MModelUniformBottomResourceCriteria;
import io.shm.tsubasa.service.MModelUniformBottomResourceQueryService;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MModelUniformBottomResourceResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MModelUniformBottomResourceResourceIT {

    private static final Integer DEFAULT_UNIFORM_TYPE_ID = 1;
    private static final Integer UPDATED_UNIFORM_TYPE_ID = 2;

    private static final Integer DEFAULT_DRESSING_TYPE = 1;
    private static final Integer UPDATED_DRESSING_TYPE = 2;

    private static final Integer DEFAULT_WIDTH = 1;
    private static final Integer UPDATED_WIDTH = 2;

    @Autowired
    private MModelUniformBottomResourceRepository mModelUniformBottomResourceRepository;

    @Autowired
    private MModelUniformBottomResourceMapper mModelUniformBottomResourceMapper;

    @Autowired
    private MModelUniformBottomResourceService mModelUniformBottomResourceService;

    @Autowired
    private MModelUniformBottomResourceQueryService mModelUniformBottomResourceQueryService;

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

    private MockMvc restMModelUniformBottomResourceMockMvc;

    private MModelUniformBottomResource mModelUniformBottomResource;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MModelUniformBottomResourceResource mModelUniformBottomResourceResource = new MModelUniformBottomResourceResource(mModelUniformBottomResourceService, mModelUniformBottomResourceQueryService);
        this.restMModelUniformBottomResourceMockMvc = MockMvcBuilders.standaloneSetup(mModelUniformBottomResourceResource)
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
    public static MModelUniformBottomResource createEntity(EntityManager em) {
        MModelUniformBottomResource mModelUniformBottomResource = new MModelUniformBottomResource()
            .uniformTypeId(DEFAULT_UNIFORM_TYPE_ID)
            .dressingType(DEFAULT_DRESSING_TYPE)
            .width(DEFAULT_WIDTH);
        return mModelUniformBottomResource;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MModelUniformBottomResource createUpdatedEntity(EntityManager em) {
        MModelUniformBottomResource mModelUniformBottomResource = new MModelUniformBottomResource()
            .uniformTypeId(UPDATED_UNIFORM_TYPE_ID)
            .dressingType(UPDATED_DRESSING_TYPE)
            .width(UPDATED_WIDTH);
        return mModelUniformBottomResource;
    }

    @BeforeEach
    public void initTest() {
        mModelUniformBottomResource = createEntity(em);
    }

    @Test
    @Transactional
    public void createMModelUniformBottomResource() throws Exception {
        int databaseSizeBeforeCreate = mModelUniformBottomResourceRepository.findAll().size();

        // Create the MModelUniformBottomResource
        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO = mModelUniformBottomResourceMapper.toDto(mModelUniformBottomResource);
        restMModelUniformBottomResourceMockMvc.perform(post("/api/m-model-uniform-bottom-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomResourceDTO)))
            .andExpect(status().isCreated());

        // Validate the MModelUniformBottomResource in the database
        List<MModelUniformBottomResource> mModelUniformBottomResourceList = mModelUniformBottomResourceRepository.findAll();
        assertThat(mModelUniformBottomResourceList).hasSize(databaseSizeBeforeCreate + 1);
        MModelUniformBottomResource testMModelUniformBottomResource = mModelUniformBottomResourceList.get(mModelUniformBottomResourceList.size() - 1);
        assertThat(testMModelUniformBottomResource.getUniformTypeId()).isEqualTo(DEFAULT_UNIFORM_TYPE_ID);
        assertThat(testMModelUniformBottomResource.getDressingType()).isEqualTo(DEFAULT_DRESSING_TYPE);
        assertThat(testMModelUniformBottomResource.getWidth()).isEqualTo(DEFAULT_WIDTH);
    }

    @Test
    @Transactional
    public void createMModelUniformBottomResourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mModelUniformBottomResourceRepository.findAll().size();

        // Create the MModelUniformBottomResource with an existing ID
        mModelUniformBottomResource.setId(1L);
        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO = mModelUniformBottomResourceMapper.toDto(mModelUniformBottomResource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMModelUniformBottomResourceMockMvc.perform(post("/api/m-model-uniform-bottom-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomResourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelUniformBottomResource in the database
        List<MModelUniformBottomResource> mModelUniformBottomResourceList = mModelUniformBottomResourceRepository.findAll();
        assertThat(mModelUniformBottomResourceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUniformTypeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomResourceRepository.findAll().size();
        // set the field null
        mModelUniformBottomResource.setUniformTypeId(null);

        // Create the MModelUniformBottomResource, which fails.
        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO = mModelUniformBottomResourceMapper.toDto(mModelUniformBottomResource);

        restMModelUniformBottomResourceMockMvc.perform(post("/api/m-model-uniform-bottom-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomResourceDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottomResource> mModelUniformBottomResourceList = mModelUniformBottomResourceRepository.findAll();
        assertThat(mModelUniformBottomResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDressingTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomResourceRepository.findAll().size();
        // set the field null
        mModelUniformBottomResource.setDressingType(null);

        // Create the MModelUniformBottomResource, which fails.
        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO = mModelUniformBottomResourceMapper.toDto(mModelUniformBottomResource);

        restMModelUniformBottomResourceMockMvc.perform(post("/api/m-model-uniform-bottom-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomResourceDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottomResource> mModelUniformBottomResourceList = mModelUniformBottomResourceRepository.findAll();
        assertThat(mModelUniformBottomResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformBottomResourceRepository.findAll().size();
        // set the field null
        mModelUniformBottomResource.setWidth(null);

        // Create the MModelUniformBottomResource, which fails.
        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO = mModelUniformBottomResourceMapper.toDto(mModelUniformBottomResource);

        restMModelUniformBottomResourceMockMvc.perform(post("/api/m-model-uniform-bottom-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomResourceDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformBottomResource> mModelUniformBottomResourceList = mModelUniformBottomResourceRepository.findAll();
        assertThat(mModelUniformBottomResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResources() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList
        restMModelUniformBottomResourceMockMvc.perform(get("/api/m-model-uniform-bottom-resources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelUniformBottomResource.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniformTypeId").value(hasItem(DEFAULT_UNIFORM_TYPE_ID)))
            .andExpect(jsonPath("$.[*].dressingType").value(hasItem(DEFAULT_DRESSING_TYPE)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)));
    }
    
    @Test
    @Transactional
    public void getMModelUniformBottomResource() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get the mModelUniformBottomResource
        restMModelUniformBottomResourceMockMvc.perform(get("/api/m-model-uniform-bottom-resources/{id}", mModelUniformBottomResource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mModelUniformBottomResource.getId().intValue()))
            .andExpect(jsonPath("$.uniformTypeId").value(DEFAULT_UNIFORM_TYPE_ID))
            .andExpect(jsonPath("$.dressingType").value(DEFAULT_DRESSING_TYPE))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH));
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByUniformTypeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where uniformTypeId equals to DEFAULT_UNIFORM_TYPE_ID
        defaultMModelUniformBottomResourceShouldBeFound("uniformTypeId.equals=" + DEFAULT_UNIFORM_TYPE_ID);

        // Get all the mModelUniformBottomResourceList where uniformTypeId equals to UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformBottomResourceShouldNotBeFound("uniformTypeId.equals=" + UPDATED_UNIFORM_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByUniformTypeIdIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where uniformTypeId in DEFAULT_UNIFORM_TYPE_ID or UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformBottomResourceShouldBeFound("uniformTypeId.in=" + DEFAULT_UNIFORM_TYPE_ID + "," + UPDATED_UNIFORM_TYPE_ID);

        // Get all the mModelUniformBottomResourceList where uniformTypeId equals to UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformBottomResourceShouldNotBeFound("uniformTypeId.in=" + UPDATED_UNIFORM_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByUniformTypeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where uniformTypeId is not null
        defaultMModelUniformBottomResourceShouldBeFound("uniformTypeId.specified=true");

        // Get all the mModelUniformBottomResourceList where uniformTypeId is null
        defaultMModelUniformBottomResourceShouldNotBeFound("uniformTypeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByUniformTypeIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where uniformTypeId greater than or equals to DEFAULT_UNIFORM_TYPE_ID
        defaultMModelUniformBottomResourceShouldBeFound("uniformTypeId.greaterOrEqualThan=" + DEFAULT_UNIFORM_TYPE_ID);

        // Get all the mModelUniformBottomResourceList where uniformTypeId greater than or equals to UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformBottomResourceShouldNotBeFound("uniformTypeId.greaterOrEqualThan=" + UPDATED_UNIFORM_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByUniformTypeIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where uniformTypeId less than or equals to DEFAULT_UNIFORM_TYPE_ID
        defaultMModelUniformBottomResourceShouldNotBeFound("uniformTypeId.lessThan=" + DEFAULT_UNIFORM_TYPE_ID);

        // Get all the mModelUniformBottomResourceList where uniformTypeId less than or equals to UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformBottomResourceShouldBeFound("uniformTypeId.lessThan=" + UPDATED_UNIFORM_TYPE_ID);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByDressingTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where dressingType equals to DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomResourceShouldBeFound("dressingType.equals=" + DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformBottomResourceList where dressingType equals to UPDATED_DRESSING_TYPE
        defaultMModelUniformBottomResourceShouldNotBeFound("dressingType.equals=" + UPDATED_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByDressingTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where dressingType in DEFAULT_DRESSING_TYPE or UPDATED_DRESSING_TYPE
        defaultMModelUniformBottomResourceShouldBeFound("dressingType.in=" + DEFAULT_DRESSING_TYPE + "," + UPDATED_DRESSING_TYPE);

        // Get all the mModelUniformBottomResourceList where dressingType equals to UPDATED_DRESSING_TYPE
        defaultMModelUniformBottomResourceShouldNotBeFound("dressingType.in=" + UPDATED_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByDressingTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where dressingType is not null
        defaultMModelUniformBottomResourceShouldBeFound("dressingType.specified=true");

        // Get all the mModelUniformBottomResourceList where dressingType is null
        defaultMModelUniformBottomResourceShouldNotBeFound("dressingType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByDressingTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where dressingType greater than or equals to DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomResourceShouldBeFound("dressingType.greaterOrEqualThan=" + DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformBottomResourceList where dressingType greater than or equals to UPDATED_DRESSING_TYPE
        defaultMModelUniformBottomResourceShouldNotBeFound("dressingType.greaterOrEqualThan=" + UPDATED_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByDressingTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where dressingType less than or equals to DEFAULT_DRESSING_TYPE
        defaultMModelUniformBottomResourceShouldNotBeFound("dressingType.lessThan=" + DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformBottomResourceList where dressingType less than or equals to UPDATED_DRESSING_TYPE
        defaultMModelUniformBottomResourceShouldBeFound("dressingType.lessThan=" + UPDATED_DRESSING_TYPE);
    }


    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByWidthIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where width equals to DEFAULT_WIDTH
        defaultMModelUniformBottomResourceShouldBeFound("width.equals=" + DEFAULT_WIDTH);

        // Get all the mModelUniformBottomResourceList where width equals to UPDATED_WIDTH
        defaultMModelUniformBottomResourceShouldNotBeFound("width.equals=" + UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByWidthIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where width in DEFAULT_WIDTH or UPDATED_WIDTH
        defaultMModelUniformBottomResourceShouldBeFound("width.in=" + DEFAULT_WIDTH + "," + UPDATED_WIDTH);

        // Get all the mModelUniformBottomResourceList where width equals to UPDATED_WIDTH
        defaultMModelUniformBottomResourceShouldNotBeFound("width.in=" + UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByWidthIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where width is not null
        defaultMModelUniformBottomResourceShouldBeFound("width.specified=true");

        // Get all the mModelUniformBottomResourceList where width is null
        defaultMModelUniformBottomResourceShouldNotBeFound("width.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByWidthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where width greater than or equals to DEFAULT_WIDTH
        defaultMModelUniformBottomResourceShouldBeFound("width.greaterOrEqualThan=" + DEFAULT_WIDTH);

        // Get all the mModelUniformBottomResourceList where width greater than or equals to UPDATED_WIDTH
        defaultMModelUniformBottomResourceShouldNotBeFound("width.greaterOrEqualThan=" + UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void getAllMModelUniformBottomResourcesByWidthIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        // Get all the mModelUniformBottomResourceList where width less than or equals to DEFAULT_WIDTH
        defaultMModelUniformBottomResourceShouldNotBeFound("width.lessThan=" + DEFAULT_WIDTH);

        // Get all the mModelUniformBottomResourceList where width less than or equals to UPDATED_WIDTH
        defaultMModelUniformBottomResourceShouldBeFound("width.lessThan=" + UPDATED_WIDTH);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMModelUniformBottomResourceShouldBeFound(String filter) throws Exception {
        restMModelUniformBottomResourceMockMvc.perform(get("/api/m-model-uniform-bottom-resources?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelUniformBottomResource.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniformTypeId").value(hasItem(DEFAULT_UNIFORM_TYPE_ID)))
            .andExpect(jsonPath("$.[*].dressingType").value(hasItem(DEFAULT_DRESSING_TYPE)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)));

        // Check, that the count call also returns 1
        restMModelUniformBottomResourceMockMvc.perform(get("/api/m-model-uniform-bottom-resources/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMModelUniformBottomResourceShouldNotBeFound(String filter) throws Exception {
        restMModelUniformBottomResourceMockMvc.perform(get("/api/m-model-uniform-bottom-resources?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMModelUniformBottomResourceMockMvc.perform(get("/api/m-model-uniform-bottom-resources/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMModelUniformBottomResource() throws Exception {
        // Get the mModelUniformBottomResource
        restMModelUniformBottomResourceMockMvc.perform(get("/api/m-model-uniform-bottom-resources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMModelUniformBottomResource() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        int databaseSizeBeforeUpdate = mModelUniformBottomResourceRepository.findAll().size();

        // Update the mModelUniformBottomResource
        MModelUniformBottomResource updatedMModelUniformBottomResource = mModelUniformBottomResourceRepository.findById(mModelUniformBottomResource.getId()).get();
        // Disconnect from session so that the updates on updatedMModelUniformBottomResource are not directly saved in db
        em.detach(updatedMModelUniformBottomResource);
        updatedMModelUniformBottomResource
            .uniformTypeId(UPDATED_UNIFORM_TYPE_ID)
            .dressingType(UPDATED_DRESSING_TYPE)
            .width(UPDATED_WIDTH);
        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO = mModelUniformBottomResourceMapper.toDto(updatedMModelUniformBottomResource);

        restMModelUniformBottomResourceMockMvc.perform(put("/api/m-model-uniform-bottom-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomResourceDTO)))
            .andExpect(status().isOk());

        // Validate the MModelUniformBottomResource in the database
        List<MModelUniformBottomResource> mModelUniformBottomResourceList = mModelUniformBottomResourceRepository.findAll();
        assertThat(mModelUniformBottomResourceList).hasSize(databaseSizeBeforeUpdate);
        MModelUniformBottomResource testMModelUniformBottomResource = mModelUniformBottomResourceList.get(mModelUniformBottomResourceList.size() - 1);
        assertThat(testMModelUniformBottomResource.getUniformTypeId()).isEqualTo(UPDATED_UNIFORM_TYPE_ID);
        assertThat(testMModelUniformBottomResource.getDressingType()).isEqualTo(UPDATED_DRESSING_TYPE);
        assertThat(testMModelUniformBottomResource.getWidth()).isEqualTo(UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void updateNonExistingMModelUniformBottomResource() throws Exception {
        int databaseSizeBeforeUpdate = mModelUniformBottomResourceRepository.findAll().size();

        // Create the MModelUniformBottomResource
        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO = mModelUniformBottomResourceMapper.toDto(mModelUniformBottomResource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMModelUniformBottomResourceMockMvc.perform(put("/api/m-model-uniform-bottom-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformBottomResourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelUniformBottomResource in the database
        List<MModelUniformBottomResource> mModelUniformBottomResourceList = mModelUniformBottomResourceRepository.findAll();
        assertThat(mModelUniformBottomResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMModelUniformBottomResource() throws Exception {
        // Initialize the database
        mModelUniformBottomResourceRepository.saveAndFlush(mModelUniformBottomResource);

        int databaseSizeBeforeDelete = mModelUniformBottomResourceRepository.findAll().size();

        // Delete the mModelUniformBottomResource
        restMModelUniformBottomResourceMockMvc.perform(delete("/api/m-model-uniform-bottom-resources/{id}", mModelUniformBottomResource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MModelUniformBottomResource> mModelUniformBottomResourceList = mModelUniformBottomResourceRepository.findAll();
        assertThat(mModelUniformBottomResourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelUniformBottomResource.class);
        MModelUniformBottomResource mModelUniformBottomResource1 = new MModelUniformBottomResource();
        mModelUniformBottomResource1.setId(1L);
        MModelUniformBottomResource mModelUniformBottomResource2 = new MModelUniformBottomResource();
        mModelUniformBottomResource2.setId(mModelUniformBottomResource1.getId());
        assertThat(mModelUniformBottomResource1).isEqualTo(mModelUniformBottomResource2);
        mModelUniformBottomResource2.setId(2L);
        assertThat(mModelUniformBottomResource1).isNotEqualTo(mModelUniformBottomResource2);
        mModelUniformBottomResource1.setId(null);
        assertThat(mModelUniformBottomResource1).isNotEqualTo(mModelUniformBottomResource2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelUniformBottomResourceDTO.class);
        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO1 = new MModelUniformBottomResourceDTO();
        mModelUniformBottomResourceDTO1.setId(1L);
        MModelUniformBottomResourceDTO mModelUniformBottomResourceDTO2 = new MModelUniformBottomResourceDTO();
        assertThat(mModelUniformBottomResourceDTO1).isNotEqualTo(mModelUniformBottomResourceDTO2);
        mModelUniformBottomResourceDTO2.setId(mModelUniformBottomResourceDTO1.getId());
        assertThat(mModelUniformBottomResourceDTO1).isEqualTo(mModelUniformBottomResourceDTO2);
        mModelUniformBottomResourceDTO2.setId(2L);
        assertThat(mModelUniformBottomResourceDTO1).isNotEqualTo(mModelUniformBottomResourceDTO2);
        mModelUniformBottomResourceDTO1.setId(null);
        assertThat(mModelUniformBottomResourceDTO1).isNotEqualTo(mModelUniformBottomResourceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mModelUniformBottomResourceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mModelUniformBottomResourceMapper.fromId(null)).isNull();
    }
}
