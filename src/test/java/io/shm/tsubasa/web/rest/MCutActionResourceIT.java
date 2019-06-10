package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCutAction;
import io.shm.tsubasa.repository.MCutActionRepository;
import io.shm.tsubasa.service.MCutActionService;
import io.shm.tsubasa.service.dto.MCutActionDTO;
import io.shm.tsubasa.service.mapper.MCutActionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCutActionCriteria;
import io.shm.tsubasa.service.MCutActionQueryService;

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
 * Integration tests for the {@Link MCutActionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCutActionResourceIT {

    private static final Integer DEFAULT_ACTION_CUT_ID = 1;
    private static final Integer UPDATED_ACTION_CUT_ID = 2;

    private static final Integer DEFAULT_CUT_ACTION_TYPE = 1;
    private static final Integer UPDATED_CUT_ACTION_TYPE = 2;

    private static final String DEFAULT_CUT_SEQUENCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CUT_SEQUENCE_TEXT = "BBBBBBBBBB";

    @Autowired
    private MCutActionRepository mCutActionRepository;

    @Autowired
    private MCutActionMapper mCutActionMapper;

    @Autowired
    private MCutActionService mCutActionService;

    @Autowired
    private MCutActionQueryService mCutActionQueryService;

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

    private MockMvc restMCutActionMockMvc;

    private MCutAction mCutAction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCutActionResource mCutActionResource = new MCutActionResource(mCutActionService, mCutActionQueryService);
        this.restMCutActionMockMvc = MockMvcBuilders.standaloneSetup(mCutActionResource)
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
    public static MCutAction createEntity(EntityManager em) {
        MCutAction mCutAction = new MCutAction()
            .actionCutId(DEFAULT_ACTION_CUT_ID)
            .cutActionType(DEFAULT_CUT_ACTION_TYPE)
            .cutSequenceText(DEFAULT_CUT_SEQUENCE_TEXT);
        return mCutAction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCutAction createUpdatedEntity(EntityManager em) {
        MCutAction mCutAction = new MCutAction()
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .cutActionType(UPDATED_CUT_ACTION_TYPE)
            .cutSequenceText(UPDATED_CUT_SEQUENCE_TEXT);
        return mCutAction;
    }

    @BeforeEach
    public void initTest() {
        mCutAction = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCutAction() throws Exception {
        int databaseSizeBeforeCreate = mCutActionRepository.findAll().size();

        // Create the MCutAction
        MCutActionDTO mCutActionDTO = mCutActionMapper.toDto(mCutAction);
        restMCutActionMockMvc.perform(post("/api/m-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutActionDTO)))
            .andExpect(status().isCreated());

        // Validate the MCutAction in the database
        List<MCutAction> mCutActionList = mCutActionRepository.findAll();
        assertThat(mCutActionList).hasSize(databaseSizeBeforeCreate + 1);
        MCutAction testMCutAction = mCutActionList.get(mCutActionList.size() - 1);
        assertThat(testMCutAction.getActionCutId()).isEqualTo(DEFAULT_ACTION_CUT_ID);
        assertThat(testMCutAction.getCutActionType()).isEqualTo(DEFAULT_CUT_ACTION_TYPE);
        assertThat(testMCutAction.getCutSequenceText()).isEqualTo(DEFAULT_CUT_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void createMCutActionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCutActionRepository.findAll().size();

        // Create the MCutAction with an existing ID
        mCutAction.setId(1L);
        MCutActionDTO mCutActionDTO = mCutActionMapper.toDto(mCutAction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCutActionMockMvc.perform(post("/api/m-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCutAction in the database
        List<MCutAction> mCutActionList = mCutActionRepository.findAll();
        assertThat(mCutActionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActionCutIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutActionRepository.findAll().size();
        // set the field null
        mCutAction.setActionCutId(null);

        // Create the MCutAction, which fails.
        MCutActionDTO mCutActionDTO = mCutActionMapper.toDto(mCutAction);

        restMCutActionMockMvc.perform(post("/api/m-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutActionDTO)))
            .andExpect(status().isBadRequest());

        List<MCutAction> mCutActionList = mCutActionRepository.findAll();
        assertThat(mCutActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCutActionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCutActionRepository.findAll().size();
        // set the field null
        mCutAction.setCutActionType(null);

        // Create the MCutAction, which fails.
        MCutActionDTO mCutActionDTO = mCutActionMapper.toDto(mCutAction);

        restMCutActionMockMvc.perform(post("/api/m-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutActionDTO)))
            .andExpect(status().isBadRequest());

        List<MCutAction> mCutActionList = mCutActionRepository.findAll();
        assertThat(mCutActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCutActions() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList
        restMCutActionMockMvc.perform(get("/api/m-cut-actions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCutAction.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].cutActionType").value(hasItem(DEFAULT_CUT_ACTION_TYPE)))
            .andExpect(jsonPath("$.[*].cutSequenceText").value(hasItem(DEFAULT_CUT_SEQUENCE_TEXT.toString())));
    }
    
    @Test
    @Transactional
    public void getMCutAction() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get the mCutAction
        restMCutActionMockMvc.perform(get("/api/m-cut-actions/{id}", mCutAction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCutAction.getId().intValue()))
            .andExpect(jsonPath("$.actionCutId").value(DEFAULT_ACTION_CUT_ID))
            .andExpect(jsonPath("$.cutActionType").value(DEFAULT_CUT_ACTION_TYPE))
            .andExpect(jsonPath("$.cutSequenceText").value(DEFAULT_CUT_SEQUENCE_TEXT.toString()));
    }

    @Test
    @Transactional
    public void getAllMCutActionsByActionCutIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where actionCutId equals to DEFAULT_ACTION_CUT_ID
        defaultMCutActionShouldBeFound("actionCutId.equals=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mCutActionList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMCutActionShouldNotBeFound("actionCutId.equals=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMCutActionsByActionCutIdIsInShouldWork() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where actionCutId in DEFAULT_ACTION_CUT_ID or UPDATED_ACTION_CUT_ID
        defaultMCutActionShouldBeFound("actionCutId.in=" + DEFAULT_ACTION_CUT_ID + "," + UPDATED_ACTION_CUT_ID);

        // Get all the mCutActionList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMCutActionShouldNotBeFound("actionCutId.in=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMCutActionsByActionCutIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where actionCutId is not null
        defaultMCutActionShouldBeFound("actionCutId.specified=true");

        // Get all the mCutActionList where actionCutId is null
        defaultMCutActionShouldNotBeFound("actionCutId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutActionsByActionCutIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where actionCutId greater than or equals to DEFAULT_ACTION_CUT_ID
        defaultMCutActionShouldBeFound("actionCutId.greaterOrEqualThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mCutActionList where actionCutId greater than or equals to UPDATED_ACTION_CUT_ID
        defaultMCutActionShouldNotBeFound("actionCutId.greaterOrEqualThan=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMCutActionsByActionCutIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where actionCutId less than or equals to DEFAULT_ACTION_CUT_ID
        defaultMCutActionShouldNotBeFound("actionCutId.lessThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mCutActionList where actionCutId less than or equals to UPDATED_ACTION_CUT_ID
        defaultMCutActionShouldBeFound("actionCutId.lessThan=" + UPDATED_ACTION_CUT_ID);
    }


    @Test
    @Transactional
    public void getAllMCutActionsByCutActionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where cutActionType equals to DEFAULT_CUT_ACTION_TYPE
        defaultMCutActionShouldBeFound("cutActionType.equals=" + DEFAULT_CUT_ACTION_TYPE);

        // Get all the mCutActionList where cutActionType equals to UPDATED_CUT_ACTION_TYPE
        defaultMCutActionShouldNotBeFound("cutActionType.equals=" + UPDATED_CUT_ACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCutActionsByCutActionTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where cutActionType in DEFAULT_CUT_ACTION_TYPE or UPDATED_CUT_ACTION_TYPE
        defaultMCutActionShouldBeFound("cutActionType.in=" + DEFAULT_CUT_ACTION_TYPE + "," + UPDATED_CUT_ACTION_TYPE);

        // Get all the mCutActionList where cutActionType equals to UPDATED_CUT_ACTION_TYPE
        defaultMCutActionShouldNotBeFound("cutActionType.in=" + UPDATED_CUT_ACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCutActionsByCutActionTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where cutActionType is not null
        defaultMCutActionShouldBeFound("cutActionType.specified=true");

        // Get all the mCutActionList where cutActionType is null
        defaultMCutActionShouldNotBeFound("cutActionType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCutActionsByCutActionTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where cutActionType greater than or equals to DEFAULT_CUT_ACTION_TYPE
        defaultMCutActionShouldBeFound("cutActionType.greaterOrEqualThan=" + DEFAULT_CUT_ACTION_TYPE);

        // Get all the mCutActionList where cutActionType greater than or equals to UPDATED_CUT_ACTION_TYPE
        defaultMCutActionShouldNotBeFound("cutActionType.greaterOrEqualThan=" + UPDATED_CUT_ACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMCutActionsByCutActionTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        // Get all the mCutActionList where cutActionType less than or equals to DEFAULT_CUT_ACTION_TYPE
        defaultMCutActionShouldNotBeFound("cutActionType.lessThan=" + DEFAULT_CUT_ACTION_TYPE);

        // Get all the mCutActionList where cutActionType less than or equals to UPDATED_CUT_ACTION_TYPE
        defaultMCutActionShouldBeFound("cutActionType.lessThan=" + UPDATED_CUT_ACTION_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCutActionShouldBeFound(String filter) throws Exception {
        restMCutActionMockMvc.perform(get("/api/m-cut-actions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCutAction.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].cutActionType").value(hasItem(DEFAULT_CUT_ACTION_TYPE)))
            .andExpect(jsonPath("$.[*].cutSequenceText").value(hasItem(DEFAULT_CUT_SEQUENCE_TEXT.toString())));

        // Check, that the count call also returns 1
        restMCutActionMockMvc.perform(get("/api/m-cut-actions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCutActionShouldNotBeFound(String filter) throws Exception {
        restMCutActionMockMvc.perform(get("/api/m-cut-actions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCutActionMockMvc.perform(get("/api/m-cut-actions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCutAction() throws Exception {
        // Get the mCutAction
        restMCutActionMockMvc.perform(get("/api/m-cut-actions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCutAction() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        int databaseSizeBeforeUpdate = mCutActionRepository.findAll().size();

        // Update the mCutAction
        MCutAction updatedMCutAction = mCutActionRepository.findById(mCutAction.getId()).get();
        // Disconnect from session so that the updates on updatedMCutAction are not directly saved in db
        em.detach(updatedMCutAction);
        updatedMCutAction
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .cutActionType(UPDATED_CUT_ACTION_TYPE)
            .cutSequenceText(UPDATED_CUT_SEQUENCE_TEXT);
        MCutActionDTO mCutActionDTO = mCutActionMapper.toDto(updatedMCutAction);

        restMCutActionMockMvc.perform(put("/api/m-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutActionDTO)))
            .andExpect(status().isOk());

        // Validate the MCutAction in the database
        List<MCutAction> mCutActionList = mCutActionRepository.findAll();
        assertThat(mCutActionList).hasSize(databaseSizeBeforeUpdate);
        MCutAction testMCutAction = mCutActionList.get(mCutActionList.size() - 1);
        assertThat(testMCutAction.getActionCutId()).isEqualTo(UPDATED_ACTION_CUT_ID);
        assertThat(testMCutAction.getCutActionType()).isEqualTo(UPDATED_CUT_ACTION_TYPE);
        assertThat(testMCutAction.getCutSequenceText()).isEqualTo(UPDATED_CUT_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void updateNonExistingMCutAction() throws Exception {
        int databaseSizeBeforeUpdate = mCutActionRepository.findAll().size();

        // Create the MCutAction
        MCutActionDTO mCutActionDTO = mCutActionMapper.toDto(mCutAction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCutActionMockMvc.perform(put("/api/m-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCutAction in the database
        List<MCutAction> mCutActionList = mCutActionRepository.findAll();
        assertThat(mCutActionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCutAction() throws Exception {
        // Initialize the database
        mCutActionRepository.saveAndFlush(mCutAction);

        int databaseSizeBeforeDelete = mCutActionRepository.findAll().size();

        // Delete the mCutAction
        restMCutActionMockMvc.perform(delete("/api/m-cut-actions/{id}", mCutAction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCutAction> mCutActionList = mCutActionRepository.findAll();
        assertThat(mCutActionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCutAction.class);
        MCutAction mCutAction1 = new MCutAction();
        mCutAction1.setId(1L);
        MCutAction mCutAction2 = new MCutAction();
        mCutAction2.setId(mCutAction1.getId());
        assertThat(mCutAction1).isEqualTo(mCutAction2);
        mCutAction2.setId(2L);
        assertThat(mCutAction1).isNotEqualTo(mCutAction2);
        mCutAction1.setId(null);
        assertThat(mCutAction1).isNotEqualTo(mCutAction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCutActionDTO.class);
        MCutActionDTO mCutActionDTO1 = new MCutActionDTO();
        mCutActionDTO1.setId(1L);
        MCutActionDTO mCutActionDTO2 = new MCutActionDTO();
        assertThat(mCutActionDTO1).isNotEqualTo(mCutActionDTO2);
        mCutActionDTO2.setId(mCutActionDTO1.getId());
        assertThat(mCutActionDTO1).isEqualTo(mCutActionDTO2);
        mCutActionDTO2.setId(2L);
        assertThat(mCutActionDTO1).isNotEqualTo(mCutActionDTO2);
        mCutActionDTO1.setId(null);
        assertThat(mCutActionDTO1).isNotEqualTo(mCutActionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCutActionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCutActionMapper.fromId(null)).isNull();
    }
}
