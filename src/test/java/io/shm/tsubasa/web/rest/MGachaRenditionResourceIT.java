package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MGachaRendition;
import io.shm.tsubasa.repository.MGachaRenditionRepository;
import io.shm.tsubasa.service.MGachaRenditionService;
import io.shm.tsubasa.service.dto.MGachaRenditionDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MGachaRenditionCriteria;
import io.shm.tsubasa.service.MGachaRenditionQueryService;

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
 * Integration tests for the {@Link MGachaRenditionResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MGachaRenditionResourceIT {

    private static final String DEFAULT_MAIN_PREFAB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_PREFAB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESULT_EXPECTED_UP_PREFAB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RESULT_EXPECTED_UP_PREFAB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESULT_QUESTION_PREFAB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RESULT_QUESTION_PREFAB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOUND_SWITCH_EVENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOUND_SWITCH_EVENT_NAME = "BBBBBBBBBB";

    @Autowired
    private MGachaRenditionRepository mGachaRenditionRepository;

    @Autowired
    private MGachaRenditionMapper mGachaRenditionMapper;

    @Autowired
    private MGachaRenditionService mGachaRenditionService;

    @Autowired
    private MGachaRenditionQueryService mGachaRenditionQueryService;

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

    private MockMvc restMGachaRenditionMockMvc;

    private MGachaRendition mGachaRendition;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MGachaRenditionResource mGachaRenditionResource = new MGachaRenditionResource(mGachaRenditionService, mGachaRenditionQueryService);
        this.restMGachaRenditionMockMvc = MockMvcBuilders.standaloneSetup(mGachaRenditionResource)
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
    public static MGachaRendition createEntity(EntityManager em) {
        MGachaRendition mGachaRendition = new MGachaRendition()
            .mainPrefabName(DEFAULT_MAIN_PREFAB_NAME)
            .resultExpectedUpPrefabName(DEFAULT_RESULT_EXPECTED_UP_PREFAB_NAME)
            .resultQuestionPrefabName(DEFAULT_RESULT_QUESTION_PREFAB_NAME)
            .soundSwitchEventName(DEFAULT_SOUND_SWITCH_EVENT_NAME);
        return mGachaRendition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MGachaRendition createUpdatedEntity(EntityManager em) {
        MGachaRendition mGachaRendition = new MGachaRendition()
            .mainPrefabName(UPDATED_MAIN_PREFAB_NAME)
            .resultExpectedUpPrefabName(UPDATED_RESULT_EXPECTED_UP_PREFAB_NAME)
            .resultQuestionPrefabName(UPDATED_RESULT_QUESTION_PREFAB_NAME)
            .soundSwitchEventName(UPDATED_SOUND_SWITCH_EVENT_NAME);
        return mGachaRendition;
    }

    @BeforeEach
    public void initTest() {
        mGachaRendition = createEntity(em);
    }

    @Test
    @Transactional
    public void createMGachaRendition() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionRepository.findAll().size();

        // Create the MGachaRendition
        MGachaRenditionDTO mGachaRenditionDTO = mGachaRenditionMapper.toDto(mGachaRendition);
        restMGachaRenditionMockMvc.perform(post("/api/m-gacha-renditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionDTO)))
            .andExpect(status().isCreated());

        // Validate the MGachaRendition in the database
        List<MGachaRendition> mGachaRenditionList = mGachaRenditionRepository.findAll();
        assertThat(mGachaRenditionList).hasSize(databaseSizeBeforeCreate + 1);
        MGachaRendition testMGachaRendition = mGachaRenditionList.get(mGachaRenditionList.size() - 1);
        assertThat(testMGachaRendition.getMainPrefabName()).isEqualTo(DEFAULT_MAIN_PREFAB_NAME);
        assertThat(testMGachaRendition.getResultExpectedUpPrefabName()).isEqualTo(DEFAULT_RESULT_EXPECTED_UP_PREFAB_NAME);
        assertThat(testMGachaRendition.getResultQuestionPrefabName()).isEqualTo(DEFAULT_RESULT_QUESTION_PREFAB_NAME);
        assertThat(testMGachaRendition.getSoundSwitchEventName()).isEqualTo(DEFAULT_SOUND_SWITCH_EVENT_NAME);
    }

    @Test
    @Transactional
    public void createMGachaRenditionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mGachaRenditionRepository.findAll().size();

        // Create the MGachaRendition with an existing ID
        mGachaRendition.setId(1L);
        MGachaRenditionDTO mGachaRenditionDTO = mGachaRenditionMapper.toDto(mGachaRendition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMGachaRenditionMockMvc.perform(post("/api/m-gacha-renditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRendition in the database
        List<MGachaRendition> mGachaRenditionList = mGachaRenditionRepository.findAll();
        assertThat(mGachaRenditionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMGachaRenditions() throws Exception {
        // Initialize the database
        mGachaRenditionRepository.saveAndFlush(mGachaRendition);

        // Get all the mGachaRenditionList
        restMGachaRenditionMockMvc.perform(get("/api/m-gacha-renditions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRendition.getId().intValue())))
            .andExpect(jsonPath("$.[*].mainPrefabName").value(hasItem(DEFAULT_MAIN_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].resultExpectedUpPrefabName").value(hasItem(DEFAULT_RESULT_EXPECTED_UP_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].resultQuestionPrefabName").value(hasItem(DEFAULT_RESULT_QUESTION_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].soundSwitchEventName").value(hasItem(DEFAULT_SOUND_SWITCH_EVENT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMGachaRendition() throws Exception {
        // Initialize the database
        mGachaRenditionRepository.saveAndFlush(mGachaRendition);

        // Get the mGachaRendition
        restMGachaRenditionMockMvc.perform(get("/api/m-gacha-renditions/{id}", mGachaRendition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mGachaRendition.getId().intValue()))
            .andExpect(jsonPath("$.mainPrefabName").value(DEFAULT_MAIN_PREFAB_NAME.toString()))
            .andExpect(jsonPath("$.resultExpectedUpPrefabName").value(DEFAULT_RESULT_EXPECTED_UP_PREFAB_NAME.toString()))
            .andExpect(jsonPath("$.resultQuestionPrefabName").value(DEFAULT_RESULT_QUESTION_PREFAB_NAME.toString()))
            .andExpect(jsonPath("$.soundSwitchEventName").value(DEFAULT_SOUND_SWITCH_EVENT_NAME.toString()));
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMGachaRenditionShouldBeFound(String filter) throws Exception {
        restMGachaRenditionMockMvc.perform(get("/api/m-gacha-renditions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mGachaRendition.getId().intValue())))
            .andExpect(jsonPath("$.[*].mainPrefabName").value(hasItem(DEFAULT_MAIN_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].resultExpectedUpPrefabName").value(hasItem(DEFAULT_RESULT_EXPECTED_UP_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].resultQuestionPrefabName").value(hasItem(DEFAULT_RESULT_QUESTION_PREFAB_NAME.toString())))
            .andExpect(jsonPath("$.[*].soundSwitchEventName").value(hasItem(DEFAULT_SOUND_SWITCH_EVENT_NAME.toString())));

        // Check, that the count call also returns 1
        restMGachaRenditionMockMvc.perform(get("/api/m-gacha-renditions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMGachaRenditionShouldNotBeFound(String filter) throws Exception {
        restMGachaRenditionMockMvc.perform(get("/api/m-gacha-renditions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMGachaRenditionMockMvc.perform(get("/api/m-gacha-renditions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMGachaRendition() throws Exception {
        // Get the mGachaRendition
        restMGachaRenditionMockMvc.perform(get("/api/m-gacha-renditions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMGachaRendition() throws Exception {
        // Initialize the database
        mGachaRenditionRepository.saveAndFlush(mGachaRendition);

        int databaseSizeBeforeUpdate = mGachaRenditionRepository.findAll().size();

        // Update the mGachaRendition
        MGachaRendition updatedMGachaRendition = mGachaRenditionRepository.findById(mGachaRendition.getId()).get();
        // Disconnect from session so that the updates on updatedMGachaRendition are not directly saved in db
        em.detach(updatedMGachaRendition);
        updatedMGachaRendition
            .mainPrefabName(UPDATED_MAIN_PREFAB_NAME)
            .resultExpectedUpPrefabName(UPDATED_RESULT_EXPECTED_UP_PREFAB_NAME)
            .resultQuestionPrefabName(UPDATED_RESULT_QUESTION_PREFAB_NAME)
            .soundSwitchEventName(UPDATED_SOUND_SWITCH_EVENT_NAME);
        MGachaRenditionDTO mGachaRenditionDTO = mGachaRenditionMapper.toDto(updatedMGachaRendition);

        restMGachaRenditionMockMvc.perform(put("/api/m-gacha-renditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionDTO)))
            .andExpect(status().isOk());

        // Validate the MGachaRendition in the database
        List<MGachaRendition> mGachaRenditionList = mGachaRenditionRepository.findAll();
        assertThat(mGachaRenditionList).hasSize(databaseSizeBeforeUpdate);
        MGachaRendition testMGachaRendition = mGachaRenditionList.get(mGachaRenditionList.size() - 1);
        assertThat(testMGachaRendition.getMainPrefabName()).isEqualTo(UPDATED_MAIN_PREFAB_NAME);
        assertThat(testMGachaRendition.getResultExpectedUpPrefabName()).isEqualTo(UPDATED_RESULT_EXPECTED_UP_PREFAB_NAME);
        assertThat(testMGachaRendition.getResultQuestionPrefabName()).isEqualTo(UPDATED_RESULT_QUESTION_PREFAB_NAME);
        assertThat(testMGachaRendition.getSoundSwitchEventName()).isEqualTo(UPDATED_SOUND_SWITCH_EVENT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMGachaRendition() throws Exception {
        int databaseSizeBeforeUpdate = mGachaRenditionRepository.findAll().size();

        // Create the MGachaRendition
        MGachaRenditionDTO mGachaRenditionDTO = mGachaRenditionMapper.toDto(mGachaRendition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMGachaRenditionMockMvc.perform(put("/api/m-gacha-renditions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mGachaRenditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MGachaRendition in the database
        List<MGachaRendition> mGachaRenditionList = mGachaRenditionRepository.findAll();
        assertThat(mGachaRenditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMGachaRendition() throws Exception {
        // Initialize the database
        mGachaRenditionRepository.saveAndFlush(mGachaRendition);

        int databaseSizeBeforeDelete = mGachaRenditionRepository.findAll().size();

        // Delete the mGachaRendition
        restMGachaRenditionMockMvc.perform(delete("/api/m-gacha-renditions/{id}", mGachaRendition.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MGachaRendition> mGachaRenditionList = mGachaRenditionRepository.findAll();
        assertThat(mGachaRenditionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRendition.class);
        MGachaRendition mGachaRendition1 = new MGachaRendition();
        mGachaRendition1.setId(1L);
        MGachaRendition mGachaRendition2 = new MGachaRendition();
        mGachaRendition2.setId(mGachaRendition1.getId());
        assertThat(mGachaRendition1).isEqualTo(mGachaRendition2);
        mGachaRendition2.setId(2L);
        assertThat(mGachaRendition1).isNotEqualTo(mGachaRendition2);
        mGachaRendition1.setId(null);
        assertThat(mGachaRendition1).isNotEqualTo(mGachaRendition2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MGachaRenditionDTO.class);
        MGachaRenditionDTO mGachaRenditionDTO1 = new MGachaRenditionDTO();
        mGachaRenditionDTO1.setId(1L);
        MGachaRenditionDTO mGachaRenditionDTO2 = new MGachaRenditionDTO();
        assertThat(mGachaRenditionDTO1).isNotEqualTo(mGachaRenditionDTO2);
        mGachaRenditionDTO2.setId(mGachaRenditionDTO1.getId());
        assertThat(mGachaRenditionDTO1).isEqualTo(mGachaRenditionDTO2);
        mGachaRenditionDTO2.setId(2L);
        assertThat(mGachaRenditionDTO1).isNotEqualTo(mGachaRenditionDTO2);
        mGachaRenditionDTO1.setId(null);
        assertThat(mGachaRenditionDTO1).isNotEqualTo(mGachaRenditionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mGachaRenditionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mGachaRenditionMapper.fromId(null)).isNull();
    }
}
