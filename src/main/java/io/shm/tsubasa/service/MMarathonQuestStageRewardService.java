package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonQuestStageReward;
import io.shm.tsubasa.repository.MMarathonQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestStageRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonQuestStageReward}.
 */
@Service
@Transactional
public class MMarathonQuestStageRewardService {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestStageRewardService.class);

    private final MMarathonQuestStageRewardRepository mMarathonQuestStageRewardRepository;

    private final MMarathonQuestStageRewardMapper mMarathonQuestStageRewardMapper;

    public MMarathonQuestStageRewardService(MMarathonQuestStageRewardRepository mMarathonQuestStageRewardRepository, MMarathonQuestStageRewardMapper mMarathonQuestStageRewardMapper) {
        this.mMarathonQuestStageRewardRepository = mMarathonQuestStageRewardRepository;
        this.mMarathonQuestStageRewardMapper = mMarathonQuestStageRewardMapper;
    }

    /**
     * Save a mMarathonQuestStageReward.
     *
     * @param mMarathonQuestStageRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonQuestStageRewardDTO save(MMarathonQuestStageRewardDTO mMarathonQuestStageRewardDTO) {
        log.debug("Request to save MMarathonQuestStageReward : {}", mMarathonQuestStageRewardDTO);
        MMarathonQuestStageReward mMarathonQuestStageReward = mMarathonQuestStageRewardMapper.toEntity(mMarathonQuestStageRewardDTO);
        mMarathonQuestStageReward = mMarathonQuestStageRewardRepository.save(mMarathonQuestStageReward);
        return mMarathonQuestStageRewardMapper.toDto(mMarathonQuestStageReward);
    }

    /**
     * Get all the mMarathonQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonQuestStageRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonQuestStageRewards");
        return mMarathonQuestStageRewardRepository.findAll(pageable)
            .map(mMarathonQuestStageRewardMapper::toDto);
    }


    /**
     * Get one mMarathonQuestStageReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonQuestStageRewardDTO> findOne(Long id) {
        log.debug("Request to get MMarathonQuestStageReward : {}", id);
        return mMarathonQuestStageRewardRepository.findById(id)
            .map(mMarathonQuestStageRewardMapper::toDto);
    }

    /**
     * Delete the mMarathonQuestStageReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonQuestStageReward : {}", id);
        mMarathonQuestStageRewardRepository.deleteById(id);
    }
}
