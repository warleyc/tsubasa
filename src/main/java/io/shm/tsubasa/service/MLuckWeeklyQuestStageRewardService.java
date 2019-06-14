package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLuckWeeklyQuestStageReward;
import io.shm.tsubasa.repository.MLuckWeeklyQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MLuckWeeklyQuestStageRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLuckWeeklyQuestStageReward}.
 */
@Service
@Transactional
public class MLuckWeeklyQuestStageRewardService {

    private final Logger log = LoggerFactory.getLogger(MLuckWeeklyQuestStageRewardService.class);

    private final MLuckWeeklyQuestStageRewardRepository mLuckWeeklyQuestStageRewardRepository;

    private final MLuckWeeklyQuestStageRewardMapper mLuckWeeklyQuestStageRewardMapper;

    public MLuckWeeklyQuestStageRewardService(MLuckWeeklyQuestStageRewardRepository mLuckWeeklyQuestStageRewardRepository, MLuckWeeklyQuestStageRewardMapper mLuckWeeklyQuestStageRewardMapper) {
        this.mLuckWeeklyQuestStageRewardRepository = mLuckWeeklyQuestStageRewardRepository;
        this.mLuckWeeklyQuestStageRewardMapper = mLuckWeeklyQuestStageRewardMapper;
    }

    /**
     * Save a mLuckWeeklyQuestStageReward.
     *
     * @param mLuckWeeklyQuestStageRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MLuckWeeklyQuestStageRewardDTO save(MLuckWeeklyQuestStageRewardDTO mLuckWeeklyQuestStageRewardDTO) {
        log.debug("Request to save MLuckWeeklyQuestStageReward : {}", mLuckWeeklyQuestStageRewardDTO);
        MLuckWeeklyQuestStageReward mLuckWeeklyQuestStageReward = mLuckWeeklyQuestStageRewardMapper.toEntity(mLuckWeeklyQuestStageRewardDTO);
        mLuckWeeklyQuestStageReward = mLuckWeeklyQuestStageRewardRepository.save(mLuckWeeklyQuestStageReward);
        return mLuckWeeklyQuestStageRewardMapper.toDto(mLuckWeeklyQuestStageReward);
    }

    /**
     * Get all the mLuckWeeklyQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckWeeklyQuestStageRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLuckWeeklyQuestStageRewards");
        return mLuckWeeklyQuestStageRewardRepository.findAll(pageable)
            .map(mLuckWeeklyQuestStageRewardMapper::toDto);
    }


    /**
     * Get one mLuckWeeklyQuestStageReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLuckWeeklyQuestStageRewardDTO> findOne(Long id) {
        log.debug("Request to get MLuckWeeklyQuestStageReward : {}", id);
        return mLuckWeeklyQuestStageRewardRepository.findById(id)
            .map(mLuckWeeklyQuestStageRewardMapper::toDto);
    }

    /**
     * Delete the mLuckWeeklyQuestStageReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLuckWeeklyQuestStageReward : {}", id);
        mLuckWeeklyQuestStageRewardRepository.deleteById(id);
    }
}
