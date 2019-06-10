package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MChatSystemMessage;
import io.shm.tsubasa.repository.MChatSystemMessageRepository;
import io.shm.tsubasa.service.MChatSystemMessageService;
import io.shm.tsubasa.service.dto.MChatSystemMessageDTO;
import io.shm.tsubasa.service.mapper.MChatSystemMessageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MChatSystemMessageCriteria;
import io.shm.tsubasa.service.MChatSystemMessageQueryService;

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
 * Integration tests for the {@Link MChatSystemMessageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MChatSystemMessageResourceIT {

    private static final Integer DEFAULT_MESSAGE_TYPE = 1;
    private static final Integer UPDATED_MESSAGE_TYPE = 2;

    private static final String DEFAULT_MESSAGE_KEY = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_KEY = "BBBBBBBBBB";

    @Autowired
    private MChatSystemMessageRepository mChatSystemMessageRepository;

    @Autowired
    private MChatSystemMessageMapper mChatSystemMessageMapper;

    @Autowired
    private MChatSystemMessageService mChatSystemMessageService;

    @Autowired
    private MChatSystemMessageQueryService mChatSystemMessageQueryService;

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

    private MockMvc restMChatSystemMessageMockMvc;

    private MChatSystemMessage mChatSystemMessage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MChatSystemMessageResource mChatSystemMessageResource = new MChatSystemMessageResource(mChatSystemMessageService, mChatSystemMessageQueryService);
        this.restMChatSystemMessageMockMvc = MockMvcBuilders.standaloneSetup(mChatSystemMessageResource)
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
    public static MChatSystemMessage createEntity(EntityManager em) {
        MChatSystemMessage mChatSystemMessage = new MChatSystemMessage()
            .messageType(DEFAULT_MESSAGE_TYPE)
            .messageKey(DEFAULT_MESSAGE_KEY);
        return mChatSystemMessage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MChatSystemMessage createUpdatedEntity(EntityManager em) {
        MChatSystemMessage mChatSystemMessage = new MChatSystemMessage()
            .messageType(UPDATED_MESSAGE_TYPE)
            .messageKey(UPDATED_MESSAGE_KEY);
        return mChatSystemMessage;
    }

    @BeforeEach
    public void initTest() {
        mChatSystemMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMChatSystemMessage() throws Exception {
        int databaseSizeBeforeCreate = mChatSystemMessageRepository.findAll().size();

        // Create the MChatSystemMessage
        MChatSystemMessageDTO mChatSystemMessageDTO = mChatSystemMessageMapper.toDto(mChatSystemMessage);
        restMChatSystemMessageMockMvc.perform(post("/api/m-chat-system-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChatSystemMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the MChatSystemMessage in the database
        List<MChatSystemMessage> mChatSystemMessageList = mChatSystemMessageRepository.findAll();
        assertThat(mChatSystemMessageList).hasSize(databaseSizeBeforeCreate + 1);
        MChatSystemMessage testMChatSystemMessage = mChatSystemMessageList.get(mChatSystemMessageList.size() - 1);
        assertThat(testMChatSystemMessage.getMessageType()).isEqualTo(DEFAULT_MESSAGE_TYPE);
        assertThat(testMChatSystemMessage.getMessageKey()).isEqualTo(DEFAULT_MESSAGE_KEY);
    }

    @Test
    @Transactional
    public void createMChatSystemMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mChatSystemMessageRepository.findAll().size();

        // Create the MChatSystemMessage with an existing ID
        mChatSystemMessage.setId(1L);
        MChatSystemMessageDTO mChatSystemMessageDTO = mChatSystemMessageMapper.toDto(mChatSystemMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMChatSystemMessageMockMvc.perform(post("/api/m-chat-system-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChatSystemMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChatSystemMessage in the database
        List<MChatSystemMessage> mChatSystemMessageList = mChatSystemMessageRepository.findAll();
        assertThat(mChatSystemMessageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMessageTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mChatSystemMessageRepository.findAll().size();
        // set the field null
        mChatSystemMessage.setMessageType(null);

        // Create the MChatSystemMessage, which fails.
        MChatSystemMessageDTO mChatSystemMessageDTO = mChatSystemMessageMapper.toDto(mChatSystemMessage);

        restMChatSystemMessageMockMvc.perform(post("/api/m-chat-system-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChatSystemMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MChatSystemMessage> mChatSystemMessageList = mChatSystemMessageRepository.findAll();
        assertThat(mChatSystemMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMChatSystemMessages() throws Exception {
        // Initialize the database
        mChatSystemMessageRepository.saveAndFlush(mChatSystemMessage);

        // Get all the mChatSystemMessageList
        restMChatSystemMessageMockMvc.perform(get("/api/m-chat-system-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChatSystemMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].messageType").value(hasItem(DEFAULT_MESSAGE_TYPE)))
            .andExpect(jsonPath("$.[*].messageKey").value(hasItem(DEFAULT_MESSAGE_KEY.toString())));
    }
    
    @Test
    @Transactional
    public void getMChatSystemMessage() throws Exception {
        // Initialize the database
        mChatSystemMessageRepository.saveAndFlush(mChatSystemMessage);

        // Get the mChatSystemMessage
        restMChatSystemMessageMockMvc.perform(get("/api/m-chat-system-messages/{id}", mChatSystemMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mChatSystemMessage.getId().intValue()))
            .andExpect(jsonPath("$.messageType").value(DEFAULT_MESSAGE_TYPE))
            .andExpect(jsonPath("$.messageKey").value(DEFAULT_MESSAGE_KEY.toString()));
    }

    @Test
    @Transactional
    public void getAllMChatSystemMessagesByMessageTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mChatSystemMessageRepository.saveAndFlush(mChatSystemMessage);

        // Get all the mChatSystemMessageList where messageType equals to DEFAULT_MESSAGE_TYPE
        defaultMChatSystemMessageShouldBeFound("messageType.equals=" + DEFAULT_MESSAGE_TYPE);

        // Get all the mChatSystemMessageList where messageType equals to UPDATED_MESSAGE_TYPE
        defaultMChatSystemMessageShouldNotBeFound("messageType.equals=" + UPDATED_MESSAGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChatSystemMessagesByMessageTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mChatSystemMessageRepository.saveAndFlush(mChatSystemMessage);

        // Get all the mChatSystemMessageList where messageType in DEFAULT_MESSAGE_TYPE or UPDATED_MESSAGE_TYPE
        defaultMChatSystemMessageShouldBeFound("messageType.in=" + DEFAULT_MESSAGE_TYPE + "," + UPDATED_MESSAGE_TYPE);

        // Get all the mChatSystemMessageList where messageType equals to UPDATED_MESSAGE_TYPE
        defaultMChatSystemMessageShouldNotBeFound("messageType.in=" + UPDATED_MESSAGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChatSystemMessagesByMessageTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mChatSystemMessageRepository.saveAndFlush(mChatSystemMessage);

        // Get all the mChatSystemMessageList where messageType is not null
        defaultMChatSystemMessageShouldBeFound("messageType.specified=true");

        // Get all the mChatSystemMessageList where messageType is null
        defaultMChatSystemMessageShouldNotBeFound("messageType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMChatSystemMessagesByMessageTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mChatSystemMessageRepository.saveAndFlush(mChatSystemMessage);

        // Get all the mChatSystemMessageList where messageType greater than or equals to DEFAULT_MESSAGE_TYPE
        defaultMChatSystemMessageShouldBeFound("messageType.greaterOrEqualThan=" + DEFAULT_MESSAGE_TYPE);

        // Get all the mChatSystemMessageList where messageType greater than or equals to UPDATED_MESSAGE_TYPE
        defaultMChatSystemMessageShouldNotBeFound("messageType.greaterOrEqualThan=" + UPDATED_MESSAGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllMChatSystemMessagesByMessageTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mChatSystemMessageRepository.saveAndFlush(mChatSystemMessage);

        // Get all the mChatSystemMessageList where messageType less than or equals to DEFAULT_MESSAGE_TYPE
        defaultMChatSystemMessageShouldNotBeFound("messageType.lessThan=" + DEFAULT_MESSAGE_TYPE);

        // Get all the mChatSystemMessageList where messageType less than or equals to UPDATED_MESSAGE_TYPE
        defaultMChatSystemMessageShouldBeFound("messageType.lessThan=" + UPDATED_MESSAGE_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMChatSystemMessageShouldBeFound(String filter) throws Exception {
        restMChatSystemMessageMockMvc.perform(get("/api/m-chat-system-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mChatSystemMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].messageType").value(hasItem(DEFAULT_MESSAGE_TYPE)))
            .andExpect(jsonPath("$.[*].messageKey").value(hasItem(DEFAULT_MESSAGE_KEY.toString())));

        // Check, that the count call also returns 1
        restMChatSystemMessageMockMvc.perform(get("/api/m-chat-system-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMChatSystemMessageShouldNotBeFound(String filter) throws Exception {
        restMChatSystemMessageMockMvc.perform(get("/api/m-chat-system-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMChatSystemMessageMockMvc.perform(get("/api/m-chat-system-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMChatSystemMessage() throws Exception {
        // Get the mChatSystemMessage
        restMChatSystemMessageMockMvc.perform(get("/api/m-chat-system-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMChatSystemMessage() throws Exception {
        // Initialize the database
        mChatSystemMessageRepository.saveAndFlush(mChatSystemMessage);

        int databaseSizeBeforeUpdate = mChatSystemMessageRepository.findAll().size();

        // Update the mChatSystemMessage
        MChatSystemMessage updatedMChatSystemMessage = mChatSystemMessageRepository.findById(mChatSystemMessage.getId()).get();
        // Disconnect from session so that the updates on updatedMChatSystemMessage are not directly saved in db
        em.detach(updatedMChatSystemMessage);
        updatedMChatSystemMessage
            .messageType(UPDATED_MESSAGE_TYPE)
            .messageKey(UPDATED_MESSAGE_KEY);
        MChatSystemMessageDTO mChatSystemMessageDTO = mChatSystemMessageMapper.toDto(updatedMChatSystemMessage);

        restMChatSystemMessageMockMvc.perform(put("/api/m-chat-system-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChatSystemMessageDTO)))
            .andExpect(status().isOk());

        // Validate the MChatSystemMessage in the database
        List<MChatSystemMessage> mChatSystemMessageList = mChatSystemMessageRepository.findAll();
        assertThat(mChatSystemMessageList).hasSize(databaseSizeBeforeUpdate);
        MChatSystemMessage testMChatSystemMessage = mChatSystemMessageList.get(mChatSystemMessageList.size() - 1);
        assertThat(testMChatSystemMessage.getMessageType()).isEqualTo(UPDATED_MESSAGE_TYPE);
        assertThat(testMChatSystemMessage.getMessageKey()).isEqualTo(UPDATED_MESSAGE_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingMChatSystemMessage() throws Exception {
        int databaseSizeBeforeUpdate = mChatSystemMessageRepository.findAll().size();

        // Create the MChatSystemMessage
        MChatSystemMessageDTO mChatSystemMessageDTO = mChatSystemMessageMapper.toDto(mChatSystemMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMChatSystemMessageMockMvc.perform(put("/api/m-chat-system-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mChatSystemMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MChatSystemMessage in the database
        List<MChatSystemMessage> mChatSystemMessageList = mChatSystemMessageRepository.findAll();
        assertThat(mChatSystemMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMChatSystemMessage() throws Exception {
        // Initialize the database
        mChatSystemMessageRepository.saveAndFlush(mChatSystemMessage);

        int databaseSizeBeforeDelete = mChatSystemMessageRepository.findAll().size();

        // Delete the mChatSystemMessage
        restMChatSystemMessageMockMvc.perform(delete("/api/m-chat-system-messages/{id}", mChatSystemMessage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MChatSystemMessage> mChatSystemMessageList = mChatSystemMessageRepository.findAll();
        assertThat(mChatSystemMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChatSystemMessage.class);
        MChatSystemMessage mChatSystemMessage1 = new MChatSystemMessage();
        mChatSystemMessage1.setId(1L);
        MChatSystemMessage mChatSystemMessage2 = new MChatSystemMessage();
        mChatSystemMessage2.setId(mChatSystemMessage1.getId());
        assertThat(mChatSystemMessage1).isEqualTo(mChatSystemMessage2);
        mChatSystemMessage2.setId(2L);
        assertThat(mChatSystemMessage1).isNotEqualTo(mChatSystemMessage2);
        mChatSystemMessage1.setId(null);
        assertThat(mChatSystemMessage1).isNotEqualTo(mChatSystemMessage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MChatSystemMessageDTO.class);
        MChatSystemMessageDTO mChatSystemMessageDTO1 = new MChatSystemMessageDTO();
        mChatSystemMessageDTO1.setId(1L);
        MChatSystemMessageDTO mChatSystemMessageDTO2 = new MChatSystemMessageDTO();
        assertThat(mChatSystemMessageDTO1).isNotEqualTo(mChatSystemMessageDTO2);
        mChatSystemMessageDTO2.setId(mChatSystemMessageDTO1.getId());
        assertThat(mChatSystemMessageDTO1).isEqualTo(mChatSystemMessageDTO2);
        mChatSystemMessageDTO2.setId(2L);
        assertThat(mChatSystemMessageDTO1).isNotEqualTo(mChatSystemMessageDTO2);
        mChatSystemMessageDTO1.setId(null);
        assertThat(mChatSystemMessageDTO1).isNotEqualTo(mChatSystemMessageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mChatSystemMessageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mChatSystemMessageMapper.fromId(null)).isNull();
    }
}
