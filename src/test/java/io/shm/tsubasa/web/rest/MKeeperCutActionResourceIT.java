package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MKeeperCutAction;
import io.shm.tsubasa.repository.MKeeperCutActionRepository;
import io.shm.tsubasa.service.MKeeperCutActionService;
import io.shm.tsubasa.service.dto.MKeeperCutActionDTO;
import io.shm.tsubasa.service.mapper.MKeeperCutActionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MKeeperCutActionCriteria;
import io.shm.tsubasa.service.MKeeperCutActionQueryService;

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
 * Integration tests for the {@Link MKeeperCutActionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MKeeperCutActionResourceIT {

    private static final Integer DEFAULT_ACTION_CUT_ID = 1;
    private static final Integer UPDATED_ACTION_CUT_ID = 2;

    private static final Integer DEFAULT_KEEPER_CUT_ACTION_TYPE = 1;
    private static final Integer UPDATED_KEEPER_CUT_ACTION_TYPE = 2;

    private static final String DEFAULT_CUT_SEQUENCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CUT_SEQUENCE_TEXT = "BBBBBBBBBB";

    @Autowired
    private MKeeperCutActionRepository mKeeperCutActionRepository;

    @Autowired
    private MKeeperCutActionMapper mKeeperCutActionMapper;

    @Autowired
    private MKeeperCutActionService mKeeperCutActionService;

    @Autowired
    private MKeeperCutActionQueryService mKeeperCutActionQueryService;

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

    private MockMvc restMKeeperCutActionMockMvc;

    private MKeeperCutAction mKeeperCutAction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MKeeperCutActionResource mKeeperCutActionResource = new MKeeperCutActionResource(mKeeperCutActionService, mKeeperCutActionQueryService);
        this.restMKeeperCutActionMockMvc = MockMvcBuilders.standaloneSetup(mKeeperCutActionResource)
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
    public static MKeeperCutAction createEntity(EntityManager em) {
        MKeeperCutAction mKeeperCutAction = new MKeeperCutAction()
            .actionCutId(DEFAULT_ACTION_CUT_ID)
            .keeperCutActionType(DEFAULT_KEEPER_CUT_ACTION_TYPE)
            .cutSequenceText(DEFAULT_CUT_SEQUENCE_TEXT);
        return mKeeperCutAction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MKeeperCutAction createUpdatedEntity(EntityManager em) {
        MKeeperCutAction mKeeperCutAction = new MKeeperCutAction()
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .keeperCutActionType(UPDATED_KEEPER_CUT_ACTION_TYPE)
            .cutSequenceText(UPDATED_CUT_SEQUENCE_TEXT);
        return mKeeperCutAction;
    }

    @BeforeEach
    public void initTest() {
        mKeeperCutAction = createEntity(em);
    }

    @Test
    @Transactional
    public void createMKeeperCutAction() throws Exception {
        int databaseSizeBeforeCreate = mKeeperCutActionRepository.findAll().size();

        // Create the MKeeperCutAction
        MKeeperCutActionDTO mKeeperCutActionDTO = mKeeperCutActionMapper.toDto(mKeeperCutAction);
        restMKeeperCutActionMockMvc.perform(post("/api/m-keeper-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mKeeperCutActionDTO)))
            .andExpect(status().isCreated());

        // Validate the MKeeperCutAction in the database
        List<MKeeperCutAction> mKeeperCutActionList = mKeeperCutActionRepository.findAll();
        assertThat(mKeeperCutActionList).hasSize(databaseSizeBeforeCreate + 1);
        MKeeperCutAction testMKeeperCutAction = mKeeperCutActionList.get(mKeeperCutActionList.size() - 1);
        assertThat(testMKeeperCutAction.getActionCutId()).isEqualTo(DEFAULT_ACTION_CUT_ID);
        assertThat(testMKeeperCutAction.getKeeperCutActionType()).isEqualTo(DEFAULT_KEEPER_CUT_ACTION_TYPE);
        assertThat(testMKeeperCutAction.getCutSequenceText()).isEqualTo(DEFAULT_CUT_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void createMKeeperCutActionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mKeeperCutActionRepository.findAll().size();

        // Create the MKeeperCutAction with an existing ID
        mKeeperCutAction.setId(1L);
        MKeeperCutActionDTO mKeeperCutActionDTO = mKeeperCutActionMapper.toDto(mKeeperCutAction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMKeeperCutActionMockMvc.perform(post("/api/m-keeper-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mKeeperCutActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MKeeperCutAction in the database
        List<MKeeperCutAction> mKeeperCutActionList = mKeeperCutActionRepository.findAll();
        assertThat(mKeeperCutActionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActionCutIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mKeeperCutActionRepository.findAll().size();
        // set the field null
        mKeeperCutAction.setActionCutId(null);

        // Create the MKeeperCutAction, which fails.
        MKeeperCutActionDTO mKeeperCutActionDTO = mKeeperCutActionMapper.toDto(mKeeperCutAction);

        restMKeeperCutActionMockMvc.perform(post("/api/m-keeper-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mKeeperCutActionDTO)))
            .andExpect(status().isBadRequest());

        List<MKeeperCutAction> mKeeperCutActionList = mKeeperCutActionRepository.findAll();
        assertThat(mKeeperCutActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKeeperCutActionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mKeeperCutActionRepository.findAll().size();
        // set the field null
        mKeeperCutAction.setKeeperCutActionType(null);

        // Create the MKeeperCutAction, which fails.
        MKeeperCutActionDTO mKeeperCutActionDTO = mKeeperCutActionMapper.toDto(mKeeperCutAction);

        restMKeeperCutActionMockMvc.perform(post("/api/m-keeper-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mKeeperCutActionDTO)))
            .andExpect(status().isBadRequest());

        List<MKeeperCutAction> mKeeperCutActionList = mKeeperCutActionRepository.findAll();
        assertThat(mKeeperCutActionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActions() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList
        restMKeeperCutActionMockMvc.perform(get("/api/m-keeper-cut-actions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mKeeperCutAction.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].keeperCutActionType").value(hasItem(DEFAULT_KEEPER_CUT_ACTION_TYPE)))
            .andExpect(jsonPath("$.[*].cutSequenceText").value(hasItem(DEFAULT_CUT_SEQUENCE_TEXT.toString())));
    }
    
    @Test
    @Transactional
    public void getMKeeperCutAction() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get the mKeeperCutAction
        restMKeeperCutActionMockMvc.perform(get("/api/m-keeper-cut-actions/{id}", mKeeperCutAction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mKeeperCutAction.getId().intValue()))
            .andExpect(jsonPath("$.actionCutId").value(DEFAULT_ACTION_CUT_ID))
            .andExpect(jsonPath("$.keeperCutActionType").value(DEFAULT_KEEPER_CUT_ACTION_TYPE))
            .andExpect(jsonPath("$.cutSequenceText").value(DEFAULT_CUT_SEQUENCE_TEXT.toString()));
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActionsByActionCutIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where actionCutId equals to DEFAULT_ACTION_CUT_ID
        defaultMKeeperCutActionShouldBeFound("actionCutId.equals=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mKeeperCutActionList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMKeeperCutActionShouldNotBeFound("actionCutId.equals=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActionsByActionCutIdIsInShouldWork() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where actionCutId in DEFAULT_ACTION_CUT_ID or UPDATED_ACTION_CUT_ID
        defaultMKeeperCutActionShouldBeFound("actionCutId.in=" + DEFAULT_ACTION_CUT_ID + "," + UPDATED_ACTION_CUT_ID);

        // Get all the mKeeperCutActionList where actionCutId equals to UPDATED_ACTION_CUT_ID
        defaultMKeeperCutActionShouldNotBeFound("actionCutId.in=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActionsByActionCutIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where actionCutId is not null
        defaultMKeeperCutActionShouldBeFound("actionCutId.specified=true");

        // Get all the mKeeperCutActionList where actionCutId is null
        defaultMKeeperCutActionShouldNotBeFound("actionCutId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActionsByActionCutIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where actionCutId greater than or equals to DEFAULT_ACTION_CUT_ID
        defaultMKeeperCutActionShouldBeFound("actionCutId.greaterOrEqualThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mKeeperCutActionList where actionCutId greater than or equals to UPDATED_ACTION_CUT_ID
        defaultMKeeperCutActionShouldNotBeFound("actionCutId.greaterOrEqualThan=" + UPDATED_ACTION_CUT_ID);
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActionsByActionCutIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where actionCutId less than or equals to DEFAULT_ACTION_CUT_ID
        defaultMKeeperCutActionShouldNotBeFound("actionCutId.lessThan=" + DEFAULT_ACTION_CUT_ID);

        // Get all the mKeeperCutActionList where actionCutId less than or equals to UPDATED_ACTION_CUT_ID
        defaultMKeeperCutActionShouldBeFound("actionCutId.lessThan=" + UPDATED_ACTION_CUT_ID);
    }


    @Test
    @Transactional
    public void getAllMKeeperCutActionsByKeeperCutActionTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where keeperCutActionType equals to DEFAULT_KEEPER_CUT_ACTION_TYPE
        defaultMKeeperCutActionShouldBeFound("keeperCutActionType.equals=" + DEFAULT_KEEPER_CUT_ACTION_TYPE);

        // Get all the mKeeperCutActionList where keeperCutActionType equals to UPDATED_KEEPER_CUT_ACTION_TYPE
        defaultMKeeperCutActionShouldNotBeFound("keeperCutActionType.equals=" + UPDATED_KEEPER_CUT_ACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActionsByKeeperCutActionTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where keeperCutActionType in DEFAULT_KEEPER_CUT_ACTION_TYPE or UPDATED_KEEPER_CUT_ACTION_TYPE
        defaultMKeeperCutActionShouldBeFound("keeperCutActionType.in=" + DEFAULT_KEEPER_CUT_ACTION_TYPE + "," + UPDATED_KEEPER_CUT_ACTION_TYPE);

        // Get all the mKeeperCutActionList where keeperCutActionType equals to UPDATED_KEEPER_CUT_ACTION_TYPE
        defaultMKeeperCutActionShouldNotBeFound("keeperCutActionType.in=" + UPDATED_KEEPER_CUT_ACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActionsByKeeperCutActionTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where keeperCutActionType is not null
        defaultMKeeperCutActionShouldBeFound("keeperCutActionType.specified=true");

        // Get all the mKeeperCutActionList where keeperCutActionType is null
        defaultMKeeperCutActionShouldNotBeFound("keeperCutActionType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActionsByKeeperCutActionTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where keeperCutActionType greater than or equals to DEFAULT_KEEPER_CUT_ACTION_TYPE
        defaultMKeeperCutActionShouldBeFound("keeperCutActionType.greaterOrEqualThan=" + DEFAULT_KEEPER_CUT_ACTION_TYPE);

        // Get all the mKeeperCutActionList where keeperCutActionType greater than or equals to UPDATED_KEEPER_CUT_ACTION_TYPE
        defaultMKeeperCutActionShouldNotBeFound("keeperCutActionType.greaterOrEqualThan=" + UPDATED_KEEPER_CUT_ACTION_TYPE);
    }

    @Test
    @Transactional
    public void getAllMKeeperCutActionsByKeeperCutActionTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        // Get all the mKeeperCutActionList where keeperCutActionType less than or equals to DEFAULT_KEEPER_CUT_ACTION_TYPE
        defaultMKeeperCutActionShouldNotBeFound("keeperCutActionType.lessThan=" + DEFAULT_KEEPER_CUT_ACTION_TYPE);

        // Get all the mKeeperCutActionList where keeperCutActionType less than or equals to UPDATED_KEEPER_CUT_ACTION_TYPE
        defaultMKeeperCutActionShouldBeFound("keeperCutActionType.lessThan=" + UPDATED_KEEPER_CUT_ACTION_TYPE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMKeeperCutActionShouldBeFound(String filter) throws Exception {
        restMKeeperCutActionMockMvc.perform(get("/api/m-keeper-cut-actions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mKeeperCutAction.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionCutId").value(hasItem(DEFAULT_ACTION_CUT_ID)))
            .andExpect(jsonPath("$.[*].keeperCutActionType").value(hasItem(DEFAULT_KEEPER_CUT_ACTION_TYPE)))
            .andExpect(jsonPath("$.[*].cutSequenceText").value(hasItem(DEFAULT_CUT_SEQUENCE_TEXT.toString())));

        // Check, that the count call also returns 1
        restMKeeperCutActionMockMvc.perform(get("/api/m-keeper-cut-actions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMKeeperCutActionShouldNotBeFound(String filter) throws Exception {
        restMKeeperCutActionMockMvc.perform(get("/api/m-keeper-cut-actions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMKeeperCutActionMockMvc.perform(get("/api/m-keeper-cut-actions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMKeeperCutAction() throws Exception {
        // Get the mKeeperCutAction
        restMKeeperCutActionMockMvc.perform(get("/api/m-keeper-cut-actions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMKeeperCutAction() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        int databaseSizeBeforeUpdate = mKeeperCutActionRepository.findAll().size();

        // Update the mKeeperCutAction
        MKeeperCutAction updatedMKeeperCutAction = mKeeperCutActionRepository.findById(mKeeperCutAction.getId()).get();
        // Disconnect from session so that the updates on updatedMKeeperCutAction are not directly saved in db
        em.detach(updatedMKeeperCutAction);
        updatedMKeeperCutAction
            .actionCutId(UPDATED_ACTION_CUT_ID)
            .keeperCutActionType(UPDATED_KEEPER_CUT_ACTION_TYPE)
            .cutSequenceText(UPDATED_CUT_SEQUENCE_TEXT);
        MKeeperCutActionDTO mKeeperCutActionDTO = mKeeperCutActionMapper.toDto(updatedMKeeperCutAction);

        restMKeeperCutActionMockMvc.perform(put("/api/m-keeper-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mKeeperCutActionDTO)))
            .andExpect(status().isOk());

        // Validate the MKeeperCutAction in the database
        List<MKeeperCutAction> mKeeperCutActionList = mKeeperCutActionRepository.findAll();
        assertThat(mKeeperCutActionList).hasSize(databaseSizeBeforeUpdate);
        MKeeperCutAction testMKeeperCutAction = mKeeperCutActionList.get(mKeeperCutActionList.size() - 1);
        assertThat(testMKeeperCutAction.getActionCutId()).isEqualTo(UPDATED_ACTION_CUT_ID);
        assertThat(testMKeeperCutAction.getKeeperCutActionType()).isEqualTo(UPDATED_KEEPER_CUT_ACTION_TYPE);
        assertThat(testMKeeperCutAction.getCutSequenceText()).isEqualTo(UPDATED_CUT_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void updateNonExistingMKeeperCutAction() throws Exception {
        int databaseSizeBeforeUpdate = mKeeperCutActionRepository.findAll().size();

        // Create the MKeeperCutAction
        MKeeperCutActionDTO mKeeperCutActionDTO = mKeeperCutActionMapper.toDto(mKeeperCutAction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMKeeperCutActionMockMvc.perform(put("/api/m-keeper-cut-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mKeeperCutActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MKeeperCutAction in the database
        List<MKeeperCutAction> mKeeperCutActionList = mKeeperCutActionRepository.findAll();
        assertThat(mKeeperCutActionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMKeeperCutAction() throws Exception {
        // Initialize the database
        mKeeperCutActionRepository.saveAndFlush(mKeeperCutAction);

        int databaseSizeBeforeDelete = mKeeperCutActionRepository.findAll().size();

        // Delete the mKeeperCutAction
        restMKeeperCutActionMockMvc.perform(delete("/api/m-keeper-cut-actions/{id}", mKeeperCutAction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MKeeperCutAction> mKeeperCutActionList = mKeeperCutActionRepository.findAll();
        assertThat(mKeeperCutActionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MKeeperCutAction.class);
        MKeeperCutAction mKeeperCutAction1 = new MKeeperCutAction();
        mKeeperCutAction1.setId(1L);
        MKeeperCutAction mKeeperCutAction2 = new MKeeperCutAction();
        mKeeperCutAction2.setId(mKeeperCutAction1.getId());
        assertThat(mKeeperCutAction1).isEqualTo(mKeeperCutAction2);
        mKeeperCutAction2.setId(2L);
        assertThat(mKeeperCutAction1).isNotEqualTo(mKeeperCutAction2);
        mKeeperCutAction1.setId(null);
        assertThat(mKeeperCutAction1).isNotEqualTo(mKeeperCutAction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MKeeperCutActionDTO.class);
        MKeeperCutActionDTO mKeeperCutActionDTO1 = new MKeeperCutActionDTO();
        mKeeperCutActionDTO1.setId(1L);
        MKeeperCutActionDTO mKeeperCutActionDTO2 = new MKeeperCutActionDTO();
        assertThat(mKeeperCutActionDTO1).isNotEqualTo(mKeeperCutActionDTO2);
        mKeeperCutActionDTO2.setId(mKeeperCutActionDTO1.getId());
        assertThat(mKeeperCutActionDTO1).isEqualTo(mKeeperCutActionDTO2);
        mKeeperCutActionDTO2.setId(2L);
        assertThat(mKeeperCutActionDTO1).isNotEqualTo(mKeeperCutActionDTO2);
        mKeeperCutActionDTO1.setId(null);
        assertThat(mKeeperCutActionDTO1).isNotEqualTo(mKeeperCutActionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mKeeperCutActionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mKeeperCutActionMapper.fromId(null)).isNull();
    }
}
