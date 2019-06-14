package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTutorialMessage;
import io.shm.tsubasa.repository.MTutorialMessageRepository;
import io.shm.tsubasa.service.MTutorialMessageService;
import io.shm.tsubasa.service.dto.MTutorialMessageDTO;
import io.shm.tsubasa.service.mapper.MTutorialMessageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTutorialMessageCriteria;
import io.shm.tsubasa.service.MTutorialMessageQueryService;

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
 * Integration tests for the {@Link MTutorialMessageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTutorialMessageResourceIT {

    private static final Integer DEFAULT_STEP = 1;
    private static final Integer UPDATED_STEP = 2;

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    private static final Integer DEFAULT_POSITION = 1;
    private static final Integer UPDATED_POSITION = 2;

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    @Autowired
    private MTutorialMessageRepository mTutorialMessageRepository;

    @Autowired
    private MTutorialMessageMapper mTutorialMessageMapper;

    @Autowired
    private MTutorialMessageService mTutorialMessageService;

    @Autowired
    private MTutorialMessageQueryService mTutorialMessageQueryService;

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

    private MockMvc restMTutorialMessageMockMvc;

    private MTutorialMessage mTutorialMessage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTutorialMessageResource mTutorialMessageResource = new MTutorialMessageResource(mTutorialMessageService, mTutorialMessageQueryService);
        this.restMTutorialMessageMockMvc = MockMvcBuilders.standaloneSetup(mTutorialMessageResource)
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
    public static MTutorialMessage createEntity(EntityManager em) {
        MTutorialMessage mTutorialMessage = new MTutorialMessage()
            .step(DEFAULT_STEP)
            .orderNum(DEFAULT_ORDER_NUM)
            .position(DEFAULT_POSITION)
            .message(DEFAULT_MESSAGE);
        return mTutorialMessage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTutorialMessage createUpdatedEntity(EntityManager em) {
        MTutorialMessage mTutorialMessage = new MTutorialMessage()
            .step(UPDATED_STEP)
            .orderNum(UPDATED_ORDER_NUM)
            .position(UPDATED_POSITION)
            .message(UPDATED_MESSAGE);
        return mTutorialMessage;
    }

    @BeforeEach
    public void initTest() {
        mTutorialMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTutorialMessage() throws Exception {
        int databaseSizeBeforeCreate = mTutorialMessageRepository.findAll().size();

        // Create the MTutorialMessage
        MTutorialMessageDTO mTutorialMessageDTO = mTutorialMessageMapper.toDto(mTutorialMessage);
        restMTutorialMessageMockMvc.perform(post("/api/m-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTutorialMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the MTutorialMessage in the database
        List<MTutorialMessage> mTutorialMessageList = mTutorialMessageRepository.findAll();
        assertThat(mTutorialMessageList).hasSize(databaseSizeBeforeCreate + 1);
        MTutorialMessage testMTutorialMessage = mTutorialMessageList.get(mTutorialMessageList.size() - 1);
        assertThat(testMTutorialMessage.getStep()).isEqualTo(DEFAULT_STEP);
        assertThat(testMTutorialMessage.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMTutorialMessage.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testMTutorialMessage.getMessage()).isEqualTo(DEFAULT_MESSAGE);
    }

    @Test
    @Transactional
    public void createMTutorialMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTutorialMessageRepository.findAll().size();

        // Create the MTutorialMessage with an existing ID
        mTutorialMessage.setId(1L);
        MTutorialMessageDTO mTutorialMessageDTO = mTutorialMessageMapper.toDto(mTutorialMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTutorialMessageMockMvc.perform(post("/api/m-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTutorialMessage in the database
        List<MTutorialMessage> mTutorialMessageList = mTutorialMessageRepository.findAll();
        assertThat(mTutorialMessageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStepIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTutorialMessageRepository.findAll().size();
        // set the field null
        mTutorialMessage.setStep(null);

        // Create the MTutorialMessage, which fails.
        MTutorialMessageDTO mTutorialMessageDTO = mTutorialMessageMapper.toDto(mTutorialMessage);

        restMTutorialMessageMockMvc.perform(post("/api/m-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MTutorialMessage> mTutorialMessageList = mTutorialMessageRepository.findAll();
        assertThat(mTutorialMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTutorialMessageRepository.findAll().size();
        // set the field null
        mTutorialMessage.setOrderNum(null);

        // Create the MTutorialMessage, which fails.
        MTutorialMessageDTO mTutorialMessageDTO = mTutorialMessageMapper.toDto(mTutorialMessage);

        restMTutorialMessageMockMvc.perform(post("/api/m-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MTutorialMessage> mTutorialMessageList = mTutorialMessageRepository.findAll();
        assertThat(mTutorialMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPositionIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTutorialMessageRepository.findAll().size();
        // set the field null
        mTutorialMessage.setPosition(null);

        // Create the MTutorialMessage, which fails.
        MTutorialMessageDTO mTutorialMessageDTO = mTutorialMessageMapper.toDto(mTutorialMessage);

        restMTutorialMessageMockMvc.perform(post("/api/m-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MTutorialMessage> mTutorialMessageList = mTutorialMessageRepository.findAll();
        assertThat(mTutorialMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessages() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList
        restMTutorialMessageMockMvc.perform(get("/api/m-tutorial-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTutorialMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].step").value(hasItem(DEFAULT_STEP)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getMTutorialMessage() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get the mTutorialMessage
        restMTutorialMessageMockMvc.perform(get("/api/m-tutorial-messages/{id}", mTutorialMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTutorialMessage.getId().intValue()))
            .andExpect(jsonPath("$.step").value(DEFAULT_STEP))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()));
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByStepIsEqualToSomething() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where step equals to DEFAULT_STEP
        defaultMTutorialMessageShouldBeFound("step.equals=" + DEFAULT_STEP);

        // Get all the mTutorialMessageList where step equals to UPDATED_STEP
        defaultMTutorialMessageShouldNotBeFound("step.equals=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByStepIsInShouldWork() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where step in DEFAULT_STEP or UPDATED_STEP
        defaultMTutorialMessageShouldBeFound("step.in=" + DEFAULT_STEP + "," + UPDATED_STEP);

        // Get all the mTutorialMessageList where step equals to UPDATED_STEP
        defaultMTutorialMessageShouldNotBeFound("step.in=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByStepIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where step is not null
        defaultMTutorialMessageShouldBeFound("step.specified=true");

        // Get all the mTutorialMessageList where step is null
        defaultMTutorialMessageShouldNotBeFound("step.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByStepIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where step greater than or equals to DEFAULT_STEP
        defaultMTutorialMessageShouldBeFound("step.greaterOrEqualThan=" + DEFAULT_STEP);

        // Get all the mTutorialMessageList where step greater than or equals to UPDATED_STEP
        defaultMTutorialMessageShouldNotBeFound("step.greaterOrEqualThan=" + UPDATED_STEP);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByStepIsLessThanSomething() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where step less than or equals to DEFAULT_STEP
        defaultMTutorialMessageShouldNotBeFound("step.lessThan=" + DEFAULT_STEP);

        // Get all the mTutorialMessageList where step less than or equals to UPDATED_STEP
        defaultMTutorialMessageShouldBeFound("step.lessThan=" + UPDATED_STEP);
    }


    @Test
    @Transactional
    public void getAllMTutorialMessagesByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMTutorialMessageShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mTutorialMessageList where orderNum equals to UPDATED_ORDER_NUM
        defaultMTutorialMessageShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMTutorialMessageShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mTutorialMessageList where orderNum equals to UPDATED_ORDER_NUM
        defaultMTutorialMessageShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where orderNum is not null
        defaultMTutorialMessageShouldBeFound("orderNum.specified=true");

        // Get all the mTutorialMessageList where orderNum is null
        defaultMTutorialMessageShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMTutorialMessageShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mTutorialMessageList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMTutorialMessageShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMTutorialMessageShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mTutorialMessageList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMTutorialMessageShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }


    @Test
    @Transactional
    public void getAllMTutorialMessagesByPositionIsEqualToSomething() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where position equals to DEFAULT_POSITION
        defaultMTutorialMessageShouldBeFound("position.equals=" + DEFAULT_POSITION);

        // Get all the mTutorialMessageList where position equals to UPDATED_POSITION
        defaultMTutorialMessageShouldNotBeFound("position.equals=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByPositionIsInShouldWork() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where position in DEFAULT_POSITION or UPDATED_POSITION
        defaultMTutorialMessageShouldBeFound("position.in=" + DEFAULT_POSITION + "," + UPDATED_POSITION);

        // Get all the mTutorialMessageList where position equals to UPDATED_POSITION
        defaultMTutorialMessageShouldNotBeFound("position.in=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByPositionIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where position is not null
        defaultMTutorialMessageShouldBeFound("position.specified=true");

        // Get all the mTutorialMessageList where position is null
        defaultMTutorialMessageShouldNotBeFound("position.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByPositionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where position greater than or equals to DEFAULT_POSITION
        defaultMTutorialMessageShouldBeFound("position.greaterOrEqualThan=" + DEFAULT_POSITION);

        // Get all the mTutorialMessageList where position greater than or equals to UPDATED_POSITION
        defaultMTutorialMessageShouldNotBeFound("position.greaterOrEqualThan=" + UPDATED_POSITION);
    }

    @Test
    @Transactional
    public void getAllMTutorialMessagesByPositionIsLessThanSomething() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        // Get all the mTutorialMessageList where position less than or equals to DEFAULT_POSITION
        defaultMTutorialMessageShouldNotBeFound("position.lessThan=" + DEFAULT_POSITION);

        // Get all the mTutorialMessageList where position less than or equals to UPDATED_POSITION
        defaultMTutorialMessageShouldBeFound("position.lessThan=" + UPDATED_POSITION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTutorialMessageShouldBeFound(String filter) throws Exception {
        restMTutorialMessageMockMvc.perform(get("/api/m-tutorial-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTutorialMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].step").value(hasItem(DEFAULT_STEP)))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())));

        // Check, that the count call also returns 1
        restMTutorialMessageMockMvc.perform(get("/api/m-tutorial-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTutorialMessageShouldNotBeFound(String filter) throws Exception {
        restMTutorialMessageMockMvc.perform(get("/api/m-tutorial-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTutorialMessageMockMvc.perform(get("/api/m-tutorial-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTutorialMessage() throws Exception {
        // Get the mTutorialMessage
        restMTutorialMessageMockMvc.perform(get("/api/m-tutorial-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTutorialMessage() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        int databaseSizeBeforeUpdate = mTutorialMessageRepository.findAll().size();

        // Update the mTutorialMessage
        MTutorialMessage updatedMTutorialMessage = mTutorialMessageRepository.findById(mTutorialMessage.getId()).get();
        // Disconnect from session so that the updates on updatedMTutorialMessage are not directly saved in db
        em.detach(updatedMTutorialMessage);
        updatedMTutorialMessage
            .step(UPDATED_STEP)
            .orderNum(UPDATED_ORDER_NUM)
            .position(UPDATED_POSITION)
            .message(UPDATED_MESSAGE);
        MTutorialMessageDTO mTutorialMessageDTO = mTutorialMessageMapper.toDto(updatedMTutorialMessage);

        restMTutorialMessageMockMvc.perform(put("/api/m-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTutorialMessageDTO)))
            .andExpect(status().isOk());

        // Validate the MTutorialMessage in the database
        List<MTutorialMessage> mTutorialMessageList = mTutorialMessageRepository.findAll();
        assertThat(mTutorialMessageList).hasSize(databaseSizeBeforeUpdate);
        MTutorialMessage testMTutorialMessage = mTutorialMessageList.get(mTutorialMessageList.size() - 1);
        assertThat(testMTutorialMessage.getStep()).isEqualTo(UPDATED_STEP);
        assertThat(testMTutorialMessage.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMTutorialMessage.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testMTutorialMessage.getMessage()).isEqualTo(UPDATED_MESSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingMTutorialMessage() throws Exception {
        int databaseSizeBeforeUpdate = mTutorialMessageRepository.findAll().size();

        // Create the MTutorialMessage
        MTutorialMessageDTO mTutorialMessageDTO = mTutorialMessageMapper.toDto(mTutorialMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTutorialMessageMockMvc.perform(put("/api/m-tutorial-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTutorialMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTutorialMessage in the database
        List<MTutorialMessage> mTutorialMessageList = mTutorialMessageRepository.findAll();
        assertThat(mTutorialMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTutorialMessage() throws Exception {
        // Initialize the database
        mTutorialMessageRepository.saveAndFlush(mTutorialMessage);

        int databaseSizeBeforeDelete = mTutorialMessageRepository.findAll().size();

        // Delete the mTutorialMessage
        restMTutorialMessageMockMvc.perform(delete("/api/m-tutorial-messages/{id}", mTutorialMessage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTutorialMessage> mTutorialMessageList = mTutorialMessageRepository.findAll();
        assertThat(mTutorialMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTutorialMessage.class);
        MTutorialMessage mTutorialMessage1 = new MTutorialMessage();
        mTutorialMessage1.setId(1L);
        MTutorialMessage mTutorialMessage2 = new MTutorialMessage();
        mTutorialMessage2.setId(mTutorialMessage1.getId());
        assertThat(mTutorialMessage1).isEqualTo(mTutorialMessage2);
        mTutorialMessage2.setId(2L);
        assertThat(mTutorialMessage1).isNotEqualTo(mTutorialMessage2);
        mTutorialMessage1.setId(null);
        assertThat(mTutorialMessage1).isNotEqualTo(mTutorialMessage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTutorialMessageDTO.class);
        MTutorialMessageDTO mTutorialMessageDTO1 = new MTutorialMessageDTO();
        mTutorialMessageDTO1.setId(1L);
        MTutorialMessageDTO mTutorialMessageDTO2 = new MTutorialMessageDTO();
        assertThat(mTutorialMessageDTO1).isNotEqualTo(mTutorialMessageDTO2);
        mTutorialMessageDTO2.setId(mTutorialMessageDTO1.getId());
        assertThat(mTutorialMessageDTO1).isEqualTo(mTutorialMessageDTO2);
        mTutorialMessageDTO2.setId(2L);
        assertThat(mTutorialMessageDTO1).isNotEqualTo(mTutorialMessageDTO2);
        mTutorialMessageDTO1.setId(null);
        assertThat(mTutorialMessageDTO1).isNotEqualTo(mTutorialMessageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTutorialMessageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTutorialMessageMapper.fromId(null)).isNull();
    }
}
