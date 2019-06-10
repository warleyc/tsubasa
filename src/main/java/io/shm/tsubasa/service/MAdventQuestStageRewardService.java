package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAdventQuestStageReward;
import io.shm.tsubasa.repository.MAdventQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MAdventQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestStageRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAdventQuestStageReward}.
 */
@Service
@Transactional
public class MAdventQuestStageRewardService {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestStageRewardService.class);

    private final MAdventQuestStageRewardRepository mAdventQuestStageRewardRepository;

    private final MAdventQuestStageRewardMapper mAdventQuestStageRewardMapper;

    public MAdventQuestStageRewardService(MAdventQuestStageRewardRepository mAdventQuestStageRewardRepository, MAdventQuestStageRewardMapper mAdventQuestStageRewardMapper) {
        this.mAdventQuestStageRewardRepository = mAdventQuestStageRewardRepository;
        this.mAdventQuestStageRewardMapper = mAdventQuestStageRewardMapper;
    }

    /**
     * Save a mAdventQuestStageReward.
     *
     * @param mAdventQuestStageRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MAdventQuestStageRewardDTO save(MAdventQuestStageRewardDTO mAdventQuestStageRewardDTO) {
        log.debug("Request to save MAdventQuestStageReward : {}", mAdventQuestStageRewardDTO);
        MAdventQuestStageReward mAdventQuestStageReward = mAdventQuestStageRewardMapper.toEntity(mAdventQuestStageRewardDTO);
        mAdventQuestStageReward = mAdventQuestStageRewardRepository.save(mAdventQuestStageReward);
        return mAdventQuestStageRewardMapper.toDto(mAdventQuestStageReward);
    }

    /**
     * Get all the mAdventQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MAdventQuestStageRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MAdventQuestStageRewards");
        return mAdventQuestStageRewardRepository.findAll(pageable)
            .map(mAdventQuestStageRewardMapper::toDto);
    }


    /**
     * Get one mAdventQuestStageReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MAdventQuestStageRewardDTO> findOne(Long id) {
        log.debug("Request to get MAdventQuestStageReward : {}", id);
        return mAdventQuestStageRewardRepository.findById(id)
            .map(mAdventQuestStageRewardMapper::toDto);
    }

    /**
     * Delete the mAdventQuestStageReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAdventQuestStageReward : {}", id);
        mAdventQuestStageRewardRepository.deleteById(id);
    }
}
