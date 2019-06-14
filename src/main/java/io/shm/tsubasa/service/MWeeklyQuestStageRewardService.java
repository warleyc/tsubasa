package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MWeeklyQuestStageReward;
import io.shm.tsubasa.repository.MWeeklyQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestStageRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MWeeklyQuestStageReward}.
 */
@Service
@Transactional
public class MWeeklyQuestStageRewardService {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestStageRewardService.class);

    private final MWeeklyQuestStageRewardRepository mWeeklyQuestStageRewardRepository;

    private final MWeeklyQuestStageRewardMapper mWeeklyQuestStageRewardMapper;

    public MWeeklyQuestStageRewardService(MWeeklyQuestStageRewardRepository mWeeklyQuestStageRewardRepository, MWeeklyQuestStageRewardMapper mWeeklyQuestStageRewardMapper) {
        this.mWeeklyQuestStageRewardRepository = mWeeklyQuestStageRewardRepository;
        this.mWeeklyQuestStageRewardMapper = mWeeklyQuestStageRewardMapper;
    }

    /**
     * Save a mWeeklyQuestStageReward.
     *
     * @param mWeeklyQuestStageRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MWeeklyQuestStageRewardDTO save(MWeeklyQuestStageRewardDTO mWeeklyQuestStageRewardDTO) {
        log.debug("Request to save MWeeklyQuestStageReward : {}", mWeeklyQuestStageRewardDTO);
        MWeeklyQuestStageReward mWeeklyQuestStageReward = mWeeklyQuestStageRewardMapper.toEntity(mWeeklyQuestStageRewardDTO);
        mWeeklyQuestStageReward = mWeeklyQuestStageRewardRepository.save(mWeeklyQuestStageReward);
        return mWeeklyQuestStageRewardMapper.toDto(mWeeklyQuestStageReward);
    }

    /**
     * Get all the mWeeklyQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MWeeklyQuestStageRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MWeeklyQuestStageRewards");
        return mWeeklyQuestStageRewardRepository.findAll(pageable)
            .map(mWeeklyQuestStageRewardMapper::toDto);
    }


    /**
     * Get one mWeeklyQuestStageReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MWeeklyQuestStageRewardDTO> findOne(Long id) {
        log.debug("Request to get MWeeklyQuestStageReward : {}", id);
        return mWeeklyQuestStageRewardRepository.findById(id)
            .map(mWeeklyQuestStageRewardMapper::toDto);
    }

    /**
     * Delete the mWeeklyQuestStageReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MWeeklyQuestStageReward : {}", id);
        mWeeklyQuestStageRewardRepository.deleteById(id);
    }
}
