package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestGoalReward;
import io.shm.tsubasa.repository.MQuestGoalRewardRepository;
import io.shm.tsubasa.service.dto.MQuestGoalRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestGoalRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestGoalReward}.
 */
@Service
@Transactional
public class MQuestGoalRewardService {

    private final Logger log = LoggerFactory.getLogger(MQuestGoalRewardService.class);

    private final MQuestGoalRewardRepository mQuestGoalRewardRepository;

    private final MQuestGoalRewardMapper mQuestGoalRewardMapper;

    public MQuestGoalRewardService(MQuestGoalRewardRepository mQuestGoalRewardRepository, MQuestGoalRewardMapper mQuestGoalRewardMapper) {
        this.mQuestGoalRewardRepository = mQuestGoalRewardRepository;
        this.mQuestGoalRewardMapper = mQuestGoalRewardMapper;
    }

    /**
     * Save a mQuestGoalReward.
     *
     * @param mQuestGoalRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestGoalRewardDTO save(MQuestGoalRewardDTO mQuestGoalRewardDTO) {
        log.debug("Request to save MQuestGoalReward : {}", mQuestGoalRewardDTO);
        MQuestGoalReward mQuestGoalReward = mQuestGoalRewardMapper.toEntity(mQuestGoalRewardDTO);
        mQuestGoalReward = mQuestGoalRewardRepository.save(mQuestGoalReward);
        return mQuestGoalRewardMapper.toDto(mQuestGoalReward);
    }

    /**
     * Get all the mQuestGoalRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestGoalRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestGoalRewards");
        return mQuestGoalRewardRepository.findAll(pageable)
            .map(mQuestGoalRewardMapper::toDto);
    }


    /**
     * Get one mQuestGoalReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestGoalRewardDTO> findOne(Long id) {
        log.debug("Request to get MQuestGoalReward : {}", id);
        return mQuestGoalRewardRepository.findById(id)
            .map(mQuestGoalRewardMapper::toDto);
    }

    /**
     * Delete the mQuestGoalReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestGoalReward : {}", id);
        mQuestGoalRewardRepository.deleteById(id);
    }
}
