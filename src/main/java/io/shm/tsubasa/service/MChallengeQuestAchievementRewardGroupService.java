package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MChallengeQuestAchievementRewardGroup;
import io.shm.tsubasa.repository.MChallengeQuestAchievementRewardGroupRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestAchievementRewardGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MChallengeQuestAchievementRewardGroup}.
 */
@Service
@Transactional
public class MChallengeQuestAchievementRewardGroupService {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestAchievementRewardGroupService.class);

    private final MChallengeQuestAchievementRewardGroupRepository mChallengeQuestAchievementRewardGroupRepository;

    private final MChallengeQuestAchievementRewardGroupMapper mChallengeQuestAchievementRewardGroupMapper;

    public MChallengeQuestAchievementRewardGroupService(MChallengeQuestAchievementRewardGroupRepository mChallengeQuestAchievementRewardGroupRepository, MChallengeQuestAchievementRewardGroupMapper mChallengeQuestAchievementRewardGroupMapper) {
        this.mChallengeQuestAchievementRewardGroupRepository = mChallengeQuestAchievementRewardGroupRepository;
        this.mChallengeQuestAchievementRewardGroupMapper = mChallengeQuestAchievementRewardGroupMapper;
    }

    /**
     * Save a mChallengeQuestAchievementRewardGroup.
     *
     * @param mChallengeQuestAchievementRewardGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MChallengeQuestAchievementRewardGroupDTO save(MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO) {
        log.debug("Request to save MChallengeQuestAchievementRewardGroup : {}", mChallengeQuestAchievementRewardGroupDTO);
        MChallengeQuestAchievementRewardGroup mChallengeQuestAchievementRewardGroup = mChallengeQuestAchievementRewardGroupMapper.toEntity(mChallengeQuestAchievementRewardGroupDTO);
        mChallengeQuestAchievementRewardGroup = mChallengeQuestAchievementRewardGroupRepository.save(mChallengeQuestAchievementRewardGroup);
        return mChallengeQuestAchievementRewardGroupMapper.toDto(mChallengeQuestAchievementRewardGroup);
    }

    /**
     * Get all the mChallengeQuestAchievementRewardGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestAchievementRewardGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MChallengeQuestAchievementRewardGroups");
        return mChallengeQuestAchievementRewardGroupRepository.findAll(pageable)
            .map(mChallengeQuestAchievementRewardGroupMapper::toDto);
    }


    /**
     * Get one mChallengeQuestAchievementRewardGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MChallengeQuestAchievementRewardGroupDTO> findOne(Long id) {
        log.debug("Request to get MChallengeQuestAchievementRewardGroup : {}", id);
        return mChallengeQuestAchievementRewardGroupRepository.findById(id)
            .map(mChallengeQuestAchievementRewardGroupMapper::toDto);
    }

    /**
     * Delete the mChallengeQuestAchievementRewardGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MChallengeQuestAchievementRewardGroup : {}", id);
        mChallengeQuestAchievementRewardGroupRepository.deleteById(id);
    }
}
