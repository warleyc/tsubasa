package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MSellCardCoin;
import io.shm.tsubasa.repository.MSellCardCoinRepository;
import io.shm.tsubasa.service.MSellCardCoinService;
import io.shm.tsubasa.service.dto.MSellCardCoinDTO;
import io.shm.tsubasa.service.mapper.MSellCardCoinMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MSellCardCoinCriteria;
import io.shm.tsubasa.service.MSellCardCoinQueryService;

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
 * Integration tests for the {@Link MSellCardCoinResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MSellCardCoinResourceIT {

    private static final Integer DEFAULT_GROUP_NUM = 1;
    private static final Integer UPDATED_GROUP_NUM = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_COIN = 1;
    private static final Integer UPDATED_COIN = 2;

    @Autowired
    private MSellCardCoinRepository mSellCardCoinRepository;

    @Autowired
    private MSellCardCoinMapper mSellCardCoinMapper;

    @Autowired
    private MSellCardCoinService mSellCardCoinService;

    @Autowired
    private MSellCardCoinQueryService mSellCardCoinQueryService;

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

    private MockMvc restMSellCardCoinMockMvc;

    private MSellCardCoin mSellCardCoin;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MSellCardCoinResource mSellCardCoinResource = new MSellCardCoinResource(mSellCardCoinService, mSellCardCoinQueryService);
        this.restMSellCardCoinMockMvc = MockMvcBuilders.standaloneSetup(mSellCardCoinResource)
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
    public static MSellCardCoin createEntity(EntityManager em) {
        MSellCardCoin mSellCardCoin = new MSellCardCoin()
            .groupNum(DEFAULT_GROUP_NUM)
            .level(DEFAULT_LEVEL)
            .coin(DEFAULT_COIN);
        return mSellCardCoin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MSellCardCoin createUpdatedEntity(EntityManager em) {
        MSellCardCoin mSellCardCoin = new MSellCardCoin()
            .groupNum(UPDATED_GROUP_NUM)
            .level(UPDATED_LEVEL)
            .coin(UPDATED_COIN);
        return mSellCardCoin;
    }

    @BeforeEach
    public void initTest() {
        mSellCardCoin = createEntity(em);
    }

    @Test
    @Transactional
    public void createMSellCardCoin() throws Exception {
        int databaseSizeBeforeCreate = mSellCardCoinRepository.findAll().size();

        // Create the MSellCardCoin
        MSellCardCoinDTO mSellCardCoinDTO = mSellCardCoinMapper.toDto(mSellCardCoin);
        restMSellCardCoinMockMvc.perform(post("/api/m-sell-card-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardCoinDTO)))
            .andExpect(status().isCreated());

        // Validate the MSellCardCoin in the database
        List<MSellCardCoin> mSellCardCoinList = mSellCardCoinRepository.findAll();
        assertThat(mSellCardCoinList).hasSize(databaseSizeBeforeCreate + 1);
        MSellCardCoin testMSellCardCoin = mSellCardCoinList.get(mSellCardCoinList.size() - 1);
        assertThat(testMSellCardCoin.getGroupNum()).isEqualTo(DEFAULT_GROUP_NUM);
        assertThat(testMSellCardCoin.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testMSellCardCoin.getCoin()).isEqualTo(DEFAULT_COIN);
    }

    @Test
    @Transactional
    public void createMSellCardCoinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mSellCardCoinRepository.findAll().size();

        // Create the MSellCardCoin with an existing ID
        mSellCardCoin.setId(1L);
        MSellCardCoinDTO mSellCardCoinDTO = mSellCardCoinMapper.toDto(mSellCardCoin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMSellCardCoinMockMvc.perform(post("/api/m-sell-card-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardCoinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSellCardCoin in the database
        List<MSellCardCoin> mSellCardCoinList = mSellCardCoinRepository.findAll();
        assertThat(mSellCardCoinList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupNumIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSellCardCoinRepository.findAll().size();
        // set the field null
        mSellCardCoin.setGroupNum(null);

        // Create the MSellCardCoin, which fails.
        MSellCardCoinDTO mSellCardCoinDTO = mSellCardCoinMapper.toDto(mSellCardCoin);

        restMSellCardCoinMockMvc.perform(post("/api/m-sell-card-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardCoinDTO)))
            .andExpect(status().isBadRequest());

        List<MSellCardCoin> mSellCardCoinList = mSellCardCoinRepository.findAll();
        assertThat(mSellCardCoinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSellCardCoinRepository.findAll().size();
        // set the field null
        mSellCardCoin.setLevel(null);

        // Create the MSellCardCoin, which fails.
        MSellCardCoinDTO mSellCardCoinDTO = mSellCardCoinMapper.toDto(mSellCardCoin);

        restMSellCardCoinMockMvc.perform(post("/api/m-sell-card-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardCoinDTO)))
            .andExpect(status().isBadRequest());

        List<MSellCardCoin> mSellCardCoinList = mSellCardCoinRepository.findAll();
        assertThat(mSellCardCoinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mSellCardCoinRepository.findAll().size();
        // set the field null
        mSellCardCoin.setCoin(null);

        // Create the MSellCardCoin, which fails.
        MSellCardCoinDTO mSellCardCoinDTO = mSellCardCoinMapper.toDto(mSellCardCoin);

        restMSellCardCoinMockMvc.perform(post("/api/m-sell-card-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardCoinDTO)))
            .andExpect(status().isBadRequest());

        List<MSellCardCoin> mSellCardCoinList = mSellCardCoinRepository.findAll();
        assertThat(mSellCardCoinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoins() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList
        restMSellCardCoinMockMvc.perform(get("/api/m-sell-card-coins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSellCardCoin.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupNum").value(hasItem(DEFAULT_GROUP_NUM)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)));
    }
    
    @Test
    @Transactional
    public void getMSellCardCoin() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get the mSellCardCoin
        restMSellCardCoinMockMvc.perform(get("/api/m-sell-card-coins/{id}", mSellCardCoin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mSellCardCoin.getId().intValue()))
            .andExpect(jsonPath("$.groupNum").value(DEFAULT_GROUP_NUM))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN));
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByGroupNumIsEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where groupNum equals to DEFAULT_GROUP_NUM
        defaultMSellCardCoinShouldBeFound("groupNum.equals=" + DEFAULT_GROUP_NUM);

        // Get all the mSellCardCoinList where groupNum equals to UPDATED_GROUP_NUM
        defaultMSellCardCoinShouldNotBeFound("groupNum.equals=" + UPDATED_GROUP_NUM);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByGroupNumIsInShouldWork() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where groupNum in DEFAULT_GROUP_NUM or UPDATED_GROUP_NUM
        defaultMSellCardCoinShouldBeFound("groupNum.in=" + DEFAULT_GROUP_NUM + "," + UPDATED_GROUP_NUM);

        // Get all the mSellCardCoinList where groupNum equals to UPDATED_GROUP_NUM
        defaultMSellCardCoinShouldNotBeFound("groupNum.in=" + UPDATED_GROUP_NUM);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByGroupNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where groupNum is not null
        defaultMSellCardCoinShouldBeFound("groupNum.specified=true");

        // Get all the mSellCardCoinList where groupNum is null
        defaultMSellCardCoinShouldNotBeFound("groupNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByGroupNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where groupNum greater than or equals to DEFAULT_GROUP_NUM
        defaultMSellCardCoinShouldBeFound("groupNum.greaterOrEqualThan=" + DEFAULT_GROUP_NUM);

        // Get all the mSellCardCoinList where groupNum greater than or equals to UPDATED_GROUP_NUM
        defaultMSellCardCoinShouldNotBeFound("groupNum.greaterOrEqualThan=" + UPDATED_GROUP_NUM);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByGroupNumIsLessThanSomething() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where groupNum less than or equals to DEFAULT_GROUP_NUM
        defaultMSellCardCoinShouldNotBeFound("groupNum.lessThan=" + DEFAULT_GROUP_NUM);

        // Get all the mSellCardCoinList where groupNum less than or equals to UPDATED_GROUP_NUM
        defaultMSellCardCoinShouldBeFound("groupNum.lessThan=" + UPDATED_GROUP_NUM);
    }


    @Test
    @Transactional
    public void getAllMSellCardCoinsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where level equals to DEFAULT_LEVEL
        defaultMSellCardCoinShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the mSellCardCoinList where level equals to UPDATED_LEVEL
        defaultMSellCardCoinShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultMSellCardCoinShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the mSellCardCoinList where level equals to UPDATED_LEVEL
        defaultMSellCardCoinShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where level is not null
        defaultMSellCardCoinShouldBeFound("level.specified=true");

        // Get all the mSellCardCoinList where level is null
        defaultMSellCardCoinShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where level greater than or equals to DEFAULT_LEVEL
        defaultMSellCardCoinShouldBeFound("level.greaterOrEqualThan=" + DEFAULT_LEVEL);

        // Get all the mSellCardCoinList where level greater than or equals to UPDATED_LEVEL
        defaultMSellCardCoinShouldNotBeFound("level.greaterOrEqualThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where level less than or equals to DEFAULT_LEVEL
        defaultMSellCardCoinShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the mSellCardCoinList where level less than or equals to UPDATED_LEVEL
        defaultMSellCardCoinShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }


    @Test
    @Transactional
    public void getAllMSellCardCoinsByCoinIsEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where coin equals to DEFAULT_COIN
        defaultMSellCardCoinShouldBeFound("coin.equals=" + DEFAULT_COIN);

        // Get all the mSellCardCoinList where coin equals to UPDATED_COIN
        defaultMSellCardCoinShouldNotBeFound("coin.equals=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByCoinIsInShouldWork() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where coin in DEFAULT_COIN or UPDATED_COIN
        defaultMSellCardCoinShouldBeFound("coin.in=" + DEFAULT_COIN + "," + UPDATED_COIN);

        // Get all the mSellCardCoinList where coin equals to UPDATED_COIN
        defaultMSellCardCoinShouldNotBeFound("coin.in=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByCoinIsNullOrNotNull() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where coin is not null
        defaultMSellCardCoinShouldBeFound("coin.specified=true");

        // Get all the mSellCardCoinList where coin is null
        defaultMSellCardCoinShouldNotBeFound("coin.specified=false");
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByCoinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where coin greater than or equals to DEFAULT_COIN
        defaultMSellCardCoinShouldBeFound("coin.greaterOrEqualThan=" + DEFAULT_COIN);

        // Get all the mSellCardCoinList where coin greater than or equals to UPDATED_COIN
        defaultMSellCardCoinShouldNotBeFound("coin.greaterOrEqualThan=" + UPDATED_COIN);
    }

    @Test
    @Transactional
    public void getAllMSellCardCoinsByCoinIsLessThanSomething() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        // Get all the mSellCardCoinList where coin less than or equals to DEFAULT_COIN
        defaultMSellCardCoinShouldNotBeFound("coin.lessThan=" + DEFAULT_COIN);

        // Get all the mSellCardCoinList where coin less than or equals to UPDATED_COIN
        defaultMSellCardCoinShouldBeFound("coin.lessThan=" + UPDATED_COIN);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMSellCardCoinShouldBeFound(String filter) throws Exception {
        restMSellCardCoinMockMvc.perform(get("/api/m-sell-card-coins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mSellCardCoin.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupNum").value(hasItem(DEFAULT_GROUP_NUM)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)));

        // Check, that the count call also returns 1
        restMSellCardCoinMockMvc.perform(get("/api/m-sell-card-coins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMSellCardCoinShouldNotBeFound(String filter) throws Exception {
        restMSellCardCoinMockMvc.perform(get("/api/m-sell-card-coins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMSellCardCoinMockMvc.perform(get("/api/m-sell-card-coins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMSellCardCoin() throws Exception {
        // Get the mSellCardCoin
        restMSellCardCoinMockMvc.perform(get("/api/m-sell-card-coins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMSellCardCoin() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        int databaseSizeBeforeUpdate = mSellCardCoinRepository.findAll().size();

        // Update the mSellCardCoin
        MSellCardCoin updatedMSellCardCoin = mSellCardCoinRepository.findById(mSellCardCoin.getId()).get();
        // Disconnect from session so that the updates on updatedMSellCardCoin are not directly saved in db
        em.detach(updatedMSellCardCoin);
        updatedMSellCardCoin
            .groupNum(UPDATED_GROUP_NUM)
            .level(UPDATED_LEVEL)
            .coin(UPDATED_COIN);
        MSellCardCoinDTO mSellCardCoinDTO = mSellCardCoinMapper.toDto(updatedMSellCardCoin);

        restMSellCardCoinMockMvc.perform(put("/api/m-sell-card-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardCoinDTO)))
            .andExpect(status().isOk());

        // Validate the MSellCardCoin in the database
        List<MSellCardCoin> mSellCardCoinList = mSellCardCoinRepository.findAll();
        assertThat(mSellCardCoinList).hasSize(databaseSizeBeforeUpdate);
        MSellCardCoin testMSellCardCoin = mSellCardCoinList.get(mSellCardCoinList.size() - 1);
        assertThat(testMSellCardCoin.getGroupNum()).isEqualTo(UPDATED_GROUP_NUM);
        assertThat(testMSellCardCoin.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testMSellCardCoin.getCoin()).isEqualTo(UPDATED_COIN);
    }

    @Test
    @Transactional
    public void updateNonExistingMSellCardCoin() throws Exception {
        int databaseSizeBeforeUpdate = mSellCardCoinRepository.findAll().size();

        // Create the MSellCardCoin
        MSellCardCoinDTO mSellCardCoinDTO = mSellCardCoinMapper.toDto(mSellCardCoin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMSellCardCoinMockMvc.perform(put("/api/m-sell-card-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mSellCardCoinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MSellCardCoin in the database
        List<MSellCardCoin> mSellCardCoinList = mSellCardCoinRepository.findAll();
        assertThat(mSellCardCoinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMSellCardCoin() throws Exception {
        // Initialize the database
        mSellCardCoinRepository.saveAndFlush(mSellCardCoin);

        int databaseSizeBeforeDelete = mSellCardCoinRepository.findAll().size();

        // Delete the mSellCardCoin
        restMSellCardCoinMockMvc.perform(delete("/api/m-sell-card-coins/{id}", mSellCardCoin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MSellCardCoin> mSellCardCoinList = mSellCardCoinRepository.findAll();
        assertThat(mSellCardCoinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSellCardCoin.class);
        MSellCardCoin mSellCardCoin1 = new MSellCardCoin();
        mSellCardCoin1.setId(1L);
        MSellCardCoin mSellCardCoin2 = new MSellCardCoin();
        mSellCardCoin2.setId(mSellCardCoin1.getId());
        assertThat(mSellCardCoin1).isEqualTo(mSellCardCoin2);
        mSellCardCoin2.setId(2L);
        assertThat(mSellCardCoin1).isNotEqualTo(mSellCardCoin2);
        mSellCardCoin1.setId(null);
        assertThat(mSellCardCoin1).isNotEqualTo(mSellCardCoin2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MSellCardCoinDTO.class);
        MSellCardCoinDTO mSellCardCoinDTO1 = new MSellCardCoinDTO();
        mSellCardCoinDTO1.setId(1L);
        MSellCardCoinDTO mSellCardCoinDTO2 = new MSellCardCoinDTO();
        assertThat(mSellCardCoinDTO1).isNotEqualTo(mSellCardCoinDTO2);
        mSellCardCoinDTO2.setId(mSellCardCoinDTO1.getId());
        assertThat(mSellCardCoinDTO1).isEqualTo(mSellCardCoinDTO2);
        mSellCardCoinDTO2.setId(2L);
        assertThat(mSellCardCoinDTO1).isNotEqualTo(mSellCardCoinDTO2);
        mSellCardCoinDTO1.setId(null);
        assertThat(mSellCardCoinDTO1).isNotEqualTo(mSellCardCoinDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mSellCardCoinMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mSellCardCoinMapper.fromId(null)).isNull();
    }
}
