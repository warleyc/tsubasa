package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MChallengeQuestAchievementReward;
import io.shm.tsubasa.repository.MChallengeQuestAchievementRewardRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestAchievementRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MChallengeQuestAchievementReward}.
 */
@Service
@Transactional
public class MChallengeQuestAchievementRewardService {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestAchievementRewardService.class);

    private final MChallengeQuestAchievementRewardRepository mChallengeQuestAchievementRewardRepository;

    private final MChallengeQuestAchievementRewardMapper mChallengeQuestAchievementRewardMapper;

    public MChallengeQuestAchievementRewardService(MChallengeQuestAchievementRewardRepository mChallengeQuestAchievementRewardRepository, MChallengeQuestAchievementRewardMapper mChallengeQuestAchievementRewardMapper) {
        this.mChallengeQuestAchievementRewardRepository = mChallengeQuestAchievementRewardRepository;
        this.mChallengeQuestAchievementRewardMapper = mChallengeQuestAchievementRewardMapper;
    }

    /**
     * Save a mChallengeQuestAchievementReward.
     *
     * @param mChallengeQuestAchievementRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MChallengeQuestAchievementRewardDTO save(MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO) {
        log.debug("Request to save MChallengeQuestAchievementReward : {}", mChallengeQuestAchievementRewardDTO);
        MChallengeQuestAchievementReward mChallengeQuestAchievementReward = mChallengeQuestAchievementRewardMapper.toEntity(mChallengeQuestAchievementRewardDTO);
        mChallengeQuestAchievementReward = mChallengeQuestAchievementRewardRepository.save(mChallengeQuestAchievementReward);
        return mChallengeQuestAchievementRewardMapper.toDto(mChallengeQuestAchievementReward);
    }

    /**
     * Get all the mChallengeQuestAchievementRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestAchievementRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MChallengeQuestAchievementRewards");
        return mChallengeQuestAchievementRewardRepository.findAll(pageable)
            .map(mChallengeQuestAchievementRewardMapper::toDto);
    }


    /**
     * Get one mChallengeQuestAchievementReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MChallengeQuestAchievementRewardDTO> findOne(Long id) {
        log.debug("Request to get MChallengeQuestAchievementReward : {}", id);
        return mChallengeQuestAchievementRewardRepository.findById(id)
            .map(mChallengeQuestAchievementRewardMapper::toDto);
    }

    /**
     * Delete the mChallengeQuestAchievementReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MChallengeQuestAchievementReward : {}", id);
        mChallengeQuestAchievementRewardRepository.deleteById(id);
    }
}
