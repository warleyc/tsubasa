package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MRegulationMatchTutorialMessage;
import io.shm.tsubasa.repository.MRegulationMatchTutorialMessageRepository;
import io.shm.tsubasa.service.MRegulationMatchTutorialMessageService;
import io.shm.tsubasa.service.dto.MRegulationMatchTutorialMessageDTO;
import io.shm.tsubasa.service.mapper.MRegulationMatchTutorialMessageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MRegulationMatchTutorialMessageCriteria;
import io.shm.tsubasa.service.MRegulationMatchTutorialMessageQueryService;

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
 * Integration tests for the {@Link MRegulationMatchTutorialMessageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MRegulationMatchTutorialMessageResourceIT {

    private static final Integer DEFAULT_RULE_ID = 1;
    private static final Integer UPDATED_RULE_ID = 2;

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    private static final Integer DEFAULT_POSITION = 1;
    private static final Integer UPDATED_POSITION = 2;

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSET_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private MRegulationMatchTutorialMessageRepository mRegulationMatchTutorialMessageRepository;

    @Autowired
    private MRegulationMatchTutorialMessageMapper mRegulationMatchTutorialMessageMapper;

    @Autowired
    private MRegulationMatchTutorialMessageService mRegulationMatchTutorialMessageService;

    @Autowired
    private MRegulationMatchTutorialMessageQueryService mRegulationMatchTutorialMessageQueryService;

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

    private MockMvc restMRegulationMatchTutorialMessageMockMvc;

    private MRegulationMatchTutorialMessage mRegulationMatchTutorialMessage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MRegulationMatchTutorialMessageResource mRegulationMatchTutorialMessageResource = new MRegulationMatchTutorialMessageResource(mRegulationMatchTutorialMessageService, mRegulationMatchTutorialMessageQueryService);
        this.restMRegulationMatchTutorialMessageMockMvc = MockMvcBuilders.standaloneSetup(mRegulationMatchTutorialMessageResource)
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
    public static MRegulationMatchTutorialMessage createEntity(EntityManager em) {
        MRegulationMatchTutorialMessage mRegulationMatchTutorialMessage = new MRegulationMatchTutorialMessage()
            .ruleId(DEFAULT_RULE_ID)
            .orderNum(DEFAULT_ORDER_NUM)
            .position(DEFAULT_POSITION)
            .message(DEFAULT_MESSAGE)
            .assetName(DEFAULT_ASSET_NAME)
            .title(DEFAULT_TITLE);
        return mRegulationMatchTutorialMessage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MRegulationMatchTutorialMessage createUpdatedEntity(EntityManager em) {
        MRegulationMatchTutorialMessage mRegulationMatchTutorialMessage = new MRegulationMatchTutorialMessage()
            .ruleId(UPDATED_RULE_ID)
            .orderNum(UPDATED_ORDER_NUM)
            .position(UPDATED_POSITION)
            .message(UPDATED_MESSAGE)
            .assetName(UPDATED_ASSET_NAME)
            .title(UPDATED_TITLE);
        return mRegulationMatchTutorialMessage;
    }

    @BeforeEach
    public void initTest() {
        mRegulationMatchTutorialMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMRegulationMatchTutorialMessage() throws Exception {
        int databaseSizeBeforeCreate = mRegulationMatchTutorialMessageRepository.findAll().size();

        // Create the MRegulationMatchTutorialMessage
        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO = mRegulationMatchTutorialMessageMapper.toDto(mRegulationMatchTutorialMessage);
        restMRegulationMatchTutorialMessageMockMvc.perform(post("/api/m-regulation-match-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulationMatchTutorialMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the MRegulationMatchTutorialMessage in the database
        List<MRegulationMatchTutorialMessage> mRegulationMatchTutorialMessageList = mRegulationMatchTutorialMessageRepository.findAll();
        assertThat(mRegulationMatchTutorialMessageList).hasSize(databaseSizeBeforeCreate + 1);
        MRegulationMatchTutorialMessage testMRegulationMatchTutorialMessage = mRegulationMatchTutorialMessageList.get(mRegulationMatchTutorialMessageList.size() - 1);
        assertThat(testMRegulationMatchTutorialMessage.getRuleId()).isEqualTo(DEFAULT_RULE_ID);
        assertThat(testMRegulationMatchTutorialMessage.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMRegulationMatchTutorialMessage.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testMRegulationMatchTutorialMessage.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testMRegulationMatchTutorialMessage.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testMRegulationMatchTutorialMessage.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createMRegulationMatchTutorialMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mRegulationMatchTutorialMessageRepository.findAll().size();

        // Create the MRegulationMatchTutorialMessage with an existing ID
        mRegulationMatchTutorialMessage.setId(1L);
        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO = mRegulationMatchTutorialMessageMapper.toDto(mRegulationMatchTutorialMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMRegulationMatchTutorialMessageMockMvc.perform(post("/api/m-regulation-match-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulationMatchTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRegulationMatchTutorialMessage in the database
        List<MRegulationMatchTutorialMessage> mRegulationMatchTutorialMessageList = mRegulationMatchTutorialMessageRepository.findAll();
        assertThat(mRegulationMatchTutorialMessageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRuleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRegulationMatchTutorialMessageRepository.findAll().size();
        // set the field null
        mRegulationMatchTutorialMessage.setRuleId(null);

        // Create the MRegulationMatchTutorialMessage, which fails.
        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO = mRegulationMatchTutorialMessageMapper.toDto(mRegulationMatchTutorialMessage);

        restMRegulationMatchTutorialMessageMockMvc.perform(post("/api/m-regulation-match-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulationMatchTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MRegulationMatchTutorialMessage> mRegulationMatchTutorialMessageList = mRegulationMatchTutorialMessageRepository.findAll();
        assertThat(mRegulationMatchTutorialMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRegulationMatchTutorialMessageRepository.findAll().size();
        // set the field null
        mRegulationMatchTutorialMessage.setOrderNum(null);

        // Create the MRegulationMatchTutorialMessage, which fails.
        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO = mRegulationMatchTutorialMessageMapper.toDto(mRegulationMatchTutorialMessage);

        restMRegulationMatchTutorialMessageMockMvc.perform(post("/api/m-regulation-match-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulationMatchTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MRegulationMatchTutorialMessage> mRegulationMatchTutorialMessageList = mRegulationMatchTutorialMessageRepository.findAll();
        assertThat(mRegulationMatchTutorialMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPositionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRegulationMatchTutorialMessageRepository.findAll().size();
        // set the field null
        mRegulationMatchTutorialMessage.setPosition(null);

        // Create the MRegulationMatchTutorialMessage, which fails.
        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO = mRegulationMatchTutorialMessageMapper.toDto(mRegulationMatchTutorialMessage);

        restMRegulationMatchTutorialMessageMockMvc.perform(post("/api/m-regulation-match-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulationMatchTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MRegulationMatchTutorialMessage> mRegulationMatchTutorialMessageList = mRegulationMatchTutorialMessageRepository.findAll();
        assertThat(mRegulationMatchTutorialMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessages() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList
        restMRegulationMatchTutorialMessageMockMvc.perform(get("/api/m-regulation-match-tutorial-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRegulationMatchTutorialMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].ruleId").value(hasItem(DEFAULT_RULE_ID)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }
    
    @Test
    @Transactional
    public void getMRegulationMatchTutorialMessage() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get the mRegulationMatchTutorialMessage
        restMRegulationMatchTutorialMessageMockMvc.perform(get("/api/m-regulation-match-tutorial-messages/{id}", mRegulationMatchTutorialMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mRegulationMatchTutorialMessage.getId().intValue()))
            .andExpect(jsonPath("$.ruleId").value(DEFAULT_RULE_ID))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()))
            .andExpect(jsonPath("$.assetName").value(DEFAULT_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByRuleIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where ruleId equals to DEFAULT_RULE_ID
        defaultMRegulationMatchTutorialMessageShouldBeFound("ruleId.equals=" + DEFAULT_RULE_ID);

        // Get all the mRegulationMatchTutorialMessageList where ruleId equals to UPDATED_RULE_ID
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("ruleId.equals=" + UPDATED_RULE_ID);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByRuleIdIsInShouldWork() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where ruleId in DEFAULT_RULE_ID or UPDATED_RULE_ID
        defaultMRegulationMatchTutorialMessageShouldBeFound("ruleId.in=" + DEFAULT_RULE_ID + "," + UPDATED_RULE_ID);

        // Get all the mRegulationMatchTutorialMessageList where ruleId equals to UPDATED_RULE_ID
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("ruleId.in=" + UPDATED_RULE_ID);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByRuleIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where ruleId is not null
        defaultMRegulationMatchTutorialMessageShouldBeFound("ruleId.specified=true");

        // Get all the mRegulationMatchTutorialMessageList where ruleId is null
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("ruleId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByRuleIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where ruleId greater than or equals to DEFAULT_RULE_ID
        defaultMRegulationMatchTutorialMessageShouldBeFound("ruleId.greaterOrEqualThan=" + DEFAULT_RULE_ID);

        // Get all the mRegulationMatchTutorialMessageList where ruleId greater than or equals to UPDATED_RULE_ID
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("ruleId.greaterOrEqualThan=" + UPDATED_RULE_ID);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByRuleIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where ruleId less than or equals to DEFAULT_RULE_ID
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("ruleId.lessThan=" + DEFAULT_RULE_ID);

        // Get all the mRegulationMatchTutorialMessageList where ruleId less than or equals to UPDATED_RULE_ID
        defaultMRegulationMatchTutorialMessageShouldBeFound("ruleId.lessThan=" + UPDATED_RULE_ID);
    }


    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMRegulationMatchTutorialMessageShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mRegulationMatchTutorialMessageList where orderNum equals to UPDATED_ORDER_NUM
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMRegulationMatchTutorialMessageShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mRegulationMatchTutorialMessageList where orderNum equals to UPDATED_ORDER_NUM
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where orderNum is not null
        defaultMRegulationMatchTutorialMessageShouldBeFound("orderNum.specified=true");

        // Get all the mRegulationMatchTutorialMessageList where orderNum is null
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMRegulationMatchTutorialMessageShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mRegulationMatchTutorialMessageList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mRegulationMatchTutorialMessageList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMRegulationMatchTutorialMessageShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByPositionIsEqualToSomething() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where position equals to DEFAULT_POSITION
        defaultMRegulationMatchTutorialMessageShouldBeFound("position.equals=" + DEFAULT_POSITION);

        // Get all the mRegulationMatchTutorialMessageList where position equals to UPDATED_POSITION
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("position.equals=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByPositionIsInShouldWork() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where position in DEFAULT_POSITION or UPDATED_POSITION
        defaultMRegulationMatchTutorialMessageShouldBeFound("position.in=" + DEFAULT_POSITION + "," + UPDATED_POSITION);

        // Get all the mRegulationMatchTutorialMessageList where position equals to UPDATED_POSITION
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("position.in=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByPositionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where position is not null
        defaultMRegulationMatchTutorialMessageShouldBeFound("position.specified=true");

        // Get all the mRegulationMatchTutorialMessageList where position is null
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("position.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByPositionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where position greater than or equals to DEFAULT_POSITION
        defaultMRegulationMatchTutorialMessageShouldBeFound("position.greaterOrEqualThan=" + DEFAULT_POSITION);

        // Get all the mRegulationMatchTutorialMessageList where position greater than or equals to UPDATED_POSITION
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("position.greaterOrEqualThan=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllMRegulationMatchTutorialMessagesByPositionIsLessThanSomething() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        // Get all the mRegulationMatchTutorialMessageList where position less than or equals to DEFAULT_POSITION
        defaultMRegulationMatchTutorialMessageShouldNotBeFound("position.lessThan=" + DEFAULT_POSITION);

        // Get all the mRegulationMatchTutorialMessageList where position less than or equals to UPDATED_POSITION
        defaultMRegulationMatchTutorialMessageShouldBeFound("position.lessThan=" + UPDATED_POSITION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMRegulationMatchTutorialMessageShouldBeFound(String filter) throws Exception {
        restMRegulationMatchTutorialMessageMockMvc.perform(get("/api/m-regulation-match-tutorial-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRegulationMatchTutorialMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].ruleId").value(hasItem(DEFAULT_RULE_ID)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));

        // Check, that the count call also returns 1
        restMRegulationMatchTutorialMessageMockMvc.perform(get("/api/m-regulation-match-tutorial-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMRegulationMatchTutorialMessageShouldNotBeFound(String filter) throws Exception {
        restMRegulationMatchTutorialMessageMockMvc.perform(get("/api/m-regulation-match-tutorial-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMRegulationMatchTutorialMessageMockMvc.perform(get("/api/m-regulation-match-tutorial-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMRegulationMatchTutorialMessage() throws Exception {
        // Get the mRegulationMatchTutorialMessage
        restMRegulationMatchTutorialMessageMockMvc.perform(get("/api/m-regulation-match-tutorial-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMRegulationMatchTutorialMessage() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        int databaseSizeBeforeUpdate = mRegulationMatchTutorialMessageRepository.findAll().size();

        // Update the mRegulationMatchTutorialMessage
        MRegulationMatchTutorialMessage updatedMRegulationMatchTutorialMessage = mRegulationMatchTutorialMessageRepository.findById(mRegulationMatchTutorialMessage.getId()).get();
        // Disconnect from session so that the updates on updatedMRegulationMatchTutorialMessage are not directly saved in db
        em.detach(updatedMRegulationMatchTutorialMessage);
        updatedMRegulationMatchTutorialMessage
            .ruleId(UPDATED_RULE_ID)
            .orderNum(UPDATED_ORDER_NUM)
            .position(UPDATED_POSITION)
            .message(UPDATED_MESSAGE)
            .assetName(UPDATED_ASSET_NAME)
            .title(UPDATED_TITLE);
        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO = mRegulationMatchTutorialMessageMapper.toDto(updatedMRegulationMatchTutorialMessage);

        restMRegulationMatchTutorialMessageMockMvc.perform(put("/api/m-regulation-match-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulationMatchTutorialMessageDTO)))
            .andExpect(status().isOk());

        // Validate the MRegulationMatchTutorialMessage in the database
        List<MRegulationMatchTutorialMessage> mRegulationMatchTutorialMessageList = mRegulationMatchTutorialMessageRepository.findAll();
        assertThat(mRegulationMatchTutorialMessageList).hasSize(databaseSizeBeforeUpdate);
        MRegulationMatchTutorialMessage testMRegulationMatchTutorialMessage = mRegulationMatchTutorialMessageList.get(mRegulationMatchTutorialMessageList.size() - 1);
        assertThat(testMRegulationMatchTutorialMessage.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testMRegulationMatchTutorialMessage.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMRegulationMatchTutorialMessage.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testMRegulationMatchTutorialMessage.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testMRegulationMatchTutorialMessage.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testMRegulationMatchTutorialMessage.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingMRegulationMatchTutorialMessage() throws Exception {
        int databaseSizeBeforeUpdate = mRegulationMatchTutorialMessageRepository.findAll().size();

        // Create the MRegulationMatchTutorialMessage
        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO = mRegulationMatchTutorialMessageMapper.toDto(mRegulationMatchTutorialMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMRegulationMatchTutorialMessageMockMvc.perform(put("/api/m-regulation-match-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRegulationMatchTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRegulationMatchTutorialMessage in the database
        List<MRegulationMatchTutorialMessage> mRegulationMatchTutorialMessageList = mRegulationMatchTutorialMessageRepository.findAll();
        assertThat(mRegulationMatchTutorialMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMRegulationMatchTutorialMessage() throws Exception {
        // Initialize the database
        mRegulationMatchTutorialMessageRepository.saveAndFlush(mRegulationMatchTutorialMessage);

        int databaseSizeBeforeDelete = mRegulationMatchTutorialMessageRepository.findAll().size();

        // Delete the mRegulationMatchTutorialMessage
        restMRegulationMatchTutorialMessageMockMvc.perform(delete("/api/m-regulation-match-tutorial-messages/{id}", mRegulationMatchTutorialMessage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MRegulationMatchTutorialMessage> mRegulationMatchTutorialMessageList = mRegulationMatchTutorialMessageRepository.findAll();
        assertThat(mRegulationMatchTutorialMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRegulationMatchTutorialMessage.class);
        MRegulationMatchTutorialMessage mRegulationMatchTutorialMessage1 = new MRegulationMatchTutorialMessage();
        mRegulationMatchTutorialMessage1.setId(1L);
        MRegulationMatchTutorialMessage mRegulationMatchTutorialMessage2 = new MRegulationMatchTutorialMessage();
        mRegulationMatchTutorialMessage2.setId(mRegulationMatchTutorialMessage1.getId());
        assertThat(mRegulationMatchTutorialMessage1).isEqualTo(mRegulationMatchTutorialMessage2);
        mRegulationMatchTutorialMessage2.setId(2L);
        assertThat(mRegulationMatchTutorialMessage1).isNotEqualTo(mRegulationMatchTutorialMessage2);
        mRegulationMatchTutorialMessage1.setId(null);
        assertThat(mRegulationMatchTutorialMessage1).isNotEqualTo(mRegulationMatchTutorialMessage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRegulationMatchTutorialMessageDTO.class);
        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO1 = new MRegulationMatchTutorialMessageDTO();
        mRegulationMatchTutorialMessageDTO1.setId(1L);
        MRegulationMatchTutorialMessageDTO mRegulationMatchTutorialMessageDTO2 = new MRegulationMatchTutorialMessageDTO();
        assertThat(mRegulationMatchTutorialMessageDTO1).isNotEqualTo(mRegulationMatchTutorialMessageDTO2);
        mRegulationMatchTutorialMessageDTO2.setId(mRegulationMatchTutorialMessageDTO1.getId());
        assertThat(mRegulationMatchTutorialMessageDTO1).isEqualTo(mRegulationMatchTutorialMessageDTO2);
        mRegulationMatchTutorialMessageDTO2.setId(2L);
        assertThat(mRegulationMatchTutorialMessageDTO1).isNotEqualTo(mRegulationMatchTutorialMessageDTO2);
        mRegulationMatchTutorialMessageDTO1.setId(null);
        assertThat(mRegulationMatchTutorialMessageDTO1).isNotEqualTo(mRegulationMatchTutorialMessageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mRegulationMatchTutorialMessageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mRegulationMatchTutorialMessageMapper.fromId(null)).isNull();
    }
}
