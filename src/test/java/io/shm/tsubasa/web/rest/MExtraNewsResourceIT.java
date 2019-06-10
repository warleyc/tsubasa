package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MExtraNews;
import io.shm.tsubasa.repository.MExtraNewsRepository;
import io.shm.tsubasa.service.MExtraNewsService;
import io.shm.tsubasa.service.dto.MExtraNewsDTO;
import io.shm.tsubasa.service.mapper.MExtraNewsMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MExtraNewsCriteria;
import io.shm.tsubasa.service.MExtraNewsQueryService;

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
 * Integration tests for the {@Link MExtraNewsResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MExtraNewsResourceIT {

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    private static final String DEFAULT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WEBVIEW_ID = "AAAAAAAAAA";
    private static final String UPDATED_WEBVIEW_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    @Autowired
    private MExtraNewsRepository mExtraNewsRepository;

    @Autowired
    private MExtraNewsMapper mExtraNewsMapper;

    @Autowired
    private MExtraNewsService mExtraNewsService;

    @Autowired
    private MExtraNewsQueryService mExtraNewsQueryService;

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

    private MockMvc restMExtraNewsMockMvc;

    private MExtraNews mExtraNews;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MExtraNewsResource mExtraNewsResource = new MExtraNewsResource(mExtraNewsService, mExtraNewsQueryService);
        this.restMExtraNewsMockMvc = MockMvcBuilders.standaloneSetup(mExtraNewsResource)
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
    public static MExtraNews createEntity(EntityManager em) {
        MExtraNews mExtraNews = new MExtraNews()
            .orderNum(DEFAULT_ORDER_NUM)
            .assetName(DEFAULT_ASSET_NAME)
            .webviewId(DEFAULT_WEBVIEW_ID)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT);
        return mExtraNews;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MExtraNews createUpdatedEntity(EntityManager em) {
        MExtraNews mExtraNews = new MExtraNews()
            .orderNum(UPDATED_ORDER_NUM)
            .assetName(UPDATED_ASSET_NAME)
            .webviewId(UPDATED_WEBVIEW_ID)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        return mExtraNews;
    }

    @BeforeEach
    public void initTest() {
        mExtraNews = createEntity(em);
    }

    @Test
    @Transactional
    public void createMExtraNews() throws Exception {
        int databaseSizeBeforeCreate = mExtraNewsRepository.findAll().size();

        // Create the MExtraNews
        MExtraNewsDTO mExtraNewsDTO = mExtraNewsMapper.toDto(mExtraNews);
        restMExtraNewsMockMvc.perform(post("/api/m-extra-news")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtraNewsDTO)))
            .andExpect(status().isCreated());

        // Validate the MExtraNews in the database
        List<MExtraNews> mExtraNewsList = mExtraNewsRepository.findAll();
        assertThat(mExtraNewsList).hasSize(databaseSizeBeforeCreate + 1);
        MExtraNews testMExtraNews = mExtraNewsList.get(mExtraNewsList.size() - 1);
        assertThat(testMExtraNews.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMExtraNews.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testMExtraNews.getWebviewId()).isEqualTo(DEFAULT_WEBVIEW_ID);
        assertThat(testMExtraNews.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMExtraNews.getEndAt()).isEqualTo(DEFAULT_END_AT);
    }

    @Test
    @Transactional
    public void createMExtraNewsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mExtraNewsRepository.findAll().size();

        // Create the MExtraNews with an existing ID
        mExtraNews.setId(1L);
        MExtraNewsDTO mExtraNewsDTO = mExtraNewsMapper.toDto(mExtraNews);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMExtraNewsMockMvc.perform(post("/api/m-extra-news")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtraNewsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MExtraNews in the database
        List<MExtraNews> mExtraNewsList = mExtraNewsRepository.findAll();
        assertThat(mExtraNewsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mExtraNewsRepository.findAll().size();
        // set the field null
        mExtraNews.setOrderNum(null);

        // Create the MExtraNews, which fails.
        MExtraNewsDTO mExtraNewsDTO = mExtraNewsMapper.toDto(mExtraNews);

        restMExtraNewsMockMvc.perform(post("/api/m-extra-news")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtraNewsDTO)))
            .andExpect(status().isBadRequest());

        List<MExtraNews> mExtraNewsList = mExtraNewsRepository.findAll();
        assertThat(mExtraNewsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mExtraNewsRepository.findAll().size();
        // set the field null
        mExtraNews.setStartAt(null);

        // Create the MExtraNews, which fails.
        MExtraNewsDTO mExtraNewsDTO = mExtraNewsMapper.toDto(mExtraNews);

        restMExtraNewsMockMvc.perform(post("/api/m-extra-news")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtraNewsDTO)))
            .andExpect(status().isBadRequest());

        List<MExtraNews> mExtraNewsList = mExtraNewsRepository.findAll();
        assertThat(mExtraNewsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mExtraNewsRepository.findAll().size();
        // set the field null
        mExtraNews.setEndAt(null);

        // Create the MExtraNews, which fails.
        MExtraNewsDTO mExtraNewsDTO = mExtraNewsMapper.toDto(mExtraNews);

        restMExtraNewsMockMvc.perform(post("/api/m-extra-news")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtraNewsDTO)))
            .andExpect(status().isBadRequest());

        List<MExtraNews> mExtraNewsList = mExtraNewsRepository.findAll();
        assertThat(mExtraNewsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMExtraNews() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList
        restMExtraNewsMockMvc.perform(get("/api/m-extra-news?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mExtraNews.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].webviewId").value(hasItem(DEFAULT_WEBVIEW_ID.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));
    }
    
    @Test
    @Transactional
    public void getMExtraNews() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get the mExtraNews
        restMExtraNewsMockMvc.perform(get("/api/m-extra-news/{id}", mExtraNews.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mExtraNews.getId().intValue()))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.assetName").value(DEFAULT_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.webviewId").value(DEFAULT_WEBVIEW_ID.toString()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT));
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMExtraNewsShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mExtraNewsList where orderNum equals to UPDATED_ORDER_NUM
        defaultMExtraNewsShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMExtraNewsShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mExtraNewsList where orderNum equals to UPDATED_ORDER_NUM
        defaultMExtraNewsShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where orderNum is not null
        defaultMExtraNewsShouldBeFound("orderNum.specified=true");

        // Get all the mExtraNewsList where orderNum is null
        defaultMExtraNewsShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMExtraNewsShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mExtraNewsList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMExtraNewsShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMExtraNewsShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mExtraNewsList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMExtraNewsShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMExtraNewsByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where startAt equals to DEFAULT_START_AT
        defaultMExtraNewsShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mExtraNewsList where startAt equals to UPDATED_START_AT
        defaultMExtraNewsShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMExtraNewsShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mExtraNewsList where startAt equals to UPDATED_START_AT
        defaultMExtraNewsShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where startAt is not null
        defaultMExtraNewsShouldBeFound("startAt.specified=true");

        // Get all the mExtraNewsList where startAt is null
        defaultMExtraNewsShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where startAt greater than or equals to DEFAULT_START_AT
        defaultMExtraNewsShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mExtraNewsList where startAt greater than or equals to UPDATED_START_AT
        defaultMExtraNewsShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where startAt less than or equals to DEFAULT_START_AT
        defaultMExtraNewsShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mExtraNewsList where startAt less than or equals to UPDATED_START_AT
        defaultMExtraNewsShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMExtraNewsByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where endAt equals to DEFAULT_END_AT
        defaultMExtraNewsShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mExtraNewsList where endAt equals to UPDATED_END_AT
        defaultMExtraNewsShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMExtraNewsShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mExtraNewsList where endAt equals to UPDATED_END_AT
        defaultMExtraNewsShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where endAt is not null
        defaultMExtraNewsShouldBeFound("endAt.specified=true");

        // Get all the mExtraNewsList where endAt is null
        defaultMExtraNewsShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where endAt greater than or equals to DEFAULT_END_AT
        defaultMExtraNewsShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mExtraNewsList where endAt greater than or equals to UPDATED_END_AT
        defaultMExtraNewsShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMExtraNewsByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        // Get all the mExtraNewsList where endAt less than or equals to DEFAULT_END_AT
        defaultMExtraNewsShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mExtraNewsList where endAt less than or equals to UPDATED_END_AT
        defaultMExtraNewsShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMExtraNewsShouldBeFound(String filter) throws Exception {
        restMExtraNewsMockMvc.perform(get("/api/m-extra-news?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mExtraNews.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].webviewId").value(hasItem(DEFAULT_WEBVIEW_ID.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));

        // Check, that the count call also returns 1
        restMExtraNewsMockMvc.perform(get("/api/m-extra-news/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMExtraNewsShouldNotBeFound(String filter) throws Exception {
        restMExtraNewsMockMvc.perform(get("/api/m-extra-news?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMExtraNewsMockMvc.perform(get("/api/m-extra-news/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMExtraNews() throws Exception {
        // Get the mExtraNews
        restMExtraNewsMockMvc.perform(get("/api/m-extra-news/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMExtraNews() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        int databaseSizeBeforeUpdate = mExtraNewsRepository.findAll().size();

        // Update the mExtraNews
        MExtraNews updatedMExtraNews = mExtraNewsRepository.findById(mExtraNews.getId()).get();
        // Disconnect from session so that the updates on updatedMExtraNews are not directly saved in db
        em.detach(updatedMExtraNews);
        updatedMExtraNews
            .orderNum(UPDATED_ORDER_NUM)
            .assetName(UPDATED_ASSET_NAME)
            .webviewId(UPDATED_WEBVIEW_ID)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        MExtraNewsDTO mExtraNewsDTO = mExtraNewsMapper.toDto(updatedMExtraNews);

        restMExtraNewsMockMvc.perform(put("/api/m-extra-news")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtraNewsDTO)))
            .andExpect(status().isOk());

        // Validate the MExtraNews in the database
        List<MExtraNews> mExtraNewsList = mExtraNewsRepository.findAll();
        assertThat(mExtraNewsList).hasSize(databaseSizeBeforeUpdate);
        MExtraNews testMExtraNews = mExtraNewsList.get(mExtraNewsList.size() - 1);
        assertThat(testMExtraNews.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMExtraNews.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testMExtraNews.getWebviewId()).isEqualTo(UPDATED_WEBVIEW_ID);
        assertThat(testMExtraNews.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMExtraNews.getEndAt()).isEqualTo(UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMExtraNews() throws Exception {
        int databaseSizeBeforeUpdate = mExtraNewsRepository.findAll().size();

        // Create the MExtraNews
        MExtraNewsDTO mExtraNewsDTO = mExtraNewsMapper.toDto(mExtraNews);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMExtraNewsMockMvc.perform(put("/api/m-extra-news")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mExtraNewsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MExtraNews in the database
        List<MExtraNews> mExtraNewsList = mExtraNewsRepository.findAll();
        assertThat(mExtraNewsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMExtraNews() throws Exception {
        // Initialize the database
        mExtraNewsRepository.saveAndFlush(mExtraNews);

        int databaseSizeBeforeDelete = mExtraNewsRepository.findAll().size();

        // Delete the mExtraNews
        restMExtraNewsMockMvc.perform(delete("/api/m-extra-news/{id}", mExtraNews.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MExtraNews> mExtraNewsList = mExtraNewsRepository.findAll();
        assertThat(mExtraNewsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MExtraNews.class);
        MExtraNews mExtraNews1 = new MExtraNews();
        mExtraNews1.setId(1L);
        MExtraNews mExtraNews2 = new MExtraNews();
        mExtraNews2.setId(mExtraNews1.getId());
        assertThat(mExtraNews1).isEqualTo(mExtraNews2);
        mExtraNews2.setId(2L);
        assertThat(mExtraNews1).isNotEqualTo(mExtraNews2);
        mExtraNews1.setId(null);
        assertThat(mExtraNews1).isNotEqualTo(mExtraNews2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MExtraNewsDTO.class);
        MExtraNewsDTO mExtraNewsDTO1 = new MExtraNewsDTO();
        mExtraNewsDTO1.setId(1L);
        MExtraNewsDTO mExtraNewsDTO2 = new MExtraNewsDTO();
        assertThat(mExtraNewsDTO1).isNotEqualTo(mExtraNewsDTO2);
        mExtraNewsDTO2.setId(mExtraNewsDTO1.getId());
        assertThat(mExtraNewsDTO1).isEqualTo(mExtraNewsDTO2);
        mExtraNewsDTO2.setId(2L);
        assertThat(mExtraNewsDTO1).isNotEqualTo(mExtraNewsDTO2);
        mExtraNewsDTO1.setId(null);
        assertThat(mExtraNewsDTO1).isNotEqualTo(mExtraNewsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mExtraNewsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mExtraNewsMapper.fromId(null)).isNull();
    }
}
