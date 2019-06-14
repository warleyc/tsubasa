package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MSceneTutorialMessage;
import io.shm.tsubasa.repository.MSceneTutorialMessageRepository;
import io.shm.tsubasa.service.MSceneTutorialMessageService;
import io.shm.tsubasa.service.dto.MSceneTutorialMessageDTO;
import io.shm.tsubasa.service.mapper.MSceneTutorialMessageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MSceneTutorialMessageCriteria;
import io.shm.tsubasa.service.MSceneTutorialMessageQueryService;

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
 * Integration tests for the {@Link MSceneTutorialMessageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MSceneTutorialMessageResourceIT {

    private static final Integer DEFAULT_TARGET = 1;
    private static final Integer UPDATED_TARGET = 2;

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
    private MSceneTutorialMessageRepository mSceneTutorialMessageRepository;

    @Autowired
    private MSceneTutorialMessageMapper mSceneTutorialMessageMapper;

    @Autowired
    private MSceneTutorialMessageService mSceneTutorialMessageService;

    @Autowired
    private MSceneTutorialMessageQueryService mSceneTutorialMessageQueryService;

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

    private MockMvc restMSceneTutorialMessageMockMvc;

    private MSceneTutorialMessage mSceneTutorialMessage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MSceneTutorialMessageResource mSceneTutorialMessageResource = new MSceneTutorialMessageResource(mSceneTutorialMessageService, mSceneTutorialMessageQueryService);
        this.restMSceneTutorialMessageMockMvc = MockMvcBuilders.standaloneSetup(mSceneTutorialMessageResource)
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
    public static MSceneTutorialMessage createEntity(EntityManager em) {
        MSceneTutorialMessage mSceneTutorialMessage = new MSceneTutorialMessage()
            .target(DEFAULT_TARGET)
            .orderNum(DEFAULT_ORDER_NUM)
            .position(DEFAULT_POSITION)
            .message(DEFAULT_MESSAGE)
            .assetName(DEFAULT_ASSET_NAME)
            .title(DEFAULT_TITLE);
        return mSceneTutorialMessage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MSceneTutorialMessage createUpdatedEntity(EntityManager em) {
        MSceneTutorialMessage mSceneTutorialMessage = new MSceneTutorialMessage()
            .target(UPDATED_TARGET)
            .orderNum(UPDATED_ORDER_NUM)
            .position(UPDATED_POSITION)
            .message(UPDATED_MESSAGE)
            .assetName(UPDATED_ASSET_NAME)
            .title(UPDATED_TITLE);
        return mSceneTutorialMessage;
    }

    @BeforeEach
    public void initTest() {
        mSceneTutorialMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMSceneTutorialMessage() throws Exception {
        int databaseSizeBeforeCreate = mSceneTutorialMessageRepository.findAll().size();

        // Create the MSceneTutorialMessage
        MSceneTutorialMessageDTO mSceneTutorialMessageDTO = mSceneTutorialMessageMapper.toDto(mSceneTutorialMessage);
        restMSceneTutorialMessageMockMvc.perform(post("/api/m-scene-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSceneTutorialMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the MSceneTutorialMessage in the database
        List<MSceneTutorialMessage> mSceneTutorialMessageList = mSceneTutorialMessageRepository.findAll();
        assertThat(mSceneTutorialMessageList).hasSize(databaseSizeBeforeCreate + 1);
        MSceneTutorialMessage testMSceneTutorialMessage = mSceneTutorialMessageList.get(mSceneTutorialMessageList.size() - 1);
        assertThat(testMSceneTutorialMessage.getTarget()).isEqualTo(DEFAULT_TARGET);
        assertThat(testMSceneTutorialMessage.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMSceneTutorialMessage.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testMSceneTutorialMessage.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testMSceneTutorialMessage.getAssetName()).isEqualTo(DEFAULT_ASSET_NAME);
        assertThat(testMSceneTutorialMessage.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createMSceneTutorialMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mSceneTutorialMessageRepository.findAll().size();

        // Create the MSceneTutorialMessage with an existing ID
        mSceneTutorialMessage.setId(1L);
        MSceneTutorialMessageDTO mSceneTutorialMessageDTO = mSceneTutorialMessageMapper.toDto(mSceneTutorialMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMSceneTutorialMessageMockMvc.perform(post("/api/m-scene-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSceneTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSceneTutorialMessage in the database
        List<MSceneTutorialMessage> mSceneTutorialMessageList = mSceneTutorialMessageRepository.findAll();
        assertThat(mSceneTutorialMessageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTargetIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSceneTutorialMessageRepository.findAll().size();
        // set the field null
        mSceneTutorialMessage.setTarget(null);

        // Create the MSceneTutorialMessage, which fails.
        MSceneTutorialMessageDTO mSceneTutorialMessageDTO = mSceneTutorialMessageMapper.toDto(mSceneTutorialMessage);

        restMSceneTutorialMessageMockMvc.perform(post("/api/m-scene-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSceneTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MSceneTutorialMessage> mSceneTutorialMessageList = mSceneTutorialMessageRepository.findAll();
        assertThat(mSceneTutorialMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSceneTutorialMessageRepository.findAll().size();
        // set the field null
        mSceneTutorialMessage.setOrderNum(null);

        // Create the MSceneTutorialMessage, which fails.
        MSceneTutorialMessageDTO mSceneTutorialMessageDTO = mSceneTutorialMessageMapper.toDto(mSceneTutorialMessage);

        restMSceneTutorialMessageMockMvc.perform(post("/api/m-scene-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSceneTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MSceneTutorialMessage> mSceneTutorialMessageList = mSceneTutorialMessageRepository.findAll();
        assertThat(mSceneTutorialMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPositionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSceneTutorialMessageRepository.findAll().size();
        // set the field null
        mSceneTutorialMessage.setPosition(null);

        // Create the MSceneTutorialMessage, which fails.
        MSceneTutorialMessageDTO mSceneTutorialMessageDTO = mSceneTutorialMessageMapper.toDto(mSceneTutorialMessage);

        restMSceneTutorialMessageMockMvc.perform(post("/api/m-scene-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSceneTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MSceneTutorialMessage> mSceneTutorialMessageList = mSceneTutorialMessageRepository.findAll();
        assertThat(mSceneTutorialMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessages() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList
        restMSceneTutorialMessageMockMvc.perform(get("/api/m-scene-tutorial-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSceneTutorialMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].target").value(hasItem(DEFAULT_TARGET)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }
    
    @Test
    @Transactional
    public void getMSceneTutorialMessage() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get the mSceneTutorialMessage
        restMSceneTutorialMessageMockMvc.perform(get("/api/m-scene-tutorial-messages/{id}", mSceneTutorialMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mSceneTutorialMessage.getId().intValue()))
            .andExpect(jsonPath("$.target").value(DEFAULT_TARGET))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()))
            .andExpect(jsonPath("$.assetName").value(DEFAULT_ASSET_NAME.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByTargetIsEqualToSomething() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where target equals to DEFAULT_TARGET
        defaultMSceneTutorialMessageShouldBeFound("target.equals=" + DEFAULT_TARGET);

        // Get all the mSceneTutorialMessageList where target equals to UPDATED_TARGET
        defaultMSceneTutorialMessageShouldNotBeFound("target.equals=" + UPDATED_TARGET);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByTargetIsInShouldWork() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where target in DEFAULT_TARGET or UPDATED_TARGET
        defaultMSceneTutorialMessageShouldBeFound("target.in=" + DEFAULT_TARGET + "," + UPDATED_TARGET);

        // Get all the mSceneTutorialMessageList where target equals to UPDATED_TARGET
        defaultMSceneTutorialMessageShouldNotBeFound("target.in=" + UPDATED_TARGET);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByTargetIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where target is not null
        defaultMSceneTutorialMessageShouldBeFound("target.specified=true");

        // Get all the mSceneTutorialMessageList where target is null
        defaultMSceneTutorialMessageShouldNotBeFound("target.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByTargetIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where target greater than or equals to DEFAULT_TARGET
        defaultMSceneTutorialMessageShouldBeFound("target.greaterOrEqualThan=" + DEFAULT_TARGET);

        // Get all the mSceneTutorialMessageList where target greater than or equals to UPDATED_TARGET
        defaultMSceneTutorialMessageShouldNotBeFound("target.greaterOrEqualThan=" + UPDATED_TARGET);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByTargetIsLessThanSomething() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where target less than or equals to DEFAULT_TARGET
        defaultMSceneTutorialMessageShouldNotBeFound("target.lessThan=" + DEFAULT_TARGET);

        // Get all the mSceneTutorialMessageList where target less than or equals to UPDATED_TARGET
        defaultMSceneTutorialMessageShouldBeFound("target.lessThan=" + UPDATED_TARGET);
    }


    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMSceneTutorialMessageShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mSceneTutorialMessageList where orderNum equals to UPDATED_ORDER_NUM
        defaultMSceneTutorialMessageShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMSceneTutorialMessageShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mSceneTutorialMessageList where orderNum equals to UPDATED_ORDER_NUM
        defaultMSceneTutorialMessageShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where orderNum is not null
        defaultMSceneTutorialMessageShouldBeFound("orderNum.specified=true");

        // Get all the mSceneTutorialMessageList where orderNum is null
        defaultMSceneTutorialMessageShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMSceneTutorialMessageShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mSceneTutorialMessageList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMSceneTutorialMessageShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMSceneTutorialMessageShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mSceneTutorialMessageList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMSceneTutorialMessageShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByPositionIsEqualToSomething() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where position equals to DEFAULT_POSITION
        defaultMSceneTutorialMessageShouldBeFound("position.equals=" + DEFAULT_POSITION);

        // Get all the mSceneTutorialMessageList where position equals to UPDATED_POSITION
        defaultMSceneTutorialMessageShouldNotBeFound("position.equals=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByPositionIsInShouldWork() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where position in DEFAULT_POSITION or UPDATED_POSITION
        defaultMSceneTutorialMessageShouldBeFound("position.in=" + DEFAULT_POSITION + "," + UPDATED_POSITION);

        // Get all the mSceneTutorialMessageList where position equals to UPDATED_POSITION
        defaultMSceneTutorialMessageShouldNotBeFound("position.in=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByPositionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where position is not null
        defaultMSceneTutorialMessageShouldBeFound("position.specified=true");

        // Get all the mSceneTutorialMessageList where position is null
        defaultMSceneTutorialMessageShouldNotBeFound("position.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByPositionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where position greater than or equals to DEFAULT_POSITION
        defaultMSceneTutorialMessageShouldBeFound("position.greaterOrEqualThan=" + DEFAULT_POSITION);

        // Get all the mSceneTutorialMessageList where position greater than or equals to UPDATED_POSITION
        defaultMSceneTutorialMessageShouldNotBeFound("position.greaterOrEqualThan=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllMSceneTutorialMessagesByPositionIsLessThanSomething() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        // Get all the mSceneTutorialMessageList where position less than or equals to DEFAULT_POSITION
        defaultMSceneTutorialMessageShouldNotBeFound("position.lessThan=" + DEFAULT_POSITION);

        // Get all the mSceneTutorialMessageList where position less than or equals to UPDATED_POSITION
        defaultMSceneTutorialMessageShouldBeFound("position.lessThan=" + UPDATED_POSITION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMSceneTutorialMessageShouldBeFound(String filter) throws Exception {
        restMSceneTutorialMessageMockMvc.perform(get("/api/m-scene-tutorial-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSceneTutorialMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].target").value(hasItem(DEFAULT_TARGET)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].assetName").value(hasItem(DEFAULT_ASSET_NAME.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));

        // Check, that the count call also returns 1
        restMSceneTutorialMessageMockMvc.perform(get("/api/m-scene-tutorial-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMSceneTutorialMessageShouldNotBeFound(String filter) throws Exception {
        restMSceneTutorialMessageMockMvc.perform(get("/api/m-scene-tutorial-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMSceneTutorialMessageMockMvc.perform(get("/api/m-scene-tutorial-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMSceneTutorialMessage() throws Exception {
        // Get the mSceneTutorialMessage
        restMSceneTutorialMessageMockMvc.perform(get("/api/m-scene-tutorial-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMSceneTutorialMessage() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        int databaseSizeBeforeUpdate = mSceneTutorialMessageRepository.findAll().size();

        // Update the mSceneTutorialMessage
        MSceneTutorialMessage updatedMSceneTutorialMessage = mSceneTutorialMessageRepository.findById(mSceneTutorialMessage.getId()).get();
        // Disconnect from session so that the updates on updatedMSceneTutorialMessage are not directly saved in db
        em.detach(updatedMSceneTutorialMessage);
        updatedMSceneTutorialMessage
            .target(UPDATED_TARGET)
            .orderNum(UPDATED_ORDER_NUM)
            .position(UPDATED_POSITION)
            .message(UPDATED_MESSAGE)
            .assetName(UPDATED_ASSET_NAME)
            .title(UPDATED_TITLE);
        MSceneTutorialMessageDTO mSceneTutorialMessageDTO = mSceneTutorialMessageMapper.toDto(updatedMSceneTutorialMessage);

        restMSceneTutorialMessageMockMvc.perform(put("/api/m-scene-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSceneTutorialMessageDTO)))
            .andExpect(status().isOk());

        // Validate the MSceneTutorialMessage in the database
        List<MSceneTutorialMessage> mSceneTutorialMessageList = mSceneTutorialMessageRepository.findAll();
        assertThat(mSceneTutorialMessageList).hasSize(databaseSizeBeforeUpdate);
        MSceneTutorialMessage testMSceneTutorialMessage = mSceneTutorialMessageList.get(mSceneTutorialMessageList.size() - 1);
        assertThat(testMSceneTutorialMessage.getTarget()).isEqualTo(UPDATED_TARGET);
        assertThat(testMSceneTutorialMessage.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMSceneTutorialMessage.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testMSceneTutorialMessage.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testMSceneTutorialMessage.getAssetName()).isEqualTo(UPDATED_ASSET_NAME);
        assertThat(testMSceneTutorialMessage.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingMSceneTutorialMessage() throws Exception {
        int databaseSizeBeforeUpdate = mSceneTutorialMessageRepository.findAll().size();

        // Create the MSceneTutorialMessage
        MSceneTutorialMessageDTO mSceneTutorialMessageDTO = mSceneTutorialMessageMapper.toDto(mSceneTutorialMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMSceneTutorialMessageMockMvc.perform(put("/api/m-scene-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSceneTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSceneTutorialMessage in the database
        List<MSceneTutorialMessage> mSceneTutorialMessageList = mSceneTutorialMessageRepository.findAll();
        assertThat(mSceneTutorialMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMSceneTutorialMessage() throws Exception {
        // Initialize the database
        mSceneTutorialMessageRepository.saveAndFlush(mSceneTutorialMessage);

        int databaseSizeBeforeDelete = mSceneTutorialMessageRepository.findAll().size();

        // Delete the mSceneTutorialMessage
        restMSceneTutorialMessageMockMvc.perform(delete("/api/m-scene-tutorial-messages/{id}", mSceneTutorialMessage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MSceneTutorialMessage> mSceneTutorialMessageList = mSceneTutorialMessageRepository.findAll();
        assertThat(mSceneTutorialMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSceneTutorialMessage.class);
        MSceneTutorialMessage mSceneTutorialMessage1 = new MSceneTutorialMessage();
        mSceneTutorialMessage1.setId(1L);
        MSceneTutorialMessage mSceneTutorialMessage2 = new MSceneTutorialMessage();
        mSceneTutorialMessage2.setId(mSceneTutorialMessage1.getId());
        assertThat(mSceneTutorialMessage1).isEqualTo(mSceneTutorialMessage2);
        mSceneTutorialMessage2.setId(2L);
        assertThat(mSceneTutorialMessage1).isNotEqualTo(mSceneTutorialMessage2);
        mSceneTutorialMessage1.setId(null);
        assertThat(mSceneTutorialMessage1).isNotEqualTo(mSceneTutorialMessage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSceneTutorialMessageDTO.class);
        MSceneTutorialMessageDTO mSceneTutorialMessageDTO1 = new MSceneTutorialMessageDTO();
        mSceneTutorialMessageDTO1.setId(1L);
        MSceneTutorialMessageDTO mSceneTutorialMessageDTO2 = new MSceneTutorialMessageDTO();
        assertThat(mSceneTutorialMessageDTO1).isNotEqualTo(mSceneTutorialMessageDTO2);
        mSceneTutorialMessageDTO2.setId(mSceneTutorialMessageDTO1.getId());
        assertThat(mSceneTutorialMessageDTO1).isEqualTo(mSceneTutorialMessageDTO2);
        mSceneTutorialMessageDTO2.setId(2L);
        assertThat(mSceneTutorialMessageDTO1).isNotEqualTo(mSceneTutorialMessageDTO2);
        mSceneTutorialMessageDTO1.setId(null);
        assertThat(mSceneTutorialMessageDTO1).isNotEqualTo(mSceneTutorialMessageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mSceneTutorialMessageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mSceneTutorialMessageMapper.fromId(null)).isNull();
    }
}
