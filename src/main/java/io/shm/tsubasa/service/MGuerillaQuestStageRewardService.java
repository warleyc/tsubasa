package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuerillaQuestStageReward;
import io.shm.tsubasa.repository.MGuerillaQuestStageRewardRepository;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageRewardDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestStageRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuerillaQuestStageReward}.
 */
@Service
@Transactional
public class MGuerillaQuestStageRewardService {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestStageRewardService.class);

    private final MGuerillaQuestStageRewardRepository mGuerillaQuestStageRewardRepository;

    private final MGuerillaQuestStageRewardMapper mGuerillaQuestStageRewardMapper;

    public MGuerillaQuestStageRewardService(MGuerillaQuestStageRewardRepository mGuerillaQuestStageRewardRepository, MGuerillaQuestStageRewardMapper mGuerillaQuestStageRewardMapper) {
        this.mGuerillaQuestStageRewardRepository = mGuerillaQuestStageRewardRepository;
        this.mGuerillaQuestStageRewardMapper = mGuerillaQuestStageRewardMapper;
    }

    /**
     * Save a mGuerillaQuestStageReward.
     *
     * @param mGuerillaQuestStageRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuerillaQuestStageRewardDTO save(MGuerillaQuestStageRewardDTO mGuerillaQuestStageRewardDTO) {
        log.debug("Request to save MGuerillaQuestStageReward : {}", mGuerillaQuestStageRewardDTO);
        MGuerillaQuestStageReward mGuerillaQuestStageReward = mGuerillaQuestStageRewardMapper.toEntity(mGuerillaQuestStageRewardDTO);
        mGuerillaQuestStageReward = mGuerillaQuestStageRewardRepository.save(mGuerillaQuestStageReward);
        return mGuerillaQuestStageRewardMapper.toDto(mGuerillaQuestStageReward);
    }

    /**
     * Get all the mGuerillaQuestStageRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuerillaQuestStageRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuerillaQuestStageRewards");
        return mGuerillaQuestStageRewardRepository.findAll(pageable)
            .map(mGuerillaQuestStageRewardMapper::toDto);
    }


    /**
     * Get one mGuerillaQuestStageReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuerillaQuestStageRewardDTO> findOne(Long id) {
        log.debug("Request to get MGuerillaQuestStageReward : {}", id);
        return mGuerillaQuestStageRewardRepository.findById(id)
            .map(mGuerillaQuestStageRewardMapper::toDto);
    }

    /**
     * Delete the mGuerillaQuestStageReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuerillaQuestStageReward : {}", id);
        mGuerillaQuestStageRewardRepository.deleteById(id);
    }
}
