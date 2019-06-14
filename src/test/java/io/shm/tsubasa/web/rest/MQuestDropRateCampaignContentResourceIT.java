package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MQuestDropRateCampaignContent;
import io.shm.tsubasa.repository.MQuestDropRateCampaignContentRepository;
import io.shm.tsubasa.service.MQuestDropRateCampaignContentService;
import io.shm.tsubasa.service.dto.MQuestDropRateCampaignContentDTO;
import io.shm.tsubasa.service.mapper.MQuestDropRateCampaignContentMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MQuestDropRateCampaignContentCriteria;
import io.shm.tsubasa.service.MQuestDropRateCampaignContentQueryService;

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
 * Integration tests for the {@Link MQuestDropRateCampaignContentResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MQuestDropRateCampaignContentResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CONTENT_TYPE = 1;
    private static final Integer UPDATED_CONTENT_TYPE = 2;

    private static final Integer DEFAULT_CONTENT_ID = 1;
    private static final Integer UPDATED_CONTENT_ID = 2;

    private static final Integer DEFAULT_RATE = 1;
    private static final Integer UPDATED_RATE = 2;

    @Autowired
    private MQuestDropRateCampaignContentRepository mQuestDropRateCampaignContentRepository;

    @Autowired
    private MQuestDropRateCampaignContentMapper mQuestDropRateCampaignContentMapper;

    @Autowired
    private MQuestDropRateCampaignContentService mQuestDropRateCampaignContentService;

    @Autowired
    private MQuestDropRateCampaignContentQueryService mQuestDropRateCampaignContentQueryService;

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

    private MockMvc restMQuestDropRateCampaignContentMockMvc;

    private MQuestDropRateCampaignContent mQuestDropRateCampaignContent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MQuestDropRateCampaignContentResource mQuestDropRateCampaignContentResource = new MQuestDropRateCampaignContentResource(mQuestDropRateCampaignContentService, mQuestDropRateCampaignContentQueryService);
        this.restMQuestDropRateCampaignContentMockMvc = MockMvcBuilders.standaloneSetup(mQuestDropRateCampaignContentResource)
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
    public static MQuestDropRateCampaignContent createEntity(EntityManager em) {
        MQuestDropRateCampaignContent mQuestDropRateCampaignContent = new MQuestDropRateCampaignContent()
            .groupId(DEFAULT_GROUP_ID)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contentId(DEFAULT_CONTENT_ID)
            .rate(DEFAULT_RATE);
        return mQuestDropRateCampaignContent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MQuestDropRateCampaignContent createUpdatedEntity(EntityManager em) {
        MQuestDropRateCampaignContent mQuestDropRateCampaignContent = new MQuestDropRateCampaignContent()
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .rate(UPDATED_RATE);
        return mQuestDropRateCampaignContent;
    }

    @BeforeEach
    public void initTest() {
        mQuestDropRateCampaignContent = createEntity(em);
    }

    @Test
    @Transactional
    public void createMQuestDropRateCampaignContent() throws Exception {
        int databaseSizeBeforeCreate = mQuestDropRateCampaignContentRepository.findAll().size();

        // Create the MQuestDropRateCampaignContent
        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO = mQuestDropRateCampaignContentMapper.toDto(mQuestDropRateCampaignContent);
        restMQuestDropRateCampaignContentMockMvc.perform(post("/api/m-quest-drop-rate-campaign-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestDropRateCampaignContentDTO)))
            .andExpect(status().isCreated());

        // Validate the MQuestDropRateCampaignContent in the database
        List<MQuestDropRateCampaignContent> mQuestDropRateCampaignContentList = mQuestDropRateCampaignContentRepository.findAll();
        assertThat(mQuestDropRateCampaignContentList).hasSize(databaseSizeBeforeCreate + 1);
        MQuestDropRateCampaignContent testMQuestDropRateCampaignContent = mQuestDropRateCampaignContentList.get(mQuestDropRateCampaignContentList.size() - 1);
        assertThat(testMQuestDropRateCampaignContent.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMQuestDropRateCampaignContent.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMQuestDropRateCampaignContent.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testMQuestDropRateCampaignContent.getRate()).isEqualTo(DEFAULT_RATE);
    }

    @Test
    @Transactional
    public void createMQuestDropRateCampaignContentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mQuestDropRateCampaignContentRepository.findAll().size();

        // Create the MQuestDropRateCampaignContent with an existing ID
        mQuestDropRateCampaignContent.setId(1L);
        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO = mQuestDropRateCampaignContentMapper.toDto(mQuestDropRateCampaignContent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMQuestDropRateCampaignContentMockMvc.perform(post("/api/m-quest-drop-rate-campaign-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestDropRateCampaignContentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestDropRateCampaignContent in the database
        List<MQuestDropRateCampaignContent> mQuestDropRateCampaignContentList = mQuestDropRateCampaignContentRepository.findAll();
        assertThat(mQuestDropRateCampaignContentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestDropRateCampaignContentRepository.findAll().size();
        // set the field null
        mQuestDropRateCampaignContent.setGroupId(null);

        // Create the MQuestDropRateCampaignContent, which fails.
        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO = mQuestDropRateCampaignContentMapper.toDto(mQuestDropRateCampaignContent);

        restMQuestDropRateCampaignContentMockMvc.perform(post("/api/m-quest-drop-rate-campaign-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestDropRateCampaignContentDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestDropRateCampaignContent> mQuestDropRateCampaignContentList = mQuestDropRateCampaignContentRepository.findAll();
        assertThat(mQuestDropRateCampaignContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestDropRateCampaignContentRepository.findAll().size();
        // set the field null
        mQuestDropRateCampaignContent.setContentType(null);

        // Create the MQuestDropRateCampaignContent, which fails.
        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO = mQuestDropRateCampaignContentMapper.toDto(mQuestDropRateCampaignContent);

        restMQuestDropRateCampaignContentMockMvc.perform(post("/api/m-quest-drop-rate-campaign-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestDropRateCampaignContentDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestDropRateCampaignContent> mQuestDropRateCampaignContentList = mQuestDropRateCampaignContentRepository.findAll();
        assertThat(mQuestDropRateCampaignContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mQuestDropRateCampaignContentRepository.findAll().size();
        // set the field null
        mQuestDropRateCampaignContent.setRate(null);

        // Create the MQuestDropRateCampaignContent, which fails.
        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO = mQuestDropRateCampaignContentMapper.toDto(mQuestDropRateCampaignContent);

        restMQuestDropRateCampaignContentMockMvc.perform(post("/api/m-quest-drop-rate-campaign-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestDropRateCampaignContentDTO)))
            .andExpect(status().isBadRequest());

        List<MQuestDropRateCampaignContent> mQuestDropRateCampaignContentList = mQuestDropRateCampaignContentRepository.findAll();
        assertThat(mQuestDropRateCampaignContentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContents() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList
        restMQuestDropRateCampaignContentMockMvc.perform(get("/api/m-quest-drop-rate-campaign-contents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestDropRateCampaignContent.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)));
    }
    
    @Test
    @Transactional
    public void getMQuestDropRateCampaignContent() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get the mQuestDropRateCampaignContent
        restMQuestDropRateCampaignContentMockMvc.perform(get("/api/m-quest-drop-rate-campaign-contents/{id}", mQuestDropRateCampaignContent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mQuestDropRateCampaignContent.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE));
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where groupId equals to DEFAULT_GROUP_ID
        defaultMQuestDropRateCampaignContentShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mQuestDropRateCampaignContentList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestDropRateCampaignContentShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMQuestDropRateCampaignContentShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mQuestDropRateCampaignContentList where groupId equals to UPDATED_GROUP_ID
        defaultMQuestDropRateCampaignContentShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where groupId is not null
        defaultMQuestDropRateCampaignContentShouldBeFound("groupId.specified=true");

        // Get all the mQuestDropRateCampaignContentList where groupId is null
        defaultMQuestDropRateCampaignContentShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMQuestDropRateCampaignContentShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestDropRateCampaignContentList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMQuestDropRateCampaignContentShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMQuestDropRateCampaignContentShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mQuestDropRateCampaignContentList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMQuestDropRateCampaignContentShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentType equals to DEFAULT_CONTENT_TYPE
        defaultMQuestDropRateCampaignContentShouldBeFound("contentType.equals=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestDropRateCampaignContentList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentType.equals=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentType in DEFAULT_CONTENT_TYPE or UPDATED_CONTENT_TYPE
        defaultMQuestDropRateCampaignContentShouldBeFound("contentType.in=" + DEFAULT_CONTENT_TYPE + "," + UPDATED_CONTENT_TYPE);

        // Get all the mQuestDropRateCampaignContentList where contentType equals to UPDATED_CONTENT_TYPE
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentType.in=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentType is not null
        defaultMQuestDropRateCampaignContentShouldBeFound("contentType.specified=true");

        // Get all the mQuestDropRateCampaignContentList where contentType is null
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentType greater than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestDropRateCampaignContentShouldBeFound("contentType.greaterOrEqualThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestDropRateCampaignContentList where contentType greater than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentType.greaterOrEqualThan=" + UPDATED_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentType less than or equals to DEFAULT_CONTENT_TYPE
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentType.lessThan=" + DEFAULT_CONTENT_TYPE);

        // Get all the mQuestDropRateCampaignContentList where contentType less than or equals to UPDATED_CONTENT_TYPE
        defaultMQuestDropRateCampaignContentShouldBeFound("contentType.lessThan=" + UPDATED_CONTENT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentId equals to DEFAULT_CONTENT_ID
        defaultMQuestDropRateCampaignContentShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestDropRateCampaignContentList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultMQuestDropRateCampaignContentShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the mQuestDropRateCampaignContentList where contentId equals to UPDATED_CONTENT_ID
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentId is not null
        defaultMQuestDropRateCampaignContentShouldBeFound("contentId.specified=true");

        // Get all the mQuestDropRateCampaignContentList where contentId is null
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentId greater than or equals to DEFAULT_CONTENT_ID
        defaultMQuestDropRateCampaignContentShouldBeFound("contentId.greaterOrEqualThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestDropRateCampaignContentList where contentId greater than or equals to UPDATED_CONTENT_ID
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentId.greaterOrEqualThan=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByContentIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where contentId less than or equals to DEFAULT_CONTENT_ID
        defaultMQuestDropRateCampaignContentShouldNotBeFound("contentId.lessThan=" + DEFAULT_CONTENT_ID);

        // Get all the mQuestDropRateCampaignContentList where contentId less than or equals to UPDATED_CONTENT_ID
        defaultMQuestDropRateCampaignContentShouldBeFound("contentId.lessThan=" + UPDATED_CONTENT_ID);
    }


    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByRateIsEqualToSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where rate equals to DEFAULT_RATE
        defaultMQuestDropRateCampaignContentShouldBeFound("rate.equals=" + DEFAULT_RATE);

        // Get all the mQuestDropRateCampaignContentList where rate equals to UPDATED_RATE
        defaultMQuestDropRateCampaignContentShouldNotBeFound("rate.equals=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByRateIsInShouldWork() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where rate in DEFAULT_RATE or UPDATED_RATE
        defaultMQuestDropRateCampaignContentShouldBeFound("rate.in=" + DEFAULT_RATE + "," + UPDATED_RATE);

        // Get all the mQuestDropRateCampaignContentList where rate equals to UPDATED_RATE
        defaultMQuestDropRateCampaignContentShouldNotBeFound("rate.in=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByRateIsNullOrNotNull() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where rate is not null
        defaultMQuestDropRateCampaignContentShouldBeFound("rate.specified=true");

        // Get all the mQuestDropRateCampaignContentList where rate is null
        defaultMQuestDropRateCampaignContentShouldNotBeFound("rate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByRateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where rate greater than or equals to DEFAULT_RATE
        defaultMQuestDropRateCampaignContentShouldBeFound("rate.greaterOrEqualThan=" + DEFAULT_RATE);

        // Get all the mQuestDropRateCampaignContentList where rate greater than or equals to UPDATED_RATE
        defaultMQuestDropRateCampaignContentShouldNotBeFound("rate.greaterOrEqualThan=" + UPDATED_RATE);
    }

    @Test
    @Transactional
    public void getAllMQuestDropRateCampaignContentsByRateIsLessThanSomething() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        // Get all the mQuestDropRateCampaignContentList where rate less than or equals to DEFAULT_RATE
        defaultMQuestDropRateCampaignContentShouldNotBeFound("rate.lessThan=" + DEFAULT_RATE);

        // Get all the mQuestDropRateCampaignContentList where rate less than or equals to UPDATED_RATE
        defaultMQuestDropRateCampaignContentShouldBeFound("rate.lessThan=" + UPDATED_RATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMQuestDropRateCampaignContentShouldBeFound(String filter) throws Exception {
        restMQuestDropRateCampaignContentMockMvc.perform(get("/api/m-quest-drop-rate-campaign-contents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mQuestDropRateCampaignContent.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)));

        // Check, that the count call also returns 1
        restMQuestDropRateCampaignContentMockMvc.perform(get("/api/m-quest-drop-rate-campaign-contents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMQuestDropRateCampaignContentShouldNotBeFound(String filter) throws Exception {
        restMQuestDropRateCampaignContentMockMvc.perform(get("/api/m-quest-drop-rate-campaign-contents?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMQuestDropRateCampaignContentMockMvc.perform(get("/api/m-quest-drop-rate-campaign-contents/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMQuestDropRateCampaignContent() throws Exception {
        // Get the mQuestDropRateCampaignContent
        restMQuestDropRateCampaignContentMockMvc.perform(get("/api/m-quest-drop-rate-campaign-contents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMQuestDropRateCampaignContent() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        int databaseSizeBeforeUpdate = mQuestDropRateCampaignContentRepository.findAll().size();

        // Update the mQuestDropRateCampaignContent
        MQuestDropRateCampaignContent updatedMQuestDropRateCampaignContent = mQuestDropRateCampaignContentRepository.findById(mQuestDropRateCampaignContent.getId()).get();
        // Disconnect from session so that the updates on updatedMQuestDropRateCampaignContent are not directly saved in db
        em.detach(updatedMQuestDropRateCampaignContent);
        updatedMQuestDropRateCampaignContent
            .groupId(UPDATED_GROUP_ID)
            .contentType(UPDATED_CONTENT_TYPE)
            .contentId(UPDATED_CONTENT_ID)
            .rate(UPDATED_RATE);
        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO = mQuestDropRateCampaignContentMapper.toDto(updatedMQuestDropRateCampaignContent);

        restMQuestDropRateCampaignContentMockMvc.perform(put("/api/m-quest-drop-rate-campaign-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestDropRateCampaignContentDTO)))
            .andExpect(status().isOk());

        // Validate the MQuestDropRateCampaignContent in the database
        List<MQuestDropRateCampaignContent> mQuestDropRateCampaignContentList = mQuestDropRateCampaignContentRepository.findAll();
        assertThat(mQuestDropRateCampaignContentList).hasSize(databaseSizeBeforeUpdate);
        MQuestDropRateCampaignContent testMQuestDropRateCampaignContent = mQuestDropRateCampaignContentList.get(mQuestDropRateCampaignContentList.size() - 1);
        assertThat(testMQuestDropRateCampaignContent.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMQuestDropRateCampaignContent.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMQuestDropRateCampaignContent.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testMQuestDropRateCampaignContent.getRate()).isEqualTo(UPDATED_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMQuestDropRateCampaignContent() throws Exception {
        int databaseSizeBeforeUpdate = mQuestDropRateCampaignContentRepository.findAll().size();

        // Create the MQuestDropRateCampaignContent
        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO = mQuestDropRateCampaignContentMapper.toDto(mQuestDropRateCampaignContent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMQuestDropRateCampaignContentMockMvc.perform(put("/api/m-quest-drop-rate-campaign-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mQuestDropRateCampaignContentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MQuestDropRateCampaignContent in the database
        List<MQuestDropRateCampaignContent> mQuestDropRateCampaignContentList = mQuestDropRateCampaignContentRepository.findAll();
        assertThat(mQuestDropRateCampaignContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMQuestDropRateCampaignContent() throws Exception {
        // Initialize the database
        mQuestDropRateCampaignContentRepository.saveAndFlush(mQuestDropRateCampaignContent);

        int databaseSizeBeforeDelete = mQuestDropRateCampaignContentRepository.findAll().size();

        // Delete the mQuestDropRateCampaignContent
        restMQuestDropRateCampaignContentMockMvc.perform(delete("/api/m-quest-drop-rate-campaign-contents/{id}", mQuestDropRateCampaignContent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MQuestDropRateCampaignContent> mQuestDropRateCampaignContentList = mQuestDropRateCampaignContentRepository.findAll();
        assertThat(mQuestDropRateCampaignContentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestDropRateCampaignContent.class);
        MQuestDropRateCampaignContent mQuestDropRateCampaignContent1 = new MQuestDropRateCampaignContent();
        mQuestDropRateCampaignContent1.setId(1L);
        MQuestDropRateCampaignContent mQuestDropRateCampaignContent2 = new MQuestDropRateCampaignContent();
        mQuestDropRateCampaignContent2.setId(mQuestDropRateCampaignContent1.getId());
        assertThat(mQuestDropRateCampaignContent1).isEqualTo(mQuestDropRateCampaignContent2);
        mQuestDropRateCampaignContent2.setId(2L);
        assertThat(mQuestDropRateCampaignContent1).isNotEqualTo(mQuestDropRateCampaignContent2);
        mQuestDropRateCampaignContent1.setId(null);
        assertThat(mQuestDropRateCampaignContent1).isNotEqualTo(mQuestDropRateCampaignContent2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MQuestDropRateCampaignContentDTO.class);
        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO1 = new MQuestDropRateCampaignContentDTO();
        mQuestDropRateCampaignContentDTO1.setId(1L);
        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO2 = new MQuestDropRateCampaignContentDTO();
        assertThat(mQuestDropRateCampaignContentDTO1).isNotEqualTo(mQuestDropRateCampaignContentDTO2);
        mQuestDropRateCampaignContentDTO2.setId(mQuestDropRateCampaignContentDTO1.getId());
        assertThat(mQuestDropRateCampaignContentDTO1).isEqualTo(mQuestDropRateCampaignContentDTO2);
        mQuestDropRateCampaignContentDTO2.setId(2L);
        assertThat(mQuestDropRateCampaignContentDTO1).isNotEqualTo(mQuestDropRateCampaignContentDTO2);
        mQuestDropRateCampaignContentDTO1.setId(null);
        assertThat(mQuestDropRateCampaignContentDTO1).isNotEqualTo(mQuestDropRateCampaignContentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mQuestDropRateCampaignContentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mQuestDropRateCampaignContentMapper.fromId(null)).isNull();
    }
}
