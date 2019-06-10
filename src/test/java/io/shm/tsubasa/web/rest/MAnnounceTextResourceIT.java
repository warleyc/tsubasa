package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MAnnounceText;
import io.shm.tsubasa.repository.MAnnounceTextRepository;
import io.shm.tsubasa.service.MAnnounceTextService;
import io.shm.tsubasa.service.dto.MAnnounceTextDTO;
import io.shm.tsubasa.service.mapper.MAnnounceTextMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MAnnounceTextCriteria;
import io.shm.tsubasa.service.MAnnounceTextQueryService;

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
 * Integration tests for the {@Link MAnnounceTextResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MAnnounceTextResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_SEQ_NO = 1;
    private static final Integer UPDATED_SEQ_NO = 2;

    private static final String DEFAULT_NORMAL_ANNOUNCE = "AAAAAAAAAA";
    private static final String UPDATED_NORMAL_ANNOUNCE = "BBBBBBBBBB";

    private static final String DEFAULT_CRITICAL_S_ANNOUNCE = "AAAAAAAAAA";
    private static final String UPDATED_CRITICAL_S_ANNOUNCE = "BBBBBBBBBB";

    private static final String DEFAULT_CRITICAL_M_ANNOUNCE = "AAAAAAAAAA";
    private static final String UPDATED_CRITICAL_M_ANNOUNCE = "BBBBBBBBBB";

    private static final String DEFAULT_CRITICAL_L_ANNOUNCE = "AAAAAAAAAA";
    private static final String UPDATED_CRITICAL_L_ANNOUNCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DELAY_TIME = 1;
    private static final Integer UPDATED_DELAY_TIME = 2;

    private static final Integer DEFAULT_CONTINUE_TIME = 1;
    private static final Integer UPDATED_CONTINUE_TIME = 2;

    @Autowired
    private MAnnounceTextRepository mAnnounceTextRepository;

    @Autowired
    private MAnnounceTextMapper mAnnounceTextMapper;

    @Autowired
    private MAnnounceTextService mAnnounceTextService;

    @Autowired
    private MAnnounceTextQueryService mAnnounceTextQueryService;

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

    private MockMvc restMAnnounceTextMockMvc;

    private MAnnounceText mAnnounceText;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MAnnounceTextResource mAnnounceTextResource = new MAnnounceTextResource(mAnnounceTextService, mAnnounceTextQueryService);
        this.restMAnnounceTextMockMvc = MockMvcBuilders.standaloneSetup(mAnnounceTextResource)
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
    public static MAnnounceText createEntity(EntityManager em) {
        MAnnounceText mAnnounceText = new MAnnounceText()
            .groupId(DEFAULT_GROUP_ID)
            .seqNo(DEFAULT_SEQ_NO)
            .normalAnnounce(DEFAULT_NORMAL_ANNOUNCE)
            .criticalSAnnounce(DEFAULT_CRITICAL_S_ANNOUNCE)
            .criticalMAnnounce(DEFAULT_CRITICAL_M_ANNOUNCE)
            .criticalLAnnounce(DEFAULT_CRITICAL_L_ANNOUNCE)
            .delayTime(DEFAULT_DELAY_TIME)
            .continueTime(DEFAULT_CONTINUE_TIME);
        return mAnnounceText;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MAnnounceText createUpdatedEntity(EntityManager em) {
        MAnnounceText mAnnounceText = new MAnnounceText()
            .groupId(UPDATED_GROUP_ID)
            .seqNo(UPDATED_SEQ_NO)
            .normalAnnounce(UPDATED_NORMAL_ANNOUNCE)
            .criticalSAnnounce(UPDATED_CRITICAL_S_ANNOUNCE)
            .criticalMAnnounce(UPDATED_CRITICAL_M_ANNOUNCE)
            .criticalLAnnounce(UPDATED_CRITICAL_L_ANNOUNCE)
            .delayTime(UPDATED_DELAY_TIME)
            .continueTime(UPDATED_CONTINUE_TIME);
        return mAnnounceText;
    }

    @BeforeEach
    public void initTest() {
        mAnnounceText = createEntity(em);
    }

    @Test
    @Transactional
    public void createMAnnounceText() throws Exception {
        int databaseSizeBeforeCreate = mAnnounceTextRepository.findAll().size();

        // Create the MAnnounceText
        MAnnounceTextDTO mAnnounceTextDTO = mAnnounceTextMapper.toDto(mAnnounceText);
        restMAnnounceTextMockMvc.perform(post("/api/m-announce-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAnnounceTextDTO)))
            .andExpect(status().isCreated());

        // Validate the MAnnounceText in the database
        List<MAnnounceText> mAnnounceTextList = mAnnounceTextRepository.findAll();
        assertThat(mAnnounceTextList).hasSize(databaseSizeBeforeCreate + 1);
        MAnnounceText testMAnnounceText = mAnnounceTextList.get(mAnnounceTextList.size() - 1);
        assertThat(testMAnnounceText.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMAnnounceText.getSeqNo()).isEqualTo(DEFAULT_SEQ_NO);
        assertThat(testMAnnounceText.getNormalAnnounce()).isEqualTo(DEFAULT_NORMAL_ANNOUNCE);
        assertThat(testMAnnounceText.getCriticalSAnnounce()).isEqualTo(DEFAULT_CRITICAL_S_ANNOUNCE);
        assertThat(testMAnnounceText.getCriticalMAnnounce()).isEqualTo(DEFAULT_CRITICAL_M_ANNOUNCE);
        assertThat(testMAnnounceText.getCriticalLAnnounce()).isEqualTo(DEFAULT_CRITICAL_L_ANNOUNCE);
        assertThat(testMAnnounceText.getDelayTime()).isEqualTo(DEFAULT_DELAY_TIME);
        assertThat(testMAnnounceText.getContinueTime()).isEqualTo(DEFAULT_CONTINUE_TIME);
    }

    @Test
    @Transactional
    public void createMAnnounceTextWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mAnnounceTextRepository.findAll().size();

        // Create the MAnnounceText with an existing ID
        mAnnounceText.setId(1L);
        MAnnounceTextDTO mAnnounceTextDTO = mAnnounceTextMapper.toDto(mAnnounceText);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMAnnounceTextMockMvc.perform(post("/api/m-announce-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAnnounceTextDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAnnounceText in the database
        List<MAnnounceText> mAnnounceTextList = mAnnounceTextRepository.findAll();
        assertThat(mAnnounceTextList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAnnounceTextRepository.findAll().size();
        // set the field null
        mAnnounceText.setGroupId(null);

        // Create the MAnnounceText, which fails.
        MAnnounceTextDTO mAnnounceTextDTO = mAnnounceTextMapper.toDto(mAnnounceText);

        restMAnnounceTextMockMvc.perform(post("/api/m-announce-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAnnounceTextDTO)))
            .andExpect(status().isBadRequest());

        List<MAnnounceText> mAnnounceTextList = mAnnounceTextRepository.findAll();
        assertThat(mAnnounceTextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSeqNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = mAnnounceTextRepository.findAll().size();
        // set the field null
        mAnnounceText.setSeqNo(null);

        // Create the MAnnounceText, which fails.
        MAnnounceTextDTO mAnnounceTextDTO = mAnnounceTextMapper.toDto(mAnnounceText);

        restMAnnounceTextMockMvc.perform(post("/api/m-announce-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAnnounceTextDTO)))
            .andExpect(status().isBadRequest());

        List<MAnnounceText> mAnnounceTextList = mAnnounceTextRepository.findAll();
        assertThat(mAnnounceTextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTexts() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList
        restMAnnounceTextMockMvc.perform(get("/api/m-announce-texts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAnnounceText.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].seqNo").value(hasItem(DEFAULT_SEQ_NO)))
            .andExpect(jsonPath("$.[*].normalAnnounce").value(hasItem(DEFAULT_NORMAL_ANNOUNCE.toString())))
            .andExpect(jsonPath("$.[*].criticalSAnnounce").value(hasItem(DEFAULT_CRITICAL_S_ANNOUNCE.toString())))
            .andExpect(jsonPath("$.[*].criticalMAnnounce").value(hasItem(DEFAULT_CRITICAL_M_ANNOUNCE.toString())))
            .andExpect(jsonPath("$.[*].criticalLAnnounce").value(hasItem(DEFAULT_CRITICAL_L_ANNOUNCE.toString())))
            .andExpect(jsonPath("$.[*].delayTime").value(hasItem(DEFAULT_DELAY_TIME)))
            .andExpect(jsonPath("$.[*].continueTime").value(hasItem(DEFAULT_CONTINUE_TIME)));
    }
    
    @Test
    @Transactional
    public void getMAnnounceText() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get the mAnnounceText
        restMAnnounceTextMockMvc.perform(get("/api/m-announce-texts/{id}", mAnnounceText.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mAnnounceText.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.seqNo").value(DEFAULT_SEQ_NO))
            .andExpect(jsonPath("$.normalAnnounce").value(DEFAULT_NORMAL_ANNOUNCE.toString()))
            .andExpect(jsonPath("$.criticalSAnnounce").value(DEFAULT_CRITICAL_S_ANNOUNCE.toString()))
            .andExpect(jsonPath("$.criticalMAnnounce").value(DEFAULT_CRITICAL_M_ANNOUNCE.toString()))
            .andExpect(jsonPath("$.criticalLAnnounce").value(DEFAULT_CRITICAL_L_ANNOUNCE.toString()))
            .andExpect(jsonPath("$.delayTime").value(DEFAULT_DELAY_TIME))
            .andExpect(jsonPath("$.continueTime").value(DEFAULT_CONTINUE_TIME));
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where groupId equals to DEFAULT_GROUP_ID
        defaultMAnnounceTextShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mAnnounceTextList where groupId equals to UPDATED_GROUP_ID
        defaultMAnnounceTextShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMAnnounceTextShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mAnnounceTextList where groupId equals to UPDATED_GROUP_ID
        defaultMAnnounceTextShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where groupId is not null
        defaultMAnnounceTextShouldBeFound("groupId.specified=true");

        // Get all the mAnnounceTextList where groupId is null
        defaultMAnnounceTextShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMAnnounceTextShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mAnnounceTextList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMAnnounceTextShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMAnnounceTextShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mAnnounceTextList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMAnnounceTextShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMAnnounceTextsBySeqNoIsEqualToSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where seqNo equals to DEFAULT_SEQ_NO
        defaultMAnnounceTextShouldBeFound("seqNo.equals=" + DEFAULT_SEQ_NO);

        // Get all the mAnnounceTextList where seqNo equals to UPDATED_SEQ_NO
        defaultMAnnounceTextShouldNotBeFound("seqNo.equals=" + UPDATED_SEQ_NO);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsBySeqNoIsInShouldWork() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where seqNo in DEFAULT_SEQ_NO or UPDATED_SEQ_NO
        defaultMAnnounceTextShouldBeFound("seqNo.in=" + DEFAULT_SEQ_NO + "," + UPDATED_SEQ_NO);

        // Get all the mAnnounceTextList where seqNo equals to UPDATED_SEQ_NO
        defaultMAnnounceTextShouldNotBeFound("seqNo.in=" + UPDATED_SEQ_NO);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsBySeqNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where seqNo is not null
        defaultMAnnounceTextShouldBeFound("seqNo.specified=true");

        // Get all the mAnnounceTextList where seqNo is null
        defaultMAnnounceTextShouldNotBeFound("seqNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsBySeqNoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where seqNo greater than or equals to DEFAULT_SEQ_NO
        defaultMAnnounceTextShouldBeFound("seqNo.greaterOrEqualThan=" + DEFAULT_SEQ_NO);

        // Get all the mAnnounceTextList where seqNo greater than or equals to UPDATED_SEQ_NO
        defaultMAnnounceTextShouldNotBeFound("seqNo.greaterOrEqualThan=" + UPDATED_SEQ_NO);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsBySeqNoIsLessThanSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where seqNo less than or equals to DEFAULT_SEQ_NO
        defaultMAnnounceTextShouldNotBeFound("seqNo.lessThan=" + DEFAULT_SEQ_NO);

        // Get all the mAnnounceTextList where seqNo less than or equals to UPDATED_SEQ_NO
        defaultMAnnounceTextShouldBeFound("seqNo.lessThan=" + UPDATED_SEQ_NO);
    }


    @Test
    @Transactional
    public void getAllMAnnounceTextsByDelayTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where delayTime equals to DEFAULT_DELAY_TIME
        defaultMAnnounceTextShouldBeFound("delayTime.equals=" + DEFAULT_DELAY_TIME);

        // Get all the mAnnounceTextList where delayTime equals to UPDATED_DELAY_TIME
        defaultMAnnounceTextShouldNotBeFound("delayTime.equals=" + UPDATED_DELAY_TIME);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByDelayTimeIsInShouldWork() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where delayTime in DEFAULT_DELAY_TIME or UPDATED_DELAY_TIME
        defaultMAnnounceTextShouldBeFound("delayTime.in=" + DEFAULT_DELAY_TIME + "," + UPDATED_DELAY_TIME);

        // Get all the mAnnounceTextList where delayTime equals to UPDATED_DELAY_TIME
        defaultMAnnounceTextShouldNotBeFound("delayTime.in=" + UPDATED_DELAY_TIME);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByDelayTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where delayTime is not null
        defaultMAnnounceTextShouldBeFound("delayTime.specified=true");

        // Get all the mAnnounceTextList where delayTime is null
        defaultMAnnounceTextShouldNotBeFound("delayTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByDelayTimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where delayTime greater than or equals to DEFAULT_DELAY_TIME
        defaultMAnnounceTextShouldBeFound("delayTime.greaterOrEqualThan=" + DEFAULT_DELAY_TIME);

        // Get all the mAnnounceTextList where delayTime greater than or equals to UPDATED_DELAY_TIME
        defaultMAnnounceTextShouldNotBeFound("delayTime.greaterOrEqualThan=" + UPDATED_DELAY_TIME);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByDelayTimeIsLessThanSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where delayTime less than or equals to DEFAULT_DELAY_TIME
        defaultMAnnounceTextShouldNotBeFound("delayTime.lessThan=" + DEFAULT_DELAY_TIME);

        // Get all the mAnnounceTextList where delayTime less than or equals to UPDATED_DELAY_TIME
        defaultMAnnounceTextShouldBeFound("delayTime.lessThan=" + UPDATED_DELAY_TIME);
    }


    @Test
    @Transactional
    public void getAllMAnnounceTextsByContinueTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where continueTime equals to DEFAULT_CONTINUE_TIME
        defaultMAnnounceTextShouldBeFound("continueTime.equals=" + DEFAULT_CONTINUE_TIME);

        // Get all the mAnnounceTextList where continueTime equals to UPDATED_CONTINUE_TIME
        defaultMAnnounceTextShouldNotBeFound("continueTime.equals=" + UPDATED_CONTINUE_TIME);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByContinueTimeIsInShouldWork() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where continueTime in DEFAULT_CONTINUE_TIME or UPDATED_CONTINUE_TIME
        defaultMAnnounceTextShouldBeFound("continueTime.in=" + DEFAULT_CONTINUE_TIME + "," + UPDATED_CONTINUE_TIME);

        // Get all the mAnnounceTextList where continueTime equals to UPDATED_CONTINUE_TIME
        defaultMAnnounceTextShouldNotBeFound("continueTime.in=" + UPDATED_CONTINUE_TIME);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByContinueTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where continueTime is not null
        defaultMAnnounceTextShouldBeFound("continueTime.specified=true");

        // Get all the mAnnounceTextList where continueTime is null
        defaultMAnnounceTextShouldNotBeFound("continueTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByContinueTimeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where continueTime greater than or equals to DEFAULT_CONTINUE_TIME
        defaultMAnnounceTextShouldBeFound("continueTime.greaterOrEqualThan=" + DEFAULT_CONTINUE_TIME);

        // Get all the mAnnounceTextList where continueTime greater than or equals to UPDATED_CONTINUE_TIME
        defaultMAnnounceTextShouldNotBeFound("continueTime.greaterOrEqualThan=" + UPDATED_CONTINUE_TIME);
    }

    @Test
    @Transactional
    public void getAllMAnnounceTextsByContinueTimeIsLessThanSomething() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        // Get all the mAnnounceTextList where continueTime less than or equals to DEFAULT_CONTINUE_TIME
        defaultMAnnounceTextShouldNotBeFound("continueTime.lessThan=" + DEFAULT_CONTINUE_TIME);

        // Get all the mAnnounceTextList where continueTime less than or equals to UPDATED_CONTINUE_TIME
        defaultMAnnounceTextShouldBeFound("continueTime.lessThan=" + UPDATED_CONTINUE_TIME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMAnnounceTextShouldBeFound(String filter) throws Exception {
        restMAnnounceTextMockMvc.perform(get("/api/m-announce-texts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mAnnounceText.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].seqNo").value(hasItem(DEFAULT_SEQ_NO)))
            .andExpect(jsonPath("$.[*].normalAnnounce").value(hasItem(DEFAULT_NORMAL_ANNOUNCE.toString())))
            .andExpect(jsonPath("$.[*].criticalSAnnounce").value(hasItem(DEFAULT_CRITICAL_S_ANNOUNCE.toString())))
            .andExpect(jsonPath("$.[*].criticalMAnnounce").value(hasItem(DEFAULT_CRITICAL_M_ANNOUNCE.toString())))
            .andExpect(jsonPath("$.[*].criticalLAnnounce").value(hasItem(DEFAULT_CRITICAL_L_ANNOUNCE.toString())))
            .andExpect(jsonPath("$.[*].delayTime").value(hasItem(DEFAULT_DELAY_TIME)))
            .andExpect(jsonPath("$.[*].continueTime").value(hasItem(DEFAULT_CONTINUE_TIME)));

        // Check, that the count call also returns 1
        restMAnnounceTextMockMvc.perform(get("/api/m-announce-texts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMAnnounceTextShouldNotBeFound(String filter) throws Exception {
        restMAnnounceTextMockMvc.perform(get("/api/m-announce-texts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMAnnounceTextMockMvc.perform(get("/api/m-announce-texts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMAnnounceText() throws Exception {
        // Get the mAnnounceText
        restMAnnounceTextMockMvc.perform(get("/api/m-announce-texts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMAnnounceText() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        int databaseSizeBeforeUpdate = mAnnounceTextRepository.findAll().size();

        // Update the mAnnounceText
        MAnnounceText updatedMAnnounceText = mAnnounceTextRepository.findById(mAnnounceText.getId()).get();
        // Disconnect from session so that the updates on updatedMAnnounceText are not directly saved in db
        em.detach(updatedMAnnounceText);
        updatedMAnnounceText
            .groupId(UPDATED_GROUP_ID)
            .seqNo(UPDATED_SEQ_NO)
            .normalAnnounce(UPDATED_NORMAL_ANNOUNCE)
            .criticalSAnnounce(UPDATED_CRITICAL_S_ANNOUNCE)
            .criticalMAnnounce(UPDATED_CRITICAL_M_ANNOUNCE)
            .criticalLAnnounce(UPDATED_CRITICAL_L_ANNOUNCE)
            .delayTime(UPDATED_DELAY_TIME)
            .continueTime(UPDATED_CONTINUE_TIME);
        MAnnounceTextDTO mAnnounceTextDTO = mAnnounceTextMapper.toDto(updatedMAnnounceText);

        restMAnnounceTextMockMvc.perform(put("/api/m-announce-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAnnounceTextDTO)))
            .andExpect(status().isOk());

        // Validate the MAnnounceText in the database
        List<MAnnounceText> mAnnounceTextList = mAnnounceTextRepository.findAll();
        assertThat(mAnnounceTextList).hasSize(databaseSizeBeforeUpdate);
        MAnnounceText testMAnnounceText = mAnnounceTextList.get(mAnnounceTextList.size() - 1);
        assertThat(testMAnnounceText.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMAnnounceText.getSeqNo()).isEqualTo(UPDATED_SEQ_NO);
        assertThat(testMAnnounceText.getNormalAnnounce()).isEqualTo(UPDATED_NORMAL_ANNOUNCE);
        assertThat(testMAnnounceText.getCriticalSAnnounce()).isEqualTo(UPDATED_CRITICAL_S_ANNOUNCE);
        assertThat(testMAnnounceText.getCriticalMAnnounce()).isEqualTo(UPDATED_CRITICAL_M_ANNOUNCE);
        assertThat(testMAnnounceText.getCriticalLAnnounce()).isEqualTo(UPDATED_CRITICAL_L_ANNOUNCE);
        assertThat(testMAnnounceText.getDelayTime()).isEqualTo(UPDATED_DELAY_TIME);
        assertThat(testMAnnounceText.getContinueTime()).isEqualTo(UPDATED_CONTINUE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingMAnnounceText() throws Exception {
        int databaseSizeBeforeUpdate = mAnnounceTextRepository.findAll().size();

        // Create the MAnnounceText
        MAnnounceTextDTO mAnnounceTextDTO = mAnnounceTextMapper.toDto(mAnnounceText);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMAnnounceTextMockMvc.perform(put("/api/m-announce-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mAnnounceTextDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MAnnounceText in the database
        List<MAnnounceText> mAnnounceTextList = mAnnounceTextRepository.findAll();
        assertThat(mAnnounceTextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMAnnounceText() throws Exception {
        // Initialize the database
        mAnnounceTextRepository.saveAndFlush(mAnnounceText);

        int databaseSizeBeforeDelete = mAnnounceTextRepository.findAll().size();

        // Delete the mAnnounceText
        restMAnnounceTextMockMvc.perform(delete("/api/m-announce-texts/{id}", mAnnounceText.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MAnnounceText> mAnnounceTextList = mAnnounceTextRepository.findAll();
        assertThat(mAnnounceTextList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAnnounceText.class);
        MAnnounceText mAnnounceText1 = new MAnnounceText();
        mAnnounceText1.setId(1L);
        MAnnounceText mAnnounceText2 = new MAnnounceText();
        mAnnounceText2.setId(mAnnounceText1.getId());
        assertThat(mAnnounceText1).isEqualTo(mAnnounceText2);
        mAnnounceText2.setId(2L);
        assertThat(mAnnounceText1).isNotEqualTo(mAnnounceText2);
        mAnnounceText1.setId(null);
        assertThat(mAnnounceText1).isNotEqualTo(mAnnounceText2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MAnnounceTextDTO.class);
        MAnnounceTextDTO mAnnounceTextDTO1 = new MAnnounceTextDTO();
        mAnnounceTextDTO1.setId(1L);
        MAnnounceTextDTO mAnnounceTextDTO2 = new MAnnounceTextDTO();
        assertThat(mAnnounceTextDTO1).isNotEqualTo(mAnnounceTextDTO2);
        mAnnounceTextDTO2.setId(mAnnounceTextDTO1.getId());
        assertThat(mAnnounceTextDTO1).isEqualTo(mAnnounceTextDTO2);
        mAnnounceTextDTO2.setId(2L);
        assertThat(mAnnounceTextDTO1).isNotEqualTo(mAnnounceTextDTO2);
        mAnnounceTextDTO1.setId(null);
        assertThat(mAnnounceTextDTO1).isNotEqualTo(mAnnounceTextDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mAnnounceTextMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mAnnounceTextMapper.fromId(null)).isNull();
    }
}
