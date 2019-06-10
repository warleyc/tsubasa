package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MCharacterBook;
import io.shm.tsubasa.repository.MCharacterBookRepository;
import io.shm.tsubasa.service.MCharacterBookService;
import io.shm.tsubasa.service.dto.MCharacterBookDTO;
import io.shm.tsubasa.service.mapper.MCharacterBookMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MCharacterBookCriteria;
import io.shm.tsubasa.service.MCharacterBookQueryService;

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
 * Integration tests for the {@Link MCharacterBookResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MCharacterBookResourceIT {

    private static final String DEFAULT_CV_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CV_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SERIES = 1;
    private static final Integer UPDATED_SERIES = 2;

    private static final Integer DEFAULT_HEIGHT = 1;
    private static final Integer UPDATED_HEIGHT = 2;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;

    private static final String DEFAULT_INTRODUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INTRODUCTION = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_APPEARED_COMIC = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_APPEARED_COMIC = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_APPEARED_COMIC_LINK = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_APPEARED_COMIC_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_1 = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_1_COMIC = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_1_COMIC = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_1_COMIC_LINK = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_1_COMIC_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_2 = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_2_COMIC = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_2_COMIC = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_2_COMIC_LINK = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_2_COMIC_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_3 = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_3_COMIC = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_3_COMIC = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_3_COMIC_LINK = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_3_COMIC_LINK = "BBBBBBBBBB";

    @Autowired
    private MCharacterBookRepository mCharacterBookRepository;

    @Autowired
    private MCharacterBookMapper mCharacterBookMapper;

    @Autowired
    private MCharacterBookService mCharacterBookService;

    @Autowired
    private MCharacterBookQueryService mCharacterBookQueryService;

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

    private MockMvc restMCharacterBookMockMvc;

    private MCharacterBook mCharacterBook;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MCharacterBookResource mCharacterBookResource = new MCharacterBookResource(mCharacterBookService, mCharacterBookQueryService);
        this.restMCharacterBookMockMvc = MockMvcBuilders.standaloneSetup(mCharacterBookResource)
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
    public static MCharacterBook createEntity(EntityManager em) {
        MCharacterBook mCharacterBook = new MCharacterBook()
            .cvName(DEFAULT_CV_NAME)
            .series(DEFAULT_SERIES)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .introduction(DEFAULT_INTRODUCTION)
            .firstAppearedComic(DEFAULT_FIRST_APPEARED_COMIC)
            .firstAppearedComicLink(DEFAULT_FIRST_APPEARED_COMIC_LINK)
            .skill1(DEFAULT_SKILL_1)
            .skill1Comic(DEFAULT_SKILL_1_COMIC)
            .skill1ComicLink(DEFAULT_SKILL_1_COMIC_LINK)
            .skill2(DEFAULT_SKILL_2)
            .skill2Comic(DEFAULT_SKILL_2_COMIC)
            .skill2ComicLink(DEFAULT_SKILL_2_COMIC_LINK)
            .skill3(DEFAULT_SKILL_3)
            .skill3Comic(DEFAULT_SKILL_3_COMIC)
            .skill3ComicLink(DEFAULT_SKILL_3_COMIC_LINK);
        return mCharacterBook;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MCharacterBook createUpdatedEntity(EntityManager em) {
        MCharacterBook mCharacterBook = new MCharacterBook()
            .cvName(UPDATED_CV_NAME)
            .series(UPDATED_SERIES)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .introduction(UPDATED_INTRODUCTION)
            .firstAppearedComic(UPDATED_FIRST_APPEARED_COMIC)
            .firstAppearedComicLink(UPDATED_FIRST_APPEARED_COMIC_LINK)
            .skill1(UPDATED_SKILL_1)
            .skill1Comic(UPDATED_SKILL_1_COMIC)
            .skill1ComicLink(UPDATED_SKILL_1_COMIC_LINK)
            .skill2(UPDATED_SKILL_2)
            .skill2Comic(UPDATED_SKILL_2_COMIC)
            .skill2ComicLink(UPDATED_SKILL_2_COMIC_LINK)
            .skill3(UPDATED_SKILL_3)
            .skill3Comic(UPDATED_SKILL_3_COMIC)
            .skill3ComicLink(UPDATED_SKILL_3_COMIC_LINK);
        return mCharacterBook;
    }

    @BeforeEach
    public void initTest() {
        mCharacterBook = createEntity(em);
    }

    @Test
    @Transactional
    public void createMCharacterBook() throws Exception {
        int databaseSizeBeforeCreate = mCharacterBookRepository.findAll().size();

        // Create the MCharacterBook
        MCharacterBookDTO mCharacterBookDTO = mCharacterBookMapper.toDto(mCharacterBook);
        restMCharacterBookMockMvc.perform(post("/api/m-character-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterBookDTO)))
            .andExpect(status().isCreated());

        // Validate the MCharacterBook in the database
        List<MCharacterBook> mCharacterBookList = mCharacterBookRepository.findAll();
        assertThat(mCharacterBookList).hasSize(databaseSizeBeforeCreate + 1);
        MCharacterBook testMCharacterBook = mCharacterBookList.get(mCharacterBookList.size() - 1);
        assertThat(testMCharacterBook.getCvName()).isEqualTo(DEFAULT_CV_NAME);
        assertThat(testMCharacterBook.getSeries()).isEqualTo(DEFAULT_SERIES);
        assertThat(testMCharacterBook.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testMCharacterBook.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testMCharacterBook.getIntroduction()).isEqualTo(DEFAULT_INTRODUCTION);
        assertThat(testMCharacterBook.getFirstAppearedComic()).isEqualTo(DEFAULT_FIRST_APPEARED_COMIC);
        assertThat(testMCharacterBook.getFirstAppearedComicLink()).isEqualTo(DEFAULT_FIRST_APPEARED_COMIC_LINK);
        assertThat(testMCharacterBook.getSkill1()).isEqualTo(DEFAULT_SKILL_1);
        assertThat(testMCharacterBook.getSkill1Comic()).isEqualTo(DEFAULT_SKILL_1_COMIC);
        assertThat(testMCharacterBook.getSkill1ComicLink()).isEqualTo(DEFAULT_SKILL_1_COMIC_LINK);
        assertThat(testMCharacterBook.getSkill2()).isEqualTo(DEFAULT_SKILL_2);
        assertThat(testMCharacterBook.getSkill2Comic()).isEqualTo(DEFAULT_SKILL_2_COMIC);
        assertThat(testMCharacterBook.getSkill2ComicLink()).isEqualTo(DEFAULT_SKILL_2_COMIC_LINK);
        assertThat(testMCharacterBook.getSkill3()).isEqualTo(DEFAULT_SKILL_3);
        assertThat(testMCharacterBook.getSkill3Comic()).isEqualTo(DEFAULT_SKILL_3_COMIC);
        assertThat(testMCharacterBook.getSkill3ComicLink()).isEqualTo(DEFAULT_SKILL_3_COMIC_LINK);
    }

    @Test
    @Transactional
    public void createMCharacterBookWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mCharacterBookRepository.findAll().size();

        // Create the MCharacterBook with an existing ID
        mCharacterBook.setId(1L);
        MCharacterBookDTO mCharacterBookDTO = mCharacterBookMapper.toDto(mCharacterBook);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMCharacterBookMockMvc.perform(post("/api/m-character-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterBookDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCharacterBook in the database
        List<MCharacterBook> mCharacterBookList = mCharacterBookRepository.findAll();
        assertThat(mCharacterBookList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSeriesIsRequired() throws Exception {
        int databaseSizeBeforeTest = mCharacterBookRepository.findAll().size();
        // set the field null
        mCharacterBook.setSeries(null);

        // Create the MCharacterBook, which fails.
        MCharacterBookDTO mCharacterBookDTO = mCharacterBookMapper.toDto(mCharacterBook);

        restMCharacterBookMockMvc.perform(post("/api/m-character-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterBookDTO)))
            .andExpect(status().isBadRequest());

        List<MCharacterBook> mCharacterBookList = mCharacterBookRepository.findAll();
        assertThat(mCharacterBookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooks() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList
        restMCharacterBookMockMvc.perform(get("/api/m-character-books?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCharacterBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].cvName").value(hasItem(DEFAULT_CV_NAME.toString())))
            .andExpect(jsonPath("$.[*].series").value(hasItem(DEFAULT_SERIES)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].introduction").value(hasItem(DEFAULT_INTRODUCTION.toString())))
            .andExpect(jsonPath("$.[*].firstAppearedComic").value(hasItem(DEFAULT_FIRST_APPEARED_COMIC.toString())))
            .andExpect(jsonPath("$.[*].firstAppearedComicLink").value(hasItem(DEFAULT_FIRST_APPEARED_COMIC_LINK.toString())))
            .andExpect(jsonPath("$.[*].skill1").value(hasItem(DEFAULT_SKILL_1.toString())))
            .andExpect(jsonPath("$.[*].skill1Comic").value(hasItem(DEFAULT_SKILL_1_COMIC.toString())))
            .andExpect(jsonPath("$.[*].skill1ComicLink").value(hasItem(DEFAULT_SKILL_1_COMIC_LINK.toString())))
            .andExpect(jsonPath("$.[*].skill2").value(hasItem(DEFAULT_SKILL_2.toString())))
            .andExpect(jsonPath("$.[*].skill2Comic").value(hasItem(DEFAULT_SKILL_2_COMIC.toString())))
            .andExpect(jsonPath("$.[*].skill2ComicLink").value(hasItem(DEFAULT_SKILL_2_COMIC_LINK.toString())))
            .andExpect(jsonPath("$.[*].skill3").value(hasItem(DEFAULT_SKILL_3.toString())))
            .andExpect(jsonPath("$.[*].skill3Comic").value(hasItem(DEFAULT_SKILL_3_COMIC.toString())))
            .andExpect(jsonPath("$.[*].skill3ComicLink").value(hasItem(DEFAULT_SKILL_3_COMIC_LINK.toString())));
    }
    
    @Test
    @Transactional
    public void getMCharacterBook() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get the mCharacterBook
        restMCharacterBookMockMvc.perform(get("/api/m-character-books/{id}", mCharacterBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mCharacterBook.getId().intValue()))
            .andExpect(jsonPath("$.cvName").value(DEFAULT_CV_NAME.toString()))
            .andExpect(jsonPath("$.series").value(DEFAULT_SERIES))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.introduction").value(DEFAULT_INTRODUCTION.toString()))
            .andExpect(jsonPath("$.firstAppearedComic").value(DEFAULT_FIRST_APPEARED_COMIC.toString()))
            .andExpect(jsonPath("$.firstAppearedComicLink").value(DEFAULT_FIRST_APPEARED_COMIC_LINK.toString()))
            .andExpect(jsonPath("$.skill1").value(DEFAULT_SKILL_1.toString()))
            .andExpect(jsonPath("$.skill1Comic").value(DEFAULT_SKILL_1_COMIC.toString()))
            .andExpect(jsonPath("$.skill1ComicLink").value(DEFAULT_SKILL_1_COMIC_LINK.toString()))
            .andExpect(jsonPath("$.skill2").value(DEFAULT_SKILL_2.toString()))
            .andExpect(jsonPath("$.skill2Comic").value(DEFAULT_SKILL_2_COMIC.toString()))
            .andExpect(jsonPath("$.skill2ComicLink").value(DEFAULT_SKILL_2_COMIC_LINK.toString()))
            .andExpect(jsonPath("$.skill3").value(DEFAULT_SKILL_3.toString()))
            .andExpect(jsonPath("$.skill3Comic").value(DEFAULT_SKILL_3_COMIC.toString()))
            .andExpect(jsonPath("$.skill3ComicLink").value(DEFAULT_SKILL_3_COMIC_LINK.toString()));
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksBySeriesIsEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where series equals to DEFAULT_SERIES
        defaultMCharacterBookShouldBeFound("series.equals=" + DEFAULT_SERIES);

        // Get all the mCharacterBookList where series equals to UPDATED_SERIES
        defaultMCharacterBookShouldNotBeFound("series.equals=" + UPDATED_SERIES);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksBySeriesIsInShouldWork() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where series in DEFAULT_SERIES or UPDATED_SERIES
        defaultMCharacterBookShouldBeFound("series.in=" + DEFAULT_SERIES + "," + UPDATED_SERIES);

        // Get all the mCharacterBookList where series equals to UPDATED_SERIES
        defaultMCharacterBookShouldNotBeFound("series.in=" + UPDATED_SERIES);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksBySeriesIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where series is not null
        defaultMCharacterBookShouldBeFound("series.specified=true");

        // Get all the mCharacterBookList where series is null
        defaultMCharacterBookShouldNotBeFound("series.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksBySeriesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where series greater than or equals to DEFAULT_SERIES
        defaultMCharacterBookShouldBeFound("series.greaterOrEqualThan=" + DEFAULT_SERIES);

        // Get all the mCharacterBookList where series greater than or equals to UPDATED_SERIES
        defaultMCharacterBookShouldNotBeFound("series.greaterOrEqualThan=" + UPDATED_SERIES);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksBySeriesIsLessThanSomething() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where series less than or equals to DEFAULT_SERIES
        defaultMCharacterBookShouldNotBeFound("series.lessThan=" + DEFAULT_SERIES);

        // Get all the mCharacterBookList where series less than or equals to UPDATED_SERIES
        defaultMCharacterBookShouldBeFound("series.lessThan=" + UPDATED_SERIES);
    }


    @Test
    @Transactional
    public void getAllMCharacterBooksByHeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where height equals to DEFAULT_HEIGHT
        defaultMCharacterBookShouldBeFound("height.equals=" + DEFAULT_HEIGHT);

        // Get all the mCharacterBookList where height equals to UPDATED_HEIGHT
        defaultMCharacterBookShouldNotBeFound("height.equals=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksByHeightIsInShouldWork() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where height in DEFAULT_HEIGHT or UPDATED_HEIGHT
        defaultMCharacterBookShouldBeFound("height.in=" + DEFAULT_HEIGHT + "," + UPDATED_HEIGHT);

        // Get all the mCharacterBookList where height equals to UPDATED_HEIGHT
        defaultMCharacterBookShouldNotBeFound("height.in=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksByHeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where height is not null
        defaultMCharacterBookShouldBeFound("height.specified=true");

        // Get all the mCharacterBookList where height is null
        defaultMCharacterBookShouldNotBeFound("height.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksByHeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where height greater than or equals to DEFAULT_HEIGHT
        defaultMCharacterBookShouldBeFound("height.greaterOrEqualThan=" + DEFAULT_HEIGHT);

        // Get all the mCharacterBookList where height greater than or equals to UPDATED_HEIGHT
        defaultMCharacterBookShouldNotBeFound("height.greaterOrEqualThan=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksByHeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where height less than or equals to DEFAULT_HEIGHT
        defaultMCharacterBookShouldNotBeFound("height.lessThan=" + DEFAULT_HEIGHT);

        // Get all the mCharacterBookList where height less than or equals to UPDATED_HEIGHT
        defaultMCharacterBookShouldBeFound("height.lessThan=" + UPDATED_HEIGHT);
    }


    @Test
    @Transactional
    public void getAllMCharacterBooksByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where weight equals to DEFAULT_WEIGHT
        defaultMCharacterBookShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the mCharacterBookList where weight equals to UPDATED_WEIGHT
        defaultMCharacterBookShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultMCharacterBookShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the mCharacterBookList where weight equals to UPDATED_WEIGHT
        defaultMCharacterBookShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where weight is not null
        defaultMCharacterBookShouldBeFound("weight.specified=true");

        // Get all the mCharacterBookList where weight is null
        defaultMCharacterBookShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksByWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where weight greater than or equals to DEFAULT_WEIGHT
        defaultMCharacterBookShouldBeFound("weight.greaterOrEqualThan=" + DEFAULT_WEIGHT);

        // Get all the mCharacterBookList where weight greater than or equals to UPDATED_WEIGHT
        defaultMCharacterBookShouldNotBeFound("weight.greaterOrEqualThan=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllMCharacterBooksByWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        // Get all the mCharacterBookList where weight less than or equals to DEFAULT_WEIGHT
        defaultMCharacterBookShouldNotBeFound("weight.lessThan=" + DEFAULT_WEIGHT);

        // Get all the mCharacterBookList where weight less than or equals to UPDATED_WEIGHT
        defaultMCharacterBookShouldBeFound("weight.lessThan=" + UPDATED_WEIGHT);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMCharacterBookShouldBeFound(String filter) throws Exception {
        restMCharacterBookMockMvc.perform(get("/api/m-character-books?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mCharacterBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].cvName").value(hasItem(DEFAULT_CV_NAME.toString())))
            .andExpect(jsonPath("$.[*].series").value(hasItem(DEFAULT_SERIES)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].introduction").value(hasItem(DEFAULT_INTRODUCTION.toString())))
            .andExpect(jsonPath("$.[*].firstAppearedComic").value(hasItem(DEFAULT_FIRST_APPEARED_COMIC.toString())))
            .andExpect(jsonPath("$.[*].firstAppearedComicLink").value(hasItem(DEFAULT_FIRST_APPEARED_COMIC_LINK.toString())))
            .andExpect(jsonPath("$.[*].skill1").value(hasItem(DEFAULT_SKILL_1.toString())))
            .andExpect(jsonPath("$.[*].skill1Comic").value(hasItem(DEFAULT_SKILL_1_COMIC.toString())))
            .andExpect(jsonPath("$.[*].skill1ComicLink").value(hasItem(DEFAULT_SKILL_1_COMIC_LINK.toString())))
            .andExpect(jsonPath("$.[*].skill2").value(hasItem(DEFAULT_SKILL_2.toString())))
            .andExpect(jsonPath("$.[*].skill2Comic").value(hasItem(DEFAULT_SKILL_2_COMIC.toString())))
            .andExpect(jsonPath("$.[*].skill2ComicLink").value(hasItem(DEFAULT_SKILL_2_COMIC_LINK.toString())))
            .andExpect(jsonPath("$.[*].skill3").value(hasItem(DEFAULT_SKILL_3.toString())))
            .andExpect(jsonPath("$.[*].skill3Comic").value(hasItem(DEFAULT_SKILL_3_COMIC.toString())))
            .andExpect(jsonPath("$.[*].skill3ComicLink").value(hasItem(DEFAULT_SKILL_3_COMIC_LINK.toString())));

        // Check, that the count call also returns 1
        restMCharacterBookMockMvc.perform(get("/api/m-character-books/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMCharacterBookShouldNotBeFound(String filter) throws Exception {
        restMCharacterBookMockMvc.perform(get("/api/m-character-books?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMCharacterBookMockMvc.perform(get("/api/m-character-books/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMCharacterBook() throws Exception {
        // Get the mCharacterBook
        restMCharacterBookMockMvc.perform(get("/api/m-character-books/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMCharacterBook() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        int databaseSizeBeforeUpdate = mCharacterBookRepository.findAll().size();

        // Update the mCharacterBook
        MCharacterBook updatedMCharacterBook = mCharacterBookRepository.findById(mCharacterBook.getId()).get();
        // Disconnect from session so that the updates on updatedMCharacterBook are not directly saved in db
        em.detach(updatedMCharacterBook);
        updatedMCharacterBook
            .cvName(UPDATED_CV_NAME)
            .series(UPDATED_SERIES)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .introduction(UPDATED_INTRODUCTION)
            .firstAppearedComic(UPDATED_FIRST_APPEARED_COMIC)
            .firstAppearedComicLink(UPDATED_FIRST_APPEARED_COMIC_LINK)
            .skill1(UPDATED_SKILL_1)
            .skill1Comic(UPDATED_SKILL_1_COMIC)
            .skill1ComicLink(UPDATED_SKILL_1_COMIC_LINK)
            .skill2(UPDATED_SKILL_2)
            .skill2Comic(UPDATED_SKILL_2_COMIC)
            .skill2ComicLink(UPDATED_SKILL_2_COMIC_LINK)
            .skill3(UPDATED_SKILL_3)
            .skill3Comic(UPDATED_SKILL_3_COMIC)
            .skill3ComicLink(UPDATED_SKILL_3_COMIC_LINK);
        MCharacterBookDTO mCharacterBookDTO = mCharacterBookMapper.toDto(updatedMCharacterBook);

        restMCharacterBookMockMvc.perform(put("/api/m-character-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterBookDTO)))
            .andExpect(status().isOk());

        // Validate the MCharacterBook in the database
        List<MCharacterBook> mCharacterBookList = mCharacterBookRepository.findAll();
        assertThat(mCharacterBookList).hasSize(databaseSizeBeforeUpdate);
        MCharacterBook testMCharacterBook = mCharacterBookList.get(mCharacterBookList.size() - 1);
        assertThat(testMCharacterBook.getCvName()).isEqualTo(UPDATED_CV_NAME);
        assertThat(testMCharacterBook.getSeries()).isEqualTo(UPDATED_SERIES);
        assertThat(testMCharacterBook.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testMCharacterBook.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testMCharacterBook.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testMCharacterBook.getFirstAppearedComic()).isEqualTo(UPDATED_FIRST_APPEARED_COMIC);
        assertThat(testMCharacterBook.getFirstAppearedComicLink()).isEqualTo(UPDATED_FIRST_APPEARED_COMIC_LINK);
        assertThat(testMCharacterBook.getSkill1()).isEqualTo(UPDATED_SKILL_1);
        assertThat(testMCharacterBook.getSkill1Comic()).isEqualTo(UPDATED_SKILL_1_COMIC);
        assertThat(testMCharacterBook.getSkill1ComicLink()).isEqualTo(UPDATED_SKILL_1_COMIC_LINK);
        assertThat(testMCharacterBook.getSkill2()).isEqualTo(UPDATED_SKILL_2);
        assertThat(testMCharacterBook.getSkill2Comic()).isEqualTo(UPDATED_SKILL_2_COMIC);
        assertThat(testMCharacterBook.getSkill2ComicLink()).isEqualTo(UPDATED_SKILL_2_COMIC_LINK);
        assertThat(testMCharacterBook.getSkill3()).isEqualTo(UPDATED_SKILL_3);
        assertThat(testMCharacterBook.getSkill3Comic()).isEqualTo(UPDATED_SKILL_3_COMIC);
        assertThat(testMCharacterBook.getSkill3ComicLink()).isEqualTo(UPDATED_SKILL_3_COMIC_LINK);
    }

    @Test
    @Transactional
    public void updateNonExistingMCharacterBook() throws Exception {
        int databaseSizeBeforeUpdate = mCharacterBookRepository.findAll().size();

        // Create the MCharacterBook
        MCharacterBookDTO mCharacterBookDTO = mCharacterBookMapper.toDto(mCharacterBook);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMCharacterBookMockMvc.perform(put("/api/m-character-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mCharacterBookDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MCharacterBook in the database
        List<MCharacterBook> mCharacterBookList = mCharacterBookRepository.findAll();
        assertThat(mCharacterBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMCharacterBook() throws Exception {
        // Initialize the database
        mCharacterBookRepository.saveAndFlush(mCharacterBook);

        int databaseSizeBeforeDelete = mCharacterBookRepository.findAll().size();

        // Delete the mCharacterBook
        restMCharacterBookMockMvc.perform(delete("/api/m-character-books/{id}", mCharacterBook.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MCharacterBook> mCharacterBookList = mCharacterBookRepository.findAll();
        assertThat(mCharacterBookList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCharacterBook.class);
        MCharacterBook mCharacterBook1 = new MCharacterBook();
        mCharacterBook1.setId(1L);
        MCharacterBook mCharacterBook2 = new MCharacterBook();
        mCharacterBook2.setId(mCharacterBook1.getId());
        assertThat(mCharacterBook1).isEqualTo(mCharacterBook2);
        mCharacterBook2.setId(2L);
        assertThat(mCharacterBook1).isNotEqualTo(mCharacterBook2);
        mCharacterBook1.setId(null);
        assertThat(mCharacterBook1).isNotEqualTo(mCharacterBook2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MCharacterBookDTO.class);
        MCharacterBookDTO mCharacterBookDTO1 = new MCharacterBookDTO();
        mCharacterBookDTO1.setId(1L);
        MCharacterBookDTO mCharacterBookDTO2 = new MCharacterBookDTO();
        assertThat(mCharacterBookDTO1).isNotEqualTo(mCharacterBookDTO2);
        mCharacterBookDTO2.setId(mCharacterBookDTO1.getId());
        assertThat(mCharacterBookDTO1).isEqualTo(mCharacterBookDTO2);
        mCharacterBookDTO2.setId(2L);
        assertThat(mCharacterBookDTO1).isNotEqualTo(mCharacterBookDTO2);
        mCharacterBookDTO1.setId(null);
        assertThat(mCharacterBookDTO1).isNotEqualTo(mCharacterBookDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mCharacterBookMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mCharacterBookMapper.fromId(null)).isNull();
    }
}
