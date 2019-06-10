package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MColorPalette;
import io.shm.tsubasa.repository.MColorPaletteRepository;
import io.shm.tsubasa.service.MColorPaletteService;
import io.shm.tsubasa.service.dto.MColorPaletteDTO;
import io.shm.tsubasa.service.mapper.MColorPaletteMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MColorPaletteCriteria;
import io.shm.tsubasa.service.MColorPaletteQueryService;

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
 * Integration tests for the {@Link MColorPaletteResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MColorPaletteResourceIT {

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDER_NUM = 1;
    private static final Integer UPDATED_ORDER_NUM = 2;

    @Autowired
    private MColorPaletteRepository mColorPaletteRepository;

    @Autowired
    private MColorPaletteMapper mColorPaletteMapper;

    @Autowired
    private MColorPaletteService mColorPaletteService;

    @Autowired
    private MColorPaletteQueryService mColorPaletteQueryService;

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

    private MockMvc restMColorPaletteMockMvc;

    private MColorPalette mColorPalette;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MColorPaletteResource mColorPaletteResource = new MColorPaletteResource(mColorPaletteService, mColorPaletteQueryService);
        this.restMColorPaletteMockMvc = MockMvcBuilders.standaloneSetup(mColorPaletteResource)
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
    public static MColorPalette createEntity(EntityManager em) {
        MColorPalette mColorPalette = new MColorPalette()
            .color(DEFAULT_COLOR)
            .orderNum(DEFAULT_ORDER_NUM);
        return mColorPalette;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MColorPalette createUpdatedEntity(EntityManager em) {
        MColorPalette mColorPalette = new MColorPalette()
            .color(UPDATED_COLOR)
            .orderNum(UPDATED_ORDER_NUM);
        return mColorPalette;
    }

    @BeforeEach
    public void initTest() {
        mColorPalette = createEntity(em);
    }

    @Test
    @Transactional
    public void createMColorPalette() throws Exception {
        int databaseSizeBeforeCreate = mColorPaletteRepository.findAll().size();

        // Create the MColorPalette
        MColorPaletteDTO mColorPaletteDTO = mColorPaletteMapper.toDto(mColorPalette);
        restMColorPaletteMockMvc.perform(post("/api/m-color-palettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mColorPaletteDTO)))
            .andExpect(status().isCreated());

        // Validate the MColorPalette in the database
        List<MColorPalette> mColorPaletteList = mColorPaletteRepository.findAll();
        assertThat(mColorPaletteList).hasSize(databaseSizeBeforeCreate + 1);
        MColorPalette testMColorPalette = mColorPaletteList.get(mColorPaletteList.size() - 1);
        assertThat(testMColorPalette.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testMColorPalette.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
    }

    @Test
    @Transactional
    public void createMColorPaletteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mColorPaletteRepository.findAll().size();

        // Create the MColorPalette with an existing ID
        mColorPalette.setId(1L);
        MColorPaletteDTO mColorPaletteDTO = mColorPaletteMapper.toDto(mColorPalette);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMColorPaletteMockMvc.perform(post("/api/m-color-palettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mColorPaletteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MColorPalette in the database
        List<MColorPalette> mColorPaletteList = mColorPaletteRepository.findAll();
        assertThat(mColorPaletteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrderNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mColorPaletteRepository.findAll().size();
        // set the field null
        mColorPalette.setOrderNum(null);

        // Create the MColorPalette, which fails.
        MColorPaletteDTO mColorPaletteDTO = mColorPaletteMapper.toDto(mColorPalette);

        restMColorPaletteMockMvc.perform(post("/api/m-color-palettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mColorPaletteDTO)))
            .andExpect(status().isBadRequest());

        List<MColorPalette> mColorPaletteList = mColorPaletteRepository.findAll();
        assertThat(mColorPaletteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMColorPalettes() throws Exception {
        // Initialize the database
        mColorPaletteRepository.saveAndFlush(mColorPalette);

        // Get all the mColorPaletteList
        restMColorPaletteMockMvc.perform(get("/api/m-color-palettes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mColorPalette.getId().intValue())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR.toString())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)));
    }
    
    @Test
    @Transactional
    public void getMColorPalette() throws Exception {
        // Initialize the database
        mColorPaletteRepository.saveAndFlush(mColorPalette);

        // Get the mColorPalette
        restMColorPaletteMockMvc.perform(get("/api/m-color-palettes/{id}", mColorPalette.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mColorPalette.getId().intValue()))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR.toString()))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM));
    }

    @Test
    @Transactional
    public void getAllMColorPalettesByOrderNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mColorPaletteRepository.saveAndFlush(mColorPalette);

        // Get all the mColorPaletteList where orderNum equals to DEFAULT_ORDER_NUM
        defaultMColorPaletteShouldBeFound("orderNum.equals=" + DEFAULT_ORDER_NUM);

        // Get all the mColorPaletteList where orderNum equals to UPDATED_ORDER_NUM
        defaultMColorPaletteShouldNotBeFound("orderNum.equals=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMColorPalettesByOrderNumIsInShouldWork() throws Exception {
        // Initialize the database
        mColorPaletteRepository.saveAndFlush(mColorPalette);

        // Get all the mColorPaletteList where orderNum in DEFAULT_ORDER_NUM or UPDATED_ORDER_NUM
        defaultMColorPaletteShouldBeFound("orderNum.in=" + DEFAULT_ORDER_NUM + "," + UPDATED_ORDER_NUM);

        // Get all the mColorPaletteList where orderNum equals to UPDATED_ORDER_NUM
        defaultMColorPaletteShouldNotBeFound("orderNum.in=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMColorPalettesByOrderNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mColorPaletteRepository.saveAndFlush(mColorPalette);

        // Get all the mColorPaletteList where orderNum is not null
        defaultMColorPaletteShouldBeFound("orderNum.specified=true");

        // Get all the mColorPaletteList where orderNum is null
        defaultMColorPaletteShouldNotBeFound("orderNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMColorPalettesByOrderNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mColorPaletteRepository.saveAndFlush(mColorPalette);

        // Get all the mColorPaletteList where orderNum greater than or equals to DEFAULT_ORDER_NUM
        defaultMColorPaletteShouldBeFound("orderNum.greaterOrEqualThan=" + DEFAULT_ORDER_NUM);

        // Get all the mColorPaletteList where orderNum greater than or equals to UPDATED_ORDER_NUM
        defaultMColorPaletteShouldNotBeFound("orderNum.greaterOrEqualThan=" + UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void getAllMColorPalettesByOrderNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mColorPaletteRepository.saveAndFlush(mColorPalette);

        // Get all the mColorPaletteList where orderNum less than or equals to DEFAULT_ORDER_NUM
        defaultMColorPaletteShouldNotBeFound("orderNum.lessThan=" + DEFAULT_ORDER_NUM);

        // Get all the mColorPaletteList where orderNum less than or equals to UPDATED_ORDER_NUM
        defaultMColorPaletteShouldBeFound("orderNum.lessThan=" + UPDATED_ORDER_NUM);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMColorPaletteShouldBeFound(String filter) throws Exception {
        restMColorPaletteMockMvc.perform(get("/api/m-color-palettes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mColorPalette.getId().intValue())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR.toString())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM)));

        // Check, that the count call also returns 1
        restMColorPaletteMockMvc.perform(get("/api/m-color-palettes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMColorPaletteShouldNotBeFound(String filter) throws Exception {
        restMColorPaletteMockMvc.perform(get("/api/m-color-palettes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMColorPaletteMockMvc.perform(get("/api/m-color-palettes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMColorPalette() throws Exception {
        // Get the mColorPalette
        restMColorPaletteMockMvc.perform(get("/api/m-color-palettes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMColorPalette() throws Exception {
        // Initialize the database
        mColorPaletteRepository.saveAndFlush(mColorPalette);

        int databaseSizeBeforeUpdate = mColorPaletteRepository.findAll().size();

        // Update the mColorPalette
        MColorPalette updatedMColorPalette = mColorPaletteRepository.findById(mColorPalette.getId()).get();
        // Disconnect from session so that the updates on updatedMColorPalette are not directly saved in db
        em.detach(updatedMColorPalette);
        updatedMColorPalette
            .color(UPDATED_COLOR)
            .orderNum(UPDATED_ORDER_NUM);
        MColorPaletteDTO mColorPaletteDTO = mColorPaletteMapper.toDto(updatedMColorPalette);

        restMColorPaletteMockMvc.perform(put("/api/m-color-palettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mColorPaletteDTO)))
            .andExpect(status().isOk());

        // Validate the MColorPalette in the database
        List<MColorPalette> mColorPaletteList = mColorPaletteRepository.findAll();
        assertThat(mColorPaletteList).hasSize(databaseSizeBeforeUpdate);
        MColorPalette testMColorPalette = mColorPaletteList.get(mColorPaletteList.size() - 1);
        assertThat(testMColorPalette.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testMColorPalette.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
    }

    @Test
    @Transactional
    public void updateNonExistingMColorPalette() throws Exception {
        int databaseSizeBeforeUpdate = mColorPaletteRepository.findAll().size();

        // Create the MColorPalette
        MColorPaletteDTO mColorPaletteDTO = mColorPaletteMapper.toDto(mColorPalette);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMColorPaletteMockMvc.perform(put("/api/m-color-palettes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mColorPaletteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MColorPalette in the database
        List<MColorPalette> mColorPaletteList = mColorPaletteRepository.findAll();
        assertThat(mColorPaletteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMColorPalette() throws Exception {
        // Initialize the database
        mColorPaletteRepository.saveAndFlush(mColorPalette);

        int databaseSizeBeforeDelete = mColorPaletteRepository.findAll().size();

        // Delete the mColorPalette
        restMColorPaletteMockMvc.perform(delete("/api/m-color-palettes/{id}", mColorPalette.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MColorPalette> mColorPaletteList = mColorPaletteRepository.findAll();
        assertThat(mColorPaletteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MColorPalette.class);
        MColorPalette mColorPalette1 = new MColorPalette();
        mColorPalette1.setId(1L);
        MColorPalette mColorPalette2 = new MColorPalette();
        mColorPalette2.setId(mColorPalette1.getId());
        assertThat(mColorPalette1).isEqualTo(mColorPalette2);
        mColorPalette2.setId(2L);
        assertThat(mColorPalette1).isNotEqualTo(mColorPalette2);
        mColorPalette1.setId(null);
        assertThat(mColorPalette1).isNotEqualTo(mColorPalette2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MColorPaletteDTO.class);
        MColorPaletteDTO mColorPaletteDTO1 = new MColorPaletteDTO();
        mColorPaletteDTO1.setId(1L);
        MColorPaletteDTO mColorPaletteDTO2 = new MColorPaletteDTO();
        assertThat(mColorPaletteDTO1).isNotEqualTo(mColorPaletteDTO2);
        mColorPaletteDTO2.setId(mColorPaletteDTO1.getId());
        assertThat(mColorPaletteDTO1).isEqualTo(mColorPaletteDTO2);
        mColorPaletteDTO2.setId(2L);
        assertThat(mColorPaletteDTO1).isNotEqualTo(mColorPaletteDTO2);
        mColorPaletteDTO1.setId(null);
        assertThat(mColorPaletteDTO1).isNotEqualTo(mColorPaletteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mColorPaletteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mColorPaletteMapper.fromId(null)).isNull();
    }
}
