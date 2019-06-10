package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MChallengeQuestStageReward;
import io.shm.tsubasa.repository.MChallengeQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestStageRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MChallengeQuestStageReward}.
 */
@Service
@Transactional
public class MChallengeQuestStageRewardService {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestStageRewardService.class);

    private final MChallengeQuestStageRewardRepository mChallengeQuestStageRewardRepository;

    private final MChallengeQuestStageRewardMapper mChallengeQuestStageRewardMapper;

    public MChallengeQuestStageRewardService(MChallengeQuestStageRewardRepository mChallengeQuestStageRewardRepository, MChallengeQuestStageRewardMapper mChallengeQuestStageRewardMapper) {
        this.mChallengeQuestStageRewardRepository = mChallengeQuestStageRewardRepository;
        this.mChallengeQuestStageRewardMapper = mChallengeQuestStageRewardMapper;
    }

    /**
     * Save a mChallengeQuestStageReward.
     *
     * @param mChallengeQuestStageRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MChallengeQuestStageRewardDTO save(MChallengeQuestStageRewardDTO mChallengeQuestStageRewardDTO) {
        log.debug("Request to save MChallengeQuestStageReward : {}", mChallengeQuestStageRewardDTO);
        MChallengeQuestStageReward mChallengeQuestStageReward = mChallengeQuestStageRewardMapper.toEntity(mChallengeQuestStageRewardDTO);
        mChallengeQuestStageReward = mChallengeQuestStageRewardRepository.save(mChallengeQuestStageReward);
        return mChallengeQuestStageRewardMapper.toDto(mChallengeQuestStageReward);
    }

    /**
     * Get all the mChallengeQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestStageRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MChallengeQuestStageRewards");
        return mChallengeQuestStageRewardRepository.findAll(pageable)
            .map(mChallengeQuestStageRewardMapper::toDto);
    }


    /**
     * Get one mChallengeQuestStageReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MChallengeQuestStageRewardDTO> findOne(Long id) {
        log.debug("Request to get MChallengeQuestStageReward : {}", id);
        return mChallengeQuestStageRewardRepository.findById(id)
            .map(mChallengeQuestStageRewardMapper::toDto);
    }

    /**
     * Delete the mChallengeQuestStageReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MChallengeQuestStageReward : {}", id);
        mChallengeQuestStageRewardRepository.deleteById(id);
    }
}
