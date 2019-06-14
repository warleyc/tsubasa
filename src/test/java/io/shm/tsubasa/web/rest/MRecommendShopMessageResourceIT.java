package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MRecommendShopMessage;
import io.shm.tsubasa.repository.MRecommendShopMessageRepository;
import io.shm.tsubasa.service.MRecommendShopMessageService;
import io.shm.tsubasa.service.dto.MRecommendShopMessageDTO;
import io.shm.tsubasa.service.mapper.MRecommendShopMessageMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MRecommendShopMessageCriteria;
import io.shm.tsubasa.service.MRecommendShopMessageQueryService;

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
 * Integration tests for the {@Link MRecommendShopMessageResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MRecommendShopMessageResourceIT {

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_HAS_SALES_PERIOD = 1;
    private static final Integer UPDATED_HAS_SALES_PERIOD = 2;

    @Autowired
    private MRecommendShopMessageRepository mRecommendShopMessageRepository;

    @Autowired
    private MRecommendShopMessageMapper mRecommendShopMessageMapper;

    @Autowired
    private MRecommendShopMessageService mRecommendShopMessageService;

    @Autowired
    private MRecommendShopMessageQueryService mRecommendShopMessageQueryService;

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

    private MockMvc restMRecommendShopMessageMockMvc;

    private MRecommendShopMessage mRecommendShopMessage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MRecommendShopMessageResource mRecommendShopMessageResource = new MRecommendShopMessageResource(mRecommendShopMessageService, mRecommendShopMessageQueryService);
        this.restMRecommendShopMessageMockMvc = MockMvcBuilders.standaloneSetup(mRecommendShopMessageResource)
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
    public static MRecommendShopMessage createEntity(EntityManager em) {
        MRecommendShopMessage mRecommendShopMessage = new MRecommendShopMessage()
            .message(DEFAULT_MESSAGE)
            .hasSalesPeriod(DEFAULT_HAS_SALES_PERIOD);
        return mRecommendShopMessage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MRecommendShopMessage createUpdatedEntity(EntityManager em) {
        MRecommendShopMessage mRecommendShopMessage = new MRecommendShopMessage()
            .message(UPDATED_MESSAGE)
            .hasSalesPeriod(UPDATED_HAS_SALES_PERIOD);
        return mRecommendShopMessage;
    }

    @BeforeEach
    public void initTest() {
        mRecommendShopMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createMRecommendShopMessage() throws Exception {
        int databaseSizeBeforeCreate = mRecommendShopMessageRepository.findAll().size();

        // Create the MRecommendShopMessage
        MRecommendShopMessageDTO mRecommendShopMessageDTO = mRecommendShopMessageMapper.toDto(mRecommendShopMessage);
        restMRecommendShopMessageMockMvc.perform(post("/api/m-recommend-shop-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendShopMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the MRecommendShopMessage in the database
        List<MRecommendShopMessage> mRecommendShopMessageList = mRecommendShopMessageRepository.findAll();
        assertThat(mRecommendShopMessageList).hasSize(databaseSizeBeforeCreate + 1);
        MRecommendShopMessage testMRecommendShopMessage = mRecommendShopMessageList.get(mRecommendShopMessageList.size() - 1);
        assertThat(testMRecommendShopMessage.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testMRecommendShopMessage.getHasSalesPeriod()).isEqualTo(DEFAULT_HAS_SALES_PERIOD);
    }

    @Test
    @Transactional
    public void createMRecommendShopMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mRecommendShopMessageRepository.findAll().size();

        // Create the MRecommendShopMessage with an existing ID
        mRecommendShopMessage.setId(1L);
        MRecommendShopMessageDTO mRecommendShopMessageDTO = mRecommendShopMessageMapper.toDto(mRecommendShopMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMRecommendShopMessageMockMvc.perform(post("/api/m-recommend-shop-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendShopMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRecommendShopMessage in the database
        List<MRecommendShopMessage> mRecommendShopMessageList = mRecommendShopMessageRepository.findAll();
        assertThat(mRecommendShopMessageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkHasSalesPeriodIsRequired() throws Exception {
        int databaseSizeBeforeTest = mRecommendShopMessageRepository.findAll().size();
        // set the field null
        mRecommendShopMessage.setHasSalesPeriod(null);

        // Create the MRecommendShopMessage, which fails.
        MRecommendShopMessageDTO mRecommendShopMessageDTO = mRecommendShopMessageMapper.toDto(mRecommendShopMessage);

        restMRecommendShopMessageMockMvc.perform(post("/api/m-recommend-shop-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendShopMessageDTO)))
            .andExpect(status().isBadRequest());

        List<MRecommendShopMessage> mRecommendShopMessageList = mRecommendShopMessageRepository.findAll();
        assertThat(mRecommendShopMessageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMRecommendShopMessages() throws Exception {
        // Initialize the database
        mRecommendShopMessageRepository.saveAndFlush(mRecommendShopMessage);

        // Get all the mRecommendShopMessageList
        restMRecommendShopMessageMockMvc.perform(get("/api/m-recommend-shop-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRecommendShopMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].hasSalesPeriod").value(hasItem(DEFAULT_HAS_SALES_PERIOD)));
    }
    
    @Test
    @Transactional
    public void getMRecommendShopMessage() throws Exception {
        // Initialize the database
        mRecommendShopMessageRepository.saveAndFlush(mRecommendShopMessage);

        // Get the mRecommendShopMessage
        restMRecommendShopMessageMockMvc.perform(get("/api/m-recommend-shop-messages/{id}", mRecommendShopMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mRecommendShopMessage.getId().intValue()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()))
            .andExpect(jsonPath("$.hasSalesPeriod").value(DEFAULT_HAS_SALES_PERIOD));
    }

    @Test
    @Transactional
    public void getAllMRecommendShopMessagesByHasSalesPeriodIsEqualToSomething() throws Exception {
        // Initialize the database
        mRecommendShopMessageRepository.saveAndFlush(mRecommendShopMessage);

        // Get all the mRecommendShopMessageList where hasSalesPeriod equals to DEFAULT_HAS_SALES_PERIOD
        defaultMRecommendShopMessageShouldBeFound("hasSalesPeriod.equals=" + DEFAULT_HAS_SALES_PERIOD);

        // Get all the mRecommendShopMessageList where hasSalesPeriod equals to UPDATED_HAS_SALES_PERIOD
        defaultMRecommendShopMessageShouldNotBeFound("hasSalesPeriod.equals=" + UPDATED_HAS_SALES_PERIOD);
    }

    @Test
    @Transactional
    public void getAllMRecommendShopMessagesByHasSalesPeriodIsInShouldWork() throws Exception {
        // Initialize the database
        mRecommendShopMessageRepository.saveAndFlush(mRecommendShopMessage);

        // Get all the mRecommendShopMessageList where hasSalesPeriod in DEFAULT_HAS_SALES_PERIOD or UPDATED_HAS_SALES_PERIOD
        defaultMRecommendShopMessageShouldBeFound("hasSalesPeriod.in=" + DEFAULT_HAS_SALES_PERIOD + "," + UPDATED_HAS_SALES_PERIOD);

        // Get all the mRecommendShopMessageList where hasSalesPeriod equals to UPDATED_HAS_SALES_PERIOD
        defaultMRecommendShopMessageShouldNotBeFound("hasSalesPeriod.in=" + UPDATED_HAS_SALES_PERIOD);
    }

    @Test
    @Transactional
    public void getAllMRecommendShopMessagesByHasSalesPeriodIsNullOrNotNull() throws Exception {
        // Initialize the database
        mRecommendShopMessageRepository.saveAndFlush(mRecommendShopMessage);

        // Get all the mRecommendShopMessageList where hasSalesPeriod is not null
        defaultMRecommendShopMessageShouldBeFound("hasSalesPeriod.specified=true");

        // Get all the mRecommendShopMessageList where hasSalesPeriod is null
        defaultMRecommendShopMessageShouldNotBeFound("hasSalesPeriod.specified=false");
    }

    @Test
    @Transactional
    public void getAllMRecommendShopMessagesByHasSalesPeriodIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mRecommendShopMessageRepository.saveAndFlush(mRecommendShopMessage);

        // Get all the mRecommendShopMessageList where hasSalesPeriod greater than or equals to DEFAULT_HAS_SALES_PERIOD
        defaultMRecommendShopMessageShouldBeFound("hasSalesPeriod.greaterOrEqualThan=" + DEFAULT_HAS_SALES_PERIOD);

        // Get all the mRecommendShopMessageList where hasSalesPeriod greater than or equals to UPDATED_HAS_SALES_PERIOD
        defaultMRecommendShopMessageShouldNotBeFound("hasSalesPeriod.greaterOrEqualThan=" + UPDATED_HAS_SALES_PERIOD);
    }

    @Test
    @Transactional
    public void getAllMRecommendShopMessagesByHasSalesPeriodIsLessThanSomething() throws Exception {
        // Initialize the database
        mRecommendShopMessageRepository.saveAndFlush(mRecommendShopMessage);

        // Get all the mRecommendShopMessageList where hasSalesPeriod less than or equals to DEFAULT_HAS_SALES_PERIOD
        defaultMRecommendShopMessageShouldNotBeFound("hasSalesPeriod.lessThan=" + DEFAULT_HAS_SALES_PERIOD);

        // Get all the mRecommendShopMessageList where hasSalesPeriod less than or equals to UPDATED_HAS_SALES_PERIOD
        defaultMRecommendShopMessageShouldBeFound("hasSalesPeriod.lessThan=" + UPDATED_HAS_SALES_PERIOD);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMRecommendShopMessageShouldBeFound(String filter) throws Exception {
        restMRecommendShopMessageMockMvc.perform(get("/api/m-recommend-shop-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mRecommendShopMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].hasSalesPeriod").value(hasItem(DEFAULT_HAS_SALES_PERIOD)));

        // Check, that the count call also returns 1
        restMRecommendShopMessageMockMvc.perform(get("/api/m-recommend-shop-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMRecommendShopMessageShouldNotBeFound(String filter) throws Exception {
        restMRecommendShopMessageMockMvc.perform(get("/api/m-recommend-shop-messages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMRecommendShopMessageMockMvc.perform(get("/api/m-recommend-shop-messages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMRecommendShopMessage() throws Exception {
        // Get the mRecommendShopMessage
        restMRecommendShopMessageMockMvc.perform(get("/api/m-recommend-shop-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMRecommendShopMessage() throws Exception {
        // Initialize the database
        mRecommendShopMessageRepository.saveAndFlush(mRecommendShopMessage);

        int databaseSizeBeforeUpdate = mRecommendShopMessageRepository.findAll().size();

        // Update the mRecommendShopMessage
        MRecommendShopMessage updatedMRecommendShopMessage = mRecommendShopMessageRepository.findById(mRecommendShopMessage.getId()).get();
        // Disconnect from session so that the updates on updatedMRecommendShopMessage are not directly saved in db
        em.detach(updatedMRecommendShopMessage);
        updatedMRecommendShopMessage
            .message(UPDATED_MESSAGE)
            .hasSalesPeriod(UPDATED_HAS_SALES_PERIOD);
        MRecommendShopMessageDTO mRecommendShopMessageDTO = mRecommendShopMessageMapper.toDto(updatedMRecommendShopMessage);

        restMRecommendShopMessageMockMvc.perform(put("/api/m-recommend-shop-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendShopMessageDTO)))
            .andExpect(status().isOk());

        // Validate the MRecommendShopMessage in the database
        List<MRecommendShopMessage> mRecommendShopMessageList = mRecommendShopMessageRepository.findAll();
        assertThat(mRecommendShopMessageList).hasSize(databaseSizeBeforeUpdate);
        MRecommendShopMessage testMRecommendShopMessage = mRecommendShopMessageList.get(mRecommendShopMessageList.size() - 1);
        assertThat(testMRecommendShopMessage.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testMRecommendShopMessage.getHasSalesPeriod()).isEqualTo(UPDATED_HAS_SALES_PERIOD);
    }

    @Test
    @Transactional
    public void updateNonExistingMRecommendShopMessage() throws Exception {
        int databaseSizeBeforeUpdate = mRecommendShopMessageRepository.findAll().size();

        // Create the MRecommendShopMessage
        MRecommendShopMessageDTO mRecommendShopMessageDTO = mRecommendShopMessageMapper.toDto(mRecommendShopMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMRecommendShopMessageMockMvc.perform(put("/api/m-recommend-shop-messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mRecommendShopMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MRecommendShopMessage in the database
        List<MRecommendShopMessage> mRecommendShopMessageList = mRecommendShopMessageRepository.findAll();
        assertThat(mRecommendShopMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMRecommendShopMessage() throws Exception {
        // Initialize the database
        mRecommendShopMessageRepository.saveAndFlush(mRecommendShopMessage);

        int databaseSizeBeforeDelete = mRecommendShopMessageRepository.findAll().size();

        // Delete the mRecommendShopMessage
        restMRecommendShopMessageMockMvc.perform(delete("/api/m-recommend-shop-messages/{id}", mRecommendShopMessage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MRecommendShopMessage> mRecommendShopMessageList = mRecommendShopMessageRepository.findAll();
        assertThat(mRecommendShopMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRecommendShopMessage.class);
        MRecommendShopMessage mRecommendShopMessage1 = new MRecommendShopMessage();
        mRecommendShopMessage1.setId(1L);
        MRecommendShopMessage mRecommendShopMessage2 = new MRecommendShopMessage();
        mRecommendShopMessage2.setId(mRecommendShopMessage1.getId());
        assertThat(mRecommendShopMessage1).isEqualTo(mRecommendShopMessage2);
        mRecommendShopMessage2.setId(2L);
        assertThat(mRecommendShopMessage1).isNotEqualTo(mRecommendShopMessage2);
        mRecommendShopMessage1.setId(null);
        assertThat(mRecommendShopMessage1).isNotEqualTo(mRecommendShopMessage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MRecommendShopMessageDTO.class);
        MRecommendShopMessageDTO mRecommendShopMessageDTO1 = new MRecommendShopMessageDTO();
        mRecommendShopMessageDTO1.setId(1L);
        MRecommendShopMessageDTO mRecommendShopMessageDTO2 = new MRecommendShopMessageDTO();
        assertThat(mRecommendShopMessageDTO1).isNotEqualTo(mRecommendShopMessageDTO2);
        mRecommendShopMessageDTO2.setId(mRecommendShopMessageDTO1.getId());
        assertThat(mRecommendShopMessageDTO1).isEqualTo(mRecommendShopMessageDTO2);
        mRecommendShopMessageDTO2.setId(2L);
        assertThat(mRecommendShopMessageDTO1).isNotEqualTo(mRecommendShopMessageDTO2);
        mRecommendShopMessageDTO1.setId(null);
        assertThat(mRecommendShopMessageDTO1).isNotEqualTo(mRecommendShopMessageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mRecommendShopMessageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mRecommendShopMessageMapper.fromId(null)).isNull();
    }
}
