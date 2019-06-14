package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MStoreReviewUrl;
import io.shm.tsubasa.repository.MStoreReviewUrlRepository;
import io.shm.tsubasa.service.MStoreReviewUrlService;
import io.shm.tsubasa.service.dto.MStoreReviewUrlDTO;
import io.shm.tsubasa.service.mapper.MStoreReviewUrlMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MStoreReviewUrlCriteria;
import io.shm.tsubasa.service.MStoreReviewUrlQueryService;

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
 * Integration tests for the {@Link MStoreReviewUrlResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MStoreReviewUrlResourceIT {

    private static final Integer DEFAULT_PLATFORM = 1;
    private static final Integer UPDATED_PLATFORM = 2;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private MStoreReviewUrlRepository mStoreReviewUrlRepository;

    @Autowired
    private MStoreReviewUrlMapper mStoreReviewUrlMapper;

    @Autowired
    private MStoreReviewUrlService mStoreReviewUrlService;

    @Autowired
    private MStoreReviewUrlQueryService mStoreReviewUrlQueryService;

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

    private MockMvc restMStoreReviewUrlMockMvc;

    private MStoreReviewUrl mStoreReviewUrl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MStoreReviewUrlResource mStoreReviewUrlResource = new MStoreReviewUrlResource(mStoreReviewUrlService, mStoreReviewUrlQueryService);
        this.restMStoreReviewUrlMockMvc = MockMvcBuilders.standaloneSetup(mStoreReviewUrlResource)
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
    public static MStoreReviewUrl createEntity(EntityManager em) {
        MStoreReviewUrl mStoreReviewUrl = new MStoreReviewUrl()
            .platform(DEFAULT_PLATFORM)
            .url(DEFAULT_URL);
        return mStoreReviewUrl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MStoreReviewUrl createUpdatedEntity(EntityManager em) {
        MStoreReviewUrl mStoreReviewUrl = new MStoreReviewUrl()
            .platform(UPDATED_PLATFORM)
            .url(UPDATED_URL);
        return mStoreReviewUrl;
    }

    @BeforeEach
    public void initTest() {
        mStoreReviewUrl = createEntity(em);
    }

    @Test
    @Transactional
    public void createMStoreReviewUrl() throws Exception {
        int databaseSizeBeforeCreate = mStoreReviewUrlRepository.findAll().size();

        // Create the MStoreReviewUrl
        MStoreReviewUrlDTO mStoreReviewUrlDTO = mStoreReviewUrlMapper.toDto(mStoreReviewUrl);
        restMStoreReviewUrlMockMvc.perform(post("/api/m-store-review-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoreReviewUrlDTO)))
            .andExpect(status().isCreated());

        // Validate the MStoreReviewUrl in the database
        List<MStoreReviewUrl> mStoreReviewUrlList = mStoreReviewUrlRepository.findAll();
        assertThat(mStoreReviewUrlList).hasSize(databaseSizeBeforeCreate + 1);
        MStoreReviewUrl testMStoreReviewUrl = mStoreReviewUrlList.get(mStoreReviewUrlList.size() - 1);
        assertThat(testMStoreReviewUrl.getPlatform()).isEqualTo(DEFAULT_PLATFORM);
        assertThat(testMStoreReviewUrl.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createMStoreReviewUrlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mStoreReviewUrlRepository.findAll().size();

        // Create the MStoreReviewUrl with an existing ID
        mStoreReviewUrl.setId(1L);
        MStoreReviewUrlDTO mStoreReviewUrlDTO = mStoreReviewUrlMapper.toDto(mStoreReviewUrl);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMStoreReviewUrlMockMvc.perform(post("/api/m-store-review-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoreReviewUrlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MStoreReviewUrl in the database
        List<MStoreReviewUrl> mStoreReviewUrlList = mStoreReviewUrlRepository.findAll();
        assertThat(mStoreReviewUrlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPlatformIsRequired() throws Exception {
        int databaseSizeBeforeTest = mStoreReviewUrlRepository.findAll().size();
        // set the field null
        mStoreReviewUrl.setPlatform(null);

        // Create the MStoreReviewUrl, which fails.
        MStoreReviewUrlDTO mStoreReviewUrlDTO = mStoreReviewUrlMapper.toDto(mStoreReviewUrl);

        restMStoreReviewUrlMockMvc.perform(post("/api/m-store-review-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoreReviewUrlDTO)))
            .andExpect(status().isBadRequest());

        List<MStoreReviewUrl> mStoreReviewUrlList = mStoreReviewUrlRepository.findAll();
        assertThat(mStoreReviewUrlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMStoreReviewUrls() throws Exception {
        // Initialize the database
        mStoreReviewUrlRepository.saveAndFlush(mStoreReviewUrl);

        // Get all the mStoreReviewUrlList
        restMStoreReviewUrlMockMvc.perform(get("/api/m-store-review-urls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mStoreReviewUrl.getId().intValue())))
            .andExpect(jsonPath("$.[*].platform").value(hasItem(DEFAULT_PLATFORM)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getMStoreReviewUrl() throws Exception {
        // Initialize the database
        mStoreReviewUrlRepository.saveAndFlush(mStoreReviewUrl);

        // Get the mStoreReviewUrl
        restMStoreReviewUrlMockMvc.perform(get("/api/m-store-review-urls/{id}", mStoreReviewUrl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mStoreReviewUrl.getId().intValue()))
            .andExpect(jsonPath("$.platform").value(DEFAULT_PLATFORM))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()));
    }

    @Test
    @Transactional
    public void getAllMStoreReviewUrlsByPlatformIsEqualToSomething() throws Exception {
        // Initialize the database
        mStoreReviewUrlRepository.saveAndFlush(mStoreReviewUrl);

        // Get all the mStoreReviewUrlList where platform equals to DEFAULT_PLATFORM
        defaultMStoreReviewUrlShouldBeFound("platform.equals=" + DEFAULT_PLATFORM);

        // Get all the mStoreReviewUrlList where platform equals to UPDATED_PLATFORM
        defaultMStoreReviewUrlShouldNotBeFound("platform.equals=" + UPDATED_PLATFORM);
    }

    @Test
    @Transactional
    public void getAllMStoreReviewUrlsByPlatformIsInShouldWork() throws Exception {
        // Initialize the database
        mStoreReviewUrlRepository.saveAndFlush(mStoreReviewUrl);

        // Get all the mStoreReviewUrlList where platform in DEFAULT_PLATFORM or UPDATED_PLATFORM
        defaultMStoreReviewUrlShouldBeFound("platform.in=" + DEFAULT_PLATFORM + "," + UPDATED_PLATFORM);

        // Get all the mStoreReviewUrlList where platform equals to UPDATED_PLATFORM
        defaultMStoreReviewUrlShouldNotBeFound("platform.in=" + UPDATED_PLATFORM);
    }

    @Test
    @Transactional
    public void getAllMStoreReviewUrlsByPlatformIsNullOrNotNull() throws Exception {
        // Initialize the database
        mStoreReviewUrlRepository.saveAndFlush(mStoreReviewUrl);

        // Get all the mStoreReviewUrlList where platform is not null
        defaultMStoreReviewUrlShouldBeFound("platform.specified=true");

        // Get all the mStoreReviewUrlList where platform is null
        defaultMStoreReviewUrlShouldNotBeFound("platform.specified=false");
    }

    @Test
    @Transactional
    public void getAllMStoreReviewUrlsByPlatformIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mStoreReviewUrlRepository.saveAndFlush(mStoreReviewUrl);

        // Get all the mStoreReviewUrlList where platform greater than or equals to DEFAULT_PLATFORM
        defaultMStoreReviewUrlShouldBeFound("platform.greaterOrEqualThan=" + DEFAULT_PLATFORM);

        // Get all the mStoreReviewUrlList where platform greater than or equals to UPDATED_PLATFORM
        defaultMStoreReviewUrlShouldNotBeFound("platform.greaterOrEqualThan=" + UPDATED_PLATFORM);
    }

    @Test
    @Transactional
    public void getAllMStoreReviewUrlsByPlatformIsLessThanSomething() throws Exception {
        // Initialize the database
        mStoreReviewUrlRepository.saveAndFlush(mStoreReviewUrl);

        // Get all the mStoreReviewUrlList where platform less than or equals to DEFAULT_PLATFORM
        defaultMStoreReviewUrlShouldNotBeFound("platform.lessThan=" + DEFAULT_PLATFORM);

        // Get all the mStoreReviewUrlList where platform less than or equals to UPDATED_PLATFORM
        defaultMStoreReviewUrlShouldBeFound("platform.lessThan=" + UPDATED_PLATFORM);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMStoreReviewUrlShouldBeFound(String filter) throws Exception {
        restMStoreReviewUrlMockMvc.perform(get("/api/m-store-review-urls?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mStoreReviewUrl.getId().intValue())))
            .andExpect(jsonPath("$.[*].platform").value(hasItem(DEFAULT_PLATFORM)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())));

        // Check, that the count call also returns 1
        restMStoreReviewUrlMockMvc.perform(get("/api/m-store-review-urls/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMStoreReviewUrlShouldNotBeFound(String filter) throws Exception {
        restMStoreReviewUrlMockMvc.perform(get("/api/m-store-review-urls?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMStoreReviewUrlMockMvc.perform(get("/api/m-store-review-urls/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMStoreReviewUrl() throws Exception {
        // Get the mStoreReviewUrl
        restMStoreReviewUrlMockMvc.perform(get("/api/m-store-review-urls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMStoreReviewUrl() throws Exception {
        // Initialize the database
        mStoreReviewUrlRepository.saveAndFlush(mStoreReviewUrl);

        int databaseSizeBeforeUpdate = mStoreReviewUrlRepository.findAll().size();

        // Update the mStoreReviewUrl
        MStoreReviewUrl updatedMStoreReviewUrl = mStoreReviewUrlRepository.findById(mStoreReviewUrl.getId()).get();
        // Disconnect from session so that the updates on updatedMStoreReviewUrl are not directly saved in db
        em.detach(updatedMStoreReviewUrl);
        updatedMStoreReviewUrl
            .platform(UPDATED_PLATFORM)
            .url(UPDATED_URL);
        MStoreReviewUrlDTO mStoreReviewUrlDTO = mStoreReviewUrlMapper.toDto(updatedMStoreReviewUrl);

        restMStoreReviewUrlMockMvc.perform(put("/api/m-store-review-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoreReviewUrlDTO)))
            .andExpect(status().isOk());

        // Validate the MStoreReviewUrl in the database
        List<MStoreReviewUrl> mStoreReviewUrlList = mStoreReviewUrlRepository.findAll();
        assertThat(mStoreReviewUrlList).hasSize(databaseSizeBeforeUpdate);
        MStoreReviewUrl testMStoreReviewUrl = mStoreReviewUrlList.get(mStoreReviewUrlList.size() - 1);
        assertThat(testMStoreReviewUrl.getPlatform()).isEqualTo(UPDATED_PLATFORM);
        assertThat(testMStoreReviewUrl.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingMStoreReviewUrl() throws Exception {
        int databaseSizeBeforeUpdate = mStoreReviewUrlRepository.findAll().size();

        // Create the MStoreReviewUrl
        MStoreReviewUrlDTO mStoreReviewUrlDTO = mStoreReviewUrlMapper.toDto(mStoreReviewUrl);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMStoreReviewUrlMockMvc.perform(put("/api/m-store-review-urls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mStoreReviewUrlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MStoreReviewUrl in the database
        List<MStoreReviewUrl> mStoreReviewUrlList = mStoreReviewUrlRepository.findAll();
        assertThat(mStoreReviewUrlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMStoreReviewUrl() throws Exception {
        // Initialize the database
        mStoreReviewUrlRepository.saveAndFlush(mStoreReviewUrl);

        int databaseSizeBeforeDelete = mStoreReviewUrlRepository.findAll().size();

        // Delete the mStoreReviewUrl
        restMStoreReviewUrlMockMvc.perform(delete("/api/m-store-review-urls/{id}", mStoreReviewUrl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MStoreReviewUrl> mStoreReviewUrlList = mStoreReviewUrlRepository.findAll();
        assertThat(mStoreReviewUrlList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MStoreReviewUrl.class);
        MStoreReviewUrl mStoreReviewUrl1 = new MStoreReviewUrl();
        mStoreReviewUrl1.setId(1L);
        MStoreReviewUrl mStoreReviewUrl2 = new MStoreReviewUrl();
        mStoreReviewUrl2.setId(mStoreReviewUrl1.getId());
        assertThat(mStoreReviewUrl1).isEqualTo(mStoreReviewUrl2);
        mStoreReviewUrl2.setId(2L);
        assertThat(mStoreReviewUrl1).isNotEqualTo(mStoreReviewUrl2);
        mStoreReviewUrl1.setId(null);
        assertThat(mStoreReviewUrl1).isNotEqualTo(mStoreReviewUrl2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MStoreReviewUrlDTO.class);
        MStoreReviewUrlDTO mStoreReviewUrlDTO1 = new MStoreReviewUrlDTO();
        mStoreReviewUrlDTO1.setId(1L);
        MStoreReviewUrlDTO mStoreReviewUrlDTO2 = new MStoreReviewUrlDTO();
        assertThat(mStoreReviewUrlDTO1).isNotEqualTo(mStoreReviewUrlDTO2);
        mStoreReviewUrlDTO2.setId(mStoreReviewUrlDTO1.getId());
        assertThat(mStoreReviewUrlDTO1).isEqualTo(mStoreReviewUrlDTO2);
        mStoreReviewUrlDTO2.setId(2L);
        assertThat(mStoreReviewUrlDTO1).isNotEqualTo(mStoreReviewUrlDTO2);
        mStoreReviewUrlDTO1.setId(null);
        assertThat(mStoreReviewUrlDTO1).isNotEqualTo(mStoreReviewUrlDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mStoreReviewUrlMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mStoreReviewUrlMapper.fromId(null)).isNull();
    }
}
