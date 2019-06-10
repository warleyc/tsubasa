package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCutSeqGroup;
import io.shm.tsubasa.repository.MCutSeqGroupRepository;
import io.shm.tsubasa.service.MCutSeqGroupService;
import io.shm.tsubasa.service.dto.MCutSeqGroupDTO;
import io.shm.tsubasa.service.mapper.MCutSeqGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCutSeqGroupCriteria;
import io.shm.tsubasa.service.MCutSeqGroupQueryService;

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
 * Integration tests for the {@Link MCutSeqGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCutSeqGroupResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM = "AAAAAAAAAA";
    private static final String UPDATED_PARAM = "BBBBBBBBBB";

    private static final String DEFAULT_CUT_SEQUENCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CUT_SEQUENCE_TEXT = "BBBBBBBBBB";

    @Autowired
    private MCutSeqGroupRepository mCutSeqGroupRepository;

    @Autowired
    private MCutSeqGroupMapper mCutSeqGroupMapper;

    @Autowired
    private MCutSeqGroupService mCutSeqGroupService;

    @Autowired
    private MCutSeqGroupQueryService mCutSeqGroupQueryService;

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

    private MockMvc restMCutSeqGroupMockMvc;

    private MCutSeqGroup mCutSeqGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCutSeqGroupResource mCutSeqGroupResource = new MCutSeqGroupResource(mCutSeqGroupService, mCutSeqGroupQueryService);
        this.restMCutSeqGroupMockMvc = MockMvcBuilders.standaloneSetup(mCutSeqGroupResource)
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
    public static MCutSeqGroup createEntity(EntityManager em) {
        MCutSeqGroup mCutSeqGroup = new MCutSeqGroup()
            .key(DEFAULT_KEY)
            .param(DEFAULT_PARAM)
            .cutSequenceText(DEFAULT_CUT_SEQUENCE_TEXT);
        return mCutSeqGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCutSeqGroup createUpdatedEntity(EntityManager em) {
        MCutSeqGroup mCutSeqGroup = new MCutSeqGroup()
            .key(UPDATED_KEY)
            .param(UPDATED_PARAM)
            .cutSequenceText(UPDATED_CUT_SEQUENCE_TEXT);
        return mCutSeqGroup;
    }

    @BeforeEach
    public void initTest() {
        mCutSeqGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCutSeqGroup() throws Exception {
        int databaseSizeBeforeCreate = mCutSeqGroupRepository.findAll().size();

        // Create the MCutSeqGroup
        MCutSeqGroupDTO mCutSeqGroupDTO = mCutSeqGroupMapper.toDto(mCutSeqGroup);
        restMCutSeqGroupMockMvc.perform(post("/api/m-cut-seq-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutSeqGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MCutSeqGroup in the database
        List<MCutSeqGroup> mCutSeqGroupList = mCutSeqGroupRepository.findAll();
        assertThat(mCutSeqGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MCutSeqGroup testMCutSeqGroup = mCutSeqGroupList.get(mCutSeqGroupList.size() - 1);
        assertThat(testMCutSeqGroup.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testMCutSeqGroup.getParam()).isEqualTo(DEFAULT_PARAM);
        assertThat(testMCutSeqGroup.getCutSequenceText()).isEqualTo(DEFAULT_CUT_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void createMCutSeqGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCutSeqGroupRepository.findAll().size();

        // Create the MCutSeqGroup with an existing ID
        mCutSeqGroup.setId(1L);
        MCutSeqGroupDTO mCutSeqGroupDTO = mCutSeqGroupMapper.toDto(mCutSeqGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCutSeqGroupMockMvc.perform(post("/api/m-cut-seq-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutSeqGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCutSeqGroup in the database
        List<MCutSeqGroup> mCutSeqGroupList = mCutSeqGroupRepository.findAll();
        assertThat(mCutSeqGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMCutSeqGroups() throws Exception {
        // Initialize the database
        mCutSeqGroupRepository.saveAndFlush(mCutSeqGroup);

        // Get all the mCutSeqGroupList
        restMCutSeqGroupMockMvc.perform(get("/api/m-cut-seq-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCutSeqGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].param").value(hasItem(DEFAULT_PARAM.toString())))
            .andExpect(jsonPath("$.[*].cutSequenceText").value(hasItem(DEFAULT_CUT_SEQUENCE_TEXT.toString())));
    }
    
    @Test
    @Transactional
    public void getMCutSeqGroup() throws Exception {
        // Initialize the database
        mCutSeqGroupRepository.saveAndFlush(mCutSeqGroup);

        // Get the mCutSeqGroup
        restMCutSeqGroupMockMvc.perform(get("/api/m-cut-seq-groups/{id}", mCutSeqGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCutSeqGroup.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.param").value(DEFAULT_PARAM.toString()))
            .andExpect(jsonPath("$.cutSequenceText").value(DEFAULT_CUT_SEQUENCE_TEXT.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCutSeqGroupShouldBeFound(String filter) throws Exception {
        restMCutSeqGroupMockMvc.perform(get("/api/m-cut-seq-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCutSeqGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].param").value(hasItem(DEFAULT_PARAM.toString())))
            .andExpect(jsonPath("$.[*].cutSequenceText").value(hasItem(DEFAULT_CUT_SEQUENCE_TEXT.toString())));

        // Check, that the count call also returns 1
        restMCutSeqGroupMockMvc.perform(get("/api/m-cut-seq-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCutSeqGroupShouldNotBeFound(String filter) throws Exception {
        restMCutSeqGroupMockMvc.perform(get("/api/m-cut-seq-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCutSeqGroupMockMvc.perform(get("/api/m-cut-seq-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCutSeqGroup() throws Exception {
        // Get the mCutSeqGroup
        restMCutSeqGroupMockMvc.perform(get("/api/m-cut-seq-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCutSeqGroup() throws Exception {
        // Initialize the database
        mCutSeqGroupRepository.saveAndFlush(mCutSeqGroup);

        int databaseSizeBeforeUpdate = mCutSeqGroupRepository.findAll().size();

        // Update the mCutSeqGroup
        MCutSeqGroup updatedMCutSeqGroup = mCutSeqGroupRepository.findById(mCutSeqGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMCutSeqGroup are not directly saved in db
        em.detach(updatedMCutSeqGroup);
        updatedMCutSeqGroup
            .key(UPDATED_KEY)
            .param(UPDATED_PARAM)
            .cutSequenceText(UPDATED_CUT_SEQUENCE_TEXT);
        MCutSeqGroupDTO mCutSeqGroupDTO = mCutSeqGroupMapper.toDto(updatedMCutSeqGroup);

        restMCutSeqGroupMockMvc.perform(put("/api/m-cut-seq-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutSeqGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MCutSeqGroup in the database
        List<MCutSeqGroup> mCutSeqGroupList = mCutSeqGroupRepository.findAll();
        assertThat(mCutSeqGroupList).hasSize(databaseSizeBeforeUpdate);
        MCutSeqGroup testMCutSeqGroup = mCutSeqGroupList.get(mCutSeqGroupList.size() - 1);
        assertThat(testMCutSeqGroup.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testMCutSeqGroup.getParam()).isEqualTo(UPDATED_PARAM);
        assertThat(testMCutSeqGroup.getCutSequenceText()).isEqualTo(UPDATED_CUT_SEQUENCE_TEXT);
    }

    @Test
    @Transactional
    public void updateNonExistingMCutSeqGroup() throws Exception {
        int databaseSizeBeforeUpdate = mCutSeqGroupRepository.findAll().size();

        // Create the MCutSeqGroup
        MCutSeqGroupDTO mCutSeqGroupDTO = mCutSeqGroupMapper.toDto(mCutSeqGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCutSeqGroupMockMvc.perform(put("/api/m-cut-seq-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCutSeqGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCutSeqGroup in the database
        List<MCutSeqGroup> mCutSeqGroupList = mCutSeqGroupRepository.findAll();
        assertThat(mCutSeqGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCutSeqGroup() throws Exception {
        // Initialize the database
        mCutSeqGroupRepository.saveAndFlush(mCutSeqGroup);

        int databaseSizeBeforeDelete = mCutSeqGroupRepository.findAll().size();

        // Delete the mCutSeqGroup
        restMCutSeqGroupMockMvc.perform(delete("/api/m-cut-seq-groups/{id}", mCutSeqGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCutSeqGroup> mCutSeqGroupList = mCutSeqGroupRepository.findAll();
        assertThat(mCutSeqGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCutSeqGroup.class);
        MCutSeqGroup mCutSeqGroup1 = new MCutSeqGroup();
        mCutSeqGroup1.setId(1L);
        MCutSeqGroup mCutSeqGroup2 = new MCutSeqGroup();
        mCutSeqGroup2.setId(mCutSeqGroup1.getId());
        assertThat(mCutSeqGroup1).isEqualTo(mCutSeqGroup2);
        mCutSeqGroup2.setId(2L);
        assertThat(mCutSeqGroup1).isNotEqualTo(mCutSeqGroup2);
        mCutSeqGroup1.setId(null);
        assertThat(mCutSeqGroup1).isNotEqualTo(mCutSeqGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCutSeqGroupDTO.class);
        MCutSeqGroupDTO mCutSeqGroupDTO1 = new MCutSeqGroupDTO();
        mCutSeqGroupDTO1.setId(1L);
        MCutSeqGroupDTO mCutSeqGroupDTO2 = new MCutSeqGroupDTO();
        assertThat(mCutSeqGroupDTO1).isNotEqualTo(mCutSeqGroupDTO2);
        mCutSeqGroupDTO2.setId(mCutSeqGroupDTO1.getId());
        assertThat(mCutSeqGroupDTO1).isEqualTo(mCutSeqGroupDTO2);
        mCutSeqGroupDTO2.setId(2L);
        assertThat(mCutSeqGroupDTO1).isNotEqualTo(mCutSeqGroupDTO2);
        mCutSeqGroupDTO1.setId(null);
        assertThat(mCutSeqGroupDTO1).isNotEqualTo(mCutSeqGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCutSeqGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCutSeqGroupMapper.fromId(null)).isNull();
    }
}
