package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MHomeMarquee;
import io.shm.tsubasa.repository.MHomeMarqueeRepository;
import io.shm.tsubasa.service.MHomeMarqueeService;
import io.shm.tsubasa.service.dto.MHomeMarqueeDTO;
import io.shm.tsubasa.service.mapper.MHomeMarqueeMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MHomeMarqueeCriteria;
import io.shm.tsubasa.service.MHomeMarqueeQueryService;

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
 * Integration tests for the {@Link MHomeMarqueeResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MHomeMarqueeResourceIT {

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final String DEFAULT_MARQUEE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_MARQUEE_TEXT = "BBBBBBBBBB";

    private static final Integer DEFAULT_START_AT = 1;
    private static final Integer UPDATED_START_AT = 2;

    private static final Integer DEFAULT_END_AT = 1;
    private static final Integer UPDATED_END_AT = 2;

    @Autowired
    private MHomeMarqueeRepository mHomeMarqueeRepository;

    @Autowired
    private MHomeMarqueeMapper mHomeMarqueeMapper;

    @Autowired
    private MHomeMarqueeService mHomeMarqueeService;

    @Autowired
    private MHomeMarqueeQueryService mHomeMarqueeQueryService;

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

    private MockMvc restMHomeMarqueeMockMvc;

    private MHomeMarquee mHomeMarquee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MHomeMarqueeResource mHomeMarqueeResource = new MHomeMarqueeResource(mHomeMarqueeService, mHomeMarqueeQueryService);
        this.restMHomeMarqueeMockMvc = MockMvcBuilders.standaloneSetup(mHomeMarqueeResource)
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
    public static MHomeMarquee createEntity(EntityManager em) {
        MHomeMarquee mHomeMarquee = new MHomeMarquee()
            .priority(DEFAULT_PRIORITY)
            .marqueeText(DEFAULT_MARQUEE_TEXT)
            .startAt(DEFAULT_START_AT)
            .endAt(DEFAULT_END_AT);
        return mHomeMarquee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MHomeMarquee createUpdatedEntity(EntityManager em) {
        MHomeMarquee mHomeMarquee = new MHomeMarquee()
            .priority(UPDATED_PRIORITY)
            .marqueeText(UPDATED_MARQUEE_TEXT)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        return mHomeMarquee;
    }

    @BeforeEach
    public void initTest() {
        mHomeMarquee = createEntity(em);
    }

    @Test
    @Transactional
    public void createMHomeMarquee() throws Exception {
        int databaseSizeBeforeCreate = mHomeMarqueeRepository.findAll().size();

        // Create the MHomeMarquee
        MHomeMarqueeDTO mHomeMarqueeDTO = mHomeMarqueeMapper.toDto(mHomeMarquee);
        restMHomeMarqueeMockMvc.perform(post("/api/m-home-marquees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeMarqueeDTO)))
            .andExpect(status().isCreated());

        // Validate the MHomeMarquee in the database
        List<MHomeMarquee> mHomeMarqueeList = mHomeMarqueeRepository.findAll();
        assertThat(mHomeMarqueeList).hasSize(databaseSizeBeforeCreate + 1);
        MHomeMarquee testMHomeMarquee = mHomeMarqueeList.get(mHomeMarqueeList.size() - 1);
        assertThat(testMHomeMarquee.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testMHomeMarquee.getMarqueeText()).isEqualTo(DEFAULT_MARQUEE_TEXT);
        assertThat(testMHomeMarquee.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testMHomeMarquee.getEndAt()).isEqualTo(DEFAULT_END_AT);
    }

    @Test
    @Transactional
    public void createMHomeMarqueeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mHomeMarqueeRepository.findAll().size();

        // Create the MHomeMarquee with an existing ID
        mHomeMarquee.setId(1L);
        MHomeMarqueeDTO mHomeMarqueeDTO = mHomeMarqueeMapper.toDto(mHomeMarquee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMHomeMarqueeMockMvc.perform(post("/api/m-home-marquees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeMarqueeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MHomeMarquee in the database
        List<MHomeMarquee> mHomeMarqueeList = mHomeMarqueeRepository.findAll();
        assertThat(mHomeMarqueeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPriorityIsRequired() throws Exception {
        int databaseSizeBeforeTest = mHomeMarqueeRepository.findAll().size();
        // set the field null
        mHomeMarquee.setPriority(null);

        // Create the MHomeMarquee, which fails.
        MHomeMarqueeDTO mHomeMarqueeDTO = mHomeMarqueeMapper.toDto(mHomeMarquee);

        restMHomeMarqueeMockMvc.perform(post("/api/m-home-marquees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeMarqueeDTO)))
            .andExpect(status().isBadRequest());

        List<MHomeMarquee> mHomeMarqueeList = mHomeMarqueeRepository.findAll();
        assertThat(mHomeMarqueeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mHomeMarqueeRepository.findAll().size();
        // set the field null
        mHomeMarquee.setStartAt(null);

        // Create the MHomeMarquee, which fails.
        MHomeMarqueeDTO mHomeMarqueeDTO = mHomeMarqueeMapper.toDto(mHomeMarquee);

        restMHomeMarqueeMockMvc.perform(post("/api/m-home-marquees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeMarqueeDTO)))
            .andExpect(status().isBadRequest());

        List<MHomeMarquee> mHomeMarqueeList = mHomeMarqueeRepository.findAll();
        assertThat(mHomeMarqueeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = mHomeMarqueeRepository.findAll().size();
        // set the field null
        mHomeMarquee.setEndAt(null);

        // Create the MHomeMarquee, which fails.
        MHomeMarqueeDTO mHomeMarqueeDTO = mHomeMarqueeMapper.toDto(mHomeMarquee);

        restMHomeMarqueeMockMvc.perform(post("/api/m-home-marquees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeMarqueeDTO)))
            .andExpect(status().isBadRequest());

        List<MHomeMarquee> mHomeMarqueeList = mHomeMarqueeRepository.findAll();
        assertThat(mHomeMarqueeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMHomeMarquees() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList
        restMHomeMarqueeMockMvc.perform(get("/api/m-home-marquees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mHomeMarquee.getId().intValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].marqueeText").value(hasItem(DEFAULT_MARQUEE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));
    }
    
    @Test
    @Transactional
    public void getMHomeMarquee() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get the mHomeMarquee
        restMHomeMarqueeMockMvc.perform(get("/api/m-home-marquees/{id}", mHomeMarquee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mHomeMarquee.getId().intValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.marqueeText").value(DEFAULT_MARQUEE_TEXT.toString()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT))
            .andExpect(jsonPath("$.endAt").value(DEFAULT_END_AT));
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByPriorityIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where priority equals to DEFAULT_PRIORITY
        defaultMHomeMarqueeShouldBeFound("priority.equals=" + DEFAULT_PRIORITY);

        // Get all the mHomeMarqueeList where priority equals to UPDATED_PRIORITY
        defaultMHomeMarqueeShouldNotBeFound("priority.equals=" + UPDATED_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByPriorityIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where priority in DEFAULT_PRIORITY or UPDATED_PRIORITY
        defaultMHomeMarqueeShouldBeFound("priority.in=" + DEFAULT_PRIORITY + "," + UPDATED_PRIORITY);

        // Get all the mHomeMarqueeList where priority equals to UPDATED_PRIORITY
        defaultMHomeMarqueeShouldNotBeFound("priority.in=" + UPDATED_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByPriorityIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where priority is not null
        defaultMHomeMarqueeShouldBeFound("priority.specified=true");

        // Get all the mHomeMarqueeList where priority is null
        defaultMHomeMarqueeShouldNotBeFound("priority.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByPriorityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where priority greater than or equals to DEFAULT_PRIORITY
        defaultMHomeMarqueeShouldBeFound("priority.greaterOrEqualThan=" + DEFAULT_PRIORITY);

        // Get all the mHomeMarqueeList where priority greater than or equals to UPDATED_PRIORITY
        defaultMHomeMarqueeShouldNotBeFound("priority.greaterOrEqualThan=" + UPDATED_PRIORITY);
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByPriorityIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where priority less than or equals to DEFAULT_PRIORITY
        defaultMHomeMarqueeShouldNotBeFound("priority.lessThan=" + DEFAULT_PRIORITY);

        // Get all the mHomeMarqueeList where priority less than or equals to UPDATED_PRIORITY
        defaultMHomeMarqueeShouldBeFound("priority.lessThan=" + UPDATED_PRIORITY);
    }


    @Test
    @Transactional
    public void getAllMHomeMarqueesByStartAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where startAt equals to DEFAULT_START_AT
        defaultMHomeMarqueeShouldBeFound("startAt.equals=" + DEFAULT_START_AT);

        // Get all the mHomeMarqueeList where startAt equals to UPDATED_START_AT
        defaultMHomeMarqueeShouldNotBeFound("startAt.equals=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByStartAtIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where startAt in DEFAULT_START_AT or UPDATED_START_AT
        defaultMHomeMarqueeShouldBeFound("startAt.in=" + DEFAULT_START_AT + "," + UPDATED_START_AT);

        // Get all the mHomeMarqueeList where startAt equals to UPDATED_START_AT
        defaultMHomeMarqueeShouldNotBeFound("startAt.in=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByStartAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where startAt is not null
        defaultMHomeMarqueeShouldBeFound("startAt.specified=true");

        // Get all the mHomeMarqueeList where startAt is null
        defaultMHomeMarqueeShouldNotBeFound("startAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByStartAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where startAt greater than or equals to DEFAULT_START_AT
        defaultMHomeMarqueeShouldBeFound("startAt.greaterOrEqualThan=" + DEFAULT_START_AT);

        // Get all the mHomeMarqueeList where startAt greater than or equals to UPDATED_START_AT
        defaultMHomeMarqueeShouldNotBeFound("startAt.greaterOrEqualThan=" + UPDATED_START_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByStartAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where startAt less than or equals to DEFAULT_START_AT
        defaultMHomeMarqueeShouldNotBeFound("startAt.lessThan=" + DEFAULT_START_AT);

        // Get all the mHomeMarqueeList where startAt less than or equals to UPDATED_START_AT
        defaultMHomeMarqueeShouldBeFound("startAt.lessThan=" + UPDATED_START_AT);
    }


    @Test
    @Transactional
    public void getAllMHomeMarqueesByEndAtIsEqualToSomething() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where endAt equals to DEFAULT_END_AT
        defaultMHomeMarqueeShouldBeFound("endAt.equals=" + DEFAULT_END_AT);

        // Get all the mHomeMarqueeList where endAt equals to UPDATED_END_AT
        defaultMHomeMarqueeShouldNotBeFound("endAt.equals=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByEndAtIsInShouldWork() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where endAt in DEFAULT_END_AT or UPDATED_END_AT
        defaultMHomeMarqueeShouldBeFound("endAt.in=" + DEFAULT_END_AT + "," + UPDATED_END_AT);

        // Get all the mHomeMarqueeList where endAt equals to UPDATED_END_AT
        defaultMHomeMarqueeShouldNotBeFound("endAt.in=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByEndAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where endAt is not null
        defaultMHomeMarqueeShouldBeFound("endAt.specified=true");

        // Get all the mHomeMarqueeList where endAt is null
        defaultMHomeMarqueeShouldNotBeFound("endAt.specified=false");
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByEndAtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where endAt greater than or equals to DEFAULT_END_AT
        defaultMHomeMarqueeShouldBeFound("endAt.greaterOrEqualThan=" + DEFAULT_END_AT);

        // Get all the mHomeMarqueeList where endAt greater than or equals to UPDATED_END_AT
        defaultMHomeMarqueeShouldNotBeFound("endAt.greaterOrEqualThan=" + UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void getAllMHomeMarqueesByEndAtIsLessThanSomething() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        // Get all the mHomeMarqueeList where endAt less than or equals to DEFAULT_END_AT
        defaultMHomeMarqueeShouldNotBeFound("endAt.lessThan=" + DEFAULT_END_AT);

        // Get all the mHomeMarqueeList where endAt less than or equals to UPDATED_END_AT
        defaultMHomeMarqueeShouldBeFound("endAt.lessThan=" + UPDATED_END_AT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMHomeMarqueeShouldBeFound(String filter) throws Exception {
        restMHomeMarqueeMockMvc.perform(get("/api/m-home-marquees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mHomeMarquee.getId().intValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].marqueeText").value(hasItem(DEFAULT_MARQUEE_TEXT.toString())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT)))
            .andExpect(jsonPath("$.[*].endAt").value(hasItem(DEFAULT_END_AT)));

        // Check, that the count call also returns 1
        restMHomeMarqueeMockMvc.perform(get("/api/m-home-marquees/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMHomeMarqueeShouldNotBeFound(String filter) throws Exception {
        restMHomeMarqueeMockMvc.perform(get("/api/m-home-marquees?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMHomeMarqueeMockMvc.perform(get("/api/m-home-marquees/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMHomeMarquee() throws Exception {
        // Get the mHomeMarquee
        restMHomeMarqueeMockMvc.perform(get("/api/m-home-marquees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMHomeMarquee() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        int databaseSizeBeforeUpdate = mHomeMarqueeRepository.findAll().size();

        // Update the mHomeMarquee
        MHomeMarquee updatedMHomeMarquee = mHomeMarqueeRepository.findById(mHomeMarquee.getId()).get();
        // Disconnect from session so that the updates on updatedMHomeMarquee are not directly saved in db
        em.detach(updatedMHomeMarquee);
        updatedMHomeMarquee
            .priority(UPDATED_PRIORITY)
            .marqueeText(UPDATED_MARQUEE_TEXT)
            .startAt(UPDATED_START_AT)
            .endAt(UPDATED_END_AT);
        MHomeMarqueeDTO mHomeMarqueeDTO = mHomeMarqueeMapper.toDto(updatedMHomeMarquee);

        restMHomeMarqueeMockMvc.perform(put("/api/m-home-marquees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeMarqueeDTO)))
            .andExpect(status().isOk());

        // Validate the MHomeMarquee in the database
        List<MHomeMarquee> mHomeMarqueeList = mHomeMarqueeRepository.findAll();
        assertThat(mHomeMarqueeList).hasSize(databaseSizeBeforeUpdate);
        MHomeMarquee testMHomeMarquee = mHomeMarqueeList.get(mHomeMarqueeList.size() - 1);
        assertThat(testMHomeMarquee.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testMHomeMarquee.getMarqueeText()).isEqualTo(UPDATED_MARQUEE_TEXT);
        assertThat(testMHomeMarquee.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testMHomeMarquee.getEndAt()).isEqualTo(UPDATED_END_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingMHomeMarquee() throws Exception {
        int databaseSizeBeforeUpdate = mHomeMarqueeRepository.findAll().size();

        // Create the MHomeMarquee
        MHomeMarqueeDTO mHomeMarqueeDTO = mHomeMarqueeMapper.toDto(mHomeMarquee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMHomeMarqueeMockMvc.perform(put("/api/m-home-marquees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mHomeMarqueeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MHomeMarquee in the database
        List<MHomeMarquee> mHomeMarqueeList = mHomeMarqueeRepository.findAll();
        assertThat(mHomeMarqueeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMHomeMarquee() throws Exception {
        // Initialize the database
        mHomeMarqueeRepository.saveAndFlush(mHomeMarquee);

        int databaseSizeBeforeDelete = mHomeMarqueeRepository.findAll().size();

        // Delete the mHomeMarquee
        restMHomeMarqueeMockMvc.perform(delete("/api/m-home-marquees/{id}", mHomeMarquee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MHomeMarquee> mHomeMarqueeList = mHomeMarqueeRepository.findAll();
        assertThat(mHomeMarqueeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MHomeMarquee.class);
        MHomeMarquee mHomeMarquee1 = new MHomeMarquee();
        mHomeMarquee1.setId(1L);
        MHomeMarquee mHomeMarquee2 = new MHomeMarquee();
        mHomeMarquee2.setId(mHomeMarquee1.getId());
        assertThat(mHomeMarquee1).isEqualTo(mHomeMarquee2);
        mHomeMarquee2.setId(2L);
        assertThat(mHomeMarquee1).isNotEqualTo(mHomeMarquee2);
        mHomeMarquee1.setId(null);
        assertThat(mHomeMarquee1).isNotEqualTo(mHomeMarquee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MHomeMarqueeDTO.class);
        MHomeMarqueeDTO mHomeMarqueeDTO1 = new MHomeMarqueeDTO();
        mHomeMarqueeDTO1.setId(1L);
        MHomeMarqueeDTO mHomeMarqueeDTO2 = new MHomeMarqueeDTO();
        assertThat(mHomeMarqueeDTO1).isNotEqualTo(mHomeMarqueeDTO2);
        mHomeMarqueeDTO2.setId(mHomeMarqueeDTO1.getId());
        assertThat(mHomeMarqueeDTO1).isEqualTo(mHomeMarqueeDTO2);
        mHomeMarqueeDTO2.setId(2L);
        assertThat(mHomeMarqueeDTO1).isNotEqualTo(mHomeMarqueeDTO2);
        mHomeMarqueeDTO1.setId(null);
        assertThat(mHomeMarqueeDTO1).isNotEqualTo(mHomeMarqueeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mHomeMarqueeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mHomeMarqueeMapper.fromId(null)).isNull();
    }
}
