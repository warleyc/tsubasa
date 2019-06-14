package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestDropRateCampaignContent;
import io.shm.tsubasa.repository.MQuestDropRateCampaignContentRepository;
import io.shm.tsubasa.service.dto.MQuestDropRateCampaignContentDTO;
import io.shm.tsubasa.service.mapper.MQuestDropRateCampaignContentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestDropRateCampaignContent}.
 */
@Service
@Transactional
public class MQuestDropRateCampaignContentService {

    private final Logger log = LoggerFactory.getLogger(MQuestDropRateCampaignContentService.class);

    private final MQuestDropRateCampaignContentRepository mQuestDropRateCampaignContentRepository;

    private final MQuestDropRateCampaignContentMapper mQuestDropRateCampaignContentMapper;

    public MQuestDropRateCampaignContentService(MQuestDropRateCampaignContentRepository mQuestDropRateCampaignContentRepository, MQuestDropRateCampaignContentMapper mQuestDropRateCampaignContentMapper) {
        this.mQuestDropRateCampaignContentRepository = mQuestDropRateCampaignContentRepository;
        this.mQuestDropRateCampaignContentMapper = mQuestDropRateCampaignContentMapper;
    }

    /**
     * Save a mQuestDropRateCampaignContent.
     *
     * @param mQuestDropRateCampaignContentDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestDropRateCampaignContentDTO save(MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO) {
        log.debug("Request to save MQuestDropRateCampaignContent : {}", mQuestDropRateCampaignContentDTO);
        MQuestDropRateCampaignContent mQuestDropRateCampaignContent = mQuestDropRateCampaignContentMapper.toEntity(mQuestDropRateCampaignContentDTO);
        mQuestDropRateCampaignContent = mQuestDropRateCampaignContentRepository.save(mQuestDropRateCampaignContent);
        return mQuestDropRateCampaignContentMapper.toDto(mQuestDropRateCampaignContent);
    }

    /**
     * Get all the mQuestDropRateCampaignContents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestDropRateCampaignContentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestDropRateCampaignContents");
        return mQuestDropRateCampaignContentRepository.findAll(pageable)
            .map(mQuestDropRateCampaignContentMapper::toDto);
    }


    /**
     * Get one mQuestDropRateCampaignContent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestDropRateCampaignContentDTO> findOne(Long id) {
        log.debug("Request to get MQuestDropRateCampaignContent : {}", id);
        return mQuestDropRateCampaignContentRepository.findById(id)
            .map(mQuestDropRateCampaignContentMapper::toDto);
    }

    /**
     * Delete the mQuestDropRateCampaignContent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestDropRateCampaignContent : {}", id);
        mQuestDropRateCampaignContentRepository.deleteById(id);
    }
}
