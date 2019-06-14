package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestStageReward;
import io.shm.tsubasa.repository.MQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestStageRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestStageReward}.
 */
@Service
@Transactional
public class MQuestStageRewardService {

    private final Logger log = LoggerFactory.getLogger(MQuestStageRewardService.class);

    private final MQuestStageRewardRepository mQuestStageRewardRepository;

    private final MQuestStageRewardMapper mQuestStageRewardMapper;

    public MQuestStageRewardService(MQuestStageRewardRepository mQuestStageRewardRepository, MQuestStageRewardMapper mQuestStageRewardMapper) {
        this.mQuestStageRewardRepository = mQuestStageRewardRepository;
        this.mQuestStageRewardMapper = mQuestStageRewardMapper;
    }

    /**
     * Save a mQuestStageReward.
     *
     * @param mQuestStageRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestStageRewardDTO save(MQuestStageRewardDTO mQuestStageRewardDTO) {
        log.debug("Request to save MQuestStageReward : {}", mQuestStageRewardDTO);
        MQuestStageReward mQuestStageReward = mQuestStageRewardMapper.toEntity(mQuestStageRewardDTO);
        mQuestStageReward = mQuestStageRewardRepository.save(mQuestStageReward);
        return mQuestStageRewardMapper.toDto(mQuestStageReward);
    }

    /**
     * Get all the mQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestStageRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestStageRewards");
        return mQuestStageRewardRepository.findAll(pageable)
            .map(mQuestStageRewardMapper::toDto);
    }


    /**
     * Get one mQuestStageReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestStageRewardDTO> findOne(Long id) {
        log.debug("Request to get MQuestStageReward : {}", id);
        return mQuestStageRewardRepository.findById(id)
            .map(mQuestStageRewardMapper::toDto);
    }

    /**
     * Delete the mQuestStageReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestStageReward : {}", id);
        mQuestStageRewardRepository.deleteById(id);
    }
}
