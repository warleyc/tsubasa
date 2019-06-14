package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MModelUniformUpResource;
import io.shm.tsubasa.repository.MModelUniformUpResourceRepository;
import io.shm.tsubasa.service.MModelUniformUpResourceService;
import io.shm.tsubasa.service.dto.MModelUniformUpResourceDTO;
import io.shm.tsubasa.service.mapper.MModelUniformUpResourceMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MModelUniformUpResourceCriteria;
import io.shm.tsubasa.service.MModelUniformUpResourceQueryService;

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
 * Integration tests for the {@Link MModelUniformUpResourceResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MModelUniformUpResourceResourceIT {

    private static final Integer DEFAULT_UNIFORM_TYPE_ID = 1;
    private static final Integer UPDATED_UNIFORM_TYPE_ID = 2;

    private static final Integer DEFAULT_DRESSING_TYPE = 1;
    private static final Integer UPDATED_DRESSING_TYPE = 2;

    private static final Integer DEFAULT_WIDTH = 1;
    private static final Integer UPDATED_WIDTH = 2;

    @Autowired
    private MModelUniformUpResourceRepository mModelUniformUpResourceRepository;

    @Autowired
    private MModelUniformUpResourceMapper mModelUniformUpResourceMapper;

    @Autowired
    private MModelUniformUpResourceService mModelUniformUpResourceService;

    @Autowired
    private MModelUniformUpResourceQueryService mModelUniformUpResourceQueryService;

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

    private MockMvc restMModelUniformUpResourceMockMvc;

    private MModelUniformUpResource mModelUniformUpResource;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MModelUniformUpResourceResource mModelUniformUpResourceResource = new MModelUniformUpResourceResource(mModelUniformUpResourceService, mModelUniformUpResourceQueryService);
        this.restMModelUniformUpResourceMockMvc = MockMvcBuilders.standaloneSetup(mModelUniformUpResourceResource)
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
    public static MModelUniformUpResource createEntity(EntityManager em) {
        MModelUniformUpResource mModelUniformUpResource = new MModelUniformUpResource()
            .uniformTypeId(DEFAULT_UNIFORM_TYPE_ID)
            .dressingType(DEFAULT_DRESSING_TYPE)
            .width(DEFAULT_WIDTH);
        return mModelUniformUpResource;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MModelUniformUpResource createUpdatedEntity(EntityManager em) {
        MModelUniformUpResource mModelUniformUpResource = new MModelUniformUpResource()
            .uniformTypeId(UPDATED_UNIFORM_TYPE_ID)
            .dressingType(UPDATED_DRESSING_TYPE)
            .width(UPDATED_WIDTH);
        return mModelUniformUpResource;
    }

    @BeforeEach
    public void initTest() {
        mModelUniformUpResource = createEntity(em);
    }

    @Test
    @Transactional
    public void createMModelUniformUpResource() throws Exception {
        int databaseSizeBeforeCreate = mModelUniformUpResourceRepository.findAll().size();

        // Create the MModelUniformUpResource
        MModelUniformUpResourceDTO mModelUniformUpResourceDTO = mModelUniformUpResourceMapper.toDto(mModelUniformUpResource);
        restMModelUniformUpResourceMockMvc.perform(post("/api/m-model-uniform-up-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpResourceDTO)))
            .andExpect(status().isCreated());

        // Validate the MModelUniformUpResource in the database
        List<MModelUniformUpResource> mModelUniformUpResourceList = mModelUniformUpResourceRepository.findAll();
        assertThat(mModelUniformUpResourceList).hasSize(databaseSizeBeforeCreate + 1);
        MModelUniformUpResource testMModelUniformUpResource = mModelUniformUpResourceList.get(mModelUniformUpResourceList.size() - 1);
        assertThat(testMModelUniformUpResource.getUniformTypeId()).isEqualTo(DEFAULT_UNIFORM_TYPE_ID);
        assertThat(testMModelUniformUpResource.getDressingType()).isEqualTo(DEFAULT_DRESSING_TYPE);
        assertThat(testMModelUniformUpResource.getWidth()).isEqualTo(DEFAULT_WIDTH);
    }

    @Test
    @Transactional
    public void createMModelUniformUpResourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mModelUniformUpResourceRepository.findAll().size();

        // Create the MModelUniformUpResource with an existing ID
        mModelUniformUpResource.setId(1L);
        MModelUniformUpResourceDTO mModelUniformUpResourceDTO = mModelUniformUpResourceMapper.toDto(mModelUniformUpResource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMModelUniformUpResourceMockMvc.perform(post("/api/m-model-uniform-up-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpResourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelUniformUpResource in the database
        List<MModelUniformUpResource> mModelUniformUpResourceList = mModelUniformUpResourceRepository.findAll();
        assertThat(mModelUniformUpResourceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUniformTypeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpResourceRepository.findAll().size();
        // set the field null
        mModelUniformUpResource.setUniformTypeId(null);

        // Create the MModelUniformUpResource, which fails.
        MModelUniformUpResourceDTO mModelUniformUpResourceDTO = mModelUniformUpResourceMapper.toDto(mModelUniformUpResource);

        restMModelUniformUpResourceMockMvc.perform(post("/api/m-model-uniform-up-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpResourceDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUpResource> mModelUniformUpResourceList = mModelUniformUpResourceRepository.findAll();
        assertThat(mModelUniformUpResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDressingTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpResourceRepository.findAll().size();
        // set the field null
        mModelUniformUpResource.setDressingType(null);

        // Create the MModelUniformUpResource, which fails.
        MModelUniformUpResourceDTO mModelUniformUpResourceDTO = mModelUniformUpResourceMapper.toDto(mModelUniformUpResource);

        restMModelUniformUpResourceMockMvc.perform(post("/api/m-model-uniform-up-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpResourceDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUpResource> mModelUniformUpResourceList = mModelUniformUpResourceRepository.findAll();
        assertThat(mModelUniformUpResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = mModelUniformUpResourceRepository.findAll().size();
        // set the field null
        mModelUniformUpResource.setWidth(null);

        // Create the MModelUniformUpResource, which fails.
        MModelUniformUpResourceDTO mModelUniformUpResourceDTO = mModelUniformUpResourceMapper.toDto(mModelUniformUpResource);

        restMModelUniformUpResourceMockMvc.perform(post("/api/m-model-uniform-up-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpResourceDTO)))
            .andExpect(status().isBadRequest());

        List<MModelUniformUpResource> mModelUniformUpResourceList = mModelUniformUpResourceRepository.findAll();
        assertThat(mModelUniformUpResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResources() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList
        restMModelUniformUpResourceMockMvc.perform(get("/api/m-model-uniform-up-resources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelUniformUpResource.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniformTypeId").value(hasItem(DEFAULT_UNIFORM_TYPE_ID)))
            .andExpect(jsonPath("$.[*].dressingType").value(hasItem(DEFAULT_DRESSING_TYPE)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)));
    }
    
    @Test
    @Transactional
    public void getMModelUniformUpResource() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get the mModelUniformUpResource
        restMModelUniformUpResourceMockMvc.perform(get("/api/m-model-uniform-up-resources/{id}", mModelUniformUpResource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mModelUniformUpResource.getId().intValue()))
            .andExpect(jsonPath("$.uniformTypeId").value(DEFAULT_UNIFORM_TYPE_ID))
            .andExpect(jsonPath("$.dressingType").value(DEFAULT_DRESSING_TYPE))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH));
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByUniformTypeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where uniformTypeId equals to DEFAULT_UNIFORM_TYPE_ID
        defaultMModelUniformUpResourceShouldBeFound("uniformTypeId.equals=" + DEFAULT_UNIFORM_TYPE_ID);

        // Get all the mModelUniformUpResourceList where uniformTypeId equals to UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformUpResourceShouldNotBeFound("uniformTypeId.equals=" + UPDATED_UNIFORM_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByUniformTypeIdIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where uniformTypeId in DEFAULT_UNIFORM_TYPE_ID or UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformUpResourceShouldBeFound("uniformTypeId.in=" + DEFAULT_UNIFORM_TYPE_ID + "," + UPDATED_UNIFORM_TYPE_ID);

        // Get all the mModelUniformUpResourceList where uniformTypeId equals to UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformUpResourceShouldNotBeFound("uniformTypeId.in=" + UPDATED_UNIFORM_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByUniformTypeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where uniformTypeId is not null
        defaultMModelUniformUpResourceShouldBeFound("uniformTypeId.specified=true");

        // Get all the mModelUniformUpResourceList where uniformTypeId is null
        defaultMModelUniformUpResourceShouldNotBeFound("uniformTypeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByUniformTypeIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where uniformTypeId greater than or equals to DEFAULT_UNIFORM_TYPE_ID
        defaultMModelUniformUpResourceShouldBeFound("uniformTypeId.greaterOrEqualThan=" + DEFAULT_UNIFORM_TYPE_ID);

        // Get all the mModelUniformUpResourceList where uniformTypeId greater than or equals to UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformUpResourceShouldNotBeFound("uniformTypeId.greaterOrEqualThan=" + UPDATED_UNIFORM_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByUniformTypeIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where uniformTypeId less than or equals to DEFAULT_UNIFORM_TYPE_ID
        defaultMModelUniformUpResourceShouldNotBeFound("uniformTypeId.lessThan=" + DEFAULT_UNIFORM_TYPE_ID);

        // Get all the mModelUniformUpResourceList where uniformTypeId less than or equals to UPDATED_UNIFORM_TYPE_ID
        defaultMModelUniformUpResourceShouldBeFound("uniformTypeId.lessThan=" + UPDATED_UNIFORM_TYPE_ID);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByDressingTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where dressingType equals to DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpResourceShouldBeFound("dressingType.equals=" + DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformUpResourceList where dressingType equals to UPDATED_DRESSING_TYPE
        defaultMModelUniformUpResourceShouldNotBeFound("dressingType.equals=" + UPDATED_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByDressingTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where dressingType in DEFAULT_DRESSING_TYPE or UPDATED_DRESSING_TYPE
        defaultMModelUniformUpResourceShouldBeFound("dressingType.in=" + DEFAULT_DRESSING_TYPE + "," + UPDATED_DRESSING_TYPE);

        // Get all the mModelUniformUpResourceList where dressingType equals to UPDATED_DRESSING_TYPE
        defaultMModelUniformUpResourceShouldNotBeFound("dressingType.in=" + UPDATED_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByDressingTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where dressingType is not null
        defaultMModelUniformUpResourceShouldBeFound("dressingType.specified=true");

        // Get all the mModelUniformUpResourceList where dressingType is null
        defaultMModelUniformUpResourceShouldNotBeFound("dressingType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByDressingTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where dressingType greater than or equals to DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpResourceShouldBeFound("dressingType.greaterOrEqualThan=" + DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformUpResourceList where dressingType greater than or equals to UPDATED_DRESSING_TYPE
        defaultMModelUniformUpResourceShouldNotBeFound("dressingType.greaterOrEqualThan=" + UPDATED_DRESSING_TYPE);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByDressingTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where dressingType less than or equals to DEFAULT_DRESSING_TYPE
        defaultMModelUniformUpResourceShouldNotBeFound("dressingType.lessThan=" + DEFAULT_DRESSING_TYPE);

        // Get all the mModelUniformUpResourceList where dressingType less than or equals to UPDATED_DRESSING_TYPE
        defaultMModelUniformUpResourceShouldBeFound("dressingType.lessThan=" + UPDATED_DRESSING_TYPE);
    }


    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByWidthIsEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where width equals to DEFAULT_WIDTH
        defaultMModelUniformUpResourceShouldBeFound("width.equals=" + DEFAULT_WIDTH);

        // Get all the mModelUniformUpResourceList where width equals to UPDATED_WIDTH
        defaultMModelUniformUpResourceShouldNotBeFound("width.equals=" + UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByWidthIsInShouldWork() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where width in DEFAULT_WIDTH or UPDATED_WIDTH
        defaultMModelUniformUpResourceShouldBeFound("width.in=" + DEFAULT_WIDTH + "," + UPDATED_WIDTH);

        // Get all the mModelUniformUpResourceList where width equals to UPDATED_WIDTH
        defaultMModelUniformUpResourceShouldNotBeFound("width.in=" + UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByWidthIsNullOrNotNull() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where width is not null
        defaultMModelUniformUpResourceShouldBeFound("width.specified=true");

        // Get all the mModelUniformUpResourceList where width is null
        defaultMModelUniformUpResourceShouldNotBeFound("width.specified=false");
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByWidthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where width greater than or equals to DEFAULT_WIDTH
        defaultMModelUniformUpResourceShouldBeFound("width.greaterOrEqualThan=" + DEFAULT_WIDTH);

        // Get all the mModelUniformUpResourceList where width greater than or equals to UPDATED_WIDTH
        defaultMModelUniformUpResourceShouldNotBeFound("width.greaterOrEqualThan=" + UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void getAllMModelUniformUpResourcesByWidthIsLessThanSomething() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        // Get all the mModelUniformUpResourceList where width less than or equals to DEFAULT_WIDTH
        defaultMModelUniformUpResourceShouldNotBeFound("width.lessThan=" + DEFAULT_WIDTH);

        // Get all the mModelUniformUpResourceList where width less than or equals to UPDATED_WIDTH
        defaultMModelUniformUpResourceShouldBeFound("width.lessThan=" + UPDATED_WIDTH);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMModelUniformUpResourceShouldBeFound(String filter) throws Exception {
        restMModelUniformUpResourceMockMvc.perform(get("/api/m-model-uniform-up-resources?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mModelUniformUpResource.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniformTypeId").value(hasItem(DEFAULT_UNIFORM_TYPE_ID)))
            .andExpect(jsonPath("$.[*].dressingType").value(hasItem(DEFAULT_DRESSING_TYPE)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)));

        // Check, that the count call also returns 1
        restMModelUniformUpResourceMockMvc.perform(get("/api/m-model-uniform-up-resources/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMModelUniformUpResourceShouldNotBeFound(String filter) throws Exception {
        restMModelUniformUpResourceMockMvc.perform(get("/api/m-model-uniform-up-resources?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMModelUniformUpResourceMockMvc.perform(get("/api/m-model-uniform-up-resources/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMModelUniformUpResource() throws Exception {
        // Get the mModelUniformUpResource
        restMModelUniformUpResourceMockMvc.perform(get("/api/m-model-uniform-up-resources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMModelUniformUpResource() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        int databaseSizeBeforeUpdate = mModelUniformUpResourceRepository.findAll().size();

        // Update the mModelUniformUpResource
        MModelUniformUpResource updatedMModelUniformUpResource = mModelUniformUpResourceRepository.findById(mModelUniformUpResource.getId()).get();
        // Disconnect from session so that the updates on updatedMModelUniformUpResource are not directly saved in db
        em.detach(updatedMModelUniformUpResource);
        updatedMModelUniformUpResource
            .uniformTypeId(UPDATED_UNIFORM_TYPE_ID)
            .dressingType(UPDATED_DRESSING_TYPE)
            .width(UPDATED_WIDTH);
        MModelUniformUpResourceDTO mModelUniformUpResourceDTO = mModelUniformUpResourceMapper.toDto(updatedMModelUniformUpResource);

        restMModelUniformUpResourceMockMvc.perform(put("/api/m-model-uniform-up-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpResourceDTO)))
            .andExpect(status().isOk());

        // Validate the MModelUniformUpResource in the database
        List<MModelUniformUpResource> mModelUniformUpResourceList = mModelUniformUpResourceRepository.findAll();
        assertThat(mModelUniformUpResourceList).hasSize(databaseSizeBeforeUpdate);
        MModelUniformUpResource testMModelUniformUpResource = mModelUniformUpResourceList.get(mModelUniformUpResourceList.size() - 1);
        assertThat(testMModelUniformUpResource.getUniformTypeId()).isEqualTo(UPDATED_UNIFORM_TYPE_ID);
        assertThat(testMModelUniformUpResource.getDressingType()).isEqualTo(UPDATED_DRESSING_TYPE);
        assertThat(testMModelUniformUpResource.getWidth()).isEqualTo(UPDATED_WIDTH);
    }

    @Test
    @Transactional
    public void updateNonExistingMModelUniformUpResource() throws Exception {
        int databaseSizeBeforeUpdate = mModelUniformUpResourceRepository.findAll().size();

        // Create the MModelUniformUpResource
        MModelUniformUpResourceDTO mModelUniformUpResourceDTO = mModelUniformUpResourceMapper.toDto(mModelUniformUpResource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMModelUniformUpResourceMockMvc.perform(put("/api/m-model-uniform-up-resources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mModelUniformUpResourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MModelUniformUpResource in the database
        List<MModelUniformUpResource> mModelUniformUpResourceList = mModelUniformUpResourceRepository.findAll();
        assertThat(mModelUniformUpResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMModelUniformUpResource() throws Exception {
        // Initialize the database
        mModelUniformUpResourceRepository.saveAndFlush(mModelUniformUpResource);

        int databaseSizeBeforeDelete = mModelUniformUpResourceRepository.findAll().size();

        // Delete the mModelUniformUpResource
        restMModelUniformUpResourceMockMvc.perform(delete("/api/m-model-uniform-up-resources/{id}", mModelUniformUpResource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MModelUniformUpResource> mModelUniformUpResourceList = mModelUniformUpResourceRepository.findAll();
        assertThat(mModelUniformUpResourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelUniformUpResource.class);
        MModelUniformUpResource mModelUniformUpResource1 = new MModelUniformUpResource();
        mModelUniformUpResource1.setId(1L);
        MModelUniformUpResource mModelUniformUpResource2 = new MModelUniformUpResource();
        mModelUniformUpResource2.setId(mModelUniformUpResource1.getId());
        assertThat(mModelUniformUpResource1).isEqualTo(mModelUniformUpResource2);
        mModelUniformUpResource2.setId(2L);
        assertThat(mModelUniformUpResource1).isNotEqualTo(mModelUniformUpResource2);
        mModelUniformUpResource1.setId(null);
        assertThat(mModelUniformUpResource1).isNotEqualTo(mModelUniformUpResource2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MModelUniformUpResourceDTO.class);
        MModelUniformUpResourceDTO mModelUniformUpResourceDTO1 = new MModelUniformUpResourceDTO();
        mModelUniformUpResourceDTO1.setId(1L);
        MModelUniformUpResourceDTO mModelUniformUpResourceDTO2 = new MModelUniformUpResourceDTO();
        assertThat(mModelUniformUpResourceDTO1).isNotEqualTo(mModelUniformUpResourceDTO2);
        mModelUniformUpResourceDTO2.setId(mModelUniformUpResourceDTO1.getId());
        assertThat(mModelUniformUpResourceDTO1).isEqualTo(mModelUniformUpResourceDTO2);
        mModelUniformUpResourceDTO2.setId(2L);
        assertThat(mModelUniformUpResourceDTO1).isNotEqualTo(mModelUniformUpResourceDTO2);
        mModelUniformUpResourceDTO1.setId(null);
        assertThat(mModelUniformUpResourceDTO1).isNotEqualTo(mModelUniformUpResourceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mModelUniformUpResourceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mModelUniformUpResourceMapper.fromId(null)).isNull();
    }
}
