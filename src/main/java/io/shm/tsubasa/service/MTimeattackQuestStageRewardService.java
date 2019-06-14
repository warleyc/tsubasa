package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTimeattackQuestStageReward;
import io.shm.tsubasa.repository.MTimeattackQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MTimeattackQuestStageRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTimeattackQuestStageReward}.
 */
@Service
@Transactional
public class MTimeattackQuestStageRewardService {

    private final Logger log = LoggerFactory.getLogger(MTimeattackQuestStageRewardService.class);

    private final MTimeattackQuestStageRewardRepository mTimeattackQuestStageRewardRepository;

    private final MTimeattackQuestStageRewardMapper mTimeattackQuestStageRewardMapper;

    public MTimeattackQuestStageRewardService(MTimeattackQuestStageRewardRepository mTimeattackQuestStageRewardRepository, MTimeattackQuestStageRewardMapper mTimeattackQuestStageRewardMapper) {
        this.mTimeattackQuestStageRewardRepository = mTimeattackQuestStageRewardRepository;
        this.mTimeattackQuestStageRewardMapper = mTimeattackQuestStageRewardMapper;
    }

    /**
     * Save a mTimeattackQuestStageReward.
     *
     * @param mTimeattackQuestStageRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MTimeattackQuestStageRewardDTO save(MTimeattackQuestStageRewardDTO mTimeattackQuestStageRewardDTO) {
        log.debug("Request to save MTimeattackQuestStageReward : {}", mTimeattackQuestStageRewardDTO);
        MTimeattackQuestStageReward mTimeattackQuestStageReward = mTimeattackQuestStageRewardMapper.toEntity(mTimeattackQuestStageRewardDTO);
        mTimeattackQuestStageReward = mTimeattackQuestStageRewardRepository.save(mTimeattackQuestStageReward);
        return mTimeattackQuestStageRewardMapper.toDto(mTimeattackQuestStageReward);
    }

    /**
     * Get all the mTimeattackQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackQuestStageRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTimeattackQuestStageRewards");
        return mTimeattackQuestStageRewardRepository.findAll(pageable)
            .map(mTimeattackQuestStageRewardMapper::toDto);
    }


    /**
     * Get one mTimeattackQuestStageReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTimeattackQuestStageRewardDTO> findOne(Long id) {
        log.debug("Request to get MTimeattackQuestStageReward : {}", id);
        return mTimeattackQuestStageRewardRepository.findById(id)
            .map(mTimeattackQuestStageRewardMapper::toDto);
    }

    /**
     * Delete the mTimeattackQuestStageReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTimeattackQuestStageReward : {}", id);
        mTimeattackQuestStageRewardRepository.deleteById(id);
    }
}
