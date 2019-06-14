package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonAchievementReward;
import io.shm.tsubasa.repository.MMarathonAchievementRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonAchievementRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonAchievementReward}.
 */
@Service
@Transactional
public class MMarathonAchievementRewardService {

    private final Logger log = LoggerFactory.getLogger(MMarathonAchievementRewardService.class);

    private final MMarathonAchievementRewardRepository mMarathonAchievementRewardRepository;

    private final MMarathonAchievementRewardMapper mMarathonAchievementRewardMapper;

    public MMarathonAchievementRewardService(MMarathonAchievementRewardRepository mMarathonAchievementRewardRepository, MMarathonAchievementRewardMapper mMarathonAchievementRewardMapper) {
        this.mMarathonAchievementRewardRepository = mMarathonAchievementRewardRepository;
        this.mMarathonAchievementRewardMapper = mMarathonAchievementRewardMapper;
    }

    /**
     * Save a mMarathonAchievementReward.
     *
     * @param mMarathonAchievementRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonAchievementRewardDTO save(MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO) {
        log.debug("Request to save MMarathonAchievementReward : {}", mMarathonAchievementRewardDTO);
        MMarathonAchievementReward mMarathonAchievementReward = mMarathonAchievementRewardMapper.toEntity(mMarathonAchievementRewardDTO);
        mMarathonAchievementReward = mMarathonAchievementRewardRepository.save(mMarathonAchievementReward);
        return mMarathonAchievementRewardMapper.toDto(mMarathonAchievementReward);
    }

    /**
     * Get all the mMarathonAchievementRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonAchievementRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonAchievementRewards");
        return mMarathonAchievementRewardRepository.findAll(pageable)
            .map(mMarathonAchievementRewardMapper::toDto);
    }


    /**
     * Get one mMarathonAchievementReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonAchievementRewardDTO> findOne(Long id) {
        log.debug("Request to get MMarathonAchievementReward : {}", id);
        return mMarathonAchievementRewardRepository.findById(id)
            .map(mMarathonAchievementRewardMapper::toDto);
    }

    /**
     * Delete the mMarathonAchievementReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonAchievementReward : {}", id);
        mMarathonAchievementRewardRepository.deleteById(id);
    }
}
