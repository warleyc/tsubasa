package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTeam;
import io.shm.tsubasa.domain.MTargetTeamGroup;
import io.shm.tsubasa.repository.MTeamRepository;
import io.shm.tsubasa.service.MTeamService;
import io.shm.tsubasa.service.dto.MTeamDTO;
import io.shm.tsubasa.service.mapper.MTeamMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTeamCriteria;
import io.shm.tsubasa.service.MTeamQueryService;

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
 * Integration tests for the {@Link MTeamResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTeamResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MTeamRepository mTeamRepository;

    @Autowired
    private MTeamMapper mTeamMapper;

    @Autowired
    private MTeamService mTeamService;

    @Autowired
    private MTeamQueryService mTeamQueryService;

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

    private MockMvc restMTeamMockMvc;

    private MTeam mTeam;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTeamResource mTeamResource = new MTeamResource(mTeamService, mTeamQueryService);
        this.restMTeamMockMvc = MockMvcBuilders.standaloneSetup(mTeamResource)
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
    public static MTeam createEntity(EntityManager em) {
        MTeam mTeam = new MTeam()
            .name(DEFAULT_NAME);
        return mTeam;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTeam createUpdatedEntity(EntityManager em) {
        MTeam mTeam = new MTeam()
            .name(UPDATED_NAME);
        return mTeam;
    }

    @BeforeEach
    public void initTest() {
        mTeam = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTeam() throws Exception {
        int databaseSizeBeforeCreate = mTeamRepository.findAll().size();

        // Create the MTeam
        MTeamDTO mTeamDTO = mTeamMapper.toDto(mTeam);
        restMTeamMockMvc.perform(post("/api/m-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamDTO)))
            .andExpect(status().isCreated());

        // Validate the MTeam in the database
        List<MTeam> mTeamList = mTeamRepository.findAll();
        assertThat(mTeamList).hasSize(databaseSizeBeforeCreate + 1);
        MTeam testMTeam = mTeamList.get(mTeamList.size() - 1);
        assertThat(testMTeam.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMTeamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTeamRepository.findAll().size();

        // Create the MTeam with an existing ID
        mTeam.setId(1L);
        MTeamDTO mTeamDTO = mTeamMapper.toDto(mTeam);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTeamMockMvc.perform(post("/api/m-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTeam in the database
        List<MTeam> mTeamList = mTeamRepository.findAll();
        assertThat(mTeamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMTeams() throws Exception {
        // Initialize the database
        mTeamRepository.saveAndFlush(mTeam);

        // Get all the mTeamList
        restMTeamMockMvc.perform(get("/api/m-teams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTeam.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMTeam() throws Exception {
        // Initialize the database
        mTeamRepository.saveAndFlush(mTeam);

        // Get the mTeam
        restMTeamMockMvc.perform(get("/api/m-teams/{id}", mTeam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTeam.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllMTeamsByMTargetTeamGroupIsEqualToSomething() throws Exception {
        // Initialize the database
        MTargetTeamGroup mTargetTeamGroup = MTargetTeamGroupResourceIT.createEntity(em);
        em.persist(mTargetTeamGroup);
        em.flush();
        mTeam.addMTargetTeamGroup(mTargetTeamGroup);
        mTeamRepository.saveAndFlush(mTeam);
        Long mTargetTeamGroupId = mTargetTeamGroup.getId();

        // Get all the mTeamList where mTargetTeamGroup equals to mTargetTeamGroupId
        defaultMTeamShouldBeFound("mTargetTeamGroupId.equals=" + mTargetTeamGroupId);

        // Get all the mTeamList where mTargetTeamGroup equals to mTargetTeamGroupId + 1
        defaultMTeamShouldNotBeFound("mTargetTeamGroupId.equals=" + (mTargetTeamGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTeamShouldBeFound(String filter) throws Exception {
        restMTeamMockMvc.perform(get("/api/m-teams?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTeam.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));

        // Check, that the count call also returns 1
        restMTeamMockMvc.perform(get("/api/m-teams/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTeamShouldNotBeFound(String filter) throws Exception {
        restMTeamMockMvc.perform(get("/api/m-teams?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTeamMockMvc.perform(get("/api/m-teams/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTeam() throws Exception {
        // Get the mTeam
        restMTeamMockMvc.perform(get("/api/m-teams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTeam() throws Exception {
        // Initialize the database
        mTeamRepository.saveAndFlush(mTeam);

        int databaseSizeBeforeUpdate = mTeamRepository.findAll().size();

        // Update the mTeam
        MTeam updatedMTeam = mTeamRepository.findById(mTeam.getId()).get();
        // Disconnect from session so that the updates on updatedMTeam are not directly saved in db
        em.detach(updatedMTeam);
        updatedMTeam
            .name(UPDATED_NAME);
        MTeamDTO mTeamDTO = mTeamMapper.toDto(updatedMTeam);

        restMTeamMockMvc.perform(put("/api/m-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamDTO)))
            .andExpect(status().isOk());

        // Validate the MTeam in the database
        List<MTeam> mTeamList = mTeamRepository.findAll();
        assertThat(mTeamList).hasSize(databaseSizeBeforeUpdate);
        MTeam testMTeam = mTeamList.get(mTeamList.size() - 1);
        assertThat(testMTeam.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMTeam() throws Exception {
        int databaseSizeBeforeUpdate = mTeamRepository.findAll().size();

        // Create the MTeam
        MTeamDTO mTeamDTO = mTeamMapper.toDto(mTeam);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTeamMockMvc.perform(put("/api/m-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTeamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTeam in the database
        List<MTeam> mTeamList = mTeamRepository.findAll();
        assertThat(mTeamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTeam() throws Exception {
        // Initialize the database
        mTeamRepository.saveAndFlush(mTeam);

        int databaseSizeBeforeDelete = mTeamRepository.findAll().size();

        // Delete the mTeam
        restMTeamMockMvc.perform(delete("/api/m-teams/{id}", mTeam.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTeam> mTeamList = mTeamRepository.findAll();
        assertThat(mTeamList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTeam.class);
        MTeam mTeam1 = new MTeam();
        mTeam1.setId(1L);
        MTeam mTeam2 = new MTeam();
        mTeam2.setId(mTeam1.getId());
        assertThat(mTeam1).isEqualTo(mTeam2);
        mTeam2.setId(2L);
        assertThat(mTeam1).isNotEqualTo(mTeam2);
        mTeam1.setId(null);
        assertThat(mTeam1).isNotEqualTo(mTeam2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTeamDTO.class);
        MTeamDTO mTeamDTO1 = new MTeamDTO();
        mTeamDTO1.setId(1L);
        MTeamDTO mTeamDTO2 = new MTeamDTO();
        assertThat(mTeamDTO1).isNotEqualTo(mTeamDTO2);
        mTeamDTO2.setId(mTeamDTO1.getId());
        assertThat(mTeamDTO1).isEqualTo(mTeamDTO2);
        mTeamDTO2.setId(2L);
        assertThat(mTeamDTO1).isNotEqualTo(mTeamDTO2);
        mTeamDTO1.setId(null);
        assertThat(mTeamDTO1).isNotEqualTo(mTeamDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTeamMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTeamMapper.fromId(null)).isNull();
    }
}
