package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.TsubasaApp;
import io.shm.tsubasa.domain.MTargetPlayableCardGroup;
import io.shm.tsubasa.domain.MPlayableCard;
import io.shm.tsubasa.repository.MTargetPlayableCardGroupRepository;
import io.shm.tsubasa.service.MTargetPlayableCardGroupService;
import io.shm.tsubasa.service.dto.MTargetPlayableCardGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetPlayableCardGroupMapper;
import io.shm.tsubasa.web.rest.errors.ExceptionTranslator;
import io.shm.tsubasa.service.dto.MTargetPlayableCardGroupCriteria;
import io.shm.tsubasa.service.MTargetPlayableCardGroupQueryService;

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
 * Integration tests for the {@Link MTargetPlayableCardGroupResource} REST controller.
 */
@SpringBootTest(classes = TsubasaApp.class)
public class MTargetPlayableCardGroupResourceIT {

    private static final Integer DEFAULT_GROUP_ID = 1;
    private static final Integer UPDATED_GROUP_ID = 2;

    private static final Integer DEFAULT_CARD_ID = 1;
    private static final Integer UPDATED_CARD_ID = 2;

    private static final Integer DEFAULT_IS_SHOW_THUMBNAIL = 1;
    private static final Integer UPDATED_IS_SHOW_THUMBNAIL = 2;

    @Autowired
    private MTargetPlayableCardGroupRepository mTargetPlayableCardGroupRepository;

    @Autowired
    private MTargetPlayableCardGroupMapper mTargetPlayableCardGroupMapper;

    @Autowired
    private MTargetPlayableCardGroupService mTargetPlayableCardGroupService;

    @Autowired
    private MTargetPlayableCardGroupQueryService mTargetPlayableCardGroupQueryService;

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

    private MockMvc restMTargetPlayableCardGroupMockMvc;

    private MTargetPlayableCardGroup mTargetPlayableCardGroup;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MTargetPlayableCardGroupResource mTargetPlayableCardGroupResource = new MTargetPlayableCardGroupResource(mTargetPlayableCardGroupService, mTargetPlayableCardGroupQueryService);
        this.restMTargetPlayableCardGroupMockMvc = MockMvcBuilders.standaloneSetup(mTargetPlayableCardGroupResource)
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
    public static MTargetPlayableCardGroup createEntity(EntityManager em) {
        MTargetPlayableCardGroup mTargetPlayableCardGroup = new MTargetPlayableCardGroup()
            .groupId(DEFAULT_GROUP_ID)
            .cardId(DEFAULT_CARD_ID)
            .isShowThumbnail(DEFAULT_IS_SHOW_THUMBNAIL);
        // Add required entity
        MPlayableCard mPlayableCard;
        if (TestUtil.findAll(em, MPlayableCard.class).isEmpty()) {
            mPlayableCard = MPlayableCardResourceIT.createEntity(em);
            em.persist(mPlayableCard);
            em.flush();
        } else {
            mPlayableCard = TestUtil.findAll(em, MPlayableCard.class).get(0);
        }
        mTargetPlayableCardGroup.setId(mPlayableCard);
        return mTargetPlayableCardGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MTargetPlayableCardGroup createUpdatedEntity(EntityManager em) {
        MTargetPlayableCardGroup mTargetPlayableCardGroup = new MTargetPlayableCardGroup()
            .groupId(UPDATED_GROUP_ID)
            .cardId(UPDATED_CARD_ID)
            .isShowThumbnail(UPDATED_IS_SHOW_THUMBNAIL);
        // Add required entity
        MPlayableCard mPlayableCard;
        if (TestUtil.findAll(em, MPlayableCard.class).isEmpty()) {
            mPlayableCard = MPlayableCardResourceIT.createUpdatedEntity(em);
            em.persist(mPlayableCard);
            em.flush();
        } else {
            mPlayableCard = TestUtil.findAll(em, MPlayableCard.class).get(0);
        }
        mTargetPlayableCardGroup.setId(mPlayableCard);
        return mTargetPlayableCardGroup;
    }

    @BeforeEach
    public void initTest() {
        mTargetPlayableCardGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMTargetPlayableCardGroup() throws Exception {
        int databaseSizeBeforeCreate = mTargetPlayableCardGroupRepository.findAll().size();

        // Create the MTargetPlayableCardGroup
        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO = mTargetPlayableCardGroupMapper.toDto(mTargetPlayableCardGroup);
        restMTargetPlayableCardGroupMockMvc.perform(post("/api/m-target-playable-card-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetPlayableCardGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the MTargetPlayableCardGroup in the database
        List<MTargetPlayableCardGroup> mTargetPlayableCardGroupList = mTargetPlayableCardGroupRepository.findAll();
        assertThat(mTargetPlayableCardGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MTargetPlayableCardGroup testMTargetPlayableCardGroup = mTargetPlayableCardGroupList.get(mTargetPlayableCardGroupList.size() - 1);
        assertThat(testMTargetPlayableCardGroup.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testMTargetPlayableCardGroup.getCardId()).isEqualTo(DEFAULT_CARD_ID);
        assertThat(testMTargetPlayableCardGroup.getIsShowThumbnail()).isEqualTo(DEFAULT_IS_SHOW_THUMBNAIL);
    }

    @Test
    @Transactional
    public void createMTargetPlayableCardGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mTargetPlayableCardGroupRepository.findAll().size();

        // Create the MTargetPlayableCardGroup with an existing ID
        mTargetPlayableCardGroup.setId(1L);
        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO = mTargetPlayableCardGroupMapper.toDto(mTargetPlayableCardGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMTargetPlayableCardGroupMockMvc.perform(post("/api/m-target-playable-card-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetPlayableCardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetPlayableCardGroup in the database
        List<MTargetPlayableCardGroup> mTargetPlayableCardGroupList = mTargetPlayableCardGroupRepository.findAll();
        assertThat(mTargetPlayableCardGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetPlayableCardGroupRepository.findAll().size();
        // set the field null
        mTargetPlayableCardGroup.setGroupId(null);

        // Create the MTargetPlayableCardGroup, which fails.
        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO = mTargetPlayableCardGroupMapper.toDto(mTargetPlayableCardGroup);

        restMTargetPlayableCardGroupMockMvc.perform(post("/api/m-target-playable-card-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetPlayableCardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetPlayableCardGroup> mTargetPlayableCardGroupList = mTargetPlayableCardGroupRepository.findAll();
        assertThat(mTargetPlayableCardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCardIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetPlayableCardGroupRepository.findAll().size();
        // set the field null
        mTargetPlayableCardGroup.setCardId(null);

        // Create the MTargetPlayableCardGroup, which fails.
        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO = mTargetPlayableCardGroupMapper.toDto(mTargetPlayableCardGroup);

        restMTargetPlayableCardGroupMockMvc.perform(post("/api/m-target-playable-card-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetPlayableCardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetPlayableCardGroup> mTargetPlayableCardGroupList = mTargetPlayableCardGroupRepository.findAll();
        assertThat(mTargetPlayableCardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsShowThumbnailIsRequired() throws Exception {
        int databaseSizeBeforeTest = mTargetPlayableCardGroupRepository.findAll().size();
        // set the field null
        mTargetPlayableCardGroup.setIsShowThumbnail(null);

        // Create the MTargetPlayableCardGroup, which fails.
        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO = mTargetPlayableCardGroupMapper.toDto(mTargetPlayableCardGroup);

        restMTargetPlayableCardGroupMockMvc.perform(post("/api/m-target-playable-card-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetPlayableCardGroupDTO)))
            .andExpect(status().isBadRequest());

        List<MTargetPlayableCardGroup> mTargetPlayableCardGroupList = mTargetPlayableCardGroupRepository.findAll();
        assertThat(mTargetPlayableCardGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroups() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList
        restMTargetPlayableCardGroupMockMvc.perform(get("/api/m-target-playable-card-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetPlayableCardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].cardId").value(hasItem(DEFAULT_CARD_ID)))
            .andExpect(jsonPath("$.[*].isShowThumbnail").value(hasItem(DEFAULT_IS_SHOW_THUMBNAIL)));
    }
    
    @Test
    @Transactional
    public void getMTargetPlayableCardGroup() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get the mTargetPlayableCardGroup
        restMTargetPlayableCardGroupMockMvc.perform(get("/api/m-target-playable-card-groups/{id}", mTargetPlayableCardGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mTargetPlayableCardGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.cardId").value(DEFAULT_CARD_ID))
            .andExpect(jsonPath("$.isShowThumbnail").value(DEFAULT_IS_SHOW_THUMBNAIL));
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByGroupIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where groupId equals to DEFAULT_GROUP_ID
        defaultMTargetPlayableCardGroupShouldBeFound("groupId.equals=" + DEFAULT_GROUP_ID);

        // Get all the mTargetPlayableCardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetPlayableCardGroupShouldNotBeFound("groupId.equals=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByGroupIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where groupId in DEFAULT_GROUP_ID or UPDATED_GROUP_ID
        defaultMTargetPlayableCardGroupShouldBeFound("groupId.in=" + DEFAULT_GROUP_ID + "," + UPDATED_GROUP_ID);

        // Get all the mTargetPlayableCardGroupList where groupId equals to UPDATED_GROUP_ID
        defaultMTargetPlayableCardGroupShouldNotBeFound("groupId.in=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByGroupIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where groupId is not null
        defaultMTargetPlayableCardGroupShouldBeFound("groupId.specified=true");

        // Get all the mTargetPlayableCardGroupList where groupId is null
        defaultMTargetPlayableCardGroupShouldNotBeFound("groupId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByGroupIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where groupId greater than or equals to DEFAULT_GROUP_ID
        defaultMTargetPlayableCardGroupShouldBeFound("groupId.greaterOrEqualThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetPlayableCardGroupList where groupId greater than or equals to UPDATED_GROUP_ID
        defaultMTargetPlayableCardGroupShouldNotBeFound("groupId.greaterOrEqualThan=" + UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByGroupIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where groupId less than or equals to DEFAULT_GROUP_ID
        defaultMTargetPlayableCardGroupShouldNotBeFound("groupId.lessThan=" + DEFAULT_GROUP_ID);

        // Get all the mTargetPlayableCardGroupList where groupId less than or equals to UPDATED_GROUP_ID
        defaultMTargetPlayableCardGroupShouldBeFound("groupId.lessThan=" + UPDATED_GROUP_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByCardIdIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where cardId equals to DEFAULT_CARD_ID
        defaultMTargetPlayableCardGroupShouldBeFound("cardId.equals=" + DEFAULT_CARD_ID);

        // Get all the mTargetPlayableCardGroupList where cardId equals to UPDATED_CARD_ID
        defaultMTargetPlayableCardGroupShouldNotBeFound("cardId.equals=" + UPDATED_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByCardIdIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where cardId in DEFAULT_CARD_ID or UPDATED_CARD_ID
        defaultMTargetPlayableCardGroupShouldBeFound("cardId.in=" + DEFAULT_CARD_ID + "," + UPDATED_CARD_ID);

        // Get all the mTargetPlayableCardGroupList where cardId equals to UPDATED_CARD_ID
        defaultMTargetPlayableCardGroupShouldNotBeFound("cardId.in=" + UPDATED_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByCardIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where cardId is not null
        defaultMTargetPlayableCardGroupShouldBeFound("cardId.specified=true");

        // Get all the mTargetPlayableCardGroupList where cardId is null
        defaultMTargetPlayableCardGroupShouldNotBeFound("cardId.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByCardIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where cardId greater than or equals to DEFAULT_CARD_ID
        defaultMTargetPlayableCardGroupShouldBeFound("cardId.greaterOrEqualThan=" + DEFAULT_CARD_ID);

        // Get all the mTargetPlayableCardGroupList where cardId greater than or equals to UPDATED_CARD_ID
        defaultMTargetPlayableCardGroupShouldNotBeFound("cardId.greaterOrEqualThan=" + UPDATED_CARD_ID);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByCardIdIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where cardId less than or equals to DEFAULT_CARD_ID
        defaultMTargetPlayableCardGroupShouldNotBeFound("cardId.lessThan=" + DEFAULT_CARD_ID);

        // Get all the mTargetPlayableCardGroupList where cardId less than or equals to UPDATED_CARD_ID
        defaultMTargetPlayableCardGroupShouldBeFound("cardId.lessThan=" + UPDATED_CARD_ID);
    }


    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByIsShowThumbnailIsEqualToSomething() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail equals to DEFAULT_IS_SHOW_THUMBNAIL
        defaultMTargetPlayableCardGroupShouldBeFound("isShowThumbnail.equals=" + DEFAULT_IS_SHOW_THUMBNAIL);

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail equals to UPDATED_IS_SHOW_THUMBNAIL
        defaultMTargetPlayableCardGroupShouldNotBeFound("isShowThumbnail.equals=" + UPDATED_IS_SHOW_THUMBNAIL);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByIsShowThumbnailIsInShouldWork() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail in DEFAULT_IS_SHOW_THUMBNAIL or UPDATED_IS_SHOW_THUMBNAIL
        defaultMTargetPlayableCardGroupShouldBeFound("isShowThumbnail.in=" + DEFAULT_IS_SHOW_THUMBNAIL + "," + UPDATED_IS_SHOW_THUMBNAIL);

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail equals to UPDATED_IS_SHOW_THUMBNAIL
        defaultMTargetPlayableCardGroupShouldNotBeFound("isShowThumbnail.in=" + UPDATED_IS_SHOW_THUMBNAIL);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByIsShowThumbnailIsNullOrNotNull() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail is not null
        defaultMTargetPlayableCardGroupShouldBeFound("isShowThumbnail.specified=true");

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail is null
        defaultMTargetPlayableCardGroupShouldNotBeFound("isShowThumbnail.specified=false");
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByIsShowThumbnailIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail greater than or equals to DEFAULT_IS_SHOW_THUMBNAIL
        defaultMTargetPlayableCardGroupShouldBeFound("isShowThumbnail.greaterOrEqualThan=" + DEFAULT_IS_SHOW_THUMBNAIL);

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail greater than or equals to UPDATED_IS_SHOW_THUMBNAIL
        defaultMTargetPlayableCardGroupShouldNotBeFound("isShowThumbnail.greaterOrEqualThan=" + UPDATED_IS_SHOW_THUMBNAIL);
    }

    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByIsShowThumbnailIsLessThanSomething() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail less than or equals to DEFAULT_IS_SHOW_THUMBNAIL
        defaultMTargetPlayableCardGroupShouldNotBeFound("isShowThumbnail.lessThan=" + DEFAULT_IS_SHOW_THUMBNAIL);

        // Get all the mTargetPlayableCardGroupList where isShowThumbnail less than or equals to UPDATED_IS_SHOW_THUMBNAIL
        defaultMTargetPlayableCardGroupShouldBeFound("isShowThumbnail.lessThan=" + UPDATED_IS_SHOW_THUMBNAIL);
    }


    @Test
    @Transactional
    public void getAllMTargetPlayableCardGroupsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        MPlayableCard id = mTargetPlayableCardGroup.getId();
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);
        Long idId = id.getId();

        // Get all the mTargetPlayableCardGroupList where id equals to idId
        defaultMTargetPlayableCardGroupShouldBeFound("idId.equals=" + idId);

        // Get all the mTargetPlayableCardGroupList where id equals to idId + 1
        defaultMTargetPlayableCardGroupShouldNotBeFound("idId.equals=" + (idId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMTargetPlayableCardGroupShouldBeFound(String filter) throws Exception {
        restMTargetPlayableCardGroupMockMvc.perform(get("/api/m-target-playable-card-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mTargetPlayableCardGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].cardId").value(hasItem(DEFAULT_CARD_ID)))
            .andExpect(jsonPath("$.[*].isShowThumbnail").value(hasItem(DEFAULT_IS_SHOW_THUMBNAIL)));

        // Check, that the count call also returns 1
        restMTargetPlayableCardGroupMockMvc.perform(get("/api/m-target-playable-card-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMTargetPlayableCardGroupShouldNotBeFound(String filter) throws Exception {
        restMTargetPlayableCardGroupMockMvc.perform(get("/api/m-target-playable-card-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMTargetPlayableCardGroupMockMvc.perform(get("/api/m-target-playable-card-groups/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMTargetPlayableCardGroup() throws Exception {
        // Get the mTargetPlayableCardGroup
        restMTargetPlayableCardGroupMockMvc.perform(get("/api/m-target-playable-card-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMTargetPlayableCardGroup() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        int databaseSizeBeforeUpdate = mTargetPlayableCardGroupRepository.findAll().size();

        // Update the mTargetPlayableCardGroup
        MTargetPlayableCardGroup updatedMTargetPlayableCardGroup = mTargetPlayableCardGroupRepository.findById(mTargetPlayableCardGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMTargetPlayableCardGroup are not directly saved in db
        em.detach(updatedMTargetPlayableCardGroup);
        updatedMTargetPlayableCardGroup
            .groupId(UPDATED_GROUP_ID)
            .cardId(UPDATED_CARD_ID)
            .isShowThumbnail(UPDATED_IS_SHOW_THUMBNAIL);
        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO = mTargetPlayableCardGroupMapper.toDto(updatedMTargetPlayableCardGroup);

        restMTargetPlayableCardGroupMockMvc.perform(put("/api/m-target-playable-card-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetPlayableCardGroupDTO)))
            .andExpect(status().isOk());

        // Validate the MTargetPlayableCardGroup in the database
        List<MTargetPlayableCardGroup> mTargetPlayableCardGroupList = mTargetPlayableCardGroupRepository.findAll();
        assertThat(mTargetPlayableCardGroupList).hasSize(databaseSizeBeforeUpdate);
        MTargetPlayableCardGroup testMTargetPlayableCardGroup = mTargetPlayableCardGroupList.get(mTargetPlayableCardGroupList.size() - 1);
        assertThat(testMTargetPlayableCardGroup.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testMTargetPlayableCardGroup.getCardId()).isEqualTo(UPDATED_CARD_ID);
        assertThat(testMTargetPlayableCardGroup.getIsShowThumbnail()).isEqualTo(UPDATED_IS_SHOW_THUMBNAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingMTargetPlayableCardGroup() throws Exception {
        int databaseSizeBeforeUpdate = mTargetPlayableCardGroupRepository.findAll().size();

        // Create the MTargetPlayableCardGroup
        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO = mTargetPlayableCardGroupMapper.toDto(mTargetPlayableCardGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMTargetPlayableCardGroupMockMvc.perform(put("/api/m-target-playable-card-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mTargetPlayableCardGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MTargetPlayableCardGroup in the database
        List<MTargetPlayableCardGroup> mTargetPlayableCardGroupList = mTargetPlayableCardGroupRepository.findAll();
        assertThat(mTargetPlayableCardGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMTargetPlayableCardGroup() throws Exception {
        // Initialize the database
        mTargetPlayableCardGroupRepository.saveAndFlush(mTargetPlayableCardGroup);

        int databaseSizeBeforeDelete = mTargetPlayableCardGroupRepository.findAll().size();

        // Delete the mTargetPlayableCardGroup
        restMTargetPlayableCardGroupMockMvc.perform(delete("/api/m-target-playable-card-groups/{id}", mTargetPlayableCardGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MTargetPlayableCardGroup> mTargetPlayableCardGroupList = mTargetPlayableCardGroupRepository.findAll();
        assertThat(mTargetPlayableCardGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetPlayableCardGroup.class);
        MTargetPlayableCardGroup mTargetPlayableCardGroup1 = new MTargetPlayableCardGroup();
        mTargetPlayableCardGroup1.setId(1L);
        MTargetPlayableCardGroup mTargetPlayableCardGroup2 = new MTargetPlayableCardGroup();
        mTargetPlayableCardGroup2.setId(mTargetPlayableCardGroup1.getId());
        assertThat(mTargetPlayableCardGroup1).isEqualTo(mTargetPlayableCardGroup2);
        mTargetPlayableCardGroup2.setId(2L);
        assertThat(mTargetPlayableCardGroup1).isNotEqualTo(mTargetPlayableCardGroup2);
        mTargetPlayableCardGroup1.setId(null);
        assertThat(mTargetPlayableCardGroup1).isNotEqualTo(mTargetPlayableCardGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MTargetPlayableCardGroupDTO.class);
        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO1 = new MTargetPlayableCardGroupDTO();
        mTargetPlayableCardGroupDTO1.setId(1L);
        MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO2 = new MTargetPlayableCardGroupDTO();
        assertThat(mTargetPlayableCardGroupDTO1).isNotEqualTo(mTargetPlayableCardGroupDTO2);
        mTargetPlayableCardGroupDTO2.setId(mTargetPlayableCardGroupDTO1.getId());
        assertThat(mTargetPlayableCardGroupDTO1).isEqualTo(mTargetPlayableCardGroupDTO2);
        mTargetPlayableCardGroupDTO2.setId(2L);
        assertThat(mTargetPlayableCardGroupDTO1).isNotEqualTo(mTargetPlayableCardGroupDTO2);
        mTargetPlayableCardGroupDTO1.setId(null);
        assertThat(mTargetPlayableCardGroupDTO1).isNotEqualTo(mTargetPlayableCardGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mTargetPlayableCardGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mTargetPlayableCardGroupMapper.fromId(null)).isNull();
    }
}
