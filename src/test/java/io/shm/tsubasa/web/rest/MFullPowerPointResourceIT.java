package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MFullPowerPoint;
import io.shm.tsubasa.repository.MFullPowerPointRepository;
import io.shm.tsubasa.service.MFullPowerPointService;
import io.shm.tsubasa.service.dto.MFullPowerPointDTO;
import io.shm.tsubasa.service.mapper.MFullPowerPointMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MFullPowerPointCriteria;
import io.shm.tsubasa.service.MFullPowerPointQueryService;

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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.shm.tsubasa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MFullPowerPointResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MFullPowerPointResourceIT {

    private static final Integer DEFAULT_POINT_TYPE = 1;
    private static final Integer UPDATED_POINT_TYPE = 2;

    private static final Integer DEFAULT_VALUE = 1;
    private static final Integer UPDATED_VALUE = 2;

    @Autowired
    private MFullPowerPointRepository mFullPowerPointRepository;

    @Autowired
    private MFullPowerPointMapper mFullPowerPointMapper;

    @Autowired
    private MFullPowerPointService mFullPowerPointService;

    @Autowired
    private MFullPowerPointQueryService mFullPowerPointQueryService;

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

    private MockMvc restMFullPowerPointMockMvc;

    private MFullPowerPoint mFullPowerPoint;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MFullPowerPointResource mFullPowerPointResource = new MFullPowerPointResource(mFullPowerPointService, mFullPowerPointQueryService);
        this.restMFullPowerPointMockMvc = MockMvcBuilders.standaloneSetup(mFullPowerPointResource)
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
    public static MFullPowerPoint createEntity(EntityManager em) {
        MFullPowerPoint mFullPowerPoint = new MFullPowerPoint()
            .pointType(DEFAULT_POINT_TYPE)
            .value(DEFAULT_VALUE);
        return mFullPowerPoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MFullPowerPoint createUpdatedEntity(EntityManager em) {
        MFullPowerPoint mFullPowerPoint = new MFullPowerPoint()
            .pointType(UPDATED_POINT_TYPE)
            .value(UPDATED_VALUE);
        return mFullPowerPoint;
    }

    @BeforeEach
    public void initTest() {
        mFullPowerPoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createMFullPowerPoint() throws Exception {
        int databaseSizeBeforeCreate = mFullPowerPointRepository.findAll().size();

        // Create the MFullPowerPoint
        MFullPowerPointDTO mFullPowerPointDTO = mFullPowerPointMapper.toDto(mFullPowerPoint);
        restMFullPowerPointMockMvc.perform(post("/api/m-full-power-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFullPowerPointDTO)))
            .andExpect(status().isCreated());

        // Validate the MFullPowerPoint in the database
        List<MFullPowerPoint> mFullPowerPointList = mFullPowerPointRepository.findAll();
        assertThat(mFullPowerPointList).hasSize(databaseSizeBeforeCreate + 1);
        MFullPowerPoint testMFullPowerPoint = mFullPowerPointList.get(mFullPowerPointList.size() - 1);
        assertThat(testMFullPowerPoint.getPointType()).isEqualTo(DEFAULT_POINT_TYPE);
        assertThat(testMFullPowerPoint.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createMFullPowerPointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mFullPowerPointRepository.findAll().size();

        // Create the MFullPowerPoint with an existing ID
        mFullPowerPoint.setId(1L);
        MFullPowerPointDTO mFullPowerPointDTO = mFullPowerPointMapper.toDto(mFullPowerPoint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMFullPowerPointMockMvc.perform(post("/api/m-full-power-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFullPowerPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MFullPowerPoint in the database
        List<MFullPowerPoint> mFullPowerPointList = mFullPowerPointRepository.findAll();
        assertThat(mFullPowerPointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPointTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mFullPowerPointRepository.findAll().size();
        // set the field null
        mFullPowerPoint.setPointType(null);

        // Create the MFullPowerPoint, which fails.
        MFullPowerPointDTO mFullPowerPointDTO = mFullPowerPointMapper.toDto(mFullPowerPoint);

        restMFullPowerPointMockMvc.perform(post("/api/m-full-power-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFullPowerPointDTO)))
            .andExpect(status().isBadRequest());

        List<MFullPowerPoint> mFullPowerPointList = mFullPowerPointRepository.findAll();
        assertThat(mFullPowerPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = mFullPowerPointRepository.findAll().size();
        // set the field null
        mFullPowerPoint.setValue(null);

        // Create the MFullPowerPoint, which fails.
        MFullPowerPointDTO mFullPowerPointDTO = mFullPowerPointMapper.toDto(mFullPowerPoint);

        restMFullPowerPointMockMvc.perform(post("/api/m-full-power-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFullPowerPointDTO)))
            .andExpect(status().isBadRequest());

        List<MFullPowerPoint> mFullPowerPointList = mFullPowerPointRepository.findAll();
        assertThat(mFullPowerPointList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMFullPowerPoints() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList
        restMFullPowerPointMockMvc.perform(get("/api/m-full-power-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mFullPowerPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].pointType").value(hasItem(DEFAULT_POINT_TYPE)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getMFullPowerPoint() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get the mFullPowerPoint
        restMFullPowerPointMockMvc.perform(get("/api/m-full-power-points/{id}", mFullPowerPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mFullPowerPoint.getId().intValue()))
            .andExpect(jsonPath("$.pointType").value(DEFAULT_POINT_TYPE))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    public void getAllMFullPowerPointsByPointTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where pointType equals to DEFAULT_POINT_TYPE
        defaultMFullPowerPointShouldBeFound("pointType.equals=" + DEFAULT_POINT_TYPE);

        // Get all the mFullPowerPointList where pointType equals to UPDATED_POINT_TYPE
        defaultMFullPowerPointShouldNotBeFound("pointType.equals=" + UPDATED_POINT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMFullPowerPointsByPointTypeIsInShouldWork() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where pointType in DEFAULT_POINT_TYPE or UPDATED_POINT_TYPE
        defaultMFullPowerPointShouldBeFound("pointType.in=" + DEFAULT_POINT_TYPE + "," + UPDATED_POINT_TYPE);

        // Get all the mFullPowerPointList where pointType equals to UPDATED_POINT_TYPE
        defaultMFullPowerPointShouldNotBeFound("pointType.in=" + UPDATED_POINT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMFullPowerPointsByPointTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where pointType is not null
        defaultMFullPowerPointShouldBeFound("pointType.specified=true");

        // Get all the mFullPowerPointList where pointType is null
        defaultMFullPowerPointShouldNotBeFound("pointType.specified=false");
    }

    @Test
    @Transactional
    public void getAllMFullPowerPointsByPointTypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where pointType greater than or equals to DEFAULT_POINT_TYPE
        defaultMFullPowerPointShouldBeFound("pointType.greaterOrEqualThan=" + DEFAULT_POINT_TYPE);

        // Get all the mFullPowerPointList where pointType greater than or equals to UPDATED_POINT_TYPE
        defaultMFullPowerPointShouldNotBeFound("pointType.greaterOrEqualThan=" + UPDATED_POINT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMFullPowerPointsByPointTypeIsLessThanSomething() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where pointType less than or equals to DEFAULT_POINT_TYPE
        defaultMFullPowerPointShouldNotBeFound("pointType.lessThan=" + DEFAULT_POINT_TYPE);

        // Get all the mFullPowerPointList where pointType less than or equals to UPDATED_POINT_TYPE
        defaultMFullPowerPointShouldBeFound("pointType.lessThan=" + UPDATED_POINT_TYPE);
    }


    @Test
    @Transactional
    public void getAllMFullPowerPointsByValueIsEqualToSomething() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where value equals to DEFAULT_VALUE
        defaultMFullPowerPointShouldBeFound("value.equals=" + DEFAULT_VALUE);

        // Get all the mFullPowerPointList where value equals to UPDATED_VALUE
        defaultMFullPowerPointShouldNotBeFound("value.equals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMFullPowerPointsByValueIsInShouldWork() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where value in DEFAULT_VALUE or UPDATED_VALUE
        defaultMFullPowerPointShouldBeFound("value.in=" + DEFAULT_VALUE + "," + UPDATED_VALUE);

        // Get all the mFullPowerPointList where value equals to UPDATED_VALUE
        defaultMFullPowerPointShouldNotBeFound("value.in=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMFullPowerPointsByValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where value is not null
        defaultMFullPowerPointShouldBeFound("value.specified=true");

        // Get all the mFullPowerPointList where value is null
        defaultMFullPowerPointShouldNotBeFound("value.specified=false");
    }

    @Test
    @Transactional
    public void getAllMFullPowerPointsByValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where value greater than or equals to DEFAULT_VALUE
        defaultMFullPowerPointShouldBeFound("value.greaterOrEqualThan=" + DEFAULT_VALUE);

        // Get all the mFullPowerPointList where value greater than or equals to UPDATED_VALUE
        defaultMFullPowerPointShouldNotBeFound("value.greaterOrEqualThan=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllMFullPowerPointsByValueIsLessThanSomething() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        // Get all the mFullPowerPointList where value less than or equals to DEFAULT_VALUE
        defaultMFullPowerPointShouldNotBeFound("value.lessThan=" + DEFAULT_VALUE);

        // Get all the mFullPowerPointList where value less than or equals to UPDATED_VALUE
        defaultMFullPowerPointShouldBeFound("value.lessThan=" + UPDATED_VALUE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMFullPowerPointShouldBeFound(String filter) throws Exception {
        restMFullPowerPointMockMvc.perform(get("/api/m-full-power-points?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mFullPowerPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].pointType").value(hasItem(DEFAULT_POINT_TYPE)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));

        // Check, that the count call also returns 1
        restMFullPowerPointMockMvc.perform(get("/api/m-full-power-points/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMFullPowerPointShouldNotBeFound(String filter) throws Exception {
        restMFullPowerPointMockMvc.perform(get("/api/m-full-power-points?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMFullPowerPointMockMvc.perform(get("/api/m-full-power-points/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMFullPowerPoint() throws Exception {
        // Get the mFullPowerPoint
        restMFullPowerPointMockMvc.perform(get("/api/m-full-power-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMFullPowerPoint() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        int databaseSizeBeforeUpdate = mFullPowerPointRepository.findAll().size();

        // Update the mFullPowerPoint
        MFullPowerPoint updatedMFullPowerPoint = mFullPowerPointRepository.findById(mFullPowerPoint.getId()).get();
        // Disconnect from session so that the updates on updatedMFullPowerPoint are not directly saved in db
        em.detach(updatedMFullPowerPoint);
        updatedMFullPowerPoint
            .pointType(UPDATED_POINT_TYPE)
            .value(UPDATED_VALUE);
        MFullPowerPointDTO mFullPowerPointDTO = mFullPowerPointMapper.toDto(updatedMFullPowerPoint);

        restMFullPowerPointMockMvc.perform(put("/api/m-full-power-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFullPowerPointDTO)))
            .andExpect(status().isOk());

        // Validate the MFullPowerPoint in the database
        List<MFullPowerPoint> mFullPowerPointList = mFullPowerPointRepository.findAll();
        assertThat(mFullPowerPointList).hasSize(databaseSizeBeforeUpdate);
        MFullPowerPoint testMFullPowerPoint = mFullPowerPointList.get(mFullPowerPointList.size() - 1);
        assertThat(testMFullPowerPoint.getPointType()).isEqualTo(UPDATED_POINT_TYPE);
        assertThat(testMFullPowerPoint.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingMFullPowerPoint() throws Exception {
        int databaseSizeBeforeUpdate = mFullPowerPointRepository.findAll().size();

        // Create the MFullPowerPoint
        MFullPowerPointDTO mFullPowerPointDTO = mFullPowerPointMapper.toDto(mFullPowerPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMFullPowerPointMockMvc.perform(put("/api/m-full-power-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mFullPowerPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MFullPowerPoint in the database
        List<MFullPowerPoint> mFullPowerPointList = mFullPowerPointRepository.findAll();
        assertThat(mFullPowerPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMFullPowerPoint() throws Exception {
        // Initialize the database
        mFullPowerPointRepository.saveAndFlush(mFullPowerPoint);

        int databaseSizeBeforeDelete = mFullPowerPointRepository.findAll().size();

        // Delete the mFullPowerPoint
        restMFullPowerPointMockMvc.perform(delete("/api/m-full-power-points/{id}", mFullPowerPoint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MFullPowerPoint> mFullPowerPointList = mFullPowerPointRepository.findAll();
        assertThat(mFullPowerPointList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MFullPowerPoint.class);
        MFullPowerPoint mFullPowerPoint1 = new MFullPowerPoint();
        mFullPowerPoint1.setId(1L);
        MFullPowerPoint mFullPowerPoint2 = new MFullPowerPoint();
        mFullPowerPoint2.setId(mFullPowerPoint1.getId());
        assertThat(mFullPowerPoint1).isEqualTo(mFullPowerPoint2);
        mFullPowerPoint2.setId(2L);
        assertThat(mFullPowerPoint1).isNotEqualTo(mFullPowerPoint2);
        mFullPowerPoint1.setId(null);
        assertThat(mFullPowerPoint1).isNotEqualTo(mFullPowerPoint2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MFullPowerPointDTO.class);
        MFullPowerPointDTO mFullPowerPointDTO1 = new MFullPowerPointDTO();
        mFullPowerPointDTO1.setId(1L);
        MFullPowerPointDTO mFullPowerPointDTO2 = new MFullPowerPointDTO();
        assertThat(mFullPowerPointDTO1).isNotEqualTo(mFullPowerPointDTO2);
        mFullPowerPointDTO2.setId(mFullPowerPointDTO1.getId());
        assertThat(mFullPowerPointDTO1).isEqualTo(mFullPowerPointDTO2);
        mFullPowerPointDTO2.setId(2L);
        assertThat(mFullPowerPointDTO1).isNotEqualTo(mFullPowerPointDTO2);
        mFullPowerPointDTO1.setId(null);
        assertThat(mFullPowerPointDTO1).isNotEqualTo(mFullPowerPointDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mFullPowerPointMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mFullPowerPointMapper.fromId(null)).isNull();
    }
}
